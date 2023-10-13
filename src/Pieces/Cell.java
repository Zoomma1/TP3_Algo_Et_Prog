package Pieces;

import Pieces.Position;

public class Cell {
    final Position position;
    boolean isEmpty;
    String piece;

    public Cell(Position position, boolean isEmpty,String piece) {
        this.position = position;
        this.isEmpty = isEmpty;
        this.piece = piece;
    }
}
