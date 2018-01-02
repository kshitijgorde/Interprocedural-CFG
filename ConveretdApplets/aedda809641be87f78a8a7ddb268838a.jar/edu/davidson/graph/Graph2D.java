// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.graph;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.InputStream;
import java.net.URL;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Color;
import java.awt.Canvas;

public class Graph2D extends Canvas
{
    boolean showAxis;
    private Color DefaultBackground;
    protected Vector axis;
    protected Vector dataset;
    protected Markers markers;
    protected LoadMessage load_thread;
    protected Color DataBackground;
    public int loadingData;
    public int borderTop;
    public int borderBottom;
    public int borderLeft;
    public int borderRight;
    public boolean frame;
    public Color framecolor;
    public boolean drawgrid;
    public Color gridcolor;
    public boolean drawzero;
    public Color zerocolor;
    public Rectangle datarect;
    public boolean clearAll;
    public boolean paintAll;
    public boolean square;
    public TextLine lastText;
    
    public Graph2D() {
        this.showAxis = true;
        this.DefaultBackground = null;
        this.axis = new Vector(4);
        this.dataset = new Vector(10);
        this.markers = null;
        this.load_thread = null;
        this.DataBackground = null;
        this.loadingData = 0;
        this.borderTop = 20;
        this.borderBottom = 20;
        this.borderLeft = 20;
        this.borderRight = 20;
        this.frame = true;
        this.drawgrid = true;
        this.gridcolor = Color.pink;
        this.drawzero = true;
        this.zerocolor = Color.orange;
        this.datarect = new Rectangle();
        this.clearAll = true;
        this.paintAll = true;
        this.square = false;
        this.lastText = null;
    }
    
    public final boolean isShowAxis() {
        return this.showAxis;
    }
    
    public final void setShowAxis(final boolean showAxis) {
        this.showAxis = showAxis;
    }
    
    public DataSet loadFile(final URL url) {
        final byte[] array = new byte[50];
        int n = 0;
        int n2 = 100;
        final int n3 = 100;
        int n4 = 0;
        double[] array2 = new double[n2];
        InputStream openStream = null;
        boolean b = false;
        try {
            openStream = url.openStream();
            int read;
            while ((read = openStream.read()) > -1) {
                switch (read) {
                    case 35: {
                        b = true;
                        continue;
                    }
                    case 10:
                    case 13: {
                        b = false;
                    }
                    case 9:
                    case 32: {
                        if (n > 0) {
                            array2[n4] = Double.valueOf(new String(array, 0, n));
                            if (++n4 >= n2) {
                                n2 += n3;
                                final double[] array3 = new double[n2];
                                System.arraycopy(array2, 0, array3, 0, n4);
                                array2 = array3;
                            }
                            n = 0;
                            continue;
                        }
                        continue;
                    }
                    default: {
                        if (!b) {
                            array[n] = (byte)read;
                            ++n;
                            continue;
                        }
                        continue;
                    }
                }
            }
            if (openStream != null) {
                openStream.close();
            }
        }
        catch (Exception ex) {
            System.out.println("Mouse Released.");
            ex.printStackTrace();
            if (openStream != null) {
                try {
                    openStream.close();
                }
                catch (Exception ex2) {}
            }
            return null;
        }
        return this.loadDataSet(array2, n4 / 2);
    }
    
    public DataSet loadDataSet(final double[] array, final int n) {
        DataSet set;
        try {
            set = new DataSet(array, n);
            this.dataset.addElement(set);
            set.g2d = this;
        }
        catch (Exception ex) {
            System.out.println("Failed to load Data set ");
            ex.printStackTrace();
            return null;
        }
        return set;
    }
    
    public void attachDataSet(final DataSet set) {
        if (set != null) {
            this.dataset.addElement(set);
            set.g2d = this;
        }
    }
    
    public void detachDataSet(final DataSet set) {
        if (set != null) {
            if (set.xaxis != null) {
                set.xaxis.detachDataSet(set);
            }
            if (set.yaxis != null) {
                set.yaxis.detachDataSet(set);
            }
            this.dataset.removeElement(set);
        }
    }
    
    public void detachDataSets() {
        if (this.dataset == null || this.dataset.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.dataset.size(); ++i) {
            final DataSet set = this.dataset.elementAt(i);
            if (set.xaxis != null) {
                set.xaxis.detachDataSet(set);
            }
            if (set.yaxis != null) {
                set.yaxis.detachDataSet(set);
            }
        }
        this.dataset.removeAllElements();
    }
    
