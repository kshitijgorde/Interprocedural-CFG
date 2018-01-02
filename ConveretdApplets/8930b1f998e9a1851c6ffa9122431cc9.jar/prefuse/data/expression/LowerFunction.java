// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class LowerFunction extends StringFunction
{
    public LowerFunction() {
        super(1);
    }
    
    public String getName() {
        return "LOWER";
    }
    
    public Object get(final Tuple tuple) {
        return this.param(0).get(tuple).toString().toLowerCase();
    }
}
