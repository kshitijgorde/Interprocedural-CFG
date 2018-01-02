// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.util.ColorLib;
import prefuse.data.Tuple;

class HSBFunction extends IntFunction
{
    public HSBFunction() {
        super(3);
    }
    
    public String getName() {
        return "HSB";
    }
    
    public int getInt(final Tuple tuple) {
        return ColorLib.hsb(this.param(0).getFloat(tuple), this.param(1).getFloat(tuple), this.param(2).getFloat(tuple));
    }
}
