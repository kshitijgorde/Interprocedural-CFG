// 
// Decompiled by Procyon v0.5.30
// 

public class a7
{
    int[] a;
    int b;
    
    public a7(final int n) {
        this.a = new int[n * 9];
        this.b = 0;
    }
    
    public void a(final int n, final int n2, final int n3) {
        final int n4 = 9 * this.b;
        this.a[n4] = n * 3;
        this.a[n4 + 1] = n2 * 3;
        this.a[n4 + 2] = n3 * 3;
        ++this.b;
    }
    
    void a(final a9 a9) {
        int n = 0;
        while (true) {
            Label_0115: {
                if (!c.l) {
                    break Label_0115;
                }
                final c9 c9 = a9.e[n];
                final int g = c9.g;
                final int a10 = a2.a(c9.g + 1);
                final int a11 = a2.a(c9.g + 2);
                final int n2 = 9 * a9.c[n];
                this.a[n2 + 6] = (n * 3 + g) * 2;
                this.a[n2 + 7] = (n * 3 + a10) * 2;
                this.a[n2 + 8] = (n * 3 + a11) * 2;
                ++n;
            }
            if (n >= a9.b) {
                return;
            }
            continue;
        }
    }
    
    void b(final a9 a9) {
        int n = 0;
        while (true) {
            Label_0120: {
                if (!c.l) {
                    break Label_0120;
                }
                final int n2 = a9.c[n];
                final c9 c9 = a9.e[n];
                final int g = c9.g;
                final int a10 = a2.a(c9.g + 1);
                final int a11 = a2.a(c9.g + 2);
                final int n3 = 9 * n2;
                this.a[n3 + 3] = c9.c[g] * 2;
                this.a[n3 + 4] = c9.c[a10] * 2;
                this.a[n3 + 5] = c9.c[a11] * 2;
                ++n;
            }
            if (n >= a9.b) {
                return;
            }
            continue;
        }
    }
    
    void a() {
        this.a = null;
    }
}
