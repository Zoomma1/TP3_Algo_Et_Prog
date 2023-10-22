import java.util.ArrayList;

public class Knight extends Pieces{
    public Knight(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return color == 1 ? Character.toString((char)(9816)) : Character.toString((char)(9822));
    }
    public String pieceStringType(){ return "N";}
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        if(!isInBoard(newPosition,board)){
            return false;
        }
        int newPosX = newPosition.column - 97;
        int newPosY = newPosition.row - 1;
        if(!board[newPosY][newPosX].isEmpty()){
            if(color != board[newPosY][newPosX].pieces.getColor()){
                return false;
            }
        }
        int deltaX = Math.abs(newPosition.column - position.column);
        int deltaY = Math.abs(newPosition.row - position.row);
        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
            return newPosX >= 0 && newPosX < 8 && newPosY >= 0 && newPosY < 8;
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
        Knight newKnight = new Knight(this.color, this.position.copy());
        return newKnight;
    }
    public ArrayList<Position> generatePossibleMoves(Cell[][] board){
        ArrayList<Position> possibleMoves = new ArrayList<>();
        int x = position.column - 97;
        int y = position.row - 1;
        int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};
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
