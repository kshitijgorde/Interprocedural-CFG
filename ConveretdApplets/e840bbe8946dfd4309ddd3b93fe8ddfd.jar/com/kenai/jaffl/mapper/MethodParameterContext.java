// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.mapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public final class MethodParameterContext implements ToNativeContext
{
    private final Method method;
    private final int parameterIndex;
    private final Annotation[] annotations;
    
    public MethodParameterContext(final Method method, final int parameterIndex) {
        this.method = method;
        this.parameterIndex = parameterIndex;
        this.annotations = method.getParameterAnnotations()[parameterIndex];
    }
    
    public Method getMethod() {
        return this.method;
    }
    
    public int getParameterIndex() {
        return this.parameterIndex;
    }
}
