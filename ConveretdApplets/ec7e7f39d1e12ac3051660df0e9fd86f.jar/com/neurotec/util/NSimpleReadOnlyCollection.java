// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.util;

import com.neurotec.lang.NObject;

public abstract class NSimpleReadOnlyCollection<E> extends NReadOnlyCollection<E>
{
    protected NSimpleReadOnlyCollection(final NObject owner) {
        super(owner);
    }
    
    public final int indexOf(final Object o) {
        throw new UnsupportedOperationException();
    }
    
    protected final E getInternal(final int index) {
        return this.getNative(index);
    }
    
    public final int size() {
        return this.sizeNative();
    }
    
    protected abstract E[] getAllNative();
    
    protected abstract E getNative(final int p0);
    
    protected abstract int sizeNative();
}
