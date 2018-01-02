// 
// Decompiled by Procyon v0.5.30
// 

package com.objectplanet.chart;

import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.Rectangle;
import java.awt.event.ComponentListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;

public class PieChart extends Chart
{
    private static final double DEGREETORAD = 0.017453292519943295;
    private int angle;
    private float depth;
    private Point chartCenter;
    private Dimension chartSize;
    boolean sampleLabelsOn;
    private boolean pieValuesOn;
    boolean percentLabelsOn;
    private int percentDecimalCount;
    ChartSample labelPieSegment;
    private int[] degrees;
    private double[] fractions;
    
    public boolean isPercentLabelsOn() {
        return this.percentLabelsOn;
    }
    
    public boolean isSampleLabelsOn() {
        return this.sampleLabelsOn;
    }
    
    protected ChartSample checkSelection(final Point point) {
        final ChartSample checkSelection = super.checkSelection(point);
        if (checkSelection == null) {
            point.x -= this.chartCenter.x;
            point.y = this.chartCenter.y - point.y;
            if (super.display3dOn) {
                point.y *= this.chartSize.width / this.chartSize.height;
            }
            if (Math.sqrt(point.x * point.x + point.y * point.y) <= this.chartSize.width / 2) {
                double n = Math.atan2(point.y, point.x) * 57.29577951308232;
                if (n > 90.0 && n < 180.0) {
                    n -= 360.0;
                }
                for (int i = 0; i < super.samples.length; ++i) {
                    final int min = Math.min(this.degrees[i], this.degrees[i + 1]);
                    final int max = Math.max(this.degrees[i], this.degrees[i + 1]);
                    if (n >= min && n < max) {
                        return super.samples[i];
                    }
                }
            }
        }
        return checkSelection;
    }
    
    private void paint3dSegment(final Graphics graphics, final Point point, final int n, final int n2, final int n3) {
        final double n4 = n * 0.017453292519943295;
        final double n5 = n2 * 0.017453292519943295;
        final int n6 = (int)(this.chartSize.width * this.depth * (this.angle / 90.0));
        final int n7 = this.chartSize.width / 2;
        final int n8 = this.chartSize.height / 2;
        final double n9 = Math.abs(n4 - n5) / (3.141592653589793 * Math.max(this.chartSize.width, this.chartSize.height) * (Math.abs(n4 - n5) / 6.283185307179586));
        graphics.setColor(this.getSampleColor(n3).darker());
        double n10 = n4;
        while (n10 >= n5) {
            n10 -= n9;
            final int n11 = (int)(Math.cos(n10) * n7);
            final int n12 = (int)(Math.sin(n10) * n8);
            if (n12 < 0) {
                graphics.drawLine(point.x + n11, point.y - n12, point.x + n11, point.y - n12 + n6);
            }
        }
    }
    
