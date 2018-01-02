// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt.shape;

import symantec.itools.awt.util.ColorUtils;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeListener;
import java.awt.Graphics;
import java.awt.Dimension;
import symantec.itools.util.GeneralUtils;
import java.beans.PropertyVetoException;
import symantec.itools.beans.PropertyChangeSupport;
import symantec.itools.beans.VetoableChangeSupport;
import java.awt.Color;
import symantec.itools.awt.BevelStyle;
import java.awt.Component;

public abstract class Shape extends Component implements BevelStyle
{
    protected int width;
    protected int height;
    protected int style;
    protected boolean fill;
    protected Color fillColor;
    protected Color bevelLighterColor;
    protected Color bevelDarkerColor;
    protected Color cachedBackground;
    protected VetoableChangeSupport vetos;
    protected PropertyChangeSupport changes;
    
    protected Shape() {
        this.fillColor = Color.black;
        this.vetos = new VetoableChangeSupport(this);
        this.changes = new PropertyChangeSupport(this);
        this.style = 2;
        this.cachedBackground = this.getBackground();
    }
    
    public void setBevelStyle(final int s) throws PropertyVetoException {
        if (this.style != s) {
            final Integer oldValue = new Integer(this.style);
            final Integer newValue = new Integer(s);
            this.vetos.fireVetoableChange("BevelStyle", oldValue, newValue);
            this.style = s;
            this.repaint();
            this.changes.firePropertyChange("BevelStyle", oldValue, newValue);
        }
    }
    
    public int getBevelStyle() {
        return this.style;
    }
    
    public void setFillMode(final boolean f) throws PropertyVetoException {
        if (this.fill != f) {
            final Boolean oldValue = new Boolean(this.fill);
            final Boolean newValue = new Boolean(f);
            this.vetos.fireVetoableChange("FillMode", oldValue, newValue);
            this.fill = f;
            this.repaint();
            this.changes.firePropertyChange("FillMode", oldValue, newValue);
        }
    }
    
    public boolean isFillMode() {
        return this.fill;
    }
    
    public boolean getFillMode() {
        return this.isFillMode();
    }
    
    public void setFillColor(final Color color) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.fillColor, color)) {
            final Boolean oldValue = new Boolean(this.fill);
            this.vetos.fireVetoableChange("FillColor", oldValue, color);
            this.fillColor = color;
            this.repaint();
            this.changes.firePropertyChange("FillColor", oldValue, color);
        }
    }
    
    public Color getFillColor() {
        return this.fillColor;
    }
    
    public void reshape(final int x, final int y, final int width, final int height) {
        super.reshape(x, y, this.width = width, this.height = height);
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(10, 10);
    }
    
    public Dimension getPreferredSize() {
        final Dimension dim = this.size();
        final Dimension min = this.getMinimumSize();
        return new Dimension(Math.max(dim.width, min.width), Math.max(dim.height, min.height));
    }
    
    public Dimension preferredSize() {
        return this.getPreferredSize();
    }
    
    public synchronized void paint(final Graphics g) {
        final Color curBackground = this.getBackground();
        if (!GeneralUtils.objectsEqual(curBackground, this.cachedBackground)) {
            this.updateBevelColors(this.cachedBackground = curBackground);
        }
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener(listener);
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener(listener);
    }
    
    public synchronized void addVetoableChangeListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener(listener);
    }
    
    public synchronized void removeVetoableChangeListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener(listener);
    }
    
    protected void updateBevelColors(final Color c) {
        this.bevelDarkerColor = ColorUtils.calculateShadowColor(c);
        this.bevelLighterColor = ColorUtils.calculateHilightColor(c);
    }
}
