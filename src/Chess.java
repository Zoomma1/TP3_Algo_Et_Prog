import java.util.ArrayList;
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
                System.out.printf("%s's turn to play !\n",currentPlayer);
                printBoard();
                String move;
                if(isCheck(currentPlayer.color, board)){
                    System.out.printf("%s's king is check !\n",currentPlayer.getStringColor());
                }
                do {
                    move = askMove();
                }
                while (!isValidMove(move));
                updateBoard(move);
                switchPlayer();
            }
            printBoard();
            System.out.println(currentPlayer.name + " lost the game !");
            String newGame = "";
            do{
                System.out.println("New game Y/N ?");
                newGame = ask_something.nextLine();
                System.out.println(newGame);
            }while (!newGame.equals("Y") && !newGame.equals("N") && !newGame.equals("y") && !newGame.equals("n") );
            if (newGame.equals("N") || newGame.equals("n")){
                break;
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
        board[0][3] = new Cell(new Position('d', 1), new Queen(0, new Position('d', 1)));
        board[7][3] = new Cell(new Position('d', 8), new Queen(1, new Position('d', 8)));
//      King initialisation
        board[0][4] = new Cell(new Position('e', 1), new King(0, new Position('e', 1)));
        board[7][4] = new Cell(new Position('e', 8), new King(1, new Position('e', 8)));
//      Empty cell initialisation
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Cell(new Position((char) ('a' + j), i + 1), null);
            }
        }
    }
    private void printBoard(){
        for (int i = 0; i < 8; i++) {
            System.out.printf("%d",8 - i);
            for (int j = 0; j < 8; j++) {
                if(board[7 - i][j].getPieces()!=null) {
                    System.out.printf("| %s ", board[7 - i][j].getPieces().toString());
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
        do {
            System.out.println("What piece do you want to move ?(ex : Pb2)");
            pieceToMove = ask_something.nextLine();
//          Checking if player wants to castle (WIP)
            if(pieceToMove.equals("O-O") || pieceToMove.equals("O-O-O")){
                return pieceToMove;
            }
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
        int currentPlayerColor = currentPlayer.color;
//      Is casteling a valid move ? (WIP)
        if(move.equals("O-O") || move.equals("O-O-O")){
            int kingRow = findKing(currentPlayerColor,board).row;
            return castle(new Position('h',kingRow),board);
            }
        else if(move.equals("O-O-O")){
            int kingRow = findKing(currentPlayerColor,board).row;
            return castle(new Position('a',kingRow),board);
        }
//      Gather starting coordinates
        int startX = move.charAt(1) - 97;
        int startY = move.charAt(2) - 49;
//      Gather destination coordinates
        char destX = move.charAt(4);
        int destY = move.charAt(5) - 48;
        Pieces pieceToMove = board[startY][startX].pieces;
        if (pieceToMove != null) {
            if (pieceToMove.pieceStringType().equals(move.substring(0, 1))) {
                if (pieceToMove.getColor() == currentPlayerColor) {
                    if(!isMoveLegal(board,move,currentPlayerColor)){
                        System.out.println("Move put your king in check or your king is still in check !");
                        return false;
                    }
                    if (pieceToMove instanceof Pawn) {
                        Pawn pawn = (Pawn) pieceToMove;
                        if (pawn.isValidMove(new Position(destX, destY), board)) {
                            if(destY == 8 || destY==1){
                                String promotion;
                                do{
                                    System.out.println("What piece do you want to promote to ? (Q R N B)");
                                    Scanner scanner = new Scanner(System.in);
                                    promotion = scanner.nextLine();
                                }while (promotion.length() != 1 && (promotion.equals("Q") || promotion.equals("R") || promotion.equals("N") || promotion.equals("B")));
                                switch (promotion){
                                    case "Q":
                                        board[startY][startX].setPieces(new Queen(currentPlayerColor,pawn.position));
                                        break;
                                    case "R":
                                        board[startY][startX].setPieces(new Rook(currentPlayerColor,pawn.position));
                                        break;
                                    case "N":
                                        board[startY][startX].setPieces(new Knight(currentPlayerColor,pawn.position));
                                        break;
                                    case "B":
                                        board[startY][startX].setPieces(new Bishop(currentPlayerColor,pawn.position));
                                        break;
                                }
                            }
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
                System.out.println("No " + move.charAt(0) + " on " + move.charAt(1) + move.charAt(2));
                    return false;
            }
        }
        else {
                System.out.println("No piece on the starting square!");
                return false;
            }
    }
    private boolean isCheckMate(){
        if (!isCheck(currentPlayer.color, board)) {
            return false;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Cell cell = board[i][j];
                if (!cell.isEmpty() && cell.getPieces().getColor() == currentPlayer.color) {
                    ArrayList<Position> possibleMoves = cell.getPieces().generatePossibleMoves(board);
                    for (Position move : possibleMoves) {
                        Cell[][] tmpBoard = copyBoard(board);
                        updatetmpBoard(cell.getPieces().pieceStringType() + cell.getPieces().position.toString() + " " + move.toString(),tmpBoard);
                        if (!isCheck(currentPlayer.color, tmpBoard)) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    private void updateBoard(String move){
        int startX = move.charAt(1) - 97;
        int startY = move.charAt(2) - 49;
        int destX = move.charAt(4) - 97;
        int destY = move.charAt(5) - 49;
        Pieces pieces = board[startY][startX].getPieces();
        pieces.setNewPosition(board[destY][destX].position);
        board[startY][startX].setPieces(null);
        board[destY][destX].setPieces(pieces);
    }
    private void updatetmpBoard(String move , Cell[][] tmpBoard){
        int startX = move.charAt(1) - 97;
        int startY = move.charAt(2) - 49;
        int destX = move.charAt(4) - 97;
        int destY = move.charAt(5) - 49;
        Pieces pieces = tmpBoard[startY][startX].getPieces();
        pieces.setNewPosition(tmpBoard[destY][destX].position);
        tmpBoard[startY][startX].setPieces(null);
        tmpBoard[destY][destX].setPieces(pieces);
    }
    private void switchPlayer(){
        if (currentPlayer.equals(players[0])){
            currentPlayer = players[1];
        }
        else{
            currentPlayer = players[0];
        }
    }
    public Position findKing(int color, Cell[][] board){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(!board[j][i].isEmpty() && board[j][i].getPieces() instanceof King && board[j][i].getPieces().color == color){
                    return board[j][i].position;
                }
            }
        }
        return null;
    }
    public boolean isCheck(int color, Cell[][] board){
        Position kingPosition = findKing(color,board);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!board[j][i].isEmpty() && board[j][i].getPieces().color != color && board[j][i].getPieces().isValidMove(kingPosition , board)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isMoveLegal(Cell[][] board, String move, int playerColor) {
        Cell[][] tmpBoard = copyBoard(board);
        updatetmpBoard(move,tmpBoard);
        if (isCheck(playerColor, tmpBoard)) {
            return false;
        }
        return true;
    }
    private Cell[][] copyBoard(Cell[][] originalBoard) {
        Cell[][] newBoard = new Cell[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Cell originalCell = originalBoard[row][col];
                if (originalCell != null) {
                    Position position = originalCell.position.copy();
                    Pieces originalPiece = originalCell.getPieces();
                    Pieces newPiece = null;
                    if (originalPiece != null) {
                        newPiece = originalPiece.createNewPieces();
                        newPiece.setNewPosition(position);
                    }
                    newBoard[row][col] = new Cell(position, newPiece);
                } else {
                    newBoard[row][col] = new Cell(new Position((char) ('a' + col), 8 - row), null);
                }
            }
        }
        return newBoard;
    }
//  WIP
    public boolean castle(Position newPosition, Cell[][] board) {
        if (!isValidCastleMove(newPosition, board)) {
            return false;
        }
        Position kingPosition = findKing(currentPlayer.color, board);
        King king = new King(currentPlayer.color,kingPosition);
        int startX = kingPosition.column - 'a';
        int startY = kingPosition.row - 1;
        int destX = newPosition.column - 'a';
        int destY = newPosition.row - 1;
        String newPositionMove = king.pieceStringType() + kingPosition.toString() + " " + newPosition.toString();
        if (isCheck(currentPlayer.color, board) || isMoveLegal(board, newPositionMove, currentPlayer.color)) {
            return false;
        }
        if (destX > startX) {
            board[startY][startX].setPieces(null);
            board[destY][destX] = board[startY][startX];
            board[startY][startX + 3] = board[startY][startX + 1];
            board[startY][startX + 1] = null;
            return true;
        } else {
            board[startY][startX].setPieces(null);
            board[destY][destX] = board[startY][startX];
            board[startY][startX - 4] = board[startY][startX - 2];
            board[startY][startX - 2] = null;
            return true;
        }
    }
//  WIP
    public boolean isValidCastleMove(Position newPosition, Cell[][] board) {
        Position kingPosition = findKing(currentPlayer.color, board);
        int deltaX = Math.abs(newPosition.column - kingPosition.column);
        int deltaY = Math.abs(newPosition.row - kingPosition.row);
        if (deltaX != 2 || deltaY != 0) {
            return false;
        }
        if (newPosition.column > kingPosition.column) {
            for (int i = kingPosition.column + 1; i < newPosition.column; i++) {
                if (!board[kingPosition.row - 1][i - 'a'].isEmpty()) {
                    return false;
                }
            }
        } else {
            for (int i = kingPosition.column - 1; i > newPosition.column; i--) {
                if (!board[kingPosition.row - 1][i - 'a'].isEmpty()) {
                    return false;
                }
            }
        }
        if (kingPosition.row != newPosition.row || (kingPosition.column != 'e' && kingPosition.column != 'c')) {
            return false;
        }
        return true;
    }
}