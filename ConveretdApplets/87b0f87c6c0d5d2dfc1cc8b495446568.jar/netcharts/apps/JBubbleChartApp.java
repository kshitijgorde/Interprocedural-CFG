// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.JBubbleChart;

public class JBubbleChartApp extends JApplet
{
    public JBubbleChart bubblechart;
    
    public JBubbleChartApp() {
    }
    
    public JBubbleChartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final JBubbleChart bubbleChart = new JBubbleChart(super.app);
        this.bubblechart = bubbleChart;
        super.graph = bubbleChart;
        super.init();
    }
}
