public class Timer {
    private long timeLimit; // Time limit in seconds
    private long startTime;

    public Timer(long timeLimit) {
        this.timeLimit = timeLimit * 1000; // Convert to milliseconds
    }

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public boolean isTimeUp() {
        return (System.currentTimeMillis() - startTime) > timeLimit;
    }
}

