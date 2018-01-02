import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class vbarchart
{
    private static Graphics pad;
    private static int i;
    private static int j;
    private static int iTemp;
    private static int xTemp;
    private static int yTemp;
    private static double dTemp;
    private static String sTemp;
    private static ImageObserver Iobs;
    private static int[] xpoints1;
    private static int[] ypoints1;
    private static int[] xpoints2;
    private static int[] ypoints2;
    
    public static String DoubleToStr(final double n, int max) {
        max = Math.max(0, max);
        if (max == 0 && n < 1000.0 && n > -1000.0) {
            return Math.round(n) + "";
        }
        final long n2 = (long)Math.pow(10.0, max);
        final long round = Math.round(n * n2);
        final long n3 = Math.abs(round) % n2 + n2;
        String s = "";
        if (max > 0) {
            s = "." + (n3 + "").substring(1);
        }
        long n4 = round / n2;
        if (n4 == 0L) {
            String s2;
            if (n < 0.0) {
                s2 = "-0" + s;
            }
            else {
                s2 = "0" + s;
            }
            return s2;
        }
        if (n4 > 0L) {
            while (n4 >= 1L) {
                final long n5 = n4 % 1000L + 1000L;
                if (n5 >= 1000L && n4 >= 1000L) {
                    s = " " + (n5 + "").substring(1) + s;
                }
                else {
                    s = n4 % 1000L + s;
                }
                n4 /= 1000L;
            }
        }
        else if (n4 < 0L) {
            while (n4 <= -1L) {
                final long n6 = n4 % 1000L - 1000L;
                if (n6 <= -1000L && n4 <= -1000L) {
                    s = " " + (n6 + "").substring(2) + s;
                }
                else {
                    s = n4 % 1000L + s;
                }
                n4 /= 1000L;
            }
        }
        return s;
    }
    
    public static String FormatStr(final String s, int n, final int n2) {
        String s2 = "";
        final int length = s.length();
        if (n < 0) {
            n = length;
        }
        String s3 = null;
        switch (n2) {
            case 1: {
                if (length > n) {
                    s3 = s.substring((length - n) / 2, n);
                }
                else {
                    final int n3 = (n - length) / 2;
                    for (int i = 0; i < n3; ++i) {
                        s2 += " ";
                    }
                    s3 = s2 + s;
                    for (int j = 0; j < n - length - n3; ++j) {
                        s3 += " ";
                    }
                }
                break;
            }
            case 2: {
                if (length > n) {
                    s3 = s.substring(length - n, n);
                }
                else {
                    for (int k = length; k < n; ++k) {
                        s2 += " ";
                    }
                    s3 = s2 + s;
                }
                break;
            }
            default: {
                if (length > n) {
                    s3 = s.substring(0, n);
                    break;
                }
                s3 = s;
                for (int l = length; l < n; ++l) {
                    s3 += " ";
                }
                break;
            }
        }
        return s3;
    }
    
    public static void DrawCol(final Image image, final int n, final int n2, final int n3, final int n4, final Color color, final boolean b, final Color color2) {
        (vbarchart.pad = image.getGraphics()).setColor(color);
        if (n2 < 0) {
            vbarchart.pad.fillRect(n3, n4, n, -n2);
        }
        else {
            vbarchart.pad.fillRect(n3, n4 - n2, n, n2);
        }
        if (b) {
            vbarchart.pad.setColor(color2);
            if (n2 < 0) {
                vbarchart.pad.drawRect(n3, n4, n, -n2);
            }
            else {
                vbarchart.pad.drawRect(n3, n4 - n2, n, n2);
            }
        }
    }
    
    public static void Draw3Dgrid(final Image image, final Color color, final int n, final int n2, final int n3, final int n4, int n5, int n6, final int n7) {
        (vbarchart.pad = image.getGraphics()).setColor(color);
        final int n8 = 7 * n7 / 10;
        final int n9 = n * n5;
        final int n10 = n2 * n6;
        ++n5;
        ++n6;
        for (int i = 0; i < n6; ++i) {
            vbarchart.pad.drawLine(n3 + n8 + i * n2, n4 - n8, n3 + n8 + i * n2, n4 - n8 - n9);
        }
        for (int j = 0; j < n5; ++j) {
            vbarchart.pad.drawLine(n3 + n8, n4 - n8 - j * n, n3 + n8 + n10, n4 - n8 - j * n);
            vbarchart.pad.drawLine(n3, n4 - j * n, n3 + n8, n4 - n8 - j * n);
        }
    }
    
    public static void Draw2Dgrid(final Image image, final Color color, final int n, final int n2, final int n3, final int n4, int n5, int n6) {
        (vbarchart.pad = image.getGraphics()).setColor(color);
        final int n7 = n * n5;
        final int n8 = n2 * n6;
        ++n5;
        ++n6;
        for (int i = 0; i < n6; ++i) {
            vbarchart.pad.drawLine(n3 + i * n2, n4, n3 + i * n2, n4 - n7);
        }
        for (int j = 0; j < n5; ++j) {
            vbarchart.pad.drawLine(n3, n4 - j * n, n3 + n8, n4 - j * n);
        }
    }
    
    public static void Draw3Daxis(final Image image, final Color color, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Color color2) {
        vbarchart.pad = image.getGraphics();
        final int n7 = 7 * n6 / 10;
        vbarchart.xpoints1 = new int[4];
        vbarchart.ypoints1 = new int[4];
        vbarchart.xpoints1[0] = n;
        vbarchart.ypoints1[0] = n2;
        vbarchart.xpoints1[1] = n + n3;
        vbarchart.ypoints1[1] = n2;
        vbarchart.xpoints1[2] = n + n3 + n7;
        vbarchart.ypoints1[2] = n2 - n7;
        vbarchart.xpoints1[3] = n + n7;
        vbarchart.ypoints1[3] = n2 - n7;
        vbarchart.pad.setColor(color2);
        vbarchart.pad.fillPolygon(vbarchart.xpoints1, vbarchart.ypoints1, 4);
        vbarchart.pad.setColor(color);
        vbarchart.pad.drawLine(n, n2, n + n3, n2);
        vbarchart.pad.drawLine(n, n2, n, n2 - n4);
        vbarchart.pad.drawLine(n, n2, n, n2 + n5);
        vbarchart.pad.drawLine(n + n7, n2 - n7, n + n3 + n7, n2 - n7);
        vbarchart.pad.drawLine(n + n7, n2 - n7, n + n7, n2 - n4 - n7);
        vbarchart.pad.drawLine(n + n7, n2 - n7, n + n7, n2 + n5 - n7);
        vbarchart.pad.drawLine(n, n2, n + n7, n2 - n7);
        vbarchart.pad.drawLine(n, n2 - n4, n + n7, n2 - n7 - n4);
        vbarchart.pad.drawLine(n, n2 + n5, n + n7, n2 - n7 + n5);
        vbarchart.pad.drawLine(n + n3, n2, n + n7 + n3, n2 - n7);
    }
    
    public static void vbarchart(final Image image, final double[][] array, final boolean[][] array2, final String[] array3, final int[][] array4, final Image[] array5, final double n, final double n2, final Color[][] array6, final boolean[] array7, final int[] array8, final String[] array9, final int[][] array10, final Font[] array11, final Color[] array12, final String[] array13) {
        final int n3 = (array8[4] + 5) * array8[3] + 5;
        vbarchart.pad = image.getGraphics();
        vbarchart.i = 0;
        while (vbarchart.i < 5) {
            if (array5[vbarchart.i] != null) {
                vbarchart.pad.drawImage(array5[vbarchart.i], array4[vbarchart.i][0], array4[vbarchart.i][1], vbarchart.Iobs);
            }
            ++vbarchart.i;
        }
        if (array7[1]) {
            if (array7[0]) {
                Draw3Dgrid(image, array12[13], array8[2], n3, array8[5], array8[6], array8[1], array8[0], array8[7]);
            }
            else {
                Draw2Dgrid(image, array12[13], array8[2], n3, array8[5], array8[6], array8[1], array8[0]);
            }
        }
        if (array7[2]) {
            vbarchart.dTemp = new Double(array8[2]);
            if (n < 0.0) {
                vbarchart.yTemp = array8[6] + (int)Math.round(vbarchart.dTemp * n / n2);
            }
            else {
                vbarchart.yTemp = array8[6];
            }
            if (array7[0]) {
                Draw3Daxis(image, array12[14], array8[5], vbarchart.yTemp, n3 * array8[0], array8[2] * array8[1] - array8[6] + vbarchart.yTemp, array8[6] - vbarchart.yTemp, array8[7], array12[15]);
            }
            else {
                DrawAxis(image, array12[14], array8[5], vbarchart.yTemp, n3 * array8[0], array8[2] * array8[1] - array8[6] + vbarchart.yTemp, array8[6] - vbarchart.yTemp);
            }
        }
        if (array9[10] != "") {
            DrawLabel(image, array9[10], array11[10], array12[10], array10[10][0], array10[10][1], 0);
        }
        if (array9[11] != "") {
            DrawLabel(image, array9[11], array11[11], array12[11], array10[11][0], array10[11][1], 0);
        }
        if (array9[12] != "") {
            DrawLabel(image, array9[12], array11[12], array12[12], array10[12][0], array10[12][1], 1);
        }
        vbarchart.i = 0;
        while (vbarchart.i < array8[0]) {
            if (array3[vbarchart.i] != "") {
                DrawLabel(image, array3[vbarchart.i], array11[14], array12[17], array8[5] + 5 + vbarchart.i * n3, array8[10], array8[9]);
            }
            ++vbarchart.i;
        }
        if (array7[3]) {
            vbarchart.i = 0;
            while (vbarchart.i < array8[1] + 1) {
                vbarchart.dTemp = new Double(vbarchart.i);
                vbarchart.dTemp = n + vbarchart.dTemp * n2;
                vbarchart.sTemp = DoubleToStr(vbarchart.dTemp, array8[8]);
                DrawLabel(image, vbarchart.sTemp = FormatStr(vbarchart.sTemp, -1, 2), array11[13], array12[17], array8[5] - 5, array8[6] - vbarchart.i * array8[2], 4);
                ++vbarchart.i;
            }
        }
        if (array7[5]) {
            final int n4 = array10[13][0];
            final int n5 = array10[13][1];
            vbarchart.pad.setFont(array11[15]);
            final FontMetrics fontMetrics = vbarchart.pad.getFontMetrics();
            int n6 = 0;
            if (array9[13] != null) {
                n6 = fontMetrics.stringWidth(array9[13]);
            }
            vbarchart.i = 0;
            while (vbarchart.i < array8[3]) {
                if (array13[vbarchart.i] != null && fontMetrics.stringWidth(array13[vbarchart.i]) > n6) {
                    n6 = fontMetrics.stringWidth(array13[vbarchart.i]);
                }
                ++vbarchart.i;
            }
            final int n7 = n6 + 45;
            int height = 15;
            if (fontMetrics.getHeight() > height) {
                height = fontMetrics.getHeight();
            }
            int n8 = 20 + (height + 10) * array8[3];
            if (array9[13] != null) {
                n8 = n8 + fontMetrics.getHeight() + 10;
            }
            vbarchart.pad.setColor(array12[20]);
            vbarchart.pad.fillRect(n4, n5, n7, n8);
            vbarchart.pad.setColor(array12[21]);
            vbarchart.pad.drawRect(n4, n5, n7, n8);
            int n9 = n5 + 10;
            if (array9[13] != null) {
                final int n10 = n9 + fontMetrics.getHeight();
                vbarchart.pad.setColor(array12[22]);
                vbarchart.pad.drawString(array9[13], n4 + n7 / 2 - fontMetrics.stringWidth(array9[13]) / 2, n10);
                n9 = n10 + 10;
            }
            vbarchart.i = 0;
            while (vbarchart.i < array8[3]) {
                final int n11 = n9 + height;
                if (array13[vbarchart.i] != null) {
                    vbarchart.pad.setColor(array12[22]);
                    vbarchart.pad.drawString(array13[vbarchart.i], n4 + 35, n11);
                }
                vbarchart.pad.setColor(array6[0][vbarchart.i]);
                vbarchart.pad.fillRect(n4 + 10, n11 - 15, 15, 15);
                vbarchart.pad.setColor(array12[21]);
                vbarchart.pad.drawRect(n4 + 10, n11 - 15, 15, 15);
                n9 = n11 + 10;
                ++vbarchart.i;
            }
        }
        vbarchart.i = 0;
        while (vbarchart.i < array8[0]) {
            vbarchart.j = 0;
            while (vbarchart.j < array8[3]) {
                vbarchart.dTemp = new Double(array8[2]);
                if (n < 0.0) {
                    vbarchart.yTemp = array8[6] + (int)Math.round(vbarchart.dTemp * n / n2);
                }
                else {
                    vbarchart.yTemp = array8[6];
                }
                vbarchart.dTemp = new Double(array8[2]);
                vbarchart.iTemp = (int)Math.round(vbarchart.dTemp * (array[vbarchart.i][vbarchart.j] - n) / n2);
                vbarchart.iTemp = vbarchart.iTemp - array8[6] + vbarchart.yTemp;
                vbarchart.xTemp = array8[5] + 5 + (vbarchart.i * array8[3] + vbarchart.j) * (array8[4] + 5) + vbarchart.i * 5;
                if (array7[0]) {
                    Draw3Dcol(image, array8[4], vbarchart.iTemp, vbarchart.xTemp, vbarchart.yTemp, array8[7], array6[vbarchart.i][vbarchart.j], array7[4], array12[16]);
                }
                else {
                    DrawCol(image, array8[4], vbarchart.iTemp, vbarchart.xTemp, vbarchart.yTemp, array6[vbarchart.i][vbarchart.j], array7[4], array12[16]);
                }
                if (array2[vbarchart.i][vbarchart.j]) {
                    vbarchart.sTemp = DoubleToStr(array[vbarchart.i][vbarchart.j], array8[8]);
                    DrawTextBox(image, vbarchart.sTemp = FormatStr(vbarchart.sTemp, -1, 2), array11[13], array12[17], array12[19], vbarchart.xTemp, vbarchart.yTemp - vbarchart.iTemp - 5);
                    DrawLabel(image, vbarchart.sTemp, array11[13], array12[17], vbarchart.xTemp, vbarchart.yTemp - vbarchart.iTemp - 5, 0);
                }
                ++vbarchart.j;
            }
            ++vbarchart.i;
        }
        vbarchart.i = 5;
        while (vbarchart.i < 10) {
            if (array5[vbarchart.i] != null) {
                vbarchart.pad.drawImage(array5[vbarchart.i], array4[vbarchart.i][0], array4[vbarchart.i][1], vbarchart.Iobs);
            }
            ++vbarchart.i;
        }
        vbarchart.i = 0;
        while (vbarchart.i < 10) {
            if (array9[vbarchart.i] != "") {
                DrawLabel(image, array9[vbarchart.i], array11[vbarchart.i], array12[vbarchart.i], array10[vbarchart.i][0], array10[vbarchart.i][1], 0);
            }
            ++vbarchart.i;
        }
    }
    
    public static void Draw3Dcol(final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final Color color, final boolean b, final Color color2) {
        vbarchart.pad = image.getGraphics();
        vbarchart.xpoints1 = new int[4];
        vbarchart.ypoints1 = new int[4];
        vbarchart.xpoints2 = new int[4];
        vbarchart.ypoints2 = new int[4];
        final int n6 = 7 * n5 / 10;
        if (n2 < 0) {
            vbarchart.xpoints1[0] = n3;
            vbarchart.ypoints1[0] = n4;
            vbarchart.xpoints1[1] = n3 + n6;
            vbarchart.ypoints1[1] = n4 - n6;
            vbarchart.xpoints1[2] = n3 + n + n6;
            vbarchart.ypoints1[2] = n4 - n6;
            vbarchart.xpoints1[3] = n3 + n;
            vbarchart.ypoints1[3] = n4;
        }
        else {
            vbarchart.xpoints1[0] = n3;
            vbarchart.ypoints1[0] = n4 - n2;
            vbarchart.xpoints1[1] = n3 + n6;
            vbarchart.ypoints1[1] = n4 - n2 - n6;
            vbarchart.xpoints1[2] = n3 + n + n6;
            vbarchart.ypoints1[2] = n4 - n2 - n6;
            vbarchart.xpoints1[3] = n3 + n;
            vbarchart.ypoints1[3] = n4 - n2;
        }
        vbarchart.xpoints2[0] = n3 + n;
        vbarchart.ypoints2[0] = n4 - n2;
        vbarchart.xpoints2[1] = n3 + n + n6;
        vbarchart.ypoints2[1] = n4 - n2 - n6;
        vbarchart.xpoints2[2] = n3 + n + n6;
        vbarchart.ypoints2[2] = n4 - n6;
        vbarchart.xpoints2[3] = n3 + n;
        vbarchart.ypoints2[3] = n4;
        vbarchart.pad.setColor(color);
        vbarchart.pad.fillPolygon(vbarchart.xpoints1, vbarchart.ypoints1, 4);
        vbarchart.pad.setColor(color.darker());
        vbarchart.pad.fillPolygon(vbarchart.xpoints2, vbarchart.ypoints2, 4);
        vbarchart.pad.setColor(color.brighter());
        vbarchart.pad.fillRect(n3, n4 - n2, n, n2);
        if (n2 < 0) {
            vbarchart.pad.fillRect(n3, n4, n, -n2);
        }
        else {
            vbarchart.pad.fillRect(n3, n4 - n2, n, n2);
        }
        if (b) {
            vbarchart.pad.setColor(color2);
            vbarchart.pad.drawPolygon(vbarchart.xpoints1, vbarchart.ypoints1, 4);
            vbarchart.pad.drawPolygon(vbarchart.xpoints2, vbarchart.ypoints2, 4);
            if (n2 < 0) {
                vbarchart.pad.drawRect(n3, n4, n, -n2);
            }
            else {
                vbarchart.pad.drawRect(n3, n4 - n2, n, n2);
            }
        }
    }
    
    public static void DrawAxis(final Image image, final Color color, final int n, final int n2, final int n3, final int n4, final int n5) {
        (vbarchart.pad = image.getGraphics()).setColor(color);
        vbarchart.pad.drawLine(n, n2, n + n3, n2);
        vbarchart.pad.drawLine(n, n2, n, n2 - n4);
        vbarchart.pad.drawLine(n, n2, n, n2 + n5);
    }
    
    public static void DrawTextBox(final Image image, final String s, final Font font, final Color color, final Color color2, final int n, final int n2) {
        (vbarchart.pad = image.getGraphics()).setFont(font);
        final FontMetrics fontMetrics = vbarchart.pad.getFontMetrics();
        vbarchart.pad.setColor(color2);
        vbarchart.pad.fillRect(n - 4, n2 - fontMetrics.getHeight(), fontMetrics.stringWidth(s) + 8, fontMetrics.getHeight() + 4);
        vbarchart.pad.setColor(color);
        vbarchart.pad.drawRect(n - 4, n2 - fontMetrics.getHeight(), fontMetrics.stringWidth(s) + 8, fontMetrics.getHeight() + 4);
    }
    
    public static void DrawLabel(final Image image, final String s, final Font font, final Color color, final int n, final int n2, final int n3) {
        (vbarchart.pad = image.getGraphics()).setColor(color);
        vbarchart.pad.setFont(font);
        final FontMetrics fontMetrics = vbarchart.pad.getFontMetrics();
        final int length = s.length();
        switch (n3) {
            case 1: {
                final int height = fontMetrics.getHeight();
                for (int i = 0; i < length; ++i) {
                    vbarchart.pad.drawString(s.substring(i, i + 1), n, n2 - height * (length - i));
                }
                break;
            }
            case 2: {
                final int n4 = 7 * fontMetrics.getHeight() / 10;
                for (int j = 0; j < length; ++j) {
                    vbarchart.pad.drawString(s.substring(j, j + 1), n + j * n4, n2 - j * n4);
                }
                break;
            }
            case 3: {
                final int height2 = fontMetrics.getHeight();
                for (int k = 0; k < length; ++k) {
                    vbarchart.pad.drawString(s.substring(k, k + 1), n, n2 - k * height2);
                }
                break;
            }
            case 4: {
                vbarchart.pad.drawString(s, n - fontMetrics.stringWidth(s), n2);
                break;
            }
            case 5: {
                vbarchart.pad.drawString(s, n, n2 + fontMetrics.getHeight());
                break;
            }
            default: {
                vbarchart.pad.drawString(s, n, n2);
                break;
            }
        }
    }
}
