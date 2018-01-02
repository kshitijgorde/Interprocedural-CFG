import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class hp
{
    private Vector e;
    
    public hp() {
        this.e = new Vector();
    }
    
    public boolean b(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("String parameter cannot be null.");
        }
        boolean b = false;
        for (int i = 0; i < this.e.size(); ++i) {
            if (this.e.elementAt(i).equals(s)) {
                b = true;
                break;
            }
        }
        if (b) {
            return false;
        }
        this.e.addElement(s);
        return true;
    }
    
    public void remove(final String s) {
        if (s == null) {
            return;
        }
        for (int i = 0; i < this.e.size(); ++i) {
            if (this.e.elementAt(i).equals(s)) {
                this.e.removeElementAt(i);
                break;
            }
        }
    }
    
    public void removeAll() {
        this.e.removeAllElements();
    }
    
    public String[] b() {
        final String[] array = new String[this.e.size()];
        for (int i = 0; i < this.e.size(); ++i) {
            array[i] = (String)this.e.elementAt(i);
        }
        return array;
    }
    
    public boolean a(final String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < this.e.size(); ++i) {
            if (this.e.elementAt(i).equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public String _() {
        if (this.e.size() == 0) {
            return null;
        }
        final String s = this.e.elementAt(this.e.size() - 1);
        this.e.removeElementAt(this.e.size() - 1);
        return s;
    }
    
    public String m() {
        if (this.e.size() == 0) {
            return null;
        }
        return this.e.elementAt(this.e.size() - 1);
    }
    
    public String n() {
        if (this.e.size() == 0) {
            return null;
        }
        final String s = this.e.elementAt(0);
        this.e.removeElementAt(0);
        return s;
    }
}
