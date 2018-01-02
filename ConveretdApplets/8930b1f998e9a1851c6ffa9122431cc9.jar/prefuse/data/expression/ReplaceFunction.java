// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class ReplaceFunction extends StringFunction
{
    public ReplaceFunction() {
        super(3);
    }
    
    public String getName() {
        return "REPLACE";
    }
    
    public Object get(final Tuple tuple) {
        return this.param(0).get(tuple).toString().replaceAll(this.param(1).get(tuple).toString(), this.param(2).get(tuple).toString());
    }
}
