// 
// Decompiled by Procyon v0.5.30
// 

package com.aviva.framework.applet;

import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import com.jrefinery.chart.plot.PiePlot;
import com.jrefinery.chart.Legend;
import com.jrefinery.data.PieDataset;
import com.jrefinery.chart.ChartFactory;
import com.jrefinery.data.DefaultPieDataset;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Vector;
import com.jrefinery.chart.JFreeChart;
import com.jrefinery.chart.ChartPanel;
import javax.swing.JApplet;

public class ExpensePieChart extends JApplet
{
    private String[] keys;
    private Double[] rawData;
    private String[] value;
    public ChartPanel chart;
    private JFreeChart chartData;
    public int numParam;
    public int index;
    public double current;
    public double inv;
    public double gaps;
    Vector incomeList;
    
    public ExpensePieChart() {
        this.keys = null;
        this.rawData = null;
        this.chart = null;
        this.chartData = null;
        this.numParam = 0;
        this.index = 0;
        this.current = 0.0;
        this.inv = 0.0;
        this.gaps = 0.0;
        this.incomeList = new Vector();
    }
    
    private void chartPanel() {
        this.generateData();
        this.chart = new ChartPanel(this.chartData, 200, 150, 100, 75, 300, 225, false, false, true, true, true, true);
    }
    
    public void destroy() {
        this.removeAll();
    }
    
    private Vector papulateData(final String text) {
        final Vector result = new Vector();
        final StringTokenizer tokens = new StringTokenizer(text, ";");
        while (tokens.hasMoreTokens()) {
            final String nextToken = tokens.nextToken();
            final StringTokenizer toks = new StringTokenizer(nextToken, "=");
            while (toks.hasMoreTokens()) {
                result.add(new DrawChartText(toks.nextToken(), new Double(toks.nextToken())));
            }
        }
        return result;
    }
    
    public void updateData(final String a) {
        String name = "";
        String value = "0";
        final StringTokenizer tokens = new StringTokenizer(a, "=");
        if (tokens.hasMoreTokens()) {
            name = tokens.nextToken().trim();
            value = tokens.nextToken().trim();
        }
        for (final DrawChartText chartText : this.incomeList) {
            if (chartText.getName().startsWith(name)) {
                chartText.setValue(new Double(value));
            }
        }
        this.showChart1();
    }
    
    public void getData() {
        final String incomeText = this.getParameter("expensechart");
        this.incomeList = this.papulateData(incomeText);
    }
    
    public void generateData() {
        try {
            DefaultPieDataset data = null;
            data = new DefaultPieDataset();
            this.keys = new String[this.incomeList.size()];
            this.rawData = new Double[this.incomeList.size()];
            final Iterator incomeListIterator = this.incomeList.iterator();
            int i = 0;
            while (incomeListIterator.hasNext()) {
                final DrawChartText chartText = incomeListIterator.next();
                this.keys[i] = chartText.getName();
                this.rawData[i] = new Double(chartText.getValue());
                data.setValue((Comparable)this.keys[i], (Number)this.rawData[i]);
                ++i;
            }
            (this.chartData = ChartFactory.createPieChart(this.getAppletInfo(), (PieDataset)data, true, true, true)).setLegend((Legend)null);
            final PiePlot plot = (PiePlot)this.chartData.getPlot();
        }
        catch (Exception ex) {}
    }
    
    public String getAppletInfo() {
        return "";
    }
    
    public void init() {
        this.chartPanel();
        final Container contentPane = this.getContentPane();
        contentPane.setLayout(new GridLayout(1, 1));
        contentPane.add((Component)this.chart);
        this.showChart();
    }
    
    public static void main(final String[] args) {
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
    
    public void setNum(final String numPrm) {
        try {
            this.numParam = Integer.parseInt(numPrm);
            this.keys = new String[this.numParam];
            this.rawData = new Double[this.numParam];
            this.value = new String[this.numParam];
        }
        catch (Exception ex) {}
    }
    
    public void showChart() {
        this.getData();
        this.generateData();
        this.chart.setChart(this.chartData);
        this.chart.repaint();
    }
    
    public void showChart1() {
        this.generateData();
        this.chart.setChart(this.chartData);
        this.chart.repaint();
    }
    
    public void start() {
    }
    
    public void stop() {
    }
}
