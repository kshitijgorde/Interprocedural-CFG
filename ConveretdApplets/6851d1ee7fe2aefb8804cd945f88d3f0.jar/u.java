import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class u
{
    String gsa;
    String hsa;
    Vector isa;
    
    public u(final String hsa) {
        this.isa = new Vector();
        this.hsa = hsa;
    }
    
    public u() {
        this.isa = new Vector();
        this.hsa = ",";
    }
    
    public void L(final String hsa) {
        this.hsa = hsa;
    }
    
    public void m(final String gsa) {
        this.gsa = gsa;
        this.isa.removeAllElements();
        int index;
        for (int length = this.gsa.length(), length2 = this.hsa.length(), i = 0; i < length; i = index + length2) {
            index = this.gsa.indexOf(this.hsa, i);
            if (index == -1) {
                this.isa.addElement(this.gsa.substring(i).trim());
                break;
            }
            this.isa.addElement(this.gsa.substring(i, index).trim());
        }
    }
    
    public String K() {
        return this.gsa;
    }
    
    public String b(final int n) {
        if (n >= 0 && n < this.isa.size()) {
            return this.isa.elementAt(n);
        }
        return null;
    }
    
    public String[] h() {
        final String[] array = new String[this.isa.size()];
        for (int i = 0; i < this.isa.size(); ++i) {
            array[i] = (String)this.isa.elementAt(i);
        }
        return array;
    }
    
    public int a() {
        return this.isa.size();
    }
}
