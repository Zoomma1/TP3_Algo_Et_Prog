public class King extends Pieces {
    public King(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return "K";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int deltaX = Math.abs(newPosition.column - position.column);
        int deltaY = Math.abs(newPosition.row - position.row);
        if ((deltaX <= 1 && deltaY <= 1) && (deltaX + deltaY > 0)) {
            Pieces pieceAtDestination = board[newPosition.column - 97][newPosition.row].getPieces();
            if (board[newPosition.column - 97][newPosition.row].isEmpty() || pieceAtDestination.getColor() != this.getColor()) {
                return true;
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
