// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util.geotiff;

public class GeoTIFFConstants
{
    public static final short SHORT = 3;
    public static final short LONG = 4;
    public static final short RATIONAL = 5;
    public static final short DOUBLE = 12;
    public static final short RGB = 2;
    public static final short UNCOMPRESSED = 1;
    public static final short NORESOLUTIONUNIT = 1;
    public static final short IMAGEWIDTH = 256;
    public static final short IMAGEHEIGHT = 257;
    public static final short BITSPERSAMPLE = 258;
    public static final short COMPRESSION = 259;
    public static final short PHOTOMETRICINTERPRETATION = 262;
    public static final short STRIPOFFSETS = 273;
    public static final short SAMPLESPERPIXEL = 277;
    public static final short ROWSPERSTRIP = 278;
    public static final short STRIPBYTECOUNTS = 279;
    public static final short XRESOLUTION = 282;
    public static final short YRESOLUTION = 283;
    public static final short RESOLUTIONUNIT = 296;
    public static final short MODELTIEPOINT = -31614;
    public static final short MODELPIXELSCALE = -31986;
    public static final short MODELTRANSFORMATION = -31272;
    public static final short GEOKEYDIRECTORY = -30801;
    public static final short GTMODELTYPE = 1024;
    public static final short GTRASTERTYPE = 1025;
    public static final short PROJECTEDCSTYPE = 3072;
    public static final short MODELTYPEPROJECTED = 1;
    public static final short MODELTYPEGEOGRAPHIC = 2;
    public static final short RASTERPIXELISAREA = 1;
    public static final short PCS_WGS84_UTM_zone_base_N = 32600;
    public static final short PCS_WGS84_UTM_zone_60N = 32660;
    public static final short PCS_WGS84_UTM_zone_base_S = 32700;
    public static final short PCS_WGS84_UTM_zone_60S = 32760;
    public static final byte[] BIG_ENDIAN;
    public static final byte[] LITTLE_ENDIAN;
    public static final short MAGIC_NUMBER = 42;
    public static final int IFD_TERMINATOR = 0;
    
    static {
        BIG_ENDIAN = new byte[] { 77, 77 };
        LITTLE_ENDIAN = new byte[] { 73, 73 };
    }
}
