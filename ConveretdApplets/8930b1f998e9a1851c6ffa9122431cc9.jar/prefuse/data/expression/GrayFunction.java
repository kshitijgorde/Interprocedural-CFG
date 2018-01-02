// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.util.ColorLib;
import prefuse.data.Tuple;

class GrayFunction extends IntFunction
{
    public GrayFunction() {
        super(2);
    }
    
    public String getName() {
        return "GRAY";
    }
    
    public int getInt(final Tuple tuple) {
        final int int1 = this.param(0).getInt(tuple);
        if (this.paramCount() == 2) {
            return ColorLib.gray(int1, this.param(1).getInt(tuple));
        }
        return ColorLib.gray(int1);
    }
}
