// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt;

import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import symantec.itools.awt.util.ColorUtils;
import java.awt.Font;
import java.awt.FontMetrics;
import java.beans.PropertyChangeListener;
import java.awt.event.ContainerEvent;
import java.awt.Graphics;
import java.beans.VetoableChangeListener;
import symantec.itools.util.GeneralUtils;
import java.beans.PropertyVetoException;
import java.awt.Component;
import java.awt.LayoutManager;
import symantec.itools.beans.PropertyChangeSupport;
import symantec.itools.beans.VetoableChangeSupport;
import java.util.Vector;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ContainerListener;
import java.awt.Panel;

public class BorderPanel extends Panel implements AlignStyle, BevelStyle, ContainerListener
{
    Dimension oldSize;
    protected static final int labelpadx = 10;
    protected static final int labelipadx = 4;
    protected Color cachedBackground;
    protected Color borderColor;
    protected Color bevelLighterColor;
    protected Color bevelDarkerColor;
    protected Color labelColor;
    protected int padtop;
    protected int padbottom;
    protected int padleft;
    protected int padright;
    protected int ixPad;
    protected int iyPadTop;
    protected int iyPadBottom;
    protected int style;
    protected String label;
    protected int labelAlignment;
    protected Insets internalInsets;
    protected Panel panel;
    private Vector cListeners;
    private Veto veto;
    private VetoableChangeSupport vetos;
    private PropertyChangeSupport changes;
    
    public BorderPanel() {
        this(null, 1, 2);
    }
    
    public BorderPanel(final int style) {
        this(null, 1, style);
    }
    
    public BorderPanel(final String s) {
        this(s, 1, 2);
    }
    
    public BorderPanel(final String s, final int alignment) {
        this(s, alignment, 2);
    }
    
    public BorderPanel(final String s, final int alignment, final int style) {
        this.oldSize = new Dimension();
        this.vetos = new VetoableChangeSupport(this);
        this.changes = new PropertyChangeSupport(this);
        this.borderColor = Color.black;
        this.labelColor = Color.black;
        this.padleft = 6;
        this.padright = 6;
        this.padtop = 10;
        this.padbottom = 6;
        this.ixPad = 4;
        this.iyPadTop = 2;
        this.iyPadBottom = 7;
        this.cachedBackground = this.getBackground();
        this.label = ((s != null && s.length() == 0) ? null : s);
        this.labelAlignment = alignment;
        this.internalInsets = new Insets(10, 10, 10, 10);
        this.style = style;
        super.setLayout(null);
        super.add(this.panel = new Panel());
        if (this.panel != null) {
            this.panel.setLayout(null);
        }
        this.sizepanel(true);
        this.cListeners = new Vector();
    }
    
    public void setPaddingTop(final int newPadTop) throws PropertyVetoException {
        if (this.padtop != newPadTop) {
            final Integer oldPadTopInt = new Integer(this.padtop);
            final Integer newPadTopInt = new Integer(newPadTop);
            this.vetos.fireVetoableChange("PaddingTop", oldPadTopInt, newPadTopInt);
            this.padtop = newPadTop;
            this.changes.firePropertyChange("PaddingTop", oldPadTopInt, newPadTopInt);
            this.sizepanel(true);
            this.invalidate();
        }
    }
    
    public int getPaddingTop() {
        return this.padtop;
    }
    
    public void setPaddingBottom(final int newPadBottom) throws PropertyVetoException {
        if (this.padbottom != newPadBottom) {
            final Integer oldPadBottomInt = new Integer(this.padbottom);
            final Integer newPadBottomInt = new Integer(newPadBottom);
            this.vetos.fireVetoableChange("PaddingBottom", oldPadBottomInt, newPadBottomInt);
            this.padbottom = newPadBottom;
            this.changes.firePropertyChange("PaddingBottom", oldPadBottomInt, newPadBottomInt);
            this.sizepanel(true);
            this.invalidate();
        }
    }
    
    public int getPaddingBottom() {
        return this.padbottom;
    }
    
