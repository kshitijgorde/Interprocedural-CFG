// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util.geotiff;

import com.itt.J2KViewer.georvm.transforms.Utm_To_Gdc_Converter;
import com.itt.J2KViewer.georvm.coords.Gdc_Coord_3d;
import com.itt.J2KViewer.georvm.transforms.Geodetic_to_UTM_Converter;
import com.itt.J2KViewer.georvm.coords.Utm_Coord_3d;
import com.itt.J2KViewer.util.Log;

public class GeoTIFFReader
{
    private static Log log;
    private Utm_Coord_3d ulCoord3d;
    private Utm_Coord_3d urCoord3d;
    private Utm_Coord_3d lrCoord3d;
    private Utm_Coord_3d llCoord3d;
    private byte[] dataBuffer;
    private boolean bigEndian;
    private int modelType;
    private int zone;
    private boolean hemisphereNorth;
    private double[] modelTransformation;
    private double[] modelTiePoint;
    private double[] modelPixelScale;
    static /* synthetic */ Class class$com$itt$J2KViewer$util$geotiff$GeoTIFFReader;
    
    public GeoTIFFReader() {
        this.bigEndian = false;
        this.modelTransformation = null;
        this.modelTiePoint = null;
        this.modelPixelScale = null;
    }
    
    public void read(final byte[] dataBuffer, final int n, final int n2, final int n3) {
        final double[] array = new double[2];
        final double[] array2 = new double[2];
        final double[] array3 = new double[2];
        final double[] array4 = new double[2];
        this.dataBuffer = dataBuffer;
        int n4 = n;
        if (dataBuffer[n4] == GeoTIFFConstants.BIG_ENDIAN[0] && dataBuffer[n4 + 1] == GeoTIFFConstants.BIG_ENDIAN[1]) {
            this.bigEndian = true;
        }
        else if (dataBuffer[n4] != GeoTIFFConstants.LITTLE_ENDIAN[0] || dataBuffer[n4 + 1] != GeoTIFFConstants.LITTLE_ENDIAN[1]) {
            GeoTIFFReader.log.warn("Invalid byte order in GeoTIFF data: '" + dataBuffer[n4] + dataBuffer[n4 + 1] + "'. GeoTIFF information is being ignored.");
            return;
        }
        n4 += 2;
        final short tiffShort = this.getTiffShort(n4);
        if (tiffShort != 42) {
            GeoTIFFReader.log.warn("Invalid 'magic number' in GeoTIFF data: '" + tiffShort + "'. GeoTIFF information is being ignored.");
            return;
        }
        n4 += 2;
        int n5;
        for (int i = this.getTiffLong(n4); i != 0; i = this.getTiffLong(n5)) {
            n5 = n + i;
            final short tiffShort2 = this.getTiffShort(n5);
            n5 += 2;
            for (short n6 = 0; n6 < tiffShort2; ++n6) {
                final short tiffShort3 = this.getTiffShort(n5);
                n5 += 2;
                n5 += 2;
                final int tiffLong = this.getTiffLong(n5);
                n5 += 4;
                int tiffLong2 = this.getTiffLong(n5);
                n5 += 4;
                switch (tiffShort3) {
                    case -31986: {
                        this.modelPixelScale = new double[3];
                        for (int j = 0; j < this.modelPixelScale.length; ++j) {
                            this.modelPixelScale[j] = this.getTiffDouble(n + tiffLong2);
                            tiffLong2 += 8;
                        }
                        break;
                    }
                    case -31614: {
                        this.modelTiePoint = new double[6];
                        for (int k = 0; k < this.modelTiePoint.length; ++k) {
                            this.modelTiePoint[k] = this.getTiffDouble(n + tiffLong2);
                            tiffLong2 += 8;
                        }
                        break;
                    }
                    case -31272: {
                        this.modelTransformation = new double[16];
                        for (int l = 0; l < tiffLong; ++l) {
                            this.modelTransformation[l] = this.getTiffDouble(n + tiffLong2);
                            tiffLong2 += 8;
                        }
                        break;
                    }
                    case -30801: {
                        tiffLong2 += 6;
                        final short tiffShort4 = this.getTiffShort(n + tiffLong2);
                        tiffLong2 += 2;
                        this.readGeoKeys(tiffShort4, n, tiffLong2);
                        break;
                    }
                }
            }
        }
        final double n7 = n2;
        final double n8 = n3;
        if (this.modelTiePoint != null && this.modelPixelScale != null) {
            array[0] = this.modelTiePoint[3] + this.modelPixelScale[0] * -this.modelTiePoint[0];
            array[1] = this.modelTiePoint[4] - this.modelPixelScale[1] * -this.modelTiePoint[1];
            array2[0] = this.modelTiePoint[3] + this.modelPixelScale[0] * (n7 - this.modelTiePoint[0]);
            array2[1] = this.modelTiePoint[4] - this.modelPixelScale[1] * -this.modelTiePoint[1];
            array4[0] = this.modelTiePoint[3] + this.modelPixelScale[0] * (n7 - this.modelTiePoint[0]);
            array4[1] = this.modelTiePoint[4] - this.modelPixelScale[1] * (n8 - this.modelTiePoint[1]);
            array3[0] = this.modelTiePoint[3] + this.modelPixelScale[0] * -this.modelTiePoint[0];
            array3[1] = this.modelTiePoint[4] - this.modelPixelScale[1] * (n8 - this.modelTiePoint[1]);
        }
        else if (this.modelTransformation != null) {
            array[0] = this.modelTransformation[3];
            array[1] = this.modelTransformation[7];
            array2[0] = n7 * this.modelTransformation[0] + this.modelTransformation[3];
            array2[1] = n7 * this.modelTransformation[4] + this.modelTransformation[7];
            array4[0] = n7 * this.modelTransformation[0] + n8 * this.modelTransformation[1] + this.modelTransformation[3];
            array4[1] = n7 * this.modelTransformation[4] + n8 * this.modelTransformation[5] + this.modelTransformation[7];
            array3[0] = n8 * this.modelTransformation[1] + this.modelTransformation[3];
            array3[1] = n8 * this.modelTransformation[5] + this.modelTransformation[7];
        }
        if (this.modelType == 2) {
            final Geodetic_to_UTM_Converter geodetic_to_UTM_Converter = new Geodetic_to_UTM_Converter();
            this.ulCoord3d = geodetic_to_UTM_Converter.Convert_Geodetic_To_UTM(3.141592653589793 * array[1] / 180.0, 3.141592653589793 * array[0] / 180.0);
            this.urCoord3d = geodetic_to_UTM_Converter.Convert_Geodetic_To_UTM(3.141592653589793 * array2[1] / 180.0, 3.141592653589793 * array2[0] / 180.0);
            this.lrCoord3d = geodetic_to_UTM_Converter.Convert_Geodetic_To_UTM(3.141592653589793 * array4[1] / 180.0, 3.141592653589793 * array4[0] / 180.0);
            this.llCoord3d = geodetic_to_UTM_Converter.Convert_Geodetic_To_UTM(3.141592653589793 * array3[1] / 180.0, 3.141592653589793 * array3[0] / 180.0);
        }
        else if (this.zone > 0) {
            this.ulCoord3d = new Utm_Coord_3d(array[0], array[1], 0.0, (byte)this.zone, this.hemisphereNorth);
            this.urCoord3d = new Utm_Coord_3d(array2[0], array2[1], 0.0, (byte)this.zone, this.hemisphereNorth);
            this.lrCoord3d = new Utm_Coord_3d(array4[0], array4[1], 0.0, (byte)this.zone, this.hemisphereNorth);
            this.llCoord3d = new Utm_Coord_3d(array3[0], array3[1], 0.0, (byte)this.zone, this.hemisphereNorth);
            final Geodetic_to_UTM_Converter geodetic_to_UTM_Converter2 = new Geodetic_to_UTM_Converter();
            final Gdc_Coord_3d gdc_Coord_3d = new Gdc_Coord_3d();
            Utm_To_Gdc_Converter.Init();
            Utm_To_Gdc_Converter.Convert(this.ulCoord3d, gdc_Coord_3d);
            this.ulCoord3d = geodetic_to_UTM_Converter2.Convert_Geodetic_To_UTM(3.141592653589793 * gdc_Coord_3d.latitude / 180.0, 3.141592653589793 * gdc_Coord_3d.longitude / 180.0);
            Utm_To_Gdc_Converter.Convert(this.urCoord3d, gdc_Coord_3d);
            this.urCoord3d = geodetic_to_UTM_Converter2.Convert_Geodetic_To_UTM(3.141592653589793 * gdc_Coord_3d.latitude / 180.0, 3.141592653589793 * gdc_Coord_3d.longitude / 180.0);
            Utm_To_Gdc_Converter.Convert(this.lrCoord3d, gdc_Coord_3d);
            this.lrCoord3d = geodetic_to_UTM_Converter2.Convert_Geodetic_To_UTM(3.141592653589793 * gdc_Coord_3d.latitude / 180.0, 3.141592653589793 * gdc_Coord_3d.longitude / 180.0);
            Utm_To_Gdc_Converter.Convert(this.llCoord3d, gdc_Coord_3d);
            this.llCoord3d = geodetic_to_UTM_Converter2.Convert_Geodetic_To_UTM(3.141592653589793 * gdc_Coord_3d.latitude / 180.0, 3.141592653589793 * gdc_Coord_3d.longitude / 180.0);
        }
    }
    
