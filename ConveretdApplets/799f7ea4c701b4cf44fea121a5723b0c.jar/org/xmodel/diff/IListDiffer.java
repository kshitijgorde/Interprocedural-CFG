// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.diff;

import java.util.List;

public interface IListDiffer
{
    void diff(final List p0, final List p1);
    
    boolean isMatch(final Object p0, final Object p1);
    
    void notifyEqual(final List p0, final int p1, final int p2, final List p3, final int p4, final int p5);
    
    void notifyInsert(final List p0, final int p1, final int p2, final List p3, final int p4, final int p5);
    
    void notifyRemove(final List p0, final int p1, final int p2, final List p3, final int p4);
}
