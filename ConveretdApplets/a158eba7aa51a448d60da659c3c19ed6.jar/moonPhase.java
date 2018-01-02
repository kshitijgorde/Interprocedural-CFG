// 
// Decompiled by Procyon v0.5.30
// 

class moonPhase
{
    final double K = 0.017453292519943295;
    double M;
    double MM;
    double F;
    double omega;
    double E;
    double T;
    double jde;
    double k;
    double year;
    String hourStr;
    String dayStr;
    String monthStr;
    String dayNameStr;
    int myLocOffset;
    int Year;
    double phase;
    
    public moonPhase(final int locOffset, final double theYear) {
        this.year = theYear;
        this.Year = (int)theYear;
        this.myLocOffset = locOffset;
    }
    
    public String newMoonStr() {
        this.k = (this.year - 2000.0) * 12.3685;
        this.k = Math.round(this.k);
        this.T = this.k / 1236.85;
        this.E = 1.0 - 0.002516 * this.T - 7.4E-6 * this.T * this.T;
        this.SunMoon(this.T, this.k);
        this.jde = this.JDE(this.T, this.k) + this.newMoon();
        this.phase = this.jde;
        this.caldat(this.jde);
        return "New   " + this.dayNameStr + " " + this.monthStr + " " + this.dayStr + ", " + this.hourStr;
    }
    
    public String lastQuarterStr() {
        this.k = (this.year - 2000.0) * 12.3685;
        this.k = Math.round(this.k);
        this.T = this.k / 1236.85;
        this.k += 0.75;
        this.E = 1.0 - 0.002516 * this.T - 7.4E-6 * this.T * this.T;
        this.SunMoon(this.T, this.k);
        this.caldat(this.jde = this.JDE(this.T, this.k) + this.quarterMoon() - this.quarter());
        return "Last  " + this.dayNameStr + " " + this.monthStr + " " + this.dayStr + ", " + this.hourStr;
    }
    
    public String firstQuarterStr() {
        this.k = (this.year - 2000.0) * 12.3685;
        this.k = Math.round(this.k);
        this.T = this.k / 1236.85;
        this.k += 0.25;
        this.E = 1.0 - 0.002516 * this.T - 7.4E-6 * this.T * this.T;
        this.SunMoon(this.T, this.k);
        this.caldat(this.jde = this.JDE(this.T, this.k) + this.quarterMoon() + this.quarter());
        return "First " + this.dayNameStr + " " + this.monthStr + " " + this.dayStr + ", " + this.hourStr;
    }
    
    public String fullMoonStr() {
        this.k = (this.year - 2000.0) * 12.3685;
        this.k = Math.round(this.k);
        this.T = this.k / 1236.85;
        this.k += 0.5;
        this.E = 1.0 - 0.002516 * this.T - 7.4E-6 * this.T * this.T;
        this.SunMoon(this.T, this.k);
        this.caldat(this.jde = this.JDE(this.T, this.k) + this.fullMoon());
        return "Full  " + this.dayNameStr + " " + this.monthStr + " " + this.dayStr + ", " + this.hourStr;
    }
    
    public double JDE(final double T, final double k) {
        return 2451550.09765 + 29.530588853 * k + (1.337E-4 - (1.5E-7 - 7.3E-10 * T) * T) * T * T;
    }
    
    public void SunMoon(final double T, final double k) {
        this.M = 2.5534 + 29.10535669 * k - (2.18E-5 + 1.1E-7 * T) * T * T;
        this.M %= 360.0;
        if (this.M < 0.0) {
            this.M += 360.0;
        }
        this.M *= 0.017453292519943295;
        this.MM = 201.5643 + 385.81693528 * k + (0.0107438 + (1.239E-5 - 5.8E-8 * T) * T) * T * T;
        this.MM %= 360.0;
        if (this.MM < 0.0) {
            this.MM += 360.0;
        }
        this.MM *= 0.017453292519943295;
        this.F = 160.7108 + 390.67050274 * k - (0.0016341 + (2.27E-6 - 1.1E-8 * T) * T) * T * T;
        this.F %= 360.0;
        if (this.F < 0.0) {
            this.F += 360.0;
        }
        this.F *= 0.017453292519943295;
        this.omega = 124.7746 - 1.5637558 * k + (0.0020691 + 2.15E-6 * T) * T * T;
        this.omega %= 360.0;
        if (this.omega < 0.0) {
            this.omega += 360.0;
        }
        this.omega *= 0.017453292519943295;
    }
    
    public double newMoon() {
        return -0.4072 * Math.sin(this.MM) + 0.17241 * this.E * Math.sin(this.M) + 0.01608 * Math.sin(2.0 * this.MM) + 0.01039 * Math.sin(2.0 * this.F) + 0.00739 * this.E * Math.sin(this.MM - this.M) - 0.00514 * this.E * Math.sin(this.MM + this.M) + 0.00208 * this.E * this.E * Math.sin(2.0 * this.M) - 0.00111 * Math.sin(this.MM - 2.0 * this.F) - 5.7E-4 * Math.sin(this.MM + 2.0 * this.F) + 5.6E-4 * this.E * Math.sin(2.0 * this.MM + this.M) - 4.2E-4 * Math.sin(3.0 * this.MM) + 4.2E-4 * this.E * Math.sin(this.M + 2.0 * this.F) + 3.8E-4 * this.E * Math.sin(this.M - 2.0 * this.F) - 2.4E-4 * this.E * Math.sin(2.0 * this.MM - this.M) - 1.7E-4 * Math.sin(this.omega) - 7.0E-5 * Math.sin(this.MM + 2.0 * this.M) + 4.0E-5 * Math.sin(2.0 * this.MM - 2.0 * this.F) + 4.0E-5 * Math.sin(3.0 * this.M) + 3.0E-5 * Math.sin(this.MM + this.M - 2.0 * this.F) + 3.0E-5 * Math.sin(2.0 * this.MM + 2.0 * this.F) - 3.0E-5 * Math.sin(this.MM + this.M + 2.0 * this.F) + 3.0E-5 * Math.sin(this.MM - this.M + 2.0 * this.F) - 2.0E-5 * Math.sin(this.MM - this.M - 2.0 * this.F) - 2.0E-5 * Math.sin(3.0 * this.MM + this.M) + 2.0E-5 * Math.sin(4.0 * this.MM);
    }
    
