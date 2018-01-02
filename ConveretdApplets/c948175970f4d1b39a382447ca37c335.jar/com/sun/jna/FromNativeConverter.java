// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.jna;

public interface FromNativeConverter
{
    Object fromNative(final Object p0, final FromNativeContext p1);
    
    Class nativeType();
}
