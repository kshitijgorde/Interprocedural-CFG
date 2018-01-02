// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.util.ColorLib;
import prefuse.data.Tuple;

class RGBFunction extends IntFunction
{
    public RGBFunction() {
        super(3);
    }
    
    public String getName() {
        return "RGB";
    }
    
    public int getInt(final Tuple tuple) {
        return ColorLib.rgb(this.param(0).getInt(tuple), this.param(1).getInt(tuple), this.param(2).getInt(tuple));
    }
}
