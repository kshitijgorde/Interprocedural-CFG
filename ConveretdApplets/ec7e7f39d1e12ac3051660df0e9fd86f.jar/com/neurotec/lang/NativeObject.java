// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

import com.sun.jna.ptr.ByReference;

final class NativeObject
{
    private ByReference nativeObject;
    private int size;
    
    NativeObject(final ByReference nativeObject, final int size) {
        this.nativeObject = nativeObject;
        this.size = size;
    }
    
    public final ByReference getNativeObject() {
        return this.nativeObject;
    }
    
    public final int getSize() {
        return this.size;
    }
}
