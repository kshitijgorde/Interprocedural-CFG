// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Schema;
import prefuse.data.Tuple;

public class BooleanLiteral extends Literal implements Predicate
{
    public static final BooleanLiteral TRUE;
    public static final BooleanLiteral FALSE;
    private final boolean m_value;
    
    public BooleanLiteral(final boolean value) {
        this.m_value = value;
    }
    
    public boolean getBoolean(final Tuple tuple) {
        return this.m_value;
    }
    
    public Class getType(final Schema schema) {
        return Boolean.TYPE;
    }
    
    public Object get(final Tuple tuple) {
        return this.getBoolean(tuple) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public String toString() {
        return String.valueOf(this.m_value).toUpperCase();
    }
    
    static {
        TRUE = new BooleanLiteral(true);
        FALSE = new BooleanLiteral(false);
    }
}