    private void paintLabel(final Graphics graphics, final ChartSample chartSample) {
        if (chartSample == null) {
            return;
        }
        graphics.setFont(this.getFont());
        final int index = chartSample.getIndex();
        final double n = (this.degrees[index] + (this.degrees[index + 1] - this.degrees[index]) / 2) * 0.017453292519943295;
        final int n2 = this.chartCenter.x + (int)(Math.cos(n) * this.chartSize.width / 4.0);
        final int n3 = this.chartCenter.y - (int)(Math.sin(n) * this.chartSize.height / 4.0);
        String s = "";
        if (this.sampleLabelsOn && chartSample.label != null) {
            s += chartSample.label;
        }
        if (super.valueLabelsOn && this.percentLabelsOn) {
            if (this.sampleLabelsOn) {
                s += " ";
            }
            s = s + ChartSample.formatNumber(chartSample.getFloatValue(), super.sampleDecimalCount) + " (" + ChartSample.formatNumber(this.fractions[index] * 100.0, this.percentDecimalCount) + "%)";
        }
        else if (super.valueLabelsOn) {
            if (this.sampleLabelsOn) {
                s += " ";
            }
            s += ChartSample.formatNumber(chartSample.getFloatValue(), super.sampleDecimalCount);
        }
        else if (this.percentLabelsOn) {
            if (this.sampleLabelsOn) {
                s += " ";
            }
            s = s + ChartSample.formatNumber(this.fractions[index] * 100.0, this.percentDecimalCount) + "%";
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int stringWidth = fontMetrics.stringWidth(s);
        final int height = fontMetrics.getHeight();
        final int n4 = n2 - stringWidth / 2;
        final int n5 = n3 - height / 2;
        graphics.setColor(new Color(255, 255, 231));
        graphics.fillRect(n4, n5, stringWidth + 4, height + 2);
        graphics.setColor(Color.black);
        graphics.drawRect(n4, n5, stringWidth + 4, height + 2);
        graphics.drawString(s, n4 + 2, n5 + height - 1);
    }
    
    public void setDepth(final float depth) {
        this.depth = depth;
        super.hasChanged = true;
        this.repaint(1L);
    }
    
    public PieChart() {
        this(5);
    }
    
    public PieChart(final int n) {
        super(n);
        this.angle = 20;
        this.depth = 0.4f;
        final EventHandler eventHandler = new EventHandler();
        this.addMouseMotionListener(eventHandler);
        this.addMouseListener(eventHandler);
        this.addComponentListener(eventHandler);
    }
    
    public float getDepth() {
        return this.depth;
    }
    
    public void setPercentDecimalCount(final int percentDecimalCount) {
        this.percentDecimalCount = percentDecimalCount;
        this.repaint();
    }
    
    public int getPercentDecimalCount() {
        return this.percentDecimalCount;
    }
    
    public double getPercentValue(final int n) {
        try {
            return this.fractions[n] * 100.0;
        }
        catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException("Invalid index: " + n);
        }
    }
    
    private void paintSegment(final Graphics graphics, final double n, final double n2, final int n3) {
        if (super.display3dOn && super.samples[n3] != null && super.samples[n3].getFloatValue() > 0.0) {
            this.paint3dSegment(graphics, this.chartCenter, (int)n, (int)(n - n2), n3);
        }
        graphics.setColor(this.getSampleColor(n3));
        graphics.fillArc(this.chartCenter.x - this.chartSize.width / 2, this.chartCenter.y - this.chartSize.height / 2, this.chartSize.width, this.chartSize.height, (int)n, (int)(-(n2 + 1.0)));
        final int n4 = (n3 > 0) ? (n3 - 1) : (super.samples.length - 1);
        if (super.samples[n3].isSelected() || super.samples[n4].isSelected()) {
            graphics.setColor(Color.white);
            final double n5 = (int)n * 0.017453292519943295;
            graphics.drawLine(this.chartCenter.x, this.chartCenter.y, this.chartCenter.x + (int)(Math.cos(n5) * (this.chartSize.width / 2)), this.chartCenter.y - (int)(Math.sin(n5) * (this.chartSize.height / 2)));
            if (super.samples[n3].isSelected()) {
                final int n6 = this.chartCenter.x - this.chartSize.width / 2;
                final int n7 = this.chartCenter.y - this.chartSize.height / 2;
                graphics.drawArc(n6 + 1, n7 + 1, this.chartSize.width - 3, this.chartSize.height - 3, (int)n, (int)(-(n2 + 1.0)));
                graphics.drawArc(n6 + 2, n7 + 2, this.chartSize.width - 5, this.chartSize.height - 5, (int)n, (int)(-(n2 + 1.0)));
            }
        }
    }
    
    public void setAngle(final int angle) {
        this.angle = angle;
        super.hasChanged = true;
        this.repaint(1L);
    }
    
    public int getAngle() {
        return this.angle;
    }
    
    public void render(final Graphics graphics) {
        final Dimension size = this.getSize();
        final Rectangle graphBounds = this.getGraphBounds();
        graphics.setFont(this.getFont());
        this.paintFrame(graphics, size);
        if (super.legendOn) {
            this.paintLegend(graphics, graphBounds, super.samples);
        }
        this.paintPie(graphics, graphBounds);
        if (super.chartTitleOn) {
            this.paintTitle(graphics, size);
        }
        if (this.sampleLabelsOn || super.valueLabelsOn || this.percentLabelsOn) {
            this.paintLabel(graphics, this.labelPieSegment);
        }
    }
    
    public void setPercentLabelsOn(final boolean percentLabelsOn) {
        this.percentLabelsOn = percentLabelsOn;
        this.repaint();
    }
    
