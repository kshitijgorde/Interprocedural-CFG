// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt;

import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.beans.PropertyChangeListener;
import java.beans.VetoableChangeListener;
import symantec.itools.resources.ErrorsBundle;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Dimension;
import symantec.itools.awt.util.ColorUtils;
import symantec.itools.util.GeneralUtils;
import java.beans.PropertyVetoException;
import symantec.itools.beans.PropertyChangeSupport;
import symantec.itools.beans.VetoableChangeSupport;
import java.util.ResourceBundle;
import java.awt.Color;

public class LabelButton extends ButtonBase implements AlignStyle
{
    public static final int ALIGN_TOP = 0;
    public static final int ALIGN_BOTTOM = 2;
    protected String sLabelButton;
    protected int vAlignStyle;
    protected int hAlignStyle;
    protected Color textColor;
    protected Color pressedTextColor;
    protected Color disabledTextColor;
    protected transient ResourceBundle errors;
    private HAVeto horizontalVeto;
    private VAVeto verticalVeto;
    private VetoableChangeSupport vetos;
    private PropertyChangeSupport changes;
    
    public LabelButton() {
        this("", 1, 1, Color.black);
    }
    
    public LabelButton(final String sText) {
        this(sText, 1, 1, Color.black);
    }
    
    public LabelButton(final String sText, final Color color) {
        this(sText, 1, 1, color);
    }
    
    public LabelButton(final String sText, final int alignStyle) {
        this(sText, alignStyle, 1, Color.black);
    }
    
    public LabelButton(final String sText, final int hAlignStyle, final int vAlignStyle, final Color color) {
        this.vetos = new VetoableChangeSupport(this);
        this.changes = new PropertyChangeSupport(this);
        try {
            this.setText(sText);
            this.setTextColor(color);
            this.setAlignStyle(hAlignStyle);
            this.setVerticalAlignStyle(vAlignStyle);
        }
        catch (PropertyVetoException ex) {}
    }
    
    public void setAlignStyle(final int style) throws PropertyVetoException {
        if (this.hAlignStyle != style) {
            final Integer oldValue = new Integer(this.hAlignStyle);
            final Integer newValue = new Integer(style);
            this.vetos.fireVetoableChange("AlignStyle", oldValue, newValue);
            this.hAlignStyle = style;
            this.repaint();
            this.changes.firePropertyChange("AlignStyle", oldValue, newValue);
        }
    }
    
    public int getAlignStyle() {
        return this.hAlignStyle;
    }
    
    public void setVerticalAlignStyle(final int style) throws PropertyVetoException {
        if (this.vAlignStyle != style) {
            final Integer oldValue = new Integer(this.vAlignStyle);
            final Integer newValue = new Integer(style);
            this.vetos.fireVetoableChange("VerticalAlignStyle", oldValue, newValue);
            this.vAlignStyle = style;
            this.repaint();
            this.changes.firePropertyChange("VerticalAlignStyle", oldValue, newValue);
        }
    }
    
    public int getVerticalAlignStyle() {
        return this.vAlignStyle;
    }
    
    public void setText(final String sText) throws PropertyVetoException {
        if (this.sLabelButton == null || !this.sLabelButton.equals(sText)) {
            final String oldValue = (this.sLabelButton == null) ? null : new String(this.sLabelButton);
            this.vetos.fireVetoableChange("Text", oldValue, sText);
            this.sLabelButton = sText;
            this.repaint();
            this.changes.firePropertyChange("Text", oldValue, sText);
        }
    }
    
    public String getText() {
        return this.sLabelButton;
    }
    
