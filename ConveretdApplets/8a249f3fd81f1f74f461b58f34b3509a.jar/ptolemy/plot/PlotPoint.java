// 
// Decompiled by Procyon v0.5.30
// 

package ptolemy.plot;

public class PlotPoint
{
    public double x;
    public double y;
    public double yLowEB;
    public double yHighEB;
    public boolean connected;
    public boolean errorBar;
    
    public PlotPoint() {
        this.connected = false;
        this.errorBar = false;
    }
}
