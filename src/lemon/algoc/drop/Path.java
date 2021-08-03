package lemon.algoc.drop;

import lemon.algoc.Point;

public class Path {
    public enum Adjacent {
        LEFT,
        RIGHT,
        DOWN,
        UP,
        UPDOWN,
        LEFTRIGHT,
        LEFTUP,
        LEFTDOWN,
        RIGHTDOWN,
        RIGHTUP,
        NONE,
    }

    Adjacent adjacent = Adjacent.NONE;
    boolean visited = false;
    Point location;

    public Path(int x, int y) {
        location = new Point(x, y);
    }

    public Path visit() {
        visited = true; return this;
    }

    public void setAdjacent(Adjacent adj) {
        if (adj == adjacent) return;

        if (adjacent == Adjacent.NONE) {
            adjacent = adj;
            return;
        }

        if (adjacent == Adjacent.LEFT)
        {
            if (adj == Adjacent.RIGHT) adjacent = Adjacent.LEFTRIGHT;
            else if (adj == Adjacent.DOWN) adjacent = Adjacent.LEFTDOWN;
            else if (adj == Adjacent.UP) adjacent = Adjacent.LEFTUP;
        }
        else if (adjacent == Adjacent.RIGHT)
        {
            if (adj == Adjacent.LEFT) adjacent = Adjacent.LEFTRIGHT;
            else if (adj == Adjacent.DOWN) adjacent = Adjacent.RIGHTDOWN;
            else if (adj == Adjacent.UP) adjacent = Adjacent.RIGHTUP;
        }
        else if (adjacent == Adjacent.DOWN)
        {
            if (adj == Adjacent.LEFT) adjacent = Adjacent.LEFTDOWN;
            else if (adj == Adjacent.RIGHT) adjacent = Adjacent.RIGHTDOWN;
            else if (adj == Adjacent.UP) adjacent = Adjacent.UPDOWN;
        }
        else if (adjacent == Adjacent.UP)
        {
            if (adj == Adjacent.LEFT) adjacent = Adjacent.LEFTUP;
            else if (adj == Adjacent.RIGHT) adjacent = Adjacent.RIGHTUP;
            else if (adj == Adjacent.DOWN) adjacent = Adjacent.UPDOWN;
        }

    }

}
