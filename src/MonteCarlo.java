import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Klasa odpowiada za obliczanie objętości bryły metodą Monte Carlo
 * x1≥0,x2≥0,x3≥0,
 * x1^2+x2^2+x3^2≤12,
 * x1^2+x3^2≤7.
 */
public class MonteCarlo {
    private int totalShots;
    CalculationResult result;
    CoordinatesLimit x1Limit, x2Limit, x3Limit;
    ExecutorService executor;
    int threadsPool;

    public MonteCarlo(int totalShots, int threadsPool) {
        this.totalShots = totalShots;
        //limity 3 wymiarów
        x1Limit = new CoordinatesLimit(0, Math.sqrt(7));
        x2Limit = new CoordinatesLimit(0, Math.sqrt(12));
        x3Limit = new CoordinatesLimit(0, Math.sqrt(7));
        executor = Executors.newFixedThreadPool(threadsPool); // grupa wątków, na których będa wykonywane obliczenia
        this.threadsPool = threadsPool;
        double cubeVolume = x1Limit.getUpperLimit() * x2Limit.getUpperLimit() * x3Limit.getUpperLimit(); //objętość kostki otaczającej zbiór X
        result = new CalculationResult(totalShots, cubeVolume);
    }

/**
 * Metoda obliczająca objętośc bryły X
 */
    public double compute() {
        for (int i = 0; i < threadsPool-1; i++) {
            executor.submit(new MonteCarloWorker(result, x1Limit, x2Limit, x3Limit, totalShots / threadsPool)); //Dodawanie watków do wykonania
        }

            executor.submit(new MonteCarloWorker(result, x1Limit, x2Limit, x3Limit, (totalShots / threadsPool)+(totalShots % threadsPool)));  //Gdy liczby losowań nie da się podzielić po równo na każdy wątek, ostatni wątek zawiera odpowiednio mniej lub więcej punktów do wylosowania
        executor.shutdown();
        while (!executor.isTerminated()) {} //Oczekiwanie aż wykonaja się obliczenia
        return result.getVolume();
    }
}
