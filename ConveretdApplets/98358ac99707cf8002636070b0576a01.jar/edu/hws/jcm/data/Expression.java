// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

public interface Expression extends Value
{
    double getValueWithCases(final Cases p0);
    
    Expression derivative(final Variable p0);
    
    boolean dependsOn(final Variable p0);
    
    String toString();
}
