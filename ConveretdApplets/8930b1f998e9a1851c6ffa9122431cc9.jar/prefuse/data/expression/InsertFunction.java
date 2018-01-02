// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class InsertFunction extends StringFunction
{
    public InsertFunction() {
        super(4);
    }
    
    public String getName() {
        return "INSERT";
    }
    
    public Object get(final Tuple tuple) {
        final String string = this.param(0).get(tuple).toString();
        final int length = string.length();
        final int int1 = this.param(1).getInt(tuple);
        final int n = int1 + this.param(2).getInt(tuple);
        final String string2 = this.param(1).get(tuple).toString();
        if (int1 < 0 || int1 > length) {
            return string;
        }
        if (n < 0 || n > length) {
            return string.substring(0, int1) + string2;
        }
        return string.substring(0, int1) + string2 + string.substring(n);
    }
}
