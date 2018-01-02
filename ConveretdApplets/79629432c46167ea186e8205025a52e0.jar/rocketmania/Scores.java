// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

public class Scores
{
    static final int skTouchHack = 1;
    static final double[] SCALES;
    static final int[] GEMS;
    static final int ROCKET_SCALE = 100;
    static final int TUBE = 5;
    static final int COMBO = 2000;
    static final int[] MULTILAUNCH;
    
    public static int TouchHack() {
        return 1;
    }
    
    static {
        SCALES = new double[] { 1.0, 1.5, 2.0 };
        GEMS = new int[] { 500, 600, 700, 800, 1000 };
        MULTILAUNCH = new int[] { 0, 0, 0, 0, 0, 1000, 2500, 10000, 25000, 100000 };
    }
}
