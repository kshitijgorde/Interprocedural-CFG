// 
// Decompiled by Procyon v0.5.30
// 

public class y
{
    public int[] a;
    public int b;
    public int c;
    
    public y(final int n) {
        this.a = new int[624];
        this.b = 625;
        this.a(n);
    }
    
    public y() {
        this.a = new int[624];
        this.b = 625;
        this.a(4357);
    }
    
    public void a(int c) {
        if (c == 0) {
            c = 4357;
        }
        this.c = c;
        this.a[0] = (c & -1);
        this.b = 1;
        while (this.b < 624) {
            this.a[this.b] = (69069 * this.a[this.b - 1] & -1);
            ++this.b;
        }
    }
    
    public int a() {
        final int[] array = { 0, -1727483681 };
        if (this.b >= 624) {
            int i = 0;
            do {
                final int n = (this.a[i] & Integer.MIN_VALUE) | (this.a[i + 1] & Integer.MAX_VALUE);
                this.a[i] = (this.a[i + 397] ^ (n >> 1 & Integer.MAX_VALUE) ^ array[n & 0x1]);
            } while (++i < 227);
            while (i < 623) {
                final int n2 = (this.a[i] & Integer.MIN_VALUE) | (this.a[i + 1] & Integer.MAX_VALUE);
                this.a[i] = (this.a[i - 227] ^ (n2 >> 1 & Integer.MAX_VALUE) ^ array[n2 & 0x1]);
                ++i;
            }
            final int n3 = (this.a[623] & Integer.MIN_VALUE) | (this.a[0] & Integer.MAX_VALUE);
            this.a[623] = (this.a[396] ^ (n3 >> 1 & Integer.MAX_VALUE) ^ array[n3 & 0x1]);
            this.b = 0;
        }
        final int n4 = this.a[this.b++];
        final int n5 = n4 ^ (n4 >> 11 & 0x1FFFFF);
        final int n6 = n5 ^ (n5 << 7 & 0x9D2C5680);
        final int n7 = n6 ^ (n6 << 15 & 0xEFC60000);
        return (n7 ^ (n7 >> 18 & 0x3FFF)) & Integer.MAX_VALUE;
    }
}
