// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.NFBubbleChart;

public class NFBubbleChartApp extends NFApplet
{
    public NFBubbleChart bubblechart;
    
    public NFBubbleChartApp() {
    }
    
    public NFBubbleChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final NFBubbleChart nfBubbleChart = new NFBubbleChart(super.app);
        this.bubblechart = nfBubbleChart;
        super.graph = nfBubbleChart;
        super.init();
    }
}
