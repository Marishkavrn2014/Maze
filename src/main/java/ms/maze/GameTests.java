package ms.maze;

import ms.maze.controller.MazeGameController;

import java.io.*;

//testing the game to calculate the average number of steps
public class GameTests {

    public static void main(String[] args) throws IOException {
        if(args.length < 2) {
            System.out.print("Usage: please write a given time as an argument and field.txt");
            System.exit(0);
        } else {
            if(!args[0].matches("[0-9]")) {
                System.out.print("Please, write a number");
                System.exit(0);
            }
            File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + args[1]);
            if(!file.canRead()) {
                System.out.print("File " + args[1] + " cannot be read");
            }
        }
        String fileName = System.getProperty("user.dir") + System.getProperty("file.separator") + args[1];
        int steps = 0;
        int n = 0, m = 0;
        int time = Integer.parseInt(args[0]);
        int count = 10*10;
        String resultGame = null;

        while (steps == 0 || count == 0) {
            for(int j = 2; j <= 10; j++) {
                if(steps == 0) {
                for (int k = 2; k <= 10; k++) {
                    //start the game
                    MazeGameController mazeGame = new MazeGameController();

                    //get steps count
                    steps = mazeGame.startGame(j, k, time, fileName);
                    resultGame = mazeGame.getResult();

                    if ("Congratulations!!! You win!!!\n".equals(resultGame)) {
                        //save n and m position
                        n = j;
                        m = k;
                        break;
                    }
                }
                } else break;
            }
            count--;
        }
        //calculate average

        //writing the results to the file "test1.txt
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File
                (System.getProperty("user.dir") + System.getProperty("file.separator") + "test1.txt"), true))) {
            if(n != 0) {
                String result = args[1] + "\n" +
                        "The ball can reach the end of the maze at a given time" +
                        "\n" +
                        "Given time(the number of steps at the beginning): " +
                        time +
                        " lockable partitions n(steps): " +
                        n +
                        " opening partitions m(steps): " +
                        m +
                        " steps: " +
                        steps;
                bw.newLine();
                bw.write(result);
            } else {
                String result = "The ball could not reach the end of the maze at a given time\n";
                bw.write(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
