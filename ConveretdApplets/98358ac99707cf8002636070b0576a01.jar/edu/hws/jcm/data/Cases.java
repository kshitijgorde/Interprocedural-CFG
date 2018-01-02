// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

public class Cases
{
    private int[] cases;
    private int caseCt;
    
    public Cases() {
        this.cases = new int[1];
    }
    
    public void clear() {
        this.caseCt = 0;
    }
    
    public void addCase(final int n) {
        if (this.caseCt == this.cases.length) {
            final int[] cases = new int[2 * this.caseCt];
            System.arraycopy(this.cases, 0, cases, 0, this.caseCt);
            this.cases = cases;
        }
        this.cases[this.caseCt++] = n;
    }
    
    public boolean equals(final Cases cases) {
        if (cases.caseCt != this.caseCt) {
            return false;
        }
        for (int i = 0; i < this.caseCt; ++i) {
            if (cases.cases[i] != this.cases[i]) {
                return false;
            }
        }
        return true;
    }
}
