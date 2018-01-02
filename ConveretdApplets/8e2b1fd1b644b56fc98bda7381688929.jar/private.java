import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class private
{
    private Vector tpa;
    
    public private() {
        this.tpa = new Vector();
    }
    
    public String[] c() {
        if (this.tpa.size() == 0) {
            return null;
        }
        final String[] array = new String[this.tpa.size()];
        int n = 0;
        for (int i = 0; i < this.tpa.size(); ++i) {
            array[n++] = this.tpa.elementAt(i).toString();
        }
        return array;
    }
    
    public void b(final public public1) {
        this.tpa.addElement(public1);
    }
    
    public public a(final String s) {
        if (s == null) {
            return null;
        }
        for (int i = 0; i < this.tpa.size(); ++i) {
            final public public1 = this.tpa.elementAt(i);
            if (s.equals(public1.toString())) {
                return public1;
            }
        }
        return null;
    }
}
