// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import com.itt.J2KViewer.georvm.transforms.MGRSConversion;
import com.itt.J2KViewer.georvm.transforms.Utm_To_Gdc_Converter;
import com.itt.J2KViewer.georvm.coords.Gdc_Coord_3d;
import com.itt.J2KViewer.georvm.coords.Utm_Coord_3d;
import com.itt.J2KViewer.georvm.NITFGeoUtils;
import java.awt.Component;
import java.awt.Point;
import com.itt.J2KViewer.controller.ViewCentral;

public class GeoJumpUtil
{
    ViewCentral viewCentral;
    
    public GeoJumpUtil(final ViewCentral viewCentral) {
        this.viewCentral = viewCentral;
    }
    
    public Point latLonToPixel(final double n, final double n2) {
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        final String checkDDFormat = nitfGeoUtils.checkDDFormat(n2, n);
        if (checkDDFormat.length() != 0) {
            this.viewCentral.reportError(null, "Geolocation Error", checkDDFormat);
            return new Point(-1, -1);
        }
        int n3;
        int n4;
        try {
            if (nitfGeoUtils.hasRPC()) {
                final double[] pixelRPC = nitfGeoUtils.findPixelRPC(n2, n);
                n3 = (int)pixelRPC[0];
                n4 = (int)pixelRPC[1];
            }
            else {
                final double[] pixel = nitfGeoUtils.findPixel(n2, n);
                n3 = (int)pixel[0];
                n4 = (int)pixel[1];
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("NumberFormatException: " + ex.getMessage());
            return new Point(-1, -1);
        }
        return new Point(n3, n4);
    }
    
    public Point eastNorthToPixel(final double n, final double n2) {
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        final byte utmZone = nitfGeoUtils.getUtmZone();
        final String checkUTMFormat = nitfGeoUtils.checkUTMFormat(n, n2, utmZone);
        if (checkUTMFormat.length() != 0) {
            this.viewCentral.reportError(null, "Geolocation Error", checkUTMFormat);
            return new Point(-1, -1);
        }
        int n3;
        int n4;
        try {
            final Utm_Coord_3d utm_Coord_3d = new Utm_Coord_3d(n, n2, 0.0, utmZone, nitfGeoUtils.getNSHemisphere() == 'N');
            final Gdc_Coord_3d gdc_Coord_3d = new Gdc_Coord_3d();
            Utm_To_Gdc_Converter.Init();
            Utm_To_Gdc_Converter.Convert(utm_Coord_3d, gdc_Coord_3d);
            final double latitude = gdc_Coord_3d.latitude;
            final double longitude = gdc_Coord_3d.longitude;
            double[] array;
            if (nitfGeoUtils.hasRPC()) {
                array = nitfGeoUtils.findPixelRPC(longitude, latitude);
            }
            else {
                array = nitfGeoUtils.findPixel(longitude, latitude);
            }
            n3 = (int)array[0];
            n4 = (int)array[1];
        }
        catch (NumberFormatException ex) {
            System.out.println("NumberFormatException: " + ex.getMessage());
            return new Point(-1, -1);
        }
        return new Point(n3, n4);
    }
    
    public Point mgrsToPixel(final String s) {
        final NITFGeoUtils nitfGeoUtils = this.viewCentral.getNITFGeoUtils();
        if (nitfGeoUtils.checkMGRSFormat(s).length() != 0) {
            this.viewCentral.reportError(null, "Geolocation Error", "Incorrect MGRS format.\nAn example of proper format is: 15SWC8081751205.");
            return new Point(-1, -1);
        }
        int n;
        int n2;
        try {
            final double[] convert_MGRS_To_Geodetic = new MGRSConversion().Convert_MGRS_To_Geodetic(s);
            double[] array;
            if (nitfGeoUtils.hasRPC()) {
                array = nitfGeoUtils.findPixelRPC(convert_MGRS_To_Geodetic[0], convert_MGRS_To_Geodetic[1]);
            }
            else {
                array = nitfGeoUtils.findPixel(convert_MGRS_To_Geodetic[0], convert_MGRS_To_Geodetic[1]);
            }
            n = (int)array[0];
            n2 = (int)array[1];
        }
        catch (NumberFormatException ex) {
            System.out.println("NumberFormatException: " + ex.getMessage());
            return new Point(-1, -1);
        }
        return new Point(n, n2);
    }
}
