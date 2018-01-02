// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.NFDialChart;

public class NFDialChartApp extends NFApplet
{
    public NFDialChart dial;
    
    public NFDialChartApp() {
    }
    
    public NFDialChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final NFDialChart nfDialChart = new NFDialChart(super.app);
        this.dial = nfDialChart;
        super.graph = nfDialChart;
        super.init();
    }
}
