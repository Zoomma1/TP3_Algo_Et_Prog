import java.util.ArrayList;
import java.util.Scanner;

public class Pawn extends Pieces{
    public Pawn(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return color == 1 ? Character.toString((char)(9817)) : Character.toString((char)(9823));
    }
    public String pieceStringType(){ return "P";}
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        if(!isInBoard(newPosition,board)){
            return false;
        }
        int deltaX = Math.abs(newPosition.column - position.column);
        int deltaY = Math.abs(newPosition.row - position.row);
        if (deltaX == 0) {
            int yDirection = (getColor() == 0) ? 1 : -1;
            int newY = position.row + yDirection;
            if (deltaY == 1 && newPosition.row == newY) {
                if (board[newPosition.row - 1][newPosition.column - 97].isEmpty()) {
                    return true;
                }
            } else if (deltaY == 2 && newPosition.row == newY + yDirection) {
                if ((getColor() == 0 && position.row == 2) || (getColor() == 1 && position.row == 7)) {
                    if (board[newY - 1][position.column - 97].isEmpty() && board[newPosition.row - 1][newPosition.column - 97].isEmpty()) {
                        return true;
                    }
                }
            }
        }
        if (deltaX == 1 && deltaY == 1) {
            if (board[newPosition.row - 1][newPosition.column - 97].getPieces() != null &&
                    board[newPosition.row - 1][newPosition.column - 97].getPieces().getColor() != this.getColor()) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Position> generatePossibleMoves(Cell[][] board){
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int x = position.column - 97;
        int y = position.row - 1;
        int direction = (color == 0) ? 1 : -1;
        if (isValidMove(new Position((char)(position.column), position.row + direction),board)) {
            possibleMoves.add(new Position(position.column, position.row + direction));
        }
        if (isValidMove(new Position((char)(position.column), position.row + 2 * direction),board)) {
            possibleMoves.add(new Position(position.column, position.row + 2 * direction));
        }
        if (isValidMove(new Position((char)(position.column + 1), position.row + direction),board)) {
            possibleMoves.add(new Position((char)(position.column + 1), position.row + direction));
        }
        if (isValidMove(new Position((char)(position.column - 1), position.row + direction),board)) {
            possibleMoves.add(new Position((char)(position.column - 1), position.row + direction));
        }
        return possibleMoves;
    }
    public void setNewPosition(Position newPosition){
        this.position=newPosition;
    }
    public Position getPosition(){
        return position;
    }
    public Pieces createNewPieces() {
        Pawn newpawn = new Pawn(this.color, this.position.copy());
        return newpawn;
    }
}
