// 
// Decompiled by Procyon v0.5.30
// 

package sTools.graph;

import java.awt.Font;
import java.awt.Component;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Color;
import sTools.Format;

public class Axis
{
    private Format labelFormat;
    static final int HORIZONTAL = 0;
    static final int VERTICAL = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int TOP = 4;
    public static final int BOTTOM = 5;
    static final int NUMBER_OF_TICS = 4;
    public boolean drawgrid;
    public boolean drawzero;
    public Color gridcolor;
    public Color zerocolor;
    public boolean redraw;
    public boolean force_end_labels;
    public int major_tic_size;
    public int minor_tic_size;
    public int minor_tic_count;
    public Color axiscolor;
    public double minimum;
    public double maximum;
    public Dimension data_window;
    public Graph2D g2d;
    protected Point amin;
    protected Point amax;
    protected int orientation;
    protected int position;
    protected int width;
    protected RTextLine title;
    protected RTextLine label;
    protected RTextLine exponent;
    protected int max_label_width;
    protected Vector dataset;
    protected String[] label_string;
    protected float[] label_value;
    protected double label_start;
    protected double label_step;
    protected int label_exponent;
    protected int label_count;
    protected int guess_label_number;
    public boolean manualRange;
    
    public Axis() {
        this.labelFormat = new Format("%5.1f");
        this.drawgrid = false;
        this.drawzero = false;
        this.gridcolor = null;
        this.zerocolor = null;
        this.redraw = true;
        this.force_end_labels = false;
        this.major_tic_size = 10;
        this.minor_tic_size = 5;
        this.minor_tic_count = 1;
        this.data_window = new Dimension(0, 0);
        this.g2d = null;
        this.width = 0;
        this.title = new RTextLine();
        this.label = new RTextLine("0");
        this.exponent = new RTextLine();
        this.max_label_width = 0;
        this.dataset = new Vector();
        this.label_string = null;
        this.label_value = null;
        this.label_start = 0.0;
        this.label_step = 0.0;
        this.label_exponent = 0;
        this.label_count = 0;
        this.guess_label_number = 4;
        this.manualRange = false;
        this.orientation = 0;
        this.position = 5;
    }
    
    public Axis(final int position) {
        this.labelFormat = new Format("%5.1f");
        this.drawgrid = false;
        this.drawzero = false;
        this.gridcolor = null;
        this.zerocolor = null;
        this.redraw = true;
        this.force_end_labels = false;
        this.major_tic_size = 10;
        this.minor_tic_size = 5;
        this.minor_tic_count = 1;
        this.data_window = new Dimension(0, 0);
        this.g2d = null;
        this.width = 0;
        this.title = new RTextLine();
        this.label = new RTextLine("0");
        this.exponent = new RTextLine();
        this.max_label_width = 0;
        this.dataset = new Vector();
        this.label_string = null;
        this.label_value = null;
        this.label_start = 0.0;
        this.label_step = 0.0;
        this.label_exponent = 0;
        this.label_count = 0;
        this.guess_label_number = 4;
        this.manualRange = false;
        this.setPosition(position);
        switch (this.position) {
            case 1:
            case 2: {
                this.title.setRotation(90);
                break;
            }
            case 3: {
                this.title.setRotation(-90);
                break;
            }
            default: {
                this.title.setRotation(0);
                break;
            }
        }
    }
    
    public void setPosition(final int position) {
        switch (this.position = position) {
            case 2: {
                this.orientation = 1;
                break;
            }
            case 3: {
                this.orientation = 1;
                break;
            }
            case 4: {
                this.orientation = 0;
                break;
            }
            case 5: {
                this.orientation = 0;
                break;
            }
            case 0: {
                this.orientation = 0;
                this.position = 5;
                break;
            }
            case 1: {
                this.orientation = 1;
                this.position = 2;
                break;
            }
            default: {
                this.orientation = 0;
                this.position = 5;
                break;
            }
        }
    }
    
    public void attachDataSet(final DataSet set) {
        if (this.orientation == 0) {
            this.attachXdata(set);
        }
        else {
            this.attachYdata(set);
        }
    }
    
