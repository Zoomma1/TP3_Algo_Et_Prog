public class Bishop extends Pieces {

    public Bishop(int color, Position position) {
        super(color, position);

    }

    public String toString() {
        return "B";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        String valid_board = "a b c d e f g h 1 2 3 4 5 6 7 8";
        String valid_positions = "";
        int i = position.row + 1,j = position.column - 48 + 1;
        int currentRow = position.row , currentColumn = position.column;
        int newRow = newPosition.row , newColumn = newPosition.column;
        int whereToX = newRow - currentRow, whereToY = newColumn - currentColumn;
        if(whereToX > 0 && whereToY > 0) {
            while (i < 8 || j < 8) {
                valid_positions += (char) (position.column + i) + (char) (j);
                j++;
                i++;
            }
        }
        else if (whereToX < 0 && whereToY > 0) {
            while (i < 8 || j > 0) {
                valid_positions += (char) (position.column + i) + (char) (j);
                j--;
                i++;
            }
        }
        else if (whereToX < 0 && whereToY < 0) {
            while (i > 0 || j > 0) {
                valid_positions += (char) (position.column + i) + (char) (j);
                j--;
                i--;
            }
        }
        else if (whereToX > 0 && whereToY < 0) {
            while (i < 8 || j < 8) {
                valid_positions += (char) (position.column + i) + (char) (j);
                j++;
                i--;
            }
        }
        for (int k = 0; k < valid_positions.length(); k=k+2) {
            if (!board[valid_positions.charAt(k)][(int)(valid_positions.charAt(k+1))].isEmpty) {
                return false;
            }
        }
        return valid_board.indexOf(newPosition.row) != -1 && valid_board.indexOf(newPosition.column) != -1 && valid_positions.contains(String.valueOf(newPosition));
    }
    public void setNewPosition(Position newPosition){
        this.position=newPosition;
    }
    public Position getPosition(){
        return position;
    }
}
