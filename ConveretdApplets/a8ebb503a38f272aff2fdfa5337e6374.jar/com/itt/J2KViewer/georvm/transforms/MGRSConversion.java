// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.transforms;

import com.itt.J2KViewer.util.PrintfFormat;
import java.util.ArrayList;
import java.util.List;
import com.itt.J2KViewer.georvm.coords.Ups_Coord_3d;
import com.itt.J2KViewer.georvm.coords.Gdc_Coord_3d;
import com.itt.J2KViewer.util.Log;
import com.itt.J2KViewer.georvm.coords.Utm_Coord_3d;

public class MGRSConversion
{
    private long mZone;
    private char mHemisphere;
    private double mEasting;
    private double mNorthing;
    private long in_precision;
    private int[] letters;
    private Utm_Coord_3d utmCoord3D;
    private double max_precision;
    private double MIN_UTM_LAT;
    private double MAX_UTM_LAT;
    private double MAX_ORIGIN_LAT;
    private double UPS_Origin_Latitude;
    private double UPS_Origin_Longitude;
    private long ltr2_low_value;
    private long ltr2_high_value;
    private double false_northing;
    public static final int LETTER_A = 0;
    public static final int LETTER_B = 1;
    public static final int LETTER_C = 2;
    public static final int LETTER_D = 3;
    public static final int LETTER_E = 4;
    public static final int LETTER_F = 5;
    public static final int LETTER_G = 6;
    public static final int LETTER_H = 7;
    public static final int LETTER_I = 8;
    public static final int LETTER_J = 9;
    public static final int LETTER_K = 10;
    public static final int LETTER_L = 11;
    public static final int LETTER_M = 12;
    public static final int LETTER_N = 13;
    public static final int LETTER_O = 14;
    public static final int LETTER_P = 15;
    public static final int LETTER_Q = 16;
    public static final int LETTER_R = 17;
    public static final int LETTER_S = 18;
    public static final int LETTER_T = 19;
    public static final int LETTER_U = 20;
    public static final int LETTER_V = 21;
    public static final int LETTER_W = 22;
    public static final int LETTER_X = 23;
    public static final int LETTER_Y = 24;
    public static final int LETTER_Z = 25;
    public static final double DEG_TO_RAD = 0.017453292519943295;
    public static final double RAD_TO_DEG = 57.29577951308232;
    public static final int MGRS_LETTERS = 3;
    public static final double ONEHT = 100000.0;
    public static final double TWOMIL = 2000000.0;
    public static final int TRUE = 1;
    public static final int FALSE = 0;
    public static final double PI = 3.141592653589793;
    public static final double PI_OVER_2 = 1.5707963267948966;
    public static final long MIN_EAST_NORTH = 0L;
    public static final long MAX_EAST_NORTH = 4000000L;
    public static final int MAX_PRECISION = 5;
    public static double UTM_a;
    public static double UTM_f;
    public static long UTM_Override;
    public static double UPS_a;
    public static double UPS_f;
    private static Log log;
    double MGRS_a;
    double MGRS_f;
    double MGRS_recpf;
    char[] MGRS_Ellipsoid_Code;
    Latitude_Band lbc;
    Latitude_Band lbd;
    Latitude_Band lbe;
    Latitude_Band lbf;
    Latitude_Band lbg;
    Latitude_Band lbh;
    Latitude_Band lbj;
    Latitude_Band lbk;
    Latitude_Band lbl;
    Latitude_Band lbm;
    Latitude_Band lbn;
    Latitude_Band lbp;
    Latitude_Band lbq;
    Latitude_Band lbr;
    Latitude_Band lbs;
    Latitude_Band lbt;
    Latitude_Band lbu;
    Latitude_Band lbv;
    Latitude_Band lbw;
    Latitude_Band lbx;
    Latitude_Band[] Latitude_Band_Table;
    UPS_Constant UPSConst1;
    UPS_Constant UPSConst2;
    UPS_Constant UPSConst3;
    UPS_Constant UPSConst4;
    UPS_Constant[] UPS_Constant_Table;
    static /* synthetic */ Class class$com$itt$J2KViewer$georvm$transforms$MGRSConversion;
    
