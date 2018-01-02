// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.io.Serializable;

public interface ValueExp extends Serializable
{
    ValueExp apply(final ObjectName p0) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException;
    
    void setMBeanServer(final MBeanServer p0);
}
