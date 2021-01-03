import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        MonteCarlo monteCarlo = new MonteCarlo(123456789, 2);
        System.out.println(monteCarlo.compute());
    }

}
