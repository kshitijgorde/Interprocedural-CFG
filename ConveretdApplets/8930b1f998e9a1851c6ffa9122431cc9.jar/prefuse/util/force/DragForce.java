// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.force;

public class DragForce extends AbstractForce
{
    private static String[] pnames;
    public static final float DEFAULT_DRAG_COEFF = 0.01f;
    public static final float DEFAULT_MIN_DRAG_COEFF = 0.0f;
    public static final float DEFAULT_MAX_DRAG_COEFF = 0.1f;
    public static final int DRAG_COEFF = 0;
    
    public DragForce(final float n) {
        this.params = new float[] { n };
        this.minValues = new float[] { 0.0f };
        this.maxValues = new float[] { 0.1f };
    }
    
    public DragForce() {
        this(0.01f);
    }
    
    public boolean isItemForce() {
        return true;
    }
    
    protected String[] getParameterNames() {
        return DragForce.pnames;
    }
    
    public void getForce(final ForceItem forceItem) {
        final float[] force = forceItem.force;
        final int n = 0;
        force[n] -= this.params[0] * forceItem.velocity[0];
        final float[] force2 = forceItem.force;
        final int n2 = 1;
        force2[n2] -= this.params[0] * forceItem.velocity[1];
    }
    
    static {
        DragForce.pnames = new String[] { "DragCoefficient" };
    }
}
