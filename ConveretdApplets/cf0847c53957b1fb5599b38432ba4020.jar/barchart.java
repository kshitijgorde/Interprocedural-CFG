import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class barchart
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
    
    public static void barchart(final Image image, final double[][] array, final boolean[][] array2, final String[] array3, final int[][] array4, final Image[] array5, final double n, final double n2, final Color[][] array6, final boolean[] array7, final int[] array8, final String[] array9, final int[][] array10, final Font[] array11, final Color[] array12) {
        final int n3 = (array8[4] + 5) * array8[3] + 5;
        barchart.pad = image.getGraphics();
        barchart.i = 0;
        while (barchart.i < 5) {
            if (array5[barchart.i] != null) {
                barchart.pad.drawImage(array5[barchart.i], array4[barchart.i][0], array4[barchart.i][1], barchart.Iobs);
            }
            ++barchart.i;
        }
        if (array7[1]) {
            if (array7[0]) {
                SIRgraphics.Draw3Dgrid2(image, array12[13], n3, array8[2], array8[5], array8[6], array8[0], array8[1], array8[7]);
            }
            else {
                SIRgraphics.Draw2Dgrid(image, array12[13], n3, array8[2], array8[5], array8[6], array8[0], array8[1]);
            }
        }
        if (array7[2]) {
            barchart.dTemp = new Double(array8[2]);
            if (n < 0.0) {
                barchart.xTemp = array8[5] - (int)Math.round(barchart.dTemp * n / n2);
            }
            else {
                barchart.xTemp = array8[5];
            }
            if (array7[0]) {
                SIRgraphics.Draw3Daxis2(image, array12[14], barchart.xTemp, array8[6], array8[2] * array8[1] + array8[5] - barchart.xTemp, n3 * array8[0], -array8[5] + barchart.xTemp, array8[7], array12[15]);
            }
            else {
                SIRgraphics.DrawAxis2(image, array12[14], barchart.xTemp, array8[6], array8[2] * array8[1] + array8[5] - barchart.xTemp, n3 * array8[0], -array8[5] + barchart.xTemp);
            }
        }
        if (array9[10] != "") {
            SIRgraphics.DrawLabel(image, array9[10], array11[10], array12[10], array10[10][0], array10[10][1], 0);
        }
        if (array9[11] != "") {
            SIRgraphics.DrawLabel(image, array9[11], array11[11], array12[11], array10[11][0], array10[11][1], 0);
        }
        if (array9[12] != "") {
            SIRgraphics.DrawLabel(image, array9[12], array11[12], array12[12], array10[12][0], array10[12][1], 1);
        }
        barchart.i = 0;
        while (barchart.i < array8[0]) {
            if (array3[barchart.i] != "") {
                SIRgraphics.DrawLabel(image, array3[barchart.i], array11[14], array12[17], array8[10], array8[6] - 15 - barchart.i * n3, array8[9]);
            }
            ++barchart.i;
        }
        if (array7[3]) {
            barchart.i = 0;
            while (barchart.i < array8[1] + 1) {
                barchart.dTemp = new Double(barchart.i);
                barchart.dTemp = n + barchart.dTemp * n2;
                barchart.sTemp = SIRdata.DoubleToStr(barchart.dTemp, array8[8]);
                SIRgraphics.DrawLabel(image, barchart.sTemp = SIRdata.FormatStr(barchart.sTemp, -1, 2), array11[13], array12[17], array8[5] + barchart.i * array8[2], array8[6] + 25, 4);
                ++barchart.i;
            }
        }
        barchart.i = 0;
        while (barchart.i < array8[0]) {
            barchart.j = 0;
            while (barchart.j < array8[3]) {
                barchart.dTemp = new Double(array8[2]);
                if (n < 0.0) {
                    barchart.xTemp = array8[5] - (int)Math.round(barchart.dTemp * n / n2);
                }
                else {
                    barchart.xTemp = array8[5];
                }
                barchart.dTemp = new Double(array8[2]);
                barchart.iTemp = (int)Math.round(barchart.dTemp * (array[barchart.i][barchart.j] - n) / n2);
                barchart.iTemp = barchart.iTemp + array8[5] - barchart.xTemp;
                barchart.yTemp = array8[6] - 5 - (barchart.i * array8[3] + barchart.j) * (array8[4] + 5) - barchart.i * 5;
                if (array7[0]) {
                    SIRgraphics.Draw3Dbar(image, array8[4], barchart.iTemp, barchart.xTemp, barchart.yTemp, array8[7], array6[barchart.i][barchart.j], array7[4], array12[16]);
                }
                else {
                    SIRgraphics.DrawBar(image, array8[4], barchart.iTemp, barchart.xTemp, barchart.yTemp, array6[barchart.i][barchart.j], array7[4], array12[16]);
                }
                if (array2[barchart.i][barchart.j]) {
                    barchart.sTemp = SIRdata.DoubleToStr(array[barchart.i][barchart.j], array8[8]);
                    barchart.sTemp = SIRdata.FormatStr(barchart.sTemp, -1, 2);
                    if (barchart.iTemp < 0.0) {
                        SIRgraphics.DrawTextBox(image, barchart.sTemp, array11[13], array12[17], array12[19], barchart.xTemp - 5, barchart.yTemp - 5);
                        SIRgraphics.DrawLabel(image, barchart.sTemp, array11[13], array12[17], barchart.xTemp - 5, barchart.yTemp - 5, 0);
                    }
                    else {
                        SIRgraphics.DrawTextBox(image, barchart.sTemp, array11[13], array12[17], array12[19], barchart.xTemp + barchart.iTemp + 5, barchart.yTemp - 5);
                        SIRgraphics.DrawLabel(image, barchart.sTemp, array11[13], array12[17], barchart.xTemp + barchart.iTemp + 5, barchart.yTemp - 5, 0);
                    }
                }
                ++barchart.j;
            }
            ++barchart.i;
        }
        barchart.i = 5;
        while (barchart.i < 10) {
            if (array5[barchart.i] != null) {
                barchart.pad.drawImage(array5[barchart.i], array4[barchart.i][0], array4[barchart.i][1], barchart.Iobs);
            }
            ++barchart.i;
        }
        barchart.i = 0;
        while (barchart.i < 10) {
            if (array9[barchart.i] != "") {
                SIRgraphics.DrawLabel(image, array9[barchart.i], array11[barchart.i], array12[barchart.i], array10[barchart.i][0], array10[barchart.i][1], 0);
            }
            ++barchart.i;
        }
    }
}
