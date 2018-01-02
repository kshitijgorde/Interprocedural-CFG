// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl;

import com.kenai.jaffl.annotations.NulTerminate;
import com.kenai.jaffl.annotations.Pinned;
import com.kenai.jaffl.annotations.Direct;
import com.kenai.jaffl.annotations.Transient;
import com.kenai.jaffl.annotations.In;
import com.kenai.jaffl.annotations.Out;
import java.lang.annotation.Annotation;

public final class ParameterFlags
{
    public static final int OUT = 1;
    public static final int IN = 2;
    public static final int PINNED = 4;
    public static final int NULTERMINATE = 8;
    public static final int TRANSIENT = 16;
    public static final int DIRECT = 32;
    
    public static final int parse(final Annotation[] annotations) {
        int flags = 0;
        for (final Annotation a : annotations) {
            flags |= ((a instanceof Out) ? 1 : 0);
            flags |= ((a instanceof In) ? 2 : 0);
            flags |= ((a instanceof Transient) ? 16 : 0);
            flags |= ((a instanceof Direct) ? 32 : 0);
            flags |= ((a instanceof Pinned) ? 4 : 0);
            flags |= ((a instanceof NulTerminate) ? 8 : 0);
        }
        return flags;
    }
    
    public static final boolean isFlag(final Annotation annotation) {
        return annotation instanceof Pinned || annotation instanceof Transient || annotation instanceof Direct || annotation instanceof NulTerminate || annotation instanceof Out || annotation instanceof In;
    }
    
    public static final boolean isPinned(final int flags) {
        return (flags & 0x4) != 0x0;
    }
    
    public static final boolean isTransient(final int flags) {
        return (flags & 0x10) != 0x0;
    }
    
    public static final boolean isDirect(final int flags) {
        return (flags & 0x20) != 0x0;
    }
    
    public static final boolean isNulTerminate(final int flags) {
        return (flags & 0x8) != 0x0;
    }
    
    public static final boolean isOut(final int flags) {
        return (flags & 0x3) != 0x2;
    }
    
    public static final boolean isIn(final int flags) {
        return (flags & 0x3) != 0x1;
    }
}
