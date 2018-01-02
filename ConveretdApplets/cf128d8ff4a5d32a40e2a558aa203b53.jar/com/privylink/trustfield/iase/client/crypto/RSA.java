// 
// Decompiled by Procyon v0.5.30
// 

package com.privylink.trustfield.iase.client.crypto;

public class RSA
{
    private byte[] _$23;
    private byte[] _$2517;
    private byte[] _$2516;
    private byte[] _$21001;
    private byte[] _$21002;
    private int _$21003;
    private byte[][] _$21004;
    private byte[] _$21005;
    private int _$21006;
    private boolean _$21007;
    
    public RSA() {
        this._$21006 = 0;
        this._$21002 = new byte[0];
        this._$21003 = 16;
        this._$21007 = true;
        this._$21004 = new byte[this._$21003 + 1][];
        for (int i = 1; i <= this._$21003; ++i) {
            (this._$21005 = new byte[1])[0] = (byte)i;
            this._$21004[i] = this._$21005;
        }
        (this._$21001 = new byte[256])[0] = 0;
        this._$21001[1] = 1;
        this._$21001[2] = 2;
        this._$21001[3] = 2;
        this._$21001[4] = 3;
        this._$21001[5] = 3;
        this._$21001[6] = 3;
        this._$21001[7] = 3;
        this._$21001[8] = 4;
        this._$21001[9] = 4;
        this._$21001[10] = 4;
        this._$21001[11] = 4;
        this._$21001[12] = 4;
        this._$21001[13] = 4;
        this._$21001[14] = 4;
        this._$21001[15] = 4;
        for (int i = 16; i < 32; ++i) {
            this._$21001[i] = 5;
        }
        for (int i = 32; i < 64; ++i) {
            this._$21001[i] = 6;
        }
        for (int i = 64; i < 128; ++i) {
            this._$21001[i] = 7;
        }
        for (int i = 128; i < 256; ++i) {
            this._$21001[i] = 8;
        }
    }
    
    public final void RSAEncryptInit(final byte[] E, final byte[] N) {
        this._$2516 = E;
        this._$2517 = N;
    }
    
    public final void RSADecryptInit(final byte[] D, final byte[] N) {
        this._$23 = D;
        this._$2517 = N;
    }
    
    public byte[] RSAEncrypt(final byte[] M) {
        return this._$21009(M, this._$2516, this._$2517);
    }
    
    public byte[] RSADecrypt(final byte[] C) {
        return this._$21009(C, this._$23, this._$2517);
    }
    
    private byte[] _$21010(final byte[] val1, final byte[] val2) {
        return this._$21013(val1, val2);
    }
    
    private byte[] _$21014(final byte[] val1, final byte[] val2) throws ArithmeticException {
        if (val2.length == 0) {
            throw new ArithmeticException("BigInteger divide by zero");
        }
        if (val1.length == 0) {
            return this._$21002;
        }
        if (val1.length < val2.length) {
            return val1;
        }
        return this._$21015(val1, val2);
    }
    
    private byte[] _$21016(final byte[] base, int exponent) {
        byte[] result = this._$408((exponent < 0 && (exponent & 0x1) == 0x1) ? -1 : 1);
        byte[] baseToPow2 = base;
        while (exponent != 0) {
            if ((exponent & 0x1) == 0x1) {
                result = this._$21010(result, baseToPow2);
            }
            if ((exponent >>= 1) != 0) {
                baseToPow2 = this._$21010(baseToPow2, baseToPow2);
            }
        }
        return result;
    }
    
    private byte[] _$21020(final byte[] val, final byte[] m) {
        final byte[] result = this._$21014(val, m);
        return result;
    }
    
