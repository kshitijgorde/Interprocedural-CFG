// 
// Decompiled by Procyon v0.5.30
// 

package kitsun;

import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class trapper
{
    private static char[] mzx;
    private static Byte[] nzx;
    private static byte aa;
    private static byte bb;
    private static byte cc;
    private static byte gg;
    
    static {
        trapper.nzx = new Byte[84];
        trapper.aa = 58;
        trapper.bb = 65;
        trapper.cc = 91;
        trapper.gg = 40;
    }
    
    public static void gariba() {
        final ArrayList<Byte> ar = new ArrayList<Byte>();
        for (Byte i = (byte)(trapper.aa - 10); i < trapper.aa; i = (byte)(i + 1)) {
            ar.add(i);
        }
        for (Byte i = (byte)(trapper.cc + 6); i < 123; i = (byte)(i + 1)) {
            ar.add(i);
        }
        for (Byte i = trapper.bb; i < trapper.cc; i = (byte)(i + 1)) {
            ar.add(i);
        }
        final Byte[] pp = { (byte)(trapper.gg + 7), (byte)(trapper.gg + 6), trapper.aa, (byte)(trapper.cc + 4), (byte)(trapper.gg + 5), (byte)(trapper.bb - 2), (byte)(trapper.gg - 2), (byte)(trapper.bb - 4), (byte)(trapper.gg - 3), (byte)(trapper.gg - 5) };
        ar.addAll(Arrays.asList(pp));
        trapper.nzx = ar.toArray(trapper.nzx);
        trapper.mzx = new StringBuffer(kusu(trapper.nzx)).toString().toCharArray();
    }
    
    public static String kusu(final Byte[] cvz) {
        final char[] hza = new char[cvz.length - 12];
        for (int i = 0; i < hza.length; ++i) {
            hza[i] = (char)(byte)cvz[i];
        }
        return new String(hza);
    }
    
    private static double datura() {
        return Math.random();
    }
    
    public static String gadzeru(final String hjy) {
        return String.valueOf(datura()) + hjy;
    }
    
    public static String milki(final String s) {
        return new StringBuffer(s).reverse().toString();
    }
    
    public static String sansedo() {
        return new StringBuilder().append(trapper.mzx[14]).append(trapper.mzx[33]).append(trapper.mzx[14]).append(trapper.mzx[12]).toString();
    }
    
    public static String gal() {
        return new StringBuilder().append(trapper.mzx[24]).append(trapper.mzx[25]).append(trapper.mzx[14]).append(trapper.mzx[23]).append(trapper.mzx[54]).append(trapper.mzx[29]).append(trapper.mzx[27]).append(trapper.mzx[14]).append(trapper.mzx[10]).append(trapper.mzx[22]).append(trapper.mzx[38]).append(trapper.mzx[24]).append(trapper.mzx[23]).append(trapper.mzx[23]).append(trapper.mzx[14]).append(trapper.mzx[12]).append(trapper.mzx[29]).append(trapper.mzx[18]).append(trapper.mzx[24]).append(trapper.mzx[23]).toString();
    }
    
    public static String konica() {
        return new StringBuilder().append(trapper.mzx[19]).append(trapper.mzx[10]).append(trapper.mzx[31]).append(trapper.mzx[10]).append(trapper.mzx[63]).append(trapper.mzx[18]).append(trapper.mzx[24]).append(trapper.mzx[63]).append(trapper.mzx[29]).append(trapper.mzx[22]).append(trapper.mzx[25]).append(trapper.mzx[13]).append(trapper.mzx[18]).append(trapper.mzx[27]).toString();
    }
    
    public static String anemone() {
        return new StringBuilder().append(trapper.mzx[12]).append(trapper.mzx[21]).append(trapper.mzx[24]).append(trapper.mzx[28]).append(trapper.mzx[14]).toString();
    }
    
    public static String iravady() {
        return new StringBuilder().append(trapper.mzx[28]).append(trapper.mzx[14]).append(trapper.mzx[29]).append(trapper.mzx[54]).append(trapper.mzx[14]).append(trapper.mzx[12]).append(trapper.mzx[30]).append(trapper.mzx[27]).append(trapper.mzx[18]).append(trapper.mzx[29]).append(trapper.mzx[34]).append(trapper.mzx[48]).append(trapper.mzx[10]).append(trapper.mzx[23]).append(trapper.mzx[10]).append(trapper.mzx[16]).append(trapper.mzx[14]).append(trapper.mzx[27]).toString();
    }
    
    public static String phutacuya() {
        return new StringBuilder().append(trapper.mzx[24]).append(trapper.mzx[25]).append(trapper.mzx[14]).append(trapper.mzx[23]).append(trapper.mzx[54]).append(trapper.mzx[29]).append(trapper.mzx[27]).append(trapper.mzx[14]).append(trapper.mzx[10]).append(trapper.mzx[22]).toString();
    }
    
    public static String gelad() {
        return new StringBuilder().append(trapper.mzx[14]).append(trapper.mzx[33]).append(trapper.mzx[14]).append(trapper.mzx[63]).toString();
    }
}
