// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import edu.hws.jcm.data.NumUtils;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class Axes extends Drawable
{
    public static final int TOP = 0;
    public static final int BOTTOM = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int CENTER = 4;
    public static final int SMART = 5;
    private int xAxisPosition;
    private int yAxisPosition;
    private Color axesColor;
    private Color lightAxesColor;
    private Color labelColor;
    private String xLabel;
    private String yLabel;
    private transient int[] xTicks;
    private transient int[] yTicks;
    private transient String[] xTickLabels;
    private transient String[] yTickLabels;
    private transient int[][] xTickLabelPos;
    private transient int[][] yTickLabelPos;
    private transient int xAxisPixelPosition;
    private transient int yAxisPixelPosition;
    private transient int xLabel_x;
    private transient int xLabel_y;
    private transient int yLabel_x;
    private transient int yLabel_y;
    private transient Font font;
    private transient int ascent;
    private transient int descent;
    private transient int digitWidth;
    
    public Axes() {
        this(null, null);
    }
    
    public Axes(final String xLabel, final String yLabel) {
        this.xAxisPosition = 5;
        this.yAxisPosition = 5;
        this.axesColor = new Color(0, 0, 180);
        this.lightAxesColor = new Color(180, 180, 255);
        this.labelColor = Color.black;
        this.xLabel = null;
        this.yLabel = null;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
    }
    
    public Color getAxesColor() {
        return this.axesColor;
    }
    
    public void setAxesColor(final Color axesColor) {
        if (axesColor != null && !axesColor.equals(this.axesColor)) {
            this.axesColor = axesColor;
            this.needsRedraw();
        }
    }
    
    public Color getLightAxesColor() {
        return this.lightAxesColor;
    }
    
    public void setLightAxesColor(final Color lightAxesColor) {
        if (lightAxesColor != null && !lightAxesColor.equals(this.lightAxesColor)) {
            this.lightAxesColor = lightAxesColor;
            this.needsRedraw();
        }
    }
    
    public Color getLabelColor() {
        return this.labelColor;
    }
    
    public void setLabelColor(final Color labelColor) {
        if (labelColor != null && !labelColor.equals(this.labelColor)) {
            this.labelColor = labelColor;
            if (this.xLabel != null || this.yLabel != null) {
                this.needsRedraw();
            }
        }
    }
    
    public int getXAxisPosition() {
        return this.xAxisPosition;
    }
    
    public void setXAxisPosition(final int xAxisPosition) {
        if ((xAxisPosition == 0 || xAxisPosition == 1 || xAxisPosition == 4 || xAxisPosition == 5) && xAxisPosition != this.xAxisPosition) {
            this.xAxisPosition = xAxisPosition;
            this.needsRedraw();
        }
    }
    
    public int getYAxisPosition() {
        return this.yAxisPosition;
    }
    
    public void setYAxisPosition(final int yAxisPosition) {
        if ((yAxisPosition == 2 || yAxisPosition == 3 || yAxisPosition == 4 || yAxisPosition == 5) && yAxisPosition != this.yAxisPosition) {
            this.yAxisPosition = yAxisPosition;
            this.needsRedraw();
        }
    }
    
    public String getXLabel() {
        return this.xLabel;
    }
    
    public void setXLabel(final String xLabel) {
        this.xLabel = xLabel;
        this.needsRedraw();
    }
    
    public String getYLabel() {
        return this.yLabel;
    }
    
    public void setYLabel(final String yLabel) {
        this.yLabel = yLabel;
        this.needsRedraw();
    }
    
    public void draw(final Graphics graphics, final boolean b) {
        if (super.coords == null) {
            return;
        }
        if (b || this.xTicks == null || !graphics.getFont().equals(this.font)) {
            this.font = graphics.getFont();
            this.setup(graphics.getFontMetrics(this.font), super.coords.getXmin(), super.coords.getXmax(), super.coords.getYmin(), super.coords.getYmax(), super.coords.getLeft(), super.coords.getTop(), super.coords.getWidth(), super.coords.getHeight(), super.coords.getGap());
        }
        this.doDraw(graphics, super.coords.getXmin(), super.coords.getXmax(), super.coords.getYmin(), super.coords.getYmax(), super.coords.getLeft(), super.coords.getTop(), super.coords.getWidth(), super.coords.getHeight(), super.coords.getGap());
    }
    
    private void doDraw(final Graphics graphics, final double n, final double n2, final double n3, final double n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        if (this.xAxisPosition == 5 && (n4 < 0.0 || n3 > 0.0)) {
            graphics.setColor(this.lightAxesColor);
        }
        else {
            graphics.setColor(this.axesColor);
        }
        graphics.drawLine(n5 + n9, this.xAxisPixelPosition, n5 + n7 - n9 - 1, this.xAxisPixelPosition);
        for (int i = 0; i < this.xTicks.length; ++i) {
            graphics.drawLine(this.xTicks[i], (this.xAxisPixelPosition - 2 < n6) ? this.xAxisPixelPosition : (this.xAxisPixelPosition - 2), this.xTicks[i], (this.xAxisPixelPosition + 2 >= n6 + n8) ? this.xAxisPixelPosition : (this.xAxisPixelPosition + 2));
        }
        for (int j = 0; j < this.xTickLabels.length; ++j) {
            graphics.drawString(this.xTickLabels[j], this.xTickLabelPos[j][0], this.xTickLabelPos[j][1]);
        }
        if (this.yAxisPosition == 5 && (n2 < 0.0 || n > 0.0)) {
            graphics.setColor(this.lightAxesColor);
        }
        else {
            graphics.setColor(this.axesColor);
        }
        graphics.drawLine(this.yAxisPixelPosition, n6 + n9, this.yAxisPixelPosition, n6 + n8 - n9 - 1);
        for (int k = 0; k < this.yTicks.length; ++k) {
            graphics.drawLine((this.yAxisPixelPosition - 2 < n5) ? this.yAxisPixelPosition : (this.yAxisPixelPosition - 2), this.yTicks[k], (this.yAxisPixelPosition + 2 >= n5 + n7) ? this.yAxisPixelPosition : (this.yAxisPixelPosition + 2), this.yTicks[k]);
        }
        for (int l = 0; l < this.yTickLabels.length; ++l) {
            graphics.drawString(this.yTickLabels[l], this.yTickLabelPos[l][0], this.yTickLabelPos[l][1]);
        }
        graphics.setColor(this.labelColor);
        if (this.xLabel != null) {
            graphics.drawString(this.xLabel, this.xLabel_x, this.xLabel_y);
        }
        if (this.yLabel != null) {
            graphics.drawString(this.yLabel, this.yLabel_x, this.yLabel_y);
        }
    }
    
    void setup(final FontMetrics fontMetrics, final double n, final double n2, final double n3, final double n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        this.digitWidth = fontMetrics.charWidth('0');
        this.ascent = fontMetrics.getAscent();
        this.descent = fontMetrics.getDescent();
        switch (this.xAxisPosition) {
            case 0: {
                this.xAxisPixelPosition = n6 + n9;
                break;
            }
            case 1: {
                this.xAxisPixelPosition = n6 + n8 - n9 - 1;
                break;
            }
            case 4: {
                this.xAxisPixelPosition = n6 + n8 / 2;
                break;
            }
            case 5: {
                if (n4 < 0.0) {
                    this.xAxisPixelPosition = n6 + n9;
                    break;
                }
                if (n3 > 0.0) {
                    this.xAxisPixelPosition = n6 + n8 - n9 - 1;
                    break;
                }
                this.xAxisPixelPosition = n6 + n9 + (int)((n8 - 2 * n9 - 1) * n4 / (n4 - n3));
                break;
            }
        }
        switch (this.yAxisPosition) {
            case 2: {
                this.yAxisPixelPosition = n5 + n9;
                break;
            }
            case 1: {
                this.yAxisPixelPosition = n5 + n7 - n9 - 1;
                break;
            }
            case 4: {
                this.yAxisPixelPosition = n5 + n7 / 2;
                break;
            }
            case 5: {
                if (n2 < 0.0) {
                    this.yAxisPixelPosition = n5 + n7 - n9 - 1;
                    break;
                }
                if (n > 0.0) {
                    this.yAxisPixelPosition = n5 + n9;
                    break;
                }
                this.yAxisPixelPosition = n5 + n9 - (int)((n7 - 2 * n9 - 1) * n / (n2 - n));
                break;
            }
        }
        if (this.xLabel != null) {
            final int stringWidth = fontMetrics.stringWidth(this.xLabel);
            if (n5 + n7 - n9 - stringWidth <= this.yAxisPixelPosition) {
                this.xLabel_x = n5 + n9;
            }
            else {
                this.xLabel_x = n5 + n7 - n9 - stringWidth;
            }
            if (this.xAxisPixelPosition + 3 + this.ascent + this.descent + n9 >= n6 + n8) {
                this.xLabel_y = this.xAxisPixelPosition - 4;
            }
            else {
                this.xLabel_y = this.xAxisPixelPosition + 3 + this.ascent;
            }
        }
        if (this.yLabel != null) {
            final int stringWidth2 = fontMetrics.stringWidth(this.yLabel);
            if (this.yAxisPixelPosition + 3 + stringWidth2 + n9 > n5 + n7) {
                this.yLabel_x = this.yAxisPixelPosition - stringWidth2 - 3;
            }
            else {
                this.yLabel_x = this.yAxisPixelPosition + 3;
            }
            if (n6 + this.ascent + this.descent + n9 > this.xAxisPixelPosition) {
                this.yLabel_y = n6 + n8 - n9 - this.descent;
            }
            else {
                this.yLabel_y = n6 + this.ascent + n9;
            }
        }
        final double fudgeStart = this.fudgeStart((n2 - n) * (this.yAxisPixelPosition - (n5 + n9)) / (n7 - 2 * n9) + n, 0.05 * (n2 - n));
        int n10 = (n7 - 2 * n9) / (10 * this.digitWidth);
        if (n10 <= 2) {
            n10 = 3;
        }
        else if (n10 > 20) {
            n10 = 20;
        }
        double n11 = this.fudge((n2 - n) / n10);
        for (double n12 = 1.5; n12 < 4.0 && fontMetrics.stringWidth(NumUtils.realToString(n11 + fudgeStart)) + this.digitWidth > n11 / (n2 - n) * (n7 - 2 * n9); n11 = this.fudge(n12 * (n2 - n) / n10), n12 += 0.5) {}
        final double[] array = new double[50];
        int n13 = 0;
        double n14 = fudgeStart + n11;
        double n15 = n5 + n7;
        if (this.xLabel != null && n5 + n7 - n9 - fontMetrics.stringWidth(this.xLabel) > this.yAxisPixelPosition) {
            n15 -= fontMetrics.stringWidth(this.xLabel) + n9 + this.digitWidth;
        }
        while (n13 < 50 && n14 <= n2 && n5 + n9 + (n7 - 2 * n9) * (n14 - n) / (n2 - n) + fontMetrics.stringWidth(NumUtils.realToString(n14)) / 2 <= n15) {
            array[n13] = n14;
            ++n13;
            n14 += n11;
        }
        double n16 = fudgeStart - n11;
        double n17 = n5;
        if (this.xLabel != null && n5 + n7 - n9 - fontMetrics.stringWidth(this.xLabel) <= this.yAxisPixelPosition) {
            n17 += fontMetrics.stringWidth(this.xLabel) + this.digitWidth;
        }
        while (n13 < 50 && n16 >= n && n5 + n9 + (n7 - 2 * n9) * (n16 - n) / (n2 - n) - fontMetrics.stringWidth(NumUtils.realToString(n16)) / 2 >= n17) {
            array[n13] = n16;
            ++n13;
            n16 -= n11;
        }
        this.xTicks = new int[n13];
        this.xTickLabels = new String[n13];
        this.xTickLabelPos = new int[n13][2];
        for (int i = 0; i < n13; ++i) {
            this.xTicks[i] = (int)(n5 + n9 + (n7 - 2 * n9) * (array[i] - n) / (n2 - n));
            this.xTickLabels[i] = NumUtils.realToString(array[i]);
            this.xTickLabelPos[i][0] = this.xTicks[i] - fontMetrics.stringWidth(this.xTickLabels[i]) / 2;
            if (this.xAxisPixelPosition - 4 - this.ascent >= n6) {
                this.xTickLabelPos[i][1] = this.xAxisPixelPosition - 4;
            }
            else {
                this.xTickLabelPos[i][1] = this.xAxisPixelPosition + 4 + this.ascent;
            }
        }
        final double fudgeStart2 = this.fudgeStart(n4 - (n4 - n3) * (this.xAxisPixelPosition - (n6 + n9)) / (n8 - 2 * n9), 0.05 * (n4 - n3));
        int n18 = (n8 - 2 * n9) / (5 * (this.ascent + this.descent));
        if (n18 <= 2) {
            n18 = 3;
        }
        else if (n18 > 20) {
            n18 = 20;
        }
        final double fudge = this.fudge((n4 - n3) / n18);
        int n19 = 0;
        double n20 = fudgeStart2 + fudge;
        double n21 = n6 + 8 + n9;
        if (this.yLabel != null && n6 + n9 + this.ascent + this.descent <= this.xAxisPixelPosition) {
            n21 = n6 + n9 + this.ascent + this.descent;
        }
        while (n19 < 50 && n20 <= n4 && n6 + n9 + (n8 - 2 * n9) * (n4 - n20) / (n4 - n3) - this.ascent / 2 >= n21) {
            array[n19] = n20;
            ++n19;
            n20 += fudge;
        }
        double n22 = fudgeStart2 - fudge;
        double n23 = n6 + n8 - n9 - 8;
        if (this.yLabel != null && n6 + n9 + this.ascent + this.descent > this.xAxisPixelPosition) {
            n23 = n6 + n8 - n9 - this.ascent - this.descent;
        }
        while (n19 < 50 && n22 >= n3 && n6 + n9 + (n8 - 2 * n9) * (n4 - n22) / (n4 - n3) + this.ascent / 2 <= n23) {
            array[n19] = n22;
            ++n19;
            n22 -= fudge;
        }
        this.yTicks = new int[n19];
        this.yTickLabels = new String[n19];
        this.yTickLabelPos = new int[n19][2];
        int n24 = 0;
        for (int j = 0; j < n19; ++j) {
            this.yTickLabels[j] = NumUtils.realToString(array[j]);
            final int stringWidth3 = fontMetrics.stringWidth(this.yTickLabels[j]);
            if (stringWidth3 > n24) {
                n24 = stringWidth3;
            }
        }
        for (int k = 0; k < n19; ++k) {
            this.yTicks[k] = (int)(n6 + n9 + (n8 - 2 * n9) * (n4 - array[k]) / (n4 - n3));
            this.yTickLabelPos[k][1] = this.yTicks[k] + this.ascent / 2;
            if (this.yAxisPixelPosition - 4 - n24 < n5) {
                this.yTickLabelPos[k][0] = this.yAxisPixelPosition + 4;
            }
            else {
                this.yTickLabelPos[k][0] = this.yAxisPixelPosition - 4 - fontMetrics.stringWidth(this.yTickLabels[k]);
            }
        }
    }
    
    double fudge(final double n) {
        if (Math.abs(n) < 5.0E-4 || Math.abs(n) > 500000.0) {
            return n;
        }
        if (Math.abs(n) < 0.1 || Math.abs(n) > 5000.0) {
            double n2 = n;
            int n3 = 0;
            if (Math.abs(n2) >= 1.0) {
                while (Math.abs(n2) >= 8.75) {
                    n2 /= 10.0;
                    ++n3;
                }
            }
            else {
                while (Math.abs(n2) < 1.0) {
                    n2 *= 10.0;
                    --n3;
                }
            }
            double n4 = Math.round(n2 * 4.0) / 4L;
            if (n3 > 0) {
                for (int i = 0; i < n3; ++i) {
                    n4 *= 10.0;
                }
            }
            else if (n3 < 0) {
                for (int j = 0; j < -n3; ++j) {
                    n4 /= 10.0;
                }
            }
            return n4;
        }
        if (Math.abs(n) < 0.5) {
            return Math.round(10.0 * n) / 10.0;
        }
        if (Math.abs(n) < 2.5) {
            return Math.round(2.0 * n) / 2.0;
        }
        if (Math.abs(n) < 12.0) {
            return Math.round(n);
        }
        if (Math.abs(n) < 120.0) {
            return Math.round(n / 10.0) * 10.0;
        }
        if (Math.abs(n) < 1200.0) {
            return Math.round(n / 100.0) * 100.0;
        }
        return Math.round(n / 1000.0) * 1000.0;
    }
    
    private double fudgeStart(final double n, final double n2) {
        if (Math.abs(Math.round(n) - n) < n2) {
            return Math.round(n);
        }
        for (double n3 = 10.0; n3 <= 100000.0; n3 *= 10.0) {
            final double n4 = Math.round(n * n3) / n3;
            if (Math.abs(n4 - n) < n2) {
                return n4;
            }
        }
        return n;
    }
}
