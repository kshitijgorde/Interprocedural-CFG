// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm;

import java.util.Iterator;
import com.itt.J2KViewer.util.ViewerConst;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import com.itt.J2KViewer.georvm.transforms.Utm_To_Gdc_Converter;
import com.itt.J2KViewer.util.Matrix;
import java.text.DecimalFormat;
import com.itt.J2KViewer.georvm.transforms.Geodetic_to_UTM_Converter;
import com.itt.J2KViewer.georvm.coords.Utm_Coord_3d;
import com.itt.J2KViewer.georvm.transforms.MGRSConversion;
import com.itt.J2KViewer.georvm.coords.Gdc_Coord_3d;
import com.itt.J2KViewer.georvm.transforms.RPC_Image_to_Geodetic;
import java.util.List;
import java.awt.Dimension;
import java.util.HashMap;
import com.itt.J2KViewer.util.Log;

public class NITFGeoUtils
{
    private static Log log;
    public static final String DEG_HEMI_LAT = "Degree Hemisphere Lat";
    public static final String DEG_HEMI_LON = "Degree Hemisphere Lon";
    public static final String DEG_LATITUDE = "Degree Latitude";
    public static final String DEG_LONGITUDE = "Degree Longitude";
    public static final String LATITUDE_BEARING = "Latitude Bearing";
    public static final String LATITUDE_DEG = "Latitude Degrees";
    public static final String LATITUDE_MIN = "Latitude Minutes";
    public static final String LATITUDE_SEC = "Latitude Seconds";
    public static final String LONGITUDE_BEARING = "Longitude Bearing";
    public static final String LONGITUDE_DEG = "Longitude Degrees";
    public static final String LONGITUDE_MIN = "Longitude Minutes";
    public static final String LONGITUDE_SEC = "Longitude Seconds";
    public static final String UTM_BAND = "UTM Band";
    public static final String UTM_EASTING = "UTM Easting";
    public static final String UTM_J = "UTM J";
    public static final String UTM_K = "UTM K";
    public static final String UTM_NORTHING = "UTM Northing";
    public static final String UTM_ZONE = "UTM Zone";
    public static final double PI = 3.141592653589793;
    public byte ANAMRPH_CORR;
    public long FI_COL;
    public long FI_ROW;
    public long ICHIPB_CEL;
    public String ICHIPB_CETAG;
    public double[] OPFI;
    public double SCALE_FACTOR;
    public long SCANBLK_NUM;
    public byte XFRM_FLAG;
    public long CEL;
    public String CETAG;
    public double ERR_BIAS;
    public double ERR_RAND;
    public double l_off;
    public double p_off;
    public double[] LDENCOEF;
    public double[] LNUMCOEF;
    public double[] OFFSETS;
    public double[] PDENCOEF;
    public double[] PNUMCOEF;
    public double[] SCALES;
    public byte SUCCESS;
    public double xstart;
    public double ystart;
    private double[] coefx;
    private double[] coefy;
    private HashMap cornerDataStrings;
    private HashMap degDataMap;
    private HashMap geoDataMap;
    private double[][] geoInterp;
    private boolean hasRPCs;
    private boolean hasICHIPB;
    private char icords;
    private String igeolo;
    private Projection imageProj;
    private boolean isDataValid;
    private double[][] kx;
    private double[][] ky;
    private char NSHemisphere;
    private char utmHemisphere;
    private byte utmZone;
    private double northRotationFactor;
    private double utmRotationFactor;
    private double xPixelScale;
    private double yPixelScale;
    private double rpcLastLat;
    private double rpcLastLon;
    private HashMap rpcDataMap;
    private Dimension totalDims;
    private HashMap utmDataMap;
    private double rotateUpIsUp;
    private double nppbh;
    private double nppbv;
    private String ititle;
    static /* synthetic */ Class class$com$itt$J2KViewer$georvm$NITFGeoUtils;
    
    public NITFGeoUtils(final List list, final Dimension totalDims) {
        this.OPFI = new double[16];
        this.LDENCOEF = new double[20];
        this.LNUMCOEF = new double[20];
        this.OFFSETS = new double[5];
        this.PDENCOEF = new double[20];
        this.PNUMCOEF = new double[20];
        this.SCALES = new double[5];
        this.xstart = 0.0;
        this.ystart = 0.0;
        this.coefx = new double[4];
        this.coefy = new double[4];
        this.cornerDataStrings = null;
        this.degDataMap = null;
        this.geoDataMap = null;
        this.geoInterp = new double[2][4];
        this.hasRPCs = false;
        this.hasICHIPB = false;
        this.igeolo = null;
        this.isDataValid = true;
        this.kx = new double[2][2];
        this.ky = new double[2][2];
        this.rpcDataMap = null;
        this.totalDims = null;
        this.utmDataMap = null;
        this.rotateUpIsUp = 0.0;
        this.nppbh = 0.0;
        this.nppbv = 0.0;
        this.ititle = null;
        this.totalDims = totalDims;
        this.processXML(list);
        if (this.isReady()) {
            this.initInterpolation();
        }
    }
    
    public double[] bilinearGeoInterpolate(final int n, final int n2) {
        double n3 = this.geoInterp[0][0] * n + this.geoInterp[0][1] * n2 + this.geoInterp[0][2] * n * n2 + this.geoInterp[0][3];
        final double n4 = this.geoInterp[1][0] * n + this.geoInterp[1][1] * n2 + this.geoInterp[1][2] * n * n2 + this.geoInterp[1][3];
        if (n3 < -180.0) {
            n3 += 360.0;
        }
        else if (n3 > 180.0) {
            n3 -= 360.0;
        }
        return new double[] { n3, n4 };
    }
    
    public double[] getGeodeticLocation(final int n, final int n2) {
        double[] bilinearGeoInterpolate;
        if (this.hasRPCs) {
            final Gdc_Coord_3d rpcImagetoGeo = new RPC_Image_to_Geodetic().RPCImagetoGeo(n, n2, this);
            bilinearGeoInterpolate = new double[] { rpcImagetoGeo.longitude, rpcImagetoGeo.latitude };
        }
        else {
            bilinearGeoInterpolate = this.bilinearGeoInterpolate(n, n2);
        }
        return bilinearGeoInterpolate;
    }
    
    public String getMGRSLocation(final int n, final int n2) {
        final double[] geodeticLocation = this.getGeodeticLocation(n, n2);
        return new MGRSConversion().Convert_Geodetic_To_MGRS(geodeticLocation[1] * 0.017453292519943295, geodeticLocation[0] * 0.017453292519943295, 5);
    }
    
    public Utm_Coord_3d getUTMLocation(final int n, final int n2) {
        final double[] geodeticLocation = this.getGeodeticLocation(n, n2);
        return new Geodetic_to_UTM_Converter().Convert_Geodetic_To_UTM(geodeticLocation[1] * 0.017453292519943295, geodeticLocation[0] * 0.017453292519943295);
    }
    
