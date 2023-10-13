package Pieces;

import Pieces.Position;

public class Cell {
    final Position position;
    boolean isEmpty;

    public Cell(Position position, boolean isEmpty) {
        this.position = position;
        this.isEmpty = isEmpty;
    }
}
