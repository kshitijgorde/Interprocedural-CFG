// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt.util;

import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.awt.Component;
import java.awt.MediaTracker;
import java.beans.PropertyChangeListener;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import symantec.itools.util.GeneralUtils;
import java.beans.VetoableChangeListener;
import java.beans.PropertyVetoException;
import symantec.itools.beans.PropertyChangeSupport;
import symantec.itools.beans.VetoableChangeSupport;
import java.awt.Color;
import java.awt.Image;
import symantec.itools.awt.AlignStyle;
import symantec.itools.awt.BevelStyle;
import java.awt.Canvas;

public class ProgressBar extends Canvas implements BevelStyle, AlignStyle
{
    public static final int INDENT_ZERO = 0;
    public static final int INDENT_ONE = 1;
    public static final int INDENT_TWO = 2;
    protected transient Image bufferImage;
    protected Color bevelLighterColor;
    protected Color bevelDarkerColor;
    protected Color cachedBackground;
    protected Color borderColor;
    protected Color progressColor;
    protected boolean bShowProgress;
    protected boolean bDrawBoxes;
    protected int progress;
    protected int boxWidth;
    protected int gapWidth;
    protected int indent;
    protected int align;
    protected int type;
    private BoxVeto boxVeto;
    private GapVeto gapVeto;
    private VetoableChangeSupport vetos;
    private PropertyChangeSupport changes;
    
    public ProgressBar() {
        this(1, 2, 0);
    }
    
    public ProgressBar(final int align, final int bevel, final int indent) {
        this.vetos = new VetoableChangeSupport(this);
        this.changes = new PropertyChangeSupport(this);
        this.borderColor = Color.black;
        this.progressColor = Color.blue;
        this.bShowProgress = true;
        this.bDrawBoxes = false;
        this.progress = 0;
        this.boxWidth = 8;
        this.gapWidth = 2;
        this.cachedBackground = this.getBackground();
        try {
            this.setBorderIndent(indent, false);
            this.setBevelStyle(bevel);
            this.setAlignStyle(align);
        }
        catch (PropertyVetoException ex) {}
        if (this.boxVeto == null) {
            this.addBoxWidthListener(this.boxVeto = new BoxVeto());
        }
        if (this.gapVeto == null) {
            this.addGapWidthListener(this.gapVeto = new GapVeto());
        }
    }
    