    public static String toIGEOLOFormat(final Utm_Coord_3d utm_Coord_3d) {
        final String s = "";
        final DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setGroupingUsed(false);
        final double x = utm_Coord_3d.x;
        final double y = utm_Coord_3d.y;
        decimalFormat.setMinimumIntegerDigits(2);
        final String string = s + decimalFormat.format(utm_Coord_3d.zone);
        decimalFormat.setMinimumIntegerDigits(6);
        final String string2 = string + decimalFormat.format((int)x);
        decimalFormat.setMinimumIntegerDigits(7);
        return string2 + decimalFormat.format((int)y);
    }
    
    public String checkDDFormat(final double n, final double n2) {
        String s = "";
        if (n2 < -90.0 || n2 > 90.0) {
            s = "Invalid latitude! Latitude must be between -90 and 90.";
        }
        if (n < -180.0 || n > 180.0) {
            s = "Invalid longitude! Longitude must be between -180 and 180.";
        }
        return s;
    }
    
    public String checkMGRSFormat(final String s) {
        return new MGRSConversion().Break_MGRS_String(s);
    }
    
    public String checkUTMFormat(final double n, final double n2, final byte b) {
        String s = "";
        final long n3 = 100000L;
        final long n4 = 900000L;
        final long n5 = 0L;
        final long n6 = 10000000L;
        final byte b2 = 0;
        final byte b3 = 60;
        if (n < n3 || n > n4) {
            s = "Invalid Easting! Easting must be between 100,000 and 900,000.";
        }
        if (n2 < n5 || n2 > n6) {
            s = "Invalid Northing! Northing must be between 0 and 10,000,000.";
        }
        if (b < b2 || b > b3) {
            s = "Invalid Zone!";
        }
        return s;
    }
    
    public double[] findPixel(final double n, final double n2) {
        double n3 = -999.0;
        double n4 = -999.0;
        double n5 = 0.0;
        double n6 = 0.0;
        final boolean b = Math.abs(this.coefx[3]) < 1.0E-16 && Math.abs(this.coefy[3]) < 1.0E-16;
        final double[] array = new double[2];
        if (b) {
            final double[] array2 = new double[2];
            final double[] array3 = { this.coefx[1], this.coefx[2] };
            array[0] = this.coefy[1];
            array[1] = this.coefy[2];
            array2[0] = n - this.coefx[0];
            array2[1] = n2 - this.coefy[0];
            final double[] pixel_linear_equation1 = this.findPixel_linear_equation1(array3, array, array2);
            return new double[] { pixel_linear_equation1[0], pixel_linear_equation1[1] };
        }
        int n7 = 0;
        for (int i = 0; i < 9999; ++i) {
            final double[] array4 = { 1.0, n5, n6, n5 * n6 };
            double n8 = 0.0;
            double n9 = 0.0;
            for (int j = 0; j < 4; ++j) {
                n8 += array4[j] * this.coefx[j];
                n9 += array4[j] * this.coefy[j];
            }
            array[0] = n8 - n;
            array[1] = n9 - n2;
            final Matrix times = new Matrix(new double[][] { { this.coefx[1] + this.coefx[3] * n6, this.coefx[2] + this.coefx[3] * n5 }, { this.coefy[1] + this.coefy[3] * n6, this.coefy[2] + this.coefy[3] * n5 } }).inverse().times(new Matrix(new double[][] { { array[0] }, { array[1] } })).times(-1.0);
            n5 += times.get(0, 0);
            n6 += times.get(1, 0);
            if (Math.sqrt(times.get(0, 0) * times.get(0, 0) + times.get(1, 0) * times.get(1, 0)) < 1.0E-8) {
                n7 = 1;
                break;
            }
        }
        if (n7 == 1) {
            n3 = n5;
            n4 = n6;
        }
        return new double[] { n3, n4 };
    }
    
    public double[] findPixelRPC(final double n, final double n2) {
        final double[] geo_to_image = new RPC_Image_to_Geodetic().geo_to_image(n2, n, this.OFFSETS[4], this);
        return new double[] { geo_to_image[1], geo_to_image[0] };
    }
    
    public double findRPCUpIsUp(final double n, final double n2) {
        final double n3 = 1.0;
        final double n4 = 10000.0;
        final double[] array = { 0.0, 0.0 };
        final double[] array2 = { 0.0, 1.0 };
        final RPC_Image_to_Geodetic rpc_Image_to_Geodetic = new RPC_Image_to_Geodetic();
        final double[] geo_to_image = rpc_Image_to_Geodetic.geo_to_image(n2, n, n3, this);
        final double[] geo_to_image2 = rpc_Image_to_Geodetic.geo_to_image(n2, n, n4, this);
        return 1.5707963267948966 - Math.atan2(-(geo_to_image2[0] - geo_to_image[0]), geo_to_image2[1] - geo_to_image[1]);
    }
    
    public HashMap getCornerStrings() {
        return this.cornerDataStrings;
    }
    
    public char getICORDS() {
        return this.icords;
    }
    
    public double[] getLatLonEastNorth(final String s) {
        double n = 0.0;
        double n2 = 0.0;
        double x = -1.0;
        double y = -1.0;
        switch (this.icords) {
            case 'C':
            case 'G': {
                final HashMap<Object, String> hashMap = this.geoDataMap.get(s);
                if (!this.isReady() || hashMap == null) {
                    break;
                }
                n = n + Double.parseDouble(hashMap.get("Latitude Degrees")) + Double.parseDouble(hashMap.get("Latitude Minutes")) / 60.0 + Double.parseDouble(hashMap.get("Latitude Seconds")) / 3600.0;
                if (hashMap.get("Latitude Bearing").equals("S")) {
                    n *= -1.0;
                }
                n2 = n2 + Double.parseDouble(hashMap.get("Longitude Degrees")) + Double.parseDouble(hashMap.get("Longitude Minutes")) / 60.0 + Double.parseDouble(hashMap.get("Longitude Seconds")) / 3600.0;
                if (hashMap.get("Longitude Bearing").equals("W")) {
                    n2 *= -1.0;
                    break;
                }
                break;
            }
            case 'N':
            case 'S':
            case 'U': {
                final HashMap<Object, String> hashMap2 = this.utmDataMap.get(s);
                if (this.isReady() && hashMap2 != null) {
                    final double double1 = Double.parseDouble(hashMap2.get("UTM Northing"));
                    final double double2 = Double.parseDouble(hashMap2.get("UTM Easting"));
                    this.utmZone = Byte.parseByte(hashMap2.get("UTM Zone"));
                    Utm_Coord_3d utmCoords = null;
                    if (this.icords == 'U') {
                        final MGRSConversion mgrsConversion = new MGRSConversion();
                        final String convert_MGRS_To_UTM = mgrsConversion.Convert_MGRS_To_UTM(this.cornerDataStrings.get(s));
                        if (convert_MGRS_To_UTM.length() == 0) {
                            utmCoords = mgrsConversion.getUTMCoords();
                        }
                        else {
                            NITFGeoUtils.log.error("Convert_MGRS_To_UTM failed with " + convert_MGRS_To_UTM);
                        }
                    }
                    else {
                        utmCoords = new Utm_Coord_3d(double2, double1, 0.0, this.utmZone, this.icords == 'N');
                    }
                    if (utmCoords != null) {
                        final Gdc_Coord_3d gdc_Coord_3d = new Gdc_Coord_3d();
                        Utm_To_Gdc_Converter.Init();
                        Utm_To_Gdc_Converter.Convert(utmCoords, gdc_Coord_3d);
                        n = gdc_Coord_3d.latitude;
                        n2 = gdc_Coord_3d.longitude;
                        x = utmCoords.x;
                        y = utmCoords.y;
                    }
                    break;
                }
                break;
            }
            case 'D': {
                final HashMap<Object, String> hashMap3 = this.degDataMap.get(s);
                if (this.isReady() && hashMap3 != null) {
                    n = Double.parseDouble(hashMap3.get("Degree Latitude"));
                    if (hashMap3.get("Degree Hemisphere Lat").equals("-")) {
                        n *= -1.0;
                    }
                    n2 = Double.parseDouble(hashMap3.get("Degree Longitude"));
                    if (hashMap3.get("Degree Hemisphere Lon").equals("-")) {
                        n2 *= -1.0;
                    }
                    break;
                }
                break;
            }
            default: {
                if (!this.hasRPC()) {
                    NITFGeoUtils.log.warn("Unrecognized ICORDS format: '" + this.icords + "'");
                }
                if (!this.hasRPC()) {
                    break;
                }
                final HashMap<Object, String> hashMap4 = this.rpcDataMap.get(s);
                if (!this.isReady() || hashMap4 == null) {
                    break;
                }
                n = Double.parseDouble(hashMap4.get("Degree Latitude"));
                if (n <= 0.0) {
                    n *= -1.0;
                }
                n2 = Double.parseDouble(hashMap4.get("Degree Longitude"));
                if (n2 <= 0.0) {
                    n2 *= -1.0;
                    break;
                }
                break;
            }
        }
        return new double[] { n2, n, x, y };
    }
    
