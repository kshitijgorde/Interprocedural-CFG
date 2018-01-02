// 
// Decompiled by Procyon v0.5.30
// 

package com.aviva.framework.applet;

import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.JFreeChart;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import org.jfree.chart.ChartPanel;
import java.awt.Paint;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import java.awt.Container;
import java.awt.Component;
import javax.swing.JLabel;
import org.jfree.data.xy.CategoryTableXYDataset;
import java.awt.Color;
import javax.swing.JApplet;

public class AreaChart extends JApplet
{
    private Color bgColor;
    private boolean showLegend;
    private boolean showToolTip;
    private boolean showUrl;
    private String verticalTitle;
    private String horizontalTitle;
    private String title;
    private int noOfSeries;
    private int availNoOfSeries;
    CategoryTableXYDataset xyDataSet;
    
    public AreaChart() {
        this.bgColor = Color.white;
        this.showLegend = true;
        this.showToolTip = true;
        this.showUrl = true;
        this.verticalTitle = "";
        this.horizontalTitle = "";
        this.title = "";
    }
    
    public void destroy() {
        this.verticalTitle = null;
        this.horizontalTitle = null;
        this.title = null;
        this.xyDataSet = null;
    }
    
    public void init() {
    }
    
    public void start() {
        this.verticalTitle = this.getParameter("verticalTitle");
        this.horizontalTitle = this.getParameter("horizontalTitle");
        this.title = this.getParameter("title");
        if (this.getParameter("bgColor") != null) {
            this.bgColor = Color.decode(this.getParameter("bgColor"));
        }
        this.noOfSeries = Integer.parseInt(this.getParameter("noOfSeries"));
        if (this.getParameter("showLegend") != null) {
            if (this.getParameter("showLegend").equalsIgnoreCase(Boolean.TRUE.toString())) {
                this.showLegend = true;
            }
            else {
                this.showLegend = false;
            }
        }
        if (this.getParameter("showToolTip") != null) {
            if (this.getParameter("showToolTip").equalsIgnoreCase(Boolean.TRUE.toString())) {
                this.showToolTip = true;
            }
            else {
                this.showToolTip = false;
            }
        }
        if (this.getParameter("showUrl") != null) {
            if (this.getParameter("showUrl").equalsIgnoreCase(Boolean.TRUE.toString())) {
                this.showUrl = true;
            }
            else {
                this.showUrl = false;
            }
        }
        if (this.getParameter("availNoOfSeries") != null) {
            this.availNoOfSeries = Integer.parseInt(this.getParameter("availNoOfSeries"));
        }
        this._setXyDataSet();
        this.repaintChart();
    }
    
    private void _setXyDataSet() {
        if (this.xyDataSet == null) {
            this.xyDataSet = new CategoryTableXYDataset();
        }
        int years = 0;
        try {
            years = Integer.parseInt(this.getParameter("noOfYears"));
        }
        catch (NumberFormatException e) {
            final Container contentPane = this.getContentPane();
            contentPane.add(new JLabel("Invalid Parameter Value : Years is not numeric"));
            this.repaint();
            return;
        }
        try {
            int i2 = 0;
            for (int i3 = 0; i3 < this.availNoOfSeries; ++i3) {
                boolean end = false;
                for (int j = 0; j < years; ++j) {
                    double xvalue;
                    double yvalue;
                    try {
                        xvalue = Double.parseDouble(this.getParameter("xvalue" + i2));
                        yvalue = Double.parseDouble(this.getParameter("yvalue" + i2));
                    }
                    catch (NullPointerException e2) {
                        return;
                    }
                    final String series = this.getParameter("series" + i2);
                    if (yvalue > 0.0) {
                        this.xyDataSet.add(xvalue, yvalue, series);
                    }
                    else if (!end) {
                        this.xyDataSet.add(xvalue, yvalue, series);
                        end = true;
                    }
                    ++i2;
                }
            }
        }
        catch (NumberFormatException e) {
            final Container contentPane = this.getContentPane();
            contentPane.add(new JLabel("Invalid Parameter Value : Value is not numeric."));
            this.repaint();
        }
    }
    
    public void resetXyDataSet() {
        this.xyDataSet = new CategoryTableXYDataset();
    }
    
    public void resetSeries(final String series) {
        if (this.xyDataSet == null) {
            this.xyDataSet = new CategoryTableXYDataset();
        }
        final double lowerBound = this.xyDataSet.getDomainLowerBound(false);
        for (double upperBound = this.xyDataSet.getDomainUpperBound(false), x = lowerBound; x <= upperBound; ++x) {
            try {
                this.xyDataSet.remove(x, series);
            }
            catch (IndexOutOfBoundsException e) {}
            catch (NullPointerException ex) {}
        }
    }
    
    public void putValue(final String series, final String xValue, final String yValue) {
        this.xyDataSet.add(Double.parseDouble(xValue), Double.parseDouble(yValue), series);
    }
    
    public void repaintChart() {
        final JFreeChart chart = ChartFactory.createXYAreaChart(this.title, this.horizontalTitle, this.verticalTitle, (XYDataset)this.xyDataSet, PlotOrientation.VERTICAL, this.showLegend, this.showToolTip, this.showUrl);
        chart.setBackgroundPaint((Paint)this.bgColor);
        final XYItemRenderer renderer = chart.getXYPlot().getRenderer();
        for (int i = 0; i < this.noOfSeries; ++i) {
            final Color color = Color.decode(this.getParameter("seriesColor" + i));
            renderer.setSeriesPaint(i, (Paint)color);
        }
        chart.getXYPlot().setForegroundAlpha(1.0f);
        final ChartPanel chartPanel = new ChartPanel(chart);
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(1, 1));
        contentPane.add((Component)chartPanel);
        this.repaint();
    }
}
