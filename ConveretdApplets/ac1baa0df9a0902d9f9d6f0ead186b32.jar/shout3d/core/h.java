// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.util.Enumeration;

public class h implements Searcher
{
    private boolean a;
    private Node b;
    private String c;
    private String d;
    private Node[][] e;
    private Node[] f;
    private int g;
    
    public Node[] searchFirst(final Node node) {
        this.f = null;
        this.e = null;
        this.a = false;
        this.a(node, true);
        if (this.e == null) {
            return null;
        }
        return this.e[0];
    }
    
    private boolean a(final Node node) {
        if (this.c(node)) {
            final Node[] b = this.b(node);
            Node[][] e;
            if (this.e == null) {
                e = new Node[][] { null };
            }
            else {
                e = new Node[this.e.length + 1][];
                System.arraycopy(this.e, 0, e, 0, this.e.length);
            }
            e[e.length - 1] = b;
            this.e = e;
            return true;
        }
        return false;
    }
    
    private Node[] b(final Node node) {
        final Node[] array = new Node[this.g + 1];
        if (this.f != null) {
            System.arraycopy(this.f, 0, array, 0, this.g);
        }
        array[array.length - 1] = node;
        return array;
    }
    
    public void setNode(final Node b) {
        this.b = b;
    }
    
    public h() {
        this.a = false;
    }
    
    private boolean c(final Node node) {
        return node != null && (this.b == null || this.b == node) && (this.d == null || this.d.equals(node.getDEFName())) && (this.c == null || node.isOfType(this.c));
    }
    
    public void setDefName(final String d) {
        this.d = d;
    }
    
    public Node[][] searchAll(final Node node) {
        this.f = null;
        this.e = null;
        this.a(node, this.a = true);
        return this.e;
    }
    
    private void a() {
        if (this.g > 0) {
            --this.g;
        }
    }
    
    private boolean a(final Node node, final boolean b) {
        boolean b2 = false;
        if (this.a(node)) {
            b2 = true;
        }
        if (b2 && !this.a) {
            return true;
        }
        if (!b) {
            return b2;
        }
        this.d(node);
        final Enumeration<Object> keys = node.a.keys();
        for (int i = 0; i < node.getNumFields(); ++i) {
            final Field field = node.a.get(keys.nextElement());
            if (field instanceof NodeArrayField) {
                final NodeArrayField nodeArrayField = (NodeArrayField)field;
                if (nodeArrayField.a != null) {
                    final boolean b3 = node instanceof Group && nodeArrayField == ((Group)node).children;
                    for (int j = 0; j < nodeArrayField.a.length; ++j) {
                        if (nodeArrayField.a[j] != null && this.a(nodeArrayField.a[j], b3)) {
                            b2 = true;
                            if (!this.a) {
                                break;
                            }
                        }
                    }
                }
            }
            else if (field instanceof NodeField) {
                final NodeField nodeField = (NodeField)field;
                if (nodeField.a != null && this.a(nodeField.a, true)) {
                    b2 = true;
                }
            }
            if (b2 && !this.a) {
                break;
            }
        }
        this.a();
        return b2;
    }
    
    public void setType(final String c) {
        this.c = c;
    }
    
    private void d(final Node node) {
        if (this.f == null) {
            this.g = 0;
            this.f = new Node[10];
        }
        else if (this.g == this.f.length) {
            final Node[] f = new Node[this.f.length + 10];
            System.arraycopy(this.f, 0, f, 0, this.f.length);
            this.f = f;
        }
        this.f[this.g] = node;
        ++this.g;
    }
}
