// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.ifeature;

import java.util.List;
import org.xmodel.IModelObject;
import org.xidget.IXidget;
import org.xmodel.xpath.expression.StatefulContext;

public interface ILayoutFeature
{
    void invalidate();
    
    void layout(final StatefulContext p0);
    
    void attachPrevious(final IXidget p0, final Side p1, final Side p2, final int p3);
    
    void attachNext(final IXidget p0, final Side p1, final Side p2, final int p3);
    
    void attachPeer(final IXidget p0, final Side p1, final IXidget p2, final int p3);
    
    void attachPeer(final IXidget p0, final Side p1, final IXidget p2, final Side p3, final int p4);
    
    void attachContainer(final IXidget p0, final Side p1, final int p2);
    
    void attachContainer(final IXidget p0, final Side p1, final float p2, final IModelObject p3, final int p4, final boolean p5);
    
    void packContainer(final List<IXidget> p0, final Side p1, final int p2);
    
    public enum Side
    {
        top("top", 0), 
        left("left", 1), 
        right("right", 2), 
        bottom("bottom", 3);
        
        private Side(final String s, final int n) {
        }
    }
}
