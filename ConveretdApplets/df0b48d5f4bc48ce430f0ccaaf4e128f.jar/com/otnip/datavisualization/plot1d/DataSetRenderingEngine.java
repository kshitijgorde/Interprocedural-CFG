// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.datavisualization.plot1d;

import java.awt.Color;
import java.util.Arrays;
import com.otnip.tools.StopWatch;
import com.otnip.datavisualization.elements.Axes;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

class DataSetRenderingEngine
{
    private static final int POLYLINE_BUFFER_SIZE = 8192;
    private final Graphics2D g2;
    private final double xScale;
    private final double yScale;
    private final double minX;
    private final double maxX;
    private final double minY;
    private final double maxY;
    private final int[] xBuffer;
    private final int[] yBuffer;
    
    DataSetRenderingEngine(final BufferedImage dataRenderingImage, final Axes axes) {
        this.xBuffer = new int[8192];
        this.yBuffer = new int[8192];
        this.g2 = dataRenderingImage.createGraphics();
        this.minX = axes.getMinX();
        this.maxX = axes.getMaxX();
        this.minY = axes.getMinY();
        this.maxY = axes.getMaxY();
        this.yScale = dataRenderingImage.getHeight() / (this.maxY - this.minY);
        this.xScale = dataRenderingImage.getWidth() / (this.maxX - this.minX);
    }
    
    Graphics2D getGraphics() {
        return this.g2;
    }
    
    void render(final DataSet dataSet) {
        final StopWatch sw = new StopWatch("Rendering");
        switch (dataSet.getRenderingPreferences().plotType) {
            case LINE: {
                this.renderLineGraph(dataSet);
                break;
            }
            case LINE_SCATTER: {
                this.renderLineGraph(dataSet);
                this.renderScatterGraph(dataSet);
                break;
            }
            case SCATTER: {
                this.renderScatterGraph(dataSet);
                break;
            }
        }
        System.out.println(sw);
    }
    
    private void renderLineGraph(final DataSet dataSet) {
        final double[] x = dataSet.getX();
        final double[] y = dataSet.getY();
        int startIndex = 0;
        int endIndex = x.length - 1;
        if (dataSet.isAlwaysIncreasingInX()) {
            startIndex = Arrays.binarySearch(x, this.minX);
            if (startIndex < 0) {
                startIndex = ~startIndex - 1;
            }
            if (startIndex < 0) {
                startIndex = 0;
            }
            if (startIndex >= x.length) {
                return;
            }
            endIndex = Arrays.binarySearch(x, this.maxX);
            if (endIndex < 0) {
                endIndex = ~endIndex + 1;
            }
            if (endIndex >= x.length) {
                endIndex = x.length - 1;
            }
            if (endIndex <= 0) {
                return;
            }
        }
        this.g2.setStroke(dataSet.getRenderingPreferences().stroke);
        this.g2.setColor(dataSet.getRenderingPreferences().color);
        int currentIndex = startIndex + 1;
        while (endIndex - currentIndex >= 8192) {
            --currentIndex;
            for (int i = 0; i < 8192; ++i) {
                this.xBuffer[i] = (int)((x[currentIndex] - this.minX) * this.xScale);
                this.yBuffer[i] = (int)((this.maxY - y[currentIndex]) * this.yScale);
                ++currentIndex;
            }
            this.g2.drawPolyline(this.xBuffer, this.yBuffer, 8192);
        }
        --currentIndex;
        final int N = endIndex - currentIndex + 1;
        for (int j = 0; j < N; ++j) {
            this.xBuffer[j] = (int)((x[currentIndex] - this.minX) * this.xScale);
            this.yBuffer[j] = (int)((this.maxY - y[currentIndex]) * this.yScale);
            ++currentIndex;
        }
        this.g2.drawPolyline(this.xBuffer, this.yBuffer, N);
    }
    
    private void renderScatterGraph(final DataSet dataSet) {
        final double[] x = dataSet.getX();
        final double[] y = dataSet.getY();
        int startIndex = 0;
        int endIndex = x.length - 1;
        if (dataSet.isAlwaysIncreasingInX()) {
            startIndex = Arrays.binarySearch(x, this.minX);
            if (startIndex < 0) {
                startIndex = ~startIndex - 1;
            }
            if (startIndex < 0) {
                startIndex = 0;
            }
            if (startIndex >= x.length) {
                return;
            }
            endIndex = Arrays.binarySearch(x, this.maxX);
            if (endIndex < 0) {
                endIndex = ~endIndex + 1;
            }
            if (endIndex >= x.length) {
                endIndex = x.length - 1;
            }
            if (endIndex <= 0) {
                return;
            }
        }
        this.g2.setStroke(dataSet.getRenderingPreferences().stroke);
        final Color fillColor = dataSet.getRenderingPreferences().color;
        final Color outlineColor = Color.BLACK;
        final int RADIUS = 2;
        final int DIAMETER = 4;
        for (int i = startIndex; i <= endIndex; ++i) {
            final int X = (int)((x[i] - this.minX) * this.xScale) - 2;
            final int Y = (int)((this.maxY - y[i]) * this.yScale) - 2;
            this.g2.setColor(fillColor);
            this.g2.fillOval(X, Y, 4, 4);
            this.g2.setColor(outlineColor);
            this.g2.drawOval(X, Y, 4, 4);
        }
    }
}
