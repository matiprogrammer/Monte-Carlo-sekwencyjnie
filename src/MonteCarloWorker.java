import java.util.Random;

public class MonteCarloWorker implements Runnable {
    CalculationResult result;
    CoordinatesLimit x1Limit, x2Limit, x3Limit;
    private int totalShots;
    Random random;

    public MonteCarloWorker(CalculationResult result, CoordinatesLimit x1Limit, CoordinatesLimit x2Limit, CoordinatesLimit x3Limit, int totalShots) {
        this.result = result;
        this.x1Limit = x1Limit;
        this.x2Limit = x2Limit;
        this.x3Limit = x3Limit;
        this.totalShots = totalShots;
        random = new Random();
    }

    /**
     * Metoda losuje punkt z kostki otaczającej X i sprawdza czy wylosowany punkt należy do X. Zlicza liczbę trafionych punktów i dodaje do wyniku.
     */
    @Override
    public void run() {
        int hits = 0;
        Point point;

        for (int i = 0; i < totalShots; i++) {
            double x1 = x1Limit.getUpperLimit() * random.nextDouble();
            double x2 = x2Limit.getUpperLimit() * random.nextDouble();
            double x3 = x3Limit.getUpperLimit() * random.nextDouble();
            point = new Point(x1, x2, x3);
            if (isHit(point))
                hits++;
        }
        result.addHits(hits);
    }

    /**
     * Metoda sprawdza czy punkt należy do X
     */
    private boolean isHit(Point point) {
        if (firstCondition(point))
            if (secondCondition(point))
                if (thirdCondition(point))
                    return true;
        return false;
    }

    /**
     * Warunek x1≥0,x2≥0,x3≥0,
     */
    private boolean firstCondition(Point point) {
        return (point.getX1() >= 0 && point.getX2() >= 0 && point.getX3() >= 0);
    }

    /**
     * Warunek x1^2+x2^2+x3^2≤12,
     */
    private boolean secondCondition(Point point) {
        return (Math.pow(point.getX1(), 2) + Math.pow(point.getX2(), 2) + Math.pow(point.getX3(), 2) <= 12);
    }

    /**
     * Warnunek x1^2+x3^2≤7.
     */
    private boolean thirdCondition(Point point) {
        return (Math.pow(point.getX1(), 2) + Math.pow(point.getX3(), 2) <= 7);
    }
}
