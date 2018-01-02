// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.transforms;

public class Polar_Stereographic_Converter
{
    private static double PI;
    private static double PI_OVER_2;
    private static double TWO_PI;
    private static double PI_Over_4;
    static double Polar_a;
    static double Polar_f;
    static double es;
    static double es_OVER_2;
    static double Southern_Hemisphere;
    static double mc;
    static double tc;
    static double e4;
    static double Polar_a_mc;
    static double two_Polar_a;
    static double Polar_Origin_Lat;
    static double Polar_Origin_Long;
    static double Polar_False_Easting;
    static double Polar_False_Northing;
    static double Polar_Delta_Easting;
    static double Polar_Delta_Northing;
    
    public String Set_Polar_Stereographic_Parameters(final double polar_a, final double polar_f, final double polar_Origin_Lat, double polar_Origin_Long, final double polar_False_Easting, final double polar_False_Northing) {
        final double n = 1.0 / polar_f;
        final double n2 = 0.01;
        String s = "";
        if (polar_a <= 0.0) {
            s = "Semi-major axis must be greater than zero.";
        }
        if (n < 250.0 || n > 350.0) {
            s = "Inverse flattening must be between 250 and 350.";
        }
        if (polar_Origin_Lat < -Polar_Stereographic_Converter.PI_OVER_2 || polar_Origin_Lat > Polar_Stereographic_Converter.PI_OVER_2) {
            s = "Origin Latitude out of range.";
        }
        if (polar_Origin_Long < -Polar_Stereographic_Converter.PI || polar_Origin_Long > Polar_Stereographic_Converter.TWO_PI) {
            s = "Origin Longitude out of range.";
        }
        if (s.length() == 0) {
            Polar_Stereographic_Converter.Polar_a = polar_a;
            Polar_Stereographic_Converter.two_Polar_a = 2.0 * Polar_Stereographic_Converter.Polar_a;
            Polar_Stereographic_Converter.Polar_f = polar_f;
            if (polar_Origin_Long > Polar_Stereographic_Converter.PI) {
                polar_Origin_Long -= Polar_Stereographic_Converter.TWO_PI;
            }
            if (polar_Origin_Lat < 0.0) {
                Polar_Stereographic_Converter.Southern_Hemisphere = 1.0;
                Polar_Stereographic_Converter.Polar_Origin_Lat = -polar_Origin_Lat;
                Polar_Stereographic_Converter.Polar_Origin_Long = -polar_Origin_Long;
            }
            else {
                Polar_Stereographic_Converter.Southern_Hemisphere = 0.0;
                Polar_Stereographic_Converter.Polar_Origin_Lat = polar_Origin_Lat;
                Polar_Stereographic_Converter.Polar_Origin_Long = polar_Origin_Long;
            }
            Polar_Stereographic_Converter.Polar_False_Easting = polar_False_Easting;
            Polar_Stereographic_Converter.Polar_False_Northing = polar_False_Northing;
            Polar_Stereographic_Converter.es = Math.sqrt(2.0 * Polar_Stereographic_Converter.Polar_f - Polar_Stereographic_Converter.Polar_f * Polar_Stereographic_Converter.Polar_f);
            Polar_Stereographic_Converter.es_OVER_2 = Polar_Stereographic_Converter.es / 2.0;
            if (Math.abs(Math.abs(Polar_Stereographic_Converter.Polar_Origin_Lat) - Polar_Stereographic_Converter.PI_OVER_2) > 1.0E-10) {
                final double n3 = Polar_Stereographic_Converter.es * Math.sin(Polar_Stereographic_Converter.Polar_Origin_Lat);
                final double polar_POW = this.POLAR_POW(n3);
                Polar_Stereographic_Converter.mc = Math.cos(Polar_Stereographic_Converter.Polar_Origin_Lat) / Math.sqrt(1.0 - n3 * n3);
                Polar_Stereographic_Converter.Polar_a_mc = Polar_Stereographic_Converter.Polar_a * Polar_Stereographic_Converter.mc;
                Polar_Stereographic_Converter.tc = Math.tan(Polar_Stereographic_Converter.PI_Over_4 - Polar_Stereographic_Converter.Polar_Origin_Lat / 2.0) / polar_POW;
            }
            else {
                final double n4 = 1.0 + Polar_Stereographic_Converter.es;
                final double n5 = 1.0 - Polar_Stereographic_Converter.es;
                Polar_Stereographic_Converter.e4 = Math.sqrt(Math.pow(n4, n4) * Math.pow(n5, n5));
            }
        }
        Polar_Stereographic_Converter.Polar_Delta_Northing = Math.abs(this.Convert_Geodetic_To_Polar_Stereographic(0.0, Polar_Stereographic_Converter.Polar_Origin_Long)[1]) + n2;
        Polar_Stereographic_Converter.Polar_Delta_Easting = Polar_Stereographic_Converter.Polar_Delta_Northing;
        return s;
    }
    