    public void detachDataSet(final DataSet set) {
        if (set == null) {
            return;
        }
        if (this.orientation == 0) {
            set.xaxis = null;
        }
        else {
            set.yaxis = null;
        }
        this.dataset.removeElement(set);
        if (!this.manualRange && !this.dataset.isEmpty()) {
            this.resetRange();
        }
    }
    
    public void detachAll() {
        if (this.dataset.isEmpty()) {
            if (!this.manualRange) {
                this.minimum = 0.0;
                this.maximum = 1.0;
            }
            return;
        }
        if (this.orientation == 0) {
            for (int i = 0; i < this.dataset.size(); ++i) {
                ((DataSet)this.dataset.elementAt(i)).xaxis = null;
            }
        }
        else {
            for (int j = 0; j < this.dataset.size(); ++j) {
                ((DataSet)this.dataset.elementAt(j)).yaxis = null;
            }
        }
        this.dataset.removeAllElements();
        if (!this.manualRange) {
            this.minimum = 0.0;
            this.maximum = 1.0;
        }
    }
    
    public double getDataMin() {
        if (this.dataset.isEmpty()) {
            return 0.0;
        }
        final DataSet set = this.dataset.firstElement();
        if (set == null) {
            return 0.0;
        }
        double n;
        if (this.orientation == 0) {
            n = set.getXmin();
            final Enumeration<DataSet> elements = this.dataset.elements();
            while (elements.hasMoreElements()) {
                n = Math.min(elements.nextElement().getXmin(), n);
            }
        }
        else {
            n = set.getYmin();
            final Enumeration<DataSet> elements2 = this.dataset.elements();
            while (elements2.hasMoreElements()) {
                n = Math.min(elements2.nextElement().getYmin(), n);
            }
        }
        return n;
    }
    
    public double getDataMax() {
        if (this.dataset.isEmpty()) {
            return 1.0;
        }
        final DataSet set = this.dataset.firstElement();
        if (set == null) {
            return 0.0;
        }
        double n;
        if (this.orientation == 0) {
            n = set.getXmax();
            final Enumeration<DataSet> elements = this.dataset.elements();
            while (elements.hasMoreElements()) {
                n = Math.max(elements.nextElement().getXmax(), n);
            }
        }
        else {
            n = set.getYmax();
            final Enumeration<DataSet> elements2 = this.dataset.elements();
            while (elements2.hasMoreElements()) {
                n = Math.max(elements2.nextElement().getYmax(), n);
            }
        }
        return n;
    }
    
    public int getInteger(final double n) {
        if (this.amax == null) {
            return 0;
        }
        if (this.maximum == this.minimum) {
            return (this.amax.x - this.amin.x) / 2;
        }
        if (this.orientation == 0) {
            return this.amin.x + (int)(0.5 + (n - this.minimum) * ((this.amax.x - this.amin.x) / (this.maximum - this.minimum)));
        }
        return this.amin.y + (int)(0.5 + (this.maximum - n) * ((this.amax.y - this.amin.y) / (this.maximum - this.minimum)));
    }
    
    public double getDouble(final int n) {
        if (this.amax == null) {
            return (this.maximum + this.minimum) / 2.0;
        }
        if (this.orientation == 0) {
            return this.minimum + (n - this.amin.x) * ((this.maximum - this.minimum) / (this.amax.x - this.amin.x));
        }
        return this.maximum - (n - this.amin.y) * ((this.maximum - this.minimum) / (this.amax.y - this.amin.y));
    }
    
    public void resetRange() {
        if (this.manualRange || this.dataset.isEmpty()) {
            return;
        }
        this.minimum = this.getDataMin();
        this.maximum = this.getDataMax();
        if (this.minimum == this.maximum) {
            if (this.dataset.isEmpty()) {
                this.minimum = 0.0;
                this.maximum = 1.0;
            }
            else if (this.maximum < 20) {
                --this.minimum;
                ++this.maximum;
            }
            else {
                this.minimum -= 0.1 * this.maximum;
                this.maximum += 0.1 * this.maximum;
            }
        }
        final double n = this.maximum - this.minimum;
        this.minimum -= 0.05 * n;
        this.maximum += 0.05 * n;
    }
    
