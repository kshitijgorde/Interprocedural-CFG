// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

public final class Struct extends Aggregate
{
    private final Type[] fields;
    
    public Struct(final Type... fields) {
        super(Foreign.getInstance(), Foreign.getInstance().newStruct(Type.nativeHandles(fields), false));
        this.fields = fields.clone();
    }
}
