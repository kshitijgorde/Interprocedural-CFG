// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.JRadarChart;

public class JRadarChartApp extends JApplet
{
    public JRadarChart radar;
    
    public JRadarChartApp() {
    }
    
    public JRadarChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final JRadarChart radarChart = new JRadarChart(super.app);
        this.radar = radarChart;
        super.graph = radarChart;
        super.init();
    }
}
