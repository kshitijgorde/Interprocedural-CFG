import java.lang.reflect.Method;
import java.awt.image.ImageObserver;
import java.io.ByteArrayOutputStream;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Point;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.Dimension;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.Image;
import java.util.Vector;
import java.io.EOFException;
import java.io.DataInputStream;
import java.io.InputStream;
import java.awt.image.ColorModel;
import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class WImage
{
    private final Rectangle bounds;
    private int[] pixels;
    public static final ColorModel COLOR_MODEL;
    static Class I;
    static Class TYPE;
    static Class addElement;
    
    private WImage(final int n, final int n2, final boolean b) {
        if (n <= 0 || n2 <= 0) {
            this.bounds = new Rectangle();
        }
        else {
            this.bounds = new Rectangle(n, n2);
        }
        if (b) {
            this.pixels = new int[this.bounds.width * this.bounds.height];
        }
    }
    
    public WImage(final int n, final int n2, final int n3) {
        this(n, n2, true);
        this.fill(n3, false, false, this);
    }
    
    public WImage(final WImage wImage) {
        this(wImage.bounds.width, wImage.bounds.height, true);
        System.arraycopy(wImage.pixels, 0, this.pixels, 0, this.pixels.length);
    }
    
    public WImage(final int n, final int n2, final int[] pixels) {
        this(n, n2, false);
        this.pixels = pixels;
    }
    
    public static final WImage read(final InputStream inputStream, final boolean b) {
        final DataInputStream dataInputStream = new DataInputStream(inputStream);
        final WImage wImage = new WImage(dataInputStream.readInt(), dataInputStream.readInt(), true);
        int read = 0;
        int n = 0;
        for (int i = 0; i < wImage.bounds.height; ++i) {
            for (int j = 0; j < wImage.bounds.width; ++j) {
                read = dataInputStream.read();
                int read2;
                int read3;
                int read4;
                if (read == 0) {
                    read2 = 0;
                    read3 = 0;
                    read4 = 0;
                }
                else {
                    read2 = dataInputStream.read();
                    read3 = dataInputStream.read();
                    read4 = dataInputStream.read();
                }
                wImage.pixels[n + j] = ((read << 24 & 0xFF000000) | (read2 << 16 & 0xFF0000) | (read3 << 8 & 0xFF00) | (read4 & 0xFF));
            }
            n += wImage.bounds.width;
            if (read == -1) {
                throw new EOFException();
            }
        }
        if (b) {
            inputStream.close();
        }
        return wImage;
    }
    
    public static final Vector readList(final InputStream inputStream, final int n) {
        final Vector<WImage> vector = new Vector<WImage>();
        try {
            for (int i = 0; i < n; ++i) {
                vector.addElement(read(inputStream, false));
            }
        }
        catch (EOFException ex) {}
        return vector;
    }
    
    public static final WImage create(final Image image, final int n, final int n2) {
        if (image == null) {
            return new WImage(1, 1, 0);
        }
        return create(image.getSource(), false, n, n2);
    }
    
    private static final WImage create(final ImageProducer imageProducer, final boolean b, final int n, final int n2) {
        WImage wImage = null;
        try {
            final PixelGrabber pixelGrabber = new PixelGrabber(imageProducer, 0, 0, n, n2, null, 0, -1);
            if (pixelGrabber.grabPixels()) {
                wImage = new WImage(pixelGrabber.getWidth(), pixelGrabber.getHeight(), false);
                wImage.pixels = (int[])pixelGrabber.getPixels();
                if (b) {
                    for (int i = 0; i < wImage.pixels.length; ++i) {
                        wImage.pixels[i] = getPixel(wImage.pixels[i]);
                    }
                }
            }
            else {
                System.err.println("Error reading image.. status=" + Integer.toHexString(pixelGrabber.getStatus()));
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Image reading interrupted..");
        }
        return wImage;
    }
    
    public final int getWidth() {
        return this.bounds.width;
    }
    
    public final int getHeight() {
        return this.bounds.height;
    }
    
    public static final WImage resize(final WImage wImage, final Dimension dimension, final int n, WImage fill) {
        if (wImage == null) {
            return new WImage(dimension.width, dimension.height, 0);
        }
        if (wImage.bounds.getSize().equals(dimension)) {
            return wImage;
        }
        switch (n) {
            case 0: {
                return wImage.grow(dimension.width, dimension.height, 1, 1, fill);
            }
            case 1: {
                return wImage.grow(dimension.width, dimension.height, wImage.bounds.width, wImage.bounds.height, fill);
            }
            case 2: {
                return create(new FilteredImageSource(wImage.getImageProducer(null, true), new AreaAveragingScaleFilter(dimension.width, dimension.height)), true, -1, -1);
            }
            default: {
                if (fill == null || !fill.bounds.getSize().equals(dimension)) {
                    fill = new WImage(dimension.width, dimension.height, true);
                }
                fill = fill.fill(0, false, false, fill);
                return fill.paste(wImage, 0, 0, true, fill);
            }
        }
    }
    
    public final WImage fill(int pixel, final boolean b, final boolean b2, WImage wImage) {
        if (wImage == null || !wImage.bounds.equals(this.bounds)) {
            wImage = new WImage(this.bounds.width, this.bounds.height, true);
        }
        pixel = getPixel(pixel);
        if (b2) {
            if (b) {
                for (int i = 0; i < this.pixels.length; ++i) {
                    wImage.pixels[i] = superpose(this.pixels[i], pixel);
                }
            }
            else {
                for (int j = 0; j < this.pixels.length; ++j) {
                    wImage.pixels[j] = superpose(pixel, this.pixels[j]);
                }
            }
        }
        else {
            for (int k = 0; k < this.pixels.length; ++k) {
                wImage.pixels[k] = pixel;
            }
        }
        return wImage;
    }
    
    public static final WImage fill(final int n, final WImage wImage) {
        final WImage wImage2 = new WImage(wImage);
        for (int i = 0; i < wImage2.pixels.length; ++i) {
            final int n2 = wImage2.pixels[i];
            if (n2 != 0) {
                wImage2.pixels[i] = superpose(-16777216 + n, n2);
            }
        }
        return wImage2;
    }
    
    public final WImage paste(final WImage wImage, final int n, final int n2, final boolean b, final WImage wImage2) {
        return this.paste(wImage, wImage.bounds, new Point(n, n2), b, wImage2);
    }
    
    public final WImage paste(final WImage wImage, Rectangle rectangle, final Point location, final boolean b, WImage wImage2) {
        if (wImage2 == null || !wImage2.bounds.equals(this.bounds)) {
            wImage2 = new WImage(this);
        }
        rectangle = rectangle.intersection(wImage.bounds);
        final int x = rectangle.x;
        final int y = rectangle.y;
        rectangle.setLocation(location);
        rectangle = rectangle.intersection(this.bounds);
        int n = rectangle.y * this.bounds.width;
        int n2 = y * wImage.bounds.width;
        if (b) {
            for (int i = 0; i < rectangle.height; ++i) {
                for (int j = 0; j < rectangle.width; ++j) {
                    wImage2.pixels[n + rectangle.x + j] = superpose(this.pixels[n + rectangle.x + j], wImage.pixels[n2 + x + j]);
                }
                n2 += wImage.bounds.width;
                n += this.bounds.width;
            }
        }
        else {
            for (int k = 0; k < rectangle.height; ++k) {
                for (int l = 0; l < rectangle.width; ++l) {
                    wImage2.pixels[n + rectangle.x + l] = wImage.pixels[n2 + x + l];
                }
                n2 += wImage.bounds.width;
                n += this.bounds.width;
            }
        }
        return wImage2;
    }
    
    public final WImage copy(Rectangle intersection, WImage wImage) {
        intersection = this.bounds.intersection(intersection);
        if (wImage == null || !wImage.bounds.equals(intersection)) {
            wImage = new WImage(intersection.width, intersection.height, true);
        }
        int n = 0;
        int n2 = intersection.y * this.bounds.width;
        for (int i = 0; i < intersection.height; ++i) {
            for (int j = 0; j < intersection.width; ++j) {
                wImage.pixels[n + j] = this.pixels[n2 + intersection.x + j];
            }
            n += wImage.bounds.width;
            n2 += this.bounds.width;
        }
        return wImage;
    }
    
    public final WImage grow(final int n, final int n2, int n3, int n4, WImage wImage) {
        final int max = Math.max(this.bounds.width, n);
        final int max2 = Math.max(this.bounds.height, n2);
        if (n3 > n) {
            n3 = n;
        }
        if (n4 > n2) {
            n4 = n2;
        }
        if (wImage == null || wImage.bounds.width != max || wImage.bounds.height != max2) {
            wImage = new WImage(max, max2, true);
        }
        final int n5 = (this.bounds.width - n3) / 2;
        final int n6 = this.bounds.width - (n5 + n3);
        int n7 = 0;
        int n8 = 0;
        for (int i = 0; i < this.bounds.height; ++i) {
            for (int j = 0; j < n5; ++j) {
                wImage.pixels[n7 + j] = this.pixels[n8 + j];
            }
            for (int k = 1; k <= n6; ++k) {
                wImage.pixels[n7 + wImage.bounds.width - k] = this.pixels[n8 + this.bounds.width - k];
            }
            for (int l = n5; l < n5 + n3; ++l) {
                for (int n9 = l; n9 < wImage.bounds.width - n6; n9 += n3) {
                    wImage.pixels[n7 + n9] = this.pixels[n8 + l];
                }
            }
            n8 += this.bounds.width;
            n7 += wImage.bounds.width;
        }
        final int n10 = (this.bounds.height - n4) / 2;
        final int n11 = this.bounds.height - (n10 + n4);
        final int n12 = n10 * wImage.bounds.width;
        final int n13 = n11 * wImage.bounds.width;
        n4 *= wImage.bounds.width;
        int n14 = n12 + n4;
        final int n15 = wImage.pixels.length - n13;
        System.arraycopy(wImage.pixels, n14, wImage.pixels, n15, n13);
        while (n14 < n15 - n4) {
            System.arraycopy(wImage.pixels, n12, wImage.pixels, n14, n4);
            n14 += n4;
        }
        System.arraycopy(wImage.pixels, n12, wImage.pixels, n14, n15 - n14);
        if (max != n || max2 != n2) {
            return wImage.copy(new Rectangle(0, 0, n, n2), null);
        }
        return wImage;
    }
    
    public static final int superpose(final int n, final int n2) {
        final boolean b = false;
        final int n3 = n2 >> 24 & 0xFF;
        return (b ? 1 : 0) | ((n >> 24 & 0xFF) * (255 - n3) / 255 + n3 & 0xFF) << 24 | ((n >> 16 & 0xFF) * (255 - n3) / 255 + (n2 >> 16) & 0xFF) << 16 | ((n >> 8 & 0xFF) * (255 - n3) / 255 + (n2 >> 8) & 0xFF) << 8 | ((n & 0xFF) * (255 - n3) / 255 + n2 & 0xFF);
    }
    
    public final WImage reflect(final boolean b, final boolean b2) {
        final WImage wImage = new WImage(this.bounds.width, this.bounds.height, true);
        int n = 0;
        int n2;
        int n3;
        if (b) {
            n2 = this.bounds.width - 1;
            n3 = -1;
        }
        else {
            n2 = 0;
            n3 = 1;
        }
        int n4;
        int n5;
        if (b2) {
            n4 = this.pixels.length - this.bounds.width;
            n5 = -1;
        }
        else {
            n4 = 0;
            n5 = 1;
        }
        for (int i = 0; i < this.bounds.height; ++i) {
            for (int j = 0; j < this.bounds.width; ++j) {
                wImage.pixels[n + j] = this.pixels[n4 + n5 * n + n2 + n3 * j];
            }
            n += this.bounds.width;
        }
        return wImage;
    }
    
    public final WImage rotate(WImage wImage) {
        if (wImage == null || wImage.bounds.width != this.bounds.height || wImage.bounds.height != this.bounds.width) {}
        wImage = new WImage(this.bounds.height, this.bounds.width, true);
        int n = 0;
        for (int i = 0; i < this.bounds.height; ++i) {
            for (int j = 0; j < this.bounds.width; ++j) {
                wImage.pixels[i + j * this.bounds.height] = this.pixels[n + j];
            }
            n += this.bounds.width;
        }
        return wImage;
    }
    
    private ImageProducer getImageProducer(Rectangle rectangle, final boolean b) {
        if (rectangle == null) {
            rectangle = this.bounds;
        }
        else {
            rectangle = this.bounds.intersection(rectangle);
        }
        ColorModel colorModel;
        if (b) {
            colorModel = WImage.COLOR_MODEL;
        }
        else {
            colorModel = ColorModel.getRGBdefault();
        }
        return new MemoryImageSource(rectangle.width, rectangle.height, colorModel, this.pixels, rectangle.y * this.bounds.width + rectangle.x, this.bounds.width);
    }
    
    public final Image toJavaImage(final Rectangle rectangle) {
        return Toolkit.getDefaultToolkit().createImage(this.getImageProducer(rectangle, false));
    }
    
    public static final int getPixel(final int n) {
        final int n2 = n >> 24 & 0xFF;
        return (n & 0xFF000000) | ((n >> 16 & 0xFF) * n2 / 255 & 0xFF) << 16 | ((n >> 8 & 0xFF) * n2 / 255 & 0xFF) << 8 | ((n & 0xFF) * n2 / 255 & 0xFF);
    }
    
    public static final byte[] image2Bytes(final Image image, final String s) {
        final ClassLoader classLoader = ((WImage.I == null) ? (WImage.I = COLOR_MODEL("WImage")) : WImage.I).getClassLoader();
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final Class<?> loadClass = classLoader.loadClass("java.awt.image.RenderedImage");
        final Image image2 = (Image)classLoader.loadClass("java.awt.image.BufferedImage").getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(new Integer(image.getWidth(null)), new Integer(image.getHeight(null)), new Integer(1));
        image2.getGraphics().drawImage(image, 0, 0, null);
        final Method method = classLoader.loadClass("javax.imageio.ImageIO").getMethod("write", loadClass, (WImage.TYPE == null) ? (WImage.TYPE = COLOR_MODEL("java.lang.String")) : WImage.TYPE, (WImage.addElement == null) ? (WImage.addElement = COLOR_MODEL("java.io.OutputStream")) : WImage.addElement);
        method.invoke(method, image2, s, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
    private static final Class COLOR_MODEL(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        COLOR_MODEL = new WImage$1(32);
    }
}
