// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.NFXYChart;

public class NFXYChartApp extends NFApplet
{
    public NFXYChart xychart;
    
    public NFXYChartApp() {
    }
    
    public NFXYChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final NFXYChart nfxyChart = new NFXYChart(super.app);
        this.xychart = nfxyChart;
        super.graph = nfxyChart;
        super.init();
    }
}
