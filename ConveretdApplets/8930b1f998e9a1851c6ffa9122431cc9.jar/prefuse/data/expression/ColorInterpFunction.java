// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.util.ColorLib;
import prefuse.data.Tuple;

class ColorInterpFunction extends IntFunction
{
    public ColorInterpFunction() {
        super(3);
    }
    
    public String getName() {
        return "COLORINTERP";
    }
    
    public int getInt(final Tuple tuple) {
        return ColorLib.interp(this.param(0).getInt(tuple), this.param(1).getInt(tuple), this.param(2).getDouble(tuple));
    }
}
