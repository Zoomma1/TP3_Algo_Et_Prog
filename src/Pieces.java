public abstract class Pieces {
    protected int color;
    protected Position position;

    public Pieces(int color, Position position) {
        this.color = color;
        this.position = position;
    }
    public boolean isValidMove(Position newPosition, Cell[][] board){
        return isInBoard(newPosition,board);
    }
    public boolean isInBoard(Position newPosition , Cell[][] board){
        int x = newPosition.row - 1;
        int y = newPosition.column - 97;
        return (x < 8 && x >= 0) && (y < 8 && y >= 0);
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    public Position getPosition() {
        return position;
    }

    public String pieceStringType(){ return "";}

    public void setNewPosition(Position position){
        this.position = position;
    }
    public Pieces createNewPieces() {
        Pieces newpieces = new Pieces(this.color, this.position.copy()) {};
        return newpieces;
    }
}
