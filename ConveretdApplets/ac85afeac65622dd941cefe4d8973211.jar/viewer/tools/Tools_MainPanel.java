// 
// Decompiled by Procyon v0.5.30
// 

package viewer.tools;

import java.awt.Component;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;
import viewer.RaisedPanel;

public class Tools_MainPanel extends RaisedPanel
{
    public Tools_ZoomPanel zoomPanel;
    public Tools_ImagePanel imagePanel;
    private Tools_LogoCanvas logo;
    
    public Tools_MainPanel(final Applet applet) {
        super(3, 3, 3, 3, 6);
        this.setLayout(new BorderLayout());
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout());
        this.zoomPanel = new Tools_ZoomPanel();
        this.imagePanel = new Tools_ImagePanel();
        this.logo = new Tools_LogoCanvas(applet);
        panel.add("North", this.zoomPanel);
        panel.add("South", this.imagePanel);
        this.add("North", panel);
        this.add("South", this.logo);
    }
}
