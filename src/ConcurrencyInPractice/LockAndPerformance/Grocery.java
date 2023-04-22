package ConcurrencyInPractice.LockAndPerformance;

import java.util.*;

public interface Grocery {
    void addFruit(int index, String fruit);
    void addVegetable(int index, String vegetable);
}
