// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

public abstract class AppletTest
{
    protected AppletPlugin m_plugin;
    protected AppletTab m_tab;
    
    public AppletTest(final AppletPlugin plugin, final AppletTab tab) {
        this.m_plugin = plugin;
        this.m_tab = tab;
    }
    
    public void doRepaint() {
        if (this.m_tab != null) {
            this.m_tab.repaint();
        }
    }
    
    public abstract String[] getAdvancedDataItem(final String p0);
    
    public abstract Object[] getSummaryItem(final String p0);
}
