// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.NFLineChart;

public class NFLineChartApp extends NFApplet
{
    public NFLineChart linechart;
    
    public NFLineChartApp() {
    }
    
    public NFLineChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final NFLineChart nfLineChart = new NFLineChart(super.app);
        this.linechart = nfLineChart;
        super.graph = nfLineChart;
        super.init();
    }
}
