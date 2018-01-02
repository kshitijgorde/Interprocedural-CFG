// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public interface Picker
{
    public static final int POINT = 0;
    public static final int NORMAL = 1;
    public static final int MATRIX = 2;
    
    Node[] pickClosestFromTo(final float[] p0, final float[] p1);
    
    Node[] pickClosest(final int p0, final int p1);
    
    void setScene(final Node p0);
    
    boolean wasPicked(final Node p0);
    
    void setPath(final Node[] p0);
    
    boolean pickAny(final int p0, final int p1);
    
    boolean pickAnyFromTo(final float[] p0, final float[] p1);
    
    void setPickInfo(final int p0, final boolean p1);
    
    float[] getPickInfo(final int p0) throws Shout3DException;
}
