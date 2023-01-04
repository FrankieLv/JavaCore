package builder;

public class NutritionFactsTest {
    public static void main(String[] args) {
        NutritionFacts nf = new NutritionFacts.Builder(100, 200).calories(300).fat(400).calories(500).sodium(600).build();
        System.out.println(nf.toString());
    }
}
