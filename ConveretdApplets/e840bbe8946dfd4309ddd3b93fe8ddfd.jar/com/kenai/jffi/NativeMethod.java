// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

public final class NativeMethod
{
    final long function;
    final String name;
    final String signature;
    
    public NativeMethod(final long address, final String name, final String signature) {
        this.function = address;
        this.name = name;
        this.signature = signature;
    }
}
