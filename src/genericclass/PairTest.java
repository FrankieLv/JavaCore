package genericclass;

import java.util.ArrayList;
import java.util.List;

public class PairTest {
    public static void main(String[] args) {
        String[] words = {"Mary", "little", "lamb", "had", "a"};
        //Pair<Comparable> pair = ArrayAlg.minmax(words);
        Pair<String> pair = ArrayAlg.minmax(words);
        System.out.println(pair.getMin() + "," + pair.getMax());

        //test if the ? can be used in a normal method in the normal class
        List<Pair<String>> list = new ArrayList<Pair<String>>();
        list.add(pair);
        test(list);
    }

    public static void test(List<? extends Pair<String>> args){
    }

    public static class ArrayAlg{
        //public static Pair<Comparable> minmax(String[] words){
        public static Pair<String> minmax(String[] words){
            if(words == null || words.length == 0) return null;
            String min = words[0];
            String max = words[0];
            for (String w: words) {
                if(min.compareTo(w) > 0) min = w;
                if(max.compareTo(w) < 0) max = w;
            }
            //return new Pair<Comparable>(min, max);
            return new Pair<String>(min, max);
        }
    }
}
