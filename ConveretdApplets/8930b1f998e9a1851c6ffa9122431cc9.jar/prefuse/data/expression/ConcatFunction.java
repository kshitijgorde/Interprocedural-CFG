// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class ConcatFunction extends StringFunction
{
    public ConcatFunction() {
        super(-1);
    }
    
    public String getName() {
        return "CONCAT";
    }
    
    public Object get(final Tuple tuple) {
        final StringBuffer buffer = this.getBuffer();
        for (int i = 0; i < this.paramCount(); ++i) {
            buffer.append(this.param(i).get(tuple).toString());
        }
        return buffer.toString();
    }
}
