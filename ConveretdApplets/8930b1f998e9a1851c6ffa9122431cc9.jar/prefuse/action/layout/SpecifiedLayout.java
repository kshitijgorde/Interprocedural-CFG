// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout;

import java.util.Iterator;
import prefuse.visual.VisualItem;

public class SpecifiedLayout extends Layout
{
    private String m_xfield;
    private String m_yfield;
    private String m_fixedfield;
    
    public SpecifiedLayout(final String s, final String xfield, final String yfield) {
        super(s);
        this.m_xfield = null;
        this.m_yfield = null;
        this.m_fixedfield = null;
        this.m_xfield = xfield;
        this.m_yfield = yfield;
    }
    
    public String getXField() {
        return this.m_xfield;
    }
    
    public void setXField(final String xfield) {
        this.m_xfield = xfield;
    }
    
    public String getYField() {
        return this.m_yfield;
    }
    
    public void setYField(final String yfield) {
        this.m_yfield = yfield;
    }
    
    public String getFixedField() {
        return this.m_fixedfield;
    }
    
    public void setFixedField(final String fixedfield) {
        this.m_fixedfield = fixedfield;
    }
    
    public void run(final double n) {
        final Iterator items = this.m_vis.items(this.m_group);
        while (items.hasNext()) {
            final VisualItem visualItem = items.next();
            try {
                if (this.m_xfield != null) {
                    this.setX(visualItem, null, visualItem.getDouble(this.m_xfield));
                }
                if (this.m_yfield != null) {
                    this.setY(visualItem, null, visualItem.getDouble(this.m_yfield));
                }
                if (this.m_fixedfield == null) {
                    continue;
                }
                visualItem.setFixed(visualItem.getBoolean(this.m_fixedfield));
            }
            catch (Exception ex) {}
        }
    }
}
