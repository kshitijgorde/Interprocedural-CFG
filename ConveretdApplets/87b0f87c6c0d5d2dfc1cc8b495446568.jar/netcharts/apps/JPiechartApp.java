// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.apps;

import java.applet.Applet;
import netcharts.graphics.JPiechart;

public class JPiechartApp extends JApplet
{
    public JPiechart pie;
    
    public JPiechartApp() {
    }
    
    public JPiechartApp(final Applet applet) {
        super(applet);
    }
    
    public void init() {
        final JPiechart piechart = new JPiechart(super.app);
        this.pie = piechart;
        super.graph = piechart;
        super.init();
    }
}
