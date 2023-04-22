package ConcurrencyInPractice.LockAndPerformance;

import java.util.ArrayList;
import java.util.List;

class ImprovedSynchornizedGrocery implements Grocery{
    private final List<String> fruits = new ArrayList<>();
    private final List<String> vegetables = new ArrayList<>();

    static final Object lockFruits = new Object();

    static final Object lockVegetables = new Object();

    @Override
    public void addFruit(int index, String fruit) {
        synchronized (lockFruits){
            fruits.add(index, fruit);
        }
    }

    @Override
    public void addVegetable(int index, String vegetable) {
        synchronized (lockVegetables){
            vegetables.add(index, vegetable);
        }
    }
}
