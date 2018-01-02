// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public final class WeakObject extends WeakReference
{
    protected final int hashCode;
    
    public WeakObject(final Object obj) {
        super(obj);
        this.hashCode = obj.hashCode();
    }
    
    public WeakObject(final Object obj, final ReferenceQueue queue) {
        super(obj, queue);
        this.hashCode = obj.hashCode();
    }
    
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == this.getClass()) {
            final WeakObject soft = (WeakObject)obj;
            final Object a = this.get();
            final Object b = soft.get();
            return a != null && b != null && (a == b || a.equals(b));
        }
        return false;
    }
    
    public int hashCode() {
        return this.hashCode;
    }
    
    public static WeakObject create(final Object obj) {
        if (obj == null) {
            return null;
        }
        return new WeakObject(obj);
    }
    
    public static WeakObject create(final Object obj, final ReferenceQueue queue) {
        if (obj == null) {
            return null;
        }
        return new WeakObject(obj, queue);
    }
}
