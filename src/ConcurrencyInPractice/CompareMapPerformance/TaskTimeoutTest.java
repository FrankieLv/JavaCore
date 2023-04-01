package ConcurrencyInPractice.CompareMapPerformance;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;


public class TaskTimeoutTest {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        System.out.println("ImprovedTestHarness: shouldStartAllCoreThreads = true");
        ImprovedTestHarness improvedTestHarness01 = new ImprovedTestHarness(true);
        System.out.println("Tasks will be cancelled:");
        testCancelTask(improvedTestHarness01, 5, 7);
        System.out.println("Tasks won't be cancelled:");
        testCancelTask(improvedTestHarness01, 5, 3);

        System.out.println("ImprovedTestHarness: shouldStartAllCoreThreads = false");
        ImprovedTestHarness improvedTestHarness02 = new ImprovedTestHarness(false);
        System.out.println("Tasks will be cancelled:");
        testCancelTask(improvedTestHarness02, 5, 7);
        System.out.println("Tasks won't be cancelled:");
        testCancelTask(improvedTestHarness02, 5, 3);
    }


    private static void testCancelTask(Harness harness, int timeoutIndSeconds, int processTimeInSeconds) throws InterruptedException, BrokenBarrierException {

            long timeCost = harness.timeTasks(5, timeoutIndSeconds, ()->{
                try{
                    //System.out.println(Thread.currentThread().getName() + " start time:" + System.currentTimeMillis());
                    TimeUnit.SECONDS.sleep(processTimeInSeconds);
                }catch(InterruptedException interruptedException){
                    //System.out.println(Thread.currentThread().getName() + " has been cancelled");
                }
            });
            System.out.println("total time:" + timeCost);
    }
}