    public int getAxisPos() {
        return this.position;
    }
    
    public boolean isVertical() {
        return this.orientation != 0;
    }
    
    public int getAxisWidth(final Graphics graphics) {
        this.width = 0;
        if (this.minimum == this.maximum) {
            this.resetRange();
        }
        if (this.minimum == this.maximum) {
            return 0;
        }
        this.calculateGridLabels();
        this.exponent.setText(null);
        if (this.label_exponent != 0) {
            this.exponent.copyState(this.label);
            this.exponent.setText(String.valueOf("x10^").concat(String.valueOf(String.valueOf(this.label_exponent))));
        }
        if (this.orientation == 0) {
            this.width = this.label.getRHeight(graphics) + this.label.getLeading(graphics);
            this.width += Math.max(this.title.getRHeight(graphics), this.exponent.getRHeight(graphics));
        }
        else {
            for (int i = 0; i < this.label_string.length; ++i) {
                this.label.setText(String.valueOf(" ").concat(String.valueOf(this.label_string[i])));
                this.width = Math.max(this.label.getRWidth(graphics), this.width);
            }
            this.max_label_width = this.width;
            this.width = 0;
            if (!this.title.isNull()) {
                this.width = Math.max(this.width, this.title.getRWidth(graphics) + this.title.charWidth(graphics, ' '));
            }
            if (!this.exponent.isNull()) {
                this.width = Math.max(this.width, this.exponent.getRWidth(graphics) + this.exponent.charWidth(graphics, ' '));
            }
            this.width += this.max_label_width;
        }
        return this.width;
    }
    
    public boolean positionAxis(final int n, final int n2, final int n3, final int n4) {
        this.amin = null;
        this.amax = null;
        if (this.orientation == 0 && n3 != n4) {
            return false;
        }
        if (this.orientation == 1 && n != n2) {
            return false;
        }
        this.amin = new Point(n, n3);
        this.amax = new Point(n2, n4);
        return true;
    }
    
    public void drawAxis(final Graphics graphics) {
        if (!this.redraw) {
            return;
        }
        if (this.minimum == this.maximum) {
            this.resetRange();
            if (this.minimum == this.maximum) {
                return;
            }
        }
        if (this.amin.equals(this.amax)) {
            return;
        }
        if (this.width == 0) {
            this.width = this.getAxisWidth(graphics);
        }
        final Graphics create = graphics.create();
        if (this.force_end_labels && !this.manualRange) {
            this.minimum = this.label_start;
            this.maximum = this.minimum + (this.label_count - 1) * this.label_step;
        }
        this.title.setDrawingComponent(this.g2d);
        this.label.setDrawingComponent(this.g2d);
        this.exponent.setDrawingComponent(this.g2d);
        if (this.orientation == 0) {
            this.drawHAxis(create);
        }
        else {
            this.drawVAxis(create);
        }
    }
    
    public void setTitleText(final String text) {
        this.title.setText(text);
    }
    
    public void setTitleColor(final Color color) {
        this.title.setColor(color);
    }
    
    public void setTitleFont(final Font font) {
        this.title.setFont(font);
    }
    
    public void setTitleRotation(final int rotation) {
        this.title.setRotation(rotation);
    }
    
    public void setLabelColor(final Color color) {
        this.label.setColor(color);
    }
    
    public void setLabelFont(final Font font) {
        this.label.setFont(font);
    }
    
    public void setExponentColor(final Color color) {
        this.exponent.setColor(color);
    }
    
    public void setExponentFont(final Font font) {
        this.exponent.setFont(font);
    }
    
    public boolean isManualRange() {
        return this.manualRange;
    }
    
    public void setManualRange(final boolean manualRange) {
        this.manualRange = manualRange;
        this.resetRange();
        if (this.manualRange && this.maximum == this.minimum) {
            this.maximum = this.minimum + 1.0E-6;
        }
        this.calculateGridLabels();
    }
    
