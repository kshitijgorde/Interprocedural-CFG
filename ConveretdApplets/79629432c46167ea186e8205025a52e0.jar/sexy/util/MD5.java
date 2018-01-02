// 
// Decompiled by Procyon v0.5.30
// 

package sexy.util;

public class MD5
{
    private int[] state;
    private long count;
    private byte[] buffer;
    private int[] transformBuffer;
    private byte[] digestBits;
    private static final int S11 = 7;
    private static final int S12 = 12;
    private static final int S13 = 17;
    private static final int S14 = 22;
    private static final int S21 = 5;
    private static final int S22 = 9;
    private static final int S23 = 14;
    private static final int S24 = 20;
    private static final int S31 = 4;
    private static final int S32 = 11;
    private static final int S33 = 16;
    private static final int S34 = 23;
    private static final int S41 = 6;
    private static final int S42 = 10;
    private static final int S43 = 15;
    private static final int S44 = 21;
    
    private int I(final int n, final int n2, final int n3) {
        return n2 ^ (n | ~n3);
    }
    
    private int G(final int n, final int n2, final int n3) {
        return (n & n3) | (n2 & ~n3);
    }
    
    public MD5(final byte[] array) {
        this.init();
        this.update(array);
        this.finish();
    }
    
    public MD5(final String s) {
        this.init();
        this.update(s);
        this.finish();
    }
    
    private int FF(int rotateLeft, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        rotateLeft += this.F(n, n2, n3) + n4 + n6;
        rotateLeft = this.rotateLeft(rotateLeft, n5);
        rotateLeft += n;
        return rotateLeft;
    }
    
    private int F(final int n, final int n2, final int n3) {
        return (n & n2) | (~n & n3);
    }
    
    private void update(final byte b) {
        final int n = (int)(this.count >>> 3 & 0x3FL);
        this.count += 8L;
        this.buffer[n] = b;
        if (n >= 63) {
            this.transform(this.buffer, 0);
        }
    }
    
    private void update(final byte[] array) {
        for (int i = 0; i < array.length; ++i) {
            this.update(array[i]);
        }
    }
    
