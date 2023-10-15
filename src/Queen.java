public class Queen extends Pieces{
    public Queen(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return "Q";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        String valid_board = "a b c d e f g h 1 2 3 4 5 6 7 8";
        String valid_positions = "";
        int i = position.row + 1,j = position.column - 48 + 1;
        while(i < 8 || j < 8){
            valid_positions += (char)(position.column+i)+(char)(j) + " ";
            j++;
            i++;
        }
        i = position.row + 1;
        j= position.column - 1 - 48;
        while(i < 8 || j > 0){
            valid_positions += (char)(position.column+i)+(char)(j) + " ";
            j--;
            i++;
        }
        i = position.row - 1;
        j= position.column - 1 - 48;
        while(i > 0 || j > 0){
            valid_positions += (char)(position.column+i)+(char)(j) + " ";
            j--;
            i--;
        }
        i = position.row - 1;
        j= position.column + 1 - 48;
        while(i < 8 || j<8){
            valid_positions += (char)(position.column+i)+(char)(j) + " ";
            j++;
            i--;
        }
        return valid_board.indexOf(newPosition.row) != -1 && valid_board.indexOf(newPosition.column) != -1 && valid_positions.contains(String.valueOf(newPosition))
                && newPosition.row == position.row && newPosition.column != position.column || newPosition.column == position.column && newPosition.row != position.row;
    }

    public void setNewPosition(Position newPosition){
        this.position=newPosition;
    }
    public Position getPosition(){
        return position;
    }
}
