package ms.maze.controller;

import ms.maze.model.Game;
import ms.maze.model.Point;
import ms.maze.view.ShowGame;

// starting
public class MazeGameController {

    private Game game;

    private String result = null;

    public int startGame(final int n, final int m, final int startSteps, String pathFile) {

        //load field from text file
        game = new Game(pathFile, n, m, startSteps);

        //load view class
        final ShowGame sr = new ShowGame(game);

        //get the start point
        Point p = game.getField().getBegin();

        //set the starting position at the start point
        game.getField().setCurrent(p);

        //could see all field state in console
        //sr.printGame(game);

        ResultsController resultsController = new ResultsController();
        //check win / loose state
        while (result == null) {
            //sr.printGame(game);
            //System.out.println("");

            //check and set partition parameters before moving
            PartitionController.checkPartition(game, game.getField());

            //choose and set the next position
            p = MoveController.makeStep(game.getField(), p, game);
            result = resultsController.getResult(game.getField(), game, p);
            game.getField().setCurrent(p);


            //count the steps
            game.setStepCount(game.getStepCount() + 1);
        }

        //print the result of winning or losing
        //sr.printResults(game.getField(), game);
        //System.out.print(getResult());
        if("Congratulations!!! You win!!!\n".equals(getResult())) {
            return game.getRealSteps();
        } else return 0;

    }

    public Game getGame() {
        return game;
    }

    public String getResult() {
        return result;
    }
}
