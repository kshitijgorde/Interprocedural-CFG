import java.awt.event.MouseEvent;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Snow extends Applet implements Runnable, MouseMotionListener, MouseListener
{
    private Thread m_th;
    private Image m_off;
    private int m_max;
    private int m_a;
    private int m_w;
    private int m_h;
    private int m_wait;
    private int m_angv;
    private int m_fade;
    private int m_mouseX;
    private int m_mouseY;
    private int m_iCatch;
    private Color backColor;
    private boolean isPress;
    private boolean isLoaded;
    private String fileSnow;
    private String fileBack;
    private long m_lastTime;
    private SnowImage[][] m_pixSnows;
    
    public Snow() {
        this.m_th = null;
        this.m_max = 20;
        this.m_a = 2;
        this.m_wait = 30;
        this.m_angv = 6;
        this.m_fade = 0;
        this.m_iCatch = -1;
        this.backColor = Color.black;
        this.isPress = false;
        this.isLoaded = false;
    }
    
    public void init() {
        final String parameter = this.getParameter("BGCOLOR");
        final Color stringToColor;
        if (parameter != null && (stringToColor = stringToColor(parameter)) != null) {
            this.backColor = stringToColor;
        }
        this.setBackground(this.backColor);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }
    
    public void start() {
        final String parameter = this.getParameter("MAX");
        final String parameter2 = this.getParameter("WAIT");
        final String parameter3 = this.getParameter("AMPLITUDE");
        final String parameter4 = this.getParameter("ANGV");
        final String parameter5 = this.getParameter("FADE");
        this.fileSnow = this.getParameter("SNOW");
        this.fileBack = this.getParameter("IMG");
        if (parameter != null) {
            try {
                this.m_max = Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {}
        }
        if (parameter2 != null) {
            try {
                this.m_wait = Integer.parseInt(parameter2);
            }
            catch (NumberFormatException ex2) {}
        }
        if (parameter3 != null) {
            try {
                this.m_a = Integer.parseInt(parameter3);
            }
            catch (NumberFormatException ex3) {}
        }
        if (parameter4 != null) {
            try {
                this.m_angv = Integer.parseInt(parameter4);
            }
            catch (NumberFormatException ex4) {}
        }
        if (parameter5 != null) {
            try {
                this.m_fade = Integer.parseInt(parameter5);
            }
            catch (NumberFormatException ex5) {}
        }
        if (this.fileSnow == null) {
            this.fileSnow = "snow.gif";
        }
        final Dimension size = this.getSize();
        this.m_w = size.width;
        this.m_h = size.height;
        if (this.m_th == null) {
            this.m_th = new Thread(this);
        }
        this.m_th.start();
    }
    
    public void run() {
        final Random random = new Random();
        final int max = this.m_max;
        final int a = this.m_a;
        final int angv = this.m_angv;
        final Image image = this.getImage(this.getCodeBase(), this.fileSnow);
        final Image image2 = (this.fileBack != null) ? this.getImage(this.getCodeBase(), this.fileBack) : null;
        int[] pixels = this.getPixels(image2);
        if (pixels == null) {
            pixels = new int[this.m_w * this.m_h];
        }
        final SnowImage[][] pixSnows = new SnowImage[360][4];
        this.m_pixSnows = pixSnows;
        final SnowImage[][] array = pixSnows;
        final int[] pixels2 = this.getPixels(image);
        for (int i = 0; i < pixels2.length; ++i) {
            final int[] array2 = pixels2;
            final int n = i;
            array2[n] &= 0xFF;
        }
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        for (int j = 0; j < 360; ++j) {
            for (int k = 0; k < 4; ++k) {
                final double n2 = (k + 1) / 6.0 + 0.3333333333333333;
                array[j][k] = this.rotateImage(pixels2, width, height, j, n2, n2);
            }
            if (j % 10 == 0) {
                this.showStatus(100 * j / 359 + "% completed.");
            }
        }
        final int width2 = image2.getWidth(this);
        this.m_w = width2;
        final int n3 = width2;
        final int height2 = image2.getHeight(this);
        this.m_h = height2;
        final int n4 = height2;
        final int[] array3 = new int[n3 * n4];
        final MemoryImageSource memoryImageSource = new MemoryImageSource(n3, n4, new DirectColorModel(24, 16711680, 65280, 255), array3, 0, n3);
        memoryImageSource.setAnimated(true);
        memoryImageSource.setFullBufferUpdates(true);
        this.m_off = this.createImage(memoryImageSource);
        final SnowPoint[] array4 = new SnowPoint[max];
        for (int l = 0; l < max; ++l) {
            array4[l] = new SnowPoint(0, n4 * 2);
        }
        System.gc();
        this.showStatus("Snow Applet Started.");
        this.m_lastTime = System.currentTimeMillis();
        this.isLoaded = true;
        int n5 = (this.m_fade > 0) ? 1 : 0;
        int n6 = 0;
        while (Thread.currentThread() == this.m_th) {
            System.arraycopy(pixels, 0, array3, 0, array3.length);
            for (int iCatch = 0; iCatch < max; ++iCatch) {
                final SnowPoint snowPoint = array4[iCatch];
                array4[iCatch].moveNext();
                if (snowPoint.y > n4 + height) {
                    snowPoint.setSnow((int)(random.nextFloat() * (n3 - width) + width / 2), (int)(-random.nextFloat() * n4 / 2.0f - height), (int)(random.nextFloat() * 2.0f * a - a), (int)(random.nextFloat() * 3.0f + 1.0f), (int)(random.nextFloat() * 360.0f), random.nextInt() % angv, (int)(random.nextFloat() * 4.0f), random.nextFloat() * 0.6f + 0.2f);
                    if (snowPoint.wDegree == 0) {
                        snowPoint.wDegree = 2;
                    }
                }
                else {
                    final SnowImage snowImage = array[snowPoint.degree][snowPoint.size];
                    if (this.isPress && (this.m_iCatch == iCatch || (this.m_iCatch < 0 && abs(this.m_mouseX - snowPoint.x) < snowImage.halfX && abs(this.m_mouseY - snowPoint.y) < snowImage.halfY))) {
                        snowPoint.x = this.m_mouseX;
                        snowPoint.y = this.m_mouseY;
                        this.m_iCatch = iCatch;
                    }
                    if (snowPoint.y > -height) {
                        this.drawSnow(array3, snowPoint);
                    }
                }
            }
            if (n5 != 0) {
                n6 += 2;
                if (this.m_fade == 1) {
                    this.fade(array3, n6);
                }
                else {
                    this.fadeWhite(array3, n6);
                }
                if (n6 > 255) {
                    n5 = 0;
                }
            }
            memoryImageSource.newPixels();
            this.sleepThread();
            this.repaint();
        }
    }
    
    private void fade(final int[] array, final int n) {
        for (int i = 0; i < array.length; ++i) {
            array[i] = ((array[i] >> 16 & 0xFF) * n / 256 << 16 | (array[i] >> 8 & 0xFF) * n / 256 << 8 | (array[i] & 0xFF) * n / 256);
        }
    }
    
    private void fadeWhite(final int[] array, int n) {
        n = 255 - n;
        for (int i = 0; i < array.length; ++i) {
            int n2;
            if ((n2 = (array[i] >> 16 & 0xFF) + n) > 255) {
                n2 = 255;
            }
            int n3;
            if ((n3 = (array[i] >> 8 & 0xFF) + n) > 255) {
                n3 = 255;
            }
            int n4;
            if ((n4 = (array[i] & 0xFF) + n) > 255) {
                n4 = 255;
            }
            array[i] = (n2 << 16 | n3 << 8 | n4);
        }
    }
    
    private static final int abs(final int n) {
        return (n > 0) ? n : (-n);
    }
    
    private final synchronized void sleepThread() {
        Thread.yield();
        final int n = (int)(this.m_wait - (System.currentTimeMillis() - this.m_lastTime));
        if (n > 0) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        else {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex2) {}
        }
        this.m_lastTime = System.currentTimeMillis();
    }
    
    public void stop() {
        this.m_th = null;
        this.m_pixSnows = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.isLoaded) {
            graphics.drawImage(this.m_off, 0, 0, this);
        }
        else {
            graphics.clearRect(0, 0, this.m_w, this.m_h);
            graphics.setColor(Color.white);
            graphics.drawString("Loading image...", 10, 20);
        }
    }
    
    private int[] getPixels(final Image image) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        int width;
        int height;
        try {
            mediaTracker.waitForAll();
            mediaTracker.removeImage(image);
            width = image.getWidth(this);
            height = image.getHeight(this);
        }
        catch (InterruptedException ex) {
            return null;
        }
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex2) {
            return null;
        }
        if ((pixelGrabber.getStatus() & 0x80) != 0x0) {
            return null;
        }
        return array;
    }
    
    private void drawSnow(final int[] array, final SnowPoint snowPoint) {
        final SnowImage snowImage = this.m_pixSnows[snowPoint.degree][snowPoint.size];
        final int[] pix = snowImage.pix;
        final int w = this.m_w;
        final int h = this.m_h;
        for (int i = 0; i < snowImage.h; ++i) {
            final int n = snowPoint.y + i - snowImage.halfY;
            if (n >= h) {
                break;
            }
            if (n >= 0) {
                final int n2 = n * w;
                final int n3 = i * snowImage.w;
                for (int j = 0; j < snowImage.w; ++j) {
                    final int n4 = snowPoint.x + j - snowImage.halfX;
                    if (n4 >= w) {
                        break;
                    }
                    final int n5 = pix[j + n3];
                    if (n4 >= 0) {
                        if (n5 != 0) {
                            final int n6 = (int)(n5 * snowPoint.blur);
                            final int n7 = n4 + n2;
                            final int n8 = (array[n7] >> 16 & 0xFF) + n6;
                            final int n9 = (array[n7] >> 8 & 0xFF) + n6;
                            final int n10 = (array[n7] & 0xFF) + n6;
                            array[n7] = (((n8 > 255) ? 255 : n8) << 16 | ((n9 > 255) ? 255 : n9) << 8 | ((n10 > 255) ? 255 : n10));
                        }
                    }
                }
            }
        }
    }
    
    private SnowImage rotateImage(final int[] array, final int n, final int n2, final double n3, final double n4, final double n5) {
        final int[] array2 = new int[array.length];
        final int n6 = (int)(n * n4 + 0.9);
        final int n7 = (int)(n2 * n5 + 0.9);
        final int n8 = n / 2;
        final int n9 = n2 / 2;
        final int n10 = n6 / 2;
        final int n11 = n7 / 2;
        final double n12 = n3 * 3.141592653589793 / 180.0;
        final double cos = Math.cos(n12);
        final double sin = Math.sin(n12);
        for (int i = -n11; i < n11; ++i) {
            final double n13 = i * cos;
            final double n14 = i * sin;
            final int n15 = (i + n11) * n6;
            for (int j = -n10; j < n10; ++j) {
                final double n16 = (j * sin + n13) / n5;
                final double n17 = (j * cos - n14) / n4;
                int n18;
                if (n16 > 0.0) {
                    n18 = (int)n16;
                }
                else {
                    n18 = (int)n16 - 1;
                }
                int n19;
                if (n17 > 0.0) {
                    n19 = (int)n17;
                }
                else {
                    n19 = (int)n17 - 1;
                }
                final double n20 = n16 - n18;
                final double n21 = n17 - n19;
                int n23;
                if (n18 >= -n9 && n18 < n9 - 1 && n19 >= -n8 && n19 < n8 - 1) {
                    final int n22 = n19 + n8 + (n18 + n9) * n;
                    n23 = (int)((1.0 - n20) * ((1.0 - n21) * array[n22] + n21 * array[n22 + 1]) + n20 * ((1.0 - n21) * array[n22 + n] + n21 * array[n22 + 1 + n]));
                }
                else {
                    n23 = 0;
                }
                array2[j + n10 + n15] = n23;
            }
        }
        return new SnowImage(array2, n6, n7);
    }
    
    private static Color stringToColor(final String s) {
        try {
            return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.m_mouseX = mouseEvent.getX();
        this.m_mouseY = mouseEvent.getY();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.isPress = false;
        this.m_iCatch = -1;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getModifiers() == 16) {
            this.isPress = true;
            this.mouseDragged(mouseEvent);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
}
