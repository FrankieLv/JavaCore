package ConcurrencyInPractice.CompareMapPerformance;

import java.util.concurrent.*;

public class ImprovedTestHarness implements Harness{
    public long timeTasks(int taskCount, final Runnable task) throws InterruptedException, BrokenBarrierException {
        final CyclicBarrier startGate = new CyclicBarrier(taskCount);
        final CyclicBarrier endGate = new CyclicBarrier(taskCount);

        ExecutorService executor = Executors.newFixedThreadPool(taskCount);

        try {
            for (int i = 0; i < taskCount; i++) {
                executor.execute(() -> {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.await();
                        }
                    } catch (InterruptedException | BrokenBarrierException ignore) {
                    }
                });
            }

            long startTime = System.nanoTime();
            startGate.await();
            endGate.await();
            long endTime = System.nanoTime();

            return endTime - startTime;
        } finally {
            executor.shutdown();
        }
    }
}
