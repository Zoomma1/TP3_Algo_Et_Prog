package Pieces;


public class Rook {
    int color;
    Position position;

    public Rook(int color,Position position) {
        this.color = color;
        this.position = position;
    }
    public String toString() {
        return "R";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        String valid_board = "a b c d e f g h 1 2 3 4 5 6 7 8";
        if (valid_board.indexOf(newPosition.toString().charAt(0))==-1 || valid_board.indexOf(newPosition.toString().charAt(1))==-1){
            return false;
        } else if (newPosition.toString().charAt(0)== position.toString().charAt(0) && newPosition.toString().charAt(1)!= position.toString().charAt(1)|| newPosition.toString().charAt(1)== position.toString().charAt(1) && newPosition.toString().charAt(0)!= position.toString().charAt(0)) {
            return true;
        }
        return false;
    }
}