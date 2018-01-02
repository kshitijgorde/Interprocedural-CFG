// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class RandFunction extends DoubleFunction
{
    public RandFunction() {
        super(0);
    }
    
    public String getName() {
        return "RAND";
    }
    
    public double getDouble(final Tuple tuple) {
        return Math.random();
    }
}