    private double[] Convert_Geodetic_To_Polar_Stereographic(double n, double n2) {
        final double[] array = new double[2];
        String s = "";
        if (n < -Polar_Stereographic_Converter.PI_OVER_2 || n > Polar_Stereographic_Converter.PI_OVER_2) {
            s = "Latitude out of range.";
        }
        if (n < 0.0 && Polar_Stereographic_Converter.Southern_Hemisphere == 0.0) {
            s = "Latitude and Origin Latitude in different hemispheres.";
        }
        if (n > 0.0 && Polar_Stereographic_Converter.Southern_Hemisphere == 1.0) {
            s = "Latitude and Origin Latitude in different hemispheres.";
        }
        if (n2 < -Polar_Stereographic_Converter.PI || n2 > Polar_Stereographic_Converter.TWO_PI) {
            s = "Longitude out of range.";
        }
        if (s.length() == 0) {
            if (Math.abs(Math.abs(n) - Polar_Stereographic_Converter.PI_OVER_2) >= 1.0E-10) {
                if (Polar_Stereographic_Converter.Southern_Hemisphere != 0.0) {
                    n2 *= -1.0;
                    n *= -1.0;
                }
                double n3 = n2 - Polar_Stereographic_Converter.Polar_Origin_Long;
                if (n3 > Polar_Stereographic_Converter.PI) {
                    n3 -= Polar_Stereographic_Converter.TWO_PI;
                }
                if (n3 < -Polar_Stereographic_Converter.PI) {
                    n3 += Polar_Stereographic_Converter.TWO_PI;
                }
                final double n4 = Math.tan(Polar_Stereographic_Converter.PI_Over_4 - n / 2.0) / this.POLAR_POW(Polar_Stereographic_Converter.es * Math.sin(n));
                double n5;
                if (Math.abs(Math.abs(Polar_Stereographic_Converter.Polar_Origin_Lat) - Polar_Stereographic_Converter.PI_OVER_2) > 1.0E-10) {
                    n5 = Polar_Stereographic_Converter.Polar_a_mc * n4 / Polar_Stereographic_Converter.tc;
                }
                else {
                    n5 = Polar_Stereographic_Converter.two_Polar_a * n4 / Polar_Stereographic_Converter.e4;
                }
                double n6 = n5 * Math.sin(n3) + Polar_Stereographic_Converter.Polar_False_Easting;
                double n7;
                if (Polar_Stereographic_Converter.Southern_Hemisphere != 0.0) {
                    n6 *= -1.0;
                    n7 = n5 * Math.cos(n3) + Polar_Stereographic_Converter.Polar_False_Northing;
                }
                else {
                    n7 = -n5 * Math.cos(n3) + Polar_Stereographic_Converter.Polar_False_Northing;
                }
                array[0] = n6;
                array[1] = n7;
            }
        }
        return array;
    }
    
