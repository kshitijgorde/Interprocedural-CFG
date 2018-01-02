// 
// Decompiled by Procyon v0.5.30
// 

package astro;

public class TimeSpan
{
    public int nYear;
    public int nMonth;
    public int nDay;
    public int nHour;
    public int nMin;
    public double fSec;
    
    public TimeSpan(final int nYear, final int nMonth, final int nDay, final int nHour, final int nMin, final double fSec) {
        this.nYear = nYear;
        this.nMonth = nMonth;
        this.nDay = nDay;
        this.nHour = nHour;
        this.nMin = nMin;
        this.fSec = fSec;
    }
}
