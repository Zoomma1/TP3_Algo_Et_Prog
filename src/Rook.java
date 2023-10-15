public class Rook extends Pieces{
    public Rook(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return "R";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        String valid_board = "a b c d e f g h 1 2 3 4 5 6 7 8";
        return newPosition.row == position.row && newPosition.column != position.column || newPosition.column == position.column && newPosition.row != position.row
                &&valid_board.indexOf(newPosition.toString().charAt(0))!=-1 && valid_board.indexOf(newPosition.toString().charAt(1))!=-1;
    }
    public void setNewPosition(Position newPosition){
        this.position=newPosition;
    }
    public Position getPosition(){
        return position;
    }
}
