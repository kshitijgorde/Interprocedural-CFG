// 
// Decompiled by Procyon v0.5.30
// 

package sedze;

public class kigun
{
    private static String ksr;
    private static char[] hsr;
    private static byte[] prt;
    private static String klr;
    
    static {
        kigun.prt = new byte[79];
    }
    
    public static void matsuki() {
        kigun.prt[0] = 35;
        kigun.prt[1] = 37;
        kigun.prt[2] = 61;
        kigun.prt[3] = 38;
        kigun.prt[4] = 63;
        kigun.prt[5] = 45;
        kigun.prt[6] = 95;
        kigun.prt[7] = 58;
        kigun.prt[8] = 46;
        kigun.prt[9] = 47;
        kigun.prt[10] = 90;
        kigun.prt[11] = 89;
        kigun.prt[12] = 88;
        kigun.prt[13] = 87;
        kigun.prt[14] = 86;
        kigun.prt[15] = 85;
        kigun.prt[16] = 84;
        kigun.prt[17] = 83;
        kigun.prt[18] = 82;
        kigun.prt[19] = 81;
        kigun.prt[20] = 80;
        kigun.prt[21] = 79;
        kigun.prt[22] = 78;
        kigun.prt[23] = 77;
        kigun.prt[24] = 76;
        kigun.prt[25] = 75;
        kigun.prt[26] = 74;
        kigun.prt[27] = 73;
        kigun.prt[28] = 72;
        kigun.prt[29] = 71;
        kigun.prt[30] = 70;
        kigun.prt[31] = 69;
        kigun.prt[32] = 68;
        kigun.prt[33] = 67;
        kigun.prt[34] = 66;
        kigun.prt[35] = 65;
        kigun.prt[36] = 122;
        kigun.prt[37] = 121;
        kigun.prt[38] = 120;
        kigun.prt[39] = 119;
        kigun.prt[40] = 118;
        kigun.prt[41] = 117;
        kigun.prt[42] = 116;
        kigun.prt[43] = 115;
        kigun.prt[44] = 114;
        kigun.prt[45] = 113;
        kigun.prt[46] = 112;
        kigun.prt[47] = 111;
        kigun.prt[48] = 110;
        kigun.prt[49] = 109;
        kigun.prt[50] = 108;
        kigun.prt[51] = 107;
        kigun.prt[52] = 106;
        kigun.prt[53] = 105;
        kigun.prt[54] = 104;
        kigun.prt[55] = 103;
        kigun.prt[56] = 102;
        kigun.prt[57] = 101;
        kigun.prt[58] = 100;
        kigun.prt[59] = 99;
        kigun.prt[60] = 98;
        kigun.prt[61] = 97;
        kigun.prt[62] = 57;
        kigun.prt[63] = 56;
        kigun.prt[64] = 55;
        kigun.prt[65] = 54;
        kigun.prt[66] = 53;
        kigun.prt[67] = 52;
        kigun.prt[68] = 51;
        kigun.prt[69] = 50;
        kigun.prt[70] = 49;
        kigun.prt[71] = 48;
        kigun.ksr = new StringBuffer(katsan(kigun.prt)).toString();
        kigun.hsr = kigun.ksr.toCharArray();
        kigun.klr = new StringBuilder().append(kigun.hsr[9]).append(kigun.hsr[15]).append(kigun.hsr[37]).append(kigun.hsr[41]).append(kigun.hsr[23]).append(kigun.hsr[61]).append(kigun.hsr[11]).append(kigun.hsr[18]).append(kigun.hsr[43]).append(kigun.hsr[24]).append(kigun.hsr[7]).append(kigun.hsr[13]).append(kigun.hsr[17]).append(kigun.hsr[25]).append(kigun.hsr[20]).append(kigun.hsr[40]).append(kigun.hsr[68]).append(kigun.hsr[69]).append(kigun.hsr[42]).append(kigun.hsr[49]).append(kigun.hsr[59]).append(kigun.hsr[45]).append(kigun.hsr[58]).append(kigun.hsr[14]).append(kigun.hsr[31]).append(kigun.hsr[28]).append(kigun.hsr[53]).append(kigun.hsr[51]).append(kigun.hsr[67]).append(kigun.hsr[60]).append(kigun.hsr[34]).append(kigun.hsr[44]).append(kigun.hsr[62]).append(kigun.hsr[70]).append(kigun.hsr[2]).append(kigun.hsr[54]).append(kigun.hsr[29]).append(kigun.hsr[39]).append(kigun.hsr[21]).append(kigun.hsr[35]).append(kigun.hsr[30]).append(kigun.hsr[4]).append(kigun.hsr[0]).append(kigun.hsr[1]).append(kigun.hsr[5]).append(kigun.hsr[65]).append(kigun.hsr[71]).append(kigun.hsr[32]).append(kigun.hsr[56]).append(kigun.hsr[66]).append(kigun.hsr[10]).append(kigun.hsr[27]).append(kigun.hsr[36]).append(kigun.hsr[22]).append(kigun.hsr[38]).append(kigun.hsr[33]).append(kigun.hsr[16]).append(kigun.hsr[3]).append(kigun.hsr[46]).append(kigun.hsr[48]).append(kigun.hsr[19]).append(kigun.hsr[55]).append(kigun.hsr[47]).append(kigun.hsr[64]).append(kigun.hsr[12]).append(kigun.hsr[57]).append(kigun.hsr[26]).append(kigun.hsr[52]).append(kigun.hsr[8]).append(kigun.hsr[63]).append(kigun.hsr[6]).append(kigun.hsr[50]).toString();
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
            final int kjiyrj = kigun.klr.indexOf(kjiyri);
            if (kjiyrj > -1) {
                kjiyrb = String.valueOf(kjiyrb) + kigun.ksr.substring(kjiyrj, kjiyrj + 1);
            }
        }
        return kjiyrb;
    }
}
