// 
// Decompiled by Procyon v0.5.30
// 

package KJEgraph;

public class GraphCatagories extends GraphColumn
{
    public String getGraphType() {
        return "GraphCatagories";
    }
    
    public void sync(final Graph graph) {
        super.sync(graph);
        super._legend._legendType = 2;
    }
}
