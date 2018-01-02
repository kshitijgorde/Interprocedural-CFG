// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Tuple;

class RowFunction extends IntFunction
{
    public RowFunction() {
        super(0);
    }
    
    public String getName() {
        return "ROW";
    }
    
    public int getInt(final Tuple tuple) {
        return tuple.getRow();
    }
}
