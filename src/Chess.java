import Pieces.*;

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
        Pawn pawnw1 = new Pawn(0,new Position('a',1));
        Pawn pawnw2 = new Pawn(0,new Position('b',1));
        Pawn pawnw3 = new Pawn(0,new Position('c',1));
        Pawn pawnw4 = new Pawn(0,new Position('d',1));
        Pawn pawnw5 = new Pawn(0,new Position('e',1));
        Pawn pawnw6 = new Pawn(0,new Position('f',1));
        Pawn pawnw7 = new Pawn(0,new Position('g',1));
        Pawn pawnw8 = new Pawn(0,new Position('h',1));
        Rook rookw1 = new Rook(0,new Position('a',0));
        Rook rookw2 = new Rook(0,new Position('h',0));
        Knight knightw1 = new Knight(0,new Position('b',0));
        Knight knightw2 = new Knight(0,new Position('g',0));
        Bishop bishopw1 = new Bishop(0,new Position('c',0));
        Bishop bishopw2 = new Bishop(0,new Position('f',0));
        King kingw = new King(0,new Position('e',0));
        Queen queenw = new Queen(0,new Position('d',0));

        Pawn pawnb1 = new Pawn(0,new Position('a',7));
        Pawn pawnb2 = new Pawn(0,new Position('b',7));
        Pawn pawnb3 = new Pawn(0,new Position('c',7));
        Pawn pawnb4 = new Pawn(0,new Position('d',7));
        Pawn pawnb5 = new Pawn(0,new Position('e',7));
        Pawn pawnb6 = new Pawn(0,new Position('f',7));
        Pawn pawnb7 = new Pawn(0,new Position('g',7));
        Pawn pawnb8 = new Pawn(0,new Position('h',7));
        Rook rookb1 = new Rook(0,new Position('a',6));
        Rook rookb2 = new Rook(0,new Position('h',6));
        Knight knightb1 = new Knight(0,new Position('b',6));
        Knight knightb2 = new Knight(0,new Position('g',6));
        Bishop bishopb1 = new Bishop(0,new Position('c',6));
        Bishop bishopb2 = new Bishop(0,new Position('f',6));
        King kingb = new King(0,new Position('e',6));
        Queen queenb = new Queen(0,new Position('d',6));
        //place pieces in starting position ?
    }
    private void printBoard(){}
    private String askMove(){
        String pieceToMove;
        String nextMove;
        do {
            System.out.println("What piece do you want to move ?(ex : Pb2)");
            pieceToMove = ask_something.nextLine();
        }while (pieceToMove=="");
        do {
            System.out.println("Where do you want to move it ?(ex : b3)");
            nextMove = ask_something.nextLine();
        }while (nextMove=="");
        return(pieceToMove+" "+nextMove);
    }
    private boolean isValidMove(String move){

        return true;
    }
    private boolean isCheckMate(){
        //does the king have a valid move ?
        //can any other piece counter the check ?
        return false;
    }
    private void updateBoard(String move){}
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