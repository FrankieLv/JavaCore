package builderforclasslevel;

public class NewyorkPizza extends Pizza{
    public enum Size { SMALL, MEDIUM, LARGE};
    private final Size size;

    public static class Builder extends Pizza.Builder<NewyorkPizza.Builder>{
        private Size size;
        public Builder(Size size){
            this.size = size;
        }
        public NewyorkPizza build(){
            return new NewyorkPizza(this);
        }

        protected Builder self(){ return this;}
    }

    private NewyorkPizza(Builder builder){
        super(builder);
        this.size = builder.size;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Size:").append(size.toString()).append(",");
        for (Topping topping: toppings) {
            builder.append("topping:").append(topping).append(",");
        }
        return builder.toString();
    }
}
