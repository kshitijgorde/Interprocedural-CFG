// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class EFunction extends DoubleFunction
{
    public EFunction() {
        super(0);
    }
    
    public String getName() {
        return "E";
    }
    
    public double getDouble(final Tuple tuple) {
        return 2.718281828459045;
    }
}
