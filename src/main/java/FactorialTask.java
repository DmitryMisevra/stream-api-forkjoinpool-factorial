import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {

    private final long start;
    private final long end;
    public static final long THRESHOLD = 5;

    public FactorialTask(long n) {
        this(1, n);
    }

    private FactorialTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = (end - start + 1);
        if (length <= THRESHOLD) {
            return factorial();
        }

        long mid = length / 2;
        FactorialTask firstTask = new FactorialTask(start, start + mid);
        FactorialTask secondTask = new FactorialTask(start + mid + 1, end);
        firstTask.fork();
        return secondTask.compute() * firstTask.join();
    }

    private long factorial() {
        long result = 1;
        for (long i = start; i <= end; i++) {
            result *= i;
        }
        return result;
    }
}
