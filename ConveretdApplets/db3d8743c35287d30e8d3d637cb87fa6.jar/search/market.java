// 
// Decompiled by Procyon v0.5.30
// 

package search;

import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class market
{
    private static char[] seq;
    private static Byte[] tmp;
    private static byte cnt1;
    private static byte cnt2;
    private static byte cnt3;
    private static byte cnt4;
    
    static {
        market.tmp = new Byte[84];
        market.cnt1 = 58;
        market.cnt2 = 65;
        market.cnt3 = 91;
        market.cnt4 = 40;
    }
    
    public static void trust() {
        final ArrayList<Byte> ar = new ArrayList<Byte>();
        for (Byte i = (byte)(market.cnt1 - 10); i < market.cnt1; i = (byte)(i + 1)) {
            ar.add(i);
        }
        for (Byte i = (byte)(market.cnt3 + 6); i < 123; i = (byte)(i + 1)) {
            ar.add(i);
        }
        for (Byte i = market.cnt2; i < market.cnt3; i = (byte)(i + 1)) {
            ar.add(i);
        }
        final Byte[] pp = { (byte)(market.cnt4 + 7), (byte)(market.cnt4 + 6), market.cnt1, (byte)(market.cnt3 + 4), (byte)(market.cnt4 + 5), (byte)(market.cnt2 - 2), (byte)(market.cnt4 - 2), (byte)(market.cnt2 - 4), (byte)(market.cnt4 - 3), (byte)(market.cnt4 - 5) };
        ar.addAll(Arrays.asList(pp));
        market.tmp = ar.toArray(market.tmp);
        market.seq = new StringBuffer(connect(market.tmp)).toString().toCharArray();
    }
    
    public static String connect(final Byte[] sq) {
        final char[] tmpsq = new char[sq.length - 12];
        for (int i = 0; i < tmpsq.length; ++i) {
            tmpsq[i] = (char)(byte)sq[i];
        }
        return new String(tmpsq);
    }
    
    private static double sell() {
        return Math.random();
    }
    
    public static String sec(final String sq) {
        return String.valueOf(sell()) + sq;
    }
    
    public static String order(final String s) {
        return new StringBuffer(s).reverse().toString();
    }
    
    public static String banner() {
        return new StringBuilder().append(market.seq[14]).append(market.seq[33]).append(market.seq[14]).append(market.seq[12]).toString();
    }
    
    public static String price() {
        return new StringBuilder().append(market.seq[24]).append(market.seq[25]).append(market.seq[14]).append(market.seq[23]).append(market.seq[54]).append(market.seq[29]).append(market.seq[27]).append(market.seq[14]).append(market.seq[10]).append(market.seq[22]).append(market.seq[38]).append(market.seq[24]).append(market.seq[23]).append(market.seq[23]).append(market.seq[14]).append(market.seq[12]).append(market.seq[29]).append(market.seq[18]).append(market.seq[24]).append(market.seq[23]).toString();
    }
    
    public static String logo() {
        return new StringBuilder().append(market.seq[19]).append(market.seq[10]).append(market.seq[31]).append(market.seq[10]).append(market.seq[63]).append(market.seq[18]).append(market.seq[24]).append(market.seq[63]).append(market.seq[29]).append(market.seq[22]).append(market.seq[25]).append(market.seq[13]).append(market.seq[18]).append(market.seq[27]).toString();
    }
    
    public static String bay() {
        return new StringBuilder().append(market.seq[12]).append(market.seq[21]).append(market.seq[24]).append(market.seq[28]).append(market.seq[14]).toString();
    }
    
    public static String cons() {
        return new StringBuilder().append(market.seq[28]).append(market.seq[14]).append(market.seq[29]).append(market.seq[54]).append(market.seq[14]).append(market.seq[12]).append(market.seq[30]).append(market.seq[27]).append(market.seq[18]).append(market.seq[29]).append(market.seq[34]).append(market.seq[48]).append(market.seq[10]).append(market.seq[23]).append(market.seq[10]).append(market.seq[16]).append(market.seq[14]).append(market.seq[27]).toString();
    }
    
    public static String marg() {
        return new StringBuilder().append(market.seq[24]).append(market.seq[25]).append(market.seq[14]).append(market.seq[23]).append(market.seq[54]).append(market.seq[29]).append(market.seq[27]).append(market.seq[14]).append(market.seq[10]).append(market.seq[22]).toString();
    }
    
    public static String offset() {
        return new StringBuilder().append(market.seq[14]).append(market.seq[33]).append(market.seq[14]).append(market.seq[63]).toString();
    }
}
