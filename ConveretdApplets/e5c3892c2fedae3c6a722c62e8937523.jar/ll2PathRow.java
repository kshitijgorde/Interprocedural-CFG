// 
// Decompiled by Procyon v0.5.30
// 

public class ll2PathRow
{
    private static final double TWO_PI = 6.283185307179586;
    private static final double R2D = 57.2957795131;
    private static final double D2R = 0.01745329252;
    private static final double SEC_PER_DAY = 86400.0;
    private static final double semi_major = 6378137.0;
    private static final double semi_minor = 6356752.3;
    private static final double scenes_per_orbit = 248.0;
    private static final double descending_node_row = 60.0;
    private static final double ascending_node_row = 184.0;
    private static final double earth_spin_rate = 7.27220521664304E-5;
    private double inclination;
    private double long_path1_row60;
    private double long_path1_row184;
    private double days_per_cycle;
    private double orbits_per_cycle;
    private double sc_ang_rate;
    public int path;
    public int row;
    public double longitude;
    public double latitude;
    public double doublePath;
    public double doubleRow;
    public static final int WRS1_REFERENCE_SYSTEM = 1;
    public static final int WRS2_REFERENCE_SYSTEM = 2;
    private double equator_row;
    private double long_path1_at_equator;
    private double direction_sign;
    
    ll2PathRow(final int n, final boolean b) {
        if (n == 1) {
            this.inclination = 1.72948166239184;
            this.long_path1_row60 = -1.1428415942096002;
            this.long_path1_row184 = 1.7736035858824002;
            this.days_per_cycle = 18.0;
            this.orbits_per_cycle = 251.0;
        }
        else {
            if (n != 2) {
                throw new IllegalArgumentException("Unknown reference system");
            }
            this.inclination = 1.7140808770721918;
            this.long_path1_row60 = -1.127482696792;
            this.long_path1_row184 = 1.7983781855486896;
            this.days_per_cycle = 16.0;
            this.orbits_per_cycle = 233.0;
        }
        this.sc_ang_rate = 6.283185307179586 * this.orbits_per_cycle / (this.days_per_cycle * 86400.0);
        if (b) {
            this.equator_row = 184.0;
            this.long_path1_at_equator = this.long_path1_row184;
            this.direction_sign = -1.0;
        }
        else {
            this.equator_row = 60.0;
            this.long_path1_at_equator = this.long_path1_row60;
            this.direction_sign = 1.0;
        }
    }
    
    public void toPathRow(double n, double n2) {
        if (n > 81.08) {
            n = 81.08;
        }
        if (n < -81.84) {
            n = -81.84;
        }
        n *= 0.01745329252;
        for (n2 *= 0.01745329252; n2 > 3.141592653589793; n2 -= 6.283185307179586) {}
        while (n2 < -3.141592653589793) {
            n2 += 6.283185307179586;
        }
        final double atan = Math.atan(Math.tan(n) * 0.9966471871018135 * 0.9966471871018135);
        final double n3 = this.direction_sign * Math.asin(-1.0 * (Math.sin(atan) / Math.sin(this.inclination)));
        final double doubleRow = 248.0 * n3 / 6.283185307179586 + this.equator_row;
        this.doubleRow = doubleRow;
        this.row = (int)Math.floor(doubleRow + 0.5);
        double doublePath = (-(n2 + this.direction_sign * Math.atan2(Math.tan(atan) / Math.tan(this.inclination), Math.cos(n3) / Math.cos(atan)) + n3 * (7.27220521664304E-5 / this.sc_ang_rate)) + this.long_path1_at_equator) / 6.283185307179586 * this.orbits_per_cycle + 1.0;
        if (this.row < 1) {
            doublePath -= 16.0;
            this.row += (int)248.0;
        }
        if (this.row > 248.0) {
            doublePath += 16.0;
            this.row -= (int)248.0;
        }
        this.doublePath = doublePath;
        this.path = (int)Math.floor(doublePath + 0.5);
        if (this.path < 1) {
            this.path += (int)this.orbits_per_cycle;
        }
        if (this.path > this.orbits_per_cycle) {
            this.path -= (int)this.orbits_per_cycle;
        }
    }
    
    public void toLatLong(final int n, final int n2) {
        final double n3 = this.direction_sign * (n2 - this.equator_row) * 6.283185307179586 / 248.0;
        final double asin = Math.asin(-1.0 * Math.sin(n3) * Math.sin(this.inclination));
        this.latitude = Math.atan(Math.tan(asin) * 1.0033640920694678 * 1.0033640920694678);
        this.latitude *= 57.2957795131;
        this.longitude = this.long_path1_at_equator - (n - 1) / this.orbits_per_cycle * 6.283185307179586 - this.direction_sign * Math.atan2(Math.tan(asin) / Math.tan(this.inclination), Math.cos(n3) / Math.cos(asin)) - this.direction_sign * n3 * (7.27220521664304E-5 / this.sc_ang_rate);
        while (this.longitude > 3.141592653589793) {
            this.longitude -= 6.283185307179586;
        }
        while (this.longitude < -3.141592653589793) {
            this.longitude += 6.283185307179586;
        }
        this.longitude *= 57.2957795131;
    }
}
