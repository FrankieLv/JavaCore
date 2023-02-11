package ConcurrencyInPractice.others;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SafeAndUnsafeStatesTest {
    ExecutorService executorService;
    public static void main(String[] args) {
        SafeAndUnsafeStatesTest safeAndUnsafeStatesTest = new SafeAndUnsafeStatesTest();
        safeAndUnsafeStatesTest.start();
    }

    private void start(){
        executorService = Executors.newFixedThreadPool(2);

        UnsafeStates unsafeStates = new UnsafeStates();
        FinalUnsafeStates finalUnsafeStates = new FinalUnsafeStates();
        SafteStates safeStates = new SafteStates();

        updateStates(unsafeStates, finalUnsafeStates, safeStates);
        readStates(unsafeStates, finalUnsafeStates, safeStates);

        executorService.shutdown();
    }

    private void updateStates(UnsafeStates unsafeStates, FinalUnsafeStates finalUnsafeStates, SafteStates safeStates ){
        executorService.submit(() -> {
            unsafeStates.getStates()[0] = "Villa";
            finalUnsafeStates.getStates()[0] = "Villa";
            safeStates.getStates()[0] = "Villa";
        });
    }

    private  void readStates(UnsafeStates unsafeStates, FinalUnsafeStates finalUnsafeStates, SafteStates safeStates ){
        executorService.submit(() -> {
            System.out.println("unsafeStates[0]:" + unsafeStates.getStates()[0]);
            System.out.println("finalunsafeStates[0]:" + finalUnsafeStates.getStates()[0]);
            System.out.println("safeStates[0]:" + safeStates.getStates()[0]);
        });
    }
}

class UnsafeStates{
    private String[] states = new String[]{"AK", "AL"};
    public String[] getStates(){return states;};
}

class FinalUnsafeStates {
    private final String[] states = new String[]{"AK", "AL"};
    public String[] getStates(){ return states; };
}

class SafteStates {
    private final String[] states = new String[]{"AK", "AL"};
    public String[] getStates(){
        return (String[])Arrays.copyOf(states, states.length);
    }
}

