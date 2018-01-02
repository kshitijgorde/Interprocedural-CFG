// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.JComboChart;

public class JComboChartApp extends JApplet
{
    public JComboChart combo;
    
    public JComboChartApp() {
    }
    
    public JComboChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final JComboChart comboChart = new JComboChart(super.app);
        this.combo = comboChart;
        super.graph = comboChart;
        super.init();
    }
}
