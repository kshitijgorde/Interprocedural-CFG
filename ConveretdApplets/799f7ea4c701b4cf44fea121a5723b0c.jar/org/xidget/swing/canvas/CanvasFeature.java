// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.canvas;

import java.util.Iterator;
import javax.swing.JPanel;
import org.xmodel.util.HashMultiMap;
import java.util.ArrayList;
import org.xmodel.util.MultiMap;
import java.util.List;
import org.xidget.IXidget;
import org.xidget.ifeature.canvas.IPaintFeature;
import org.xidget.ifeature.canvas.ICanvasFeature;

public class CanvasFeature implements ICanvasFeature, IPaintFeature
{
    private IXidget xidget;
    private String layer;
    private List<String> layers;
    private MultiMap<String, IXidget> children;
    
    public CanvasFeature(final IXidget xidget) {
        this.xidget = xidget;
    }
    
    @Override
    public void addLayer(final String s, final int n) {
        if (this.layers == null) {
            this.layers = new ArrayList<String>();
        }
        if (this.layers.size() <= n) {
            for (int i = n; i <= this.layers.size(); ++i) {
                this.layers.add(null);
            }
        }
        this.layers.set(n, s);
    }
    
    @Override
    public void removeLayer(final String s) {
        for (int i = 0; i < this.layers.size(); ++i) {
            if (this.layers.get(i).equals(s)) {
                this.layers.set(i, null);
                break;
            }
        }
    }
    
    @Override
    public void addChild(final IXidget xidget) {
        if (this.children == null) {
            this.children = new HashMultiMap<String, IXidget>();
        }
        final IPaintFeature paintFeature = xidget.getFeature(IPaintFeature.class);
        if (paintFeature != null) {
            this.children.put(paintFeature.getLayer(), xidget);
        }
    }
    
    @Override
    public void removeChild(final IXidget xidget) {
        if (this.children == null) {
            return;
        }
        final IPaintFeature paintFeature = xidget.getFeature(IPaintFeature.class);
        if (paintFeature != null) {
            this.children.remove(paintFeature.getLayer(), xidget);
        }
    }
    
    @Override
    public int getHeight() {
        return this.xidget.getFeature(JPanel.class).getWidth();
    }
    
    @Override
    public int getWidth() {
        return this.xidget.getFeature(JPanel.class).getHeight();
    }
    
    @Override
    public void repaint() {
        this.xidget.getFeature(JPanel.class).repaint();
    }
    
    @Override
    public void repaint(final int n, final int n2, final int n3, final int n4) {
        this.xidget.getFeature(JPanel.class).repaint(n, n2, n3, n4);
    }
    
    @Override
    public void setLayer(final String layer) {
        this.layer = layer;
    }
    
    @Override
    public String getLayer() {
        return this.layer;
    }
    
    @Override
    public void paint(final Object o) {
        final Iterator<String> iterator = this.layers.iterator();
        while (iterator.hasNext()) {
            for (final IXidget xidget : this.children.get(iterator.next())) {
                if (xidget != null) {
                    final IPaintFeature<Object> paintFeature = xidget.getFeature((Class<IPaintFeature<Object>>)IPaintFeature.class);
                    if (paintFeature == null) {
                        continue;
                    }
                    paintFeature.paint(o);
                }
            }
        }
    }
}
