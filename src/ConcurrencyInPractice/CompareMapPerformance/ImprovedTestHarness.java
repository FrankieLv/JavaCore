package ConcurrencyInPractice.CompareMapPerformance;

import java.util.concurrent.*;

public class ImprovedTestHarness implements Harness{
    private boolean shouldStartAllCoreThreads;
    ImprovedTestHarness(){}
    ImprovedTestHarness(boolean shouldStartAllCoreThreads){
        this.shouldStartAllCoreThreads = shouldStartAllCoreThreads;
    }
    public long timeTasks(int taskCount, final Runnable task) throws InterruptedException, BrokenBarrierException {
        final CyclicBarrier startGate = new CyclicBarrier(taskCount + 1);
        final CyclicBarrier endGate = new CyclicBarrier(taskCount + 1);

        ExecutorService executor = Executors.newFixedThreadPool(taskCount);

        try {
            for (int i = 0; i < taskCount; i++) {
                executor.submit(() -> {
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

    public long timeTasks(int taskCount, int timeoutInSeconds, final Runnable task) throws InterruptedException, BrokenBarrierException {
        final CyclicBarrier startGate = new CyclicBarrier(taskCount + 1);
        final CyclicBarrier endGate = new CyclicBarrier(taskCount + 1);

        //ExecutorService executor = Executors.newFixedThreadPool(taskCount);
        ExecutorService executor = new TimingThreadPool(taskCount, this.shouldStartAllCoreThreads);

        try {
            for (int i = 0; i < taskCount; i++) {
                executor.submit(() -> {
                    try {
                        startGate.await();
                        ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
                        Future<?> future = singleExecutor.submit(task);
                        try {
                            future.get(timeoutInSeconds, TimeUnit.SECONDS);
                        }catch (ExecutionException executionException){
                            executionException.printStackTrace();
                        }catch (TimeoutException timeoutException){
                            future.cancel(true);
                        }finally{
                            singleExecutor.shutdown();
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
