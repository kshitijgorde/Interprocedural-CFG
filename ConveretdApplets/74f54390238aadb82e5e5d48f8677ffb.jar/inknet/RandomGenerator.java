// 
// Decompiled by Procyon v0.5.30
// 

package inknet;

import java.util.Random;

public class RandomGenerator
{
    private static final int MASK = 1020;
    private static final int SIZE = 256;
    private static final int SIZEL = 8;
    private int a;
    private int b;
    private int c;
    private int count;
    long m_lLastTime;
    int m_nDelta;
    private int[] mem;
    private int[] rsl;
    
    public RandomGenerator() {
        this.m_nDelta = 0;
        final int[] seed = new int[256];
        final Random rnd = new Random();
        int nCount = rnd.nextInt();
        if (nCount < 0) {
            nCount = -nCount;
        }
        nCount %= 10000;
        for (int i = 0; i < nCount; ++i) {
            rnd.nextInt();
        }
        for (int j = 0; j < 256; ++j) {
            seed[j] = rnd.nextInt();
        }
        this.m_lLastTime = System.currentTimeMillis();
        this.init(seed);
    }
    
    public synchronized int getInt(final int nMax) {
        int count = nMax;
        final int numBits = 1;
        int mask = 1;
        int shift = 31;
        while ((count >>= 1) != 0) {
            --shift;
            mask = (mask << 1 | 0x1);
        }
        int val;
        do {
            val = this.val();
            val >>= shift;
            val += this.m_nDelta;
            val &= mask;
        } while (val > nMax);
        return val;
    }
    
    private final void init(final boolean flag) {
        int h;
        int g;
        int f;
        int e;
        int d;
        int c;
        int a;
        int b = a = (c = (d = (e = (f = (g = (h = -1640531527))))));
        for (int i = 0; i < 4; ++i) {
            a ^= b << 11;
            d += a;
            b += c;
            b ^= c >>> 2;
            e += b;
            c += d;
            c ^= d << 8;
            f += c;
            d += e;
            d ^= e >>> 16;
            g += d;
            e += f;
            e ^= f << 10;
            h += e;
            f += g;
            f ^= g >>> 4;
            a += f;
            g += h;
            g ^= h << 8;
            b += g;
            h += a;
            h ^= a >>> 9;
            c += h;
            a += b;
        }
        for (int i = 0; i < 256; i += 8) {
            if (flag) {
                a += this.rsl[i];
                b += this.rsl[i + 1];
                c += this.rsl[i + 2];
                d += this.rsl[i + 3];
                e += this.rsl[i + 4];
                f += this.rsl[i + 5];
                g += this.rsl[i + 6];
                h += this.rsl[i + 7];
            }
            a ^= b << 11;
            d += a;
            b += c;
            b ^= c >>> 2;
            e += b;
            c += d;
            c ^= d << 8;
            f += c;
            d += e;
            d ^= e >>> 16;
            g += d;
            e += f;
            e ^= f << 10;
            h += e;
            f += g;
            f ^= g >>> 4;
            a += f;
            g += h;
            g ^= h << 8;
            b += g;
            h += a;
            h ^= a >>> 9;
            c += h;
            a += b;
            this.mem[i] = a;
            this.mem[i + 1] = b;
            this.mem[i + 2] = c;
            this.mem[i + 3] = d;
            this.mem[i + 4] = e;
            this.mem[i + 5] = f;
            this.mem[i + 6] = g;
            this.mem[i + 7] = h;
        }
        if (flag) {
            for (int i = 0; i < 256; i += 8) {
                a += this.mem[i];
                b += this.mem[i + 1];
                c += this.mem[i + 2];
                d += this.mem[i + 3];
                e += this.mem[i + 4];
                f += this.mem[i + 5];
                g += this.mem[i + 6];
                h += this.mem[i + 7];
                a ^= b << 11;
                d += a;
                b += c;
                b ^= c >>> 2;
                e += b;
                c += d;
                c ^= d << 8;
                f += c;
                d += e;
                d ^= e >>> 16;
                g += d;
                e += f;
                e ^= f << 10;
                h += e;
                f += g;
                f ^= g >>> 4;
                a += f;
                g += h;
                g ^= h << 8;
                b += g;
                h += a;
                h ^= a >>> 9;
                c += h;
                a += b;
                this.mem[i] = a;
                this.mem[i + 1] = b;
                this.mem[i + 2] = c;
                this.mem[i + 3] = d;
                this.mem[i + 4] = e;
                this.mem[i + 5] = f;
                this.mem[i + 6] = g;
                this.mem[i + 7] = h;
            }
        }
        this.isaac();
        this.count = 256;
    }
    
    private void init(final int[] seed) {
        this.mem = new int[256];
        this.rsl = new int[256];
        for (int i = 0; i < seed.length; ++i) {
            this.rsl[i] = seed[i];
        }
        this.init(true);
    }
    
    private final void isaac() {
        this.b += ++this.c;
        for (int i = 0; i < 256; ++i) {
            final int x = this.mem[i];
            switch (i & 0x3) {
                case 0: {
                    this.a ^= this.a << 13;
                    break;
                }
                case 1: {
                    this.a ^= this.a >>> 6;
                    break;
                }
                case 2: {
                    this.a ^= this.a << 2;
                    break;
                }
                case 3: {
                    this.a ^= this.a >>> 16;
                    break;
                }
            }
            this.a += this.mem[i + 128 & 0xFF];
            final int y = this.mem[i] = this.mem[(x & 0x3FC) >> 2] + this.a + this.b;
            this.rsl[i] = (this.b = this.mem[(y >> 8 & 0x3FC) >> 2] + x);
        }
    }
    
    public void randomize() {
        final long lTime = System.currentTimeMillis();
        this.m_nDelta = (int)(lTime - this.m_lLastTime) >> 2;
        this.m_lLastTime = lTime;
    }
    
    private final int val() {
        if (0 == this.count--) {
            this.isaac();
            this.count = 255;
        }
        return this.rsl[this.count];
    }
}
