import javax.swing.Icon;

// 
// Decompiled by Procyon v0.5.30
// 

class IconData
{
    protected Icon m_icon;
    protected Icon m_expandedIcon;
    protected Object m_data;
    
    public IconData(final Icon icon, final Object data) {
        this.m_icon = icon;
        this.m_expandedIcon = null;
        this.m_data = data;
    }
    
    public IconData(final Icon icon, final Icon expandedIcon, final Object data) {
        this.m_icon = icon;
        this.m_expandedIcon = expandedIcon;
        this.m_data = data;
    }
    
    public Icon getIcon() {
        return this.m_icon;
    }
    
    public Icon getExpandedIcon() {
        return (this.m_expandedIcon != null) ? this.m_expandedIcon : this.m_icon;
    }
    
    public Object getObject() {
        return this.m_data;
    }
    
    public String toString() {
        return this.m_data.toString();
    }
}
