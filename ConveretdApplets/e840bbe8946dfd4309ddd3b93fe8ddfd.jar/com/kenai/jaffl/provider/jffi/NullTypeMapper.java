// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.mapper.ToNativeConverter;
import com.kenai.jaffl.mapper.FromNativeConverter;
import com.kenai.jaffl.mapper.TypeMapper;

public class NullTypeMapper implements TypeMapper
{
    public static final TypeMapper INSTANCE;
    
    public FromNativeConverter getFromNativeConverter(final Class type) {
        return null;
    }
    
    public ToNativeConverter getToNativeConverter(final Class type) {
        return null;
    }
    
    static {
        INSTANCE = new NullTypeMapper();
    }
}
