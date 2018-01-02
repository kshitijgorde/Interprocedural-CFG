// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graph;

import hypergraph.graphApi.Node;
import hypergraph.graphApi.Edge;

public class EdgeImpl extends ElementImpl implements Edge
{
    private Node source;
    private Node target;
    private String label;
    
    protected EdgeImpl(final String s, final Node source, final Node target) {
        super(s);
        this.source = source;
        this.target = target;
        this.label = null;
    }
    
    public Node getSource() {
        return this.source;
    }
    
    public Node getTarget() {
        return this.target;
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public Node getOtherNode(final Node node) {
        if (node == this.source) {
            return this.target;
        }
        if (node == this.target) {
            return this.source;
        }
        return null;
    }
    
    public void reverse() {
        final Node target = this.target;
        this.target = this.source;
        this.source = target;
    }
    
    public int getElementType() {
        return 2;
    }
    
    public String toString() {
        return "[ Edge : \n  name   : " + this.getName() + "\n" + "  source : " + this.source + "\n" + "  target : " + this.target + " ]\n";
    }
}
