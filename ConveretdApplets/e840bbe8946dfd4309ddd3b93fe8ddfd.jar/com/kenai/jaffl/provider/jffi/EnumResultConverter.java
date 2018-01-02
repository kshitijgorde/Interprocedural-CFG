// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

import com.kenai.jaffl.util.EnumMapper;
import com.kenai.jaffl.mapper.FromNativeContext;
import com.kenai.jaffl.mapper.FromNativeConverter;

public class EnumResultConverter implements FromNativeConverter
{
    private final Class enumClass;
    
    EnumResultConverter(final Class enumClass) {
        this.enumClass = enumClass;
    }
    
    public Object fromNative(final Object nativeValue, final FromNativeContext context) {
        return EnumMapper.getInstance().valueOf((int)nativeValue, (Class<Object>)this.enumClass);
    }
    
    public Class nativeType() {
        return Integer.class;
    }
}
