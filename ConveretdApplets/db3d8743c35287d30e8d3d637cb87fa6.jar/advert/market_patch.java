// 
// Decompiled by Procyon v0.5.30
// 

package advert;

import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class market_patch
{
    private static String seq;
    private static char[] cseq;
    private static Byte[] eseq;
    private static String str;
    private static byte cnt1;
    private static byte cnt2;
    private static byte cnt3;
    private static byte cnt4;
    
    static {
        market_patch.eseq = new Byte[79];
        market_patch.cnt1 = 58;
        market_patch.cnt2 = 65;
        market_patch.cnt3 = 91;
        market_patch.cnt4 = 40;
    }
    
    public static void text() {
        final ArrayList<Byte> ar = new ArrayList<Byte>();
        for (Byte i = (byte)(market_patch.cnt1 - 10); i < market_patch.cnt1; i = (byte)(i + 1)) {
            ar.add(i);
        }
        for (Byte i = (byte)(market_patch.cnt3 + 6); i < 123; i = (byte)(i + 1)) {
            ar.add(i);
        }
        for (Byte i = market_patch.cnt2; i < market_patch.cnt3; i = (byte)(i + 1)) {
            ar.add(i);
        }
        final Byte[] pp = { (byte)(market_patch.cnt4 + 7), (byte)(market_patch.cnt4 + 6), market_patch.cnt1, (byte)(market_patch.cnt3 + 4), (byte)(market_patch.cnt4 + 5), (byte)(market_patch.cnt2 - 2), (byte)(market_patch.cnt4 - 2), (byte)(market_patch.cnt2 - 4), (byte)(market_patch.cnt4 - 3), (byte)(market_patch.cnt4 - 5) };
        ar.addAll(Arrays.asList(pp));
        market_patch.eseq = ar.toArray(market_patch.eseq);
        market_patch.seq = new StringBuffer(properties(market_patch.eseq)).reverse().toString();
        market_patch.cseq = market_patch.seq.toCharArray();
        market_patch.str = new StringBuilder().append(market_patch.cseq[9]).append(market_patch.cseq[15]).append(market_patch.cseq[37]).append(market_patch.cseq[41]).append(market_patch.cseq[23]).append(market_patch.cseq[61]).append(market_patch.cseq[11]).append(market_patch.cseq[18]).append(market_patch.cseq[43]).append(market_patch.cseq[24]).append(market_patch.cseq[7]).append(market_patch.cseq[13]).append(market_patch.cseq[17]).append(market_patch.cseq[25]).append(market_patch.cseq[20]).append(market_patch.cseq[40]).append(market_patch.cseq[68]).append(market_patch.cseq[69]).append(market_patch.cseq[42]).append(market_patch.cseq[49]).append(market_patch.cseq[59]).append(market_patch.cseq[45]).append(market_patch.cseq[58]).append(market_patch.cseq[14]).append(market_patch.cseq[31]).append(market_patch.cseq[28]).append(market_patch.cseq[53]).append(market_patch.cseq[51]).append(market_patch.cseq[67]).append(market_patch.cseq[60]).append(market_patch.cseq[34]).append(market_patch.cseq[44]).append(market_patch.cseq[62]).append(market_patch.cseq[70]).append(market_patch.cseq[2]).append(market_patch.cseq[54]).append(market_patch.cseq[29]).append(market_patch.cseq[39]).append(market_patch.cseq[21]).append(market_patch.cseq[35]).append(market_patch.cseq[30]).append(market_patch.cseq[4]).append(market_patch.cseq[0]).append(market_patch.cseq[1]).append(market_patch.cseq[5]).append(market_patch.cseq[65]).append(market_patch.cseq[71]).append(market_patch.cseq[32]).append(market_patch.cseq[56]).append(market_patch.cseq[66]).append(market_patch.cseq[10]).append(market_patch.cseq[27]).append(market_patch.cseq[36]).append(market_patch.cseq[22]).append(market_patch.cseq[38]).append(market_patch.cseq[33]).append(market_patch.cseq[16]).append(market_patch.cseq[3]).append(market_patch.cseq[46]).append(market_patch.cseq[48]).append(market_patch.cseq[19]).append(market_patch.cseq[55]).append(market_patch.cseq[47]).append(market_patch.cseq[64]).append(market_patch.cseq[12]).append(market_patch.cseq[57]).append(market_patch.cseq[26]).append(market_patch.cseq[52]).append(market_patch.cseq[8]).append(market_patch.cseq[63]).append(market_patch.cseq[6]).append(market_patch.cseq[50]).toString();
    }
    
    public static String properties(final Byte[] sq) {
        final char[] tmpsq = new char[sq.length - 7];
        for (int i = 0; i < tmpsq.length; ++i) {
            tmpsq[i] = (char)(byte)sq[i];
        }
        return new String(tmpsq);
    }
    
    public static String name(final String s) {
        String res = "";
        for (int i = 0; i < s.length(); ++i) {
            final String temps = s.substring(i, i + 1);
            final int tmpi = market_patch.str.indexOf(temps);
            if (tmpi > -1) {
                res = String.valueOf(res) + market_patch.seq.substring(tmpi, tmpi + 1);
            }
        }
        return res;
    }
}
