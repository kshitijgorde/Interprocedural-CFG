// 
// Decompiled by Procyon v0.5.30
// 

package medusa.graph;

public class UniqueEdge extends BasicEdge
{
    public UniqueEdge(final String n1, final String n2) {
        this.n1 = n1;
        this.n2 = n2;
    }
    
    public UniqueEdge(final Edge edge) {
        this.n1 = edge.n1;
        this.n2 = edge.n2;
    }
    
    public boolean equals(final Object o) {
        return this.sameName(o);
    }
    
    public String report() {
        return super.report();
    }
}
