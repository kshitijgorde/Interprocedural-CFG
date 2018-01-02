import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class de
{
    private Vector a;
    private static er b;
    
    public de() {
        this.a = new Vector();
    }
    
    public final Object a(final int n) {
        final Object element = this.a.elementAt(n);
        if (element == de.b) {
            return null;
        }
        return element;
    }
    
    public final void b(final int n) {
        for (int i = this.a.size(); i <= n; ++i) {
            this.a.addElement(de.b);
        }
    }
    
    public final boolean c(final int n) {
        return this.a.size() > n && this.a(n) != null;
    }
    
    public final synchronized void a(final Object o, final int n) {
        for (int i = this.a.size(); i < n; ++i) {
            this.a.addElement(de.b);
        }
        this.a.insertElementAt(o, n);
    }
    
    public final void b(final Object o, final int n) {
        this.b(n + 1);
        this.a.setElementAt(o, n);
    }
    
    public final void d(final int n) {
        this.a.removeElementAt(n);
    }
    
    public final int a() {
        return this.a.size();
    }
    
    public String toString() {
        return this.a.toString();
    }
    
    static {
        de.b = new Object() {
            public String toString() {
                return "null";
            }
        };
    }
}
