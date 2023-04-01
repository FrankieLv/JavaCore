package ConcurrencyInPractice.CompareMapPerformance;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimingThreadPool extends ThreadPoolExecutor {
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private static final Logger LOGGER = Logger.getLogger("TimingThreadPool");
    private final AtomicLong numTasks = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();

    public TimingThreadPool(int corePoolSize, boolean shouldStartAllCoreThreads) {
        super(corePoolSize, corePoolSize, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        if(shouldStartAllCoreThreads){
            this.prestartAllCoreThreads();
        }
    }

    protected void beforeExecute(Thread t, Runnable r){
        super.beforeExecute(t, r);
        LOGGER.fine(String.format("Thread %s: start %s", t, r));
        startTime.set(System.nanoTime());
    }


    protected void afterExecute(Runnable r, Throwable t){
        try{
            long endTime = System.nanoTime();
            long taskTime = endTime - startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            LOGGER.fine(String.format("Thread %s: end %s, time=%dns", t, r, taskTime));
        }finally{
            super.afterExecute(r, t);
        }
    }

    protected void terminated(){
        try{
            LOGGER.fine(String.format("Terminated: avg time=%dns", totalTime.get() / numTasks.get()));
            System.out.println(String.format("Terminated: avg time=%dns", totalTime.get() / numTasks.get()));
        }finally {
            super.terminated();
        }
    }
}
