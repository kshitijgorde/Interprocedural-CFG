// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout;

import java.util.Iterator;
import java.awt.geom.Rectangle2D;
import prefuse.data.Tuple;
import prefuse.visual.VisualItem;
import prefuse.data.Table;

public class CollapsedStackLayout extends Layout
{
    private String m_polyField;
    private int m_orientation;
    private boolean m_horiz;
    private boolean m_top;
    
    public CollapsedStackLayout(final String s) {
        this(s, "_polygon");
    }
    
    public CollapsedStackLayout(final String s, final String polyField) {
        super(s);
        this.m_orientation = 3;
        this.m_horiz = false;
        this.m_top = false;
        this.m_polyField = polyField;
    }
    
    public int getOrientation() {
        return this.m_orientation;
    }
    
    public void setOrientation(final int orientation) {
        if (orientation != 2 && orientation != 3 && orientation != 0 && orientation != 1) {
            throw new IllegalArgumentException("Invalid orientation value: " + orientation);
        }
        this.m_orientation = orientation;
        this.m_horiz = (this.m_orientation == 0 || this.m_orientation == 1);
        this.m_top = (this.m_orientation == 2 || this.m_orientation == 0);
    }
    
    public void run(final double n) {
        Tuple tuple = null;
        final Rectangle2D layoutBounds = this.getLayoutBounds();
        final float n2 = (float)(this.m_horiz ? (this.m_top ? layoutBounds.getMaxX() : layoutBounds.getMinX()) : (this.m_top ? layoutBounds.getMinY() : layoutBounds.getMaxY()));
        final int n3 = this.m_horiz ? 0 : 1;
        final Iterator tuplesReversed = ((Table)this.m_vis.getGroup(this.m_group)).tuplesReversed();
        while (tuplesReversed.hasNext()) {
            final VisualItem visualItem = tuplesReversed.next();
            final boolean startVisible = visualItem.isStartVisible();
            final boolean visible = visualItem.isVisible();
            if (!startVisible && visible) {
                final float[] array = (float[])visualItem.get(this.m_polyField);
                if (array == null) {
                    continue;
                }
                if (tuple == null) {
                    for (int i = 0; i < array.length; i += 2) {
                        array[i + n3] = n2;
                    }
                }
                else {
                    final float[] array2 = (float[])tuple.get(this.m_polyField);
                    for (int j = 0; j < array.length / 2; j += 2) {
                        array[j + n3] = (array[array.length - 2 - j + n3] = array2[j + n3]);
                    }
                }
            }
            else {
                if (!startVisible || !visible) {
                    continue;
                }
                tuple = visualItem;
            }
        }
    }
}
