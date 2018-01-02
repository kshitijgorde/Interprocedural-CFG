// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.JDialChart;

public class JDialChartApp extends JApplet
{
    public JDialChart dial;
    
    public JDialChartApp() {
    }
    
    public JDialChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final JDialChart dialChart = new JDialChart(super.app);
        this.dial = dialChart;
        super.graph = dialChart;
        super.init();
    }
}
