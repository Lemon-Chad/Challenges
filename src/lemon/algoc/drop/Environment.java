package lemon.algoc.drop;

import lemon.algoc.Point;

public class Environment {
    Cell[][] cells;
    int width; int height;

    public Point parse(String in) {
        Point start = null;

        String[] lines = in.split("\\r?\\n");
        int w = lines[0].length();
        int h = lines.length;

        for (String line : lines) w = Math.min(line.length(), w);

        cells = new Cell[h][w];

        for (int y = 0; y < h; y++) {
            char[] chars = lines[y].toCharArray();
            for (int x = 0; x < w; x++)
                switch (chars[x]) {
                    case 'X' -> cells[y][x] = new Cell(x, y, true);
                    case 'S' -> {
                        start = new Point(x, y);
                        cells[y][x] = new Cell(x, y);
                    }
                    default -> cells[y][x] = new Cell(x, y);
                }
        }

        width  = w;
        height = h;

        return start;
    }

    public boolean fall(Point p) {
        return p.getY() != height - 1 && !cells[p.getY() + 1][p.getX()].solid;
    }

    public boolean bounds(Point p) { return 0 <= p.getY() && p.getY() < height
                                            && 0 <= p.getX() && p.getX() < width; }

}
