// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.force;

public class CircularWallForce extends AbstractForce
{
    private static String[] pnames;
    public static final float DEFAULT_GRAV_CONSTANT = -0.1f;
    public static final float DEFAULT_MIN_GRAV_CONSTANT = -1.0f;
    public static final float DEFAULT_MAX_GRAV_CONSTANT = 1.0f;
    public static final int GRAVITATIONAL_CONST = 0;
    private float x;
    private float y;
    private float r;
    
    public CircularWallForce(final float n, final float x, final float y, final float r) {
        this.params = new float[] { n };
        this.minValues = new float[] { -1.0f };
        this.maxValues = new float[] { 1.0f };
        this.x = x;
        this.y = y;
        this.r = r;
    }
    
    public CircularWallForce(final float n, final float n2, final float n3) {
        this(-0.1f, n, n2, n3);
    }
    
    public boolean isItemForce() {
        return true;
    }
    
    protected String[] getParameterNames() {
        return CircularWallForce.pnames;
    }
    
    public void getForce(final ForceItem forceItem) {
        final float[] location = forceItem.location;
        float n = this.x - location[0];
        float n2 = this.y - location[1];
        float n3 = (float)Math.sqrt(n * n + n2 * n2);
        final float n4 = this.r - n3;
        final float n5 = ((n4 > 0.0f) ? -1.0f : 1.0f) * this.params[0] * forceItem.mass / (n4 * n4);
        if (n3 == 0.0) {
            n = ((float)Math.random() - 0.5f) / 50.0f;
            n2 = ((float)Math.random() - 0.5f) / 50.0f;
            n3 = (float)Math.sqrt(n * n + n2 * n2);
        }
        final float[] force = forceItem.force;
        final int n6 = 0;
        force[n6] += n5 * n / n3;
        final float[] force2 = forceItem.force;
        final int n7 = 1;
        force2[n7] += n5 * n2 / n3;
    }
    
    static {
        CircularWallForce.pnames = new String[] { "GravitationalConstant" };
    }
}
