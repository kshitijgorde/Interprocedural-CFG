// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.axis;

import java.text.NumberFormat;
import java.io.Serializable;

public class SymbolicTickUnit extends NumberTickUnit implements Serializable
{
    private String[] symbolicValue;
    
    public SymbolicTickUnit(final double size, final String[] sv) {
        super(size, null);
        System.arraycopy(sv, 0, this.symbolicValue = new String[sv.length], 0, sv.length);
    }
    
    public String valueToString(final double value) {
        if (value < 0.0 || value >= this.symbolicValue.length) {
            throw new IllegalArgumentException("The value " + value + " does not have a corresponding symbolic value");
        }
        return this.symbolicValue[(int)value];
    }
    
    public int getSymbolCount() {
        return this.symbolicValue.length;
    }
}
