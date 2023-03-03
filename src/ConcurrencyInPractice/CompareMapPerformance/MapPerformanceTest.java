package ConcurrencyInPractice.CompareMapPerformance;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

public class MapPerformanceTest {
    public static void main(String[] args) throws InterruptedException{
        Map<String, String> improvedHashMap = new ImprovedHashMap<>(new HashMap());
        long improvedMapTime = timeTasks(improvedHashMap);

        Map<String, String> synchronizedMap = Collections.synchronizedMap(new HashMap<String, String>());
        long synchronizedMapTime = timeTasks(synchronizedMap);

        Map<String, String> concurrentMap = new ConcurrentHashMap();
        long concurrentMapTime = timeTasks(concurrentMap);

        comparePerformance(improvedMapTime, synchronizedMapTime, concurrentMapTime);
    }

    public static long timeTasks(Map<String, String> map) throws InterruptedException{
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(20);
        for(int i = 0; i < 20; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        startGate.await();
                        for (int j = 1; j <= 3000; j++) {
                            map.put(j + "", j + "");
                        }
                        endGate.countDown();
                    }catch(InterruptedException e){}
                }
            });
            thread.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

    private static void comparePerformance(long improvedMapTime, long synchronizedMapTime, long concurrentMapTime){
        if((improvedMapTime - synchronizedMapTime) > 0){
            String result = "improvedMapTime < synchronizedMapTime";
            if((synchronizedMapTime - concurrentMapTime) > 0){
                result = result + " < concurrentMapTime";
                System.out.println(result);
            }else{
                System.out.println("something wrong");
            }
        }else{
            System.out.println("something wrong");
        }
    }
}
