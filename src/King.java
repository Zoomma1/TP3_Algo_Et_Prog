public class King extends Pieces {
    public King(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return "K";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        String valid_board = "a b c d e f g h 1 2 3 4 5 6 7 8";
        String valid_board_char= "a b c d e f g h";
        String valid_board_num = "1 2 3 4 5 6 7 8";

        return valid_board_char.substring(valid_board_char.indexOf(newPosition.row) - 1, valid_board_char.indexOf(newPosition.row) + 1).indexOf(newPosition.row) != -1
                || valid_board_num.substring(valid_board_num.indexOf(newPosition.column) - 1, valid_board_num.indexOf(newPosition.column) + 1).indexOf(newPosition.column) != -1
                || valid_board.indexOf(newPosition.toString().charAt(0)) != -1 || valid_board.indexOf(newPosition.toString().charAt(1)) != -1;
    }
    public void setNewPosition(Position newPosition){
        this.position=newPosition;
    }
    public Position getPosition(){
        return position;
    }
}
