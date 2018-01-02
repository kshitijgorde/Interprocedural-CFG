// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

import java.util.Vector;

public class Decomp
{
    String pattern;
    boolean mem;
    ReasembList reasemb;
    int currReasmb;
    
    Decomp(final String pattern, final boolean mem, final ReasembList reasemb) {
        this.pattern = pattern;
        this.mem = mem;
        this.reasemb = reasemb;
        this.currReasmb = 100;
    }
    
    public void print(final int n) {
        final String s = this.mem ? "true" : "false";
        for (int i = 0; i < n; ++i) {
            System.out.print(" ");
        }
        System.out.println("decomp: " + this.pattern + " " + s);
        this.reasemb.print(n + 2);
    }
    
    public String pattern() {
        return this.pattern;
    }
    
    public boolean mem() {
        return this.mem;
    }
    
    public String nextRule() {
        if (((Vector)this.reasemb).size() == 0) {
            System.out.println("No reassembly rule.");
            return null;
        }
        return ((Vector<String>)this.reasemb).elementAt(this.currReasmb);
    }
    
    public void stepRule() {
        final int size = ((Vector)this.reasemb).size();
        if (this.mem) {
            this.currReasmb = (int)(Math.random() * size);
        }
        ++this.currReasmb;
        if (this.currReasmb >= size) {
            this.currReasmb = 0;
        }
    }
}
