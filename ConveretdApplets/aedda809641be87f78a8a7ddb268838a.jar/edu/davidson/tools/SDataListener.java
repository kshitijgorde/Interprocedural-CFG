// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.tools;

public interface SDataListener
{
    int getID();
    
    void addDatum(final SDataSource p0, final int p1, final double p2, final double p3);
    
    void addData(final SDataSource p0, final int p1, final double[] p2, final double[] p3);
    
    void deleteSeries(final int p0);
    
    void clearSeries(final int p0);
    
    void setOwner(final SApplet p0);
    
    SApplet getOwner();
}
