// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.struct;

import java.lang.reflect.Array;
import com.kenai.jaffl.MemoryIO;

public final class StructUtil
{
    public static final MemoryIO getMemoryIO(final Struct struct) {
        return struct.__info.getMemoryIO(0);
    }
    
    public static final MemoryIO getMemoryIO(final Struct struct, final int flags) {
        return struct.__info.getMemoryIO(flags);
    }
    
    public static final int getSize(final Struct struct) {
        return struct.__info.size();
    }
    
    public static final int getMinimumAlignment(final Struct struct) {
        return struct.__info.getMinimumAlignment();
    }
    
    public static final boolean isDirect(final Struct struct) {
        return struct.__info.isDirect();
    }
    
    public static final <T extends Struct> T[] newArray(final Class<T> type, final int length) {
        try {
            final T[] array = (T[])Array.newInstance(type, length);
            for (int i = 0; i < length; ++i) {
                array[i] = type.newInstance();
            }
            if (array.length > 0) {
                final int align = getMinimumAlignment(array[0]);
                final int mask = align - 1;
                int structSize = getSize(array[0]);
                if ((structSize & mask) != 0x0) {
                    structSize = (structSize & ~mask) + align;
                }
                final MemoryIO memory = MemoryIO.allocateDirect(structSize * length);
                for (int j = 0; j < array.length; ++j) {
                    array[j].useMemory(memory.slice(structSize * j, structSize));
                }
            }
            return array;
        }
        catch (SecurityException ex) {
            throw new RuntimeException(ex);
        }
        catch (InstantiationException ex2) {
            throw new RuntimeException(ex2);
        }
        catch (IllegalAccessException ex3) {
            throw new RuntimeException(ex3);
        }
    }
}
