// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.assignment;

import prefuse.visual.VisualItem;
import prefuse.util.DataLib;
import java.util.Map;

public class DataShapeAction extends ShapeAction
{
    protected static final int NO_SHAPE = Integer.MIN_VALUE;
    protected String m_dataField;
    protected int[] m_palette;
    protected Map m_ordinalMap;
    
    public DataShapeAction(final String s, final String dataField) {
        super(s, Integer.MIN_VALUE);
        this.m_dataField = dataField;
    }
    
    public DataShapeAction(final String s, final String dataField, final int[] palette) {
        super(s, Integer.MIN_VALUE);
        this.m_dataField = dataField;
        this.m_palette = palette;
    }
    
    public String getDataField() {
        return this.m_dataField;
    }
    
    public void setDataField(final String dataField) {
        this.m_dataField = dataField;
    }
    
    public void setDefaultShape(final int n) {
        throw new UnsupportedOperationException();
    }
    
    protected void setup() {
        this.m_ordinalMap = DataLib.ordinalMap(this.m_vis.getGroup(this.m_group), this.m_dataField);
    }
    
    public int getShape(final VisualItem visualItem) {
        final int shape = super.getShape(visualItem);
        if (shape != Integer.MIN_VALUE) {
            return shape;
        }
        final int intValue = this.m_ordinalMap.get(visualItem.get(this.m_dataField));
        if (this.m_palette == null) {
            return intValue % 10;
        }
        return this.m_palette[intValue % this.m_palette.length];
    }
}
