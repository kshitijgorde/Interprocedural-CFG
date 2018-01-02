// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.transforms;

import com.itt.J2KViewer.georvm.coords.Gdc_Coord_3d;
import com.itt.J2KViewer.georvm.coords.Utm_Coord_3d;
import com.itt.J2KViewer.georvm.ellipsoids.Ellipsoid;

public class Utm_To_Gdc_Converter
{
    static final double DEGREES_PER_RADIAN = 57.29577951308232;
    static double A;
    static double F;
    static double C;
    static double Eps2;
    static double Eps21;
    static double Eps25;
    static double Con;
    static double Con2;
    static double EF;
    static double Epsp2;
    static double Con6;
    static double Con24;
    static double Con120;
    static double Con720;
    static double polx2b;
    static double polx3b;
    static double polx4b;
    static double polx5b;
    static double conap;
    
    public static void Init(final double n, final double n2) {
        CreateConstants(n, n2);
    }
    
    public static void Init() {
        CreateConstants(6378137.0, 298.257223563);
    }
    
    public static void Init(final Ellipsoid ellipsoid) {
        CreateConstants(ellipsoid.a, ellipsoid.f);
    }
    
    protected static void CreateConstants(final double a, final double f) {
        Utm_To_Gdc_Converter.A = a;
        Utm_To_Gdc_Converter.F = f;
        Utm_To_Gdc_Converter.F = 1.0 / Utm_To_Gdc_Converter.F;
        Utm_To_Gdc_Converter.C = Utm_To_Gdc_Converter.A * (1.0 - Utm_To_Gdc_Converter.F);
        Utm_To_Gdc_Converter.Eps2 = Utm_To_Gdc_Converter.F * (2.0 - Utm_To_Gdc_Converter.F);
        Utm_To_Gdc_Converter.Eps25 = 0.25 * Utm_To_Gdc_Converter.Eps2;
        Utm_To_Gdc_Converter.EF = Utm_To_Gdc_Converter.F / (2.0 - Utm_To_Gdc_Converter.F);
        Utm_To_Gdc_Converter.Con = 1.0 - Utm_To_Gdc_Converter.Eps2;
        Utm_To_Gdc_Converter.Con2 = 2.0 / (1.0 - Utm_To_Gdc_Converter.Eps2);
        Utm_To_Gdc_Converter.Con6 = 0.166666666666667;
        Utm_To_Gdc_Converter.Con24 = 0.1666666666666668 / (1.0 - Utm_To_Gdc_Converter.Eps2);
        Utm_To_Gdc_Converter.Con120 = 0.00833333333333333;
        Utm_To_Gdc_Converter.Con720 = 0.00555555555555552 / (1.0 - Utm_To_Gdc_Converter.Eps2);
        Utm_To_Gdc_Converter.conap = Utm_To_Gdc_Converter.A * (1.0 - Utm_To_Gdc_Converter.Eps2 / 4.0 - 0.046875 * Math.pow(Utm_To_Gdc_Converter.Eps2, 2.0) - 0.01953125 * Math.pow(Utm_To_Gdc_Converter.Eps2, 3.0) - 0.01068115234375 * Math.pow(Utm_To_Gdc_Converter.Eps2, 4.0));
        final double n = 1.5 * Utm_To_Gdc_Converter.EF - 0.84375 * Math.pow(Utm_To_Gdc_Converter.EF, 3.0);
        final double n2 = 1.3125 * Math.pow(Utm_To_Gdc_Converter.EF, 2.0) - 1.71875 * Math.pow(Utm_To_Gdc_Converter.EF, 4.0);
        final double n3 = 1.5729166666666667 * Math.pow(Utm_To_Gdc_Converter.EF, 3.0);
        final double n4 = 2.142578125 * Math.pow(Utm_To_Gdc_Converter.EF, 4.0);
        Utm_To_Gdc_Converter.polx2b = n * 2.0 + n2 * 4.0 + n3 * 6.0 + n4 * 8.0;
        Utm_To_Gdc_Converter.polx3b = n2 * -8.0 - n3 * 32.0 - 80.0 * n4;
        Utm_To_Gdc_Converter.polx4b = n3 * 32.0 + 192.0 * n4;
        Utm_To_Gdc_Converter.polx5b = -128.0 * n4;
    }
    
