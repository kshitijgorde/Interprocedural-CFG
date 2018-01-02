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
    int myLocOffset;
    int Year;
    double time;
    
    public moonPhase(final int locOffset, final double theYear) {
        this.year = theYear;
        this.Year = (int)theYear;
        this.myLocOffset = locOffset;
    }
    
    public double newMoonJD() {
        this.k = (this.year - 2000.0) * 12.3685;
        this.k = Math.round(this.k);
        this.T = this.k / 1236.85;
        this.E = 1.0 - 0.002516 * this.T - 7.4E-6 * this.T * this.T;
        this.SunMoon(this.T, this.k);
        this.jde = this.JDE(this.T, this.k) + this.newMoon() + this.planets(this.T, this.k);
        return this.jde -= (66.0 + (this.year - 2000.0)) / 86400.0;
    }
    
    public double lastQuarterJD() {
        this.k = (this.year - 2000.0) * 12.3685;
        this.k = Math.round(this.k);
        this.T = this.k / 1236.85;
        this.k += 0.75;
        this.E = 1.0 - 0.002516 * this.T - 7.4E-6 * this.T * this.T;
        this.SunMoon(this.T, this.k);
        this.jde = this.JDE(this.T, this.k) + this.quarterMoon() - this.quarter() + this.planets(this.T, this.k);
        return this.jde -= (66.0 + (this.year - 2000.0)) / 86400.0;
    }
    
    public double firstQuarterJD() {
        this.k = (this.year - 2000.0) * 12.3685;
        this.k = Math.round(this.k);
        this.T = this.k / 1236.85;
        this.k += 0.25;
        this.E = 1.0 - 0.002516 * this.T - 7.4E-6 * this.T * this.T;
        this.SunMoon(this.T, this.k);
        this.jde = this.JDE(this.T, this.k) + this.quarterMoon() + this.quarter() + this.planets(this.T, this.k);
        return this.jde -= (66.0 + (this.year - 2000.0)) / 86400.0;
    }
    
    public double fullMoonJD() {
        this.k = (this.year - 2000.0) * 12.3685;
        this.k = Math.round(this.k);
        this.T = this.k / 1236.85;
        this.k += 0.5;
        this.E = 1.0 - 0.002516 * this.T - 7.4E-6 * this.T * this.T;
        this.SunMoon(this.T, this.k);
        this.jde = this.JDE(this.T, this.k) + this.fullMoon() + this.planets(this.T, this.k);
        return this.jde -= (66.0 + (this.year - 2000.0)) / 86400.0;
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
    
    public String monthString(final int m) {
        final String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        if (m > 0 && m < 13) {
            return monthArray[m - 1];
        }
        return "month";
    }
    
    public String dayString(final double jd) {
        final String[] dayArray = { null, "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun", null, null, null, null };
        long num = (long)(jd + 0.5);
        num = num - num / 7L * 7L + 1L;
        return dayArray[(int)num];
    }
    
    public double planets(final double T, final double k) {
        final double[] A = new double[15];
        final double[] B = new double[15];
        double sum = 0.0;
        A[1] = 299.77 + 0.107408 * k - 0.009173 * T * T;
        A[2] = 251.88 + 0.016321 * k;
        A[3] = 251.83 + 26.651996 * k;
        A[4] = 349.42 + 36.412478 * k;
        A[5] = 84.66 + 18.206239 * k;
        A[6] = 141.74 + 53.303771 * k;
        A[7] = 207.14 + 2.453732 * k;
        A[8] = 154.84 + 7.30686 * k;
        A[9] = 34.52 + 27.261239 * k;
        A[10] = 207.19 + 0.121824 * k;
        A[11] = 291.34 + 1.844379 * k;
        A[12] = 161.72 + 24.198154 * k;
        A[13] = 239.56 + 25.513099 * k;
        A[14] = 331.55 + 3.592518 * k;
        B[1] = 3.25E-4 * Math.sin(0.017453292519943295 * A[1]);
        B[2] = 1.65E-4 * Math.sin(0.017453292519943295 * A[2]);
        B[3] = 1.64E-4 * Math.sin(0.017453292519943295 * A[3]);
        B[4] = 1.26E-4 * Math.sin(0.017453292519943295 * A[4]);
        B[5] = 1.1E-4 * Math.sin(0.017453292519943295 * A[5]);
        B[6] = 2.6E-5 * Math.sin(0.017453292519943295 * A[6]);
        B[7] = 6.0E-5 * Math.sin(0.017453292519943295 * A[7]);
        B[8] = 5.6E-5 * Math.sin(0.017453292519943295 * A[8]);
        B[9] = 4.7E-5 * Math.sin(0.017453292519943295 * A[9]);
        B[10] = 4.2E-5 * Math.sin(0.017453292519943295 * A[10]);
        B[11] = 4.0E-5 * Math.sin(0.017453292519943295 * A[11]);
        B[12] = 3.7E-5 * Math.sin(0.017453292519943295 * A[12]);
        B[13] = 3.5E-5 * Math.sin(0.017453292519943295 * A[13]);
        B[14] = 2.3E-5 * Math.sin(0.017453292519943295 * A[14]);
        for (int i = 1; i < 15; ++i) {
            sum += B[i];
        }
        return sum;
    }
}