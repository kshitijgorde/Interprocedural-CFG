// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.force;

import java.awt.geom.Line2D;

public class WallForce extends AbstractForce
{
    private static String[] pnames;
    public static final float DEFAULT_GRAV_CONSTANT = -0.1f;
    public static final float DEFAULT_MIN_GRAV_CONSTANT = -1.0f;
    public static final float DEFAULT_MAX_GRAV_CONSTANT = 1.0f;
    public static final int GRAVITATIONAL_CONST = 0;
    private float x1;
    private float y1;
    private float x2;
    private float y2;
    private float dx;
    private float dy;
    
    public WallForce(final float n, final float x1, final float y1, final float x2, final float y2) {
        this.params = new float[] { n };
        this.minValues = new float[] { -1.0f };
        this.maxValues = new float[] { 1.0f };
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.dx = x2 - x1;
        this.dy = y2 - y1;
        final float n2 = (float)Math.sqrt(this.dx * this.dx + this.dy * this.dy);
        if (this.dx != 0.0) {
            this.dx /= n2;
        }
        if (this.dy != 0.0) {
            this.dy /= n2;
        }
    }
    
    public WallForce(final float n, final float n2, final float n3, final float n4) {
        this(-0.1f, n, n2, n3, n4);
    }
    
    public boolean isItemForce() {
        return true;
    }
    
    protected String[] getParameterNames() {
        return WallForce.pnames;
    }
    
    public void getForce(final ForceItem forceItem) {
        final float[] location = forceItem.location;
        final int relativeCCW = Line2D.relativeCCW(this.x1, this.y1, this.x2, this.y2, location[0], location[1]);
        float n = (float)Line2D.ptSegDist(this.x1, this.y1, this.x2, this.y2, location[0], location[1]);
        if (n == 0.0) {
            n = (float)Math.random() / 100.0f;
        }
        final float n2 = this.params[0] * forceItem.mass / (n * n * n);
        if (location[0] >= Math.min(this.x1, this.x2) && location[0] <= Math.max(this.x1, this.x2)) {
            final float[] force = forceItem.force;
            final int n3 = 1;
            force[n3] += relativeCCW * n2 * this.dx;
        }
        if (location[1] >= Math.min(this.y1, this.y2) && location[1] <= Math.max(this.y1, this.y2)) {
            final float[] force2 = forceItem.force;
            final int n4 = 0;
            force2[n4] += -1 * relativeCCW * n2 * this.dy;
        }
    }
    
    static {
        WallForce.pnames = new String[] { "GravitationalConstant" };
    }
}
