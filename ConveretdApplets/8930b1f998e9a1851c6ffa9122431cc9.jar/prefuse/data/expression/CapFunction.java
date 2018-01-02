// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.util.StringLib;
import prefuse.data.Tuple;

class CapFunction extends StringFunction
{
    public CapFunction() {
        super(1);
    }
    
    public String getName() {
        return "CAP";
    }
    
    public Object get(final Tuple tuple) {
        return StringLib.capitalizeFirstOnly(this.param(0).get(tuple).toString());
    }
}