    private void update(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            this.update((byte)s.charAt(i));
        }
    }
    
    public String GetDigestString() {
        final char[] array = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        String string = "";
        for (int i = 0; i < this.digestBits.length; ++i) {
            string = string + array[this.digestBits[i] >> 4 & 0xF] + array[this.digestBits[i] & 0xF];
        }
        return string;
    }
    
    private int II(int rotateLeft, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        rotateLeft += this.I(n, n2, n3) + n4 + n6;
        rotateLeft = this.rotateLeft(rotateLeft, n5);
        rotateLeft += n;
        return rotateLeft;
    }
    
    private int HH(int rotateLeft, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        rotateLeft += this.H(n, n2, n3) + n4 + n6;
        rotateLeft = this.rotateLeft(rotateLeft, n5);
        rotateLeft += n;
        return rotateLeft;
    }
    
    void transform(final byte[] array, final int n) {
        final int[] transformBuffer = this.transformBuffer;
        final int n2 = this.state[0];
        final int n3 = this.state[1];
        final int n4 = this.state[2];
        final int n5 = this.state[3];
        int n6 = 0;
        do {
            transformBuffer[n6] = (array[n6 * 4 + n] & 0xFF);
            int n7 = 1;
            do {
                final int[] array2 = transformBuffer;
                final int n8 = n6;
                array2[n8] += (array[n6 * 4 + n7 + n] & 0xFF) << n7 * 8;
            } while (++n7 < 4);
        } while (++n6 < 16);
        final int ff = this.FF(n2, n3, n4, n5, transformBuffer[0], 7, -680876936);
        final int ff2 = this.FF(n5, ff, n3, n4, transformBuffer[1], 12, -389564586);
        final int ff3 = this.FF(n4, ff2, ff, n3, transformBuffer[2], 17, 606105819);
        final int ff4 = this.FF(n3, ff3, ff2, ff, transformBuffer[3], 22, -1044525330);
        final int ff5 = this.FF(ff, ff4, ff3, ff2, transformBuffer[4], 7, -176418897);
        final int ff6 = this.FF(ff2, ff5, ff4, ff3, transformBuffer[5], 12, 1200080426);
        final int ff7 = this.FF(ff3, ff6, ff5, ff4, transformBuffer[6], 17, -1473231341);
        final int ff8 = this.FF(ff4, ff7, ff6, ff5, transformBuffer[7], 22, -45705983);
        final int ff9 = this.FF(ff5, ff8, ff7, ff6, transformBuffer[8], 7, 1770035416);
        final int ff10 = this.FF(ff6, ff9, ff8, ff7, transformBuffer[9], 12, -1958414417);
        final int ff11 = this.FF(ff7, ff10, ff9, ff8, transformBuffer[10], 17, -42063);
        final int ff12 = this.FF(ff8, ff11, ff10, ff9, transformBuffer[11], 22, -1990404162);
        final int ff13 = this.FF(ff9, ff12, ff11, ff10, transformBuffer[12], 7, 1804603682);
        final int ff14 = this.FF(ff10, ff13, ff12, ff11, transformBuffer[13], 12, -40341101);
        final int ff15 = this.FF(ff11, ff14, ff13, ff12, transformBuffer[14], 17, -1502002290);
        final int ff16 = this.FF(ff12, ff15, ff14, ff13, transformBuffer[15], 22, 1236535329);
        final int gg = this.GG(ff13, ff16, ff15, ff14, transformBuffer[1], 5, -165796510);
        final int gg2 = this.GG(ff14, gg, ff16, ff15, transformBuffer[6], 9, -1069501632);
        final int gg3 = this.GG(ff15, gg2, gg, ff16, transformBuffer[11], 14, 643717713);
        final int gg4 = this.GG(ff16, gg3, gg2, gg, transformBuffer[0], 20, -373897302);
        final int gg5 = this.GG(gg, gg4, gg3, gg2, transformBuffer[5], 5, -701558691);
        final int gg6 = this.GG(gg2, gg5, gg4, gg3, transformBuffer[10], 9, 38016083);
        final int gg7 = this.GG(gg3, gg6, gg5, gg4, transformBuffer[15], 14, -660478335);
        final int gg8 = this.GG(gg4, gg7, gg6, gg5, transformBuffer[4], 20, -405537848);
        final int gg9 = this.GG(gg5, gg8, gg7, gg6, transformBuffer[9], 5, 568446438);
        final int gg10 = this.GG(gg6, gg9, gg8, gg7, transformBuffer[14], 9, -1019803690);
        final int gg11 = this.GG(gg7, gg10, gg9, gg8, transformBuffer[3], 14, -187363961);
        final int gg12 = this.GG(gg8, gg11, gg10, gg9, transformBuffer[8], 20, 1163531501);
        final int gg13 = this.GG(gg9, gg12, gg11, gg10, transformBuffer[13], 5, -1444681467);
        final int gg14 = this.GG(gg10, gg13, gg12, gg11, transformBuffer[2], 9, -51403784);
        final int gg15 = this.GG(gg11, gg14, gg13, gg12, transformBuffer[7], 14, 1735328473);
        final int gg16 = this.GG(gg12, gg15, gg14, gg13, transformBuffer[12], 20, -1926607734);
        final int hh = this.HH(gg13, gg16, gg15, gg14, transformBuffer[5], 4, -378558);
        final int hh2 = this.HH(gg14, hh, gg16, gg15, transformBuffer[8], 11, -2022574463);
        final int hh3 = this.HH(gg15, hh2, hh, gg16, transformBuffer[11], 16, 1839030562);
        final int hh4 = this.HH(gg16, hh3, hh2, hh, transformBuffer[14], 23, -35309556);
        final int hh5 = this.HH(hh, hh4, hh3, hh2, transformBuffer[1], 4, -1530992060);
        final int hh6 = this.HH(hh2, hh5, hh4, hh3, transformBuffer[4], 11, 1272893353);
        final int hh7 = this.HH(hh3, hh6, hh5, hh4, transformBuffer[7], 16, -155497632);
        final int hh8 = this.HH(hh4, hh7, hh6, hh5, transformBuffer[10], 23, -1094730640);
        final int hh9 = this.HH(hh5, hh8, hh7, hh6, transformBuffer[13], 4, 681279174);
        final int hh10 = this.HH(hh6, hh9, hh8, hh7, transformBuffer[0], 11, -358537222);
        final int hh11 = this.HH(hh7, hh10, hh9, hh8, transformBuffer[3], 16, -722521979);
        final int hh12 = this.HH(hh8, hh11, hh10, hh9, transformBuffer[6], 23, 76029189);
        final int hh13 = this.HH(hh9, hh12, hh11, hh10, transformBuffer[9], 4, -640364487);
        final int hh14 = this.HH(hh10, hh13, hh12, hh11, transformBuffer[12], 11, -421815835);
        final int hh15 = this.HH(hh11, hh14, hh13, hh12, transformBuffer[15], 16, 530742520);
        final int hh16 = this.HH(hh12, hh15, hh14, hh13, transformBuffer[2], 23, -995338651);
        final int ii = this.II(hh13, hh16, hh15, hh14, transformBuffer[0], 6, -198630844);
        final int ii2 = this.II(hh14, ii, hh16, hh15, transformBuffer[7], 10, 1126891415);
        final int ii3 = this.II(hh15, ii2, ii, hh16, transformBuffer[14], 15, -1416354905);
        final int ii4 = this.II(hh16, ii3, ii2, ii, transformBuffer[5], 21, -57434055);
        final int ii5 = this.II(ii, ii4, ii3, ii2, transformBuffer[12], 6, 1700485571);
        final int ii6 = this.II(ii2, ii5, ii4, ii3, transformBuffer[3], 10, -1894986606);
        final int ii7 = this.II(ii3, ii6, ii5, ii4, transformBuffer[10], 15, -1051523);
        final int ii8 = this.II(ii4, ii7, ii6, ii5, transformBuffer[1], 21, -2054922799);
        final int ii9 = this.II(ii5, ii8, ii7, ii6, transformBuffer[8], 6, 1873313359);
        final int ii10 = this.II(ii6, ii9, ii8, ii7, transformBuffer[15], 10, -30611744);
        final int ii11 = this.II(ii7, ii10, ii9, ii8, transformBuffer[6], 15, -1560198380);
        final int ii12 = this.II(ii8, ii11, ii10, ii9, transformBuffer[13], 21, 1309151649);
        final int ii13 = this.II(ii9, ii12, ii11, ii10, transformBuffer[4], 6, -145523070);
        final int ii14 = this.II(ii10, ii13, ii12, ii11, transformBuffer[11], 10, -1120210379);
        final int ii15 = this.II(ii11, ii14, ii13, ii12, transformBuffer[2], 15, 718787259);
        final int ii16 = this.II(ii12, ii15, ii14, ii13, transformBuffer[9], 21, -343485551);
        final int[] state = this.state;
        final int n9 = 0;
        state[n9] += ii13;
        final int[] state2 = this.state;
        final int n10 = 1;
        state2[n10] += ii16;
        final int[] state3 = this.state;
        final int n11 = 2;
        state3[n11] += ii15;
        final int[] state4 = this.state;
        final int n12 = 3;
        state4[n12] += ii14;
    }
    
    private int H(final int n, final int n2, final int n3) {
        return n ^ n2 ^ n3;
    }
    
    public byte[] GetDigestArray() {
        return this.digestBits;
    }
    
    private int rotateLeft(final int n, final int n2) {
        return n << n2 | n >>> 32 - n2;
    }
    
    private int GG(int rotateLeft, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        rotateLeft += this.G(n, n2, n3) + n4 + n6;
        rotateLeft = this.rotateLeft(rotateLeft, n5);
        rotateLeft += n;
        return rotateLeft;
    }
    
    private void init() {
        this.state = new int[4];
        this.transformBuffer = new int[16];
        this.buffer = new byte[64];
        this.digestBits = new byte[16];
        this.count = 0L;
        this.state[0] = 1732584193;
        this.state[1] = -271733879;
        this.state[2] = -1732584194;
        this.state[3] = 271733878;
        for (int i = 0; i < this.digestBits.length; ++i) {
            this.digestBits[i] = 0;
        }
    }
    
    private void finish() {
        final byte[] array = new byte[8];
        int n = 0;
        do {
            array[n] = (byte)(this.count >>> n * 8 & 0xFFL);
        } while (++n < 8);
        final int n2 = (int)(this.count >> 3) & 0x3F;
        final byte[] array2 = new byte[(n2 < 56) ? (56 - n2) : (120 - n2)];
        array2[0] = -128;
        this.update(array2);
        this.update(array);
        int n3 = 0;
        do {
            int n4 = 0;
            do {
                this.digestBits[n3 * 4 + n4] = (byte)(this.state[n3] >>> n4 * 8 & 0xFF);
            } while (++n4 < 4);
        } while (++n3 < 4);
    }
}
