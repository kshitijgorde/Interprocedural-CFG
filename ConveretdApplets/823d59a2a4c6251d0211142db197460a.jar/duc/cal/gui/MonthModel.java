// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.gui;

import java.util.Vector;
import duc.cal.LunarDate;

public class MonthModel
{
    static String[] GIO_HD;
    static String[] HANH;
    static String[] NTBT;
    static String[] SAO_HD;
    static String[] TIETKHI;
    static String[] TRUC;
    boolean astro;
    static Global calc;
    private int day;
    private int month;
    private int year;
    private int firstDay;
    private LunarDate[] lunarDates;
    private LunarDate[] lunarDatesAlt;
    private String[] solarTermNames;
    private int[] solarTerms;
    
    static {
        MonthModel.GIO_HD = new String[] { "110100101100", "001101001011", "110011010010", "101100110100", "001011001101", "010010110011" };
        MonthModel.HANH = new String[] { "M\u1ed9c", "Kim", "Th\u1ed5", "Nh\u1eadt", "Nguy\u1ec7t", "H\u1ecfa", "Th\u1ee7y" };
        MonthModel.NTBT = new String[] { "Gi\u00e1c", "Cang", "\u0110\u00ea", "Ph\u00f2ng", "T\u00e2m", "V\u0129", "C\u01a1", "\u0110\u1ea9u", "Ng\u01b0u", "N\u1eef", "H\u01b0", "Nguy", "Th\u1ea5t", "B\u00edch", "Khu\u00ea", "L\u00e2u", "V\u1ecb", "M\u00e3o", "T\u1ea5t", "Ch\u1ee7y", "S\u00e2m", "T\u1ec9nh", "Qu\u1ef7", "Li\u1ec5u", "Tinh", "Tr\u01b0\u01a1ng", "D\u1ef1c", "Ch\u1ea9n" };
        MonthModel.SAO_HD = new String[] { "Thanh Long", "Minh \u0110\u01b0\u1eddng", "Thi\u00ean H\u00ecnh", "Chu T\u01b0\u1edbc", "Kim Qu\u1ef9", "Kim \u0110\u01b0\u1eddng", "B\u1ea1ch H\u1ed5", "Ng\u1ecdc \u0110\u01b0\u1eddng", "Thi\u00ean Lao", "Nguy\u00ean V\u0169", "T\u01b0 M\u1ec7nh", "C\u00e2u Tr\u1ea7n" };
        MonthModel.TIETKHI = new String[] { "Ti\u1ec3u h\u00e0n", "\u0110\u1ea1i h\u00e0n", "L\u1eadp xu\u00e2n", "V\u0169 Th\u1ee7y", "Kinh tr\u1eadp", "Xu\u00e2n ph\u00e2n", "Thanh minh", "C\u1ed1c v\u0169", "L\u1eadp h\u1ea1", "Ti\u1ec3u m\u00e3n", "Mang ch\u1ee7ng", "H\u1ea1 ch\u00ed", "Ti\u1ec3u th\u1eed", "\u0110\u1ea1i th\u1eed", "L\u1eadp thu", "X\u1eed th\u1eed", "B\u1ea1ch l\u1ed9", "Thu ph\u00e2n", "H\u00e0n l\u1ed9", "S\u01b0\u01a1ng gi\u00e1ng", "L\u1eadp \u0111\u00f4ng", "Ti\u1ec3u tuy\u1ebft", "\u0110\u1ea1i tuy\u1ebft", "\u0110\u00f4ng ch\u00ed" };
        MonthModel.TRUC = new String[] { "Ki\u1ebfn", "Tr\u1eeb", "M\u00e3n", "B\u00ecnh", "\u0110\u1ecbnh", "Ch\u1ea5p", "Ph\u00e1", "Nguy", "Th\u00e0nh", "Thu", "Khai", "B\u1ebf" };
        MonthModel.calc = Global.getSingleton();
    }
    
    public int getFirstDay() {
        return this.firstDay;
    }
    
    public LunarDate[] getLunarDates() {
        return this.lunarDates;
    }
    
    public LunarDate[] getLunarDatesAlt() {
        return this.lunarDatesAlt;
    }
    
    public int getMonth() {
        return this.month;
    }
    
    public static String getSaoNTBT(final int jd) {
        return MonthModel.NTBT[floorDiv(jd + 11, 28)[1]];
    }
    
