// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.NFPiechart;

public class NFPiechartApp extends NFApplet
{
    public NFPiechart pie;
    
    public NFPiechartApp() {
    }
    
    public NFPiechartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final NFPiechart nfPiechart = new NFPiechart(super.app);
        this.pie = nfPiechart;
        super.graph = nfPiechart;
        super.init();
    }
}
