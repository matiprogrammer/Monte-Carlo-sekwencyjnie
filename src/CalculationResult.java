/**
 * Klasa licząca objętośc bryły X na podstawie ilości trafionych punktów
 */
public class CalculationResult {
    private volatile int hits = 0; //ilość punktów należących do X
    private int totalShots; //ilość losowań
    private double cubeVolume;

    public CalculationResult(int totalShots, double cubeVolume) {
        this.totalShots = totalShots;
        this.cubeVolume = cubeVolume;
    }

    /**
     * Metoda dodaje ilość trafionych punktów do puli
     *
     */
    public synchronized void addHits(int hits) {
        this.hits += hits;
    }

    /**
     * Metoda zwraca objętość bryły X
     */
    public double getVolume() {
        return ((double) hits / (double) totalShots) * cubeVolume;
    }
}
