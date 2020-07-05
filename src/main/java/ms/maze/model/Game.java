package ms.maze.model;

//contains the number of steps and file name, activates the loading of fields
public class Game {
    private final String pathFile;
    private int stepCount;
    private final Field field;
    final int n;
    final int m;
    final int startSteps;

    public Game(final String pathFile, final int n, final int m, int startSteps) {
        this.pathFile = pathFile;
        this.n = n;
        this.m = m;
        this.startSteps = startSteps;
        stepCount = startSteps;
        FieldReader fieldReader = new FieldReader();
        field = fieldReader.loadFields(pathFile, n, m);

    }

    //return steps count
    public int getStepCount() {
        return stepCount;
    }
    //change steps count
    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }
    //return file path
    public String getPathFile() {
        return pathFile;
    }
    //return field
    public Field getField() {
        return field;
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public int getRealSteps() {
        return getStepCount() - startSteps;
    }
}
