// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graphApi;

public class GraphException extends Exception
{
    private Exception wrapped;
    
    public GraphException(final String s) {
        super(s);
    }
    
    public GraphException(final String s, final Exception wrapped) {
        super(s);
        this.wrapped = wrapped;
    }
}
