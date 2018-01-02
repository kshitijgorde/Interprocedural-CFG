// 
// Decompiled by Procyon v0.5.30
// 

package com.objectplanet.chart;

import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Polygon;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.Hashtable;

public class BarChart extends Chart
{
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int TARGET_LINE_NO_LABEL = 0;
    public static final int TARGET_LINE_ID_LABEL = 1;
    public static final int TARGET_LINE_VALUE_LABEL = 2;
    public static final int TARGET_LINE_ID_AND_VALUE_LABEL = 3;
    private double upperRange;
    private double lowerRange;
    private int rangeDecimalCount;
    private Hashtable targetValueLines;
    boolean multiColorOn;
    boolean barLabelsOn;
    boolean valueLinesOn;
    boolean rangeLabelsOn;
    int barAlignment;
    Color valueLinesColor;
    private static final int VALUE_LINE_SPACE = 15;
    private static final float ANGLE = 1.25f;
    private static final float DEPTH = 0.75f;
    private static final float BAR_FRONT_WIDTH = 0.4f;
    private Rectangle[] barBounds;
    
    public synchronized void setRange(final double upperRange) {
        this.upperRange = upperRange;
        this.repaint(1L);
    }
    
    public synchronized long getRange() {
        return (long)this.upperRange;
    }
    
    public void setBarLabelsOn(final boolean barLabelsOn) {
        this.barLabelsOn = barLabelsOn;
        this.repaint(1L);
    }
    
    public boolean isValueLinesOn() {
        return this.valueLinesOn;
    }
    
    public void setRangeLabelsOn(final boolean rangeLabelsOn) {
        this.rangeLabelsOn = rangeLabelsOn;
        this.repaint(1L);
    }
    
    public void setTargetValueLine(final String s, final double value, final Color color, final int n) {
        if (color != null) {
            switch (n) {
                case 0:
                case 1:
                case 2:
                case 3: {
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Invalid style");
                }
            }
        }
        final TargetValueLine targetValueLine = this.targetValueLines.get(s);
        if (targetValueLine != null) {
            if (color != null) {
                targetValueLine.value = value;
                targetValueLine.color = color;
            }
            else {
                this.targetValueLines.remove(s);
            }
        }
        else {
            this.targetValueLines.put(s, new TargetValueLine(s, value, color, n));
        }
        this.repaint(1L);
    }
    
    public double getTargetValueLine(final String s) {
        final TargetValueLine targetValueLine = this.targetValueLines.get(s);
        if (targetValueLine != null) {
            return targetValueLine.value;
        }
        throw new IllegalArgumentException("No target value line with the id: " + s);
    }
    
