// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Node;
import prefuse.data.Tuple;

class IsNodeFunction extends BooleanFunction
{
    public IsNodeFunction() {
        super(0);
    }
    
    public String getName() {
        return "ISNODE";
    }
    
    public boolean getBoolean(final Tuple tuple) {
        return tuple instanceof Node;
    }
}
