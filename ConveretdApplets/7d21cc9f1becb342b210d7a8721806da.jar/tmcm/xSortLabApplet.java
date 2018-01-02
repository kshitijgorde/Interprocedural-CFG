// 
// Decompiled by Procyon v0.5.30
// 

package tmcm;

import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import tmcm.xSortLab.xSortLabPanel;
import java.applet.Applet;

public class xSortLabApplet extends Applet
{
    xSortLabPanel sortLab;
    
    public String getAppletInfo() {
        return "xSortLabApplet, by David J. Eck (eck@hws.edu), August 1997";
    }
    
    public void init() {
        this.setBackground(Color.lightGray);
        this.setLayout(new BorderLayout());
        this.add("Center", this.sortLab = new xSortLabPanel());
    }
    
    public Insets insets() {
        return new Insets(5, 5, 5, 5);
    }
    
    public void start() {
        this.sortLab.start();
    }
    
    public void stop() {
        this.sortLab.stop();
    }
    
    public void destroy() {
        this.sortLab.destroy();
    }
}
