// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal;

public class LONG
{
    public int value;
    
    public LONG(final int value) {
        this.value = value;
    }
    
    public boolean equals(final Object o) {
        return o == this || (o instanceof LONG && ((LONG)o).value == this.value);
    }
    
    public int hashCode() {
        return this.value;
    }
}
