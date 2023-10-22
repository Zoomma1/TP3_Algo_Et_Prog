import java.util.ArrayList;

public class King extends Pieces {
    public King(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return color == 1 ? Character.toString((char)(9812)) : Character.toString((char)(9818));
    }
    public String pieceStringType(){ return "K";}
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        if(!isInBoard(newPosition,board)){
            return false;
        }
        int deltaX = Math.abs(newPosition.column - position.column);
        int deltaY = Math.abs(newPosition.row - position.row);
        if ((deltaX <= 1 && deltaY <= 1) && (deltaX + deltaY > 0)) {
            Pieces pieceAtDestination = board[newPosition.row - 1][newPosition.column - 97].getPieces();
            if (board[newPosition.row - 1][newPosition.column - 97].isEmpty() || pieceAtDestination.getColor() != this.getColor()) {
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
        King newking = new King(this.color, this.position.copy());
        return newking;
    }
    public ArrayList<Position> generatePossibleMoves(Cell[][] board){
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int x = position.column - 97;
        int y = position.row - 1;
        int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};
        for (int i = 0; i < 8; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (isValidMove(new Position((char)('a'+newX),newY+1),board)) {
                if (board[newY][newX].isEmpty() || board[newY][newX].getPieces().getColor() != color) {
                    possibleMoves.add(new Position((char) ('a' + newX), newY+1));
                }
            }
        }

        return possibleMoves;
    }
}