    public MGRSConversion() {
        this.mZone = 0L;
        this.mHemisphere = '\0';
        this.mEasting = 0.0;
        this.mNorthing = 0.0;
        this.in_precision = 0L;
        this.letters = new int[3];
        this.utmCoord3D = null;
        this.max_precision = 5.0;
        this.MIN_UTM_LAT = -1.3962634015954636;
        this.MAX_UTM_LAT = 1.4660765716752369;
        this.MAX_ORIGIN_LAT = 1.4157155848011311;
        this.UPS_Origin_Latitude = this.MAX_ORIGIN_LAT;
        this.UPS_Origin_Longitude = 0.0;
        this.false_northing = 0.0;
        this.MGRS_a = 6378137.0;
        this.MGRS_f = 0.0033528106647474805;
        this.MGRS_recpf = 298.257223563;
        this.MGRS_Ellipsoid_Code = new char[] { 'W', 'E', '\0' };
        this.lbc = new Latitude_Band(2, 1100000.0, -72.0, -80.5);
        this.lbd = new Latitude_Band(3, 2000000.0, -64.0, -72.0);
        this.lbe = new Latitude_Band(4, 2800000.0, -56.0, -64.0);
        this.lbf = new Latitude_Band(5, 3700000.0, -48.0, -56.0);
        this.lbg = new Latitude_Band(6, 4600000.0, -40.0, -48.0);
        this.lbh = new Latitude_Band(7, 5500000.0, -32.0, -40.0);
        this.lbj = new Latitude_Band(9, 6400000.0, -24.0, -32.0);
        this.lbk = new Latitude_Band(10, 7300000.0, -16.0, -24.0);
        this.lbl = new Latitude_Band(11, 8200000.0, -8.0, -16.0);
        this.lbm = new Latitude_Band(12, 9100000.0, 0.0, -8.0);
        this.lbn = new Latitude_Band(13, 0.0, 8.0, 0.0);
        this.lbp = new Latitude_Band(15, 800000.0, 16.0, 8.0);
        this.lbq = new Latitude_Band(16, 1700000.0, 24.0, 16.0);
        this.lbr = new Latitude_Band(17, 2600000.0, 32.0, 24.0);
        this.lbs = new Latitude_Band(18, 3500000.0, 40.0, 32.0);
        this.lbt = new Latitude_Band(19, 4400000.0, 48.0, 40.0);
        this.lbu = new Latitude_Band(20, 5300000.0, 56.0, 48.0);
        this.lbv = new Latitude_Band(21, 6200000.0, 64.0, 56.0);
        this.lbw = new Latitude_Band(22, 7000000.0, 72.0, 64.0);
        this.lbx = new Latitude_Band(23, 7900000.0, 84.5, 72.0);
        this.Latitude_Band_Table = new Latitude_Band[] { this.lbc, this.lbd, this.lbe, this.lbf, this.lbg, this.lbh, this.lbj, this.lbk, this.lbl, this.lbm, this.lbn, this.lbp, this.lbq, this.lbr, this.lbs, this.lbt, this.lbu, this.lbv, this.lbw, this.lbx };
        this.UPSConst1 = new UPS_Constant(0L, 9L, 25L, 25L, 800000.0, 800000.0);
        this.UPSConst2 = new UPS_Constant(1L, 0L, 17L, 25L, 2000000.0, 800000.0);
        this.UPSConst3 = new UPS_Constant(24L, 9L, 25L, 15L, 800000.0, 1300000.0);
        this.UPSConst4 = new UPS_Constant(25L, 0L, 9L, 15L, 2000000.0, 1300000.0);
        this.UPS_Constant_Table = new UPS_Constant[] { this.UPSConst1, this.UPSConst2, this.UPSConst3, this.UPSConst4 };
    }
    
