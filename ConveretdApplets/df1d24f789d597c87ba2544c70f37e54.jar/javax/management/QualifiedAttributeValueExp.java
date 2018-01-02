// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

class QualifiedAttributeValueExp extends AttributeValueExp
{
    private static final long serialVersionUID = 8832517277410933254L;
    private String className;
    
    public QualifiedAttributeValueExp(final String className, final String value) {
        super(value);
        this.className = className;
    }
    
    public ValueExp apply(final ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException {
        try {
            final ObjectInstance instance = QueryEval.getMBeanServer().getObjectInstance(name);
            if (instance.getClassName().equals(this.className)) {
                return super.apply(name);
            }
        }
        catch (Exception e) {
            return null;
        }
        throw new InvalidApplicationException((Object)new String(name + "\n" + this.className));
    }
    
    public String toString() {
        return new String(this.className + "." + this.getAttributeName());
    }
}
