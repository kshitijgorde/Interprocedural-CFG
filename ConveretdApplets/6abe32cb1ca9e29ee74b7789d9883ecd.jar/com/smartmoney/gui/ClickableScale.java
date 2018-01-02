// 
// Decompiled by Procyon v0.5.30
// 

package com.smartmoney.gui;

import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Point;
import java.awt.Font;
import java.util.Vector;
import java.awt.Color;
import com.smartmoney.util.BufferPanel;

public class ClickableScale extends BufferPanel implements Runnable
{
    public static final int DOLLARS = 2;
    public static final int PERCENT = 1;
    public static final int PLAIN = 0;
    public static final int PLAIN_PERC = 3;
    public static final int GRADE = 4;
    protected Color dotColor;
    protected Color naDotColor;
    protected Color leftMarginColor;
    protected Color rulerColor;
    protected Color connectorColor;
    protected Color coloredLabelColor;
    protected int formatType;
    protected String[] labels;
    protected Vector coloredLabels;
    protected String[] scaleNames;
    protected double[] nameCutoffs;
    protected double[] values;
    protected double[] newValues;
    protected double min;
    protected double max;
    protected double scaleLow;
    protected double scaleHigh;
    protected double bigInc;
    protected double smallInc;
    protected int yTop;
    protected int yBottom;
    protected int verticalMargin;
    protected boolean topIsHigh;
    protected int[] labelY;
    protected int[] newLabelY;
    protected int[] dotY;
    protected int[] newDotY;
    protected double time;
    protected Font numberFont;
    protected int rulerX;
    protected int bigTicR;
    protected int smallTicR;
    protected int labelX;
    protected int dotR;
    protected int smallTicDivisor;
    protected double bigTicDivisor;
    protected int labelHeight;
    protected boolean running;
    protected boolean mouseIsDown;
    protected Point mouse;
    protected int itemSelected;
    protected String emptyMessage;
    protected boolean clickable;
    private int lastMaxHeight;
    
    public ClickableScale() {
        this.dotColor = new Color(13369344);
        this.naDotColor = Color.gray;
        this.leftMarginColor = Color.black;
        this.rulerColor = Color.gray;
        this.connectorColor = Color.gray;
        this.coloredLabelColor = new Color(0, 153, 102);
        this.formatType = 0;
        this.verticalMargin = 20;
        this.topIsHigh = true;
        this.rulerX = 50;
        this.bigTicR = 10;
        this.smallTicR = 4;
        this.labelX = 100;
        this.dotR = 3;
        this.smallTicDivisor = 5;
        this.bigTicDivisor = 1.0;
        this.labelHeight = 15;
        this.running = false;
        this.mouse = new Point(-1, -1);
        this.itemSelected = -1;
        this.emptyMessage = "";
        this.clickable = true;
        this.lastMaxHeight = -1;
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
        this.yTop = this.verticalMargin;
        this.yBottom = n4 - this.verticalMargin;
    }
    
    public void setClickable(final boolean clickable) {
        this.clickable = clickable;
    }
    
    public void setScaleNames(final String[] scaleNames, final double[] nameCutoffs) {
        this.scaleNames = scaleNames;
        this.nameCutoffs = nameCutoffs;
        this.placeItems(this.values, this.labelY, this.dotY);
        this.redraw();
    }
    
    public void removeScaleNames() {
        this.setScaleNames(null, null);
    }
    
    public void setEmptyMessage(final String emptyMessage) {
        this.emptyMessage = emptyMessage;
        this.redraw();
    }
    
    public void setDotColor(final Color dotColor) {
        this.dotColor = dotColor;
        this.redraw();
    }
    
    public void setTopIsHigh(final boolean topIsHigh) {
        this.topIsHigh = topIsHigh;
        this.redraw();
    }
    
    public void setConnectorColor(final Color connectorColor) {
        this.connectorColor = connectorColor;
        this.redraw();
    }
    
    public void setRulerColor(final Color rulerColor) {
        this.rulerColor = rulerColor;
        this.redraw();
    }
    
    public void setLeftMarginColor(final Color leftMarginColor) {
        this.leftMarginColor = leftMarginColor;
        this.redraw();
    }
    
    public void setVerticalMargin(final int n) {
        this.verticalMargin = n;
        this.yTop = n;
        this.yBottom = this.size().height - n;
        this.display(this.values, this.labels);
    }
    
    public void setRulerX(final int rulerX) {
        this.rulerX = rulerX;
        this.redraw();
    }
    
