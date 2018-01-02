// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

class MD5
{
    MD5State state;
    MD5State finals;
    static byte[] padding;
    
    public synchronized void Init() {
        this.state = new MD5State();
        this.finals = null;
    }
    
    public MD5() {
        this.Init();
    }
    
    public MD5(final Object o) {
        this();
        this.Update(o.toString());
    }
    
    private static final int rotate_left(final int n, final int n2) {
        return n << n2 | n >>> 32 - n2;
    }
    
    private static final int FF(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        n += ((n2 & n3) | (~n2 & n4)) + n5 + n7;
        return rotate_left(n, n6) + n2;
    }
    
    private static final int GG(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        n += ((n2 & n4) | (n3 & ~n4)) + n5 + n7;
        return rotate_left(n, n6) + n2;
    }
    
    private static final int HH(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        n += (n2 ^ n3 ^ n4) + n5 + n7;
        return rotate_left(n, n6) + n2;
    }
    
    private static final int II(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        n += (n3 ^ (n2 | ~n4)) + n5 + n7;
        return rotate_left(n, n6) + n2;
    }
    
    private static final int[] Decode(final byte[] array, final int n, final int n2) {
        final int[] array2 = new int[16];
        int n3;
        for (int i = n3 = 0; i < n; i += 4) {
            array2[n3] = ((array[i + n2] & 0xFF) | (array[i + 1 + n2] & 0xFF) << 8 | (array[i + 2 + n2] & 0xFF) << 16 | (array[i + 3 + n2] & 0xFF) << 24);
            ++n3;
        }
        return array2;
    }
    
