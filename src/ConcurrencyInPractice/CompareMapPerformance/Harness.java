package ConcurrencyInPractice.CompareMapPerformance;

import java.util.concurrent.BrokenBarrierException;

public interface Harness {
    public long timeTasks(int taskCount, final Runnable task) throws InterruptedException, BrokenBarrierException;
    public long timeTasks(int taskCount, int timeoutInSeconds, final Runnable task) throws InterruptedException, BrokenBarrierException;
}
