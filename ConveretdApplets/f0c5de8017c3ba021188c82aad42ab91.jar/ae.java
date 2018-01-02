import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class ae extends ad
{
    private Hashtable a;
    private Vector b;
    
    public ae() {
        this.a = new Hashtable();
        this.b = new Vector();
    }
    
    public ae(final i i, final i j) {
        this.a = new Hashtable();
        this.b = new Vector();
        this.a(i);
        this.a(j);
    }
    
    public final void append(final f f, final h h, final String s, final Throwable t) {
        synchronized (this.b) {
            for (int i = 0; i < this.b.size(); ++i) {
                ((i)this.b.elementAt(i)).append(f, h, s, t);
            }
        }
    }
    
    public final void a(final i i) {
        if (!this.b(i)) {
            this.b.addElement(i);
        }
    }
    
    private boolean b(final i i) {
        if (this.a.containsKey(i)) {
            if (f.e.i()) {
                f.e.g(this.toString() + ": addAppender: Appender " + i + " already contained");
            }
            return true;
        }
        return false;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(this.getClass().getName() + "[ ");
        this.b.trimToSize();
        for (int i = 0; i < this.b.size() - 1; ++i) {
            sb.append(this.b.elementAt(i) + "; ");
        }
        sb.append(this.b.elementAt(this.b.size() - 1));
        sb.append(" ]");
        return sb.toString();
    }
}
