import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

class computeSeasons
{
    final double K = 0.017453292519943295;
    String hourStr;
    String dayStr;
    String monthStr;
    String dayNameStr;
    int Year;
    double y;
    double T;
    double marchT;
    double juneT;
    double septemberT;
    double decemberT;
    String dateString;
    int DAY;
    double DD;
    
    public computeSeasons(final int year) {
        this.y = (year - 2000.0) / 1000.0;
        this.Year = year;
    }
    
    public double S() {
        double x = 485.0 * Math.cos(0.017453292519943295 * (324.96 + 1934.136 * this.T));
        x += 203.0 * Math.cos(0.017453292519943295 * (337.23 + 32964.467 * this.T));
        x += 199.0 * Math.cos(0.017453292519943295 * (342.08 + 20.186 * this.T));
        x += 182.0 * Math.cos(0.017453292519943295 * (27.85 + 445267.112 * this.T));
        x += 156.0 * Math.cos(0.017453292519943295 * (73.14 + 45036.886 * this.T));
        x += 136.0 * Math.cos(0.017453292519943295 * (171.52 + 22518.443 * this.T));
        x += 77.0 * Math.cos(0.017453292519943295 * (222.54 + 65928.934 * this.T));
        x += 74.0 * Math.cos(0.017453292519943295 * (296.72 + 3034.906 * this.T));
        x += 70.0 * Math.cos(0.017453292519943295 * (243.58 + 9037.513 * this.T));
        x += 58.0 * Math.cos(0.017453292519943295 * (119.81 + 33718.147 * this.T));
        x += 52.0 * Math.cos(0.017453292519943295 * (297.17 + 150.678 * this.T));
        x += 50.0 * Math.cos(0.017453292519943295 * (21.02 + 2281.226 * this.T));
        x += 45.0 * Math.cos(0.017453292519943295 * (247.54 + 29929.562 * this.T));
        x += 44.0 * Math.cos(0.017453292519943295 * (325.15 + 31555.956 * this.T));
        x += 29.0 * Math.cos(0.017453292519943295 * (60.93 + 4443.417 * this.T));
        x += 18.0 * Math.cos(0.017453292519943295 * (155.12 + 67555.328 * this.T));
        x += 17.0 * Math.cos(0.017453292519943295 * (288.79 + 4562.452 * this.T));
        x += 16.0 * Math.cos(0.017453292519943295 * (198.04 + 62894.029 * this.T));
        x += 14.0 * Math.cos(0.017453292519943295 * (199.76 + 31436.921 * this.T));
        x += 12.0 * Math.cos(0.017453292519943295 * (95.39 + 14577.848 * this.T));
        x += 12.0 * Math.cos(0.017453292519943295 * (287.11 + 31931.756 * this.T));
        x += 12.0 * Math.cos(0.017453292519943295 * (320.81 + 34777.259 * this.T));
        x += 9.0 * Math.cos(0.017453292519943295 * (227.73 + 1222.114 * this.T));
        x += 8.0 * Math.cos(0.017453292519943295 * (15.45 + 16859.074 * this.T));
        return x;
    }
    
    public double march() {
        final double JDE0 = 2451623.80984 + (365242.37404 + (0.05169 - (0.00411 - 5.7E-4 * this.y) * this.y) * this.y) * this.y;
        this.T = (JDE0 - 2451545.0) / 36525.0;
        double W = 35999.373 * this.T - 2.47;
        W *= 0.017453292519943295;
        final double dL = 1.0 + 0.0334 * Math.cos(W) + 7.0E-4 * Math.cos(2.0 * W);
        return this.marchT = JDE0 + 1.0E-5 * this.S() / dL - (66.0 + (this.Year - 2000)) / 86400.0;
    }
    
    public double june() {
        final double JDE0 = 2451716.56767 + (365241.62603 + (0.00325 - (0.00888 - 3.0E-4 * this.y) * this.y) * this.y) * this.y;
        this.T = (JDE0 - 2451545.0) / 36525.0;
        double W = 35999.373 * this.T - 2.47;
        W *= 0.017453292519943295;
        final double dL = 1.0 + 0.0334 * Math.cos(W) + 7.0E-4 * Math.cos(2.0 * W);
        return this.juneT = JDE0 + 1.0E-5 * this.S() / dL - (66.0 + (this.Year - 2000)) / 86400.0;
    }
    
