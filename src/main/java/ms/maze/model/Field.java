package ms.maze.model;

import java.util.LinkedList;
import java.util.List;

//initialise the fields on the board
public class Field {

    private int sizeField = 8;
    private Point begin;
    private Point finish;
    private Point current;
    private int[][] heights;
    private boolean[][] walls;
    private Partitions[][] partitions;
    private List<Partitions> open = new LinkedList<>();
    private List<Partitions> lock = new LinkedList<>();

    //initialize the heights
    public boolean setHeights(int[][] heights) {
        this.heights = heights;
        return true;
    }

    //set partitions on the board
    public boolean setPartitions(Partitions[][] partitions) {
        this.partitions = partitions;

        //initialise the list with only opening partitions
        for (int i = 0; i < getSizeField(); i++) {
            for(int j = 0; j < getSizeField(); j++) {
                if(partitions[i][j].getType() == PartitionTypes.OPENING) {
                    open.add(partitions[i][j]);
                } else {
                    //initialise the list with only lockable partitions
                    if(partitions[i][j].getType() == PartitionTypes.LOCKABLE) {
                        lock.add(partitions[i][j]);
                    }
                }
            }
        }
        return true;
    }

    //initialize walls
    public boolean setWalls(boolean[][] walls) {
        this.walls = walls;
        return true;
    }

    //return partitions table
    public Partitions[][] getPartitions() {
        return partitions;
    }

    //return walls table
    public boolean[][] getWalls() {
        return walls;
    }
    // return heights
    public int[][] getHeights() {
        return heights;
    }

    //set start position
    public void setBegin(Point begin) {
        this.begin = begin;
    }

    //set finish position
    public void setFinish(Point finish) {
        this.finish = finish;
    }

    //return of the start position
    public Point getBegin() {
        return begin;
    }

    //return of the finish position
    public Point getFinish() {
        return finish;
    }

    //return field size
    public int getSizeField() {
        return sizeField;
    }

    //set current point
    public void setCurrent(Point current) {
        this.current = current;
    }

    //return current point
    public Point getCurrent() {
        return current;
    }

    //return the list with only lockable partitions
    public List<Partitions> getLock() {
        return lock;
    }

    //return the list with only opening partitions
    public List<Partitions> getOpen() {
        return open;
    }
}