    private byte[] _$21009(final byte[] b, final byte[] exponent, final byte[] m) {
        final byte[] base = (this._$21023(b, m) >= 0) ? this._$21020(b, m) : b;
        final int nb = this._$21025(exponent);
        if (nb == 0) {
            return this._$21004[1];
        }
        if (nb == 1) {
            return base;
        }
        byte[] result = base;
        final byte[] x2 = this._$21020(this._$21016(base, 2), m);
        final byte[] x3 = this._$21020(this._$21010(base, x2), m);
        final byte[] x4 = this._$21020(this._$21010(x2, x3), m);
        final byte[] x5 = this._$21020(this._$21010(x4, x2), m);
        int i = nb - 2;
        while (i >= 0) {
            result = this._$21020(this._$21016(result, 2), m);
            if (this._$21030(exponent, i--)) {
                if (i >= 0 && this._$21030(exponent, i)) {
                    if (--i >= 0 && this._$21030(exponent, i)) {
                        --i;
                        result = this._$21020(this._$21016(result, 4), m);
                        result = this._$21020(this._$21010(result, x5), m);
                    }
                    else {
                        result = this._$21020(this._$21016(result, 2), m);
                        result = this._$21020(this._$21010(result, x3), m);
                    }
                }
                else if (i >= 1 && this._$21030(exponent, i - 1)) {
                    i -= 2;
                    result = this._$21020(this._$21016(result, 4), m);
                    result = this._$21020(this._$21010(result, x4), m);
                }
                else {
                    result = this._$21020(this._$21010(result, base), m);
                }
            }
        }
        return result;
    }
    
    private boolean _$21030(final byte[] val, final int n) throws ArithmeticException {
        if (n < 0) {
            throw new ArithmeticException("Negative bit address");
        }
        return (this._$21031(val, n / 8) & 1 << n % 8) != 0x0;
    }
    
    private int _$21025(final byte[] val) {
        if (val.length == 0) {
            return 0;
        }
        return 8 * (val.length - 1) + this._$21001[val[0] & 0xFF];
    }
    
    private int _$21023(final byte[] arg1, final byte[] arg2) {
        if (arg1.length < arg2.length) {
            return -1;
        }
        if (arg1.length > arg2.length) {
            return 1;
        }
        for (int i = 0; i < arg1.length; ++i) {
            final int b1 = arg1[i] & 0xFF;
            final int b2 = arg2[i] & 0xFF;
            if (b1 < b2) {
                return -1;
            }
            if (b1 > b2) {
                return 1;
            }
        }
        return 0;
    }
    
    private byte[] _$408(long val) throws ArithmeticException {
        if (val < 0) {
            throw new ArithmeticException("Argument is negative");
        }
        if (val == 0) {
            return this._$21002;
        }
        if (val <= this._$21003) {
            return this._$21004[(int)val];
        }
        final byte[] valArray = new byte[8];
        for (int i = 0; i < 8; ++i, val >>= 8) {
            valArray[7 - i] = (byte)val;
        }
        return this._$21037(valArray);
    }
    
    private byte[] _$21037(final byte[] a) {
        int keep;
        for (keep = 0; keep < a.length && a[keep] == 0; ++keep) {}
        final byte[] result = new byte[a.length - keep];
        for (int i = keep; i < a.length; ++i) {
            result[i - keep] = a[i];
        }
        return result;
    }
    
    private byte _$21031(final byte[] val, final int n) throws ArithmeticException {
        if (n >= val.length) {
            throw new ArithmeticException("Byte address out of range");
        }
        return val[val.length - n - 1];
    }
    
