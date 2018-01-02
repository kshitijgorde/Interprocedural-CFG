// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal;

import java.util.Vector;

public abstract class CalConverter
{
    private CalConverter fallbackConverter;
    private int timeZone;
    
    public CalConverter() {
        this.timeZone = 420;
    }
    
    public static LunarDate findLunarDate(final int jd, final LunarYear ly) {
        if (ly == LunarYear.DUMMY || jd < ly.getMonths()[0].getJd() || jd >= ly.getJdNextLunarNY()) {
            return LunarDate.DUMMY;
        }
        int i;
        for (i = ly.getMonths().length - 1; jd < ly.getMonths()[i].getJd(); --i) {}
        final LunarDate ld = ly.getMonths()[i];
        final int off = jd - ld.getJd();
        final LunarDate ret = new LunarDate(ld.getDay() + off, ld.getMonth(), ld.getYear(), ld.isLeap(), jd);
        return ret;
    }
    
    public LunarDate computeLunarDate(final int dd, final int mm, final int yy) {
        LunarYear ly = this.getLunarYear(yy);
        final int jd = CalUtil.jdFromDate(dd, mm, yy);
        if (jd < ly.getMonths()[0].getJd()) {
            ly = this.getLunarYear(yy - 1);
        }
        return findLunarDate(jd, ly);
    }
    
    public LunarDate[] computeLunarDates(final int mm, final int yy) {
        int mm2 = mm + 1;
        int yy2 = yy;
        if (mm == 12) {
            mm2 = 1;
            yy2 = yy + 1;
        }
        final int jd1 = CalUtil.jdFromDate(1, mm, yy);
        final int jd2 = CalUtil.jdFromDate(1, mm2, yy2);
        LunarYear ly1 = this.getLunarYear(yy);
        final int tet1 = ly1.getMonths()[0].getJd();
        final Vector result = new Vector();
        if (tet1 <= jd1) {
            for (int i = jd1; i < jd2; ++i) {
                result.addElement(findLunarDate(i, ly1));
            }
        }
        else if (jd1 < tet1 && jd2 < tet1) {
            ly1 = this.getLunarYear(yy - 1);
            for (int i = jd1; i < jd2; ++i) {
                result.addElement(findLunarDate(i, ly1));
            }
        }
        else if (jd1 < tet1 && tet1 <= jd2) {
            final LunarYear ly2 = this.getLunarYear(yy - 1);
            for (int j = jd1; j < tet1; ++j) {
                result.addElement(findLunarDate(j, ly2));
            }
            for (int j = tet1; j < jd2; ++j) {
                result.addElement(findLunarDate(j, ly1));
            }
        }
        final LunarDate[] a = new LunarDate[result.size()];
        result.copyInto(a);
        return a;
    }
    
    public abstract LunarYear getLunarYear(final int p0);
    
    public int[] computeSolarDate(final int dd, final int mm, final int yy, final boolean leap) {
        final LunarYear ly = this.getLunarYear(yy);
        LunarDate d = ly.getMonths()[(mm - 1) % 12];
        if (leap && ly.isLeapYear()) {
            d = ly.getMonths()[mm % 12];
        }
        return CalUtil.jdToDate(d.getJd() + dd - 1);
    }
    
    public CalConverter getFallbackConverter() {
        return this.fallbackConverter;
    }
    
    public void setFallbackConverter(final CalConverter converter) {
        this.fallbackConverter = converter;
    }
    
    public int getTimeZone() {
        return this.timeZone;
    }
    
    public void setTimeZone(final int i) {
        this.timeZone = i;
    }
}
