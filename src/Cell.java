public class Cell {
    final Position position;
    boolean isEmpty;
    Character pieces;

    public Cell(Position position, boolean isEmpty,Character pieces) {
        this.position = position;
        this.isEmpty = isEmpty;
        this.pieces = pieces;
    }
    public char whatPiece(){return Cell.this.pieces;}
}