    private byte[] _$21013(final byte[] a, final byte[] b) {
        final int lx = (a.length >> 1) + (a.length & 0x1);
        final int ly = (b.length >> 1) + (b.length & 0x1);
        final int[] x = new int[lx];
        final int[] y = new int[ly];
        final int[] z = new int[lx + ly];
        for (int i = 0; i < lx - 1; ++i) {
            int temp = a.length - 2 * i - 1;
            x[i] = (a[temp--] & 0xFF);
            final int[] array = x;
            final int n = i;
            array[n] |= (a[temp--] & 0xFF) << 8;
        }
        if (2 * lx == a.length) {
            x[lx - 1] = (a[1] & 0xFF);
            final int[] array2 = x;
            final int n2 = lx - 1;
            array2[n2] |= (a[0] & 0xFF) << 8;
        }
        else {
            x[lx - 1] = (a[0] & 0xFF);
        }
        for (int i = 0; i < ly - 1; ++i) {
            int temp = b.length - 2 * i - 1;
            y[i] = (b[temp--] & 0xFF);
            final int[] array3 = y;
            final int n3 = i;
            array3[n3] |= (b[temp--] & 0xFF) << 8;
        }
        if (2 * ly == b.length) {
            y[ly - 1] = (b[1] & 0xFF);
            final int[] array4 = y;
            final int n4 = ly - 1;
            array4[n4] |= (b[0] & 0xFF) << 8;
        }
        else {
            y[ly - 1] = (b[0] & 0xFF);
        }
        for (int i = 0; i < lx; ++i) {
            int carry = 0;
            int j;
            for (j = 0; j < ly; ++j) {
                final long dble = x[i] * y[j] + carry;
                final int dig = (int)dble & 0xFFFF;
                carry = ((int)(dble >>> 16) & 0xFFFF);
                z[i + j] = (dig + z[i + j] & 0xFFFF);
                if (z[i + j] < dig) {
                    ++carry;
                }
            }
            z[j + i] = carry;
        }
        final byte[] c = new byte[2 * z.length];
        for (int i = 0; i < z.length; ++i) {
            c[2 * i] = (byte)(z[z.length - i - 1] >> 8);
            c[2 * i + 1] = (byte)z[z.length - i - 1];
        }
        return c;
    }
    
    private byte[] _$21015(final byte[] a, final byte[] b) {
        final byte[][] result = this._$21045(a, b);
        return result[1];
    }
    
    private int _$21046(final int[] x, final int n, final int[] z) {
        final int flag = 0;
        int r = 0;
        final int lx = x.length;
        for (int i = lx - 1; i >= 0; --i) {
            if (this._$21007) {
                try {
                    Thread.sleep(1L);
                    this._$21007 = false;
                }
                catch (InterruptedException ie) {
                    System.out.println("Interrupted:".concat(String.valueOf(String.valueOf(ie))));
                }
            }
            final long dble = (r << 16) + x[i];
            z[i] = (int)(dble / n);
            r = (int)(dble - z[i] * n);
        }
        return r;
    }
    
    private void _$21050(final int[] x, final int n, final int[] z) {
        int carry = 0;
        int i;
        for (i = 0; i < x.length; ++i) {
            final long dble = x[i] * n + carry;
            carry = (int)(dble >> 16);
            z[i] = ((int)dble & 0xFFFF);
        }
        if (carry > 0) {
            z[i] = carry;
        }
    }
    
    private int _$21051(final int[] y) {
        final int len = y.length - 1;
        final int r;
        int norm;
        if ((r = (y[len] + 1 & 0xFFFF)) == 0) {
            norm = 1;
        }
        else {
            norm = ((int)(65536L / r) & 0xFFFF);
        }
        if (norm != 1) {
            this._$21050(y, norm, y);
        }
        return norm;
    }
    
