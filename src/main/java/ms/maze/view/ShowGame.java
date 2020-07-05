package ms.maze.view;

import ms.maze.controller.MoveController;
import ms.maze.model.Field;
import ms.maze.model.Game;
import ms.maze.model.Point;

public class ShowGame {
    final Game game;
    public ShowGame(final Game game) {
        this.game = game;
    }

    //print board
    public void printGame(final Game game){
        final Field field = game.getField();
        int sizeLine = 0;
        //print lines
        while(sizeLine < field.getSizeField()) {

            //print dividing lines between rooms
            printLineSeparator(8);
            int sizeColumn = 0;

            //print Columns
            while (sizeColumn < field.getSizeField()) {
                //print dividing lines between rooms
                printColumnSeparator();

                //room printing: B = start, E = end, C = current position, L = partition blocking, W = walls, numbers mean height
                if(field.getBegin().getX() == sizeLine && field.getBegin().getY() == sizeColumn) {
                    System.out.print(" B ");
                } else {
                    if(field.getCurrent().getX() == sizeLine && field.getCurrent().getY() == sizeColumn) {
                        System.out.print(" C ");
                    } else {
                        if(field.getWalls()[sizeLine][sizeColumn]) {
                            System.out.print(" W ");
                        } else {
                                if(!MoveController.checkPartitions(new Point(sizeLine, sizeColumn), field)) {
                                    System.out.print(" L ");
                                } else {
                                    if(field.getFinish().getX() == sizeLine && field.getFinish().getY() == sizeColumn) {
                                        System.out.print(" E ");
                                    } else {
                                        if(field.getHeights()[sizeLine][sizeColumn] / 10 == 0) {
                                            System.out.print(" ");
                                        }
                                        System.out.print(field.getHeights()[sizeLine][sizeColumn] + " ");
                                    }
                                }
                            }
                        }

                }
                sizeColumn++;
            }

            //print the last separator
            System.out.println("|");
            sizeLine++;
        }
        //print the last separator
        printLineSeparator(sizeLine);
    }

    //method of printing results
    public void printResults(Field field, Game game) {
        //System.out.println(ResultsController.result(field, game));
        System.out.println(game.getStepCount());
    }
    //method of printing separator between the lines
    public void printLineSeparator(int size) {

        while (size != 0) {
            System.out.print(" ----");
            size--;
        }
        System.out.println("");
    }

    //method of printing separator between the columns
    public void printColumnSeparator() {
        System.out.print("| ");
    }

}
