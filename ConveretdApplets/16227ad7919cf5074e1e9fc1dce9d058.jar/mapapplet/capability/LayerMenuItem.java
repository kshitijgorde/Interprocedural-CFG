// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet.capability;

import java.awt.CheckboxMenuItem;

final class LayerMenuItem extends CheckboxMenuItem
{
    public String layerName;
    public String layerQuery;
    public String layerStyle;
    protected LayersMenu parent;
    
    public LayerMenuItem() {
    }
    
    public LayerMenuItem(final String layerDisplay, final LayersMenu parent) {
        this(layerDisplay, layerDisplay, null, parent);
    }
    
    public LayerMenuItem(final String layerDisplay, final String layerQuery, final LayersMenu parent) {
        this(layerDisplay, layerQuery, null, parent);
    }
    
    public LayerMenuItem(final String layerDisplay, final String layerQuery, final String layerStyle, final LayersMenu parent) {
        this.setLabel(this.layerName = layerDisplay);
        this.layerQuery = layerQuery;
        this.layerStyle = layerStyle;
        this.parent = parent;
    }
    
    public boolean equals(final Object obj) {
        if (obj.getClass().isInstance(new LayerMenuItem())) {
            final LayerMenuItem item = (LayerMenuItem)obj;
            if (item.getLabel() != null && this.getLabel() != null && item.layerName.equals(this.layerName) && item.getLabel().equals(this.getLabel())) {
                return true;
            }
        }
        return false;
    }
}
