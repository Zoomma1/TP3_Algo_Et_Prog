public class Pawn extends Pieces{
    public Pawn(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return "P";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        String valid_board = "a b c d e f g h 1 2 3 4 5 6 7 8";
        return valid_board.indexOf(newPosition.row) != -1 && valid_board.indexOf(newPosition.column) != -1 && board[newPosition.row][newPosition.column].isEmpty
                && position.column == newPosition.column && newPosition.row - position.row != 1;
    }
    public void setNewPosition(Position newPosition){
        this.position=newPosition;
    }
    public Position getPosition(){
        return position;
    }
}
