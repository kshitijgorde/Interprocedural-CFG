// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

import java.applet.Applet;
import pclient.main.MainClient;

public interface ChatApplet
{
    void init();
    
    void start();
    
    void setParent(final MainClient p0, final Applet p1, final Config p2);
    
    void stop();
    
    Applet getApplet();
}
