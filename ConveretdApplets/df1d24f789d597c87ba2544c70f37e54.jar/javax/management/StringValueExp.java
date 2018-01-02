// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import org.jboss.mx.util.QueryExpSupport;

public class StringValueExp implements ValueExp
{
    private static final long serialVersionUID = -3256390509806284044L;
    private String val;
    
    public StringValueExp() {
    }
    
    public StringValueExp(final String value) {
        this.val = value;
    }
    
    public ValueExp apply(final ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException {
        return this;
    }
    
    public String getValue() {
        return this.val;
    }
    
    public void setMBeanServer(final MBeanServer server) {
        QueryExpSupport.server.set(server);
    }
    
    public String toString() {
        return this.val;
    }
}
