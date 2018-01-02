// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.util;

import edu.wise.stats.distributions.Distribution;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import edu.wise.graph.DataToScreen;
import edu.wise.graph.StyleSheet;
import edu.wise.stats.distributions.NormalDistribution;
import edu.wise.graph.HotSpot;
import edu.wise.graph.Plot;

public class RawDists extends Plot
{
    private HotSpot signalHS;
    private HotSpot critHS;
    protected UtilApplet utilApplet;
    private boolean wtd;
    private int arrLength;
    private int abscissa;
    private double[] noisePdf;
    private double[] signalPdf;
    private double[] noiseGtCPdf;
    private double[] signalGtCPdf;
    private double[] underGtCPdf;
    private double[] noiseLtCPdf;
    private double[] signalLtCPdf;
    private double[] underLtCPdf;
    private double[] zX;
    private double[] znoise;
    private double[] zsignal;
    private double[] znoiseLt;
    private double[] zsignalLt;
    private double[] zUnder;
    protected NormalDistribution noiseDist;
    protected NormalDistribution signalDist;
    private String graphLabel;
    private double zStart;
    private double zmax;
    private double increment;
    private double deltaDPrime;
    
    public RawDists(final NormalDistribution noiseDist, final NormalDistribution signalDist, final UtilApplet utilApplet, final boolean wtd, final String graphLabel) {
        super(300, 200);
        this.arrLength = 201;
        this.noisePdf = new double[this.arrLength];
        this.signalPdf = new double[this.arrLength];
        this.noiseGtCPdf = new double[this.arrLength];
        this.signalGtCPdf = new double[this.arrLength];
        this.underGtCPdf = new double[this.arrLength];
        this.noiseLtCPdf = new double[this.arrLength];
        this.signalLtCPdf = new double[this.arrLength];
        this.underLtCPdf = new double[this.arrLength];
        this.zX = new double[this.arrLength];
        this.znoise = new double[this.arrLength];
        this.zsignal = new double[this.arrLength];
        this.znoiseLt = new double[this.arrLength];
        this.zsignalLt = new double[this.arrLength];
        this.zUnder = new double[this.arrLength];
        this.graphLabel = "Normal Distributions";
        this.zStart = -10.0;
        this.zmax = 10.0;
        this.increment = Math.abs((this.zmax - this.zStart) / (this.arrLength - 1));
        this.deltaDPrime = 0.0;
        for (int i = 0; i < this.arrLength; ++i) {
            this.zX[i] = this.zStart + i * this.increment;
        }
        this.setBackground(StyleSheet.ENABLED_BACKGROUND);
        this.utilApplet = utilApplet;
        this.noiseDist = noiseDist;
        this.signalDist = signalDist;
        this.graphLabel = graphLabel;
        this.wtd = wtd;
        this.setMinMax(this.zStart, this.zmax, 0.0, 0.55);
        this.setOffsets(0, 5, 30, 5);
        this.abscissa = this.getSize().height - super.bottomOffset;
        super.dts = new DataToScreen(this.getSize().width, this.getSize().height, this);
    }
    
    public void update(final Graphics graphics) {
        super.image = this.createImage(this.getSize().width, this.getSize().height);
        (super.graphics = super.image.getGraphics()).setColor(super.background);
        super.graphics.clearRect(0, 0, this.getSize().width, this.getSize().height);
        super.graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        super.graphics.setColor(super.foreground);
        this.paint(super.graphics);
        graphics.drawImage(super.image, 0, 0, this);
        super.image.flush();
    }
    
