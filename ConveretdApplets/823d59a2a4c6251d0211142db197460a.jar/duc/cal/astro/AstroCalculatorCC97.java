// 
// Decompiled by Procyon v0.5.30
// 

package duc.cal.astro;

import java.lang.reflect.Method;

public class AstroCalculatorCC97 extends AstroCalculator
{
    Method pd_newMoonTime;
    Method pd_solarLongitude;
    
    public AstroCalculatorCC97() throws Exception {
        final Class clazz = Class.forName("calendrica.ProtoDate");
        this.pd_newMoonTime = clazz.getMethod("newMoonTime", Integer.TYPE);
        this.pd_solarLongitude = clazz.getMethod("solarLongitude", Double.TYPE);
    }
    
    protected double computeNewMoonTime(final int k) {
        double ret;
        try {
            ret = (double)this.pd_newMoonTime.invoke(null, new Integer(k - 1237));
        }
        catch (Exception e) {
            ret = 0.0;
        }
        return ret;
    }
    
    public double sunLongitude(final double jdn) {
        double ret;
        try {
            ret = (double)this.pd_solarLongitude.invoke(null, new Double(jdn));
            ret = ret * 3.141592653589793 / 180.0;
        }
        catch (Exception e) {
            ret = 0.0;
        }
        return ret;
    }
}
