// 
// Decompiled by Procyon v0.5.30
// 

package org.jnp.interfaces;

import java.io.IOException;
import java.rmi.MarshalledObject;
import java.io.Serializable;

public class MarshalledValuePair implements Serializable
{
    private static final long serialVersionUID = -3403843515711139134L;
    private static boolean enableCallByReference;
    public MarshalledObject marshalledValue;
    public transient Object value;
    
    public static boolean getEnableCallByReference() {
        return MarshalledValuePair.enableCallByReference;
    }
    
    public static void setEnableCallByReference(final boolean flag) {
        MarshalledValuePair.enableCallByReference = flag;
    }
    
    public MarshalledValuePair(final Object value) throws IOException {
        this.value = value;
        this.marshalledValue = new MarshalledObject((T)value);
    }
    
    public Object get() throws ClassNotFoundException, IOException {
        Object theValue = MarshalledValuePair.enableCallByReference ? this.value : null;
        if (theValue == null && this.marshalledValue != null) {
            theValue = this.marshalledValue.get();
        }
        return theValue;
    }
    
    static {
        MarshalledValuePair.enableCallByReference = true;
    }
}
