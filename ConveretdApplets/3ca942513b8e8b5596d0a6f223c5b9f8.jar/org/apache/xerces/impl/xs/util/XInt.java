// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.util;

public final class XInt
{
    private int fValue;
    
    XInt(final int fValue) {
        this.fValue = fValue;
    }
    
    public final int intValue() {
        return this.fValue;
    }
    
    public final short shortValue() {
        return (short)this.fValue;
    }
    
    public final boolean equals(final XInt xInt) {
        return this.fValue == xInt.fValue;
    }
    
    public String toString() {
        return Integer.toString(this.fValue);
    }
}