    public void setManualRange(final boolean manualRange, final double minimum, final double maximum) {
        this.manualRange = manualRange;
        this.minimum = minimum;
        this.maximum = maximum;
        this.resetRange();
        this.calculateGridLabels();
    }
    
    protected void drawHAxis(final Graphics graphics) {
        final double n = this.minimum * 1.001;
        final double n2 = this.maximum * 1.001;
        final double n3 = (this.amax.x - this.amin.x) / (this.maximum - this.minimum);
        if (this.axiscolor != null) {
            graphics.setColor(this.axiscolor);
        }
        graphics.drawLine(this.amin.x, this.amin.y, this.amax.x, this.amax.y);
        int n4;
        if (this.position == 4) {
            n4 = 1;
        }
        else {
            n4 = -1;
        }
        final double n5 = this.label_step / (this.minor_tic_count + 1);
        double label_start = this.label_start;
        for (int i = 0; i < this.label_count; ++i) {
            if (label_start >= n && label_start <= n2) {
                final int y = this.amin.y;
                final int n6 = this.amin.x + (int)((label_start - this.minimum) * n3);
                if (Math.abs(this.label_value[i]) <= 1.0E-4 && this.drawzero) {
                    final Color color = graphics.getColor();
                    if (this.zerocolor != null) {
                        graphics.setColor(this.zerocolor);
                    }
                    graphics.drawLine(n6, y, n6, y + this.data_window.height * n4);
                    graphics.setColor(color);
                }
                else if (this.drawgrid) {
                    final Color color2 = graphics.getColor();
                    if (this.gridcolor != null) {
                        graphics.setColor(this.gridcolor);
                    }
                    graphics.drawLine(n6, y, n6, y + this.data_window.height * n4);
                    graphics.setColor(color2);
                }
                graphics.drawLine(n6, y, n6, y + this.major_tic_size * n4);
            }
            double n7 = label_start + n5;
            for (int j = 0; j < this.minor_tic_count; ++j) {
                if (n7 >= n && n7 <= n2) {
                    final int y2 = this.amin.y;
                    final int n8 = this.amin.x + (int)((n7 - this.minimum) * n3);
                    if (this.drawgrid) {
                        final Color color3 = graphics.getColor();
                        if (this.gridcolor != null) {
                            graphics.setColor(this.gridcolor);
                        }
                        graphics.drawLine(n8, y2, n8, y2 + this.data_window.height * n4);
                        graphics.setColor(color3);
                    }
                    graphics.drawLine(n8, y2, n8, y2 + this.minor_tic_size * n4);
                }
                n7 += n5;
            }
            label_start += this.label_step;
        }
        int n9;
        if (this.position == 4) {
            n9 = -this.label.getLeading(graphics) - this.label.getDescent(graphics);
        }
        else {
            n9 = this.label.getLeading(graphics) + this.label.getAscent(graphics);
        }
        double label_start2 = this.label_start;
        for (int k = 0; k < this.label_count; ++k) {
            if (label_start2 >= n && label_start2 <= n2) {
                final int n10 = this.amin.y + n9;
                final int n11 = this.amin.x + (int)((label_start2 - this.minimum) * n3);
                this.label.setText(this.label_string[k]);
                this.label.draw(graphics, n11, n10, 0);
            }
            label_start2 += this.label_step;
        }
        if (!this.exponent.isNull()) {
            final int x = this.amax.x;
            int n12;
            int n13;
            if (this.position == 4) {
                n12 = this.amin.y - this.label.getLeading(graphics) - this.label.getDescent(graphics) - this.exponent.getLeading(graphics) - this.exponent.getDescent(graphics);
                n13 = x;
            }
            else {
                n12 = this.amax.y + this.label.getLeading(graphics) + this.label.getAscent(graphics) + this.exponent.getLeading(graphics) + this.exponent.getAscent(graphics);
                n13 = x - 20;
            }
            this.exponent.draw(graphics, n13, n12, 1);
        }
        if (!this.title.isNull()) {
            int n14;
            if (this.position == 4) {
                n14 = this.amin.y - this.label.getLeading(graphics) - this.label.getDescent(graphics) - this.title.getLeading(graphics) - this.title.getDescent(graphics);
            }
            else {
                n14 = this.amax.y + this.label.getLeading(graphics) + this.label.getAscent(graphics) + this.title.getLeading(graphics) + this.title.getAscent(graphics);
            }
            this.title.draw(graphics, this.amin.x + (this.amax.x - this.amin.x) / 2, n14, 0);
        }
    }
    
