package ConcurrencyInPractice.CompareMapPerformance;

import java.util.concurrent.*;

public class ImprovedTestHarness implements Harness{
    public long timeTasks(int taskCount, int timeoutInSeconds, final Runnable task) throws InterruptedException, BrokenBarrierException {
        final CyclicBarrier startGate = new CyclicBarrier(taskCount + 1);
        final CyclicBarrier endGate = new CyclicBarrier(taskCount + 1);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        try {
            for (int i = 0; i < taskCount; i++) {
                executor.submit(() -> {
                    try {
                        startGate.await();
                        try {
                            Future<?> future = executor.submit(task);
                            try{
                                future.get(timeoutInSeconds, TimeUnit.SECONDS);
                            }catch (ExecutionException executionException){
                                executionException.printStackTrace();
                            }catch (TimeoutException timeoutException){
                                future.cancel(true);
                            }
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
