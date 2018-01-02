// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.Container;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JToolBar;
import java.awt.Dimension;
import javax.swing.JSeparator;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicToolBarSeparatorUI;

public class TinyToolBarSeparatorUI extends BasicToolBarSeparatorUI
{
    private static final int W99_SIZE = 6;
    private static final int YQ_SIZE = 7;
    private int defaultSize;
    
    public TinyToolBarSeparatorUI() {
        this.defaultSize = 7;
    }
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyToolBarSeparatorUI();
    }
    
    protected void installDefaults(final JSeparator separator) {
        if (Theme.derivedStyle[Theme.style] == 1) {
            this.defaultSize = 6;
        }
    }
    
    public Dimension getMinimumSize(final JComponent component) {
        if (((JToolBar.Separator)component).getOrientation() == 0) {
            return new Dimension(0, 1);
        }
        return new Dimension(1, 0);
    }
    
    public Dimension getMaximumSize(final JComponent component) {
        final JToolBar.Separator separator = (JToolBar.Separator)component;
        final Dimension separatorSize = separator.getSeparatorSize();
        if (separator.getOrientation() == 0) {
            if (separatorSize != null) {
                return new Dimension(32767, separatorSize.height);
            }
            return new Dimension(32767, this.defaultSize);
        }
        else {
            if (separatorSize != null) {
                return new Dimension(32767, separatorSize.width);
            }
            return new Dimension(this.defaultSize, 32767);
        }
    }
    
    public Dimension getPreferredSize(final JComponent component) {
        final JToolBar.Separator separator = (JToolBar.Separator)component;
        final Dimension separatorSize = separator.getSeparatorSize();
        if (separatorSize != null) {
            return separatorSize.getSize();
        }
        if (separator.getOrientation() == 0) {
            return new Dimension(0, this.defaultSize);
        }
        return new Dimension(this.defaultSize, 0);
    }
    
    public void paint(final Graphics graphics, final JComponent component) {
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyToolBarSeparator(graphics, component);
                break;
            }
            case 1: {
                this.drawWinToolBarSeparator(graphics, component);
                break;
            }
            case 2: {
                this.drawXpToolBarSeparator(graphics, component);
                break;
            }
        }
    }
    
    protected void drawTinyToolBarSeparator(final Graphics graphics, final JComponent component) {
    }
    
    protected void drawWinToolBarSeparator(final Graphics graphics, final JComponent component) {
        final JToolBar.Separator separator = (JToolBar.Separator)component;
        if (separator.getOrientation() == 0) {
            final Container parent = component.getParent();
            final int n = separator.getPreferredSize().height / 2 - 1;
            graphics.setColor(Theme.toolSepDarkColor[Theme.style].getColor());
            graphics.drawLine(1, n, parent.getWidth() - 6, n);
            graphics.setColor(Theme.toolSepLightColor[Theme.style].getColor());
            graphics.drawLine(1, n + 1, parent.getWidth() - 6, n + 1);
        }
        else {
            final int n2 = separator.getPreferredSize().width / 2 - 1;
            graphics.setColor(Theme.toolSepDarkColor[Theme.style].getColor());
            graphics.drawLine(n2, 0, n2, separator.getHeight());
            graphics.setColor(Theme.toolSepLightColor[Theme.style].getColor());
            graphics.drawLine(n2 + 1, 0, n2 + 1, separator.getHeight());
        }
    }
    
    protected void drawXpToolBarSeparator(final Graphics graphics, final JComponent component) {
        final JToolBar.Separator separator = (JToolBar.Separator)component;
        if (separator.getOrientation() == 0) {
            final int n = separator.getHeight() / 2;
            graphics.setColor(Theme.toolSepDarkColor[Theme.style].getColor());
            graphics.drawLine(0, n, separator.getWidth(), n);
        }
        else {
            final int n2 = separator.getWidth() / 2;
            graphics.setColor(Theme.toolSepDarkColor[Theme.style].getColor());
            graphics.drawLine(n2, 0, n2, separator.getHeight());
        }
    }
}