    public Utm_Coord_3d getUTMCoords() {
        return this.utmCoord3D;
    }
    
    public String Convert_Geodetic_To_MGRS(final double n, double n2, final int n3) {
        String s = "";
        String s2 = "";
        if (n2 < -3.141592653589793) {
            n2 += 6.283185307179586;
        }
        else if (n2 > 6.283185307179586) {
            n2 -= 6.283185307179586;
        }
        if (n < -1.5707963267948966 || n > 1.5707963267948966) {
            s = "Latitude out of range";
            MGRSConversion.log.error("Latitude out of range.");
        }
        if (n3 < 0 || n3 > this.max_precision) {
            s = "Precision is out of range";
            MGRSConversion.log.error("Precision out of range.");
        }
        if (s.length() == 0) {
            if (n < this.MIN_UTM_LAT || n > this.MAX_UTM_LAT) {
                final Geodetic_to_UPS_Converter geodetic_to_UPS_Converter = new Geodetic_to_UPS_Converter();
                final double[] convert_Geodetic_To_UPS = geodetic_to_UPS_Converter.Convert_Geodetic_To_UPS(n, n2);
                s2 = this.Convert_UPS_To_MGRS(geodetic_to_UPS_Converter.Hemisphere, convert_Geodetic_To_UPS[0], convert_Geodetic_To_UPS[1], n3);
            }
            else {
                final String set_UTM_Parameters = this.Set_UTM_Parameters(this.MGRS_a, this.MGRS_f, 0L);
                if (set_UTM_Parameters.length() == 0) {
                    final Geodetic_to_UTM_Converter geodetic_to_UTM_Converter = new Geodetic_to_UTM_Converter();
                    final Utm_Coord_3d utm_Coord_3d = new Utm_Coord_3d();
                    final Utm_Coord_3d convert_Geodetic_To_UTM = geodetic_to_UTM_Converter.Convert_Geodetic_To_UTM(n, n2);
                    s2 = this.UTM_To_MGRS(convert_Geodetic_To_UTM.zone, n, convert_Geodetic_To_UTM.x, convert_Geodetic_To_UTM.y, n3);
                }
                else {
                    MGRSConversion.log.error(set_UTM_Parameters);
                }
            }
        }
        return s2;
    }
    
    String UTM_To_MGRS(final long n, final double n2, double n3, double n4, final long n5) {
        final int[] array = { 0, 0, 0 };
        final String s = "";
        String make_MGRS_String = "";
        final double pow = Math.pow(10.0, 5L - n5);
        n3 = this.Round_MGRS(n3 / pow) * pow;
        n4 = this.Round_MGRS(n4 / pow) * pow;
        this.Get_Grid_Values(n);
        array[0] = this.Get_Latitude_Letter(n2);
        if (s.length() == 0) {
            double n6 = n4;
            if (n6 == 1.0E7) {
                --n6;
            }
            while (n6 >= 2000000.0) {
                n6 -= 2000000.0;
            }
            double n7 = n6 - this.false_northing;
            if (n7 < 0.0) {
                n7 += 2000000.0;
            }
            array[2] = (int)(n7 / 100000.0);
            if (array[2] > 7) {
                ++array[2];
            }
            if (array[2] > 13) {
                ++array[2];
            }
            double n8 = n3;
            if (array[0] == 21 && n == 31L && n8 == 500000.0) {
                --n8;
            }
            array[1] = (int)this.ltr2_low_value + (int)((long)(n8 / 100000.0) - 1L);
            if (this.ltr2_low_value == 9L && array[1] > 13) {
                ++array[1];
            }
            make_MGRS_String = this.Make_MGRS_String(n, array, n3, n4, n5);
        }
        return make_MGRS_String;
    }
    
