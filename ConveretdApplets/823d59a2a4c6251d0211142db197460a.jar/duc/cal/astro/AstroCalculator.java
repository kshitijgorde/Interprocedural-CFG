// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.astro;

import java.util.Hashtable;

public abstract class AstroCalculator implements IAstroCalculator
{
    public static final double radPerDay = 0.017202791255726685;
    private Hashtable cache;
    
    public AstroCalculator() {
        this.cache = new Hashtable();
    }
    
    public double getTimeSolarTerm(final int k) {
        double d = 2415099.568645406 + k * 365.242199 / 24.0;
        final double term = (k - 24.0 * Math.floor(k / 24)) * 3.141592653589793 / 12.0;
        d += this.diff(term, this.sunLongitude(d)) / 0.017202791255726685;
        double hi = d + 1.0;
        double lo = d - 1.0;
        int count = 1000;
        while (count-- > 0 && hi - lo > 1.0E-5) {
            if (this.diff(term, this.sunLongitude(d)) < 0.0) {
                hi = d;
            }
            else {
                lo = d;
            }
            d = (hi + lo) / 2.0;
        }
        return d;
    }
    
    public int getDaySolarTerm(final int k, final int timeZone) {
        final double d = 2415099.568645406 + k * 365.242199 / 24.0;
        double mn = this.midnight(d, timeZone);
        final double term = (k - 24.0 * Math.floor(k / 24)) * 3.141592653589793 / 12.0;
        mn += Math.floor(this.diff(term, this.sunLongitude(mn)) / 0.017202791255726685);
        for (int comp = this.compare(term, this.sunLongitude(mn), this.sunLongitude(mn + 1.0)); comp != 0; comp = this.compare(term, this.sunLongitude(mn), this.sunLongitude(mn + 1.0))) {
            mn += comp;
        }
        return (int)Math.floor(mn + 0.5 + timeZone / 1440.0);
    }
    
    double diff(final double sl, final double term) {
        double ret = sl - term;
        if (ret > 3.141592653589793) {
            ret -= 6.283185307179586;
        }
        else if (ret < -3.141592653589793) {
            ret += 6.283185307179586;
        }
        return ret;
    }
    
    int compare(final double term, final double sl1, final double sl2) {
        if (this.diff(sl2, term) <= 0.0) {
            return 1;
        }
        if (this.diff(term, sl1) < 0.0) {
            return -1;
        }
        return 0;
    }
    
    double midnight(final double jd, final int timeZone) {
        final int n = (int)Math.floor(jd + 0.5 + timeZone / 1440.0);
        return n - 0.5 - timeZone / 1440.0;
    }
    
    public double newMoonTime(final int k) {
        final Integer key = new Integer(k);
        Double ret = this.cache.get(key);
        if (ret == null) {
            ret = new Double(this.computeNewMoonTime(k));
            if (this.cache.size() > 100) {
                this.cache.clear();
            }
            this.cache.put(key, ret);
        }
        return ret;
    }
    
    protected abstract double computeNewMoonTime(final int p0);
    
    public abstract double sunLongitude(final double p0);
}
