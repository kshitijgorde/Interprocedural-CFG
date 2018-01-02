// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.datavisualization.elements;

public interface DataConverter
{
    String getHumanRepresentation(final double p0);
    
    double getMachineRepresentation(final String p0);
}
