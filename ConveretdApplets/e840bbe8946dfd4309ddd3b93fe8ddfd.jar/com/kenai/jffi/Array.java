// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

public final class Array extends Aggregate
{
    private final Type elementType;
    private final int length;
    
    public Array(final Type elementType, final int length) {
        super(Foreign.getInstance(), Foreign.getInstance().newArray(elementType.handle(), length));
        this.elementType = elementType;
        this.length = length;
    }
    
    public final Type getElementType() {
        return this.elementType;
    }
    
    public final int length() {
        return this.length;
    }
}