    private String Convert_UPS_To_MGRS(final char c, double n, double n2, final long n3) {
        final int[] array = { 0, 0, 0 };
        String make_MGRS_String = "";
        String s = "";
        if (c != 'N' && c != 'S') {
            s = "MGRS_HEMISPHERE_ERROR";
        }
        if (n < 0.0 || n > 4000000.0) {
            s = "MGRS_EASTING_ERROR";
        }
        if (n2 < 0.0 || n2 > 4000000.0) {
            s = "MGRS_NORTHING_ERROR";
        }
        if (n3 < 0L || n3 > 5L) {
            s = "MGRS_PRECISION_ERROR";
        }
        if (s.length() == 0) {
            final double pow = Math.pow(10.0, 5L - n3);
            n = this.Round_MGRS(n / pow) * pow;
            n2 = this.Round_MGRS(n2 / pow) * pow;
            long n5;
            double n6;
            double n7;
            if (c == 'N') {
                if (n >= 2000000.0) {
                    array[0] = 25;
                }
                else {
                    array[0] = 24;
                }
                final int n4 = array[0] - 22;
                n5 = this.UPS_Constant_Table[n4].ltr2_low_value;
                n6 = this.UPS_Constant_Table[n4].false_easting;
                n7 = this.UPS_Constant_Table[n4].false_northing;
            }
            else {
                if (n >= 2000000.0) {
                    array[0] = 1;
                }
                else {
                    array[0] = 0;
                }
                n5 = this.UPS_Constant_Table[array[0]].ltr2_low_value;
                n6 = this.UPS_Constant_Table[array[0]].false_easting;
                n7 = this.UPS_Constant_Table[array[0]].false_northing;
            }
            array[2] = (int)((n2 - n7) / 100000.0);
            if (array[2] > 7) {
                ++array[2];
            }
            if (array[2] > 13) {
                ++array[2];
            }
            array[1] = (int)n5 + (int)((n - n6) / 100000.0);
            if (n < 2000000.0) {
                if (array[1] > 11) {
                    array[1] += 3;
                }
                if (array[1] > 20) {
                    array[1] += 2;
                }
            }
            else {
                if (array[1] > 2) {
                    array[1] += 2;
                }
                if (array[1] > 7) {
                    ++array[1];
                }
                if (array[1] > 11) {
                    array[1] += 3;
                }
            }
            make_MGRS_String = this.Make_MGRS_String(0L, array, n, n2, n3);
        }
        else {
            MGRSConversion.log.error(s);
        }
        return make_MGRS_String;
    }
    
    String Set_UTM_Parameters(final double utm_a, final double utm_f, final long utm_Override) {
        final double n = 1.0 / utm_f;
        String s = "";
        if (utm_a <= 0.0) {
            s = "Semi-major axis must be greater than zero";
        }
        if (n < 250.0 || n > 350.0) {
            s = "Inverse flattening must be between 250 and 350";
        }
        if (utm_Override < 0L || utm_Override > 60L) {
            s = "UTM_ZONE_OVERRIDE_ERROR";
        }
        if (s.length() == 0) {
            MGRSConversion.UTM_a = utm_a;
            MGRSConversion.UTM_f = utm_f;
            MGRSConversion.UTM_Override = utm_Override;
        }
        return s;
    }
    
    private String Set_UPS_Parameters(final double ups_a, final double ups_f) {
        final double n = 1.0 / ups_f;
        String s = "";
        if (ups_a <= 0.0) {
            s = "UPS_A_ERROR";
        }
        if (n < 250.0 || n > 350.0) {
            s = "UPS_INV_F_ERROR";
        }
        if (s.length() == 0) {
            MGRSConversion.UPS_a = ups_a;
            MGRSConversion.UPS_f = ups_f;
        }
        return s;
    }
    
    public long Round_MGRS(final double n) {
        long n2 = (long)n;
        final double n3 = n % 1.0;
        if (n3 > 0.5 || (n3 == 0.5 && n2 % 2L == 1L)) {
            ++n2;
        }
        return n2;
    }
    
