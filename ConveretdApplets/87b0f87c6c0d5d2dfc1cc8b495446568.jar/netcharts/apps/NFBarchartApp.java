// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.NFBarchart;

public class NFBarchartApp extends NFApplet
{
    public NFBarchart bar;
    
    public NFBarchartApp() {
    }
    
    public NFBarchartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final NFBarchart nfBarchart = new NFBarchart(super.app);
        this.bar = nfBarchart;
        super.graph = nfBarchart;
        super.init();
    }
}
