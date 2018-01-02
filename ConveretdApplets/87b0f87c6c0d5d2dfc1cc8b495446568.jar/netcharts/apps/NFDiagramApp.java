// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.NFDiagram;

public class NFDiagramApp extends NFApplet
{
    public NFDiagram diag;
    
    public NFDiagramApp() {
    }
    
    public NFDiagramApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final NFDiagram nfDiagram = new NFDiagram(super.app);
        this.diag = nfDiagram;
        super.graph = nfDiagram;
        super.init();
    }
}