    public char getNSHemisphere() {
        return this.NSHemisphere;
    }
    
    public Projection getProj() {
        return this.imageProj;
    }
    
    public double getNorthRotationFactor() {
        return this.northRotationFactor;
    }
    
    public double getUTMRotationFactor() {
        return this.utmRotationFactor;
    }
    
    public double getRPCUpRotationFactor() {
        return this.rotateUpIsUp;
    }
    
    public double getXPixelScale() {
        return this.xPixelScale;
    }
    
    public double getYPixelScale() {
        return this.yPixelScale;
    }
    
    public double getRPCLastLat() {
        return this.rpcLastLat;
    }
    
    public double getRPCLastLon() {
        return this.rpcLastLon;
    }
    
    public char getUtmHemisphere() {
        return this.utmHemisphere;
    }
    
    public byte getUtmZone() {
        return this.utmZone;
    }
    
    public boolean hasRPC() {
        return this.hasRPCs;
    }
    
    public boolean hasICHIPB() {
        return this.hasICHIPB;
    }
    
    public boolean isDataValid() {
        return this.isDataValid;
    }
    
    public boolean isReady() {
        return this.isDataValid && this.cornerDataStrings != null && this.cornerDataStrings.size() == 4;
    }
    
    public double[] rpcLocation(final int n, final int n2, final NITFGeoUtils nitfGeoUtils) {
        final Gdc_Coord_3d rpcImagetoGeo = new RPC_Image_to_Geodetic().RPCImagetoGeo(n, n2, nitfGeoUtils);
        this.rpcLastLat = rpcImagetoGeo.latitude;
        this.rpcLastLon = rpcImagetoGeo.longitude;
        return new double[] { rpcImagetoGeo.latitude, rpcImagetoGeo.longitude };
    }
    
    public void setRPCLastLat(final double rpcLastLat) {
        this.rpcLastLat = rpcLastLat;
    }
    
    public void setRPCLastLon(final double rpcLastLon) {
        this.rpcLastLon = rpcLastLon;
    }
    
    private void calcKxKy(final Matrix matrix, final Matrix matrix2, final Matrix matrix3, final Matrix matrix4) {
        final int rowDimension = matrix.getRowDimension();
        final int n = 1;
        final int n2 = (int)Math.pow(n + 1, 2.0);
        final Matrix transpose = matrix.transpose();
        final Matrix transpose2 = matrix2.transpose();
        final Matrix transpose3 = matrix3.transpose();
        final Matrix transpose4 = matrix4.transpose();
        final double[][] array = new double[4][2];
        final double[][] array2 = new double[4][2];
        array[0][0] = transpose.get(0, 0);
        array[1][0] = transpose.get(0, 1);
        array[2][0] = transpose.get(0, 2);
        array[3][0] = transpose.get(0, 3);
        array[0][1] = transpose2.get(0, 0);
        array[1][1] = transpose2.get(0, 1);
        array[2][1] = transpose2.get(0, 2);
        array[3][1] = transpose2.get(0, 3);
        array2[0][0] = transpose3.get(0, 0);
        array2[1][0] = transpose3.get(0, 1);
        array2[2][0] = transpose3.get(0, 2);
        array2[3][0] = transpose3.get(0, 3);
        array2[0][1] = transpose4.get(0, 0);
        array2[1][1] = transpose4.get(0, 1);
        array2[2][1] = transpose4.get(0, 2);
        array2[3][1] = transpose4.get(0, 3);
        final double[][] array3 = new double[n2][rowDimension];
        final double[] array4 = new double[n + 1];
        for (int i = 0; i < rowDimension; ++i) {
            array4[0] = 1.0;
            array4[1] = array4[0] * array2[i][1];
            final Matrix matrix5 = new Matrix(new double[][] { { array4[0] }, { array4[1] } });
            array3[0][i] = matrix5.get(0, 0);
            array3[1][i] = matrix5.get(1, 0);
            for (int j = 1; j == n; ++j) {
                final Matrix times = matrix5.times(Math.pow(array2[i][0], j));
                array3[j * (n + 1)][i] = times.get(0, 0);
                array3[j * (n + 1) + 1][i] = times.get(1, 0);
            }
        }
        final Matrix matrix6 = new Matrix(array3);
        final Matrix times2 = matrix6.times(matrix6.transpose()).inverse().times(matrix6);
        final double[][] array5 = { { array[0][0] }, { array[1][0] }, { array[2][0] }, { array[3][0] } };
        final double[][] array6 = { { array[0][1] }, { array[1][1] }, { array[2][1] }, { array[3][1] } };
        final Matrix matrix7 = new Matrix(array5);
        final Matrix matrix8 = new Matrix(array6);
        final Matrix times3 = times2.times(matrix7);
        final Matrix times4 = times2.times(matrix8);
        this.kx[0][0] = times3.get(0, 0);
        this.kx[0][1] = times3.get(1, 0);
        this.kx[1][0] = times3.get(2, 0);
        this.kx[1][1] = times3.get(3, 0);
        this.ky[0][0] = times4.get(0, 0);
        this.ky[0][1] = times4.get(1, 0);
        this.ky[1][0] = times4.get(2, 0);
        this.ky[1][1] = times4.get(3, 0);
    }
    
