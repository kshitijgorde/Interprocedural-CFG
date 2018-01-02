// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.mina.core.session;

import java.io.Serializable;

public final class AttributeKey implements Serializable
{
    private static final long serialVersionUID = -583377473376683096L;
    private final String name;
    
    public AttributeKey(final Class<?> clazz, final String s) {
        this.name = clazz.getName() + '.' + s + '@' + Integer.toHexString(this.hashCode());
    }
    
    @Override
    public final String toString() {
        return this.name;
    }
}
