// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.io.FileInputStream;

class MD4
{
    private static final byte[] padding;
    private MD4State state;
    private byte[] final_hash;
    
    public MD4() {
        this.state = new MD4State();
    }
    
    public MD4(final byte[] array) {
        this();
        this.update(array);
    }
    
    public void update(final byte[] array, int i, int n) {
        if (this.final_hash != null) {
            throw new IllegalStateException("Hash already terminated");
        }
        int n2 = (int)(this.state.count >>> 3 & 0x3FL);
        final int n3 = 64 - n2;
        final MD4State state = this.state;
        state.count += n << 3;
        if (n >= n3) {
            System.arraycopy(array, i, this.state.buffer, n2, n3);
            this.transform(this.state.buffer, 0);
            int n4;
            for (n4 = i + n, i += n3; i < n4 - 63; i += 64) {
                this.transform(array, i);
            }
            n2 = 0;
            n = n4 - i;
        }
        System.arraycopy(array, i, this.state.buffer, n2, n);
    }
    
    public void update(final byte[] array) {
        this.update(array, 0, array.length);
    }
    
    private static final int rotate_left(final int n, final int n2) {
        return n << n2 | n >>> 32 - n2;
    }
    
    private static final int FF(int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        n += ((n2 & n3) | (~n2 & n4)) + n5;
        return rotate_left(n, n6);
    }
    
    private static final int GG(int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        n += ((n2 & n3) | (n2 & n4) | (n3 & n4)) + n5 + 1518500249;
        return rotate_left(n, n6);
    }
    
    private static final int HH(int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        n += (n2 ^ n3 ^ n4) + n5 + 1859775393;
        return rotate_left(n, n6);
    }
    
    private void transform(final byte[] array, final int n) {
        final int n2 = this.state.state[0];
        final int n3 = this.state.state[1];
        final int n4 = this.state.state[2];
        final int n5 = this.state.state[3];
        final int[] decode = decode(array, n, 64);
        final int ff = FF(n2, n3, n4, n5, decode[0], 3);
        final int ff2 = FF(n5, ff, n3, n4, decode[1], 7);
        final int ff3 = FF(n4, ff2, ff, n3, decode[2], 11);
        final int ff4 = FF(n3, ff3, ff2, ff, decode[3], 19);
        final int ff5 = FF(ff, ff4, ff3, ff2, decode[4], 3);
        final int ff6 = FF(ff2, ff5, ff4, ff3, decode[5], 7);
        final int ff7 = FF(ff3, ff6, ff5, ff4, decode[6], 11);
        final int ff8 = FF(ff4, ff7, ff6, ff5, decode[7], 19);
        final int ff9 = FF(ff5, ff8, ff7, ff6, decode[8], 3);
        final int ff10 = FF(ff6, ff9, ff8, ff7, decode[9], 7);
        final int ff11 = FF(ff7, ff10, ff9, ff8, decode[10], 11);
        final int ff12 = FF(ff8, ff11, ff10, ff9, decode[11], 19);
        final int ff13 = FF(ff9, ff12, ff11, ff10, decode[12], 3);
        final int ff14 = FF(ff10, ff13, ff12, ff11, decode[13], 7);
        final int ff15 = FF(ff11, ff14, ff13, ff12, decode[14], 11);
        final int ff16 = FF(ff12, ff15, ff14, ff13, decode[15], 19);
        final int gg = GG(ff13, ff16, ff15, ff14, decode[0], 3);
        final int gg2 = GG(ff14, gg, ff16, ff15, decode[4], 5);
        final int gg3 = GG(ff15, gg2, gg, ff16, decode[8], 9);
        final int gg4 = GG(ff16, gg3, gg2, gg, decode[12], 13);
        final int gg5 = GG(gg, gg4, gg3, gg2, decode[1], 3);
        final int gg6 = GG(gg2, gg5, gg4, gg3, decode[5], 5);
        final int gg7 = GG(gg3, gg6, gg5, gg4, decode[9], 9);
        final int gg8 = GG(gg4, gg7, gg6, gg5, decode[13], 13);
        final int gg9 = GG(gg5, gg8, gg7, gg6, decode[2], 3);
        final int gg10 = GG(gg6, gg9, gg8, gg7, decode[6], 5);
        final int gg11 = GG(gg7, gg10, gg9, gg8, decode[10], 9);
        final int gg12 = GG(gg8, gg11, gg10, gg9, decode[14], 13);
        final int gg13 = GG(gg9, gg12, gg11, gg10, decode[3], 3);
        final int gg14 = GG(gg10, gg13, gg12, gg11, decode[7], 5);
        final int gg15 = GG(gg11, gg14, gg13, gg12, decode[11], 9);
        final int gg16 = GG(gg12, gg15, gg14, gg13, decode[15], 13);
        final int hh = HH(gg13, gg16, gg15, gg14, decode[0], 3);
        final int hh2 = HH(gg14, hh, gg16, gg15, decode[8], 9);
        final int hh3 = HH(gg15, hh2, hh, gg16, decode[4], 11);
        final int hh4 = HH(gg16, hh3, hh2, hh, decode[12], 15);
        final int hh5 = HH(hh, hh4, hh3, hh2, decode[2], 3);
        final int hh6 = HH(hh2, hh5, hh4, hh3, decode[10], 9);
        final int hh7 = HH(hh3, hh6, hh5, hh4, decode[6], 11);
        final int hh8 = HH(hh4, hh7, hh6, hh5, decode[14], 15);
        final int hh9 = HH(hh5, hh8, hh7, hh6, decode[1], 3);
        final int hh10 = HH(hh6, hh9, hh8, hh7, decode[9], 9);
        final int hh11 = HH(hh7, hh10, hh9, hh8, decode[5], 11);
        final int hh12 = HH(hh8, hh11, hh10, hh9, decode[13], 15);
        final int hh13 = HH(hh9, hh12, hh11, hh10, decode[3], 3);
        final int hh14 = HH(hh10, hh13, hh12, hh11, decode[11], 9);
        final int hh15 = HH(hh11, hh14, hh13, hh12, decode[7], 11);
        final int hh16 = HH(hh12, hh15, hh14, hh13, decode[15], 15);
        final int[] state = this.state.state;
        final int n6 = 0;
        state[n6] += hh13;
        final int[] state2 = this.state.state;
        final int n7 = 1;
        state2[n7] += hh16;
        final int[] state3 = this.state.state;
        final int n8 = 2;
        state3[n8] += hh15;
        final int[] state4 = this.state.state;
        final int n9 = 3;
        state4[n9] += hh14;
    }
    
