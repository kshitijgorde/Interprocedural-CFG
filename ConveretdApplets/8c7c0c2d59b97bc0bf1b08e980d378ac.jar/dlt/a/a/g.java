// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.a;

public class g
{
    int if;
    int a;
    double[][] do;
    
    public g(final int if1, final int a) {
        this.do = new double[if1 + 1][a + 1];
        this.if = if1;
        this.a = a;
        this.a();
    }
    
    public void a() {
        try {
            for (int i = 0; i < this.if; ++i) {
                for (int j = 0; j < this.a; ++j) {
                    this.do[i][j] = 1.0;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void a(final int if1, final int a) {
        this.do = new double[if1 + 1][a + 1];
        this.if = if1;
        this.a = a;
        this.a();
    }
    
    public boolean a(final int n, final int n2, final double n3) {
        if (n3 < this.do[n][n2]) {
            this.do[n][n2] = n3;
            return true;
        }
        return false;
    }
}