    public void update() {
        final int arrLength = this.arrLength;
        this.noiseDist.set(this.utilApplet.getUtil().getM1(), this.utilApplet.getUtil().getS1());
        this.signalDist.set(this.utilApplet.getUtil().getM2(), this.utilApplet.getUtil().getS2());
        this.zStart = Math.min(this.noiseDist.getMu() - 3.0 * this.noiseDist.getSigma(), this.signalDist.getMu() - 3.0 * this.signalDist.getSigma());
        this.zmax = Math.max(this.noiseDist.getMu() + 3.0 * this.noiseDist.getSigma(), this.signalDist.getMu() + 3.0 * this.signalDist.getSigma());
        this.increment = Math.abs((this.zmax - this.zStart) / (this.arrLength - 1));
        for (int i = 0; i < this.arrLength; ++i) {
            this.zX[i] = this.zStart + i * this.increment;
        }
        this.setMinMax(this.zStart, this.zmax, super.ymin, super.ymax);
        double n = 0.0;
        double n2 = 0.0;
        for (int j = 0; j < this.arrLength; ++j) {
            final double n3 = this.zStart + j * this.increment;
            if (!this.wtd) {
                this.noisePdf[j] = this.noiseDist.Pdf(n3);
                if (this.noisePdf[j] > n) {
                    n = this.noisePdf[j];
                }
                this.signalPdf[j] = this.signalDist.Pdf(n3);
                if (this.signalPdf[j] > n2) {
                    n2 = this.signalPdf[j];
                }
                this.setMinMax(super.xmin, super.xmax, super.ymin, Math.max(n * 1.33, n2 * 1.33));
            }
            else {
                this.noisePdf[j] = this.noiseDist.Pdf(n3) * this.utilApplet.getUtil().getn1();
                if (this.noisePdf[j] > n) {
                    n = this.noisePdf[j];
                }
                this.signalPdf[j] = this.signalDist.Pdf(n3) * this.utilApplet.getUtil().getn2() * this.utilApplet.getUtil().getU();
                if (this.signalPdf[j] > n2) {
                    n2 = this.signalPdf[j];
                }
                this.setMinMax(super.xmin, super.xmax, super.ymin, Math.max(n * 1.33, n2 * 1.33));
            }
        }
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        graphics.setFont(StyleSheet.SMALL_FONT);
        final String[] labels = super.dts.getLabels(7, 2, false);
        final int n = this.getSize().height - super.bottomOffset;
        final int n2 = graphics.getFontMetrics().getAscent() / 2;
        graphics.setColor(Color.lightGray);
        for (int i = 1; i < labels.length - 1; ++i) {
            graphics.drawString(String.valueOf(labels[i]), 5, n - i * (n / (labels.length - 1)) + n2);
        }
        for (int j = 0; j < labels.length - 1; ++j) {
            graphics.drawLine(30, n - j * (n / (labels.length - 1)), 35, n - j * (n / (labels.length - 1)));
        }
        graphics.drawLine(33, 16, 33, n - 1);
        graphics.setFont(StyleSheet.REGULAR_FONT);
        graphics.setColor(Color.black);
        final int n3 = this.getSize().width - super.leftOffset - super.rightOffset;
        final String[] labels2 = super.dts.getLabels(5, 2, true);
        final int n4 = this.getSize().height - super.bottomOffset;
        for (int k = 0; k < labels2.length; ++k) {
            graphics.drawString(labels2[k], 1 + super.leftOffset - graphics.getFontMetrics().stringWidth(labels2[k]) / 2 + n3 / (labels2.length - 1) * k, this.getSize().height - 3 - graphics.getFontMetrics().getHeight());
            graphics.drawLine(1 + super.leftOffset + n3 / (labels2.length - 1) * k, this.abscissa, 1 + super.leftOffset + n3 / (labels2.length - 1) * k, this.abscissa + 3);
        }
        final int[] screenCoordinates = super.dts.toScreenCoordinates(this.noisePdf, false);
        final int[] screenCoordinates2 = super.dts.toScreenCoordinates(this.signalPdf, false);
        final int[] screenCoordinates3 = super.dts.toScreenCoordinates(this.zX, true);
        graphics.setColor(StyleSheet.NOISE_COLOR);
        graphics.drawPolyline(screenCoordinates3, screenCoordinates, screenCoordinates3.length);
        graphics.setColor(StyleSheet.SIGNAL_COLOR);
        graphics.drawPolyline(screenCoordinates3, screenCoordinates2, screenCoordinates3.length);
        graphics.setColor(Color.black);
        graphics.drawLine(0, this.abscissa, this.getSize().width, this.abscissa);
        graphics.setFont(StyleSheet.LABEL_FONT);
        graphics.drawString(this.graphLabel, this.getSize().width / 2 - graphics.getFontMetrics().stringWidth("Normal Distributions") / 2, 12);
    }
    
    private int getMin(final int[] array) {
        int n = 1000;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < n) {
                n = array[i];
            }
        }
        return n;
    }
    
    public void setLabel(final String graphLabel) {
        this.graphLabel = graphLabel;
    }
    
    public void setWtd(final boolean wtd) {
        this.wtd = wtd;
    }
    
    public double[] getXArr() {
        return this.noisePdf;
    }
    
    public double[] getYArr() {
        return this.signalPdf;
    }
    
    public Distribution getIntnoiseDist() {
        return this.noiseDist;
    }
    
    public Distribution getIntsignalDist() {
        return this.signalDist;
    }
    
    public HotSpot getCritHs() {
        return this.critHS;
    }
    
    public HotSpot getSignalHs() {
        return this.signalHS;
    }
}
