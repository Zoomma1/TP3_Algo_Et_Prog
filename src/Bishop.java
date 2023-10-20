public class Bishop extends Pieces {

    public Bishop(int color, Position position) {
        super(color, position);

    }

    public String toString() {
        return "B";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int deltaX = Math.abs(newPosition.column - position.column);
        int deltaY = Math.abs(newPosition.row - position.row);
        if (deltaX == deltaY) {
            int startX = position.column - 97;
            int startY = position.row;
            int destX = newPosition.column - 97;
            int destY = newPosition.row;
            int xDirection = (destX > startX) ? 1 : -1;
            int yDirection = (destY > startY) ? 1 : -1;
            for (int i = 1; i < deltaX; i++) {
                int x = startX + i * xDirection;
                int y = startY + i * yDirection;

                if (!board[y][x].isEmpty()) {
                    return false;
                }
            }
            Pieces pieceAtDestination = board[destX][destY].getPieces();
            if (board[destY][destX].isEmpty() || pieceAtDestination.getColor() != this.getColor()) {
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
