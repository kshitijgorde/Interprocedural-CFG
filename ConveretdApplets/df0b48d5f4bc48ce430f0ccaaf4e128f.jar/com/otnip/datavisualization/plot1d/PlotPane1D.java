// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.datavisualization.plot1d;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import com.otnip.math.Statistics;
import java.util.Arrays;
import java.util.Iterator;
import com.otnip.datavisualization.elements.Axes;
import com.otnip.math.VectorTools;
import java.awt.LayoutManager;
import java.util.ArrayList;
import com.otnip.datavisualization.PlotPane;

public class PlotPane1D extends PlotPane
{
    private static final long serialVersionUID = 0L;
    private ArrayList<DataSet> dataSets;
    
    public PlotPane1D() {
        this.dataSets = new ArrayList<DataSet>();
        this.initComponents();
    }
    
    private void initComponents() {
        this.setLayout(null);
    }
    
    public DataSet plot(final double[] y) {
        return this.plot(VectorTools.linspace(0.0, y.length - 1, y.length), y);
    }
    
    public DataSet plot(final double[] x, final double[] y) {
        final DataSet dataSet = new DataSet(x, y);
        dataSet.setName("DataSet " + this.dataSets.size());
        dataSet.getRenderingPreferences().color = DataSetRenderingPreferences.defaultColor[this.dataSets.size() % DataSetRenderingPreferences.defaultColor.length];
        this.dataSets.add(dataSet);
        this.updateDataRenderingImage = true;
        this.repaint();
        this.zoomFull();
        return dataSet;
    }
    
    public void zoomFull() {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = -1.7976931348623157E308;
        double maxY = -1.7976931348623157E308;
        for (final DataSet dataSet : this.dataSets) {
            minX = Math.min(minX, dataSet.getMinX());
            minY = Math.min(minY, dataSet.getMinY());
            maxX = Math.max(maxX, dataSet.getMaxX());
            maxY = Math.max(maxY, dataSet.getMaxY());
        }
        final Axes axes = new Axes(minX, minY, maxX, maxY);
        this.setAxes(axes);
    }
    
    public void zoomFullY() {
        double minY = Double.MAX_VALUE;
        double maxY = -1.7976931348623157E308;
        for (final DataSet dataSet : this.dataSets) {
            if (dataSet.isAlwaysIncreasingInX()) {
                int startIndex = Arrays.binarySearch(dataSet.getX(), this.getAxes().getMinX());
                if (startIndex < 0) {
                    startIndex = ~startIndex - 1;
                }
                if (startIndex < 0) {
                    startIndex = 0;
                }
                int endIndex = Arrays.binarySearch(dataSet.getX(), this.getAxes().getMaxX());
                if (endIndex < 0) {
                    endIndex = ~endIndex + 1;
                }
                if (endIndex >= dataSet.getX().length) {
                    endIndex = dataSet.getX().length - 1;
                }
                final double tempMinY = Statistics.min(dataSet.getY(), startIndex, endIndex - startIndex + 1);
                final double tempMaxY = Statistics.max(dataSet.getY(), startIndex, endIndex - startIndex + 1);
                minY = Math.min(tempMinY, minY);
                maxY = Math.max(tempMaxY, maxY);
            }
            else {
                System.out.println("QUICK");
                minY = Math.min(minY, dataSet.getMinY());
                maxY = Math.max(maxY, dataSet.getMaxY());
            }
        }
        final Axes axes = new Axes(this.getAxes().getMinX(), minY, this.getAxes().getMaxX(), maxY);
        this.setAxes(axes);
    }
    
    protected void renderData() {
        final BufferedImage dataRenderingImage = this.getPlotImage();
        final DataSetRenderingEngine dataSetRenderingEngine = new DataSetRenderingEngine(dataRenderingImage, this.getAxes());
        final Graphics2D g2 = dataSetRenderingEngine.getGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, dataRenderingImage.getWidth(), dataRenderingImage.getHeight());
        g2.setColor(Color.BLUE);
        for (final DataSet dataSet : this.dataSets) {
            dataSetRenderingEngine.render(dataSet);
        }
    }
}
