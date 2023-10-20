public class Knight extends Pieces{
    public Knight(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return color == 1 ? Character.toString((char)(9816)) : Character.toString((char)(9822));
    }
    public String pieceStringType(){ return "N";}
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        int newPosX = newPosition.column - 97;
        int newPosY = newPosition.row;
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
}
