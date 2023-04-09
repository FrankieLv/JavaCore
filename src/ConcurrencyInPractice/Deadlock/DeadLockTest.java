package ConcurrencyInPractice.Deadlock;

import java.util.concurrent.*;

public class DeadLockTest {
    static final Object lock1 = new Object();
    static final Object lock2 = new Object();
    
    public static void main(String[] args) throws InterruptedException {
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
                    try{
                        synchronized (lock1){
                            TimeUnit.SECONDS.sleep(3);
                            System.out.println(Thread.currentThread().getName() + " has the lock1:" + lock1 + ", and attempts to get the lock2" + lock2);
                            synchronized (lock2){
                                System.out.println(Thread.currentThread().getName() + "get the lock1 -> lock2");
                            }
                        } 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                
        executor.submit(() -> {
                    try{
                        synchronized (lock2){
                            TimeUnit.SECONDS.sleep(3);
                            System.out.println(Thread.currentThread().getName() + " has the lock2:" + lock2 + ", and attempts to get the lock1" + lock1);
                            synchronized (lock1){
                                System.out.println(Thread.currentThread().getName() + "get the lock2 -> lock1");
                            }
                        }
                    } catch (InterruptedException e) {
                                e.printStackTrace();
                    }
                });
    }
}
