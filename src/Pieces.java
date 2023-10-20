abstract class Pieces {
    int color;
    Position position;

    public Pieces(int color, Position position) {
        this.color = color;
        this.position = position;
    }
    public boolean isValidMove(Position newPosition, Cell[][] board){
        int x = newPosition.row;
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
    public void setPosition(Position position){
        this.position = position;
    }
}
