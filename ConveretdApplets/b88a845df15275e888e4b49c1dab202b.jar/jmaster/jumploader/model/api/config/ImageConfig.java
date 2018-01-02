// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.api.config;

import java.util.MissingResourceException;
import jmaster.util.property.C;
import jmaster.jumploader.model.api.B;

public class ImageConfig
{
    private static final String C = "ImageConfig.properties";
    public static final int JPEG_QUALITY_DEFAULT = 800;
    private int B;
    private String F;
    private String E;
    private boolean D;
    private String A;
    
    public ImageConfig(final B b) {
        this.B = 800;
        this.F = null;
        this.E = null;
        this.D = true;
        this.A = "400x300;400;300;640x480;640;480;800x600;800;600;1024x768;1024;768;1200x800;1200;800";
        try {
            jmaster.util.property.C.A().A(this, jmaster.util.property.B.C().G("ImageConfig.properties"), null);
        }
        catch (MissingResourceException ex) {}
    }
    
    public String toString() {
        return "jpegQuality=" + this.B + "\r\n" + "cropPerimeterMin=" + this.F + "\r\n" + "cropRatio=" + this.E + "\r\n" + "respectExifOrientation=" + this.D + "\r\n" + "resizeOptions=" + this.A + "\r\n" + "";
    }
    
    public int getJpegQuality() {
        return this.B;
    }
    
    public void setJpegQuality(final int b) {
        this.B = b;
    }
    
    public String getCropPerimeterMin() {
        return this.F;
    }
    
    public void setCropPerimeterMin(final String f) {
        this.F = f;
    }
    
    public String getCropRatio() {
        return this.E;
    }
    
    public void setCropRatio(final String e) {
        this.E = e;
    }
    
    public boolean isRespectExifOrientation() {
        return this.D;
    }
    
    public void setRespectExifOrientation(final boolean d) {
        this.D = d;
    }
    
    public String getResizeOptions() {
        return this.A;
    }
    
    public void setResizeOptions(final String a) {
        this.A = a;
    }
}
