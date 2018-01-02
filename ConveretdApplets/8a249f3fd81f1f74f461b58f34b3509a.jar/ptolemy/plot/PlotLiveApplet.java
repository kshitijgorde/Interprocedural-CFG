// 
// Decompiled by Procyon v0.5.30
// 

package ptolemy.plot;

public class PlotLiveApplet extends PlotApplet
{
    public String getAppletInfo() {
        return "PlotLiveApplet 2.0: Demo of PlotLive.\nBy: Edward A. Lee, eal@eecs.berkeley.edu\n    Christopher Hylands, @eecs.berkeley.edu\n($Id: PlotLiveApplet.java,v 1.8 1998/11/18 07:43:22 cxh Exp $)";
    }
    
    public void start() {
        ((PlotLive)this.plot()).start();
    }
    
    public void stop() {
        ((PlotLive)this.plot()).stop();
    }
}
