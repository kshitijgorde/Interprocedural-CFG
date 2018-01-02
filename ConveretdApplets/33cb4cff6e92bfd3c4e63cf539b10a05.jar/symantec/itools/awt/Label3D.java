// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt;

import symantec.itools.awt.util.ColorUtils;
import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeListener;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import symantec.itools.util.GeneralUtils;
import java.beans.PropertyVetoException;
import java.awt.Font;
import java.awt.FontMetrics;
import symantec.itools.beans.PropertyChangeSupport;
import symantec.itools.beans.VetoableChangeSupport;
import java.awt.Color;
import java.awt.Component;

public class Label3D extends Component implements AlignStyle, BevelStyle
{
    public static final int INDENT_ZERO = 0;
    public static final int INDENT_ONE = 1;
    public static final int INDENT_TWO = 2;
    protected String sLabel3D;
    protected int alignStyle;
    protected int bevelStyle;
    protected int indent;
    protected Color textColor;
    protected Color borderedColor;
    protected Color hilightColor;
    protected Color shadowColor;
    protected Color cachedBackground;
    private VetoableChangeSupport vetos;
    private PropertyChangeSupport changes;
    private transient FontMetrics fm;
    private transient int xTemp;
    private transient int yTemp;
    private transient boolean bOsFlag;
    
    public Label3D() {
        this("", 1, 1, Color.black, 0);
    }
    
    public Label3D(final String sText, final int alignStyle, final int bevelStyle) {
        this(sText, alignStyle, bevelStyle, Color.black, 0);
    }
    
    public Label3D(final String sText, final int alignStyle, final int bevelStyle, final Color color) {
        this(sText, alignStyle, bevelStyle, color, 0);
    }
    
    public Label3D(final String sText, final int alignStyle, final int bevelStyle, final int indent) {
        this(sText, alignStyle, bevelStyle, Color.black, indent);
    }
    
    public Label3D(final String sText, final int alignStyle, final int bevelStyle, final Color color, final int indent) {
        this.indent = 0;
        this.vetos = new VetoableChangeSupport(this);
        this.changes = new PropertyChangeSupport(this);
        this.bOsFlag = false;
        final String sOs = System.getProperty("os.name");
        if (sOs.startsWith("S") || sOs.startsWith("OSF")) {
            this.bOsFlag = true;
            this.setFont(new Font("Dialog", 0, 10));
        }
        else {
            this.bOsFlag = false;
        }
        this.sLabel3D = sText;
        this.textColor = color;
        this.borderedColor = Color.black;
        this.cachedBackground = this.getBackground();
        try {
            this.setBorderIndent(indent);
            this.setAlignStyle(alignStyle);
            this.setBevelStyle(bevelStyle);
        }
        catch (PropertyVetoException ex) {}
    }
    
    public void setAlignStyle(final int newAlignStyle) throws PropertyVetoException {
        if (this.alignStyle != newAlignStyle) {
            final Integer oldAlignStyleInt = new Integer(this.alignStyle);
            final Integer newAlignStyleInt = new Integer(newAlignStyle);
            this.vetos.fireVetoableChange("AlignStyle", oldAlignStyleInt, newAlignStyleInt);
            this.alignStyle = newAlignStyle;
            this.repaint();
            this.changes.firePropertyChange("AlignStyle", oldAlignStyleInt, newAlignStyleInt);
        }
    }
    
    public int getAlignStyle() {
        return this.alignStyle;
    }
    
    public void setBevelStyle(final int newBevelStyle) throws PropertyVetoException {
        if (this.bevelStyle != newBevelStyle) {
            final Integer oldValue = new Integer(this.bevelStyle);
            final Integer newValue = new Integer(newBevelStyle);
            this.vetos.fireVetoableChange("BevelStyle", oldValue, newValue);
            this.bevelStyle = newBevelStyle;
            this.repaint();
            this.changes.firePropertyChange("BevelStyle", oldValue, newValue);
        }
    }
    
    public int getBevelStyle() {
        return this.bevelStyle;
    }
    
    public void setBorderIndent(int newBorderIndent) throws PropertyVetoException {
        if (newBorderIndent < 0) {
            newBorderIndent = 0;
        }
        else if (newBorderIndent > 2) {
            newBorderIndent = 2;
        }
        if (this.indent != newBorderIndent) {
            final Integer oldBorderIndentInt = new Integer(this.indent);
            final Integer newBorderIndentInt = new Integer(newBorderIndent);
            this.vetos.fireVetoableChange("BorderIndent", oldBorderIndentInt, newBorderIndentInt);
            this.indent = newBorderIndent;
            this.repaint();
            this.changes.firePropertyChange("BorderIndent", oldBorderIndentInt, newBorderIndentInt);
        }
    }
    
    public int getBorderIndent() {
        return this.indent;
    }
    
