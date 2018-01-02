// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.JStripChart;

public class JStripChartApp extends JApplet
{
    public JStripChart stripchart;
    
    public JStripChartApp() {
    }
    
    public JStripChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final JStripChart stripChart = new JStripChart(super.app);
        this.stripchart = stripChart;
        super.graph = stripChart;
        super.init();
    }
}
