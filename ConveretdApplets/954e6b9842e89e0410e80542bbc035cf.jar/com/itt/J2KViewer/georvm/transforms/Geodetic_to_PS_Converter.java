// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.transforms;

import com.itt.J2KViewer.util.Log;

public class Geodetic_to_PS_Converter
{
    public static final double PI = 3.141592653589793;
    public static final double PI_OVER_2 = 1.5707963267948966;
    public static final double TWO_PI = 6.283185307179586;
    public static double POLAR_POW;
    public static final double PI_Over_4 = 0.7853981633974483;
    public static double Polar_a;
    public static double Polar_f;
    static double es;
    static double es_OVER_2;
    static double Southern_Hemisphere;
    static double mc;
    static double tc;
    static double e4;
    static double Polar_a_mc;
    static double two_Polar_a;
    public static double Polar_Origin_Lat;
    public static double Polar_Origin_Long;
    public static double Polar_False_Easting;
    public static double Polar_False_Northing;
    static double Polar_Delta_Easting;
    static double Polar_Delta_Northing;
    private static Log log;
    static /* synthetic */ Class class$com$itt$J2KViewer$georvm$transforms$Geodetic_to_PS_Converter;
    
    public Geodetic_to_PS_Converter() {
        this.Set_Polar_Pow(-999.0);
    }
    
    private double Set_Polar_Pow(final double n) {
        return Geodetic_to_PS_Converter.POLAR_POW = Math.pow((1.0 - n) / (1.0 + n), Geodetic_to_PS_Converter.es_OVER_2);
    }
    
    String Set_Polar_Stereographic_Parameters(final double polar_a, final double polar_f, final double polar_Origin_Lat, double polar_Origin_Long, final double polar_False_Easting, final double polar_False_Northing) {
        final double n = 1.0 / polar_f;
        String s = "";
        if (polar_a <= 0.0) {
            s = "Semi-major axis must be greater than zero";
        }
        if (n < 250.0 || n > 350.0) {
            s = "Inverse flattening must be between 250 and 350";
        }
        if (polar_Origin_Lat < -1.5707963267948966 || polar_Origin_Lat > 1.5707963267948966) {
            s = "Origin Latitude out of range";
        }
        if (polar_Origin_Long < -3.141592653589793 || polar_Origin_Long > 6.283185307179586) {
            s = "Origin Longitude out of range";
        }
        if (s.length() == 0) {
            Geodetic_to_PS_Converter.Polar_a = polar_a;
            Geodetic_to_PS_Converter.two_Polar_a = 2.0 * Geodetic_to_PS_Converter.Polar_a;
            Geodetic_to_PS_Converter.Polar_f = polar_f;
            if (polar_Origin_Long > 3.141592653589793) {
                polar_Origin_Long -= 6.283185307179586;
            }
            if (polar_Origin_Lat < 0.0) {
                Geodetic_to_PS_Converter.Southern_Hemisphere = 1.0;
                Geodetic_to_PS_Converter.Polar_Origin_Lat = -polar_Origin_Lat;
                Geodetic_to_PS_Converter.Polar_Origin_Long = -polar_Origin_Long;
            }
            else {
                Geodetic_to_PS_Converter.Southern_Hemisphere = 0.0;
                Geodetic_to_PS_Converter.Polar_Origin_Lat = polar_Origin_Lat;
                Geodetic_to_PS_Converter.Polar_Origin_Long = polar_Origin_Long;
            }
            Geodetic_to_PS_Converter.Polar_False_Easting = polar_False_Easting;
            Geodetic_to_PS_Converter.Polar_False_Northing = polar_False_Northing;
            Geodetic_to_PS_Converter.es = Math.sqrt(2.0 * Geodetic_to_PS_Converter.Polar_f - Geodetic_to_PS_Converter.Polar_f * Geodetic_to_PS_Converter.Polar_f);
            Geodetic_to_PS_Converter.es_OVER_2 = Geodetic_to_PS_Converter.es / 2.0;
            if (Math.abs(Math.abs(Geodetic_to_PS_Converter.Polar_Origin_Lat) - 1.5707963267948966) > 1.0E-10) {
                final double n2 = Geodetic_to_PS_Converter.es * Math.sin(Geodetic_to_PS_Converter.Polar_Origin_Lat);
                final double set_Polar_Pow = this.Set_Polar_Pow(n2);
                Geodetic_to_PS_Converter.mc = Math.cos(Geodetic_to_PS_Converter.Polar_Origin_Lat) / Math.sqrt(1.0 - n2 * n2);
                Geodetic_to_PS_Converter.Polar_a_mc = Geodetic_to_PS_Converter.Polar_a * Geodetic_to_PS_Converter.mc;
                Geodetic_to_PS_Converter.tc = Math.tan(0.7853981633974483 - Geodetic_to_PS_Converter.Polar_Origin_Lat / 2.0) / set_Polar_Pow;
            }
            else {
                final double n3 = 1.0 + Geodetic_to_PS_Converter.es;
                final double n4 = 1.0 - Geodetic_to_PS_Converter.es;
                Geodetic_to_PS_Converter.e4 = Math.sqrt(Math.pow(n3, n3) * Math.pow(n4, n4));
            }
        }
        else {
            Geodetic_to_PS_Converter.log.error(s);
        }
        Geodetic_to_PS_Converter.Polar_Delta_Northing = Math.abs(this.Convert_Geodetic_To_Polar_Stereographic(0.0, Geodetic_to_PS_Converter.Polar_Origin_Long)[1]) + 0.01;
        Geodetic_to_PS_Converter.Polar_Delta_Easting = Geodetic_to_PS_Converter.Polar_Delta_Northing;
        return s;
    }
    
