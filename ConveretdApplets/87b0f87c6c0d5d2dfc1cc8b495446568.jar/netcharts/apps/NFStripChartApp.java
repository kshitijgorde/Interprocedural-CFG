// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.NFStripChart;

public class NFStripChartApp extends NFApplet
{
    public NFStripChart stripchart;
    
    public NFStripChartApp() {
    }
    
    public NFStripChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final NFStripChart nfStripChart = new NFStripChart(super.app);
        this.stripchart = nfStripChart;
        super.graph = nfStripChart;
        super.init();
    }
}
