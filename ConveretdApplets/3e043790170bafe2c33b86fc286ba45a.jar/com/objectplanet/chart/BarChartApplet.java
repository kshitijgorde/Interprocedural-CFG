// 
// Decompiled by Procyon v0.5.30
// 

package com.objectplanet.chart;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;

public class BarChartApplet extends ChartApplet
{
    private BarChart chart;
    
    public void init() {
        final String parameter = this.getParameter("sampleCount");
        if (parameter != null) {
            this.chart = new BarChart(Integer.parseInt(parameter));
            final NonFlickerPanel nonFlickerPanel = new NonFlickerPanel(new BorderLayout());
            nonFlickerPanel.add("Center", this.chart);
            this.setLayout(new BorderLayout());
            this.add("Center", nonFlickerPanel);
            final String parameter2 = this.getParameter("multiColorOn");
            if (parameter2 != null && parameter2.toLowerCase().equals("true")) {
                this.chart.setMultiColorOn(true);
            }
            else {
                this.chart.setMultiColorOn(false);
            }
            final String parameter3 = this.getParameter("barLabelsOn");
            if (parameter3 != null && parameter3.toLowerCase().equals("true")) {
                this.chart.setBarLabelsOn(true);
            }
            else {
                this.chart.setBarLabelsOn(false);
            }
            final String parameter4 = this.getParameter("valueLinesOn");
            if (parameter4 != null && parameter4.toLowerCase().equals("true")) {
                this.chart.setValueLinesOn(true);
            }
            else {
                this.chart.setValueLinesOn(false);
            }
            final String parameter5 = this.getParameter("barAlignment");
            if (parameter5 != null) {
                final String lowerCase = parameter5.toLowerCase();
                if (lowerCase.equals("horizontal")) {
                    this.chart.setBarAlignment(0);
                }
                else {
                    if (!lowerCase.equals("vertical")) {
                        throw new IllegalArgumentException("Invalid bar alignment, must be horizontal or vertical");
                    }
                    this.chart.setBarAlignment(1);
                }
            }
            this.readDefaultParameters(this.chart);
            this.readSamples(this.chart);
            final String parameter6 = this.getParameter("range");
            if (parameter6 != null) {
                try {
                    this.chart.setRange(new Double(parameter6));
                }
                catch (NumberFormatException ex) {
                    throw new IllegalAccessError("Invalid upper range: " + parameter6);
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
                catch (NumberFormatException ex2) {
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
                catch (NumberFormatException ex3) {}
            }
            return;
        }
        throw new IllegalArgumentException("No sampleCount was defined");
    }
}
