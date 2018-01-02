// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

public interface GraphViewManager
{
    ItemPainter getPainter(final GraphLocation p0, final Object p1, final int p2);
    
    ItemEditor getEditor(final GraphLocation p0, final Object p1, final int p2);
}