    public double september() {
        final double JDE0 = 2451810.21715 + (365242.01767 + (0.11575 - (0.00337 - 7.8E-4 * this.y) * this.y) * this.y) * this.y;
        this.T = (JDE0 - 2451545.0) / 36525.0;
        double W = 35999.373 * this.T - 2.47;
        W *= 0.017453292519943295;
        final double dL = 1.0 + 0.0334 * Math.cos(W) + 7.0E-4 * Math.cos(2.0 * W);
        return this.septemberT = JDE0 + 1.0E-5 * this.S() / dL - (66.0 + (this.Year - 2000)) / 86400.0;
    }
    
    public double december() {
        final double JDE0 = 2451900.05952 + (365242.74049 + (0.06223 - (0.00823 - 3.2E-4 * this.y) * this.y) * this.y) * this.y;
        this.T = (JDE0 - 2451545.0) / 36525.0;
        double W = 35999.373 * this.T - 2.47;
        W *= 0.017453292519943295;
        final double dL = 1.0 + 0.0334 * Math.cos(W) + 7.0E-4 * Math.cos(2.0 * W);
        return this.decemberT = JDE0 + 1.0E-5 * this.S() / dL - (66.0 + (this.Year - 2000)) / 86400.0;
    }
    
    public void caldat(final double JD) {
        final double JD2 = (int)(JD + 0.5);
        final int B = (int)((JD2 - 1867216.25) / 36524.25);
        final double C = JD2 + B - B / 4 + 1525.0;
        final int D = (int)((C - 122.1) / 365.25);
        final double E = 365.0 * D + D / 4;
        final int F = (int)((C - E) / 30.6001);
        final int day = (int)(C - E + 0.5) - (int)(30.6001 * F);
        this.dayStr = String.valueOf(day);
        if (day < 10) {
            this.dayStr = " " + this.dayStr;
        }
        final int month = F - 1 - 12 * (F / 14);
        final int year = D - 4715 - (7 + month) / 10;
        double hour = 24.0 * (JD + 0.5 - JD2);
        final double diff = Math.abs(hour) - (int)Math.abs(hour);
        int min = (int)Math.round(diff * 60.0);
        if (min == 60) {
            min = 0;
            ++hour;
        }
        String str;
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        this.hourStr = String.valueOf((int)hour) + str + min;
        if ((int)hour < 10) {
            this.hourStr = " " + this.hourStr;
        }
        this.monthStr = this.monthString(month);
        this.dayNameStr = this.dayString(JD);
        final Date dat = new Date();
        dat.setDate(day);
        dat.setMonth(month - 1);
        dat.setYear(year - 1900);
        dat.setHours((int)hour);
        dat.setMinutes(min);
        dat.setSeconds(0);
        this.dateString = dat.toString();
        this.DAY = day;
        this.DD = day + hour / 24.0;
    }
    
    public String monthString(final int m) {
        final String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        if (m < 1) {
            return "month = " + m;
        }
        return monthArray[m - 1];
    }
    
    public String dayString(final double jd) {
        final String[] dayArray = { null, "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", null, null, null, null };
        long num = (long)(jd + 0.5);
        num = num - num / 7L * 7L + 1L;
        return dayArray[(int)num];
    }
    
    public String spring() {
        this.caldat(this.march());
        return String.valueOf(this.dateString.substring(0, 16)) + " UT";
    }
    
    public String summer() {
        this.caldat(this.june());
        return String.valueOf(this.dateString.substring(0, 16)) + " UT";
    }
    
    public String autumn() {
        this.caldat(this.september());
        return String.valueOf(this.dateString.substring(0, 16)) + " UT";
    }
    
    public String winter() {
        this.caldat(this.december());
        return String.valueOf(this.dateString.substring(0, 16)) + " UT";
    }
    
    public double marchJD() {
        return this.marchT;
    }
    
    public double juneJD() {
        return this.juneT;
    }
    
    public double septemberJD() {
        return this.septemberT;
    }
    
    public double decemberJD() {
        return this.decemberT;
    }
    
    public double springDuration() {
        return this.juneT - this.marchT;
    }
    
    public double summerDuration() {
        return this.septemberT - this.juneT;
    }
    
    public double autumnDuration() {
        return this.decemberT - this.septemberT;
    }
    
    public double winterDuration() {
        return this.decemberT - this.septemberT;
    }
    
    public int intDay() {
        return this.DAY;
    }
    
    public double fracDay() {
        return Math.round(1000.0 * this.DD) / 1000.0;
    }
}
