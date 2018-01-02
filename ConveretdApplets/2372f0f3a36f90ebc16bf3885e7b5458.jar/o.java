// 
// Decompiled by Procyon v0.5.30
// 

public class o
{
    public byte[] a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    
    public int a() {
        this.a = null;
        return 0;
    }
    
    public int a(final int n) {
        if (this.d != 0) {
            this.c -= this.d;
            if (this.c > 0) {
                System.arraycopy(this.a, this.d, this.a, 0, this.c);
            }
            this.d = 0;
        }
        if (n > this.b - this.c) {
            final int b = n + this.c + 4096;
            if (this.a != null) {
                final byte[] a = new byte[b];
                System.arraycopy(this.a, 0, a, 0, this.a.length);
                this.a = a;
            }
            else {
                this.a = new byte[b];
            }
            this.b = b;
        }
        return this.c;
    }
    
    public int b(final int n) {
        if (this.c + n > this.b) {
            return -1;
        }
        this.c += n;
        return 0;
    }
    
    public int a(final q q) {
        final int d = this.d;
        final int n = this.c - this.d;
        final byte[] array = new byte[4];
        if (this.f == 0) {
            if (n < 27) {
                return 0;
            }
            if (this.a[d] != 79 || this.a[d + 1] != 103 || this.a[d + 2] != 103 || this.a[d + 3] != 83) {
                this.f = 0;
                this.g = 0;
                int c = 0;
                for (int i = 0; i < n - 1; ++i) {
                    if (this.a[d + 1 + i] == 79) {
                        c = d + 1 + i;
                        break;
                    }
                }
                if (c == 0) {
                    c = this.c;
                }
                return -((this.d = c) - d);
            }
            final int f = (this.a[d + 26] & 0xFF) + 27;
            if (n < f) {
                return 0;
            }
            for (int j = 0; j < (this.a[d + 26] & 0xFF); ++j) {
                this.g += (this.a[d + 27 + j] & 0xFF);
            }
            this.f = f;
        }
        if (this.g + this.f > n) {
            return 0;
        }
        synchronized (array) {
            System.arraycopy(this.a, d + 22, array, 0, 4);
            this.a[d + 22] = 0;
            this.a[d + 23] = 0;
            this.a[d + 24] = 0;
            this.a[d + 25] = 0;
            final q q2 = new q();
            q2.a = this.a;
            q2.b = d;
            q2.c = this.f;
            q2.d = this.a;
            q2.e = d + this.f;
            q2.f = this.g;
            q2.h();
            if (array[0] != this.a[d + 22] || array[1] != this.a[d + 23] || array[2] != this.a[d + 24] || array[3] != this.a[d + 25]) {
                System.arraycopy(array, 0, this.a, d + 22, 4);
                this.f = 0;
                this.g = 0;
                int c2 = 0;
                for (int k = 0; k < n - 1; ++k) {
                    if (this.a[d + 1 + k] == 79) {
                        c2 = d + 1 + k;
                        break;
                    }
                }
                if (c2 == 0) {
                    c2 = this.c;
                }
                return -((this.d = c2) - d);
            }
        }
        final int d2 = this.d;
        if (q != null) {
            q.a = this.a;
            q.b = d2;
            q.c = this.f;
            q.d = this.a;
            q.e = d2 + this.f;
            q.f = this.g;
        }
        this.e = 0;
        final int n2;
        this.d += (n2 = this.f + this.g);
        this.f = 0;
        this.g = 0;
        return n2;
    }
    
    public int b(final q q) {
        while (true) {
            final int a = this.a(q);
            if (a > 0) {
                return 1;
            }
            if (a == 0) {
                return 0;
            }
            if (this.e == 0) {
                this.e = 1;
                return -1;
            }
        }
    }
    
    public int b() {
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        return this.g = 0;
    }
    
    public int c() {
        return 0;
    }
}
