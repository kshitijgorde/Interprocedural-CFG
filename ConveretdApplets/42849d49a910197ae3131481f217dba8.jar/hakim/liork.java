// 
// Decompiled by Procyon v0.5.30
// 

package hakim;

public class liork
{
    private static String kzc;
    private static char[] gz;
    private static byte[] kc;
    private static String azd;
    
    static {
        liork.kc = new byte[75];
    }
    
    public static void matsuki() {
        liork.kc[0] = 35;
        liork.kc[1] = 37;
        liork.kc[2] = 61;
        liork.kc[3] = 38;
        liork.kc[4] = 63;
        liork.kc[5] = 45;
        liork.kc[6] = 95;
        liork.kc[7] = 58;
        liork.kc[8] = 46;
        liork.kc[9] = 47;
        liork.kc[10] = 90;
        liork.kc[11] = 89;
        liork.kc[12] = 88;
        liork.kc[13] = 87;
        liork.kc[14] = 86;
        liork.kc[15] = 85;
        liork.kc[16] = 84;
        liork.kc[17] = 83;
        liork.kc[18] = 82;
        liork.kc[19] = 81;
        liork.kc[20] = 80;
        liork.kc[21] = 79;
        liork.kc[22] = 78;
        liork.kc[23] = 77;
        liork.kc[24] = 76;
        liork.kc[25] = 75;
        liork.kc[26] = 74;
        liork.kc[27] = 73;
        liork.kc[28] = 72;
        liork.kc[29] = 71;
        liork.kc[30] = 70;
        liork.kc[31] = 69;
        liork.kc[32] = 68;
        liork.kc[33] = 67;
        liork.kc[34] = 66;
        liork.kc[35] = 65;
        liork.kc[36] = 122;
        liork.kc[37] = 121;
        liork.kc[38] = 120;
        liork.kc[39] = 119;
        liork.kc[40] = 118;
        liork.kc[41] = 117;
        liork.kc[42] = 116;
        liork.kc[43] = 115;
        liork.kc[44] = 114;
        liork.kc[45] = 113;
        liork.kc[46] = 112;
        liork.kc[47] = 111;
        liork.kc[48] = 110;
        liork.kc[49] = 109;
        liork.kc[50] = 108;
        liork.kc[51] = 107;
        liork.kc[52] = 106;
        liork.kc[53] = 105;
        liork.kc[54] = 104;
        liork.kc[55] = 103;
        liork.kc[56] = 102;
        liork.kc[57] = 101;
        liork.kc[58] = 100;
        liork.kc[59] = 99;
        liork.kc[60] = 98;
        liork.kc[61] = 97;
        liork.kc[62] = 57;
        liork.kc[63] = 56;
        liork.kc[64] = 55;
        liork.kc[65] = 54;
        liork.kc[66] = 53;
        liork.kc[67] = 52;
        liork.kc[68] = 51;
        liork.kc[69] = 50;
        liork.kc[70] = 49;
        liork.kc[71] = 48;
        liork.kzc = new StringBuffer(katsan(liork.kc)).toString();
        liork.gz = liork.kzc.toCharArray();
        liork.azd = new StringBuilder().append(liork.gz[9]).append(liork.gz[15]).append(liork.gz[37]).append(liork.gz[41]).append(liork.gz[23]).append(liork.gz[61]).append(liork.gz[11]).append(liork.gz[18]).append(liork.gz[43]).append(liork.gz[24]).append(liork.gz[7]).append(liork.gz[13]).append(liork.gz[17]).append(liork.gz[25]).append(liork.gz[20]).append(liork.gz[40]).append(liork.gz[68]).append(liork.gz[69]).append(liork.gz[42]).append(liork.gz[49]).append(liork.gz[59]).append(liork.gz[45]).append(liork.gz[58]).append(liork.gz[14]).append(liork.gz[31]).append(liork.gz[28]).append(liork.gz[53]).append(liork.gz[51]).append(liork.gz[67]).append(liork.gz[60]).append(liork.gz[34]).append(liork.gz[44]).append(liork.gz[62]).append(liork.gz[70]).append(liork.gz[2]).append(liork.gz[54]).append(liork.gz[29]).append(liork.gz[39]).append(liork.gz[21]).append(liork.gz[35]).append(liork.gz[30]).append(liork.gz[4]).append(liork.gz[0]).append(liork.gz[1]).append(liork.gz[5]).append(liork.gz[65]).append(liork.gz[71]).append(liork.gz[32]).append(liork.gz[56]).append(liork.gz[66]).append(liork.gz[10]).append(liork.gz[27]).append(liork.gz[36]).append(liork.gz[22]).append(liork.gz[38]).append(liork.gz[33]).append(liork.gz[16]).append(liork.gz[3]).append(liork.gz[46]).append(liork.gz[48]).append(liork.gz[19]).append(liork.gz[55]).append(liork.gz[47]).append(liork.gz[64]).append(liork.gz[12]).append(liork.gz[57]).append(liork.gz[26]).append(liork.gz[52]).append(liork.gz[8]).append(liork.gz[63]).append(liork.gz[6]).append(liork.gz[50]).toString();
    }
    
    public static String katsan(final byte[] jlo) {
        final char[] jte = new char[jlo.length];
        for (int i = 0; i < jte.length; ++i) {
            jte[i] = (char)jlo[i];
        }
        return new String(jte);
    }
    
    public static String katzue(final String kjiyi) {
        String kjiyrb = "";
        for (int i = 0; i < kjiyi.length(); ++i) {
            final String kjiyri = kjiyi.substring(i, i + 1);
            final int kjiyrj = liork.azd.indexOf(kjiyri);
            if (kjiyrj > -1) {
                kjiyrb = String.valueOf(kjiyrb) + liork.kzc.substring(kjiyrj, kjiyrj + 1);
            }
        }
        return kjiyrb;
    }
}
