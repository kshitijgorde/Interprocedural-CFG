// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.mapper;

public interface FromNativeConverter<J, N>
{
    J fromNative(final N p0, final FromNativeContext p1);
    
    Class<N> nativeType();
}