    private void paintGrid(final Graphics graphics, final Rectangle rectangle) {
        int n;
        if (this.barAlignment == 1) {
            n = (int)(rectangle.width / super.samples.length * 0.4f);
        }
        else {
            n = (int)(rectangle.height / super.samples.length * 0.4f);
        }
        Polygon polygon = null;
        Polygon polygon2 = null;
        if (super.display3dOn) {
            polygon = new Polygon();
            polygon.addPoint(rectangle.x, rectangle.y);
            if (this.barAlignment == 1) {
                polygon.addPoint(rectangle.x - (int)(n * 1.25f), rectangle.y + (int)(n / 1.25f));
                polygon.addPoint(rectangle.x - (int)(n * 1.25f), rectangle.y + rectangle.height + (int)(n / 1.25f));
            }
            else {
                final int n2 = rectangle.x - (int)(n * 1.25f * 0.75f);
                final int n3 = rectangle.y + (int)(n / 1.25f * 0.75f);
                polygon.addPoint(n2, n3);
                polygon.addPoint(n2, n3 + rectangle.height);
            }
            polygon.addPoint(rectangle.x, rectangle.y + rectangle.height);
            polygon2 = new Polygon();
            polygon2.addPoint(rectangle.x, rectangle.y + rectangle.height);
            if (this.barAlignment == 1) {
                polygon2.addPoint(rectangle.x - (int)(n * 1.25f), rectangle.y + rectangle.height + (int)(n / 1.25f));
                polygon2.addPoint(rectangle.x + rectangle.width - (int)(n * 1.25f), rectangle.y + rectangle.height + (int)(n / 1.25f));
            }
            else {
                final int n4 = rectangle.x - (int)(n * 1.25f * 0.75f);
                final int n5 = rectangle.y + rectangle.height + (int)(n / 1.25f * 0.75f);
                polygon2.addPoint(n4, n5);
                polygon2.addPoint(n4 + rectangle.width, n5);
            }
            polygon2.addPoint(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
        }
        graphics.setColor(super.chartBackground);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if (super.display3dOn) {
            graphics.setColor((this.barAlignment == 1) ? super.chartBackground : super.chartBackground.darker());
            graphics.fillPolygon(polygon);
            graphics.setColor((this.barAlignment == 0) ? super.chartBackground : super.chartBackground.darker());
            graphics.fillPolygon(polygon2);
        }
        int n6 = Math.max(Math.min((int)(rectangle.height * (this.upperRange / (this.upperRange - this.lowerRange))), rectangle.height), 0);
        if (this.barAlignment == 0) {
            n6 = Math.max(Math.min((int)(rectangle.width * (this.upperRange / (this.upperRange - this.lowerRange))), rectangle.width), 0);
        }
        if (this.upperRange > 0.0) {
            final int valueLineCount = this.getValueLineCount(n6, true);
            final Rectangle rectangle2 = new Rectangle(rectangle);
            if (this.barAlignment == 1) {
                rectangle2.height = Math.min(n6, rectangle2.height);
            }
            else {
                rectangle2.x = Math.max(0, rectangle.x + rectangle.width - n6);
                rectangle2.width = rectangle.x + rectangle.width - rectangle2.x;
            }
            this.paintValueLines(graphics, rectangle2, valueLineCount, Math.max(0.0, this.lowerRange), this.upperRange);
        }
        if (this.lowerRange < 0.0) {
            int height = rectangle.height - n6;
            if (this.barAlignment == 0) {
                height = rectangle.width - n6;
            }
            final int valueLineCount2 = this.getValueLineCount(height, false);
            final Rectangle rectangle3 = new Rectangle(rectangle);
            if (this.barAlignment == 1) {
                rectangle3.height = height;
                rectangle3.y = rectangle.y + rectangle.height - height;
            }
            else {
                final Rectangle rectangle4 = rectangle3;
                rectangle4.width -= n6;
            }
            this.paintValueLines(graphics, rectangle3, valueLineCount2, this.lowerRange, Math.min(0.0, this.upperRange));
        }
        this.paintTargetValueLines(graphics, rectangle);
        if (super.display3dOn) {
            graphics.setColor(super.chartForeground);
            graphics.drawPolygon(polygon);
            graphics.setColor(super.chartForeground);
            graphics.drawPolygon(polygon2);
        }
        graphics.setColor(super.chartForeground);
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public synchronized void setLowerRange(final double lowerRange) {
        this.lowerRange = lowerRange;
        this.repaint(1L);
    }
    
    public synchronized long getLowerRange() {
        return (long)this.lowerRange;
    }
    
    public synchronized void appendSampleValue(final double value, final boolean b) {
        int n = -1;
        for (int sampleCount = this.getSampleCount(), i = 0; i < sampleCount; ++i) {
            if (super.samples[i].value == null) {
                n = i;
                break;
            }
        }
        if (n >= 0) {
            super.samples[n].setValue(value);
            this.repaint(1L);
        }
        else if (b) {
            final int sampleCount2 = this.getSampleCount();
            this.setSampleCount(sampleCount2 + 1);
            this.setSampleValue(sampleCount2, value);
        }
        else {
            final double[] floatSampleValues = this.getFloatSampleValues();
            for (int j = 0; j < floatSampleValues.length - 1; ++j) {
                floatSampleValues[j] = floatSampleValues[j + 1];
            }
            floatSampleValues[floatSampleValues.length - 1] = value;
            this.setSampleValues(floatSampleValues);
        }
    }
    
    public void setRangeDecimalCount(final int rangeDecimalCount) {
        this.rangeDecimalCount = rangeDecimalCount;
        this.repaint(1L);
    }
    
    public int getRangeDecimalCount() {
        return this.rangeDecimalCount;
    }
    
    public void setBarAlignment(final int barAlignment) {
        if (barAlignment == 0 || barAlignment == 1) {
            this.barAlignment = barAlignment;
            this.repaint(1L);
            return;
        }
        throw new IllegalArgumentException("Alignment must be HORIZONTAL or VERTICAL");
    }
    
    public int getBarAlignment() {
        return this.barAlignment;
    }
    
    private void paintBarLabels(final Graphics graphics, final Rectangle rectangle) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        if (this.barAlignment == 1) {
            final int height = fontMetrics.getHeight();
            for (int i = 0; i < super.samples.length; ++i) {
                final Rectangle rectangle2 = this.barBounds[i];
                if (rectangle2 != null && super.samples[i] != null && super.samples[i].label != null) {
                    final int stringWidth = fontMetrics.stringWidth(super.samples[i].label);
                    int n = rectangle.y + rectangle.height + height;
                    int n2 = rectangle2.x + rectangle2.width / 2 - stringWidth / 2;
                    if (super.display3dOn) {
                        final int n3 = (int)(rectangle2.width / 2.25f);
                        n2 = rectangle2.x + n3 / 2 - fontMetrics.stringWidth(super.samples[i].label) / 2;
                        n += (int)(n3 / 1.25f);
                    }
                    graphics.setColor(this.getForeground());
                    graphics.drawString(super.samples[i].label, n2, n);
                }
            }
        }
        else {
            final int height2 = fontMetrics.getHeight();
            for (int j = 0; j < super.samples.length; ++j) {
                final Rectangle rectangle3 = this.barBounds[j];
                if (rectangle3 != null && super.samples[j] != null && super.samples[j].label != null) {
                    final int stringWidth2 = fontMetrics.stringWidth(super.samples[j].label);
                    final int y = rectangle3.y;
                    int n4 = rectangle.x - stringWidth2 - 5;
                    int n5;
                    if (super.display3dOn) {
                        n5 = rectangle3.y + rectangle3.height - (int)(rectangle3.height / 2.25f) / 2;
                        n4 -= (int)((int)(rectangle.height / super.samples.length * 0.4f) * 1.25f * 0.75f);
                    }
                    else {
                        n5 = rectangle3.y + rectangle3.height / 2 + (height2 / 2 - fontMetrics.getDescent());
                    }
                    graphics.setColor(this.getForeground());
                    graphics.drawString(super.samples[j].label, n4, n5);
                }
            }
        }
    }
    
    public synchronized double getFloatLowerRange() {
        return this.lowerRange;
    }
    
    public synchronized void setLowerRelativeRange(final double n) {
        if (this.getFloatMinValue() < 0.0) {
            this.setLowerRange(this.getFloatMinValue() * Math.abs(n));
        }
        else {
            this.setLowerRange(0.0);
        }
    }
    
    public void setMultiColorOn(final boolean multiColorOn) {
        this.multiColorOn = multiColorOn;
        this.repaint(1L);
    }
    
    public void setValueLinesOn(final boolean valueLinesOn) {
        this.valueLinesOn = valueLinesOn;
        this.repaint(1L);
    }
    
    private void paintBars(final Graphics graphics, final Rectangle rectangle) {
        int n;
        if (this.barAlignment == 1) {
            n = rectangle.width / super.samples.length;
        }
        else {
            n = rectangle.height / super.samples.length;
        }
        int n2 = 0;
        if (this.upperRange - this.lowerRange != 0.0) {
            n2 = (int)(rectangle.height * (this.upperRange / (this.upperRange - this.lowerRange)));
        }
        int x = rectangle.y + n2;
        if (this.barAlignment == 0) {
            if (this.upperRange - this.lowerRange != 0.0) {
                n2 = (int)(rectangle.width * (this.upperRange / (this.upperRange - this.lowerRange)));
            }
            x = rectangle.x + rectangle.width - n2;
        }
        final Rectangle rectangle2 = new Rectangle();
        if (this.barAlignment == 1) {
            rectangle2.width = (int)(n * 0.4f);
            rectangle2.x = rectangle.x + (int)(rectangle2.width * 0.8);
        }
        else {
            rectangle2.height = (int)(n * 0.4f);
            rectangle2.x = rectangle.x;
            rectangle2.y = rectangle.y + (int)(rectangle2.height * 0.8);
        }
        if (super.display3dOn) {
            if (this.barAlignment == 1) {
                final Rectangle rectangle3 = rectangle2;
                rectangle3.x -= (int)(rectangle2.width * 1.25f);
            }
            else {
                final Rectangle rectangle4 = rectangle2;
                rectangle4.y += (int)(rectangle2.height / 1.25f * 0.75f);
            }
        }
        for (int i = 0; i < super.samples.length; ++i) {
            if (super.samples[i] == null || super.samples[i].value == null) {
                if (this.barAlignment == 1) {
                    final Rectangle rectangle5 = rectangle2;
                    rectangle5.x += n;
                }
                else {
                    final Rectangle rectangle6 = rectangle2;
                    rectangle6.y += n;
                }
            }
            else {
                if (this.barAlignment == 1) {
                    if (super.samples[i].getFloatValue() >= 0.0) {
                        double n3 = 0.0;
                        if (this.upperRange != 0.0) {
                            n3 = super.samples[i].getFloatValue() / this.upperRange;
                        }
                        rectangle2.height = (int)(n2 * n3);
                        rectangle2.y = x - rectangle2.height;
                        rectangle2.height = Math.min(rectangle2.height, rectangle.height - (rectangle2.y - rectangle.y));
                        if (x < rectangle.y) {
                            rectangle2.y = rectangle.y;
                            rectangle2.height = 0;
                        }
                    }
                    else {
                        double n4 = 0.0;
                        if (this.lowerRange != 0.0) {
                            n4 = super.samples[i].getFloatValue() / this.lowerRange;
                        }
                        rectangle2.height = (int)((rectangle.height - n2) * n4);
                        rectangle2.y = Math.max(x, rectangle.y);
                        final Rectangle rectangle7 = rectangle2;
                        rectangle7.height -= Math.max(0, rectangle2.y - x);
                        if (x > rectangle.y + rectangle.height) {
                            rectangle2.y = rectangle.y + rectangle.height;
                            rectangle2.height = 0;
                        }
                    }
                }
                else if (super.samples[i].getFloatValue() >= 0.0) {
                    double n5 = 0.0;
                    if (this.upperRange != 0.0) {
                        n5 = super.samples[i].getFloatValue() / this.upperRange;
                    }
                    rectangle2.x = x;
                    rectangle2.width = (int)(n2 * n5);
                    final Rectangle rectangle8 = rectangle2;
                    rectangle8.width -= Math.max(0, rectangle.x - rectangle2.x);
                    rectangle2.x = Math.max(rectangle2.x, rectangle.x);
                    if (x > rectangle.x + rectangle.width) {
                        rectangle2.x = rectangle.x + rectangle.width;
                        rectangle2.width = 0;
                    }
                }
                else {
                    double n6 = 0.0;
                    if (this.lowerRange != 0.0) {
                        n6 = super.samples[i].getFloatValue() / this.lowerRange;
                    }
                    rectangle2.width = (int)((rectangle.width - n2) * n6);
                    rectangle2.x = x - rectangle2.width;
                    final Rectangle rectangle9 = rectangle2;
                    rectangle9.width -= Math.max(0, x - (rectangle.x + rectangle.width));
                    if (x < rectangle.x) {
                        rectangle2.x = rectangle.x;
                        rectangle2.width = 0;
                    }
                }
                if (super.display3dOn) {
                    if (this.barAlignment == 1) {
                        final Rectangle rectangle10 = rectangle2;
                        rectangle10.y += (int)(rectangle2.width / 1.25f);
                    }
                    else {
                        final Rectangle rectangle11 = rectangle2;
                        rectangle11.x -= (int)(rectangle2.height * 1.25f * 0.75f);
                    }
                }
                Color sampleColor = super.sampleColors[0];
                Color color = Color.black;
                if (this.multiColorOn) {
                    sampleColor = this.getSampleColor(i);
                }
                if (super.samples[i].isSelected()) {
                    color = Chart.SELECTED_COLOR;
                }
                graphics.setColor(sampleColor);
                graphics.fillRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
                if (super.display3dOn) {
                    this.paint3DBar(graphics, rectangle2, color);
                }
                this.setBarBounds(i, rectangle2);
                graphics.setColor(color);
                graphics.drawRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
                if (this.barAlignment == 1) {
                    final Rectangle rectangle12 = rectangle2;
                    rectangle12.x += n;
                }
                else {
                    final Rectangle rectangle13 = rectangle2;
                    rectangle13.y += n;
                }
            }
        }
    }
    
    public boolean isMultiColorOn() {
        return this.multiColorOn;
    }
    
    protected ChartSample checkSelection(final Point point) {
        ChartSample checkSelection = super.checkSelection(point);
        if (checkSelection == null) {
            for (int i = 0; i < this.barBounds.length; ++i) {
                if (this.barBounds[i] != null && this.barBounds[i].contains(point)) {
                    checkSelection = super.samples[i];
                    break;
                }
            }
        }
        return checkSelection;
    }
    
    public BarChart() {
        this(5);
    }
    
    public BarChart(final int n) {
        this(n, 100.0);
    }
    
    public BarChart(final int n, final double n2) {
        this(n, n2, 0.0);
    }
    
    public BarChart(final int n, final double range, final double lowerRange) {
        super(n);
        this.targetValueLines = new Hashtable();
        this.rangeLabelsOn = true;
        this.setRangeDecimalCount(0);
        this.valueLinesColor = Color.lightGray;
        this.setRange(range);
        this.setLowerRange(lowerRange);
        this.setBarAlignment(1);
    }
    
    public void setSampleCount(int max) {
        max = Math.max(1, max);
        this.barBounds = new Rectangle[max];
        super.setSampleCount(max);
    }
    
    private void paintBarValues(final Graphics graphics, final Rectangle rectangle) {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        for (int i = 0; i < super.samples.length; ++i) {
            if (super.samples[i] != null) {
                try {
                    final String formatNumber = ChartSample.formatNumber(super.samples[i].getFloatValue(), super.sampleDecimalCount);
                    final int stringWidth = fontMetrics.stringWidth(formatNumber);
                    final int height = fontMetrics.getHeight();
                    final Rectangle rectangle2 = this.barBounds[i];
                    int n;
                    int n2;
                    if (this.barAlignment == 1) {
                        n = rectangle2.x + rectangle2.width / 2 - stringWidth / 2;
                        n2 = Math.max(rectangle.y, rectangle2.y);
                        if (super.samples[i].getFloatValue() < this.lowerRange) {
                            n2 = Math.min(n2, rectangle.y + rectangle.height);
                        }
                    }
                    else {
                        n = rectangle2.x + rectangle2.width + 2;
                        n2 = rectangle2.y + rectangle2.height / 2 + height / 2 + 2;
                        if (n > rectangle.x + rectangle.width) {
                            n = rectangle.x + rectangle.width + 3;
                        }
                    }
                    graphics.setColor(super.chartBackground);
                    graphics.fillRect(n - 1, n2 - height + 1, stringWidth + 3, height - 3);
                    graphics.setColor(this.getForeground());
                    graphics.drawString(formatNumber + "", n + 1, n2 - 3);
                }
                catch (NullPointerException ex) {}
            }
        }
    }
    
    public boolean isBarLabelsOn() {
        return this.barLabelsOn;
    }
    
    public boolean isRangeLabelsOn() {
        return this.rangeLabelsOn;
    }
    
    public synchronized double getFloatRange() {
        return this.upperRange;
    }
    
    public synchronized void setRelativeRange(final double n) {
        if (this.getFloatMaxValue() >= 0.0) {
            this.setRange(this.getFloatMaxValue() * Math.abs(n));
        }
        else {
            this.setRange(0.0);
        }
    }
    
    public void render(final Graphics graphics) {
        final Dimension size = this.getSize();
        final Rectangle graphBounds = this.getGraphBounds();
        graphics.setFont(this.getFont());
        this.paintFrame(graphics, size);
        this.paintGrid(graphics, graphBounds);
        this.paintBars(graphics, graphBounds);
        if (super.display3dOn) {
            this.paint3DZeroDivider(graphics, graphBounds);
        }
        if (super.chartTitleOn) {
            this.paintTitle(graphics, size);
        }
        if (this.barLabelsOn) {
            this.paintBarLabels(graphics, graphBounds);
        }
        if (super.valueLabelsOn) {
            this.paintBarValues(graphics, graphBounds);
        }
        if (super.legendOn) {
            if (super.display3dOn) {
                final Rectangle rectangle = graphBounds;
                rectangle.height += (int)(graphBounds.width / super.samples.length * 0.4f / 1.25f);
            }
            this.paintLegend(graphics, graphBounds, super.samples);
        }
    }
    
    private void paint3DBar(final Graphics graphics, final Rectangle rectangle, final Color color) {
        final Polygon polygon = new Polygon();
        polygon.addPoint(rectangle.x, rectangle.y);
        if (this.barAlignment == 1) {
            polygon.addPoint((int)(rectangle.x + rectangle.width * 1.25f), (int)(rectangle.y - rectangle.width / 1.25f));
            polygon.addPoint((int)(rectangle.x + rectangle.width * 1.25f + rectangle.width), (int)(rectangle.y - rectangle.width / 1.25f));
        }
        else {
            final int n = rectangle.x + (int)(rectangle.height * 1.25f * 0.75f);
            polygon.addPoint(n, rectangle.y - (int)(rectangle.height / 1.25f * 0.75f));
            polygon.addPoint(n + rectangle.width, rectangle.y - (int)(rectangle.height / 1.25f * 0.75f));
        }
        polygon.addPoint(rectangle.x + rectangle.width, rectangle.y);
        final Polygon polygon2 = new Polygon();
        polygon2.addPoint(rectangle.x + rectangle.width, rectangle.y);
        if (this.barAlignment == 1) {
            polygon2.addPoint((int)(rectangle.x + rectangle.width * 1.25f + rectangle.width), (int)(rectangle.y - rectangle.width / 1.25f));
            polygon2.addPoint((int)(rectangle.x + rectangle.width * 1.25f + rectangle.width), (int)(rectangle.y - rectangle.width / 1.25f + rectangle.height));
        }
        else {
            final int n2 = rectangle.x + rectangle.width + (int)(rectangle.height * 1.25f * 0.75f);
            polygon2.addPoint(n2, rectangle.y - (int)(rectangle.height / 1.25f * 0.75f));
            polygon2.addPoint(n2, rectangle.y - (int)(rectangle.height / 1.25f * 0.75f) + rectangle.height);
        }
        polygon2.addPoint(rectangle.x + rectangle.width, rectangle.y + rectangle.height);
        graphics.setColor(graphics.getColor().darker());
        graphics.fillPolygon(polygon);
        graphics.setColor(graphics.getColor().darker());
        graphics.fillPolygon(polygon2);
        graphics.setColor(color);
        graphics.drawPolygon(polygon);
        graphics.drawPolygon(polygon2);
    }
    
    private void paintValueLines(final Graphics graphics, final Rectangle rectangle, final int n, final double n2, final double n3) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int y = rectangle.y;
        final int x = rectangle.x;
        final int n4 = rectangle.x + rectangle.width;
        double n5 = n3;
        if (this.barAlignment == 0) {
            final int n6 = rectangle.y + rectangle.height;
            n5 = n2;
        }
        int n7 = 0;
        if (super.display3dOn) {
            if (this.barAlignment == 1) {
                n7 = (int)(rectangle.width / super.samples.length * 0.4f);
            }
            else {
                n7 = (int)(rectangle.height / super.samples.length * 0.4f);
            }
        }
        for (int i = 0; i <= n; ++i) {
            int y2;
            int n8;
            int x2;
            int n9;
            if (this.barAlignment == 1) {
                n8 = (y2 = rectangle.y + i / n * rectangle.height);
                x2 = rectangle.x;
                n9 = rectangle.x + rectangle.width;
            }
            else {
                n9 = (x2 = rectangle.x + i / n * rectangle.width);
                y2 = rectangle.y;
                n8 = rectangle.y + rectangle.height;
            }
            boolean valueLinesOn = this.valueLinesOn;
            if (!this.valueLinesOn) {
                valueLinesOn = (i == 0 || i == n);
            }
            if (valueLinesOn) {
                if (i == 0 || i == n) {
                    graphics.setColor(super.chartForeground);
                }
                else {
                    graphics.setColor(this.valueLinesColor);
                }
                graphics.drawLine(x2, y2, n9, n8);
                if (super.display3dOn) {
                    if (this.barAlignment == 1) {
                        graphics.drawLine(x2, y2, x2 - (int)(n7 * 1.25f), y2 + (int)(n7 / 1.25f));
                    }
                    else {
                        graphics.drawLine(x2, n8, x2 - (int)(n7 * 1.25f * 0.75f), n8 + (int)(n7 / 1.25f * 0.75f));
                    }
                }
            }
            if (this.rangeLabelsOn) {
                if (super.display3dOn) {
                    if (this.barAlignment == 1) {
                        x2 -= (int)(n7 * 1.25f);
                        y2 += (int)(n7 / 1.25f);
                    }
                    else {
                        n8 += (int)(n7 / 1.25f * 0.75f);
                        x2 -= (int)(n7 * 1.25f * 0.75f);
                    }
                }
                final String formatNumber = ChartSample.formatNumber(n5, this.rangeDecimalCount);
                graphics.setColor(super.chartForeground);
                if (this.barAlignment == 1) {
                    graphics.drawLine(x2 - 3, y2, x2, y2);
                    graphics.setColor(this.getForeground());
                    graphics.drawString(formatNumber, x2 - 3 - fontMetrics.stringWidth(formatNumber), y2 + 4);
                }
                else {
                    graphics.drawLine(x2, n8, x2, n8 + 3);
                    graphics.setColor(this.getForeground());
                    graphics.drawString(formatNumber, x2 - fontMetrics.stringWidth(formatNumber) / 2, n8 + height);
                }
            }
            if (this.barAlignment == 1) {
                n5 -= (n3 - n2) / n;
            }
            else {
                n5 += (n3 - n2) / n;
            }
        }
    }
    
