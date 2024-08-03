public class Main {

    public static void main(String[] args) {
        
        Matrix33 m1 = new Matrix33(new Double[]{1.0, 2.0, 3.0}, new Double[]{4.0, 5.0, 6.0}, new Double[]{7.0, 8.0, 9.0});
        Matrix33 m2 = new Matrix33(new Double[]{1.0, 3.0, -1.0}, new Double[]{2.0, 4.0, 2.0}, new Double[]{1.0, 1.0, -1.0});
        Matrix33 m3 = new Matrix33();

        Vector3 v1 = new Vector3(1.0, 0.0, 9.0);
        m2.setColumn(v1, 0);


        System.out.println(Matrix33.rotationYDeg(90.0).multiplyVector(v1));


    }
}