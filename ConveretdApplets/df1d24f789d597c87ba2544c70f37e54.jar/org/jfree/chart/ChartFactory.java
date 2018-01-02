// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart;

import org.jfree.chart.renderer.WaferMapRenderer;
import org.jfree.chart.plot.WaferMapPlot;
import org.jfree.data.WaferMapDataset;
import org.jfree.chart.renderer.WindItemRenderer;
import org.jfree.data.WindDataset;
import org.jfree.chart.renderer.XYBoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
import org.jfree.chart.urls.StandardXYZURLGenerator;
import org.jfree.chart.labels.StandardXYZToolTipGenerator;
import org.jfree.chart.renderer.XYBubbleRenderer;
import org.jfree.data.XYZDataset;
import org.jfree.chart.renderer.SignalRenderer;
import org.jfree.data.SignalsDataset;
import org.jfree.chart.axis.Timeline;
import org.jfree.chart.labels.HighLowItemLabelGenerator;
import org.jfree.chart.renderer.HighLowRenderer;
import org.jfree.chart.renderer.CandlestickRenderer;
import org.jfree.data.HighLowDataset;
import org.jfree.chart.renderer.XYStepAreaRenderer;
import org.jfree.chart.renderer.XYStepRenderer;
import org.jfree.chart.renderer.StackedXYAreaRenderer;
import org.jfree.data.TableXYDataset;
import org.jfree.chart.renderer.XYAreaRenderer;
import org.jfree.chart.renderer.XYBarRenderer;
import org.jfree.data.IntervalXYDataset;
import org.jfree.chart.urls.XYURLGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.renderer.StandardXYItemRenderer;
import org.jfree.chart.urls.StandardXYURLGenerator;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.renderer.XYItemRenderer;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.PolarItemRenderer;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.data.XYDataset;
import org.jfree.chart.plot.Marker;
import org.jfree.ui.Layer;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.WaterfallBarRenderer;
import org.jfree.chart.labels.IntervalCategoryToolTipGenerator;
import java.text.DateFormat;
import org.jfree.chart.renderer.GanttRenderer;
import org.jfree.chart.axis.DateAxis;
import org.jfree.data.IntervalCategoryDataset;
import org.jfree.chart.renderer.LineAndShapeRenderer;
import org.jfree.chart.renderer.StackedAreaRenderer;
import org.jfree.chart.renderer.AreaRenderer;
import org.jfree.chart.renderer.StackedBarRenderer3D;
import org.jfree.util.SortOrder;
import org.jfree.chart.renderer.BarRenderer3D;
import org.jfree.chart.axis.NumberAxis3D;
import org.jfree.chart.axis.CategoryAxis3D;
import org.jfree.chart.renderer.StackedBarRenderer;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.renderer.CategoryItemRenderer;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.urls.CategoryURLGenerator;
import org.jfree.chart.urls.StandardCategoryURLGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.ui.TextAnchor;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.renderer.BarRenderer;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.plot.PiePlot3D;
import java.awt.Stroke;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.util.TableOrder;
import org.jfree.data.CategoryDataset;
import java.util.Iterator;
import java.util.List;
import org.jfree.chart.title.Title;
import org.jfree.chart.title.TextTitle;
import java.awt.Font;
import java.text.NumberFormat;
import java.awt.Paint;
import java.awt.Color;
import org.jfree.data.DefaultPieDataset;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.urls.PieURLGenerator;
import org.jfree.chart.urls.StandardPieURLGenerator;
import org.jfree.chart.labels.PieToolTipGenerator;
import java.awt.Insets;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieItemLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.PieDataset;

