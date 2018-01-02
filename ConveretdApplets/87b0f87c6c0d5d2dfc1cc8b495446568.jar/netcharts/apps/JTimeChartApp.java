// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.JTimeChart;

public class JTimeChartApp extends JApplet
{
    public JTimeChart timechart;
    
    public JTimeChartApp() {
    }
    
    public JTimeChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final JTimeChart timeChart = new JTimeChart(super.app);
        this.timechart = timeChart;
        super.graph = timeChart;
        super.init();
    }
}