    public void setPaddingLeft(final int newPadLeft) throws PropertyVetoException {
        if (this.padleft != newPadLeft) {
            final Integer oldPadLeftInt = new Integer(this.padleft);
            final Integer newPadLeftInt = new Integer(newPadLeft);
            this.vetos.fireVetoableChange("PaddingLeft", oldPadLeftInt, newPadLeftInt);
            this.padleft = newPadLeft;
            this.changes.firePropertyChange("PaddingLeft", oldPadLeftInt, newPadLeftInt);
            this.sizepanel(true);
            this.invalidate();
        }
    }
    
    public int getPaddingLeft() {
        return this.padleft;
    }
    
    public void setPaddingRight(final int newPadRight) throws PropertyVetoException {
        if (this.padright != newPadRight) {
            final Integer oldPadRightInt = new Integer(this.padright);
            final Integer newPadRightInt = new Integer(newPadRight);
            this.vetos.fireVetoableChange("PaddingRight", oldPadRightInt, newPadRightInt);
            this.padright = newPadRight;
            this.changes.firePropertyChange("PaddingRight", oldPadRightInt, newPadRightInt);
            this.sizepanel(true);
            this.invalidate();
        }
    }
    
    public int getPaddingRight() {
        return this.padright;
    }
    
    public void setIPadTop(final int t) throws PropertyVetoException {
        if (this.iyPadTop != t) {
            final Integer oldPadTopInt = new Integer(this.iyPadTop);
            final Integer newPadTopInt = new Integer(t);
            this.vetos.fireVetoableChange("IPadTop", oldPadTopInt, newPadTopInt);
            this.iyPadTop = t;
            this.changes.firePropertyChange("IPadTop", oldPadTopInt, newPadTopInt);
            this.sizepanel(true);
            this.invalidate();
        }
    }
    
    public int getIPadTop() {
        return this.iyPadTop;
    }
    
    public void setIPadBottom(final int b) throws PropertyVetoException {
        if (this.iyPadBottom != b) {
            final Integer oldPadBottomInt = new Integer(this.iyPadBottom);
            final Integer newPadBottomInt = new Integer(b);
            this.vetos.fireVetoableChange("IPadBottom", oldPadBottomInt, newPadBottomInt);
            this.iyPadBottom = b;
            this.changes.firePropertyChange("IPadBottom", oldPadBottomInt, newPadBottomInt);
            this.sizepanel(true);
            this.invalidate();
        }
    }
    
    public int getIPadBottom() {
        return this.iyPadBottom;
    }
    
    public void setIPadSides(final int s) throws PropertyVetoException {
        if (this.ixPad != s) {
            final Integer oldIPadSidesInt = new Integer(this.ixPad);
            final Integer newIPadSidesInt = new Integer(s);
            this.vetos.fireVetoableChange("IPadSides", oldIPadSidesInt, newIPadSidesInt);
            this.ixPad = s;
            this.changes.firePropertyChange("IPadSides", oldIPadSidesInt, newIPadSidesInt);
            this.sizepanel(true);
            this.invalidate();
        }
    }
    
    public int getIPadSides() {
        return this.ixPad;
    }
    
