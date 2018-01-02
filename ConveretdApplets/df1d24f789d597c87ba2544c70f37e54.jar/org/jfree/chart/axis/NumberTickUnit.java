// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import java.text.NumberFormat;
import java.io.Serializable;

public class NumberTickUnit extends TickUnit implements Serializable
{
    private NumberFormat formatter;
    
    public NumberTickUnit(final double size) {
        this(size, NumberFormat.getNumberInstance());
    }
    
    public NumberTickUnit(final double size, final NumberFormat formatter) {
        super(size);
        this.formatter = formatter;
    }
    
    public String valueToString(final double value) {
        return this.formatter.format(value);
    }
}
