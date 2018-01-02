// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet.capability;

public final class LayersMenu extends MyMenu
{
    private float layerTransparency;
    private StringBuffer defaultQuery;
    
    public LayersMenu() {
        this.layerTransparency = 1.0f;
    }
    
    public LayersMenu(final String name, final int layerTransparency) {
        super(name);
        this.layerTransparency = 1.0f;
        this.layerTransparency = layerTransparency;
        this.defaultQuery = new StringBuffer();
    }
    
    public float getLayerTransparency() {
        return this.layerTransparency;
    }
    
    public void setLayerTransparency(final float layerTransparency) {
        this.layerTransparency = layerTransparency;
    }
    
    public boolean haveSelectedElements() {
        for (int i = 0; i < super.subMenus.size(); ++i) {
            final LayerMenuItem item = super.subMenus.elementAt(i);
            if (item.getState()) {
                return true;
            }
        }
        return false;
    }
    
    public StringBuffer getDefaultQuery() {
        return this.defaultQuery;
    }
    
    public void setDefaultQuery(final StringBuffer defaultQuery) {
        this.defaultQuery = defaultQuery;
    }
}
