// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.datavisualization.elements;

import java.text.DecimalFormat;

public class DefaultDataConverter implements DataConverter
{
    private DecimalFormat format;
    
    public DefaultDataConverter() {
        this.format = new DecimalFormat();
    }
    
    public String getHumanRepresentation(final double input) {
        return this.format.format(input);
    }
    
    public double getMachineRepresentation(final String input) {
        return Double.parseDouble(input.replaceAll(",", ""));
    }
}
