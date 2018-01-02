// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.mapper;

public interface TypeMapper
{
    FromNativeConverter getFromNativeConverter(final Class p0);
    
    ToNativeConverter getToNativeConverter(final Class p0);
}