    protected void drawVAxis(final Graphics graphics) {
        final int n = 0;
        graphics.getColor();
        graphics.getFont();
        final double n2 = this.minimum * 1.001;
        final double n3 = this.maximum * 1.001;
        final double n4 = (this.amax.y - this.amin.y) / (this.maximum - this.minimum);
        if (this.axiscolor != null) {
            graphics.setColor(this.axiscolor);
        }
        graphics.drawLine(this.amin.x, this.amin.y, this.amax.x, this.amax.y);
        int n5;
        if (this.position == 3) {
            n5 = -1;
        }
        else {
            n5 = 1;
        }
        final double n6 = this.label_step / (this.minor_tic_count + 1);
        double label_start = this.label_start;
        for (int i = 0; i < this.label_count; ++i) {
            if (label_start >= n2 && label_start <= n3) {
                final int x = this.amin.x;
                final int n7 = this.amax.y - (int)((label_start - this.minimum) * n4);
                if (Math.abs(this.label_value[i]) <= 1.0E-4 && this.drawzero) {
                    final Color color = graphics.getColor();
                    if (this.zerocolor != null) {
                        graphics.setColor(this.zerocolor);
                    }
                    graphics.drawLine(x, n7, x + this.data_window.width * n5, n7);
                    graphics.setColor(color);
                }
                else if (this.drawgrid) {
                    final Color color2 = graphics.getColor();
                    if (this.gridcolor != null) {
                        graphics.setColor(this.gridcolor);
                    }
                    graphics.drawLine(x, n7, x + this.data_window.width * n5, n7);
                    graphics.setColor(color2);
                }
                graphics.drawLine(x, n7, x + this.major_tic_size * n5, n7);
            }
            double n8 = label_start + n6;
            for (int j = 0; j < this.minor_tic_count; ++j) {
                if (n8 >= n2 && n8 <= n3) {
                    final int x2 = this.amin.x;
                    final int n9 = this.amax.y - (int)((n8 - this.minimum) * n4);
                    if (this.drawgrid) {
                        final Color color3 = graphics.getColor();
                        if (this.gridcolor != null) {
                            graphics.setColor(this.gridcolor);
                        }
                        graphics.drawLine(x2, n9, x2 + this.data_window.width * n5, n9);
                        graphics.setColor(color3);
                    }
                    graphics.drawLine(x2, n9, x2 + this.minor_tic_size * n5, n9);
                }
                n8 += n6;
            }
            label_start += this.label_step;
        }
        double label_start2 = this.label_start;
        for (int k = 0; k < this.label_count; ++k) {
            if (label_start2 >= n2 && label_start2 <= n3) {
                final int n10 = this.amin.x + n;
                final int n11 = this.amax.y - (int)((label_start2 - this.minimum) * n4) + this.label.getAscent(graphics) / 2;
                if (this.position == 3) {
                    this.label.setText(String.valueOf(" ").concat(String.valueOf(this.label_string[k])));
                    this.label.draw(graphics, n10, n11, 1);
                }
                else {
                    this.label.setText(String.valueOf(this.label_string[k]).concat(String.valueOf(" ")));
                    this.label.draw(graphics, n10, n11, 2);
                }
            }
            label_start2 += this.label_step;
        }
        if (!this.exponent.isNull()) {
            final int y = this.amin.y;
            if (this.position == 3) {
                this.exponent.draw(graphics, this.amin.x + this.max_label_width + this.exponent.charWidth(graphics, ' '), y, 1);
            }
            else {
                this.exponent.draw(graphics, this.amin.x - this.max_label_width - this.exponent.charWidth(graphics, ' ') + 5, y, 2);
            }
        }
        if (!this.title.isNull()) {
            final int n12 = this.amin.y + (this.amax.y - this.amin.y) / 2;
            if (this.title.getRotation() == 0 || this.title.getRotation() == 180) {
                if (this.position == 3) {
                    this.title.draw(graphics, this.amin.x + this.max_label_width + this.title.charWidth(graphics, ' '), n12, 1);
                }
                else {
                    this.title.draw(graphics, this.amin.x - this.max_label_width - this.title.charWidth(graphics, ' '), n12, 2);
                }
            }
            else {
                this.title.setJustification(0);
                int n13;
                if (this.position == 3) {
                    n13 = this.amin.x + this.max_label_width - this.title.getLeftEdge(graphics) + this.title.charWidth(graphics, ' ');
                }
                else {
                    n13 = this.amin.x - this.max_label_width - this.title.getRightEdge(graphics) - this.title.charWidth(graphics, ' ');
                }
                this.title.draw(graphics, n13, n12);
            }
        }
    }
    