    public void setProgressBarColor(final Color c) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.progressColor, c)) {
            final Color oldValue = this.progressColor;
            this.vetos.fireVetoableChange("progressBarColor", oldValue, c);
            this.progressColor = c;
            this.repaint();
            this.changes.firePropertyChange("progressBarColor", oldValue, c);
        }
    }
    
    public Color getProgressBarColor() {
        return this.progressColor;
    }
    
    public void setProgressBarTextColor(final Color c) throws PropertyVetoException {
        this.setForeground(c);
    }
    
    public Color getProgressBarTextColor() {
        return this.getForeground();
    }
    
    public void setDrawBoxes(final boolean b) throws PropertyVetoException {
        if (this.bDrawBoxes != b) {
            final Boolean oldDrawBoxesBoolean = new Boolean(this.bDrawBoxes);
            final Boolean newDrawBoxesBoolean = new Boolean(b);
            this.vetos.fireVetoableChange("drawBoxes", oldDrawBoxesBoolean, newDrawBoxesBoolean);
            this.bDrawBoxes = b;
            this.repaint();
            this.changes.firePropertyChange("drawBoxes", oldDrawBoxesBoolean, newDrawBoxesBoolean);
        }
    }
    
    public boolean isDrawBoxes() {
        return this.bDrawBoxes;
    }
    
    public boolean getDrawBoxes() {
        return this.isDrawBoxes();
    }
    
    public void setBoxWidth(final int i) throws PropertyVetoException {
        final Integer newValue = new Integer(i);
        final Integer oldValue = new Integer(this.boxWidth);
        this.vetos.fireVetoableChange("boxWidth", oldValue, newValue);
        this.boxWidth = i;
        this.repaint();
        this.changes.firePropertyChange("boxWidth", oldValue, newValue);
    }
    
    public int getBoxWidth() {
        return this.boxWidth;
    }
    
    public void setGapWidth(final int i) throws PropertyVetoException {
        final Integer newValue = new Integer(i);
        final Integer oldValue = new Integer(this.gapWidth);
        this.vetos.fireVetoableChange("gapWidth", oldValue, newValue);
        this.gapWidth = i;
        this.repaint();
        this.changes.firePropertyChange("gapWidth", oldValue, newValue);
    }
    
    public int getGapWidth() {
        return this.gapWidth;
    }
    
    public boolean getShowProgress() {
        return this.isShowProgress();
    }
    
    public void setShowProgress(final boolean b) throws PropertyVetoException {
        if (this.bShowProgress != b) {
            final Boolean oldValue = new Boolean(this.bShowProgress);
            final Boolean newValue = new Boolean(b);
            this.vetos.fireVetoableChange("showProgress", oldValue, newValue);
            this.bShowProgress = b;
            this.repaint();
            this.changes.firePropertyChange("showProgress", oldValue, newValue);
        }
    }
    
    public boolean isShowProgress() {
        return this.bShowProgress;
    }
    
    public void setAlignStyle(final int style) throws PropertyVetoException {
        final Integer newValue = new Integer(style);
        final Integer oldValue = new Integer(this.align);
        this.vetos.fireVetoableChange("alignStyle", oldValue, newValue);
        this.align = style;
        this.repaint();
        this.changes.firePropertyChange("alignStyle", oldValue, newValue);
    }
    
    public int getAlignStyle() {
        return this.align;
    }
    
    public void setBevelStyle(final int style) throws PropertyVetoException {
        if (this.type != style) {
            final Integer oldValue = new Integer(this.type);
            final Integer newValue = new Integer(style);
            this.vetos.fireVetoableChange("bevelStyle", oldValue, newValue);
            this.type = style;
            this.repaint();
            this.changes.firePropertyChange("bevelStyle", oldValue, newValue);
        }
    }
    
    public int getBevelStyle() {
        return this.type;
    }
    
    public void setBorderIndent(final int indent) throws PropertyVetoException {
        this.setBorderIndent(indent, true);
    }
    
    public int getBorderIndent() {
        return this.indent;
    }
    
    public void setBorderColor(final Color color) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.borderColor, color)) {
            final Color oldValue = this.getBorderColor();
            this.vetos.fireVetoableChange("borderColor", oldValue, color);
            this.borderColor = color;
            this.repaint();
            this.changes.firePropertyChange("borderColor", oldValue, color);
        }
    }
    
    public Color getBorderColor() {
        return this.borderColor;
    }
    
    public void setBorderedColor(final Color color) throws PropertyVetoException {
        this.setBorderColor(color);
    }
    
    public Color getBorderedColor() {
        return this.getBorderColor();
    }
    
    public void setProgressPercent(int p) throws PropertyVetoException {
        if (p < 0) {
            p = 0;
        }
        if (p > 100) {
            p = 100;
        }
        if (this.progress != p) {
            final Integer oldValue = new Integer(this.progress);
            final Integer newValue = new Integer(p);
            this.vetos.fireVetoableChange("progressPercent", oldValue, newValue);
            this.progress = p;
            this.repaint();
            this.changes.firePropertyChange("progressPercent", oldValue, newValue);
        }
    }
    
    public void updateProgress(final int p) throws PropertyVetoException {
        this.setProgressPercent(p);
    }
    
    public int getProgressPercent() {
        return this.progress;
    }
    
    public void setValue(final int p) throws PropertyVetoException {
        this.setProgressPercent(p);
    }
    
    public int getValue() {
        return this.progress;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        final Color curBackground = this.getBackground();
        if (!GeneralUtils.objectsEqual(curBackground, this.cachedBackground)) {
            this.calculateHilightColors(this.cachedBackground = curBackground);
        }
        this.updateBufferImage();
        g.drawImage(this.bufferImage, 0, 0, this);
    }
    
    public Dimension getPreferredSize() {
        final Dimension s = this.getSize();
        final Dimension m = this.getMinimumSize();
        return new Dimension(Math.max(s.width, m.width), Math.max(s.height, m.height));
    }
    
    public Dimension preferredSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getMinimumSize() {
        Font f = null;
        FontMetrics fm = null;
        f = this.getFont();
        if (f != null) {
            fm = this.getFontMetrics(f);
        }
        return (fm != null) ? new Dimension(50, fm.getHeight() + 4) : new Dimension(50, 20);
    }
    
    public Dimension minimumSize() {
        return this.getMinimumSize();
    }
    
    public synchronized void addNotify() {
        if (this.boxVeto == null) {
            this.addBoxWidthListener(this.boxVeto = new BoxVeto());
        }
        if (this.gapVeto == null) {
            this.addGapWidthListener(this.gapVeto = new GapVeto());
        }
        super.addNotify();
    }
    
    public synchronized void removeNotify() {
        if (this.boxVeto != null) {
            this.removeBoxWidthListener(this.boxVeto);
            this.boxVeto = null;
        }
        if (this.gapVeto != null) {
            this.removeGapWidthListener(this.gapVeto);
            this.gapVeto = null;
        }
        super.removeNotify();
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
    
    public synchronized void addGapWidthListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener("gapWidth", listener);
    }
    
    public synchronized void removeGapWidthListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener("gapWidth", listener);
    }
    
    public synchronized void addGapWidthListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener("gapWidth", listener);
    }
    
    public synchronized void removeGapWidthListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener("gapWidth", listener);
    }
    
    public synchronized void addBoxWidthListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener("boxWidth", listener);
    }
    
    public synchronized void removeBoxWidthListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener("boxWidth", listener);
    }
    
    public synchronized void addBoxWidthListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener("boxWidth", listener);
    }
    
    public synchronized void removeBoxWidthListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener("boxWidth", listener);
    }
    
    protected boolean isValidBoxWidth(final int i) {
        return i > 0;
    }
    
    protected boolean isValidGapWidth(final int i) {
        return i >= 0;
    }
    
    protected void updateBufferImage() {
        final Dimension s = this.getSize();
        final int width = s.width - 1;
        final int height = s.height - 1;
        final int barHeight = height - this.indent - this.indent - 1;
        final int barOffset = this.indent + 1;
        Color color1 = null;
        Color color2 = null;
        if (this.isBufferImageInvalid()) {
            this.bufferImage = this.createImage(s.width, s.height);
            try {
                final MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(this.bufferImage, 0);
                tracker.waitForID(0);
            }
            catch (InterruptedException ex) {}
        }
        final Graphics g = this.bufferImage.getGraphics();
        final Color oldColor = g.getColor();
        g.setColor(this.cachedBackground);
        g.fillRect(0, 0, s.width, s.height);
        g.setColor(this.progressColor);
        final int position = (s.width - this.indent - this.indent - 2) * this.progress / 100;
        g.fillRect(barOffset, barOffset, position, barHeight);
        if (this.bDrawBoxes) {
            final int totalWidth = this.boxWidth + this.gapWidth;
            int offset;
            final int offsetConstant = offset = this.boxWidth + barOffset;
            int i = 0;
            final int internalPos = position + barOffset;
            g.setColor(this.cachedBackground);
            while (offset <= internalPos) {
                g.fillRect(offset, barOffset, this.gapWidth, barHeight);
                offset = totalWidth * ++i + offsetConstant;
            }
            final int xpos = totalWidth * (i - 1) + offsetConstant + this.gapWidth;
            final int rWidth = internalPos - xpos;
            if (rWidth < this.boxWidth) {
                g.fillRect(xpos, barOffset, rWidth, barHeight);
            }
        }
        if (this.type != 3) {
            switch (this.type) {
                case 2: {
                    color2 = (color1 = this.borderColor);
                    break;
                }
                case 1: {
                    color1 = this.bevelLighterColor;
                    color2 = this.bevelDarkerColor;
                    break;
                }
                case 0: {
                    color1 = this.bevelDarkerColor;
                    color2 = this.bevelLighterColor;
                    break;
                }
            }
            g.setColor(color1);
            g.drawLine(this.indent, this.indent, width - this.indent, this.indent);
            g.setColor(color2);
            g.drawLine(this.indent, height - this.indent, width - this.indent, height - this.indent);
            g.setColor(color1);
            g.drawLine(this.indent, this.indent, this.indent, height - this.indent);
            g.setColor(color2);
            g.drawLine(width - this.indent, this.indent, width - this.indent, height - this.indent);
        }
        if (this.bShowProgress) {
            g.setColor(this.getForeground());
            final FontMetrics fm = this.getFontMetrics(this.getFont());
            final String sz = String.valueOf(Integer.toString(this.progress)) + "%";
            final int yTemp = (s.height + fm.getAscent()) / 2 - 2;
            switch (this.align) {
                case 0: {
                    if (this.type == 3) {
                        g.drawString(sz, 4, yTemp);
                        break;
                    }
                    g.drawString(sz, 8, yTemp);
                    break;
                }
                case 2: {
                    final int xTemp = s.width - fm.stringWidth(sz);
                    if (this.type == 3) {
                        g.drawString(sz, xTemp - 6, yTemp);
                        break;
                    }
                    g.drawString(sz, xTemp - 10, yTemp);
                    break;
                }
                case 1: {
                    final int xTemp = (s.width - fm.stringWidth(sz)) / 2;
                    if (this.type == 3) {
                        g.drawString(sz, xTemp, yTemp);
                        break;
                    }
                    g.drawString(sz, xTemp, yTemp);
                    break;
                }
            }
        }
        g.setColor(oldColor);
    }
    
    protected boolean isBufferImageInvalid() {
        final Dimension s = this.getSize();
        return this.bufferImage == null || s.width != this.bufferImage.getWidth(this) || s.height != this.bufferImage.getHeight(this);
    }
    
    protected void calculateHilightColors(final Color c) {
        this.bevelLighterColor = ColorUtils.calculateHilightColor(c);
        this.bevelDarkerColor = ColorUtils.calculateShadowColor(c);
    }
    
    protected void setBorderIndent(final int indent, final boolean bRepaint) throws PropertyVetoException {
        if (this.indent != indent) {
            final Integer oldValue = new Integer(this.indent);
            final Integer newValue = new Integer(indent);
            this.vetos.fireVetoableChange("borderIndent", oldValue, newValue);
            if (indent < 0) {
                this.indent = 0;
            }
            else if (indent > 2) {
                this.indent = 2;
            }
            else {
                this.indent = indent;
            }
            if (bRepaint) {
                this.repaint();
            }
            this.changes.firePropertyChange("borderIndent", oldValue, new Integer(this.indent));
        }
    }
    
    class BoxVeto implements VetoableChangeListener, Serializable
    {
        public void vetoableChange(final PropertyChangeEvent e) throws PropertyVetoException {
            final int i = (int)e.getNewValue();
            if (!ProgressBar.this.isValidBoxWidth(i)) {
                throw new PropertyVetoException("Invalid box width: " + i, e);
            }
        }
    }
    
    class GapVeto implements VetoableChangeListener, Serializable
    {
        public void vetoableChange(final PropertyChangeEvent e) throws PropertyVetoException {
            final int i = (int)e.getNewValue();
            if (!ProgressBar.this.isValidGapWidth(i)) {
                throw new PropertyVetoException("Invalid gap width: " + i, e);
            }
        }
    }
}
