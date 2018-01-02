// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.JXYChart;

public class JXYChartApp extends JApplet
{
    public JXYChart xychart;
    
    public JXYChartApp() {
    }
    
    public JXYChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final JXYChart jxyChart = new JXYChart(super.app);
        this.xychart = jxyChart;
        super.graph = jxyChart;
        super.init();
    }
}