    public void setLabelX(final int labelX) {
        this.labelX = labelX;
        this.redraw();
    }
    
    public void setBigTicDivisor(final double bigTicDivisor) {
        this.bigTicDivisor = bigTicDivisor;
        this.display(this.values, this.labels);
    }
    
    public void setSmallTicDivisor(final int smallTicDivisor) {
        this.smallTicDivisor = smallTicDivisor;
        this.display(this.values, this.labels);
    }
    
    public void setNumberFont(final Font numberFont) {
        this.numberFont = numberFont;
        this.redraw();
    }
    
    public void setColoredLabelColor(final Color coloredLabelColor) {
        this.coloredLabelColor = coloredLabelColor;
    }
    
    public void clearLabels() {
        this.nameCutoffs = null;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return this.setMouse(true, n, n2);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.clickable) {
            return false;
        }
        this.setMouse(false, n, n2);
        if (this.itemSelected != -1) {
            this.deliverEvent(new Event(this, 1001, new Integer(this.itemSelected)));
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        return this.setMouse(false, n, n2);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        return this.setMouse(false, n, -1);
    }
    
    boolean setMouse(final boolean mouseIsDown, final int x, final int y) {
        if (!this.clickable) {
            return false;
        }
        if (x != this.mouse.x || y != this.mouse.y || mouseIsDown != this.mouseIsDown) {
            this.mouse.x = x;
            this.mouse.y = y;
            this.mouseIsDown = mouseIsDown;
            if (!this.running) {
                this.redraw();
            }
        }
        return true;
    }
    
    public synchronized void display(final double[] array, final String[] array2) {
        this.display(array, array2, null, null, this.formatType, new Vector());
    }
    
    public synchronized void display(final double[] array, final String[] array2, final String[] array3, final double[] array4) {
        this.display(array, array2, array3, array4, this.formatType, new Vector());
    }
    
    public synchronized void display(final double[] array, final String[] array2, final int n) {
        this.display(array, array2, this.scaleNames, this.nameCutoffs, n, new Vector());
    }
    
    public synchronized void display(final double[] array, final String[] array2, final Vector vector) {
        this.display(array, array2, null, null, this.formatType, vector);
    }
    
    public synchronized void display(final double[] array, final String[] array2, final String[] array3, final double[] array4, final Vector vector) {
        this.display(array, array2, array3, array4, this.formatType, vector);
    }
    
    public synchronized void display(final double[] array, final String[] array2, final int n, final Vector vector) {
        this.display(array, array2, this.scaleNames, this.nameCutoffs, n, vector);
    }
    
    public synchronized void display(final double[] values, final String[] labels, final String[] scaleNames, final double[] nameCutoffs, final int formatType, final Vector coloredLabels) {
        this.values = values;
        this.labels = labels;
        this.scaleNames = scaleNames;
        this.nameCutoffs = nameCutoffs;
        this.formatType = formatType;
        this.coloredLabels = coloredLabels;
        if (values == null || values.length == 0) {
            this.redraw();
            return;
        }
        final int length = values.length;
        this.labelY = new int[length];
        this.dotY = new int[length];
        this.newDotY = new int[length];
        this.newLabelY = new int[length];
        this.placeItems(values, this.labelY, this.dotY);
        this.redraw();
    }
    
    public void moveTo(final double[] array) {
        this.moveTo(array, this.formatType);
    }
    
    public void moveTo(final double[] newValues, final int formatType) {
        if (this.running || (this.time != 0.0 && formatType == this.formatType)) {
            return;
        }
        this.scaleNames = null;
        if (this.values == null || this.values.length == 0) {
            this.redraw();
            return;
        }
        this.formatType = formatType;
        this.newValues = newValues;
        final int length = newValues.length;
        this.newDotY = new int[length];
        this.placeItems(newValues, this.newLabelY = new int[length], this.newDotY);
        new Thread(this).start();
    }
    
    public void moveTo(final double[] newValues, final String[] scaleNames, final double[] nameCutoffs) {
        if (this.running || this.time != 0.0) {
            return;
        }
        if (this.values == null || this.values.length == 0) {
            this.redraw();
            return;
        }
        this.formatType = this.formatType;
        this.newValues = newValues;
        this.scaleNames = scaleNames;
        this.nameCutoffs = nameCutoffs;
        final int length = newValues.length;
        this.newDotY = new int[length];
        this.placeItems(newValues, this.newLabelY = new int[length], this.newDotY);
        new Thread(this).start();
    }
    