    public double[] Convert_MGRS_To_Geodetic(final String s) {
        double[] convert_UPS_To_Geodetic = new double[2];
        if (this.checkZone(s)) {
            this.Convert_MGRS_To_UTM(s);
            this.Set_UTM_Parameters(this.MGRS_a, this.MGRS_f, 0L);
            if (this.mHemisphere == 'N') {
                this.utmCoord3D.hemisphere_north = true;
            }
            this.utmCoord3D.x = this.mEasting;
            this.utmCoord3D.y = this.mNorthing;
            this.utmCoord3D.zone = (byte)this.mZone;
            final Gdc_Coord_3d gdc_Coord_3d = new Gdc_Coord_3d();
            Utm_To_Gdc_Converter.Init();
            Utm_To_Gdc_Converter.Convert(this.utmCoord3D, gdc_Coord_3d);
            convert_UPS_To_Geodetic[0] = gdc_Coord_3d.longitude;
            convert_UPS_To_Geodetic[1] = gdc_Coord_3d.latitude;
        }
        else {
            final Ups_Coord_3d ups_Coord_3d = new Ups_Coord_3d();
            this.Convert_MGRS_To_UPS(s);
            this.Set_UPS_Parameters(this.MGRS_a, this.MGRS_f);
            convert_UPS_To_Geodetic = new Geodetic_to_UPS_Converter().Convert_UPS_To_Geodetic(this.mHemisphere, this.mEasting, this.mNorthing);
        }
        return convert_UPS_To_Geodetic;
    }
    
    private boolean checkZone(final String s) {
        final char[] charArray = s.toCharArray();
        int n = 0;
        boolean b = false;
        while (charArray[n] == ' ') {
            ++n;
        }
        final int n2 = n;
        int i = 0;
        while (i == 0) {
            if (Character.isDigit(new Character(charArray[n]))) {
                ++n;
            }
            else {
                i = 1;
            }
        }
        final int n3 = n - n2;
        if (n3 <= 2) {
            b = (n3 > 0);
        }
        return b;
    }
    
    public String Convert_MGRS_To_UTM(final String s) {
        final double n = 0.0;
        long longValue = 0L;
        long longValue2 = 0L;
        double doubleValue = 0.0;
        String break_MGRS_String = this.Break_MGRS_String(s);
        if (this.mZone == 0L) {
            break_MGRS_String = "Invalid Zone";
        }
        else if (break_MGRS_String.length() == 0) {
            if (this.letters[0] == 23 && (this.mZone == 32L || this.mZone == 34L || this.mZone == 36L)) {
                break_MGRS_String = "Invalid Zone/Letters";
            }
            else {
                if (this.letters[0] < 13) {
                    this.mHemisphere = 'S';
                }
                else {
                    this.mHemisphere = 'N';
                }
                final List get_Grid_Values = this.Get_Grid_Values(this.mZone);
                if (get_Grid_Values.size() == 3) {
                    longValue = get_Grid_Values.get(0);
                    longValue2 = get_Grid_Values.get(1);
                    doubleValue = get_Grid_Values.get(2);
                }
                else {
                    break_MGRS_String = "Get_Grid_Values failed";
                }
                if (this.letters[1] < longValue || this.letters[1] > longValue2 || this.letters[2] > 21) {
                    break_MGRS_String = "Invalid Letter 2 or 3";
                }
                if (break_MGRS_String.length() == 0) {
                    double n2 = this.letters[2] * 100000.0 + doubleValue;
                    double n3 = (this.letters[1] - longValue + 1L) * 100000.0;
                    if (longValue == 9L && this.letters[1] > 14) {
                        n3 -= 100000.0;
                    }
                    if (this.letters[2] > 14) {
                        n2 -= 100000.0;
                    }
                    if (this.letters[2] > 8) {
                        n2 -= 100000.0;
                    }
                    if (n2 >= 2000000.0) {
                        n2 -= 2000000.0;
                    }
                    final double get_Latitude_Band_Min_Northing = this.Get_Latitude_Band_Min_Northing(this.letters[0], n);
                    if (break_MGRS_String.length() == 0) {
                        double n4;
                        for (n4 = get_Latitude_Band_Min_Northing; n4 >= 2000000.0; n4 -= 2000000.0) {}
                        double n5 = n2 - n4;
                        if (n5 < 0.0) {
                            n5 += 2000000.0;
                        }
                        final double n6 = get_Latitude_Band_Min_Northing + n5;
                        this.mEasting += n3;
                        this.mNorthing += n6;
                    }
                    else {
                        MGRSConversion.log.error(break_MGRS_String);
                    }
                }
                else {
                    MGRSConversion.log.error(break_MGRS_String);
                }
            }
        }
        else {
            MGRSConversion.log.error(break_MGRS_String);
        }
        this.utmCoord3D = new Utm_Coord_3d(this.mEasting, this.mNorthing, 0.0, new Byte(String.valueOf(this.mZone)), this.mHemisphere == 'N');
        return break_MGRS_String;
    }
    
