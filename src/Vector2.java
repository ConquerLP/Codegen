public class Vector2 {

    private final Double[] values;

    public Vector2(Double x, Double y) {
        this.values = new Double[]{x, y};
    }

    public String toString() {
        return String.format("(%.3f, %.3f)", values[0], values[1]);
    }



}