    private void calcOffsets() {
        if (this.hasRPC()) {
            if (this.OPFI[0] != this.OPFI[2] || this.OPFI[1] != this.OPFI[5] || this.OPFI[8] != this.OPFI[10] || this.OPFI[9] != this.OPFI[13] || this.OPFI[4] - this.OPFI[0] != this.OPFI[12] - this.OPFI[8] || this.OPFI[3] - this.OPFI[1] != this.OPFI[11] - this.OPFI[9] || this.OPFI[6] - this.OPFI[0] != this.OPFI[4] - this.OPFI[2] || this.OPFI[15] - this.OPFI[9] != this.OPFI[11] - this.OPFI[13]) {
                NITFGeoUtils.log.info("This image has been chipped from a larger source image. However, the chip is rotated or stretched with respect to the source.RPC solution not possible.");
                this.hasRPCs = false;
                return;
            }
            this.l_off = this.OPFI[8] - this.OPFI[0];
            this.p_off = this.OPFI[9] - this.OPFI[1];
        }
        else if (!this.hasRPC() && this.isReady()) {
            this.l_off = this.OPFI[8] - this.OPFI[0];
            this.p_off = this.OPFI[9] - this.OPFI[1];
        }
    }
    
    private void calcNorthRotation(final double[] array, final double[] array2, final double[] array3, final double[] array4) {
        if (this.isReady() && array != null && array2 != null && array3 != null && array4 != null) {
            double n = array[0] - array3[0];
            if (n > 180.0) {
                n -= 360.0;
            }
            else if (n < -180.0) {
                n += 360.0;
            }
            double n2 = array2[0] - array4[0];
            if (n2 > 180.0) {
                n2 -= 360.0;
            }
            else if (n2 < -180.0) {
                n2 += 360.0;
            }
            this.northRotationFactor = Math.atan2(-((n + n2) / 2.0 * Math.cos(Math.toRadians(Math.abs((array[1] + array3[1]) / 2.0)))), (array[1] - array3[1] + (array2[1] - array4[1])) / 2.0);
        }
    }
    
    private void calcImageOrientation() {
        int n = 1;
        final int min = Math.min(this.totalDims.width / 4, this.totalDims.height / 4);
        int n2 = 0;
        int[] array = null;
        int[] array2 = null;
        int[] array3 = null;
        Utm_Coord_3d utmLocation = null;
        Utm_Coord_3d utmLocation2 = null;
        Utm_Coord_3d utmLocation3 = null;
        while (n2 == 0 && n < min) {
            final int n3 = this.totalDims.width / n;
            final int n4 = this.totalDims.height / n;
            for (int n5 = 0; n2 == 0 && n5 < n; ++n5) {
                final int n6 = n3 * n5;
                for (int n7 = 0; n2 == 0 && n7 < n; ++n7) {
                    final int n8 = n4 * n7;
                    array = new int[] { n6, n8 };
                    array2 = new int[] { n6 + n3 - 1, n8 };
                    array3 = new int[] { n6, n8 + n4 - 1 };
                    utmLocation = this.getUTMLocation(array[0], array[1]);
                    utmLocation2 = this.getUTMLocation(array2[0], array2[1]);
                    utmLocation3 = this.getUTMLocation(array3[0], array3[1]);
                    if (utmLocation.zone == utmLocation2.zone && utmLocation.hemisphere_north == utmLocation2.hemisphere_north && utmLocation.zone == utmLocation3.zone && utmLocation.hemisphere_north == utmLocation3.hemisphere_north) {
                        n2 = 1;
                    }
                }
            }
            ++n;
        }
        if (n2 != 0) {
            this.utmRotationFactor = Math.atan2(utmLocation3.x - utmLocation.x, utmLocation.y - utmLocation3.y);
            final double abs = Math.abs(Math.sin(this.utmRotationFactor));
            final double abs2 = Math.abs(Math.cos(this.utmRotationFactor));
            final int n9 = array2[0] - array[0];
            final int n10 = array3[1] - array[1];
            if (abs2 >= abs) {
                this.xPixelScale = Math.abs((utmLocation2.x - utmLocation.x) / abs2 / n9);
                this.yPixelScale = Math.abs((utmLocation.y - utmLocation3.y) / abs2 / n10);
            }
            else {
                this.xPixelScale = Math.abs((utmLocation.x - utmLocation3.x) / abs / n10);
                this.yPixelScale = Math.abs((utmLocation2.y - utmLocation.y) / abs / n9);
            }
        }
        else {
            NITFGeoUtils.log.error("Unable to determine north direction, or pixel scale. Could not find a large enough region of the image that is within a single UTM zone");
        }
    }
    
    private void extractRawData(final Element element) {
        if (element != null) {
            final Element element2 = this.getElement(element, "ICORDS");
            if (element2 != null) {
                final String nodeValue = element2.getFirstChild().getNodeValue();
                if (nodeValue != null) {
                    this.icords = nodeValue.charAt(0);
                }
            }
            final Element element3 = this.getElement(element, "IGEOLO");
            if (element3 != null) {
                this.igeolo = element3.getFirstChild().getNodeValue();
            }
            else {
                NITFGeoUtils.log.info("Could not get GeoCoord data. <IGEOLO> element not available in metadata.");
            }
            Element element4 = this.getElement(element, "ITITLE");
            if (element4 == null) {
                element4 = this.getElement(element, "IID2");
            }
            if (element4 != null) {
                this.ititle = element4.getFirstChild().getNodeValue();
            }
            final Element element5 = this.getElement(element, "NPPBV");
            if (element5 != null) {
                this.nppbv = Double.parseDouble(element5.getFirstChild().getNodeValue());
            }
            final Element element6 = this.getElement(element, "NPPBH");
            if (element6 != null) {
                this.nppbh = Double.parseDouble(element6.getFirstChild().getNodeValue());
            }
            if (this.ititle != null && this.nppbh > 0.0 && this.nppbv > 0.0) {
                final int index = this.ititle.indexOf("_");
                final int length = this.ititle.trim().length();
                if (index > 0 && index < this.ititle.length() - 7) {
                    this.ititle = this.ititle.substring(index + 1);
                    int n = 0;
                    String s;
                    String s2;
                    if (length == 40) {
                        s = this.ititle.substring(3, 7);
                        s2 = this.ititle.substring(0, 2);
                    }
                    else {
                        s = this.ititle.substring(3, 7);
                        s2 = this.ititle.substring(0, 3);
                    }
                    try {
                        this.xstart = (Double.parseDouble(s2) - 1.0) * this.nppbh;
                    }
                    catch (NumberFormatException ex) {
                        n = 1;
                    }
                    try {
                        this.ystart = (Double.parseDouble(s) - 1.0) * this.nppbv;
                    }
                    catch (NumberFormatException ex2) {
                        n = 1;
                    }
                    if (n == 1) {
                        this.xstart = 0.0;
                        this.ystart = 0.0;
                    }
                    if (this.xstart < 0.0) {
                        this.xstart = 0.0;
                    }
                    if (this.ystart < 0.0) {
                        this.ystart = 0.0;
                    }
                }
            }
        }
    }
    