    public Utm_Coord_3d[] getCorners() {
        if (this.ulCoord3d != null && this.urCoord3d != null && this.lrCoord3d != null && this.llCoord3d != null) {
            return new Utm_Coord_3d[] { this.ulCoord3d, this.urCoord3d, this.lrCoord3d, this.llCoord3d };
        }
        return null;
    }
    
    private void readGeoKeys(final int n, final int n2, int n3) {
        for (int i = 0; i < n; ++i) {
            final short tiffShort = this.getTiffShort(n2 + n3);
            n3 += 2;
            n3 += 2;
            n3 += 2;
            final short tiffShort2 = this.getTiffShort(n2 + n3);
            n3 += 2;
            switch (tiffShort) {
                case 1024: {
                    this.modelType = tiffShort2;
                    if (this.modelType != 1 && this.modelType != 2) {
                        GeoTIFFReader.log.warn("Unsupported model type: '" + this.modelType + "'. GeoTIFF information is being ignored.");
                        return;
                    }
                    break;
                }
                case 3072: {
                    if (this.modelType == 1) {
                        final short n4 = tiffShort2;
                        if (n4 > 32600 && n4 <= 32660) {
                            this.zone = n4 - 32600;
                            this.hemisphereNorth = true;
                        }
                        else {
                            if (n4 <= 32700 || n4 > 32760) {
                                GeoTIFFReader.log.warn("Unsupported GeoTIFF projection code: '" + n4 + "'. GeoTIFF information is being ignored.");
                                return;
                            }
                            this.zone = n4 - 32700;
                            this.hemisphereNorth = false;
                        }
                        break;
                    }
                    break;
                }
            }
        }
    }
    
