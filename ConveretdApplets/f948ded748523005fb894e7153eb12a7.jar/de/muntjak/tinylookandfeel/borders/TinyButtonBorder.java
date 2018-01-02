// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.borders;

import de.muntjak.tinylookandfeel.controlpanel.DrawRoutines;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import de.muntjak.tinylookandfeel.Theme;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Insets;
import javax.swing.plaf.UIResource;
import javax.swing.border.AbstractBorder;

public class TinyButtonBorder extends AbstractBorder implements UIResource
{
    protected final Insets borderInsets;
    
    public TinyButtonBorder() {
        this.borderInsets = new Insets(2, 2, 2, 2);
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
    
    private void drawWinBorder(final Component component, final Graphics graphics, int n, int n2, int n3, int n4) {
        final AbstractButton abstractButton = (AbstractButton)component;
        final boolean b = component instanceof JButton && ((JButton)component).isDefaultButton();
        if (abstractButton.getModel().isPressed() || (abstractButton instanceof JToggleButton && abstractButton.isSelected())) {
            graphics.setColor(Color.BLACK);
            graphics.drawRect(0, 0, n3 - 1, n4 - 1);
            graphics.setColor(Theme.buttonDarkColor[Theme.style].getColor());
            graphics.drawRect(1, 1, n3 - 3, n4 - 3);
            return;
        }
        if (b && abstractButton.isEnabled()) {
            graphics.setColor(Theme.buttonDefaultColor[Theme.style].getColor());
            graphics.drawRect(0, 0, n3 - 1, n4 - 1);
            ++n;
            ++n2;
            --n3;
            --n4;
        }
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.buttonLightDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.buttonLightColor[Theme.style].getColor());
        }
        graphics.drawLine(n, n2, n3 - 2, n2);
        graphics.drawLine(n, n2 + 1, n, n4 - 2);
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.buttonBorderDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.buttonBorderColor[Theme.style].getColor());
        }
        graphics.drawLine(n, n4 - 1, n3 - 1, n4 - 1);
        graphics.drawLine(n3 - 1, n2, n3 - 1, n4 - 1);
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.buttonDarkDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.buttonDarkColor[Theme.style].getColor());
        }
        graphics.drawLine(n + 1, n4 - 2, n3 - 2, n4 - 2);
        graphics.drawLine(n3 - 2, n2 + 1, n3 - 2, n4 - 2);
    }
    
    private void drawXpBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final AbstractButton abstractButton = (AbstractButton)component;
        final boolean b = component instanceof JButton && ((JButton)component).isDefaultButton();
        if (Boolean.TRUE.equals(abstractButton.getClientProperty("isComboBoxButton"))) {
            if (!abstractButton.isEnabled()) {
                DrawRoutines.drawRoundedBorder(graphics, Theme.comboBorderDisabledColor[Theme.style].getColor(), n, n2, n3, n4);
            }
            else {
                DrawRoutines.drawRoundedBorder(graphics, Theme.comboBorderColor[Theme.style].getColor(), n, n2, n3, n4);
                if (abstractButton.getModel().isPressed()) {
                    return;
                }
                if (abstractButton.getModel().isRollover() && Theme.comboRollover[Theme.style]) {
                    DrawRoutines.drawRolloverBorder(graphics, Theme.buttonRolloverColor[Theme.style].getColor(), n, n2, n3, n4);
                }
            }
        }
        else {
            final boolean equals = Boolean.TRUE.equals(abstractButton.getClientProperty("isSpinnerButton"));
            final boolean b2 = (equals && Theme.spinnerRollover[Theme.style]) || (!equals && Theme.buttonRollover[Theme.style]);
            if (!abstractButton.isEnabled()) {
                DrawRoutines.drawRoundedBorder(graphics, Theme.buttonBorderDisabledColor[Theme.style].getColor(), n, n2, n3, n4);
            }
            else {
                DrawRoutines.drawRoundedBorder(graphics, Theme.buttonBorderColor[Theme.style].getColor(), n, n2, n3, n4);
                if (abstractButton.getModel().isPressed()) {
                    return;
                }
                if (abstractButton.getModel().isRollover() && b2) {
                    DrawRoutines.drawRolloverBorder(graphics, Theme.buttonRolloverColor[Theme.style].getColor(), n, n2, n3, n4);
                }
                else if (b || (Theme.buttonFocusBorder[Theme.style] && abstractButton.isFocusOwner())) {
                    DrawRoutines.drawRolloverBorder(graphics, Theme.buttonDefaultColor[Theme.style].getColor(), n, n2, n3, n4);
                }
            }
        }
    }
    
    public Insets getBorderInsets(final Component component) {
        return this.borderInsets;
    }
}
