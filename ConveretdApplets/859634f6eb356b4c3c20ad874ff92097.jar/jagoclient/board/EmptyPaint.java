// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.board;

import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import rene.gui.Global;
import java.awt.Component;
import java.awt.Image;
import java.awt.Color;
import jagoclient.StopThread;

public class EmptyPaint extends StopThread
{
    Board B;
    static int W;
    static int H;
    public static int Ox;
    public static int Oy;
    public static int D;
    static Color C;
    boolean Shadows;
    public static Image StaticImage;
    public static Image StaticShadowImage;
    
    EmptyPaint(final Board b, final int w, final int h, final Color c, final boolean shadows, final int ox, final int oy, final int d) {
        this.B = b;
        EmptyPaint.W = w;
        EmptyPaint.H = h;
        EmptyPaint.C = c;
        this.Shadows = shadows;
        EmptyPaint.Ox = ox;
        EmptyPaint.Oy = oy;
        EmptyPaint.D = d;
        this.start();
    }
    
    public void run() {
        try {
            this.setPriority(this.getPriority() - 1);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        try {
            Thread.sleep(100L);
        }
        catch (Exception ex2) {}
        createwood(this, this.B, EmptyPaint.W, EmptyPaint.H, EmptyPaint.C, this.Shadows, EmptyPaint.Ox, EmptyPaint.Oy, EmptyPaint.D);
        if (!this.stopped()) {
            this.B.updateboard();
        }
    }
    
    public static void createwood(final StopThread stopThread, final Component component, final int w, final int h, final Color c, final boolean b, final int ox, final int oy, final int d) {
        if (w == 0 || h == 0) {
            return;
        }
        EmptyPaint.StaticImage = (EmptyPaint.StaticShadowImage = null);
        final int[] array = new int[w * h];
        int[] array2 = null;
        if (b) {
            array2 = new int[w * h];
        }
        final int rgb = c.getRGB();
        final int n = rgb & 0xFF;
        final int n2 = (rgb & 0xFF00) >> 8;
        final int n3 = (rgb & 0xFF0000) >> 16;
        final boolean parameter = Global.getParameter("fineboard", true);
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                double n4;
                if (parameter) {
                    n4 = ((Math.sin(18.0 * j / w) + 1.0) / 2.0 + (Math.sin(3.0 * j / w) + 1.0) / 10.0 + 0.2 * Math.cos(5.0 * i / h) + 0.1 * Math.sin(11.0 * i / h)) * 20.0 + 0.5;
                }
                else {
                    n4 = ((Math.sin(14.0 * j / w) + 1.0) / 2.0 + 0.2 * Math.cos(3.0 * i / h) + 0.1 * Math.sin(11.0 * i / h)) * 10.0 + 0.5;
                }
                final double n5 = n4 - Math.floor(n4);
                double n6;
                if (n5 < 0.2) {
                    n6 = 1.0 - n5 / 2.0;
                }
                else if (n5 < 0.4) {
                    n6 = 1.0 - (0.4 - n5) / 2.0;
                }
                else {
                    n6 = 1.0;
                }
                if (i == w - 1 || (i == w - 2 && j < w - 2) || j == 0 || (j == 1 && i > 1)) {
                    n6 /= 2.0;
                }
                double n7;
                double n8;
                double n9;
                if (i == 0 || (i == 1 && j > 1) || j >= w - 1 || (j == w - 2 && i < h - 1)) {
                    n7 = 128.0 + n3 * n6 / 2.0;
                    n8 = 128.0 + n2 * n6 / 2.0;
                    n9 = 128.0 + n * n6 / 2.0;
                }
                else {
                    n7 = n3 * n6;
                    n8 = n2 * n6;
                    n9 = n * n6;
                }
                array[w * i + j] = (0xFF000000 | (int)n7 << 16 | (int)n8 << 8 | (int)n9);
                if (b) {
                    double n10 = 1.0;
                    final double abs = Math.abs(i - (ox + d / 2 + (i - ox) / d * d));
                    final double abs2 = Math.abs(j - (oy + d / 2 + (j - oy) / d * d));
                    final double n11 = Math.sqrt(abs2 * abs2 + abs * abs) / d * 2.0;
                    if (n11 < 1.0) {
                        n10 = 0.9 * n11;
                    }
                    array2[w * i + j] = (0xFF000000 | (int)(n7 * n10) << 16 | (int)(n8 * n10) << 8 | (int)(n9 * n10));
                }
                if (stopThread.stopped()) {
                    return;
                }
            }
        }
        if (b) {
            EmptyPaint.StaticShadowImage = component.createImage(new MemoryImageSource(w, h, ColorModel.getRGBdefault(), array2, 0, w));
        }
        EmptyPaint.StaticImage = component.createImage(new MemoryImageSource(w, h, ColorModel.getRGBdefault(), array, 0, w));
        EmptyPaint.W = w;
        EmptyPaint.H = h;
        EmptyPaint.D = d;
        EmptyPaint.Ox = ox;
        EmptyPaint.Oy = oy;
        EmptyPaint.C = c;
        savesize(component);
    }
    
    static void savesize(final ImageObserver imageObserver) {
        final Image staticImage = EmptyPaint.StaticImage;
        if (staticImage != null) {
            Global.setParameter("sboardwidth", staticImage.getWidth(imageObserver));
            Global.setParameter("sboardheight", staticImage.getHeight(imageObserver));
            Global.setParameter("sboardox", EmptyPaint.Ox);
            Global.setParameter("sboardoy", EmptyPaint.Oy);
            Global.setParameter("sboardd", EmptyPaint.D);
        }
    }
    
    static boolean haveImage(final int n, final int n2, final Color color, final int n3, final int n4, final int n5) {
        return EmptyPaint.StaticImage != null && n == EmptyPaint.W && n2 == EmptyPaint.H && n3 == EmptyPaint.Ox && n4 == EmptyPaint.Oy && EmptyPaint.D == n5 && EmptyPaint.C.getRGB() == color.getRGB();
    }
    
    static {
        EmptyPaint.StaticImage = null;
        EmptyPaint.StaticShadowImage = null;
    }
}
