package Pieces;

public class Pawn {
    int color;
    Position position;
    public Pawn(int color,Position position) {
        this.color = color;
        this.position = position;

    }
    public String toString() {
        return "P";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        String valid_board = "a b c d e f g h 1 2 3 4 5 6 7 8";
        if(valid_board.indexOf(newPosition.toString().charAt(0))==-1 || valid_board.indexOf(newPosition.toString().charAt(1))==-1){
            return false;
        }
        return true;
    }
}
