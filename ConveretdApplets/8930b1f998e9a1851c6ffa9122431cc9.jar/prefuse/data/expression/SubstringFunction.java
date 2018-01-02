// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class SubstringFunction extends StringFunction
{
    public SubstringFunction() {
        super(3);
    }
    
    public String getName() {
        return "SUBSTRING";
    }
    
    public Object get(final Tuple tuple) {
        final String string = this.param(0).get(tuple).toString();
        final int int1 = this.param(1).getInt(tuple);
        if (this.paramCount() == 3) {
            return string.substring(int1, int1 + this.param(2).getInt(tuple));
        }
        return string.substring(int1);
    }
}
