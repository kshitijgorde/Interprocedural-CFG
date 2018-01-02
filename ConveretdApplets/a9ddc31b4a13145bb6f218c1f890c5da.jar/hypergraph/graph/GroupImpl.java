// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graph;

import hypergraph.graphApi.Group;

public class GroupImpl extends ElementImpl implements Group
{
    protected GroupImpl(final String s) {
        super(s);
    }
    
    public int getElementType() {
        return 3;
    }
}
