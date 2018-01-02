// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.transforms;

import com.itt.J2KViewer.georvm.coords.Utm_Coord_3d;
import com.itt.J2KViewer.util.Log;

public class Geodetic_to_UTM_Converter
{
    public static final double PI = 3.141592653589793;
    public static final double MIN_LAT = -1.4049900478554351;
    public static final double MAX_LAT = 1.4748032179352084;
    public static final long MIN_EASTING = 100000L;
    public static final long MAX_EASTING = 900000L;
    public static final long MIN_NORTHING = 0L;
    public static final long MAX_NORTHING = 10000000L;
    public static final double PI_OVER = 1.5707963267948966;
    public static final double TM_MAX_LAT = 1.570621793869697;
    public static final double TM_MAX_DELTA_LONG = 1.5707963267948966;
    public static double UTM_a;
    public static double UTM_f;
    public static long UTM_Override;
    public static double MIN_SCALE_FACTOR;
    public static double MAX_SCALE_FACTOR;
    public static double TranMerc_a;
    public static double TranMerc_f;
    public static double TranMerc_es;
    public static double TranMerc_ebs;
    public static double TranMerc_Origin_Lat;
    public static double TranMerc_Origin_Long;
    public static double TranMerc_False_Northing;
    public static double TranMerc_False_Easting;
    public static double TranMerc_Scale_Factor;
    public static double TranMerc_ap;
    public static double TranMerc_bp;
    public static double TranMerc_cp;
    public static double TranMerc_dp;
    public static double TranMerc_ep;
    public static double TranMerc_Delta_Easting;
    public static double TranMerc_Delta_Northing;
    private static Log log;
    static /* synthetic */ Class class$com$itt$J2KViewer$georvm$transforms$Geodetic_to_UTM_Converter;
    
    public Utm_Coord_3d Convert_Geodetic_To_UTM(final double n, double n2) {
        String s = "";
        final double n3 = 0.0;
        final double n4 = 500000.0;
        double n5 = 0.0;
        final double n6 = 0.9996;
        final Utm_Coord_3d utm_Coord_3d = new Utm_Coord_3d();
        if (n2 < -3.141592653589793) {
            n2 += 6.283185307179586;
        }
        else if (n2 > 6.283185307179586) {
            n2 -= 6.283185307179586;
        }
        if (n < -1.4049900478554351 || n > 1.4748032179352084) {
            s = "Latitude out of range";
        }
        if (s.length() == 0) {
            if (n2 < 0.0) {
                n2 += 6.283185307279586;
            }
            final long n7 = (long)(n * 180.0 / 3.141592653589793);
            final long n8 = (long)(n2 * 180.0 / 3.141592653589793);
            long n9;
            if (n2 < 3.141592653589793) {
                n9 = (long)(31.0 + n2 * 180.0 / 3.141592653589793 / 6.0);
            }
            else {
                n9 = (long)(n2 * 180.0 / 3.141592653589793 / 6.0 - 29.0);
            }
            if (n9 > 60L) {
                n9 = 1L;
            }
            if (n7 > 55L && n7 < 64L && n8 > -1L && n8 < 3L) {
                n9 = 31L;
            }
            if (n7 > 55L && n7 < 64L && n8 > 2L && n8 < 12L) {
                n9 = 32L;
            }
            if (n7 > 71L && n8 > -1L && n8 < 9L) {
                n9 = 31L;
            }
            if (n7 > 71L && n8 > 8L && n8 < 21L) {
                n9 = 33L;
            }
            if (n7 > 71L && n8 > 20L && n8 < 33L) {
                n9 = 35L;
            }
            if (n7 > 71L && n8 > 32L && n8 < 42L) {
                n9 = 37L;
            }
            if (Geodetic_to_UTM_Converter.UTM_Override > 0L) {
                if (n9 == 1L && Geodetic_to_UTM_Converter.UTM_Override == 60L) {
                    n9 = Geodetic_to_UTM_Converter.UTM_Override;
                }
                else if (n9 == 60L && Geodetic_to_UTM_Converter.UTM_Override == 1L) {
                    n9 = Geodetic_to_UTM_Converter.UTM_Override;
                }
                else if (n9 - 1L <= Geodetic_to_UTM_Converter.UTM_Override && Geodetic_to_UTM_Converter.UTM_Override <= n9 + 1L) {
                    n9 = Geodetic_to_UTM_Converter.UTM_Override;
                }
                else {
                    s = "UTM_ZONE_OVERRIDE_ERROR";
                }
            }
            if (s.length() == 0) {
                double n10;
                if (n9 >= 31L) {
                    n10 = (6L * n9 - 183L) * 3.141592653589793 / 180.0;
                }
                else {
                    n10 = (6L * n9 + 177L) * 3.141592653589793 / 180.0;
                }
                utm_Coord_3d.zone = (byte)n9;
                if (n < 0.0) {
                    n5 = 1.0E7;
                    utm_Coord_3d.hemisphere_north = false;
                }
                else {
                    utm_Coord_3d.hemisphere_north = true;
                }
                this.Set_Transverse_Mercator_Parameters(Geodetic_to_UTM_Converter.UTM_a, Geodetic_to_UTM_Converter.UTM_f, n3, n10, n4, n5, n6);
                final double[] convert_Geodetic_To_Transverse_Mercator = this.Convert_Geodetic_To_Transverse_Mercator(n, n2);
                utm_Coord_3d.x = convert_Geodetic_To_Transverse_Mercator[1];
                utm_Coord_3d.y = convert_Geodetic_To_Transverse_Mercator[0];
                if (convert_Geodetic_To_Transverse_Mercator[0] < 100000.0 || convert_Geodetic_To_Transverse_Mercator[0] > 900000.0) {}
                if (convert_Geodetic_To_Transverse_Mercator[1] < 0.0 || convert_Geodetic_To_Transverse_Mercator[1] > 1.0E7) {}
            }
        }
        else {
            Geodetic_to_UTM_Converter.log.error(s);
        }
        return utm_Coord_3d;
    }
    
