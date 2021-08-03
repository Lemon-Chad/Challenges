package lemon.algoc.drop;

import lemon.algoc.Point;

public class Cell {
    boolean solid;
    Point location;

    public Cell(int x, int y) {
        location = new Point(x, y);
        solid = false;
    }

    public Cell(int x, int y, boolean solid) {
        location = new Point(x, y);
        this.solid = solid;
    }

}
