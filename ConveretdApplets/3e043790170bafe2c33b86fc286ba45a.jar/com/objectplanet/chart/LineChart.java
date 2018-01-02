// 
// Decompiled by Procyon v0.5.30
// 

package com.objectplanet.chart;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Hashtable;

public class LineChart extends Chart
{
    public static final int TARGET_LINE_NO_LABEL = 0;
    public static final int TARGET_LINE_ID_LABEL = 1;
    public static final int TARGET_LINE_VALUE_LABEL = 2;
    public static final int TARGET_LINE_ID_AND_VALUE_LABEL = 3;
    double upperRange;
    double lowerRange;
    private int rangeDecimalCount;
    private boolean valueLinesOn;
    private Hashtable targetValueLines;
    private boolean sampleLabelsOn;
    private boolean rangeLabelsOn;
    boolean rangeAdjusterOn;
    double currentUpperRange;
    double currentLowerRange;
    Rectangle rangeAdjusterBounds;
    private ChartSample[][] series;
    private ChartSample[] seriesLabels;
    private Color valueLinesColor;
    
    public synchronized void setRange(final double n) {
        this.upperRange = n;
        this.currentUpperRange = n;
        this.repaint(1L);
    }
    
    public synchronized long getRange() {
        return (long)this.upperRange;
    }
    
    public boolean isValueLinesOn() {
        return this.valueLinesOn;
    }
    
    public void setRangeLabelsOn(final boolean rangeLabelsOn) {
        this.rangeLabelsOn = rangeLabelsOn;
        this.repaint(1L);
    }
    
    public boolean isSampleLabelsOn() {
        return this.sampleLabelsOn;
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
    
    public void setSeriesLabel(final int n, final String label) {
        try {
            if (this.seriesLabels[n] == null) {
                this.seriesLabels[n] = new ChartSample(n);
            }
            this.seriesLabels[n].label = label;
        }
        catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Invalid series index: " + n);
        }
    }
    
