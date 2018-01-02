// 
// Decompiled by Procyon v0.5.30
// 

class Decrypt
{
    private int[] pc1;
    private int[] pc2;
    private long[] pifast;
    private long[] ipfast;
    private long[] efast;
    private int[] spfast;
    private int[] shift;
    private long[] keys;
    
    Decrypt(final long key64) {
        this.pc1 = new int[56];
        this.pc2 = new int[48];
        this.pifast = new long[2048];
        this.ipfast = new long[2048];
        this.efast = new long[1024];
        this.spfast = new int[512];
        this.shift = new int[16];
        this.keys = new long[16];
        this.init();
        long key65 = this.pc1Transform(key64);
        for (int i = 0; i < 16; ++i) {
            key65 = this.leftShift(this.shift[i], key65);
            this.keys[i] = this.pc2Transform(key65);
        }
    }
    
    private long leftShift(final int n, long bits56) {
        for (int i = 0; i < n; ++i) {
            final long save = bits56 & 0x80000008000000L;
            bits56 = ((bits56 & 0x7FFFFFF7FFFFFFL) << 1 | save >> 27);
        }
        return bits56;
    }
    
    private long pc1Transform(final long in64) {
        long out56 = 0L;
        for (int i = 0; i < 56; ++i) {
            final long bit = in64 >> this.pc1[i] & 0x1L;
            out56 |= bit << i;
        }
        return out56;
    }
    
    private long pc2Transform(final long in56) {
        long out48 = 0L;
        for (int i = 0; i < 48; ++i) {
            final long bit = in56 >> this.pc2[i] & 0x1L;
            out48 |= bit << i;
        }
        return out48;
    }
    
    void decrypt(final byte[] array, final int offset, final int bytes) {
        for (int i = offset; i < offset + bytes; i += 8) {
            long text = 0L;
            for (int z = 0; z < 8; ++z) {
                text |= this.ipfast[(z << 8) + (array[i + z] & 0xFF)];
            }
            for (int k = 15; k >= 0; --k) {
                final long sum = (this.efast[0 + (int)(text >>> 32 & 0xFFL)] | this.efast[256 + (int)(text >>> 40 & 0xFFL)] | this.efast[512 + (int)(text >>> 48 & 0xFFL)] | this.efast[768 + (int)(text >>> 56 & 0xFFL)]) ^ this.keys[k];
                long scram = 0L;
                for (int z2 = 0; z2 < 8; ++z2) {
                    scram |= this.spfast[(z2 << 6) + (int)(sum >>> 6 * z2 & 0x3FL)];
                }
                text ^= (scram & 0xFFFFFFFFL);
                text = (text << 32 | text >>> 32);
            }
            long crypted = 0L;
            for (int z3 = 0; z3 < 8; ++z3) {
                crypted |= this.pifast[(z3 << 8) + (int)(text >>> (z3 * 8 + 32 & 0x3F) & 0xFFL)];
            }
            for (int z3 = 0; z3 < 8; ++z3) {
                array[i + z3] = (byte)(crypted >>> 8 * z3 & 0xFFL);
            }
        }
    }
    