    public void run() {
        this.running = true;
        this.time = 0.1;
        while (this.time <= 1.0) {
            this.redraw();
            try {
                Thread.sleep(40L);
            }
            catch (InterruptedException ex) {}
            this.time += 0.1;
        }
        this.running = false;
        this.time = 0.0;
        this.display(this.newValues, this.labels, this.scaleNames, this.nameCutoffs, this.formatType, this.coloredLabels);
    }
    
    protected void placeItems(final double[] array, final int[] array2, final int[] array3) {
        if (array == null) {
            return;
        }
        int n = 1;
        for (int i = 0; i < array.length; ++i) {
            final double n2 = array[i];
            if (!Double.isNaN(n2)) {
                if (n != 0 || n2 < this.min) {
                    this.min = n2;
                }
                if (n != 0 || n2 > this.max) {
                    this.max = n2;
                }
                n = 0;
            }
        }
        if (this.scaleNames != null && this.nameCutoffs != null) {
            final double n3 = this.nameCutoffs[this.nameCutoffs.length - 1];
            final double n4 = this.nameCutoffs[0];
            final double n5 = n3 - n4;
            this.max = Math.max(this.max, n3 + 0.15 * n5);
            this.min = Math.min(this.min, n4 - 0.15 * n5);
        }
        if (this.min == this.max) {
            ++this.max;
        }
        if (Double.isNaN(this.max) || this.max <= this.min) {
            this.min = 0.0;
            this.max = 100.0;
        }
        this.bigInc = this.decimalFloor((this.max - this.min) / this.bigTicDivisor);
        this.smallInc = this.bigInc / this.smallTicDivisor;
        this.scaleLow = this.bigInc * Math.floor(this.min / this.bigInc);
        this.scaleHigh = this.bigInc * Math.ceil(this.max / this.bigInc);
        final int length = array.length;
        final int[] array4 = new int[length];
        for (int j = 0; j < length; ++j) {
            array4[j] = j;
        }
        for (int k = 0; k < length; ++k) {
            for (int l = 0; l < length - 1; ++l) {
                final double n6 = array[array4[l]];
                final double n7 = array[array4[l + 1]];
                if (Double.isNaN(n6) || (!Double.isNaN(n7) && (!this.topIsHigh ^ n6 < n7))) {
                    final int n8 = array4[l];
                    array4[l] = array4[l + 1];
                    array4[l + 1] = n8;
                }
            }
        }
        for (final int n10 : array4) {
            array2[n10] = (array3[n10] = this.scaleY(array[n10]));
        }
        final int[] array5 = new int[length];
        final int[] array6 = new int[length];
        for (int n11 = 0; n11 < length; ++n11) {
            array5[n11] = (array6[n11] = n11);
        }
        boolean b = true;
        while (b) {
            b = false;
            for (int n12 = 0; n12 < length - 1; ++n12) {
                if (array5[n12] != -1 && array5[n12 + 1] != -1 && array2[array4[array6[n12]]] > array2[array4[array5[n12 + 1]]] - this.labelHeight) {
                    b = true;
                    final int n13 = array5[n12];
                    final int n14 = array6[n12 + 1];
                    for (int n15 = n13; n15 <= n14; ++n15) {
                        array2[array4[n15]] = n15 * this.labelHeight;
                    }
                    this.adjust(array4, array2, array3, n13, n14);
                    array6[n12] = n14;
                    for (int n16 = n12 + 1; n16 < length - 1; ++n16) {
                        array5[n16] = array5[n16 + 1];
                        array6[n16] = array6[n16 + 1];
                    }
                    array5[length - 1] = -1;
                }
            }
        }
        if (array2[array4[length - 1]] > this.yBottom) {
            array2[array4[length - 1]] = this.yBottom;
            for (int n17 = length - 1; n17 > 0 && array2[array4[n17 - 1]] > array2[array4[n17]] - this.labelHeight; --n17) {
                array2[array4[n17 - 1]] = array2[array4[n17]] - this.labelHeight;
            }
        }
        if (array2[array4[0]] < this.yTop) {
            array2[array4[0]] = this.yTop;
            for (int n18 = 0; n18 < length - 1 && array2[array4[n18 + 1]] < array2[array4[n18]] + this.labelHeight; ++n18) {
                array2[array4[n18 + 1]] = array2[array4[n18]] + this.labelHeight;
            }
        }
    }
    
