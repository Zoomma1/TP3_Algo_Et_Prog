public class King extends Pieces {
    public King(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return color == 1 ? Character.toString((char)(9812)) : Character.toString((char)(9818));
    }
    public String pieceStringType(){ return "K";}
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int deltaX = Math.abs(newPosition.column - position.column);
        int deltaY = Math.abs(newPosition.row - position.row);
        if ((deltaX <= 1 && deltaY <= 1) && (deltaX + deltaY > 0)) {
            Pieces pieceAtDestination = board[newPosition.row][newPosition.column - 97].getPieces();
            if (board[newPosition.row][newPosition.column - 97].isEmpty() || pieceAtDestination.getColor() != this.getColor()) {
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
