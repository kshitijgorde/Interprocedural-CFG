// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class n implements ay
{
    ap char;
    ap goto;
    int else;
    int case;
    
    public n() {
        this.char = null;
        this.goto = null;
        this.else = 1;
        this.case = 0;
    }
    
    public void a(final ap goto1, final ap char1) {
        this.goto = goto1;
        this.char = char1;
        this.case = (int)(14.0 * Math.random());
        if (this.case == 14) {
            this.case = 13;
        }
    }
    
    public void a(final ap ap, int n, int n2, final int n3, final int n4) {
        final int n5 = n4 * this.else / 25;
        final int n6 = n3 * this.else / 25;
        switch (this.case) {
            case 0: {
                a3.a(this.goto, ap, n, n2, n3, n4);
                a3.a(this.char, ap, n, n2, n6, n5);
                break;
            }
            case 1: {
                a3.a(this.goto, ap, n, n2, n3, n4);
                a3.a(this.char, ap, n, n2 + n4 - n5, n6, n5);
                break;
            }
            case 2: {
                a3.a(this.goto, ap, n, n2, n3, n4);
                a3.a(this.char, ap, n + n3 - n6, n2, n6, n5);
                break;
            }
            case 3: {
                a3.a(this.goto, ap, n, n2, n3, n4);
                a3.a(this.char, ap, n + n3 - n6, n2 + n4 - n5, n6, n5);
                break;
            }
            case 4: {
                final int n7 = n4 * (25 - this.else) / 25;
                final int n8 = n3 * (25 - this.else) / 25;
                a3.a(this.char, ap, n, n2, n3, n4);
                a3.a(this.goto, ap, n, n2, n8, n7);
                break;
            }
            case 5: {
                final int n9 = n4 * (25 - this.else) / 25;
                final int n10 = n3 * (25 - this.else) / 25;
                a3.a(this.char, ap, n, n2, n3, n4);
                a3.a(this.goto, ap, n, n2 + n4 - n9, n10, n9);
                break;
            }
            case 6: {
                final int n11 = n4 * (25 - this.else) / 25;
                final int n12 = n3 * (25 - this.else) / 25;
                a3.a(this.char, ap, n, n2, n3, n4);
                a3.a(this.goto, ap, n + n3 - n12, n2, n12, n11);
                break;
            }
            case 7: {
                final int n13 = n4 * (25 - this.else) / 25;
                final int n14 = n3 * (25 - this.else) / 25;
                a3.a(this.char, ap, n, n2, n3, n4);
                a3.a(this.goto, ap, n + n3 - n14, n2 + n4 - n13, n14, n13);
                break;
            }
            case 8: {
                a3.a(this.goto, ap, n + n6, n2, n3 - n6, n4);
                a3.a(this.char, ap, n, n2, n6, n4);
                break;
            }
            case 9: {
                a3.a(this.goto, ap, n, n2, n3 - n6, n4);
                a3.a(this.char, ap, n + n3 - n6, n2, n6, n4);
                break;
            }
            case 10: {
                a3.a(this.goto, ap, n, n2 + n5, n3, n4 - n5);
                a3.a(this.char, ap, n, n2, n3, n5);
                break;
            }
            case 11: {
                a3.a(this.goto, ap, n, n2, n3, n4 - n5);
                a3.a(this.char, ap, n, n2 + n4 - n5, n3, n5);
                break;
            }
            case 12: {
                a3.a(this.goto, ap, n, n2, n3, n4);
                n += n3 - n6 >> 1;
                n2 += n4 - n5 >> 1;
                a3.a(this.char, ap, n, n2, n6, n5);
                break;
            }
            case 13: {
                a3.a(this.char, ap, n, n2, n3, n4);
                final int n15 = n4 * (25 - this.else) / 25;
                final int n16 = n3 * (25 - this.else) / 25;
                n += n3 - n16 >> 1;
                n2 += n4 - n15 >> 1;
                a3.a(this.goto, ap, n, n2, n16, n15);
                break;
            }
        }
        ++this.else;
    }
    
    public boolean a(final long n) {
        return this.else < 25;
    }
}
