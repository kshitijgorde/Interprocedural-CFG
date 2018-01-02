// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class ConcatWsFunction extends StringFunction
{
    public ConcatWsFunction() {
        super(-1);
    }
    
    public String getName() {
        return "CONCAT_WS";
    }
    
    public Object get(final Tuple tuple) {
        final StringBuffer buffer = this.getBuffer();
        final String string = this.param(0).get(tuple).toString();
        for (int i = 1; i < this.paramCount(); ++i) {
            buffer.append(this.param(i).get(tuple).toString());
            buffer.append(string);
        }
        return buffer.toString();
    }
}
