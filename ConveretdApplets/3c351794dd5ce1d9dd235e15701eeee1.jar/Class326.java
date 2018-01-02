import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class326
{
    private Class332[] aClass332Array2731;
    private static int anInt2732;
    private static int[] anIntArray2733;
    private int[] anIntArray2734;
    private int anInt2735;
    private int anInt2736;
    private boolean aBoolean2737;
    private int[] anIntArray2738;
    
    final int method3701(final String s) {
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            n += this.anIntArray2738[Class326.anIntArray2733[s.charAt(i)]];
        }
        return n;
    }
    
    private final void method3702(final ha ha, final Font font, final FontMetrics fontMetrics, final char c, final int n, boolean b) {
        final int charWidth;
        int n2 = charWidth = fontMetrics.charWidth(c);
        if (b) {
            try {
                if (c == '/') {
                    b = false;
                }
                if (c == 'f' || c == 't' || c == 'w' || c == 'v' || c == 'k' || c == 'x' || c == 'y' || c == 'A' || c == 'V' || c == 'W') {
                    ++n2;
                }
            }
            catch (Exception ex) {}
        }
        final int maxAscent = fontMetrics.getMaxAscent();
        final int n3 = fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
        final int height = fontMetrics.getHeight();
        final Image image = Class42_Sub3.aCanvas5361.createImage(n2, n3);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, n2, n3);
        graphics.setColor(Color.white);
        graphics.setFont(font);
        graphics.drawString(String.valueOf(c), 0, maxAscent);
        if (b) {
            graphics.drawString(String.valueOf(c), 1, maxAscent);
        }
        final int[] array = new int[n2 * n3];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n2, n3, array, 0, n2);
        try {
            pixelGrabber.grabPixels();
        }
        catch (Exception ex2) {}
        image.flush();
        int n4 = 0;
    Label_0324:
        for (int i = 0; i < n3; ++i) {
            for (int j = 0; j < n2; ++j) {
                if ((array[j + i * n2] & 0xFFFFFF) != 0x0) {
                    n4 = i;
                    break Label_0324;
                }
            }
        }
        for (int k = 0; k < array.length; ++k) {
            if ((array[k] & 0xFFFFFF) == 0x0) {
                array[k] = 0;
            }
        }
        this.anInt2736 = maxAscent - n4;
        this.anInt2735 = height;
        this.anIntArray2738[n] = charWidth;
        this.aClass332Array2731[n] = ha.method1748(-7962, 0, n2, n3, array, n2);
    }
    
    public static void method3703() {
        Class326.anIntArray2733 = null;
    }
    
    final int method3704() {
        return this.anInt2736;
    }
    
    final int method3705() {
        return this.anInt2735 - 1;
    }
    
    final void method3706(final ha ha, final String s, final int n, final int n2, final int n3, final boolean b) {
        final int n4 = this.method3701(s) / 2;
        ha.K(this.anIntArray2734);
        if (n - n4 <= this.anIntArray2734[2] && n + n4 >= this.anIntArray2734[0] && n2 - this.anInt2736 <= this.anIntArray2734[3] && n2 + this.anInt2735 >= this.anIntArray2734[1]) {
            this.method3707(ha, s, this.anIntArray2734, n - n4, n2, n3, b);
        }
    }
    
    private final void method3707(final ha ha, final String s, final int[] array, int n, final int n2, int n3, boolean b) {
        if (n3 == 0) {
            b = false;
        }
        n3 |= 0xFF000000;
        for (int i = 0; i < s.length(); ++i) {
            final int n4 = Class326.anIntArray2733[s.charAt(i)];
            if (b) {
                this.aClass332Array2731[n4].method3748(n + 1, n2 + 1, 0, -16777216, 1);
            }
            this.aClass332Array2731[n4].method3748(n, n2, 0, n3, 1);
            n += this.anIntArray2738[n4];
        }
    }
    
    Class326(final ha ha, final int n, final boolean b, final Component component) {
        this.anIntArray2734 = new int[4];
        this.aBoolean2737 = false;
        this.aBoolean2737 = false;
        this.aClass332Array2731 = new Class332[256];
        this.anIntArray2738 = new int[256];
        final Font font = new Font("Helvetica", b ? 1 : 0, n);
        final FontMetrics fontMetrics = component.getFontMetrics(font);
        for (int i = 0; i < Class326.anInt2732; ++i) {
            this.method3702(ha, font, fontMetrics, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:'@#~,<.>/?\\| \u00c4\u00cb\u00cf\u00d6\u00dc\u00e4\u00eb\u00ef\u00f6\u00fc\u00ff\u00df\u00c1\u00c0\u00c9\u00c8\u00cd\u00cc\u00d3\u00d2\u00da\u00d9\u00e1\u00e0\u00e9\u00e8\u00ed\u00ec\u00f3\u00f2\u00fa\u00f9\u00c2\u00ca\u00ce\u00d4\u00db\u00e2\u00ea\u00ee\u00f4\u00fb\u00c6\u00e6\u00e3\u00c3\u00f5\u00d5\u00e7\u00c7".charAt(i), i, false);
        }
        if (b && this.aBoolean2737) {
            this.aBoolean2737 = false;
            final Font font2 = new Font("Helvetica", 0, n);
            final FontMetrics fontMetrics2 = component.getFontMetrics(font2);
            for (int j = 0; j < Class326.anInt2732; ++j) {
                this.method3702(ha, font2, fontMetrics2, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:'@#~,<.>/?\\| \u00c4\u00cb\u00cf\u00d6\u00dc\u00e4\u00eb\u00ef\u00f6\u00fc\u00ff\u00df\u00c1\u00c0\u00c9\u00c8\u00cd\u00cc\u00d3\u00d2\u00da\u00d9\u00e1\u00e0\u00e9\u00e8\u00ed\u00ec\u00f3\u00f2\u00fa\u00f9\u00c2\u00ca\u00ce\u00d4\u00db\u00e2\u00ea\u00ee\u00f4\u00fb\u00c6\u00e6\u00e3\u00c3\u00f5\u00d5\u00e7\u00c7".charAt(j), j, false);
            }
            if (!this.aBoolean2737) {
                this.aBoolean2737 = false;
                for (int k = 0; k < Class326.anInt2732; ++k) {
                    this.method3702(ha, font2, fontMetrics2, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:'@#~,<.>/?\\| \u00c4\u00cb\u00cf\u00d6\u00dc\u00e4\u00eb\u00ef\u00f6\u00fc\u00ff\u00df\u00c1\u00c0\u00c9\u00c8\u00cd\u00cc\u00d3\u00d2\u00da\u00d9\u00e1\u00e0\u00e9\u00e8\u00ed\u00ec\u00f3\u00f2\u00fa\u00f9\u00c2\u00ca\u00ce\u00d4\u00db\u00e2\u00ea\u00ee\u00f4\u00fb\u00c6\u00e6\u00e3\u00c3\u00f5\u00d5\u00e7\u00c7".charAt(k), k, true);
                }
            }
        }
    }
    
    static {
        Class326.anInt2732 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:'@#~,<.>/?\\| \u00c4\u00cb\u00cf\u00d6\u00dc\u00e4\u00eb\u00ef\u00f6\u00fc\u00ff\u00df\u00c1\u00c0\u00c9\u00c8\u00cd\u00cc\u00d3\u00d2\u00da\u00d9\u00e1\u00e0\u00e9\u00e8\u00ed\u00ec\u00f3\u00f2\u00fa\u00f9\u00c2\u00ca\u00ce\u00d4\u00db\u00e2\u00ea\u00ee\u00f4\u00fb\u00c6\u00e6\u00e3\u00c3\u00f5\u00d5\u00e7\u00c7".length();
        Class326.anIntArray2733 = new int[256];
        for (int i = 0; i < 256; ++i) {
            int index = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:'@#~,<.>/?\\| \u00c4\u00cb\u00cf\u00d6\u00dc\u00e4\u00eb\u00ef\u00f6\u00fc\u00ff\u00df\u00c1\u00c0\u00c9\u00c8\u00cd\u00cc\u00d3\u00d2\u00da\u00d9\u00e1\u00e0\u00e9\u00e8\u00ed\u00ec\u00f3\u00f2\u00fa\u00f9\u00c2\u00ca\u00ce\u00d4\u00db\u00e2\u00ea\u00ee\u00f4\u00fb\u00c6\u00e6\u00e3\u00c3\u00f5\u00d5\u00e7\u00c7".indexOf(i);
            if (index == -1) {
                index = 74;
            }
            Class326.anIntArray2733[i] = index;
        }
    }
}
