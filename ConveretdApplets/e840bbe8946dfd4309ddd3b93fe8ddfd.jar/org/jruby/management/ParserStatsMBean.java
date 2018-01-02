// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.management;

public interface ParserStatsMBean
{
    double getTotalParseTime();
    
    double getParseTimePerKB();
    
    long getTotalParsedBytes();
    
    int getNumberOfEvalParses();
    
    int getNumberOfLoadParses();
}
