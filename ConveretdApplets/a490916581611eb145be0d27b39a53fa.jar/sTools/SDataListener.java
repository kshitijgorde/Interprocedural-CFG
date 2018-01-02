// 
// Decompiled by Procyon v0.5.30
// 

package sTools;

public interface SDataListener
{
    int getID();
    
    void addDatum(final int p0, final double p1, final double p2);
    
    void addData(final int p0, final double[] p1, final double[] p2);
    
    void deleteSeries(final int p0);
    
    void clearSeries(final int p0);
    
    void setOwner(final SApplet p0);
    
    SApplet getOwner();
}
