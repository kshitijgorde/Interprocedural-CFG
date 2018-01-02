// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.util;

import java.io.Serializable;

public class BooleanList extends AbstractObjectList implements Cloneable, Serializable
{
    public Boolean getBoolean(final int n) {
        return (Boolean)this.get(n);
    }
    
    public void setBoolean(final int n, final Boolean b) {
        this.set(n, b);
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public boolean equals(final Object o) {
        return o instanceof BooleanList && super.equals(o);
    }
    
    public int hashCode() {
        return super.hashCode();
    }
}
