// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamField;

class NumericValueExp extends QueryEval implements ValueExp
{
    private static final long serialVersionUID = -4679739485102359104L;
    private static final ObjectStreamField[] serialPersistentFields;
    private Number val;
    
    public NumericValueExp() {
        this.val = new Double(0.0);
    }
    
    public NumericValueExp(final Number value) {
        this.val = value;
    }
    
    public boolean isInteger() {
        return this.val instanceof Integer || this.val instanceof Long;
    }
    
    public double getLongValue() {
        return this.val.longValue();
    }
    
    public double getDoubleValue() {
        return this.val.doubleValue();
    }
    
    public ValueExp apply(final ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException {
        return this;
    }
    
    public String toString() {
        return this.val.toString();
    }
    
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
    }
    
    private void writeObject(final ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
    }
    
    static {
        serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("val", Number.class) };
    }
}
