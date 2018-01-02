import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class m
{
    private Vector KIb;
    
    public m() {
        this.KIb = new Vector();
    }
    
    public String[] m() {
        if (this.KIb.size() == 0) {
            return null;
        }
        final String[] array = new String[this.KIb.size()];
        int n = 0;
        for (int i = 0; i < this.KIb.size(); ++i) {
            array[n++] = this.KIb.elementAt(i).toString();
        }
        return array;
    }
    
    public void b(final o o) {
        this.KIb.addElement(o);
    }
    
    public o _(final String s) {
        if (s == null) {
            return null;
        }
        for (int i = 0; i < this.KIb.size(); ++i) {
            final o o = this.KIb.elementAt(i);
            if (o.toString() == s) {
                return o;
            }
        }
        return null;
    }
}
