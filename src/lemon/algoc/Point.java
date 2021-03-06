package lemon.algoc;

public class Point {
    private final int x, y;

    public Point(int x, int y) {
        this.x = x; this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;

        if (!(o instanceof Point)) return false;

        Point p = (Point) o;
        return p.x == x && p.y == y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
