// 
// Decompiled by Procyon v0.5.30
// 

package com.fortunengine.chart;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.text.DecimalFormat;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.FontMetrics;
import java.awt.Color;
import java.util.Vector;
import java.awt.Canvas;

public class ChartPanel extends Canvas
{
    public static final int CHARTTYPE_BAR = 1;
    public static final int CHARTTYPE_LINE = 2;
    public static final int XCOORDFORMAT_INTRADAY = 1;
    public static final int XCOORDFORMAT_DAY = 2;
    public static final int XCOORDFORMAT_WEEK = 3;
    public static final int XCOORDFORMAT_MONTH = 4;
    public static final int XCOORDFORMAT_SESSON = 5;
    public static final int XCOORDFORMAT_HALFYEAR = 6;
    public static final int XCOORDFORMAT_YEAR = 7;
    private int chartType;
    private int dataPieces;
    private int XCoordFormat;
    private Vector XCoord;
    private Vector SDateData;
    private String chartLabel;
    private int chartDataCount;
    private String YCoordFormat;
    private String labelFormat;
    private Vector chartData;
    private Vector chartDataLabel;
    private Vector chartDataUnit;
    private Vector maxValue;
    private Vector minValue;
    private int chartDataCount_R;
    private String YCoordFormat_R;
    private String labelFormat_R;
    private Vector chartData_R;
    private Vector chartDataLabel_R;
    private Vector chartDataUnit_R;
    private Vector maxValue_R;
    private Vector minValue_R;
    private int cursorPosition;
    private Color[] lineXColor;
    private Color[] barColor;
    private Color ruler;
    private boolean drawCursor;
    private boolean drawSecCoord;
    private boolean showTimeLabel;
    private boolean showTimeMark;
    private Coordinate coord;
    private int t;
    private int b;
    private int l;
    private int r;
    private int w;
    private int h;
    private int unit_total;
    private int unit_width;
    private int diff;
    private FontMetrics fm;
    
    public ChartPanel() {
        this.chartType = 0;
        this.dataPieces = 4;
        this.XCoordFormat = 2;
        this.XCoord = null;
        this.SDateData = null;
        this.chartLabel = null;
        this.chartDataCount = 0;
        this.YCoordFormat = "";
        this.labelFormat = null;
        this.chartData = new Vector();
        this.chartDataLabel = new Vector();
        this.chartDataUnit = new Vector();
        this.maxValue = new Vector();
        this.minValue = new Vector();
        this.chartDataCount_R = 0;
        this.YCoordFormat_R = "";
        this.labelFormat_R = null;
        this.chartData_R = new Vector();
        this.chartDataLabel_R = new Vector();
        this.chartDataUnit_R = new Vector();
        this.maxValue_R = new Vector();
        this.minValue_R = new Vector();
        this.cursorPosition = -1;
        this.lineXColor = new Color[10];
        this.barColor = new Color[2];
        this.ruler = new Color(224, 224, 224);
        this.drawCursor = false;
        this.drawSecCoord = false;
        this.showTimeLabel = true;
        this.showTimeMark = true;
        this.coord = new Coordinate();
        this.t = 0;
        this.b = 0;
        this.l = 0;
        this.r = 0;
        this.w = 0;
        this.h = 0;
        this.unit_total = 1;
        this.unit_width = 1;
        this.diff = 1;
        this.fm = null;
        this.initChart();
    }
    
