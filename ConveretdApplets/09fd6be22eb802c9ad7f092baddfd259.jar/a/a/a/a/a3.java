// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class a3
{
    public static final int for = 1;
    public static final int if = 2;
    public static final int void = 3;
    static final int try = 4;
    static final int new = 5;
    static final int byte = 31;
    public int goto;
    public char[] do;
    public int char;
    public boolean long;
    public long case;
    public double else;
    public char[] int;
    public a3[] a;
    
    public a3() {
        this.goto = 0;
        this.char = 1;
        this.long = false;
        this.do = new char[31];
    }
    
    public boolean a(final int n) {
        if (this.int == null || this.int.length - 2 < n) {
            final char[] int1 = new char[n + 128];
            int length = 0;
            if (this.int != null) {
                length = this.int.length;
            }
            for (int i = 0; i < length; ++i) {
                int1[i] = this.int[i];
            }
            for (int j = length; j < n + 128; ++j) {
                int1[j] = '\0';
            }
            this.int = int1;
        }
        return false;
    }
    
    public void a() {
        this.do = null;
        this.int = null;
        if (this.a != null) {
            for (int i = 0; i < this.a.length; ++i) {
                if (this.a[i] != null) {
                    this.a[i].a();
                    this.a[i] = null;
                }
            }
        }
        this.a = null;
    }
}
