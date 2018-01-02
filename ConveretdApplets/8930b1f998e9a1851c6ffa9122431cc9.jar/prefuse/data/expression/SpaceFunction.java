// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class SpaceFunction extends StringFunction
{
    public SpaceFunction() {
        super(1);
    }
    
    public String getName() {
        return "SPACE";
    }
    
    public Object get(final Tuple tuple) {
        final int int1 = this.param(0).getInt(tuple);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < int1; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }
}
