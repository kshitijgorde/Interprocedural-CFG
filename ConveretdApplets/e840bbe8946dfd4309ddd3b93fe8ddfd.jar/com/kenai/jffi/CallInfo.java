// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

public interface CallInfo
{
    int getParameterCount();
    
    Type getReturnType();
    
    Type getParameterType(final int p0);
    
    int getRawParameterSize();
}
