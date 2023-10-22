import java.util.ArrayList;

public class Queen extends Pieces{
    public Queen(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return color == 1 ? Character.toString((char)(9813)) : Character.toString((char)(9819));
    }
    public String pieceStringType(){ return "Q";}
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        if(!isInBoard(newPosition,board)){
            return false;
        }
        int deltaX = Math.abs(newPosition.column - position.column);
        int deltaY = Math.abs(newPosition.row - position.row);
        int startX = position.column - 97;
        int startY = position.row - 1;
        int destX = newPosition.column - 97;
        int destY = newPosition.row - 1;
        int xDirection = (destX > startX) ? 1 : (destX < startX) ? -1 : 0;
        int yDirection = (destY > startY) ? 1 : (destY < startY) ? -1 : 0;
        if ((deltaX == 0 && deltaY > 0) || (deltaY == 0 && deltaX > 0)) {
            for (int i = 1; i < Math.max(deltaX, deltaY); i++) {
                int x = startX + i * xDirection;
                int y = startY + i * yDirection;

                if (!board[y][x].isEmpty()) {
                    return false;
                }
            }
        }
        else if (deltaX == deltaY) {
            for (int i = 1; i < deltaX; i++) {
                int x = startX + i * xDirection;
                int y = startY + i * yDirection;

                if (!board[y][x].isEmpty()) {
                    return false;
                }
            }
        }
        else {
            return false;
        }
        Pieces pieceAtDestination = board[destY][destX].getPieces();
        if (board[destY][destX].isEmpty() || pieceAtDestination.getColor() != this.getColor()) {
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
    public Pieces createNewPieces() {
        Queen newqueen = new Queen(this.color, this.position.copy());
        return newqueen;
    }
    public ArrayList<Position> generatePossibleMoves(Cell[][] board){
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int x = position.column - 97;
        int y = position.row - 1;
        for (int i = 0; i < 7; i++) {
            if(isValidMove(new Position(position.column,y),board)){
                possibleMoves.add(new Position(position.column , y));
            }
            if(isValidMove(new Position((char)(position.column + i), position.row),board)){
                possibleMoves.add(new Position((char)(position.column + i), position.row));
            }
        }
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
