// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi;

public interface Element
{
    public static final int GRAPH_ELEMENT = 0;
    public static final int NODE_ELEMENT = 1;
    public static final int EDGE_ELEMENT = 2;
    public static final int GROUP_ELEMENT = 3;
    
    Group getGroup();
    
    void setGroup(final Group p0);
    
    String getName();
    
    int getElementType();
}
