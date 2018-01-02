// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.force;

public class ForceItem implements Cloneable
{
    public float mass;
    public float[] force;
    public float[] velocity;
    public float[] location;
    public float[] plocation;
    public float[][] k;
    public float[][] l;
    
    public ForceItem() {
        this.mass = 1.0f;
        this.force = new float[] { 0.0f, 0.0f };
        this.velocity = new float[] { 0.0f, 0.0f };
        this.location = new float[] { 0.0f, 0.0f };
        this.plocation = new float[] { 0.0f, 0.0f };
        this.k = new float[4][2];
        this.l = new float[4][2];
    }
    
    public Object clone() {
        final ForceItem forceItem = new ForceItem();
        forceItem.mass = this.mass;
        System.arraycopy(this.force, 0, forceItem.force, 0, 2);
        System.arraycopy(this.velocity, 0, forceItem.velocity, 0, 2);
        System.arraycopy(this.location, 0, forceItem.location, 0, 2);
        System.arraycopy(this.plocation, 0, forceItem.plocation, 0, 2);
        for (int i = 0; i < this.k.length; ++i) {
            System.arraycopy(this.k[i], 0, forceItem.k[i], 0, 2);
            System.arraycopy(this.l[i], 0, forceItem.l[i], 0, 2);
        }
        return forceItem;
    }
    
    public static final boolean isValid(final ForceItem forceItem) {
        return !Float.isNaN(forceItem.location[0]) && !Float.isNaN(forceItem.location[1]) && !Float.isNaN(forceItem.plocation[0]) && !Float.isNaN(forceItem.plocation[1]) && !Float.isNaN(forceItem.velocity[0]) && !Float.isNaN(forceItem.velocity[1]) && !Float.isNaN(forceItem.force[0]) && !Float.isNaN(forceItem.force[1]);
    }
}
