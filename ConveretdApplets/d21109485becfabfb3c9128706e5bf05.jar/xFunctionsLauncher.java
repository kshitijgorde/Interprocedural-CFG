import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class xFunctionsLauncher extends Applet
{
    private Button launchButton;
    private boolean frameIsOpen;
    private xFunctionsFrame frame;
    
    public String getAppletInfo() {
        return "xFunctionsApplet\nDavid Eck\neck@hws.edu\nhttp://math.hws.edu/eck/\n27 October 1999";
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.add("Center", this.launchButton = new Button("Launch xFunctions"));
    }
    
    void frameHasOpened() {
        this.frameIsOpen = true;
        this.launchButton.enable();
        this.launchButton.setLabel("Close xFunctions");
    }
    
    void frameHasClosed() {
        this.frameIsOpen = false;
        this.launchButton.setLabel("Launch xFunctions");
        this.frame = null;
    }
    
    public void destroy() {
        if (this.frame != null) {
            this.frame.close();
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.launchButton) {
            if (this.frame == null) {
                this.launchButton.disable();
                try {
                    this.frame = new xFunctionsFrame(null, this);
                }
                catch (Exception ex) {
                    this.launchButton.enable();
                    this.launchButton.setLabel("Error! Try Again?");
                }
            }
            else {
                this.frame.close();
            }
            return true;
        }
        return super.action(event, o);
    }
}
