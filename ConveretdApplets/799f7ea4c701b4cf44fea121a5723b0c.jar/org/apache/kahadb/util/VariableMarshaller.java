// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.kahadb.util;

public abstract class VariableMarshaller<T> implements Marshaller<T>
{
    public int getFixedSize() {
        return -1;
    }
    
    public boolean isDeepCopySupported() {
        return false;
    }
    
    public T deepCopy(final T source) {
        throw new UnsupportedOperationException();
    }
}
