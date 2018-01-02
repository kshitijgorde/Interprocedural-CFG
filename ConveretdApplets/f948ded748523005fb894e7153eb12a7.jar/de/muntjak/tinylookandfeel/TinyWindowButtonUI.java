// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.Dimension;
import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import de.muntjak.tinylookandfeel.borders.TinyFrameBorder;
import de.muntjak.tinylookandfeel.controlpanel.DrawRoutines;
import de.muntjak.tinylookandfeel.borders.TinyInternalFrameBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import javax.swing.border.Border;
import javax.swing.AbstractButton;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;

public class TinyWindowButtonUI extends TinyButtonUI
{
    private int type;
    public static final int CLOSE = 0;
    public static final int MAXIMIZE = 1;
    public static final int MINIMIZE = 2;
    
    public static ComponentUI createUI(final JComponent component) {
        throw new IllegalStateException("Must not be used this way.");
    }
    
    TinyWindowButtonUI(final int type) {
        this.type = type;
    }
    
    public void installDefaults(final AbstractButton abstractButton) {
        super.installDefaults(abstractButton);
        abstractButton.setBorder(null);
        abstractButton.setFocusable(false);
    }
    
    protected void paintFocus(final Graphics graphics, final AbstractButton abstractButton, final Rectangle rectangle, final Rectangle rectangle2, final Rectangle rectangle3) {
    }
    