    public static String getGioHoangDao(final int jd) {
        final int chiOfDay = floorDiv(jd + 1, 12)[1];
        final String gioHD = MonthModel.GIO_HD[chiOfDay % 6];
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < gioHD.length(); ++i) {
            if (gioHD.charAt(i) == '1') {
                sb.append(LunarDate.CHI[i]);
                sb.append(' ');
            }
        }
        return sb.toString().trim();
    }
    
    public static String getSaoHoangDao(final LunarDate v) {
        final LunarDate soc = new LunarDate(1, v.getMonth(), v.getYear(), v.isLeap(), v.getJd() - v.getDay() + 1);
        final int chiOfThanhLong = (2 * soc.getMonth() - 2) % 12;
        final int chiOfSoc = floorDiv(soc.getJd() + 1, 12)[1];
        final int dayOfThanhLong = soc.getJd() + floorDiv(chiOfThanhLong - chiOfSoc, 12)[1];
        final int idx = floorDiv(v.getJd() - dayOfThanhLong, 12)[1];
        return MonthModel.SAO_HD[idx];
    }
    
    public String[] getSolarTermNames() {
        return this.solarTermNames;
    }
    
    public int[] getSolarTerms() {
        return this.solarTerms;
    }
    
    public int getYear() {
        return this.year;
    }
    
    private void initSolarTerms(final int start, final int end) {
        int k = (int)Math.ceil(24.0 * (start - 2415099.568645406) / 365.242199);
        int t1 = MonthModel.calc.getAstroCalculator().getDaySolarTerm(k, MonthModel.calc.getTimeZone());
        if (t1 < start) {
            ++k;
            t1 = MonthModel.calc.getAstroCalculator().getDaySolarTerm(k, MonthModel.calc.getTimeZone());
        }
        final int t2 = MonthModel.calc.getAstroCalculator().getDaySolarTerm(k + 1, MonthModel.calc.getTimeZone());
        final Vector v = new Vector();
        final int[] a = { t1, t2 };
        for (int i = 0; i < a.length; ++i) {
            if (start <= a[i] && a[i] <= end) {
                v.addElement(new Integer(a[i]));
            }
        }
        int n = k + 5;
        n = floorDiv(n, 24)[1];
        this.solarTerms = new int[v.size()];
        this.solarTermNames = new String[this.solarTerms.length];
        for (int j = 0; j < this.solarTerms.length; ++j) {
            this.solarTerms[j] = v.elementAt(j);
            this.solarTermNames[j] = MonthModel.TIETKHI[(n + j) % 24];
        }
    }
    
    public boolean isAstro() {
        return this.astro;
    }
    
    public void setAstro(final boolean b) {
        this.astro = b;
    }
    
    public void setMonth(final int mm, final int yy) {
        this.month = mm;
        this.year = yy;
        if (this.isAstro()) {
            this.lunarDates = MonthModel.calc.getConverterAstro().computeLunarDates(mm, yy);
            this.lunarDatesAlt = null;
        }
        else {
            this.lunarDates = MonthModel.calc.getConverterVN().computeLunarDates(mm, yy);
            if ((yy >= 1645 && yy <= 1812) || yy >= 1968) {
                this.lunarDatesAlt = MonthModel.calc.getConverterAlt().computeLunarDates(mm, yy);
            }
            else {
                this.lunarDatesAlt = null;
            }
        }
        this.initSolarTerms(this.firstDay = this.lunarDates[0].getJd(), this.lunarDates[this.lunarDates.length - 1].getJd());
    }
    
    public int getDay() {
        return this.day;
    }
    
    public void setDay(final int i) {
        this.day = i;
    }
    
    public static String getTruc(final int jd) {
        int k = (int)Math.ceil(24.0 * (jd - 2415099.568645406) / 365.242199);
        if (k % 2 == 0) {
            --k;
        }
        int minorTerm = MonthModel.calc.getAstroCalculator().getDaySolarTerm(k, MonthModel.calc.getTimeZone());
        if (minorTerm > jd) {
            k -= 2;
            minorTerm = MonthModel.calc.getAstroCalculator().getDaySolarTerm(k, MonthModel.calc.getTimeZone());
        }
        final int chiOfKien = floorDiv(k - 17, 24)[1] / 2;
        final int chiOfMinorTerm = floorDiv(minorTerm + 1, 12)[1];
        final int dayOfKien = minorTerm + floorDiv(chiOfKien - chiOfMinorTerm, 12)[1];
        final int idx = floorDiv(jd - dayOfKien, 12)[1];
        final String ret = MonthModel.TRUC[idx];
        return ret;
    }
    
    public static int[] floorDiv(final int x, final int y) {
        final int ret = (int)Math.floor(x / y);
        final int rest = x - ret * y;
        return new int[] { ret, rest };
    }
}
