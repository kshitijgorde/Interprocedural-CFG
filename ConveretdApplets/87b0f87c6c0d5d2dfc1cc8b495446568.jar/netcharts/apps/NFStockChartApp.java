// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.NFStockChart;

public class NFStockChartApp extends NFApplet
{
    public NFStockChart stock;
    
    public NFStockChartApp() {
    }
    
    public NFStockChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final NFStockChart nfStockChart = new NFStockChart(super.app);
        this.stock = nfStockChart;
        super.graph = nfStockChart;
        super.init();
    }
}
