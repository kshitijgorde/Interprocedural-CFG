// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.datavisualization.plot2d;

import com.otnip.datavisualization.elements.Axes;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.util.Arrays;
import java.awt.image.WritableRaster;
import com.otnip.tools.StopWatch;
import com.otnip.math.Statistics;
import java.awt.image.BufferedImage;
import com.otnip.datavisualization.PlotPane;

public class PlotPane2D extends PlotPane
{
    private static final long serialVersionUID = 0L;
    private BufferedImage image;
    private double[] x;
    private double[] y;
    private double[][] X;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    
    public PlotPane2D(final double[][] X, final double[] x, final double[] y) {
        this.X = X;
        this.x = x;
        this.y = y;
        this.minX = Statistics.min(x);
        this.maxX = Statistics.max(x);
        this.minY = Statistics.min(y);
        this.maxY = Statistics.max(y);
        double minValue = Double.MAX_VALUE;
        double maxValue = -1.7976931348623157E308;
        for (int i = 0; i < X.length; ++i) {
            minValue = Math.min(minValue, Statistics.min(X[i]));
            maxValue = Math.max(maxValue, Statistics.max(X[i]));
        }
        final Colormap colormap = new Colormap(minValue, maxValue);
        final StopWatch s = new StopWatch();
        s.start();
        final int width = X.length;
        final int height = X[0].length;
        this.image = new BufferedImage(width, height, 1);
        final WritableRaster raster = this.image.getRaster();
        for (int j = 0; j < width; ++j) {
            for (int k = 0; k < height; ++k) {
                raster.setPixel(j, height - k - 1, colormap.getColorIndicies(X[j][k]));
            }
        }
        s.stop();
    }
    
    protected void renderData() {
        final BufferedImage targetImage = this.getPlotImage();
        final Graphics2D g2 = targetImage.createGraphics();
        System.out.println(this.getAxes());
        int startX = Arrays.binarySearch(this.x, this.getAxes().getMinX());
        if (startX < 0) {
            startX = ~startX - 1;
        }
        if (startX < 0) {
            startX = 0;
        }
        int endX = Arrays.binarySearch(this.x, this.getAxes().getMaxX());
        if (endX < 0) {
            endX ^= -1;
        }
        if (endX >= this.x.length) {
            endX = this.x.length - 1;
        }
        System.out.println(startX + " -> " + endX);
        g2.drawImage(this.image, 0, 0, targetImage.getWidth(), targetImage.getHeight(), startX, 0, endX, this.image.getHeight(), null);
    }
    
    protected void zoomFull() {
        this.setAxes(new Axes(this.minX, this.minY, this.maxX, this.maxY));
    }
    
    protected void zoomFullY() {
    }
    
    public void setAxes(final Axes axes) {
        final Axes realAxes = new Axes(Math.max(axes.getMinX(), this.minX), Math.max(axes.getMinY(), this.minY), Math.min(axes.getMaxX(), this.maxX), Math.min(axes.getMaxY(), this.maxY));
        super.setAxes(realAxes);
    }
}
