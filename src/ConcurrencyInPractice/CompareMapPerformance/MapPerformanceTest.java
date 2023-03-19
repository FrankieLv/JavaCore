package ConcurrencyInPractice.CompareMapPerformance;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;

public class MapPerformanceTest {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException{

        Map<String, String> improvedHashMap = new ImprovedHashMap<>(new HashMap());
        Map<String, String> synchronizedMap = Collections.synchronizedMap(new HashMap<String, String>());
        Map<String, String> concurrentMap = new ConcurrentHashMap();

        System.out.println("testHarness:");
        TestHarness testHarness = new TestHarness();
        testMapPerformance(testHarness, improvedHashMap);
        testMapPerformance(testHarness, synchronizedMap);
        testMapPerformance(testHarness, concurrentMap);

        System.out.println("ImprovedTestHarness:");
        ImprovedTestHarness improvedTestHarness = new ImprovedTestHarness();
        testMapPerformance(improvedTestHarness, improvedHashMap);
        testMapPerformance(improvedTestHarness, synchronizedMap);
        testMapPerformance(improvedTestHarness, concurrentMap);


    }

    private static void testMapPerformance(Harness harness, Map<String, String> map) throws InterruptedException, BrokenBarrierException {

        long time = harness.timeTasks(30, 20,()->{
            for(int i = 0; i< 3000; i++) {
            map.put(i + "", i + "");
        }});
        System.out.println(map.getClass().getName() + ":" + time);
        map.clear();
    }

}
