// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;
import prefuse.data.Schema;

public abstract class AbstractPredicate extends AbstractExpression implements Predicate
{
    public Class getType(final Schema schema) {
        return Boolean.TYPE;
    }
    
    public Object get(final Tuple tuple) {
        return this.getBoolean(tuple) ? Boolean.TRUE : Boolean.FALSE;
    }
}
