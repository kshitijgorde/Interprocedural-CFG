// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.NFRadarChart;

public class NFRadarChartApp extends NFApplet
{
    public NFRadarChart radar;
    
    public NFRadarChartApp() {
    }
    
    public NFRadarChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final NFRadarChart nfRadarChart = new NFRadarChart(super.app);
        this.radar = nfRadarChart;
        super.graph = nfRadarChart;
        super.init();
    }
}
