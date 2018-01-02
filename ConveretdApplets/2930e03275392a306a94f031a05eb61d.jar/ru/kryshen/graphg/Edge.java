// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

class Edge
{
    Node node1;
    Node node2;
    boolean connected;
    boolean selected;
    int value;
    boolean enabled;
    boolean marked;
    private float k;
    private float b;
    
    public boolean isVertical() {
        return this.node1.x == this.node2.x;
    }
    
    public int getVectorX() {
        return this.node2.x - this.node1.x;
    }
    
    public int getVectorY() {
        return this.node2.y - this.node1.y;
    }
    
    public float getK() {
        if (this.k != this.k) {
            if (this.isVertical()) {
                this.k = -1.0f;
            }
            else {
                this.k = this.getVectorY() / this.getVectorX();
            }
        }
        return this.k;
    }
    
    public float getB() {
        if (this.b != this.b) {
            if (this.isVertical()) {
                this.b = this.node1.x;
            }
            else {
                this.b = this.node1.y - this.node1.x * this.getK();
            }
        }
        return this.b;
    }
    
    public boolean crosses(final Edge edge) {
        if (edge.isVertical()) {
            return !this.isVertical() && edge.crosses(this);
        }
        float n;
        if (this.isVertical()) {
            n = this.node1.x;
        }
        else {
            n = (edge.getB() - this.getB()) / (this.getK() - edge.getK());
        }
        final float n2 = edge.getK() * n + edge.getB();
        return n >= Math.min(this.node1.x, this.node2.x) & n <= Math.max(this.node1.x, this.node2.x) & n >= Math.min(edge.node1.x, edge.node2.x) & n <= Math.max(edge.node1.x, edge.node2.x) & n2 >= Math.min(this.node1.y, this.node2.y) & n2 <= Math.max(this.node1.y, this.node2.y) & n2 >= Math.min(edge.node1.y, edge.node2.y) & n2 <= Math.max(edge.node1.y, edge.node2.y);
    }
    
    Edge(final Node node1, final Node node2) {
        this.connected = false;
        this.selected = false;
        this.value = 0;
        this.enabled = true;
        this.marked = false;
        this.k = Float.NaN;
        this.b = Float.NaN;
        this.node1 = node1;
        this.node2 = node2;
    }
    
    public void swapNodes() {
        final Node node1 = this.node1;
        this.node1 = this.node2;
        this.node2 = node1;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof Edge)) {
            return false;
        }
        final Edge edge = (Edge)o;
        return (this.node1.equals(edge.node1) && this.node2.equals(edge.node2)) || (this.node1.equals(edge.node2) && this.node2.equals(edge.node1));
    }
    
    public String toString() {
        return "(" + this.node1.x + "; " + this.node1.y + ") - (" + this.node2.x + "; " + this.node2.y + ")";
    }
    
    Node getAnotherNode(final Node node) {
        if (node.equals(this.node1)) {
            return this.node2;
        }
        return this.node1;
    }
    
    MultiNode getAnotherNode(final MultiNode multiNode) {
        if (multiNode.equals(this.node1.multiNode)) {
            return this.node2.multiNode;
        }
        return this.node1.multiNode;
    }
    
    MultiNode incidentNode(final Edge edge) {
        if (this.node1.multiNode == edge.node1.multiNode || this.node1.multiNode == edge.node2.multiNode) {
            return this.node1.multiNode;
        }
        if (this.node2.multiNode == edge.node1.multiNode || this.node2.multiNode == edge.node2.multiNode) {
            return this.node2.multiNode;
        }
        return null;
    }
    
    void connect() {
        if (this.connected) {
            return;
        }
        this.connected = true;
        this.node1.connectedEdges.addElement(this);
        this.node2.connectedEdges.addElement(this);
        this.node1.multiNode.connect(this.node2.multiNode);
    }
}
