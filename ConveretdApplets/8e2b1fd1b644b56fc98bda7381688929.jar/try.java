import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class try
{
    String wwa;
    String xwa;
    Vector ywa;
    
    public try(final String xwa) {
        this.ywa = new Vector();
        this.xwa = xwa;
    }
    
    public try() {
        this.ywa = new Vector();
        this.xwa = ",";
    }
    
    public void H(final String xwa) {
        this.xwa = xwa;
    }
    
    public void l(final String wwa) {
        this.wwa = wwa;
        this.ywa.removeAllElements();
        int index;
        for (int length = this.wwa.length(), length2 = this.xwa.length(), i = 0; i < length; i = index + length2) {
            index = this.wwa.indexOf(this.xwa, i);
            if (index == -1) {
                this.ywa.addElement(this.wwa.substring(i).trim());
                break;
            }
            this.ywa.addElement(this.wwa.substring(i, index).trim());
        }
    }
    
    public String E() {
        return this.wwa;
    }
    
    public String a(final int n) {
        if (n >= 0 && n < this.ywa.size()) {
            return this.ywa.elementAt(n);
        }
        return null;
    }
    
    public String[] e() {
        final String[] array = new String[this.ywa.size()];
        for (int i = 0; i < this.ywa.size(); ++i) {
            array[i] = (String)this.ywa.elementAt(i);
        }
        return array;
    }
    
    public int g() {
        return this.ywa.size();
    }
}
