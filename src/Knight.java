public class Knight extends Pieces{
    public Knight(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return "N";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        String valid_board = "a b c d e f g h 1 2 3 4 5 6 7 8";
        String valid_positions = (char)(position.row + 2)+Integer.toString(position.column+1) + " " + (char)(position.row + 2)+Integer.toString(position.column - 1) +
                (char)(position.row - 2)+Integer.toString(position.column+1) + " " + (char)(position.row - 2)+Integer.toString(position.column - 1) +
                (char)(position.row + 1)+Integer.toString(position.column+2) + " " + (char)(position.row + 1)+Integer.toString(position.column - 2)+
                (char)(position.row - 1)+Integer.toString(position.column+2) + " " + (char)(position.row - 1)+Integer.toString(position.column - 2);

        return valid_board.indexOf(newPosition.toString().charAt(0)) != -1 && valid_board.indexOf(newPosition.toString().charAt(1)) != -1 && valid_positions.contains(String.valueOf(newPosition));
    }
    public void setNewPosition(Position newPosition){
        this.position=newPosition;
    }
    public Position getPosition(){
        return position;
    }
}
