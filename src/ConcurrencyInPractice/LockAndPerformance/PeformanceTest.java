package ConcurrencyInPractice.LockAndPerformance;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.List;

public class PeformanceTest {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException{

        Grocery synchornizedGrocery = new SynchornizedGrocery();
        System.out.println("synchornizedGrocery running time:" +
                testGrocery(
                        List.of((Integer j) -> { synchornizedGrocery.addFruit(j, "apple");},
                                (Integer j) -> { synchornizedGrocery.addVegetable(j, "tomato");}
                        ))
        );

        Grocery improvedSynchornizedGrocery = new ImprovedSynchornizedGrocery();
        System.out.println("improvedSynchornizedGrocery running time:" +
                testGrocery(
                        List.of((Integer j) -> { improvedSynchornizedGrocery.addFruit(j, "apple");},
                                (Integer j) -> { improvedSynchornizedGrocery.addVegetable(j, "tomato");}
                ))
        );

    }

    private static long testGrocery(List<Consumer<Integer>> taskList) throws InterruptedException, BrokenBarrierException{
        ExecutorService executor = Executors.newFixedThreadPool(taskList.size());
        final CyclicBarrier startGate = new CyclicBarrier(taskList.size() + 1);
        final CyclicBarrier endGate = new CyclicBarrier(taskList.size() + 1);

        try{
            taskList.forEach((Consumer<Integer> task) -> {
                executor.submit(() -> {
                    try {
                        startGate.await();
                        for (int j = 0; j < 1000; j++) {
                            task.accept(j);
                        }
                        endGate.await();
                    } catch (InterruptedException | BrokenBarrierException ignore) {
                    }
                });
            });


            long startTime = System.nanoTime();
            startGate.await();
            endGate.await();
            long endTime = System.nanoTime();

            return endTime - startTime;
        }finally {
            executor.shutdown();
        }
    }

}