    public Axis createAxis(final int n) {
        Axis axis;
        try {
            axis = new Axis(n);
            axis.g2d = this;
            this.axis.addElement(axis);
        }
        catch (Exception ex) {
            System.out.println("Failed to create Axis");
            ex.printStackTrace();
            return null;
        }
        return axis;
    }
    
    public void attachAxis(final Axis axis) {
        if (axis == null) {
            return;
        }
        try {
            this.axis.addElement(axis);
            axis.g2d = this;
        }
        catch (Exception ex) {
            System.out.println("Failed to attach Axis");
            ex.printStackTrace();
        }
    }
    
    public void detachAxis(final Axis axis) {
        if (axis != null) {
            axis.detachAll();
            axis.g2d = null;
            this.axis.removeElement(axis);
        }
    }
    
    public void detachAxes() {
        if (this.axis == null || this.axis.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.axis.size(); ++i) {
            ((Axis)this.axis.elementAt(i)).detachAll();
            ((Axis)this.axis.elementAt(i)).g2d = null;
        }
        this.axis.removeAllElements();
    }
    
    public double getXmax() {
        double n = 0.0;
        if (this.dataset == null || this.dataset.isEmpty()) {
            return 1.0;
        }
        for (int i = 0; i < this.dataset.size(); ++i) {
            final DataSet set = this.dataset.elementAt(i);
            if (i == 0) {
                n = set.getXmax();
            }
            else {
                n = Math.max(n, set.getXmax());
            }
        }
        return n;
    }
    
    public double getYmax() {
        double n = 0.0;
        if (this.dataset == null || this.dataset.isEmpty()) {
            return 1.0;
        }
        for (int i = 0; i < this.dataset.size(); ++i) {
            final DataSet set = this.dataset.elementAt(i);
            if (i == 0) {
                n = set.getYmax();
            }
            else {
                n = Math.max(n, set.getYmax());
            }
        }
        return n;
    }
    
    public double getXmin() {
        double n = 0.0;
        if (this.dataset == null || this.dataset.isEmpty()) {
            return n;
        }
        for (int i = 0; i < this.dataset.size(); ++i) {
            final DataSet set = this.dataset.elementAt(i);
            if (i == 0) {
                n = set.getXmin();
            }
            else {
                n = Math.min(n, set.getXmin());
            }
        }
        return n;
    }
    
    public double getYmin() {
        double n = 0.0;
        if (this.dataset == null || this.dataset.isEmpty()) {
            return n;
        }
        for (int i = 0; i < this.dataset.size(); ++i) {
            final DataSet set = this.dataset.elementAt(i);
            if (i == 0) {
                n = set.getYmin();
            }
            else {
                n = Math.min(n, set.getYmin());
            }
        }
        return n;
    }
    
    public void setMarkers(final Markers markers) {
        this.markers = markers;
    }
    
    public Markers getMarkers() {
        return this.markers;
    }
    
    public void setGraphBackground(final Color background) {
        if (background == null) {
            return;
        }
        this.setBackground(background);
    }
    
    public void setDataBackground(final Color dataBackground) {
        if (dataBackground == null) {
            return;
        }
        this.DataBackground = dataBackground;
    }
    
    public Color getDataBackground() {
        if (this.DataBackground == null) {
            return this.DataBackground = this.getBackground();
        }
        return this.DataBackground;
    }
    
    public void adjustScale() {
    }
    
    public void paint(final Graphics graphics) {
        this.paint(graphics, this.getBounds());
    }
    
    public void paint(final Graphics graphics, Rectangle drawAxis) {
        this.adjustScale();
        final Graphics create = graphics.create();
        drawAxis.x = 0;
        drawAxis.y = 0;
        if (this.DefaultBackground == null) {
            this.DefaultBackground = this.getBackground();
        }
        if (this.DataBackground == null) {
            this.DataBackground = this.getBackground();
        }
        if (!this.paintAll) {
            return;
        }
        final Rectangle rectangle = drawAxis;
        rectangle.x += this.borderLeft;
        final Rectangle rectangle2 = drawAxis;
        rectangle2.y += this.borderTop;
        final Rectangle rectangle3 = drawAxis;
        rectangle3.width -= this.borderLeft + this.borderRight;
        final Rectangle rectangle4 = drawAxis;
        rectangle4.height -= this.borderBottom + this.borderTop;
        this.paintFirst(create, drawAxis);
        if (!this.axis.isEmpty()) {
            drawAxis = this.drawAxis(create, drawAxis);
        }
        else {
            if (this.clearAll) {
                final Color color = graphics.getColor();
                graphics.setColor(this.DataBackground);
                graphics.fillRect(drawAxis.x, drawAxis.y, drawAxis.width, drawAxis.height);
                graphics.setColor(color);
            }
            this.drawFrame(create, drawAxis.x, drawAxis.y, drawAxis.width, drawAxis.height);
        }
        this.paintBeforeData(create, drawAxis);
        this.paintFunctions(create, drawAxis);
        this.datarect.x = drawAxis.x;
        this.datarect.y = drawAxis.y;
        this.datarect.width = drawAxis.width;
        this.datarect.height = drawAxis.height;
        if (!this.dataset.isEmpty()) {
            for (int i = 0; i < this.dataset.size(); ++i) {
                ((DataSet)this.dataset.elementAt(i)).draw_data(create, drawAxis);
            }
        }
        this.paintLast(create, drawAxis);
        create.dispose();
    }
    