    String Set_Transverse_Mercator_Parameters(final double tranMerc_a, final double tranMerc_f, final double tranMerc_Origin_Lat, double tranMerc_Origin_Long, final double tranMerc_False_Easting, final double tranMerc_False_Northing, final double tranMerc_Scale_Factor) {
        final double n = 1.0 / tranMerc_f;
        String s = "";
        if (tranMerc_a <= 0.0) {
            s = "Semi-major axis must be greater than zero";
        }
        if (n < 250.0 || n > 350.0) {
            s = "Inverse flattening must be between 250 and 350";
        }
        if (tranMerc_Origin_Lat < -1.570621793869697 || tranMerc_Origin_Lat > 1.570621793869697) {
            s = "Origin latitude out of range";
        }
        if (tranMerc_Origin_Long < -3.141592653589793 || tranMerc_Origin_Long > 6.283185307179586) {
            s = "Origin longitude out of range";
        }
        if (tranMerc_Scale_Factor < Geodetic_to_UTM_Converter.MIN_SCALE_FACTOR || tranMerc_Scale_Factor > Geodetic_to_UTM_Converter.MAX_SCALE_FACTOR) {
            s = "TRANMERC_SCALE_FACTOR_ERROR";
        }
        if (s.length() == 0) {
            Geodetic_to_UTM_Converter.TranMerc_a = tranMerc_a;
            Geodetic_to_UTM_Converter.TranMerc_f = tranMerc_f;
            Geodetic_to_UTM_Converter.TranMerc_Origin_Lat = 0.0;
            Geodetic_to_UTM_Converter.TranMerc_Origin_Long = 0.0;
            Geodetic_to_UTM_Converter.TranMerc_False_Northing = 0.0;
            Geodetic_to_UTM_Converter.TranMerc_False_Easting = 0.0;
            Geodetic_to_UTM_Converter.TranMerc_Scale_Factor = 1.0;
            Geodetic_to_UTM_Converter.TranMerc_es = 2.0 * Geodetic_to_UTM_Converter.TranMerc_f - Geodetic_to_UTM_Converter.TranMerc_f * Geodetic_to_UTM_Converter.TranMerc_f;
            Geodetic_to_UTM_Converter.TranMerc_ebs = 1.0 / (1.0 - Geodetic_to_UTM_Converter.TranMerc_es) - 1.0;
            final double n2 = Geodetic_to_UTM_Converter.TranMerc_a * (1.0 - Geodetic_to_UTM_Converter.TranMerc_f);
            final double n3 = (Geodetic_to_UTM_Converter.TranMerc_a - n2) / (Geodetic_to_UTM_Converter.TranMerc_a + n2);
            final double n4 = n3 * n3;
            final double n5 = n4 * n3;
            final double n6 = n5 * n3;
            final double n7 = n6 * n3;
            Geodetic_to_UTM_Converter.TranMerc_ap = Geodetic_to_UTM_Converter.TranMerc_a * (1.0 - n3 + 5.0 * (n4 - n5) / 4.0 + 81.0 * (n6 - n7) / 64.0);
            Geodetic_to_UTM_Converter.TranMerc_bp = 3.0 * Geodetic_to_UTM_Converter.TranMerc_a * (n3 - n4 + 7.0 * (n5 - n6) / 8.0 + 55.0 * n7 / 64.0) / 2.0;
            Geodetic_to_UTM_Converter.TranMerc_cp = 15.0 * Geodetic_to_UTM_Converter.TranMerc_a * (n4 - n5 + 3.0 * (n6 - n7) / 4.0) / 16.0;
            Geodetic_to_UTM_Converter.TranMerc_dp = 35.0 * Geodetic_to_UTM_Converter.TranMerc_a * (n5 - n6 + 11.0 * n7 / 16.0) / 48.0;
            Geodetic_to_UTM_Converter.TranMerc_ep = 315.0 * Geodetic_to_UTM_Converter.TranMerc_a * (n6 - n7) / 512.0;
            final double[] convert_Geodetic_To_Transverse_Mercator = this.Convert_Geodetic_To_Transverse_Mercator(1.570621793869697, 1.5707963267948966);
            Geodetic_to_UTM_Converter.TranMerc_Delta_Easting = convert_Geodetic_To_Transverse_Mercator[0];
            Geodetic_to_UTM_Converter.TranMerc_Delta_Northing = convert_Geodetic_To_Transverse_Mercator[1];
            Geodetic_to_UTM_Converter.TranMerc_Delta_Easting = this.Convert_Geodetic_To_Transverse_Mercator(0.0, 1.5707963267948966)[0];
            Geodetic_to_UTM_Converter.TranMerc_Origin_Lat = tranMerc_Origin_Lat;
            if (tranMerc_Origin_Long > 3.141592653589793) {
                tranMerc_Origin_Long -= 6.283185307179586;
            }
            Geodetic_to_UTM_Converter.TranMerc_Origin_Long = tranMerc_Origin_Long;
            Geodetic_to_UTM_Converter.TranMerc_False_Northing = tranMerc_False_Northing;
            Geodetic_to_UTM_Converter.TranMerc_False_Easting = tranMerc_False_Easting;
            Geodetic_to_UTM_Converter.TranMerc_Scale_Factor = tranMerc_Scale_Factor;
        }
        else {
            Geodetic_to_UTM_Converter.log.error(s);
        }
        return s;
    }
    
