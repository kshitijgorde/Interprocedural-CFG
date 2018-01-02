// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.image;

import javax.swing.ImageIcon;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.RenderingHints;
import java.awt.image.AffineTransformOp;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import z.A.A.A.C;
import java.io.InputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.io.ByteArrayInputStream;
import java.awt.image.RenderedImage;
import z.B.t;
import java.awt.color.ColorSpace;
import javax.imageio.ImageReadParam;
import java.util.Iterator;
import javax.imageio.stream.ImageInputStream;
import z.A.A.B.A.D;
import z.A.A.A.B.g;
import java.util.Hashtable;
import java.awt.Point;
import java.awt.image.Raster;
import z.B.J;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageReader;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Dimension;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.image.ImageObserver;
import jmaster.jumploader.model.api.B.A;

public class B implements A, ImageObserver
{
    public static final String[] R;
    private static final int Q = 3;
    private static final int S = 6;
    private static final int O = 8;
    protected jmaster.util.log.A P;
    protected jmaster.jumploader.model.api.B N;
    static /* synthetic */ Class class$z$A$A$A$B$g;
    
    public B(final jmaster.jumploader.model.api.B n) {
        this.P = jmaster.util.log.B.getInstance().getLog(this.getClass());
        this.N = n;
    }
    
    public String B(final String s) {
        String s2;
        if ("jpg".equalsIgnoreCase(s) || "jpe".equalsIgnoreCase(s) || "jpeg".equalsIgnoreCase(s)) {
            s2 = "jpeg";
        }
        else if ("png".equalsIgnoreCase(s)) {
            s2 = "png";
        }
        else if ("tif".equalsIgnoreCase(s) || "tiff".equalsIgnoreCase(s)) {
            s2 = "tiff";
        }
        else if ("bmp".equalsIgnoreCase(s)) {
            s2 = "bmp";
        }
        else if ("gif".equalsIgnoreCase(s)) {
            s2 = "gif";
        }
        else {
            s2 = s;
        }
        return s2;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return (n & 0x20) == 0x0;
    }
    
    public BufferedImage D(final Image image) {
        return jmaster.util.D.A.B(image, this);
    }
    
    public BufferedImage B(final Image image) {
        return jmaster.util.D.A.A(image, this);
    }
    
    public boolean B(final File file) {
        boolean equalsIgnoreCase = false;
        final String c = jmaster.util.F.A.C(file);
        for (int n = 0; c != null && !equalsIgnoreCase && n < B.R.length; equalsIgnoreCase = B.R[n].equalsIgnoreCase(c), ++n) {}
        return equalsIgnoreCase;
    }
    
    public Image C(final File file) throws IOException {
        return this.A(file, null, null);
    }
    
    public Image A(final File file, final Integer n) throws IOException {
        return this.A(file, n, null);
    }
    
    public Image A(final File file, final Dimension dimension) throws IOException {
        return this.A(file, null, dimension);
    }
    
