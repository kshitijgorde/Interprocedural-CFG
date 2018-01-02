// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.JBarchart;

public class JBarchartApp extends JApplet
{
    public JBarchart bar;
    
    public JBarchartApp() {
    }
    
    public JBarchartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final JBarchart barchart = new JBarchart(super.app);
        this.bar = barchart;
        super.graph = barchart;
        super.init();
    }
}
