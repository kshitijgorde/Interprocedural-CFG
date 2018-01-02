// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.util.ColorLib;
import prefuse.data.Tuple;

class HexFunction extends IntFunction
{
    public HexFunction() {
        super(1);
    }
    
    public String getName() {
        return "RGB";
    }
    
    public int getInt(final Tuple tuple) {
        return ColorLib.hex((String)this.param(0).get(tuple));
    }
}
