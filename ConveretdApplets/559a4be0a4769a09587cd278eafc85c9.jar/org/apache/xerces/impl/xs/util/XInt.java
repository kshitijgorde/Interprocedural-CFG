// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.util;

public final class XInt
{
    private int fValue;
    
    XInt(final int value) {
        this.fValue = value;
    }
    
    public final int intValue() {
        return this.fValue;
    }
    
    public final short shortValue() {
        return (short)this.fValue;
    }
    
    public final boolean equals(final XInt compareVal) {
        return this.fValue == compareVal.fValue;
    }
    
    public String toString() {
        return Integer.toString(this.fValue);
    }
}
