// 
// Decompiled by Procyon v0.5.30
// 

package de.mud.ssh;

public final class IDEA extends Cipher
{
    protected int[] key_schedule;
    protected int IV0;
    protected int IV1;
    
    public IDEA() {
        this.key_schedule = new int[52];
        this.IV0 = 0;
        this.IV1 = 0;
    }
    
    public synchronized void encrypt(final byte[] src, final int srcOff, final byte[] dest, final int destOff, final int len) {
        final int[] out = new int[2];
        int iv0 = this.IV0;
        int iv2 = this.IV1;
        for (int end = srcOff + len, si = srcOff, di = destOff; si < end; si += 8, di += 8) {
            this.encrypt(iv0, iv2, out);
            iv0 = out[0];
            iv2 = out[1];
            iv0 ^= ((src[si + 3] & 0xFF) | (src[si + 2] & 0xFF) << 8 | (src[si + 1] & 0xFF) << 16 | (src[si] & 0xFF) << 24);
            iv2 ^= ((src[si + 7] & 0xFF) | (src[si + 6] & 0xFF) << 8 | (src[si + 5] & 0xFF) << 16 | (src[si + 4] & 0xFF) << 24);
            if (di + 8 <= end) {
                dest[di + 3] = (byte)(iv0 & 0xFF);
                dest[di + 2] = (byte)(iv0 >>> 8 & 0xFF);
                dest[di + 1] = (byte)(iv0 >>> 16 & 0xFF);
                dest[di] = (byte)(iv0 >>> 24 & 0xFF);
                dest[di + 7] = (byte)(iv2 & 0xFF);
                dest[di + 6] = (byte)(iv2 >>> 8 & 0xFF);
                dest[di + 5] = (byte)(iv2 >>> 16 & 0xFF);
                dest[di + 4] = (byte)(iv2 >>> 24 & 0xFF);
            }
            else {
                switch (end - di) {
                    case 7: {
                        dest[di + 6] = (byte)(iv2 >>> 8 & 0xFF);
                    }
                    case 6: {
                        dest[di + 5] = (byte)(iv2 >>> 16 & 0xFF);
                    }
                    case 5: {
                        dest[di + 4] = (byte)(iv2 >>> 24 & 0xFF);
                    }
                    case 4: {
                        dest[di + 3] = (byte)(iv0 & 0xFF);
                    }
                    case 3: {
                        dest[di + 2] = (byte)(iv0 >>> 8 & 0xFF);
                    }
                    case 2: {
                        dest[di + 1] = (byte)(iv0 >>> 16 & 0xFF);
                    }
                    case 1: {
                        dest[di] = (byte)(iv0 >>> 24 & 0xFF);
                        break;
                    }
                }
            }
        }
        this.IV0 = iv0;
        this.IV1 = iv2;
    }
    
