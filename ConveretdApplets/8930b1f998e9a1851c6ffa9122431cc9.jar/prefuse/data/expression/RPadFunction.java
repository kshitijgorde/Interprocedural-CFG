// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class RPadFunction extends StringFunction
{
    public RPadFunction() {
        super(3);
    }
    
    public String getName() {
        return "RPAD";
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
        buffer.append(string);
        for (int length2 = string2.length(), n = int1 - length, i = 0; i < n; i += length2) {
            buffer.append(string2);
        }
        if (buffer.length() > int1) {
            buffer.delete(int1, buffer.length());
        }
        return buffer.toString();
    }
}
