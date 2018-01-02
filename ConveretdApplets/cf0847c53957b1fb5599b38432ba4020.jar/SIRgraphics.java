import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class SIRgraphics
{
    private static Graphics pad;
    private static Color LineColor;
    private static int[] xpoints1;
    private static int[] ypoints1;
    private static int[] xpoints2;
    private static int[] ypoints2;
    private static int i;
    private static int j;
    private static int stA;
    private static int sgA;
    
    public static void DrawCol(final Image image, final int n, final int n2, final int n3, final int n4, final Color color, final boolean b, final Color color2) {
        (SIRgraphics.pad = image.getGraphics()).setColor(color);
        if (n2 < 0) {
            SIRgraphics.pad.fillRect(n3, n4, n, -n2);
        }
        else {
            SIRgraphics.pad.fillRect(n3, n4 - n2, n, n2);
        }
        if (b) {
            SIRgraphics.pad.setColor(color2);
            if (n2 < 0) {
                SIRgraphics.pad.drawRect(n3, n4, n, -n2);
            }
            else {
                SIRgraphics.pad.drawRect(n3, n4 - n2, n, n2);
            }
        }
    }
    
    public static void Draw3Dcol(final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final Color color, final boolean b, final Color color2) {
        SIRgraphics.pad = image.getGraphics();
        SIRgraphics.xpoints1 = new int[4];
        SIRgraphics.ypoints1 = new int[4];
        SIRgraphics.xpoints2 = new int[4];
        SIRgraphics.ypoints2 = new int[4];
        SIRgraphics.i = 7 * n5 / 10;
        if (n2 < 0) {
            SIRgraphics.xpoints1[0] = n3;
            SIRgraphics.ypoints1[0] = n4;
            SIRgraphics.xpoints1[1] = n3 + SIRgraphics.i;
            SIRgraphics.ypoints1[1] = n4 - SIRgraphics.i;
            SIRgraphics.xpoints1[2] = n3 + n + SIRgraphics.i;
            SIRgraphics.ypoints1[2] = n4 - SIRgraphics.i;
            SIRgraphics.xpoints1[3] = n3 + n;
            SIRgraphics.ypoints1[3] = n4;
        }
        else {
            SIRgraphics.xpoints1[0] = n3;
            SIRgraphics.ypoints1[0] = n4 - n2;
            SIRgraphics.xpoints1[1] = n3 + SIRgraphics.i;
            SIRgraphics.ypoints1[1] = n4 - n2 - SIRgraphics.i;
            SIRgraphics.xpoints1[2] = n3 + n + SIRgraphics.i;
            SIRgraphics.ypoints1[2] = n4 - n2 - SIRgraphics.i;
            SIRgraphics.xpoints1[3] = n3 + n;
            SIRgraphics.ypoints1[3] = n4 - n2;
        }
        SIRgraphics.xpoints2[0] = n3 + n;
        SIRgraphics.ypoints2[0] = n4 - n2;
        SIRgraphics.xpoints2[1] = n3 + n + SIRgraphics.i;
        SIRgraphics.ypoints2[1] = n4 - n2 - SIRgraphics.i;
        SIRgraphics.xpoints2[2] = n3 + n + SIRgraphics.i;
        SIRgraphics.ypoints2[2] = n4 - SIRgraphics.i;
        SIRgraphics.xpoints2[3] = n3 + n;
        SIRgraphics.ypoints2[3] = n4;
        SIRgraphics.pad.setColor(color);
        SIRgraphics.pad.fillPolygon(SIRgraphics.xpoints1, SIRgraphics.ypoints1, 4);
        SIRgraphics.pad.setColor(color.darker());
        SIRgraphics.pad.fillPolygon(SIRgraphics.xpoints2, SIRgraphics.ypoints2, 4);
        SIRgraphics.pad.setColor(color.brighter());
        SIRgraphics.pad.fillRect(n3, n4 - n2, n, n2);
        if (n2 < 0) {
            SIRgraphics.pad.fillRect(n3, n4, n, -n2);
        }
        else {
            SIRgraphics.pad.fillRect(n3, n4 - n2, n, n2);
        }
        if (b) {
            SIRgraphics.pad.setColor(color2);
            SIRgraphics.pad.drawPolygon(SIRgraphics.xpoints1, SIRgraphics.ypoints1, 4);
            SIRgraphics.pad.drawPolygon(SIRgraphics.xpoints2, SIRgraphics.ypoints2, 4);
            if (n2 < 0) {
                SIRgraphics.pad.drawRect(n3, n4, n, -n2);
            }
            else {
                SIRgraphics.pad.drawRect(n3, n4 - n2, n, n2);
            }
        }
    }
    
    public static void DrawBar(final Image image, final int n, final int n2, final int n3, final int n4, final Color color, final boolean b, final Color color2) {
        (SIRgraphics.pad = image.getGraphics()).setColor(color);
        if (n2 < 0) {
            SIRgraphics.pad.fillRect(n3 + n2, n4 - n, -n2, n);
        }
        else {
            SIRgraphics.pad.fillRect(n3, n4 - n, n2, n);
        }
        if (b) {
            SIRgraphics.pad.setColor(color2);
            if (n2 < 0) {
                SIRgraphics.pad.drawRect(n3 + n2, n4 - n, -n2, n);
            }
            else {
                SIRgraphics.pad.drawRect(n3, n4 - n, n2, n);
            }
        }
    }
    
    public static void Draw3Dbar(final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final Color color, final boolean b, final Color color2) {
        SIRgraphics.pad = image.getGraphics();
        SIRgraphics.xpoints1 = new int[4];
        SIRgraphics.ypoints1 = new int[4];
        SIRgraphics.xpoints2 = new int[4];
        SIRgraphics.ypoints2 = new int[4];
        SIRgraphics.i = 7 * n5 / 10;
        if (n2 < 0) {
            SIRgraphics.xpoints1[0] = n3;
            SIRgraphics.ypoints1[0] = n4;
            SIRgraphics.xpoints1[1] = n3 + SIRgraphics.i;
            SIRgraphics.ypoints1[1] = n4 - SIRgraphics.i;
            SIRgraphics.xpoints1[2] = n3 + SIRgraphics.i;
            SIRgraphics.ypoints1[2] = n4 - n - SIRgraphics.i;
            SIRgraphics.xpoints1[3] = n3;
            SIRgraphics.ypoints1[3] = n4 - n;
        }
        else {
            SIRgraphics.xpoints1[0] = n3 + n2;
            SIRgraphics.ypoints1[0] = n4;
            SIRgraphics.xpoints1[1] = n3 + n2;
            SIRgraphics.ypoints1[1] = n4 - n;
            SIRgraphics.xpoints1[2] = n3 + n2 + SIRgraphics.i;
            SIRgraphics.ypoints1[2] = n4 - n - SIRgraphics.i;
            SIRgraphics.xpoints1[3] = n3 + n2 + SIRgraphics.i;
            SIRgraphics.ypoints1[3] = n4 - SIRgraphics.i;
        }
        SIRgraphics.xpoints2[0] = n3;
        SIRgraphics.ypoints2[0] = n4 - n;
        SIRgraphics.xpoints2[1] = n3 + SIRgraphics.i;
        SIRgraphics.ypoints2[1] = n4 - n - SIRgraphics.i;
        SIRgraphics.xpoints2[2] = n3 + n2 + SIRgraphics.i;
        SIRgraphics.ypoints2[2] = n4 - n - SIRgraphics.i;
        SIRgraphics.xpoints2[3] = n3 + n2;
        SIRgraphics.ypoints2[3] = n4 - n;
        SIRgraphics.pad.setColor(color);
        SIRgraphics.pad.fillPolygon(SIRgraphics.xpoints1, SIRgraphics.ypoints1, 4);
        SIRgraphics.pad.setColor(color.darker());
        SIRgraphics.pad.fillPolygon(SIRgraphics.xpoints2, SIRgraphics.ypoints2, 4);
        SIRgraphics.pad.setColor(color.brighter());
        if (n2 < 0) {
            SIRgraphics.pad.fillRect(n3 + n2, n4 - n, -n2, n);
        }
        else {
            SIRgraphics.pad.fillRect(n3, n4 - n, n2, n);
        }
        if (b) {
            SIRgraphics.pad.setColor(color2);
            SIRgraphics.pad.drawPolygon(SIRgraphics.xpoints1, SIRgraphics.ypoints1, 4);
            SIRgraphics.pad.drawPolygon(SIRgraphics.xpoints2, SIRgraphics.ypoints2, 4);
            if (n2 < 0) {
                SIRgraphics.pad.drawRect(n3 + n2, n4 - n, -n2, n);
            }
            else {
                SIRgraphics.pad.drawRect(n3, n4 - n, n2, n);
            }
        }
    }
    
    public static void DrawPoint(final Image image, final int n, final int n2, final Color color, final int n3) {
        (SIRgraphics.pad = image.getGraphics()).setColor(color);
        switch (n3) {
            case 1: {
                SIRgraphics.pad.drawLine(n - 2, n2, n + 2, n2);
                SIRgraphics.pad.drawLine(n, n2 - 2, n, n2 + 2);
                break;
            }
            case 2: {
                SIRgraphics.pad.drawLine(n, n2, n, n2);
                SIRgraphics.pad.drawRect(n - 2, n2 - 2, 4, 4);
                break;
            }
            default: {
                SIRgraphics.pad.drawLine(n, n2, n, n2);
                break;
            }
        }
    }
    
    public static void Drawline(final Image image, final int n, final int n2, final int n3, final int n4, final Color color) {
        (SIRgraphics.pad = image.getGraphics()).setColor(color);
        SIRgraphics.pad.drawLine(n, n2, n3, n4);
    }
    
    public static void Draw3Dline(final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final Color color, final boolean b) {
        SIRgraphics.pad = image.getGraphics();
        SIRgraphics.i = 7 * n5 / 10;
        SIRgraphics.xpoints1 = new int[4];
        SIRgraphics.ypoints1 = new int[4];
        SIRgraphics.xpoints1[0] = n;
        SIRgraphics.ypoints1[0] = n2;
        SIRgraphics.xpoints1[1] = n + SIRgraphics.i;
        SIRgraphics.ypoints1[1] = n2 - SIRgraphics.i;
        SIRgraphics.xpoints1[2] = n3 + SIRgraphics.i;
        SIRgraphics.ypoints1[2] = n4 - SIRgraphics.i;
        SIRgraphics.xpoints1[3] = n3;
        SIRgraphics.ypoints1[3] = n4;
        if (n2 < n4) {
            SIRgraphics.pad.setColor(color.darker());
        }
        else {
            SIRgraphics.pad.setColor(color.brighter());
        }
        SIRgraphics.pad.fillPolygon(SIRgraphics.xpoints1, SIRgraphics.ypoints1, 4);
        if (b) {
            SIRgraphics.pad.setColor(color);
            SIRgraphics.pad.drawPolygon(SIRgraphics.xpoints1, SIRgraphics.ypoints1, 4);
        }
    }
    
    public static void DrawSegment(final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final Color color, final Color color2, final boolean b) {
        (SIRgraphics.pad = image.getGraphics()).setColor(color);
        SIRgraphics.pad.fillArc(n, n2, n3, n3, n4, n5);
        SIRgraphics.pad.setColor(color2);
        SIRgraphics.pad.drawArc(n, n2, n3, n3, n4, n5);
    }
    
    public static void Draw3Dsegment(final Image image, final int n, final int n2, final int n3, final int stA, final int sgA, final Color color, final int n4, final Color color2, final boolean b) {
        SIRgraphics.pad = image.getGraphics();
        SIRgraphics.stA = stA;
        SIRgraphics.sgA = sgA;
        if (SIRgraphics.stA + SIRgraphics.sgA > 180) {
            if (SIRgraphics.stA < 180) {
                SIRgraphics.stA = 180;
            }
            if (SIRgraphics.stA + SIRgraphics.sgA > 360) {
                SIRgraphics.sgA = 360 - SIRgraphics.stA;
            }
            SIRgraphics.pad.setColor(color.darker());
            SIRgraphics.j = 0;
            while (SIRgraphics.j < n4) {
                SIRgraphics.pad.drawArc(n, n2 + SIRgraphics.j, n3, 7 * n3 / 10, SIRgraphics.stA, SIRgraphics.sgA);
                ++SIRgraphics.j;
            }
        }
        SIRgraphics.pad.setColor(color);
        SIRgraphics.pad.fillArc(n, n2, n3, 7 * n3 / 10, stA, sgA);
        SIRgraphics.pad.setColor(color2);
        SIRgraphics.pad.drawArc(n, n2, n3, 7 * n3 / 10, stA, sgA);
    }
    
    public static void DrawAxis(final Image image, final Color color, final int n, final int n2, final int n3, final int n4, final int n5) {
        (SIRgraphics.pad = image.getGraphics()).setColor(color);
        SIRgraphics.pad.drawLine(n, n2, n + n3, n2);
        SIRgraphics.pad.drawLine(n, n2, n, n2 - n4);
        SIRgraphics.pad.drawLine(n, n2, n, n2 + n5);
    }
    
    public static void Draw3Daxis(final Image image, final Color color, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Color color2) {
        SIRgraphics.pad = image.getGraphics();
        SIRgraphics.i = 7 * n6 / 10;
        SIRgraphics.xpoints1 = new int[4];
        SIRgraphics.ypoints1 = new int[4];
        SIRgraphics.xpoints1[0] = n;
        SIRgraphics.ypoints1[0] = n2;
        SIRgraphics.xpoints1[1] = n + n3;
        SIRgraphics.ypoints1[1] = n2;
        SIRgraphics.xpoints1[2] = n + n3 + SIRgraphics.i;
        SIRgraphics.ypoints1[2] = n2 - SIRgraphics.i;
        SIRgraphics.xpoints1[3] = n + SIRgraphics.i;
        SIRgraphics.ypoints1[3] = n2 - SIRgraphics.i;
        SIRgraphics.pad.setColor(color2);
        SIRgraphics.pad.fillPolygon(SIRgraphics.xpoints1, SIRgraphics.ypoints1, 4);
        SIRgraphics.pad.setColor(color);
        SIRgraphics.pad.drawLine(n, n2, n + n3, n2);
        SIRgraphics.pad.drawLine(n, n2, n, n2 - n4);
        SIRgraphics.pad.drawLine(n, n2, n, n2 + n5);
        SIRgraphics.pad.drawLine(n + SIRgraphics.i, n2 - SIRgraphics.i, n + n3 + SIRgraphics.i, n2 - SIRgraphics.i);
        SIRgraphics.pad.drawLine(n + SIRgraphics.i, n2 - SIRgraphics.i, n + SIRgraphics.i, n2 - n4 - SIRgraphics.i);
        SIRgraphics.pad.drawLine(n + SIRgraphics.i, n2 - SIRgraphics.i, n + SIRgraphics.i, n2 + n5 - SIRgraphics.i);
        SIRgraphics.pad.drawLine(n, n2, n + SIRgraphics.i, n2 - SIRgraphics.i);
        SIRgraphics.pad.drawLine(n, n2 - n4, n + SIRgraphics.i, n2 - SIRgraphics.i - n4);
        SIRgraphics.pad.drawLine(n, n2 + n5, n + SIRgraphics.i, n2 - SIRgraphics.i + n5);
        SIRgraphics.pad.drawLine(n + n3, n2, n + SIRgraphics.i + n3, n2 - SIRgraphics.i);
    }
    
    public static void DrawAxis2(final Image image, final Color color, final int n, final int n2, final int n3, final int n4, final int n5) {
        (SIRgraphics.pad = image.getGraphics()).setColor(color);
        SIRgraphics.pad.drawLine(n, n2, n + n3, n2);
        SIRgraphics.pad.drawLine(n, n2, n - n5, n2);
        SIRgraphics.pad.drawLine(n, n2, n, n2 - n4);
    }
    
    public static void Draw3Daxis2(final Image image, final Color color, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Color color2) {
        SIRgraphics.pad = image.getGraphics();
        SIRgraphics.i = 7 * n6 / 10;
        SIRgraphics.xpoints1 = new int[4];
        SIRgraphics.ypoints1 = new int[4];
        SIRgraphics.xpoints1[0] = n;
        SIRgraphics.ypoints1[0] = n2;
        SIRgraphics.xpoints1[1] = n;
        SIRgraphics.ypoints1[1] = n2 - n4;
        SIRgraphics.xpoints1[2] = n + SIRgraphics.i;
        SIRgraphics.ypoints1[2] = n2 - SIRgraphics.i - n4;
        SIRgraphics.xpoints1[3] = n + SIRgraphics.i;
        SIRgraphics.ypoints1[3] = n2 - SIRgraphics.i;
        SIRgraphics.pad.setColor(color2);
        SIRgraphics.pad.fillPolygon(SIRgraphics.xpoints1, SIRgraphics.ypoints1, 4);
        SIRgraphics.pad.setColor(color);
        SIRgraphics.pad.drawLine(n, n2, n + n3, n2);
        SIRgraphics.pad.drawLine(n, n2, n - n5, n2);
        SIRgraphics.pad.drawLine(n, n2, n, n2 - n4);
        SIRgraphics.pad.drawLine(n + SIRgraphics.i, n2 - SIRgraphics.i, n + SIRgraphics.i + n3, n2 - SIRgraphics.i);
        SIRgraphics.pad.drawLine(n + SIRgraphics.i, n2 - SIRgraphics.i, n + SIRgraphics.i - n5, n2 - SIRgraphics.i);
        SIRgraphics.pad.drawLine(n + SIRgraphics.i, n2 - SIRgraphics.i, n + SIRgraphics.i, n2 - n4 - SIRgraphics.i);
        SIRgraphics.pad.drawLine(n, n2, n + SIRgraphics.i, n2 - SIRgraphics.i);
        SIRgraphics.pad.drawLine(n, n2 - n4, n + SIRgraphics.i, n2 - SIRgraphics.i - n4);
        SIRgraphics.pad.drawLine(n - n5, n2, n + SIRgraphics.i - n5, n2 - SIRgraphics.i);
        SIRgraphics.pad.drawLine(n + n3, n2, n + SIRgraphics.i + n3, n2 - SIRgraphics.i);
    }
    
    public static void Draw2Dgrid(final Image image, final Color color, final int n, final int n2, final int n3, final int n4, int n5, int n6) {
        (SIRgraphics.pad = image.getGraphics()).setColor(color);
        final int n7 = n * n5;
        final int n8 = n2 * n6;
        ++n5;
        ++n6;
        SIRgraphics.i = 0;
        while (SIRgraphics.i < n6) {
            SIRgraphics.pad.drawLine(n3 + SIRgraphics.i * n2, n4, n3 + SIRgraphics.i * n2, n4 - n7);
            ++SIRgraphics.i;
        }
        SIRgraphics.i = 0;
        while (SIRgraphics.i < n5) {
            SIRgraphics.pad.drawLine(n3, n4 - SIRgraphics.i * n, n3 + n8, n4 - SIRgraphics.i * n);
            ++SIRgraphics.i;
        }
    }
    
    public static void Draw3Dgrid(final Image image, final Color color, final int n, final int n2, final int n3, final int n4, int n5, int n6, final int n7) {
        (SIRgraphics.pad = image.getGraphics()).setColor(color);
        final int n8 = 7 * n7 / 10;
        final int n9 = n * n5;
        final int n10 = n2 * n6;
        ++n5;
        ++n6;
        SIRgraphics.i = 0;
        while (SIRgraphics.i < n6) {
            SIRgraphics.pad.drawLine(n3 + n8 + SIRgraphics.i * n2, n4 - n8, n3 + n8 + SIRgraphics.i * n2, n4 - n8 - n9);
            ++SIRgraphics.i;
        }
        SIRgraphics.i = 0;
        while (SIRgraphics.i < n5) {
            SIRgraphics.pad.drawLine(n3 + n8, n4 - n8 - SIRgraphics.i * n, n3 + n8 + n10, n4 - n8 - SIRgraphics.i * n);
            SIRgraphics.pad.drawLine(n3, n4 - SIRgraphics.i * n, n3 + n8, n4 - n8 - SIRgraphics.i * n);
            ++SIRgraphics.i;
        }
    }
    
    public static void Draw3Dgrid2(final Image image, final Color color, final int n, final int n2, final int n3, final int n4, int n5, int n6, final int n7) {
        (SIRgraphics.pad = image.getGraphics()).setColor(color);
        final int n8 = 7 * n7 / 10;
        final int n9 = n * n5;
        final int n10 = n2 * n6;
        ++n5;
        ++n6;
        SIRgraphics.i = 0;
        while (SIRgraphics.i < n6) {
            SIRgraphics.pad.drawLine(n3 + n8 + SIRgraphics.i * n2, n4 - n8, n3 + n8 + SIRgraphics.i * n2, n4 - n8 - n9);
            SIRgraphics.pad.drawLine(n3 + SIRgraphics.i * n2, n4, n3 + n8 + SIRgraphics.i * n2, n4 - n8);
            ++SIRgraphics.i;
        }
        SIRgraphics.i = 0;
        while (SIRgraphics.i < n5) {
            SIRgraphics.pad.drawLine(n3 + n8, n4 - n8 - SIRgraphics.i * n, n3 + n8 + n10, n4 - n8 - SIRgraphics.i * n);
            ++SIRgraphics.i;
        }
    }
    
    public static void DrawLabel(final Image image, final String s, final Font font, final Color color, final int n, final int n2, final int n3) {
        (SIRgraphics.pad = image.getGraphics()).setColor(color);
        SIRgraphics.pad.setFont(font);
        final FontMetrics fontMetrics = SIRgraphics.pad.getFontMetrics();
        final int length = s.length();
        switch (n3) {
            case 1: {
                final int height = fontMetrics.getHeight();
                SIRgraphics.i = 0;
                while (SIRgraphics.i < length) {
                    SIRgraphics.pad.drawString(s.substring(SIRgraphics.i, SIRgraphics.i + 1), n, n2 - height * (length - SIRgraphics.i));
                    ++SIRgraphics.i;
                }
                break;
            }
            case 2: {
                final int n4 = 7 * fontMetrics.getHeight() / 10;
                SIRgraphics.i = 0;
                while (SIRgraphics.i < length) {
                    SIRgraphics.pad.drawString(s.substring(SIRgraphics.i, SIRgraphics.i + 1), n + SIRgraphics.i * n4, n2 - SIRgraphics.i * n4);
                    ++SIRgraphics.i;
                }
                break;
            }
            case 3: {
                final int height2 = fontMetrics.getHeight();
                SIRgraphics.i = 0;
                while (SIRgraphics.i < length) {
                    SIRgraphics.pad.drawString(s.substring(SIRgraphics.i, SIRgraphics.i + 1), n, n2 - SIRgraphics.i * height2);
                    ++SIRgraphics.i;
                }
                break;
            }
            case 4: {
                SIRgraphics.pad.drawString(s, n - fontMetrics.stringWidth(s), n2);
                break;
            }
            case 5: {
                SIRgraphics.pad.drawString(s, n, n2 + fontMetrics.getHeight());
                break;
            }
            default: {
                SIRgraphics.pad.drawString(s, n, n2);
                break;
            }
        }
    }
    
    public static void DrawTextBox(final Image image, final String s, final Font font, final Color color, final Color color2, final int n, final int n2) {
        (SIRgraphics.pad = image.getGraphics()).setFont(font);
        final FontMetrics fontMetrics = SIRgraphics.pad.getFontMetrics();
        SIRgraphics.pad.setColor(color2);
        SIRgraphics.pad.fillRect(n - 4, n2 - fontMetrics.getHeight(), fontMetrics.stringWidth(s) + 8, fontMetrics.getHeight() + 4);
        SIRgraphics.pad.setColor(color);
        SIRgraphics.pad.drawRect(n - 4, n2 - fontMetrics.getHeight(), fontMetrics.stringWidth(s) + 8, fontMetrics.getHeight() + 4);
    }
}
