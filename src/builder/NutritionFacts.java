package builder;

public class NutritionFacts {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;

    static class Builder{
        private final int servingSize;
        private final int servings;
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        public Builder(int servingSize, int servings){
            this.servingSize = servingSize;
            this.servings = servings;
        }
        public Builder calories(int calories){
            this.calories = calories;
            return this;
        }

        public Builder fat(int fat){
            this.fat = fat;
            return this;
        }

        public Builder sodium(int sodium){
            this.sodium = sodium;
            return this;
        }

        public NutritionFacts build(){
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder){
        this.servings = builder.servings;
        this.servingSize = builder.servingSize;
        this.fat = builder.fat;
        this.calories = builder.calories;
        this.sodium = builder.sodium;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("servingSize:").append(servingSize).append(",")
                .append("servings:").append(servings).append(",")
                .append("fat").append(fat).append(",")
                .append("calories").append(calories).append(",")
                .append("sodium").append(sodium);
        return builder.toString();
    }
}
