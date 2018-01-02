// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import org.jfree.util.ObjectUtils;
import java.io.Serializable;
import org.jfree.util.PublicCloneable;

public class KeyedObject implements Cloneable, PublicCloneable, Serializable
{
    private Comparable key;
    private Object object;
    
    public KeyedObject(final Comparable key, final Object object) {
        this.key = key;
        this.object = object;
    }
    
    public Comparable getKey() {
        return this.key;
    }
    
    public Object getObject() {
        return this.object;
    }
    
    public void setObject(final Object object) {
        this.object = object;
    }
    
    public Object clone() throws CloneNotSupportedException {
        final KeyedObject clone = (KeyedObject)super.clone();
        if (this.object instanceof PublicCloneable) {
            final PublicCloneable pc = (PublicCloneable)this.object;
            clone.object = pc.clone();
        }
        return clone;
    }
    
    public boolean equals(final Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof KeyedObject)) {
            return false;
        }
        final KeyedObject ko = (KeyedObject)object;
        return ObjectUtils.equal(this.key, ko.key) && ObjectUtils.equal(this.object, ko.object);
    }
}
