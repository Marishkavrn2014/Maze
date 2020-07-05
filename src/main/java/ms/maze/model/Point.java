package ms.maze.model;

//contain the points on board
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //get X coordinate
    public int getX() {
        return x;
    }
    //get Y coordinate
    public int getY() {
        return y;
    }

}
