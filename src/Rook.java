public class Rook extends Pieces{
    public Rook(int color, Position position) {
        super(color, position);
    }

    public String toString() {
        return "R";
    }
    public boolean isValidMove(Position newPosition, Cell[][] board) {
        String valid_positions = "";
        int currentRow = position.row , currentColumn = position.column;
        int newRow = newPosition.row , newColumn = newPosition.column;
        int whereToX = newRow - currentRow, whereToY = newColumn - currentColumn;
        int i = 0;
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
        for (int j = 0; j < valid_positions.length(); j=j+2) {
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
