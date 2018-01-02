// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class ReverseFunction extends StringFunction
{
    public ReverseFunction() {
        super(1);
    }
    
    public String getName() {
        return "REVERSE";
    }
    
    public Object get(final Tuple tuple) {
        final String string = this.param(0).get(tuple).toString();
        final StringBuffer buffer = this.getBuffer();
        int n = string.length() - 1;
        while (--n >= 0) {
            buffer.append(string.charAt(n));
        }
        return buffer.toString();
    }
}