    public byte[] getHash() {
        if (this.final_hash != null) {
            return this.final_hash;
        }
        final int[] array = { (int)(this.state.count & -1L), (int)(this.state.count >> 32) };
        final int n = (int)(this.state.count >>> 3 & 0x3FL);
        this.update(MD4.padding, 0, (n < 56) ? (56 - n) : (120 - n));
        this.update(encode(array, 0, 2), 0, 8);
        return this.final_hash = encode(this.state.state, 0, 4);
    }
    
    private static final int[] decode(final byte[] array, final int n, final int n2) {
        final int n3 = n2 >>> 2;
        final int[] array2 = new int[n3];
        int i = 0;
        int n4 = n;
        while (i < n3) {
            array2[i] = ((array[n4++] & 0xFF) | (array[n4++] & 0xFF) << 8 | (array[n4++] & 0xFF) << 16 | (array[n4++] & 0xFF) << 24);
            ++i;
        }
        return array2;
    }
    
    private static final byte[] encode(final int[] array, final int n, final int n2) {
        final int n3 = n2 << 2;
        final byte[] array2 = new byte[n3];
        int n5;
        for (int i = 0, n4 = n; i < n3; array2[i++] = (byte)(n5 & 0xFF), array2[i++] = (byte)(n5 >> 8 & 0xFF), array2[i++] = (byte)(n5 >> 16 & 0xFF), array2[i++] = (byte)(n5 >> 24 & 0xFF), ++n4) {
            n5 = array[n4];
        }
        return array2;
    }
    
    public String toString() {
        final byte[] hash = this.getHash();
        final StringBuffer sb = new StringBuffer(hash.length * 2);
        for (int i = 0; i < hash.length; ++i) {
            final int n = hash[i] & 0xFF;
            if (n < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(n, 16));
        }
        return sb.toString();
    }
    
    public static void main(final String[] array) throws Exception {
        if (array.length > 0) {
            if (array[0].equals("-s")) {
                System.out.println("MD4 (\"" + array[1] + "\") = " + new MD4(array[1].getBytes()));
            }
            else if (array[0].equals("-x")) {
                md4TestSuite();
            }
            else {
                final FileInputStream fileInputStream = new FileInputStream(array[0]);
                final byte[] array2 = new byte[10000];
                final MD4 md4 = new MD4();
                int read;
                while ((read = fileInputStream.read(array2)) > 0) {
                    md4.update(array2, 0, read);
                }
                System.out.println("MD4 (\"" + array[0] + "\") = " + md4);
            }
        }
        else {
            System.out.println("Usage: -s <string>: print md4 hash of string");
            System.out.println("       -x: run md4 test suite");
            System.out.println("       <file>: print md4 hash of file");
        }
    }
    
    private static final void md4TestSuite() {
        final String[][] array = { { "", "31d6cfe0d16ae931b73c59d7e0c089c0" }, { "a", "bde52cb31de33e46245e05fbdbd6fb24" }, { "abc", "a448017aaf21d8525fc10ae87aa6729d" }, { "message digest", "d9130a8164549fe818874806e1c7014b" }, { "abcdefghijklmnopqrstuvwxyz", "d79e1c308aa5bbcdeea8ed63df412da9" }, { "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789", "043f8582f241db351ce627e153e7f0e4" }, { "12345678901234567890123456789012345678901234567890123456789012345678901234567890", "e33b4ddc9c38f2199c3e7b164fcc0536" } };
        for (int i = 0; i < array.length; ++i) {
            final byte[] bytes = array[i][0].getBytes();
            if (!new MD4(bytes).toString().equals(array[i][1])) {
                System.err.println("Test failed!");
                System.err.println("Input string: \"" + array[i][0] + "\"");
                System.err.println("Calculated: " + new MD4(bytes));
                System.err.println("Expected:   " + array[i][1]);
                System.exit(1);
            }
        }
        System.out.println("All tests passed successfuly");
    }
    
    static {
        padding = new byte[] { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
    
    private class MD4State
    {
        int[] state;
        long count;
        byte[] buffer;
        
        MD4State() {
            this.buffer = new byte[64];
            (this.state = new int[4])[0] = 1732584193;
            this.state[1] = -271733879;
            this.state[2] = -1732584194;
            this.state[3] = 271733878;
            this.count = 0L;
        }
    }
}
