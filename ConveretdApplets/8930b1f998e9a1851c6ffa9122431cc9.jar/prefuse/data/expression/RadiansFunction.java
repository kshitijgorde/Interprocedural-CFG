// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class RadiansFunction extends DoubleFunction
{
    public RadiansFunction() {
        super(1);
    }
    
    public String getName() {
        return "RADIANS";
    }
    
    public double getDouble(final Tuple tuple) {
        if (this.paramCount() == 1) {
            return Math.toRadians(this.param(0).getDouble(tuple));
        }
        this.missingParams();
        return Double.NaN;
    }
}