    public void paintFirst(final Graphics graphics, final Rectangle rectangle) {
    }
    
    public void paintBeforeData(final Graphics graphics, final Rectangle rectangle) {
    }
    
    public void paintFunctions(final Graphics graphics, final Rectangle rectangle) {
    }
    
    public void paintLast(final Graphics graphics, final Rectangle rectangle) {
        if (this.lastText != null) {
            this.lastText.draw(graphics, rectangle.width / 2, rectangle.height / 2, 0);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.clearAll) {
            final Color color = graphics.getColor();
            final Rectangle bounds = this.getBounds();
            bounds.x = 0;
            bounds.y = 0;
            graphics.setColor(this.getBackground());
            graphics.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            graphics.setColor(color);
        }
        if (this.paintAll) {
            this.paint(graphics);
        }
    }
    
    public void startedloading() {
        ++this.loadingData;
        if (this.loadingData != 1) {
            return;
        }
        if (this.load_thread == null) {
            this.load_thread = new LoadMessage(this);
        }
        this.load_thread.setFont(new Font("Helvetica", 0, 25));
        this.load_thread.begin();
    }
    
    public void finishedloading() {
        --this.loadingData;
        if (this.loadingData > 0) {
            return;
        }
        if (this.load_thread != null) {
            this.load_thread.end();
        }
        this.load_thread = null;
    }
    
    public void loadmessage(final String message) {
        if (this.load_thread == null) {
            this.load_thread = new LoadMessage(this);
        }
        this.load_thread.setMessage(message);
    }
    
    protected Rectangle ForceSquare(final Graphics graphics, final Rectangle rectangle) {
        int x = rectangle.x;
        int y = rectangle.y;
        int width = rectangle.width;
        int height = rectangle.height;
        double max = 0.0;
        double max2 = 0.0;
        if (this.dataset == null || this.dataset.isEmpty()) {
            return rectangle;
        }
        for (int i = 0; i < this.axis.size(); ++i) {
            final Axis axis = this.axis.elementAt(i);
            final double n = axis.maximum - axis.minimum;
            if (axis.isVertical()) {
                max2 = Math.max(n, max2);
            }
            else {
                max = Math.max(n, max);
            }
        }
        if (max <= 0 || max2 <= 0) {
            return rectangle;
        }
        double n2;
        if (max > max2) {
            n2 = max;
        }
        else {
            n2 = max2;
        }
        for (int j = 0; j < this.axis.size(); ++j) {
            final Axis axis2 = this.axis.elementAt(j);
            axis2.maximum = axis2.minimum + n2;
        }
        final Rectangle dataRectangle = this.getDataRectangle(graphics, rectangle);
        if (dataRectangle.width > dataRectangle.height) {
            x += (int)((dataRectangle.width - dataRectangle.height) / 2.0);
            width -= dataRectangle.width - dataRectangle.height;
        }
        else {
            y += (int)((dataRectangle.height - dataRectangle.width) / 2.0);
            height -= dataRectangle.height - dataRectangle.width;
        }
        return new Rectangle(x, y, width, height);
    }
    
    protected Rectangle getDataRectangle(final Graphics graphics, final Rectangle rectangle) {
        int x = rectangle.x;
        int y = rectangle.y;
        int width = rectangle.width;
        int height = rectangle.height;
        for (int i = 0; i < this.axis.size(); ++i) {
            final Axis axis = this.axis.elementAt(i);
            final int axisWidth = axis.getAxisWidth(graphics);
            switch (axis.getAxisPos()) {
                case 2: {
                    x += axisWidth;
                    width -= axisWidth;
                    break;
                }
                case 3: {
                    width -= axisWidth;
                    break;
                }
                case 4: {
                    y += axisWidth;
                    height -= axisWidth;
                    break;
                }
                case 5: {
                    height -= axisWidth;
                    break;
                }
            }
        }
        return new Rectangle(x, y, width, height);
    }
    
