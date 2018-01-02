// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

import java.io.Serializable;

public interface ExpressionCommand extends Serializable
{
    void apply(final StackOfDouble p0, final Cases p1);
    
    void compileDerivative(final ExpressionProgram p0, final int p1, final ExpressionProgram p2, final Variable p3);
    
    int extent(final ExpressionProgram p0, final int p1);
    
    boolean dependsOn(final Variable p0);
    
    void appendOutputString(final ExpressionProgram p0, final int p1, final StringBuffer p2);
}