    public String getSeriesLabel(final int n) {
        try {
            if (this.seriesLabels[n] != null) {
                return this.seriesLabels[n].label;
            }
            return null;
        }
        catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Invalid series index: " + n);
        }
    }
    
    private void paintGrid(final Graphics graphics, final Rectangle rectangle) {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        graphics.setColor(super.chartBackground);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        final int max = Math.max(Math.min((int)(rectangle.height * (this.upperRange / (this.upperRange - this.lowerRange))), rectangle.height), 0);
        if (this.upperRange > 0.0) {
            final int valueLineCount = this.getValueLineCount(max);
            final Rectangle rectangle2 = new Rectangle(rectangle);
            rectangle2.height = Math.min(max, rectangle2.height);
            this.paintValueLines(graphics, rectangle2, valueLineCount, Math.max(0.0, this.currentLowerRange), this.currentUpperRange);
        }
        if (this.lowerRange < 0.0) {
            final int height = rectangle.height - max;
            final int valueLineCount2 = this.getValueLineCount(height);
            final Rectangle rectangle3 = new Rectangle(rectangle);
            rectangle3.height = height;
            rectangle3.y = rectangle.y + rectangle.height - height;
            this.paintValueLines(graphics, rectangle3, valueLineCount2, this.currentLowerRange, Math.min(0.0, this.currentUpperRange));
        }
        if (this.sampleLabelsOn) {
            graphics.setColor(this.getForeground());
            final double n = rectangle.width / (super.samples.length - 1);
            final int n2 = rectangle.y + rectangle.height + fontMetrics.getHeight() + 7;
            double n3 = rectangle.x;
            int n4 = 0;
            for (int i = 0; i < super.samples.length; ++i) {
                if (super.samples[i] != null && super.samples[i].label != null) {
                    final int stringWidth = fontMetrics.stringWidth(super.samples[i].label);
                    final int n5 = (int)(n3 - stringWidth / 2);
                    if (n5 - 2 > n4 + 2) {
                        graphics.drawLine((int)n3, rectangle.y + rectangle.height, (int)n3, rectangle.y + rectangle.height + 3);
                        graphics.drawString(super.samples[i].label, n5, n2);
                        n4 = (int)n3 + stringWidth / 2;
                    }
                }
                n3 += n;
            }
        }
        this.paintTargetValueLines(graphics, rectangle);
        graphics.setColor(super.chartForeground);
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public synchronized void setLowerRange(final double n) {
        this.lowerRange = n;
        this.currentLowerRange = n;
        this.repaint(1L);
    }
    
    public synchronized long getLowerRange() {
        return (long)this.lowerRange;
    }
    
    public synchronized void setSampleValue(final int n, final int n2, final double value) {
        ChartSample[] array;
        try {
            array = this.series[n];
        }
        catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Invalid series index: " + n);
        }
        try {
            if (array[n2] == null) {
                array[n2] = new ChartSample(n2);
            }
            array[n2].setValue(value);
        }
        catch (IndexOutOfBoundsException ex2) {
            throw new IllegalArgumentException("Invalid sample index: " + n2);
        }
        this.repaint(1L);
    }
    
    public long getSampleValue(final int n, final int n2) {
        return (long)this.getFloatSampleValue(n, n2);
    }
    
    public synchronized void appendSampleValue(final int n, final double value, final boolean b) {
        if (n < 0 || n >= this.getSeriesCount()) {
            throw new IllegalArgumentException("Illegal serie: " + n);
        }
        final ChartSample[] array = this.series[n];
        int n2 = -1;
        for (int sampleCount = this.getSampleCount(), i = 0; i < sampleCount; ++i) {
            if (array[i].value == null) {
                n2 = i;
                break;
            }
        }
        if (n2 >= 0) {
            array[n2].setValue(value);
            this.repaint(1L);
        }
        else if (b) {
            final int sampleCount2 = this.getSampleCount();
            this.setSampleCount(sampleCount2 + 1);
            this.setSampleValue(n, sampleCount2, value);
        }
        else {
            final double[] floatSampleValues = this.getFloatSampleValues(n);
            for (int j = 0; j < floatSampleValues.length - 1; ++j) {
                floatSampleValues[j] = floatSampleValues[j + 1];
            }
            floatSampleValues[floatSampleValues.length - 1] = value;
            this.setSampleValues(n, floatSampleValues);
        }
    }
    
    public void setRangeDecimalCount(final int rangeDecimalCount) {
        this.rangeDecimalCount = rangeDecimalCount;
        this.repaint(1L);
    }
    
    public int getRangeDecimalCount() {
        return this.rangeDecimalCount;
    }
    
    public double getFloatMinValue() {
        double min = Long.MAX_VALUE;
        boolean b = false;
        for (int i = 0; i < this.series.length; ++i) {
            for (int j = 0; j < this.series[i].length; ++j) {
                if (this.series[i][j] != null && this.series[i][j].value != null) {
                    min = Math.min(min, this.series[i][j].getFloatValue());
                    b = true;
                }
            }
        }
        if (b) {
            return min;
        }
        return 0.0;
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
    
    public void setValueLinesOn(final boolean valueLinesOn) {
        this.valueLinesOn = valueLinesOn;
        this.repaint(1L);
    }
    
    public ChartSample[] getSamples(final int n) {
        try {
            return this.series[n];
        }
        catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Invalid series index: " + n);
        }
    }
    
    public synchronized void setSampleValues(final int n, final long[] array) {
        if (array == null) {
            return;
        }
        final double[] array2 = new double[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = array[i];
        }
        this.setSampleValues(n, array2);
    }
    
    public synchronized void setSampleValues(final int n, final double[] array) {
        if (n < 0 || n >= this.series.length) {
            throw new IllegalArgumentException("Invalid series index: " + n);
        }
        final int min = Math.min(array.length, this.series[n].length);
        for (int i = 0; i < min; ++i) {
            if (this.series[n][i] == null) {
                this.series[n][i] = new ChartSample(i);
            }
            this.series[n][i].setValue(array[i]);
        }
        for (int j = min; j < this.series[n].length; ++j) {
            if (this.series[n][j] != null) {
                this.series[n][j].value = null;
            }
        }
        this.repaint(1L);
    }
    
    public long[] getSampleValues(final int n) {
        try {
            final long[] array = new long[this.series[n].length];
            for (int i = 0; i < array.length; ++i) {
                if (this.series[n][i] != null && this.series[n][i].value != null) {
                    array[i] = this.series[n][i].getValue();
                }
            }
            return array;
        }
        catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Invalid series index: " + n);
        }
    }
    
    public void setSampleLabelsOn(final boolean sampleLabelsOn) {
        this.sampleLabelsOn = sampleLabelsOn;
        this.repaint(1L);
    }
    
    public boolean isRangeAdjusterOn() {
        return this.rangeAdjusterOn;
    }
    
    private void paintRangeAdjuster(final Graphics graphics, final Rectangle rectangle, final double n) {
        final Color background = this.getBackground();
        final int n2 = rectangle.x + rectangle.width;
        final int n3 = 3;
        graphics.setColor(background.darker());
        graphics.drawLine(n2, rectangle.y, n2, rectangle.y + rectangle.height);
        graphics.drawLine(n2, rectangle.y, n2 + n3, rectangle.y);
        graphics.setColor(background.brighter());
        graphics.drawLine(n2 + n3, rectangle.y, n2 + n3, rectangle.y + rectangle.height);
        graphics.drawLine(n2 + n3, rectangle.y + rectangle.height, n2, rectangle.y + rectangle.height);
        final Rectangle rangeAdjusterBounds = this.rangeAdjusterBounds;
        rangeAdjusterBounds.y = ((rangeAdjusterBounds.y < rectangle.y) ? rectangle.y : rangeAdjusterBounds.y);
        rangeAdjusterBounds.x = rectangle.x + rectangle.width - 3;
        graphics.setColor(background);
        graphics.fillRect(rangeAdjusterBounds.x, rangeAdjusterBounds.y, rangeAdjusterBounds.width, rangeAdjusterBounds.height);
        graphics.setColor(Color.white);
        graphics.drawLine(rangeAdjusterBounds.x, rangeAdjusterBounds.y, rangeAdjusterBounds.x + rangeAdjusterBounds.width, rangeAdjusterBounds.y);
        graphics.drawLine(rangeAdjusterBounds.x, rangeAdjusterBounds.y, rangeAdjusterBounds.x, rangeAdjusterBounds.y + rangeAdjusterBounds.height);
        graphics.setColor(Color.black);
        graphics.drawLine(rangeAdjusterBounds.x, rangeAdjusterBounds.y + rangeAdjusterBounds.height, rangeAdjusterBounds.x + rangeAdjusterBounds.width, rangeAdjusterBounds.y + rangeAdjusterBounds.height);
        graphics.drawLine(rangeAdjusterBounds.x + rangeAdjusterBounds.width, rangeAdjusterBounds.y + rangeAdjusterBounds.height, rangeAdjusterBounds.x + rangeAdjusterBounds.width, rangeAdjusterBounds.y);
    }
    
    protected ChartSample checkSelection(final Point point) {
        ChartSample chartSample = null;
        for (int i = 0; i < super.legendBounds.length; ++i) {
            if (super.legendBounds[i] != null && super.legendBounds[i].contains(point)) {
                chartSample = this.seriesLabels[i];
                break;
            }
        }
        return chartSample;
    }
    
    public double getFloatSampleValue(final int n, final int n2) {
        ChartSample[] array;
        try {
            array = this.series[n];
        }
        catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Illegal series index: " + n);
        }
        try {
            if (array[n2] != null) {
                return array[n2].getFloatValue();
            }
            return 0.0;
        }
        catch (IndexOutOfBoundsException ex2) {
            throw new IllegalArgumentException("Illegal sample index: " + n2);
        }
    }
    
    public void setSeriesCount(final int n) {
        final ChartSample[][] series = new ChartSample[n][this.getSampleCount()];
        System.arraycopy(this.series, 0, series, 0, Math.min(n, this.series.length));
        this.series = series;
        final ChartSample[] seriesLabels = new ChartSample[n];
        System.arraycopy(this.seriesLabels, 0, seriesLabels, 0, Math.min(n, this.seriesLabels.length));
        for (int i = this.seriesLabels.length; i < n; ++i) {
            seriesLabels[i] = new ChartSample(i);
        }
        this.seriesLabels = seriesLabels;
        this.initLegendBounds();
        super.hasChanged = true;
        this.repaint(1L);
    }
    
    public int getSeriesCount() {
        return this.series.length;
    }
    
    public void setSampleColors(final Color[] sampleColors) {
        super.setSampleColors(sampleColors);
        if (sampleColors == null) {
            this.setSampleColor(2, new Color(132, 255, 198));
        }
        this.repaint(1L);
    }
    
    public LineChart() {
        this(1, 5, 100.0);
    }
    
    public LineChart(final int n, final int n2, final double n3) {
        this(n, n2, n3, 0.0);
    }
    
    public LineChart(final int n, final int n2, final double range, final double lowerRange) {
        super(n2);
        this.targetValueLines = new Hashtable();
        this.rangeLabelsOn = true;
        this.series = new ChartSample[n][n2];
        this.setRange(range);
        this.setLowerRange(lowerRange);
        this.valueLinesColor = Color.lightGray;
        this.rangeAdjusterBounds = new Rectangle(0, 0, 0, 0);
        this.setSampleColors(null);
        this.series[0] = super.samples;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < n2; ++j) {
                this.series[i][j] = new ChartSample(0);
            }
        }
        this.seriesLabels = new ChartSample[n];
        for (int k = 0; k < this.seriesLabels.length; ++k) {
            this.seriesLabels[k] = new ChartSample(k);
        }
        this.initLegendBounds();
        final EventHandler eventHandler = new EventHandler();
        this.addMouseListener(eventHandler);
        this.addMouseMotionListener(eventHandler);
    }
    
    public synchronized void setSampleCount(final int sampleCount) {
        super.setSampleCount(sampleCount);
        if (this.series == null) {
            return;
        }
        this.series[0] = super.samples;
        for (int i = 1; i < this.series.length; ++i) {
            if (sampleCount < this.series[i].length) {
                final ChartSample[] array = new ChartSample[sampleCount];
                System.arraycopy(this.series[i], 0, array, 0, sampleCount);
                this.series[i] = array;
            }
            else if (sampleCount > this.series[i].length) {
                final ChartSample[] array2 = new ChartSample[sampleCount];
                System.arraycopy(this.series[i], 0, array2, 0, this.series[i].length);
                for (int j = this.series[i].length; j < array2.length; ++j) {
                    array2[j] = new ChartSample(j);
                }
                this.series[i] = array2;
            }
        }
    }
    
    public void setLineColor(final Color color) {
        this.setSampleColor(0, color);
    }
    
    public Color getLineColor() {
        return this.getSampleColor(0);
    }
    
    private void paintLine(final Graphics graphics, final int n, final Rectangle rectangle, final boolean b) {
        if (n < 0 || n >= this.series.length) {
            throw new IllegalArgumentException("Invalid index: " + n);
        }
        final ChartSample[] array = this.series[n];
        int n2 = 0;
        if (this.upperRange - this.lowerRange != 0.0) {
            n2 = (int)(rectangle.height * (this.upperRange / (this.upperRange - this.lowerRange)));
        }
        final int n3 = rectangle.y + n2;
        final int length = array.length;
        double n4 = rectangle.x;
        double n5 = 0.0;
        if (length - 1 > 0) {
            n5 = rectangle.width / (length - 1);
        }
        int n6 = rectangle.x;
        int max = rectangle.y + 1;
        int width = rectangle.width;
        int height = rectangle.height;
        final Rectangle clipBounds = graphics.getClipBounds();
        if (clipBounds != null) {
            n6 = Math.max(n6, clipBounds.x);
            max = Math.max(max, clipBounds.y);
            final int min = Math.min(rectangle.x + rectangle.width, clipBounds.x + clipBounds.width);
            final int min2 = Math.min(rectangle.y + rectangle.height, clipBounds.y + clipBounds.height);
            width = min - n6;
            height = min2 - max;
        }
        graphics.setClip(n6, max, width, height);
        double n7 = 0.0;
        if (array[0] != null && array[0].value != null && this.currentUpperRange - this.currentLowerRange != 0.0) {
            n7 = n3 - rectangle.height * (array[0].getFloatValue() / (this.currentUpperRange - this.currentLowerRange));
        }
        final Point point = new Point((int)n4, (int)n7);
        Color color;
        if (this.getSeriesCount() > 1) {
            color = this.getSampleColor(n);
        }
        else {
            color = this.getSampleColor(0);
        }
        for (int i = 1; i < length; ++i) {
            if (array[i] != null && array[i].value != null && this.currentUpperRange - this.currentLowerRange != 0.0) {
                n7 = n3 - rectangle.height * (array[i].getFloatValue() / (this.currentUpperRange - this.currentLowerRange));
            }
            n4 += n5;
            if (array[i] != null && array[i].value != null) {
                graphics.setColor(color);
                graphics.drawLine(point.x, point.y, (int)n4, (int)n7);
                if (b) {
                    graphics.setColor(super.chartForeground);
                    final int n8 = (int)(point.y - n7);
                    final int n9 = (int)n4 - point.x;
                    if (n9 > 0 && Math.abs(n8 / n9) < 1) {
                        graphics.drawLine(point.x, point.y + 1, (int)n4, (int)(n7 + 1.0));
                    }
                    else {
                        graphics.drawLine(point.x + 1, point.y, (int)n4 + 1, (int)n7);
                    }
                }
            }
            point.x = (int)n4;
            point.y = (int)n7;
        }
        graphics.setClip(clipBounds);
    }
    
    public double[] getFloatSampleValues(final int n) {
        try {
            final double[] array = new double[this.series[n].length];
            for (int i = 0; i < array.length; ++i) {
                if (this.series[n][i] != null && this.series[n][i].value != null) {
                    array[i] = this.series[n][i].getFloatValue();
                }
            }
            return array;
        }
        catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Invalid series index: " + n);
        }
    }
    
    public boolean isRangeLabelsOn() {
        return this.rangeLabelsOn;
    }
    
    void initLegendBounds() {
        if (this.seriesLabels != null) {
            super.legendBounds = new Rectangle[this.seriesLabels.length];
        }
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
    
    public void setSeriesLabels(final String[] array) {
        if (array == null) {
            return;
        }
        for (int min = Math.min(array.length, this.getSeriesCount()), i = 0; i < min; ++i) {
            if (this.seriesLabels[i] == null) {
                this.seriesLabels[i] = new ChartSample(i);
            }
            this.seriesLabels[i].label = array[i];
        }
        super.hasChanged = true;
        this.repaint(1L);
    }
    
    public String[] getSeriesLabels() {
        final String[] array = new String[this.getSeriesCount()];
        for (int i = 0; i < array.length; ++i) {
            if (this.seriesLabels[i] != null) {
                array[i] = this.seriesLabels[i].label;
            }
            else {
                array[i] = "";
            }
        }
        return array;
    }
    
    public void render(final Graphics graphics) {
        final Dimension size = this.getSize();
        final Rectangle graphBounds = this.getGraphBounds();
        graphics.setFont(this.getFont());
        this.paintFrame(graphics, size);
        this.paintGrid(graphics, graphBounds);
        if (super.chartTitleOn) {
            this.paintTitle(graphics, size);
        }
        if (super.legendOn) {
            this.paintLegend(graphics, graphBounds, this.seriesLabels);
        }
        this.paintLines(graphics, graphBounds);
        if (this.rangeAdjusterOn) {
            this.paintRangeAdjuster(graphics, graphBounds, this.currentUpperRange);
        }
    }
    
    private void paintValueLines(final Graphics graphics, final Rectangle rectangle, final int n, final double n2, final double n3) {
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int x = rectangle.x;
        final int n4 = rectangle.x + rectangle.width;
        double n5 = n3;
        for (int i = 0; i <= n; ++i) {
            final int n6 = rectangle.y + i / n * rectangle.height;
            final int x2 = rectangle.x;
            final int n7 = rectangle.x + rectangle.width;
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
                graphics.drawLine(x2, n6, n7, n6);
            }
            if (this.rangeLabelsOn) {
                final String formatNumber = ChartSample.formatNumber(n5, this.rangeDecimalCount);
                graphics.setColor(super.chartForeground);
                graphics.drawLine(x2 - 3, n6, x2, n6);
                graphics.setColor(this.getForeground());
                graphics.drawString(formatNumber, x2 - 3 - fontMetrics.stringWidth(formatNumber), n6 + 4);
            }
            n5 -= (n3 - n2) / n;
        }
    }
    
    public int getValuePosition(final double n) {
        return this.getValuePosition(n, this.getGraphBounds());
    }
    
    private int getValuePosition(final double n, final Rectangle rectangle) {
        int n2 = 0;
        if (this.currentUpperRange - this.currentLowerRange != 0.0) {
            n2 = (int)(rectangle.height * (this.currentUpperRange / (this.currentUpperRange - this.currentLowerRange)));
        }
        final int n3 = rectangle.y + n2;
        int n4 = 0;
        if (this.currentUpperRange - this.currentLowerRange != 0.0) {
            n4 = (int)(n3 - rectangle.height * (n / (this.currentUpperRange - this.currentLowerRange)));
        }
        return n4;
    }
    
    public void setRangeAdjusterOn(final boolean rangeAdjusterOn) {
        if (!(this.rangeAdjusterOn = rangeAdjusterOn)) {
            this.currentUpperRange = this.upperRange;
            this.currentLowerRange = this.lowerRange;
        }
        else {
            final Rectangle graphBounds = this.getGraphBounds();
            this.rangeAdjusterBounds.setBounds(graphBounds.x + graphBounds.width - 3, graphBounds.y, 9, 4);
        }
        this.repaint(1L);
    }
    
    private void paintTargetValueLines(final Graphics graphics, final Rectangle rectangle) {
        final Enumeration<TargetValueLine> elements = (Enumeration<TargetValueLine>)this.targetValueLines.elements();
        while (elements.hasMoreElements()) {
            final TargetValueLine targetValueLine = elements.nextElement();
            if (targetValueLine != null) {
                final int valuePosition = this.getValuePosition(targetValueLine.value, rectangle);
                final int x = rectangle.x;
                final int n = x + rectangle.width;
                graphics.setColor(targetValueLine.color);
                if (valuePosition >= rectangle.y && valuePosition <= rectangle.y + rectangle.height) {
                    graphics.drawLine(x, valuePosition, n, valuePosition);
                }
                final String label = targetValueLine.getLabel(this.rangeDecimalCount);
                if (label == null) {
                    continue;
                }
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                final int height = fontMetrics.getHeight();
                final int stringWidth = fontMetrics.stringWidth(label);
                graphics.setColor(this.getBackground());
                graphics.fillRect(0, valuePosition + 4 - (height - 4), x - 2, height - 2);
                graphics.setColor(targetValueLine.color);
                graphics.drawLine(x - 3, valuePosition, x, valuePosition);
                graphics.drawString(label, x - 3 - stringWidth, valuePosition + 4);
            }
        }
    }
    
    public Rectangle getGraphBounds() {
        final Rectangle graphBounds = super.getGraphBounds(this.getSeriesLabels());
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
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
        final int descent = fontMetrics.getDescent();
        final Rectangle rectangle3 = graphBounds;
        rectangle3.y += descent;
        final Rectangle rectangle4 = graphBounds;
        rectangle4.height -= descent;
        if (this.sampleLabelsOn) {
            final Rectangle rectangle5 = graphBounds;
            rectangle5.height -= fontMetrics.getHeight() + 5;
            try {
                final String label2 = super.samples[super.samples.length - 1].label;
                final Rectangle rectangle6 = graphBounds;
                rectangle6.width -= fontMetrics.stringWidth(label2) / 2;
            }
            catch (NullPointerException ex) {}
        }
        return graphBounds;
    }
    
    private void paintLines(final Graphics graphics, final Rectangle rectangle) {
        if (this.currentUpperRange == 0.0 && this.currentLowerRange == 0.0) {
            return;
        }
        for (int i = 0; i < this.series.length; ++i) {
            if (this.seriesLabels[i] != null) {
                this.paintLine(graphics, i, rectangle, this.seriesLabels[i].isSelected());
            }
            else {
                this.paintLine(graphics, i, rectangle, false);
            }
        }
    }
    
    public double getFloatMaxValue() {
        double max = Long.MIN_VALUE;
        boolean b = false;
        for (int i = 0; i < this.series.length; ++i) {
            for (int j = 0; j < this.series[i].length; ++j) {
                if (this.series[i][j] != null && this.series[i][j].value != null) {
                    max = Math.max(max, this.series[i][j].getFloatValue());
                    b = true;
                }
            }
        }
        if (b) {
            return max;
        }
        return 0.0;
    }
    
    private int getValueLineCount(final int n) {
        int n2 = 20;
        for (int n3 = (int)(this.getFontMetrics(this.getFont()).getHeight() * 1.25); n2 > 0 && n / n2 < n3; n2 /= 2) {}
        return Math.max(n2, 1);
    }
    
    public void setValueLinesColor(final Color valueLinesColor) {
        this.valueLinesColor = valueLinesColor;
        this.repaint(1L);
    }
    
    public Color getValueLinesColor() {
        return this.valueLinesColor;
    }
    
    public ChartSample getSample(final int n, final int n2) {
        try {
            return this.getSamples(n)[n2];
        }
        catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Invalid sample index: " + n2);
        }
    }
    
    private class TargetValueLine
    {
        String id;
        double value;
        Color color;
        int style;
        
        public TargetValueLine(final String id, final double value, final Color color, final int style) {
            LineChart.this.getClass();
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
    
    class EventHandler extends MouseAdapter implements MouseMotionListener
    {
        public void mouseDragged(final MouseEvent mouseEvent) {
            if (!LineChart.this.rangeAdjusterOn) {
                return;
            }
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            final Rectangle graphBounds = LineChart.this.getGraphBounds();
            if (x >= graphBounds.x + graphBounds.width - 3 && x <= graphBounds.x + graphBounds.width + 9 && y >= graphBounds.y && y <= graphBounds.y + graphBounds.height) {
                LineChart.this.rangeAdjusterBounds.y = y;
                final double n = 1.0 - (y - graphBounds.y) / graphBounds.height;
                LineChart.this.currentUpperRange = n * LineChart.this.upperRange;
                LineChart.this.currentLowerRange = n * LineChart.this.lowerRange;
                LineChart.this.repaint(1L);
            }
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            this.mouseDragged(mouseEvent);
        }
        
        EventHandler() {
            LineChart.this.getClass();
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
    }
}
