// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.mapper;

public interface ToNativeConverter<J, N>
{
    N toNative(final J p0, final ToNativeContext p1);
    
    Class<N> nativeType();
}
