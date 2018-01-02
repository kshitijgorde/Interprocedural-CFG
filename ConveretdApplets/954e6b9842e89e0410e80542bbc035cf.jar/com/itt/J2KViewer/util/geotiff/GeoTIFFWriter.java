// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util.geotiff;

import java.io.IOException;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import com.itt.J2KViewer.georvm.coords.Utm_Coord_3d;
import java.awt.image.BufferedImage;
import com.itt.J2KViewer.util.Log;

public class GeoTIFFWriter
{
    private static Log log;
    private BufferedImage image;
    private int imageWidth;
    private int imageHeight;
    private short samplesPerPixel;
    private short[] bitsPerSample;
    private short compression;
    private short photometricInterpretation;
    private int rowsPerStrip;
    private int[] stripByteCounts;
    private int[] xResolution;
    private int[] yResolution;
    private short resolutionUnit;
    private double[] modelTransformation;
    private int zone;
    private boolean hemisphereNorth;
    static /* synthetic */ Class class$com$itt$J2KViewer$util$geotiff$GeoTIFFWriter;
    
    public GeoTIFFWriter(final BufferedImage image) {
        this.samplesPerPixel = 3;
        this.bitsPerSample = new short[] { 8, 8, 8 };
        this.compression = 1;
        this.photometricInterpretation = 2;
        this.stripByteCounts = new int[] { 0 };
        this.xResolution = new int[] { 1, 1 };
        this.yResolution = new int[] { 1, 1 };
        this.resolutionUnit = 1;
        this.modelTransformation = null;
        this.image = image;
        this.imageWidth = image.getWidth();
        this.imageHeight = image.getHeight();
        this.rowsPerStrip = this.imageHeight;
        this.stripByteCounts[0] = this.imageWidth * this.imageHeight * this.samplesPerPixel;
    }
    
    public void setTransformation(final Utm_Coord_3d utm_Coord_3d, final double n, final double n2, final double n3) {
        this.zone = utm_Coord_3d.zone;
        this.hemisphereNorth = utm_Coord_3d.hemisphere_north;
        (this.modelTransformation = new double[16])[0] = n2 * Math.cos(n);
        this.modelTransformation[1] = n2 * -Math.sin(n);
        this.modelTransformation[3] = utm_Coord_3d.x;
        this.modelTransformation[4] = -n3 * Math.sin(n);
        this.modelTransformation[5] = -n3 * Math.cos(n);
        this.modelTransformation[7] = utm_Coord_3d.y;
        this.modelTransformation[15] = 1.0;
    }
    
    public void setTransformation(final Utm_Coord_3d utm_Coord_3d, final double n, final double n2, final double n3, final int n4, final int n5) {
        this.zone = utm_Coord_3d.zone;
        this.hemisphereNorth = utm_Coord_3d.hemisphere_north;
        (this.modelTransformation = new double[16])[0] = n2 * Math.cos(n);
        this.modelTransformation[1] = n2 * -Math.sin(n);
        this.modelTransformation[3] = utm_Coord_3d.x - n4 * this.modelTransformation[0] - n5 * this.modelTransformation[1];
        this.modelTransformation[4] = -n3 * Math.sin(n);
        this.modelTransformation[5] = -n3 * Math.cos(n);
        this.modelTransformation[7] = utm_Coord_3d.y - n4 * this.modelTransformation[4] - n5 * this.modelTransformation[5];
        this.modelTransformation[15] = 1.0;
    }
    