    public int getValuePosition(final double n) {
        return this.getValuePosition(n, this.getGraphBounds());
    }
    
    private int getValuePosition(final double n, final Rectangle rectangle) {
        int n2 = 0;
        int n3;
        if (this.barAlignment == 1) {
            if (this.upperRange - this.lowerRange != 0.0) {
                n2 = (int)(rectangle.height * (this.upperRange / (this.upperRange - this.lowerRange)));
            }
            n3 = rectangle.y + n2;
        }
        else {
            if (this.upperRange - this.lowerRange != 0.0) {
                n2 = (int)(rectangle.width * (this.upperRange / (this.upperRange - this.lowerRange)));
            }
            n3 = rectangle.x + rectangle.width - n2;
        }
        int n4 = 0;
        if (this.upperRange - this.lowerRange != 0.0) {
            if (this.barAlignment == 1) {
                n4 = (int)(n3 - rectangle.height * (n / (this.upperRange - this.lowerRange)));
            }
            else {
                n4 = (int)(n3 + rectangle.width * (n / (this.upperRange - this.lowerRange)));
            }
        }
        return n4;
    }
    
    private void paintTargetValueLines(final Graphics graphics, final Rectangle rectangle) {
        int n = 0;
        if (super.display3dOn) {
            if (this.barAlignment == 1) {
                n = (int)(rectangle.width / super.samples.length * 0.4f);
            }
            else {
                n = (int)(rectangle.height / super.samples.length * 0.4f);
            }
        }
        final Enumeration<TargetValueLine> elements = this.targetValueLines.elements();
        while (elements.hasMoreElements()) {
            final TargetValueLine targetValueLine = elements.nextElement();
            if (targetValueLine != null) {
                int n3;
                int n2;
                int n4;
                int n5;
                if (this.barAlignment == 1) {
                    n2 = (n3 = this.getValuePosition(targetValueLine.value, rectangle));
                    n4 = rectangle.x;
                    n5 = n4 + rectangle.width;
                }
                else {
                    n3 = rectangle.y;
                    n2 = rectangle.y + rectangle.height;
                    n5 = (n4 = this.getValuePosition(targetValueLine.value, rectangle));
                }
                graphics.setColor(targetValueLine.color);
                if (n3 >= rectangle.y && n2 <= rectangle.y + rectangle.height && n4 >= rectangle.x && n5 <= rectangle.x + rectangle.width) {
                    graphics.drawLine(n4, n3, n5, n2);
                }
                if (super.display3dOn) {
                    if (this.barAlignment == 1) {
                        graphics.drawLine(n4, n3, n4 - (int)(n * 1.25f), n3 + (int)(n / 1.25f));
                    }
                    else {
                        graphics.drawLine(n4, n2, n4 - (int)(n * 1.25f * 0.75f), n2 + (int)(n / 1.25f * 0.75f));
                    }
                }
                final String label = targetValueLine.getLabel(this.rangeDecimalCount);
                if (label == null) {
                    continue;
                }
                if (super.display3dOn) {
                    if (this.barAlignment == 1) {
                        n4 -= (int)(n * 1.25f);
                        n3 += (int)(n / 1.25f);
                    }
                    else {
                        n2 += (int)(n / 1.25f * 0.75f);
                        n4 -= (int)(n * 1.25f * 0.75f);
                    }
                }
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                final int height = fontMetrics.getHeight();
                final int stringWidth = fontMetrics.stringWidth(label);
                if (this.barAlignment == 1) {
                    graphics.setColor(this.getBackground());
                    graphics.fillRect(0, n3 + 4 - (height - 4), n4 - 2, height - 4);
                    graphics.setColor(targetValueLine.color);
                    graphics.drawLine(n4 - 3, n3, n4, n3);
                    graphics.drawString(label, n4 - 3 - stringWidth, n3 + 4);
                }
                else {
                    graphics.setColor(this.getBackground());
                    graphics.fillRect(n4 - stringWidth / 2 - 1, n2 + 4, stringWidth + 2, height - 3);
                    graphics.setColor(targetValueLine.color);
                    graphics.drawLine(n4, n2, n4, n2 + 3);
                    graphics.drawString(label, n4 - stringWidth / 2, n2 + height);
                }
            }
        }
    }
    