    double[] Convert_Geodetic_To_Polar_Stereographic(double n, double n2) {
        double n3 = 0.0;
        double n4 = 0.0;
        String s = "";
        if (n < -1.5707963267948966 || n > 1.5707963267948966) {
            s = "Latitude out of range";
        }
        if (n < 0.0 && Geodetic_to_PS_Converter.Southern_Hemisphere == 0.0) {
            s = "Latitude and Origin Latitude in different hemispheres";
        }
        if (n > 0.0 && Geodetic_to_PS_Converter.Southern_Hemisphere == 1.0) {
            s = "Latitude and Origin Latitude in different hemispheres";
        }
        if (n2 < -3.141592653589793 || n2 > 6.283185307179586) {
            s = "Longitude out of range";
        }
        if (s.length() == 0) {
            if (Math.abs(Math.abs(n) - 1.5707963267948966) < 1.0E-10) {
                n3 = 0.0;
                n4 = 0.0;
            }
            else {
                if (Geodetic_to_PS_Converter.Southern_Hemisphere != 0.0) {
                    n2 *= -1.0;
                    n *= -1.0;
                }
                double n5 = n2 - Geodetic_to_PS_Converter.Polar_Origin_Long;
                if (n5 > 3.141592653589793) {
                    n5 -= 6.283185307179586;
                }
                if (n5 < -3.141592653589793) {
                    n5 += 6.283185307179586;
                }
                final double n6 = Math.tan(0.7853981633974483 - n / 2.0) / this.Set_Polar_Pow(Geodetic_to_PS_Converter.es * Math.sin(n));
                double n7;
                if (Math.abs(Math.abs(Geodetic_to_PS_Converter.Polar_Origin_Lat) - 1.5707963267948966) > 1.0E-10) {
                    n7 = Geodetic_to_PS_Converter.Polar_a_mc * n6 / Geodetic_to_PS_Converter.tc;
                }
                else {
                    n7 = Geodetic_to_PS_Converter.two_Polar_a * n6 / Geodetic_to_PS_Converter.e4;
                }
                n3 = n7 * Math.sin(n5) + Geodetic_to_PS_Converter.Polar_False_Easting;
                if (Geodetic_to_PS_Converter.Southern_Hemisphere != 0.0) {
                    n3 *= -1.0;
                    n4 = n7 * Math.cos(n5) + Geodetic_to_PS_Converter.Polar_False_Northing;
                }
                else {
                    n4 = -n7 * Math.cos(n5) + Geodetic_to_PS_Converter.Polar_False_Northing;
                }
            }
        }
        else {
            Geodetic_to_PS_Converter.log.error(s);
        }
        return new double[] { n3, n4 };
    }
    
