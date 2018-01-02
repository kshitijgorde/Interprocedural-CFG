// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class UpperFunction extends StringFunction
{
    public UpperFunction() {
        super(1);
    }
    
    public String getName() {
        return "UPPER";
    }
    
    public Object get(final Tuple tuple) {
        return this.param(0).get(tuple).toString().toUpperCase();
    }
}
