// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.jna.ptr;

import com.sun.jna.Native;
import com.sun.jna.ptr.ByReference;

public final class CharByReference extends ByReference
{
    public CharByReference() {
        this('\0');
    }
    
    public CharByReference(final char value) {
        super(Native.WCHAR_SIZE);
        this.setValue(value);
    }
    
    public void setValue(final char value) {
        this.getPointer().setChar(0L, value);
    }
    
    public char getValue() {
        return this.getPointer().getChar(0L);
    }
}
