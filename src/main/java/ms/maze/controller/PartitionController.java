package ms.maze.controller;

import ms.maze.model.Field;
import ms.maze.model.Game;
import ms.maze.model.Partitions;

//partition state management class
public class PartitionController {

    //partition state management method
    public static void checkPartition(Game game, Field field) {

        //for first step nothing changing, because partitions are loaded with field
        if(game.getStepCount() == 0)
            return;

        //check blocking conditions. True = lock, false = open
        for(Partitions p : field.getOpen()) {

            //change condition after N steps
            if(game.getStepCount() % p.getCount() == 0) {
                p.setCondition(false);
                break;

            } else {

                //change condition after the one step
                if(!p.getCondition()) {
                    p.setCondition(true);
                }
            }
        }
        //check opening conditions. True = open, false = lock
        for(Partitions p : field.getLock()) {

            //change condition after M steps
            if(game.getStepCount() % p.getCount() == 0) {
                p.setCondition(false);
                break;
            } else {

                //change condition after the one step
                if(!p.getCondition()) {
                    p.setCondition(true);
                }
            }
        }
    }
}