    public void setBorderedColor(final Color newBorderColor) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.borderedColor, newBorderColor)) {
            final Color oldBorderColor = this.borderedColor;
            this.vetos.fireVetoableChange("BorderedColor", oldBorderColor, newBorderColor);
            this.borderedColor = newBorderColor;
            this.repaint();
            this.changes.firePropertyChange("BorderedColor", oldBorderColor, newBorderColor);
        }
    }
    
    public Color getBorderedColor() {
        return this.borderedColor;
    }
    
    public void setText(final String newText) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.sLabel3D, newText)) {
            final String oldText = this.sLabel3D;
            this.vetos.fireVetoableChange("Text", oldText, newText);
            this.sLabel3D = newText;
            this.repaint();
            this.changes.firePropertyChange("Text", oldText, newText);
        }
    }
    
    public String getText() {
        return this.sLabel3D;
    }
    
    public void setTextColor(final Color newTextColor) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.textColor, newTextColor)) {
            final Color oldValue = this.textColor;
            this.vetos.fireVetoableChange("TextColor", oldValue, newTextColor);
            this.textColor = newTextColor;
            this.repaint();
            this.changes.firePropertyChange("TextColor", oldValue, newTextColor);
        }
    }
    
    public Color getTextColor() {
        return this.textColor;
    }
    
    public void paint(final Graphics g) {
        final Color curBackground = this.getBackground();
        if (!GeneralUtils.objectsEqual(curBackground, this.cachedBackground)) {
            this.calculateHilightColors(this.cachedBackground = curBackground);
        }
        final Rectangle r = this.bounds();
        final Color c = g.getColor();
        g.setFont(this.getFont());
        Color color1 = null;
        Color color2 = null;
        switch (this.bevelStyle) {
            case 0: {
                color1 = this.shadowColor;
                color2 = this.hilightColor;
                break;
            }
            case 1: {
                color1 = this.hilightColor;
                color2 = this.shadowColor;
                break;
            }
            case 2: {
                color1 = this.borderedColor;
                color2 = this.borderedColor;
                break;
            }
            default: {
                color2 = (color1 = null);
                break;
            }
        }
        g.setColor(this.getBackground());
        g.fillRect(0, 0, r.width - 1, r.height - 1);
        if (color1 != null) {
            g.setColor(color1);
            g.drawLine(1 + this.indent, this.indent, r.width - 3 - this.indent, this.indent);
            g.setColor(color2);
            g.drawLine(1 + this.indent, r.height - 1 - this.indent, r.width - 3 - this.indent, r.height - 1 - this.indent);
            g.setColor(color1);
            g.drawLine(this.indent, this.indent, this.indent, r.height - 1 - this.indent);
            g.setColor(color2);
            g.drawLine(r.width - 2 - this.indent, this.indent, r.width - 2 - this.indent, r.height - 1 - this.indent);
            g.clipRect(2 + this.indent, 1 + this.indent, r.width - 9 - this.indent, r.height - 4 - this.indent);
            this.yTemp = 1 + this.indent;
        }
        else {
            g.drawRect(this.indent, this.indent, r.width - 2 - this.indent, r.height - 1 - this.indent);
            g.clipRect(2, 1, r.width - 7, r.height - 2);
            this.yTemp = 1;
        }
        g.setColor(this.textColor);
        this.fm = this.getFontMetrics(g.getFont());
        this.yTemp = (r.height - this.yTemp + this.fm.getAscent()) / 2;
        switch (this.alignStyle) {
            case 0: {
                if (this.bevelStyle == 2) {
                    g.drawString(this.sLabel3D, 4, this.yTemp);
                    break;
                }
                g.drawString(this.sLabel3D, 8, this.yTemp);
                break;
            }
            case 2: {
                this.xTemp = r.width - this.fm.stringWidth(this.sLabel3D);
                if (this.bevelStyle == 2) {
                    g.drawString(this.sLabel3D, this.xTemp - 6, this.yTemp);
                    break;
                }
                g.drawString(this.sLabel3D, this.xTemp - 10, this.yTemp);
                break;
            }
            case 1: {
                this.xTemp = (r.width - this.fm.stringWidth(this.sLabel3D)) / 2;
                g.drawString(this.sLabel3D, this.xTemp, this.yTemp);
                break;
            }
        }
        g.setColor(c);
    }
    
    public Dimension getPreferredSize() {
        final Dimension s = this.size();
        final Dimension m = this.minimumSize();
        return new Dimension(Math.max(s.width, m.width), Math.max(s.height, m.height));
    }
    
    public Dimension preferredSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getMinimumSize() {
        final Dimension min = new Dimension(18, 10);
        final Font f = this.getFont();
        if (f == null) {
            if (this.bOsFlag) {
                min.height = 29;
            }
        }
        else {
            this.fm = this.getFontMetrics(f);
            min.width = this.fm.stringWidth(this.sLabel3D) + 18;
            min.height = this.fm.getHeight() + 10;
            if (this.bOsFlag && min.height < 29) {
                min.height = 29;
            }
        }
        return min;
    }
    
    public Dimension minimumSize() {
        return this.getMinimumSize();
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
    
    protected void calculateHilightColors(final Color c) {
        this.hilightColor = ColorUtils.calculateHilightColor(c);
        this.shadowColor = ColorUtils.calculateShadowColor(c);
    }
}