    public Ups_Coord_3d Convert_MGRS_To_UPS(final String s) {
        final Ups_Coord_3d ups_Coord_3d = new Ups_Coord_3d();
        String break_MGRS_String = this.Break_MGRS_String(s);
        if (break_MGRS_String.length() == 0) {
            long n2;
            long n3;
            long n4;
            double n5;
            double n6;
            if (this.letters[0] >= 24) {
                ups_Coord_3d.hemisphere = 'N';
                final int n = this.letters[0] - 22;
                n2 = this.UPS_Constant_Table[n].ltr2_low_value;
                n3 = this.UPS_Constant_Table[n].ltr2_high_value;
                n4 = this.UPS_Constant_Table[n].ltr3_high_value;
                n5 = this.UPS_Constant_Table[n].false_easting;
                n6 = this.UPS_Constant_Table[n].false_northing;
            }
            else {
                ups_Coord_3d.hemisphere = 'S';
                n2 = this.UPS_Constant_Table[this.letters[0]].ltr2_low_value;
                n3 = this.UPS_Constant_Table[this.letters[0]].ltr2_high_value;
                n4 = this.UPS_Constant_Table[this.letters[0]].ltr3_high_value;
                n5 = this.UPS_Constant_Table[this.letters[0]].false_easting;
                n6 = this.UPS_Constant_Table[this.letters[0]].false_northing;
            }
            if (this.letters[1] < n2 || this.letters[1] > n3 || this.letters[1] == 3 || this.letters[1] == 4 || this.letters[1] == 12 || this.letters[1] == 13 || this.letters[1] == 21 || this.letters[1] == 22 || this.letters[2] > n4) {
                break_MGRS_String = "MGRS_STRING_ERROR";
            }
            if (break_MGRS_String.length() == 0) {
                double n7 = this.letters[2] * 100000.0 + n6;
                if (this.letters[2] > 8) {
                    n7 -= 100000.0;
                }
                if (this.letters[2] > 14) {
                    n7 -= 100000.0;
                }
                double n8 = (this.letters[1] - n2) * 100000.0 + n5;
                if (n2 != 0L) {
                    if (this.letters[1] > 11) {
                        n8 -= 300000.0;
                    }
                    if (this.letters[1] > 20) {
                        n8 -= 200000.0;
                    }
                }
                else {
                    if (this.letters[1] > 2) {
                        n8 -= 200000.0;
                    }
                    if (this.letters[1] > 8) {
                        n8 -= 100000.0;
                    }
                    if (this.letters[1] > 11) {
                        n8 -= 300000.0;
                    }
                }
                ups_Coord_3d.x += n8;
                ups_Coord_3d.y += n7;
            }
        }
        return ups_Coord_3d;
    }
    
    public int Get_Latitude_Letter(final double n) {
        final double n2 = n * 57.29577951308232;
        int letter = 0;
        if (n2 >= 72.0 && n2 < 84.5) {
            letter = 23;
        }
        else if (n2 > -80.5 && n2 < 72.0) {
            letter = this.Latitude_Band_Table[(int)((n + 1.3962634015954636) / 0.13962634015954636 + 1.0E-12)].letter;
        }
        else {
            MGRSConversion.log.error("MGRS_LAT_ERROR");
        }
        return letter;
    }
    