    public synchronized void decrypt(final byte[] src, final int srcOff, final byte[] dest, final int destOff, final int len) {
        final int[] out = new int[2];
        int iv0 = this.IV0;
        int iv2 = this.IV1;
        for (int end = srcOff + len, si = srcOff, di = destOff; si < end; si += 8, di += 8) {
            this.decrypt(iv0, iv2, out);
            iv0 = ((src[si + 3] & 0xFF) | (src[si + 2] & 0xFF) << 8 | (src[si + 1] & 0xFF) << 16 | (src[si] & 0xFF) << 24);
            iv2 = ((src[si + 7] & 0xFF) | (src[si + 6] & 0xFF) << 8 | (src[si + 5] & 0xFF) << 16 | (src[si + 4] & 0xFF) << 24);
            final int plain0 = out[0] ^ iv0;
            final int plain2 = out[1] ^ iv2;
            if (di + 8 <= end) {
                dest[di + 3] = (byte)(plain0 & 0xFF);
                dest[di + 2] = (byte)(plain0 >>> 8 & 0xFF);
                dest[di + 1] = (byte)(plain0 >>> 16 & 0xFF);
                dest[di] = (byte)(plain0 >>> 24 & 0xFF);
                dest[di + 7] = (byte)(plain2 & 0xFF);
                dest[di + 6] = (byte)(plain2 >>> 8 & 0xFF);
                dest[di + 5] = (byte)(plain2 >>> 16 & 0xFF);
                dest[di + 4] = (byte)(plain2 >>> 24 & 0xFF);
            }
            else {
                switch (end - di) {
                    case 7: {
                        dest[di + 6] = (byte)(plain2 >>> 8 & 0xFF);
                    }
                    case 6: {
                        dest[di + 5] = (byte)(plain2 >>> 16 & 0xFF);
                    }
                    case 5: {
                        dest[di + 4] = (byte)(plain2 >>> 24 & 0xFF);
                    }
                    case 4: {
                        dest[di + 3] = (byte)(plain0 & 0xFF);
                    }
                    case 3: {
                        dest[di + 2] = (byte)(plain0 >>> 8 & 0xFF);
                    }
                    case 2: {
                        dest[di + 1] = (byte)(plain0 >>> 16 & 0xFF);
                    }
                    case 1: {
                        dest[di] = (byte)(plain0 >>> 24 & 0xFF);
                        break;
                    }
                }
            }
        }
        this.IV0 = iv0;
        this.IV1 = iv2;
    }
    
    public void setKey(final byte[] key) {
        int ki = 0;
        int j = 0;
        for (int i = 0; i < 8; ++i) {
            this.key_schedule[i] = ((key[2 * i] & 0xFF) << 8 | (key[2 * i + 1] & 0xFF));
        }
        int i = 8;
        j = 0;
        while (i < 52) {
            ++j;
            this.key_schedule[ki + j + 7] = ((this.key_schedule[ki + (j & 0x7)] << 9 | this.key_schedule[ki + (j + 1 & 0x7)] >>> 7) & 0xFFFF);
            ki += (j & 0x8);
            j &= 0x7;
            ++i;
        }
    }
    
    public final void encrypt(final int l, final int r, final int[] out) {
        int t1 = 0;
        int t2 = 0;
        int ki = 0;
        int x1 = l >>> 16;
        int x2 = l & 0xFFFF;
        int x3 = r >>> 16;
        int x4 = r & 0xFFFF;
        for (int round = 0; round < 8; ++round) {
            x1 = mulop(x1 & 0xFFFF, this.key_schedule[ki++]);
            x2 += this.key_schedule[ki++];
            x3 += this.key_schedule[ki++];
            x4 = mulop(x4 & 0xFFFF, this.key_schedule[ki++]);
            t1 = (x1 ^ x3);
            t2 = (x2 ^ x4);
            t1 = mulop(t1 & 0xFFFF, this.key_schedule[ki++]);
            t2 += t1;
            t2 = mulop(t2 & 0xFFFF, this.key_schedule[ki++]);
            t1 += t2;
            x1 ^= t2;
            x4 ^= t1;
            t1 ^= x2;
            x2 = (t2 ^ x3);
            x3 = t1;
        }
        t2 = x2;
        x1 = mulop(x1 & 0xFFFF, this.key_schedule[ki++]);
        x2 = t1 + this.key_schedule[ki++];
        x3 = (t2 + this.key_schedule[ki++] & 0xFFFF);
        x4 = mulop(x4 & 0xFFFF, this.key_schedule[ki]);
        out[0] = (x1 << 16 | (x2 & 0xFFFF));
        out[1] = (x3 << 16 | (x4 & 0xFFFF));
    }
    
    public final void decrypt(final int l, final int r, final int[] out) {
        this.encrypt(l, r, out);
    }
    
    public static final int mulop(final int a, final int b) {
        final int ab = a * b;
        if (ab != 0) {
            final int lo = ab & 0xFFFF;
            final int hi = ab >>> 16 & 0xFFFF;
            return lo - hi + ((lo < hi) ? 1 : 0);
        }
        if (a == 0) {
            return 1 - b;
        }
        return 1 - a;
    }
}
