import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class Uo
{
    Vector lIb;
    
    public Uo() {
        this.lIb = new Vector();
    }
    
    synchronized void b(final c c) {
        boolean b = false;
        for (int i = 0; i < this.lIb.size(); ++i) {
            if (this.lIb.elementAt(i) == c) {
                b = true;
                break;
            }
        }
        if (!b) {
            this.lIb.addElement(c);
        }
        this._(c);
    }
    
    synchronized void _(final c c) {
        for (int i = 0; i < this.lIb.size(); ++i) {
            final c c2 = this.lIb.elementAt(i);
            if (c != c2 && c.getState()) {
                c2.F(false);
            }
        }
    }
}
