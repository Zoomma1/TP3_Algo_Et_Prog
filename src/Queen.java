public class Queen extends Pieces{
    public Queen(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return "Q";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int deltaX = Math.abs(newPosition.column - position.column);
        int deltaY = Math.abs(newPosition.row - position.row);
        int startX = position.column - 97;
        int startY = position.row;
        int destX = newPosition.column - 97;
        int destY = newPosition.row;
        int xDirection = (destX > startX) ? 1 : (destX < startX) ? -1 : 0;
        int yDirection = (destY > startY) ? 1 : (destY < startY) ? -1 : 0;
        if ((deltaX == 0 && deltaY > 0) || (deltaY == 0 && deltaX > 0)) {
            for (int i = 1; i < Math.max(deltaX, deltaY); i++) {
                int x = startX + i * xDirection;
                int y = startY + i * yDirection;

                if (!board[x][y].isEmpty()) {
                    return false;
                }
            }
        }
        else if (deltaX == deltaY) {
            for (int i = 1; i < deltaX; i++) {
                int x = startX + i * xDirection;
                int y = startY + i * yDirection;

                if (!board[x][y].isEmpty()) {
                    return false;
                }
            }
        }
        else {
            return false;
        }
        Pieces pieceAtDestination = board[destX][destY].getPieces();
        if (board[destX][destY].isEmpty() || pieceAtDestination.getColor() != this.getColor()) {
            return true;
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
