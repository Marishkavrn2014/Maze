package ms.maze.model;

//An object for storing types and conditions of partitions.
// There is also a counter - how often the partition is closed and opened
public class Partitions {

    private final PartitionTypes type;
    private boolean condition;
    private final int count;

    public Partitions(PartitionTypes type, boolean condition, int count) {
        this.type = type;
        this.condition = condition;
        this.count = count;

    }
    //for change the condition
    public void setCondition(boolean condition) {
        this.condition = condition;
    }

    //return actual condition
    public boolean getCondition() {
        return condition;
    }

    //return type
    public PartitionTypes getType() {
        return type;
    }

    //return count
    public int getCount() {
        return count;
    }
}
