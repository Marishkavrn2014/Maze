package ms.maze;

import ms.maze.controller.MazeGameController;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.System.exit;

//testing the game to calculate the average number of steps
public class GameTests {

    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            System.out.print("Usage: please write a name for the text file as field.txt");
            exit(0);
        } else {

            File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + args[0]);
            if(!file.canRead()) {
                System.out.print("File " + args[0] + " cannot be read");
                exit(0);
            }
        }
        String fileName = System.getProperty("user.dir") + System.getProperty("file.separator") + args[0];
        String resultGame = null;

        //writing the results to the file "test1.txt
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File
                (System.getProperty("user.dir") + System.getProperty("file.separator") + "test1.txt"), true))) {
            for(int j = 2; j <= 10; j++) {
                for (int k = 2; k <= 10; k++) {
                    for (int t = 0; t < 100; t++) {
                        int count = 1000;
                        while (count != 0) {
                            //start the game
                            MazeGameController mazeGame = new MazeGameController();
                            mazeGame.startGame(j, k, t, fileName);
                            //get steps count
                            resultGame = mazeGame.getResult();

                            if (!"Congratulations!!! You win!!!\n".equals(resultGame)) {
                                //save n and m position
                                break;
                            }
                            count--;
                        }

                        if (count == 0) {
                            String s = "\nFor opening partition n = " + j + " times, lockable partition m = " + k + " times, MIN TIME = " + t;
                            bw.write(s);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
