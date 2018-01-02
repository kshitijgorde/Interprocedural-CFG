import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class kea
{
    private Vector z;
    
    public kea() {
        this.z = new Vector();
    }
    
    public boolean b(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("String parameter cannot be null.");
        }
        boolean b = false;
        for (int i = 0; i < this.z.size(); ++i) {
            if (this.z.elementAt(i).equals(s)) {
                b = true;
                break;
            }
        }
        if (b) {
            return false;
        }
        this.z.addElement(s);
        return true;
    }
    
    public void remove(final String s) {
        if (s == null) {
            return;
        }
        for (int i = 0; i < this.z.size(); ++i) {
            if (this.z.elementAt(i).equals(s)) {
                this.z.removeElementAt(i);
                break;
            }
        }
    }
    
    public void removeAll() {
        this.z.removeAllElements();
    }
    
    public String[] _() {
        final String[] array = new String[this.z.size()];
        for (int i = 0; i < this.z.size(); ++i) {
            array[i] = (String)this.z.elementAt(i);
        }
        return array;
    }
    
    public boolean a(final String s) {
        if (s == null) {
            return false;
        }
        for (int i = 0; i < this.z.size(); ++i) {
            if (this.z.elementAt(i).equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public String b() {
        if (this.z.size() == 0) {
            return null;
        }
        final String s = this.z.elementAt(this.z.size() - 1);
        this.z.removeElementAt(this.z.size() - 1);
        return s;
    }
    
    public String d() {
        if (this.z.size() == 0) {
            return null;
        }
        return this.z.elementAt(this.z.size() - 1);
    }
    
    public String e() {
        if (this.z.size() == 0) {
            return null;
        }
        final String s = this.z.elementAt(0);
        this.z.removeElementAt(0);
        return s;
    }
}
