public class Queen extends Pieces{
    public Queen(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return "Q";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
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
        i = 0;
        if (whereToX == 0 && whereToY > 0){
            i = currentColumn - 48;
            while (i < 8){
                valid_positions += "%c%d".formatted((char)(currentColumn + i), currentRow);
                i++;
            }
        }
        else if (whereToX == 0 && whereToY < 0){
            i = currentColumn - 48;
            while (i > 0){
                valid_positions += "%c%d".formatted((char)(currentColumn - i), currentRow);
                i++;
            }
        }
        else if (whereToX > 0 && whereToY == 0){
            i = currentRow;
            while (i < 8){
                valid_positions += "%c%d".formatted((currentColumn), currentRow + i);
                i++;
            }
        }
        else if (whereToX < 0 && whereToY == 0) {
            i = currentRow;
            while (i > 0) {
                valid_positions += "%c%d".formatted((currentColumn), currentRow - i);
                i++;
            }
        }
        for (j = 0; j < valid_positions.length(); j=j+2) {
            if (!board[valid_positions.charAt(j)][(int)(valid_positions.charAt(j+1))].isEmpty) {
                return false;
            }
        }
        return valid_positions.contains(String.valueOf(newPosition));
    }

    public void setNewPosition(Position newPosition){
        this.position=newPosition;
    }
    public Position getPosition(){
        return position;
    }
}