    public void paint(final Graphics graphics, final JComponent component) {
        final AbstractButton abstractButton = (AbstractButton)component;
        boolean b = false;
        boolean b2 = false;
        if (component.getParent() instanceof TinyInternalFrameTitlePane) {
            b = ((TinyInternalFrameTitlePane)component.getParent()).isFrameSelected();
            b2 = ((TinyInternalFrameTitlePane)component.getParent()).isFrameMaximized();
        }
        else if (component.getParent() instanceof TinyTitlePane) {
            b = ((TinyTitlePane)component.getParent()).isSelected();
            b2 = ((TinyTitlePane)component.getParent()).isFrameMaximized();
        }
        final int width = abstractButton.getWidth();
        final int height = abstractButton.getHeight();
        ColorUIResource color;
        if (!b) {
            if (abstractButton.isEnabled()) {
                if (abstractButton.getModel().isPressed()) {
                    if (this.type == 0) {
                        color = Theme.frameButtClosePressedColor[Theme.style].getColor();
                    }
                    else {
                        color = Theme.frameButtPressedColor[Theme.style].getColor();
                    }
                }
                else if (this.type == 0) {
                    color = Theme.frameButtCloseColor[Theme.style].getColor();
                }
                else {
                    color = Theme.frameButtColor[Theme.style].getColor();
                }
            }
            else if (this.type == 0) {
                color = Theme.frameButtCloseDisabledColor[Theme.style].getColor();
            }
            else {
                color = Theme.frameButtDisabledColor[Theme.style].getColor();
            }
        }
        else if (abstractButton.getModel().isPressed()) {
            if (abstractButton.getModel().isRollover()) {
                if (this.type == 0) {
                    color = Theme.frameButtClosePressedColor[Theme.style].getColor();
                }
                else {
                    color = Theme.frameButtPressedColor[Theme.style].getColor();
                }
            }
            else if (this.type == 0) {
                color = Theme.frameButtCloseColor[Theme.style].getColor();
            }
            else {
                color = Theme.frameButtColor[Theme.style].getColor();
            }
        }
        else if (abstractButton.getModel().isRollover()) {
            if (this.type == 0) {
                color = Theme.frameButtCloseRolloverColor[Theme.style].getColor();
            }
            else {
                color = Theme.frameButtRolloverColor[Theme.style].getColor();
            }
        }
        else if (this.type == 0) {
            color = Theme.frameButtCloseColor[Theme.style].getColor();
        }
        else {
            color = Theme.frameButtColor[Theme.style].getColor();
        }
        graphics.setColor(color);
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyButton(graphics, abstractButton, color, width, height, b);
                break;
            }
            case 1: {
                this.drawWinButton(graphics, abstractButton, color, width, height, b);
                break;
            }
            case 2: {
                this.drawXpButton(graphics, abstractButton, color, width, height, b);
                break;
            }
        }
        if (!abstractButton.isEnabled()) {
            if (this.type == 0) {
                graphics.setColor(Theme.frameSymbolCloseDisabledColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.frameSymbolDisabledColor[Theme.style].getColor());
            }
        }
        else if (this.type == 0) {
            graphics.setColor(Theme.frameSymbolCloseColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.frameSymbolColor[Theme.style].getColor());
        }
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinySymbol(graphics, abstractButton, color, width, height, b, b2);
                break;
            }
            case 1: {
                this.drawWinSymbol(graphics, abstractButton, color, width, height, b, b2);
                break;
            }
            case 2: {
                this.drawXpSymbol(graphics, abstractButton, color, width, height, b, b2);
                break;
            }
        }
    }
    
    private void drawTinyButton(final Graphics graphics, final AbstractButton abstractButton, final Color color, final int n, final int n2, final boolean b) {
        graphics.fillRect(1, 1, n - 2, n2 - 2);
    }
    
    private void drawWinButton(final Graphics graphics, final AbstractButton abstractButton, final Color color, final int n, final int n2, final boolean b) {
        graphics.fillRect(1, 1, n - 2, n2 - 2);
        if ((abstractButton.getModel().isPressed() && abstractButton.getModel().isRollover()) || (abstractButton.getModel().isPressed() && !b)) {
            graphics.setColor(Theme.frameButtBorderColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.frameButtLightColor[Theme.style].getColor());
        }
        graphics.drawLine(0, 0, n - 2, 0);
        graphics.drawLine(0, 1, 0, n2 - 2);
        graphics.setColor(Theme.frameButtDarkColor[Theme.style].getColor());
        if ((abstractButton.getModel().isPressed() && abstractButton.getModel().isRollover()) || (abstractButton.getModel().isPressed() && !b)) {
            graphics.drawLine(1, 1, n - 2, 1);
            graphics.drawLine(1, 1, 1, n2 - 3);
        }
        else {
            graphics.drawLine(1, n2 - 2, n - 2, n2 - 2);
            graphics.drawLine(n - 2, 1, n - 2, n2 - 3);
        }
        if ((abstractButton.getModel().isPressed() && abstractButton.getModel().isRollover()) || (abstractButton.getModel().isPressed() && !b)) {
            graphics.setColor(Theme.frameButtLightColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.frameButtBorderColor[Theme.style].getColor());
        }
        graphics.drawLine(0, n2 - 1, n - 1, n2 - 1);
        graphics.drawLine(n - 1, 0, n - 1, n2 - 2);
    }
    
    private void drawXpButton(final Graphics graphics, final AbstractButton abstractButton, final Color color, final int n, final int n2, final boolean b) {
        if (abstractButton.getClientProperty("externalFrameButton") == Boolean.TRUE) {
            this.drawXpLargeButton(graphics, abstractButton, color, n, n2, b);
            return;
        }
        graphics.fillRect(1, 1, n - 2, n2 - 2);
        if (abstractButton.getParent() instanceof TinyInternalFrameTitlePane && ((TinyInternalFrameTitlePane)abstractButton.getParent()).isPalette()) {}
        if (b) {
            graphics.setColor(TinyInternalFrameBorder.frameUpperColor);
            graphics.drawLine(0, 0, n - 1, 0);
            graphics.drawLine(0, 1, 0, 1);
            graphics.drawLine(n - 1, 1, n - 1, 1);
            graphics.setColor(TinyInternalFrameBorder.frameLowerColor);
            graphics.drawLine(0, n2 - 1, n - 1, n2 - 1);
            graphics.drawLine(0, n2 - 2, 0, n2 - 2);
            graphics.drawLine(n - 1, n2 - 2, n - 1, n2 - 2);
        }
        else {
            graphics.setColor(TinyInternalFrameBorder.disabledUpperColor);
            graphics.drawLine(0, 0, n - 1, 0);
            graphics.drawLine(0, 1, 0, 1);
            graphics.drawLine(n - 1, 1, n - 1, 1);
            graphics.setColor(TinyInternalFrameBorder.disabledLowerColor);
            graphics.drawLine(0, n2 - 1, n - 1, n2 - 1);
            graphics.drawLine(0, n2 - 2, 0, n2 - 2);
            graphics.drawLine(n - 1, n2 - 2, n - 1, n2 - 2);
        }
        ColorUIResource colorUIResource;
        if (!abstractButton.isEnabled()) {
            if (this.type == 0) {
                colorUIResource = Theme.frameButtCloseBorderDisabledColor[Theme.style].getColor();
            }
            else {
                colorUIResource = Theme.frameButtBorderDisabledColor[Theme.style].getColor();
            }
        }
        else if (this.type == 0) {
            colorUIResource = Theme.frameButtCloseBorderColor[Theme.style].getColor();
        }
        else {
            colorUIResource = Theme.frameButtBorderColor[Theme.style].getColor();
        }
        DrawRoutines.drawRoundedBorder(graphics, colorUIResource, 0, 0, n, n2);
        ColorUIResource color2;
        if (!abstractButton.isEnabled()) {
            if (this.type == 0) {
                color2 = Theme.frameButtCloseLightDisabledColor[Theme.style].getColor();
            }
            else {
                color2 = Theme.frameButtLightDisabledColor[Theme.style].getColor();
            }
        }
        else if (this.type == 0) {
            color2 = Theme.frameButtCloseLightColor[Theme.style].getColor();
        }
        else {
            color2 = Theme.frameButtLightColor[Theme.style].getColor();
        }
        graphics.setColor(color2);
        graphics.drawLine(2, 1, n - 3, 1);
        graphics.drawLine(1, 2, 1, n2 - 3);
        ColorUIResource color3;
        if (!abstractButton.isEnabled()) {
            if (this.type == 0) {
                color3 = Theme.frameButtCloseDarkDisabledColor[Theme.style].getColor();
            }
            else {
                color3 = Theme.frameButtDarkDisabledColor[Theme.style].getColor();
            }
        }
        else if (this.type == 0) {
            color3 = Theme.frameButtCloseDarkColor[Theme.style].getColor();
        }
        else {
            color3 = Theme.frameButtDarkColor[Theme.style].getColor();
        }
        graphics.setColor(color3);
        graphics.drawLine(n - 2, 2, n - 2, n2 - 3);
        graphics.drawLine(2, n2 - 2, n - 3, n2 - 2);
    }
    
    private void drawXpLargeButton(final Graphics graphics, final AbstractButton abstractButton, final Color color, final int n, final int n2, final boolean b) {
        graphics.setColor(TinyFrameBorder.buttonUpperColor);
        graphics.drawLine(0, 0, n - 1, 0);
        graphics.setColor(TinyFrameBorder.buttonLowerColor);
        graphics.drawLine(0, n2 - 1, n - 1, n2 - 1);
        int n3 = Theme.frameButtSpreadLight[Theme.style];
        int n4 = Theme.frameButtSpreadDark[Theme.style];
        if (!abstractButton.isEnabled()) {
            if (this.type == 0) {
                n3 = Theme.frameButtCloseSpreadLightDisabled[Theme.style];
                n4 = Theme.frameButtCloseSpreadDarkDisabled[Theme.style];
            }
            else {
                n3 = Theme.frameButtSpreadLightDisabled[Theme.style];
                n4 = Theme.frameButtSpreadDarkDisabled[Theme.style];
            }
        }
        else if (this.type == 0) {
            n3 = Theme.frameButtCloseSpreadLight[Theme.style];
            n4 = Theme.frameButtCloseSpreadDark[Theme.style];
        }
        final float n5 = 10.0f * n3 / (n2 - 3);
        final float n6 = 10.0f * n4 / (n2 - 3);
        final int n7 = n2 / 2;
        for (int i = 1; i < n2 - 1; ++i) {
            if (i < n7) {
                graphics.setColor(ColorRoutines.lighten(color, (int)((n7 - i) * n5)));
            }
            else if (i == n7) {
                graphics.setColor(color);
            }
            else {
                graphics.setColor(ColorRoutines.darken(color, (int)((i - n7) * n6)));
            }
            graphics.drawLine(2, i, n - 3, i);
            if (i == 1 && abstractButton.isEnabled()) {
                graphics.drawLine(1, 2, 1, n2 - 3);
            }
            else if (i == n2 - 2) {
                graphics.drawLine(n - 2, 2, n - 2, n2 - 3);
            }
        }
        if (!abstractButton.isEnabled()) {
            if (this.type == 0) {
                DrawRoutines.drawRoundedBorder(graphics, Theme.frameButtCloseBorderDisabledColor[Theme.style].getColor(), 0, 0, n, n2);
            }
            else {
                DrawRoutines.drawRoundedBorder(graphics, Theme.frameButtBorderDisabledColor[Theme.style].getColor(), 0, 0, n, n2);
            }
        }
        else if (this.type == 0) {
            DrawRoutines.drawRoundedBorder(graphics, Theme.frameButtCloseBorderColor[Theme.style].getColor(), 0, 0, n, n2);
        }
        else {
            DrawRoutines.drawRoundedBorder(graphics, Theme.frameButtBorderColor[Theme.style].getColor(), 0, 0, n, n2);
        }
    }
    
    private void drawTinySymbol(final Graphics graphics, final AbstractButton abstractButton, final Color color, final int n, final int n2, final boolean b, final boolean b2) {
    }
    
    private void drawWinSymbol(final Graphics graphics, final AbstractButton abstractButton, final Color color, final int n, final int n2, final boolean b, final boolean b2) {
        int n3 = 3;
        int n4 = 2;
        if ((abstractButton.getModel().isPressed() && abstractButton.getModel().isRollover()) || (abstractButton.getModel().isPressed() && !b)) {
            n3 = 4;
            n4 = 3;
        }
        switch (this.type) {
            case 0: {
                graphics.drawLine(n3 + 1, n4 + 1, n3 + 2, n4 + 1);
                graphics.drawLine(n3 + 7, n4 + 1, n3 + 8, n4 + 1);
                graphics.drawLine(n3 + 2, n4 + 2, n3 + 3, n4 + 2);
                graphics.drawLine(n3 + 6, n4 + 2, n3 + 7, n4 + 2);
                graphics.drawLine(n3 + 3, n4 + 3, n3 + 6, n4 + 3);
                graphics.drawLine(n3 + 4, n4 + 4, n3 + 5, n4 + 4);
                graphics.drawLine(n3 + 3, n4 + 5, n3 + 6, n4 + 5);
                graphics.drawLine(n3 + 2, n4 + 6, n3 + 3, n4 + 6);
                graphics.drawLine(n3 + 6, n4 + 6, n3 + 7, n4 + 6);
                graphics.drawLine(n3 + 1, n4 + 7, n3 + 2, n4 + 7);
                graphics.drawLine(n3 + 7, n4 + 7, n3 + 8, n4 + 7);
                break;
            }
            case 1: {
                if (b2) {
                    graphics.fillRect(n3 + 2, n4, 6, 2);
                    graphics.drawLine(n3 + 2, n4 + 2, n3 + 2, n4 + 2);
                    graphics.drawLine(n3 + 6, n4 + 5, n3 + 7, n4 + 5);
                    graphics.drawLine(n3 + 7, n4 + 2, n3 + 7, n4 + 4);
                    graphics.drawLine(n3, n4 + 3, n3 + 5, n4 + 3);
                    graphics.drawRect(n3, n4 + 4, 5, 4);
                    break;
                }
                graphics.drawRect(n3, n4, 8, 8);
                graphics.drawLine(n3 + 1, n4 + 1, n3 + 7, n4 + 1);
                break;
            }
            case 2: {
                graphics.drawRect(n3 + 1, n4 + 7, 6, 1);
                break;
            }
        }
    }
    
    private void drawXpSymbol(final Graphics graphics, final AbstractButton abstractButton, final Color color, final int n, final int n2, final boolean b, final boolean b2) {
        if (abstractButton.getClientProperty("externalFrameButton") == Boolean.TRUE) {
            this.drawXpLargeSymbol(graphics, abstractButton, color, n, n2, b, b2);
            return;
        }
        if (abstractButton.getParent() instanceof TinyInternalFrameTitlePane && ((TinyInternalFrameTitlePane)abstractButton.getParent()).isPalette()) {
            this.drawXpSmallSymbol(graphics, abstractButton, color, n, n2, b, b2);
            return;
        }
        if (!b) {
            if (abstractButton.isEnabled() && !abstractButton.getModel().isPressed()) {
                if (this.type == 0) {
                    graphics.setColor(Theme.frameSymbolCloseColor[Theme.style].getColor());
                }
                else {
                    graphics.setColor(Theme.frameSymbolColor[Theme.style].getColor());
                }
            }
            else if (this.type == 0) {
                if (abstractButton.getModel().isPressed()) {
                    graphics.setColor(Theme.frameSymbolClosePressedColor[Theme.style].getColor());
                }
                else {
                    graphics.setColor(Theme.frameSymbolCloseDisabledColor[Theme.style].getColor());
                }
            }
            else if (abstractButton.getModel().isPressed()) {
                graphics.setColor(Theme.frameSymbolPressedColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.frameSymbolDisabledColor[Theme.style].getColor());
            }
        }
        else if (abstractButton.getModel().isPressed() && abstractButton.getModel().isRollover()) {
            if (this.type == 0) {
                graphics.setColor(Theme.frameSymbolClosePressedColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.frameSymbolPressedColor[Theme.style].getColor());
            }
        }
        else if (this.type == 0) {
            graphics.setColor(Theme.frameSymbolCloseColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.frameSymbolColor[Theme.style].getColor());
        }
        final int n3 = 0;
        final int n4 = 0;
        switch (this.type) {
            case 0: {
                graphics.drawLine(n3 + 4, n4 + 3, n3 + 4, n4 + 3);
                graphics.drawLine(n3 + 12, n4 + 3, n3 + 12, n4 + 3);
                graphics.drawLine(n3 + 3, n4 + 4, n3 + 5, n4 + 4);
                graphics.drawLine(n3 + 11, n4 + 4, n3 + 13, n4 + 4);
                graphics.drawLine(n3 + 4, n4 + 5, n3 + 6, n4 + 5);
                graphics.drawLine(n3 + 10, n4 + 5, n3 + 12, n4 + 5);
                graphics.drawLine(n3 + 5, n4 + 6, n3 + 7, n4 + 6);
                graphics.drawLine(n3 + 9, n4 + 6, n3 + 11, n4 + 6);
                graphics.drawLine(n3 + 6, n4 + 7, n3 + 10, n4 + 7);
                graphics.drawLine(n3 + 7, n4 + 8, n3 + 9, n4 + 8);
                graphics.drawLine(n3 + 4, n4 + 13, n3 + 4, n4 + 13);
                graphics.drawLine(n3 + 12, n4 + 13, n3 + 12, n4 + 13);
                graphics.drawLine(n3 + 3, n4 + 12, n3 + 5, n4 + 12);
                graphics.drawLine(n3 + 11, n4 + 12, n3 + 13, n4 + 12);
                graphics.drawLine(n3 + 4, n4 + 11, n3 + 6, n4 + 11);
                graphics.drawLine(n3 + 10, n4 + 11, n3 + 12, n4 + 11);
                graphics.drawLine(n3 + 5, n4 + 10, n3 + 7, n4 + 10);
                graphics.drawLine(n3 + 9, n4 + 10, n3 + 11, n4 + 10);
                graphics.drawLine(n3 + 6, n4 + 9, n3 + 10, n4 + 9);
                break;
            }
            case 1: {
                if (b2) {
                    graphics.fillRect(n3 + 6, n4 + 3, 8, 2);
                    graphics.drawLine(n3 + 6, n4 + 5, n3 + 6, n4 + 6);
                    graphics.drawLine(n3 + 11, n4 + 9, n3 + 13, n4 + 9);
                    graphics.drawLine(n3 + 13, n4 + 5, n3 + 13, n4 + 8);
                    graphics.drawLine(n3 + 3, n4 + 7, n3 + 10, n4 + 7);
                    graphics.drawRect(n3 + 3, n4 + 8, 7, 5);
                    break;
                }
                graphics.fillRect(n3 + 3, n4 + 3, 11, 2);
                graphics.drawRect(n3 + 3, n4 + 5, 10, 8);
                break;
            }
            case 2: {
                graphics.fillRect(n3 + 3, n4 + 11, 7, 3);
                break;
            }
        }
    }
    
    private void drawXpSmallSymbol(final Graphics graphics, final AbstractButton abstractButton, final Color color, final int n, final int n2, final boolean b, final boolean b2) {
        if (!b) {
            if (abstractButton.isEnabled() && !abstractButton.getModel().isPressed()) {
                if (this.type == 0) {
                    graphics.setColor(Theme.frameSymbolCloseColor[Theme.style].getColor());
                }
                else {
                    graphics.setColor(Theme.frameSymbolColor[Theme.style].getColor());
                }
            }
            else if (this.type == 0) {
                if (abstractButton.getModel().isPressed()) {
                    graphics.setColor(Theme.frameSymbolClosePressedColor[Theme.style].getColor());
                }
                else {
                    graphics.setColor(Theme.frameSymbolCloseDisabledColor[Theme.style].getColor());
                }
            }
            else if (abstractButton.getModel().isPressed()) {
                graphics.setColor(Theme.frameSymbolPressedColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.frameSymbolDisabledColor[Theme.style].getColor());
            }
        }
        else if (abstractButton.getModel().isPressed() && abstractButton.getModel().isRollover()) {
            if (this.type == 0) {
                graphics.setColor(Theme.frameSymbolClosePressedColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.frameSymbolPressedColor[Theme.style].getColor());
            }
        }
        else if (this.type == 0) {
            graphics.setColor(Theme.frameSymbolCloseColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.frameSymbolColor[Theme.style].getColor());
        }
        final int n3 = 0;
        final int n4 = 0;
        switch (this.type) {
            case 0: {
                graphics.drawLine(n3 + 3, n4 + 2, n3 + 3, n4 + 2);
                graphics.drawLine(n3 + 9, n4 + 2, n3 + 9, n4 + 2);
                graphics.drawLine(n3 + 2, n4 + 3, n3 + 4, n4 + 3);
                graphics.drawLine(n3 + 8, n4 + 3, n3 + 10, n4 + 3);
                graphics.drawLine(n3 + 3, n4 + 4, n3 + 5, n4 + 4);
                graphics.drawLine(n3 + 7, n4 + 4, n3 + 9, n4 + 4);
                graphics.drawLine(n3 + 4, n4 + 5, n3 + 8, n4 + 5);
                graphics.drawLine(n3 + 5, n4 + 6, n3 + 7, n4 + 6);
                graphics.drawLine(n3 + 4, n4 + 7, n3 + 8, n4 + 7);
                graphics.drawLine(n3 + 3, n4 + 8, n3 + 5, n4 + 8);
                graphics.drawLine(n3 + 7, n4 + 8, n3 + 9, n4 + 8);
                graphics.drawLine(n3 + 2, n4 + 9, n3 + 4, n4 + 9);
                graphics.drawLine(n3 + 8, n4 + 9, n3 + 10, n4 + 9);
                graphics.drawLine(n3 + 3, n4 + 10, n3 + 3, n4 + 10);
                graphics.drawLine(n3 + 9, n4 + 10, n3 + 9, n4 + 10);
                break;
            }
            case 1: {
                if (b2) {
                    graphics.drawRect(n3 + 2, n4 + 6, 6, 4);
                    graphics.drawLine(n3 + 2, n4 + 5, n3 + 8, n4 + 5);
                    graphics.fillRect(n3 + 4, n4 + 2, n3 + 7, n4 + 2);
                    graphics.drawLine(n3 + 4, n4 + 4, n3 + 4, n4 + 4);
                    graphics.drawLine(n3 + 9, n4 + 7, n3 + 9, n4 + 7);
                    graphics.drawLine(n3 + 10, n4 + 4, n3 + 10, n4 + 7);
                    break;
                }
                graphics.drawLine(n3 + 2, n4 + 2, n3 + 10, n4 + 2);
                graphics.drawRect(n3 + 2, n4 + 3, 8, 7);
                break;
            }
            case 2: {
                graphics.fillRect(n3 + 2, n4 + 8, 6, 3);
                break;
            }
        }
    }
    
    private void drawXpLargeSymbol(final Graphics graphics, final AbstractButton abstractButton, final Color color, final int n, final int n2, final boolean b, final boolean b2) {
        if (!b) {
            if (abstractButton.isEnabled() && !abstractButton.getModel().isPressed()) {
                if (this.type == 0) {
                    graphics.setColor(Theme.frameSymbolCloseColor[Theme.style].getColor());
                }
                else {
                    graphics.setColor(Theme.frameSymbolColor[Theme.style].getColor());
                }
            }
            else if (this.type == 0) {
                if (abstractButton.getModel().isPressed()) {
                    graphics.setColor(Theme.frameSymbolClosePressedColor[Theme.style].getColor());
                }
                else {
                    graphics.setColor(Theme.frameSymbolCloseDisabledColor[Theme.style].getColor());
                }
            }
            else if (abstractButton.getModel().isPressed()) {
                graphics.setColor(Theme.frameSymbolPressedColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.frameSymbolDisabledColor[Theme.style].getColor());
            }
        }
        else if (abstractButton.getModel().isPressed() && abstractButton.getModel().isRollover()) {
            if (this.type == 0) {
                graphics.setColor(Theme.frameSymbolClosePressedColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.frameSymbolPressedColor[Theme.style].getColor());
            }
        }
        else if (this.type == 0) {
            graphics.setColor(Theme.frameSymbolCloseColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.frameSymbolColor[Theme.style].getColor());
        }
        final int n3 = 0;
        final int n4 = 0;
        switch (this.type) {
            case 0: {
                graphics.drawLine(n3 + 5, n4 + 5, n3 + 6, n4 + 5);
                graphics.drawLine(n3 + 14, n4 + 5, n3 + 15, n4 + 5);
                graphics.drawLine(n3 + 5, n4 + 6, n3 + 7, n4 + 6);
                graphics.drawLine(n3 + 13, n4 + 6, n3 + 15, n4 + 6);
                graphics.drawLine(n3 + 6, n4 + 7, n3 + 8, n4 + 7);
                graphics.drawLine(n3 + 12, n4 + 7, n3 + 14, n4 + 7);
                graphics.drawLine(n3 + 7, n4 + 8, n3 + 9, n4 + 8);
                graphics.drawLine(n3 + 11, n4 + 8, n3 + 13, n4 + 8);
                graphics.drawLine(n3 + 8, n4 + 9, n3 + 12, n4 + 9);
                graphics.drawLine(n3 + 9, n4 + 10, n3 + 11, n4 + 10);
                graphics.drawLine(n3 + 5, n4 + 15, n3 + 6, n4 + 15);
                graphics.drawLine(n3 + 14, n4 + 15, n3 + 15, n4 + 15);
                graphics.drawLine(n3 + 5, n4 + 14, n3 + 7, n4 + 14);
                graphics.drawLine(n3 + 13, n4 + 14, n3 + 15, n4 + 14);
                graphics.drawLine(n3 + 6, n4 + 13, n3 + 8, n4 + 13);
                graphics.drawLine(n3 + 12, n4 + 13, n3 + 14, n4 + 13);
                graphics.drawLine(n3 + 7, n4 + 12, n3 + 9, n4 + 12);
                graphics.drawLine(n3 + 11, n4 + 12, n3 + 13, n4 + 12);
                graphics.drawLine(n3 + 8, n4 + 11, n3 + 12, n4 + 11);
                break;
            }
            case 1: {
                if (b2) {
                    graphics.fillRect(n3 + 8, n4 + 4, 8, 2);
                    graphics.fillRect(n3 + 4, n4 + 9, 8, 2);
                    break;
                }
                graphics.fillRect(n3 + 5, n4 + 6, 10, 2);
                break;
            }
            case 2: {
                graphics.fillRect(n3 + 5, n4 + 14, 6, 2);
                break;
            }
        }
        Color color2;
        if (this.type == 0) {
            color2 = Theme.frameSymbolCloseLightColor[Theme.style].getColor();
        }
        else {
            color2 = Theme.frameSymbolLightColor[Theme.style].getColor();
        }
        if (!b) {
            color2 = ColorRoutines.getAverage(color2, color);
        }
        graphics.setColor(color2);
        switch (this.type) {
            case 1: {
                if (b2) {
                    graphics.drawLine(n3 + 7, n4 + 4, n3 + 7, n4 + 7);
                    graphics.drawLine(n3 + 9, n4 + 6, n3 + 14, n4 + 6);
                    graphics.drawLine(n3 + 15, n4 + 6, n3 + 15, n4 + 10);
                    graphics.drawLine(n3 + 13, n4 + 12, n3 + 16, n4 + 12);
                    graphics.drawLine(n3 + 3, n4 + 9, n3 + 3, n4 + 16);
                    graphics.drawLine(n3 + 3, n4 + 17, n3 + 12, n4 + 17);
                    graphics.drawLine(n3 + 5, n4 + 11, n3 + 10, n4 + 11);
                    graphics.drawLine(n3 + 11, n4 + 11, n3 + 11, n4 + 15);
                    break;
                }
                graphics.drawLine(n3 + 4, n4 + 6, n3 + 4, n4 + 15);
                graphics.drawLine(n3 + 4, n4 + 16, n3 + 15, n4 + 16);
                graphics.drawLine(n3 + 6, n4 + 8, n3 + 13, n4 + 8);
                graphics.drawLine(n3 + 14, n4 + 8, n3 + 14, n4 + 14);
                break;
            }
            case 2: {
                graphics.drawLine(n3 + 4, n4 + 13, n3 + 4, n4 + 16);
                graphics.drawLine(n3 + 5, n4 + 16, n3 + 11, n4 + 16);
                break;
            }
        }
        Color color3;
        if (this.type == 0) {
            color3 = Theme.frameSymbolCloseDarkColor[Theme.style].getColor();
        }
        else {
            color3 = Theme.frameSymbolDarkColor[Theme.style].getColor();
        }
        if (!b) {
            color3 = ColorRoutines.getAverage(color3, color);
        }
        graphics.setColor(color3);
        switch (this.type) {
            case 0: {
                graphics.drawLine(n3 + 5, n4 + 4, n3 + 6, n4 + 4);
                graphics.drawLine(n3 + 14, n4 + 4, n3 + 15, n4 + 4);
                graphics.drawLine(n3 + 7, n4 + 5, n3 + 7, n4 + 5);
                graphics.drawLine(n3 + 13, n4 + 5, n3 + 13, n4 + 5);
                graphics.drawLine(n3 + 8, n4 + 6, n3 + 8, n4 + 6);
                graphics.drawLine(n3 + 12, n4 + 6, n3 + 12, n4 + 6);
                graphics.drawLine(n3 + 9, n4 + 7, n3 + 9, n4 + 7);
                graphics.drawLine(n3 + 11, n4 + 7, n3 + 11, n4 + 7);
                graphics.drawLine(n3 + 10, n4 + 8, n3 + 10, n4 + 8);
                graphics.drawLine(n3 + 8, n4 + 10, n3 + 8, n4 + 10);
                graphics.drawLine(n3 + 12, n4 + 10, n3 + 12, n4 + 10);
                graphics.drawLine(n3 + 7, n4 + 11, n3 + 7, n4 + 11);
                graphics.drawLine(n3 + 13, n4 + 11, n3 + 13, n4 + 11);
                graphics.drawLine(n3 + 6, n4 + 12, n3 + 6, n4 + 12);
                graphics.drawLine(n3 + 14, n4 + 12, n3 + 14, n4 + 12);
                graphics.drawLine(n3 + 5, n4 + 13, n3 + 5, n4 + 13);
                graphics.drawLine(n3 + 15, n4 + 13, n3 + 15, n4 + 13);
                graphics.drawLine(n3 + 4, n4 + 14, n3 + 4, n4 + 14);
                graphics.drawLine(n3 + 16, n4 + 14, n3 + 16, n4 + 14);
                break;
            }
            case 1: {
                if (b2) {
                    graphics.drawLine(n3 + 8, n4 + 3, n3 + 15, n4 + 3);
                    graphics.drawLine(n3 + 16, n4 + 3, n3 + 16, n4 + 11);
                    graphics.drawLine(n3 + 13, n4 + 11, n3 + 15, n4 + 11);
                    graphics.drawLine(n3 + 8, n4 + 6, n3 + 8, n4 + 7);
                    graphics.drawLine(n3 + 4, n4 + 8, n3 + 11, n4 + 8);
                    graphics.drawLine(n3 + 12, n4 + 8, n3 + 12, n4 + 16);
                    graphics.drawLine(n3 + 4, n4 + 16, n3 + 11, n4 + 16);
                    graphics.drawLine(n3 + 4, n4 + 11, n3 + 4, n4 + 15);
                    break;
                }
                graphics.drawLine(n3 + 5, n4 + 5, n3 + 14, n4 + 5);
                graphics.drawLine(n3 + 15, n4 + 5, n3 + 15, n4 + 15);
                graphics.drawLine(n3 + 5, n4 + 15, n3 + 14, n4 + 15);
                graphics.drawLine(n3 + 5, n4 + 8, n3 + 5, n4 + 14);
                break;
            }
            case 2: {
                graphics.drawLine(n3 + 5, n4 + 13, n3 + 10, n4 + 13);
                graphics.drawLine(n3 + 11, n4 + 13, n3 + 11, n4 + 15);
                break;
            }
        }
    }
    
    public static TinyWindowButtonUI createButtonUIForType(final int n) {
        return new TinyWindowButtonUI(n);
    }
    
    public Dimension getPreferredSize(final JComponent component) {
        if (((AbstractButton)component).getClientProperty("externalFrameButton") == Boolean.TRUE) {
            return Theme.frameExternalButtonSize[Theme.derivedStyle[Theme.style]];
        }
        if (component.getParent() instanceof TinyInternalFrameTitlePane && ((TinyInternalFrameTitlePane)component.getParent()).isPalette()) {
            return Theme.framePaletteButtonSize[Theme.derivedStyle[Theme.style]];
        }
        return Theme.frameInternalButtonSize[Theme.derivedStyle[Theme.style]];
    }
}
