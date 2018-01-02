// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.util.ColorLib;
import prefuse.data.Tuple;

class HSBAFunction extends IntFunction
{
    public HSBAFunction() {
        super(4);
    }
    
    public String getName() {
        return "HSBA";
    }
    
    public int getInt(final Tuple tuple) {
        return ColorLib.hsba(this.param(0).getFloat(tuple), this.param(1).getFloat(tuple), this.param(2).getFloat(tuple), this.param(3).getFloat(tuple));
    }
}
