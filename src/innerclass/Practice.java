package innerclass;

public class Practice{

    public static class Pair{
        private double min;
        private double max;

		public Pair(double min, double max){
            this.min = min;
            this.max = max;
        }

        public double getMin() {
            return min;
        }

        public double getMax() {
            return max;
        }
    }

    public static Pair minmax(double[] values){
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (double value: values){
            if (min > value) min = value;
            if (max < value) max = value;
        }
        return new Pair(min, max);
    }

    public static void main(String[] args) {
        Pair pair = Practice.minmax(new double[]{1.0, 3.0, 5.0, 10.0});
        System.out.println(pair.getMin());
        System.out.println(pair.getMax());
    }

}