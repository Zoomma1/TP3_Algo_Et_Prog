import java.util.Arrays;
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
    private void initialiseBoard(){
        board = new Cell[8][8];
        //      Empty cell creation
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = new Cell(new Position((char)('a'+i),j),true,' ');
            }
        }
//      White pieces creation
        Pawn pawnw1 = new Pawn(0,new Position('a',2));
        Pawn pawnw2 = new Pawn(0,new Position('b',2));
        Pawn pawnw3 = new Pawn(0,new Position('c',2));
        Pawn pawnw4 = new Pawn(0,new Position('d',2));
        Pawn pawnw5 = new Pawn(0,new Position('e',2));
        Pawn pawnw6 = new Pawn(0,new Position('f',2));
        Pawn pawnw7 = new Pawn(0,new Position('g',2));
        Pawn pawnw8 = new Pawn(0,new Position('h',2));
        board[0][1] = new Cell(pawnw1.position,false,'P');
        board[1][1] = new Cell(pawnw2.position,false,'P');
        board[2][1] = new Cell(pawnw3.position,false,'P');
        board[3][1] = new Cell(pawnw4.position,false,'P');
        board[4][1] = new Cell(pawnw5.position,false,'P');
        board[5][1] = new Cell(pawnw6.position,false,'P');
        board[6][1] = new Cell(pawnw7.position,false,'P');
        board[7][1] = new Cell(pawnw8.position,false,'P');
        Rook rookw1 = new Rook(0,new Position('a',1));
        Rook rookw2 = new Rook(0,new Position('h',1));
        board[0][0] = new Cell(rookw1.position,false,'R');
        board[7][0] = new Cell(rookw2.position,false,'R');
        Knight knightw1 = new Knight(0,new Position('b',1));
        Knight knightw2 = new Knight(0,new Position('g',1));
        board[1][0] = new Cell(knightw1.position,false,'N');
        board[6][0] = new Cell(knightw2.position,false,'N');
        Bishop bishopw1 = new Bishop(0,new Position('c',1));
        Bishop bishopw2 = new Bishop(0,new Position('f',1));
        board[2][0] = new Cell(bishopw1.position,false,'B');
        board[5][0] = new Cell(bishopw2.position,false,'B');
        King kingw = new King(0,new Position('e',1));
        board[4][0] = new Cell(kingw.position,false,'K');
        Queen queenw = new Queen(0,new Position('d',1));
        board[3][0] = new Cell(queenw.position,false,'Q');
