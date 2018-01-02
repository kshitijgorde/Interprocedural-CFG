import java.awt.Image;
import java.io.IOException;
import java.awt.image.PixelGrabber;
import java.io.BufferedOutputStream;
import util.ImageBlotter;
import java.io.OutputStream;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import util.ImageHolder;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class DesignCanvas extends Canvas
{
    public static final int FORMAT_PPM = 1;
    public static final int FORMAT_TGA = 2;
    int originalSize;
    Vector images;
    public Vector designs;
    Color xorColor;
    Graphics xorGraphics;
    Point rbStart;
    Point rbCurrent;
    PShape rbShape;
    
    DesignCanvas() {
        this.images = new Vector();
        this.designs = new Vector();
        this.originalSize = 0;
        this.xorColor = new Color(102, 102, 102);
        this.xorGraphics = null;
        this.setCursor(new Cursor(1));
    }
    
    public DesignCanvas(final int originalSize) {
        this();
        this.setSize(this.originalSize = originalSize, originalSize);
    }
    
    public void addImage(final ImageHolder imageHolder, final Design design) {
        this.images.addElement(imageHolder);
        this.designs.addElement(design);
        this.repaint();
    }
    
    public int countImages() {
        return this.images.size();
    }
    
    public void removeImage(final int n) {
        if (n >= 0 && n < this.images.size()) {
            this.images.removeElementAt(n);
            this.designs.removeElementAt(n);
            this.repaint();
        }
    }
    
    public void removeAllImages() {
        this.images.removeAllElements();
        this.designs.removeAllElements();
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.xorGraphics == null) {
            this.xorGraphics = this.getGraphics();
        }
        final Enumeration<ImageHolder> elements = this.images.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().draw(graphics, this);
        }
    }
    
    public boolean writeData(final Design[] array, final OutputStream outputStream, final Color color, final int n) {
        int[] addToMemoryImage = null;
        array[0].message("Re-rendering high-quality image.");
        for (int i = 0; i < array.length; ++i) {
            final Design design = array[i];
            design.run();
            final ImageBlotter blotter = design.getBlotter();
            final Color background = blotter.getBackground();
            blotter.setColors(null, color);
            design.message("Adding to composite image..");
            addToMemoryImage = blotter.addToMemoryImage(addToMemoryImage);
            blotter.release();
            design.message("Done with design " + (i + 1) + ".");
            blotter.setColors(null, background);
        }
        return this.writeData(outputStream, color, addToMemoryImage, n);
    }
    
    public boolean writeData(final OutputStream outputStream, final Color color, int[] array, final int n) {
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        boolean b = false;
        final int originalSize = this.originalSize;
        if (array == null) {
            final Image image = this.createImage(originalSize, originalSize);
            final Graphics graphics = image.getGraphics();
            graphics.setColor(color);
            graphics.fillRect(0, 0, originalSize, originalSize);
            this.paint(graphics);
            array = new int[originalSize * originalSize];
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, originalSize, originalSize, array, 0, originalSize);
            try {
                pixelGrabber.grabPixels();
                b = true;
            }
            catch (InterruptedException ex) {}
        }
        else {
            b = true;
        }
        if (b) {
            switch (n) {
                case 1: {
                    final StringBuffer sb = new StringBuffer();
                    sb.append("P6\n");
                    sb.append(originalSize);
                    sb.append(" ");
                    sb.append(originalSize);
                    sb.append("\n255\n");
                    try {
                        bufferedOutputStream.write(sb.toString().getBytes());
                        for (int i = 0; i < array.length; ++i) {
                            bufferedOutputStream.write(array[i] >> 16 & 0xFF);
                            bufferedOutputStream.write(array[i] >> 8 & 0xFF);
                            bufferedOutputStream.write(array[i] & 0xFF);
                        }
                        bufferedOutputStream.flush();
                    }
                    catch (IOException ex2) {
                        b = false;
                    }
                    break;
                }
                case 2: {
                    final byte[] array2 = new byte[18];
                    for (int j = 0; j < array2.length; ++j) {
                        array2[j] = 0;
                    }
                    array2[2] = 2;
                    array2[12] = (byte)(originalSize % 256);
                    array2[13] = (byte)(originalSize / 256);
                    array2[14] = (byte)(originalSize % 256);
                    array2[15] = (byte)(originalSize / 256);
                    array2[16] = 24;
                    try {
                        bufferedOutputStream.write(array2);
                        for (int k = originalSize - 1; k >= 0; --k) {
                            for (int l = 0; l < originalSize; ++l) {
                                bufferedOutputStream.write(array[l + k * originalSize] & 0xFF);
                                bufferedOutputStream.write(array[l + k * originalSize] >> 8 & 0xFF);
                                bufferedOutputStream.write(array[l + k * originalSize] >> 16 & 0xFF);
                            }
                        }
                        bufferedOutputStream.flush();
                    }
                    catch (IOException ex3) {
                        b = false;
                    }
                    break;
                }
                default: {
                    throw new RuntimeException("Unsupported image format.");
                }
            }
        }
        return b;
    }
}
