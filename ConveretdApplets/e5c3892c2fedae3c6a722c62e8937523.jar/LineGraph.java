import java.awt.RenderingHints;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class LineGraph
{
    private int padding;
    private int topMargin;
    private int bottomMargin;
    private int leftMargin;
    private int rightMargin;
    private double yScaleMax;
    private double yScaleMin;
    private int numXSteps;
    private double yAxisStep;
    private int yRange;
    public Color[] spectrum;
    private DecimalFormat formatter;
    private Dimension savedDisplaySize;
    
    public LineGraph(final Color[] spectrum, final int topMargin, final int bottomMargin, final int leftMargin, final int rightMargin, final int padding, final double yScaleMax, final double yScaleMin, final int numXSteps, final double yAxisStep, final int yRange, final boolean b) {
        this.spectrum = spectrum;
        this.topMargin = topMargin;
        this.bottomMargin = bottomMargin;
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
        this.padding = padding;
        this.yScaleMax = yScaleMax;
        this.yScaleMin = yScaleMin;
        this.numXSteps = numXSteps;
        this.yAxisStep = yAxisStep;
        this.yRange = yRange;
        if (b) {
            this.formatter = new DecimalFormat("##0.0");
        }
        else {
            this.formatter = new DecimalFormat("###,###.##");
        }
    }
    
    public void drawPoints(final Graphics graphics, final Dimension savedDisplaySize, final double[][] array, final Integer[] array2) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        this.savedDisplaySize = savedDisplaySize;
        final int n = savedDisplaySize.height - this.bottomMargin;
        final int n2 = savedDisplaySize.width - this.rightMargin;
        final double yScaleMax = this.yScaleMax;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        boolean b = true;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null) {
                b = false;
                break;
            }
        }
        if (b) {
            graphics2D.setColor(Color.RED);
            graphics2D.setFont(new Font("Arial", 0, 20));
            graphics2D.drawString("No Data Available", n2 / 2 - 40, n / 2);
            return;
        }
        for (int j = 0; j < array.length; ++j) {
            if (array[j] != null) {
                graphics2D.setColor(this.spectrum[array2[n5]]);
                final int n6 = (n - this.topMargin) / this.yRange;
                final int n7 = (n2 - this.leftMargin) / this.numXSteps;
                int n8 = 0;
                double n9 = 0.0;
                graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                final double[] array3 = array[j];
                for (int k = 0; k < array3.length; ++k) {
                    final double n10 = array3[k];
                    ++n8;
                    final int n11 = this.leftMargin + n7 * n8;
                    final int n12 = (int)Math.round(n - (n - this.topMargin) / yScaleMax * n10);
                    if (n10 >= 0.0) {
                        graphics2D.fillOval(n11 - 2, n12 - 2, 5, 5);
                        if (n4 != 0 && n3 != 0 && n9 >= 0.0) {
                            graphics2D.drawLine(n3, n4, n11, n12);
                        }
                    }
                    n9 = n10;
                    n3 = n11;
                    n4 = n12;
                }
                n3 = 0;
                n4 = 0;
                ++n5;
            }
        }
    }
    
    public void drawLegend(final Graphics graphics, final Dimension savedDisplaySize, final String[] array, final Integer[] array2) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        this.savedDisplaySize = savedDisplaySize;
        final int n = 20;
        final int n2 = 90;
        final int n3 = 4;
        final int n4 = 10;
        int n5 = savedDisplaySize.height - (this.bottomMargin - 60);
        int n6 = 0;
        int n7 = 0;
        final int n8 = savedDisplaySize.width / 2;
        int n9 = n8 - n4 * (n3 - 1) - (n2 + n4) * n3 / 2;
        final int n10 = n2 + n4;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null) {
                final String s = array[i];
                final String string = s.substring(0, 1) + s.substring(1).toLowerCase();
                graphics2D.setColor(this.spectrum[array2[n7]]);
                graphics2D.fillRect(n9, n5, 5, 5);
                graphics2D.setColor(Color.BLACK);
                graphics2D.drawString(string, n9 + 7, n5 + 5);
                ++n6;
                n9 += n10 + n4;
                if (n6 == n3) {
                    n5 += n;
                    n9 = n8 - n4 * (n3 - 1) - (n2 + n4) * n3 / 2;
                    n6 = 0;
                }
                ++n7;
            }
        }
    }
    
    public void drawSelectedDataRange(final Graphics graphics, final Dimension savedDisplaySize, final float n) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        this.savedDisplaySize = savedDisplaySize;
        final int leftMargin = this.leftMargin;
        final int n2 = savedDisplaySize.width - this.rightMargin;
        final int topMargin = this.topMargin;
        final int n3 = savedDisplaySize.height - this.bottomMargin;
        final int n4 = (n2 - this.leftMargin) / this.numXSteps;
        final int n5 = leftMargin + n4;
        final int n6 = n5 + (int)((n5 + n4 * (this.numXSteps - 1) - n5) * n + 0.5f);
        graphics2D.drawLine(n6, n3, n6, topMargin);
    }
    
    public float getXPercentage(int n) {
        final int leftMargin = this.leftMargin;
        final int n2 = (this.savedDisplaySize.width - this.rightMargin - this.leftMargin) / this.numXSteps;
        final int n3 = leftMargin + n2;
        final int n4 = n3 + n2 * this.numXSteps;
        if (n < n3) {
            n = n3;
        }
        else if (n > n4) {
            n = n4;
        }
        return (n - n3) / (n4 - n2 - n3);
    }
    
    public void drawGraphLayout(final Graphics graphics, final String[] array, final Dimension savedDisplaySize, final String s, final String s2, final String s3) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        this.savedDisplaySize = savedDisplaySize;
        final int n = savedDisplaySize.height - this.bottomMargin;
        final int n2 = savedDisplaySize.width - this.rightMargin;
        final double yScaleMax = this.yScaleMax;
        graphics2D.drawString(s, savedDisplaySize.width / 2 - Math.round(s.length() * 3), this.topMargin / 2);
        graphics2D.drawLine(this.leftMargin, this.topMargin, this.leftMargin, n);
        graphics2D.drawString(s2, savedDisplaySize.width / 2 - Math.round(s2.length() * 3), savedDisplaySize.height - this.padding);
        final int n3 = savedDisplaySize.height / 2 - Math.round(s3.length() * 3);
        graphics2D.rotate(300.0, this.padding, n3);
        graphics2D.drawString(s3, this.padding, n3);
        graphics2D.rotate(-300.0, this.padding, n3);
        int n4 = n;
        final int n5 = (n - this.topMargin) / this.yRange;
        int n6 = 0;
        for (double yScaleMin = this.yScaleMin; yScaleMin <= yScaleMax; yScaleMin += this.yAxisStep) {
            ++n6;
            final String format = this.formatter.format(yScaleMin);
            graphics2D.drawString(format, this.leftMargin - format.length() * 3 - 12, n4);
            graphics2D.drawLine(this.leftMargin, n4, n2, n4);
            n4 = n - n5 * n6;
        }
        final int n7 = (n2 - this.leftMargin) / this.numXSteps;
        int n8 = 0;
        for (int i = 0; i < this.numXSteps; ++i) {
            ++n8;
            final int n9 = this.leftMargin + n7 * n8;
            graphics2D.drawLine(n9, n, n9, n + 10);
            final int n10 = array[i].length() * 3 + 25 + n;
            final int n11 = n9 - array[i].length() * 3;
            graphics2D.rotate(-0.7853981633974483, n11, n10);
            graphics2D.drawString(array[i], n11, n10);
            graphics2D.rotate(0.7853981633974483, n11, n10);
        }
    }
}
