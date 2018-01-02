// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.math.BigInteger;
import java.math.BigDecimal;
import org.jboss.proxy.ClientContainer;
import java.io.IOException;
import org.jboss.remoting.serialization.impl.jboss.LocalMarshalledValue;
import org.jboss.serial.objectmetamodel.safecloning.SafeCloningRepository;
import org.jboss.remoting.serialization.IMarshalledValue;
import org.jboss.serial.objectmetamodel.safecloning.SafeClone;

public class DataContainerMarshallingInvokerInterceptor extends MarshallingInvokerInterceptor
{
    private static final long serialVersionUID = -1889397492156790576L;
    static final SafeClone safeToReuse;
    
    protected IMarshalledValue createMarshalledValueForCallByValue(final Object value) throws IOException {
        return (IMarshalledValue)new LocalMarshalledValue(value, new SafeCloningRepository(DataContainerMarshallingInvokerInterceptor.safeToReuse));
    }
    
    static {
        safeToReuse = (SafeClone)new SafeClone() {
            public boolean isSafeToReuse(final Object obj) {
                return obj != null && (obj instanceof ClientContainer || obj instanceof String || obj instanceof Number || obj instanceof BigDecimal || obj instanceof BigInteger || obj instanceof Byte || obj instanceof Double || obj instanceof Float || obj instanceof Integer || obj instanceof Long || obj instanceof Short);
            }
        };
    }
}
