import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class piechart
{
    private static Graphics pad;
    private static int i;
    private static int j;
    private static int Cx;
    private static int Cy;
    private static int iTemp;
    private static int i2Temp;
    private static int startAngle;
    private static int segAngle;
    private static int xTemp;
    private static int yTemp;
    private static int xTemp2;
    private static int yTemp2;
    private static int yTempNeg;
    private static int yPos;
    private static double dTemp;
    private static double dTemp2;
    private static double size;
    private static double radangle;
    private static String sTemp;
    private static ImageObserver Iobs;
    
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
    
    public static void piechart(final Image image, final double[] array, final boolean[] array2, final String[] array3, final int[][] array4, final Image[] array5, final Color[] array6, final boolean[] array7, final int[] array8, final String[] array9, final int[][] array10, final Font[] array11, final Color[] array12, final String[] array13, int n) {
        if (n > 90) {
            n = 90;
        }
        if (n < 1) {
            n = 1;
        }
        if (!array7[0]) {
            n = 90;
            array8[7] = 0;
        }
        piechart.pad = image.getGraphics();
        piechart.i = 0;
        while (piechart.i < 5) {
            if (array5[piechart.i] != null) {
                piechart.pad.drawImage(array5[piechart.i], array4[piechart.i][0], array4[piechart.i][1], piechart.Iobs);
            }
            ++piechart.i;
        }
        if (array9[10] != "") {
            DrawLabel(image, array9[10], array11[10], array12[10], array10[10][0], array10[10][1], 0);
        }
        for (int i = array8[7]; i > -1; --i) {
            piechart.startAngle = 0;
            piechart.dTemp = 0.0;
            piechart.i = 0;
            while (piechart.i < array8[0]) {
                piechart.dTemp += array[piechart.i];
                ++piechart.i;
            }
            piechart.i = 0;
            while (piechart.i < array8[0]) {
                piechart.segAngle = (int)Math.round(360.0 * array[piechart.i] / piechart.dTemp);
                if (piechart.i == array8[0] - 1 && piechart.startAngle + piechart.segAngle < 360) {
                    piechart.segAngle = 360 - piechart.startAngle;
                }
                Draw3Dsegment(image, array8[5], array8[6], array8[3], array8[1], piechart.startAngle, piechart.segAngle, array6[piechart.i], array8[7], n, i);
                piechart.startAngle += piechart.segAngle;
                ++piechart.i;
            }
        }
        piechart.startAngle = 0;
        piechart.size = new Double(array8[3] + 5 + 2 * array8[1]);
        piechart.Cx = array8[5] + array8[3] / 2;
        piechart.Cy = array8[6] + n * array8[3] / 180;
        piechart.pad.setFont(array11[14]);
        final FontMetrics fontMetrics = piechart.pad.getFontMetrics();
        piechart.i = 0;
        while (piechart.i < array8[0]) {
            piechart.segAngle = (int)Math.round(180.0 * array[piechart.i] / piechart.dTemp);
            piechart.startAngle += piechart.segAngle;
            piechart.radangle = new Double(piechart.startAngle) / 57.29577951308232;
            if (array7[0]) {
                piechart.xTemp = piechart.Cx + (int)Math.round(Math.cos(piechart.radangle) * piechart.size / 2.0);
                piechart.yTemp = piechart.Cy - (int)Math.round(Math.sin(piechart.radangle) * n * piechart.size / 180.0);
            }
            else {
                piechart.dTemp2 = piechart.size * Math.cos(piechart.radangle) / 2.0;
                piechart.xTemp = piechart.Cx + (int)Math.round(piechart.dTemp2);
                piechart.dTemp2 = piechart.size * Math.sin(piechart.radangle) / 2.0;
                piechart.yTemp = piechart.Cy - (int)Math.round(piechart.dTemp2);
            }
            if (array3[piechart.i] != "" && array3[piechart.i] != null) {
                if (piechart.xTemp < piechart.Cx) {
                    piechart.iTemp = fontMetrics.stringWidth(array3[piechart.i]);
                }
                else {
                    piechart.iTemp = 0;
                }
                if (piechart.yTemp < piechart.Cy) {
                    piechart.i2Temp = fontMetrics.getHeight();
                }
                else if (array7[0]) {
                    piechart.i2Temp = -array8[7];
                }
                else {
                    piechart.i2Temp = 0;
                }
                if (array7[3]) {
                    DrawLabel(image, array3[piechart.i], array11[14], array12[17], piechart.xTemp - piechart.iTemp, piechart.yTemp - piechart.i2Temp, array8[9]);
                }
            }
            if (array2[piechart.i]) {
                piechart.sTemp = DoubleToStr(array[piechart.i], array8[8]);
                DrawTextBox(image, piechart.sTemp = FormatStr(piechart.sTemp, -1, 2), array11[13], array12[17], array12[19], piechart.xTemp, piechart.yTemp);
                DrawLabel(image, piechart.sTemp, array11[13], array12[17], piechart.xTemp, piechart.yTemp, 0);
            }
            piechart.startAngle += piechart.segAngle;
            ++piechart.i;
        }
        if (array7[5]) {
            final int length = array13.length;
            final int n2 = array10[13][0];
            final int n3 = array10[13][1];
            piechart.pad.setFont(array11[15]);
            final FontMetrics fontMetrics2 = piechart.pad.getFontMetrics();
            int n4 = 0;
            if (array9[13] != null) {
                n4 = fontMetrics2.stringWidth(array9[13]);
            }
            piechart.i = 0;
            while (piechart.i < length) {
                if (array13[piechart.i] != null && fontMetrics2.stringWidth(array13[piechart.i]) > n4) {
                    n4 = fontMetrics2.stringWidth(array13[piechart.i]);
                }
                ++piechart.i;
            }
            final int n5 = n4 + 45;
            int height = 15;
            if (fontMetrics2.getHeight() > height) {
                height = fontMetrics2.getHeight();
            }
            int n6 = 20 + (height + 10) * length;
            if (array9[13] != null) {
                n6 = n6 + fontMetrics2.getHeight() + 10;
            }
            piechart.pad.setColor(array12[20]);
            piechart.pad.fillRect(n2, n3, n5, n6);
            piechart.pad.setColor(array12[21]);
            piechart.pad.drawRect(n2, n3, n5, n6);
            int n7 = n3 + 10;
            if (array9[13] != null) {
                final int n8 = n7 + fontMetrics2.getHeight();
                piechart.pad.setColor(array12[22]);
                piechart.pad.drawString(array9[13], n2 + n5 / 2 - fontMetrics2.stringWidth(array9[13]) / 2, n8);
                n7 = n8 + 10;
            }
            piechart.i = 0;
            while (piechart.i < length) {
                final int n9 = n7 + height;
                if (array13[piechart.i] != null) {
                    piechart.pad.setColor(array12[22]);
                    piechart.pad.drawString(array13[piechart.i], n2 + 35, n9);
                }
                piechart.pad.setColor(array6[piechart.i]);
                piechart.pad.fillRect(n2 + 10, n9 - 15, 15, 15);
                piechart.pad.setColor(array12[21]);
                piechart.pad.drawRect(n2 + 10, n9 - 15, 15, 15);
                n7 = n9 + 10;
                ++piechart.i;
            }
        }
        piechart.i = 5;
        while (piechart.i < 10) {
            if (array5[piechart.i] != null) {
                piechart.pad.drawImage(array5[piechart.i], array4[piechart.i][0], array4[piechart.i][1], piechart.Iobs);
            }
            ++piechart.i;
        }
        piechart.i = 0;
        while (piechart.i < 10) {
            if (array9[piechart.i] != "") {
                DrawLabel(image, array9[piechart.i], array11[piechart.i], array12[piechart.i], array10[piechart.i][0], array10[piechart.i][1], 0);
            }
            ++piechart.i;
        }
    }
    
    public static void Draw3Dsegment(final Image image, int n, int n2, final int n3, final int n4, final int n5, final int n6, final Color color, final int n7, int n8, final int n9) {
        if (n8 > 90) {
            n8 = 90;
        }
        if (n8 < 1) {
            n8 = 1;
        }
        final double n10 = new Double(n5 + n6 / 2) / 57.29577951308232;
        n += (int)Math.round(Math.cos(n10) * n4);
        n2 -= (int)Math.round(Math.sin(n10) * n8 * n4 / 90.0);
        piechart.pad = image.getGraphics();
        if (n9 == 0) {
            piechart.pad.setColor(color);
        }
        else {
            piechart.pad.setColor(color.darker());
        }
        piechart.pad.fillArc(n, n2 + n9, n3, n8 * n3 / 90, n5, n6);
    }
    
    public static void DrawTextBox(final Image image, final String s, final Font font, final Color color, final Color color2, final int n, final int n2) {
        (piechart.pad = image.getGraphics()).setFont(font);
        final FontMetrics fontMetrics = piechart.pad.getFontMetrics();
        piechart.pad.setColor(color2);
        piechart.pad.fillRect(n - 4, n2 - fontMetrics.getHeight(), fontMetrics.stringWidth(s) + 8, fontMetrics.getHeight() + 4);
        piechart.pad.setColor(color);
        piechart.pad.drawRect(n - 4, n2 - fontMetrics.getHeight(), fontMetrics.stringWidth(s) + 8, fontMetrics.getHeight() + 4);
    }
    
    public static void DrawLabel(final Image image, final String s, final Font font, final Color color, final int n, final int n2, final int n3) {
        (piechart.pad = image.getGraphics()).setColor(color);
        piechart.pad.setFont(font);
        final FontMetrics fontMetrics = piechart.pad.getFontMetrics();
        final int length = s.length();
        switch (n3) {
            case 1: {
                final int height = fontMetrics.getHeight();
                for (int i = 0; i < length; ++i) {
                    piechart.pad.drawString(s.substring(i, i + 1), n, n2 - height * (length - i));
                }
                break;
            }
            case 2: {
                final int n4 = 7 * fontMetrics.getHeight() / 10;
                for (int j = 0; j < length; ++j) {
                    piechart.pad.drawString(s.substring(j, j + 1), n + j * n4, n2 - j * n4);
                }
                break;
            }
            case 3: {
                final int height2 = fontMetrics.getHeight();
                for (int k = 0; k < length; ++k) {
                    piechart.pad.drawString(s.substring(k, k + 1), n, n2 - k * height2);
                }
                break;
            }
            case 4: {
                piechart.pad.drawString(s, n - fontMetrics.stringWidth(s), n2);
                break;
            }
            case 5: {
                piechart.pad.drawString(s, n, n2 + fontMetrics.getHeight());
                break;
            }
            default: {
                piechart.pad.drawString(s, n, n2);
                break;
            }
        }
    }
}
