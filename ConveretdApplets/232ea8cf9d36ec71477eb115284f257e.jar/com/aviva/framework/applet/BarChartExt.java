// 
// Decompiled by Procyon v0.5.30
// 

package com.aviva.framework.applet;

import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JPanel;
import com.jrefinery.chart.renderer.CategoryItemRenderer;
import com.jrefinery.chart.axis.CategoryAxis;
import com.jrefinery.chart.axis.ValueAxis;
import com.jrefinery.chart.plot.CategoryPlot;
import java.awt.Font;
import com.jrefinery.chart.TextTitle;
import java.awt.Paint;
import com.jrefinery.chart.Legend;
import com.jrefinery.data.CategoryDataset;
import com.jrefinery.chart.ChartFactory;
import com.jrefinery.data.DefaultCategoryDataset;
import com.jrefinery.chart.ChartPanel;
import com.jrefinery.chart.JFreeChart;
import java.awt.Color;
import javax.swing.JApplet;

public class BarChartExt extends JApplet
{
    private Color color;
    private int titleSize;
    private int legendPosition;
    private boolean hideLegend;
    private boolean is3D;
    private boolean isHorizontal;
    private boolean stacked;
    private String verticalTitle;
    private String horizontalTitle;
    private String title;
    private JFreeChart barChart;
    private ChartPanel chartPanel;
    private DefaultCategoryDataset data;
    private int noOfBars;
    
    public BarChartExt() {
        this.color = Color.white;
        this.titleSize = 10;
        this.legendPosition = 3;
        this.hideLegend = false;
        this.is3D = false;
        this.isHorizontal = false;
        this.stacked = true;
        this.verticalTitle = "";
        this.horizontalTitle = "";
        this.title = "";
        this.barChart = null;
        this.chartPanel = null;
        this.data = new DefaultCategoryDataset();
        this.noOfBars = 0;
    }
    
    private void createChart() {
        final ChartPanel chart = null;
        JFreeChart jFreeChart = null;
        this.noOfBars = 0;
        if (this.is3D) {
            if (this.isHorizontal) {
                jFreeChart = ChartFactory.createHorizontalBarChart3D(this.getAppletInfo(), this.horizontalTitle, this.verticalTitle, (CategoryDataset)this.data, true, true, true);
            }
            else if (this.stacked) {
                jFreeChart = ChartFactory.createStackedVerticalBarChart3D(this.getAppletInfo(), this.horizontalTitle, this.verticalTitle, (CategoryDataset)this.data, true, true, true);
            }
            else {
                jFreeChart = ChartFactory.createVerticalBarChart3D(this.getAppletInfo(), this.horizontalTitle, this.verticalTitle, (CategoryDataset)this.data, true, true, true);
            }
        }
        else if (this.isHorizontal) {
            jFreeChart = ChartFactory.createHorizontalBarChart(this.getAppletInfo(), this.horizontalTitle, this.verticalTitle, (CategoryDataset)this.data, true, true, true);
        }
        else if (this.stacked) {
            jFreeChart = ChartFactory.createStackedVerticalBarChart(this.getAppletInfo(), this.horizontalTitle, this.verticalTitle, (CategoryDataset)this.data, true, true, true);
        }
        else {
            jFreeChart = ChartFactory.createVerticalBarChart(this.getAppletInfo(), this.horizontalTitle, this.verticalTitle, (CategoryDataset)this.data, true, true, true);
        }
        if (this.hideLegend) {
            jFreeChart.setLegend((Legend)null);
        }
        else {
            jFreeChart.getLegend().setAnchor(this.legendPosition);
        }
        final TextTitle title = jFreeChart.getTitle();
        if (title != null) {
            final Font titleFont = title.getFont();
            title.setFont(titleFont.deriveFont(0, this.titleSize));
        }
        jFreeChart.setTitle(this.title);
        final CategoryPlot plot = jFreeChart.getCategoryPlot();
        final ValueAxis range = plot.getRangeAxis();
        range.setUpperMargin(0.12);
        final CategoryAxis category = plot.getDomainAxis();
        category.setUpperMargin(0.25);
        category.setLowerMargin(0.25);
        final CategoryItemRenderer renderer = plot.getRenderer();
        plot.setValueLabelsVisible(false);
        plot.setVerticalValueLabels(false);
        jFreeChart.getPlot().setBackgroundPaint((Paint)this.color);
        jFreeChart.setBackgroundPaint((Paint)this.color);
        this.barChart = jFreeChart;
    }
    
