public class Matrix33 {

    private final Double[][] values;

    public Matrix33(Double[] v1, Double[] v2, Double[] v3) {
        values = new Double[][]{
                {v1[0], v2[0], v3[0]},
                {v1[1], v2[1], v3[1]},
                {v1[2], v2[2], v3[2]}
        };
    }

    public Matrix33() {
        this(new Double[]{1.0, 0.0, 0.0}, new Double[]{0.0, 1.0, 0.0}, new Double[]{0.0, 0.0, 1.0});
    }

    public String toString() {
        return String.format("(%.3f, %.3f, %.3f)\n(%.3f, %.3f, %.3f)\n(%.3f, %.3f, %.3f)", values[0][0], values[0][1], values[0][2],
                values[1][0], values[1][1], values[1][2],
                values[2][0], values[2][1], values[2][2]);
    }

    public Matrix33 add(Matrix33 other) {
        Double[][] result = new Double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[j][i] = values[i][j] + other.values[i][j];
            }
        }
        return new Matrix33(result[0], result[1], result[2]);
    }

    public Matrix33 sub(Matrix33 other) {
        Double[][] result = new Double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[j][i] = values[i][j] - other.values[i][j];
            }
        }
        return new Matrix33(result[0], result[1], result[2]);
    }

    public Matrix33 scale(double scalar) {
        Double[][] result = new Double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[j][i] = values[i][j] * scalar;
            }
        }
        return new Matrix33(result[0], result[1], result[2]);
    }

    public Matrix33 multiply(Matrix33 other) {
        Double[][] result = new Double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[j][i] = values[i][0] * other.values[0][j] + values[i][1] * other.values[1][j] + values[i][2] * other.values[2][j];
            }
        }
        return new Matrix33(result[0], result[1], result[2]);
    }

    public Vector3 multiplyVector(Vector3 vector) {
        Double[] result = new Double[3];
        Double[] vectorValues = vector.getValues();
        for (int i = 0; i < 3; i++) {
            result[i] = values[i][0] * vectorValues[0] + values[i][1] * vectorValues[1] + values[i][2] * vectorValues[2];
        }
        return new Vector3(result[0], result[1], result[2]);
    }

    public Matrix33 transpose() {
        Double[][] result = new Double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[j][i] = values[j][i];
            }
        }
        return new Matrix33(result[0], result[1], result[2]);
    }

    public static Matrix33 rotationXRad(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Matrix33(new Double[]{1.0, 0.0, 0.0}, new Double[]{0.0, cos, sin}, new Double[]{0.0, -sin, cos});
    }

    public static Matrix33 rotationXDeg(double angle) {
        return rotationXRad(Math.toRadians(angle));
    }

    public static Matrix33 rotationYRad(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Matrix33(new Double[]{cos, 0.0, -sin}, new Double[]{0.0, 1.0, 0.0}, new Double[]{sin, 0.0, cos});
    }

    public static Matrix33 rotationYDeg(double angle) {
        return rotationYRad(Math.toRadians(angle));
    }

    public static Matrix33 rotationZRad(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Matrix33(new Double[]{cos, sin, 0.0}, new Double[]{-sin, cos, 0.0}, new Double[]{0.0, 0.0, 1.0});
    }

    public static Matrix33 rotationZDeg(double angle) {
        return rotationZRad(Math.toRadians(angle));
    }

    public double determinant() {
        return (values[0][0] * values[1][1] * values[2][2] + values[0][1] * values[1][2] * values[2][0] + values[0][2] * values[1][0] * values[2][1])
                - (values[0][2] * values[1][1] * values[2][0] + values[0][1] * values[1][0] * values[2][2] + values[0][0] * values[1][2] * values[2][1]);
    }

    public Matrix33 inverse() {
        double det = determinant();
        if (det == 0.0) {
            throw new ArithmeticException("Matrix is not invertible");
        }
        double invDet = 1.0 / det;
        Double[][] result = new Double[3][3];
        result[0][0] = (values[1][1] * values[2][2] - values[1][2] * values[2][1]) * invDet;
        result[1][0] = (values[0][2] * values[2][1] - values[0][1] * values[2][2]) * invDet;
        result[2][0] = (values[0][1] * values[1][2] - values[0][2] * values[1][1]) * invDet;
        result[0][1] = (values[1][2] * values[2][0] - values[1][0] * values[2][2]) * invDet;
        result[1][1] = (values[0][0] * values[2][2] - values[0][2] * values[2][0]) * invDet;
        result[2][1] = (values[0][2] * values[1][0] - values[0][0] * values[1][2]) * invDet;
        result[0][2] = (values[1][0] * values[2][1] - values[1][1] * values[2][0]) * invDet;
        result[1][2] = (values[0][1] * values[2][0] - values[0][0] * values[2][1]) * invDet;
        result[2][2] = (values[0][0] * values[1][1] - values[0][1] * values[1][0]) * invDet;
        return new Matrix33(result[0], result[1], result[2]);
    }

    public void setColumn(Vector3 column, int index) {
        Double[] columnValues = column.getValues();
        values[0][index] = columnValues[0];
        values[1][index] = columnValues[1];
        values[2][index] = columnValues[2];
    }

}