    double[] Convert_Polar_Stereographic_To_Geodetic(final double n, final double n2, double n3, double polar_Origin_Long) {
        double n4 = 0.0;
        String s = "";
        if (n > Geodetic_to_PS_Converter.Polar_False_Easting + Geodetic_to_PS_Converter.Polar_Delta_Easting || n < Geodetic_to_PS_Converter.Polar_False_Easting - Geodetic_to_PS_Converter.Polar_Delta_Easting) {
            s = "Easting out of range";
        }
        if (n2 > Geodetic_to_PS_Converter.Polar_False_Northing + Geodetic_to_PS_Converter.Polar_Delta_Northing || n2 < Geodetic_to_PS_Converter.Polar_False_Northing - Geodetic_to_PS_Converter.Polar_Delta_Northing) {
            s = "Northing out of range";
        }
        if (s.length() == 0) {
            final double sqrt = Math.sqrt(n * n + n2 * n2);
            if (sqrt > Geodetic_to_PS_Converter.Polar_False_Easting + Geodetic_to_PS_Converter.Polar_Delta_Easting || sqrt > Geodetic_to_PS_Converter.Polar_False_Northing + Geodetic_to_PS_Converter.Polar_Delta_Northing || sqrt < Geodetic_to_PS_Converter.Polar_False_Easting - Geodetic_to_PS_Converter.Polar_Delta_Easting || sqrt < Geodetic_to_PS_Converter.Polar_False_Northing - Geodetic_to_PS_Converter.Polar_Delta_Northing) {
                s = "Point is outside of projection area";
            }
        }
        else {
            Geodetic_to_PS_Converter.log.error(s);
        }
        if (s.length() == 0) {
            double n5 = n2 - Geodetic_to_PS_Converter.Polar_False_Northing;
            double n6 = n - Geodetic_to_PS_Converter.Polar_False_Easting;
            if (n5 == 0.0 && n6 == 0.0) {
                n3 = 1.5707963267948966;
                polar_Origin_Long = Geodetic_to_PS_Converter.Polar_Origin_Long;
            }
            else {
                if (Geodetic_to_PS_Converter.Southern_Hemisphere != 0.0) {
                    n5 *= -1.0;
                    n6 *= -1.0;
                }
                final double sqrt2 = Math.sqrt(n6 * n6 + n5 * n5);
                double n7;
                if (Math.abs(Math.abs(Geodetic_to_PS_Converter.Polar_Origin_Lat) - 1.5707963267948966) > 1.0E-10) {
                    n7 = sqrt2 * Geodetic_to_PS_Converter.tc / Geodetic_to_PS_Converter.Polar_a_mc;
                }
                else {
                    n7 = sqrt2 * Geodetic_to_PS_Converter.e4 / Geodetic_to_PS_Converter.two_Polar_a;
                }
                double n8;
                for (n8 = 1.5707963267948966 - 2.0 * Math.atan(n7); Math.abs(n8 - n4) > 1.0E-10; n4 = n8, n8 = 1.5707963267948966 - 2.0 * Math.atan(n7 * this.Set_Polar_Pow(Geodetic_to_PS_Converter.es * Math.sin(n8)))) {}
                n3 = n8;
                polar_Origin_Long = Geodetic_to_PS_Converter.Polar_Origin_Long + Math.atan2(n6, -n5);
                if (polar_Origin_Long > 3.141592653589793) {
                    polar_Origin_Long -= 6.283185307179586;
                }
                else if (polar_Origin_Long < -3.141592653589793) {
                    polar_Origin_Long += 6.283185307179586;
                }
                if (n3 > 1.5707963267948966) {
                    n3 = 1.5707963267948966;
                }
                else if (n3 < -1.5707963267948966) {
                    n3 = -1.5707963267948966;
                }
                if (polar_Origin_Long > 3.141592653589793) {
                    polar_Origin_Long = 3.141592653589793;
                }
                else if (polar_Origin_Long < -3.141592653589793) {
                    polar_Origin_Long = -3.141592653589793;
                }
            }
            if (Geodetic_to_PS_Converter.Southern_Hemisphere != 0.0) {
                n3 *= -1.0;
                polar_Origin_Long *= -1.0;
            }
        }
        else {
            Geodetic_to_PS_Converter.log.error(s);
        }
        return new double[] { n3, polar_Origin_Long };
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
        Geodetic_to_PS_Converter.Polar_a = 6378137.0;
        Geodetic_to_PS_Converter.Polar_f = 0.0033528106647474805;
        Geodetic_to_PS_Converter.es = 0.08181919084262188;
        Geodetic_to_PS_Converter.es_OVER_2 = 0.040909595421311;
        Geodetic_to_PS_Converter.Southern_Hemisphere = 0.0;
        Geodetic_to_PS_Converter.mc = 1.0;
        Geodetic_to_PS_Converter.tc = 1.0;
        Geodetic_to_PS_Converter.e4 = 1.0033565552493;
        Geodetic_to_PS_Converter.Polar_a_mc = 6378137.0;
        Geodetic_to_PS_Converter.two_Polar_a = 1.2756274E7;
        Geodetic_to_PS_Converter.Polar_Origin_Lat = 1.5707963267948966;
        Geodetic_to_PS_Converter.Polar_Origin_Long = 0.0;
        Geodetic_to_PS_Converter.Polar_False_Easting = 0.0;
        Geodetic_to_PS_Converter.Polar_False_Northing = 0.0;
        Geodetic_to_PS_Converter.Polar_Delta_Easting = 1.2713601E7;
        Geodetic_to_PS_Converter.Polar_Delta_Northing = 1.2713601E7;
        Geodetic_to_PS_Converter.log = new Log((Geodetic_to_PS_Converter.class$com$itt$J2KViewer$georvm$transforms$Geodetic_to_PS_Converter == null) ? (Geodetic_to_PS_Converter.class$com$itt$J2KViewer$georvm$transforms$Geodetic_to_PS_Converter = class$("com.itt.J2KViewer.georvm.transforms.Geodetic_to_PS_Converter")) : Geodetic_to_PS_Converter.class$com$itt$J2KViewer$georvm$transforms$Geodetic_to_PS_Converter);
    }
}