    public Image A(final File file, final Integer n, final Dimension dimension) throws IOException {
        Image image = null;
        Object o = null;
        ImageReader imageReader = null;
        try {
            o = new FileInputStream(file);
            final ImageInputStream imageInputStream = ImageIO.createImageInputStream(o);
            final Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(imageInputStream);
            int n2 = 1;
            ImageReadParam defaultReadParam = null;
            while (n2 != 0 && imageReaders.hasNext()) {
                imageReader = imageReaders.next();
                imageReader.setInput(imageInputStream);
                defaultReadParam = imageReader.getDefaultReadParam();
                for (Iterator<ImageTypeSpecifier> imageTypes = imageReader.getImageTypes(0); n2 != 0 && imageTypes.hasNext(); n2 = 0) {
                    final ImageTypeSpecifier destinationType = imageTypes.next();
                    final ColorSpace colorSpace = destinationType.getColorModel().getColorSpace();
                    if (colorSpace.isCS_sRGB()) {
                        defaultReadParam.setDestinationType(destinationType);
                        n2 = 0;
                    }
                    if (colorSpace.getType() != 5) {}
                }
            }
            if (imageReader != null) {
                if (dimension != null) {
                    dimension.width = imageReader.getWidth(0);
                    dimension.height = imageReader.getHeight(0);
                }
                if (n != null) {
                    defaultReadParam.setSourceSubsampling(n, n, 0, 0);
                }
                else if (this.N != null && this.N.B() != null && this.N.B().getImageSubsamplingFactor() > 0) {
                    final int round = Math.round(imageReader.getWidth(0) * imageReader.getHeight(0) / 1000000.0f);
                    final int n3 = 1 + round / this.N.B().getImageSubsamplingFactor();
                    if (n3 > 1) {
                        this.P.D("About to use subsampling " + n3 + " for loading image from " + file.getAbsolutePath() + " (" + round + " MPX)");
                        defaultReadParam.setSourceSubsampling(n3, n3, 0, 0);
                        image = imageReader.read(0, defaultReadParam);
                    }
                }
                image = imageReader.read(0, defaultReadParam);
            }
            if (image == null) {
                final t a = z.B.J.A(this.B(jmaster.util.F.A.C(file)), file, null);
                if (a == null) {
                    throw new IOException("Cannot read image from file " + file.getAbsolutePath());
                }
                final RenderedImage d = a.D();
                if (d != null) {
                    final Raster data = d.getData();
                    image = new BufferedImage(d.getColorModel(), Raster.createWritableRaster(data.getSampleModel(), data.getDataBuffer(), null), false, null);
                }
            }
        }
        finally {
            try {
                imageReader.dispose();
            }
            catch (Exception ex2) {}
            try {
                ((InputStream)o).close();
            }
            catch (Exception ex3) {}
        }
        if (this.N != null) {
            if (!this.N.J().isRespectExifOrientation()) {
                return image;
            }
        }
        try {
            final g g = (g)z.A.A.B.A.D.A(file).B((B.class$z$A$A$A$B$g == null) ? (B.class$z$A$A$A$B$g = class$("z.A.A.A.B.g")) : B.class$z$A$A$A$B$g);
            if (g.N(274)) {
                switch (g.I(274)) {
                    case 3: {
                        image = this.C(image);
                        break;
                    }
                    case 6: {
                        image = this.A(image);
                        break;
                    }
                    case 8: {
                        image = this.F(image);
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {
            if (!(ex instanceof z.A.A.B.A.B)) {
                ex.printStackTrace();
            }
        }
        return image;
    }
    
    public Image A(final File file, final int n, final int n2, final String s) throws IOException {
        Image image = null;
        final String c = jmaster.util.F.A.C(file);
        try {
            if ("jpg".equalsIgnoreCase(c) || "jpeg".equalsIgnoreCase(c)) {
                image = this.B(file, n, n2, s);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        if (image == null) {
            try {
                image = this.C(file, n, n2, s);
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        return this.D(image);
    }
    
    private Image B(final File file, final int n, final int n2, final String s) throws z.A.A.B.A.B, C, IOException {
        Image a = null;
        final g g = (g)z.A.A.B.A.D.A(file).B((B.class$z$A$A$A$B$g == null) ? (B.class$z$A$A$A$B$g = class$("z.A.A.A.B.g")) : B.class$z$A$A$A$B$g);
        final byte[] i = g.I();
        if (i != null) {
            final ImageReader imageReader = ImageIO.getImageReadersBySuffix("jpg").next();
            imageReader.setInput(new MemoryCacheImageInputStream(new ByteArrayInputStream(i)));
            BufferedImage bufferedImage = this.D(imageReader.read(0));
            if (this.N == null || this.N.J().isRespectExifOrientation()) {
                switch (g.I(274)) {
                    case 3: {
                        bufferedImage = this.D(this.C(bufferedImage));
                        break;
                    }
                    case 6: {
                        bufferedImage = this.D(this.A(bufferedImage));
                        break;
                    }
                    case 8: {
                        bufferedImage = this.D(this.F(bufferedImage));
                        break;
                    }
                }
            }
            a = this.A(bufferedImage, n, n2, s, true);
            final float n3 = a.getWidth(null) / a.getHeight(null);
            final float aspectRatio = imageReader.getAspectRatio(0);
            if (Math.min(n3, aspectRatio) < 1.0f && Math.max(n3, aspectRatio) > 1.0f) {
                a = null;
            }
            imageReader.dispose();
        }
        return a;
    }
    
    public Image C(final File file, final int n, final int n2, final String s) throws IOException {
        Image image = null;
        Object o = null;
        ImageReader imageReader = null;
        try {
            o = new FileInputStream(file);
            final ImageInputStream imageInputStream = ImageIO.createImageInputStream(o);
            final Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(imageInputStream);
            if (imageReaders != null && imageReaders.hasNext()) {
                imageReader = imageReaders.next();
                imageReader.setInput(imageInputStream);
                final int n3 = (int)Math.floor(Math.max(1.0, Math.max(imageReader.getWidth(0) / n, imageReader.getHeight(0) / n2)));
                final ImageReadParam defaultReadParam = imageReader.getDefaultReadParam();
                defaultReadParam.setSourceSubsampling(n3, n3, 0, 0);
                final BufferedImage read = imageReader.read(0, defaultReadParam);
                image = this.A(read, n, n2, s, true);
                if (image == null) {
                    image = read;
                }
            }
            if (image == null) {
                final t a = z.B.J.A(this.B(jmaster.util.F.A.C(file)), file, null);
                if (a == null) {
                    throw new IOException("Cannot read image from file " + file.getAbsolutePath());
                }
                final RenderedImage d = a.D();
                if (d != null) {
                    final Raster data = d.getData();
                    image = this.A(new BufferedImage(d.getColorModel(), Raster.createWritableRaster(data.getSampleModel(), data.getDataBuffer(), null), false, null), n, n2, s, true);
                }
            }
            Label_0447: {
                if (image != null) {
                    if (this.N != null) {
                        if (!this.N.J().isRespectExifOrientation()) {
                            break Label_0447;
                        }
                    }
                    try {
                        switch (((g)z.A.A.B.A.D.A(file).B((B.class$z$A$A$A$B$g == null) ? (B.class$z$A$A$A$B$g = class$("z.A.A.A.B.g")) : B.class$z$A$A$A$B$g)).I(274)) {
                            case 3: {
                                image = this.C(image);
                                break;
                            }
                            case 6: {
                                image = this.A(image);
                                break;
                            }
                            case 8: {
                                image = this.F(image);
                                break;
                            }
                        }
                    }
                    catch (Exception ex) {}
                }
                try {}
                catch (Exception ex2) {}
            }
        }
        finally {
            try {
                imageReader.dispose();
            }
            catch (Exception ex3) {}
            try {
                ((InputStream)o).close();
            }
            catch (Exception ex4) {}
        }
        return image;
    }
    
    public File A(final Image image, String string, final double n) throws IOException {
        final BufferedImage d = this.D(image);
        if (string == null) {
            string = "";
        }
        while (string.length() < 3) {
            string += "x";
        }
        final File tempFile = File.createTempFile(string, ".jpg");
        tempFile.deleteOnExit();
        jmaster.util.D.A.A(d, n, tempFile);
        return tempFile;
    }
    
    public File A(final Image image, final File file, final int n) throws IOException {
        jmaster.util.D.A.A(this.D(image), n / 1000.0, file);
        return file;
    }
    
    protected int A(final String s) {
        int n = 1;
        if ("smooth".equals(s)) {
            n = 4;
        }
        else if ("fast".equals(s)) {
            n = 2;
        }
        return n;
    }
    
    public Image C(final Image image, final int n, final int n2, String s, final boolean b) {
        if (!b && (image.getWidth(null) < n || image.getHeight(null) < n2)) {
            return null;
        }
        if (s == null) {
            s = "bilinear";
        }
        Image image2;
        if ("bilinear".equals(s)) {
            image2 = jmaster.jumploader.model.impl.image.A.A(this.D(image), n, n2);
        }
        else {
            image2 = image.getScaledInstance(n, n2, this.A(s));
        }
        return image2;
    }
    
    public Image A(final Image image, final double n, final String s, final boolean b) {
        return this.C(image, (int)(image.getWidth(null) * n), (int)(image.getHeight(null) * n), s, b);
    }
    
    public Image A(final Image image, final int n, final int n2, final String s, final boolean b) {
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final double min = Math.min(n / width, n2 / height);
        return this.C(image, (int)(min * width), (int)(min * height), s, b);
    }
    
    public Image B(final Image image, final int n, final int n2, final String s, final boolean b) {
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final double max = Math.max(n / width, n2 / height);
        return this.C(image, (int)(max * width), (int)(max * height), s, b);
    }
    
    public Image A(final Image image, final int n, final int n2, final String s) {
        final BufferedImage bufferedImage = new BufferedImage(n, n2, 1);
        final int width = image.getWidth(null);
        final int height = image.getHeight(null);
        final double max = Math.max(bufferedImage.getWidth() / width, bufferedImage.getHeight() / height);
        final int n3 = (int)(max * width);
        final int n4 = (int)(max * height);
        final BufferedImage d = this.D(this.C(image, n3, n4, s, true));
        final Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
        final int n5 = (n3 - n) / 2;
        final int n6 = (n4 - n2) / 2;
        graphics2D.drawImage(d, 0, 0, n, n2, n5, n6, n3 - n5, n4 - n6, null);
        graphics2D.dispose();
        d.flush();
        bufferedImage.flush();
        return bufferedImage;
    }
    
    public Image E(final Image image) {
        final BufferedImage d = this.D(image);
        final AffineTransform scaleInstance = AffineTransform.getScaleInstance(-1.0, 1.0);
        scaleInstance.translate(-d.getWidth(), 0.0);
        final BufferedImage filter = new AffineTransformOp(scaleInstance, 1).filter(d, null);
        d.flush();
        filter.flush();
        return filter;
    }
    
    public Image G(final Image image) {
        final BufferedImage d = this.D(image);
        final AffineTransform scaleInstance = AffineTransform.getScaleInstance(1.0, -1.0);
        scaleInstance.translate(0.0, -d.getHeight());
        final BufferedImage filter = new AffineTransformOp(scaleInstance, 1).filter(d, null);
        d.flush();
        filter.flush();
        return filter;
    }
    
    public Image F(final Image image) {
        final BufferedImage d = this.D(image);
        final AffineTransform rotateInstance = AffineTransform.getRotateInstance(Math.toRadians(270.0));
        rotateInstance.translate(d.getWidth() * -1, 0.0);
        final BufferedImage filter = new AffineTransformOp(rotateInstance, null).filter(d, null);
        d.flush();
        filter.flush();
        return filter;
    }
    
    public Image A(final Image image) {
        final BufferedImage d = this.D(image);
        final AffineTransform rotateInstance = AffineTransform.getRotateInstance(Math.toRadians(90.0));
        rotateInstance.translate(0.0, d.getHeight() * -1);
        final BufferedImage filter = new AffineTransformOp(rotateInstance, null).filter(d, null);
        d.flush();
        filter.flush();
        return filter;
    }
    
    public Image C(final Image image) {
        final BufferedImage d = this.D(image);
        final AffineTransform rotateInstance = AffineTransform.getRotateInstance(Math.toRadians(180.0));
        rotateInstance.translate(d.getWidth() * -1, d.getHeight() * -1);
        final BufferedImage filter = new AffineTransformOp(rotateInstance, null).filter(d, null);
        d.flush();
        filter.flush();
        return filter;
    }
    
    public void A(final BufferedImage bufferedImage, final WatermarkConfig watermarkConfig) {
        final Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
        final ImageIcon orLoadImage = watermarkConfig.getOrLoadImage();
        final ImageObserver imageObserver = null;
        final int iconWidth = orLoadImage.getIconWidth();
        final int iconHeight = orLoadImage.getIconHeight();
        final int width = bufferedImage.getWidth();
        final int height = bufferedImage.getHeight();
        int n = 0;
        int n2 = 0;
        if ("center".equals(watermarkConfig.getHalign())) {
            n = (width - iconWidth) / 2;
        }
        if ("right".equals(watermarkConfig.getHalign())) {
            n = width - iconWidth;
        }
        if ("middle".equals(watermarkConfig.getValign())) {
            n2 = (height - iconHeight) / 2;
        }
        if ("bottom".equals(watermarkConfig.getValign())) {
            n2 = height - iconHeight;
        }
        graphics2D.setComposite(AlphaComposite.getInstance(3, (float)(watermarkConfig.getOpacityPercent() / 100.0)));
        graphics2D.drawImage(orLoadImage.getImage(), n, n2, imageObserver);
    }
    
    public Dimension A(final File file) {
        Dimension dimension = null;
        ImageInputStream imageInputStream = null;
        try {
            imageInputStream = ImageIO.createImageInputStream(file);
            final Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(imageInputStream);
            if (imageReaders != null && imageReaders.hasNext()) {
                final ImageReader imageReader = imageReaders.next();
                imageReader.setInput(imageInputStream);
                dimension = new Dimension(imageReader.getWidth(0), imageReader.getHeight(0));
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        finally {
            try {
                imageInputStream.close();
            }
            catch (Exception ex2) {}
        }
        return dimension;
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
        R = new String[] { "bmp", "gif", "png", "jpg", "jpe", "jpeg", "tif", "tiff" };
    }
}
