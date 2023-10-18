public class Cell {
    final Position position;
    boolean isEmpty;
    Pieces pieces;

    public Cell(Position position, boolean isEmpty,Pieces pieces) {
        this.position = position;
        this.isEmpty = isEmpty;
        this.pieces = pieces;
    }
    public Pieces getPieces(){
        return pieces;
    }
    public void setEmpty(Boolean isEmpty){
        this.isEmpty = isEmpty;
    }
    public void setPieces(Pieces pieces){
        this.pieces = pieces;
    }
}