    private byte[][] _$21045(final byte[] a, byte[] b) {
        final byte[][] result = new byte[2][];
        b = this._$21037(b);
        int tst = this._$21023(a, b);
        if (tst == 0) {
            (result[0] = new byte[1])[0] = 1;
            result[1] = new byte[0];
        }
        else if (tst < 0) {
            result[0] = new byte[0];
            result[1] = new byte[a.length];
            for (int i = 0; i < a.length; ++i) {
                result[1][i] = a[i];
            }
        }
        int lx = (a.length >> 1) + (a.length & 0x1);
        int ly = (b.length >> 1) + (b.length & 0x1);
        final int[] x = new int[lx];
        final int[] y = new int[ly];
        for (int i = 0; i < lx - 1; ++i) {
            int temp = a.length - 2 * i - 1;
            x[i] = (a[temp--] & 0xFF);
            final int[] array = x;
            final int n = i;
            array[n] |= (a[temp--] & 0xFF) << 8;
        }
        if (2 * lx == a.length) {
            x[lx - 1] = (a[1] & 0xFF);
            final int[] array2 = x;
            final int n2 = lx - 1;
            array2[n2] |= (a[0] & 0xFF) << 8;
        }
        else {
            x[lx - 1] = (a[0] & 0xFF);
        }
        for (int i = 0; i < ly - 1; ++i) {
            int temp = b.length - 2 * i - 1;
            y[i] = (b[temp--] & 0xFF);
            final int[] array3 = y;
            final int n3 = i;
            array3[n3] |= (b[temp--] & 0xFF) << 8;
        }
        if (2 * ly == b.length) {
            y[ly - 1] = (b[1] & 0xFF);
            final int[] array4 = y;
            final int n4 = ly - 1;
            array4[n4] |= (b[0] & 0xFF) << 8;
        }
        else {
            y[ly - 1] = (b[0] & 0xFF);
        }
        lx = x.length - 1;
        ly = y.length - 1;
        int[] z;
        int[] bm_w0;
        if (lx == 0) {
            z = new int[] { x[0] / y[0] };
            bm_w0 = new int[] { x[0] % y[0] };
        }
        else if (ly == 0) {
            z = new int[x.length];
            bm_w0 = new int[] { this._$21046(x, y[0], z) };
        }
        else {
            bm_w0 = new int[x.length + 1];
            z = new int[x.length - y.length + 1];
            for (int i = 0; i < x.length; ++i) {
                bm_w0[i] = x[i];
            }
            final int d = this._$21051(y);
            if (d != 0) {
                this._$21050(bm_w0, d, bm_w0);
            }
            final int y2 = y[ly];
            final int y3 = y[ly - 1];
            for (int k = x.length - 1; k >= ly; --k) {
                int carry = 0;
                int itry;
                int ra;
                if (bm_w0[k + 1] == y2) {
                    itry = 65535;
                    ra = (y2 + bm_w0[k] & 0xFFFF);
                    if (ra < y2) {
                        carry = 1;
                    }
                }
                else {
                    final long dble = (bm_w0[k + 1] << 16) + bm_w0[k];
                    itry = (int)(dble / y2);
                    ra = ((int)(dble - itry * y2) & 0xFFFF);
                }
                while (carry == 0) {
                    final long dble = itry * y3;
                    final int r = (int)dble & 0xFFFF;
                    tst = ((int)(dble >>> 16) & 0xFFFF);
                    if (tst < ra) {
                        break;
                    }
                    if (tst == ra && r <= bm_w0[k - 1]) {
                        break;
                    }
                    --itry;
                    ra = (ra + y2 & 0xFFFF);
                    if (ra >= y2) {
                        continue;
                    }
                    carry = 1;
                }
                final int m = k - ly;
                if (itry > 0) {
                    int borrow = 0;
                    for (int i = 0; i <= ly; ++i) {
                        final long dble = itry * y[i] + borrow;
                        final int dig = (int)(dble & 0xFFFF);
                        borrow = (int)(dble >>> 16 & 0xFFFF);
                        if (bm_w0[m + i] < dig) {
                            ++borrow;
                        }
                        bm_w0[m + i] = (bm_w0[m + i] - dig & 0xFFFF);
                    }
                    if (bm_w0[k + 1] < borrow) {
                        bm_w0[k + 1] = 0;
                        carry = 0;
                        for (int i = 0; i <= ly; ++i) {
                            final int sum = bm_w0[m + i] + y[i] + carry & 0xFFFF;
                            if (sum > y[i]) {
                                carry = 0;
                            }
                            if (sum < y[i]) {
                                carry = 1;
                            }
                            bm_w0[m + i] = sum;
                        }
                        --itry;
                    }
                    else {
                        bm_w0[k + 1] = (bm_w0[k + 1] - borrow & 0xFFFF);
                    }
                }
                z[m] = itry;
            }
            if (d != 1) {
                this._$21046(bm_w0, d, bm_w0);
            }
        }
        byte[] c = new byte[2 * z.length];
        for (int i = 0; i < z.length; ++i) {
            c[2 * i] = (byte)(z[z.length - i - 1] >> 8);
            c[2 * i + 1] = (byte)z[z.length - i - 1];
        }
        result[0] = this._$21037(c);
        c = new byte[2 * bm_w0.length];
        for (int i = 0; i < bm_w0.length; ++i) {
            c[2 * i] = (byte)(bm_w0[bm_w0.length - i - 1] >> 8);
            c[2 * i + 1] = (byte)bm_w0[bm_w0.length - i - 1];
        }
        result[1] = this._$21037(c);
        return result;
    }
    
