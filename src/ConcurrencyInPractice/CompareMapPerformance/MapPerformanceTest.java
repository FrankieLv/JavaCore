package ConcurrencyInPractice.CompareMapPerformance;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class MapPerformanceTest {
    public static void main(String[] args) throws InterruptedException{
        TestHarness testHarness = new TestHarness();


        Map<String, String> improvedHashMap = new ImprovedHashMap<>(new HashMap());
        long improvedMapTime = testMapPerformance(improvedHashMap);

        Map<String, String> synchronizedMap = Collections.synchronizedMap(new HashMap<String, String>());
        long synchronizedMapTime = testMapPerformance(synchronizedMap);

        Map<String, String> concurrentMap = new ConcurrentHashMap();
        long concurrentMapTime = testMapPerformance(concurrentMap);

        comparePerformance(improvedMapTime, synchronizedMapTime, concurrentMapTime);
    }

    private static long testMapPerformance(Map<String, String> map) throws InterruptedException{
        TestHarness testHarness = new TestHarness();

        final Runnable mapTask = new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i< 3000;i++) {
                    map.put(i + "", i + "");
                }
            }
        };
        return testHarness.timeTasks(30, mapTask);
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
