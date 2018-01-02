// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Image;
import javax.swing.plaf.ColorUIResource;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.plaf.basic.BasicArrowButton;

public class TinyScrollButton extends BasicArrowButton
{
    static HashMap cache;
    private boolean isRollover;
    private TinyScrollBarUI scrollbarUI;
    protected static Dimension[] size;
    
    public TinyScrollButton(final int n, final TinyScrollBarUI scrollbarUI) {
        super(n);
        this.scrollbarUI = scrollbarUI;
        this.setBorder(null);
        this.setRolloverEnabled(true);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setSize(TinyScrollButton.size[Theme.derivedStyle[Theme.style]]);
    }
    
    public void paint(final Graphics graphics) {
        this.isRollover = false;
        ColorUIResource color;
        if (!this.scrollbarUI.isThumbVisible()) {
            color = Theme.scrollButtDisabledColor[Theme.style].getColor();
        }
        else if (this.getModel().isPressed()) {
            color = Theme.scrollButtPressedColor[Theme.style].getColor();
        }
        else if (this.getModel().isRollover() && Theme.scrollRollover[Theme.style]) {
            color = Theme.scrollButtRolloverColor[Theme.style].getColor();
            this.isRollover = true;
        }
        else {
            color = Theme.scrollButtColor[Theme.style].getColor();
        }
        graphics.setColor(color);
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyButton(graphics, this.getSize());
                break;
            }
            case 1: {
                this.drawWinButton(graphics, this.getSize());
                break;
            }
            case 2: {
                if (TinyLookAndFeel.controlPanelInstantiated) {
                    this.drawXpButtonNoCache(graphics, this.getSize(), color);
                    break;
                }
                this.drawXpButton(graphics, this.getSize(), color);
                break;
            }
        }
        if (!this.scrollbarUI.isThumbVisible()) {
            graphics.setColor(Theme.scrollArrowDisabledColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.scrollArrowColor[Theme.style].getColor());
        }
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyArrow(graphics, this.getSize());
                break;
            }
            case 1: {
                this.drawWinArrow(graphics, this.getSize());
                break;
            }
            case 2: {
                this.drawXpArrow(graphics, this.getSize());
                break;
            }
        }
    }
    
    private void drawTinyButton(final Graphics graphics, final Dimension dimension) {
    }
    
    private void drawWinButton(final Graphics graphics, final Dimension dimension) {
        graphics.fillRect(1, 1, dimension.width - 2, dimension.height - 2);
        graphics.drawLine(0, 0, dimension.width - 1, 0);
        graphics.drawLine(0, 0, 0, dimension.height - 1);
        if (this.getModel().isPressed() && this.scrollbarUI.isThumbVisible()) {
            graphics.setColor(Theme.scrollBorderColor[Theme.style].getColor());
            graphics.drawRect(0, 0, dimension.width - 1, dimension.height - 1);
        }
        else {
            graphics.setColor(Theme.scrollLightColor[Theme.style].getColor());
            graphics.drawLine(1, 1, dimension.width - 3, 1);
            graphics.drawLine(1, 1, 1, dimension.height - 3);
            graphics.setColor(Theme.scrollDarkColor[Theme.style].getColor());
            graphics.drawLine(dimension.width - 2, 1, dimension.width - 2, dimension.height - 3);
            graphics.drawLine(1, dimension.height - 2, dimension.width - 2, dimension.height - 2);
            graphics.setColor(Theme.scrollBorderColor[Theme.style].getColor());
            graphics.drawLine(dimension.width - 1, 0, dimension.width - 1, dimension.height - 2);
            graphics.drawLine(0, dimension.height - 1, dimension.width - 1, dimension.height - 1);
        }
    }
    
    private void drawXpButton(final Graphics graphics, final Dimension dimension, final Color color) {
        final ScrollButtonKey scrollButtonKey = new ScrollButtonKey(this.direction == 1 || this.direction == 5, color, this.getModel().isPressed(), this.scrollbarUI.isThumbVisible(), this.getModel().isRollover() && Theme.scrollRollover[Theme.style]);
        final Object value = TinyScrollButton.cache.get(scrollButtonKey);
        if (value != null) {
            graphics.drawImage((Image)value, 0, 0, this);
            return;
        }
        final BufferedImage bufferedImage = new BufferedImage(17, 17, 2);
        final Graphics graphics2 = bufferedImage.getGraphics();
        int n = Theme.scrollSpreadLight[Theme.style];
        int n2 = Theme.scrollSpreadDark[Theme.style];
        if (!this.scrollbarUI.isThumbVisible()) {
            n = Theme.scrollSpreadLightDisabled[Theme.style];
            n2 = Theme.scrollSpreadDarkDisabled[Theme.style];
        }
        switch (this.direction) {
            case 1:
            case 5: {
                final int n3 = 17;
                final float n4 = 10.0f * n / 11.0f;
                final float n5 = 10.0f * n2 / 11.0f;
                final int n6 = 6;
                for (int i = 1; i < n3 - 1; ++i) {
                    if (i < n6) {
                        graphics2.setColor(ColorRoutines.lighten(color, (int)((n6 - i) * n4)));
                    }
                    else if (i == n6) {
                        graphics2.setColor(color);
                    }
                    else {
                        graphics2.setColor(ColorRoutines.darken(color, (int)((i - n6) * n5)));
                    }
                    graphics2.drawLine(3, i, 14, i);
                }
                graphics2.setColor(Theme.scrollTrackBorderColor[Theme.style].getColor());
                graphics2.drawLine(0, 0, 0, 16);
                ColorUIResource color2;
                if (!this.scrollbarUI.isThumbVisible()) {
                    color2 = Theme.scrollLightDisabledColor[Theme.style].getColor();
                }
                else {
                    color2 = Theme.scrollLightColor[Theme.style].getColor();
                }
                graphics2.setColor(color2);
                graphics2.drawLine(2, 1, 2, 15);
                graphics2.drawLine(15, 1, 15, 15);
                final Color color3 = new Color(color2.getRed(), color2.getGreen(), color2.getBlue(), 92);
                ColorUIResource color4;
                if (!this.scrollbarUI.isThumbVisible()) {
                    color4 = Theme.scrollBorderDisabledColor[Theme.style].getColor();
                }
                else {
                    color4 = Theme.scrollBorderColor[Theme.style].getColor();
                }
                graphics2.setColor(color4);
                graphics2.drawRect(1, 0, 15, 16);
                graphics2.setColor(new Color(color4.getRed(), color4.getGreen(), color4.getBlue(), 92));
                graphics2.drawLine(2, 1, 2, 1);
                graphics2.drawLine(15, 1, 15, 1);
                graphics2.drawLine(2, 15, 2, 15);
                graphics2.drawLine(15, 15, 15, 15);
                graphics2.setColor(color3);
                graphics2.drawLine(1, 0, 1, 0);
                graphics2.drawLine(1, 16, 1, 16);
                graphics2.drawLine(16, 0, 16, 0);
                graphics2.drawLine(16, 16, 16, 16);
                break;
            }
            case 3:
            case 7: {
                final float n7 = 10.0f * n / 10.0f;
                final float n8 = 10.0f * n2 / 10.0f;
                final int n9 = 6;
                for (int j = 1; j < 15; ++j) {
                    if (j < n9) {
                        graphics2.setColor(ColorRoutines.lighten(color, (int)((n9 - j) * n7)));
                    }
                    else if (j == n9) {
                        graphics2.setColor(color);
                    }
                    else {
                        graphics2.setColor(ColorRoutines.darken(color, (int)((j - n9) * n8)));
                    }
                    graphics2.drawLine(2, j + 1, 14, j + 1);
                }
                graphics2.setColor(Theme.scrollTrackBorderColor[Theme.style].getColor());
                graphics2.drawLine(0, 0, 16, 0);
                ColorUIResource color5;
                if (!this.scrollbarUI.isThumbVisible()) {
                    color5 = Theme.scrollLightDisabledColor[Theme.style].getColor();
                }
                else {
                    color5 = Theme.scrollLightColor[Theme.style].getColor();
                }
                graphics2.setColor(color5);
                graphics2.drawLine(1, 2, 1, 15);
                graphics2.drawLine(15, 2, 15, 15);
                final Color color6 = new Color(color5.getRed(), color5.getGreen(), color5.getBlue(), 92);
                ColorUIResource color7;
                if (!this.scrollbarUI.isThumbVisible()) {
                    color7 = Theme.scrollBorderDisabledColor[Theme.style].getColor();
                }
                else {
                    color7 = Theme.scrollBorderColor[Theme.style].getColor();
                }
                graphics2.setColor(color7);
                graphics2.drawRect(0, 1, 16, 15);
                graphics2.setColor(new Color(color7.getRed(), color7.getGreen(), color7.getBlue(), 92));
                graphics2.drawLine(1, 2, 1, 2);
                graphics2.drawLine(15, 2, 15, 2);
                graphics2.drawLine(1, 15, 1, 15);
                graphics2.drawLine(15, 15, 15, 15);
                graphics2.setColor(color6);
                graphics2.drawLine(0, 1, 0, 1);
                graphics2.drawLine(16, 1, 16, 1);
                graphics2.drawLine(0, 16, 0, 16);
                graphics2.drawLine(16, 16, 16, 16);
                break;
            }
        }
        graphics2.dispose();
        graphics.drawImage(bufferedImage, 0, 0, this);
        TinyScrollButton.cache.put(scrollButtonKey, bufferedImage);
    }
    
    private void drawXpButtonNoCache(final Graphics graphics, final Dimension dimension, final Color color) {
        int n = Theme.scrollSpreadLight[Theme.style];
        int n2 = Theme.scrollSpreadDark[Theme.style];
        if (!this.scrollbarUI.isThumbVisible()) {
            n = Theme.scrollSpreadLightDisabled[Theme.style];
            n2 = Theme.scrollSpreadDarkDisabled[Theme.style];
        }
        switch (this.direction) {
            case 1:
            case 5: {
                final int n3 = 17;
                final float n4 = 10.0f * n / 11.0f;
                final float n5 = 10.0f * n2 / 11.0f;
                final int n6 = 6;
                for (int i = 1; i < n3 - 1; ++i) {
                    if (i < n6) {
                        graphics.setColor(ColorRoutines.lighten(color, (int)((n6 - i) * n4)));
                    }
                    else if (i == n6) {
                        graphics.setColor(color);
                    }
                    else {
                        graphics.setColor(ColorRoutines.darken(color, (int)((i - n6) * n5)));
                    }
                    graphics.drawLine(3, i, 14, i);
                }
                graphics.setColor(Theme.scrollTrackBorderColor[Theme.style].getColor());
                graphics.drawLine(0, 0, 0, 16);
                ColorUIResource color2;
                if (!this.scrollbarUI.isThumbVisible()) {
                    color2 = Theme.scrollLightDisabledColor[Theme.style].getColor();
                }
                else {
                    color2 = Theme.scrollLightColor[Theme.style].getColor();
                }
                graphics.setColor(color2);
                graphics.drawLine(2, 1, 2, 15);
                graphics.drawLine(15, 1, 15, 15);
                final Color color3 = new Color(color2.getRed(), color2.getGreen(), color2.getBlue(), 92);
                ColorUIResource color4;
                if (!this.scrollbarUI.isThumbVisible()) {
                    color4 = Theme.scrollBorderDisabledColor[Theme.style].getColor();
                }
                else {
                    color4 = Theme.scrollBorderColor[Theme.style].getColor();
                }
                graphics.setColor(color4);
                graphics.drawRect(1, 0, 15, 16);
                graphics.setColor(new Color(color4.getRed(), color4.getGreen(), color4.getBlue(), 92));
                graphics.drawLine(2, 1, 2, 1);
                graphics.drawLine(15, 1, 15, 1);
                graphics.drawLine(2, 15, 2, 15);
                graphics.drawLine(15, 15, 15, 15);
                graphics.setColor(color3);
                graphics.drawLine(1, 0, 1, 0);
                graphics.drawLine(1, 16, 1, 16);
                graphics.drawLine(16, 0, 16, 0);
                graphics.drawLine(16, 16, 16, 16);
                break;
            }
            case 3:
            case 7: {
                final float n7 = 10.0f * n / 10.0f;
                final float n8 = 10.0f * n2 / 10.0f;
                final int n9 = 6;
                for (int j = 1; j < 15; ++j) {
                    if (j < n9) {
                        graphics.setColor(ColorRoutines.lighten(color, (int)((n9 - j) * n7)));
                    }
                    else if (j == n9) {
                        graphics.setColor(color);
                    }
                    else {
                        graphics.setColor(ColorRoutines.darken(color, (int)((j - n9) * n8)));
                    }
                    graphics.drawLine(2, j + 1, 14, j + 1);
                }
                graphics.setColor(Theme.scrollTrackBorderColor[Theme.style].getColor());
                graphics.drawLine(0, 0, 16, 0);
                ColorUIResource color5;
                if (!this.scrollbarUI.isThumbVisible()) {
                    color5 = Theme.scrollLightDisabledColor[Theme.style].getColor();
                }
                else {
                    color5 = Theme.scrollLightColor[Theme.style].getColor();
                }
                graphics.setColor(color5);
                graphics.drawLine(1, 2, 1, 15);
                graphics.drawLine(15, 2, 15, 15);
                final Color color6 = new Color(color5.getRed(), color5.getGreen(), color5.getBlue(), 92);
                ColorUIResource color7;
                if (!this.scrollbarUI.isThumbVisible()) {
                    color7 = Theme.scrollBorderDisabledColor[Theme.style].getColor();
                }
                else {
                    color7 = Theme.scrollBorderColor[Theme.style].getColor();
                }
                graphics.setColor(color7);
                graphics.drawRect(0, 1, 16, 15);
                graphics.setColor(new Color(color7.getRed(), color7.getGreen(), color7.getBlue(), 92));
                graphics.drawLine(1, 2, 1, 2);
                graphics.drawLine(15, 2, 15, 2);
                graphics.drawLine(1, 15, 1, 15);
                graphics.drawLine(15, 15, 15, 15);
                graphics.setColor(color6);
                graphics.drawLine(0, 1, 0, 1);
                graphics.drawLine(16, 1, 16, 1);
                graphics.drawLine(0, 16, 0, 16);
                graphics.drawLine(16, 16, 16, 16);
                break;
            }
        }
    }
    
    private void drawTinyArrow(final Graphics graphics, final Dimension dimension) {
    }
    
    private void drawWinArrow(final Graphics graphics, final Dimension dimension) {
        int n = 4;
        if (this.getModel().isPressed() && this.scrollbarUI.isThumbVisible()) {
            n = 5;
        }
        switch (this.direction) {
            case 1: {
                graphics.drawLine(n + 3, n + 2, n + 3, n + 2);
                graphics.drawLine(n + 2, n + 3, n + 4, n + 3);
                graphics.drawLine(n + 1, n + 4, n + 5, n + 4);
                graphics.drawLine(n, n + 5, n + 6, n + 5);
                if (!this.scrollbarUI.isThumbVisible()) {
                    graphics.setColor(Color.WHITE);
                    graphics.drawLine(n + 1, n + 6, n + 7, n + 6);
                    break;
                }
                break;
            }
            case 5: {
                graphics.drawLine(n, n + 2, n + 6, n + 2);
                graphics.drawLine(n + 1, n + 3, n + 5, n + 3);
                graphics.drawLine(n + 2, n + 4, n + 4, n + 4);
                graphics.drawLine(n + 3, n + 5, n + 3, n + 5);
                if (!this.scrollbarUI.isThumbVisible()) {
                    graphics.setColor(Color.WHITE);
                    graphics.drawLine(n + 4, n + 6, n + 4, n + 6);
                    graphics.drawLine(n + 4, n + 5, n + 5, n + 5);
                    graphics.drawLine(n + 5, n + 4, n + 6, n + 4);
                    graphics.drawLine(n + 6, n + 3, n + 7, n + 3);
                    break;
                }
                break;
            }
            case 3: {
                graphics.drawLine(n + 5, n + 3, n + 5, n + 3);
                graphics.drawLine(n + 4, n + 2, n + 4, n + 4);
                graphics.drawLine(n + 3, n + 1, n + 3, n + 5);
                graphics.drawLine(n + 2, n, n + 2, n + 6);
                if (!this.scrollbarUI.isThumbVisible()) {
                    graphics.setColor(Color.WHITE);
                    graphics.drawLine(n + 3, n + 7, n + 3, n + 7);
                    graphics.drawLine(n + 3, n + 6, n + 4, n + 6);
                    graphics.drawLine(n + 4, n + 5, n + 5, n + 5);
                    graphics.drawLine(n + 5, n + 4, n + 6, n + 4);
                    break;
                }
                break;
            }
            case 7: {
                graphics.drawLine(n + 1, n + 3, n + 1, n + 3);
                graphics.drawLine(n + 2, n + 2, n + 2, n + 4);
                graphics.drawLine(n + 3, n + 1, n + 3, n + 5);
                graphics.drawLine(n + 4, n, n + 4, n + 6);
                if (!this.scrollbarUI.isThumbVisible()) {
                    graphics.setColor(Color.WHITE);
                    graphics.drawLine(n + 5, n + 1, n + 5, n + 7);
                    break;
                }
                break;
            }
        }
    }
    
    private void drawXpArrow(final Graphics graphics, final Dimension dimension) {
        switch (this.direction) {
            case 1: {
                graphics.drawLine(8, 5, 8, 5);
                graphics.drawLine(7, 6, 9, 6);
                graphics.drawLine(6, 7, 10, 7);
                graphics.drawLine(5, 8, 7, 8);
                graphics.drawLine(9, 8, 11, 8);
                graphics.drawLine(4, 9, 6, 9);
                graphics.drawLine(10, 9, 12, 9);
                graphics.drawLine(5, 10, 5, 10);
                graphics.drawLine(11, 10, 11, 10);
                break;
            }
            case 5: {
                graphics.drawLine(5, 6, 5, 6);
                graphics.drawLine(11, 6, 11, 6);
                graphics.drawLine(4, 7, 6, 7);
                graphics.drawLine(10, 7, 12, 7);
                graphics.drawLine(5, 8, 7, 8);
                graphics.drawLine(9, 8, 11, 8);
                graphics.drawLine(6, 9, 10, 9);
                graphics.drawLine(7, 10, 9, 10);
                graphics.drawLine(8, 11, 8, 11);
                break;
            }
            case 3: {
                graphics.drawLine(6, 5, 6, 5);
                graphics.drawLine(6, 11, 6, 11);
                graphics.drawLine(7, 4, 7, 6);
                graphics.drawLine(7, 10, 7, 12);
                graphics.drawLine(8, 5, 8, 7);
                graphics.drawLine(8, 9, 8, 11);
                graphics.drawLine(9, 6, 9, 10);
                graphics.drawLine(10, 7, 10, 9);
                graphics.drawLine(11, 8, 11, 8);
                break;
            }
            case 7: {
                graphics.drawLine(4, 8, 4, 8);
                graphics.drawLine(5, 7, 5, 9);
                graphics.drawLine(6, 6, 6, 10);
                graphics.drawLine(7, 5, 7, 7);
                graphics.drawLine(7, 9, 7, 11);
                graphics.drawLine(8, 4, 8, 6);
                graphics.drawLine(8, 10, 8, 12);
                graphics.drawLine(9, 5, 9, 5);
                graphics.drawLine(9, 11, 9, 11);
                break;
            }
        }
    }
    
    public Dimension getPreferredSize() {
        return TinyScrollButton.size[Theme.derivedStyle[Theme.style]];
    }
    
    static {
        TinyScrollButton.cache = new HashMap();
        (TinyScrollButton.size = new Dimension[3])[0] = new Dimension(15, 15);
        TinyScrollButton.size[1] = new Dimension(16, 16);
        TinyScrollButton.size[2] = new Dimension(17, 17);
    }
    
    static class ScrollButtonKey
    {
        private boolean vertical;
        private Color c;
        private boolean pressed;
        private boolean enabled;
        private boolean rollover;
        
        ScrollButtonKey(final boolean vertical, final Color c, final boolean pressed, final boolean enabled, final boolean rollover) {
            this.vertical = vertical;
            this.c = c;
            this.pressed = pressed;
            this.enabled = enabled;
            this.rollover = rollover;
        }
        
        public boolean equals(final Object o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof ScrollButtonKey)) {
                return false;
            }
            final ScrollButtonKey scrollButtonKey = (ScrollButtonKey)o;
            return this.vertical == scrollButtonKey.vertical && this.pressed == scrollButtonKey.pressed && this.enabled == scrollButtonKey.enabled && this.rollover == scrollButtonKey.rollover && this.c.equals(scrollButtonKey.c);
        }
        
        public int hashCode() {
            return this.c.hashCode() * (this.pressed ? 1 : 2) * (this.enabled ? 4 : 8) * (this.rollover ? 16 : 32) * (this.vertical ? 64 : 128);
        }
    }
}
