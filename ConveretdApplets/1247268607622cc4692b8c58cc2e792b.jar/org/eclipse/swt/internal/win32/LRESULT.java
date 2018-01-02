// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.win32;

public class LRESULT
{
    public int value;
    public static final LRESULT ONE;
    public static final LRESULT ZERO;
    
    static {
        ONE = new LRESULT(1);
        ZERO = new LRESULT(0);
    }
    
    public LRESULT(final int value) {
        this.value = value;
    }
}
