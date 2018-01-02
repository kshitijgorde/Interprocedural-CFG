// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.io.Serializable;

public final class Null implements Serializable
{
    static final long serialVersionUID = -403173436435490144L;
    public static final Null VALUE;
    
    public String toString() {
        return null;
    }
    
    public int hashCode() {
        return 0;
    }
    
    public boolean equals(final Object obj) {
        return obj == this || obj == null || obj.getClass() == this.getClass();
    }
    
    static {
        VALUE = new Null();
    }
}
