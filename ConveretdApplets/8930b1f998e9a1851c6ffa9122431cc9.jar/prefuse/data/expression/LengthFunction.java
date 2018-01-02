// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class LengthFunction extends IntFunction
{
    public LengthFunction() {
        super(1);
    }
    
    public String getName() {
        return "LENGTH";
    }
    
    public int getInt(final Tuple tuple) {
        return this.param(0).get(tuple).toString().length();
    }
}
