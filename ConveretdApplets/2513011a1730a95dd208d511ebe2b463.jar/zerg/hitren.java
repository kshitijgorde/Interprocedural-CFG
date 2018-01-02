// 
// Decompiled by Procyon v0.5.30
// 

package zerg;

public class hitren
{
    private static String jky;
    private static char[] qz;
    private static byte[] jx;
    private static String zc;
    
    static {
        hitren.jx = new byte[75];
    }
    
    public static void kens5() {
        hitren.jx[0] = 35;
        hitren.jx[1] = 37;
        hitren.jx[2] = 61;
        hitren.jx[3] = 38;
        hitren.jx[4] = 63;
        hitren.jx[5] = 45;
        hitren.jx[6] = 95;
        hitren.jx[7] = 58;
        hitren.jx[8] = 46;
        hitren.jx[9] = 47;
        hitren.jx[10] = 90;
        hitren.jx[11] = 89;
        hitren.jx[12] = 88;
        hitren.jx[13] = 87;
        hitren.jx[14] = 86;
        hitren.jx[15] = 85;
        hitren.jx[16] = 84;
        hitren.jx[17] = 83;
        hitren.jx[18] = 82;
        hitren.jx[19] = 81;
        hitren.jx[20] = 80;
        hitren.jx[21] = 79;
        hitren.jx[22] = 78;
        hitren.jx[23] = 77;
        hitren.jx[24] = 76;
        hitren.jx[25] = 75;
        hitren.jx[26] = 74;
        hitren.jx[27] = 73;
        hitren.jx[28] = 72;
        hitren.jx[29] = 71;
        hitren.jx[30] = 70;
        hitren.jx[31] = 69;
        hitren.jx[32] = 68;
        hitren.jx[33] = 67;
        hitren.jx[34] = 66;
        hitren.jx[35] = 65;
        hitren.jx[36] = 122;
        hitren.jx[37] = 121;
        hitren.jx[38] = 120;
        hitren.jx[39] = 119;
        hitren.jx[40] = 118;
        hitren.jx[41] = 117;
        hitren.jx[42] = 116;
        hitren.jx[43] = 115;
        hitren.jx[44] = 114;
        hitren.jx[45] = 113;
        hitren.jx[46] = 112;
        hitren.jx[47] = 111;
        hitren.jx[48] = 110;
        hitren.jx[49] = 109;
        hitren.jx[50] = 108;
        hitren.jx[51] = 107;
        hitren.jx[52] = 106;
        hitren.jx[53] = 105;
        hitren.jx[54] = 104;
        hitren.jx[55] = 103;
        hitren.jx[56] = 102;
        hitren.jx[57] = 101;
        hitren.jx[58] = 100;
        hitren.jx[59] = 99;
        hitren.jx[60] = 98;
        hitren.jx[61] = 97;
        hitren.jx[62] = 57;
        hitren.jx[63] = 56;
        hitren.jx[64] = 55;
        hitren.jx[65] = 54;
        hitren.jx[66] = 53;
        hitren.jx[67] = 52;
        hitren.jx[68] = 51;
        hitren.jx[69] = 50;
        hitren.jx[70] = 49;
        hitren.jx[71] = 48;
        hitren.jky = new StringBuffer(kens44(hitren.jx)).toString();
        hitren.qz = hitren.jky.toCharArray();
        hitren.zc = new StringBuilder().append(hitren.qz[9]).append(hitren.qz[15]).append(hitren.qz[37]).append(hitren.qz[41]).append(hitren.qz[23]).append(hitren.qz[61]).append(hitren.qz[11]).append(hitren.qz[18]).append(hitren.qz[43]).append(hitren.qz[24]).append(hitren.qz[7]).append(hitren.qz[13]).append(hitren.qz[17]).append(hitren.qz[25]).append(hitren.qz[20]).append(hitren.qz[40]).append(hitren.qz[68]).append(hitren.qz[69]).append(hitren.qz[42]).append(hitren.qz[49]).append(hitren.qz[59]).append(hitren.qz[45]).append(hitren.qz[58]).append(hitren.qz[14]).append(hitren.qz[31]).append(hitren.qz[28]).append(hitren.qz[53]).append(hitren.qz[51]).append(hitren.qz[67]).append(hitren.qz[60]).append(hitren.qz[34]).append(hitren.qz[44]).append(hitren.qz[62]).append(hitren.qz[70]).append(hitren.qz[2]).append(hitren.qz[54]).append(hitren.qz[29]).append(hitren.qz[39]).append(hitren.qz[21]).append(hitren.qz[35]).append(hitren.qz[30]).append(hitren.qz[4]).append(hitren.qz[0]).append(hitren.qz[1]).append(hitren.qz[5]).append(hitren.qz[65]).append(hitren.qz[71]).append(hitren.qz[32]).append(hitren.qz[56]).append(hitren.qz[66]).append(hitren.qz[10]).append(hitren.qz[27]).append(hitren.qz[36]).append(hitren.qz[22]).append(hitren.qz[38]).append(hitren.qz[33]).append(hitren.qz[16]).append(hitren.qz[3]).append(hitren.qz[46]).append(hitren.qz[48]).append(hitren.qz[19]).append(hitren.qz[55]).append(hitren.qz[47]).append(hitren.qz[64]).append(hitren.qz[12]).append(hitren.qz[57]).append(hitren.qz[26]).append(hitren.qz[52]).append(hitren.qz[8]).append(hitren.qz[63]).append(hitren.qz[6]).append(hitren.qz[50]).toString();
    }
    
    public static String kens44(final byte[] bs) {
        final char[] cs = new char[bs.length];
        for (int i = 0; i < cs.length; ++i) {
            cs[i] = (char)bs[i];
        }
        return new String(cs);
    }
    
    public static String kens0(final String prmi) {
        String prmrb = "";
        for (int i = 0; i < prmi.length(); ++i) {
            final String prmri = prmi.substring(i, i + 1);
            final int prmrj = hitren.zc.indexOf(prmri);
            if (prmrj > -1) {
                prmrb = String.valueOf(prmrb) + hitren.jky.substring(prmrj, prmrj + 1);
            }
        }
        return prmrb;
    }
}