    private byte[] _$21062(final byte[] a) {
        final int lx = (a.length >> 1) + (a.length & 0x1);
        final int[] x = new int[lx];
        final int[] z = new int[2 * lx];
        for (int i = 0; i < lx - 1; ++i) {
            int temp = a.length - 2 * i - 1;
            x[i] = (a[temp--] & 0xFF);
            final int[] array = x;
            final int n = i;
            array[n] |= (a[temp--] & 0xFF) << 8;
        }
        if (2 * lx == a.length) {
            x[lx - 1] = (a[1] & 0xFF);
            final int[] array2 = x;
            final int n2 = lx - 1;
            array2[n2] |= (a[0] & 0xFF) << 8;
        }
        else {
            x[lx - 1] = (a[0] & 0xFF);
        }
        for (int i = 0; i < lx; ++i) {
            int carry = 0;
            int j;
            for (j = i + 1; j < lx; ++j) {
                final long dble = x[i] * x[j] + carry;
                final int dig = (int)(dble & 0xFFFF);
                carry = ((int)(dble >>> 16) & 0xFFFF);
                final int tmp = z[i + j] + dig & 0xFFFF;
                if (tmp < dig) {
                    ++carry;
                }
                z[i + j] = tmp;
            }
            z[j + i] = carry;
        }
        int carry = 0;
        for (int i = 0; i < z.length; ++i) {
            z[i] = (z[i] << 1) + carry;
            carry = (((z[i] & 0xFFFF0000) != 0x0) ? 1 : 0);
        }
        carry = 0;
        for (int i = 0; i < lx; ++i) {
            final int ii = i + i;
            final long dble = x[i] * x[i] + carry;
            final int dig = (int)dble & 0xFFFF;
            carry = ((int)(dble >>> 16) & 0xFFFF);
            final int[] array3 = z;
            final int n3 = ii;
            array3[n3] += dig;
            if ((z[ii] & 0xFFFF) < dig) {
                ++carry;
            }
            final int[] array4 = z;
            final int n4 = ii + 1;
            array4[n4] += carry;
            if ((z[ii + 1] & 0xFFFF) < carry) {
                carry = 1;
            }
            else {
                carry = 0;
            }
        }
        final byte[] c = new byte[2 * z.length];
        for (int i = 0; i < z.length; ++i) {
            c[2 * i] = (byte)(z[z.length - i - 1] >> 8);
            c[2 * i + 1] = (byte)z[z.length - i - 1];
        }
        return c;
    }
    
    public void getVersion() {
        System.out.println("RSA Version 3.04");
        System.out.println("Creation Date: 22-1-2001");
        System.out.println("Modification Date: 23-6-2003");
    }
}
