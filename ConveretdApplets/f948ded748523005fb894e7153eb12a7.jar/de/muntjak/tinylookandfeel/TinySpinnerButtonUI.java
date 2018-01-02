// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import java.awt.Color;
import javax.swing.AbstractButton;
import java.awt.Graphics;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import java.awt.Dimension;

public class TinySpinnerButtonUI extends TinyButtonUI
{
    private int orientation;
    protected static Dimension winSize;
    protected static Dimension xpSize;
    
    public static ComponentUI createUI(final JComponent component) {
        throw new IllegalStateException("Must not be used this way.");
    }
    
    TinySpinnerButtonUI(final int orientation) {
        this.orientation = orientation;
    }
    
    public void paint(final Graphics graphics, final JComponent component) {
        final AbstractButton abstractButton = (AbstractButton)component;
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.spinnerButtDisabledColor[Theme.style].getColor());
        }
        else if (abstractButton.getModel().isPressed()) {
            graphics.setColor(Theme.spinnerButtPressedColor[Theme.style].getColor());
        }
        else if (abstractButton.getModel().isRollover()) {
            graphics.setColor(Theme.spinnerButtRolloverColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.spinnerButtColor[Theme.style].getColor());
        }
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyButton(graphics, abstractButton);
                break;
            }
            case 1: {
                this.drawWinButton(graphics, abstractButton);
                break;
            }
            case 2: {
                this.drawXpButton(graphics, abstractButton);
                break;
            }
        }
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.spinnerArrowDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.spinnerArrowColor[Theme.style].getColor());
        }
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyArrow(graphics, abstractButton);
                break;
            }
            case 1: {
                this.drawWinArrow(graphics, abstractButton);
                break;
            }
            case 2: {
                this.drawXpArrow(graphics, abstractButton);
                break;
            }
        }
    }
    
    private void drawTinyButton(final Graphics graphics, final AbstractButton abstractButton) {
    }
    
    private void drawWinButton(final Graphics graphics, final AbstractButton abstractButton) {
        graphics.fillRect(0, 0, abstractButton.getWidth(), abstractButton.getHeight());
        final int n = abstractButton.getSize().width - 3;
        final int n2 = (this.orientation == 1) ? (abstractButton.getSize().height - 1) : (abstractButton.getSize().height - 3);
        final int n3 = (this.orientation == 1) ? 2 : 0;
        if (abstractButton.getModel().isPressed()) {
            graphics.setColor(Theme.spinnerDarkColor[Theme.style].getColor());
            graphics.drawRect(0, n3, n, n2 - n3);
            return;
        }
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.spinnerLightDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.spinnerLightColor[Theme.style].getColor());
        }
        graphics.drawLine(1, n3 + 1, n - 2, n3 + 1);
        graphics.drawLine(1, n3 + 2, 1, n2 - 2);
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.spinnerDarkDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.spinnerDarkColor[Theme.style].getColor());
        }
        graphics.drawLine(1, n2 - 1, n - 1, n2 - 1);
        graphics.drawLine(n - 1, n3 + 1, n - 1, n2 - 2);
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.spinnerBorderDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.spinnerBorderColor[Theme.style].getColor());
        }
        graphics.drawLine(0, n2, n, n2);
        graphics.drawLine(n, n3, n, n2);
    }
    
    private void drawXpButton(final Graphics graphics, final AbstractButton abstractButton) {
        final int n = abstractButton.getSize().width - 1;
        final int n2 = abstractButton.getSize().height - 1;
        final int height = abstractButton.getSize().height;
        int n3 = Theme.spinnerSpreadLight[Theme.style];
        int n4 = Theme.spinnerSpreadDark[Theme.style];
        if (!abstractButton.isEnabled()) {
            n3 = Theme.spinnerSpreadLightDisabled[Theme.style];
            n4 = Theme.spinnerSpreadDarkDisabled[Theme.style];
        }
        final float n5 = 10.0f * n3 / (height - 2);
        final float n6 = 10.0f * n4 / (height - 2);
        final int n7 = height / 2;
        final Color color = graphics.getColor();
        for (int i = 1; i < height - 1; ++i) {
            if (i < n7) {
                graphics.setColor(ColorRoutines.lighten(color, (int)((n7 - i) * n5)));
            }
            else if (i == n7) {
                graphics.setColor(color);
            }
            else {
                graphics.setColor(ColorRoutines.darken(color, (int)((i - n7) * n6)));
            }
            graphics.drawLine(1, i, n, i);
        }
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.spinnerBorderDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.spinnerBorderColor[Theme.style].getColor());
        }
        graphics.drawRect(0, 0, n, n2);
    }
    
    private void drawTinyArrow(final Graphics graphics, final AbstractButton abstractButton) {
    }
    
    private void drawWinArrow(final Graphics graphics, final AbstractButton abstractButton) {
        int n = 6;
        int n2 = (abstractButton.getSize().height - 2) / 2;
        if (this.orientation == 1) {
            ++n2;
        }
        else {
            --n2;
        }
        if (abstractButton.getModel().isPressed()) {
            ++n2;
            ++n;
        }
        switch (this.orientation) {
            case 1: {
                graphics.drawLine(n + 1, n2, n + 1, n2);
                graphics.drawLine(n, n2 + 1, n + 2, n2 + 1);
                break;
            }
            case 5: {
                graphics.drawLine(n + 1, n2 + 1, n + 1, n2 + 1);
                graphics.drawLine(n, n2, n + 2, n2);
                break;
            }
        }
    }
    
    private void drawXpArrow(final Graphics graphics, final AbstractButton abstractButton) {
        int n = (abstractButton.getSize().height - 6) / 2;
        switch (this.orientation) {
            case 1: {
                --n;
                graphics.drawLine(7, n + 2, 7, n + 2);
                graphics.drawLine(6, n + 3, 8, n + 3);
                graphics.drawLine(5, n + 4, 9, n + 4);
                graphics.drawLine(4, n + 5, 6, n + 5);
                graphics.drawLine(8, n + 5, 10, n + 5);
                break;
            }
            case 5: {
                graphics.drawLine(4, n + 2, 6, n + 2);
                graphics.drawLine(8, n + 2, 10, n + 2);
                graphics.drawLine(5, n + 3, 9, n + 3);
                graphics.drawLine(6, n + 4, 8, n + 4);
                graphics.drawLine(7, n + 5, 7, n + 5);
                break;
            }
        }
    }
    
    public Dimension getPreferredSize(final JComponent component) {
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                return TinySpinnerButtonUI.xpSize;
            }
            case 1: {
                return TinySpinnerButtonUI.winSize;
            }
            default: {
                return TinySpinnerButtonUI.xpSize;
            }
        }
    }
    
    static {
        TinySpinnerButtonUI.winSize = new Dimension(18, 8);
        TinySpinnerButtonUI.xpSize = new Dimension(15, 8);
    }
}
