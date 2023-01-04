package builderforclasslevel;

public class CalzonePizza extends Pizza{
    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<CalzonePizza.Builder>{
        private boolean sauceInside;

        public Builder(boolean sauceInside){
            this.sauceInside = sauceInside;
        }

        public CalzonePizza build(){
            return new CalzonePizza(this);
        }

        protected Builder self(){return this;}
    }

    private CalzonePizza(Builder builder){
        super(builder);
        this.sauceInside = builder.sauceInside;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("sauceInside:").append(sauceInside).append(",");
        for (Topping topping: toppings) {
            builder.append("topping:").append(topping).append(",");
        }
        return builder.toString();
    }
}
