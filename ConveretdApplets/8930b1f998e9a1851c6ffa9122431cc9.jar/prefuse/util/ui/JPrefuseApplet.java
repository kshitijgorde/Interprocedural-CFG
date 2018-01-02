// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.ui;

import prefuse.activity.ActivityManager;
import javax.swing.JApplet;

public class JPrefuseApplet extends JApplet
{
    public void destroy() {
        ActivityManager.stopThread();
    }
    
    public void stop() {
        ActivityManager.stopThread();
    }
}
