// 
// Decompiled by Procyon v0.5.30
// 

package com.objectplanet.chart;

import java.util.StringTokenizer;
import java.awt.Color;
import java.applet.Applet;

public class ChartApplet extends Applet
{
    private void setBackground(final Chart chart, final String s) {
        final Color color = this.createColor(s);
        if (color != null) {
            chart.setBackground(color);
        }
    }
    
    protected void readDefaultParameters(final Chart chart) {
        chart.setTitle(this.getParameter("title"));
        final String parameter = this.getParameter("titleOn");
        chart.setTitleOn(parameter != null && parameter.toLowerCase().equals("true"));
        final String parameter2 = this.getParameter("legendOn");
        chart.setLegendOn(parameter2 != null && parameter2.toLowerCase().equals("true"));
        final String parameter3 = this.getParameter("legendPosition");
        if (parameter3 != null) {
            final String lowerCase = parameter3.toLowerCase();
            if (lowerCase.equals("top")) {
                chart.setLegendPosition(2);
            }
            else if (lowerCase.equals("bottom")) {
                chart.setLegendPosition(3);
            }
            else if (lowerCase.equals("right")) {
                chart.setLegendPosition(1);
            }
            else {
                if (!lowerCase.equals("left")) {
                    throw new IllegalArgumentException("Invalid legend position, must be top, bottom, left, or right");
                }
                chart.setLegendPosition(0);
            }
        }
        final String parameter4 = this.getParameter("valueLabelsOn");
        chart.setValueLabelsOn(parameter4 != null && parameter4.toLowerCase().equals("true"));
        final String parameter5 = this.getParameter("3DModeOn");
        chart.set3DModeOn(parameter5 != null && parameter5.toLowerCase().equals("true"));
        final String parameter6 = this.getParameter("frameOn");
        chart.setFrameOn(parameter6 != null && parameter6.toLowerCase().equals("true"));
        this.setForeground(chart, this.getParameter("foreground"));
        this.setBackground(chart, this.getParameter("background"));
        this.setChartForeground(chart, this.getParameter("chartForeground"));
        this.setChartBackground(chart, this.getParameter("chartBackground"));
        final String parameter7 = this.getParameter("sampleDecimalCount");
        if (parameter7 != null) {
            try {
                chart.setSampleDecimalCount(Integer.parseInt(parameter7));
            }
            catch (NumberFormatException ex) {}
        }
        final String parameter8 = this.getParameter("sampleColors");
        if (parameter8 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter8, ",");
            final Color[] sampleColors = new Color[stringTokenizer.countTokens()];
            for (int i = 0; i < sampleColors.length; ++i) {
                final Color color = this.createColor(stringTokenizer.nextToken().trim());
                if (color != null) {
                    sampleColors[i] = color;
                }
                else {
                    sampleColors[i] = chart.getSampleColor(i);
                }
            }
            chart.setSampleColors(sampleColors);
        }
    }
    
    protected void readSamples(final Chart chart) {
        final double[] sampleValues = this.readSampleValues("sampleValues");
        if (sampleValues != null) {
            for (int min = Math.min(chart.getSampleCount(), sampleValues.length), i = 0; i < min; ++i) {
                chart.setSampleValue(i, sampleValues[i]);
            }
        }
        final String[] sampleLabels = this.readSampleLabels("sampleLabels");
        if (sampleLabels != null) {
            for (int min2 = Math.min(chart.getSampleCount(), sampleLabels.length), j = 0; j < min2; ++j) {
                chart.setSampleLabel(j, sampleLabels[j]);
            }
        }
    }
    
    protected double[] readSampleValues(final String s) {
        final String parameter = this.getParameter(s);
        double[] array = null;
        if (parameter != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ", ");
            array = new double[stringTokenizer.countTokens()];
            for (int i = 0; i < array.length; ++i) {
                String nextToken = "";
                try {
                    nextToken = stringTokenizer.nextToken();
                    array[i] = new Double(nextToken);
                }
                catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Invalid value: " + nextToken);
                }
            }
        }
        return array;
    }
    
    private void setChartForeground(final Chart chart, final String s) {
        final Color color = this.createColor(s);
        if (color != null) {
            chart.setChartForeground(color);
        }
    }
    
    protected Color createColor(String lowerCase) {
        Color color = null;
        if (lowerCase != null && lowerCase.indexOf(",") >= 0) {
            final StringTokenizer stringTokenizer = new StringTokenizer(lowerCase, ", ");
            try {
                color = new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
            }
            catch (Exception ex) {}
        }
        else if (lowerCase != null && lowerCase.startsWith("#")) {
            try {
                color = new Color(Integer.parseInt(lowerCase.substring(1, 3), 16), Integer.parseInt(lowerCase.substring(3, 5), 16), Integer.parseInt(lowerCase.substring(5, 7), 16));
            }
            catch (Exception ex2) {}
        }
        else if (lowerCase != null) {
            lowerCase = lowerCase.toLowerCase();
            if (lowerCase.equals("black")) {
                color = Color.black;
            }
            else if (lowerCase.equals("blue")) {
                color = Color.blue;
            }
            else if (lowerCase.equals("cyan")) {
                color = Color.cyan;
            }
            else if (lowerCase.equals("darkgray")) {
                color = Color.darkGray;
            }
            else if (lowerCase.equals("gray")) {
                color = Color.gray;
            }
            else if (lowerCase.equals("green")) {
                color = Color.green;
            }
            else if (lowerCase.equals("lightgray")) {
                color = Color.lightGray;
            }
            else if (lowerCase.equals("magenta")) {
                color = Color.magenta;
            }
            else if (lowerCase.equals("orange")) {
                color = Color.orange;
            }
            else if (lowerCase.equals("pink")) {
                color = Color.pink;
            }
            else if (lowerCase.equals("red")) {
                color = Color.red;
            }
            else if (lowerCase.equals("white")) {
                color = Color.white;
            }
            else if (lowerCase.equals("yellow")) {
                color = Color.yellow;
            }
        }
        return color;
    }
    
    protected String[] readSampleLabels(final String s) {
        final String parameter = this.getParameter(s);
        String[] array = null;
        if (parameter != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
            array = new String[stringTokenizer.countTokens()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = stringTokenizer.nextToken().trim();
            }
        }
        return array;
    }
    
    private void setForeground(final Chart chart, final String s) {
        final Color color = this.createColor(s);
        if (color != null) {
            chart.setForeground(color);
        }
    }
    
    private void setChartBackground(final Chart chart, final String s) {
        final Color color = this.createColor(s);
        if (color != null) {
            chart.setChartBackground(color);
        }
    }
}
