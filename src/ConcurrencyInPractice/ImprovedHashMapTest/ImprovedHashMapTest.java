package ConcurrencyInPractice.ImprovedHashMapTest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ImprovedHashMapTest {
    public static void main(String[] args) throws InterruptedException {

        test(new HashMap<String, String>());
        test(new ImprovedHashMap<String, String>(new HashMap<String, String>()));

    }


    private static void test(Map map) {
        var isError = false;

        map.put("hello", "123");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 1000; i++) {
                    map.put(i, i);
                }
            }
        });

        thread.start();

        for (int i = 1; i <= 1000; i++) {
            if (null == map.get("hello")) {
                isError = true;
                break;
            }
        }

        if (isError) {
            System.out.println(Thread.currentThread() + ": Waohh, I'm lost");
        } else {
            System.out.println(Thread.currentThread() + ": I'm okay");
        }
    }
}

class ImprovedHashMap<K, V> implements Map<K, V>{
    private final Map<K, V> map;

    public ImprovedHashMap(Map<K, V> map){
        this.map = map;
    }

    public synchronized V get(Object key) {
        return map.get(key);
    }

    public synchronized V put(K key, V value) {
        return map.put(key, value);
    }

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean containsKey(Object key) {
        return false;
    }

    public boolean containsValue(Object value) {
        return false;
    }

    public V remove(Object key) {
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> m) {

    }

    public void clear() {

    }

    public Set<K> keySet() {
        return null;
    }

    public Collection<V> values() {
        return null;
    }

    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    public V getOrDefault(Object key, V defaultValue) {
        return Map.super.getOrDefault(key, defaultValue);
    }

    public void forEach(BiConsumer<? super K, ? super V> action) {
        Map.super.forEach(action);
    }

    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        Map.super.replaceAll(function);
    }

    public V putIfAbsent(K key, V value) {
        return Map.super.putIfAbsent(key, value);
    }

    public boolean remove(Object key, Object value) {
        return Map.super.remove(key, value);
    }

    public boolean replace(K key, V oldValue, V newValue) {
        return Map.super.replace(key, oldValue, newValue);
    }

    public V replace(K key, V value) {
        return Map.super.replace(key, value);
    }

    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return Map.super.computeIfAbsent(key, mappingFunction);
    }

    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return Map.super.computeIfPresent(key, remappingFunction);
    }

    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return Map.super.compute(key, remappingFunction);
    }

    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return Map.super.merge(key, value, remappingFunction);
    }
}
