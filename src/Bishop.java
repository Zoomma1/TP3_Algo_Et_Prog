import java.util.ArrayList;

public class Bishop extends Pieces {

    public Bishop(int color, Position position) {
        super(color, position);

    }

    public String toString() {
        return color == 1 ? Character.toString((char)(9815)) : Character.toString((char)(9821));
    }
    public String pieceStringType(){ return "B";}
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        if(!isInBoard(newPosition,board)){
            return false;
        }
        int deltaX = Math.abs(newPosition.column - position.column);
        int deltaY = Math.abs(newPosition.row - position.row);
        if (deltaX == deltaY) {
            int startX = position.column - 97;
            int startY = position.row - 1;
            int destX = newPosition.column - 97;
            int destY = newPosition.row - 1;
            int xDirection = (destX > startX) ? 1 : -1;
            int yDirection = (destY > startY) ? 1 : -1;
            for (int i = 1; i < deltaX; i++) {
                int x = startX + i * xDirection;
                int y = startY + i * yDirection;
                if (!board[x][y].isEmpty()) {
                    return false;
                }
            }
            Pieces pieceAtDestination = board[destY][destX].getPieces();
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
    public Pieces createNewPieces() {
        Bishop newbishop = new Bishop(this.color, this.position.copy());
        return newbishop;
    }
    public ArrayList<Position> generatePossibleMoves(Cell[][] board){
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int x = position.column - 97;
        int y = position.row - 1;
        int row = y + 1;
        for (int col = x + 1; col < 8 && row <= 8; col++, row++) {
            if (isValidMove(new Position((char)('a'+row),col),board) && board[row][col].getPieces().getColor() != color) {
                if (board[row][col].isEmpty()) {
                    possibleMoves.add(new Position((char) ('a' + col), row));
                }
                else {
                    break;
                }
            }
        }
        row = y + 1;
        for (int col = x - 1; col >= 0 && row <= 8; col--, row++) {
            if (isValidMove(new Position((char)('a'+row),col),board)) {
                if (board[row][col].isEmpty() && board[row][col].getPieces().getColor() != color) {
                    possibleMoves.add(new Position((char) ('a' + col), row));
                }
                else {
                    break;
                }
            }
        }
        row = y - 1;
        for (int col = x + 1; col < 8 && row >= 1; col++, row--) {
            if (isValidMove(new Position((char)('a'+row),col),board)) {
                if (board[row][col].isEmpty() && board[row][col].getPieces().getColor() != color) {
                    possibleMoves.add(new Position((char) ('a' + col), row));
                }
                else {
                    break;
                }
            }
        }
        row = y - 1;
        for (int col = x - 1; col >= 0 && row >= 1; col--, row--) {
            if (isValidMove(new Position((char)('a'+row),col),board)) {
                if (board[row][col].isEmpty() && board[row][col].getPieces().getColor() != color) {
                    possibleMoves.add(new Position((char) ('a' + col), row));
                }
                else {
                    break;
                }
            }
        }
        return possibleMoves;
    }
}
