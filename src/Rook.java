public class Rook extends Pieces{
    public Rook(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return "R";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int deltaX = Math.abs(newPosition.column - position.column);
        int deltaY = Math.abs(newPosition.row - position.row);
        if (deltaX == 0 || deltaY == 0) {
            int startX = position.column - 97;
            int startY = position.row;
            int destX = newPosition.column - 97;
            int destY = newPosition.row;
            if (deltaX != 0){
                int Direction = (destX > startX) ? 1 : -1;
                for (int i = 1; i < deltaX; i++) {
                    int x = startX - 1 + i * Direction;

                    if (!board[x][startY].isEmpty()) {
                        return false;
                    }
                }
            }
            else{
                int Direction = (destX > startX) ? 1 : -1;
                for (int i = 1; i < deltaY; i++) {
                    int y = startY + i * Direction;

                    if (!board[startX][y].isEmpty()) {
                        return false;
                    }
                }
            }
            Pieces pieceAtDestination = board[destX][destY].getPieces();
            if (board[destX][destY].isEmpty() || pieceAtDestination.getColor() != this.getColor()) {
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
