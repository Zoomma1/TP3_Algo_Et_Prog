public class Pieces {
    int color;
    Position position;

    public Pieces(int color, Position position) {
        this.color = color;
        this.position = position;
    }
    public boolean isValidMove(Position newPosition, Cell[][] board){
        String valid_board = "a b c d e f g h 1 2 3 4 5 6 7 8";
        return valid_board.contains("%s".formatted(newPosition.row)) && valid_board.contains("%s".formatted(newPosition.column));
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
