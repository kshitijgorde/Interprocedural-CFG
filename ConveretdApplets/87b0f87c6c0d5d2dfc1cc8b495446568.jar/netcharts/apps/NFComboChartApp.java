// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.NFComboChart;

public class NFComboChartApp extends NFApplet
{
    public NFComboChart combo;
    
    public NFComboChartApp() {
    }
    
    public NFComboChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final NFComboChart nfComboChart = new NFComboChart(super.app);
        this.combo = nfComboChart;
        super.graph = nfComboChart;
        super.init();
    }
}
