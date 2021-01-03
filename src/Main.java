import java.util.Random;

public class Main {

    /*x1≥0,x2≥0,x3≥0,
    x1^2+x2^2+x3^2≤12,
    x1^2+x3^2≤7
     */
    public static void main(String[] args) {
    MonteCarlo monteCarlo=new MonteCarlo(1000000);
   double solidVolume= monteCarlo.compute();
   System.out.println(solidVolume);
    }

}
