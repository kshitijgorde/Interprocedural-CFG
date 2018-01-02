// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.util;

import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageWriteParam;
import javax.imageio.metadata.IIOMetadata;
import java.util.List;
import java.awt.image.RenderedImage;
import javax.imageio.IIOImage;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import java.util.Locale;
import javax.imageio.ImageWriter;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import javax.swing.JPanel;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Point;

public class ImageUtils
{
    private static Log log;
    static /* synthetic */ Class class$com$itt$J2KViewer$util$ImageUtils;
    
    public static Rectangle getRectangleFromTwoPoints(final Point point, final Point point2) {
        if (point == null || point2 == null) {
            return null;
        }
        if (point.x < point2.x && point.y < point2.y) {
            return new Rectangle(point.x, point.y, point2.x - point.x, point2.y - point.y);
        }
        if (point.x < point2.x && point.y > point2.y) {
            return new Rectangle(point.x, point2.y, point2.x - point.x, point.y - point2.y);
        }
        if (point.x > point2.x && point.y > point2.y) {
            return new Rectangle(point2.x, point2.y, point.x - point2.x, point.y - point2.y);
        }
        if (point.x > point2.x && point.y < point2.y) {
            return new Rectangle(point2.x, point.y, point.x - point2.x, point2.y - point.y);
        }
        return null;
    }
    
    public static int fullToZoomed(final int n, final int n2) {
        int n3 = n;
        if (n2 < 0) {
            n3 = 1 + (n - 1 << -n2);
        }
        else if (n2 > 0) {
            n3 = 1 + (n - 1 >> n2);
        }
        return n3;
    }
    
    public Point getCenterOfRectangle(final Rectangle rectangle) {
        Point point = null;
        if (rectangle != null && rectangle.width > 0 && rectangle.height > 0) {
            point = new Point((int)rectangle.getCenterX(), (int)rectangle.getCenterY());
        }
        return point;
    }
    
    public Rectangle getRectangleCenteredOnPoint(final Point point, final int n, final int n2) {
        Rectangle rectangle = null;
        if (point != null && n > 0 && n2 > 0) {
            rectangle = new Rectangle(point.x - n / 2, point.y - n2 / 2, n, n2);
        }
        return rectangle;
    }
    
    public static Rectangle fullToZoomed(final Rectangle rectangle, final int n) {
        Rectangle rectangle2 = null;
        if (rectangle != null) {
            rectangle2 = new Rectangle(fullToZoomed(rectangle.x, n), fullToZoomed(rectangle.y, n), fullToZoomed(rectangle.width, n), fullToZoomed(rectangle.height, n));
        }
        return rectangle2;
    }
    
    public static Rectangle zoomedToZoomed(final Rectangle rectangle, final int n, final int n2) {
        Rectangle fullToZoomed;
        if (n != n2) {
            if (n == 0) {
                return fullToZoomed(rectangle, n2);
            }
            if (n2 == 0) {
                return zoomedToFull(rectangle, n);
            }
            fullToZoomed = fullToZoomed(zoomedToFull(rectangle, n), n2);
        }
        else {
            fullToZoomed = rectangle;
        }
        return fullToZoomed;
    }
    
    public static double getMemoryFootPrintInBytes(final Rectangle rectangle, final int n) {
        double n2 = -1.0;
        if (rectangle != null && rectangle.width > 0 && rectangle.height > 0 && n > 0) {
            n2 = rectangle.width * rectangle.height * 3 * n;
        }
        return n2;
    }
    
    public static int zoomedToFull(final int n, final int n2) {
        int n3 = n;
        if (n2 < 0) {
            n3 = n >> -n2;
        }
        else if (n2 > 0) {
            n3 = n << n2;
        }
        return n3;
    }
    
    public static Rectangle zoomedToFull(final Rectangle rectangle, final int n) {
        Rectangle rectangle2 = null;
        if (rectangle != null) {
            rectangle2 = new Rectangle(zoomedToFull(rectangle.x, n), zoomedToFull(rectangle.y, n), zoomedToFull(rectangle.width, n), zoomedToFull(rectangle.height, n));
        }
        return rectangle2;
    }
    
    public static BufferedImage rotateImage(final BufferedImage bufferedImage, final double n, final int n2, final int n3) {
        final BufferedImage bufferedImage2 = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        final Graphics2D graphics = bufferedImage2.createGraphics();
        final AffineTransform transform = graphics.getTransform();
        transform.rotate(n, n2, n3);
        graphics.setTransform(transform);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.drawImage(bufferedImage, 0, 0, null);
        return bufferedImage2;
    }
    
    public static BufferedImage convertImageToBufferedImage(final Image image, final JPanel panel) {
        return convertImageToBufferedImageByType(image, 1, panel);
    }
    
    public static BufferedImage convertImageToBufferedImageByType(final Image image, final int n, final JPanel panel) {
        try {
            final MediaTracker mediaTracker = new MediaTracker(panel);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
        }
        catch (Exception ex) {
            ImageUtils.log.error("MediaTracker Error!  ", ex);
        }
        final BufferedImage bufferedImage = new BufferedImage(image.getWidth(panel), image.getHeight(panel), n);
        bufferedImage.createGraphics().drawImage(image, 0, 0, panel);
        return bufferedImage;
    }
    
    public static void writeJPEG(final BufferedImage bufferedImage, final ImageOutputStream output, final float compressionQuality) throws IOException {
        ImageWriter imageWriter = null;
        final Iterator<ImageWriter> imageWritersByFormatName = ImageIO.getImageWritersByFormatName("jpg");
        if (imageWritersByFormatName.hasNext()) {
            imageWriter = imageWritersByFormatName.next();
        }
        final JPEGImageWriteParam jpegImageWriteParam = new JPEGImageWriteParam(Locale.getDefault());
        jpegImageWriteParam.setCompressionMode(2);
        jpegImageWriteParam.setCompressionQuality(compressionQuality);
        imageWriter.setOutput(output);
        imageWriter.write(null, new IIOImage(bufferedImage, null, null), jpegImageWriteParam);
        output.flush();
        imageWriter.dispose();
        output.close();
    }
    
    public static int getZoomChangeForMagnification(final int n, final int n2) {
        int n3 = 0;
        if (n > 1) {
            if (n % 2 == 0) {
                n3 = n / 2;
            }
            else {
                final double n4 = 1.5;
                final double n5 = n;
                if (n5 / 2.0 >= n4) {
                    n3 = (int)Math.ceil(n5 / 2.0);
                }
            }
        }
        if (n2 - n3 <= 0) {
            n3 = n2;
        }
        return n3;
    }
    
    public static int getReductionFactorForZoomChange(final int n, final int n2) {
        int n3;
        if (n == 0) {
            n3 = 1;
        }
        else if (n2 > 1) {
            n3 = n * (n2 / 2);
        }
        else {
            n3 = n;
        }
        return n3;
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
        ImageUtils.log = new Log((ImageUtils.class$com$itt$J2KViewer$util$ImageUtils == null) ? (ImageUtils.class$com$itt$J2KViewer$util$ImageUtils = class$("com.itt.J2KViewer.util.ImageUtils")) : ImageUtils.class$com$itt$J2KViewer$util$ImageUtils);
    }
}
