// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class RepeatFunction extends StringFunction
{
    public RepeatFunction() {
        super(2);
    }
    
    public String getName() {
        return "REPEAT";
    }
    
    public Object get(final Tuple tuple) {
        final String string = this.param(0).get(tuple).toString();
        final int int1 = this.param(1).getInt(tuple);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < int1; ++i) {
            sb.append(string);
        }
        return sb.toString();
    }
}