    public boolean write(final OutputStream outputStream) throws IOException {
        if (this.image == null) {
            GeoTIFFWriter.log.error("Unable to write GeoTIFF file because image is null.");
            return false;
        }
        if (this.modelTransformation == null) {
            GeoTIFFWriter.log.error("Unable to write GeoTIFF file because ModelTransformation is null.");
            return false;
        }
        short n;
        if (this.hemisphereNorth) {
            n = (short)(32600 + this.zone);
        }
        else {
            n = (short)(32700 + this.zone);
        }
        final short[] array = { 1, 1, 0, 3, 1024, 0, 1, 1, 1025, 0, 1, 1, 3072, 0, 1, n };
        final int n2 = 14;
        final int n3 = 8;
        final int n4 = n3 + 2 + 12 * n2 + 4;
        final short n5 = (short)(n4 + this.samplesPerPixel * 2);
        final short n6 = (short)(n5 + 8);
        final short n7 = (short)(n6 + 8);
        final int n8 = n7 + 8 * this.modelTransformation.length;
        final int[] array2 = { n8 + 2 * array.length };
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        final DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
        dataOutputStream.write(GeoTIFFConstants.BIG_ENDIAN);
        dataOutputStream.writeShort(42);
        dataOutputStream.writeInt(n3);
        dataOutputStream.writeShort(n2);
        dataOutputStream.writeShort(256);
        dataOutputStream.writeShort(4);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeInt(this.imageWidth);
        dataOutputStream.writeShort(257);
        dataOutputStream.writeShort(4);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeInt(this.imageHeight);
        dataOutputStream.writeShort(258);
        dataOutputStream.writeShort(3);
        dataOutputStream.writeInt(this.samplesPerPixel);
        dataOutputStream.writeInt(n4);
        dataOutputStream.writeShort(259);
        dataOutputStream.writeShort(3);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeShort(this.compression);
        dataOutputStream.writeShort(0);
        dataOutputStream.writeShort(262);
        dataOutputStream.writeShort(3);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeShort(this.photometricInterpretation);
        dataOutputStream.writeShort(0);
        dataOutputStream.writeShort(273);
        dataOutputStream.writeShort(4);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeInt(array2[0]);
        dataOutputStream.writeShort(277);
        dataOutputStream.writeShort(3);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeShort(this.samplesPerPixel);
        dataOutputStream.writeShort(0);
        dataOutputStream.writeShort(278);
        dataOutputStream.writeShort(4);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeInt(this.rowsPerStrip);
        dataOutputStream.writeShort(279);
        dataOutputStream.writeShort(4);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeInt(this.stripByteCounts[0]);
        dataOutputStream.writeShort(282);
        dataOutputStream.writeShort(5);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeInt(n5);
        dataOutputStream.writeShort(283);
        dataOutputStream.writeShort(5);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeInt(n6);
        dataOutputStream.writeShort(296);
        dataOutputStream.writeShort(3);
        dataOutputStream.writeInt(1);
        dataOutputStream.writeShort(this.resolutionUnit);
        dataOutputStream.writeShort(0);
        dataOutputStream.writeShort(-31272);
        dataOutputStream.writeShort(12);
        dataOutputStream.writeInt(this.modelTransformation.length);
        dataOutputStream.writeInt(n7);
        dataOutputStream.writeShort(-30801);
        dataOutputStream.writeShort(3);
        dataOutputStream.writeInt(array.length);
        dataOutputStream.writeInt(n8);
        dataOutputStream.writeInt(0);
        for (short n9 = 0; n9 < this.samplesPerPixel; ++n9) {
            dataOutputStream.writeShort(this.bitsPerSample[n9]);
        }
        dataOutputStream.writeInt(this.xResolution[0]);
        dataOutputStream.writeInt(this.xResolution[1]);
        dataOutputStream.writeInt(this.yResolution[0]);
        dataOutputStream.writeInt(this.yResolution[1]);
        for (int i = 0; i < this.modelTransformation.length; ++i) {
            dataOutputStream.writeDouble(this.modelTransformation[i]);
        }
        for (int j = 0; j < array.length; ++j) {
            dataOutputStream.writeShort(array[j]);
        }
        for (int k = 0; k < this.imageHeight; ++k) {
            for (int l = 0; l < this.imageWidth; ++l) {
                final int rgb = this.image.getRGB(l, k);
                final byte b = (byte)((rgb & 0xFF0000) >> 16);
                final byte b2 = (byte)((rgb & 0xFF00) >> 8);
                final byte b3 = (byte)(rgb & 0xFF);
                dataOutputStream.writeByte(b);
                dataOutputStream.writeByte(b2);
                dataOutputStream.writeByte(b3);
            }
        }
        dataOutputStream.close();
        bufferedOutputStream.close();
        return true;
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
        GeoTIFFWriter.log = new Log((GeoTIFFWriter.class$com$itt$J2KViewer$util$geotiff$GeoTIFFWriter == null) ? (GeoTIFFWriter.class$com$itt$J2KViewer$util$geotiff$GeoTIFFWriter = class$("com.itt.J2KViewer.util.geotiff.GeoTIFFWriter")) : GeoTIFFWriter.class$com$itt$J2KViewer$util$geotiff$GeoTIFFWriter);
    }
}
