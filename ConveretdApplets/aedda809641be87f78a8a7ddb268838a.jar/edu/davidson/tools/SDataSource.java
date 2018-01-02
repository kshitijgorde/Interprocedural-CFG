// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.tools;

public interface SDataSource
{
    double[][] getVariables();
    
    String[] getVarStrings();
    
    int getID();
    
    void setOwner(final SApplet p0);
    
    SApplet getOwner();
}
