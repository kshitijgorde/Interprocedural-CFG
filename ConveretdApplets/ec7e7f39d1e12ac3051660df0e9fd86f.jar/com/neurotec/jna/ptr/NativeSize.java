// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.jna.ptr;

import com.sun.jna.Native;
import com.sun.jna.IntegerType;

public final class NativeSize extends IntegerType
{
    private static final long serialVersionUID = -6513517561483284492L;
    public static final int SIZE;
    public static final NativeSize ZERO;
    
    public NativeSize() {
        this(0L);
    }
    
    public NativeSize(final long value) {
        super(NativeSize.SIZE, value);
    }
    
    static {
        SIZE = Native.SIZE_T_SIZE;
        ZERO = new NativeSize();
    }
}
