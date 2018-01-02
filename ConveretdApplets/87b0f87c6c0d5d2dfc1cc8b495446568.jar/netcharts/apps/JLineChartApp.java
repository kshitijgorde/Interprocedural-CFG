// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.JLineChart;

public class JLineChartApp extends JApplet
{
    public JLineChart linechart;
    
    public JLineChartApp() {
    }
    
    public JLineChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final JLineChart lineChart = new JLineChart(super.app);
        this.linechart = lineChart;
        super.graph = lineChart;
        super.init();
    }
}