    public void setTextColor(final Color color) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.textColor, color)) {
            final Color oldValue = this.textColor;
            this.vetos.fireVetoableChange("TextColor", oldValue, color);
            this.textColor = color;
            try {
                this.disabledTextColor = ColorUtils.lighten(this.textColor, 0.333);
                this.pressedTextColor = ColorUtils.darken(this.textColor, 0.25);
            }
            catch (IllegalArgumentException ex) {}
            this.repaint();
            this.changes.firePropertyChange("TextColor", oldValue, color);
        }
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    public Dimension getPreferredSize() {
        if (super.isAdded) {
            final Dimension s = this.size();
            final Graphics g = this.getGraphics();
            final FontMetrics fm = g.getFontMetrics();
            int labelWidth = 0;
            if (this.sLabelButton != null && !this.sLabelButton.equals("")) {
                labelWidth = fm.stringWidth(this.sLabelButton);
            }
            if (g != null) {
                g.dispose();
            }
            return new Dimension(labelWidth + super.bevel + super.bevel + 4, fm.getAscent() + fm.getDescent() + super.bevel + super.bevel + 4);
        }
        return super.getPreferredSize();
    }
    
    public synchronized void addNotify() {
        super.addNotify();
        try {
            this.errors = ResourceBundle.getBundle("symantec.itools.resources.ErrorsBundle");
        }
        catch (Throwable t) {
            this.errors = new ErrorsBundle();
        }
        if (this.horizontalVeto == null) {
            this.addAlignStyleListener(this.horizontalVeto = new HAVeto());
        }
        if (this.verticalVeto == null) {
            this.addVerticalAlignStyleListener(this.verticalVeto = new VAVeto());
        }
    }
    
    public synchronized void removeNotify() {
        if (this.horizontalVeto != null) {
            this.removeAlignStyleListener(this.horizontalVeto);
            this.horizontalVeto = null;
        }
        if (this.verticalVeto != null) {
            this.removeVerticalAlignStyleListener(this.verticalVeto);
            this.verticalVeto = null;
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
    
    public synchronized void addAlignStyleListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener("AlignStyle", listener);
    }
    
    public synchronized void removeAlignStyleListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener("AlignStyle", listener);
    }
    
    public synchronized void addAlignStyleListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener("AlignStyle", listener);
    }
    
    public synchronized void removeAlignStyleListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener("AlignStyle", listener);
    }
    
    public synchronized void addVerticalAlignStyleListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener("VerticalAlignStyle", listener);
    }
    
    public synchronized void removeVerticalAlignStyleListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener("VerticalAlignStyle", listener);
    }
    
    public synchronized void addVerticalAlignStyleListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener("VerticalAlignStyle", listener);
    }
    
    public synchronized void removeVerticalAlignStyleListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener("VerticalAlignStyle", listener);
    }
    
    protected boolean isValidHorizontalAlignStyle(final int i) {
        switch (i) {
            case 0:
            case 1:
            case 2: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected boolean isValidVerticalAlignStyle(final int i) {
        switch (i) {
            case 0:
            case 1:
            case 2: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    protected void updateButtonImage() {
        super.updateButtonImage();
        final FontMetrics fm = super.buttonImageGraphics.getFontMetrics();
        int xPos = 0;
        int yPos = 0;
        int labelWidth = 0;
        final Dimension s = this.size();
        if (this.sLabelButton != null && !this.sLabelButton.equals("")) {
            labelWidth = fm.stringWidth(this.sLabelButton);
        }
        Color tempColor;
        if (this.isEnabled()) {
            if (super.pressed) {
                tempColor = this.pressedTextColor;
            }
            else {
                tempColor = this.textColor;
            }
        }
        else {
            tempColor = this.disabledTextColor;
        }
        switch (this.hAlignStyle) {
            case 0: {
                xPos = super.bevel + 2;
                break;
            }
            case 2: {
                xPos = s.width - 3 - super.bevel - labelWidth;
                break;
            }
            case 1: {
                xPos = s.width - labelWidth >> 1;
                break;
            }
        }
        switch (this.vAlignStyle) {
            case 0: {
                yPos = super.bevel + 2 + fm.getAscent();
                break;
            }
            case 2: {
                yPos = s.height - 3 - super.bevel - fm.getDescent();
                break;
            }
            case 1: {
                yPos = s.height + fm.getAscent() >> 1;
                break;
            }
        }
        super.buttonImageGraphics.setColor(tempColor);
        super.buttonImageGraphics.drawString(this.sLabelButton, xPos + super.pressedAdjustment, yPos + super.pressedAdjustment);
    }
    
    class HAVeto implements VetoableChangeListener, Serializable
    {
        public void vetoableChange(final PropertyChangeEvent e) throws PropertyVetoException {
            final int i = (int)e.getNewValue();
            if (!LabelButton.this.isValidHorizontalAlignStyle(i)) {
                throw new PropertyVetoException(String.valueOf(LabelButton.this.errors.getString("InvalidAlignStyle")) + i, e);
            }
        }
    }
    
    class VAVeto implements VetoableChangeListener, Serializable
    {
        public void vetoableChange(final PropertyChangeEvent e) throws PropertyVetoException {
            final int i = (int)e.getNewValue();
            if (!LabelButton.this.isValidVerticalAlignStyle(i)) {
                throw new PropertyVetoException(String.valueOf(LabelButton.this.errors.getString("InvalidVerticalAlignStyle")) + i, e);
            }
        }
    }
}
