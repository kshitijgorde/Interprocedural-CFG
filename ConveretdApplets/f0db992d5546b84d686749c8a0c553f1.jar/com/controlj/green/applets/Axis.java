// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets;

import com.controlj.green.applets.label.FixedLabelGenerator;
import com.controlj.green.applets.label.TextLine;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Font;
import com.controlj.green.applets.draw.HorizontalRange;
import com.controlj.green.applets.label.RTextLine;
import com.controlj.green.applets.label.LabelGenerator;
import com.controlj.green.applets.draw.Range;
import java.awt.Color;

public class Axis
{
    private int major_tic_size;
    private int minor_tic_size;
    private int minor_tic_count;
    private boolean drawgrid;
    public boolean drawzero;
    public Color gridcolor;
    public Color zerocolor;
    public boolean forceEndLabels;
    public Color axiscolor;
    protected boolean textOnDefaultSide;
    protected int startPositionAlongAxis;
    protected int startPositionAcrossAxis;
    protected int axisBreadth;
    protected Range range;
    LabelGenerator labelGenerator;
    protected RTextLine title;
    protected RTextLine label;
    protected String[] labels;
    protected boolean axisLabelEnabled;
    
    public Axis() {
        this.major_tic_size = 10;
        this.minor_tic_size = 5;
        this.minor_tic_count = 1;
        this.drawgrid = false;
        this.drawzero = false;
        this.gridcolor = null;
        this.zerocolor = null;
        this.forceEndLabels = false;
        this.textOnDefaultSide = true;
        this.startPositionAlongAxis = 0;
        this.startPositionAcrossAxis = 0;
        this.axisBreadth = 0;
        this.range = new HorizontalRange();
        this.labelGenerator = new LabelGenerator();
        this.title = new RTextLine("", new Font("Serif", 0, 10));
        this.label = new RTextLine("0", new Font("Serif", 0, 10));
        this.labels = null;
        this.axisLabelEnabled = true;
    }
    
    public Axis(final Range range) {
        this.major_tic_size = 10;
        this.minor_tic_size = 5;
        this.minor_tic_count = 1;
        this.drawgrid = false;
        this.drawzero = false;
        this.gridcolor = null;
        this.zerocolor = null;
        this.forceEndLabels = false;
        this.textOnDefaultSide = true;
        this.startPositionAlongAxis = 0;
        this.startPositionAcrossAxis = 0;
        this.axisBreadth = 0;
        this.range = new HorizontalRange();
        this.labelGenerator = new LabelGenerator();
        this.title = new RTextLine("", new Font("Serif", 0, 10));
        this.label = new RTextLine("0", new Font("Serif", 0, 10));
        this.labels = null;
        this.axisLabelEnabled = true;
        this.setRange(range);
    }
    
    public void setAxisLabelEnabled(final boolean enablelabels) {
        this.axisLabelEnabled = enablelabels;
    }
    
    public void setLabelGenerator(final LabelGenerator generator) {
        if (generator == null) {
            throw new IllegalArgumentException("The label generator can't be null");
        }
        this.labelGenerator = generator;
    }
    
    public int getLocationFromValue(final double value) {
        return this.range.getPixelFromValue(value);
    }
    
    public double getValueFromLocation(final int pixelLocation) {
        return this.range.getValueFromPixel(pixelLocation);
    }
    
    public void setRange(final Range range) {
        this.range = range;
        if (this.isHorizontal()) {
            this.title.setRotation(0);
        }
        else {
            this.title.setRotation(90);
        }
    }
    
    public Range getRange() {
        return this.range;
    }
    
    public void setTextOnDefaultSide(final boolean p) {
        this.textOnDefaultSide = p;
    }
    
    public boolean isTextOnDefaultSide() {
        return this.textOnDefaultSide;
    }
    
    public boolean isHorizontal() {
        return this.range instanceof HorizontalRange;
    }
    
    public int calculateBreadth(final Graphics g, final int minBreadth) {
        this.calculateGridLabels();
        if (this.forceEndLabels) {
            this.range.setValues(this.labelGenerator.getStartValue(), this.labelGenerator.getEndValue());
        }
        this.axisBreadth = 0;
        if (this.axisLabelEnabled && this.labels != null) {
            if (this.isHorizontal()) {
                for (int i = 0; i < this.labels.length; ++i) {
                    this.label.setText(" " + this.labels[i]);
                    this.axisBreadth = Math.max(this.label.getRHeight(g), this.axisBreadth);
                }
                if (!this.title.isNull()) {
                    this.axisBreadth += this.title.getRHeight(g);
                }
            }
            else {
                for (int i = 0; i < this.labels.length; ++i) {
                    this.label.setText(" " + this.labels[i]);
                    this.axisBreadth = Math.max(this.label.getRWidth(g), this.axisBreadth);
                }
                if (!this.title.isNull()) {
                    this.axisBreadth += this.title.getRWidth(g);
                }
            }
        }
        return this.axisBreadth = Math.max(this.axisBreadth, minBreadth);
    }
    
