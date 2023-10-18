public class Cell {
    final Position position;
    Pieces pieces;

    public Cell(Position position,Pieces pieces) {
        this.position = position;
        this.pieces = pieces;
    }
    public Pieces getPieces(){
        return pieces;
    }
    public void setPieces(Pieces pieces){
        this.pieces = pieces;
    }
    public boolean isEmpty(){
        return pieces == null;
    }
}
