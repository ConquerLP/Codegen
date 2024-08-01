package ch.codegen.math;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Vector {

    private final List<Double> values;
    public Vector(List<Double> values) {
        this.values = new ArrayList<>(values);
    }
    public String toString() {
        StringBuilder string = new StringBuilder("Vector:\n");
        for(int i = 0; i < values.size(); i++){
            string.append("\tX" + i + ": " + values.get(i) + "\n");
        }
        return string.toString();
    }
    private Vector compute(Vector other, BiFunction<Double, Double, Double> operation) {
        if (this.values.size() != other.values.size()) throw new IllegalArgumentException("Vectors must be of the same length");
        return new Vector(IntStream.range(0, values.size())
                .mapToObj(i -> operation.apply(this.values.get(i), other.values.get(i)))
                .collect(Collectors.toList()));
    }
    private Vector compute(Function<Double, Double> operation) {
        return new Vector(IntStream.range(0, values.size())
                .mapToObj(i -> operation.apply(this.values.get(i)))
                .collect(Collectors.toList()));
    }
    public Vector add(Vector other) {
        return compute(other, Double::sum);
    }
    public Vector sub(Vector other) {
        return compute(other, (a,b) -> a - b);
    }
    public Vector scale(double factor) {
        return compute(f -> f * factor);
    }
    public double length() {
        return Math.sqrt(values.stream()
                .map(x -> x * x)
                .reduce(0.0, Double::sum));
    }
    public Vector normalize() {
        return scale(1.0 / length());
    }
    public double dotProduct(Vector other) {
        return compute(other, (a,b) -> a * b).values
                .stream()
                .reduce(0.0, Double::sum);
    }
    public double angleDeg(Vector other) {
        return Math.toDegrees(angleRad(other));
    }
    public double angleRad(Vector other) {
        return Math.acos(dotProduct(other) / (length() * other.length()));
    }
}
