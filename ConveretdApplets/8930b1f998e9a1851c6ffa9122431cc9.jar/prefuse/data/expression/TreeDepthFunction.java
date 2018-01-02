// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Node;
import prefuse.data.Tuple;

class TreeDepthFunction extends IntFunction
{
    public TreeDepthFunction() {
        super(0);
    }
    
    public String getName() {
        return "TREEDEPTH";
    }
    
    public int getInt(final Tuple tuple) {
        return (tuple instanceof Node) ? ((Node)tuple).getDepth() : 0;
    }
}
