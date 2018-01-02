// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.JBoxchart;

public class JBoxchartApp extends JApplet
{
    public JBoxchart boxchart;
    
    public JBoxchartApp() {
    }
    
    public JBoxchartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final JBoxchart boxchart = new JBoxchart(super.app);
        this.boxchart = boxchart;
        super.graph = boxchart;
        super.init();
    }
}
