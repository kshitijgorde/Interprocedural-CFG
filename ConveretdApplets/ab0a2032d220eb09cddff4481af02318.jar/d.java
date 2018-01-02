import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class d
{
    String cJb;
    String dJb;
    Vector eJb;
    
    public d(final String dJb) {
        this.eJb = new Vector();
        this.dJb = dJb;
    }
    
    public d() {
        this.eJb = new Vector();
        this.dJb = ",";
    }
    
    public void F(final String dJb) {
        this.dJb = dJb;
    }
    
    public void e(final String cJb) {
        this.cJb = cJb;
        this.eJb.removeAllElements();
        int index;
        for (int length = this.cJb.length(), length2 = this.dJb.length(), i = 0; i < length; i = index + length2) {
            index = this.cJb.indexOf(this.dJb, i);
            if (index == -1) {
                this.eJb.addElement(this.cJb.substring(i).trim());
                break;
            }
            this.eJb.addElement(this.cJb.substring(i, index).trim());
        }
    }
    
    public String f() {
        return this.cJb;
    }
    
    public String _(final int n) {
        if (n >= 0 && n < this.eJb.size()) {
            return this.eJb.elementAt(n);
        }
        return null;
    }
    
    public String[] n() {
        final String[] array = new String[this.eJb.size()];
        for (int i = 0; i < this.eJb.size(); ++i) {
            array[i] = (String)this.eJb.elementAt(i);
        }
        return array;
    }
    
    public int z() {
        return this.eJb.size();
    }
}
