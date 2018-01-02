// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.Container;
import de.muntjak.tinylookandfeel.controlpanel.DrawRoutines;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Image;
import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import javax.swing.plaf.ColorUIResource;
import java.awt.Color;
import javax.swing.AbstractButton;
import java.awt.Graphics;
import java.awt.Component;
import java.util.HashMap;
import javax.swing.plaf.metal.MetalCheckBoxIcon;

public class TinyCheckBoxIcon extends MetalCheckBoxIcon
{
    static HashMap cache;
    static final int[][] a;
    
    protected int getControlSize() {
        return this.getIconWidth();
    }
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        final AbstractButton abstractButton = (AbstractButton)component;
        ColorUIResource color;
        if (!abstractButton.isEnabled()) {
            color = Theme.buttonDisabledColor[Theme.style].getColor();
        }
        else if (abstractButton.getModel().isPressed()) {
            if (abstractButton.getModel().isRollover()) {
                color = Theme.buttonPressedColor[Theme.style].getColor();
            }
            else {
                color = Theme.buttonNormalColor[Theme.style].getColor();
            }
        }
        else if (abstractButton.getModel().isRollover() && Theme.buttonRollover[Theme.style]) {
            color = Theme.buttonRolloverBgColor[Theme.style].getColor();
        }
        else {
            color = Theme.buttonNormalColor[Theme.style].getColor();
        }
        graphics.setColor(color);
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyCheck(graphics, abstractButton, color, n, n2, this.getIconWidth(), this.getIconHeight());
                break;
            }
            case 1: {
                this.drawWinCheck(graphics, abstractButton, color, n, n2, this.getIconWidth(), this.getIconHeight());
                break;
            }
            case 2: {
                if (TinyLookAndFeel.controlPanelInstantiated) {
                    this.drawXpCheckNoCache(graphics, abstractButton, color, n, n2, this.getIconWidth(), this.getIconHeight());
                    break;
                }
                this.drawXpCheck(graphics, abstractButton, color, n, n2, this.getIconWidth(), this.getIconHeight());
                break;
            }
        }
        if (!abstractButton.isSelected()) {
            return;
        }
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.buttonCheckDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.buttonCheckColor[Theme.style].getColor());
        }
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyCheckMark(graphics, abstractButton, color, n, n2);
                break;
            }
            case 1: {
                this.drawWinCheckMark(graphics, n, n2);
                break;
            }
            case 2: {
                this.drawXpCheckMark(graphics, n, n2);
                break;
            }
        }
    }
    
    private void drawTinyCheck(final Graphics graphics, final AbstractButton abstractButton, final Color color, final int n, final int n2, final int n3, final int n4) {
    }
    
    private void drawWinCheck(final Graphics graphics, final AbstractButton abstractButton, final Color color, final int n, final int n2, final int n3, final int n4) {
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.buttonLightDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.buttonLightColor[Theme.style].getColor());
        }
        if (abstractButton.getModel().isPressed() && abstractButton.getModel().isRollover()) {
            graphics.drawLine(n + 0, n2 + 12, n + 12, n2 + 12);
            graphics.drawLine(n + 12, n2 + 0, n + 12, n2 + 11);
        }
        else {
            graphics.fillRect(n, n2, n3, n4);
        }
        if (!abstractButton.isEnabled()) {
            graphics.setColor(ColorRoutines.getAverage(color, Theme.buttonDarkDisabledColor[Theme.style].getColor()));
        }
        else {
            graphics.setColor(ColorRoutines.getAverage(color, Theme.buttonDarkColor[Theme.style].getColor()));
        }
        graphics.drawLine(n + 0, n2 + 0, n + 11, n2 + 0);
        graphics.drawLine(n + 0, n2 + 1, n + 0, n2 + 11);
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.buttonDarkDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.buttonDarkColor[Theme.style].getColor());
        }
        graphics.drawLine(n + 1, n2 + 1, n + 10, n2 + 1);
        graphics.drawLine(n + 1, n2 + 2, n + 1, n2 + 10);
        graphics.setColor(Theme.backColor[Theme.style].getColor());
        graphics.drawLine(n + 1, n2 + 11, n + 11, n2 + 11);
        graphics.drawLine(n + 11, n2 + 1, n + 11, n2 + 10);
    }
    
    private void drawXpCheck(final Graphics graphics, final AbstractButton abstractButton, Color lighten, final int n, final int n2, final int n3, final int n4) {
        final boolean pressed = abstractButton.getModel().isPressed();
        final boolean armed = abstractButton.getModel().isArmed();
        final boolean enabled = abstractButton.isEnabled();
        final boolean rollover = abstractButton.getModel().isRollover();
        final boolean b = Theme.buttonFocusBorder[Theme.style] && !rollover && abstractButton.isFocusOwner();
        final CheckKey checkKey = new CheckKey(lighten, pressed, enabled, rollover || armed, b);
        final Object value = TinyCheckBoxIcon.cache.get(checkKey);
        if (value != null) {
            graphics.drawImage((Image)value, n, n2, abstractButton);
            return;
        }
        final BufferedImage bufferedImage = new BufferedImage(n3, n4, 2);
        final Graphics graphics2 = bufferedImage.getGraphics();
        int n5 = Theme.buttonSpreadLight[Theme.style];
        int n6 = Theme.buttonSpreadDark[Theme.style];
        if (!abstractButton.isEnabled()) {
            n5 = Theme.buttonSpreadLightDisabled[Theme.style];
            n6 = Theme.buttonSpreadDarkDisabled[Theme.style];
        }
        final int n7 = n5 * 5;
        int n8 = n6 * 4;
        if (pressed && (rollover || armed)) {
            n8 *= 2;
        }
        lighten = ColorRoutines.lighten(lighten, n7);
        graphics2.setColor(ColorRoutines.darken(lighten, n8));
        graphics2.fillRect(1, 1, n3 - 2, n4 - 2);
        for (int i = 0; i < 11; ++i) {
            for (int j = 0; j < 11; ++j) {
                graphics2.setColor(new Color(lighten.getRed(), lighten.getGreen(), lighten.getBlue(), 255 - TinyCheckBoxIcon.a[j][i]));
                graphics2.drawLine(j + 1, i + 1, j + 1, i + 1);
            }
        }
        if (!abstractButton.isEnabled()) {
            graphics2.setColor(Theme.buttonBorderDisabledColor[Theme.style].getColor());
            graphics2.drawRect(0, 0, n3 - 1, n4 - 1);
        }
        else {
            graphics2.setColor(Theme.buttonBorderColor[Theme.style].getColor());
            graphics2.drawRect(0, 0, n3 - 1, n4 - 1);
            if (rollover && Theme.buttonRollover[Theme.style] && !pressed) {
                DrawRoutines.drawRolloverCheckBorder(graphics2, Theme.buttonRolloverColor[Theme.style].getColor(), 0, 0, n3, n4);
            }
            else if (b && !pressed) {
                DrawRoutines.drawRolloverCheckBorder(graphics2, Theme.buttonDefaultColor[Theme.style].getColor(), 0, 0, n3, n4);
            }
        }
        graphics2.dispose();
        graphics.drawImage(bufferedImage, n, n2, abstractButton);
        TinyCheckBoxIcon.cache.put(checkKey, bufferedImage);
    }
    
    private void drawXpCheckNoCache(final Graphics graphics, final AbstractButton abstractButton, Color lighten, final int n, final int n2, final int n3, final int n4) {
        final boolean pressed = abstractButton.getModel().isPressed();
        final boolean armed = abstractButton.getModel().isArmed();
        final boolean enabled = abstractButton.isEnabled();
        final boolean rollover = abstractButton.getModel().isRollover();
        final boolean b = Theme.buttonFocusBorder[Theme.style] && !rollover && abstractButton.isFocusOwner();
        final boolean b2 = !pressed && !armed && !rollover && !b;
        Image image = null;
        Object o = null;
        if (b2) {
            if (enabled) {
                o = new EnabledCheckKey(lighten, Theme.buttonBorderColor[Theme.style].getColor());
            }
            else {
                o = new DisabledCheckKey(lighten, Theme.buttonBorderDisabledColor[Theme.style].getColor());
            }
            final Object value = TinyCheckBoxIcon.cache.get(o);
            if (value != null) {
                graphics.drawImage((Image)value, n, n2, abstractButton);
                return;
            }
            image = new BufferedImage(n3, n4, 2);
        }
        int n5 = Theme.buttonSpreadLight[Theme.style];
        int n6 = Theme.buttonSpreadDark[Theme.style];
        if (!enabled) {
            n5 = Theme.buttonSpreadLightDisabled[Theme.style];
            n6 = Theme.buttonSpreadDarkDisabled[Theme.style];
        }
        final int n7 = n5 * 5;
        int n8 = n6 * 4;
        if (pressed && (rollover || armed)) {
            n8 *= 2;
        }
        lighten = ColorRoutines.lighten(lighten, n7);
        int n9 = n;
        int n10 = n2;
        Graphics graphics2;
        if (image != null) {
            graphics2 = image.getGraphics();
            Color color = abstractButton.getBackground();
            if (!abstractButton.isOpaque()) {
                Container container = abstractButton.getParent();
                color = container.getBackground();
                while (container != null && !container.isOpaque()) {
                    container = container.getParent();
                    color = container.getBackground();
                }
            }
            graphics2.setColor(color);
            graphics2.fillRect(0, 0, n3 - 1, n4 - 1);
            n9 = 0;
            n10 = 0;
        }
        else {
            graphics2 = graphics;
            graphics2.translate(n, n2);
        }
        graphics2.setColor(ColorRoutines.darken(lighten, n8));
        graphics2.fillRect(1, 1, n3 - 2, n4 - 2);
        for (int i = 0; i < 11; ++i) {
            for (int j = 0; j < 11; ++j) {
                graphics2.setColor(new Color(lighten.getRed(), lighten.getGreen(), lighten.getBlue(), 255 - TinyCheckBoxIcon.a[j][i]));
                graphics2.drawLine(j + 1, i + 1, j + 1, i + 1);
            }
        }
        if (image == null) {
            graphics2.translate(-n, -n2);
        }
        if (!enabled) {
            graphics2.setColor(Theme.buttonBorderDisabledColor[Theme.style].getColor());
            graphics2.drawRect(n9, n10, n3 - 1, n4 - 1);
        }
        else {
            graphics2.setColor(Theme.buttonBorderColor[Theme.style].getColor());
            graphics2.drawRect(n9, n10, n3 - 1, n4 - 1);
            if (rollover && Theme.buttonRollover[Theme.style] && !pressed) {
                DrawRoutines.drawRolloverCheckBorder(graphics2, Theme.buttonRolloverColor[Theme.style].getColor(), n9, n10, n3, n4);
            }
            else if (b && !pressed) {
                DrawRoutines.drawRolloverCheckBorder(graphics2, Theme.buttonDefaultColor[Theme.style].getColor(), n9, n10, n3, n4);
            }
        }
        if (image != null) {
            graphics2.dispose();
            graphics.drawImage(image, n, n2, abstractButton);
            TinyCheckBoxIcon.cache.put(o, image);
        }
    }
    
    private void drawTinyCheckMark(final Graphics graphics, final AbstractButton abstractButton, final Color color, final int n, final int n2) {
        graphics.drawLine(n + 2, n2 + 5, n + 3, n2 + 5);
        graphics.drawLine(n + 3, n2 + 6, n + 4, n2 + 6);
        graphics.drawLine(n + 4, n2 + 7, n + 6, n2 + 7);
        graphics.drawLine(n + 5, n2 + 8, n + 5, n2 + 8);
        graphics.drawLine(n + 6, n2 + 6, n + 7, n2 + 6);
        graphics.drawLine(n + 7, n2 + 5, n + 8, n2 + 5);
        graphics.drawLine(n + 8, n2 + 4, n + 9, n2 + 4);
        graphics.drawLine(n + 9, n2 + 3, n + 10, n2 + 3);
        graphics.drawLine(n + 10, n2 + 2, n + 10, n2 + 2);
        graphics.drawLine(n + 12, n2 + 1, n + 12, n2 + 1);
        if (!abstractButton.isEnabled()) {
            graphics.setColor(ColorRoutines.darken(color, 10));
        }
        else {
            graphics.setColor(ColorRoutines.darken(color, 20));
        }
        graphics.drawLine(n + 3, n2 + 7, n + 3, n2 + 7);
        graphics.drawLine(n + 4, n2 + 8, n + 4, n2 + 8);
        graphics.drawLine(n + 6, n2 + 9, n + 6, n2 + 9);
        graphics.drawLine(n + 7, n2 + 8, n + 7, n2 + 8);
        graphics.drawLine(n + 8, n2 + 7, n + 8, n2 + 7);
        graphics.drawLine(n + 9, n2 + 6, n + 9, n2 + 6);
        graphics.drawLine(n + 12, n2 + 3, n + 12, n2 + 3);
        graphics.drawLine(n + 13, n2 + 2, n + 13, n2 + 2);
        if (!abstractButton.isEnabled()) {
            graphics.setColor(ColorRoutines.darken(color, 20));
        }
        else {
            graphics.setColor(ColorRoutines.darken(color, 40));
        }
        graphics.drawLine(n + 5, n2 + 9, n + 5, n2 + 9);
        graphics.drawLine(n + 6, n2 + 8, n + 6, n2 + 8);
        graphics.drawLine(n + 7, n2 + 7, n + 7, n2 + 7);
        graphics.drawLine(n + 8, n2 + 6, n + 8, n2 + 6);
        graphics.drawLine(n + 9, n2 + 5, n + 9, n2 + 5);
        graphics.drawLine(n + 12, n2 + 2, n + 12, n2 + 2);
    }
    
    private void drawWinCheckMark(final Graphics graphics, final int n, final int n2) {
        graphics.drawLine(n + 3, n2 + 5, n + 3, n2 + 7);
        graphics.drawLine(n + 4, n2 + 6, n + 4, n2 + 8);
        graphics.drawLine(n + 5, n2 + 7, n + 5, n2 + 9);
        graphics.drawLine(n + 6, n2 + 6, n + 6, n2 + 8);
        graphics.drawLine(n + 7, n2 + 5, n + 7, n2 + 7);
        graphics.drawLine(n + 8, n2 + 4, n + 8, n2 + 6);
        graphics.drawLine(n + 9, n2 + 3, n + 9, n2 + 5);
    }
    
    private void drawXpCheckMark(final Graphics graphics, final int n, final int n2) {
        graphics.drawLine(n + 3, n2 + 5, n + 3, n2 + 7);
        graphics.drawLine(n + 4, n2 + 6, n + 4, n2 + 8);
        graphics.drawLine(n + 5, n2 + 7, n + 5, n2 + 9);
        graphics.drawLine(n + 6, n2 + 6, n + 6, n2 + 8);
        graphics.drawLine(n + 7, n2 + 5, n + 7, n2 + 7);
        graphics.drawLine(n + 8, n2 + 4, n + 8, n2 + 6);
        graphics.drawLine(n + 9, n2 + 3, n + 9, n2 + 5);
    }
    
    public int getIconWidth() {
        return Theme.checkSize[Theme.derivedStyle[Theme.style]].width;
    }
    
    public int getIconHeight() {
        return Theme.checkSize[Theme.derivedStyle[Theme.style]].height;
    }
    
    static {
        TinyCheckBoxIcon.cache = new HashMap();
        a = new int[][] { { 255, 255, 255, 242, 228, 209, 187, 165, 142, 120, 104 }, { 255, 255, 242, 228, 209, 187, 165, 142, 120, 104, 86 }, { 255, 242, 228, 209, 187, 165, 142, 120, 104, 86, 72 }, { 242, 228, 209, 187, 165, 142, 120, 104, 86, 72, 56 }, { 228, 209, 187, 165, 142, 120, 104, 86, 72, 56, 42 }, { 209, 187, 165, 142, 120, 104, 86, 72, 56, 42, 28 }, { 187, 165, 142, 120, 104, 86, 72, 56, 42, 28, 17 }, { 165, 142, 120, 104, 86, 72, 56, 42, 28, 17, 9 }, { 142, 120, 104, 86, 72, 56, 42, 28, 17, 9, 0 }, { 120, 104, 86, 72, 56, 42, 28, 17, 9, 0, 0 }, { 104, 86, 72, 56, 42, 28, 17, 9, 0, 0, 0 } };
    }
    
    static class CheckKey
    {
        private Color c;
        private boolean pressed;
        private boolean enabled;
        private boolean rollover;
        private boolean focused;
        
        CheckKey(final Color c, final boolean pressed, final boolean enabled, final boolean rollover, final boolean focused) {
            this.c = c;
            this.pressed = pressed;
            this.enabled = enabled;
            this.rollover = rollover;
            this.focused = focused;
        }
        
        public boolean equals(final Object o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof CheckKey)) {
                return false;
            }
            final CheckKey checkKey = (CheckKey)o;
            return this.pressed == checkKey.pressed && this.enabled == checkKey.enabled && this.rollover == checkKey.rollover && this.focused == checkKey.focused && this.c.equals(checkKey.c);
        }
        
        public int hashCode() {
            return this.c.hashCode() * (this.pressed ? 1 : 2) * (this.enabled ? 4 : 8) * (this.rollover ? 16 : 32);
        }
    }
    
    static class DisabledCheckKey
    {
        int spread1;
        int spread2;
        Color c;
        Color back;
        
        DisabledCheckKey(final Color c, final Color back) {
            this.spread1 = Theme.buttonSpreadLightDisabled[Theme.style];
            this.spread2 = Theme.buttonSpreadDarkDisabled[Theme.style];
            this.c = c;
            this.back = back;
        }
        
        public boolean equals(final Object o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof DisabledCheckKey)) {
                return false;
            }
            final DisabledCheckKey disabledCheckKey = (DisabledCheckKey)o;
            return this.c.equals(disabledCheckKey.c) && this.back.equals(disabledCheckKey.back) && this.spread1 == disabledCheckKey.spread1 && this.spread2 == disabledCheckKey.spread2;
        }
        
        public int hashCode() {
            return this.c.hashCode() * this.back.hashCode() * this.spread1 * this.spread2;
        }
    }
    
    static class EnabledCheckKey
    {
        int spread1;
        int spread2;
        Color c;
        Color back;
        
        EnabledCheckKey(final Color c, final Color back) {
            this.spread1 = Theme.buttonSpreadLight[Theme.style];
            this.spread2 = Theme.buttonSpreadDark[Theme.style];
            this.c = c;
            this.back = back;
        }
        
        public boolean equals(final Object o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof EnabledCheckKey)) {
                return false;
            }
            final EnabledCheckKey enabledCheckKey = (EnabledCheckKey)o;
            return this.c.equals(enabledCheckKey.c) && this.back.equals(enabledCheckKey.back) && this.spread1 == enabledCheckKey.spread1 && this.spread2 == enabledCheckKey.spread2;
        }
        
        public int hashCode() {
            return this.c.hashCode() * this.back.hashCode() * this.spread1 * this.spread2;
        }
    }
}
