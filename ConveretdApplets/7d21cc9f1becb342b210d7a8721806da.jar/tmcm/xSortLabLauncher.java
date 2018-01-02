// 
// Decompiled by Procyon v0.5.30
// 

package tmcm;

import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Button;
import java.applet.Applet;

public class xSortLabLauncher extends Applet
{
    private xSortLabFrame frame;
    private Button launchButton;
    
    public String getAppletInfo() {
        return "xSortLabLauncher, by David J. Eck (eck@hws.edu), August 1997";
    }
    
    public void init() {
        this.setLayout(new BorderLayout());
        this.add("Center", this.launchButton = new Button("Launch xSortLab"));
    }
    
    private void launch() {
        if (this.frame != null) {
            if (this.frame.isClosed()) {
                this.frame = new xSortLabFrame();
            }
            else {
                this.frame.show();
            }
        }
        else {
            this.frame = new xSortLabFrame();
        }
        this.frame.exitOnClose = true;
    }
    
    public void destroy() {
        if (this.frame != null && !this.frame.isClosed()) {
            this.frame.close();
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target == this.launchButton) {
            this.launch();
            return true;
        }
        return super.action(event, o);
    }
}
