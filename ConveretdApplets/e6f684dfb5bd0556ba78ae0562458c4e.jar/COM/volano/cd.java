// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.util.Vector;

public class cd extends Vector
{
    private int pf;
    private int qf;
    
    public cd(final int pf) {
        super(pf + 1);
        this.pf = pf;
        this.addElement("");
    }
    
    public String uc() {
        if (this.qf < this.size() - 1) {
            return this.elementAt(++this.qf);
        }
        return this.elementAt(this.qf);
    }
    
    public String vc() {
        if (this.qf > 0) {
            final int qf = this.qf - 1;
            this.qf = qf;
            return (String)this.elementAt(qf);
        }
        return this.elementAt(this.qf);
    }
    
    public void sc(final String s) {
        if (this.size() == this.pf) {
            this.removeElementAt(this.pf - 1);
        }
        this.insertElementAt(s, 1);
    }
    
    public void tc() {
        this.qf = 0;
    }
}
