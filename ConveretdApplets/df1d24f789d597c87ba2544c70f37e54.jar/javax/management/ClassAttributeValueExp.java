// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

class ClassAttributeValueExp extends AttributeValueExp
{
    private static final long serialVersionUID = -1081892073854801359L;
    String attr;
    
    public ClassAttributeValueExp() {
        super(null);
        this.attr = "Class";
    }
    
    public ValueExp apply(final ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException {
        try {
            final ObjectInstance instance = QueryEval.getMBeanServer().getObjectInstance(name);
            return Query.value(instance.getClassName());
        }
        catch (Exception e) {
            throw new InvalidApplicationException(name);
        }
    }
    
    public String toString() {
        return new String("class");
    }
}