    private void paint3DZeroDivider(final Graphics graphics, final Rectangle rectangle) {
        int n;
        if (this.barAlignment == 1) {
            n = (int)(rectangle.width / super.samples.length * 0.4f);
        }
        else {
            n = (int)(rectangle.height / super.samples.length * 0.4f);
        }
        graphics.setColor(this.getForeground());
        if (this.barAlignment == 1) {
            final int min = Math.min(Math.max(rectangle.y + (int)(rectangle.height * (this.upperRange / (this.upperRange - this.lowerRange))), rectangle.y), rectangle.y + rectangle.height);
            final int n2 = rectangle.x - (int)(n * 1.25f);
            final int n3 = min + (int)(n / 1.25f);
            graphics.drawLine(n2, n3, n2 + rectangle.width, n3);
            graphics.drawLine(n2 + rectangle.width, n3, rectangle.x + rectangle.width, min);
        }
        else {
            final int min2 = Math.min(Math.max(rectangle.x + rectangle.width - (int)(rectangle.width * (this.upperRange / (this.upperRange - this.lowerRange))), rectangle.x), rectangle.x + rectangle.width);
            final int n4 = rectangle.y + rectangle.height + (int)(n / 1.25f * 0.75f);
            final int n5 = min2 - (int)(n * 1.25f * 0.75f);
            graphics.drawLine(n5, n4, n5, rectangle.y + (int)(n / 1.25f * 0.75f));
            graphics.drawLine(n5, rectangle.y + (int)(n / 1.25f * 0.75f), min2, rectangle.y);
        }
    }
    
