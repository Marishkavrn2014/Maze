package ms.maze.model;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//class for reading a text file with the name "text.txt" and initializing board fields
public class FieldReader {

    //reading a file and load the board fields. Return the Field object
    public Field loadFields(String fileName, final int n, final int m) {

        //creating variables and arrays
        String line;
        Field field = new Field();
        int size = field.getSizeField();
        int column = 0;
        boolean[][] walls = new boolean[size][size];
        Partitions[][] partitions = new Partitions[size][size];
        Partitions opening = new Partitions(PartitionTypes.OPENING, true, n);
        Partitions lockable = new Partitions(PartitionTypes.LOCKABLE, true, m);
        Partitions empty = new Partitions(PartitionTypes.EMPTY, true, 1);
        int[][] heights = new int[size][size];

        //loading initial wall values ​into an array
        fillEmptyWalls(walls, size);
        //loading initial partitions values ​into an array
        fillEmptyPartitions(partitions, size, empty);

        //read the file and initialize the values
        try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
            //loading partitions values from the first line, where 0,1,op means point[1][0], opening partition, lp = lockable partition
            setPartitions(br, partitions, lockable, opening);

            //board loading, where b = start point, w = wall, e = end point, numbers mean height
            while ((line = br.readLine()) != null) {

                //check the symbols in column
                if (checkSymbolsInLine(line)) {

                    //split symbols
                    String[] s = line.split("\\s");
                    List<String> rooms = new LinkedList<>();

                    //checking numbers and characters a-z and A-Z and adding to the list of values
                    for (int i = 0; i < s.length; i++) {
                        if (checkSymbolsInLine(s[i])) {
                            rooms.add(s[i]);
                        }
                    }
                    Pattern patternDigits = Pattern.compile("[0-9]");

                    for (int i = 0; i < rooms.size(); i++) {

                        //select numbers and create a new point in the heights array
                        Matcher matcher = patternDigits.matcher(rooms.get(i));
                        if (matcher.find()) {
                            Point point = new Point(column, i);
                            heights[point.getX()][point.getY()] = Integer.parseInt(rooms.get(i));
                        } else {
                            //find and set the begin point
                            if ("b".equals(rooms.get(i))) {
                                Point point = new Point(column, i);
                                field.setBegin(point);
                            } else {
                                //find and set walls
                                if ("w".equals(rooms.get(i))) {
                                    Point point = new Point(column, i);
                                    walls[point.getX()][point.getY()] = true;
                                } else {
                                    //find and set the finish point
                                    if ("e".equals(rooms.get(i))) {
                                        Point point = new Point(column, i);
                                        field.setFinish(point);
                                    } else {
                                        continue;
                                    }
                                }
                            }
                        }
                    }
                    column++;
                }
            }

            //load the heights, walls and partitions in the Field class
            field.setHeights(heights);
            field.setWalls(walls);
            field.setPartitions(partitions);
        } catch(IOException e){
                    e.printStackTrace();
                }
        return field;
}

//loading initial wall values ​into an array
public static void fillEmptyWalls ( boolean[][] walls, int size) {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            walls[i][j] = false;
        }
    }
}

//loading initial partitions values ​into an array
public static void fillEmptyPartitions (Partitions[][]partitions,int size, Partitions empty) {
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            partitions[i][j] = empty;
        }
    }
}

//finding the symbols in the line
public static boolean checkSymbolsInLine (String line) {
    if (line.matches("(.*)[0-9a-zA-Z]+(.*)")) {
        return true;
    }
    return false;
}

// find the first line with the characters in the file and load the partitions points
public static boolean setPartitions (BufferedReader br, Partitions[][]partitions, Partitions lockable, Partitions opening) throws IOException {
    String line = br.readLine();

    //check empty lines
    while (line.isEmpty())
        line = br.readLine();

    //check string with characters, create points and load into array
    if (checkSymbolsInLine(line)) {
        String[] parts = line.split(" ");
        for (int i = 0; i < parts.length; i++) {
            String[] condition = parts[i].split(",");
            int x = Integer.parseInt(condition[0]);
            int y = Integer.parseInt(condition[1]);
            Point point = new Point(x, y);
            if ("op".equals(condition[2])) {
                partitions[point.getX()][point.getY()] = opening;
            } else {
                if ("lp".equals(condition[2])) {
                    partitions[point.getX()][point.getY()] = lockable;
                }
            }

        }
        return true;
    } else {
        setPartitions(br, partitions, opening, lockable);
    }
    return false;
}
}
