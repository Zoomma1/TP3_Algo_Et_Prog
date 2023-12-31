import java.util.ArrayList;

public class Rook extends Pieces{
    public Rook(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return color == 1 ? Character.toString((char)(9814)) : Character.toString((char)(9820));
    }
    public String pieceStringType(){ return "R";}
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        if(!isInBoard(newPosition,board)){
            return false;
        }
        int deltaX = Math.abs(newPosition.column - position.column);
        int deltaY = Math.abs(newPosition.row - position.row);
        if (deltaX == 0 || deltaY == 0) {
            int startX = position.column - 97;
            int startY = position.row - 1;
            int destX = newPosition.column - 97;
            int destY = newPosition.row - 1;
            if (deltaX != 0){
                int Direction = (destX > startX) ? 1 : -1;
                for (int i = 2; i < deltaX; i++) {
                    int x = startX + i * Direction;
                    if (!board[startY][x].isEmpty()) {
                        return false;
                    }
                }
            }
            else{
                int Direction = (destX > startX) ? 1 : -1;
                for (int i = 2; i < deltaY; i++) {
                    int y = startY + i * Direction;
                    if (!board[y][startX].isEmpty()) {
                        return false;
                    }
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
        Rook newrook = new Rook(this.color, this.position.copy());
        return newrook;
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
        return possibleMoves;
    }
}
