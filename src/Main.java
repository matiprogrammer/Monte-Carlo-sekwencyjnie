import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        MonteCarlo monteCarlo = new MonteCarlo(123456789, 16);
        long startTime=System.nanoTime();
       double solidVolume= monteCarlo.compute();
       long endTime=System.nanoTime();
        System.out.println("Objetosc: "+solidVolume);
        System.out.println("czas wykoniania: "+(endTime-startTime)/1000000+"ms");
    }

}
