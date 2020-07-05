package ms.maze.controller;

import ms.maze.model.Field;
import ms.maze.model.Game;
import ms.maze.model.PartitionTypes;
import ms.maze.model.Point;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

//choose the right point
public class MoveController {

    //method return point
    public static Point makeStep(final Field field, final Point point, final Game game) {

        //list with all rooms. Checking border before adding
        List<Point> rooms = new LinkedList<>();

        //list with rooms without walls and closed rooms
        List<Point> available = new LinkedList<>();

        //current point
        int x = point.getX();
        int y = point.getY();

        //checking border
        if(x > 0) {
            rooms.add(new Point(x - 1, point.getY()));
            if(y > 0) {
                rooms.add(new Point(x, y - 1));
            }
        } else {
            if(y > 0) {
                rooms.add(new Point(x, y - 1));
            }
        }
        if(x < field.getSizeField() - 1) {
            rooms.add(new Point(x + 1, y));
            if(y < field.getSizeField() - 1) {
                rooms.add(new Point(x, y + 1));
            }
        } else {
           if(y < field.getSizeField() - 1 && y != 0) {
                rooms.add(new Point(x, y + 1));
            }
        }

        //checking list for null
        if(!rooms.isEmpty()) {

            //for each point check walls, minimum height and partitions. Excluding begin point
            for (Point p : rooms) {
                if (checkWalls(p, field) && checkGravity(p, field, point) && checkPartitions(p, field) && !checkBeginPoint(p, field)){
                    available.add(p);
                }
            }

            //check list for null
            if(!available.isEmpty()) {

                //return the next point(minimum or random)
                return choosePoint(point, field, available);
            }
        }

        //return current point if nothing is available
        return point;

    }

    //check walls in point. Return true or false
    public static boolean checkWalls(Point p, Field field) {
        return !field.getWalls()[p.getX()][p.getY()];
    }

    //check gravity. If the point is greater than the current, return true, otherwise return false. Excluding the starting point
    public static boolean checkGravity(Point p, Field field, Point current) {
        if(field.getCurrent().getX() == field.getBegin().getX() && field.getCurrent().getY() == field.getBegin().getY()) {
            return true;
        }
        return field.getHeights()[p.getX()][p.getY()] <= field.getHeights()[current.getX()][current.getY()];
    }

    //check partition. If room is closed - return false, else return true
    public static boolean checkPartitions(Point p, Field field) {
        if(field.getPartitions()[p.getX()][p.getY()].getType() == PartitionTypes.LOCKABLE && field.getPartitions()[p.getX()][p.getY()].getCondition()) {
            return true;
        } else {
            if(field.getPartitions()[p.getX()][p.getY()].getType() == PartitionTypes.OPENING && !field.getPartitions()[p.getX()][p.getY()].getCondition()) {
                return true;
            } else {
                if(field.getPartitions()[p.getX()][p.getY()].getType() == PartitionTypes.EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    //return min point or random selected point
    public static Point choosePoint(Point current, Field field, List<Point> rooms) {

        Point min = null;

        //save a point that is less than the current
        for(Point p : rooms) {
            if(field.getHeights()[p.getX()][p.getY()] < field.getHeights()[current.getX()][current.getY()] && !checkBeginPoint(p, field)) {
                min = p;
            }
        }

        //if all available rooms have the same height - select a random point
        if(min != null) {
            return min;
        } else {
            Random random = new Random();
            Point p;

            //if the list contains more then one point
            if (rooms.size() > 1)
                p = rooms.get(random.nextInt(rooms.size()));
            else return rooms.get(0);
            return p;
        }
    }
    // for exclude the starting point
    public static boolean checkBeginPoint(Point p, Field field) {
        if(p.getY() == field.getBegin().getY() && p.getX() == field.getBegin().getX()) {
            return true;
        }
        return false;
    }
}
