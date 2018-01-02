// 
// Decompiled by Procyon v0.5.30
// 

package c;

class a
{
    int do;
    int int;
    char[] a;
    int for;
    int if;
    
    public a(final int for1) {
        this.int = 0;
        this.for = for1;
        this.if = 0;
        this.a = new char[for1];
        this.do = 0;
    }
    
    public a() {
        this.int = 0;
        this.for = 128;
        this.if = 0;
        this.a = new char[128];
    }
    
    public e a(final int n, final int n2, final int n3) {
        final int n4 = n3 - n2;
        final e e = new e();
        e.a(n >>> n4);
        e.if(n - (e.a() << n4));
        return e;
    }
    
    int if(int n, final int n2, final int n3) {
        n <<= n3;
        n += n2;
        return n;
    }
    
    void a(int n, int i) {
        this.do += i;
        if (this.int == 0) {
            this.a[this.if] = '\0';
        }
        while (i > 0) {
            if (this.int == 8) {
                this.int = 0;
                ++this.if;
                this.a[this.if] = '\0';
            }
            final int n2 = 8 - this.int;
            if (i <= n2) {
                final char[] a = this.a;
                final int if1 = this.if;
                a[if1] |= (char)(n << n2 - i);
                this.int += i;
                i = 0;
            }
            else {
                final char[] a2 = this.a;
                final int if2 = this.if;
                a2[if2] |= (char)(n >>> i - n2);
                this.int = 8;
                n -= n >>> i - n2 << i - n2;
                i -= n2;
            }
        }
    }
    
    public int a(int i) {
        char c = '\0';
        while (i > 0) {
            if (this.int == 8) {
                this.int = 0;
                ++this.if;
            }
            final int n = 8 - this.int;
            if (n >= i) {
                c += (char)((this.a[this.if] << this.int & '\u00ff') >>> 8 - i);
                this.int += i;
                i = 0;
            }
            else {
                if (8 - i > 0) {
                    c += (char)((this.a[this.if] << this.int & '\u00ff') >>> 8 - i);
                    this.int = 8;
                }
                else {
                    c += (char)((this.a[this.if] << this.int & '\u00ff') << i - 8);
                    this.int = 8;
                }
                i -= n;
            }
        }
        return c;
    }
}