    private void extractRawICHIPBData(final Element element) {
        if (element != null) {
            final Element element2 = this.getElement(element, "CETAG");
            if (element2 != null) {
                this.ICHIPB_CETAG = element2.getFirstChild().getNodeValue();
            }
            final Element element3 = this.getElement(element, "CEL");
            if (element3 != null) {
                this.ICHIPB_CEL = Long.parseLong(element3.getFirstChild().getNodeValue());
            }
            final Element element4 = this.getElement(element, "XFRM_FLAG");
            if (element4 != null) {
                this.XFRM_FLAG = Byte.parseByte(element4.getFirstChild().getNodeValue());
            }
            final Element element5 = this.getElement(element, "SCALE_FACTOR");
            if (element5 != null) {
                this.SCALE_FACTOR = Double.parseDouble(element5.getFirstChild().getNodeValue());
            }
            final Element element6 = this.getElement(element, "ANAMRPH_CORR");
            if (element6 != null) {
                this.ANAMRPH_CORR = Byte.parseByte(element6.getFirstChild().getNodeValue());
            }
            final Element element7 = this.getElement(element, "SCANBLK_NUM");
            if (element7 != null) {
                this.SCANBLK_NUM = Long.parseLong(element7.getFirstChild().getNodeValue());
            }
            final String[] array = { "_11", "_11", "_12", "_12", "_21", "_21", "_22", "_22" };
            for (int i = 0; i < 16; ++i) {
                Element element8;
                if (i < 8) {
                    if ((i + 2) % 2 == 0) {
                        element8 = this.getElement(element, "OP_ROW" + array[i]);
                    }
                    else {
                        element8 = this.getElement(element, "OP_COL" + array[i]);
                    }
                }
                else if ((i + 2) % 2 == 0) {
                    element8 = this.getElement(element, "FI_ROW" + array[i - 8]);
                }
                else {
                    element8 = this.getElement(element, "FI_COL" + array[i - 8]);
                }
                if (element8 != null) {
                    this.OPFI[i] = Double.parseDouble(element8.getFirstChild().getNodeValue());
                }
            }
            final Element element9 = this.getElement(element, "FI_ROW");
            if (element9 != null) {
                this.FI_ROW = Long.parseLong(element9.getFirstChild().getNodeValue());
            }
            final Element element10 = this.getElement(element, "FI_COL");
            if (element10 != null) {
                this.FI_COL = Long.parseLong(element10.getFirstChild().getNodeValue());
            }
            if (this.hasRPC() || this.isReady()) {
                this.hasICHIPB = true;
                this.calcOffsets();
            }
        }
    }
    
    private void extractRawRPCData(final Element element) {
        if (element != null) {
            final Element element2 = this.getElement(element, "CETAG");
            if (element2 != null) {
                this.CETAG = element2.getFirstChild().getNodeValue();
            }
            final Element element3 = this.getElement(element, "CEL");
            if (element3 != null) {
                this.CEL = Long.parseLong(element3.getFirstChild().getNodeValue());
            }
            final Element element4 = this.getElement(element, "SUCCESS");
            if (element4 != null && (this.SUCCESS = Byte.parseByte(element4.getFirstChild().getNodeValue())) != 1) {
                this.hasRPCs = false;
                return;
            }
            final Element element5 = this.getElement(element, "ERR_BIAS");
            if (element5 != null) {
                this.ERR_BIAS = Double.parseDouble(element5.getFirstChild().getNodeValue());
            }
            final Element element6 = this.getElement(element, "ERR_RAND");
            if (element6 != null) {
                this.ERR_RAND = Double.parseDouble(element6.getFirstChild().getNodeValue());
            }
            final String[] array = { "LINE_", "SAMP_", "LAT_", "LONG_", "HEIGHT_" };
            for (int i = 0; i < array.length; ++i) {
                final Element element7 = this.getElement(element, array[i] + "OFF");
                if (element7 != null) {
                    this.OFFSETS[i] = Double.parseDouble(element7.getFirstChild().getNodeValue());
                }
            }
            for (int j = 0; j < array.length; ++j) {
                final Element element8 = this.getElement(element, array[j] + "SCALE");
                if (element8 != null) {
                    this.SCALES[j] = Double.parseDouble(element8.getFirstChild().getNodeValue());
                }
            }
            for (int k = 0; k < 20; ++k) {
                final Element element9 = this.getElement(element, "LINE_NUM_COEFF_" + String.valueOf(k + 1));
                if (element9 != null) {
                    this.LNUMCOEF[k] = Double.parseDouble(element9.getFirstChild().getNodeValue());
                }
            }
            for (int l = 0; l < 20; ++l) {
                final Element element10 = this.getElement(element, "LINE_DEN_COEFF_" + String.valueOf(l + 1));
                if (element10 != null) {
                    this.LDENCOEF[l] = Double.parseDouble(element10.getFirstChild().getNodeValue());
                }
            }
            for (int n = 0; n < 20; ++n) {
                final Element element11 = this.getElement(element, "SAMP_NUM_COEFF_" + String.valueOf(n + 1));
                if (element11 != null) {
                    this.PNUMCOEF[n] = Double.parseDouble(element11.getFirstChild().getNodeValue());
                }
            }
            for (int n2 = 0; n2 < 20; ++n2) {
                final Element element12 = this.getElement(element, "SAMP_DEN_COEFF_" + String.valueOf(n2 + 1));
                if (element12 != null) {
                    this.PDENCOEF[n2] = Double.parseDouble(element12.getFirstChild().getNodeValue());
                }
            }
            this.rpcLastLat = this.OFFSETS[2];
            this.rpcLastLon = this.OFFSETS[3];
            this.hasRPCs = true;
        }
    }
    
    private double[] findPixel_linear_equation1(final double[] array, final double[] array2, final double[] array3) {
        final double n = array[0] * array2[1] - array[1] * array2[0];
        if (Math.abs(n) < 1.0E-24) {
            final double n2;
            return new double[] { n2, n2 = -1.0 };
        }
        return new double[] { (array3[0] * array2[1] - array3[1] * array[1]) / n, (array[0] * array3[1] - array2[0] * array3[0]) / n };
    }
    
    private String getCornerKey(final int n) {
        switch (n) {
            case 0: {
                return "UpperLeft";
            }
            case 1: {
                return "UpperRight";
            }
            case 2: {
                return "LowerRight";
            }
            case 3: {
                return "LowerLeft";
            }
            default: {
                throw new IllegalArgumentException("getCornerKey passed illegal index: " + n);
            }
        }
    }
    
    private Element getElement(final Element element, final String s) {
        final NodeList elementsByTagName = element.getElementsByTagName(s);
        if (elementsByTagName != null && elementsByTagName.getLength() > 0) {
            return (Element)elementsByTagName.item(0);
        }
        return null;
    }
    