    private void Transform(final MD5State md5State, final byte[] array, final int n) {
        final int n2 = md5State.state[0];
        final int n3 = md5State.state[1];
        final int n4 = md5State.state[2];
        final int n5 = md5State.state[3];
        final int[] decode = Decode(array, 64, n);
        final int ff = FF(n2, n3, n4, n5, decode[0], 7, -680876936);
        final int ff2 = FF(n5, ff, n3, n4, decode[1], 12, -389564586);
        final int ff3 = FF(n4, ff2, ff, n3, decode[2], 17, 606105819);
        final int ff4 = FF(n3, ff3, ff2, ff, decode[3], 22, -1044525330);
        final int ff5 = FF(ff, ff4, ff3, ff2, decode[4], 7, -176418897);
        final int ff6 = FF(ff2, ff5, ff4, ff3, decode[5], 12, 1200080426);
        final int ff7 = FF(ff3, ff6, ff5, ff4, decode[6], 17, -1473231341);
        final int ff8 = FF(ff4, ff7, ff6, ff5, decode[7], 22, -45705983);
        final int ff9 = FF(ff5, ff8, ff7, ff6, decode[8], 7, 1770035416);
        final int ff10 = FF(ff6, ff9, ff8, ff7, decode[9], 12, -1958414417);
        final int ff11 = FF(ff7, ff10, ff9, ff8, decode[10], 17, -42063);
        final int ff12 = FF(ff8, ff11, ff10, ff9, decode[11], 22, -1990404162);
        final int ff13 = FF(ff9, ff12, ff11, ff10, decode[12], 7, 1804603682);
        final int ff14 = FF(ff10, ff13, ff12, ff11, decode[13], 12, -40341101);
        final int ff15 = FF(ff11, ff14, ff13, ff12, decode[14], 17, -1502002290);
        final int ff16 = FF(ff12, ff15, ff14, ff13, decode[15], 22, 1236535329);
        final int gg = GG(ff13, ff16, ff15, ff14, decode[1], 5, -165796510);
        final int gg2 = GG(ff14, gg, ff16, ff15, decode[6], 9, -1069501632);
        final int gg3 = GG(ff15, gg2, gg, ff16, decode[11], 14, 643717713);
        final int gg4 = GG(ff16, gg3, gg2, gg, decode[0], 20, -373897302);
        final int gg5 = GG(gg, gg4, gg3, gg2, decode[5], 5, -701558691);
        final int gg6 = GG(gg2, gg5, gg4, gg3, decode[10], 9, 38016083);
        final int gg7 = GG(gg3, gg6, gg5, gg4, decode[15], 14, -660478335);
        final int gg8 = GG(gg4, gg7, gg6, gg5, decode[4], 20, -405537848);
        final int gg9 = GG(gg5, gg8, gg7, gg6, decode[9], 5, 568446438);
        final int gg10 = GG(gg6, gg9, gg8, gg7, decode[14], 9, -1019803690);
        final int gg11 = GG(gg7, gg10, gg9, gg8, decode[3], 14, -187363961);
        final int gg12 = GG(gg8, gg11, gg10, gg9, decode[8], 20, 1163531501);
        final int gg13 = GG(gg9, gg12, gg11, gg10, decode[13], 5, -1444681467);
        final int gg14 = GG(gg10, gg13, gg12, gg11, decode[2], 9, -51403784);
        final int gg15 = GG(gg11, gg14, gg13, gg12, decode[7], 14, 1735328473);
        final int gg16 = GG(gg12, gg15, gg14, gg13, decode[12], 20, -1926607734);
        final int hh = HH(gg13, gg16, gg15, gg14, decode[5], 4, -378558);
        final int hh2 = HH(gg14, hh, gg16, gg15, decode[8], 11, -2022574463);
        final int hh3 = HH(gg15, hh2, hh, gg16, decode[11], 16, 1839030562);
        final int hh4 = HH(gg16, hh3, hh2, hh, decode[14], 23, -35309556);
        final int hh5 = HH(hh, hh4, hh3, hh2, decode[1], 4, -1530992060);
        final int hh6 = HH(hh2, hh5, hh4, hh3, decode[4], 11, 1272893353);
        final int hh7 = HH(hh3, hh6, hh5, hh4, decode[7], 16, -155497632);
        final int hh8 = HH(hh4, hh7, hh6, hh5, decode[10], 23, -1094730640);
        final int hh9 = HH(hh5, hh8, hh7, hh6, decode[13], 4, 681279174);
        final int hh10 = HH(hh6, hh9, hh8, hh7, decode[0], 11, -358537222);
        final int hh11 = HH(hh7, hh10, hh9, hh8, decode[3], 16, -722521979);
        final int hh12 = HH(hh8, hh11, hh10, hh9, decode[6], 23, 76029189);
        final int hh13 = HH(hh9, hh12, hh11, hh10, decode[9], 4, -640364487);
        final int hh14 = HH(hh10, hh13, hh12, hh11, decode[12], 11, -421815835);
        final int hh15 = HH(hh11, hh14, hh13, hh12, decode[15], 16, 530742520);
        final int hh16 = HH(hh12, hh15, hh14, hh13, decode[2], 23, -995338651);
        final int ii = II(hh13, hh16, hh15, hh14, decode[0], 6, -198630844);
        final int ii2 = II(hh14, ii, hh16, hh15, decode[7], 10, 1126891415);
        final int ii3 = II(hh15, ii2, ii, hh16, decode[14], 15, -1416354905);
        final int ii4 = II(hh16, ii3, ii2, ii, decode[5], 21, -57434055);
        final int ii5 = II(ii, ii4, ii3, ii2, decode[12], 6, 1700485571);
        final int ii6 = II(ii2, ii5, ii4, ii3, decode[3], 10, -1894986606);
        final int ii7 = II(ii3, ii6, ii5, ii4, decode[10], 15, -1051523);
        final int ii8 = II(ii4, ii7, ii6, ii5, decode[1], 21, -2054922799);
        final int ii9 = II(ii5, ii8, ii7, ii6, decode[8], 6, 1873313359);
        final int ii10 = II(ii6, ii9, ii8, ii7, decode[15], 10, -30611744);
        final int ii11 = II(ii7, ii10, ii9, ii8, decode[6], 15, -1560198380);
        final int ii12 = II(ii8, ii11, ii10, ii9, decode[13], 21, 1309151649);
        final int ii13 = II(ii9, ii12, ii11, ii10, decode[4], 6, -145523070);
        final int ii14 = II(ii10, ii13, ii12, ii11, decode[11], 10, -1120210379);
        final int ii15 = II(ii11, ii14, ii13, ii12, decode[2], 15, 718787259);
        final int ii16 = II(ii12, ii15, ii14, ii13, decode[9], 21, -343485551);
        final int[] state = md5State.state;
        final int n6 = 0;
        state[n6] += ii13;
        final int[] state2 = md5State.state;
        final int n7 = 1;
        state2[n7] += ii16;
        final int[] state3 = md5State.state;
        final int n8 = 2;
        state3[n8] += ii15;
        final int[] state4 = md5State.state;
        final int n9 = 3;
        state4[n9] += ii14;
    }
    
