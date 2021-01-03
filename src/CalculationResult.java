public class CalculationResult {
    private volatile int hits=0, totalShots;
    private double cubeVolume;

    public CalculationResult(int totalShots, double cubeVolume) {
        this.totalShots = totalShots;
        this.cubeVolume = cubeVolume;
    }

    public synchronized void addHits(int hits) {
        this.hits += hits;
    }

    public synchronized double getVolume() {
        return ((double) hits / (double) totalShots) * cubeVolume;
    }
}
