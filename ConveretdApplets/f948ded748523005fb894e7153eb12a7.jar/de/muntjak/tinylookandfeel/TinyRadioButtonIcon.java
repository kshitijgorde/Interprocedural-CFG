// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.Container;
import de.muntjak.tinylookandfeel.controlpanel.DrawRoutines;
import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Image;
import javax.swing.plaf.ColorUIResource;
import java.awt.Color;
import javax.swing.AbstractButton;
import java.awt.Graphics;
import java.awt.Component;
import java.util.HashMap;

class TinyRadioButtonIcon extends TinyCheckBoxIcon
{
    static HashMap cache;
    static final int[][] a;
    
    public void paintIcon(final Component component, final Graphics graphics, final int n, final int n2) {
        final AbstractButton abstractButton = (AbstractButton)component;
        ColorUIResource color;
        if (!abstractButton.isEnabled()) {
            color = Theme.buttonDisabledColor[Theme.style].getColor();
        }
        else if (abstractButton.getModel().isPressed()) {
            if (abstractButton.getModel().isRollover() || abstractButton.getModel().isArmed()) {
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
                this.drawTinyRadio(graphics, abstractButton, color, n, n2, this.getIconWidth(), this.getIconHeight());
                break;
            }
            case 1: {
                this.drawWinRadio(graphics, abstractButton, color, n, n2, this.getIconWidth(), this.getIconHeight());
                break;
            }
            case 2: {
                if (TinyLookAndFeel.controlPanelInstantiated) {
                    this.drawXpRadioNoCache(graphics, abstractButton, color, n, n2, this.getIconWidth(), this.getIconHeight());
                    break;
                }
                this.drawXpRadio(graphics, abstractButton, color, n, n2, this.getIconWidth(), this.getIconHeight());
                break;
            }
        }
        if (!abstractButton.isSelected()) {
            return;
        }
        ColorUIResource color2;
        if (!abstractButton.isEnabled()) {
            color2 = Theme.buttonCheckDisabledColor[Theme.style].getColor();
        }
        else {
            color2 = Theme.buttonCheckColor[Theme.style].getColor();
        }
        graphics.setColor(color2);
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyCheckMark(graphics, color2, n, n2);
                break;
            }
            case 1: {
                this.drawWinCheckMark(graphics, color2, n, n2);
                break;
            }
            case 2: {
                this.drawXpCheckMark(graphics, color2, n, n2);
                break;
            }
        }
    }
    
    private void drawTinyRadio(final Graphics graphics, final AbstractButton abstractButton, final Color color, final int n, final int n2, final int n3, final int n4) {
    }
    
    private void drawWinRadio(final Graphics graphics, final AbstractButton abstractButton, final Color color, final int n, final int n2, final int n3, final int n4) {
        if (abstractButton.getModel().isPressed() && abstractButton.getModel().isRollover()) {
            graphics.setColor(Theme.backColor[Theme.style].getColor());
        }
        else if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.buttonLightDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.buttonLightColor[Theme.style].getColor());
        }
        graphics.fillRect(n + 2, n2 + 2, 8, 8);
        graphics.setColor(Theme.backColor[Theme.style].getColor());
        graphics.drawLine(n + 9, n2 + 3, n + 9, n2 + 3);
        graphics.drawLine(n + 10, n2 + 4, n + 10, n2 + 7);
        graphics.drawLine(n + 9, n2 + 8, n + 9, n2 + 8);
        graphics.drawLine(n + 8, n2 + 9, n + 9, n2 + 9);
        graphics.drawLine(n + 2, n2 + 9, n + 3, n2 + 9);
        graphics.drawLine(n + 4, n2 + 10, n + 7, n2 + 10);
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.buttonLightDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.buttonLightColor[Theme.style].getColor());
        }
        graphics.drawLine(n + 10, n2 + 2, n + 10, n2 + 3);
        graphics.drawLine(n + 11, n2 + 4, n + 11, n2 + 7);
        graphics.drawLine(n + 10, n2 + 8, n + 10, n2 + 9);
        graphics.drawLine(n + 8, n2 + 10, n + 9, n2 + 10);
        graphics.drawLine(n + 4, n2 + 11, n + 7, n2 + 11);
        graphics.drawLine(n + 2, n2 + 10, n + 3, n2 + 10);
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.buttonBorderDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.buttonBorderColor[Theme.style].getColor());
        }
        graphics.drawLine(n + 4, n2 + 1, n + 7, n2 + 1);
        graphics.drawLine(n + 2, n2 + 2, n + 3, n2 + 2);
        graphics.drawLine(n + 8, n2 + 2, n + 9, n2 + 2);
        graphics.drawLine(n + 2, n2 + 3, n + 2, n2 + 3);
        graphics.drawLine(n + 1, n2 + 4, n + 1, n2 + 7);
        graphics.drawLine(n + 2, n2 + 8, n + 2, n2 + 8);
        if (!abstractButton.isEnabled()) {
            graphics.setColor(Theme.buttonDarkDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.buttonDarkColor[Theme.style].getColor());
        }
        graphics.drawLine(n + 4, n2 + 0, n + 7, n2 + 0);
        graphics.drawLine(n + 2, n2 + 1, n + 3, n2 + 1);
        graphics.drawLine(n + 8, n2 + 1, n + 9, n2 + 1);
        graphics.drawLine(n + 1, n2 + 2, n + 1, n2 + 3);
        graphics.drawLine(n + 0, n2 + 4, n + 0, n2 + 7);
        graphics.drawLine(n + 1, n2 + 8, n + 1, n2 + 9);
    }
    
    private void drawXpRadio(final Graphics graphics, final AbstractButton abstractButton, Color lighten, final int n, final int n2, final int n3, final int n4) {
        final boolean pressed = abstractButton.getModel().isPressed();
        final boolean armed = abstractButton.getModel().isArmed();
        final boolean enabled = abstractButton.isEnabled();
        final boolean rollover = abstractButton.getModel().isRollover();
        final boolean b = Theme.buttonFocusBorder[Theme.style] && !rollover && abstractButton.isFocusOwner();
        Color color = abstractButton.getBackground();
        if (!abstractButton.isOpaque()) {
            Container container = abstractButton.getParent();
            color = container.getBackground();
            while (container != null && !container.isOpaque()) {
                container = container.getParent();
                color = container.getBackground();
            }
        }
        final RadioKey radioKey = new RadioKey(lighten, color, pressed, enabled, rollover || armed, b);
        final Object value = TinyRadioButtonIcon.cache.get(radioKey);
        if (value != null) {
            graphics.drawImage((Image)value, n, n2, abstractButton);
            return;
        }
        final BufferedImage bufferedImage = new BufferedImage(n3, n4, 2);
        final Graphics graphics2 = bufferedImage.getGraphics();
        graphics2.setColor(color);
        graphics2.fillRect(0, 0, n3, n4);
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
        graphics2.setColor(ColorRoutines.darken(lighten, n8));
        graphics2.fillRect(2, 2, n3 - 4, n4 - 4);
        graphics2.fillRect(1, 5, 1, 3);
        graphics2.fillRect(11, 5, 1, 3);
        graphics2.fillRect(5, 1, 3, 1);
        graphics2.fillRect(5, 11, 3, 1);
        for (int i = 0; i < 11; ++i) {
            for (int j = 0; j < 11; ++j) {
                graphics2.setColor(new Color(lighten.getRed(), lighten.getGreen(), lighten.getBlue(), 255 - TinyRadioButtonIcon.a[j][i]));
                graphics2.drawLine(j + 1, i + 1, j + 1, i + 1);
            }
        }
        if (!enabled) {
            DrawRoutines.drawXpRadioBorder(graphics2, Theme.buttonBorderDisabledColor[Theme.style].getColor(), 0, 0, n3, n4);
        }
        else {
            if (rollover && Theme.buttonRollover[Theme.style] && !pressed) {
                DrawRoutines.drawXpRadioRolloverBorder(graphics2, Theme.buttonRolloverColor[Theme.style].getColor(), 0, 0, n3, n4);
            }
            else if (b && !pressed) {
                DrawRoutines.drawXpRadioRolloverBorder(graphics2, Theme.buttonDefaultColor[Theme.style].getColor(), 0, 0, n3, n4);
            }
            DrawRoutines.drawXpRadioBorder(graphics2, Theme.buttonBorderColor[Theme.style].getColor(), 0, 0, n3, n4);
        }
        graphics2.dispose();
        graphics.drawImage(bufferedImage, n, n2, abstractButton);
        TinyRadioButtonIcon.cache.put(radioKey, bufferedImage);
    }
    
    private void drawXpRadioNoCache(final Graphics graphics, final AbstractButton abstractButton, Color lighten, final int n, final int n2, final int n3, final int n4) {
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
                o = new EnabledRadioKey(lighten, Theme.buttonBorderColor[Theme.style].getColor());
            }
            else {
                o = new DisabledRadioKey(lighten, Theme.buttonBorderDisabledColor[Theme.style].getColor());
            }
            final Object value = TinyRadioButtonIcon.cache.get(o);
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
        graphics2.fillRect(2, 2, n3 - 4, n4 - 4);
        graphics2.fillRect(1, 5, 1, 3);
        graphics2.fillRect(11, 5, 1, 3);
        graphics2.fillRect(6, 1, 1, 1);
        graphics2.fillRect(6, 11, 1, 1);
        for (int i = 0; i < 11; ++i) {
            for (int j = 0; j < 11; ++j) {
                graphics2.setColor(new Color(lighten.getRed(), lighten.getGreen(), lighten.getBlue(), 255 - TinyRadioButtonIcon.a[j][i]));
                graphics2.drawLine(j + 1, i + 1, j + 1, i + 1);
            }
        }
        if (image == null) {
            graphics2.translate(-n, -n2);
        }
        if (!enabled) {
            DrawRoutines.drawXpRadioBorder(graphics2, Theme.buttonBorderDisabledColor[Theme.style].getColor(), n9, n10, n3, n4);
        }
        else {
            if (rollover && Theme.buttonRollover[Theme.style] && !pressed) {
                DrawRoutines.drawXpRadioRolloverBorder(graphics2, Theme.buttonRolloverColor[Theme.style].getColor(), n9, n10, n3, n4);
            }
            else if (b && !pressed) {
                DrawRoutines.drawXpRadioRolloverBorder(graphics2, Theme.buttonDefaultColor[Theme.style].getColor(), n9, n10, n3, n4);
            }
            DrawRoutines.drawXpRadioBorder(graphics2, Theme.buttonBorderColor[Theme.style].getColor(), n9, n10, n3, n4);
        }
        if (image != null) {
            graphics2.dispose();
            graphics.drawImage(image, n, n2, abstractButton);
            TinyRadioButtonIcon.cache.put(o, image);
        }
    }
    
    private void drawTinyCheckMark(final Graphics graphics, final Color color, final int n, final int n2) {
    }
    
    private void drawWinCheckMark(final Graphics graphics, final Color color, final int n, final int n2) {
        graphics.fillRect(n + 4, n2 + 5, 4, 2);
        graphics.drawLine(n + 5, n2 + 4, n + 6, n2 + 4);
        graphics.drawLine(n + 5, n2 + 7, n + 6, n2 + 7);
    }
    
    private void drawXpCheckMark(final Graphics graphics, final Color color, final int n, final int n2) {
        graphics.translate(n, n2);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 224));
        graphics.fillRect(5, 5, 3, 3);
        graphics.setColor(color);
        graphics.drawLine(6, 6, 6, 6);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 192));
        graphics.drawLine(6, 4, 6, 4);
        graphics.drawLine(4, 6, 4, 6);
        graphics.drawLine(8, 6, 8, 6);
        graphics.drawLine(6, 8, 6, 8);
        graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 128));
        graphics.drawLine(5, 4, 5, 4);
        graphics.drawLine(7, 4, 7, 4);
        graphics.drawLine(4, 5, 4, 5);
        graphics.drawLine(8, 5, 8, 5);
        graphics.drawLine(4, 7, 4, 7);
        graphics.drawLine(8, 7, 8, 7);
        graphics.drawLine(5, 8, 5, 8);
        graphics.drawLine(7, 8, 7, 8);
        graphics.translate(-n, -n2);
    }
    
    static {
        TinyRadioButtonIcon.cache = new HashMap();
        a = new int[][] { { 255, 255, 255, 242, 228, 209, 187, 165, 142, 255, 255 }, { 255, 255, 242, 228, 209, 187, 165, 142, 120, 104, 255 }, { 255, 242, 228, 209, 187, 165, 142, 120, 104, 86, 72 }, { 242, 228, 209, 187, 165, 142, 120, 104, 86, 72, 56 }, { 228, 209, 187, 165, 142, 120, 104, 86, 72, 56, 42 }, { 209, 187, 165, 142, 120, 104, 86, 72, 56, 42, 28 }, { 187, 165, 142, 120, 104, 86, 72, 56, 42, 28, 17 }, { 165, 142, 120, 104, 86, 72, 56, 42, 28, 17, 9 }, { 142, 120, 104, 86, 72, 56, 42, 28, 17, 9, 0 }, { 255, 104, 86, 72, 56, 42, 28, 17, 9, 0, 255 }, { 255, 255, 72, 56, 42, 28, 17, 9, 0, 255, 255 } };
    }
    
    static class RadioKey
    {
        private Color c;
        private Color background;
        private boolean pressed;
        private boolean enabled;
        private boolean rollover;
        private boolean focused;
        
        RadioKey(final Color c, final Color background, final boolean pressed, final boolean enabled, final boolean rollover, final boolean focused) {
            this.c = c;
            this.background = background;
            this.pressed = pressed;
            this.enabled = enabled;
            this.rollover = rollover;
            this.focused = focused;
        }
        
        public boolean equals(final Object o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof RadioKey)) {
                return false;
            }
            final RadioKey radioKey = (RadioKey)o;
            return this.pressed == radioKey.pressed && this.enabled == radioKey.enabled && this.rollover == radioKey.rollover && this.focused == radioKey.focused && this.c.equals(radioKey.c) && this.background.equals(radioKey.background);
        }
        
        public int hashCode() {
            return this.c.hashCode() * this.background.hashCode() * (this.pressed ? 1 : 2) * (this.enabled ? 4 : 8) * (this.rollover ? 16 : 32);
        }
    }
    
    static class DisabledRadioKey
    {
        int spread1;
        int spread2;
        Color c;
        Color back;
        
        DisabledRadioKey(final Color c, final Color back) {
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
    
    static class EnabledRadioKey
    {
        int spread1;
        int spread2;
        Color c;
        Color back;
        
        EnabledRadioKey(final Color c, final Color back) {
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
