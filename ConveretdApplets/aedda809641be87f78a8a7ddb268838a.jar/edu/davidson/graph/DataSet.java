// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.graph;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

public class DataSet
{
    public static final int NOLINE = 0;
    public static final int LINE = 1;
    public static final int MAX_LENGTH = 20000;
    public Graph2D g2d;
    public int linestyle;
    public Color linecolor;
    public int marker;
    public Color markercolor;
    public double markerscale;
    public Axis xaxis;
    public Axis yaxis;
    public double xmax;
    public double xmin;
    public double ymax;
    public double ymin;
    public boolean clipping;
    protected boolean sorted;
    protected boolean stripChart;
    protected int chartPts;
    private double[] tempDatum;
    private double[] lastPoint;
    protected double dxmax;
    protected double dxmin;
    protected double dymax;
    protected double dymin;
    protected double[] data;
    protected int length;
    protected double xrange;
    protected double yrange;
    protected int legend_length;
    protected TextLine legend_text;
    protected int legend_ix;
    protected int legend_iy;
    protected double legend_dx;
    protected double legend_dy;
    protected int increment;
    protected int stride;
    
    public void setSorted(final boolean sorted) {
        this.sorted = sorted;
        if (this.sorted) {
            this.insertionSort();
        }
    }
    
    public boolean isSorted() {
        return this.sorted;
    }
    
    public void setStripChart(final int n, final boolean stripChart) {
        this.stripChart = stripChart;
        this.chartPts = Math.max(n, 1);
        if (this.stripChart) {
            this.chartPoints();
        }
    }
    
    public boolean isStripChart() {
        return this.stripChart;
    }
    
    public double[] getLastPoint() {
        return this.lastPoint;
    }
    
    public DataSet() {
        this.linestyle = 1;
        this.linecolor = null;
        this.marker = 0;
        this.markercolor = null;
        this.markerscale = 1.0;
        this.clipping = true;
        this.sorted = false;
        this.stripChart = false;
        this.chartPts = 0;
        this.tempDatum = new double[2];
        this.lastPoint = new double[2];
        this.legend_length = 20;
        this.legend_text = null;
        this.increment = 100;
        this.stride = 2;
        this.length = 0;
        this.data = null;
        this.lastPoint = new double[this.stride];
        this.range(this.stride);
    }
    
    public DataSet(final int stride) throws Exception {
        this.linestyle = 1;
        this.linecolor = null;
        this.marker = 0;
        this.markercolor = null;
        this.markerscale = 1.0;
        this.clipping = true;
        this.sorted = false;
        this.stripChart = false;
        this.chartPts = 0;
        this.tempDatum = new double[2];
        this.lastPoint = new double[2];
        this.legend_length = 20;
        this.legend_text = null;
        this.increment = 100;
        this.stride = 2;
        if (stride < 2) {
            throw new Exception("Invalid stride parameter!");
        }
        this.stride = stride;
        this.lastPoint = new double[stride];
        this.length = 0;
        this.lastPoint = new double[stride];
        this.range(stride);
    }
    
    public DataSet(final double[] array, final int n) throws Exception {
        this.linestyle = 1;
        this.linecolor = null;
        this.marker = 0;
        this.markercolor = null;
        this.markerscale = 1.0;
        this.clipping = true;
        this.sorted = false;
        this.stripChart = false;
        this.chartPts = 0;
        this.tempDatum = new double[2];
        this.lastPoint = new double[2];
        this.legend_length = 20;
        this.legend_text = null;
        this.increment = 100;
        this.stride = 2;
        this.length = 0;
        if (array == null || array.length == 0 || n <= 0) {
            throw new Exception("DataSet: Error in parsed data!");
        }
        if (array.length > 20000 || n > 20000) {
            System.out.println("Error: DataSet passed to constructor has too many points.  Max Pointst=".concat(String.valueOf(String.valueOf(this.length / this.stride))));
            return;
        }
        this.lastPoint = new double[this.stride];
        this.data = new double[n * this.stride];
        this.length = n * this.stride;
        System.arraycopy(array, 0, this.data, 0, this.length);
        System.arraycopy(array, this.length - this.stride, this.lastPoint, 0, this.stride);
        if (this.sorted) {
            this.insertionSort();
        }
        if (this.stripChart) {
            this.chartPoints();
        }
        this.range(this.stride);
    }
    