    double[] Convert_Geodetic_To_Transverse_Mercator(final double n, double n2) {
        String s = "";
        final double[] array = { 0.0, 0.0 };
        if (n < -1.570621793869697 || n > 1.570621793869697) {
            s = "Latitude out of range";
        }
        if (n2 > 3.141592653589793) {
            n2 -= 6.283185307179586;
        }
        if (n2 < Geodetic_to_UTM_Converter.TranMerc_Origin_Long - 1.5707963267948966 || n2 > Geodetic_to_UTM_Converter.TranMerc_Origin_Long + 1.5707963267948966) {
            double n3;
            if (n2 < 0.0) {
                n3 = n2 + 6.283185307179586;
            }
            else {
                n3 = n2;
            }
            double tranMerc_Origin_Long;
            if (Geodetic_to_UTM_Converter.TranMerc_Origin_Long < 0.0) {
                tranMerc_Origin_Long = Geodetic_to_UTM_Converter.TranMerc_Origin_Long + 6.283185307179586;
            }
            else {
                tranMerc_Origin_Long = Geodetic_to_UTM_Converter.TranMerc_Origin_Long;
            }
            if (n3 < tranMerc_Origin_Long - 1.5707963267948966 || n3 > tranMerc_Origin_Long + 1.5707963267948966) {
                s = "TRANMERC_LON_ERROR";
            }
        }
        if (s.length() == 0) {
            double n4 = n2 - Geodetic_to_UTM_Converter.TranMerc_Origin_Long;
            if (Math.abs(n4) > 0.15707963267948966) {}
            if (n4 > 3.141592653589793) {
                n4 -= 6.283185307179586;
            }
            if (n4 < -3.141592653589793) {
                n4 += 6.283185307179586;
            }
            if (Math.abs(n4) < 2.0E-10) {
                n4 = 0.0;
            }
            final double sin = Math.sin(n);
            final double cos = Math.cos(n);
            final double n5 = cos * cos;
            final double n6 = n5 * cos;
            final double n7 = n6 * n5;
            final double n8 = n7 * n5;
            final double tan = Math.tan(n);
            final double n9 = tan * tan;
            final double n10 = n9 * tan * tan;
            final double n11 = n10 * tan * tan;
            final double n12 = Geodetic_to_UTM_Converter.TranMerc_ebs * n5;
            final double n13 = n12 * n12;
            final double n14 = n13 * n12;
            final double n15 = n14 * n12;
            final double sphsn = this.SPHSN(n);
            array[0] = Geodetic_to_UTM_Converter.TranMerc_False_Northing + (this.SPHTMD(n) - this.SPHTMD(Geodetic_to_UTM_Converter.TranMerc_Origin_Lat)) * Geodetic_to_UTM_Converter.TranMerc_Scale_Factor + Math.pow(n4, 2.0) * (sphsn * sin * cos * Geodetic_to_UTM_Converter.TranMerc_Scale_Factor / 2.0) + Math.pow(n4, 4.0) * (sphsn * sin * n6 * Geodetic_to_UTM_Converter.TranMerc_Scale_Factor * (5.0 - n9 + 9.0 * n12 + 4.0 * n13) / 24.0) + Math.pow(n4, 6.0) * (sphsn * sin * n7 * Geodetic_to_UTM_Converter.TranMerc_Scale_Factor * (61.0 - 58.0 * n9 + n10 + 270.0 * n12 - 330.0 * n9 * n12 + 445.0 * n13 + 324.0 * n14 - 680.0 * n9 * n13 + 88.0 * n15 - 600.0 * n9 * n14 - 192.0 * n9 * n15) / 720.0) + Math.pow(n4, 8.0) * (sphsn * sin * n8 * Geodetic_to_UTM_Converter.TranMerc_Scale_Factor * (1385.0 - 3111.0 * n9 + 543.0 * n10 - n11) / 40320.0);
            array[1] = Geodetic_to_UTM_Converter.TranMerc_False_Easting + n4 * (sphsn * cos * Geodetic_to_UTM_Converter.TranMerc_Scale_Factor) + Math.pow(n4, 3.0) * (sphsn * n6 * Geodetic_to_UTM_Converter.TranMerc_Scale_Factor * (1.0 - n9 + n12) / 6.0) + Math.pow(n4, 5.0) * (sphsn * n7 * Geodetic_to_UTM_Converter.TranMerc_Scale_Factor * (5.0 - 18.0 * n9 + n10 + 14.0 * n12 - 58.0 * n9 * n12 + 13.0 * n13 + 4.0 * n14 - 64.0 * n9 * n13 - 24.0 * n9 * n14) / 120.0) + Math.pow(n4, 7.0) * (sphsn * n8 * Geodetic_to_UTM_Converter.TranMerc_Scale_Factor * (61.0 - 479.0 * n9 + 179.0 * n10 - n11) / 5040.0);
        }
        else {
            Geodetic_to_UTM_Converter.log.error(s);
        }
        return array;
    }
    
