// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt;

import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeListener;
import java.awt.Dimension;
import java.awt.Graphics;
import symantec.itools.util.GeneralUtils;
import java.beans.PropertyVetoException;
import symantec.itools.beans.PropertyChangeSupport;
import symantec.itools.beans.VetoableChangeSupport;
import java.awt.FontMetrics;
import java.awt.Canvas;

public class WrappingLabel extends Canvas implements AlignStyle
{
    protected String text;
    protected int align;
    protected int baseline;
    protected transient FontMetrics fm;
    private VetoableChangeSupport vetos;
    private PropertyChangeSupport changes;
    
    public WrappingLabel() {
        this("");
    }
    
    public WrappingLabel(final String s) {
        this(s, 0);
    }
    
    public WrappingLabel(final String s, final int a) {
        this.vetos = new VetoableChangeSupport(this);
        this.changes = new PropertyChangeSupport(this);
        try {
            this.setText(s);
        }
        catch (PropertyVetoException ex) {}
        try {
            this.setAlignStyle(a);
        }
        catch (PropertyVetoException ex2) {}
    }
    
    public int getAlignStyle() {
        return this.align;
    }
    
    public void setAlignStyle(final int newAlignStyle) throws PropertyVetoException {
        if (this.align != newAlignStyle) {
            final Integer oldAlignStyleInt = new Integer(this.align);
            final Integer newAlignStyleInt = new Integer(newAlignStyle);
            this.vetos.fireVetoableChange("AlignStyle", oldAlignStyleInt, newAlignStyleInt);
            this.align = newAlignStyle;
            this.changes.firePropertyChange("AlignStyle", oldAlignStyleInt, newAlignStyleInt);
            this.repaint();
        }
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String newText) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.text, newText)) {
            final String oldText = this.text;
            this.vetos.fireVetoableChange("Text", oldText, newText);
            this.text = newText;
            this.changes.firePropertyChange("Text", oldText, newText);
            this.repaint();
        }
    }
    
    public String paramString() {
        return "";
    }
    
    public synchronized void reshape(final int x, final int y, final int width, final int height) {
        super.reshape(x, y, width, height);
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    public synchronized void resize(final int width, final int height) {
        super.resize(width, height);
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        if (this.text != null) {
            int fromIndex = 0;
            int pos = 0;
            this.fm = this.getToolkit().getFontMetrics(this.getFont());
            this.baseline = this.fm.getMaxAscent();
            final Dimension d = this.size();
            final int boundx = d.width;
            final int boundy = d.height;
            final int x = 0;
            for (int y = 0; y + this.fm.getHeight() <= boundy && fromIndex != -1; y += this.fm.getHeight()) {
                while (fromIndex < this.text.length() && this.text.charAt(fromIndex) == ' ' && ++fromIndex < this.text.length()) {}
                pos = fromIndex;
                int bestpos = -1;
                String largestString = null;
                while (pos >= fromIndex) {
                    pos = this.text.indexOf(32, pos);
                    String s;
                    if (pos == -1) {
                        s = this.text.substring(fromIndex);
                    }
                    else {
                        s = this.text.substring(fromIndex, pos);
                    }
                    if (this.fm.stringWidth(s) >= boundx) {
                        break;
                    }
                    largestString = s;
                    if ((bestpos = pos) == -1) {
                        break;
                    }
                    ++pos;
                }
                if (largestString == null) {
                    int totalWidth = 0;
                    int oneCharWidth = 0;
                    for (pos = fromIndex; pos < this.text.length(); ++pos) {
                        oneCharWidth = this.fm.charWidth(this.text.charAt(pos));
                        if (totalWidth + oneCharWidth >= boundx) {
                            break;
                        }
                        totalWidth += oneCharWidth;
                    }
                    this.drawAlignedString(g, this.text.substring(fromIndex, pos), x, y, boundx);
                    fromIndex = pos;
                }
                else {
                    this.drawAlignedString(g, largestString, x, y, boundx);
                    fromIndex = bestpos;
                }
            }
            this.fm = null;
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
    
    protected void drawAlignedString(final Graphics g, final String s, final int x, final int y, final int width) {
        int drawx = x;
        final int drawy = y + this.baseline;
        if (this.align != 0) {
            final int sw = this.fm.stringWidth(s);
            if (this.align == 1) {
                drawx += (width - sw) / 2;
            }
            else if (this.align == 2) {
                drawx = drawx + width - sw;
            }
        }
        g.drawString(s, drawx, drawy);
    }
}