    public List Get_Grid_Values(final long n) {
        long n2 = n % 6L;
        if (n2 == 0L) {
            n2 = 6L;
        }
        final long n3 = 1L;
        if (n2 == 1L || n2 == 4L) {
            this.ltr2_low_value = 0L;
            this.ltr2_high_value = 7L;
        }
        else if (n2 == 2L || n2 == 5L) {
            this.ltr2_low_value = 9L;
            this.ltr2_high_value = 17L;
        }
        else if (n2 == 3L || n2 == 6L) {
            this.ltr2_low_value = 18L;
            this.ltr2_high_value = 25L;
        }
        if (n3 != 0L) {
            if (n2 % 2L == 0L) {
                this.false_northing = 1500000.0;
            }
            else {
                this.false_northing = 0.0;
            }
        }
        else if (n2 % 2L == 0L) {
            this.false_northing = 500000.0;
        }
        else {
            this.false_northing = 1000000.0;
        }
        final ArrayList<Long> list = new ArrayList<Long>();
        list.add(new Long(this.ltr2_low_value));
        list.add(new Long(this.ltr2_high_value));
        list.add((Long)(Object)new Double(this.false_northing));
        return list;
    }
    
    private double Get_Latitude_Band_Min_Northing(final long n, double n2) {
        if (n >= 2L && n <= 7L) {
            n2 = this.Latitude_Band_Table[(int)n - 2].min_northing;
        }
        else if (n >= 9L && n <= 13L) {
            n2 = this.Latitude_Band_Table[(int)n - 3].min_northing;
        }
        else if (n >= 15L && n <= 23L) {
            n2 = this.Latitude_Band_Table[(int)n - 4].min_northing;
        }
        return n2;
    }
    
    private String Make_MGRS_String(final long n, final int[] array, double n2, double n3, final long n4) {
        final char[] array2 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
        String s;
        if (n >= 0L) {
            s = new PrintfFormat("%2.2ld").sprintf(n);
            final long n5 = s.length();
        }
        else {
            s = "  ";
        }
        for (long n6 = 0L; n6 < 3L; ++n6) {
            s += array2[array[(int)n6]];
        }
        final double pow = Math.pow(10.0, 5L - n4);
        n2 %= 100000.0;
        if (n2 >= 99999.5) {
            n2 = 99999.0;
        }
        final long n7 = (long)(n2 / pow);
        final String string = "%" + n4 + "." + n4 + "ld";
        final String string2 = s + new PrintfFormat(string).sprintf(n7);
        n3 %= 100000.0;
        if (n3 >= 99999.5) {
            n3 = 99999.0;
        }
        return string2 + new PrintfFormat(string).sprintf((long)(n3 / pow));
    }
    
