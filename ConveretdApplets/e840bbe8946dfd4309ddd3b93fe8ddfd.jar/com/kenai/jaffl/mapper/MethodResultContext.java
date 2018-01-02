// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.mapper;

import java.lang.reflect.Method;

public class MethodResultContext implements FromNativeContext
{
    private final Method method;
    
    public MethodResultContext(final Method method) {
        this.method = method;
    }
    
    public Method getMethod() {
        return this.method;
    }
}
