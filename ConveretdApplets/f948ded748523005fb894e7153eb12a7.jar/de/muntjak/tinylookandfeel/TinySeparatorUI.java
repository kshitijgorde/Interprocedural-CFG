// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.LookAndFeel;
import javax.swing.JSeparator;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import java.awt.Dimension;
import javax.swing.plaf.basic.BasicSeparatorUI;

public class TinySeparatorUI extends BasicSeparatorUI
{
    protected static final Dimension vertDimension;
    protected static final Dimension horzDimension;
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinySeparatorUI();
    }
    
    protected void installDefaults(final JSeparator separator) {
        LookAndFeel.installColors(separator, "Separator.background", "Separator.foreground");
    }
    
    public void paint(final Graphics graphics, final JComponent component) {
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinySeparator(graphics, component);
                break;
            }
            case 1: {
                this.drawWinSeparator(graphics, component);
                break;
            }
            case 2: {
                this.drawXpSeparator(graphics, component);
                break;
            }
        }
    }
    
    protected void drawTinySeparator(final Graphics graphics, final JComponent component) {
    }
    
    protected void drawWinSeparator(final Graphics graphics, final JComponent component) {
        final Dimension size = component.getSize();
        if (((JSeparator)component).getOrientation() == 1) {
            graphics.setColor(Theme.sepDarkColor[Theme.style].getColor());
            graphics.drawLine(0, 0, 0, size.height);
            graphics.setColor(Theme.sepLightColor[Theme.style].getColor());
            graphics.drawLine(1, 0, 1, size.height);
        }
        else {
            graphics.setColor(Theme.sepDarkColor[Theme.style].getColor());
            graphics.drawLine(0, 0, size.width, 0);
            graphics.setColor(Theme.sepLightColor[Theme.style].getColor());
            graphics.drawLine(0, 1, size.width, 1);
        }
    }
    
    protected void drawXpSeparator(final Graphics graphics, final JComponent component) {
        final Dimension size = component.getSize();
        graphics.setColor(component.getBackground());
        if (((JSeparator)component).getOrientation() == 1) {
            graphics.drawLine(0, 0, 0, size.height);
        }
        else {
            graphics.drawLine(0, 0, size.width, 0);
        }
    }
    
    public Dimension getPreferredSize(final JComponent component) {
        if (((JSeparator)component).getOrientation() == 1) {
            return TinySeparatorUI.horzDimension;
        }
        return TinySeparatorUI.vertDimension;
    }
    
    public Dimension getMinimumSize(final JComponent component) {
        return this.getPreferredSize(component);
    }
    
    static {
        vertDimension = new Dimension(0, 2);
        horzDimension = new Dimension(2, 0);
    }
}
