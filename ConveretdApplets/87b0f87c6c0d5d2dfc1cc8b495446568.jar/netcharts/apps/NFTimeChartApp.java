// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.NFTimeChart;

public class NFTimeChartApp extends NFApplet
{
    public NFTimeChart timechart;
    
    public NFTimeChartApp() {
    }
    
    public NFTimeChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final NFTimeChart nfTimeChart = new NFTimeChart(super.app);
        this.timechart = nfTimeChart;
        super.graph = nfTimeChart;
        super.init();
    }
}
