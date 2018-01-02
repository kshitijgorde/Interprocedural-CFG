// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter.output;

import java.util.Vector;
import java.awt.image.Raster;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.awt.image.SampleModel;
import java.util.Properties;
import ji.io.h;
import ji.util.i;
import java.io.IOException;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import ji.io.a8;
import ji.render.c1;
import java.awt.Rectangle;
import ji.v1event.af;
import ji.document.ad;
import ji.image.cy;
import ji.io.ac;
import ji.util.d;

public abstract class hd
{
    private String a;
    
    public hd() {
        try {
            ClassLoader.getSystemClassLoader().loadClass("javax.imageio.ImageIO");
        }
        catch (ClassNotFoundException ex) {
            if (d.em()) {
                throw new RuntimeException("MS JVM does not support this output filter.");
            }
            throw new RuntimeException("Java version does not support the ImageIO classes.");
        }
    }
    
    public String d() {
        return this.a;
    }
    
    public void a(final String a) {
        this.a = a;
    }
    
    public boolean a(final ac ac, final ac ac2, final String s, final String s2, final cy cy, final ad ad, final boolean b, final boolean b2, final int n, final String s3, final int n2, final int n3, final int n4, final int n5, final boolean b3, final int n6, final int n7, final af af, final boolean b4, final Object o, final boolean b5, final boolean b6, final int n8, final int n9) throws Exception {
        Image image = null;
        try {
            cy.ar();
            image = cy.a(false, false, null, false);
            if (image == null) {
                cy.b1();
                image = cy.a(false, false, null, false);
                cy.ar();
            }
            if (image == null) {
                this.a(this.a, "Problem saving: null image");
                return false;
            }
            if (n2 > 0 && n3 > 0) {
                if (image.getSource() instanceof c1) {
                    final c1 c1 = (c1)image.getSource();
                    final ym ym = new ym(c1);
                    c1.g();
                    try {
                        return this.a(ym, new BufferedOutputStream(new a8(ac, ad), 16384));
                    }
                    catch (OutOfMemoryError outOfMemoryError) {
                        this.a(this.a, String.valueOf(String.valueOf(new StringBuffer("Memory problem when saving ").append(n2).append("x").append(n3))));
                        return false;
                    }
                    finally {
                        c1.f();
                    }
                }
                BufferedImage bufferedImage = null;
                try {
                    bufferedImage = new BufferedImage(n2, n3, 1);
                    if (bufferedImage.getGraphics().drawImage(image, 0, 0, null)) {
                        return this.a(bufferedImage, new BufferedOutputStream(new a8(ac, ad), 16384));
                    }
                    this.a(this.a, "Problem saving: image not yet loaded");
                    return false;
                }
                catch (OutOfMemoryError outOfMemoryError2) {
                    this.a(this.a, String.valueOf(String.valueOf(new StringBuffer("Memory problem when saving ").append(n2).append("x").append(n3))));
                    return false;
                }
                finally {
                    if (bufferedImage != null) {
                        bufferedImage.flush();
                    }
                }
            }
            this.a(this.a, String.valueOf(String.valueOf(new StringBuffer("Image width or height is 0 ").append(n2).append("x").append(n3))));
            return false;
        }
        catch (Exception ex) {
            this.a(this.a, "Exception when generating rendered image: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
            return false;
        }
        finally {
            if (image != null) {
                image.flush();
            }
        }
    }
    
    public abstract boolean a(final RenderedImage p0, final OutputStream p1) throws IOException;
    
    private void a(final String s, final String s2) {
        if (i.c(5)) {
            h.d(s, s2);
        }
    }
    
    public void e() {
    }
    
    public boolean a(final ac ac, final af af, final ad ad, final String s, final boolean[] array) throws Exception {
        return true;
    }
    
    public boolean a(final int n, final boolean[] array) throws Exception {
        return true;
    }
    
    public void a(final Properties properties) {
    }
    
    private class ym implements RenderedImage
    {
        private c1 a;
        private SampleModel b;
        private WritableRaster c;
        private ColorModel d;
        
        public ym(final hd hd, final c1 a) {
            this.a = a;
            this.d = a.e();
            this.c = this.d.createCompatibleWritableRaster(a.a(), a.b());
            this.b = this.c.getSampleModel();
        }
        
        public WritableRaster copyData(final WritableRaster writableRaster) {
            return null;
        }
        
        public ColorModel getColorModel() {
            return this.d;
        }
        
        public Raster getData() {
            return null;
        }
        
        public Raster getData(final Rectangle rectangle) {
            this.c.setPixels(rectangle.x, rectangle.y, rectangle.width, rectangle.height, this.a.a(rectangle.x, rectangle.y, rectangle.width, rectangle.height));
            return this.c;
        }
        
        public int getHeight() {
            return this.a.b();
        }
        
        public int getMinTileX() {
            return 0;
        }
        
        public int getMinTileY() {
            return 0;
        }
        
        public int getMinX() {
            return 0;
        }
        
        public int getMinY() {
            return 0;
        }
        
        public int getNumXTiles() {
            return 0;
        }
        
        public int getNumYTiles() {
            return 0;
        }
        
        public Object getProperty(final String s) {
            return null;
        }
        
        public String[] getPropertyNames() {
            return null;
        }
        
        public SampleModel getSampleModel() {
            return this.b;
        }
        
        public Vector getSources() {
            return null;
        }
        
        public Raster getTile(final int n, final int n2) {
            return null;
        }
        
        public int getTileGridXOffset() {
            return 0;
        }
        
        public int getTileGridYOffset() {
            return 0;
        }
        
        public int getTileHeight() {
            return 0;
        }
        
        public int getTileWidth() {
            return 0;
        }
        
        public int getWidth() {
            return this.a.a();
        }
    }
}