    public DataSet(final double[] array, final int n, final int stride) throws Exception {
        this.linestyle = 1;
        this.linecolor = null;
        this.marker = 0;
        this.markercolor = null;
        this.markerscale = 1.0;
        this.clipping = true;
        this.sorted = false;
        this.stripChart = false;
        this.chartPts = 0;
        this.tempDatum = new double[2];
        this.lastPoint = new double[2];
        this.legend_length = 20;
        this.legend_text = null;
        this.increment = 100;
        this.stride = 2;
        if (stride < 2) {
            throw new Exception("Invalid stride parameter!");
        }
        this.length = 0;
        if (array == null || array.length == 0 || n <= 0) {
            throw new Exception("DataSet: Error in parsed data!");
        }
        if (array.length > 20000 || n > 20000) {
            System.out.println("Error: DataSet passed to constructor has too many points.  Max Pointst=".concat(String.valueOf(String.valueOf(this.length / this.stride))));
            return;
        }
        this.stride = stride;
        this.lastPoint = new double[this.stride];
        this.data = new double[n * this.stride];
        this.length = n * this.stride;
        System.arraycopy(array, 0, this.data, 0, this.length);
        System.arraycopy(array, this.length - this.stride, this.lastPoint, 0, this.stride);
        if (this.sorted) {
            this.insertionSort();
        }
        if (this.stripChart) {
            this.chartPoints();
        }
        this.range(this.stride);
    }
    
    public synchronized void replace(final double[] array, final int n) throws Exception {
        if (n * this.stride > 20000) {
            System.out.println("Error: DataSet has too many points.  Max Pointst=".concat(String.valueOf(String.valueOf(this.length / this.stride))));
            return;
        }
        if (array == null || array.length == 0 || n <= 0) {
            throw new Exception("DataSet: Error in replace data!");
        }
        if (this.data == null || this.data.length != array.length) {
            this.length = 0;
            this.data = null;
            this.append(array, n);
            return;
        }
        System.arraycopy(array, 0, this.data, 0, this.length);
        System.arraycopy(array, this.length - this.stride, this.lastPoint, 0, this.stride);
        if (this.sorted) {
            this.insertionSort();
        }
        if (this.stripChart) {
            this.chartPoints();
        }
        this.range(this.stride);
        if (this.xaxis != null) {
            this.xaxis.resetRange();
        }
        if (this.yaxis != null) {
            this.yaxis.resetRange();
        }
    }
    
    public synchronized void append(final double[] array, final int n) throws Exception {
        if (n * this.stride > 20000) {
            System.out.println("Error: DataSet has too many points.  Max Pointst=".concat(String.valueOf(String.valueOf(this.length / this.stride))));
            return;
        }
        final int n2 = n * this.stride;
        if (array == null || array.length == 0 || n <= 0) {
            throw new Exception("DataSet: Error in append data!");
        }
        if (this.data == null) {
            this.data = new double[n * this.stride];
            this.length = n * this.stride;
            System.arraycopy(array, 0, this.data, 0, this.length);
            if (this.sorted) {
                this.insertionSort();
            }
            if (this.stripChart) {
                this.chartPoints();
            }
            this.range(this.stride);
            if (this.xaxis != null) {
                this.xaxis.resetRange();
            }
            if (this.yaxis != null) {
                this.yaxis.resetRange();
            }
            return;
        }
        if (n2 + this.length < this.data.length) {
            System.arraycopy(array, 0, this.data, this.length, n2);
            this.length += n2;
        }
        else {
            final double[] data = new double[n2 + this.length + this.increment];
            if (this.length != 0) {
                System.arraycopy(this.data, 0, data, 0, this.length);
            }
            System.arraycopy(array, 0, data, this.length, n2);
            this.length += n2;
            this.data = data;
        }
        if (this.sorted && n2 == this.stride) {
            this.insertDatum(this.length - n2);
        }
        else if (this.sorted) {
            this.insertionSort();
        }
        if (this.stripChart) {
            this.chartPoints();
        }
        System.arraycopy(array, n2 - this.stride, this.lastPoint, 0, this.stride);
        if (this.stripChart || n > 1 || this.stride != 2 || this.length < 6) {
            this.range(this.stride);
        }
        else {
            if (this.dxmax < array[0]) {
                this.dxmax = array[0];
            }
            else if (this.dxmin > array[0]) {
                this.dxmin = array[0];
            }
            if (this.dymax < array[1]) {
                this.dymax = array[1];
            }
            else if (this.dymin > array[1]) {
                this.dymin = array[1];
            }
            if (this.xaxis == null) {
                this.xmin = this.dxmin;
                this.xmax = this.dxmax;
            }
            if (this.yaxis == null) {
                this.ymin = this.dymin;
                this.ymax = this.dymax;
            }
        }
        if (this.xaxis != null) {
            this.xaxis.resetRange();
        }
        if (this.yaxis != null) {
            this.yaxis.resetRange();
        }
    }
    