    private Element getNitfElement(final Node node) {
        final NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            if (childNodes.item(i) instanceof Element) {
                final Element element = (Element)childNodes.item(i);
                if (element.getTagName().startsWith("NITF")) {
                    return element;
                }
            }
        }
        return null;
    }
    
    private void initializeUTMZone(final double[] array, final double[] array2, final double[] array3, final double[] array4) {
        if (this.isReady() && array != null && array2 != null && array3 != null && array4 != null) {
            int n = -1;
            int n2 = -1;
            int n3 = -1;
            int n4 = -1;
            int n5 = -1;
            final double[][][] array5 = { { array }, { array2 }, { array3 }, { array4 } };
            for (int i = 0; i < 4; ++i) {
                final double n6 = array5[i][0][0];
                final double n7 = array5[i][0][1];
                final double n8 = n6 * 3.141592653589793 / 180.0;
                if (n8 < 3.141592653589793) {
                    n5 = (int)(31.0 + n8 * 180.0 / 3.141592653589793 / 6.0);
                }
                else {
                    n5 = (int)(n8 * 180.0 / 3.141592653589793 / 6.0 - 29.0);
                }
                if (n5 > 60) {
                    n5 = 1;
                }
                if (n7 > 55.0 && n7 < 64.0 && n6 > -1.0 && n6 < 3.0) {
                    n5 = 31;
                }
                if (n7 > 55.0 && n7 < 64.0 && n6 > 2.0 && n6 < 12.0) {
                    n5 = 32;
                }
                if (n7 > 71.0 && n6 > -1.0 && n6 < 9.0) {
                    n5 = 31;
                }
                if (n7 > 71.0 && n6 > 8.0 && n6 < 21.0) {
                    n5 = 33;
                }
                if (n7 > 71.0 && n6 > 20.0 && n6 < 33.0) {
                    n5 = 35;
                }
                if (n7 > 71.0 && n6 > 32.0 && n6 < 42.0) {
                    n5 = 37;
                }
                switch (i) {
                    case 0: {
                        n = n5;
                        break;
                    }
                    case 1: {
                        n2 = n5;
                        break;
                    }
                    case 2: {
                        n3 = n5;
                        break;
                    }
                    case 3: {
                        n4 = n5;
                        break;
                    }
                }
            }
            if (n == n2 && n == n3 && n == n4 && n != -1) {
                this.utmZone = (byte)n5;
            }
        }
    }
    
    private void initInterpolation() {
        final double n = this.totalDims.getWidth() - 1.0;
        final double n2 = this.totalDims.getHeight() - 1.0;
        final int n3 = 0;
        final int n4 = 1;
        final double[] latLonEastNorth = this.getLatLonEastNorth("UpperLeft");
        final double[] latLonEastNorth2 = this.getLatLonEastNorth("UpperRight");
        final double[] latLonEastNorth3 = this.getLatLonEastNorth("LowerLeft");
        final double[] latLonEastNorth4 = this.getLatLonEastNorth("LowerRight");
        this.NSHemisphere = (char)((latLonEastNorth[1] > 0.0 || latLonEastNorth2[1] > 0.0 || latLonEastNorth3[1] > 0.0 || latLonEastNorth4[1] > 0.0) ? 78 : 83);
        this.initializeUTMZone(latLonEastNorth, latLonEastNorth2, latLonEastNorth3, latLonEastNorth4);
        this.calcKxKy(new Matrix(new double[][] { { latLonEastNorth[0] }, { latLonEastNorth2[0] }, { latLonEastNorth3[0] }, { latLonEastNorth4[0] } }), new Matrix(new double[][] { { latLonEastNorth[1] }, { latLonEastNorth2[1] }, { latLonEastNorth3[1] }, { latLonEastNorth4[1] } }), new Matrix(new double[][] { { 1.0 }, { n }, { 1.0 }, { n } }), new Matrix(new double[][] { { 1.0 }, { 1.0 }, { n2 }, { n2 } }));
        this.coefx[0] = this.kx[0][0];
        this.coefx[1] = this.kx[1][0];
        this.coefx[2] = this.kx[0][1];
        this.coefx[3] = this.kx[1][1];
        this.coefy[0] = this.ky[0][0];
        this.coefy[1] = this.ky[1][0];
        this.coefy[2] = this.ky[0][1];
        this.coefy[3] = this.ky[1][1];
        if (latLonEastNorth != null && latLonEastNorth2 != null && latLonEastNorth3 != null && latLonEastNorth4 != null) {
            this.geoInterp[n3][0] = (latLonEastNorth2[0] - latLonEastNorth[0]) / n;
            this.geoInterp[n3][1] = (latLonEastNorth3[0] - latLonEastNorth[0]) / n2;
            this.geoInterp[n3][2] = (latLonEastNorth4[0] - latLonEastNorth2[0] + latLonEastNorth[0] - latLonEastNorth3[0]) / (n * n2);
            this.geoInterp[n3][3] = latLonEastNorth[0];
            this.geoInterp[n4][0] = (latLonEastNorth2[1] - latLonEastNorth[1]) / n;
            this.geoInterp[n4][1] = (latLonEastNorth3[1] - latLonEastNorth[1]) / n2;
            this.geoInterp[n4][2] = (latLonEastNorth4[1] - latLonEastNorth2[1] + latLonEastNorth[1] - latLonEastNorth3[1]) / (n * n2);
            this.geoInterp[n4][3] = latLonEastNorth[1];
        }
        this.calcNorthRotation(latLonEastNorth, latLonEastNorth2, latLonEastNorth3, latLonEastNorth4);
        this.calcImageOrientation();
        if (this.hasRPC()) {
            final int n5 = this.totalDims.width / 2;
            final int n6 = this.totalDims.height / 2;
            final Gdc_Coord_3d rpcImagetoGeo = new RPC_Image_to_Geodetic().RPCImagetoGeo(n5, n6, this);
            if (rpcImagetoGeo.latitude == -9999.0 && rpcImagetoGeo.longitude == -9999.0) {
                final double[] bilinearGeoInterpolate = this.bilinearGeoInterpolate(n6, n5);
                rpcImagetoGeo.latitude = bilinearGeoInterpolate[1];
                rpcImagetoGeo.longitude = bilinearGeoInterpolate[0];
            }
            this.rotateUpIsUp = this.findRPCUpIsUp(rpcImagetoGeo.longitude, rpcImagetoGeo.latitude);
        }
    }
    
    private boolean isDegValid() {
        for (int n = 0; n < 4 && this.isDataValid; ++n) {
            final HashMap<Object, String> hashMap = this.degDataMap.get(this.getCornerKey(n));
            final String s = hashMap.get("Degree Latitude");
            final String s2 = hashMap.get("Degree Longitude");
            final String s3 = hashMap.get("Degree Hemisphere Lat");
            final String s4 = hashMap.get("Degree Hemisphere Lon");
            final boolean b = (s3.equals("+") || s3.equals("-")) && s.length() == 6;
            final boolean b2 = (s4.equals("+") || s4.equals("-")) && s2.length() == 7;
            this.isDataValid = (b && b2);
        }
        return this.isDataValid;
    }
    
    private boolean isGeoValid() {
        for (int n = 0; n < 4 && this.isDataValid; ++n) {
            final HashMap<Object, String> hashMap = this.geoDataMap.get(this.getCornerKey(n));
            final String s = hashMap.get("Latitude Degrees");
            final String s2 = hashMap.get("Latitude Minutes");
            final String s3 = hashMap.get("Latitude Seconds");
            final String s4 = hashMap.get("Latitude Bearing");
            final String s5 = hashMap.get("Longitude Degrees");
            final String s6 = hashMap.get("Longitude Minutes");
            final String s7 = hashMap.get("Longitude Seconds");
            final String s8 = hashMap.get("Longitude Bearing");
            try {
                final boolean b = Integer.parseInt(s) <= 360 && Integer.parseInt(s2) <= 360 && Integer.parseInt(s3) <= 360 && (s4.equals("N") || s4.equals("S"));
                final boolean b2 = Integer.parseInt(s5) <= 360 && Integer.parseInt(s6) <= 360 && Integer.parseInt(s7) <= 360 && (s8.equals("E") || s8.equals("W"));
                this.isDataValid = (b && b2);
            }
            catch (NumberFormatException ex) {
                this.isDataValid = false;
                NITFGeoUtils.log.warn("Invalid number format.", ex);
            }
        }
        return this.isDataValid;
    }
    
    private void parseDegFormatType() {
        this.degDataMap = new HashMap();
        this.cornerDataStrings = new HashMap();
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < 4; ++i) {
            final StringBuffer sb = new StringBuffer();
            final HashMap hashMap = new HashMap();
            sb.append(this.parseNext(hashMap, "Degree Hemisphere Lat", n2, ++n));
            final int n3 = n;
            final StringBuffer sb2 = sb;
            final HashMap hashMap2 = hashMap;
            final String s = "Degree Latitude";
            final int n4 = n3;
            n += 6;
            sb2.append(this.parseNext(hashMap2, s, n4, n));
            sb.append(this.parseNext(hashMap, "Degree Hemisphere Lon", n, ++n));
            final int n5 = n;
            final StringBuffer sb3 = sb;
            final HashMap hashMap3 = hashMap;
            final String s2 = "Degree Longitude";
            final int n6 = n5;
            n += 7;
            sb3.append(this.parseNext(hashMap3, s2, n6, n));
            n2 = n;
            this.cornerDataStrings.put(this.getCornerKey(i), sb.toString());
            this.degDataMap.put(this.getCornerKey(i), hashMap);
        }
        this.isDegValid();
    }
    
    private void parseGeoFormatType() {
        if (this.igeolo != null) {
            this.geoDataMap = new HashMap();
            this.cornerDataStrings = new HashMap();
            int n = 0;
            int n2 = 0;
            for (int i = 0; i < 4; ++i) {
                final StringBuffer sb = new StringBuffer();
                final HashMap hashMap = new HashMap();
                final StringBuffer sb2 = sb;
                final HashMap hashMap2 = hashMap;
                final String s = "Latitude Degrees";
                final int n3 = n2;
                n += 2;
                sb2.append(this.parseNext(hashMap2, s, n3, n)).append('°');
                final int n4 = n;
                final StringBuffer sb3 = sb;
                final HashMap hashMap3 = hashMap;
                final String s2 = "Latitude Minutes";
                final int n5 = n4;
                n += 2;
                sb3.append(this.parseNext(hashMap3, s2, n5, n)).append("'");
                final int n6 = n;
                final StringBuffer sb4 = sb;
                final HashMap hashMap4 = hashMap;
                final String s3 = "Latitude Seconds";
                final int n7 = n6;
                n += 2;
                sb4.append(this.parseNext(hashMap4, s3, n7, n)).append("\"");
                sb.append(this.parseNext(hashMap, "Latitude Bearing", n, ++n)).append(" ");
                final int n8 = n;
                final StringBuffer sb5 = sb;
                final HashMap hashMap5 = hashMap;
                final String s4 = "Longitude Degrees";
                final int n9 = n8;
                n += 3;
                sb5.append(this.parseNext(hashMap5, s4, n9, n)).append('°');
                final int n10 = n;
                final StringBuffer sb6 = sb;
                final HashMap hashMap6 = hashMap;
                final String s5 = "Longitude Minutes";
                final int n11 = n10;
                n += 2;
                sb6.append(this.parseNext(hashMap6, s5, n11, n)).append("'");
                final int n12 = n;
                final StringBuffer sb7 = sb;
                final HashMap hashMap7 = hashMap;
                final String s6 = "Longitude Seconds";
                final int n13 = n12;
                n += 2;
                sb7.append(this.parseNext(hashMap7, s6, n13, n)).append("\"");
                sb.append(this.parseNext(hashMap, "Longitude Bearing", n, ++n)).append("");
                n2 = n;
                this.cornerDataStrings.put(this.getCornerKey(i), sb.toString());
                this.geoDataMap.put(this.getCornerKey(i), hashMap);
            }
        }
        this.isGeoValid();
    }
    
    private String parseNext(final HashMap hashMap, final String s, final int n, final int n2) {
        final String substring = this.igeolo.substring(n, n2);
        hashMap.put(s, substring);
        return substring;
    }
    
    private void parseRPCGeoFormatType() {
        final double n = this.totalDims.getWidth() - 1.0;
        final double n2 = this.totalDims.getHeight() - 1.0;
        final double[][] array = { { n - n, n2 - n2 }, { n, n2 - n2 }, { n, n2 }, { n - n, n2 } };
        this.rpcDataMap = new HashMap();
        this.cornerDataStrings = new HashMap();
        for (int i = 0; i < 4; ++i) {
            final StringBuffer sb = new StringBuffer();
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            final double[] rpcLocation = this.rpcLocation((int)array[i][0], (int)array[i][1], this);
            final double abs = Math.abs(rpcLocation[0]);
            final double abs2 = Math.abs(rpcLocation[1]);
            final double n3 = abs % 1.0;
            final double n4 = abs2 % 1.0;
            final int n5 = (int)(n3 * 60.0);
            final int n6 = (int)(n4 * 60.0);
            final double n7 = Math.round(n3 * 60.0 % 1.0 * 60.0 * 100.0) / 100.0;
            final double n8 = Math.round(n4 * 60.0 % 1.0 * 60.0 * 100.0) / 100.0;
            final String string = Double.toString(abs);
            final String string2 = Double.toString(abs2);
            final String s = (rpcLocation[0] >= 0.0) ? "N" : "S";
            final String s2 = (rpcLocation[1] >= 0.0) ? "E" : "W";
            sb.append(string.substring(0, string.indexOf("."))).append('°');
            sb.append(Integer.toString(n5)).append("'");
            sb.append(Double.toString(n7)).append("\"");
            sb.append(s).append(" ");
            sb.append(string2.substring(0, string2.indexOf("."))).append('°');
            sb.append(Integer.toString(n6)).append("'");
            sb.append(Double.toString(n8)).append("\"");
            sb.append(s2).append(" ");
            this.cornerDataStrings.put(this.getCornerKey(i), sb.toString());
            hashMap.put("Degree Latitude", Double.toString(abs));
            hashMap.put("Degree Longitude", Double.toString(abs2));
            this.rpcDataMap.put(this.getCornerKey(i), hashMap);
        }
    }
    
    private void parseUTMFormatType() {
        if (this.igeolo != null) {
            this.utmDataMap = new HashMap();
            this.cornerDataStrings = new HashMap();
            int n = 0;
            int n2 = 0;
            if (this.icords == 'U') {
                for (int i = 0; i < 4; ++i) {
                    final StringBuffer sb = new StringBuffer();
                    final HashMap hashMap2;
                    final HashMap hashMap = hashMap2 = new HashMap();
                    final String s = "UTM Zone";
                    final int n3 = n2;
                    n += 2;
                    final String next = this.parseNext(hashMap2, s, n3, n);
                    sb.append(next);
                    final String next2 = this.parseNext(hashMap, "UTM Band", n, ++n);
                    sb.append(next2);
                    if (i == 0) {
                        this.imageProj.setZone(Integer.parseInt(next));
                        this.imageProj.setBand(next2.charAt(0));
                    }
                    sb.append(this.parseNext(hashMap, "UTM J", n, ++n));
                    sb.append(this.parseNext(hashMap, "UTM K", n, ++n));
                    final int n4 = n;
                    final StringBuffer sb2 = sb;
                    final HashMap hashMap3 = hashMap;
                    final String s2 = "UTM Easting";
                    final int n5 = n4;
                    n += 5;
                    sb2.append(this.parseNext(hashMap3, s2, n5, n));
                    final int n6 = n;
                    final StringBuffer sb3 = sb;
                    final HashMap hashMap4 = hashMap;
                    final String s3 = "UTM Northing";
                    final int n7 = n6;
                    n += 5;
                    sb3.append(this.parseNext(hashMap4, s3, n7, n));
                    n2 = n;
                    this.cornerDataStrings.put(this.getCornerKey(i), sb.toString());
                    this.utmDataMap.put(this.getCornerKey(i), hashMap);
                }
            }
            else if (this.icords == 'N' || this.icords == 'S') {
                for (int j = 0; j < 4; ++j) {
                    final StringBuffer sb4 = new StringBuffer();
                    final HashMap hashMap6;
                    final HashMap hashMap5 = hashMap6 = new HashMap();
                    final String s4 = "UTM Zone";
                    final int n8 = n2;
                    n += 2;
                    final String next3 = this.parseNext(hashMap6, s4, n8, n);
                    if (j == 0) {
                        this.imageProj.setZone(Integer.parseInt(next3));
                    }
                    final int n9 = n;
                    final StringBuffer sb5 = sb4;
                    final HashMap hashMap7 = hashMap5;
                    final String s5 = "UTM Easting";
                    final int n10 = n9;
                    n += 6;
                    sb5.append(this.parseNext(hashMap7, s5, n10, n)).append("E ");
                    n2 = n;
                    if (this.icords == 'N') {
                        final StringBuffer sb6 = sb4;
                        final HashMap hashMap8 = hashMap5;
                        final String s6 = "UTM Northing";
                        final int n11 = n2;
                        n += 7;
                        sb6.append(this.parseNext(hashMap8, s6, n11, n)).append("N ");
                        n2 = n;
                    }
                    else if (this.icords == 'S') {
                        final StringBuffer sb7 = sb4;
                        final HashMap hashMap9 = hashMap5;
                        final String s7 = "UTM Northing";
                        final int n12 = n2;
                        n += 7;
                        sb7.append(this.parseNext(hashMap9, s7, n12, n)).append("S ");
                        n2 = n;
                    }
                    this.cornerDataStrings.put(this.getCornerKey(j), sb4.toString());
                    this.utmDataMap.put(this.getCornerKey(j), hashMap5);
                }
            }
        }
    }
    
    private void processXML(final List list) {
        Node node = null;
        Node node2 = null;
        Node node3 = null;
        Node node4 = null;
        final Iterator<Document> iterator = list.iterator();
        while (iterator.hasNext()) {
            final Element documentElement = iterator.next().getDocumentElement();
            final String nodeName = documentElement.getNodeName();
            if (nodeName.equals("OriginalMetadata")) {
                node = documentElement;
            }
            else if (nodeName.equals("parsedFile")) {
                node2 = documentElement;
            }
            else {
                if (!nodeName.equals("GeoTIFF")) {
                    continue;
                }
                node3 = documentElement;
            }
        }
        if (node != null) {
            node4 = node;
        }
        else if (node2 != null) {
            node4 = node2;
        }
        else if (node3 != null) {
            node4 = node3;
        }
        if (node4 != null) {
            final Element nitfElement = this.getNitfElement(node4);
            final Element element = this.getElement(nitfElement, "Image_Segment");
            Element element2 = this.getElement(element, "RPC00A");
            if (element2 == null) {
                element2 = this.getElement(element, "RPC00B");
            }
            final Element element3 = this.getElement(element, "ICHIPB");
            this.extractRawData(element);
            this.extractRawRPCData(element2);
            this.extractRawICHIPBData(element3);
            if (this.icords == 'G') {
                this.imageProj = ViewerConst.GEOGRAPHIC_PROJECTION;
                this.parseGeoFormatType();
            }
            else if (this.icords == 'C') {
                this.imageProj = ViewerConst.GEOGRAPHIC_PROJECTION;
                this.parseGeoFormatType();
            }
            else if (this.icords == 'U') {
                this.imageProj = ViewerConst.MGRS_PROJECTION;
                this.parseUTMFormatType();
            }
            else if (this.icords == 'N' && !"NITF2_0".equals(nitfElement.getNodeName())) {
                (this.imageProj = ViewerConst.UTM_PROJECTION).setHemisphere(0);
                this.utmHemisphere = 'N';
                this.parseUTMFormatType();
            }
            else if (this.icords == 'S') {
                (this.imageProj = ViewerConst.UTM_PROJECTION).setHemisphere(1);
                this.utmHemisphere = 'S';
                this.parseUTMFormatType();
            }
            else if (this.icords == 'D') {
                this.imageProj = ViewerConst.GEOGRAPHIC_PROJECTION;
                this.parseDegFormatType();
            }
            else if (this.hasRPCs) {
                this.icords = ' ';
                this.imageProj = ViewerConst.GEOGRAPHIC_PROJECTION;
                this.parseRPCGeoFormatType();
            }
        }
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
        NITFGeoUtils.log = new Log((NITFGeoUtils.class$com$itt$J2KViewer$georvm$NITFGeoUtils == null) ? (NITFGeoUtils.class$com$itt$J2KViewer$georvm$NITFGeoUtils = class$("com.itt.J2KViewer.georvm.NITFGeoUtils")) : NITFGeoUtils.class$com$itt$J2KViewer$georvm$NITFGeoUtils);
    }
}
