public class Pawn extends Pieces{
    public Pawn(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return "P";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        String valid_board = "a b c d e f g h 1 2 3 4 5 6 7 8";
        System.out.println(valid_board.indexOf("%s".formatted(newPosition.row)));
        return valid_board.contains("%s".formatted(newPosition.row)) && valid_board.contains("%s".formatted(newPosition.column)) && board[newPosition.row][(int)(newPosition.column-97-1)].isEmpty
                && position.column == newPosition.column && newPosition.row - position.row != 1;
    }
    public void setNewPosition(Position newPosition){
        this.position=newPosition;
    }
    public Position getPosition(){
        return position;
    }
}
