import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class hj
{
    private Vector ib;
    
    public hj() {
        this.ib = new Vector();
    }
    
    public boolean a(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("String parameter cannot be null.");
        }
        boolean b = false;
        for (int i = 0; i < this.ib.size(); ++i) {
            if (this.ib.elementAt(i).equals(s)) {
                b = true;
                break;
            }
        }
        if (b) {
            return false;
        }
        this.ib.addElement(s);
        return true;
    }
    
    public void remove(final String s) {
        if (s == null) {
            return;
        }
        for (int i = 0; i < this.ib.size(); ++i) {
            if (this.ib.elementAt(i).equals(s)) {
                this.ib.removeElementAt(i);
                break;
            }
        }
    }
    
    public void removeAll() {
        this.ib.removeAllElements();
    }
    
    public String[] a() {
        final String[] array = new String[this.ib.size()];
        for (int i = 0; i < this.ib.size(); ++i) {
            array[i] = (String)this.ib.elementAt(i);
        }
        return array;
    }
    
    public boolean _(final String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < this.ib.size(); ++i) {
            if (this.ib.elementAt(i).equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public String b() {
        if (this.ib.size() == 0) {
            return null;
        }
        final String s = this.ib.elementAt(this.ib.size() - 1);
        this.ib.removeElementAt(this.ib.size() - 1);
        return s;
    }
    
    public String e() {
        if (this.ib.size() == 0) {
            return null;
        }
        return this.ib.elementAt(this.ib.size() - 1);
    }
    
    public String f() {
        if (this.ib.size() == 0) {
            return null;
        }
        final String s = this.ib.elementAt(0);
        this.ib.removeElementAt(0);
        return s;
    }
}
