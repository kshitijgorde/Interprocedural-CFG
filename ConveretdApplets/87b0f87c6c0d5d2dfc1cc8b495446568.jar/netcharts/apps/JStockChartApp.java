// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.JStockChart;

public class JStockChartApp extends JApplet
{
    public JStockChart stock;
    
    public JStockChartApp() {
    }
    
    public JStockChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final JStockChart stockChart = new JStockChart(super.app);
        this.stock = stockChart;
        super.graph = stockChart;
        super.init();
    }
}
