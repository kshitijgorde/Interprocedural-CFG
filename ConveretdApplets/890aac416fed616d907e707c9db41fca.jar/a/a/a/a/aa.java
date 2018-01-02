// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class aa implements ay
{
    ap e;
    ap g;
    int f;
    int d;
    
    public aa() {
        this.e = null;
        this.g = null;
        this.f = 1;
        this.d = 0;
    }
    
    public void a(final ap g, final ap e) {
        this.g = g;
        this.e = e;
        this.d = (int)(12.0 * Math.random());
        if (this.d == 12) {
            this.d = 11;
        }
    }
    
    public void a(final ap ap, final int n, final int n2, final int n3, final int n4) {
        final int n5 = n4 * this.f / 25;
        final int n6 = n3 * this.f / 25;
        switch (this.d) {
            case 0: {
                a3.a(this.g, ap, n, n2, n3, n4);
                a3.a(this.e, ap, n - n3 + n6, n2 - n4 + n5, n3, n4);
                break;
            }
            case 1: {
                a3.a(this.g, ap, n, n2, n3, n4);
                a3.a(this.e, ap, n - n3 + n6, n2 + n4 - n5, n3, n4);
                break;
            }
            case 2: {
                a3.a(this.g, ap, n, n2, n3, n4);
                a3.a(this.e, ap, n + n3 - n6, n2 - n4 + n5, n3, n4);
                break;
            }
            case 3: {
                a3.a(this.g, ap, n, n2, n3, n4);
                a3.a(this.e, ap, n + n3 - n6, n2 + n4 - n5, n3, n4);
                break;
            }
            case 4: {
                final int n7 = n4 * (25 - this.f) / 25;
                final int n8 = n3 * (25 - this.f) / 25;
                a3.a(this.e, ap, n, n2, n3, n4);
                a3.a(this.g, ap, n - n3 + n8, n2 - n4 + n7, n3, n4);
                break;
            }
            case 5: {
                final int n9 = n4 * (25 - this.f) / 25;
                final int n10 = n3 * (25 - this.f) / 25;
                a3.a(this.e, ap, n, n2, n3, n4);
                a3.a(this.g, ap, n - n3 + n10, n2 + n4 - n9, n3, n4);
                break;
            }
            case 6: {
                final int n11 = n4 * (25 - this.f) / 25;
                final int n12 = n3 * (25 - this.f) / 25;
                a3.a(this.e, ap, n, n2, n3, n4);
                a3.a(this.g, ap, n + n3 - n12, n2 - n4 + n11, n3, n4);
                break;
            }
            case 7: {
                final int n13 = n4 * (25 - this.f) / 25;
                final int n14 = n3 * (25 - this.f) / 25;
                a3.a(this.e, ap, n, n2, n3, n4);
                a3.a(this.g, ap, n + n3 - n14, n2 + n4 - n13, n3, n4);
                break;
            }
            case 8: {
                a3.a(this.g, ap, n, n2, n3, n4);
                a3.a(this.e, ap, n - n3 + n6, n2, n3, n4);
                break;
            }
            case 9: {
                a3.a(this.g, ap, n, n2, n3, n4);
                a3.a(this.e, ap, n + n3 - n6, n2, n3, n4);
                break;
            }
            case 10: {
                a3.a(this.g, ap, n, n2, n3, n4);
                a3.a(this.e, ap, n, n2 - n4 + n5, n3, n4);
                break;
            }
            case 11: {
                a3.a(this.g, ap, n, n2, n3, n4);
                a3.a(this.e, ap, n, n2 + n4 - n5, n3, n4);
                break;
            }
        }
        ++this.f;
    }
    
    public boolean a(final long n) {
        return this.f < 25;
    }
}
