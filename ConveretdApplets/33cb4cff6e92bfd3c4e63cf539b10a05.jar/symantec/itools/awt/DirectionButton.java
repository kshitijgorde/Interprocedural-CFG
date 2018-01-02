// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt;

import java.beans.PropertyChangeEvent;
import java.awt.Graphics;
import symantec.itools.lang.OS;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import symantec.itools.resources.ErrorsBundle;
import java.awt.Dimension;
import symantec.itools.awt.util.ColorUtils;
import symantec.itools.util.GeneralUtils;
import java.beans.PropertyVetoException;
import symantec.itools.beans.PropertyChangeSupport;
import symantec.itools.beans.VetoableChangeSupport;
import java.util.ResourceBundle;
import java.awt.Color;
import java.io.Serializable;

public class DirectionButton extends ButtonBase implements Serializable
{
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    protected Color arrowColor;
    protected Color disabledArrowColor;
    protected int direction;
    protected int left;
    protected int right;
    protected int top;
    protected int bottom;
    protected int indent;
    protected int tempIndent;
    protected transient ResourceBundle errors;
    private SizeVeto sizeVeto;
    private IndntVeto indentVeto;
    private VetoableChangeSupport vetos;
    private PropertyChangeSupport changes;
    
    public DirectionButton() {
        this(0);
    }
    
    public DirectionButton(final int d) {
        this.vetos = new VetoableChangeSupport(this);
        this.changes = new PropertyChangeSupport(this);
        this.direction = d;
        this.left = 0;
        this.right = 0;
        this.bottom = 0;
        this.indent = 0;
        this.tempIndent = this.indent;
        try {
            this.setArrowColor(Color.black);
        }
        catch (PropertyVetoException ex) {}
    }
    
    public void setDirection(final int d) throws PropertyVetoException {
        if (this.direction != d) {
            final Integer oldValue = new Integer(this.direction);
            final Integer newValue = new Integer(d);
            this.vetos.fireVetoableChange("direction", oldValue, newValue);
            this.direction = d;
            this.repaint();
            this.changes.firePropertyChange("direction", oldValue, newValue);
        }
    }
    
    public int getDirection() {
        return this.direction;
    }
    
    public void setArrowIndent(final int ai) throws PropertyVetoException {
        if (super.isAdded) {
            if (this.indent != ai) {
                final Integer oldValue = new Integer(this.indent);
                final Integer newValue = new Integer(ai);
                this.vetos.fireVetoableChange("arrowIndent", oldValue, newValue);
                this.indent = ai;
                this.tempIndent = ai;
                this.shrinkTriangle(this.left, this.right, this.top, this.bottom);
                this.repaint();
                this.changes.firePropertyChange("arrowIndent", oldValue, newValue);
            }
        }
        else {
            this.tempIndent = ai;
        }
    }
    
