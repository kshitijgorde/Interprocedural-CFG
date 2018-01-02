// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.experimental.chart.demo;

import java.awt.Window;
import org.jfree.ui.RefineryUtilities;
import javax.swing.JPanel;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.annotations.XYAnnotation;
import org.jfree.chart.title.Title;
import org.jfree.experimental.chart.annotations.XYTitleAnnotation;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.block.BlockFrame;
import org.jfree.chart.block.BlockBorder;
import java.awt.Font;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.title.LegendTitle;
import org.jfree.ui.RectangleInsets;
import org.jfree.chart.plot.XYPlot;
import java.awt.Paint;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import java.awt.Container;
import java.awt.Dimension;
import org.jfree.chart.ChartPanel;
import org.jfree.ui.ApplicationFrame;

public class XYTitleAnnotationDemo1 extends ApplicationFrame
{
    static /* synthetic */ Class class$org$jfree$data$time$Month;
    
    public XYTitleAnnotationDemo1(final String title) {
        super(title);
        final ChartPanel chartPanel = (ChartPanel)createDemoPanel();
        chartPanel.setPreferredSize(new Dimension(500, 270));
        chartPanel.setMouseZoomable(true, false);
        this.setContentPane(chartPanel);
    }
    
    private static JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart chart = ChartFactory.createTimeSeriesChart("Legal & General Unit Trust Prices", "Date", "Price Per Unit", dataset, false, true, false);
        chart.setBackgroundPaint(Color.white);
        final XYPlot plot = (XYPlot)chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        final LegendTitle lt = new LegendTitle(plot);
        lt.setItemFont(new Font("Dialog", 0, 9));
        lt.setBackgroundPaint(new Color(200, 200, 255, 100));
        lt.setFrame(new BlockBorder(Color.white));
        lt.setPosition(RectangleEdge.BOTTOM);
        final XYTitleAnnotation ta = new XYTitleAnnotation(0.98, 0.02, lt, RectangleAnchor.BOTTOM_RIGHT);
        ta.setMaxWidth(0.48);
        plot.addAnnotation(ta);
        final XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            final XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer)r;
            renderer.setBaseShapesVisible(true);
            renderer.setBaseShapesFilled(true);
        }
        final DateAxis axis = (DateAxis)plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        final ValueAxis yAxis = plot.getRangeAxis();
        yAxis.setLowerMargin(0.35);
        return chart;
    }
    
    private static XYDataset createDataset() {
        final TimeSeries s1 = new TimeSeries("L&G European Index Trust", (XYTitleAnnotationDemo1.class$org$jfree$data$time$Month == null) ? (XYTitleAnnotationDemo1.class$org$jfree$data$time$Month = class$("org.jfree.data.time.Month")) : XYTitleAnnotationDemo1.class$org$jfree$data$time$Month);
        s1.add(new Month(2, 2001), 181.8);
        s1.add(new Month(3, 2001), 167.3);
        s1.add(new Month(4, 2001), 153.8);
        s1.add(new Month(5, 2001), 167.6);
        s1.add(new Month(6, 2001), 158.8);
        s1.add(new Month(7, 2001), 148.3);
        s1.add(new Month(8, 2001), 153.9);
        s1.add(new Month(9, 2001), 142.7);
        s1.add(new Month(10, 2001), 123.2);
        s1.add(new Month(11, 2001), 131.8);
        s1.add(new Month(12, 2001), 139.6);
        s1.add(new Month(1, 2002), 142.9);
        s1.add(new Month(2, 2002), 138.7);
        s1.add(new Month(3, 2002), 137.3);
        s1.add(new Month(4, 2002), 143.9);
        s1.add(new Month(5, 2002), 139.8);
        s1.add(new Month(6, 2002), 137.0);
        s1.add(new Month(7, 2002), 132.8);
        final TimeSeries s2 = new TimeSeries("L&G UK Index Trust", (XYTitleAnnotationDemo1.class$org$jfree$data$time$Month == null) ? (XYTitleAnnotationDemo1.class$org$jfree$data$time$Month = class$("org.jfree.data.time.Month")) : XYTitleAnnotationDemo1.class$org$jfree$data$time$Month);
        s2.add(new Month(2, 2001), 129.6);
        s2.add(new Month(3, 2001), 123.2);
        s2.add(new Month(4, 2001), 117.2);
        s2.add(new Month(5, 2001), 124.1);
        s2.add(new Month(6, 2001), 122.6);
        s2.add(new Month(7, 2001), 119.2);
        s2.add(new Month(8, 2001), 116.5);
        s2.add(new Month(9, 2001), 112.7);
        s2.add(new Month(10, 2001), 101.5);
        s2.add(new Month(11, 2001), 106.1);
        s2.add(new Month(12, 2001), 110.3);
        s2.add(new Month(1, 2002), 111.7);
        s2.add(new Month(2, 2002), 111.0);
        s2.add(new Month(3, 2002), 109.6);
        s2.add(new Month(4, 2002), 113.2);
        s2.add(new Month(5, 2002), 111.6);
        s2.add(new Month(6, 2002), 108.8);
        s2.add(new Month(7, 2002), 101.6);
        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        return dataset;
    }
    
    public static JPanel createDemoPanel() {
        final JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
    
    public static void main(final String[] args) {
        final XYTitleAnnotationDemo1 demo = new XYTitleAnnotationDemo1("XYTitleAnnotationDemo1");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
