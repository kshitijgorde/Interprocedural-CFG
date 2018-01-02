import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class dj
{
    Vector f;
    
    public dj() {
        this.f = new Vector();
    }
    
    synchronized void b(final t t) {
        boolean b = false;
        for (int i = 0; i < this.f.size(); ++i) {
            if (this.f.elementAt(i) == t) {
                b = true;
                break;
            }
        }
        if (!b) {
            this.f.addElement(t);
        }
        this._(t);
    }
    
    synchronized void _(final t t) {
        for (int i = 0; i < this.f.size(); ++i) {
            final t t2 = this.f.elementAt(i);
            if (t != t2 && t.getState()) {
                t2.f(false);
            }
        }
    }
}