    public static void Convert(final Utm_Coord_3d utm_Coord_3d, final Gdc_Coord_3d gdc_Coord_3d) {
        final Utm_Coord_3d[] array = { null };
        final Gdc_Coord_3d[] array2 = { null };
        array[0] = utm_Coord_3d;
        array2[0] = gdc_Coord_3d;
        Convert(array, array2);
    }
    
    public static void Convert(final Utm_Coord_3d[] array, final Gdc_Coord_3d[] array2) {
        for (int i = 0; i < array2.length; ++i) {
            array2[i].elevation = array[i].z;
            final double n = (array[i].x - 500000.0) / 0.9996;
            double n2;
            if (array[i].hemisphere_north) {
                n2 = array[i].y / 0.9996;
            }
            else {
                n2 = (array[i].y - 1.0E7) / 0.9996;
            }
            final double n3 = n2 / Utm_To_Gdc_Converter.conap;
            final double sin = Math.sin(n3);
            final double cos = Math.cos(n3);
            final double n4 = sin * sin;
            final double n5 = (6.0 * array[i].zone - 183.0) / 57.29577951308232;
            final double n6 = n3 + sin * cos * (Utm_To_Gdc_Converter.polx2b + n4 * (Utm_To_Gdc_Converter.polx3b + n4 * (Utm_To_Gdc_Converter.polx4b + n4 * Utm_To_Gdc_Converter.polx5b)));
            final double sin2 = Math.sin(n6);
            final double cos2 = Math.cos(n6);
            final double n7 = sin2 / cos2;
            final double n8 = n7 * n7;
            final double n9 = sin2 * sin2;
            final double n10 = Utm_To_Gdc_Converter.Epsp2 * (cos2 * cos2);
            final double n11 = 0.25 - n9 * (Utm_To_Gdc_Converter.Eps2 / 4.0);
            final double n12 = Utm_To_Gdc_Converter.A / (0.25 - Utm_To_Gdc_Converter.Eps25 * n9 + 0.249998608869975 + (0.25 - Utm_To_Gdc_Converter.Eps25 * n9) / (0.25 - Utm_To_Gdc_Converter.Eps25 * n9 + 0.249998608869975));
            final double n13 = 1.0 + n8 + n8 + n10;
            final double n14 = 5.0 + n8 * (3.0 - 9.0 * n10) + n10 * (1.0 - 4.0 * n10);
            final double n15 = 5.0 + n8 * (n8 * 24.0 + 28.0) + n10 * (n8 * 8.0 + 6.0);
            final double n16 = n10 * (46.0 - 3.0 * n10 + n8 * (-252.0 - n8 * 90.0) + n10 * n8 * (n8 * 225.0 - 66.0)) + (61.0 + n8 * (n8 * 45.0 + 90.0));
            final double n17 = n / n12;
            final double n18 = n17 * n17;
            array2[i].latitude = n6 - n7 * n11 * (n18 * (Utm_To_Gdc_Converter.Con2 + n18 * (-Utm_To_Gdc_Converter.Con24 * n14 + n18 * Utm_To_Gdc_Converter.Con720 * n16)));
            array2[i].longitude = n5 + n17 * (1.0 + n18 * (-Utm_To_Gdc_Converter.Con6 * n13 + n18 * Utm_To_Gdc_Converter.Con120 * n15)) / cos2;
            final Gdc_Coord_3d gdc_Coord_3d = array2[i];
            gdc_Coord_3d.latitude *= 57.29577951308232;
            final Gdc_Coord_3d gdc_Coord_3d2 = array2[i];
            gdc_Coord_3d2.longitude *= 57.29577951308232;
        }
    }
}
