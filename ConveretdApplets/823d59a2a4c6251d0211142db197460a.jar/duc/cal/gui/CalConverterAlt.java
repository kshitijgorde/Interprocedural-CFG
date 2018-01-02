// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.gui;

import duc.cal.LunarYear;
import duc.cal.astro.CalConverterAstro;
import duc.cal.CalConverterVN;
import java.util.Hashtable;
import duc.cal.CalConverter;

public class CalConverterAlt extends CalConverter
{
    static int[] MIENNAM1955;
    static int[] TAYSON1789;
    Hashtable cache;
    CalConverter cn;
    CalConverter vn;
    
    static {
        CalConverterAlt.MIENNAM1955 = new int[] { 3097971, 5526128, 3959144, 6351184, 5008032, 3583654, 5942096, 4606816, 3189476, 5678448, 4215392, 2683491, 5167440, 3824471, 6182560, 4757200, 3427797, 5917392, 4367568, 2938068, 5427792 };
        CalConverterAlt.TAYSON1789 = new int[] { 3331285, 5812896, 4371792, 3038548, 5393824, 3970480, 2643314, 5130672, 3582326, 6055088, 4614800, 3193508, 5663568, 4336544 };
    }
    
    public CalConverterAlt() {
        this.vn = new CalConverterVN();
        this.cn = new CalConverterAstro();
        this.cache = new Hashtable();
    }
    
    public LunarYear getLunarYear(final int yy) {
        final Integer key = new Integer(yy);
        LunarYear ret = this.cache.get(key);
        if (ret == null) {
            ret = this.getLunarYearCN(yy);
            if (this.cache.size() > 10) {
                this.cache.clear();
            }
            this.cache.put(key, ret);
        }
        return ret;
    }
    
    private LunarYear getLunarYearCN(final int yy) {
        if (yy < 1645) {
            return this.vn.getLunarYear(yy);
        }
        if (yy < 1813) {
            this.cn.setTimeZone(466);
            return this.cn.getLunarYear(yy);
        }
        if (yy < 1968) {
            return this.vn.getLunarYear(yy);
        }
        this.cn.setTimeZone(480);
        return this.cn.getLunarYear(yy);
    }
    
    private LunarYear getLunarYearMN(final int y) {
        if (y >= 1789 && y <= 1802) {
            return new LunarYear(y, CalConverterAlt.TAYSON1789[y - 1789]);
        }
        if (y >= 1965 && y <= 1975) {
            return new LunarYear(y, CalConverterAlt.MIENNAM1955[y - 1955]);
        }
        if (this.getFallbackConverter() == null) {
            return LunarYear.DUMMY;
        }
        return this.getFallbackConverter().getLunarYear(y);
    }
}