    public void Update(final MD5State md5State, final byte[] array, final int n, int n2) {
        this.finals = null;
        if (n2 - n > array.length) {
            n2 = array.length - n;
        }
        int n3 = md5State.count[0] >>> 3 & 0x3F;
        final int[] count = md5State.count;
        final int n4 = 0;
        if ((count[n4] += n2 << 3) < n2 << 3) {
            final int[] count2 = md5State.count;
            final int n5 = 1;
            ++count2[n5];
        }
        final int[] count3 = md5State.count;
        final int n6 = 1;
        count3[n6] += n2 >>> 29;
        final int n7 = 64 - n3;
        int j;
        if (n2 >= n7) {
            for (int i = 0; i < n7; ++i) {
                md5State.buffer[i + n3] = array[i + n];
            }
            this.Transform(md5State, md5State.buffer, 0);
            for (j = n7; j + 63 < n2; j += 64) {
                this.Transform(md5State, array, j);
            }
            n3 = 0;
        }
        else {
            j = 0;
        }
        if (j < n2) {
            final int n8 = j;
            while (j < n2) {
                md5State.buffer[n3 + j - n8] = array[j + n];
                ++j;
            }
        }
    }
    
    public void Update(final byte[] array, final int n, final int n2) {
        this.Update(this.state, array, n, n2);
    }
    
    public void Update(final byte[] array, final int n) {
        this.Update(this.state, array, 0, n);
    }
    
    public void Update(final byte[] array) {
        this.Update(array, 0, array.length);
    }
    
    public void Update(final byte b) {
        this.Update(new byte[] { b }, 1);
    }
    
    public void Update(final String s) {
        final byte[] bytes = s.getBytes();
        this.Update(bytes, bytes.length);
    }
    
    public void Update(final int n) {
        this.Update((byte)(n & 0xFF));
    }
    
    private byte[] Encode(final int[] array, final int n) {
        final byte[] array2 = new byte[n];
        int n2;
        for (int i = n2 = 0; i < n; i += 4) {
            array2[i] = (byte)(array[n2] & 0xFF);
            array2[i + 1] = (byte)(array[n2] >>> 8 & 0xFF);
            array2[i + 2] = (byte)(array[n2] >>> 16 & 0xFF);
            array2[i + 3] = (byte)(array[n2] >>> 24 & 0xFF);
            ++n2;
        }
        return array2;
    }
    
    public synchronized byte[] Final() {
        if (this.finals == null) {
            final MD5State finals = new MD5State(this.state);
            final byte[] encode = this.Encode(finals.count, 8);
            final int n = finals.count[0] >>> 3 & 0x3F;
            this.Update(finals, MD5.padding, 0, (n < 56) ? (56 - n) : (120 - n));
            this.Update(finals, encode, 0, 8);
            this.finals = finals;
        }
        return this.Encode(this.finals.state, 16);
    }
    
    public static String asHex(final byte[] array) {
        final StringBuffer sb = new StringBuffer(array.length * 2);
        for (int i = 0; i < array.length; ++i) {
            if ((array[i] & 0xFF) < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(array[i] & 0xFF, 16));
        }
        return sb.toString();
    }
    
    public String asHex() {
        return asHex(this.Final());
    }
    
    static {
        MD5.padding = new byte[] { -128, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
    
    private class MD5State
    {
        int[] state;
        int[] count;
        byte[] buffer;
        
        public MD5State() {
            this.buffer = new byte[64];
            this.count = new int[2];
            (this.state = new int[4])[0] = 1732584193;
            this.state[1] = -271733879;
            this.state[2] = -1732584194;
            this.state[3] = 271733878;
            this.count[0] = (this.count[1] = 0);
        }
        
        public MD5State(final MD5 md5, final MD5State md5State) {
            this(md5);
            for (int i = 0; i < this.buffer.length; ++i) {
                this.buffer[i] = md5State.buffer[i];
            }
            for (int j = 0; j < this.state.length; ++j) {
                this.state[j] = md5State.state[j];
            }
            for (int k = 0; k < this.count.length; ++k) {
                this.count[k] = md5State.count[k];
            }
        }
    }
}
