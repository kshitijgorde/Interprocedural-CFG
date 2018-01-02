// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

public interface NFDragObserver
{
    boolean preDrag(final Object p0, final double p1, final double p2, final double p3, final double p4);
    
    void postDrag(final Object p0, final double p1, final double p2);
}
