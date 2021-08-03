package lemon.algoc.drop;

import lemon.algoc.Pair3;
import lemon.algoc.Point;

import java.util.ArrayList;
import java.util.List;

public class DropAlgo {
    Point start;
    Environment enviro;
    String in;

    public DropAlgo(String in) {
        this.in = in;

        enviro = new Environment();
        start = enviro.parse(in);
    }

    public int toLine(Point p) {
        return p.getY() * enviro.width + p.getX();
    }

    public int toLine(int x, int y) {
        return y * enviro.width + x;
    }

    public void adjacent(Point p, Path[] path) {
        Point left = new Point(p.getX() - 1, p.getY());
        Point right = new Point(p.getX() + 1, p.getY());
        Point up = new Point(p.getX(), p.getY() - 1);
        Point down = new Point(p.getX(), p.getY() + 1);

        if (enviro.bounds(left))
            path[toLine(left)].setAdjacent(Path.Adjacent.RIGHT);
        if (enviro.bounds(right))
            path[toLine(right)].setAdjacent(Path.Adjacent.LEFT);
        if (enviro.bounds(up))
            path[toLine(up)].setAdjacent(Path.Adjacent.DOWN);
        if (enviro.bounds(down))
            path[toLine(down)].setAdjacent(Path.Adjacent.UP);

    }

    public Pair3<Point, Point, Path[]> moveTo(Point start, Point p, int velocity, Path[] path) {
        p = new Point(p.getX() + velocity, p.getY());

        // If the new point is in bounds and also has empty space below it
        if (!enviro.bounds(p) || !enviro.fall(p)) return new Pair3<>(start, p, path);

        // Move to pointer
        while (start.getX() != p.getX()) {
            start = new Point(start.getX() + velocity, p.getY());

            // Update adjacencies and visiting
            adjacent(start, path);
            path[toLine(start)].visit();
        }

        return new Pair3<>(start, p, path);
    }

    public String path() {

        // Sets up empty paths for tracing a path later -->

        Path[] path = new Path[enviro.height * enviro.width];
        System.out.println(enviro.height + " " + enviro.width);

        for (int y = 0; y < enviro.height; y++) for (int x = 0; x < enviro.width; x++)
                path[toLine(x, y)] = new Path(x, y);

        // <--

        // Algorithm -->

        Point left = new Point(start.getX(), start.getY());
        Point right = new Point(start.getX(), start.getY());

        path[toLine(start)].visit();
        adjacent(start, path);

        Pair3<Point, Point, Path[]> pair3;
        while (true) {

            // Move down when possible
            while (enviro.fall(start)) {
                start = new Point(start.getX(), start.getY() + 1);

                // Visit the current path
                path[toLine(start)].visit();
                // Tell all adjacent paths that they are adjacent to it
                adjacent(start, path);
                // Update left and right pointers
                left = new Point(start.getX(), start.getY());
                right = new Point(start.getX(), start.getY());
            }

            if (start.getY() == enviro.height - 1) break; // If at the bottom now, break

            System.out.println(left.getX() + " " + left.getY());

            // Move left and right pointers
            pair3 = moveTo(start, left, -1, path);
            start = pair3.first; left = pair3.second; path = pair3.third;

            pair3 = moveTo(start, right, 1, path);
            start = pair3.first; right = pair3.second; path = pair3.third;
        }

        // <--

        // Sets up StringBuilders to swap out characters and draw path -->

        List<StringBuilder> builders = new ArrayList<>();

        String[] lns = in.split("\\r?\\n");
        for (String ln : lns) builders.add(new StringBuilder(ln));

        // <--

        // Draws paths -->

        /*
            Iterates over each path.
            If the path is not visited skip.
            Otherwise, set the character at the corresponding location to the correct character based on
            adjacent visited paths.
         */

        for (Path p : path) {
            if (!p.visited) continue;
            builders.get(p.location.getY()).setCharAt(p.location.getX(), switch (p.adjacent) {
                case LEFT, RIGHT, LEFTRIGHT -> '━';
                case DOWN, UP, UPDOWN -> '┃';

                case LEFTDOWN -> '┓';
                case LEFTUP -> '┛';

                case RIGHTDOWN -> '┏';
                case RIGHTUP -> '┗';

                default -> '-';
            });
        }

        // <--

        // Compile all the StringBuilders into one String, and then return that string -->

        StringBuilder sb = new StringBuilder();
        for (StringBuilder s : builders) sb.append(s.toString()).append("\n");
        return sb.toString();

        // <--
    }

}
