import java.util.Random;

public class MonteCarlo {
    private int totalShots;

    public MonteCarlo(int totalShots) {
        this.totalShots = totalShots;
    }

    public double compute()
    {
        CoordinatesLimit x1Limit, x2Limit, x3Limit;
        x1Limit = new CoordinatesLimit(0, Math.sqrt(7));
        x2Limit = new CoordinatesLimit(0, Math.sqrt(12));
        x3Limit = new CoordinatesLimit(0, Math.sqrt(7));
        double cubeVolume=x1Limit.getUpperLimit()* x2Limit.getUpperLimit()* x3Limit.getUpperLimit();
        int hits = 0;
        Random random = new Random();
        for (int i=0; i < totalShots; i++) {
            if (isHit(new Point(x1Limit.getUpperLimit() * random.nextDouble(), x2Limit.getUpperLimit() * random.nextDouble(), x3Limit.getUpperLimit() * random.nextDouble())))
                hits++;
        }
        return (((double)hits/(double)totalShots))*cubeVolume;
    }

    private boolean isHit(Point point) {
        if (firstCondition(point))
            if (secondCondition(point))
                if (thirdCondition(point))
                    return true;
        return false;
    }

    private boolean firstCondition(Point point) {
        return (point.getX1() >= 0 && point.getX2() >= 0 && point.getX3() >= 0);
    }

    private boolean secondCondition(Point point) {
        return (Math.pow(point.getX1(), 2) + Math.pow(point.getX2(), 2) + Math.pow(point.getX3(), 2) <= 12);
    }

    private boolean thirdCondition(Point point) {
        return (Math.pow(point.getX1(), 2) + Math.pow(point.getX3(), 2) <= 7);
    }
}
