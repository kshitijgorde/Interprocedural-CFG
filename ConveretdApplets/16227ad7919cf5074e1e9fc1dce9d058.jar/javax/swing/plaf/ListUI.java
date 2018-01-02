// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.plaf;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JList;

public abstract class ListUI extends ComponentUI
{
    public abstract Rectangle getCellBounds(final JList p0, final int p1, final int p2);
    
    public abstract Point indexToLocation(final JList p0, final int p1);
    
    public abstract int locationToIndex(final JList p0, final Point p1);
}
