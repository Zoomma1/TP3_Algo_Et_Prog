import java.util.Scanner;

public class Chess {
    private Cell[][] board;
    private Player[] players = new Player[2];
    private Player currentPlayer;
    Scanner ask_something = new Scanner(System.in);
    public void play() {
        while (true) {
            createPlayers();
            initialiseBoard();
            while (!isCheckMate()) {
                printBoard();
                String move;
                do {
                    move = askMove();
                }
                while (!isValidMove(move));
                updateBoard(move);
                switchPlayer();
            }
        }
    }

    private void createPlayers(){
        System.out.println("White player's name ?");
        Player player1 = new Player(ask_something.nextLine(),0);
        players[0]=player1;
        currentPlayer = players[0];
        System.out.println("Black player's name ?");
        Player player2 = new Player(ask_something.nextLine(),1);
        players[1]=player2;
    }
    private void initialiseBoard() {
        board = new Cell[8][8];
//      White pawn initialisation
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Cell(new Position((char) ('a' + i), 2), new Pawn(0, new Position((char) ('a' + i), 2)));
        }
//      Black pawn initialisation
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Cell(new Position((char) ('a' + i), 7), new Pawn(1, new Position((char) ('a' + i), 7)));
        }
//      Rook initialisation
        board[0][0] = new Cell(new Position('a', 1), new Rook(0, new Position('a', 1)));
        board[0][7] = new Cell(new Position('h', 1), new Rook(0, new Position('h', 1)));
        board[7][0] = new Cell(new Position('a', 8), new Rook(1, new Position('a', 8)));
        board[7][7] = new Cell(new Position('h', 8), new Rook(1, new Position('h', 8)));
//      Knight initialisation
        board[0][1] = new Cell(new Position('b', 1), new Knight(0, new Position('b', 1)));
        board[0][6] = new Cell(new Position('g', 1), new Knight(0, new Position('g', 1)));
        board[7][1] = new Cell(new Position('b', 8), new Knight(1, new Position('b', 8)));
        board[7][6] = new Cell(new Position('g', 8), new Knight(1, new Position('g', 8)));
//      Bishop Initialisation
        board[0][2] = new Cell(new Position('c', 1), new Bishop(0, new Position('c', 1)));
        board[0][5] = new Cell(new Position('f', 1), new Bishop(0, new Position('f', 1)));
        board[7][2] = new Cell(new Position('c', 8), new Bishop(1, new Position('c', 8)));
        board[7][5] = new Cell(new Position('f', 8), new Bishop(1, new Position('f', 8)));
//      Queen initialisation
        board[0][4] = new Cell(new Position('d', 1), new Queen(0, new Position('d', 1)));
        board[7][4] = new Cell(new Position('d', 8), new Queen(1, new Position('d', 8)));
//      King initialisation
        board[0][3] = new Cell(new Position('e', 1), new King(0, new Position('e', 1)));
        board[7][3] = new Cell(new Position('e', 8), new King(1, new Position('e', 8)));
//      Empty cell initialisation
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Cell(new Position((char) ('a' + j), i + 1), null);
            }
        }
    }
    private void printBoard(){
        for (int i = 0; i < 8; i++) {
            System.out.printf("%d",i+1);
            for (int j = 0; j < 8; j++) {
                if(board[i][j].getPieces()!=null) {
                    System.out.printf("| %s ", board[i][j].getPieces().toString());
                }
                else System.out.printf("|   ");
            }
            System.out.println("|");
        }
        for (int i = 0; i < 8; i++) {
            System.out.printf("   %c",(char)('a'+i));
        }
        System.out.println();
    }
    private String askMove(){
        String pieceToMove;
        String nextMove;
        System.out.printf("%s ,\n",players[currentPlayer.color]);
        do {
            System.out.println("What piece do you want to move ?(ex : Pb2)");
            pieceToMove = ask_something.nextLine();
        }while ("".equals(pieceToMove));
        do {
            System.out.println("Where do you want to move it ?(ex : b3)");
            nextMove = ask_something.nextLine();
        }while ("".equals(nextMove));
        String move = (pieceToMove+" "+nextMove);
        if (move.length() != 6){
            System.out.println("Unknown move please insert a correct one ");
            move = askMove();
        }
        return(move);
    }
    private boolean isValidMove(String move) {
//      Gather starting coordinates
        int startX = move.charAt(1) - 97;
        int startY = move.charAt(2) - 49;
//      Gather destination coordinates
        char destX = move.charAt(4);
        int destY = move.charAt(5) - 48;
        Pieces pieceToMove = board[startY][startX].pieces;
        if (pieceToMove != null) {
            if (pieceToMove.toString().equals(move.substring(0, 1))) {
                if (pieceToMove.getColor() == currentPlayer.color) {
                    if (pieceToMove instanceof Pawn) {
                        Pawn pawn = (Pawn) pieceToMove;
                        if (pawn.isValidMove(new Position(destX, destY), board)) {
                            return true;
                        } else {
                            System.out.println("Move is not valid!");
                            return false;
                        }
                    }
                    if (pieceToMove instanceof Rook) {
                        Rook rook = (Rook) pieceToMove;
                        if (rook.isValidMove(new Position(destX, destY), board)) {
                            return true;
                        } else {
                            System.out.println("Move is not valid!");
                            return false;
                        }
                    }
                    if (pieceToMove instanceof Knight) {
                        Knight knight = (Knight) pieceToMove;
                        if (knight.isValidMove(new Position(destX, destY), board)) {
                            return true;
                        } else {
                            System.out.println("Move is not valid!");
                            return false;
                        }
                    } else if (pieceToMove instanceof Bishop) {
                        Bishop bishop = (Bishop) pieceToMove;
                        if (bishop.isValidMove(new Position(destX, destY), board)) {
                            return true;
                        } else {
                            System.out.println("Move is not valid!");
                            return false;
                        }
                    } else if (pieceToMove instanceof Queen) {
                        Queen queen = (Queen) pieceToMove;
                        if (queen.isValidMove(new Position(destX, destY), board)) {
                            return true;
                        } else {
                            System.out.println("Move is not valid!");
                            return false;
                        }
                    } else if (pieceToMove instanceof King) {
                        King king = (King) pieceToMove;
                        if (king.isValidMove(new Position(destX, destY), board)) {
                            return true;
                        } else {
                            System.out.println("Move is not valid!");
                            return false;
                        }
                    } else {
                        System.out.println("Unknown piece type!");
                        return false;
                    }
                } else {
                    System.out.println("Piece color does not match the player!");
                    return false;
                }
            } else {
                System.out.println("No " + move.charAt(0) + " on " + startX + startY);
                    return false;
            }
        }
        else {
                System.out.println("No piece on the starting square!");
                return false;
            }
    }
    private boolean isCheckMate(){
//        does the king have a valid move ?
//        can any other piece counter the check ?
        return false;
    }
    private void updateBoard(String move){
        int startX = move.charAt(1) - 97;
        int startY = move.charAt(2) - 49;
        int destX = move.charAt(4) - 97;
        int destY = move.charAt(5) - 49;
        Pieces pieces = board[startY][startX].getPieces();
        pieces.setPosition(board[destY][destX].position);
        board[startY][startX].setPieces(null);
        board[destY][destX].setPieces(pieces);
    }
    private void switchPlayer(){
        if (currentPlayer.equals(players[0])){
            currentPlayer = players[1];
            System.out.printf("%s's turn to play !\n",currentPlayer);
        }
        else{
            currentPlayer = players[0];
            System.out.printf("%s's turn to play !\n",currentPlayer);
        }
    }
}