    private short getTiffShort(final int n) {
        short n2;
        if (this.bigEndian) {
            n2 = (short)((0xFF & this.dataBuffer[n]) << 8 | (0xFF & this.dataBuffer[n + 1]));
        }
        else {
            n2 = (short)((0xFF & this.dataBuffer[n + 1]) << 8 | (0xFF & this.dataBuffer[n]));
        }
        return n2;
    }
    
    private int getTiffLong(final int n) {
        int n2;
        if (this.bigEndian) {
            n2 = ((0xFF & this.dataBuffer[n]) << 24 | (0xFF & this.dataBuffer[n + 1]) << 16 | (0xFF & this.dataBuffer[n + 2]) << 8 | (0xFF & this.dataBuffer[n + 3]));
        }
        else {
            n2 = ((0xFF & this.dataBuffer[n + 3]) << 24 | (0xFF & this.dataBuffer[n + 2]) << 16 | (0xFF & this.dataBuffer[n + 1]) << 8 | (0xFF & this.dataBuffer[n]));
        }
        return n2;
    }
    
    private double getTiffDouble(final int n) {
        double n2;
        if (this.bigEndian) {
            n2 = Double.longBitsToDouble((0xFF & this.dataBuffer[n]) << 56 | (0xFF & this.dataBuffer[n + 1]) << 48 | (0xFF & this.dataBuffer[n + 2]) << 40 | (0xFF & this.dataBuffer[n + 3]) << 32 | (0xFF & this.dataBuffer[n + 4]) << 24 | (0xFF & this.dataBuffer[n + 5]) << 16 | (0xFF & this.dataBuffer[n + 6]) << 8 | (0xFF & this.dataBuffer[n + 7]));
        }
        else {
            n2 = Double.longBitsToDouble((0xFF & this.dataBuffer[n + 7]) << 56 | (0xFF & this.dataBuffer[n + 6]) << 48 | (0xFF & this.dataBuffer[n + 5]) << 40 | (0xFF & this.dataBuffer[n + 4]) << 32 | (0xFF & this.dataBuffer[n + 3]) << 24 | (0xFF & this.dataBuffer[n + 2]) << 16 | (0xFF & this.dataBuffer[n + 1]) << 8 | (0xFF & this.dataBuffer[n]));
        }
        return n2;
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
        GeoTIFFReader.log = new Log((GeoTIFFReader.class$com$itt$J2KViewer$util$geotiff$GeoTIFFReader == null) ? (GeoTIFFReader.class$com$itt$J2KViewer$util$geotiff$GeoTIFFReader = class$("com.itt.J2KViewer.util.geotiff.GeoTIFFReader")) : GeoTIFFReader.class$com$itt$J2KViewer$util$geotiff$GeoTIFFReader);
    }
}