    public ChartPanel(final int n, final int n2) {
        this.chartType = 0;
        this.dataPieces = 4;
        this.XCoordFormat = 2;
        this.XCoord = null;
        this.SDateData = null;
        this.chartLabel = null;
        this.chartDataCount = 0;
        this.YCoordFormat = "";
        this.labelFormat = null;
        this.chartData = new Vector();
        this.chartDataLabel = new Vector();
        this.chartDataUnit = new Vector();
        this.maxValue = new Vector();
        this.minValue = new Vector();
        this.chartDataCount_R = 0;
        this.YCoordFormat_R = "";
        this.labelFormat_R = null;
        this.chartData_R = new Vector();
        this.chartDataLabel_R = new Vector();
        this.chartDataUnit_R = new Vector();
        this.maxValue_R = new Vector();
        this.minValue_R = new Vector();
        this.cursorPosition = -1;
        this.lineXColor = new Color[10];
        this.barColor = new Color[2];
        this.ruler = new Color(224, 224, 224);
        this.drawCursor = false;
        this.drawSecCoord = false;
        this.showTimeLabel = true;
        this.showTimeMark = true;
        this.coord = new Coordinate();
        this.t = 0;
        this.b = 0;
        this.l = 0;
        this.r = 0;
        this.w = 0;
        this.h = 0;
        this.unit_total = 1;
        this.unit_width = 1;
        this.diff = 1;
        this.fm = null;
        this.setSize(n, n2);
        this.initChart();
    }
    
    public ChartPanel(final GraphicsConfiguration graphicsConfiguration) {
        super(graphicsConfiguration);
        this.chartType = 0;
        this.dataPieces = 4;
        this.XCoordFormat = 2;
        this.XCoord = null;
        this.SDateData = null;
        this.chartLabel = null;
        this.chartDataCount = 0;
        this.YCoordFormat = "";
        this.labelFormat = null;
        this.chartData = new Vector();
        this.chartDataLabel = new Vector();
        this.chartDataUnit = new Vector();
        this.maxValue = new Vector();
        this.minValue = new Vector();
        this.chartDataCount_R = 0;
        this.YCoordFormat_R = "";
        this.labelFormat_R = null;
        this.chartData_R = new Vector();
        this.chartDataLabel_R = new Vector();
        this.chartDataUnit_R = new Vector();
        this.maxValue_R = new Vector();
        this.minValue_R = new Vector();
        this.cursorPosition = -1;
        this.lineXColor = new Color[10];
        this.barColor = new Color[2];
        this.ruler = new Color(224, 224, 224);
        this.drawCursor = false;
        this.drawSecCoord = false;
        this.showTimeLabel = true;
        this.showTimeMark = true;
        this.coord = new Coordinate();
        this.t = 0;
        this.b = 0;
        this.l = 0;
        this.r = 0;
        this.w = 0;
        this.h = 0;
        this.unit_total = 1;
        this.unit_width = 1;
        this.diff = 1;
        this.fm = null;
        this.initChart();
    }
    
    public void setLineColor(final int n, final Color color) {
        this.lineXColor[n] = color;
        this.repaint();
    }
    
    public void clearChartData() {
        this.chartDataCount = 0;
        this.YCoordFormat = "";
        this.chartData = new Vector();
        this.chartDataLabel = new Vector();
        this.chartDataUnit = new Vector();
        this.maxValue = new Vector();
        this.minValue = new Vector();
        this.unit_total = 1;
        this.chartDataCount_R = 0;
        this.YCoordFormat_R = "";
        this.chartData_R = new Vector();
        this.chartDataLabel_R = new Vector();
        this.chartDataUnit_R = new Vector();
        this.maxValue_R = new Vector();
        this.minValue_R = new Vector();
        this.repaint();
    }
    