    void adjust(final int[] array, final int[] array2, final int[] array3, final int n, final int n2) {
        int n3 = 0;
        for (int i = n; i <= n2; ++i) {
            n3 += array3[array[i]];
        }
        final int n4 = n3 / (n2 - n + 1);
        int n5 = 0;
        for (int j = n; j <= n2; ++j) {
            n5 += array2[array[j]];
        }
        final int n6 = n4 - n5 / (n2 - n + 1);
        for (int k = n; k <= n2; ++k) {
            final int n7 = array[k];
            array2[n7] += n6;
        }
    }
    
    public synchronized void drawOffscreen(final Graphics graphics) {
        super.offscreen.setColor(this.getBackground());
        super.offscreen.fillRect(0, 0, this.size().width, Math.max(this.lastMaxHeight, this.size().height));
        graphics.setColor(this.leftMarginColor);
        graphics.fillRect(0, 0, this.rulerX + 1, this.size().height);
        graphics.setFont(this.getFont());
        if (this.values == null || this.values.length == 0) {
            if (this.emptyMessage != null) {
                graphics.drawString(this.emptyMessage, this.rulerX + 10, this.size().height / 2);
            }
            return;
        }
        if (this.min >= this.max || this.bigInc == 0.0) {
            return;
        }
        final boolean b = this.time != 0.0;
        final boolean b2 = this.scaleNames != null && this.nameCutoffs != null;
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int rulerX = this.rulerX;
        if (!b) {
            if (b2) {
                for (int i = 0; i < this.scaleNames.length; ++i) {
                    this.rulerX = Math.max(this.rulerX, this.bigTicR + fontMetrics.stringWidth(this.scaleNames[i]));
                }
            }
            else {
                for (double scaleLow = this.scaleLow; scaleLow <= this.scaleHigh * 1.001; scaleLow += this.bigInc) {
                    this.rulerX = Math.max(this.rulerX, this.bigTicR + fontMetrics.stringWidth(this.format(scaleLow, this.formatType)) + 4);
                }
            }
        }
        if (this.rulerX != rulerX) {
            this.labelX += this.rulerX - rulerX;
            graphics.fillRect(0, 0, this.rulerX + 1, this.size().height);
        }
        graphics.setColor(this.rulerColor);
        graphics.drawLine(this.rulerX, this.yTop, this.rulerX, this.yBottom);
        if (!b) {
            int n = 0;
            if (!b2) {
                for (double scaleLow2 = this.scaleLow; scaleLow2 < this.scaleHigh; scaleLow2 += this.smallInc) {
                    if (n++ % this.smallTicDivisor != 0) {
                        final int scaleY = this.scaleY(scaleLow2);
                        graphics.drawLine(this.rulerX - this.smallTicR, scaleY, this.rulerX - 1, scaleY);
                    }
                }
            }
            if (b2) {
                for (int j = 0; j < this.nameCutoffs.length; ++j) {
                    this.drawTicLabel(graphics, fontMetrics, this.nameCutoffs[j], false);
                }
                final int n2 = this.rulerX - this.bigTicR + 4;
                for (int k = 0; k < this.nameCutoffs.length - 1; ++k) {
                    this.middleRight(graphics, fontMetrics, this.scaleNames[k + 1], n2, this.nameCutoffs[k], this.nameCutoffs[k + 1]);
                }
                this.middleRight(graphics, fontMetrics, this.scaleNames[0], n2, this.min, this.nameCutoffs[0]);
                this.middleRight(graphics, fontMetrics, this.scaleNames[this.scaleNames.length - 1], n2, this.max, this.nameCutoffs[this.nameCutoffs.length - 1]);
            }
            else {
                for (double scaleLow3 = this.scaleLow; scaleLow3 <= this.scaleHigh * 1.001; scaleLow3 += this.bigInc) {
                    this.drawTicLabel(graphics, fontMetrics, scaleLow3, true);
                }
            }
        }
        int itemSelected = -1;
        for (int length = this.labels.length, l = 0; l < length; ++l) {
            final boolean naN = Double.isNaN(this.values[l]);
            final int n3 = (int)(this.dotY[l] * (1.0 - this.time) + this.newDotY[l] * this.time);
            final int n4 = (int)(this.labelY[l] * (1.0 - this.time) + this.newLabelY[l] * this.time);
            graphics.setColor(naN ? Color.gray : this.dotColor);
            graphics.fillOval(this.rulerX - this.dotR, n3 - this.dotR, 2 * this.dotR, 2 * this.dotR);
            graphics.setColor(naN ? Color.lightGray : this.connectorColor);
            graphics.drawLine(this.rulerX + this.bigTicR, n3, this.labelX - 4, n4);
            final String s = this.labels[l];
            final int stringWidth = fontMetrics.stringWidth(s);
            final int x = this.mouse.x;
            final int y = this.mouse.y;
            if (x > this.labelX && x < this.labelX + stringWidth && y < n4 + 8 && y > n4 - 7) {
                itemSelected = l;
            }
            final Color color = (itemSelected == l && !b) ? (this.mouseIsDown ? Color.blue : Color.red) : (naN ? Color.gray : (this.coloredLabels.contains(this.labels[l]) ? this.coloredLabelColor : Color.black));
            if (!b) {
                if (this.numberFont != null) {
                    graphics.setFont(this.numberFont);
                }
                graphics.setColor(this.dotColor);
                graphics.drawString(this.format(this.values[l], this.formatType), this.labelX + stringWidth + 5, n4 + 4);
                if (itemSelected == l) {
                    graphics.setColor(color);
                    graphics.drawLine(this.labelX, n4 + 5, this.labelX + stringWidth, n4 + 5);
                }
            }
            graphics.setColor(color);
            graphics.setFont(this.getFont());
            graphics.drawString(s, this.labelX, n4 + 4);
            this.lastMaxHeight = Math.max(this.lastMaxHeight, n4 + 4);
        }
        this.itemSelected = itemSelected;
        Toolkit.getDefaultToolkit().sync();
    }
    
