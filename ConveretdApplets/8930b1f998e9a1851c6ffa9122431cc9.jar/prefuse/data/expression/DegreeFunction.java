// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Node;
import prefuse.data.Tuple;

class DegreeFunction extends IntFunction
{
    public DegreeFunction() {
        super(0);
    }
    
    public String getName() {
        return "DEGREE";
    }
    
    public int getInt(final Tuple tuple) {
        return (tuple instanceof Node) ? ((Node)tuple).getDegree() : 0;
    }
}
