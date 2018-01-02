// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.JDiagram;

public class JDiagramApp extends JApplet
{
    public JDiagram diag;
    
    public JDiagramApp() {
    }
    
    public JDiagramApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final JDiagram diagram = new JDiagram(super.app);
        this.diag = diagram;
        super.graph = diagram;
        super.init();
    }
}
