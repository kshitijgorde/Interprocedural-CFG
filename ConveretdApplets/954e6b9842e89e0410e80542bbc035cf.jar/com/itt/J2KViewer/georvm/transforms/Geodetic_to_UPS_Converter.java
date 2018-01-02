// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.transforms;

import com.itt.J2KViewer.util.Log;

public class Geodetic_to_UPS_Converter
{
    public static final double PI = 3.141592653589793;
    public static final double PI_OVER = 1.5707963267948966;
    public static final double MAX_LAT = 1.5707963267948966;
    public static final double MAX_ORIGIN_LAT = 1.4157155848011311;
    public static final double MIN_NORTH_LAT = 1.4573499254152653;
    public static final double MIN_SOUTH_LAT = -1.387536755335492;
    public static final double MIN_EAST_NORTH = 0.0;
    public static final double MAX_EAST_NORTH = 4000000.0;
    public char Hemisphere;
    double UPS_a;
    double UPS_f;
    double UPS_False_Easting;
    double UPS_False_Northing;
    double UPS_Origin_Latitude;
    double UPS_Origin_Longitude;
    double false_easting;
    double false_northing;
    double UPS_Easting;
    double UPS_Northing;
    double easting;
    double northing;
    private static Log log;
    static /* synthetic */ Class class$com$itt$J2KViewer$georvm$transforms$Geodetic_to_UPS_Converter;
    
    public Geodetic_to_UPS_Converter() {
        this.Hemisphere = ' ';
        this.UPS_a = 6378137.0;
        this.UPS_f = 0.0033528106647474805;
        this.UPS_False_Easting = 2000000.0;
        this.UPS_False_Northing = 2000000.0;
        this.UPS_Origin_Latitude = 1.4157155848011311;
        this.UPS_Origin_Longitude = 0.0;
        this.false_easting = 0.0;
        this.false_northing = 0.0;
        this.UPS_Easting = 0.0;
        this.UPS_Northing = 0.0;
        this.easting = 0.0;
        this.northing = 0.0;
    }
    
    double[] Convert_Geodetic_To_UPS(final double n, final double n2) {
        String s = "";
        if (n < -1.5707963267948966 || n > 1.5707963267948966) {
            s = "Latitude out of range";
        }
        if (n < 0.0 && n > -1.387536755335492) {
            s = "UPS Latitude Error";
        }
        if (n >= 0.0 && n < 1.4573499254152653) {
            s = "UPS Latitude Error";
        }
        if (n2 < -3.141592653589793 || n2 > 6.283185307179586) {
            s = "Slam out of range";
        }
        if (s.length() == 0) {
            if (n < 0.0) {
                this.UPS_Origin_Latitude = -1.4157155848011311;
                this.Hemisphere = 'S';
            }
            else {
                this.UPS_Origin_Latitude = 1.4157155848011311;
                this.Hemisphere = 'N';
            }
            final Geodetic_to_PS_Converter geodetic_to_PS_Converter = new Geodetic_to_PS_Converter();
            geodetic_to_PS_Converter.Set_Polar_Stereographic_Parameters(this.UPS_a, this.UPS_f, this.UPS_Origin_Latitude, this.UPS_Origin_Longitude, this.false_easting, this.false_northing);
            final double[] convert_Geodetic_To_Polar_Stereographic = geodetic_to_PS_Converter.Convert_Geodetic_To_Polar_Stereographic(n, n2);
            this.UPS_Easting = this.UPS_False_Easting + convert_Geodetic_To_Polar_Stereographic[0];
            this.UPS_Northing = this.UPS_False_Northing + convert_Geodetic_To_Polar_Stereographic[1];
            this.easting = this.UPS_Easting;
            this.northing = this.UPS_Northing;
        }
        else {
            Geodetic_to_UPS_Converter.log.error(s);
        }
        return new double[] { this.easting, this.northing };
    }
    
    public double[] Convert_UPS_To_Geodetic(final char c, final double n, final double n2) {
        String s = "";
        double[] convert_Polar_Stereographic_To_Geodetic = new double[2];
        if (c != 'N' && c != 'S') {
            s = "UPS_HEMISPHERE_ERROR";
        }
        if (n < 0.0 || n > 4000000.0) {
            s = "UPS_EASTING_ERROR";
        }
        if (n2 < 0.0 || n2 > 4000000.0) {
            s = "UPS_NORTHING_ERROR";
        }
        if (c == 'N') {
            this.UPS_Origin_Latitude = 1.4157155848011311;
        }
        if (c == 'S') {
            this.UPS_Origin_Latitude = -1.4157155848011311;
        }
        if (s.length() == 0) {
            final Polar_Stereographic_Converter polar_Stereographic_Converter = new Polar_Stereographic_Converter();
            polar_Stereographic_Converter.Set_Polar_Stereographic_Parameters(this.UPS_a, this.UPS_f, this.UPS_Origin_Latitude, this.UPS_Origin_Longitude, this.UPS_False_Easting, this.UPS_False_Northing);
            convert_Polar_Stereographic_To_Geodetic = polar_Stereographic_Converter.Convert_Polar_Stereographic_To_Geodetic(n, n2);
            if (convert_Polar_Stereographic_To_Geodetic[1] < 0.0 && convert_Polar_Stereographic_To_Geodetic[1] > -1.387536755335492) {}
            if (convert_Polar_Stereographic_To_Geodetic[1] >= 0.0 && convert_Polar_Stereographic_To_Geodetic[1] < 1.4573499254152653) {}
        }
        return convert_Polar_Stereographic_To_Geodetic;
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
        Geodetic_to_UPS_Converter.log = new Log((Geodetic_to_UPS_Converter.class$com$itt$J2KViewer$georvm$transforms$Geodetic_to_UPS_Converter == null) ? (Geodetic_to_UPS_Converter.class$com$itt$J2KViewer$georvm$transforms$Geodetic_to_UPS_Converter = class$("com.itt.J2KViewer.georvm.transforms.Geodetic_to_UPS_Converter")) : Geodetic_to_UPS_Converter.class$com$itt$J2KViewer$georvm$transforms$Geodetic_to_UPS_Converter);
    }
}
