// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.borders;

import javax.swing.plaf.UIResource;
import javax.swing.plaf.ColorUIResource;
import de.muntjak.tinylookandfeel.controlpanel.DrawRoutines;
import java.awt.Color;
import javax.swing.AbstractButton;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.border.AbstractBorder;

public class TinyToolButtonBorder extends AbstractBorder
{
    protected final Insets insets;
    
    public TinyToolButtonBorder() {
        this.insets = new Insets(1, 1, 1, 1);
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
        final AbstractButton abstractButton = (AbstractButton)component;
        graphics.translate(-n, -n2);
        graphics.setColor(Theme.toolBorderLightColor[Theme.style].getColor());
        if (abstractButton.isSelected() || (abstractButton.getModel().isPressed() && abstractButton.getModel().isRollover())) {
            graphics.drawLine(0, n4 - 1, n3 - 1, n4 - 1);
            graphics.drawLine(n3 - 1, 1, n3 - 1, n4 - 1);
        }
        else {
            graphics.drawLine(0, 0, n3 - 1, 0);
            graphics.drawLine(0, 1, 0, n4 - 1);
        }
        graphics.setColor(Theme.toolBorderDarkColor[Theme.style].getColor());
        if (abstractButton.isSelected() || (abstractButton.getModel().isPressed() && abstractButton.getModel().isRollover())) {
            graphics.drawLine(0, 0, n3 - 1, 0);
            graphics.drawLine(0, 1, 0, n4 - 1);
        }
        else {
            graphics.drawLine(0, n4 - 1, n3 - 1, n4 - 1);
            graphics.drawLine(n3 - 1, 1, n3 - 1, n4 - 1);
        }
        graphics.translate(n, n2);
    }
    
    private void drawXpBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final AbstractButton abstractButton = (AbstractButton)component;
        final boolean equals = Boolean.TRUE.equals(abstractButton.getClientProperty("JFileChooser.isFileChooserButton"));
        final boolean b = abstractButton.getModel().isRollover() || abstractButton.getModel().isArmed();
        ColorUIResource colorUIResource;
        if (abstractButton.getModel().isPressed()) {
            if (b) {
                colorUIResource = Theme.toolBorderPressedColor[Theme.style].getColor();
            }
            else if (abstractButton.isSelected()) {
                colorUIResource = Theme.toolBorderSelectedColor[Theme.style].getColor();
            }
            else {
                if (equals) {
                    return;
                }
                colorUIResource = Theme.toolBorderColor[Theme.style].getColor();
            }
        }
        else if (b) {
            if (abstractButton.isSelected()) {
                colorUIResource = Theme.toolBorderSelectedColor[Theme.style].getColor();
            }
            else {
                colorUIResource = Theme.toolBorderRolloverColor[Theme.style].getColor();
            }
        }
        else if (abstractButton.isSelected()) {
            colorUIResource = Theme.toolBorderSelectedColor[Theme.style].getColor();
        }
        else {
            if (equals) {
                return;
            }
            colorUIResource = Theme.toolBorderColor[Theme.style].getColor();
        }
        DrawRoutines.drawRoundedBorder(graphics, colorUIResource, n, n2, n3, n4);
    }
    
    public Insets getBorderInsets(final Component component) {
        if (!(component instanceof AbstractButton)) {
            return this.insets;
        }
        final AbstractButton abstractButton = (AbstractButton)component;
        if (abstractButton.getMargin() == null || abstractButton.getMargin() instanceof UIResource) {
            return new Insets(Theme.toolMarginTop[Theme.style], Theme.toolMarginLeft[Theme.style], Theme.toolMarginBottom[Theme.style], Theme.toolMarginRight[Theme.style]);
        }
        final Insets margin = abstractButton.getMargin();
        return new Insets(margin.top + 1, margin.left + 1, margin.bottom + 1, margin.right + 1);
    }
}
