package builderforclasslevel;

public class PizzaTest {
    public static void main(String[] args) {
        NewyorkPizza np = new NewyorkPizza.Builder(NewyorkPizza.Size.LARGE).addTopping(Pizza.Topping.SAUSAGE).addTopping(Pizza.Topping.ONION).build();
        CalzonePizza cp = new CalzonePizza.Builder(true).addTopping(Pizza.Topping.HAM).build();

        System.out.println(np.toString());
        System.out.println(cp.toString());
    }
}
