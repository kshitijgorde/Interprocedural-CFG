// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.util.ColorLib;
import prefuse.data.Tuple;

class RGBAFunction extends IntFunction
{
    public RGBAFunction() {
        super(4);
    }
    
    public String getName() {
        return "RGBA";
    }
    
    public int getInt(final Tuple tuple) {
        return ColorLib.rgba(this.param(0).getInt(tuple), this.param(1).getInt(tuple), this.param(2).getInt(tuple), this.param(3).getInt(tuple));
    }
}