    public double[] Convert_Polar_Stereographic_To_Geodetic(final double n, final double n2) {
        double n3 = 0.0;
        String s = "";
        final double[] array = new double[2];
        if (n > Polar_Stereographic_Converter.Polar_False_Easting + Polar_Stereographic_Converter.Polar_Delta_Easting || n < Polar_Stereographic_Converter.Polar_False_Easting - Polar_Stereographic_Converter.Polar_Delta_Easting) {
            s = "Easting out of range.";
        }
        if (n2 > Polar_Stereographic_Converter.Polar_False_Northing + Polar_Stereographic_Converter.Polar_Delta_Northing || n2 < Polar_Stereographic_Converter.Polar_False_Northing - Polar_Stereographic_Converter.Polar_Delta_Northing) {
            s = "Northing out of range.";
        }
        if (s.length() == 0) {
            final double sqrt = Math.sqrt(n * n + n2 * n2);
            if (sqrt > Polar_Stereographic_Converter.Polar_False_Easting + Polar_Stereographic_Converter.Polar_Delta_Easting || sqrt > Polar_Stereographic_Converter.Polar_False_Northing + Polar_Stereographic_Converter.Polar_Delta_Northing || sqrt < Polar_Stereographic_Converter.Polar_False_Easting - Polar_Stereographic_Converter.Polar_Delta_Easting || sqrt < Polar_Stereographic_Converter.Polar_False_Northing - Polar_Stereographic_Converter.Polar_Delta_Northing) {
                s = "Point is outside of projection area.";
            }
        }
        if (s.length() == 0) {
            double n4 = n2 - Polar_Stereographic_Converter.Polar_False_Northing;
            double n5 = n - Polar_Stereographic_Converter.Polar_False_Easting;
            double n6;
            double n7;
            if (n4 == 0.0 && n5 == 0.0) {
                n6 = Polar_Stereographic_Converter.PI_OVER_2;
                n7 = Polar_Stereographic_Converter.Polar_Origin_Long;
            }
            else {
                if (Polar_Stereographic_Converter.Southern_Hemisphere != 0.0) {
                    n4 *= -1.0;
                    n5 *= -1.0;
                }
                final double sqrt2 = Math.sqrt(n5 * n5 + n4 * n4);
                double n8;
                if (Math.abs(Math.abs(Polar_Stereographic_Converter.Polar_Origin_Lat) - Polar_Stereographic_Converter.PI_OVER_2) > 1.0E-10) {
                    n8 = sqrt2 * Polar_Stereographic_Converter.tc / Polar_Stereographic_Converter.Polar_a_mc;
                }
                else {
                    n8 = sqrt2 * Polar_Stereographic_Converter.e4 / Polar_Stereographic_Converter.two_Polar_a;
                }
                double n9;
                for (n9 = Polar_Stereographic_Converter.PI_OVER_2 - 2.0 * Math.atan(n8); Math.abs(n9 - n3) > 1.0E-10; n3 = n9, n9 = Polar_Stereographic_Converter.PI_OVER_2 - 2.0 * Math.atan(n8 * this.POLAR_POW(Polar_Stereographic_Converter.es * Math.sin(n9)))) {}
                n6 = n9;
                n7 = Polar_Stereographic_Converter.Polar_Origin_Long + Math.atan2(n5, -n4);
                if (n7 > Polar_Stereographic_Converter.PI) {
                    n7 -= Polar_Stereographic_Converter.TWO_PI;
                }
                else if (n7 < -Polar_Stereographic_Converter.PI) {
                    n7 += Polar_Stereographic_Converter.TWO_PI;
                }
                if (n6 > Polar_Stereographic_Converter.PI_OVER_2) {
                    n6 = Polar_Stereographic_Converter.PI_OVER_2;
                }
                else if (n6 < -Polar_Stereographic_Converter.PI_OVER_2) {
                    n6 = -Polar_Stereographic_Converter.PI_OVER_2;
                }
                if (n7 > Polar_Stereographic_Converter.PI) {
                    n7 = Polar_Stereographic_Converter.PI;
                }
                else if (n7 < -Polar_Stereographic_Converter.PI) {
                    n7 = -Polar_Stereographic_Converter.PI;
                }
            }
            if (Polar_Stereographic_Converter.Southern_Hemisphere != 0.0) {
                n6 *= -1.0;
                n7 *= -1.0;
            }
            array[0] = n7;
            array[1] = n6;
        }
        return array;
    }
    
    private double POLAR_POW(final double n) {
        return Math.pow((1.0 - n) / (1.0 + n), Polar_Stereographic_Converter.es_OVER_2);
    }
    
    static {
        Polar_Stereographic_Converter.PI = 3.141592653589793;
        Polar_Stereographic_Converter.PI_OVER_2 = Polar_Stereographic_Converter.PI / 2.0;
        Polar_Stereographic_Converter.TWO_PI = 2.0 * Polar_Stereographic_Converter.PI;
        Polar_Stereographic_Converter.PI_Over_4 = Polar_Stereographic_Converter.PI / 4.0;
        Polar_Stereographic_Converter.Polar_a = 6378137.0;
        Polar_Stereographic_Converter.Polar_f = 0.0033528106647474805;
        Polar_Stereographic_Converter.es = 0.08181919084262188;
        Polar_Stereographic_Converter.es_OVER_2 = 0.040909595421311;
        Polar_Stereographic_Converter.Southern_Hemisphere = 0.0;
        Polar_Stereographic_Converter.mc = 1.0;
        Polar_Stereographic_Converter.tc = 1.0;
        Polar_Stereographic_Converter.e4 = 1.0033565552493;
        Polar_Stereographic_Converter.Polar_a_mc = 6378137.0;
        Polar_Stereographic_Converter.two_Polar_a = 1.2756274E7;
        Polar_Stereographic_Converter.Polar_Origin_Lat = Polar_Stereographic_Converter.PI * 90.0 / 180.0;
        Polar_Stereographic_Converter.Polar_Origin_Long = 0.0;
        Polar_Stereographic_Converter.Polar_False_Easting = 0.0;
        Polar_Stereographic_Converter.Polar_False_Northing = 0.0;
        Polar_Stereographic_Converter.Polar_Delta_Easting = 1.2713601E7;
        Polar_Stereographic_Converter.Polar_Delta_Northing = 1.2713601E7;
    }
}