    public synchronized void delete(final int n, final int n2) {
        int n3 = this.stride * n2;
        int n4 = this.stride * n;
        if (this.length <= 0) {
            return;
        }
        if (n3 < n4) {
            return;
        }
        if (n4 < 0) {
            n4 = 0;
        }
        if (n3 > this.length - this.stride) {
            n3 = this.length - this.stride;
        }
        if (n3 < this.length - this.stride) {
            System.arraycopy(this.data, n3 + this.stride, this.data, n4, this.length - n3 - this.stride);
        }
        this.length -= n3 + this.stride - n4;
        this.range(this.stride);
        if (this.xaxis != null) {
            this.xaxis.resetRange();
        }
        if (this.yaxis != null) {
            this.yaxis.resetRange();
        }
    }
    
    public synchronized void deleteData() {
        this.length = 0;
        this.data = null;
        this.range(this.stride);
        if (this.xaxis != null) {
            this.xaxis.resetRange();
        }
        if (this.yaxis != null) {
            this.yaxis.resetRange();
        }
    }
    
    public synchronized void draw_data(final Graphics graphics, final Rectangle rectangle) {
        if (this.xaxis != null) {
            this.xmax = this.xaxis.maximum;
            this.xmin = this.xaxis.minimum;
        }
        if (this.yaxis != null) {
            this.ymax = this.yaxis.maximum;
            this.ymin = this.yaxis.minimum;
        }
        this.xrange = this.xmax - this.xmin;
        this.yrange = this.ymax - this.ymin;
        this.draw_legend(graphics, rectangle);
        if (this.clipping) {
            graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        final Color color = graphics.getColor();
        if (this.linestyle != 0) {
            if (this.linecolor != null) {
                graphics.setColor(this.linecolor);
            }
            else {
                graphics.setColor(color);
            }
            this.draw_lines(graphics, rectangle);
        }
        if (this.linestyle == 0 && this.marker == 0) {
            if (this.markercolor != null) {
                graphics.setColor(this.markercolor);
            }
            else {
                graphics.setColor(color);
            }
            this.draw_dots(graphics, rectangle);
        }
        if (this.marker > 0) {
            if (this.markercolor != null) {
                graphics.setColor(this.markercolor);
            }
            else {
                graphics.setColor(color);
            }
            this.draw_markers(graphics, rectangle);
        }
        if (this.marker == -1) {
            if (this.linecolor != null) {
                graphics.setColor(this.linecolor);
            }
            else {
                graphics.setColor(color);
            }
            this.draw_polygon(graphics, rectangle);
        }
        if (this.marker == -2) {
            if (this.linecolor != null) {
                graphics.setColor(this.linecolor);
            }
            else {
                graphics.setColor(color);
            }
            this.draw_polygon2(graphics, rectangle);
        }
        if (this.marker == -3) {
            if (this.linecolor != null) {
                graphics.setColor(this.linecolor);
            }
            else {
                graphics.setColor(color);
            }
            this.draw_histogram(graphics, rectangle);
        }
        graphics.setColor(color);
    }
    
    public double[] getData() {
        return this.data;
    }
    
    public double getXmax() {
        return this.dxmax;
    }
    
    public double getXmin() {
        return this.dxmin;
    }
    
    public double getYmax() {
        return this.dymax;
    }
    
    public double getYmin() {
        return this.dymin;
    }
    
    public void legend(final int legend_ix, final int legend_iy, final String text) {
        if (text == null) {
            this.legend_text = null;
            return;
        }
        if (this.legend_text == null) {
            this.legend_text = new TextLine(text);
        }
        else {
            this.legend_text.setText(text);
        }
        this.legend_text.setJustification(1);
        this.legend_ix = legend_ix;
        this.legend_iy = legend_iy;
        this.legend_dx = 0.0;
        this.legend_dy = 0.0;
    }
    
    public String getLegend() {
        return this.legend_text.getText();
    }
    
    public int getLegend_ix() {
        return this.legend_ix;
    }
    
    public int getLegend_iy() {
        return this.legend_iy;
    }
    
    public void legend(final double legend_dx, final double legend_dy, final String text) {
        if (text == null) {
            this.legend_text = null;
            return;
        }
        if (this.legend_text == null) {
            this.legend_text = new TextLine(text);
        }
        else {
            this.legend_text.setText(text);
        }
        this.legend_text.setJustification(1);
        this.legend_dx = legend_dx;
        this.legend_dy = legend_dy;
        this.legend_ix = 0;
        this.legend_iy = 0;
    }
    
    public void legendFont(final Font font) {
        if (font == null) {
            return;
        }
        if (this.legend_text == null) {
            this.legend_text = new TextLine();
        }
        this.legend_text.setFont(font);
    }
    
    public void legendColor(final Color color) {
        if (color == null) {
            return;
        }
        if (this.legend_text == null) {
            this.legend_text = new TextLine();
        }
        this.legend_text.setColor(color);
    }
    
    public int dataPoints() {
        return this.length / this.stride;
    }
    
    public double[] getPoint(final int n) {
        final double[] array = new double[this.stride];
        final int n2 = n * this.stride;
        if (n < 0 || n2 > this.length - this.stride) {
            return null;
        }
        for (int i = 0; i < this.stride; ++i) {
            array[i] = this.data[n2 + i];
        }
        return array;
    }
    
    public double[] getClosestPoint(final double n, final double n2) {
        final double[] array = { 0.0, 0.0, 0.0 };
        final double n3 = this.data[0] - n;
        final double n4 = this.data[1] - n2;
        array[0] = this.data[0];
        array[1] = this.data[1];
        array[2] = n3 * n3 + n4 * n4;
        for (int i = this.stride; i < this.length - 1; i += this.stride) {
            final double n5 = this.data[i] - n;
            final double n6 = this.data[i + 1] - n2;
            final double n7 = n5 * n5 + n6 * n6;
            if (n7 < array[2]) {
                array[0] = this.data[i];
                array[1] = this.data[i + 1];
                array[2] = n7;
            }
        }
        return array;
    }
    
    private void draw_polygon2(final Graphics graphics, final Rectangle rectangle) {
        if (this.data == null || this.data.length < this.stride || this.data.length < 4) {
            return;
        }
        int n = 0;
        final int[] array = new int[this.data.length / this.stride + 4];
        final int[] array2 = new int[this.data.length / this.stride + 4];
        final int n2 = (int)(rectangle.y + (1.0 - (0 - this.ymin) / this.yrange) * rectangle.height);
        final Color color = graphics.getColor();
        graphics.setColor(new Color(0, 255, 255));
        int n3;
        if (this.xmin - this.data[0] > 10 * this.xrange) {
            n3 = -100000;
        }
        else if (this.data[0] - this.xmax > 10 * this.xrange) {
            n3 = 100000;
        }
        else {
            n3 = (int)(rectangle.x + (this.data[0] - this.xmin) / this.xrange * rectangle.width);
        }
        int n4;
        if (this.ymin - this.data[1] > 10 * this.yrange) {
            n4 = 100000;
        }
        else if (this.data[1] - this.ymax > 10 * this.yrange) {
            n4 = -100000;
        }
        else {
            n4 = (int)(rectangle.y + (1.0 - (this.data[1] - this.ymin) / this.yrange) * rectangle.height);
        }
        int n5 = n4;
        if (n4 > n2) {
            n4 = n2;
        }
        for (int i = this.stride; i < this.length; i += this.stride) {
            int n6;
            if (this.xmin - this.data[i] > 10 * this.xrange) {
                n6 = -100000;
            }
            else if (this.data[i] - this.xmax > 10 * this.xrange) {
                n6 = 100000;
            }
            else {
                n6 = (int)(rectangle.x + (this.data[i] - this.xmin) / this.xrange * rectangle.width);
            }
            int n7;
            if (this.ymin - this.data[i + 1] > 10 * this.yrange) {
                n7 = 100000;
            }
            else if (this.data[i + 1] - this.ymax > 10 * this.yrange) {
                n7 = -100000;
            }
            else {
                n7 = (int)(rectangle.y + (1.0 - (this.data[i + 1] - this.ymin) / this.yrange) * rectangle.height);
            }
            if (n7 != n2 && (n4 - n2) / (n7 - n2) < 0 && n7 != n4) {
                final double n8 = (n6 - n3) / (n7 - n4);
                if (n7 > n2) {
                    n6 = (int)(n3 + (n2 - n4) * n8);
                    n7 = n2;
                }
            }
            else if (n4 == n2 && n7 < n2) {
                n3 = n6 + (n2 - n7) * ((n6 - n3) / (n7 - n5));
                n4 = n2;
            }
            else if (n7 > n2) {
                n5 = n7;
                n7 = n2;
            }
            array[n] = n3;
            array2[n] = n4;
            ++n;
            n3 = n6;
            n4 = n7;
        }
        array[n] = n3;
        array2[n] = n4;
        ++n;
        array[n] = n3;
        array2[n] = n2;
        ++n;
        array[n] = array[0];
        array2[n] = n2;
        ++n;
        graphics.fillPolygon(array, array2, n);
        graphics.setColor(color);
        graphics.drawPolygon(array, array2, n);
        int n9 = 0;
        graphics.setColor(new Color(255, 255, 0));
        int n10;
        if (this.xmin - this.data[0] > 10 * this.xrange) {
            n10 = -10000;
        }
        else if (this.data[0] - this.xmax > 10 * this.xrange) {
            n10 = 10000;
        }
        else {
            n10 = (int)(rectangle.x + (this.data[0] - this.xmin) / this.xrange * rectangle.width);
        }
        int n11;
        if (this.ymin - this.data[1] > 10 * this.yrange) {
            n11 = 10000;
        }
        else if (this.data[1] - this.ymax > 10 * this.yrange) {
            n11 = -10000;
        }
        else {
            n11 = (int)(rectangle.y + (1.0 - (this.data[1] - this.ymin) / this.yrange) * rectangle.height);
        }
        int n12 = n11;
        if (n11 < n2) {
            n11 = n2;
        }
        for (int j = this.stride; j < this.length; j += this.stride) {
            int n13;
            if (this.xmin - this.data[j] > 10 * this.xrange) {
                n13 = -10000;
            }
            else if (this.data[j] - this.xmax > 10 * this.xrange) {
                n13 = 10000;
            }
            else {
                n13 = (int)(rectangle.x + (this.data[j] - this.xmin) / this.xrange * rectangle.width);
            }
            int n14;
            if (this.ymin - this.data[j + 1] > 10 * this.yrange) {
                n14 = 10000;
            }
            else if (this.data[j + 1] - this.ymax > 10 * this.yrange) {
                n14 = -10000;
            }
            else {
                n14 = (int)(rectangle.y + (1.0 - (this.data[j + 1] - this.ymin) / this.yrange) * rectangle.height);
            }
            if (n14 != n2 && (n11 - n2) / (n14 - n2) < 0 && n14 != n11) {
                final double n15 = (n13 - n10) / (n14 - n11);
                if (n14 < n2) {
                    n13 = (int)(n10 + (n2 - n11) * n15);
                    n14 = n2;
                }
            }
            else if (n11 == n2 && n14 > n2) {
                n10 = n13 + (n2 - n14) * ((n13 - n10) / (n14 - n12));
                n11 = n2;
            }
            else if (n14 < n2) {
                n12 = n14;
                n14 = n2;
            }
            array[n9] = n10;
            array2[n9] = n11;
            ++n9;
            n10 = n13;
            n11 = n14;
        }
        array[n9] = n10;
        array2[n9] = n11;
        ++n9;
        array[n9] = n10;
        array2[n9] = n2;
        ++n9;
        array[n9] = array[0];
        array2[n9] = n2;
        ++n9;
        graphics.fillPolygon(array, array2, n9);
        graphics.setColor(color);
        graphics.drawPolygon(array, array2, n9);
    }
    
    private void draw_polygon(final Graphics graphics, final Rectangle rectangle) {
        if (this.data == null || this.data.length < this.stride || this.data.length < 4) {
            return;
        }
        int n = 0;
        final int[] array = new int[this.data.length / this.stride + 4];
        final int[] array2 = new int[this.data.length / this.stride + 4];
        final int n2 = (int)(rectangle.y + (1.0 - (0 - this.ymin) / this.yrange) * rectangle.height);
        int n3;
        if (this.xmin - this.data[0] > 10 * this.xrange) {
            n3 = -10000;
        }
        else if (this.data[0] - this.xmax > 10 * this.xrange) {
            n3 = 10000;
        }
        else {
            n3 = (int)(rectangle.x + (this.data[0] - this.xmin) / this.xrange * rectangle.width);
        }
        int n4;
        if (this.ymin - this.data[1] > 10 * this.yrange) {
            n4 = 10000;
        }
        else if (this.data[1] - this.ymax > 10 * this.yrange) {
            n4 = -10000;
        }
        else {
            n4 = (int)(rectangle.y + (1.0 - (this.data[1] - this.ymin) / this.yrange) * rectangle.height);
        }
        for (int i = this.stride; i < this.length; i += this.stride) {
            int n5;
            if (this.xmin - this.data[i] > 10 * this.xrange) {
                n5 = -10000;
            }
            else if (this.data[i] - this.xmax > 10 * this.xrange) {
                n5 = 10000;
            }
            else {
                n5 = (int)(rectangle.x + (this.data[i] - this.xmin) / this.xrange * rectangle.width);
            }
            int n6;
            if (this.ymin - this.data[i + 1] > 10 * this.yrange) {
                n6 = 10000;
            }
            else if (this.data[i + 1] - this.ymax > 10 * this.yrange) {
                n6 = -10000;
            }
            else {
                n6 = (int)(rectangle.y + (1.0 - (this.data[i + 1] - this.ymin) / this.yrange) * rectangle.height);
            }
            array[n] = n3;
            array2[n] = n4;
            ++n;
            n3 = n5;
            n4 = n6;
        }
        array[n] = n3;
        array2[n] = n4;
        ++n;
        array[n] = n3;
        array2[n] = n2;
        ++n;
        array[n] = array[0];
        array2[n] = n2;
        ++n;
        graphics.fillPolygon(array, array2, n);
    }
    
    protected synchronized void draw_histogram(final Graphics graphics, final Rectangle rectangle) {
        if (this.data == null || this.data.length < this.stride || this.data.length < 2) {
            return;
        }
        if (this.g2d.getMarkers() == null) {
            return;
        }
        final int n = (int)(this.markerscale * 2);
        final int n2 = (int)(rectangle.y + (1.0 - (0 - this.ymin) / this.yrange) * rectangle.height);
        for (int i = 0; i < this.length; i += this.stride) {
            int n3;
            if (this.xmin - this.data[i] > 10 * this.xrange) {
                n3 = -10000;
            }
            else if (this.data[i] - this.xmax > 10 * this.xrange) {
                n3 = 10000;
            }
            else {
                n3 = (int)(rectangle.x + (this.data[i] - this.xmin) / this.xrange * rectangle.width);
            }
            int n4;
            if (this.ymin - this.data[i + 1] > 10 * this.yrange) {
                n4 = 10000;
            }
            else if (this.data[i + 1] - this.ymax > 10 * this.yrange) {
                n4 = -10000;
            }
            else {
                n4 = (int)(rectangle.y + (1.0 - (this.data[i + 1] - this.ymin) / this.yrange) * rectangle.height);
            }
            if (n2 - n4 < 0) {
                graphics.fillRect(n3 - n, n2 + 1, 2 * n + 1, -n2 + n4);
            }
            else {
                graphics.fillRect(n3 - n, n4 + 1, 2 * n + 1, n2 - n4);
            }
        }
    }
    
    protected synchronized void draw_lines(final Graphics graphics, final Rectangle rectangle) {
        if (this.data == null || this.data.length < this.stride) {
            return;
        }
        int n;
        if (this.xmin - this.data[0] > 10 * this.xrange) {
            n = -10000;
        }
        else if (this.data[0] - this.xmax > 10 * this.xrange) {
            n = 10000;
        }
        else {
            n = (int)(rectangle.x + (this.data[0] - this.xmin) / this.xrange * rectangle.width);
        }
        int n2;
        if (this.ymin - this.data[1] > 10 * this.yrange) {
            n2 = 10000;
        }
        else if (this.data[1] - this.ymax > 10 * this.yrange) {
            n2 = -10000;
        }
        else {
            n2 = (int)(rectangle.y + (1.0 - (this.data[1] - this.ymin) / this.yrange) * rectangle.height);
        }
        for (int i = this.stride; i < this.length; i += this.stride) {
            int n3;
            if (this.xmin - this.data[i] > 10 * this.xrange) {
                n3 = -10000;
            }
            else if (this.data[i] - this.xmax > 10 * this.xrange) {
                n3 = 10000;
            }
            else {
                n3 = (int)(rectangle.x + (this.data[i] - this.xmin) / this.xrange * rectangle.width);
            }
            int n4;
            if (this.ymin - this.data[i + 1] > 10 * this.yrange) {
                n4 = 10000;
            }
            else if (this.data[i + 1] - this.ymax > 10 * this.yrange) {
                n4 = -10000;
            }
            else {
                n4 = (int)(rectangle.y + (1.0 - (this.data[i + 1] - this.ymin) / this.yrange) * rectangle.height);
            }
            graphics.drawLine(n, n2, n3, n4);
            n = n3;
            n2 = n4;
        }
    }
    
    protected boolean inside(final double n, final double n2) {
        return n >= this.xmin && n <= this.xmax && n2 >= this.ymin && n2 <= this.ymax;
    }
    
    protected void draw_markers(final Graphics graphics, final Rectangle rectangle) {
        final Rectangle clipBounds = graphics.getClipBounds();
        final int x = clipBounds.x;
        final int n = clipBounds.x + clipBounds.width;
        final int y = clipBounds.y;
        final int n2 = clipBounds.y + clipBounds.height;
        final Markers markers = this.g2d.getMarkers();
        if (markers == null) {
            return;
        }
        for (int i = 0; i < this.length; i += this.stride) {
            if (this.inside(this.data[i], this.data[i + 1])) {
                final int n3 = (int)(rectangle.x + (this.data[i] - this.xmin) / this.xrange * rectangle.width);
                final int n4 = (int)(rectangle.y + (1.0 - (this.data[i + 1] - this.ymin) / this.yrange) * rectangle.height);
                if (n3 >= x && n3 <= n && n4 >= y && n4 <= n2) {
                    markers.draw(graphics, this.marker, this.markerscale, n3, n4);
                }
            }
        }
    }
    
    protected synchronized void draw_dots(final Graphics graphics, final Rectangle rectangle) {
        final Rectangle clipBounds = graphics.getClipBounds();
        final int x = clipBounds.x;
        final int n = clipBounds.x + clipBounds.width;
        final int y = clipBounds.y;
        final int n2 = clipBounds.y + clipBounds.height;
        for (int i = 0; i < this.length; i += this.stride) {
            if (this.inside(this.data[i], this.data[i + 1])) {
                final int n3 = (int)(rectangle.x + (this.data[i] - this.xmin) / this.xrange * rectangle.width);
                final int n4 = (int)(rectangle.y + (1.0 - (this.data[i + 1] - this.ymin) / this.yrange) * rectangle.height);
                if (n3 >= x && n3 <= n && n4 >= y && n4 <= n2) {
                    graphics.drawLine(n3, n4, n3, n4);
                }
            }
        }
    }
    
    protected void draw_legend(final Graphics graphics, final Rectangle rectangle) {
        final Color color = graphics.getColor();
        if (this.legend_text == null) {
            return;
        }
        if (this.legend_text.isNull()) {
            return;
        }
        if (this.legend_ix == 0 && this.legend_iy == 0) {
            this.legend_ix = (int)(rectangle.x + (this.legend_dx - this.xmin) / this.xrange * rectangle.width);
            this.legend_iy = (int)(rectangle.y + (1.0 - (this.legend_dy - this.ymin) / this.yrange) * rectangle.height);
        }
        if (this.linestyle != 0) {
            if (this.linecolor != null) {
                graphics.setColor(this.linecolor);
            }
            graphics.drawLine(this.legend_ix, this.legend_iy, this.legend_ix + this.legend_length, this.legend_iy);
        }
        if (this.marker > 0) {
            final Markers markers = this.g2d.getMarkers();
            if (markers != null) {
                if (this.markercolor != null) {
                    graphics.setColor(this.markercolor);
                }
                else {
                    graphics.setColor(color);
                }
                markers.draw(graphics, this.marker, 1.0, this.legend_ix + this.legend_length / 2, this.legend_iy);
            }
        }
        this.legend_text.draw(graphics, this.legend_ix + this.legend_length + this.legend_text.charWidth(graphics, ' '), this.legend_iy + this.legend_text.getAscent(graphics) / 3);
        graphics.setColor(color);
    }
    
    protected void range(final int n) {
        if (this.length >= n) {
            this.dxmax = this.data[0];
            this.dymax = this.data[1];
            this.dxmin = this.dxmax;
            this.dymin = this.dymax;
        }
        else {
            this.dxmin = 0.0;
            this.dxmax = 0.0;
            this.dymin = 0.0;
            this.dymax = 0.0;
        }
        for (int i = n; i < this.length; i += n) {
            if (this.dxmax < this.data[i]) {
                this.dxmax = this.data[i];
            }
            else if (this.dxmin > this.data[i]) {
                this.dxmin = this.data[i];
            }
            if (this.dymax < this.data[i + 1]) {
                this.dymax = this.data[i + 1];
            }
            else if (this.dymin > this.data[i + 1]) {
                this.dymin = this.data[i + 1];
            }
        }
        if (this.xaxis == null) {
            this.xmin = this.dxmin;
            this.xmax = this.dxmax;
        }
        if (this.yaxis == null) {
            this.ymin = this.dymin;
            this.ymax = this.dymax;
        }
    }
    
    private synchronized void insertDatum(final int n) {
        if (this.length < 2 * this.stride) {
            return;
        }
        if (this.tempDatum.length != this.stride) {
            this.tempDatum = new double[this.stride];
        }
        System.arraycopy(this.data, n, this.tempDatum, 0, this.stride);
        for (int i = 0; i < this.length; i += this.stride) {
            if (this.data[i] > this.data[n]) {
                System.arraycopy(this.data, i, this.data, i + this.stride, this.length - i - this.stride);
                System.arraycopy(this.tempDatum, 0, this.data, i, this.stride);
                return;
            }
        }
    }
    
    protected void insertionSort() {
        if (this.length < 2 * this.stride) {
            return;
        }
        for (int i = this.stride; i < this.length; i += this.stride) {
            if (this.data[i] < this.data[i - this.stride]) {
                this.insertDatum(i);
            }
        }
    }
    
    protected void chartPoints() {
        if (this.length <= this.chartPts * this.stride) {
            return;
        }
        System.arraycopy(this.data, this.length - this.chartPts * this.stride, this.data, 0, this.chartPts * this.stride);
        this.length = this.chartPts * this.stride;
    }
}
