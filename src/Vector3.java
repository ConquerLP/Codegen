public class Vector3 {

    private final Double[] values;

    public Vector3(double x, double y, double z) {
        this.values = new Double[]{x, y, z};
    }

    public Vector3() {
        this(0, 0, 0);
    }

    public String toString(){
        return String.format("(%.3f, %.3f, %.3f)", values[0], values[1], values[2]);
    }

    public Vector2 reduce() {
        return new Vector2(values[0], values[1]);
    }

    public Vector3 add(Vector3 other) {
        return new Vector3(this.values[0] + other.values[0], this.values[1] + other.values[1], this.values[2] + other.values[2]);
    }

    public Vector3 sub(Vector3 other) {
        return new Vector3(this.values[0] - other.values[0], this.values[1] - other.values[1], this.values[2] - other.values[2]);
    }

    public Vector3 scale(double scalar) {
        return new Vector3(this.values[0] * scalar, this.values[1] * scalar, this.values[2] * scalar);
    }

    public Vector3 normalize() {
        double length = length();
        return new Vector3(this.values[0] / length, this.values[1] / length, this.values[2] / length);
    }

    public double length() {
        return Math.sqrt(this.values[0] * this.values[0] + this.values[1] * this.values[1] + this.values[2] * this.values[2]);
    }

    public double dotP(Vector3 other) {
        return this.values[0] * other.values[0] + this.values[1] * other.values[1] + this.values[2] * other.values[2];
    }

    public double angleDeg(Vector3 other) {
        return Math.toDegrees(angleRad(other));
    }

    public double angleRad(Vector3 other) {
        return Math.acos(this.dotP(other) / (this.length() * other.length()));
    }

    public Vector3 crossProduct(Vector3 other) {
        return new Vector3(values[1] * other.values[2] - values[2] * other.values[1],
                values[2] * other.values[0] - values[0] * other.values[2],
                values[0] * other.values[1] - values[1] * other.values[0]);
    }

    public Double[] getValues() {
        return values;
    }
}