    private void putValue2(final String key, final String value, final String colorCode, final boolean newBar) {
        final CategoryItemRenderer renderer = this.barChart.getCategoryPlot().getRenderer();
        Number number = null;
        try {
            number = new Double(value);
        }
        catch (NumberFormatException nfe) {
            return;
        }
        this.data.setValue(number, (Comparable)key, (Comparable)" ");
        if (colorCode != null) {
            renderer.setSeriesPaint(this.noOfBars, (Paint)Color.decode(colorCode));
        }
        if (newBar) {
            ++this.noOfBars;
        }
    }
    
    public void putValue(final String key, final String value) {
        this.putValue2(key, value, null, false);
        this.repaint();
    }
    
    private void showChart() {
        if (this.chartPanel == null) {
            this.chartPanel = new ChartPanel(this.barChart);
            final Container contentPane = this.getContentPane();
            final JPanel panel1 = new JPanel();
            final JPanel panel2 = new JPanel();
            panel1.setLayout(new GridLayout(1, 1));
            panel1.add((Component)this.chartPanel);
            panel2.setLayout(new GridLayout(1, 1));
            final Label lblTitle = new Label("          '000");
            lblTitle.setAlignment(0);
            panel2.add(lblTitle);
            panel1.setBackground(this.color);
            panel2.setBackground(this.color);
            contentPane.setLayout(new BorderLayout());
            contentPane.setBackground(this.color);
            contentPane.add(panel1, "South");
            contentPane.add(panel2, "North");
        }
        this.repaint();
    }
    
    public void init() {
        this.is3D = "true".equals(this.getParameter("three_d"));
        this.isHorizontal = "true".equals(this.getParameter("horizontal"));
        this.stacked = !"false".equals(this.getParameter("stacked"));
        try {
            final String red = this.getParameter("red");
            final String green = this.getParameter("green");
            final String blue = this.getParameter("blue");
            this.color = new Color(Integer.parseInt((red == null) ? "255" : red), Integer.parseInt((green == null) ? "255" : green), Integer.parseInt((blue == null) ? "255" : blue));
        }
        catch (NumberFormatException nfe) {
            System.err.println(nfe.getMessage() + "in arg. red, green, or blue");
        }
        final String mainTitle = this.getParameter("title");
        this.title = ((mainTitle != null) ? mainTitle : "");
        try {
            this.titleSize = Integer.parseInt(this.getParameter("title_size"));
        }
        catch (NumberFormatException nfe2) {
            this.titleSize = 10;
        }
        final String hTitle = this.getParameter("h_title");
        this.horizontalTitle = ((hTitle != null) ? hTitle : "");
        final String vTitle = this.getParameter("v_title");
        this.verticalTitle = ((vTitle != null) ? vTitle : "");
        if ("hide".equals(this.getParameter("legend"))) {
            this.hideLegend = true;
        }
        else {
            this.hideLegend = false;
            if ("west".equals(this.getParameter("legend"))) {
                this.legendPosition = 0;
            }
            else if ("north".equals(this.getParameter("legend"))) {
                this.legendPosition = 1;
            }
            else if ("east".equals(this.getParameter("legend"))) {
                this.legendPosition = 2;
            }
            else {
                this.legendPosition = 3;
            }
        }
        this.createChart();
        final String num = this.getParameter("num");
        if (num != null) {
            final int n = Integer.parseInt(num);
            String name = null;
            String value = null;
            String color = null;
            for (int i = 0; i < n; ++i) {
                name = this.getParameter("key" + i);
                value = this.getParameter("value" + i);
                color = this.getParameter("color" + i);
                this.putValue2(name, value, color, true);
            }
        }
        this.showChart();
    }
    
    public void destroy() {
        this.removeAll();
    }
}
