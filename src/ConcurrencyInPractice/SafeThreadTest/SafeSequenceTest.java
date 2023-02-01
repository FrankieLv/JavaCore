package ConcurrencyInPractice.SafeThreadTest;

class SafeSequence {
    private int value;
    public synchronized int getNext(){
        return value++;
    }
}

public class SafeSequenceTest {
    public static void main(String[] args) {
        SafeSequence safeSequence = new SafeSequence();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 10000){
                    System.out.println("ThreadA NextValue:" + safeSequence.getNext());
                    i++;
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 10000){
                    System.out.println("ThreadB NextValue:" + safeSequence.getNext());
                    i++;
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 10000){
                    System.out.println("ThreadC NextValue:" + safeSequence.getNext());
                    i++;
                }
            }
        });

        Thread threadD = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 10000){
                    System.out.println("ThreadD NextValue:" + safeSequence.getNext());
                    i++;
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
    }
}
