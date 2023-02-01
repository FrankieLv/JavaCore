package ConcurrencyInPractice.UnsafeThreadTest;

class UnsafeSequence {
    private int value;
    public int getNext(){
        return value++;
    }
}

public class UnsafeSequenceTest {
    public static void main(String[] args) {
        UnsafeSequence unsafeSequence = new UnsafeSequence();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 10000){
                    System.out.println("ThreadA NextValue:" + unsafeSequence.getNext());
                    i++;
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 10000){
                    System.out.println("ThreadB NextValue:" + unsafeSequence.getNext());
                    i++;
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 10000){
                    System.out.println("ThreadC NextValue:" + unsafeSequence.getNext());
                    i++;
                }
            }
        });

        Thread threadD = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 10000){
                    System.out.println("ThreadD NextValue:" + unsafeSequence.getNext());
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
