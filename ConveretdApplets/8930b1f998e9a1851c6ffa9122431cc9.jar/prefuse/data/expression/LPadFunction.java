// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class LPadFunction extends StringFunction
{
    public LPadFunction() {
        super(3);
    }
    
    public String getName() {
        return "LPAD";
    }
    
    public Object get(final Tuple tuple) {
        final String string = this.param(0).get(tuple).toString();
        final int int1 = this.param(1).getInt(tuple);
        final String string2 = this.param(2).get(tuple).toString();
        final int length = string.length();
        if (length > int1) {
            return string.substring(0, int1);
        }
        if (length == int1) {
            return string;
        }
        final StringBuffer buffer = this.getBuffer();
        final int length2 = string2.length();
        final int n = int1 - length;
        for (int i = 0; i < n; i += length2) {
            buffer.append(string2);
        }
        if (buffer.length() > n) {
            buffer.delete(n, buffer.length());
        }
        buffer.append(string);
        return buffer.toString();
    }
}