//      Black pieces creation
        Pawn pawnb1 = new Pawn(1,new Position('a',7));
        Pawn pawnb2 = new Pawn(1,new Position('b',7));
        Pawn pawnb3 = new Pawn(1,new Position('c',7));
        Pawn pawnb4 = new Pawn(1,new Position('d',7));
        Pawn pawnb5 = new Pawn(1,new Position('e',7));
        Pawn pawnb6 = new Pawn(1,new Position('f',7));
        Pawn pawnb7 = new Pawn(1,new Position('g',7));
        Pawn pawnb8 = new Pawn(1,new Position('h',7));
        board[0][6] = new Cell(pawnb1.position,false,'P');
        board[1][6] = new Cell(pawnb2.position,false,'P');
        board[2][6] = new Cell(pawnb3.position,false,'P');
        board[3][6] = new Cell(pawnb4.position,false,'P');
        board[4][6] = new Cell(pawnb5.position,false,'P');
        board[5][6] = new Cell(pawnb6.position,false,'P');
        board[6][6] = new Cell(pawnb7.position,false,'P');
        board[7][6] = new Cell(pawnb8.position,false,'P');
        Rook rookb1 = new Rook(1,new Position('a',8));
        Rook rookb2 = new Rook(1,new Position('h',8));
        board[0][7] = new Cell(rookb1.position,false,'R');
        board[7][7] = new Cell(rookb2.position,false,'R');
        Knight knightb1 = new Knight(1,new Position('b',8));
        Knight knightb2 = new Knight(1,new Position('g',8));
        board[1][7] = new Cell(knightb1.position,false,'N');
        board[6][7] = new Cell(knightb2.position,false,'N');
        Bishop bishopb1 = new Bishop(1,new Position('c',8));
        Bishop bishopb2 = new Bishop(1,new Position('f',8));
        board[2][7] = new Cell(bishopb1.position,false,'B');
        board[5][7] = new Cell(bishopb2.position,false,'B');
        King kingb = new King(1,new Position('e',8));
        board[4][7] = new Cell(kingb.position,false,'K');
        Queen queenb = new Queen(1,new Position('d',8));
        board[3][7] = new Cell(queenb.position,false,'Q');
    }
    private void printBoard(){
        for (int i = 7; i >= 0; i--) {
            System.out.printf("%d",i+1);
            for (int j = 7; j >= 0; j--) {
                System.out.printf("| %c ",board[j][i].pieces);
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
        if(currentPlayer==players[0]) System.out.println("White player,");
        else System.out.println("Black player:");
        do {
            System.out.println("What piece do you want to move ?(ex : Pb2)");
            pieceToMove = ask_something.nextLine();
        }while ("".equals(pieceToMove));
        do {
            System.out.println("Where do you want to move it ?(ex : b3)");
            nextMove = ask_something.nextLine();
        }while ("".equals(nextMove));
        return(pieceToMove+" "+nextMove);
    }
    private boolean isValidMove(String move){
//      WIP
//      If cell !isEmpty check color
//      Manage to get piece to move
        int y = move.charAt(2)-48;
        String pieceToMove = move.substring(0,1);
        switch (pieceToMove) {
            case "P" -> {
                Pawn pawn = new Pawn(0, new Position(move.charAt(1), y));
                if(pieceToMove.equals(pawn.toString())){
                    if (pawn.isValidMove(new Position(move.charAt(1), y), board)){
                        return true;
                    }
                    else {
                        System.out.println("Move is not valid !");
                        return false;
                    }
                }
                return false;
            }
            case "B" -> {
                Bishop bishop = new Bishop(0, new Position(move.charAt(0), y));
                if(pieceToMove.equals(bishop.toString())){
                    return bishop.isValidMove(new Position(move.charAt(1), y), board);
                }
                return false;
            }
            case "N" -> {
                Knight knight = new Knight(0, new Position(move.charAt(0), y));
                if(pieceToMove.equals(knight.toString())){
                    return knight.isValidMove(new Position(move.charAt(1), y), board);
                }
                return false;
            }
            case "R" -> {
                Rook rook = new Rook(0, new Position(move.charAt(0), y));
                if(pieceToMove.equals(rook.toString())){
                    return rook.isValidMove(new Position(move.charAt(1), y), board);
                }
                return false;
            }
            case "K" -> {
                King king = new King(0, new Position(move.charAt(0), y));
                if(pieceToMove.equals(king.toString())){
                    return king.isValidMove(new Position(move.charAt(1), y), board);
                }
                return false;
            }
            case "Q" -> {
                Queen queen = new Queen(0, new Position(move.charAt(0), y));
                if(pieceToMove.equals(queen.toString())){
                    return queen.isValidMove(new Position(move.charAt(1), y), board);
                }
                return false;
            }
        }
        System.out.println("Move is not valid !");
        return false;
    }
    private boolean isCheckMate(){
//        does the king have a valid move ?
//        can any other piece counter the check ?
        return false;
    }
    private void updateBoard(String move){
        String pieceToMove = move.substring(0,1);
        board[(int)(move.charAt(1))-97][(int)(move.charAt(2))-48].setEmpty(true);
        board[(int)(move.charAt(4))-97][(int)(move.charAt(5))-48].setEmpty(false);
        board[(int)(move.charAt(1))-97][(int)(move.charAt(2))-48].setPieces(' ');
        board[(int)(move.charAt(4))-97][(int)(move.charAt(5))-48].setPieces(move.charAt(0));
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