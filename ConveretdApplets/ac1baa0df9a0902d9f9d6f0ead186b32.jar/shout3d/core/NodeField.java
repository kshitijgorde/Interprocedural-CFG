// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class NodeField extends Field
{
    Node a;
    
    public void setValue(final Node a) {
        this.a = a;
        this.a();
    }
    
    public Node getValue() {
        return this.a;
    }
    
    public NodeField(final Node node, final String s, final int n, final Node a) {
        super(node, s, n);
        this.a = a;
    }
    
    protected void a(final Field field) {
        if (!(field instanceof NodeField)) {
            return;
        }
        ((NodeField)field).setValue(this.a);
    }
}
