// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.force;

public class GravitationalForce extends AbstractForce
{
    private static final String[] pnames;
    public static final int GRAVITATIONAL_CONST = 0;
    public static final int DIRECTION = 1;
    public static final float DEFAULT_FORCE_CONSTANT = 1.0E-4f;
    public static final float DEFAULT_MIN_FORCE_CONSTANT = 1.0E-5f;
    public static final float DEFAULT_MAX_FORCE_CONSTANT = 0.001f;
    public static final float DEFAULT_DIRECTION = -1.5707964f;
    public static final float DEFAULT_MIN_DIRECTION = -3.1415927f;
    public static final float DEFAULT_MAX_DIRECTION = 3.1415927f;
    
    public GravitationalForce(final float n, final float n2) {
        this.params = new float[] { n, n2 };
        this.minValues = new float[] { 1.0E-5f, -3.1415927f };
        this.maxValues = new float[] { 0.001f, 3.1415927f };
    }
    
    public GravitationalForce() {
        this(1.0E-4f, -1.5707964f);
    }
    
    public boolean isItemForce() {
        return true;
    }
    
    protected String[] getParameterNames() {
        return GravitationalForce.pnames;
    }
    
    public void getForce(final ForceItem forceItem) {
        final float n = this.params[1];
        final float n2 = this.params[0] * forceItem.mass;
        final float[] force = forceItem.force;
        final int n3 = 0;
        force[n3] += (float)(Math.cos(n) * n2);
        final float[] force2 = forceItem.force;
        final int n4 = 1;
        force2[n4] += (float)(Math.sin(n) * n2);
    }
    
    static {
        pnames = new String[] { "GravitationalConstant", "Direction" };
    }
}