public abstract class ChartFactory
{
    public static JFreeChart createPieChart(final String title, final PieDataset dataset, final boolean legend, final boolean tooltips, final boolean urls) {
        final PiePlot plot = new PiePlot(dataset);
        plot.setLabelGenerator(new StandardPieItemLabelGenerator());
        plot.setInsets(new Insets(0, 5, 5, 5));
        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieItemLabelGenerator("{0} = {1}"));
        }
        if (urls) {
            plot.setURLGenerator(new StandardPieURLGenerator());
        }
        return new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
    }
    
    public static JFreeChart createPieChart(final String title, final PieDataset dataset, final PieDataset previousDataset, final int percentDiffForMaxScale, final boolean greenForIncrease, final boolean legend, final boolean tooltips, final boolean urls, final boolean subTitle, final boolean showDifference) {
        final PiePlot plot = new PiePlot(dataset);
        plot.setLabelGenerator(new StandardPieItemLabelGenerator());
        plot.setInsets(new Insets(0, 5, 5, 5));
        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieItemLabelGenerator("{0} = {1}"));
        }
        if (urls) {
            plot.setURLGenerator(new StandardPieURLGenerator());
        }
        final List keys = dataset.getKeys();
        DefaultPieDataset series = null;
        if (showDifference) {
            series = new DefaultPieDataset();
        }
        final double colorPerPercent = 255.0 / percentDiffForMaxScale;
        for (final Comparable key : keys) {
            final Number newValue = dataset.getValue(key);
            final Number oldValue = previousDataset.getValue(key);
            final int section = dataset.getIndex(key);
            if (oldValue == null) {
                if (greenForIncrease) {
                    plot.setSectionPaint(section, Color.green);
                }
                else {
                    plot.setSectionPaint(section, Color.red);
                }
                if (!showDifference) {
                    continue;
                }
                series.setValue(key + " (+100%)", newValue);
            }
            else {
                final double percentChange = (newValue.doubleValue() / oldValue.doubleValue() - 1.0) * 100.0;
                final double shade = (Math.abs(percentChange) >= percentDiffForMaxScale) ? 255.0 : (Math.abs(percentChange) * colorPerPercent);
                if ((greenForIncrease && newValue.doubleValue() > oldValue.doubleValue()) || (!greenForIncrease && newValue.doubleValue() < oldValue.doubleValue())) {
                    plot.setSectionPaint(section, new Color(0, (int)shade, 0));
                }
                else {
                    plot.setSectionPaint(section, new Color((int)shade, 0, 0));
                }
                if (!showDifference) {
                    continue;
                }
                series.setValue(key + " (" + ((percentChange >= 0.0) ? "+" : "") + NumberFormat.getPercentInstance().format(percentChange / 100.0) + ")", newValue);
            }
        }
        if (showDifference) {
            plot.setDataset(series);
        }
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        if (subTitle) {
            TextTitle subtitle = null;
            subtitle = new TextTitle("Bright " + (greenForIncrease ? "red" : "green") + "=change >=-" + percentDiffForMaxScale + "%, Bright " + (greenForIncrease ? "green" : "red") + "=change >=+" + percentDiffForMaxScale + "%", new Font("SansSerif", 0, 10));
            chart.addSubtitle(subtitle);
        }
        return chart;
    }
    
    public static JFreeChart createMultiplePieChart(final String title, final CategoryDataset dataset, final TableOrder order, final boolean legend, final boolean tooltips, final boolean urls) {
        if (order == null) {
            throw new IllegalArgumentException("Null 'order' argument.");
        }
        final MultiplePiePlot plot = new MultiplePiePlot(dataset);
        plot.setDataExtractOrder(order);
        plot.setBackgroundPaint(null);
        plot.setOutlineStroke(null);
        if (tooltips) {
            final PieToolTipGenerator tooltipGenerator = new StandardPieItemLabelGenerator();
            final PiePlot pp = (PiePlot)plot.getPieChart().getPlot();
            pp.setToolTipGenerator(tooltipGenerator);
        }
        if (urls) {
            final PieURLGenerator urlGenerator = new StandardPieURLGenerator();
            final PiePlot pp = (PiePlot)plot.getPieChart().getPlot();
            pp.setURLGenerator(urlGenerator);
        }
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createPieChart3D(final String title, final PieDataset dataset, final boolean legend, final boolean tooltips, final boolean urls) {
        final PiePlot3D plot = new PiePlot3D(dataset);
        plot.setInsets(new Insets(0, 5, 5, 5));
        if (tooltips) {
            plot.setToolTipGenerator(new StandardPieItemLabelGenerator());
        }
        if (urls) {
            plot.setURLGenerator(new StandardPieURLGenerator());
        }
        return new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
    }
    
    public static JFreeChart createMultiplePieChart3D(final String title, final CategoryDataset dataset, final TableOrder order, final boolean legend, final boolean tooltips, final boolean urls) {
        if (order == null) {
            throw new IllegalArgumentException("Null 'order' argument.");
        }
        final MultiplePiePlot plot = new MultiplePiePlot(dataset);
        plot.setDataExtractOrder(order);
        plot.setBackgroundPaint(null);
        plot.setOutlineStroke(null);
        final JFreeChart pieChart = new JFreeChart(new PiePlot3D(null));
        final TextTitle seriesTitle = new TextTitle("Series Title", new Font("SansSerif", 1, 12));
        seriesTitle.setPosition(RectangleEdge.BOTTOM);
        pieChart.setTitle(seriesTitle);
        pieChart.setBackgroundPaint(null);
        plot.setPieChart(pieChart);
        if (tooltips) {
            final PieToolTipGenerator tooltipGenerator = new StandardPieItemLabelGenerator();
            final PiePlot pp = (PiePlot)plot.getPieChart().getPlot();
            pp.setToolTipGenerator(tooltipGenerator);
        }
        if (urls) {
            final PieURLGenerator urlGenerator = new StandardPieURLGenerator();
            final PiePlot pp = (PiePlot)plot.getPieChart().getPlot();
            pp.setURLGenerator(urlGenerator);
        }
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createBarChart(final String title, final String categoryAxisLabel, final String valueAxisLabel, final CategoryDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        final ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
        final BarRenderer renderer = new BarRenderer();
        if (orientation == PlotOrientation.HORIZONTAL) {
            final ItemLabelPosition position1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
            renderer.setPositiveItemLabelPosition(position1);
            final ItemLabelPosition position2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
            renderer.setNegativeItemLabelPosition(position2);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            final ItemLabelPosition position1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
            renderer.setPositiveItemLabelPosition(position1);
            final ItemLabelPosition position2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
            renderer.setNegativeItemLabelPosition(position2);
        }
        if (tooltips) {
            renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        }
        if (urls) {
            renderer.setItemURLGenerator(new StandardCategoryURLGenerator());
        }
        final CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createStackedBarChart(final String title, final String domainAxisLabel, final String rangeAxisLabel, final CategoryDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final CategoryAxis categoryAxis = new CategoryAxis(domainAxisLabel);
        final ValueAxis valueAxis = new NumberAxis(rangeAxisLabel);
        final StackedBarRenderer renderer = new StackedBarRenderer();
        if (tooltips) {
            renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        }
        if (urls) {
            renderer.setItemURLGenerator(new StandardCategoryURLGenerator());
        }
        final CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createBarChart3D(final String title, final String categoryAxisLabel, final String valueAxisLabel, final CategoryDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final CategoryAxis categoryAxis = new CategoryAxis3D(categoryAxisLabel);
        final ValueAxis valueAxis = new NumberAxis3D(valueAxisLabel);
        final BarRenderer3D renderer = new BarRenderer3D();
        if (tooltips) {
            renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        }
        if (urls) {
            renderer.setItemURLGenerator(new StandardCategoryURLGenerator());
        }
        final CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
        if (orientation == PlotOrientation.HORIZONTAL) {
            plot.setRowRenderingOrder(SortOrder.DESCENDING);
            plot.setColumnRenderingOrder(SortOrder.DESCENDING);
        }
        plot.setForegroundAlpha(0.75f);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createStackedBarChart3D(final String title, final String categoryAxisLabel, final String valueAxisLabel, final CategoryDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final CategoryAxis categoryAxis = new CategoryAxis3D(categoryAxisLabel);
        final ValueAxis valueAxis = new NumberAxis3D(valueAxisLabel);
        final CategoryItemRenderer renderer = new StackedBarRenderer3D();
        if (tooltips) {
            renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        }
        if (urls) {
            renderer.setItemURLGenerator(new StandardCategoryURLGenerator());
        }
        final CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
        if (orientation == PlotOrientation.HORIZONTAL) {
            plot.setColumnRenderingOrder(SortOrder.DESCENDING);
        }
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createAreaChart(final String title, final String categoryAxisLabel, final String valueAxisLabel, final CategoryDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        categoryAxis.setCategoryMargin(0.0);
        final ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
        final AreaRenderer renderer = new AreaRenderer();
        if (tooltips) {
            renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        }
        if (urls) {
            renderer.setItemURLGenerator(new StandardCategoryURLGenerator());
        }
        final CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createStackedAreaChart(final String title, final String categoryAxisLabel, final String valueAxisLabel, final CategoryDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        final ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
        final StackedAreaRenderer renderer = new StackedAreaRenderer();
        if (tooltips) {
            renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        }
        if (urls) {
            renderer.setItemURLGenerator(new StandardCategoryURLGenerator());
        }
        final CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createLineChart(final String title, final String categoryAxisLabel, final String valueAxisLabel, final CategoryDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        final ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
        final LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setDrawLines(true);
        renderer.setDrawShapes(false);
        if (tooltips) {
            renderer.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        }
        if (urls) {
            renderer.setItemURLGenerator(new StandardCategoryURLGenerator());
        }
        final CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createGanttChart(final String title, final String categoryAxisLabel, final String dateAxisLabel, final IntervalCategoryDataset dataset, final boolean legend, final boolean tooltips, final boolean urls) {
        final CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        final DateAxis dateAxis = new DateAxis(dateAxisLabel);
        final CategoryItemRenderer renderer = new GanttRenderer();
        if (tooltips) {
            renderer.setToolTipGenerator(new IntervalCategoryToolTipGenerator("{3} - {4}", DateFormat.getDateInstance()));
        }
        if (urls) {
            renderer.setItemURLGenerator(new StandardCategoryURLGenerator());
        }
        final CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, dateAxis, renderer);
        plot.setOrientation(PlotOrientation.HORIZONTAL);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createWaterfallChart(final String title, final String categoryAxisLabel, final String valueAxisLabel, final CategoryDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
        categoryAxis.setCategoryMargin(0.0);
        final ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
        final WaterfallBarRenderer renderer = new WaterfallBarRenderer();
        if (orientation == PlotOrientation.HORIZONTAL) {
            final ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 1.5707963267948966);
            renderer.setPositiveItemLabelPosition(position);
            renderer.setNegativeItemLabelPosition(position);
        }
        else if (orientation == PlotOrientation.VERTICAL) {
            final ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 0.0);
            renderer.setPositiveItemLabelPosition(position);
            renderer.setNegativeItemLabelPosition(position);
        }
        if (tooltips) {
            final StandardCategoryToolTipGenerator generator = new StandardCategoryToolTipGenerator();
            renderer.setToolTipGenerator(generator);
        }
        if (urls) {
            renderer.setItemURLGenerator(new StandardCategoryURLGenerator());
        }
        final CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
        plot.clearRangeMarkers();
        final Marker baseline = new ValueMarker(0.0);
        baseline.setPaint(Color.black);
        plot.addRangeMarker(baseline, Layer.FOREGROUND);
        plot.setOrientation(orientation);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createPolarChart(final String title, final XYDataset dataset, final boolean legend, final boolean tooltips, final boolean urls) {
        final PolarPlot plot = new PolarPlot();
        plot.setDataset(dataset);
        final NumberAxis rangeAxis = new NumberAxis();
        rangeAxis.setAxisLineVisible(false);
        rangeAxis.setTickMarksVisible(false);
        rangeAxis.setTickLabelInsets(new Insets(0, 0, 0, 0));
        plot.setRadialAxis(rangeAxis);
        plot.setRenderer(new DefaultPolarItemRenderer());
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createScatterPlot(final String title, final String xAxisLabel, final String yAxisLabel, final XYDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        final NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setAutoRangeIncludesZero(false);
        final XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = new StandardXYToolTipGenerator();
        }
        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        final StandardXYItemRenderer renderer = new StandardXYItemRenderer(1, toolTipGenerator, urlGenerator);
        renderer.setShapesFilled(Boolean.TRUE);
        plot.setRenderer(renderer);
        plot.setOrientation(orientation);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createXYBarChart(final String title, final String xAxisLabel, final boolean dateAxis, final String yAxisLabel, final IntervalXYDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        ValueAxis domainAxis = null;
        if (dateAxis) {
            domainAxis = new DateAxis(xAxisLabel);
        }
        else {
            final NumberAxis axis = new NumberAxis(xAxisLabel);
            axis.setAutoRangeIncludesZero(false);
            domainAxis = axis;
        }
        final ValueAxis valueAxis = new NumberAxis(yAxisLabel);
        final XYBarRenderer renderer = new XYBarRenderer();
        if (tooltips) {
            renderer.setToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }
        final XYPlot plot = new XYPlot(dataset, domainAxis, valueAxis, renderer);
        plot.setOrientation(orientation);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createXYAreaChart(final String title, final String xAxisLabel, final String yAxisLabel, final XYDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        final NumberAxis yAxis = new NumberAxis(yAxisLabel);
        final XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
        plot.setOrientation(orientation);
        XYToolTipGenerator tipGenerator = null;
        if (tooltips) {
            tipGenerator = new StandardXYToolTipGenerator();
        }
        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        plot.setRenderer(new XYAreaRenderer(4, tipGenerator, urlGenerator));
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createStackedXYAreaChart(final String title, final String xAxisLabel, final String yAxisLabel, final TableXYDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        xAxis.setLowerMargin(0.0);
        xAxis.setUpperMargin(0.0);
        final NumberAxis yAxis = new NumberAxis(yAxisLabel);
        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = new StandardXYToolTipGenerator();
        }
        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        final StackedXYAreaRenderer renderer = new StackedXYAreaRenderer(4, toolTipGenerator, urlGenerator);
        renderer.setOutline(true);
        final XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
        plot.setRangeAxis(yAxis);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createXYLineChart(final String title, final String xAxisLabel, final String yAxisLabel, final XYDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        final NumberAxis yAxis = new NumberAxis(yAxisLabel);
        final XYItemRenderer renderer = new StandardXYItemRenderer(2);
        final XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
        if (tooltips) {
            renderer.setToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createXYStepChart(final String title, final String xAxisLabel, final String yAxisLabel, final XYDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final DateAxis xAxis = new DateAxis(xAxisLabel);
        final NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = new StandardXYToolTipGenerator();
        }
        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        final XYItemRenderer renderer = new XYStepRenderer(toolTipGenerator, urlGenerator);
        final XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
        plot.setRenderer(renderer);
        plot.setOrientation(orientation);
        plot.setDomainCrosshairVisible(false);
        plot.setRangeCrosshairVisible(false);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createXYStepAreaChart(final String title, final String xAxisLabel, final String yAxisLabel, final XYDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        final NumberAxis yAxis = new NumberAxis(yAxisLabel);
        XYToolTipGenerator toolTipGenerator = null;
        if (tooltips) {
            toolTipGenerator = new StandardXYToolTipGenerator();
        }
        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        final XYItemRenderer renderer = new XYStepAreaRenderer(3, toolTipGenerator, urlGenerator);
        final XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
        plot.setRenderer(renderer);
        plot.setOrientation(orientation);
        plot.setDomainCrosshairVisible(false);
        plot.setRangeCrosshairVisible(false);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createTimeSeriesChart(final String title, final String timeAxisLabel, final String valueAxisLabel, final XYDataset dataset, final boolean legend, final boolean tooltips, final boolean urls) {
        final ValueAxis timeAxis = new DateAxis(timeAxisLabel);
        timeAxis.setLowerMargin(0.02);
        timeAxis.setUpperMargin(0.02);
        final NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        valueAxis.setAutoRangeIncludesZero(false);
        final XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, null);
        XYToolTipGenerator labelGenerator = null;
        if (tooltips) {
            labelGenerator = StandardXYToolTipGenerator.getTimeSeriesInstance();
        }
        XYURLGenerator urlGenerator = null;
        if (urls) {
            urlGenerator = new StandardXYURLGenerator();
        }
        plot.setRenderer(new StandardXYItemRenderer(2, labelGenerator, urlGenerator));
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createCandlestickChart(final String title, final String timeAxisLabel, final String valueAxisLabel, final HighLowDataset dataset, final boolean legend) {
        final ValueAxis timeAxis = new DateAxis(timeAxisLabel);
        final NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        final XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, null);
        plot.setRenderer(new CandlestickRenderer());
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createHighLowChart(final String title, final String timeAxisLabel, final String valueAxisLabel, final HighLowDataset dataset, final boolean legend) {
        final ValueAxis timeAxis = new DateAxis(timeAxisLabel);
        final NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        final HighLowRenderer renderer = new HighLowRenderer();
        renderer.setToolTipGenerator(new HighLowItemLabelGenerator());
        final XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createHighLowChart(final String title, final String timeAxisLabel, final String valueAxisLabel, final HighLowDataset dataset, final Timeline timeline, final boolean legend) {
        final ValueAxis timeAxis = new DateAxis(timeAxisLabel, timeline);
        final NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        final HighLowRenderer renderer = new HighLowRenderer();
        renderer.setToolTipGenerator(new HighLowItemLabelGenerator());
        final XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createSignalChart(final String title, final String timeAxisLabel, final String valueAxisLabel, final SignalsDataset dataset, final boolean legend) {
        final ValueAxis timeAxis = new DateAxis(timeAxisLabel);
        final NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        final XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, null);
        plot.setRenderer(new SignalRenderer());
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createBubbleChart(final String title, final String xAxisLabel, final String yAxisLabel, final XYZDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final NumberAxis xAxis = new NumberAxis(xAxisLabel);
        xAxis.setAutoRangeIncludesZero(false);
        final NumberAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setAutoRangeIncludesZero(false);
        final XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
        final XYItemRenderer renderer = new XYBubbleRenderer(2);
        if (tooltips) {
            renderer.setToolTipGenerator(new StandardXYZToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYZURLGenerator());
        }
        plot.setRenderer(renderer);
        plot.setOrientation(orientation);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createHistogram(final String title, final String xAxisLabel, final String yAxisLabel, final IntervalXYDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final ValueAxis xAxis = new NumberAxis(xAxisLabel);
        final ValueAxis yAxis = new NumberAxis(yAxisLabel);
        final XYItemRenderer renderer = new XYBarRenderer();
        if (tooltips) {
            renderer.setToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }
        final XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setOrientation(orientation);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createBoxAndWhiskerChart(final String title, final String timeAxisLabel, final String valueAxisLabel, final BoxAndWhiskerXYDataset dataset, final boolean legend) {
        final ValueAxis timeAxis = new DateAxis(timeAxisLabel);
        final NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
        valueAxis.setAutoRangeIncludesZero(false);
        final XYBoxAndWhiskerRenderer renderer = new XYBoxAndWhiskerRenderer(10.0);
        final XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createWindPlot(final String title, final String xAxisLabel, final String yAxisLabel, final WindDataset dataset, final boolean legend, final boolean tooltips, final boolean urls) {
        final ValueAxis xAxis = new DateAxis(xAxisLabel);
        final ValueAxis yAxis = new NumberAxis(yAxisLabel);
        yAxis.setRange(-12.0, 12.0);
        final WindItemRenderer renderer = new WindItemRenderer();
        if (tooltips) {
            renderer.setToolTipGenerator(new StandardXYToolTipGenerator());
        }
        if (urls) {
            renderer.setURLGenerator(new StandardXYURLGenerator());
        }
        final XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createWaferMapChart(final String title, final WaferMapDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        if (orientation == null) {
            throw new IllegalArgumentException("Null 'orientation' argument.");
        }
        final WaferMapPlot plot = new WaferMapPlot(dataset);
        final WaferMapRenderer renderer = new WaferMapRenderer();
        plot.setRenderer(renderer);
        final JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
        return chart;
    }
    
    public static JFreeChart createPie3DChart(final String title, final PieDataset data, final boolean legend, final boolean tooltips, final boolean urls) {
        return createPieChart3D(title, data, legend, tooltips, urls);
    }
    
    public static JFreeChart createLineXYChart(final String title, final String xAxisLabel, final String yAxisLabel, final XYDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        return createXYLineChart(title, xAxisLabel, yAxisLabel, dataset, orientation, legend, tooltips, urls);
    }
    
    public static JFreeChart createAreaXYChart(final String title, final String xAxisLabel, final String yAxisLabel, final XYDataset data, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        return createXYAreaChart(title, xAxisLabel, yAxisLabel, data, orientation, legend, tooltips, urls);
    }
    
    public static JFreeChart createStackedAreaXYChart(final String title, final String xAxisLabel, final String yAxisLabel, final TableXYDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        return createStackedXYAreaChart(title, xAxisLabel, yAxisLabel, dataset, orientation, legend, tooltips, urls);
    }
    
    public static JFreeChart createXYBarChart(final String title, final String xAxisLabel, final String yAxisLabel, final IntervalXYDataset dataset, final PlotOrientation orientation, final boolean legend, final boolean tooltips, final boolean urls) {
        return createXYBarChart(title, xAxisLabel, true, yAxisLabel, dataset, orientation, legend, tooltips, urls);
    }
}
