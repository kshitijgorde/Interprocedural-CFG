// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class SumFunction extends DoubleFunction
{
    public SumFunction() {
        super(-1);
    }
    
    public String getName() {
        return "SUM";
    }
    
    public double getDouble(final Tuple tuple) {
        double double1 = this.param(0).getDouble(tuple);
        for (int i = 1; i < this.paramCount(); ++i) {
            double1 += this.param(i).getDouble(tuple);
        }
        return double1;
    }
}
