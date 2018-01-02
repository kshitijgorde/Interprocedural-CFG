// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.LibraryOption;
import java.util.Map;

public abstract class LibraryLoader
{
    abstract <T> T loadLibrary(final Library p0, final Class<T> p1, final Map<LibraryOption, ?> p2);
    
    abstract boolean isInterfaceSupported(final Class p0, final Map<LibraryOption, ?> p1);
}
