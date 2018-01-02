// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.graph;

import hypergraph.graphApi.Node;

public class NodeImpl extends ElementImpl implements Node
{
    private String label;
    
    NodeImpl(final String s) {
        super(s);
    }
    
    public String getLabel() {
        if (this.label != null) {
            return this.label;
        }
        return this.getName();
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    public int getElementType() {
        return 1;
    }
    
    public Object clone() {
        final NodeImpl nodeImpl = new NodeImpl(this.getName());
        nodeImpl.setGroup(this.getGroup());
        nodeImpl.setLabel(this.getLabel());
        return nodeImpl;
    }
    
    public String toString() {
        return "[Node : " + this.getName() + " ]";
    }
}
