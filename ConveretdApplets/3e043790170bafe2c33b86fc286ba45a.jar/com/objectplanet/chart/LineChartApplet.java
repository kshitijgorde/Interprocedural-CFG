// 
// Decompiled by Procyon v0.5.30
// 

package com.objectplanet.chart;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;

public class LineChartApplet extends ChartApplet
{
    private LineChart chart;
    
    public void init() {
        final String parameter = this.getParameter("sampleCount");
        String parameter2 = this.getParameter("seriesCount");
        if (parameter == null) {
            throw new IllegalArgumentException("No sampleCount was defined");
        }
        if (parameter2 == null) {
            parameter2 = "1";
        }
        if (parameter != null) {
            try {
                this.chart = new LineChart(Integer.parseInt(parameter2), Integer.parseInt(parameter), 100.0);
                final NonFlickerPanel nonFlickerPanel = new NonFlickerPanel(new BorderLayout());
                this.setLayout(new BorderLayout());
                nonFlickerPanel.add("Center", this.chart);
                this.add("Center", nonFlickerPanel);
            }
            catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Invalid seriesCount: " + parameter2 + " or sampleCount: " + parameter);
            }
            final String parameter3 = this.getParameter("sampleLabelsOn");
            this.chart.setSampleLabelsOn(parameter3 != null && parameter3.toLowerCase().equals("true"));
            final String parameter4 = this.getParameter("valueLinesOn");
            this.chart.setValueLinesOn(parameter4 != null && parameter4.toLowerCase().equals("true"));
            final String parameter5 = this.getParameter("rangeAdjusterOn");
            this.chart.setRangeAdjusterOn(parameter5 != null && parameter5.toLowerCase().equals("true"));
            this.readDefaultParameters(this.chart);
            this.readSamples(this.chart);
            final int sampleCount = this.chart.getSampleCount();
            final int seriesCount = this.chart.getSeriesCount();
            for (int i = 0; i < seriesCount; ++i) {
                final double[] sampleValues = this.readSampleValues("series" + i + "_values");
                if (sampleValues != null) {
                    for (int min = Math.min(sampleValues.length, sampleCount), j = 0; j < min; ++j) {
                        this.chart.setSampleValue(i, j, sampleValues[j]);
                    }
                }
            }
            final String[] sampleLabels = this.readSampleLabels("seriesLabels");
            if (sampleLabels != null) {
                for (int min2 = Math.min(seriesCount, sampleLabels.length), k = 0; k < min2; ++k) {
                    this.chart.setSeriesLabel(k, sampleLabels[k]);
                }
            }
            final String parameter6 = this.getParameter("range");
            if (parameter6 != null) {
                try {
                    this.chart.setRange(new Double(parameter6));
                }
                catch (NumberFormatException ex2) {
                    throw new IllegalArgumentException("Invalid chart range: " + parameter6);
                }
            }
            else {
                this.chart.setRelativeRange(1.0);
            }
            final String parameter7 = this.getParameter("lowerRange");
            if (parameter7 != null) {
                try {
                    this.chart.setLowerRange(new Double(parameter7));
                }
                catch (NumberFormatException ex3) {
                    throw new IllegalArgumentException("Invalid lower chart range: " + parameter7);
                }
            }
            else {
                this.chart.setLowerRelativeRange(1.0);
            }
            final String parameter8 = this.getParameter("rangeDecimalCount");
            if (parameter8 != null) {
                try {
                    this.chart.setRangeDecimalCount(Integer.parseInt(parameter8));
                }
                catch (NumberFormatException ex4) {}
            }
            final Color color = this.createColor(this.getParameter("valueLinesColor"));
            if (color != null) {
                this.chart.setValueLinesColor(color);
            }
        }
    }
}
