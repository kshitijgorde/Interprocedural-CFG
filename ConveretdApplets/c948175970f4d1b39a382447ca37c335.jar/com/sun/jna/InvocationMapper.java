// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.jna;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public interface InvocationMapper
{
    InvocationHandler getInvocationHandler(final NativeLibrary p0, final Method p1);
}