    public double SPHTMD(final double n) {
        return Geodetic_to_UTM_Converter.TranMerc_ap * n - Geodetic_to_UTM_Converter.TranMerc_bp * Math.sin(2.0 * n) + Geodetic_to_UTM_Converter.TranMerc_cp * Math.sin(4.0 * n) - Geodetic_to_UTM_Converter.TranMerc_dp * Math.sin(6.0 * n) + Geodetic_to_UTM_Converter.TranMerc_ep * Math.sin(8.0 * n);
    }
    
    public double SPHSN(final double n) {
        return Geodetic_to_UTM_Converter.TranMerc_a / Math.sqrt(1.0 - Geodetic_to_UTM_Converter.TranMerc_es * Math.pow(Math.sin(n), 2.0));
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        Geodetic_to_UTM_Converter.UTM_a = 6378137.0;
        Geodetic_to_UTM_Converter.UTM_f = 0.0033528106647474805;
        Geodetic_to_UTM_Converter.UTM_Override = 0L;
        Geodetic_to_UTM_Converter.MIN_SCALE_FACTOR = 0.3;
        Geodetic_to_UTM_Converter.MAX_SCALE_FACTOR = 3.0;
        Geodetic_to_UTM_Converter.TranMerc_a = 6378137.0;
        Geodetic_to_UTM_Converter.TranMerc_f = 0.0033528106647474805;
        Geodetic_to_UTM_Converter.TranMerc_es = 0.00669437999014138;
        Geodetic_to_UTM_Converter.TranMerc_ebs = 0.0067394967565869;
        Geodetic_to_UTM_Converter.TranMerc_Origin_Lat = 0.0;
        Geodetic_to_UTM_Converter.TranMerc_Origin_Long = 0.0;
        Geodetic_to_UTM_Converter.TranMerc_False_Northing = 0.0;
        Geodetic_to_UTM_Converter.TranMerc_False_Easting = 0.0;
        Geodetic_to_UTM_Converter.TranMerc_Scale_Factor = 1.0;
        Geodetic_to_UTM_Converter.TranMerc_ap = 6367449.1458008;
        Geodetic_to_UTM_Converter.TranMerc_bp = 16038.508696861;
        Geodetic_to_UTM_Converter.TranMerc_cp = 16.832613334334;
        Geodetic_to_UTM_Converter.TranMerc_dp = 0.021984404273757;
        Geodetic_to_UTM_Converter.TranMerc_ep = 3.1148371319283E-5;
        Geodetic_to_UTM_Converter.TranMerc_Delta_Easting = 4.0E7;
        Geodetic_to_UTM_Converter.TranMerc_Delta_Northing = 4.0E7;
        Geodetic_to_UTM_Converter.log = new Log((Geodetic_to_UTM_Converter.class$com$itt$J2KViewer$georvm$transforms$Geodetic_to_UTM_Converter == null) ? (Geodetic_to_UTM_Converter.class$com$itt$J2KViewer$georvm$transforms$Geodetic_to_UTM_Converter = class$("com.itt.J2KViewer.georvm.transforms.Geodetic_to_UTM_Converter")) : Geodetic_to_UTM_Converter.class$com$itt$J2KViewer$georvm$transforms$Geodetic_to_UTM_Converter);
    }
}