    public void addChartData(final Vector vector, final String s, final String s2) {
        this.chartDataLabel.addElement(s);
        this.chartDataUnit.addElement(s2);
        this.chartData.addElement(vector);
        ++this.chartDataCount;
        if (vector.size() > this.unit_total) {
            this.unit_total = vector.size();
        }
        Double n = vector.elementAt(0);
        Double n2 = vector.elementAt(0);
        try {
            int n3 = 0;
            while (true) {
                ++n3;
                if (n < vector.elementAt(n3)) {
                    n = vector.elementAt(n3);
                }
                else {
                    if (n2 <= vector.elementAt(n3)) {
                        continue;
                    }
                    n2 = vector.elementAt(n3);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.maxValue.addElement(n);
            this.minValue.addElement(n2);
            this.repaint();
        }
    }
    
    public void addChartData_R(final Vector vector, final String s, final String s2) {
        this.chartDataLabel_R.addElement(s);
        this.chartDataUnit_R.addElement(s2);
        this.chartData_R.addElement(vector);
        ++this.chartDataCount_R;
        if (vector.size() > this.unit_total) {
            this.unit_total = vector.size();
        }
        Double n = vector.elementAt(0);
        Double n2 = vector.elementAt(0);
        try {
            int n3 = 0;
            while (true) {
                ++n3;
                if (n < vector.elementAt(n3)) {
                    n = vector.elementAt(n3);
                }
                else {
                    if (n2 <= vector.elementAt(n3)) {
                        continue;
                    }
                    n2 = vector.elementAt(n3);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.maxValue_R.addElement(n);
            this.minValue_R.addElement(n2);
            this.repaint();
        }
    }
    
    public void changeCursorPosition(final int cursorPosition) {
        this.cursorPosition = cursorPosition;
        this.drawCursor = true;
        if (cursorPosition < this.l) {
            this.cursorPosition = this.l;
        }
        if (cursorPosition > this.r) {
            this.cursorPosition = this.r;
        }
        this.repaint();
    }
    
    public void changeCursorPosition(final boolean b) {
        int n;
        if (!this.drawCursor) {
            n = this.unit_total - 1;
            this.drawCursor = true;
        }
        else {
            n = (this.cursorPosition - this.l) / (this.unit_width + this.diff);
            if (!b) {
                --n;
            }
            else if (b) {
                ++n;
            }
        }
        this.cursorPosition = n * (this.unit_width + this.diff) + this.l;
        this.repaint();
    }
    
    private void drawBARChart(final Graphics graphics) {
        this.coord.adjustCoordinate(this.getMaxValue(0), this.getMinValue(0));
        final double newMaxValue = this.coord.getNewMaxValue();
        final double newMinValue = this.coord.getNewMinValue();
        final double n = (newMaxValue - newMinValue) / (this.h - 1);
        graphics.setColor(this.lineXColor[0]);
        int i = this.unit_total - 1;
        int n2 = this.l + this.unit_width / 2;
        double n3 = 0.0;
        if (newMinValue > 0.0) {
            n3 = newMinValue;
        }
        else if (newMaxValue < 0.0) {
            n3 = newMaxValue;
        }
        final int n4 = this.b - (int)(Object)new Double((n3 - newMinValue) / n);
        final Vector<Double> vector = this.chartData.elementAt(0);
        while (i >= 0) {
            if (vector.elementAt(i) > -9.999999999999E9) {
                final double doubleValue = vector.elementAt(i--);
                final int n5 = this.b - (int)(Object)new Double((doubleValue - newMinValue) / n);
                if (doubleValue >= 0.0) {
                    graphics.setColor(this.barColor[0]);
                    graphics.fillRect(n2, n5, this.unit_width + this.diff - 1, n4 - n5);
                }
                else {
                    graphics.setColor(this.barColor[1]);
                    graphics.fillRect(n2, n4, this.unit_width + this.diff - 1, n5 - n4 - 1);
                }
            }
            n2 = n2 + this.unit_width + this.diff;
        }
        final DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern(this.labelFormat);
        final int n6 = this.t + this.fm.getHeight() / 2 - 2;
        graphics.setColor(Color.black);
        final String format = decimalFormat.format(newMaxValue);
        graphics.drawString(format, this.l - this.fm.stringWidth(format) - 4, n6);
        for (int j = 1; j < this.dataPieces; ++j) {
            final int n7 = this.t + this.h / this.dataPieces * j + this.fm.getHeight() / 2;
            final String format2 = decimalFormat.format(newMaxValue - j * ((newMaxValue - newMinValue) / this.dataPieces));
            graphics.drawString(format2, this.l - this.fm.stringWidth(format2) - 4, n7);
        }
        final int n8 = this.b + this.fm.getHeight() / 2 - 2;
        final String format3 = decimalFormat.format(newMinValue);
        graphics.drawString(format3, this.l - this.fm.stringWidth(format3) - 4, n8);
    }
    
    private void drawLINEChart(final Graphics graphics) {
        int n = 0;
        double n2 = this.getMaxValue(n);
        double n3 = this.getMinValue(n);
        try {
            while (true) {
                ++n;
                if (n2 < this.getMaxValue(n)) {
                    n2 = this.getMaxValue(n);
                }
                if (n3 > this.getMinValue(n)) {
                    n3 = this.getMinValue(n);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.coord.adjustCoordinate(n2, n3);
            final double newMaxValue = this.coord.getNewMaxValue();
            final double newMinValue = this.coord.getNewMinValue();
            int n4 = 0;
            try {
                final double n5 = (newMaxValue - newMinValue) / (this.h - 1);
                while (true) {
                    final Vector<Double> vector = this.chartData.elementAt(n4);
                    graphics.setColor(this.lineXColor[n4]);
                    int i = this.unit_total - 1;
                    int n6 = this.l + this.unit_width / 2;
                    int n7 = this.b - (int)(Object)new Double((vector.elementAt(i) - newMinValue) / n5);
                    int n8 = 0;
                    while (i > 0) {
                        final int n9 = n6 + this.unit_width + this.diff;
                        if (vector.elementAt(i) > -9.99999999999E9) {
                            n8 = this.b - (int)(Object)new Double((vector.elementAt(--i) - newMinValue) / n5);
                            graphics.drawLine(n6, n7, n9, n8);
                        }
                        n6 = n9;
                        n7 = n8;
                    }
                    ++n4;
                }
            }
            catch (ArrayIndexOutOfBoundsException ex2) {
                final DecimalFormat decimalFormat = new DecimalFormat();
                decimalFormat.applyPattern(this.labelFormat);
                final int n10 = this.t + this.fm.getHeight() / 2 - 2;
                graphics.setColor(Color.black);
                final String format = decimalFormat.format(newMaxValue);
                graphics.drawString(format, this.l - this.fm.stringWidth(format) - 4, n10);
                for (int j = 1; j < this.dataPieces; ++j) {
                    final int n11 = this.t + this.h / this.dataPieces * j + 4;
                    final String format2 = decimalFormat.format(newMaxValue - j * ((newMaxValue - newMinValue) / this.dataPieces));
                    graphics.drawString(format2, this.l - this.fm.stringWidth(format2) - 4, n11);
                }
                final int n12 = this.b + this.fm.getHeight() / 2 - 2;
                final String format3 = decimalFormat.format(newMinValue);
                graphics.drawString(format3, this.l - this.fm.stringWidth(format3) - 4, n12);
            }
        }
    }
    
    private void drawLINEChart_R(final Graphics graphics) {
        int n = 0;
        double n2 = this.getMaxValue_R(n);
        double n3 = this.getMinValue_R(n);
        try {
            while (true) {
                ++n;
                if (n2 < this.getMaxValue_R(n)) {
                    n2 = this.getMaxValue_R(n);
                }
                if (n3 > this.getMinValue_R(n)) {
                    n3 = this.getMinValue_R(n);
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            this.coord.adjustCoordinate(n2, n3);
            final double newMaxValue = this.coord.getNewMaxValue();
            final double newMinValue = this.coord.getNewMinValue();
            int n4 = 0;
            try {
                final double n5 = (newMaxValue - newMinValue) / (this.h - 1);
                while (true) {
                    final Vector<Double> vector = this.chartData_R.elementAt(n4);
                    graphics.setColor(this.lineXColor[this.chartDataCount + n4]);
                    int i = this.unit_total - 1;
                    int n6 = this.l + this.unit_width / 2;
                    int n7 = this.b - (int)(Object)new Double((vector.elementAt(i) - newMinValue) / n5);
                    int n8 = 0;
                    while (i > 0) {
                        final int n9 = n6 + this.unit_width + this.diff;
                        if (vector.elementAt(i) > -9.99999999999E9) {
                            n8 = this.b - (int)(Object)new Double((vector.elementAt(--i) - newMinValue) / n5);
                            graphics.drawLine(n6, n7, n9, n8);
                        }
                        n6 = n9;
                        n7 = n8;
                    }
                    ++n4;
                }
            }
            catch (ArrayIndexOutOfBoundsException ex2) {
                final DecimalFormat decimalFormat = new DecimalFormat();
                decimalFormat.applyPattern(this.labelFormat_R);
                final int n10 = this.t + this.fm.getHeight() / 2 - 2;
                graphics.setColor(Color.black);
                final String format = decimalFormat.format(newMaxValue);
                graphics.drawString(format, this.r + 4 + this.fm.stringWidth(this.labelFormat_R) - this.fm.stringWidth(format), n10);
                for (int j = 1; j < this.dataPieces; ++j) {
                    final int n11 = this.t + this.h / this.dataPieces * j + 4;
                    final String format2 = decimalFormat.format(newMaxValue - j * ((newMaxValue - newMinValue) / this.dataPieces));
                    graphics.drawString(format2, this.r + 4 + this.fm.stringWidth(this.labelFormat_R) - this.fm.stringWidth(format2), n11);
                }
                final int n12 = this.b + this.fm.getHeight() / 2 - 2;
                final String format3 = decimalFormat.format(newMinValue);
                graphics.drawString(format3, this.r + 4 + this.fm.stringWidth(this.labelFormat_R) - this.fm.stringWidth(format3), n12);
            }
        }
    }
    
    private void drawRuler(final Graphics graphics, final boolean b) {
        char c = '/';
        int i = this.unit_total - 1;
        String s = this.SDateData.elementAt(i);
        int n = this.l + this.unit_width / 2;
        while (i >= 0) {
            int n2 = 0;
            String s2 = null;
            int n3 = 0;
            Label_0226: {
                if (this.XCoordFormat == 1) {
                    n2 = Integer.parseInt(s.substring(8, 10));
                    try {
                        s2 = this.SDateData.elementAt(--i);
                        n3 = Integer.parseInt(s2.substring(8, 10));
                        break Label_0226;
                    }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        return;
                    }
                }
                if (this.XCoordFormat == 4) {
                    n2 = Integer.parseInt(s.substring(2, 4));
                    try {
                        s2 = this.SDateData.elementAt(--i);
                        n3 = Integer.parseInt(s2.substring(2, 4));
                        break Label_0226;
                    }
                    catch (ArrayIndexOutOfBoundsException ex2) {
                        return;
                    }
                }
                n2 = Integer.parseInt(s.substring(2, 4));
                try {
                    s2 = this.SDateData.elementAt(--i);
                    n3 = Integer.parseInt(s2.substring(2, 4));
                }
                catch (ArrayIndexOutOfBoundsException ex3) {
                    return;
                }
            }
            boolean b2;
            if (this.XCoordFormat == 1) {
                c = ':';
                b2 = (n2 % 30 == 0);
            }
            else {
                int n4 = 1;
                switch (this.XCoordFormat) {
                    case 2: {
                        if (this.unit_total > 144) {
                            n4 = 3;
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if (this.unit_total > 144) {
                            n4 = 6;
                            break;
                        }
                        n4 = 3;
                        break;
                    }
                    case 4: {
                        if (this.unit_total <= 20) {
                            n4 = 3;
                            break;
                        }
                        n4 = 12;
                        break;
                    }
                }
                b2 = (n2 != n3 && n2 % n4 == 0);
            }
            if (b2) {
                if (n != this.l) {
                    graphics.setColor(this.ruler);
                    if (this.XCoordFormat == 1) {
                        graphics.drawLine(n, this.t + 1, n, this.b - 1);
                    }
                    else {
                        graphics.drawLine(n + this.unit_width + this.diff, this.t + 1, n + this.unit_width + this.diff, this.b - 1);
                    }
                }
                if (b && n != this.l && this.showTimeMark) {
                    graphics.setColor(Color.black);
                    String s3;
                    if (this.XCoordFormat == 1) {
                        s3 = String.valueOf(s.substring(6, 8)) + c + s.substring(8, 10);
                    }
                    else if (this.XCoordFormat == 4) {
                        s3 = "'" + s2.substring(0, 2) + c + s2.substring(2, 4);
                    }
                    else {
                        s3 = String.valueOf(s2.substring(2, 4)) + c + s2.substring(4, 6);
                    }
                    if (this.XCoordFormat == 1) {
                        graphics.drawString(s3, n - this.fm.stringWidth(s3) / 2, this.b + this.fm.getHeight());
                    }
                    else {
                        graphics.drawString(s3, n + this.unit_width + this.diff - this.fm.stringWidth(s3) / 2, this.b + this.fm.getHeight());
                    }
                }
            }
            s = s2;
            n = n + this.unit_width + this.diff;
        }
    }
    
    private double getMaxValue(final int n) {
        return this.maxValue.elementAt(n);
    }
    
    private double getMaxValue_R(final int n) {
        return this.maxValue_R.elementAt(n);
    }
    
    private double getMinValue(final int n) {
        return this.minValue.elementAt(n);
    }
    
    private double getMinValue_R(final int n) {
        return this.minValue_R.elementAt(n);
    }
    
    void initChart() {
        this.lineXColor[0] = new Color(16, 128, 175);
        this.lineXColor[1] = new Color(144, 0, 192);
        this.lineXColor[2] = new Color(0, 0, 255);
        this.lineXColor[3] = new Color(110, 0, 59);
        this.lineXColor[4] = new Color(255, 0, 0);
        this.lineXColor[5] = new Color(125, 198, 34);
        this.lineXColor[6] = new Color(160, 104, 0);
        this.lineXColor[7] = new Color(0, 178, 235);
        this.lineXColor[8] = new Color(0, 78, 75);
        this.lineXColor[9] = new Color(231, 101, 26);
        this.barColor[0] = new Color(225, 0, 87);
        this.barColor[1] = new Color(0, 152, 255);
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void setChartLabel(final String chartLabel) {
        this.chartLabel = chartLabel;
    }
    
    public void setChartType(final int chartType) {
        this.chartType = chartType;
        this.repaint();
    }
    
    public void setDataPieces(final int n) {
        this.dataPieces = n;
        this.coord.setDataPieces(n);
    }
    
    public void setDisplayTimeLabel(final boolean showTimeLabel) {
        this.showTimeLabel = showTimeLabel;
    }
    
    public void setDisplayTimeMark(final boolean showTimeMark) {
        this.showTimeMark = showTimeMark;
    }
    
    public void setLabelFormat(final String labelFormat) {
        this.labelFormat = labelFormat;
        this.repaint();
    }
    
    public void setLabelFormat_R(final String labelFormat_R) {
        if (labelFormat_R == null) {
            this.drawSecCoord = false;
        }
        else {
            this.drawSecCoord = true;
        }
        this.labelFormat_R = labelFormat_R;
        this.repaint();
    }
    
    public void setSDate(final Vector sDateData) {
        this.SDateData = sDateData;
        this.repaint();
    }
    
    public void setXCoordFormat(final int xCoordFormat) {
        this.XCoordFormat = xCoordFormat;
        this.repaint();
    }
    
    public void setYCoordFormat(final String yCoordFormat) {
        this.YCoordFormat = yCoordFormat;
        this.repaint();
    }
    
    public void setYCoordFormat_R(final String yCoordFormat_R) {
        this.YCoordFormat_R = yCoordFormat_R;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.fm = graphics.getFontMetrics();
        this.b = this.getBounds().height - this.fm.getHeight() / 2;
        double n;
        if (this.showTimeMark) {
            this.b -= this.fm.getHeight();
            n = 2.5;
        }
        else {
            n = 1.5;
        }
        if (this.chartLabel != null) {
            ++n;
        }
        final int intValue = (int)(Object)new Double((this.getBounds().height - this.fm.getHeight() * n) / (this.dataPieces - 1));
        this.t = this.b - intValue * (this.dataPieces - 1);
        this.l = this.fm.stringWidth(this.labelFormat) + 4;
        if (this.drawSecCoord) {
            this.r = this.getBounds().width - 1 - this.fm.stringWidth(this.labelFormat_R) - 4;
        }
        else {
            this.r = this.getBounds().width - 1;
        }
        this.w = this.r - this.l + 1;
        this.h = intValue * (this.dataPieces - 1) + 1;
        final int n2 = this.w / this.unit_total;
        if (n2 >= 10) {
            this.unit_width = 7;
        }
        if (n2 >= 5) {
            this.unit_width = 5;
        }
        else if (n2 >= 3) {
            this.unit_width = 3;
        }
        else {
            this.unit_width = 1;
        }
        this.diff = n2 - this.unit_width;
        final Image image = this.createImage(this.getBounds().width, this.getBounds().height);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setColor(new Color(240, 240, 240));
        graphics2.fillRect(this.l, this.t, this.w - 1, this.h - 1);
        graphics2.setColor(Color.black);
        graphics2.drawRect(this.l, this.t, this.w - 1, this.h - 1);
        graphics2.setColor(this.ruler);
        for (int i = 1; i < this.dataPieces; ++i) {
            final int n3 = this.b - this.h / this.dataPieces * i;
            graphics2.drawLine(this.l + 1, n3, this.r - 1, n3);
        }
        try {
            this.drawRuler(graphics2, true);
            if (this.chartData.size() > 0) {
                switch (this.chartType) {
                    case 1: {
                        this.drawBARChart(graphics2);
                        break;
                    }
                    case 2: {
                        this.drawLINEChart(graphics2);
                        if (this.chartData_R.size() > 0) {
                            this.drawLINEChart_R(graphics2);
                            break;
                        }
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex);
        }
        Label_1867: {
            try {
                if (this.chartData.elementAt(0) != null) {
                    final DecimalFormat decimalFormat = new DecimalFormat();
                    decimalFormat.applyPattern(this.labelFormat);
                    final int n4 = this.r - 8;
                    String s = null;
                    int n5 = 0;
                    if (this.drawCursor) {
                        graphics2.setColor(new Color(176, 176, 176));
                        graphics2.drawLine(this.cursorPosition, this.t + 1, this.cursorPosition, this.b - 1);
                        n5 = this.unit_total - (this.cursorPosition - this.l) / n2 - 1;
                        if (n5 < 0) {
                            n5 = 0;
                        }
                    }
                    graphics2.setColor(Color.black);
                    if (this.chartLabel != null) {
                        graphics2.drawString(this.chartLabel, (this.w - this.fm.stringWidth(this.chartLabel)) / 2, this.fm.getHeight() - 2);
                    }
                    int n6 = this.fm.getHeight() - 2;
                    if (this.chartLabel != null) {
                        n6 += this.fm.getHeight();
                    }
                    if (this.showTimeLabel) {
                        try {
                            if (this.XCoordFormat == 1) {
                                s = "\u6642\u9593 ";
                                if (((String)this.SDateData.elementAt(n5)).substring(6, 10).indexOf("9999") == 0) {
                                    s = String.valueOf(s) + ((String)this.SDateData.elementAt(n5)).substring(0, 2) + "/" + ((String)this.SDateData.elementAt(n5)).substring(2, 4) + "/" + ((String)this.SDateData.elementAt(n5)).substring(4, 6) + " " + "\u6536\u76e4 ";
                                }
                                else {
                                    s = String.valueOf(s) + ((String)this.SDateData.elementAt(n5)).substring(0, 2) + "/" + ((String)this.SDateData.elementAt(n5)).substring(2, 4) + "/" + ((String)this.SDateData.elementAt(n5)).substring(4, 6) + " " + ((String)this.SDateData.elementAt(n5)).substring(6, 8) + ":" + ((String)this.SDateData.elementAt(n5)).substring(8, 10);
                                }
                            }
                            else if (this.XCoordFormat == 4) {
                                s = "\u65e5\u671f ";
                                s = String.valueOf(s) + ((String)this.SDateData.elementAt(n5)).substring(0, 2) + "/" + ((String)this.SDateData.elementAt(n5)).substring(2, 4);
                            }
                            else {
                                s = "\u65e5\u671f ";
                                s = String.valueOf(s) + ((String)this.SDateData.elementAt(n5)).substring(0, 2) + "/" + ((String)this.SDateData.elementAt(n5)).substring(2, 4) + "/" + ((String)this.SDateData.elementAt(n5)).substring(4, 6);
                            }
                            graphics2.drawString(s, n4 - this.fm.stringWidth(s), n6);
                        }
                        catch (ArrayIndexOutOfBoundsException ex2) {}
                    }
                    int n7 = this.l + 8;
                    final int n8 = (this.w - ((s != null) ? this.fm.stringWidth(s) : 0) - 16) / 4;
                    int n9 = 0;
                    try {
                        if (this.chartType == 2) {
                            while (true) {
                                graphics2.setColor(this.lineXColor[n9]);
                                final String s2 = this.chartDataLabel.elementAt(n9);
                                graphics2.drawString(s2, n7, n6);
                                String s3 = decimalFormat.format((double)this.chartData.elementAt(n9).elementAt(n5));
                                if (this.chartDataUnit.elementAt(n9) != null) {
                                    s3 = String.valueOf(s3) + this.chartDataUnit.elementAt(n9);
                                }
                                graphics2.drawString(s3, n7 + this.fm.stringWidth(s2), n6);
                                n7 += n8;
                                ++n9;
                            }
                        }
                        else if (this.chartType == 1) {
                            final String s4 = this.chartDataLabel.elementAt(n9);
                            final double doubleValue = this.chartData.elementAt(n9).elementAt(n5);
                            if (doubleValue >= 0.0) {
                                graphics2.setColor(this.barColor[0]);
                            }
                            else {
                                graphics2.setColor(this.barColor[1]);
                            }
                            graphics2.drawString(s4, n7, n6);
                            String s5 = decimalFormat.format(doubleValue);
                            if (this.chartDataUnit.elementAt(n9) != null) {
                                s5 = String.valueOf(s5) + this.chartDataUnit.elementAt(n9);
                            }
                            graphics2.drawString(s5, n7 + this.fm.stringWidth(s4), n6);
                            n7 += n8;
                            ++n9;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException ex3) {}
                    int n10 = 0;
                    try {
                        while (true) {
                            graphics2.setColor(this.lineXColor[n10 + this.chartDataCount]);
                            final String s6 = this.chartDataLabel_R.elementAt(n10);
                            graphics2.drawString(s6, n7, n6);
                            String s7 = decimalFormat.format((double)this.chartData_R.elementAt(n10).elementAt(n5));
                            if (this.chartDataUnit_R.elementAt(n10) != null) {
                                s7 = String.valueOf(s7) + this.chartDataUnit_R.elementAt(n10);
                            }
                            graphics2.drawString(s7, n7 + this.fm.stringWidth(s6), n6);
                            n7 += n8;
                            ++n10;
                        }
                    }
                    catch (ArrayIndexOutOfBoundsException ex4) {
                        break Label_1867;
                    }
                }
                graphics2.setColor(Color.black);
                graphics2.drawString("\u8cc7\u6599\u8f09\u5165\u4e2d, \u8acb\u7a0d\u5019...", 0, this.fm.getHeight() - 2);
            }
            catch (ArrayIndexOutOfBoundsException ex5) {}
        }
        graphics.drawImage(image, 0, 0, this);
    }
}
