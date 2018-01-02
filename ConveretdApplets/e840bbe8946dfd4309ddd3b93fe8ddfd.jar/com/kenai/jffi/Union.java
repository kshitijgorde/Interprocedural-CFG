// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

public final class Union extends Aggregate
{
    private final Type[] fields;
    
    public Union(final Type... fields) {
        super(Foreign.getInstance(), Foreign.getInstance().newStruct(Type.nativeHandles(fields), true));
        this.fields = fields.clone();
    }
}
