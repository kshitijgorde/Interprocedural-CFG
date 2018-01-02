// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class NodeArrayField extends ArrayField
{
    Node[] a;
    
    public void set1Value(final int n, final Node node) throws Shout3DException {
        if (this.a == null || n < 0 || n >= this.a.length) {
            throw new Shout3DException("NodeArrayField.set1Value got out-of-range index");
        }
        this.a[n] = node;
        this.a();
    }
    
    public void setValue(final Node[] a) {
        this.a = a;
        this.a();
    }
    
    public Node[] getValue() {
        return this.a;
    }
    
    public NodeArrayField(final Node node, final String s, final int n, final Node[] a) {
        super(node, s, n);
        this.a = a;
    }
    
    protected void a(final Field field) {
        if (!(field instanceof NodeArrayField)) {
            return;
        }
        ((NodeArrayField)field).setValue(this.a);
    }
    
    public int getLength() {
        return this.a.length;
    }
}
