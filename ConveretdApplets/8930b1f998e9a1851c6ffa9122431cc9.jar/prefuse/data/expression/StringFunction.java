// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Schema;

abstract class StringFunction extends FunctionExpression
{
    protected StringFunction(final int n) {
        super(n);
    }
    
    public Class getType(final Schema schema) {
        return String.class;
    }
    
    protected StringBuffer getBuffer() {
        return new StringBuffer();
    }
}
