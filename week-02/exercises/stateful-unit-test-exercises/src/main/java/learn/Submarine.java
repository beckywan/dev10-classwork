package learn;

/**
 * An underwater, submersible vehicle.
 * Includes two behaviors.
 * dive: go down a little deeper under water to a maximum depth
 * surface: come up a little shallower to a minimum depth of sea level
 * <p>
 * The submarine's current depth and pressure are available via getters.
 */
public class Submarine {

    private final double maxDepth;
    private double depthInMeters;

    public Submarine(double maxDepth) {
        this.maxDepth = maxDepth;
    }

    public double getDepthInMeters() {
        return depthInMeters;
    }

    public double dive() {
        // 1. Each dive should increase the depth by 3 meters.
        // Depth cannot exceed maxDepth.
        double input = getDepthInMeters() + 3;
        depthInMeters = Math.min(input, maxDepth);
        return depthInMeters;
    }

    public double surface() {
        // 2. Each surface should decrease the depth by 5 meters.
        // Minimum depth is 0.0 (sea level).
        double input = getDepthInMeters() - 5;
        depthInMeters = Math.max(input, 0.0);
        return depthInMeters;
    }

    public double getPressureInAtmospheres() {
        // 3. At sea level, pressure is 1 atmosphere.
        // Pressure increases by 1 atmosphere for every 10 meters.
        double result = 1 + getDepthInMeters()/10;
        return result;
    }

}
