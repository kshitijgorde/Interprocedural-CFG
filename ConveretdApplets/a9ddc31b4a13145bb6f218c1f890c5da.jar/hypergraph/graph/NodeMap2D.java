// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graph;

import java.util.HashMap;
import java.util.Map;

public class NodeMap2D extends AbstractMap2D
{
    public NodeMap2D() {
        this.map = this.createMap();
    }
    
    protected Map createMap() {
        return new HashMap();
    }
}
