import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import xfunctions.xFunctionsPanel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class xFunctionsApplet extends Applet
{
    private xFunctionsPanel xFunctions;
    
    public String getAppletInfo() {
        return "xFunctionsApplet\nDavid Eck\neck@hws.edu\nhttp://math.hws.edu/eck/\n27 October 1999";
    }
    
    public void init() {
        this.setBackground(Color.darkGray);
        this.setLayout(new BorderLayout());
        this.add("Center", this.xFunctions = new xFunctionsPanel(null, this));
    }
    
    public void start() {
        this.xFunctions.start();
    }
    
    public void stop() {
        this.xFunctions.stop();
    }
}