    public double fullMoon() {
        return -0.40614 * Math.sin(this.MM) + 0.17302 * this.E * Math.sin(this.M) + 0.01614 * Math.sin(2.0 * this.MM) + 0.01043 * Math.sin(2.0 * this.F) + 0.00734 * this.E * Math.sin(this.MM - this.M) - 0.00515 * this.E * Math.sin(this.MM + this.M) + 0.00209 * this.E * this.E * Math.sin(2.0 * this.M) - 0.00111 * Math.sin(this.MM - 2.0 * this.F) - 5.7E-4 * Math.sin(this.MM + 2.0 * this.F) + 5.6E-4 * this.E * Math.sin(2.0 * this.MM + this.M) - 4.2E-4 * Math.sin(3.0 * this.MM) + 4.2E-4 * this.E * Math.sin(this.M + 2.0 * this.F) + 3.8E-4 * this.E * Math.sin(this.M - 2.0 * this.F) - 2.4E-4 * this.E * Math.sin(2.0 * this.MM - this.M) - 1.7E-4 * Math.sin(this.omega) - 7.0E-5 * Math.sin(this.MM + 2.0 * this.M) + 4.0E-5 * Math.sin(2.0 * this.MM - 2.0 * this.F) + 4.0E-5 * Math.sin(3.0 * this.M) + 3.0E-5 * Math.sin(this.MM + this.M - 2.0 * this.F) + 3.0E-5 * Math.sin(2.0 * this.MM + 2.0 * this.F) - 3.0E-5 * Math.sin(this.MM + this.M + 2.0 * this.F) + 3.0E-5 * Math.sin(this.MM - this.M + 2.0 * this.F) - 2.0E-5 * Math.sin(this.MM - this.M - 2.0 * this.F) - 2.0E-5 * Math.sin(3.0 * this.MM + this.M) + 2.0E-5 * Math.sin(4.0 * this.MM);
    }
    
    public double quarterMoon() {
        return -0.62801 * Math.sin(this.MM) + 0.17172 * this.E * Math.sin(this.M) - 0.01183 * this.E * Math.sin(this.MM + this.M) + 0.00862 * Math.sin(2.0 * this.MM) + 0.00804 * Math.sin(2.0 * this.F) + 0.00454 * this.E * Math.sin(this.MM - this.M) + 0.00204 * this.E * this.E * Math.sin(2.0 * this.M) - 0.0018 * Math.sin(this.MM - 2.0 * this.F) - 7.0E-4 * Math.sin(this.MM + 2.0 * this.F) - 4.0E-4 * Math.sin(3.0 * this.MM) - 3.4E-4 * this.E * Math.sin(2.0 * this.MM - this.M) + 3.2E-4 * this.E * Math.sin(this.M + 2.0 * this.F) + 3.2E-4 * this.E * Math.sin(this.M - 2.0 * this.F) - 2.8E-4 * this.E * this.E * Math.sin(this.MM + 2.0 * this.M) + 2.7E-4 * this.E * Math.sin(2.0 * this.MM + this.M) - 1.7E-4 * Math.sin(this.omega) - 5.0E-5 * Math.sin(this.MM - this.M - 2.0 * this.F) + 4.0E-5 * Math.sin(2.0 * this.MM + 2.0 * this.F) - 4.0E-5 * Math.sin(this.MM + this.M + 2.0 * this.F) + 4.0E-5 * Math.sin(this.MM - 2.0 * this.M) + 3.0E-5 * Math.sin(this.MM + this.M - 2.0 * this.F) + 3.0E-5 * Math.sin(3.0 * this.M) + 2.0E-5 * Math.sin(2.0 * this.MM - 2.0 * this.F) + 2.0E-5 * Math.sin(this.MM - this.M + 2.0 * this.F) - 2.0E-5 * Math.sin(3.0 * this.MM + this.M);
    }
    
    public double quarter() {
        return 0.00306 - 3.8E-4 * this.E * Math.cos(this.M) + 2.6E-4 * Math.cos(this.MM) - 2.0E-5 * Math.cos(this.MM - this.M) + 2.0E-5 * Math.cos(this.MM + this.M) + 2.0E-5 * Math.cos(2.0 * this.F);
    }
    
    public void caldat(double JD) {
        JD += this.myLocOffset / 24.0;
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
    }
    
    public String monthString(final int m) {
        String[] monthArray = new String[12];
        monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        if (m < 1) {
            return "month = " + m;
        }
        return monthArray[m - 1];
    }
    
    public String dayString(final double jd) {
        String[] dayArray = new String[8];
        dayArray = new String[] { "", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        long num = (long)(jd + 0.5);
        num = num - num / 7L * 7L + 1L;
        return dayArray[(int)num];
    }
    
    public double moonAge() {
        return this.phase;
    }
}