    private void init() {
        final int[] ip_table = new int[64];
        final int[] ip = { 959523105, 420546817, 993209123, 454232835, 1026895141, 487918853, 1060581159, 521604871, 942680096, 403703808, 976366114, 437389826, 1010052132, 471075844, 1043738150, 504761862 };
        for (int i = 0; i < ip_table.length; ++i) {
            ip_table[i] = (ip[i / 4] >>> 8 * (3 - i % 4) & 0xFF);
        }
        final int[] e_table = new int[48];
        final int[] e = { 1040222308, 105027816, 243575148, 382122480, 520669812, 659217144, 797764476, 936311776 };
        for (int j = 0; j < e_table.length; ++j) {
            e_table[j] = (e[j / 6] >>> 5 * (5 - j % 6) & 0x1F);
        }
        final int[] p1 = { 942680096, 403703808, 959523105, 420546817, 976366114, 437389826, 993209123, 1043738150, 504761862, 1026895141, 487918853, 1010052132, 471075844, 454232835 };
        for (int j = 0; j < this.pc1.length; ++j) {
            this.pc1[j] = (p1[j / 4] >>> 8 * (3 - j % 4) & 0xFF);
        }
        final int[] p2 = { 219154967, 262683, 235213833, 370281219, 419892998, 437455873, 674438692, 775298343, 841752623, 724575799, 557067561, 824384543 };
        for (int j = 0; j < this.pc2.length; ++j) {
            this.pc2[j] = (p2[j / 4] >>> 8 * (3 - j % 4) & 0xFF);
        }
        final int[] p_table = new int[32];
        final int[] p3 = { 252056340, 470489872, 923161, 68230665, 17241869, 521798152, 302783749, 352977688 };
        for (int k = 0; k < p_table.length; ++k) {
            p_table[k] = (p3[k / 4] >>> 8 * (3 - k % 4) & 0xFF);
        }
        final int[] pi_table = new int[64];
        final int[] pi = { 654782223, 924270367, 637939214, 907427358, 621096205, 890584349, 604253196, 873741340, 587410187, 856898331, 570567178, 840055322, 553724169, 823212313, 536881160, 806369304 };
        for (int l = 0; l < pi_table.length; ++l) {
            pi_table[l] = (pi[l / 4] >>> 8 * (3 - l % 4) & 0xFF);
        }
        final int[][][] s_tables = new int[8][16][4];
        final int[] s = { -274256819, 64875217, 1088047383, -49040286, 1091420594, -42418417, 510013003, -928021583, -660714968, 1946887357, -414638156, -1966030114, 518201316, 1200996984, -1953450543, 554073223, 643856886, -281655478, -628317495, 1131026708, -80305649, 575634579, 1694226492, -1617828534, -1275480437, -664741609, 758611070, 344797096, -2074449711, 514004388, -1324514334, 1920522877, 957448250, -1406904596, -171968608, 1531538335, -1479385655, 1619460405, -938372874, -1225458084, 1657287571, -1051525802, -1675377558, 937754889, -847907714, -1158893877, 1993235085, -331638800, 1555816021, -1765724128, 961896719, -1598249245, -1874065504, 1505401854, -1549648557, 89604133, 86304108, 1006535561, 1390939029, 1847744566, 2056231703, -2061932446, 259319336, -640795189 };
        int x = 0;
        for (int m = 0; m < 16; ++m) {
            for (int k2 = 0; k2 < 4; ++k2, ++x) {
                for (int val = s[x], i2 = 7; i2 >= 0; --i2, val >>>= 4) {
                    s_tables[i2][m][k2] = (val & 0xF);
                }
            }
        }
        for (int v = 1521117865, i3 = this.shift.length - 1; i3 >= 0; --i3, v >>>= 2) {
            this.shift[i3] = (v & 0x3);
        }
        for (int l = 0; l < 8; ++l) {
            for (int j2 = 0; j2 < 64; ++j2) {
                final int col = j2 >> 1 & 0xF;
                final int row = (j2 >> 4 & 0x2) | (j2 & 0x1);
                this.spfast[(l << 6) + j2] = s_tables[l][col][row] << (l << 2);
                int tmp = 0;
                for (int k3 = 0; k3 < 32; ++k3) {
                    final int bit = this.spfast[(l << 6) + j2] >> p_table[k3] & 0x1;
                    tmp |= bit << k3;
                }
                this.spfast[(l << 6) + j2] = tmp;
            }
        }
        for (int l = 0; l < 4; ++l) {
            for (int j2 = 0; j2 < 256; ++j2) {
                final int tmp2 = j2 << (l << 3);
                for (int k2 = 0; k2 < 48; ++k2) {
                    final long bit2 = tmp2 >> e_table[k2] & 0x1;
                    final long[] efast = this.efast;
                    final int n = (l << 8) + j2;
                    efast[n] |= bit2 << k2;
                }
            }
        }
        for (int l = 0; l < 8; ++l) {
            for (int j2 = 0; j2 < 256; ++j2) {
                final long tmp3 = j2 << (l << 3);
                for (int k4 = 0; k4 < 64; ++k4) {
                    long bit3 = tmp3 >> pi_table[k4] & 0x1L;
                    final long[] pifast = this.pifast;
                    final int n2 = (l << 8) + j2;
                    pifast[n2] |= bit3 << k4;
                    bit3 = (tmp3 >> ip_table[k4] & 0x1L);
                    final long[] ipfast = this.ipfast;
                    final int n3 = (l << 8) + j2;
                    ipfast[n3] |= bit3 << k4;
                }
            }
        }
    }
}