    protected void positionAxis(final int x, final int y, final int axisLen) {
        if (this.isHorizontal()) {
            this.startPositionAlongAxis = x;
            this.startPositionAcrossAxis = y;
        }
        else {
            this.startPositionAlongAxis = y;
            this.startPositionAcrossAxis = x;
        }
    }
    
    public void drawAxis(final Graphics g, final Component g2d, final int x, final int y, final int axisLen, int gridLength) {
        if (axisLen == 0) {
            return;
        }
        if (this.axisBreadth == 0) {
            this.axisBreadth = this.calculateBreadth(g, 0);
        }
        this.positionAxis(x, y, axisLen);
        this.title.setDrawingComponent(g2d);
        this.label.setDrawingComponent(g2d);
        this.label.setLinePadding(-2);
        final double minor_step = this.labelGenerator.getIncrement() / (this.minor_tic_count + 1);
        final int acrossAxisPos = this.startPositionAcrossAxis;
        int majorTickLength = this.major_tic_size;
        int minorTickLength = this.minor_tic_size;
        if (this.isHorizontal() == this.isTextOnDefaultSide()) {
            gridLength *= -1;
            majorTickLength *= -1;
            minorTickLength *= -1;
        }
        final Color c = g.getColor();
        if (this.axiscolor != null) {
            g.setColor(this.axiscolor);
        }
        this.drawLineAlongAxis(g, this.startPositionAlongAxis, this.startPositionAcrossAxis, this.getAxisLength());
        double val = this.labelGenerator.getStartValue();
        for (int i = 0; i < this.labels.length; ++i) {
            if (val >= this.getMinimumValue() && val <= this.getMaximumValue()) {
                final int alongAxisPos = this.getLocationFromValue(val);
                if (Math.abs(val) <= 1.0E-4 && this.drawzero) {
                    if (this.zerocolor != null) {
                        g.setColor(this.zerocolor);
                    }
                    this.drawLineAcrossAxis(g, alongAxisPos, acrossAxisPos, gridLength);
                }
                else if (this.drawgrid && val != this.getMinimumValue() && val != this.getMaximumValue()) {
                    if (this.gridcolor != null) {
                        g.setColor(this.gridcolor);
                    }
                    this.drawLineAcrossAxis(g, alongAxisPos, acrossAxisPos, gridLength);
                }
                if (this.axiscolor != null) {
                    g.setColor(this.axiscolor);
                }
                this.drawLineAcrossAxis(g, alongAxisPos, acrossAxisPos, majorTickLength);
                if (this.axisLabelEnabled) {
                    this.drawAxisLabel(g, alongAxisPos, acrossAxisPos, this.labels[i]);
                }
            }
            double minor = val + minor_step;
            for (int j = 0; j < this.minor_tic_count; ++j) {
                if (minor >= this.getMinimumValue() && minor <= this.getMaximumValue()) {
                    final int alongAxisPos = this.getLocationFromValue(minor);
                    if (this.drawgrid) {
                        if (this.gridcolor != null) {
                            g.setColor(this.gridcolor);
                        }
                        this.drawLineAcrossAxis(g, alongAxisPos, acrossAxisPos, gridLength);
                    }
                    if (this.axiscolor != null) {
                        g.setColor(this.axiscolor);
                    }
                    this.drawLineAcrossAxis(g, alongAxisPos, acrossAxisPos, minorTickLength);
                }
                minor += minor_step;
            }
            val += this.labelGenerator.getIncrement();
        }
        g.setColor(c);
        if (!this.title.isNull()) {
            this.drawTitle(g);
        }
    }
    
    public double getMinimumValue() {
        return this.range.getValueMin();
    }
    
    public double getMaximumValue() {
        return this.range.getValueMax();
    }
    
    public void setAllColors(final Color axis, final Color grid, final Color labelColor, final Color titleColor) {
        this.axiscolor = axis;
        this.gridcolor = grid;
        this.label.setColor(labelColor);
        this.title.setColor(titleColor);
    }
    
    public void setTitleText(final String s) {
        if (s != null && !s.equals("")) {
            this.title.setText(s);
        }
    }
    
    public TextLine getTitle() {
        return this.title;
    }
    
    public TextLine getLabel() {
        return this.label;
    }
    
    public void setLabelFont(final Font f) {
        this.label.setFont(f);
    }
    
    protected void drawLineAcrossAxis(final Graphics g, final int alongAxis, final int acrossAxis, final int lengthOfLine) {
        if (this.isHorizontal()) {
            g.drawLine(alongAxis, acrossAxis, alongAxis, acrossAxis + lengthOfLine);
        }
        else {
            g.drawLine(acrossAxis, alongAxis, acrossAxis + lengthOfLine, alongAxis);
        }
    }
    
