// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class PiFunction extends DoubleFunction
{
    public PiFunction() {
        super(0);
    }
    
    public String getName() {
        return "PI";
    }
    
    public double getDouble(final Tuple tuple) {
        return 3.141592653589793;
    }
}
