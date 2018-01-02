// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class MinFunction extends DoubleFunction
{
    public MinFunction() {
        super(-1);
    }
    
    public String getName() {
        return "MIN";
    }
    
    public double getDouble(final Tuple tuple) {
        double double1 = this.param(0).getDouble(tuple);
        for (int i = 1; i < this.paramCount(); ++i) {
            final double double2 = this.param(i).getDouble(tuple);
            if (double2 < double1) {
                double1 = double2;
            }
        }
        return double1;
    }
}
