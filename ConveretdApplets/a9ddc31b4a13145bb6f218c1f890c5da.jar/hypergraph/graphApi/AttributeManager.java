// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi;

public interface AttributeManager
{
    public static final String GRAPH_ROOT = "GRAPH_ROOT";
    
    Object getAttribute(final String p0, final Element p1);
    
    void setAttribute(final String p0, final Element p1, final Object p2);
}
