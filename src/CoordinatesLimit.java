/**
 * Klasa reprezentująca dolny i górny limit współrzędniej
 */
public class CoordinatesLimit {
    private double upperLimit, lowerLimit;

    public CoordinatesLimit( double lowerLimit,double upperLimit) {
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
    }

    public double getUpperLimit() {
        return upperLimit;
    }

    public double getLowerLimit() {
        return lowerLimit;
    }
}
