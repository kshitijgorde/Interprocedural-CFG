import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class hea
{
    Vector Poa;
    
    public hea() {
        this.Poa = new Vector();
    }
    
    synchronized void b(final transient transient1) {
        boolean b = false;
        for (int i = 0; i < this.Poa.size(); ++i) {
            if (this.Poa.elementAt(i) == transient1) {
                b = true;
                break;
            }
        }
        if (!b) {
            this.Poa.addElement(transient1);
        }
        this._(transient1);
    }
    
    synchronized void _(final transient transient1) {
        for (int i = 0; i < this.Poa.size(); ++i) {
            final transient transient2 = this.Poa.elementAt(i);
            if (transient1 != transient2 && transient1.getState()) {
                transient2.l(false);
            }
        }
    }
}
