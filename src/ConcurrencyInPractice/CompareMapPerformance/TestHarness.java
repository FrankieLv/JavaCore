package ConcurrencyInPractice.CompareMapPerformance;

import java.util.concurrent.CountDownLatch;

public class TestHarness implements Harness{
    public long timeTasks(int threadCount, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(threadCount);

        for(int i=0;i<threadCount;i++) {
            Thread t = new Thread() {
                public void run() {
                    try{
                        startGate.await();
                        try{
                            task.run();
                        }finally {
                            endGate.countDown();
                        }
                    }catch (InterruptedException ignore) {}
                }
            };
            t.start();
        }

        long startTime = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

}
