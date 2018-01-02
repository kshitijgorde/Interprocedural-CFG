// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class LeftFunction extends StringFunction
{
    public LeftFunction() {
        super(2);
    }
    
    public String getName() {
        return "LEFT";
    }
    
    public Object get(final Tuple tuple) {
        return this.param(0).get(tuple).toString().substring(0, this.param(1).getInt(tuple));
    }
}