    public void setSampleLabelsOn(final boolean sampleLabelsOn) {
        this.sampleLabelsOn = sampleLabelsOn;
        this.repaint();
    }
    
    public Rectangle getGraphBounds() {
        return this.getGraphBounds(this.getSampleLabels());
    }
    
    private void initSegmentFractions() {
        double n = 0.0;
        for (int i = 0; i < super.samples.length; ++i) {
            if (super.samples[i] != null) {
                n += super.samples[i].getFloatValue();
            }
        }
        if (this.fractions == null || this.fractions.length != super.samples.length) {
            this.fractions = new double[super.samples.length];
        }
        for (int j = 0; j < super.samples.length; ++j) {
            if (super.samples[j] != null) {
                this.fractions[j] = super.samples[j].getFloatValue() / n;
            }
            else {
                this.fractions[j] = 0.0;
            }
        }
    }
    
    private void paintPie(final Graphics graphics, final Rectangle rectangle) {
        if (this.chartSize == null) {
            this.chartSize = new Dimension();
        }
        final Dimension chartSize = this.chartSize;
        final Dimension chartSize2 = this.chartSize;
        final int min = Math.min(rectangle.width, rectangle.height);
        chartSize2.height = min;
        chartSize.width = min;
        int height = this.chartSize.height;
        if (super.display3dOn) {
            final Dimension chartSize3 = this.chartSize;
            chartSize3.height -= (int)(this.chartSize.width * (this.angle / 90.0));
            height = this.chartSize.height + (int)(this.chartSize.width * this.depth * (this.angle / 90.0));
        }
        if (this.chartCenter == null) {
            this.chartCenter = new Point();
        }
        this.chartCenter.x = rectangle.x + rectangle.width / 2;
        this.chartCenter.y = rectangle.y + rectangle.height / 2 - height / 2 + this.chartSize.height / 2;
        this.initSegmentFractions();
        if (this.degrees == null || this.degrees.length != super.samples.length + 1) {
            this.degrees = new int[super.samples.length + 1];
        }
        double n = 90.0;
        for (int i = 0; i < super.samples.length; ++i) {
            this.degrees[i] = (int)n;
            final double n2 = this.fractions[i] * 360.0;
            this.paintSegment(graphics, n, n2, i);
            n -= n2;
        }
        this.degrees[this.degrees.length - 1] = (int)n;
        graphics.setColor(Color.black);
        graphics.drawOval(this.chartCenter.x - this.chartSize.width / 2, this.chartCenter.y - this.chartSize.height / 2, this.chartSize.width - 1, this.chartSize.height - 1);
        if (super.display3dOn) {
            final int n3 = (int)(this.chartSize.width * this.depth * (this.angle / 90.0));
            graphics.drawArc(this.chartCenter.x - this.chartSize.width / 2, this.chartCenter.y - this.chartSize.height / 2 + n3 + 1, this.chartSize.width - 1, this.chartSize.height - 1, 0, -180);
            final int n4 = this.chartCenter.x - this.chartSize.width / 2;
            graphics.drawLine(n4, this.chartCenter.y, n4, this.chartCenter.y + n3);
            final int n5 = this.chartCenter.x + this.chartSize.width / 2;
            graphics.drawLine(n5, this.chartCenter.y, n5, this.chartCenter.y + n3);
        }
    }
    
    class EventHandler extends MouseAdapter implements MouseMotionListener, ComponentListener
    {
        public void componentShown(final ComponentEvent componentEvent) {
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
        }
        
        public void componentResized(final ComponentEvent componentEvent) {
            PieChart.this.repaint(1L);
        }
        
        public void componentHidden(final ComponentEvent componentEvent) {
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (PieChart.this.sampleLabelsOn || PieChart.this.valueLabelsOn || PieChart.this.percentLabelsOn) {
                final ChartSample checkSelection = PieChart.this.checkSelection(new Point(mouseEvent.getX(), mouseEvent.getY()));
                if (checkSelection != PieChart.this.labelPieSegment) {
                    PieChart.this.labelPieSegment = checkSelection;
                    PieChart.this.repaint(1L);
                }
            }
        }
        
        EventHandler() {
            PieChart.this.getClass();
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            PieChart.this.labelPieSegment = null;
            PieChart.this.repaint(1L);
        }
        
        public void componentMoved(final ComponentEvent componentEvent) {
        }
    }
}