    public void setArrowColor(final Color newValue) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.arrowColor, newValue)) {
            final Color oldValue = this.arrowColor;
            this.vetos.fireVetoableChange("arrowColor", oldValue, newValue);
            this.arrowColor = newValue;
            try {
                this.disabledArrowColor = ColorUtils.fade(this.arrowColor, Color.lightGray, 0.5);
            }
            catch (IllegalArgumentException ex) {}
            this.repaint();
            this.changes.firePropertyChange("arrowColor", oldValue, newValue);
        }
    }
    
    public Color getArrowColor() {
        return this.arrowColor;
    }
    
    public int getArrowIndent() {
        return super.isAdded ? this.indent : this.tempIndent;
    }
    
    public void shrinkTriangle(final int l, final int r, final int t, final int b) {
        if (super.isAdded) {
            final Dimension s = this.getSize();
            final int maxWidth = s.width - super.bevel - super.bevel - 2;
            final int maxHeight = s.height - super.bevel - super.bevel - 2;
            if (maxWidth - (l + r + this.indent + this.indent) >= 3) {
                this.left = l;
                this.right = r;
            }
            else {
                this.left = (maxWidth - this.indent - this.indent - 3) / 2;
                this.right = this.left;
            }
            if (maxHeight - (t + b + this.indent + this.indent) >= 3) {
                this.top = t;
                this.bottom = b;
            }
            else {
                this.top = (maxHeight - this.indent - this.indent - 3) / 2;
                this.bottom = this.top;
            }
        }
    }
    
    public Dimension getPreferredSize() {
        final Dimension defaultSize = super.getPreferredSize();
        return new Dimension(defaultSize.width + 7, defaultSize.height + 7);
    }
    
    public Dimension getMinimumSize() {
        final Dimension defaultSize = super.getPreferredSize();
        return new Dimension(defaultSize.width + 3, defaultSize.height + 3);
    }
    
    public synchronized void addNotify() {
        try {
            this.errors = ResourceBundle.getBundle("symantec.itools.resources.ErrorsBundle");
        }
        catch (Throwable t) {
            this.errors = new ErrorsBundle();
        }
        if (this.sizeVeto == null) {
            this.addDirectionListener(this.sizeVeto = new SizeVeto());
        }
        if (this.indentVeto == null) {
            this.addArrowIndentListener(this.indentVeto = new IndntVeto());
        }
        super.addNotify();
    }
    
    public synchronized void removeNotify() {
        if (this.sizeVeto != null) {
            this.removeDirectionListener(this.sizeVeto);
            this.sizeVeto = null;
        }
        if (this.indentVeto != null) {
            this.removeArrowIndentListener(this.indentVeto);
            this.indentVeto = null;
        }
        super.removeNotify();
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        this.changes.addPropertyChangeListener(listener);
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener listener) {
        super.removePropertyChangeListener(listener);
        this.changes.removePropertyChangeListener(listener);
    }
    
    public synchronized void addVetoableChangeListener(final VetoableChangeListener listener) {
        super.addVetoableChangeListener(listener);
        this.vetos.addVetoableChangeListener(listener);
    }
    
    public synchronized void removeVetoableChangeListener(final VetoableChangeListener listener) {
        super.removeVetoableChangeListener(listener);
        this.vetos.removeVetoableChangeListener(listener);
    }
    
    public synchronized void addDirectionListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener("direction", listener);
    }
    
    public synchronized void removeDirectionListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener("direction", listener);
    }
    
    public synchronized void addDirectionListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener("direction", listener);
    }
    
    public synchronized void removeDirectionListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener("direction", listener);
    }
    
    public synchronized void addArrowIndentListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener("arrowIndent", listener);
    }
    
    public synchronized void removeArrowIndentListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener("arrowIndent", listener);
    }
    
    public synchronized void addArrowIndentListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener("arrowIndent", listener);
    }
    
    public synchronized void removeArrowIndentListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener("arrowIndent", listener);
    }
    
    protected void updateButtonImage() {
        super.updateButtonImage();
        final Graphics g = super.buttonImage.getGraphics();
        final Dimension s = this.size();
        final int trueBevel = super.bevel + 1;
        if (this.isEnabled()) {
            g.setColor(this.arrowColor);
        }
        else {
            g.setColor(this.disabledArrowColor);
        }
        final int centerHorizontal = (s.width - 1) / 2 + super.pressedAdjustment;
        final int centerVertical = (s.height - 1) / 2 + super.pressedAdjustment;
        int topSide = this.top + trueBevel - 1 + super.pressedAdjustment + this.indent;
        final int bottomSide = s.height - 1 - this.bottom - trueBevel + super.pressedAdjustment - this.indent;
        int leftSide = this.left + trueBevel - 1 + super.pressedAdjustment + this.indent;
        final int rightSide = s.width - 1 - this.right - trueBevel + super.pressedAdjustment - this.indent;
        if (OS.isMacintosh()) {
            ++leftSide;
            ++topSide;
        }
        switch (this.direction) {
            case 2: {
                this.fillTriangle(g, centerHorizontal, topSide, leftSide, bottomSide, rightSide, bottomSide, this.direction);
                break;
            }
            case 3: {
                this.fillTriangle(g, centerHorizontal, bottomSide, leftSide, topSide, rightSide, topSide, this.direction);
                break;
            }
            case 0: {
                this.fillTriangle(g, leftSide, centerVertical, rightSide, bottomSide, rightSide, topSide, this.direction);
                break;
            }
            case 1: {
                this.fillTriangle(g, rightSide, centerVertical, leftSide, bottomSide, leftSide, topSide, this.direction);
                break;
            }
        }
        if (g != null) {
            g.dispose();
        }
    }
    
    protected void fillTriangle(final Graphics g, final int tipX, final int tipY, final int aX, final int aY, final int bX, final int bY, final int direction) {
        switch (direction) {
            case 2:
            case 3: {
                final int dist = Math.abs(aX - bX);
                int i;
                for (int max = Math.max(aX, bX), min = i = Math.min(aX, bX); i <= max; ++i) {
                    g.drawLine(tipX, tipY, i, aY);
                }
                break;
            }
            case 0:
            case 1: {
                final int dist = Math.abs(aY - bY);
                int i;
                for (int max = Math.max(aY, bY), min = i = Math.min(aY, bY); i <= max; ++i) {
                    g.drawLine(tipX, tipY, aX, i);
                }
                break;
            }
        }
    }
    
    protected boolean isValidBevelSize(final int i) {
        final Dimension s = this.size();
        final int temp = i * 2 + 4;
        return i >= 0 && s.width >= temp && s.height >= temp;
    }
    
    protected boolean isValidDirection(final int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected boolean isValidArrowIndent(final int i) {
        final Dimension s = this.size();
        final int temp = i * 2 + (super.bevel + 1) * 2 + 4;
        return i >= 0 && s.width >= temp && s.height >= temp;
    }
    
    protected void verifyContstrainedPropertyValues() {
        super.verifyContstrainedPropertyValues();
        try {
            this.setArrowIndent(this.tempIndent);
        }
        catch (PropertyVetoException ex) {}
    }
    
    class SizeVeto implements VetoableChangeListener, Serializable
    {
        public void vetoableChange(final PropertyChangeEvent e) throws PropertyVetoException {
            final int i = (int)e.getNewValue();
            if (!DirectionButton.this.isValidDirection(i)) {
                throw new PropertyVetoException(String.valueOf(DirectionButton.this.errors.getString("InvalidDirection")) + i, e);
            }
        }
    }
    
    class IndntVeto implements VetoableChangeListener, Serializable
    {
        public void vetoableChange(final PropertyChangeEvent e) throws PropertyVetoException {
            final int i = (int)e.getNewValue();
            if (!DirectionButton.this.isValidArrowIndent(i)) {
                throw new PropertyVetoException(String.valueOf(DirectionButton.this.errors.getString("InvalidArrowIndent")) + i, e);
            }
        }
    }
}
