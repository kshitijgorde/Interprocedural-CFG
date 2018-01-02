import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class ci
{
    private Vector U;
    
    public ci() {
        this.U = new Vector();
    }
    
    public String[] n() {
        if (this.U.size() == 0) {
            return null;
        }
        final String[] array = new String[this.U.size()];
        int n = 0;
        for (int i = 0; i < this.U.size(); ++i) {
            array[n++] = this.U.elementAt(i).toString();
        }
        return array;
    }
    
    public void _(final implements implements1) {
        this.U.addElement(implements1);
    }
    
    public implements _(final String s) {
        if (s == null) {
            return null;
        }
        for (int i = 0; i < this.U.size(); ++i) {
            final implements implements1 = this.U.elementAt(i);
            if (s.equals(implements1.toString())) {
                return implements1;
            }
        }
        return null;
    }
}
