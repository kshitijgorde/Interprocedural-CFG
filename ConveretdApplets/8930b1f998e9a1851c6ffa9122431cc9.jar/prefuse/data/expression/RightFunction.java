// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class RightFunction extends StringFunction
{
    public RightFunction() {
        super(2);
    }
    
    public String getName() {
        return "RIGHT";
    }
    
    public Object get(final Tuple tuple) {
        final String string = this.param(0).get(tuple).toString();
        return string.substring(string.length() - this.param(1).getInt(tuple));
    }
}
