package ms.maze.controller;

import ms.maze.model.Field;
import ms.maze.model.Game;
import ms.maze.model.Point;

//win control
public class ResultsController {

    String result;

    //checking board, return a string with the result or null
    public void result(final Field field, final Game game, Point point) {

        //if there are no fields for steps
        if(point == field.getCurrent()) {
            result =  "You lose!\n";
        } else {

            //if the current point is the finish point
            if (field.getCurrent().getX() == field.getFinish().getX() && field.getCurrent().getY() == field.getFinish().getY()) {
                result = "Congratulations!!! You win!!!\n";
            } else {

                //define a loop and finish with a loss
                if(game.getStepCount() > 3000) {
                    result =  "You are lost!\n";
                } else {
                    result = null;
                }
            }
        }
    }

    public String getResult(final Field field, final Game game, Point point) {
        result(field, game, point);
        return result;
    }
}
