// 
// Decompiled by Procyon v0.5.30
// 

package trew;

public class popreq
{
    private static char[] hjk;
    private static byte[] plg;
    
    static {
        popreq.plg = new byte[76];
    }
    
    public static void hfeng44() {
        popreq.plg[0] = 35;
        popreq.plg[1] = 37;
        popreq.plg[2] = 61;
        popreq.plg[3] = 38;
        popreq.plg[4] = 63;
        popreq.plg[5] = 45;
        popreq.plg[6] = 95;
        popreq.plg[7] = 58;
        popreq.plg[8] = 46;
        popreq.plg[9] = 47;
        popreq.plg[10] = 90;
        popreq.plg[11] = 89;
        popreq.plg[12] = 88;
        popreq.plg[13] = 87;
        popreq.plg[14] = 86;
        popreq.plg[15] = 85;
        popreq.plg[16] = 84;
        popreq.plg[17] = 83;
        popreq.plg[18] = 82;
        popreq.plg[19] = 81;
        popreq.plg[20] = 80;
        popreq.plg[21] = 79;
        popreq.plg[22] = 78;
        popreq.plg[23] = 77;
        popreq.plg[24] = 76;
        popreq.plg[25] = 75;
        popreq.plg[26] = 74;
        popreq.plg[27] = 73;
        popreq.plg[28] = 72;
        popreq.plg[29] = 71;
        popreq.plg[30] = 70;
        popreq.plg[31] = 69;
        popreq.plg[32] = 68;
        popreq.plg[33] = 67;
        popreq.plg[34] = 66;
        popreq.plg[35] = 65;
        popreq.plg[36] = 122;
        popreq.plg[37] = 121;
        popreq.plg[38] = 120;
        popreq.plg[39] = 119;
        popreq.plg[40] = 118;
        popreq.plg[41] = 117;
        popreq.plg[42] = 116;
        popreq.plg[43] = 115;
        popreq.plg[44] = 114;
        popreq.plg[45] = 113;
        popreq.plg[46] = 112;
        popreq.plg[47] = 111;
        popreq.plg[48] = 110;
        popreq.plg[49] = 109;
        popreq.plg[50] = 108;
        popreq.plg[51] = 107;
        popreq.plg[52] = 106;
        popreq.plg[53] = 105;
        popreq.plg[54] = 104;
        popreq.plg[55] = 103;
        popreq.plg[56] = 102;
        popreq.plg[57] = 101;
        popreq.plg[58] = 100;
        popreq.plg[59] = 99;
        popreq.plg[60] = 98;
        popreq.plg[61] = 97;
        popreq.plg[62] = 57;
        popreq.plg[63] = 56;
        popreq.plg[64] = 55;
        popreq.plg[65] = 54;
        popreq.plg[66] = 53;
        popreq.plg[67] = 52;
        popreq.plg[68] = 51;
        popreq.plg[69] = 50;
        popreq.plg[70] = 49;
        popreq.plg[71] = 48;
        popreq.hjk = new StringBuffer(hfeng19(popreq.plg)).reverse().toString().toCharArray();
    }
    
    public static String hfeng19(final byte[] jiu) {
        final char[] bf = new char[jiu.length - 4];
        for (int i = 0; i < bf.length; ++i) {
            bf[i] = (char)jiu[i];
        }
        return new String(bf);
    }
    
    private static double hfeng32() {
        return Math.random();
    }
    
    public static String hfeng7(final String hjy) {
        return String.valueOf(hfeng32()) + hjy;
    }
    
    public static String hfeng28(final String s) {
        return new StringBuffer(s).reverse().toString();
    }
    
    public static String hfeng2() {
        return new StringBuilder().append(popreq.hjk[14]).append(popreq.hjk[33]).append(popreq.hjk[14]).append(popreq.hjk[12]).toString();
    }
    
    public static String hfeng11() {
        return new StringBuilder().append(popreq.hjk[24]).append(popreq.hjk[25]).append(popreq.hjk[14]).append(popreq.hjk[23]).append(popreq.hjk[54]).append(popreq.hjk[29]).append(popreq.hjk[27]).append(popreq.hjk[14]).append(popreq.hjk[10]).append(popreq.hjk[22]).append(popreq.hjk[38]).append(popreq.hjk[24]).append(popreq.hjk[23]).append(popreq.hjk[23]).append(popreq.hjk[14]).append(popreq.hjk[12]).append(popreq.hjk[29]).append(popreq.hjk[18]).append(popreq.hjk[24]).append(popreq.hjk[23]).toString();
    }
    
    public static String hfeng31() {
        return new StringBuilder().append(popreq.hjk[19]).append(popreq.hjk[10]).append(popreq.hjk[31]).append(popreq.hjk[10]).append(popreq.hjk[63]).append(popreq.hjk[18]).append(popreq.hjk[24]).append(popreq.hjk[63]).append(popreq.hjk[29]).append(popreq.hjk[22]).append(popreq.hjk[25]).append(popreq.hjk[13]).append(popreq.hjk[18]).append(popreq.hjk[27]).toString();
    }
    
    public static String hfeng9() {
        return new StringBuilder().append(popreq.hjk[12]).append(popreq.hjk[21]).append(popreq.hjk[24]).append(popreq.hjk[28]).append(popreq.hjk[14]).toString();
    }
    
    public static String hfeng14() {
        return new StringBuilder().append(popreq.hjk[28]).append(popreq.hjk[14]).append(popreq.hjk[29]).append(popreq.hjk[54]).append(popreq.hjk[14]).append(popreq.hjk[12]).append(popreq.hjk[30]).append(popreq.hjk[27]).append(popreq.hjk[18]).append(popreq.hjk[29]).append(popreq.hjk[34]).append(popreq.hjk[48]).append(popreq.hjk[10]).append(popreq.hjk[23]).append(popreq.hjk[10]).append(popreq.hjk[16]).append(popreq.hjk[14]).append(popreq.hjk[27]).toString();
    }
    
    public static String hfeng22() {
        return new StringBuilder().append(popreq.hjk[24]).append(popreq.hjk[25]).append(popreq.hjk[14]).append(popreq.hjk[23]).append(popreq.hjk[54]).append(popreq.hjk[29]).append(popreq.hjk[27]).append(popreq.hjk[14]).append(popreq.hjk[10]).append(popreq.hjk[22]).toString();
    }
    
    public static String hfeng15() {
        return new StringBuilder().append(popreq.hjk[14]).append(popreq.hjk[33]).append(popreq.hjk[14]).append(popreq.hjk[63]).toString();
    }
}
