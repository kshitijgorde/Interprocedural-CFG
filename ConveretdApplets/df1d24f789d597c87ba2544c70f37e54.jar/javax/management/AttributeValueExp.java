// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import org.jboss.mx.util.QueryExpSupport;

public class AttributeValueExp implements ValueExp
{
    private static final long serialVersionUID = -7768025046539163385L;
    private String attr;
    
    public AttributeValueExp() {
    }
    
    public AttributeValueExp(final String attr) {
        this.attr = attr;
    }
    
    public String getAttributeName() {
        return this.attr;
    }
    
    public ValueExp apply(final ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException {
        final Object object = this.getAttribute(name);
        if (object != null && object instanceof String) {
            return new StringValueExp((String)object);
        }
        if (object != null && object instanceof Boolean) {
            return new BooleanValueExp((Boolean)object);
        }
        if (object != null && object instanceof Number) {
            return new NumericValueExp((Number)object);
        }
        throw new BadAttributeValueExpException(object);
    }
    
    public void setMBeanServer(final MBeanServer server) {
        QueryExpSupport.server.set(server);
    }
    
    public String toString() {
        return this.attr;
    }
    
    protected Object getAttribute(final ObjectName name) {
        try {
            return QueryEval.getMBeanServer().getAttribute(name, this.attr);
        }
        catch (Exception e) {
            return null;
        }
    }
}
