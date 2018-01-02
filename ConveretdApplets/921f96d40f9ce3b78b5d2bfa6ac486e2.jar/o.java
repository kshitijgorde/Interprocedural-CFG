// 
// Decompiled by Procyon v0.5.30
// 

public class o
{
    int p;
    int d;
    int a;
    int n;
    int[] p;
    int[] d;
    
    o(final int p3, final int d, final int n) {
        this.p = new int[3];
        this.d = new int[] { 6, 10, 14 };
        this.p = p3;
        this.d = d;
        this.a = 1;
        this.n = 1;
        this.p[0] = n;
    }
    
    o(final o o) {
        this.p = new int[3];
        this.d = new int[] { 6, 10, 14 };
        this.p = o.p;
        this.d = o.d;
        this.a = o.a;
        this.n = o.n;
        for (int i = 0; i < 3; ++i) {
            this.p[i] = o.p[i];
        }
    }
    
    final boolean p(final o o) {
        if (this.p != o.p) {
            return false;
        }
        if (this.d != o.d) {
            return false;
        }
        if (this.n != o.n) {
            return false;
        }
        for (int i = 0; i < this.n; ++i) {
            if (this.p[i] != o.p[i]) {
                return false;
            }
        }
        return this.a < o.a;
    }
    
    final boolean d(final o o) {
        if (this.p != o.p) {
            return false;
        }
        if (this.d != o.d) {
            return false;
        }
        if (this.n != o.n) {
            return false;
        }
        for (int i = 0; i < this.n; ++i) {
            if (this.p[i] != o.p[i]) {
                return false;
            }
        }
        return this.a == o.a;
    }
    
    final boolean p(final int p3, final int n, final int n2) {
        if (this.p == p3) {
            return false;
        }
        if (n != this.d) {
            return false;
        }
        this.p = p3;
        ++this.a;
        if (this.n == 3) {
            return true;
        }
        boolean b = false;
        for (int i = 0; i < this.n; ++i) {
            if (this.p[i] == n2) {
                b = true;
            }
        }
        if (!b) {
            this.p[this.n++] = n2;
            for (int j = this.n - 1; j >= 1; --j) {
                if (this.p[j] > this.p[j - 1]) {
                    final int n3 = this.p[j];
                    this.p[j] = this.p[j - 1];
                    this.p[j - 1] = n3;
                }
            }
        }
        return true;
    }
    
    final boolean p(final int n) {
        return this.a > this.d[this.n - 1] - n;
    }
    
    public final String toString() {
        return String.valueOf(this.p) + " " + h.p[this.d / 10 % 2][this.d % 10] + " " + this.a + " " + this.n + " " + h.p[this.p[0] / 10 % 2][this.p[0] % 10] + " " + h.p[this.p[1] / 10 % 2][this.p[1] % 10] + " " + h.p[this.p[2] / 10 % 2][this.p[2] % 10];
    }
}