    protected void drawLineAlongAxis(final Graphics g, final int alongAxis, final int acrossAxis, final int lengthOfLine) {
        if (this.isHorizontal()) {
            g.drawLine(alongAxis, acrossAxis, alongAxis + lengthOfLine, acrossAxis);
        }
        else {
            g.drawLine(acrossAxis, alongAxis, acrossAxis, alongAxis + lengthOfLine);
        }
    }
    
    protected void drawAxisLabel(final Graphics g, final int alongAxis, final int acrossAxis, final String text) {
        this.label.setText(text);
        if (this.isHorizontal()) {
            if (this.isTextOnDefaultSide()) {
                this.label.draw(g, alongAxis, acrossAxis + (this.label.getLeading(g) + this.label.getAscent(g) + 1), 0);
            }
            else {
                this.label.draw(g, alongAxis, acrossAxis + (-this.label.getLeading(g) - this.label.getDescent(g) - 1), 0);
            }
        }
        else if (this.isTextOnDefaultSide()) {
            this.label.draw(g, acrossAxis, alongAxis + this.label.getAscent(g) / 2 - 1, 2);
        }
        else {
            this.label.draw(g, acrossAxis, alongAxis + this.label.getAscent(g) / 2, 1);
        }
    }
    
    protected void drawTitle(final Graphics g) {
        if (this.title == null || this.title.getText().length() == 0) {
            return;
        }
        int posAlongAxis = this.startPositionAlongAxis + this.getAxisLength() / 2;
        final int widthoftext = this.title.getWidth(g);
        if (this.isHorizontal()) {
            if (this.isTextOnDefaultSide()) {
                if (widthoftext >= g.getClipBounds().width) {
                    this.title.setJustification(1);
                    posAlongAxis = this.startPositionAlongAxis;
                }
                else {
                    this.title.setJustification(0);
                }
                this.title.draw(g, posAlongAxis, this.startPositionAcrossAxis + this.label.getLeading(g) + this.label.getAscent(g) + this.title.getLeading(g) + this.title.getAscent(g));
            }
            else {
                if (widthoftext >= g.getClipBounds().width) {
                    this.title.setJustification(1);
                    posAlongAxis = this.startPositionAlongAxis;
                }
                else {
                    this.title.setJustification(0);
                }
                this.title.draw(g, posAlongAxis, this.startPositionAcrossAxis - this.label.getLeading(g) - this.label.getDescent(g) - this.title.getLeading(g) - this.title.getDescent(g), 0);
            }
        }
        else if (this.isTextOnDefaultSide()) {
            if (this.title.getRotation() == 0 || this.title.getRotation() == 180) {
                this.title.draw(g, this.startPositionAcrossAxis - this.axisBreadth + this.title.getRWidth(g), posAlongAxis, 2);
            }
            else {
                if (widthoftext >= g.getClipBounds().height) {
                    this.title.setJustification(1);
                    posAlongAxis = this.getAxisLength() + this.startPositionAlongAxis;
                }
                else {
                    this.title.setJustification(0);
                }
                this.title.draw(g, this.startPositionAcrossAxis - this.axisBreadth + this.title.getRWidth(g), posAlongAxis);
            }
        }
        else if (this.title.getRotation() == 0 || this.title.getRotation() == 180) {
            this.title.draw(g, this.startPositionAcrossAxis + this.axisBreadth, posAlongAxis, 1);
        }
        else {
            if (widthoftext >= g.getClipBounds().height) {
                this.title.setJustification(1);
                posAlongAxis = this.getAxisLength() + this.startPositionAlongAxis;
            }
            else {
                this.title.setJustification(0);
            }
            this.title.draw(g, this.startPositionAcrossAxis + this.axisBreadth - this.title.getLeftEdge(g), posAlongAxis);
        }
    }
    
    protected int getAxisLength() {
        return this.range.getPixelRange();
    }
    
    public int getBreadth() {
        return this.axisBreadth;
    }
    
    protected void calculateGridLabels() {
        if ((this.labels != null && this.labelGenerator.getStartValue() == this.getMinimumValue() && this.labelGenerator.getEndValue() == this.getMaximumValue()) || this.labelGenerator instanceof FixedLabelGenerator) {
            return;
        }
        this.labels = this.labelGenerator.generateLabels(this.getMinimumValue(), this.getMaximumValue());
    }
    
    public void setFixedAxisLabels(final String[] labels, final double labelStartValue, final double labelIncrement) {
        this.labelGenerator = new FixedLabelGenerator(labels, labelStartValue, labelIncrement);
        this.labels = this.labelGenerator.generateLabels(this.getMaximumValue(), this.getMaximumValue());
        this.getRange().setValues(this.labelGenerator.getStartValue(), this.labelGenerator.getEndValue());
        this.getRange().setValueLocked(true);
    }
    
    public void setMinorTicCount(final int count) {
        this.minor_tic_count = count;
    }
    
    public void forceEndLabels(final boolean force) {
        this.forceEndLabels = force;
    }
    
    public void drawGrid(final boolean draw) {
        this.drawgrid = draw;
    }
    
    public boolean isDrawingGrid() {
        return this.drawgrid;
    }
}
