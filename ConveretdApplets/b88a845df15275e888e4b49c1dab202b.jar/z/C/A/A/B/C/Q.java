// 
// Decompiled by Procyon v0.5.30
// 

package z.C.A.A.B.C;

import z.C.A.A.B.A.M;
import z.C.A.A.B.A.B;
import java.io.IOException;
import z.C.A.A.B.A.G;
import z.C.A.A.B.A.N;

public final class Q implements N
{
    private int C;
    private L B;
    private O G;
    private O A;
    private C F;
    private int[] D;
    private int E;
    
    public Q() {
        this.B = null;
        this.D = new int[2];
        this.G = new O();
        this.G.B = true;
    }
    
    public boolean A(final char[] c, final G g, final int n) {
        this.F = (C)g;
        this.G.C = c;
        this.G.E = c.length;
        final O g2 = this.G;
        final boolean b = false;
        this.E = (b ? 1 : 0);
        g2.A = (b ? 1 : 0);
        this.G.B = true;
        this.A = this.G;
        this.D[0] = n;
        int c2;
        try {
            c2 = this.C();
        }
        catch (IOException ex) {
            c2 = -1;
        }
        if (c2 < 0) {
            this.B = null;
            return false;
        }
        this.B = new L(new String(c, 0, c2), n);
        return true;
    }
    
    public boolean B(final char[] array, final G g) {
        return this.A(array, g, 0);
    }
    
    public boolean B(final String s, final G g) {
        return this.A(s.toCharArray(), g, 0);
    }
    
    public boolean C(final B b, final G g) {
        this.F = (C)g;
        this.G.C = b.J();
        final O g2 = this.G;
        final int f = b.F();
        this.E = f;
        g2.A = f;
        this.D[0] = b.B();
        this.G.E = b.L();
        this.G.B = true;
        this.A = this.G;
        int c;
        try {
            c = this.C();
        }
        catch (IOException ex) {
            c = -1;
        }
        if (c < 0) {
            this.B = null;
            return false;
        }
        this.B = new L(new String(this.G.C, this.D[0], c), this.D[0]);
        return true;
    }
    
    public boolean A(final char[] c, final G g) {
        this.F = (C)g;
        this.G.C = c;
        this.G.E = c.length;
        final O g2 = this.G;
        final boolean b = false;
        this.E = (b ? 1 : 0);
        g2.A = (b ? 1 : 0);
        this.G.B = true;
        this.A = this.G;
        this.D[0] = 0;
        int c2;
        try {
            c2 = this.C();
        }
        catch (IOException ex) {
            c2 = -1;
        }
        if (c2 != c.length) {
            this.B = null;
            return false;
        }
        this.B = new L(new String(c, 0, c2), 0);
        return true;
    }
    
    public boolean A(final String s, final G g) {
        return this.A(s.toCharArray(), g);
    }
    
    public boolean A(final B b, final G g) {
        this.F = (C)g;
        this.G.C = b.J();
        this.G.E = b.L();
        final O g2 = this.G;
        final int f = b.F();
        this.E = f;
        g2.A = f;
        this.D[0] = b.F();
        this.G.B = true;
        this.A = this.G;
        int c;
        try {
            c = this.C();
        }
        catch (IOException ex) {
            c = -1;
        }
        if (c != this.G.E) {
            this.B = null;
            return false;
        }
        this.B = new L(new String(this.G.C, this.D[0], this.G.E), this.D[0]);
        return true;
    }
    
    public boolean C(final char[] c, final G g) {
        this.F = (C)g;
        if (this.F.L && !this.F.D[c[0]]) {
            this.B = null;
            return false;
        }
        this.G.C = c;
        this.G.E = c.length;
        final O g2 = this.G;
        final boolean b = false;
        this.E = (b ? 1 : 0);
        g2.A = (b ? 1 : 0);
        this.G.B = true;
        this.A = this.G;
        this.C = 0;
        try {
            this.B();
        }
        catch (IOException ex) {}
        return this.B != null;
    }
    
    public boolean C(final String s, final G g) {
        return this.C(s.toCharArray(), g);
    }
    
    public boolean B(final B b, final G g) {
        this.F = (C)g;
        this.G.C = b.J();
        final O g2 = this.G;
        final int f = b.F();
        this.E = f;
        g2.A = f;
        this.C = b.B();
        if (this.F.L && (this.E != this.C || !this.F.D[this.G.C[this.E]])) {
            this.B = null;
            return false;
        }
        this.G.E = b.L();
        this.G.B = true;
        this.A = this.G;
        try {
            this.B();
        }
        catch (IOException ex) {}
        b.C(this.C);
        if (this.B == null) {
            return false;
        }
        b.B(this.B.C(0), this.B.B(0));
        return true;
    }
    
    public boolean A(final O a, final G g) throws IOException {
        this.F = (C)g;
        if (this.F.L) {
            if (a.A != 0) {
                this.B = null;
                return false;
            }
            if (a.A() && !this.F.D[a.C[0]]) {
                this.B = null;
                return false;
            }
        }
        this.C = a.D;
        this.A = a;
        this.E = 0;
        this.B();
        a.D = this.C;
        if (this.B != null) {
            this.B.F(a.A);
            return true;
        }
        return false;
    }
    
    private int C() throws IOException {
        int n = 1;
        int n2 = -1;
        int i;
        int n3 = i = this.D[0];
        int n4 = this.A.E + this.E;
        while (i < n4) {
            final char c = this.A.C[i++];
            if (n >= this.F.P) {
                break;
            }
            final int n5 = n;
            final int[] a = this.F.A(n);
            n = a[c];
            if (n == 0) {
                this.F.A(n5, c, a);
                n = a[c];
            }
            if (n == -1) {
                break;
            }
            if (this.F.B.get(n)) {
                n2 = i;
            }
            if (i != n4) {
                continue;
            }
            i = this.A.A(n3) + this.E;
            n4 = this.A.E + this.E;
            if (i == n4) {
                continue;
            }
            if (n2 != -1) {
                n2 -= n3;
            }
            n3 = 0;
        }
        this.D[0] = n3;
        this.D[1] = n2 - 1;
        if (n2 == -1 && this.F.R) {
            return 0;
        }
        if (this.F.Q && (!this.A.B || n2 < this.A.E + this.E)) {
            return -1;
        }
        return n2 - n3;
    }
    
    void B() throws IOException {
        this.B = null;
        while (true) {
            if (this.C >= this.A.E + this.E) {
                if (this.A.B) {
                    this.A = null;
                    return;
                }
                if (!this.A.A()) {
                    return;
                }
                this.C = 0;
            }
            int i;
            for (i = this.C; i < this.A.E + this.E; i = this.D[0] + 1) {
                this.D[0] = i;
                final int c;
                if (this.F.D[this.A.C[i]] && (c = this.C()) > -1) {
                    this.B = new L(new String(this.A.C, this.D[0], c), this.D[0]);
                    this.C = ((c > 0) ? (this.D[1] + 1) : (this.D[0] + 1));
                    return;
                }
                if (this.F.R) {
                    this.B = new L(new String(), i);
                    this.C = i + 1;
                    return;
                }
            }
            this.C = i;
        }
    }
    
    public M A() {
        return this.B;
    }
}
