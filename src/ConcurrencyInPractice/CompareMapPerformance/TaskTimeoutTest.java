package ConcurrencyInPractice.CompareMapPerformance;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class TaskTimeoutTest {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        System.out.println("ImprovedTestHarness:");
        ImprovedTestHarness improvedTestHarness = new ImprovedTestHarness();
        System.out.println("Tasks will be cancelled:");
        testCancelTask(improvedTestHarness, 5, 7);
        System.out.println("Tasks won't be cancelled:");
        testCancelTask(improvedTestHarness, 5, 3);
    }


    private static void testCancelTask(Harness harness, int timeoutIndSeconds, int processTimeInSeconds) throws InterruptedException, BrokenBarrierException {

            long timeCost = harness.timeTasks(2, timeoutIndSeconds, ()->{
                try{
                    TimeUnit.SECONDS.sleep(processTimeInSeconds);
                }catch(InterruptedException interruptedException){
                    System.out.println(Thread.currentThread().getName() + " has been cancelled");
                }
            });
            System.out.println("time:" + timeCost);
    }
}
