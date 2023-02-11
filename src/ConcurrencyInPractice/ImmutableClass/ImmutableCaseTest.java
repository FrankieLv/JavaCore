package ConcurrencyInPractice.ImmutableClass;

import java.util.Arrays;

public class ImmutableCaseTest {

    public static void main(String[] args) {
        States[] allStates = new States[]{ new UnsafeStates(), new FinalUnsafeStates(), new SafteStates() };
        writeStates(allStates);
        readStates(allStates);
    }

    private static void writeStates(States[] allStates){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (States state: allStates) {
                    state.getStates()[0] = "AAA";
                }

            }
        });

        thread.start();
    }

    private static void readStates(States[] allStates){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (States states: allStates) {
                    if("AK".equals(states.getStates()[0])){
                        System.out.println(states.getSateName() + ": Everything is good");
                    }else{
                        System.out.println(states.getSateName() + ": Something is bad");
                    }
                }
            }
        });

        thread.start();
    }
}

interface States{
    public String getSateName();
    public String[] getStates();
}
class UnsafeStates implements States{
    private String[] states = new String[]{"AK", "AL"};
    public String getSateName(){
        return "UnSafeStates";
    }
    public String[] getStates(){return states;};
}

class FinalUnsafeStates implements States{
    private final String[] states = new String[]{"AK", "AL"};
    public String getSateName(){
        return "FinalUnSafeStates";
    }
    public String[] getStates(){return states;};
}

class SafteStates implements States{
    private final String[] states = new String[]{"AK", "AL"};
    public String getSateName(){
        return "SafeStates";
    }
    public String[] getStates(){ return (String[])Arrays.copyOf(states, states.length); }
}

