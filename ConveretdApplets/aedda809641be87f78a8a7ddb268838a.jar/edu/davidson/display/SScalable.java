// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

public interface SScalable
{
    double xFromPix(final int p0);
    
    double yFromPix(final int p0);
    
    int pixFromX(final double p0);
    
    int pixFromY(final double p0);
    
    int getPixWidth();
    
    int getPixHeight();
}