    protected Rectangle drawAxis(final Graphics graphics, Rectangle forceSquare) {
        try {
            if (this.square) {
                forceSquare = this.ForceSquare(graphics, forceSquare);
            }
            Rectangle dataRectangle;
            if (!this.showAxis) {
                dataRectangle = forceSquare;
            }
            else {
                dataRectangle = this.getDataRectangle(graphics, forceSquare);
            }
            final int x = dataRectangle.x;
            final int y = dataRectangle.y;
            final int width = dataRectangle.width;
            final int height = dataRectangle.height;
            if (this.clearAll) {
                final Color color = graphics.getColor();
                graphics.setColor(this.DataBackground);
                graphics.fillRect(x, y, width, height);
                graphics.setColor(color);
            }
            if (this.frame || !this.showAxis) {
                this.drawFrame(graphics, x, y, width, height);
            }
            for (int i = 0; i < this.axis.size(); ++i) {
                final Axis axis = this.axis.elementAt(i);
                axis.data_window = new Dimension(width, height);
                switch (axis.getAxisPos()) {
                    case 2: {
                        if (this.showAxis) {
                            final Rectangle rectangle = forceSquare;
                            rectangle.x += axis.width;
                        }
                        if (this.showAxis) {
                            final Rectangle rectangle2 = forceSquare;
                            rectangle2.width -= axis.width;
                        }
                        axis.positionAxis(forceSquare.x, forceSquare.x, y, y + height);
                        if (forceSquare.x == x) {
                            axis.gridcolor = this.gridcolor;
                            axis.drawgrid = this.drawgrid;
                            axis.zerocolor = this.zerocolor;
                            axis.drawzero = this.drawzero;
                        }
                        axis.drawAxis(graphics);
                        axis.drawgrid = false;
                        axis.drawzero = false;
                        break;
                    }
                    case 3: {
                        if (this.showAxis) {
                            final Rectangle rectangle3 = forceSquare;
                            rectangle3.width -= axis.width;
                        }
                        axis.positionAxis(forceSquare.x + forceSquare.width, forceSquare.x + forceSquare.width, y, y + height);
                        if (forceSquare.x + forceSquare.width == x + width) {
                            axis.gridcolor = this.gridcolor;
                            axis.drawgrid = this.drawgrid;
                            axis.zerocolor = this.zerocolor;
                            axis.drawzero = this.drawzero;
                        }
                        axis.drawAxis(graphics);
                        axis.drawgrid = false;
                        axis.drawzero = false;
                        break;
                    }
                    case 4: {
                        if (this.showAxis) {
                            final Rectangle rectangle4 = forceSquare;
                            rectangle4.y += axis.width;
                        }
                        if (this.showAxis) {
                            final Rectangle rectangle5 = forceSquare;
                            rectangle5.height -= axis.width;
                        }
                        axis.positionAxis(x, x + width, forceSquare.y, forceSquare.y);
                        if (forceSquare.y == y) {
                            axis.gridcolor = this.gridcolor;
                            axis.drawgrid = this.drawgrid;
                            axis.zerocolor = this.zerocolor;
                            axis.drawzero = this.drawzero;
                        }
                        axis.drawAxis(graphics);
                        axis.drawgrid = false;
                        axis.drawzero = false;
                        break;
                    }
                    case 5: {
                        if (this.showAxis) {
                            final Rectangle rectangle6 = forceSquare;
                            rectangle6.height -= axis.width;
                        }
                        axis.positionAxis(x, x + width, forceSquare.y + forceSquare.height, forceSquare.y + forceSquare.height);
                        if (forceSquare.y + forceSquare.height == y + height) {
                            axis.gridcolor = this.gridcolor;
                            axis.drawgrid = this.drawgrid;
                            axis.zerocolor = this.zerocolor;
                            axis.drawzero = this.drawzero;
                        }
                        axis.drawAxis(graphics);
                        axis.drawgrid = false;
                        axis.drawzero = false;
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {}
        return forceSquare;
    }
    
    protected void drawFrame(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Color black = Color.black;
        if (this.framecolor != null) {
            graphics.setColor(this.framecolor);
        }
        if (this.showAxis) {
            graphics.drawRect(n, n2, n3, n4);
        }
        else {
            graphics.drawRect(n, n2, n3 - 1, n4 - 1);
        }
        graphics.setColor(black);
    }
}