    public void setLabel(String newLabel) throws PropertyVetoException {
        if (newLabel != null && newLabel.length() == 0) {
            newLabel = null;
        }
        if (!GeneralUtils.objectsEqual(this.label, newLabel)) {
            final String oldLabel = this.label;
            this.vetos.fireVetoableChange("Label", oldLabel, newLabel);
            this.label = newLabel;
            this.changes.firePropertyChange("Label", oldLabel, newLabel);
            this.sizepanel(true);
            this.repaint();
        }
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setBorderColor(final Color newBorderColor) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.borderColor, newBorderColor)) {
            final Color oldBorderColor = this.borderColor;
            this.vetos.fireVetoableChange("BorderColor", oldBorderColor, newBorderColor);
            this.borderColor = newBorderColor;
            this.changes.firePropertyChange("BorderColor", oldBorderColor, newBorderColor);
            this.repaint();
        }
    }
    
    public Color getBorderColor() {
        return this.borderColor;
    }
    
    public void setLabelColor(final Color newLabelBorderColor) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.labelColor, newLabelBorderColor)) {
            final Color oldLabelColor = this.labelColor;
            this.vetos.fireVetoableChange("LabelColor", oldLabelColor, newLabelBorderColor);
            this.labelColor = newLabelBorderColor;
            this.changes.firePropertyChange("LabelColor", oldLabelColor, newLabelBorderColor);
            this.repaint();
        }
    }
    
    public Color getLabelColor() {
        return this.labelColor;
    }
    
    public void setAlignStyle(final int newLabelAlignment) throws PropertyVetoException {
        if (this.labelAlignment != newLabelAlignment) {
            final Integer oldLabelAlignmentInteger = new Integer(this.labelAlignment);
            final Integer newLabelAlignmentInteger = new Integer(newLabelAlignment);
            this.vetos.fireVetoableChange("AlignStyle", oldLabelAlignmentInteger, newLabelAlignmentInteger);
            this.labelAlignment = newLabelAlignment;
            this.changes.firePropertyChange("AlignStyle", oldLabelAlignmentInteger, newLabelAlignmentInteger);
            this.sizepanel(true);
            this.repaint();
        }
    }
    
    public int getAlignStyle() {
        return this.labelAlignment;
    }
    
    public void setBevelStyle(final int newBevelStyle) throws PropertyVetoException {
        if (this.style != newBevelStyle) {
            final Integer oldBevelStyleInteger = new Integer(this.style);
            final Integer newBevelStyleInteger = new Integer(newBevelStyle);
            this.vetos.fireVetoableChange("BevelStyle", oldBevelStyleInteger, newBevelStyleInteger);
            this.style = newBevelStyle;
            this.changes.firePropertyChange("BevelStyle", oldBevelStyleInteger, newBevelStyleInteger);
            this.repaint();
        }
    }
    
    public int getBevelStyle() {
        return this.style;
    }
    
    public void setInternalInsets(final Insets newInsets) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.internalInsets, newInsets)) {
            final Insets oldInsets = this.internalInsets;
            this.vetos.fireVetoableChange("InternalInsets", oldInsets, newInsets);
            this.internalInsets = newInsets;
            this.changes.firePropertyChange("InternalInsets", oldInsets, newInsets);
            this.sizepanel(true);
            this.invalidate();
        }
    }
    
    public Insets getInternalInsets() {
        return this.internalInsets;
    }
    
    public void setPadding(final int t, final int b, final int l, final int r) throws PropertyVetoException {
        this.setPaddingTop(t);
        this.setPaddingBottom(b);
        this.setPaddingLeft(l);
        this.setPaddingRight(r);
    }
    
    public void setBorderColor(final Color clr, final boolean useForLabel) throws PropertyVetoException {
        this.setBorderColor(clr);
        if (useForLabel) {
            this.setLabelColor(clr);
        }
    }
    
    public Dimension getPreferredSize() {
        final Dimension p = this.size();
        final Dimension m = this.getMinimumSize();
        return new Dimension(Math.max(p.width, m.width), Math.max(p.height, m.height));
    }
    
    public Dimension preferredSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(20, 40);
    }
    
    public Dimension minimumSize() {
        return this.getMinimumSize();
    }
    
    public void setLayout(final LayoutManager l) {
        if (this.panel != null) {
            this.panel.setLayout(l);
        }
    }
    
    public LayoutManager getLayout() {
        return this.panel.getLayout();
    }
    
    public Component getComponent(final int i) {
        return this.panel.getComponent(i);
    }
    
    public Component[] getComponents() {
        return this.panel.getComponents();
    }
    
    public int getComponentCount() {
        return this.panel.getComponentCount();
    }
    
    public boolean isValidBevelStyle(final int bevelStyle) {
        switch (bevelStyle) {
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
    
    public synchronized void addNotify() {
        super.addNotify();
        this.sizepanel(true);
        if (this.veto == null) {
            this.addBevelStyleListener(this.veto = new Veto());
        }
        if (this.panel != null) {
            this.panel.addContainerListener(this);
        }
    }
    
    public synchronized void removeNotify() {
        if (this.veto != null) {
            this.removeBevelStyleListener(this.veto);
            this.veto = null;
        }
        if (this.panel != null) {
            this.panel.removeContainerListener(this);
        }
        super.removeNotify();
    }
    
    public void layout() {
        this.sizepanel(false);
        this.panel.layout();
    }
    
    protected void addImpl(final Component comp, final Object constraints, final int index) {
        if (comp == this.panel) {
            super.addImpl(comp, constraints, index);
        }
        else {
            this.panel.add(comp, constraints, index);
        }
    }
    
    public void remove(final int index) {
        this.panel.remove(index);
    }
    
    public void remove(final Component comp) {
        this.panel.remove(comp);
    }
    
    public void removeAll() {
        this.panel.removeAll();
    }
    
    public void setBounds(final int x, final int y, final int width, final int height) {
        super.setBounds(x, y, width, height);
        this.sizepanel(false);
    }
    
    public void reshape(final int x, final int y, final int width, final int height) {
        super.reshape(x, y, width, height);
        this.sizepanel(false);
    }
    
    public void update(final Graphics g) {
        final Dimension s = this.size();
        final Insets insets = this.insets();
        g.setColor(this.getBackground());
        if (insets.left > 0) {
            g.fillRect(0, 0, insets.left, s.height);
        }
        if (insets.top > 0) {
            g.fillRect(0, 0, s.width, insets.top);
        }
        if (insets.bottom > 0) {
            g.fillRect(0, s.height - insets.bottom, s.width, insets.bottom);
        }
        if (insets.right > 0) {
            g.fillRect(s.width - insets.right, 0, insets.right, s.height);
        }
        this.paint(g);
        this.panel.repaint();
    }
    
    public int countComponents() {
        return this.panel.countComponents();
    }
    
    public Insets insets() {
        final int h = this.getLabelTopMargin();
        final Insets insets = this.getInternalInsets();
        return new Insets(h + insets.top, insets.left, insets.bottom, insets.right);
    }
    
    public void paint(final Graphics g) {
        this.sizepanel(false);
        final Color curBackground = this.getBackground();
        if (!GeneralUtils.objectsEqual(curBackground, this.cachedBackground)) {
            this.calculateHilightColors(this.cachedBackground = curBackground);
        }
        g.setColor(curBackground);
        this.draw(g);
    }
    
    public void setBackground(final Color c) {
        super.setBackground(c);
        this.panel.setBackground(c);
    }
    
    public void componentAdded(final ContainerEvent e) {
        if (e.getSource() == this.panel) {
            for (int i = 0; i < this.cListeners.size(); ++i) {
                this.cListeners.elementAt(i).componentAdded(new ContainerEvent(this, 300, e.getChild()));
            }
        }
    }
    
    public void componentRemoved(final ContainerEvent e) {
        if (e.getSource() == this.panel) {
            for (int i = 0; i < this.cListeners.size(); ++i) {
                this.cListeners.elementAt(i).componentRemoved(new ContainerEvent(this, 301, e.getChild()));
            }
        }
    }
    
    public void addContainerListener(final ContainerListener l) {
        if (this.panel != null) {
            this.panel.addContainerListener(l);
            this.cListeners.addElement(l);
        }
    }
    
    public void removeContainerListener(final ContainerListener l) {
        if (this.panel != null) {
            this.panel.removeContainerListener(l);
            this.cListeners.removeElement(l);
        }
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener(listener);
    }
    
    public void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener(listener);
    }
    
    public void addVetoableChangeListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener(listener);
    }
    
    public void removeVetoableChangeListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener(listener);
    }
    
    public void addBevelStyleListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener("BevelStyle", listener);
    }
    
    public void removeBevelStyleListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener("BevelStyle", listener);
    }
    
    public void addBevelStyleListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener("BevelStyle", listener);
    }
    
    public void removeBevelStyleListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener("BevelStyle", listener);
    }
    
    protected void sizepanel(final boolean force) {
        final Dimension s = this.size();
        if (force || this.oldSize.width != s.width || this.oldSize.height != s.height) {
            this.oldSize = s;
            this.panel.setBounds(this.padleft + this.ixPad, this.getLabelTopMargin() + this.iyPadTop, s.width - this.padright - this.padleft - this.ixPad * 2 - 1, s.height - this.padbottom - this.getLabelTopMargin() - this.iyPadBottom - this.iyPadTop - 1);
        }
    }
    
    protected void draw(final Graphics g) {
        final Dimension s = this.size();
        int delta = this.padtop;
        final FontMetrics fm = this.getFontMetrics(this.getFont());
        g.clipRect(0, 0, s.width, s.height);
        if (this.label != null && fm != null) {
            delta = (fm.getAscent() + fm.getDescent() + this.padtop) / 2;
        }
        final int x = this.padleft;
        final int y = delta;
        final int w = s.width - this.padleft - this.padright - 1;
        final int h = s.height - 1 - delta - this.padbottom;
        this.drawBorder(g, x, y, w, h);
        this.drawLabel(g, fm);
    }
    
    protected void drawBorder(final Graphics g, final int x, final int y, final int w, final int h) {
        switch (this.style) {
            default: {
                g.setColor(this.borderColor);
                g.drawRect(x, y, w, h);
            }
            case 3: {}
            case 1: {
                g.setColor(this.bevelLighterColor);
                g.drawLine(x, y, x + w, y);
                g.drawLine(x, y, x, y + h);
                g.setColor(this.bevelDarkerColor);
                g.drawLine(x, y + h, x + w, y + h);
                g.drawLine(x + w, y, x + w, y + h);
            }
            case 0: {
                g.setColor(this.bevelDarkerColor);
                g.drawLine(x, y, x + w, y);
                g.drawLine(x, y, x, y + h);
                g.setColor(this.bevelLighterColor);
                g.drawLine(x, y + h, x + w, y + h);
                g.drawLine(x + w, y, x + w, y + h);
            }
            case 2: {
                g.setColor(this.borderColor);
                g.drawRect(x, y, w, h);
            }
        }
    }
    
    protected void drawLabel(final Graphics g, final FontMetrics fm) {
        if (this.label != null && fm != null) {
            int fWidth = 10;
            final Dimension s = this.size();
            if (this.getFont().getSize() > fWidth) {
                fWidth += this.getFont().getSize() / 2;
            }
            final int stringWidth = fm.stringWidth(this.label);
            final int ascent = fm.getAscent();
            final int descent = fm.getDescent();
            int x = 0;
            switch (this.labelAlignment) {
                case 1: {
                    x = (s.width - stringWidth) / 2;
                    break;
                }
                case 2: {
                    x = s.width - fWidth - (stringWidth + 7);
                    break;
                }
                default: {
                    x = fWidth + 7;
                    break;
                }
            }
            final int h = ascent + descent + this.padtop;
            final int y = (fWidth - h) / 2 + (this.padtop + ascent);
            g.setColor(this.getBackground());
            g.fillRect(x - 2, y - 1 - ascent - this.padtop / 2, stringWidth + 4, h);
            g.setColor(this.labelColor);
            g.drawString(this.label, x, y - 1);
        }
    }
    
    protected int getLabelTopMargin() {
        if (this.label == null) {
            return this.padtop;
        }
        int top = this.padtop;
        final Font font = this.getFont();
        if (font != null) {
            final FontMetrics fm = this.getFontMetrics(font);
            top = fm.getAscent() + fm.getDescent() + this.padtop;
        }
        return top;
    }
    
    protected int getLabelWidthMargin() {
        if (this.label == null) {
            return 0;
        }
        int w = 2 + this.internalInsets.left + this.internalInsets.right;
        final Font font = this.getFont();
        if (font != null) {
            final FontMetrics fm = this.getFontMetrics(font);
            w = Math.max(w, 2 + fm.stringWidth(this.label) + 10 + 4);
        }
        return w;
    }
    
    protected void calculateHilightColors(final Color c) {
        this.bevelLighterColor = ColorUtils.calculateHilightColor(c);
        this.bevelDarkerColor = ColorUtils.calculateShadowColor(c);
    }
    
    class Veto implements VetoableChangeListener, Serializable
    {
        public void vetoableChange(final PropertyChangeEvent e) throws PropertyVetoException {
            final int i = (int)e.getNewValue();
            if (!BorderPanel.this.isValidBevelStyle(i)) {
                throw new PropertyVetoException("Invalid BevelStyle: " + i, e);
            }
        }
    }
}
