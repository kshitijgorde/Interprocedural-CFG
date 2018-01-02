// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

public final class ArrayFlags
{
    public static final int IN = 1;
    public static final int OUT = 2;
    public static final int PINNED = 8;
    public static final int NULTERMINATE = 4;
    public static final int CLEAR = 16;
    
    public static final boolean isOut(final int flags) {
        return (flags & 0x3) != 0x1;
    }
    
    public static final boolean isIn(final int flags) {
        return (flags & 0x3) != 0x2;
    }
}
