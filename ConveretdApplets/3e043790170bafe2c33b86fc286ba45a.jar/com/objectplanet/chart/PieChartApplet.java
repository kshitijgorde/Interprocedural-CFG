// 
// Decompiled by Procyon v0.5.30
// 

package com.objectplanet.chart;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;

public class PieChartApplet extends ChartApplet
{
    private PieChart chart;
    
    public void init() {
        final String parameter = this.getParameter("sampleCount");
        if (parameter != null) {
            this.chart = new PieChart(Integer.parseInt(parameter));
            final NonFlickerPanel nonFlickerPanel = new NonFlickerPanel(new BorderLayout());
            this.setLayout(new BorderLayout());
            nonFlickerPanel.add("Center", this.chart);
            this.add("Center", nonFlickerPanel);
            final String parameter2 = this.getParameter("angle");
            if (parameter2 != null) {
                try {
                    final int int1 = Integer.parseInt(parameter2);
                    if (int1 < 10 || int1 > 90) {
                        throw new NumberFormatException();
                    }
                    this.chart.setAngle(int1);
                }
                catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Invalid angle: " + parameter2 + ", should be 10-90");
                }
            }
            final String parameter3 = this.getParameter("depth");
            if (parameter3 != null) {
                try {
                    final float floatValue = new Float(parameter3);
                    if (floatValue < 0.0 || floatValue > 1.0) {
                        throw new NumberFormatException();
                    }
                    this.chart.setDepth(floatValue);
                }
                catch (NumberFormatException ex2) {
                    throw new IllegalArgumentException("Invalid depth: " + parameter3 + ", should be 0.0-1.0");
                }
            }
            final String parameter4 = this.getParameter("sampleLabelsOn");
            if (parameter4 != null && parameter4.toLowerCase().equals("true")) {
                this.chart.setSampleLabelsOn(true);
            }
            else {
                this.chart.setSampleLabelsOn(false);
            }
            final String parameter5 = this.getParameter("percentLabelsOn");
            if (parameter5 != null && parameter5.toLowerCase().equals("true")) {
                this.chart.setPercentLabelsOn(true);
            }
            else {
                this.chart.setPercentLabelsOn(false);
            }
            final String parameter6 = this.getParameter("percentDecimalCount");
            if (parameter6 != null) {
                try {
                    this.chart.setPercentDecimalCount(Integer.parseInt(parameter6));
                }
                catch (NumberFormatException ex3) {
                    this.chart.setPercentDecimalCount(0);
                }
            }
            this.readSamples(this.chart);
            this.readDefaultParameters(this.chart);
            return;
        }
        throw new IllegalArgumentException("No sampleCount was defined");
    }
}
