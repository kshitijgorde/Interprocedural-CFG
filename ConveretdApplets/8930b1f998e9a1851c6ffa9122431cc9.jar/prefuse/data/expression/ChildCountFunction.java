// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Node;
import prefuse.data.Tuple;

class ChildCountFunction extends IntFunction
{
    public ChildCountFunction() {
        super(0);
    }
    
    public String getName() {
        return "CHILDCOUNT";
    }
    
    public int getInt(final Tuple tuple) {
        return (tuple instanceof Node) ? ((Node)tuple).getChildCount() : 0;
    }
}