    public String Break_MGRS_String(final String s) {
        int n = 0;
        String s2 = "";
        while (n < s.length() - 1 && Character.isDigit(s.charAt(n))) {
            ++n;
        }
        final long n2 = n;
        if (n2 <= 2L) {
            if (n2 > 0L && n2 != 1L) {
                this.mZone = Long.parseLong(s.substring(0, 2));
                if (this.mZone < 1L || this.mZone > 60L) {
                    s2 = "Invalid zone in Break_MGRS_String";
                }
            }
            else {
                this.mZone = 0L;
            }
        }
        else {
            s2 = "Wrong zone digit count in Break_MGRS_String";
        }
        int n3 = n;
        while (Character.isLetter(s.charAt(n)) && n < s.length() - 1) {
            ++n;
        }
        if (n - n3 == 3L) {
            this.letters[0] = s.substring(n3, ++n3).toUpperCase().charAt(0) - 'A';
            if (this.letters[0] == 8 || this.letters[0] == 14) {
                s2 = "Invalid letter[0] in Break_MGRS_String";
            }
            this.letters[1] = s.substring(n3, ++n3).toUpperCase().charAt(0) - 'A';
            if (this.letters[1] == 8 || this.letters[1] == 14) {
                s2 = "Invalid letter[1] in Break_MGRS_String";
            }
            this.letters[2] = s.substring(n3, ++n3).toUpperCase().charAt(0) - 'A';
            if (this.letters[2] == 8 || this.letters[2] == 14) {
                s2 = "Invalid letter[2] in Break_MGRS_String";
            }
        }
        else {
            s2 = "Wrong letter count in Break_MGRS_String";
        }
        final int n4 = n;
        while (n < s.length() && Character.isDigit(s.charAt(n))) {
            ++n;
        }
        final long n5 = n - n4;
        if (n5 <= 10L && n5 % 2L == 0L) {
            final String substring = s.substring(n4, n4 + (int)n5 / 2);
            final int n6 = n4 + (int)n5 / 2;
            final String substring2 = s.substring(n6, n6 + (int)n5 / 2);
            final long in_precision = n5 / 2L;
            this.in_precision = in_precision;
            if (in_precision > 0L) {
                final long long1 = Long.parseLong(substring);
                final long long2 = Long.parseLong(substring2);
                final double pow = Math.pow(10.0, 5L - in_precision);
                this.mEasting = long1 * pow;
                this.mNorthing = long2 * pow;
            }
            else {
                this.mEasting = 0.0;
                this.mNorthing = 0.0;
            }
        }
        else {
            s2 = "Wrong digit count for easting/northing in Break_MGRS_String";
        }
        if (s2.length() != 0) {
            MGRSConversion.log.error(s2);
        }
        return s2;
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
        MGRSConversion.UTM_a = 6378137.0;
        MGRSConversion.UTM_f = 0.0033528106647474805;
        MGRSConversion.UTM_Override = 0L;
        MGRSConversion.UPS_a = 6378137.0;
        MGRSConversion.UPS_f = 0.0033528106647474805;
        MGRSConversion.log = new Log((MGRSConversion.class$com$itt$J2KViewer$georvm$transforms$MGRSConversion == null) ? (MGRSConversion.class$com$itt$J2KViewer$georvm$transforms$MGRSConversion = class$("com.itt.J2KViewer.georvm.transforms.MGRSConversion")) : MGRSConversion.class$com$itt$J2KViewer$georvm$transforms$MGRSConversion);
    }
    
    private static class Grid_Values
    {
        int ltr2_low_value;
        int ltr2_high_value;
        double false_northing;
        
        public Grid_Values(final int ltr2_low_value, final int ltr2_high_value, final double false_northing) {
            this.ltr2_low_value = ltr2_low_value;
            this.ltr2_high_value = ltr2_high_value;
            this.false_northing = false_northing;
        }
    }
    
    private static class UPS_Constant
    {
        long letter;
        long ltr2_low_value;
        long ltr2_high_value;
        long ltr3_high_value;
        double false_easting;
        double false_northing;
        
        public UPS_Constant(final long letter, final long ltr2_low_value, final long ltr2_high_value, final long ltr3_high_value, final double false_easting, final double false_northing) {
            this.letter = letter;
            this.ltr2_low_value = ltr2_low_value;
            this.ltr2_high_value = ltr2_high_value;
            this.ltr3_high_value = ltr3_high_value;
            this.false_easting = false_easting;
            this.false_northing = false_northing;
        }
    }
    
    private static class Latitude_Band
    {
        public int letter;
        public double min_northing;
        public double north;
        public double south;
        
        public Latitude_Band(final int letter, final double min_northing, final double north, final double south) {
            this.letter = 0;
            this.min_northing = 0.0;
            this.north = 0.0;
            this.south = 0.0;
            this.letter = letter;
            this.min_northing = min_northing;
            this.north = north;
            this.south = south;
        }
    }
}
