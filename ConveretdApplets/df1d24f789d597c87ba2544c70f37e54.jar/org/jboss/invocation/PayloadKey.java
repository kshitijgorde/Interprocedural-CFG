// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.io.ObjectStreamException;
import java.io.Serializable;

public final class PayloadKey implements Serializable
{
    private static final long serialVersionUID = 5436722659170811314L;
    private static final int MAX_KEY_ID = 3;
    private static final PayloadKey[] values;
    public static final PayloadKey TRANSIENT;
    public static final PayloadKey AS_IS;
    public static final PayloadKey PAYLOAD;
    private final transient String name;
    private final int ordinal;
    
    private PayloadKey(final String name, final int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
        PayloadKey.values[ordinal] = this;
    }
    
    public String toString() {
        return this.name;
    }
    
    Object readResolve() throws ObjectStreamException {
        return PayloadKey.values[this.ordinal];
    }
    
    static {
        values = new PayloadKey[4];
        TRANSIENT = new PayloadKey("TRANSIENT", 0);
        AS_IS = new PayloadKey("AS_IS", 1);
        PAYLOAD = new PayloadKey("PAYLOAD", 2);
    }
}
