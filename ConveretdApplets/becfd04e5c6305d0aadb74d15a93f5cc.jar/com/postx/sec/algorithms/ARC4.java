// 
// Decompiled by Procyon v0.5.30
// 

package com.postx.sec.algorithms;

public class ARC4
{
    private static final String Ident = "$Id: ARC4.java,v 1.6 2010/06/02 09:49:18 steve Exp $";
    private int[] S;
    private int I;
    private int J;
    
    public ARC4(final byte[] array) {
        this(array, 0, false);
    }
    
    public ARC4(final byte[] array, final boolean b) {
        this(array, 0, b);
    }
    
    public ARC4(final byte[] array, final int n) {
        this(array, n, false);
    }
    
    public ARC4(final byte[] array, final int n, final boolean b) {
        this(array, n, b ? 256 : 0);
    }
    
    public ARC4(final byte[] array, int length, int n) {
        final int[] array2 = new int[256];
        final int[] s = new int[256];
        if (length == 0 || length > array.length) {
            length = array.length;
        }
        int i;
        for (int n2 = i = 0; i < 256; ++i) {
            array2[s[i] = i] = (array[n2] & 0xFF);
            if (++n2 == length) {
                n2 = 0;
            }
        }
        int j;
        for (int n3 = j = 0; j < 256; ++j) {
            n3 = (n3 + s[j] + array2[j] & 0xFF);
            final int n4 = s[j];
            s[j] = s[n3];
            s[n3] = n4;
        }
        int l;
        int k = l = 0;
        while (n-- > 0) {
            l = (l + 1 & 0xFF);
            k = (k + s[l] & 0xFF);
            final int n5 = s[l];
            s[l] = s[k];
            s[k] = n5;
        }
        this.S = s;
        this.I = l;
        this.J = k;
    }
    
    public byte[] arraycrypt(final byte[] array) {
        return this.arraycrypt(array, 0, null, 0, array.length);
    }
    
    public byte[] arraycrypt(final byte[] array, final byte[] array2) {
        return this.arraycrypt(array, 0, array2, 0, array.length);
    }
    
    public byte[] arraycrypt(final byte[] array, final int n, byte[] array2, final int n2, final int n3) {
        if (array == array2 && n != n2 && Math.abs(n - n2) < n3) {
            throw new IllegalArgumentException("src and dst overlap");
        }
        if (array2 == null) {
            array2 = new byte[n2 + n3];
        }
        final int[] s = this.S;
        int i = this.I;
        int j = this.J;
        for (int k = 0; k < n3; ++k) {
            i = (i + 1 & 0xFF);
            j = (j + s[i] & 0xFF);
            final int n4 = s[i];
            s[i] = s[j];
            s[j] = n4;
            array2[k + n2] = (byte)(array[k + n] ^ s[s[i] + s[j] & 0xFF]);
        }
        this.I = i;
        this.J = j;
        return array2;
    }
    
    public void wipe() {
        final int[] s = this.S;
        int n = 0;
        do {
            s[n] = 0;
        } while (++n < 256);
        final boolean b = false;
        this.J = (b ? 1 : 0);
        this.I = (b ? 1 : 0);
    }
    
    public byte next() {
        final int[] s = this.S;
        final int i = this.I + 1 & 0xFF;
        final int j = this.J + s[i] & 0xFF;
        final int n = s[i];
        s[i] = s[j];
        s[j] = n;
        final int n2 = s[s[i] + s[j] & 0xFF];
        this.I = i;
        this.J = j;
        return (byte)n2;
    }
}
