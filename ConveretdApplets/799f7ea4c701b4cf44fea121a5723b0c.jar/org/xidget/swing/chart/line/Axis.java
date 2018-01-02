// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.chart.line;

import org.xidget.IXidget;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import org.xidget.chart.Scale;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.ComponentListener;
import org.xidget.ifeature.chart.IAxisFeature;
import javax.swing.JPanel;

public abstract class Axis extends JPanel implements IAxisFeature
{
    private ComponentListener resizeListener;
    private MouseMotionListener mouseListener;
    private MouseWheelListener wheelListener;
    protected LineChart graph;
    protected String axis;
    protected Scale.Format format;
    protected Scale scale;
    protected double min;
    protected double max;
    protected double log;
    protected double cursor;
    protected int textDepth;
    
    public Axis() {
        this.resizeListener = new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent componentEvent) {
                Axis.this.scale = null;
                if (Axis.this.graph != null) {
                    Axis.this.graph.axisResized(Axis.this.axis);
                }
            }
        };
        this.mouseListener = new MouseAdapter() {
            @Override
            public void mouseMoved(final MouseEvent mouseEvent) {
                if (Axis.this.scale != null) {
                    Axis.this.mouseMoved(mouseEvent.getX(), mouseEvent.getY());
                }
            }
        };
        this.wheelListener = new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
                if (Axis.this.scale != null) {
                    Axis.this.mouseWheelMoved(mouseWheelEvent.getWheelRotation());
                }
            }
        };
        this.format = Scale.Format.decimal;
        this.min = 0.0;
        this.max = 1.0;
        this.log = 0.0;
        this.setFont(Font.decode("times-10"));
        this.setBackground(Color.white);
        this.addComponentListener(this.resizeListener);
        this.addMouseMotionListener(this.mouseListener);
        this.addMouseWheelListener(this.wheelListener);
    }
    
    @Override
    public void setGraph(final String axis, final IXidget xidget) {
        this.axis = axis;
        this.graph = xidget.getFeature(LineChart.class);
    }
    
    @Override
    public void setExtrema(final double min, final double max) {
        this.min = min;
        this.max = max;
        this.scale = null;
        this.repaint();
    }
    
    @Override
    public void setLogBase(final int n) {
        this.log = n;
        this.scale = null;
        this.repaint();
    }
    
    @Override
    public void setFormat(final Scale.Format format) {
        this.format = format;
        this.scale = null;
        this.repaint();
    }
    
    public abstract Scale getScale();
    
    protected abstract void mouseMoved(final int p0, final int p1);
    
    protected abstract void mouseWheelMoved(final int p0);
}
