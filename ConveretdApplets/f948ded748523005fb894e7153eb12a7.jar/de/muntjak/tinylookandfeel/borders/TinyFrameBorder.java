// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel.borders;

import java.awt.Frame;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import de.muntjak.tinylookandfeel.controlpanel.SBChooser;
import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.plaf.ColorUIResource;
import de.muntjak.tinylookandfeel.TinyLookAndFeel;
import de.muntjak.tinylookandfeel.Theme;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Window;
import java.awt.Robot;
import java.awt.Color;
import java.util.HashMap;
import javax.swing.plaf.UIResource;
import javax.swing.border.AbstractBorder;

public class TinyFrameBorder extends AbstractBorder implements UIResource
{
    private static HashMap cache;
    public static Color buttonUpperColor;
    public static Color buttonLowerColor;
    private static TinyFrameBorder onlyInstance;
    private static Robot robot;
    private static boolean robotsSupported;
    private Window window;
    private int titleHeight;
    private boolean isActive;
    
    public static TinyFrameBorder getInstance() {
        if (TinyFrameBorder.onlyInstance == null) {
            TinyFrameBorder.onlyInstance = new TinyFrameBorder();
            if (TinyFrameBorder.robot == null && TinyFrameBorder.robotsSupported) {
                try {
                    TinyFrameBorder.robot = new Robot();
                }
                catch (Exception ex) {
                    TinyFrameBorder.robotsSupported = false;
                }
            }
        }
        return TinyFrameBorder.onlyInstance;
    }
    
