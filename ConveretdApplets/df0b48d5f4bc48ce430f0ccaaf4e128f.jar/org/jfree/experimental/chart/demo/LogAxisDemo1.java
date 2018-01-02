// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.experimental.chart.demo;

import java.awt.Window;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.experimental.chart.axis.LogAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.Dimension;
import org.jfree.ui.ApplicationFrame;

public class LogAxisDemo1 extends ApplicationFrame
{
    public LogAxisDemo1(final String title) {
        super(title);
        final JPanel chartPanel = createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        this.setContentPane(chartPanel);
    }
    
    private static JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createScatterPlot("Log Axis Demo 1", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        final XYPlot plot = (XYPlot)chart.getPlot();
        final LogAxis xAxis = new LogAxis("X");
        final LogAxis yAxis = new LogAxis("Y");
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);
        return chart;
    }
    
    private static XYDataset createDataset() {
        final XYSeries series = new XYSeries("Random Data");
        series.add(1.0, 500.2);
        series.add(5.0, 694.1);
        series.add(4.0, 100.0);
        series.add(12.5, 734.4);
        series.add(17.3, 453.2);
        series.add(21.2, 500.2);
        series.add(21.9, 9005.5);
        series.add(25.6, 734.4);
        series.add(3000.0, 453.2);
        return new XYSeriesCollection(series);
    }
    
    public static JPanel createDemoPanel() {
        final JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
    
    public static void main(final String[] args) {
        final LogAxisDemo1 demo = new LogAxisDemo1("Log Axis Demo 1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
