// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.util.Vector;

public class Series
{
    private Graph parent;
    private float[] data;
    private float[] scaledData;
    private float min;
    private float max;
    private float yScale;
    private Vector<Float> horizontalLines;
    private Vector<Float> verticalLines;
    private Vector<Float> verticalLines2;
    public static final int LINE_GRAPH = 0;
    public static final int BAR_GRAPH = 1;
    public static final int POINT_GRAPH = 2;
    private boolean scale;
    private int graphType;
    private Color seriesColour;
    private boolean plotPoints;
    
    public Series(final Graph parent) {
        this.scale = true;
        this.parent = parent;
        this.horizontalLines = new Vector<Float>();
        this.verticalLines = new Vector<Float>();
        this.verticalLines2 = new Vector<Float>();
        this.graphType = 0;
        this.plotPoints = false;
        this.seriesColour = Color.BLACK;
    }
    
    public void addHorizontalLine(final float value) {
        this.horizontalLines.addElement(new Float(value));
        this.parent.repaint();
    }
    
    public void addVerticalLine(final float value) {
        this.verticalLines.addElement(new Float(value));
        this.parent.repaint();
    }
    
    public void addVerticalLine2(final float value) {
        this.verticalLines2.addElement(new Float(value));
        this.parent.repaint();
    }
    
    public float[] getData() {
        return this.data;
    }
    
    public void setData(final float[] data) {
        assert data != null && data.length != 0;
        this.data = data;
        this.min = data[0];
        this.max = data[0];
        for (int i = 1; i < data.length; ++i) {
            if (data[i] > this.max) {
                this.max = data[i];
            }
            if (data[i] < this.min) {
                this.min = data[i];
            }
        }
        this.calculateScale();
    }
    
    public float getMin() {
        return this.min;
    }
    
    private void calculateScale() {
        final Dimension bounds = this.parent.getSize();
        if (this.scale) {
            final GraphPanel panel = this.parent.getGraphPanel();
            if (bounds.width > this.data.length * this.parent.getScalingFactor()) {
                this.parent.setHorizontalScrollBarPolicy(31);
            }
            else {
                this.parent.setHorizontalScrollBarPolicy(32);
                bounds.height = this.parent.getViewport().getHeight();
                bounds.width = (int)(this.data.length * this.parent.getScalingFactor());
            }
            this.parent.getGraphPanel().setPreferredSize(bounds);
            this.parent.getGraphPanel().setSize(bounds);
        }
        this.yScale = (bounds.height - 1.0f) / (this.max - this.min);
    }
    
    public void setMin(final float min) {
        this.min = min;
        this.calculateScale();
    }
    
    public float getMax() {
        return this.max;
    }
    
    public void setMax(final float max) {
        this.max = max;
        this.calculateScale();
    }
    
    public void paint(final Graphics g) {
        if (this.data == null) {
            return;
        }
        if (this.data.length < 2) {
            return;
        }
        final Graphics2D g2 = (Graphics2D)g;
        final Dimension bounds = this.parent.getGraphPanel().getSize();
        this.calculateScale();
        final float xScale = this.data.length / bounds.width;
        final Rectangle clipBounds = g.getClipBounds();
        g2.setColor(this.seriesColour);
        final int start = (int)Math.floor(clipBounds.x * xScale);
        final int end = (int)Math.ceil((clipBounds.x + clipBounds.width) * xScale);
        switch (this.graphType) {
            case 0: {
                for (int i = start; i < end - 1 && this.data != null && i < this.data.length; ++i) {
                    final int x1 = Math.round(i / xScale);
                    final int y1 = (int)Math.floor(this.calculateY(this.data[i]));
                    final int x2 = Math.round((i + 1.0f) / xScale);
                    final int y2 = (int)Math.floor(this.calculateY(this.data[i + 1]));
                    g2.drawLine(x1, y1, x2, y2);
                }
                break;
            }
            case 1: {
                for (int i = start; i < end; ++i) {
                    final float x3 = Math.round(i / xScale);
                    final float x4 = Math.round((i + 1.0f) / xScale);
                    if (this.data == null) {
                        break;
                    }
                    if (i >= this.data.length - 1) {
                        break;
                    }
                    final int y3 = this.calculateY(this.data[i]);
                    g2.drawRect((int)x3, y3, (int)x4 - (int)x3, bounds.height);
                }
                break;
            }
            case 2: {
                for (int i = clipBounds.x; i < clipBounds.x + clipBounds.width; ++i) {
                    final int x5 = (int)(i * xScale);
                    if (x5 >= this.data.length) {
                        System.out.println("Too long!!");
                        return;
                    }
                    g2.drawLine(i, this.calculateY(this.data[x5]), i, this.calculateY(this.data[x5]));
                }
                break;
            }
        }
        if (this.plotPoints && this.data != null) {
            try {
                for (int i = 0; i < this.data.length; ++i) {
                    final int y4 = this.calculateY(this.data[i]);
                    g2.drawOval((int)(i / xScale) - 3, y4 - 3, 6, 6);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        g2.setColor(Color.PINK);
        for (int i = 0; i < this.verticalLines2.size(); ++i) {
            final int x5 = (int)(this.verticalLines2.get(i) / xScale);
            g2.drawLine(x5, 0, x5, bounds.height);
        }
        g2.setColor(Color.RED);
        for (int i = 0; i < this.horizontalLines.size(); ++i) {
            final int y4 = this.calculateY(this.horizontalLines.get(i));
            g2.drawLine(0, y4, bounds.width - 1, y4);
        }
        for (int i = 0; i < this.verticalLines.size(); ++i) {
            final int x5 = (int)(this.verticalLines.get(i) / xScale);
            g2.drawLine(x5, 0, x5, bounds.height);
        }
    }
    
    private int calculateY(final float data) {
        return Math.round(this.parent.getGraphPanel().getSize().height - (data + -this.min) * this.yScale);
    }
    
    public int getGraphType() {
        return this.graphType;
    }
    
    public void setGraphType(final int graphType) {
        this.graphType = graphType;
    }
    
    public boolean isPlotPoints() {
        return this.plotPoints;
    }
    
    public void setPlotPoints(final boolean plotPoints) {
        this.plotPoints = plotPoints;
    }
    
    public void clearLines() {
        this.horizontalLines.clear();
        this.verticalLines.clear();
        this.verticalLines2.clear();
    }
    
    public void clear() {
        this.horizontalLines.clear();
        this.verticalLines.clear();
        this.verticalLines2.clear();
        this.data = null;
        this.parent.repaint();
    }
    
    public Color getSeriesColour() {
        return this.seriesColour;
    }
    
    public void setSeriesColour(final Color seriesColour) {
        this.seriesColour = seriesColour;
    }
    
    public boolean isScale() {
        return this.scale;
    }
    
    public void setScale(final boolean scale) {
        this.scale = scale;
    }
}
