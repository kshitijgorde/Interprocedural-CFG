// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class PositionFunction extends IntFunction
{
    public PositionFunction() {
        super(2);
    }
    
    public String getName() {
        return "POSITION";
    }
    
    public int getInt(final Tuple tuple) {
        return this.param(1).get(tuple).toString().indexOf(this.param(0).get(tuple).toString());
    }
}