    public void paintBorder(final Component component, final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        this.window = SwingUtilities.getWindowAncestor(component);
        this.isActive = this.window.isActive();
        if (this.window instanceof JFrame) {
            this.titleHeight = Theme.frameTitleHeight[Theme.derivedStyle[Theme.style]];
        }
        else {
            this.titleHeight = Theme.frameInternalTitleHeight[Theme.derivedStyle[Theme.style]];
        }
        if (this.isActive) {
            graphics.setColor(Theme.frameBorderColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.frameBorderDisabledColor[Theme.style].getColor());
        }
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyBorder(graphics, n, n2, n3, n4);
                break;
            }
            case 1: {
                this.drawWinBorder(graphics, n, n2, n3, n4);
                break;
            }
            case 2: {
                this.drawXpBorder(graphics, n, n2, n3, n4);
                break;
            }
        }
        ColorUIResource color;
        if (this.isActive) {
            color = Theme.frameCaptionColor[Theme.style].getColor();
        }
        else {
            color = Theme.frameCaptionDisabledColor[Theme.style].getColor();
        }
        graphics.setColor(color);
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyCaption(graphics, n, n2, n3, n4, color);
                break;
            }
            case 1: {
                this.drawWinCaption(graphics, n, n2, n3, n4, color);
                break;
            }
            case 2: {
                if (TinyLookAndFeel.controlPanelInstantiated) {
                    this.drawXpCaptionNoCache(graphics, n, n2, n3, n4, color);
                    break;
                }
                this.drawXpCaption(graphics, n, n2, n3, n4, color);
                break;
            }
        }
    }
    
    private void drawTinyBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
    }
    
    private void drawWinBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.drawLine(n, n2, n + n3 - 2, n2);
        graphics.drawLine(n, n2 + 1, n, n2 + n4 - 2);
        graphics.drawRect(n + 2, n2 + 2, n3 - 5, n4 - 5);
        graphics.drawRect(n + 3, n2 + 3, n3 - 7, n4 - 7);
        graphics.setColor(Color.WHITE);
        graphics.drawLine(n + 1, n2 + 1, n + n3 - 3, n2 + 1);
        graphics.drawLine(n + 1, n2 + 2, n + 1, n2 + n4 - 3);
        graphics.setColor(Theme.frameDarkColor[Theme.style].getColor());
        graphics.drawLine(n, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 1, n2, n + n3 - 1, n2 + n4 - 1);
        graphics.setColor(Theme.frameLightColor[Theme.style].getColor());
        graphics.drawLine(n + 1, n2 + n4 - 2, n + n3 - 2, n2 + n4 - 2);
        graphics.drawLine(n3 - 2, n2 + 1, n + n3 - 2, n2 + n4 - 2);
    }
    
    private void drawXpBorder(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.drawLine(n, n2 + 6, n, n2 + n4 - 1);
        graphics.drawLine(n + 2, n2 + this.titleHeight, n + 2, n2 + n4 - 3);
        graphics.drawLine(n + n3 - 1, n2 + 6, n + n3 - 1, n2 + n4 - 1);
        graphics.drawLine(n + n3 - 3, n2 + this.titleHeight, n + n3 - 3, n2 + n4 - 3);
        graphics.drawLine(n + 2, n2 + n4 - 3, n + n3 - 3, n2 + n4 - 3);
        graphics.drawLine(n, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
        if (TinyFrameBorder.robot != null) {
            final int n5 = this.window.getLocationOnScreen().x - 4;
            final int y = this.window.getLocationOnScreen().y;
            graphics.drawImage(TinyFrameBorder.robot.createScreenCapture(new Rectangle(n5, y, 4, 4)), n, n2, null);
            graphics.drawImage(TinyFrameBorder.robot.createScreenCapture(new Rectangle(this.window.getLocationOnScreen().x + this.window.getWidth() + 1, y, 4, 4)), n + n3 - 4, n2, null);
        }
        else {
            graphics.setColor(Theme.backColor[Theme.style].getColor());
            graphics.fillRect(0, 0, n3, 3);
        }
        if (this.isActive) {
            graphics.setColor(Theme.frameCaptionColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.frameCaptionDisabledColor[Theme.style].getColor());
        }
        graphics.drawLine(n + 1, n2 + this.titleHeight, n + 1, n2 + n4 - 2);
        graphics.drawLine(n + n3 - 2, n2 + this.titleHeight, n + n3 - 2, n2 + n4 - 2);
        graphics.drawLine(n + 1, n2 + n4 - 2, n + n3 - 2, n2 + n4 - 2);
        ColorUIResource colorUIResource;
        if (this.isActive) {
            colorUIResource = Theme.frameBorderColor[Theme.style].getColor();
        }
        else {
            colorUIResource = Theme.frameBorderDisabledColor[Theme.style].getColor();
        }
        graphics.setColor(ColorRoutines.getAlphaColor(colorUIResource, 82));
        graphics.drawLine(n, n2 + 3, n, n2 + 3);
        graphics.drawLine(n + n3 - 1, n2 + 3, n + n3 - 1, n2 + 3);
        graphics.setColor(ColorRoutines.getAlphaColor(colorUIResource, 156));
        graphics.drawLine(n, n2 + 4, n, n2 + 4);
        graphics.drawLine(n + n3 - 1, n2 + 4, n + n3 - 1, n2 + 4);
        graphics.setColor(ColorRoutines.getAlphaColor(colorUIResource, 215));
        graphics.drawLine(n, n2 + 5, n, n2 + 5);
        graphics.drawLine(n + n3 - 1, n2 + 5, n + n3 - 1, n2 + 5);
    }
    
    private void drawTinyCaption(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color) {
    }
    
    private void drawWinCaption(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color) {
        final int n5 = n2 + this.titleHeight - 2;
        final int n6 = n3 - 8;
        int n7 = 56;
        int n8 = 78;
        if (!this.isActive) {
            n7 = 0;
            n8 = 50;
        }
        int n9 = 0;
        int n10 = 0;
        Color adjustedColor = color;
        for (int i = 0; i < n6; ++i) {
            graphics.setColor(adjustedColor);
            graphics.drawLine(n + 4 + i, n2 + 4, n + 4 + i, n5);
            final int n11 = n7 * i / n6;
            final int n12 = n8 * i / n6;
            if (n11 != n9 || n12 != n10) {
                n9 = n11;
                n10 = n12;
                adjustedColor = SBChooser.getAdjustedColor(color, n11, n12);
            }
        }
        graphics.setColor(Theme.frameBorderColor[Theme.style].getColor());
        graphics.drawLine(n + 4, n2 + this.titleHeight - 1, n + n3 - 5, n2 + this.titleHeight - 1);
    }
    
    private void drawXpCaption(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color) {
        if (this.titleHeight == Theme.frameInternalTitleHeight[Theme.derivedStyle[Theme.style]]) {
            this.drawXpInternalCaption(graphics, n, n2, n3, n4, color);
            return;
        }
        int n5 = Theme.frameSpreadDarkDisabled[Theme.style];
        int n6 = Theme.frameSpreadLightDisabled[Theme.style];
        int n7 = n2;
        ColorUIResource color2;
        if (this.isActive) {
            color2 = Theme.frameBorderColor[Theme.style].getColor();
            n5 = Theme.frameSpreadDark[Theme.style];
            n6 = Theme.frameSpreadLight[Theme.style];
        }
        else {
            color2 = Theme.frameBorderDisabledColor[Theme.style].getColor();
        }
        graphics.setColor(ColorRoutines.getAlphaColor(color2, 82));
        graphics.drawLine(n + 3, n7, n + 3, n7);
        graphics.drawLine(n + n3 - 4, n7, n + n3 - 4, n7);
        graphics.setColor(ColorRoutines.getAlphaColor(color2, 156));
        graphics.drawLine(n + 4, n7, n + 4, n7);
        graphics.drawLine(n + n3 - 5, n7, n + n3 - 5, n7);
        graphics.setColor(ColorRoutines.getAlphaColor(color2, 215));
        graphics.drawLine(n + 5, n7, n + 5, n7);
        graphics.drawLine(n + n3 - 6, n7, n + n3 - 6, n7);
        ++n7;
        final Color darken = ColorRoutines.darken(color, 4 * n5);
        graphics.setColor(darken);
        graphics.drawLine(n + 3, n7, n + 5, n7);
        graphics.drawLine(n + n3 - 6, n7, n + n3 - 4, n7);
        graphics.setColor(ColorRoutines.getAlphaColor(darken, 139));
        graphics.drawLine(n + 2, n7, n + 2, n7);
        graphics.drawLine(n + n3 - 3, n7, n + n3 - 3, n7);
        graphics.setColor(ColorRoutines.getAlphaColor(darken, 23));
        graphics.drawLine(n + 1, n7, n + 1, n7);
        graphics.drawLine(n + n3 - 2, n7, n + n3 - 2, n7);
        ++n7;
        graphics.setColor(ColorRoutines.lighten(color, 10 * n6));
        graphics.drawLine(n + 4, n7, n + 5, n7);
        graphics.drawLine(n + n3 - 6, n7, n + n3 - 5, n7);
        graphics.setColor(color);
        graphics.drawLine(n + 3, n7, n + 3, n7);
        graphics.drawLine(n + n3 - 4, n7, n + n3 - 4, n7);
        final Color darken2 = ColorRoutines.darken(color, 6 * n5);
        graphics.setColor(darken2);
        graphics.drawLine(n + 2, n7, n + 2, n7);
        graphics.drawLine(n + n3 - 3, n7, n + n3 - 3, n7);
        graphics.setColor(ColorRoutines.getAlphaColor(darken2, 139));
        graphics.drawLine(n + 1, n7, n + 1, n7);
        graphics.drawLine(n + n3 - 2, n7, n + n3 - 2, n7);
        ++n7;
        graphics.setColor(color);
        graphics.drawLine(n + 2, n7, n + 2, n7);
        graphics.drawLine(n + n3 - 3, n7, n + n3 - 3, n7);
        graphics.setColor(ColorRoutines.darken(color, 6 * n5));
        graphics.drawLine(n + 1, n7, n + 1, n7);
        graphics.drawLine(n + n3 - 2, n7, n + n3 - 2, n7);
        graphics.setColor(ColorRoutines.lighten(color, 10 * n6));
        graphics.drawLine(n + 3, n7, n + 3, n7);
        graphics.drawLine(n + n3 - 4, n7, n + n3 - 4, n7);
        graphics.setColor(ColorRoutines.lighten(color, 7 * n6));
        graphics.drawLine(n + 4, n7, n + 4, n7);
        graphics.drawLine(n + n3 - 5, n7, n + n3 - 5, n7);
        graphics.setColor(ColorRoutines.lighten(color, 3 * n6));
        graphics.drawLine(n + 5, n7, n + 5, n7);
        graphics.drawLine(n + n3 - 6, n7, n + n3 - 6, n7);
        graphics.setColor(color);
        graphics.drawLine(n + 6, n7, n + 6, n7);
        graphics.drawLine(n + n3 - 7, n7, n + n3 - 7, n7);
        ++n7;
        graphics.setColor(ColorRoutines.darken(color, 2 * n5));
        graphics.drawLine(n + 5, n7, n + 6, n7);
        graphics.drawLine(n + n + n3 - 7, n7, n + n3 - 6, n7);
        graphics.setColor(ColorRoutines.darken(color, 6 * n5));
        graphics.drawLine(n + 1, n7, n + 1, n7);
        graphics.drawLine(n + n3 - 2, n7, n + n3 - 2, n7);
        graphics.setColor(ColorRoutines.lighten(color, 10 * n6));
        graphics.drawLine(n + 2, n7, n + 2, n7);
        graphics.drawLine(n + n3 - 3, n7, n + n3 - 3, n7);
        graphics.setColor(ColorRoutines.lighten(color, 5 * n6));
        graphics.drawLine(n + 3, n7, n + 3, n7);
        graphics.drawLine(n + n3 - 4, n7, n + n3 - 4, n7);
        graphics.setColor(color);
        graphics.drawLine(n + 4, n7, n + 4, n7);
        graphics.drawLine(n + n3 - 5, n7, n + n3 - 5, n7);
        ++n7;
        graphics.setColor(ColorRoutines.darken(color, 4 * n5));
        graphics.drawLine(n + 1, n7, n + 1, n7);
        graphics.drawLine(n + n3 - 2, n7, n + n3 - 2, n7);
        final CaptionKey captionKey = new CaptionKey(this.isActive, this.titleHeight);
        final Object value = TinyFrameBorder.cache.get(captionKey);
        if (value != null) {
            graphics.drawImage((Image)value, n + 6, n2, n + n3 - 6, n2 + 5, 0, 0, 1, 5, this.window);
            graphics.drawImage((Image)value, n + 1, n2 + 5, n + n3 - 1, n2 + this.titleHeight, 0, 5, 1, this.titleHeight, this.window);
            TinyFrameBorder.buttonUpperColor = ColorRoutines.darken(color, 4 * n5);
            TinyFrameBorder.buttonLowerColor = ColorRoutines.lighten(color, 10 * n6);
            return;
        }
        final BufferedImage bufferedImage = new BufferedImage(1, this.titleHeight, 2);
        final Graphics graphics2 = bufferedImage.getGraphics();
        graphics2.setColor(color2);
        graphics2.drawLine(0, 0, 1, 0);
        graphics2.setColor(ColorRoutines.darken(color, 4 * n5));
        graphics2.drawLine(0, 1, 1, 1);
        graphics2.setColor(ColorRoutines.lighten(color, 10 * n6));
        graphics2.drawLine(0, 2, 1, 2);
        graphics2.setColor(color);
        graphics2.drawLine(0, 3, 1, 3);
        graphics2.setColor(ColorRoutines.darken(color, 2 * n5));
        graphics2.drawLine(0, 4, 1, 4);
        graphics2.setColor(TinyFrameBorder.buttonUpperColor = ColorRoutines.darken(color, 4 * n5));
        graphics2.drawLine(0, 5, 1, 5);
        graphics2.setColor(ColorRoutines.darken(color, 4 * n5));
        graphics2.drawLine(0, 6, 1, 6);
        graphics2.drawLine(0, 7, 1, 7);
        graphics2.setColor(ColorRoutines.darken(color, 3 * n5));
        graphics2.drawLine(0, 8, 1, 8);
        graphics2.drawLine(0, 9, 1, 9);
        graphics2.drawLine(0, 10, 1, 10);
        graphics2.drawLine(0, 11, 1, 11);
        graphics2.setColor(ColorRoutines.darken(color, 2 * n5));
        graphics2.drawLine(0, 12, 1, 12);
        graphics2.drawLine(0, 13, 1, 13);
        graphics2.drawLine(0, 14, 1, 14);
        graphics2.setColor(ColorRoutines.darken(color, n5));
        graphics2.drawLine(0, 15, 1, 15);
        graphics2.drawLine(0, 16, 1, 16);
        graphics2.setColor(color);
        graphics2.drawLine(0, 17, 1, 17);
        graphics2.drawLine(0, 18, 1, 18);
        graphics2.setColor(ColorRoutines.lighten(color, 2 * n6));
        graphics2.drawLine(0, 19, 1, 19);
        graphics2.setColor(ColorRoutines.lighten(color, 4 * n6));
        graphics2.drawLine(0, 20, 1, 20);
        graphics2.setColor(ColorRoutines.lighten(color, 5 * n6));
        graphics2.drawLine(0, 21, 1, 21);
        graphics2.setColor(ColorRoutines.lighten(color, 6 * n6));
        graphics2.drawLine(0, 22, 1, 22);
        graphics2.setColor(ColorRoutines.lighten(color, 8 * n6));
        graphics2.drawLine(0, 23, 1, 23);
        graphics2.setColor(ColorRoutines.lighten(color, 9 * n6));
        graphics2.drawLine(0, 24, 1, 24);
        graphics2.setColor(ColorRoutines.lighten(color, 10 * n6));
        graphics2.drawLine(0, 25, 1, 25);
        graphics2.setColor(ColorRoutines.lighten(color, 4 * n6));
        graphics2.drawLine(0, 26, 1, 26);
        graphics2.setColor(ColorRoutines.darken(color, 2 * n5));
        graphics2.drawLine(0, 27, 1, 27);
        if (this.isActive) {
            graphics2.setColor(Theme.frameLightColor[Theme.style].getColor());
        }
        else {
            graphics2.setColor(Theme.frameLightDisabledColor[Theme.style].getColor());
        }
        graphics2.drawLine(0, 28, 1, 28);
        graphics2.dispose();
        graphics.drawImage(bufferedImage, n + 6, n2, n + n3 - 6, n2 + 5, 0, 0, 1, 5, this.window);
        graphics.drawImage(bufferedImage, n + 1, n2 + 5, n + n3 - 1, n2 + this.titleHeight, 0, 5, 1, this.titleHeight, this.window);
        TinyFrameBorder.cache.put(captionKey, bufferedImage);
    }
    
    private void drawXpCaptionNoCache(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color) {
        if (this.titleHeight == Theme.frameInternalTitleHeight[Theme.derivedStyle[Theme.style]]) {
            this.drawXpInternalCaptionNoCache(graphics, n, n2, n3, n4, color);
            return;
        }
        int n5 = n2;
        int n6 = Theme.frameSpreadDarkDisabled[Theme.style];
        int n7 = Theme.frameSpreadLightDisabled[Theme.style];
        ColorUIResource color2;
        if (this.isActive) {
            color2 = Theme.frameBorderColor[Theme.style].getColor();
            n6 = Theme.frameSpreadDark[Theme.style];
            n7 = Theme.frameSpreadLight[Theme.style];
        }
        else {
            color2 = Theme.frameBorderDisabledColor[Theme.style].getColor();
        }
        graphics.setColor(color2);
        graphics.drawLine(n + 6, n5, n + n3 - 7, n5);
        graphics.setColor(ColorRoutines.getAlphaColor(color2, 82));
        graphics.drawLine(n + 3, n5, n + 3, n5);
        graphics.drawLine(n + n3 - 4, n5, n + n3 - 4, n5);
        graphics.setColor(ColorRoutines.getAlphaColor(color2, 156));
        graphics.drawLine(n + 4, n5, n + 4, n5);
        graphics.drawLine(n + n3 - 5, n5, n + n3 - 5, n5);
        graphics.setColor(ColorRoutines.getAlphaColor(color2, 215));
        graphics.drawLine(n + 5, n5, n + 5, n5);
        graphics.drawLine(n + n3 - 6, n5, n + n3 - 6, n5);
        ++n5;
        final Color darken = ColorRoutines.darken(color, 4 * n6);
        graphics.setColor(darken);
        graphics.drawLine(n + 3, n5, n + n3 - 4, n5);
        graphics.setColor(ColorRoutines.getAlphaColor(darken, 139));
        graphics.drawLine(n + 2, n5, n + 2, n5);
        graphics.drawLine(n + n3 - 3, n5, n + n3 - 3, n5);
        graphics.setColor(ColorRoutines.getAlphaColor(darken, 23));
        graphics.drawLine(n + 1, n5, n + 1, n5);
        graphics.drawLine(n + n3 - 2, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 10 * n7));
        graphics.drawLine(n + 4, n5, n + n3 - 5, n5);
        graphics.setColor(color);
        graphics.drawLine(n + 3, n5, n + 3, n5);
        graphics.drawLine(n + n3 - 4, n5, n + n3 - 4, n5);
        final Color darken2 = ColorRoutines.darken(color, 6 * n6);
        graphics.setColor(darken2);
        graphics.drawLine(n + 2, n5, n + 2, n5);
        graphics.drawLine(n + n3 - 3, n5, n + n3 - 3, n5);
        graphics.setColor(ColorRoutines.getAlphaColor(darken2, 139));
        graphics.drawLine(n + 1, n5, n + 1, n5);
        graphics.drawLine(n + n3 - 2, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(color);
        graphics.drawLine(n + 7, n5, n + n3 - 8, n5);
        graphics.setColor(color);
        graphics.drawLine(n + 2, n5, n + 2, n5);
        graphics.drawLine(n + n3 - 3, n5, n + n3 - 3, n5);
        graphics.setColor(ColorRoutines.darken(color, 6 * n6));
        graphics.drawLine(n + 1, n5, n + 1, n5);
        graphics.drawLine(n + n3 - 2, n5, n + n3 - 2, n5);
        graphics.setColor(ColorRoutines.lighten(color, 10 * n7));
        graphics.drawLine(n + 3, n5, n + 3, n5);
        graphics.drawLine(n + n3 - 4, n5, n + n3 - 4, n5);
        graphics.setColor(ColorRoutines.lighten(color, 7 * n7));
        graphics.drawLine(n + 4, n5, n + 4, n5);
        graphics.drawLine(n + n3 - 5, n5, n + n3 - 5, n5);
        graphics.setColor(ColorRoutines.lighten(color, 3 * n7));
        graphics.drawLine(n + 5, n5, n + 5, n5);
        graphics.drawLine(n + n3 - 6, n5, n + n3 - 6, n5);
        graphics.setColor(color);
        graphics.drawLine(n + 6, n5, n + 6, n5);
        graphics.drawLine(n + n3 - 7, n5, n + n3 - 7, n5);
        ++n5;
        graphics.setColor(ColorRoutines.darken(color, 2 * n6));
        graphics.drawLine(n + 5, n5, n + n3 - 6, n5);
        graphics.setColor(ColorRoutines.darken(color, 6 * n6));
        graphics.drawLine(n + 1, n5, n + 1, n5);
        graphics.drawLine(n + n3 - 2, n5, n + n3 - 2, n5);
        graphics.setColor(ColorRoutines.lighten(color, 10 * n7));
        graphics.drawLine(n + 2, n5, n + 2, n5);
        graphics.drawLine(n + n3 - 3, n5, n + n3 - 3, n5);
        graphics.setColor(ColorRoutines.lighten(color, 5 * n7));
        graphics.drawLine(n + 3, n5, n + 3, n5);
        graphics.drawLine(n + n3 - 4, n5, n + n3 - 4, n5);
        graphics.setColor(color);
        graphics.drawLine(n + 4, n5, n + 4, n5);
        graphics.drawLine(n + n3 - 5, n5, n + n3 - 5, n5);
        ++n5;
        graphics.setColor(TinyFrameBorder.buttonUpperColor = ColorRoutines.darken(color, 4 * n6));
        graphics.drawLine(n + 2, n5, n + n3 - 3, n5);
        graphics.setColor(ColorRoutines.darken(color, 4 * n6));
        graphics.drawLine(n + 1, n5, n + 1, n5);
        graphics.drawLine(n + n3 - 2, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.darken(color, 4 * n6));
        graphics.fillRect(n + 1, n5, n3 - 2, 2);
        n5 += 2;
        graphics.setColor(ColorRoutines.darken(color, 3 * n6));
        graphics.fillRect(n + 1, n5, n3 - 2, 4);
        n5 += 4;
        graphics.setColor(ColorRoutines.darken(color, 2 * n6));
        graphics.fillRect(n + 1, n5, n3 - 2, 3);
        n5 += 3;
        graphics.setColor(ColorRoutines.darken(color, 1 * n6));
        graphics.fillRect(n + 1, n5, n3 - 2, 2);
        n5 += 2;
        graphics.setColor(color);
        graphics.fillRect(n + 1, n5, n3 - 2, 2);
        n5 += 2;
        graphics.setColor(ColorRoutines.lighten(color, 2 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 4 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 5 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 6 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 8 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 9 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(TinyFrameBorder.buttonLowerColor = ColorRoutines.lighten(color, 10 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 4 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.darken(color, 2 * n6));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        if (this.isActive) {
            graphics.setColor(Theme.frameLightColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.frameLightDisabledColor[Theme.style].getColor());
        }
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
    }
    
    private void drawXpInternalCaption(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color) {
        int n5 = Theme.frameSpreadDarkDisabled[Theme.style];
        int n6 = Theme.frameSpreadLightDisabled[Theme.style];
        int n7 = n2;
        ColorUIResource color2;
        if (this.isActive) {
            color2 = Theme.frameBorderColor[Theme.style].getColor();
            n5 = Theme.frameSpreadDark[Theme.style];
            n6 = Theme.frameSpreadLight[Theme.style];
        }
        else {
            color2 = Theme.frameBorderDisabledColor[Theme.style].getColor();
        }
        graphics.setColor(ColorRoutines.getAlphaColor(color2, 82));
        graphics.drawLine(n + 3, n7, n + 3, n7);
        graphics.drawLine(n + n3 - 4, n7, n + n3 - 4, n7);
        graphics.setColor(ColorRoutines.getAlphaColor(color2, 156));
        graphics.drawLine(n + 4, n7, n + 4, n7);
        graphics.drawLine(n + n3 - 5, n7, n + n3 - 5, n7);
        graphics.setColor(ColorRoutines.getAlphaColor(color2, 215));
        graphics.drawLine(n + 5, n7, n + 5, n7);
        graphics.drawLine(n + n3 - 6, n7, n + n3 - 6, n7);
        ++n7;
        final Color darken = ColorRoutines.darken(color, 4 * n5);
        graphics.setColor(darken);
        graphics.drawLine(n + 3, n7, n + 5, n7);
        graphics.drawLine(n + n3 - 6, n7, n + n3 - 4, n7);
        graphics.setColor(ColorRoutines.getAlphaColor(darken, 139));
        graphics.drawLine(n + 2, n7, n + 2, n7);
        graphics.drawLine(n + n3 - 3, n7, n + n3 - 3, n7);
        graphics.setColor(ColorRoutines.getAlphaColor(darken, 23));
        graphics.drawLine(n + 1, n7, n + 1, n7);
        graphics.drawLine(n + n3 - 2, n7, n + n3 - 2, n7);
        ++n7;
        graphics.setColor(ColorRoutines.lighten(color, 10 * n6));
        graphics.drawLine(n + 4, n7, n + 5, n7);
        graphics.drawLine(n + n3 - 6, n7, n + n3 - 5, n7);
        graphics.setColor(color);
        graphics.drawLine(n + 3, n7, n + 3, n7);
        graphics.drawLine(n + n3 - 4, n7, n + n3 - 4, n7);
        final Color darken2 = ColorRoutines.darken(color, 6 * n5);
        graphics.setColor(darken2);
        graphics.drawLine(n + 2, n7, n + 2, n7);
        graphics.drawLine(n + n3 - 3, n7, n + n3 - 3, n7);
        graphics.setColor(ColorRoutines.getAlphaColor(darken2, 139));
        graphics.drawLine(n + 1, n7, n + 1, n7);
        graphics.drawLine(n + n3 - 2, n7, n + n3 - 2, n7);
        ++n7;
        graphics.setColor(color);
        graphics.drawLine(n + 2, n7, n + 2, n7);
        graphics.drawLine(n + n3 - 3, n7, n + n3 - 3, n7);
        graphics.setColor(ColorRoutines.darken(color, 6 * n5));
        graphics.drawLine(n + 1, n7, n + 1, n7);
        graphics.drawLine(n + n3 - 2, n7, n + n3 - 2, n7);
        graphics.setColor(ColorRoutines.lighten(color, 10 * n6));
        graphics.drawLine(n + 3, n7, n + 3, n7);
        graphics.drawLine(n + n3 - 4, n7, n + n3 - 4, n7);
        graphics.setColor(ColorRoutines.lighten(color, 7 * n6));
        graphics.drawLine(n + 4, n7, n + 4, n7);
        graphics.drawLine(n + n3 - 5, n7, n + n3 - 5, n7);
        graphics.setColor(ColorRoutines.lighten(color, 3 * n6));
        graphics.drawLine(n + 5, n7, n + 5, n7);
        graphics.drawLine(n + n3 - 6, n7, n + n3 - 6, n7);
        graphics.setColor(color);
        graphics.drawLine(n + 6, n7, n + 6, n7);
        graphics.drawLine(n + n3 - 7, n7, n + n3 - 7, n7);
        ++n7;
        graphics.setColor(ColorRoutines.darken(color, 2 * n5));
        graphics.drawLine(n + 5, n7, n + 6, n7);
        graphics.drawLine(n + n + n3 - 7, n7, n + n3 - 6, n7);
        graphics.setColor(ColorRoutines.darken(color, 6 * n5));
        graphics.drawLine(n + 1, n7, n + 1, n7);
        graphics.drawLine(n + n3 - 2, n7, n + n3 - 2, n7);
        graphics.setColor(ColorRoutines.lighten(color, 10 * n6));
        graphics.drawLine(n + 2, n7, n + 2, n7);
        graphics.drawLine(n + n3 - 3, n7, n + n3 - 3, n7);
        graphics.setColor(ColorRoutines.lighten(color, 5 * n6));
        graphics.drawLine(n + 3, n7, n + 3, n7);
        graphics.drawLine(n + n3 - 4, n7, n + n3 - 4, n7);
        graphics.setColor(color);
        graphics.drawLine(n + 4, n7, n + 4, n7);
        graphics.drawLine(n + n3 - 5, n7, n + n3 - 5, n7);
        ++n7;
        graphics.setColor(ColorRoutines.darken(color, 4 * n5));
        graphics.drawLine(n + 1, n7, n + 1, n7);
        graphics.drawLine(n + n3 - 2, n7, n + n3 - 2, n7);
        final CaptionKey captionKey = new CaptionKey(this.isActive, this.titleHeight);
        final Object value = TinyFrameBorder.cache.get(captionKey);
        if (value != null) {
            graphics.drawImage((Image)value, n + 6, n2, n + n3 - 6, n2 + 5, 0, 0, 1, 5, this.window);
            graphics.drawImage((Image)value, n + 1, n2 + 5, n + n3 - 1, n2 + this.titleHeight, 0, 5, 1, this.titleHeight, this.window);
            TinyFrameBorder.buttonUpperColor = ColorRoutines.darken(color, 4 * n5);
            TinyFrameBorder.buttonLowerColor = ColorRoutines.lighten(color, 10 * n6);
            return;
        }
        final BufferedImage bufferedImage = new BufferedImage(1, this.titleHeight, 2);
        final Graphics graphics2 = bufferedImage.getGraphics();
        graphics2.setColor(color2);
        graphics2.drawLine(0, 0, 1, 0);
        graphics2.setColor(ColorRoutines.darken(color, 4 * n5));
        graphics2.drawLine(0, 1, 1, 1);
        graphics2.setColor(ColorRoutines.lighten(color, 10 * n6));
        graphics2.drawLine(0, 2, 1, 2);
        graphics2.setColor(color);
        graphics2.drawLine(0, 3, 1, 3);
        graphics2.setColor(ColorRoutines.darken(color, 2 * n5));
        graphics2.drawLine(0, 4, 1, 4);
        graphics2.setColor(ColorRoutines.darken(color, 4 * n5));
        graphics2.drawLine(0, 5, 1, 5);
        graphics2.setColor(ColorRoutines.darken(color, 4 * n5));
        graphics2.drawLine(0, 6, 1, 6);
        graphics2.setColor(ColorRoutines.darken(color, 3 * n5));
        graphics2.drawLine(0, 7, 1, 7);
        graphics2.drawLine(0, 8, 1, 8);
        graphics2.drawLine(0, 9, 1, 9);
        graphics2.setColor(ColorRoutines.darken(color, 2 * n5));
        graphics2.drawLine(0, 10, 1, 10);
        graphics2.drawLine(0, 11, 1, 11);
        graphics2.setColor(ColorRoutines.darken(color, n5));
        graphics2.drawLine(0, 12, 1, 12);
        graphics2.setColor(color);
        graphics2.drawLine(0, 13, 1, 13);
        graphics2.drawLine(0, 14, 1, 14);
        graphics2.setColor(ColorRoutines.lighten(color, 2 * n6));
        graphics2.drawLine(0, 15, 1, 15);
        graphics2.setColor(ColorRoutines.lighten(color, 4 * n6));
        graphics2.drawLine(0, 16, 1, 16);
        graphics2.setColor(ColorRoutines.lighten(color, 5 * n6));
        graphics2.drawLine(0, 17, 1, 17);
        graphics2.setColor(ColorRoutines.lighten(color, 6 * n6));
        graphics2.drawLine(0, 18, 1, 18);
        graphics2.setColor(ColorRoutines.lighten(color, 8 * n6));
        graphics2.drawLine(0, 19, 1, 19);
        graphics2.setColor(ColorRoutines.lighten(color, 9 * n6));
        graphics2.drawLine(0, 20, 1, 20);
        graphics2.setColor(ColorRoutines.lighten(color, 10 * n6));
        graphics2.drawLine(0, 21, 1, 21);
        graphics2.setColor(ColorRoutines.lighten(color, 4 * n6));
        graphics2.drawLine(0, 22, 1, 22);
        graphics2.setColor(ColorRoutines.darken(color, 2 * n5));
        graphics2.drawLine(0, 23, 1, 23);
        if (this.isActive) {
            graphics2.setColor(Theme.frameLightColor[Theme.style].getColor());
        }
        else {
            graphics2.setColor(Theme.frameLightDisabledColor[Theme.style].getColor());
        }
        graphics2.drawLine(0, 24, 1, 24);
        graphics2.dispose();
        graphics.drawImage(bufferedImage, n + 6, n2, n + n3 - 6, n2 + 5, 0, 0, 1, 5, this.window);
        graphics.drawImage(bufferedImage, n + 1, n2 + 5, n + n3 - 1, n2 + this.titleHeight, 0, 5, 1, this.titleHeight, this.window);
        TinyFrameBorder.cache.put(captionKey, bufferedImage);
    }
    
    private void drawXpInternalCaptionNoCache(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color) {
        int n5 = n2;
        int n6 = Theme.frameSpreadDarkDisabled[Theme.style];
        int n7 = Theme.frameSpreadLightDisabled[Theme.style];
        ColorUIResource color2;
        if (this.isActive) {
            color2 = Theme.frameBorderColor[Theme.style].getColor();
            n6 = Theme.frameSpreadDark[Theme.style];
            n7 = Theme.frameSpreadLight[Theme.style];
        }
        else {
            color2 = Theme.frameBorderDisabledColor[Theme.style].getColor();
        }
        graphics.setColor(color2);
        graphics.drawLine(n + 6, n5, n + n3 - 7, n5);
        graphics.setColor(ColorRoutines.getAlphaColor(color2, 82));
        graphics.drawLine(n + 3, n5, n + 3, n5);
        graphics.drawLine(n + n3 - 4, n5, n + n3 - 4, n5);
        graphics.setColor(ColorRoutines.getAlphaColor(color2, 156));
        graphics.drawLine(n + 4, n5, n + 4, n5);
        graphics.drawLine(n + n3 - 5, n5, n + n3 - 5, n5);
        graphics.setColor(ColorRoutines.getAlphaColor(color2, 215));
        graphics.drawLine(n + 5, n5, n + 5, n5);
        graphics.drawLine(n + n3 - 6, n5, n + n3 - 6, n5);
        ++n5;
        final Color darken = ColorRoutines.darken(color, 4 * n6);
        graphics.setColor(darken);
        graphics.drawLine(n + 3, n5, n + n3 - 4, n5);
        graphics.setColor(ColorRoutines.getAlphaColor(darken, 139));
        graphics.drawLine(n + 2, n5, n + 2, n5);
        graphics.drawLine(n + n3 - 3, n5, n + n3 - 3, n5);
        graphics.setColor(ColorRoutines.getAlphaColor(darken, 23));
        graphics.drawLine(n + 1, n5, n + 1, n5);
        graphics.drawLine(n + n3 - 2, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 10 * n7));
        graphics.drawLine(n + 4, n5, n + n3 - 5, n5);
        graphics.setColor(color);
        graphics.drawLine(n + 3, n5, n + 3, n5);
        graphics.drawLine(n + n3 - 4, n5, n + n3 - 4, n5);
        final Color darken2 = ColorRoutines.darken(color, 6 * n6);
        graphics.setColor(darken2);
        graphics.drawLine(n + 2, n5, n + 2, n5);
        graphics.drawLine(n + n3 - 3, n5, n + n3 - 3, n5);
        graphics.setColor(ColorRoutines.getAlphaColor(darken2, 139));
        graphics.drawLine(n + 1, n5, n + 1, n5);
        graphics.drawLine(n + n3 - 2, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(color);
        graphics.drawLine(n + 7, n5, n + n3 - 8, n5);
        graphics.setColor(color);
        graphics.drawLine(n + 2, n5, n + 2, n5);
        graphics.drawLine(n + n3 - 3, n5, n + n3 - 3, n5);
        graphics.setColor(ColorRoutines.darken(color, 6 * n6));
        graphics.drawLine(n + 1, n5, n + 1, n5);
        graphics.drawLine(n + n3 - 2, n5, n + n3 - 2, n5);
        graphics.setColor(ColorRoutines.lighten(color, 10 * n7));
        graphics.drawLine(n + 3, n5, n + 3, n5);
        graphics.drawLine(n + n3 - 4, n5, n + n3 - 4, n5);
        graphics.setColor(ColorRoutines.lighten(color, 7 * n7));
        graphics.drawLine(n + 4, n5, n + 4, n5);
        graphics.drawLine(n + n3 - 5, n5, n + n3 - 5, n5);
        graphics.setColor(ColorRoutines.lighten(color, 3 * n7));
        graphics.drawLine(n + 5, n5, n + 5, n5);
        graphics.drawLine(n + n3 - 6, n5, n + n3 - 6, n5);
        graphics.setColor(color);
        graphics.drawLine(n + 6, n5, n + 6, n5);
        graphics.drawLine(n + n3 - 7, n5, n + n3 - 7, n5);
        ++n5;
        graphics.setColor(ColorRoutines.darken(color, 2 * n6));
        graphics.drawLine(n + 5, n5, n + n3 - 6, n5);
        graphics.setColor(ColorRoutines.darken(color, 6 * n6));
        graphics.drawLine(n + 1, n5, n + 1, n5);
        graphics.drawLine(n + n3 - 2, n5, n + n3 - 2, n5);
        graphics.setColor(ColorRoutines.lighten(color, 10 * n7));
        graphics.drawLine(n + 2, n5, n + 2, n5);
        graphics.drawLine(n + n3 - 3, n5, n + n3 - 3, n5);
        graphics.setColor(ColorRoutines.lighten(color, 5 * n7));
        graphics.drawLine(n + 3, n5, n + 3, n5);
        graphics.drawLine(n + n3 - 4, n5, n + n3 - 4, n5);
        graphics.setColor(color);
        graphics.drawLine(n + 4, n5, n + 4, n5);
        graphics.drawLine(n + n3 - 5, n5, n + n3 - 5, n5);
        ++n5;
        graphics.setColor(ColorRoutines.darken(color, 4 * n6));
        graphics.drawLine(n + 2, n5, n + n3 - 3, n5);
        graphics.setColor(ColorRoutines.darken(color, 4 * n6));
        graphics.drawLine(n + 1, n5, n + 1, n5);
        graphics.drawLine(n + n3 - 2, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.darken(color, 4 * n6));
        graphics.fillRect(n + 1, n5, n3 - 2, 1);
        ++n5;
        graphics.setColor(ColorRoutines.darken(color, 3 * n6));
        graphics.fillRect(n + 1, n5, n3 - 2, 3);
        n5 += 3;
        graphics.setColor(ColorRoutines.darken(color, 2 * n6));
        graphics.fillRect(n + 1, n5, n3 - 2, 2);
        n5 += 2;
        graphics.setColor(ColorRoutines.darken(color, 1 * n6));
        graphics.fillRect(n + 1, n5, n3 - 2, 1);
        ++n5;
        graphics.setColor(color);
        graphics.fillRect(n + 1, n5, n3 - 2, 2);
        n5 += 2;
        graphics.setColor(ColorRoutines.lighten(color, 2 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 4 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 5 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 6 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 8 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 9 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 10 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.lighten(color, 4 * n7));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        graphics.setColor(ColorRoutines.darken(color, 2 * n6));
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
        ++n5;
        if (this.isActive) {
            graphics.setColor(Theme.frameLightColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.frameLightDisabledColor[Theme.style].getColor());
        }
        graphics.drawLine(n + 1, n5, n + n3 - 2, n5);
    }
    
    public Insets getBorderInsets(final Component component) {
        final Window windowAncestor = SwingUtilities.getWindowAncestor(component);
        if (windowAncestor != null && windowAncestor instanceof Frame) {
            final Frame frame = (Frame)windowAncestor;
            if (frame.getExtendedState() == (frame.getExtendedState() | 0x6)) {
                return new Insets(0, 0, 0, 0);
            }
        }
        return new Insets(0, Theme.frameBorderWidth[Theme.derivedStyle[Theme.style]], Theme.frameBorderWidth[Theme.derivedStyle[Theme.style]], Theme.frameBorderWidth[Theme.derivedStyle[Theme.style]]);
    }
    
    static {
        TinyFrameBorder.cache = new HashMap();
        TinyFrameBorder.robotsSupported = true;
    }
    
    static class CaptionKey
    {
        private boolean isActive;
        private int titleHeight;
        
        CaptionKey(final boolean isActive, final int titleHeight) {
            this.isActive = isActive;
            this.titleHeight = titleHeight;
        }
        
        public boolean equals(final Object o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof CaptionKey)) {
                return false;
            }
            final CaptionKey captionKey = (CaptionKey)o;
            return this.isActive == captionKey.isActive && this.titleHeight == captionKey.titleHeight;
        }
        
        public int hashCode() {
            return (this.isActive ? 1 : 2) * this.titleHeight;
        }
    }
}