    protected void drawTicLabel(final Graphics graphics, final FontMetrics fontMetrics, final double n, final boolean b) {
        final int scaleY = this.scaleY(n);
        graphics.drawLine(this.rulerX - this.bigTicR, scaleY, this.rulerX - 1, scaleY);
        if (b) {
            final String format = this.format(n, this.formatType);
            graphics.drawString(format, this.rulerX - this.bigTicR - 2 - fontMetrics.stringWidth(format), scaleY + 4);
        }
    }
    
    protected void middleRight(final Graphics graphics, final FontMetrics fontMetrics, final String s, final int n, final double n2, final double n3) {
        graphics.drawString(s, n - fontMetrics.stringWidth(s), (this.scaleY(n2) + this.scaleY(n3)) / 2 + fontMetrics.getAscent() / 2);
    }
    
    protected int scaleY(final double n) {
        if (Double.isNaN(n)) {
            return (this.yBottom + this.size().height) / 2;
        }
        final double n2 = (n - this.scaleLow) / (this.scaleHigh - this.scaleLow) * (this.yBottom - this.yTop);
        return (int)Math.round(this.topIsHigh ? (this.yBottom - n2) : (this.yTop + n2));
    }
    
    protected double decimalFloor(double n) {
        if (n < 0.0) {
            n = -n;
        }
        if (n == 0.0) {
            System.out.println("decimalFloor: x=0");
        }
        final double pow = Math.pow(10.0, Math.floor(Math.log(n) / Math.log(10.0)) - 1.0);
        if (n >= 50.0 * pow) {
            return 20.0 * pow;
        }
        if (n >= 30.0 * pow) {
            return 10.0 * pow;
        }
        if (n >= 15.0 * pow) {
            return 5.0 * pow;
        }
        return 2.0 * pow;
    }
    
    final String format(final double n, final int n2) {
        if (Double.isNaN(n)) {
            return "n/a";
        }
        if (this.scaleNames != null && this.nameCutoffs != null) {
            int i;
            for (i = 0; i < this.nameCutoffs.length; ++i) {
                if (n < this.nameCutoffs[i]) {
                    return this.scaleNames[i];
                }
            }
            return this.scaleNames[i];
        }
        final double n3 = Math.round(100.0 * n) / 100.0;
        String s;
        if (n2 == 1) {
            s = String.valueOf((n3 >= 0.0) ? "+" : "") + n3;
        }
        else if (n2 == 2) {
            s = ((n3 >= 0.0) ? ("$" + n3) : ("-$" + Math.abs(n3)));
        }
        else {
            s = String.valueOf(n3);
        }
        final int index = s.indexOf(46);
        if (index >= 0 && s.length() - index > 3) {
            s = s.substring(0, index + 3);
        }
        if (s.endsWith(".0")) {
            s = s.substring(0, s.length() - 2);
        }
        if (n2 == 2 && s.indexOf(".") == s.length() - 2) {
            s = String.valueOf(s) + "0";
        }
        if (n2 == 1 || n2 == 3) {
            return String.valueOf(s) + "%";
        }
        return s;
    }
}
