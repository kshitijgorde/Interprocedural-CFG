// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.borders;

import de.muntjak.tinylookandfeel.controlpanel.DrawRoutines;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.Insets;
import javax.swing.plaf.UIResource;
import javax.swing.border.AbstractBorder;

public class TinyTextFieldBorder extends AbstractBorder implements UIResource
{
    private Insets insets;
    
    public TinyTextFieldBorder() {
        this.insets = Theme.textInsets[Theme.style];
    }
    
    public TinyTextFieldBorder(final Insets insets) {
        this.insets = insets;
    }
    
    public Insets getBorderInsets(final Component component) {
        return this.insets;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyBorder(component, graphics, n, n2, n3, n4);
                break;
            }
            case 1: {
                this.drawWinBorder(component, graphics, n, n2, n3, n4);
                break;
            }
            case 2: {
                this.drawXpBorder(component, graphics, n, n2, n3, n4);
                break;
            }
        }
    }
    
    private void drawTinyBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
    }
    
    private void drawWinBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (!component.isEnabled()) {
            graphics.setColor(Theme.textBorderDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.textBorderColor[Theme.style].getColor());
        }
        graphics.drawLine(n + 1, n2 + 1, n + n3 - 3, n2 + 1);
        graphics.drawLine(n + 1, n2 + 2, n + 1, n2 + n4 - 3);
        if (!component.isEnabled()) {
            graphics.setColor(Theme.textBorderDarkDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.textBorderDarkColor[Theme.style].getColor());
        }
        graphics.drawLine(n, n2, n + n3 - 2, n2);
        graphics.drawLine(n, n2 + 1, n, n2 + n4 - 2);
        graphics.setColor(Theme.backColor[Theme.style].getColor());
        graphics.drawLine(n + 1, n2 + n4 - 2, n + n3 - 2, n2 + n4 - 2);
        graphics.drawLine(n + n3 - 2, n2 + 1, n + n3 - 2, n2 + n4 - 2);
        if (!component.isEnabled()) {
            graphics.setColor(Theme.textBorderLightDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.textBorderLightColor[Theme.style].getColor());
        }
        graphics.drawLine(n, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 1, n2, n + n3 - 1, n2 + n4 - 1);
    }
    
    private void drawXpBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        if (!component.isEnabled()) {
            DrawRoutines.drawBorder(graphics, Theme.textBorderDisabledColor[Theme.style].getColor(), n, n2, n3, n4);
        }
        else {
            DrawRoutines.drawBorder(graphics, Theme.textBorderColor[Theme.style].getColor(), n, n2, n3, n4);
        }
    }
}