    public Rectangle getGraphBounds() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int height = fontMetrics.getHeight();
        final Rectangle graphBounds = super.getGraphBounds(this.getSampleLabels());
        if (this.barAlignment == 1) {
            int n = 0;
            if (this.rangeLabelsOn) {
                n = Math.max(fontMetrics.stringWidth(ChartSample.formatNumber(this.upperRange, this.rangeDecimalCount)), fontMetrics.stringWidth(ChartSample.formatNumber(this.lowerRange, this.rangeDecimalCount)));
            }
            final Enumeration<TargetValueLine> elements = (Enumeration<TargetValueLine>)this.targetValueLines.elements();
            while (elements.hasMoreElements()) {
                final TargetValueLine targetValueLine = elements.nextElement();
                if (targetValueLine != null) {
                    final String label = targetValueLine.getLabel(this.rangeDecimalCount);
                    if (label == null) {
                        continue;
                    }
                    n = Math.max(n, fontMetrics.stringWidth(label));
                }
            }
            final Rectangle rectangle = graphBounds;
            rectangle.x += n;
            final Rectangle rectangle2 = graphBounds;
            rectangle2.width -= n;
            if (super.valueLabelsOn) {
                final int n2 = fontMetrics.getHeight() - fontMetrics.getDescent();
                final int n3 = (int)(graphBounds.height - Math.min(this.getFloatMaxValue(), this.upperRange) / this.upperRange * graphBounds.height);
                if (n3 < n2) {
                    final Rectangle rectangle3 = graphBounds;
                    rectangle3.y += n2 - n3;
                    final Rectangle rectangle4 = graphBounds;
                    rectangle4.height -= n2 - n3;
                }
            }
        }
        else {
            int max = 0;
            if (this.barLabelsOn) {
                int max2 = 0;
                for (int i = 0; i < super.samples.length; ++i) {
                    if (super.samples[i] != null && super.samples[i].label != null) {
                        max2 = Math.max(max2, fontMetrics.stringWidth(super.samples[i].label));
                    }
                }
                max2 += 5;
                if (this.rangeLabelsOn) {
                    max = Math.max(fontMetrics.stringWidth(ChartSample.formatNumber(this.lowerRange, this.rangeDecimalCount)) / 2, max2);
                }
            }
            else if (this.rangeLabelsOn) {
                max = fontMetrics.stringWidth(ChartSample.formatNumber(this.lowerRange, this.rangeDecimalCount)) / 2;
            }
            int max3;
            if (super.valueLabelsOn) {
                double n4 = Math.min(this.getFloatMaxValue(), this.upperRange);
                int n5 = (int)(graphBounds.width - n4 / this.upperRange * graphBounds.width);
                if (this.upperRange <= 0.0) {
                    n4 = this.getFloatMinValue();
                    n5 = 0;
                }
                max3 = Math.max(fontMetrics.stringWidth(ChartSample.formatNumber(this.upperRange, this.rangeDecimalCount)) / 2, fontMetrics.stringWidth(ChartSample.formatNumber(n4, super.sampleDecimalCount)) + 5 - n5);
            }
            else {
                max3 = fontMetrics.stringWidth(ChartSample.formatNumber(this.upperRange, this.rangeDecimalCount)) / 2;
            }
            max -= 3;
            max3 -= 3;
            final Rectangle rectangle5 = graphBounds;
            rectangle5.x += max;
            final Rectangle rectangle6 = graphBounds;
            rectangle6.width -= max + max3;
            final Rectangle rectangle7 = graphBounds;
            rectangle7.height -= height;
        }
        if (this.barLabelsOn && this.barAlignment == 1) {
            final Rectangle rectangle8 = graphBounds;
            rectangle8.height -= height - 5;
        }
        if (super.display3dOn) {
            if (this.barAlignment == 1) {
                final int n6 = (int)(graphBounds.width * 0.4f * 1.25f / (super.samples.length + 0.5f));
                final Rectangle rectangle9 = graphBounds;
                rectangle9.width -= n6;
                final Rectangle rectangle10 = graphBounds;
                rectangle10.x += n6;
                final int n7 = (int)(graphBounds.width / super.samples.length * 0.4f);
                final Rectangle rectangle11 = graphBounds;
                rectangle11.height -= (int)(n7 / 1.25f);
            }
            else {
                final int n8 = (int)(graphBounds.height * 0.4f * 0.75f / (1.25f * super.samples.length + 0.3f));
                final Rectangle rectangle12 = graphBounds;
                rectangle12.height -= n8;
                final int n9 = (int)(graphBounds.height / super.samples.length * 0.4f);
                final Rectangle rectangle13 = graphBounds;
                rectangle13.width -= (int)(n9 * 1.25f * 0.75f);
                final Rectangle rectangle14 = graphBounds;
                rectangle14.x += (int)(n9 * 1.25f * 0.75f);
            }
        }
        return graphBounds;
    }
    
    private void setBarBounds(final int n, final Rectangle bounds) {
        if (bounds == null) {
            return;
        }
        if (this.barBounds[n] == null) {
            this.barBounds[n] = new Rectangle(bounds);
        }
        if (this.barBounds[n] != null) {
            this.barBounds[n].setBounds(bounds);
            if (super.display3dOn) {
                final Rectangle rectangle = this.barBounds[n];
                if (this.barAlignment == 1) {
                    final Rectangle rectangle2 = rectangle;
                    rectangle2.y -= (int)(bounds.width / 1.25f);
                    final Rectangle rectangle3 = rectangle;
                    rectangle3.height += (int)(bounds.width / 1.25f);
                    final Rectangle rectangle4 = rectangle;
                    rectangle4.width += (int)(bounds.width * 1.25f);
                }
                else {
                    final int n2 = (int)(bounds.height / 1.25f * 0.75f);
                    final Rectangle rectangle5 = rectangle;
                    rectangle5.y -= n2;
                    final Rectangle rectangle6 = rectangle;
                    rectangle6.height += n2;
                    final Rectangle rectangle7 = rectangle;
                    rectangle7.width += (int)(bounds.height * 1.25f * 0.75f);
                }
            }
        }
    }
    
    private int getValueLineCount(final int n, final boolean b) {
        int n2 = 20;
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int n3 = (int)(fontMetrics.getHeight() * 1.25);
        if (this.barAlignment == 1) {
            while (n2 > 0 && n / n2 < n3) {
                n2 /= 2;
            }
        }
        else {
            String s;
            if (b) {
                s = ChartSample.formatNumber(this.upperRange, this.rangeDecimalCount);
            }
            else {
                s = ChartSample.formatNumber(this.lowerRange, this.rangeDecimalCount);
            }
            for (int n4 = (int)(fontMetrics.stringWidth(s) * 1.25); n2 > 0 && n / n2 < n4; n2 /= 2) {}
        }
        if (n2 == 0) {
            ++n2;
        }
        return n2;
    }
    
    public void setValueLinesColor(final Color valueLinesColor) {
        this.valueLinesColor = valueLinesColor;
        this.repaint(1L);
    }
    
    public Color getValueLinesColor() {
        return this.valueLinesColor;
    }
    
    public void setBarColor(final Color color) {
        this.setSampleColor(0, color);
    }
    
    public Color getBarColor() {
        return this.getSampleColor(0);
    }
    
    private class TargetValueLine
    {
        String id;
        double value;
        Color color;
        int style;
        
        public TargetValueLine(final String id, final double value, final Color color, final int style) {
            BarChart.this.getClass();
            this.id = id;
            this.value = value;
            this.color = color;
            this.style = style;
        }
        
        public String getLabel(final int n) {
            switch (this.style) {
                case 1: {
                    return this.id;
                }
                case 2: {
                    return ChartSample.formatNumber(this.value, n);
                }
                case 3: {
                    return this.id + " " + ChartSample.formatNumber(this.value, n);
                }
                default: {
                    return null;
                }
            }
        }
    }
}
