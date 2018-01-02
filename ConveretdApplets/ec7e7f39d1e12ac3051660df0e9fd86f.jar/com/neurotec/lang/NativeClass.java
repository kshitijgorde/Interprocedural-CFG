// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

final class NativeClass
{
    private Class type;
    private boolean isUnsigned;
    
    public NativeClass(final Class type, final boolean isUnsigned) {
        this.type = type;
        this.isUnsigned = isUnsigned;
    }
    
    public final Class getType() {
        return this.type;
    }
    
    public final boolean isUnsigned() {
        return this.isUnsigned;
    }
}
