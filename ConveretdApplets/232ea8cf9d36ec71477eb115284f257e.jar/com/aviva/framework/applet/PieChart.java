// 
// Decompiled by Procyon v0.5.30
// 

package com.aviva.framework.applet;

import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import com.jrefinery.chart.plot.Plot;
import com.jrefinery.chart.plot.Pie3DPlot;
import com.jrefinery.chart.plot.PiePlot;
import java.awt.Paint;
import com.jrefinery.chart.Legend;
import com.jrefinery.data.PieDataset;
import com.jrefinery.chart.ChartFactory;
import java.text.NumberFormat;
import com.jrefinery.data.DefaultPieDataset;
import java.awt.Color;
import com.jrefinery.chart.JFreeChart;
import com.jrefinery.chart.ChartPanel;
import javax.swing.JApplet;

public class PieChart extends JApplet
{
    private String[] keys;
    private String[] clr;
    private Double[] rawData;
    private String[] value;
    public ChartPanel chart;
    private JFreeChart chartData;
    public int numParam;
    public int index;
    private boolean is3D;
    private int legendPosition;
    private boolean hideLegend;
    private Color color;
    boolean isHideLabel;
    
    public PieChart() {
        this.keys = null;
        this.clr = null;
        this.rawData = null;
        this.chart = null;
        this.chartData = null;
        this.numParam = 0;
        this.index = 0;
        this.is3D = false;
        this.legendPosition = 2;
        this.hideLegend = false;
        this.color = Color.white;
        this.isHideLabel = false;
    }
    
    private void chartPanel() {
        this.generateData();
        this.chart = new ChartPanel(this.chartData);
    }
    
    public void destroy() {
        this.removeAll();
    }
    
    public void generateData() {
        try {
            DefaultPieDataset data = null;
            double eval = 0.0;
            data = new DefaultPieDataset();
            double temp = 0.0;
            for (int i = 0; i < this.numParam; ++i) {
                temp += Double.parseDouble(this.value[i]);
            }
            final NumberFormat t = NumberFormat.getInstance();
            t.setMaximumFractionDigits(2);
            for (int j = 0; j < this.numParam; ++j) {
                if (temp != 0.0) {
                    final double tData = Double.parseDouble(this.value[j]);
                    eval = tData / temp * 100.0;
                }
                else {
                    eval = 0.0;
                }
                this.rawData[j] = new Double(t.parse(t.format(eval)).doubleValue());
                data.setValue((Comparable)this.keys[j], (Number)this.rawData[j]);
            }
            if (this.is3D) {
                this.chartData = ChartFactory.createPie3DChart(this.getAppletInfo(), (PieDataset)data, true, true, true);
            }
            else {
                this.chartData = ChartFactory.createPieChart(this.getAppletInfo(), (PieDataset)data, true, true, true);
            }
            if (this.hideLegend) {
                this.chartData.setLegend((Legend)null);
            }
            else {
                this.chartData.getLegend().setAnchor(this.legendPosition);
            }
            this.chartData.getPlot().setBackgroundPaint((Paint)this.color);
            for (int k = 0; k < this.numParam; ++k) {
                final String colour = this.getParameter("colour" + k);
                this.putColour(colour, "" + k);
            }
            for (int k = 0; k < this.numParam; ++k) {
                final Plot p = this.chartData.getPlot();
                if (p instanceof PiePlot) {
                    ((PiePlot)p).setPaint(k, (Paint)Color.decode(this.clr[k]));
                }
                else if (p instanceof Pie3DPlot) {
                    ((Pie3DPlot)p).setPaint(k, (Paint)Color.decode(this.clr[k]));
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void init() {
        this.is3D = "true".equals(this.getParameter("three_d"));
        this.isHideLabel = Boolean.valueOf(this.getParameter("hide_label"));
        try {
            final String red = this.getParameter("red");
            final String green = this.getParameter("green");
            final String blue = this.getParameter("blue");
            this.color = new Color(Integer.parseInt((red == null) ? "255" : red), Integer.parseInt((green == null) ? "255" : green), Integer.parseInt((blue == null) ? "255" : blue));
        }
        catch (NumberFormatException nfe) {
            System.err.println(nfe.getMessage() + "in arg. red, green, or blue");
        }
        final String num = this.getParameter("num");
        if (num != null) {
            final int n = Integer.parseInt(num);
            this.setNum(num);
            for (int i = 0; i < n; ++i) {
                final String name = this.getParameter("name" + i);
                final String value = this.getParameter("value" + i);
                this.putValue(name, value, "" + i);
            }
        }
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
            else if ("south".equals(this.getParameter("legend"))) {
                this.legendPosition = 3;
            }
            else {
                this.legendPosition = 2;
            }
        }
        this.chartPanel();
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(1, 1));
        contentPane.add((Component)this.chart);
    }
    
    public void putValue(final String vVar, final String vlVar, final String idx) {
        try {
            this.index = Integer.parseInt(idx);
            if (vVar != null) {
                this.keys[this.index] = vVar;
            }
            if (vlVar != null) {
                this.value[this.index] = new String(vlVar);
            }
        }
        catch (Exception ex) {}
    }
    
    public void putColour(final String vVar, final String idx) {
        try {
            this.index = Integer.parseInt(idx);
            if (vVar != null) {
                this.clr[this.index] = vVar;
            }
        }
        catch (Exception ex) {}
    }
    
    public void setNum(final String numPrm) {
        try {
            this.numParam = Integer.parseInt(numPrm);
            this.keys = new String[this.numParam];
            this.rawData = new Double[this.numParam];
            this.value = new String[this.numParam];
            this.clr = new String[this.numParam];
        }
        catch (Exception ex) {}
    }
    
    public void showChart() {
        this.generateData();
        this.chart.setChart(this.chartData);
        this.chart.repaint();
    }
    
    private class ChartLabel
    {
        String label;
        
        public ChartLabel(final String label) {
            this.label = new String(label);
        }
        
        public String toString() {
            if (PieChart.this.isHideLabel) {
                return "";
            }
            return this.label;
        }
    }
}
