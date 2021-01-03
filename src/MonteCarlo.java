import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MonteCarlo {
    private int totalShots;
    CalculationResult result;
    CoordinatesLimit x1Limit, x2Limit, x3Limit;
    ExecutorService executor;
    int threadsPool;

    public MonteCarlo(int totalShots, int threadsPool) {
        this.totalShots = totalShots;
        x1Limit = new CoordinatesLimit(0, Math.sqrt(7));
        x2Limit = new CoordinatesLimit(0, Math.sqrt(12));
        x3Limit = new CoordinatesLimit(0, Math.sqrt(7));
        executor = Executors.newFixedThreadPool(threadsPool);
        this.threadsPool = threadsPool;
        double cubeVolume = x1Limit.getUpperLimit() * x2Limit.getUpperLimit() * x3Limit.getUpperLimit();
        result = new CalculationResult(totalShots, cubeVolume);
    }


    public double compute() {
        for (int i = 0; i < threadsPool-1; i++) {
            executor.submit(new MonteCarloWorker(result, x1Limit, x2Limit, x3Limit, totalShots / threadsPool));
        }
            executor.submit(new MonteCarloWorker(result, x1Limit, x2Limit, x3Limit, (totalShots / threadsPool)+(totalShots % threadsPool)));
        executor.shutdown();
        while (!executor.isTerminated()) {}
        return result.getVolume();
    }
}
