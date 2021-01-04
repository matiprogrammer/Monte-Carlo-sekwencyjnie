import java.util.Random;

/**
 * Klasa odpowiada za obliczanie objętości bryły metodą Monte Carlo
 * x1≥0,x2≥0,x3≥0,
 * x1^2+x2^2+x3^2≤12,
 * x1^2+x3^2≤7.
 */
public class MonteCarlo {
    private int totalShots;

    public MonteCarlo(int totalShots) {
        this.totalShots = totalShots;
    }

    /**
     * Metoda obliczająca objętośc bryły X sekwencyjnie.
     * Metoda losuje punkt z kostki otaczającej X i sprawdza czy wylosowany punkt należy do X. Zlicza liczbę trafionych punktów i dodaje do wyniku.
     */
    public double compute() {
        CoordinatesLimit x1Limit, x2Limit, x3Limit;
        //limity 3 wymiarów
        x1Limit = new CoordinatesLimit(0, Math.sqrt(7));
        x2Limit = new CoordinatesLimit(0, Math.sqrt(12));
        x3Limit = new CoordinatesLimit(0, Math.sqrt(7));
        double cubeVolume = x1Limit.getUpperLimit() * x2Limit.getUpperLimit() * x3Limit.getUpperLimit(); //objętość kostki otaczającej zbiór X
        int hits = 0;
        Random random = new Random();
        Point point;
        for (int i = 0; i < totalShots; i++) {
            double x1 = x1Limit.getUpperLimit() * random.nextDouble();
            double x2 = x2Limit.getUpperLimit() * random.nextDouble();
            double x3 = x3Limit.getUpperLimit() * random.nextDouble();
            point = new Point(x1, x2, x3);
            if (isHit(point))
                hits++;
        }
        return (((double) hits / (double) totalShots)) * cubeVolume;

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