    protected void attachXdata(final DataSet set) {
        this.dataset.addElement(set);
        set.xaxis = this;
        if (this.manualRange) {
            return;
        }
        if (this.dataset.size() == 1) {
            this.minimum = set.dxmin;
            this.maximum = set.dxmax;
        }
        else {
            if (this.minimum > set.dxmin) {
                this.minimum = set.dxmin;
            }
            if (this.maximum < set.dxmax) {
                this.maximum = set.dxmax;
            }
        }
    }
    
    protected void attachYdata(final DataSet set) {
        this.dataset.addElement(set);
        set.yaxis = this;
        if (this.manualRange) {
            return;
        }
        if (this.dataset.size() == 1) {
            this.minimum = set.dymin;
            this.maximum = set.dymax;
        }
        else {
            if (this.minimum > set.dymin) {
                this.minimum = set.dymin;
            }
            if (this.maximum < set.dymax) {
                this.maximum = set.dymax;
            }
        }
    }
    
    public void calculateGridLabels() {
        if (Math.abs(this.minimum) == 0 && Math.abs(this.maximum) == 0) {
            this.maximum = this.minimum + 1.0E-6;
        }
        if (Math.abs(this.minimum) > Math.abs(this.maximum)) {
            this.label_exponent = (int)Math.floor(SpecialFunction.log10(Math.abs(this.minimum)) / 3.0) * 3;
        }
        else {
            this.label_exponent = (int)Math.floor(SpecialFunction.log10(Math.abs(this.maximum)) / 3.0) * 3;
        }
        this.label_step = this.RoundUp((this.maximum - this.minimum) / this.guess_label_number);
        this.label_start = Math.floor(this.minimum / this.label_step) * this.label_step;
        double label_start = this.label_start;
        this.label_count = 1;
        while (label_start < this.maximum) {
            label_start += this.label_step;
            ++this.label_count;
        }
        this.label_string = new String[this.label_count];
        this.label_value = new float[this.label_count];
        for (int i = 0; i < this.label_count; ++i) {
            double n = this.label_start + i * this.label_step;
            if (this.label_exponent < 0) {
                for (int j = this.label_exponent; j < 0; ++j) {
                    n *= 10;
                }
            }
            else {
                for (int k = 0; k < this.label_exponent; ++k) {
                    n /= 10;
                }
            }
            this.label_string[i] = this.labelFormat.form(n);
            this.label_value[i] = (float)n;
        }
    }
    
    private double RoundUp(double n) {
        final int n2 = (int)Math.floor(SpecialFunction.log10(n));
        if (n2 < 0) {
            for (int i = n2; i < 0; ++i) {
                n *= 10.0;
            }
        }
        else {
            for (int j = 0; j < n2; ++j) {
                n /= 10.0;
            }
        }
        if (n > 5.0) {
            n = 10.0;
        }
        else if (n > 2.0) {
            n = 5.0;
        }
        else if (n > 1.0) {
            n = 2.0;
        }
        else {
            n = 1.0;
        }
        if (n2 < 0) {
            for (int k = n2; k < 0; ++k) {
                n /= 10.0;
            }
        }
        else {
            for (int l = 0; l < n2; ++l) {
                n *= 10.0;
            }
        }
        return n;
    }
}
