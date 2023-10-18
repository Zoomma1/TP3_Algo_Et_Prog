public class Pawn extends Pieces{
    public Pawn(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return "P";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int deltaX = Math.abs(newPosition.column - position.column);
        int deltaY = Math.abs(newPosition.row - position.row);
        if (deltaX == 0) {
            int yDirection = (getColor() == 0) ? 1 : -1;
            if (deltaY == 1 && (newPosition.row == position.row + yDirection)) {
                if (board[newPosition.column-97][newPosition.row].isEmpty) {
                    return true;
                }
            }
            else if (deltaY == 2 && (newPosition.row == position.row + 2 * yDirection)) {
                if ((getColor() == 0 && position.row == 2) || (getColor() == 1 && position.row == 7)) {
                    if (board[position.column-97][position.row + yDirection].isEmpty &&
                            board[newPosition.column-97][newPosition.row].isEmpty) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void setNewPosition(Position newPosition){
        this.position=newPosition;
    }
    public Position getPosition(){
        return position;
    }
}
