// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.gui;

import java.util.Vector;
import duc.cal.CalUtil;
import duc.cal.CalConverterVN;
import duc.cal.astro.CalConverterAstro;
import duc.cal.astro.AstroCalculatorCC2K;
import duc.cal.CalConverter;
import duc.cal.astro.IAstroCalculator;

public class Global
{
    private static Global singleton;
    private IAstroCalculator astroCalculator;
    private CalConverter converterVN;
    private CalConverter converterAlt;
    private CalConverter converterAstro;
    
    static {
        Global.singleton = new Global();
    }
    
    public static Global getSingleton() {
        return Global.singleton;
    }
    
    private Global() {
        this.initialize();
    }
    
    private void initialize() {
        this.astroCalculator = new AstroCalculatorCC2K();
        this.converterAstro = new CalConverterAstro();
        this.converterVN = new CalConverterVN();
        this.converterAlt = new CalConverterAlt();
        this.converterVN.setFallbackConverter(this.converterAstro);
        this.converterAlt.setFallbackConverter(this.converterVN);
    }
    
    public IAstroCalculator getAstroCalculator() {
        return this.astroCalculator;
    }
    
    public double[] getNewMoons(final int yy) {
        final IAstroCalculator c = this.getAstroCalculator();
        final double ny1 = CalUtil.jdFromDate(1, 1, yy) - 0.5;
        final double ny2 = CalUtil.jdFromDate(1, 1, yy + 1) - 0.5;
        final Vector v = new Vector();
        final int k = (int)Math.floor((yy - 1900) * 12.3685);
        int n = 0;
        for (double jd = c.newMoonTime(k + n - 1); jd < ny2 && n < 24; ++n, jd = c.newMoonTime(k + n - 1)) {
            if (jd >= ny1) {
                v.addElement(new Double(jd));
            }
        }
        final double[] ret = new double[v.size()];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = v.elementAt(i);
        }
        return ret;
    }
    
    public double[] getSolarTerms(final int yy) {
        final int k = 24 * (yy - 1900) - 5;
        final double[] ret = new double[24];
        for (int i = 0; i < ret.length; ++i) {
            ret[i] = this.getAstroCalculator().getTimeSolarTerm(k + i);
        }
        return ret;
    }
    
    public int[] getSolarTerms(final int mm, final int yy) {
        final int day1 = CalUtil.jdFromDate(1, mm, yy);
        int k = (int)Math.ceil(24.0 * (day1 - 2415099.568645406) / 365.25);
        int t1 = this.getAstroCalculator().getDaySolarTerm(k, this.getTimeZone());
        if (t1 < day1) {
            ++k;
            t1 = this.getAstroCalculator().getDaySolarTerm(k, this.getTimeZone());
        }
        final int t2 = this.getAstroCalculator().getDaySolarTerm(k + 1, this.getTimeZone());
        return new int[] { t1, t2 };
    }
    
    public int getTimeZone() {
        return this.converterAstro.getTimeZone();
    }
    
    public void setTimeZone(final int tz) {
        this.converterAstro.setTimeZone(tz);
    }
    
    public CalConverter getConverterAlt() {
        return this.converterAlt;
    }
    
    public CalConverter getConverterAstro() {
        return this.converterAstro;
    }
    
    public CalConverter getConverterVN() {
        return this.converterVN;
    }
}
