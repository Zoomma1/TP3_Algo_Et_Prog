import Pieces.Player;

import java.util.Scanner;

public class Chess {
    private Cell[][] board;
    private Player[] players;
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
        players[1]=player1;
        currentPlayer = players[1];
        System.out.println("Black player's name ?");
        Player player2 = new Player(ask_something.nextLine(),1);
        players[2]=player2;
    }
    private void initialiseBoard(){
        //create board
        //place pieces in starting position ?
    }
    private void printBoard(){}
    private String askMove(){
        String pieceToMove;
        String nextMove;
        do {
            System.out.println("What piece do you want to move ?");
            pieceToMove = ask_something.nextLine();
        }while (pieceToMove=="");
        do {
            System.out.println("Where do you want to move it ?");
            nextMove = ask_something.nextLine();
        }while (nextMove=="");
        return(pieceToMove+" "+nextMove);
    }
    private boolean isValidMove(String move){

        return false;
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
            System.out.printf("%s's turn to play !",currentPlayer);
        }
        else{
            currentPlayer = players[0];
            System.out.printf("%s's turn to play !",currentPlayer);
        }
    }
}