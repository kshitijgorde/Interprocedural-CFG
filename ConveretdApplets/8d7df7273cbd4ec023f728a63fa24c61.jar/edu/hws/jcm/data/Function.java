// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

import java.io.Serializable;

public interface Function extends Serializable
{
    int getArity();
    
    double getVal(final double[] p0);
    
    double getValueWithCases(final double[] p0, final Cases p1);
    
    Function derivative(final int p0);
    
    Function derivative(final Variable p0);
    
    boolean dependsOn(final Variable p0);
}
