// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.NFBoxchart;

public class NFBoxchartApp extends NFApplet
{
    public NFBoxchart boxchart;
    
    public NFBoxchartApp() {
    }
    
    public NFBoxchartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final NFBoxchart nfBoxchart = new NFBoxchart(super.app);
        this.boxchart = nfBoxchart;
        super.graph = nfBoxchart;
        super.init();
    }
}
