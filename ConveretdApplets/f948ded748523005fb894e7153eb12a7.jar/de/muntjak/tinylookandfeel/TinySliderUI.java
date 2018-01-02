// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import de.muntjak.tinylookandfeel.controlpanel.SBChooser;
import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import javax.swing.plaf.ColorUIResource;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.BasicStroke;
import javax.swing.plaf.metal.MetalSliderUI;

public class TinySliderUI extends MetalSliderUI
{
    private static BasicStroke focusStroke;
    protected boolean isRollover;
    protected boolean wasRollover;
    protected boolean isDragging;
    protected TrackListener trackListener;
    
    public TinySliderUI() {
        this.isRollover = false;
        this.wasRollover = false;
        this.isDragging = false;
    }
    
    protected TrackListener createTrackListener(final JSlider slider) {
        return new MyTrackListener();
    }
    
    protected Dimension getThumbSize() {
        if (this.slider.getOrientation() == 1) {
            return Theme.sliderVertSize[Theme.derivedStyle[Theme.style]];
        }
        return Theme.sliderHorzSize[Theme.derivedStyle[Theme.style]];
    }
    
    protected int getTrackWidth() {
        return 4;
    }
    
    public void paintThumb(final Graphics graphics) {
        ColorUIResource color;
        if (!this.slider.isEnabled()) {
            color = Theme.sliderThumbDisabledColor[Theme.style].getColor();
        }
        else if (this.isDragging) {
            color = Theme.sliderThumbPressedColor[Theme.style].getColor();
        }
        else if (this.isRollover && Theme.sliderRolloverEnabled[Theme.style]) {
            if (Theme.derivedStyle[Theme.style] == 2) {
                color = Theme.sliderThumbColor[Theme.style].getColor();
            }
            else {
                color = Theme.sliderThumbRolloverColor[Theme.style].getColor();
            }
        }
        else {
            color = Theme.sliderThumbColor[Theme.style].getColor();
        }
        graphics.setColor(color);
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyThumb(graphics, color);
                break;
            }
            case 1: {
                this.drawWinThumb(graphics, color);
                break;
            }
            case 2: {
                this.drawXpThumb(graphics);
                break;
            }
        }
    }
    
    private void drawTinyThumb(final Graphics graphics, final Color color) {
        final int x = this.thumbRect.x;
        final int y = this.thumbRect.y;
        final int n = x + this.thumbRect.width - 1;
        final int n2 = y + this.thumbRect.height - 1;
        graphics.fillRect(x + 1, y + 1, this.thumbRect.width - 2, this.thumbRect.height - 2);
    }
    
    private void drawWinThumb(final Graphics graphics, final Color color) {
        final int x = this.thumbRect.x;
        final int y = this.thumbRect.y;
        final int n = x + this.thumbRect.width - 1;
        final int n2 = y + this.thumbRect.height - 1;
        if (this.slider.getOrientation() == 0) {
            graphics.fillRect(x + 1, y + 1, this.thumbRect.width - 2, 15);
            graphics.drawLine(x + 2, y + 16, x + 7, y + 16);
            graphics.drawLine(x + 3, y + 17, x + 6, y + 17);
            graphics.drawLine(x + 4, y + 18, x + 5, y + 18);
            if (this.isDragging && this.slider.isEnabled()) {
                graphics.setColor(Theme.sliderThumbColor[Theme.style].getColor());
                graphics.drawLine(x + 1, y + 1, n - 2, y + 1);
                graphics.drawLine(x + 1, y + 2, x + 1, y + 16);
            }
            if (!this.slider.isEnabled()) {
                graphics.setColor(Theme.sliderLightDisabledColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.sliderLightColor[Theme.style].getColor());
            }
            graphics.drawLine(x, y, x, y + 15);
            graphics.drawLine(x + 1, y, n - 1, y);
            graphics.drawLine(x + 1, y + 16, x + 1, y + 16);
            graphics.drawLine(x + 2, y + 17, x + 2, y + 17);
            graphics.drawLine(x + 3, y + 18, x + 3, y + 18);
            graphics.drawLine(x + 4, y + 19, x + 4, y + 19);
            if (!this.slider.isEnabled()) {
                graphics.setColor(Theme.sliderDarkDisabledColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.sliderDarkColor[Theme.style].getColor());
            }
            graphics.drawLine(n - 1, y + 1, n - 1, y + 15);
            graphics.drawLine(x + 8, y + 16, x + 8, y + 16);
            graphics.drawLine(x + 7, y + 17, x + 7, y + 17);
            graphics.drawLine(x + 6, y + 18, x + 6, y + 18);
            graphics.drawLine(x + 5, y + 19, x + 5, y + 19);
            if (!this.slider.isEnabled()) {
                graphics.setColor(Theme.sliderBorderDisabledColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.sliderBorderColor[Theme.style].getColor());
            }
            graphics.drawLine(n, y, n, y + 15);
            graphics.drawLine(x + 9, y + 16, x + 9, y + 16);
            graphics.drawLine(x + 8, y + 17, x + 8, y + 17);
            graphics.drawLine(x + 7, y + 18, x + 7, y + 18);
            graphics.drawLine(x + 6, y + 19, x + 6, y + 19);
            graphics.drawLine(x + 5, y + 20, x + 5, y + 20);
        }
        else {
            graphics.fillRect(x + 2, y + 1, this.thumbRect.width - 2, this.thumbRect.height - 2);
            if (this.isDragging && this.slider.isEnabled()) {
                graphics.setColor(Theme.sliderThumbColor[Theme.style].getColor());
                graphics.drawLine(x + 2, y + 1, n - 2, y + 1);
                graphics.drawLine(x + 2, y + 2, x + 2, n2 - 2);
            }
            if (!this.slider.isEnabled()) {
                graphics.setColor(Theme.sliderLightDisabledColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.sliderLightColor[Theme.style].getColor());
            }
            graphics.drawLine(x + 1, y, x + 1, n2 - 1);
            graphics.drawLine(x + 1, y, n - 1, y);
            if (!this.slider.isEnabled()) {
                graphics.setColor(Theme.sliderDarkDisabledColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.sliderDarkColor[Theme.style].getColor());
            }
            graphics.drawLine(x + 2, y + 9, n - 1, y + 9);
            graphics.drawLine(n - 1, y + 1, n - 1, n2 - 2);
            if (!this.slider.isEnabled()) {
                graphics.setColor(Theme.sliderBorderDisabledColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.sliderBorderColor[Theme.style].getColor());
            }
            graphics.drawLine(x + 1, n2, n, n2);
            graphics.drawLine(n, y, n, n2);
        }
    }
    
    private void drawXpThumb(final Graphics graphics) {
        final int x = this.thumbRect.x;
        final int y = this.thumbRect.y;
        final int n = x + this.thumbRect.width - 1;
        final int n2 = y + this.thumbRect.height - 1;
        if (this.slider.getPaintTicks()) {
            if (this.slider.getOrientation() == 0) {
                graphics.fillRect(x + 1, y + 4, this.thumbRect.width - 4, this.thumbRect.height - 8);
                graphics.drawLine(x + 5, n2 - 3, x + 5, n2 - 3);
                final Color color = graphics.getColor();
                graphics.setColor(ColorRoutines.darken(color, 10));
                graphics.drawLine(n - 2, y + 4, n - 2, n2 - 6);
                graphics.setColor(ColorRoutines.darken(color, 20));
                graphics.drawLine(n - 1, y + 4, n - 1, n2 - 7);
                ColorUIResource color2;
                if (!this.slider.isEnabled()) {
                    color2 = Theme.sliderBorderDisabledColor[Theme.style].getColor();
                }
                else {
                    color2 = Theme.sliderBorderColor[Theme.style].getColor();
                }
                graphics.setColor(color2);
                graphics.drawLine(x + 1, y, n - 1, y);
                graphics.drawLine(x, y + 1, x, n2 - 5);
                graphics.drawLine(x + 1, n2 - 4, x + 1, n2 - 4);
                graphics.drawLine(x + 2, n2 - 3, x + 2, n2 - 3);
                graphics.drawLine(x + 3, n2 - 2, x + 3, n2 - 2);
                graphics.drawLine(x + 4, n2 - 1, x + 4, n2 - 1);
                graphics.setColor(Theme.sliderDarkColor[Theme.style].getColor());
                graphics.drawLine(n, y + 1, n, n2 - 5);
                graphics.drawLine(n - 1, n2 - 4, n - 1, n2 - 4);
                graphics.drawLine(n - 2, n2 - 3, n - 2, n2 - 3);
                graphics.drawLine(n - 3, n2 - 2, n - 3, n2 - 2);
                graphics.drawLine(n - 4, n2 - 1, n - 4, n2 - 1);
                graphics.drawLine(n - 5, n2, n - 5, n2);
                ColorUIResource colorUIResource;
                if (!this.slider.isEnabled()) {
                    colorUIResource = Theme.sliderBorderDisabledColor[Theme.style].getColor();
                    graphics.setColor(colorUIResource);
                }
                else if (!this.isDragging && this.isRollover && Theme.sliderRolloverEnabled[Theme.style]) {
                    colorUIResource = Theme.sliderThumbRolloverColor[Theme.style].getColor();
                }
                else {
                    colorUIResource = Theme.sliderLightColor[Theme.style].getColor();
                }
                final Color adjustedColor = SBChooser.getAdjustedColor(colorUIResource, 67, 39);
                if (this.slider.isEnabled()) {
                    graphics.setColor(adjustedColor);
                }
                graphics.drawLine(x + 1, y + 1, n - 1, y + 1);
                graphics.drawLine(x + 1, n2 - 6, x + 1, n2 - 6);
                graphics.drawLine(x + 2, n2 - 5, x + 2, n2 - 5);
                graphics.drawLine(x + 3, n2 - 4, x + 3, n2 - 4);
                if (this.slider.isEnabled()) {
                    graphics.setColor(ColorRoutines.getAverage(color2, adjustedColor));
                }
                graphics.drawLine(x + 1, n2 - 5, x + 1, n2 - 5);
                graphics.drawLine(x + 2, n2 - 4, x + 2, n2 - 4);
                graphics.drawLine(x + 3, n2 - 3, x + 3, n2 - 3);
                final Color average = ColorRoutines.getAverage(colorUIResource, adjustedColor);
                if (this.slider.isEnabled()) {
                    graphics.setColor(average);
                }
                graphics.drawLine(x + 1, y + 2, n - 1, y + 2);
                graphics.drawLine(x + 4, n2 - 3, x + 4, n2 - 3);
                graphics.drawLine(x + 5, n2 - 2, x + 5, n2 - 2);
                graphics.drawLine(x + 6, n2 - 3, x + 6, n2 - 3);
                if (this.slider.isEnabled()) {
                    graphics.setColor(ColorRoutines.getAverage(color2, average));
                }
                graphics.drawLine(x + 4, n2 - 2, x + 4, n2 - 2);
                graphics.drawLine(x + 5, n2 - 1, x + 5, n2 - 1);
                graphics.drawLine(x + 6, n2 - 2, x + 6, n2 - 2);
                if (this.slider.isEnabled()) {
                    graphics.setColor(colorUIResource);
                }
                graphics.drawLine(x + 1, y + 3, n - 1, y + 3);
                graphics.drawLine(x + 9, n2 - 6, x + 9, n2 - 6);
                graphics.drawLine(x + 8, n2 - 5, x + 8, n2 - 5);
                graphics.drawLine(x + 7, n2 - 4, x + 7, n2 - 4);
                if (this.slider.isEnabled()) {
                    graphics.setColor(ColorRoutines.getAverage(color2, colorUIResource));
                }
                graphics.drawLine(x + 9, n2 - 5, x + 9, n2 - 5);
                graphics.drawLine(x + 8, n2 - 4, x + 8, n2 - 4);
                graphics.drawLine(x + 7, n2 - 3, x + 7, n2 - 3);
            }
            else {
                graphics.fillRect(x + 4, y + 1, this.thumbRect.width - 8, this.thumbRect.height - 4);
                graphics.drawLine(n - 3, y + 5, n - 3, y + 5);
                final Color color3 = graphics.getColor();
                graphics.setColor(ColorRoutines.darken(color3, 10));
                graphics.drawLine(x + 4, n2 - 2, n - 6, n2 - 2);
                graphics.setColor(ColorRoutines.darken(color3, 20));
                graphics.drawLine(x + 4, n2 - 1, n - 7, n2 - 1);
                ColorUIResource color4;
                if (!this.slider.isEnabled()) {
                    color4 = Theme.sliderBorderDisabledColor[Theme.style].getColor();
                }
                else {
                    color4 = Theme.sliderBorderColor[Theme.style].getColor();
                }
                graphics.setColor(color4);
                graphics.drawLine(x, y + 1, x, n2 - 1);
                graphics.drawLine(x + 1, y, n - 5, y);
                graphics.drawLine(n - 4, y + 1, n - 4, y + 1);
                graphics.drawLine(n - 3, y + 2, n - 3, y + 2);
                graphics.drawLine(n - 2, y + 3, n - 2, y + 3);
                graphics.drawLine(n - 1, y + 4, n - 1, y + 4);
                graphics.setColor(Theme.sliderDarkColor[Theme.style].getColor());
                graphics.drawLine(x + 1, n2, n - 5, n2);
                graphics.drawLine(n - 4, n2 - 1, n - 4, n2 - 1);
                graphics.drawLine(n - 3, n2 - 2, n - 3, n2 - 2);
                graphics.drawLine(n - 2, n2 - 3, n - 2, n2 - 3);
                graphics.drawLine(n - 1, n2 - 4, n - 1, n2 - 4);
                graphics.drawLine(n, n2 - 5, n, n2 - 5);
                ColorUIResource colorUIResource2;
                if (!this.slider.isEnabled()) {
                    colorUIResource2 = Theme.sliderBorderDisabledColor[Theme.style].getColor();
                    graphics.setColor(colorUIResource2);
                }
                else if (!this.isDragging && this.isRollover && Theme.sliderRolloverEnabled[Theme.style]) {
                    colorUIResource2 = Theme.sliderThumbRolloverColor[Theme.style].getColor();
                }
                else {
                    colorUIResource2 = Theme.sliderLightColor[Theme.style].getColor();
                }
                final Color adjustedColor2 = SBChooser.getAdjustedColor(colorUIResource2, 67, 39);
                if (this.slider.isEnabled()) {
                    graphics.setColor(adjustedColor2);
                }
                graphics.drawLine(x + 1, y + 1, x + 1, n2 - 1);
                graphics.drawLine(n - 6, y + 1, n - 6, y + 1);
                graphics.drawLine(n - 5, y + 2, n - 5, y + 2);
                graphics.drawLine(n - 4, y + 3, n - 4, y + 3);
                if (this.slider.isEnabled()) {
                    graphics.setColor(ColorRoutines.getAverage(color4, adjustedColor2));
                }
                graphics.drawLine(n - 5, y + 1, n - 5, y + 1);
                graphics.drawLine(n - 4, y + 2, n - 4, y + 2);
                graphics.drawLine(n - 3, y + 3, n - 3, y + 3);
                final Color average2 = ColorRoutines.getAverage(colorUIResource2, adjustedColor2);
                if (this.slider.isEnabled()) {
                    graphics.setColor(average2);
                }
                graphics.drawLine(x + 2, y + 1, x + 2, n2 - 1);
                graphics.drawLine(n - 3, y + 4, n - 3, y + 4);
                graphics.drawLine(n - 2, y + 5, n - 2, y + 5);
                graphics.drawLine(n - 3, y + 6, n - 3, y + 6);
                if (this.slider.isEnabled()) {
                    graphics.setColor(ColorRoutines.getAverage(color4, average2));
                }
                graphics.drawLine(n - 2, y + 4, n - 2, y + 4);
                graphics.drawLine(n - 1, y + 5, n - 1, y + 5);
                graphics.drawLine(n - 2, y + 6, n - 2, y + 6);
                if (this.slider.isEnabled()) {
                    graphics.setColor(colorUIResource2);
                }
                graphics.drawLine(x + 3, y + 1, x + 3, n2 - 1);
                graphics.drawLine(n - 6, y + 9, n - 6, y + 9);
                graphics.drawLine(n - 5, y + 8, n - 5, y + 8);
                graphics.drawLine(n - 4, y + 7, n - 4, y + 7);
                if (this.slider.isEnabled()) {
                    graphics.setColor(ColorRoutines.getAverage(color4, colorUIResource2));
                }
                graphics.drawLine(n - 5, y + 9, n - 5, y + 9);
                graphics.drawLine(n - 4, y + 8, n - 4, y + 8);
                graphics.drawLine(n - 3, y + 7, n - 3, y + 7);
            }
        }
        else if (this.slider.getOrientation() == 0) {
            graphics.fillRect(x + 1, y + 1, this.thumbRect.width - 4, this.thumbRect.height - 4);
            final Color color5 = graphics.getColor();
            graphics.setColor(ColorRoutines.darken(color5, 10));
            graphics.drawLine(n - 2, y + 3, n - 2, n2 - 3);
            graphics.setColor(ColorRoutines.darken(color5, 20));
            graphics.drawLine(n - 1, y + 3, n - 1, n2 - 3);
            if (!this.slider.isEnabled()) {
                graphics.setColor(Theme.sliderBorderDisabledColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.sliderBorderColor[Theme.style].getColor());
            }
            graphics.drawLine(x, y + 1, x, n2 - 1);
            graphics.drawLine(x + 1, y, n - 1, y);
            if (!this.slider.isEnabled()) {
                graphics.setColor(Theme.sliderDarkDisabledColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.sliderDarkColor[Theme.style].getColor());
            }
            graphics.drawLine(x + 1, n2, n - 1, n2);
            graphics.drawLine(n, y + 1, n, n2 - 1);
            ColorUIResource colorUIResource3;
            if (!this.slider.isEnabled()) {
                colorUIResource3 = Theme.sliderBorderDisabledColor[Theme.style].getColor();
                graphics.setColor(colorUIResource3);
            }
            else if (!this.isDragging && this.isRollover && Theme.sliderRolloverEnabled[Theme.style]) {
                colorUIResource3 = Theme.sliderThumbRolloverColor[Theme.style].getColor();
            }
            else {
                colorUIResource3 = Theme.sliderLightColor[Theme.style].getColor();
            }
            final Color adjustedColor3 = SBChooser.getAdjustedColor(colorUIResource3, 67, 39);
            if (this.slider.isEnabled()) {
                graphics.setColor(adjustedColor3);
            }
            graphics.drawLine(x + 1, y + 1, n - 1, y + 1);
            if (this.slider.isEnabled()) {
                graphics.setColor(ColorRoutines.getAverage(colorUIResource3, adjustedColor3));
            }
            graphics.drawLine(x + 1, y + 2, n - 1, y + 2);
            graphics.drawLine(x + 1, n2 - 2, n - 1, n2 - 2);
            if (this.slider.isEnabled()) {
                graphics.setColor(colorUIResource3);
            }
            graphics.drawLine(x + 1, n2 - 1, n - 1, n2 - 1);
        }
        else {
            graphics.fillRect(x + 1, y + 1, this.thumbRect.width - 4, this.thumbRect.height - 4);
            final Color color6 = graphics.getColor();
            graphics.setColor(ColorRoutines.darken(color6, 10));
            graphics.drawLine(x + 3, n2 - 2, n - 3, n2 - 2);
            graphics.setColor(ColorRoutines.darken(color6, 20));
            graphics.drawLine(x + 3, n2 - 1, n - 3, n2 - 1);
            if (!this.slider.isEnabled()) {
                graphics.setColor(Theme.sliderBorderDisabledColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.sliderBorderColor[Theme.style].getColor());
            }
            graphics.drawLine(x + 1, y, n - 1, y);
            graphics.drawLine(x, y + 1, x, n2 - 1);
            if (!this.slider.isEnabled()) {
                graphics.setColor(Theme.sliderDarkDisabledColor[Theme.style].getColor());
            }
            else {
                graphics.setColor(Theme.sliderDarkColor[Theme.style].getColor());
            }
            graphics.drawLine(n, y + 1, n, n2 - 1);
            graphics.drawLine(x + 1, n2, n - 1, n2);
            ColorUIResource colorUIResource4;
            if (!this.slider.isEnabled()) {
                colorUIResource4 = Theme.sliderBorderDisabledColor[Theme.style].getColor();
                graphics.setColor(colorUIResource4);
            }
            else if (!this.isDragging && this.isRollover && Theme.sliderRolloverEnabled[Theme.style]) {
                colorUIResource4 = Theme.sliderThumbRolloverColor[Theme.style].getColor();
            }
            else {
                colorUIResource4 = Theme.sliderLightColor[Theme.style].getColor();
            }
            final Color adjustedColor4 = SBChooser.getAdjustedColor(colorUIResource4, 67, 39);
            if (this.slider.isEnabled()) {
                graphics.setColor(adjustedColor4);
            }
            graphics.drawLine(x + 1, y + 1, x + 1, n2 - 1);
            if (this.slider.isEnabled()) {
                graphics.setColor(ColorRoutines.getAverage(colorUIResource4, adjustedColor4));
            }
            graphics.drawLine(x + 2, y + 1, x + 2, n2 - 1);
            graphics.drawLine(n - 2, y + 1, n - 2, n2 - 1);
            if (this.slider.isEnabled()) {
                graphics.setColor(colorUIResource4);
            }
            graphics.drawLine(n - 1, y + 1, n - 1, n2 - 1);
        }
    }
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinySliderUI();
    }
    
    public void installUI(final JComponent component) {
        super.installUI(component);
        component.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
    }
    
    protected int getThumbOverhang() {
        if (this.slider.getOrientation() == 1) {
            return (int)(this.getThumbSize().getWidth() - this.getTrackWidth()) / 2;
        }
        return (int)(this.getThumbSize().getHeight() - this.getTrackWidth()) / 2;
    }
    
    public void paintTrack(final Graphics graphics) {
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyTrack(graphics);
                break;
            }
            case 1: {
                this.drawWinTrack(graphics);
                break;
            }
            case 2: {
                this.drawXpTrack(graphics);
                break;
            }
        }
    }
    
    public void paintFocus(final Graphics graphics) {
        if (!Theme.sliderFocusEnabled[Theme.style]) {
            return;
        }
        if (!(graphics instanceof Graphics2D)) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Stroke stroke = graphics2D.getStroke();
        graphics2D.setStroke(TinySliderUI.focusStroke);
        graphics2D.setColor(Theme.sliderFocusColor[Theme.style].getColor());
        graphics2D.drawRect(0, 0, this.slider.getWidth() - 1, this.slider.getHeight() - 1);
        graphics2D.setStroke(stroke);
    }
    
    private void drawTinyTrack(final Graphics graphics) {
        final int x = this.trackRect.x;
        final int n = x + this.trackRect.width;
        final int y = this.trackRect.y;
        final int n2 = y + this.trackRect.height;
        if (this.slider.getOrientation() == 0) {
            final int n3 = y + (this.trackRect.height - 7) / 2;
            graphics.setColor(new Color(170, 170, 170));
            graphics.drawLine(x, n3 + 1, x, n3 + 4);
            graphics.drawLine(n - 1, n3 + 1, n - 1, n3 + 1);
            graphics.fillRect(x + 1, n3, this.trackRect.width - 2, 5);
            graphics.setColor(new Color(34, 34, 34));
            graphics.drawLine(x + 1, n3 + 2, x + 1, n3 + 4);
            graphics.drawLine(n - 1, n3 + 2, n - 1, n3 + 4);
            graphics.drawLine(x + 2, n3 + 1, n - 2, n3 + 1);
            graphics.drawLine(x + 2, n3 + 5, n - 2, n3 + 5);
            graphics.setColor(Color.WHITE);
            graphics.drawLine(x + 1, n3 + 5, x + 1, n3 + 5);
            graphics.drawLine(x + 2, n3 + 6, n - 1, n3 + 6);
            graphics.drawLine(n - 1, n3 + 5, n - 1, n3 + 5);
            graphics.drawLine(n, n3 + 2, n, n3 + 4);
        }
        else {
            final int n4 = x + (this.trackRect.width - 7) / 2 + 1;
            graphics.setColor(new Color(170, 170, 170));
            graphics.drawLine(n4 + 1, y + 1, n4 + 1, y + 1);
            graphics.drawLine(n4 + 1, n2, n4 + 4, n2);
            graphics.fillRect(n4, y + 2, 5, this.trackRect.height - 2);
            graphics.setColor(new Color(34, 34, 34));
            graphics.drawLine(n4 + 2, y + 1, n4 + 4, y + 1);
            graphics.drawLine(n4 + 2, n2 - 1, n4 + 4, n2 - 1);
            graphics.drawLine(n4 + 1, y + 2, n4 + 1, n2 - 2);
            graphics.drawLine(n4 + 5, y + 2, n4 + 5, n2 - 2);
            graphics.setColor(Color.WHITE);
            graphics.drawLine(n4 + 2, y, n4 + 5, y);
            graphics.drawLine(n4 + 5, y + 1, n4 + 5, y + 1);
            graphics.drawLine(n4 + 6, y + 1, n4 + 6, n2 - 2);
            graphics.drawLine(n4 + 5, n2 - 1, n4 + 5, n2 - 1);
        }
    }
    
    private void drawWinTrack(final Graphics graphics) {
        final int x = this.trackRect.x;
        final int n = x + this.trackRect.width;
        final int y = this.trackRect.y;
        final int n2 = y + this.trackRect.height;
        if (this.slider.getOrientation() == 0) {
            final int n3 = y + (this.trackRect.height - 4) / 2;
            graphics.setColor(Theme.sliderTrackColor[Theme.style].getColor());
            graphics.drawLine(x + 1, n3 + 2, n - 1, n3 + 2);
            graphics.drawLine(n - 1, n3 + 1, n - 1, n3 + 1);
            graphics.setColor(Theme.sliderTrackDarkColor[Theme.style].getColor());
            graphics.drawLine(x, n3, x, n3 + 3);
            graphics.drawLine(x + 1, n3, n, n3);
            graphics.setColor(Theme.sliderTrackBorderColor[Theme.style].getColor());
            graphics.drawLine(x + 1, n3 + 1, n - 2, n3 + 1);
            graphics.setColor(Theme.sliderTrackLightColor[Theme.style].getColor());
            graphics.drawLine(n, n3 + 1, n, n3 + 2);
            graphics.drawLine(x + 1, n3 + 3, n, n3 + 3);
        }
        else {
            final int n4 = x + (this.trackRect.width - 4) / 2 + 1;
            graphics.setColor(Theme.sliderTrackColor[Theme.style].getColor());
            graphics.drawLine(n4 + 2, y + 1, n4 + 2, n2 - 1);
            graphics.drawLine(n4 + 1, n2 - 1, n4 + 1, n2 - 1);
            graphics.setColor(Theme.sliderTrackDarkColor[Theme.style].getColor());
            graphics.drawLine(n4, y, n4 + 3, y);
            graphics.drawLine(n4, y + 1, n4, n2);
            graphics.setColor(Theme.sliderTrackBorderColor[Theme.style].getColor());
            graphics.drawLine(n4 + 1, y + 1, n4 + 1, n2 - 2);
            graphics.setColor(Theme.sliderTrackLightColor[Theme.style].getColor());
            graphics.drawLine(n4 + 3, y + 1, n4 + 3, n2);
            graphics.drawLine(n4 + 1, n2, n4 + 2, n2);
        }
    }
    
    private void drawXpTrack(final Graphics graphics) {
        final int x = this.trackRect.x;
        final int n = x + this.trackRect.width;
        final int y = this.trackRect.y;
        final int n2 = y + this.trackRect.height;
        if (this.slider.getOrientation() == 0) {
            final int n3 = y + (this.trackRect.height - 4) / 2;
            graphics.setColor(Theme.sliderTrackColor[Theme.style].getColor());
            graphics.drawLine(x + 1, n3 + 2, n - 2, n3 + 2);
            graphics.setColor(Theme.sliderTrackDarkColor[Theme.style].getColor());
            graphics.drawLine(x + 1, n3 + 1, n - 2, n3 + 1);
            graphics.drawLine(n - 1, n3 + 1, n - 1, n3 + 2);
            graphics.drawLine(x, n3, x, n3);
            graphics.drawLine(x, n3 + 3, x, n3 + 3);
            graphics.setColor(Theme.sliderTrackLightColor[Theme.style].getColor());
            graphics.drawLine(x + 1, n3 + 3, n - 1, n3 + 3);
            graphics.drawLine(n, n3 + 1, n, n3 + 3);
            graphics.setColor(Theme.sliderTrackBorderColor[Theme.style].getColor());
            graphics.drawLine(x + 1, n3, n, n3);
            graphics.setColor(ColorRoutines.lighten(Theme.sliderTrackBorderColor[Theme.style].getColor(), 20));
            graphics.drawLine(x, n3 + 1, x, n3 + 2);
        }
        else {
            final int n4 = x + (this.trackRect.width - 4) / 2;
            graphics.setColor(Theme.sliderTrackBorderColor[Theme.style].getColor());
            graphics.drawLine(n4, y + 2, n4, n2 - 2);
            graphics.setColor(ColorRoutines.lighten(Theme.sliderTrackBorderColor[Theme.style].getColor(), 20));
            graphics.drawLine(n4, y + 1, n4 + 2, y + 1);
            graphics.drawLine(n4, n2 - 1, n4 + 2, n2 - 1);
            graphics.setColor(Theme.sliderTrackDarkColor[Theme.style].getColor());
            graphics.drawLine(n4, y, n4 + 3, y);
            graphics.drawLine(n4 + 1, y + 2, n4 + 1, n2 - 2);
            graphics.drawLine(n4, n2, n4 + 3, n2);
            graphics.setColor(Theme.sliderTrackLightColor[Theme.style].getColor());
            graphics.drawLine(n4 + 3, y + 1, n4 + 3, n2 - 1);
            graphics.setColor(Theme.sliderTrackColor[Theme.style].getColor());
            graphics.drawLine(n4 + 2, y + 2, n4 + 2, n2 - 2);
        }
    }
    
    protected void paintMinorTickForHorizSlider(final Graphics graphics, final Rectangle rectangle, final int n) {
        graphics.setColor(this.slider.isEnabled() ? Theme.sliderTickColor[Theme.style].getColor() : Theme.sliderTickDisabledColor[Theme.style].getColor());
        graphics.drawLine(n, 0, n, rectangle.height / 2 - 1);
    }
    
    protected void paintMajorTickForHorizSlider(final Graphics graphics, final Rectangle rectangle, final int n) {
        graphics.setColor(this.slider.isEnabled() ? Theme.sliderTickColor[Theme.style].getColor() : Theme.sliderTickDisabledColor[Theme.style].getColor());
        graphics.drawLine(n, 0, n, rectangle.height - 2);
    }
    
    protected void paintMinorTickForVertSlider(final Graphics graphics, final Rectangle rectangle, final int n) {
        graphics.setColor(this.slider.isEnabled() ? Theme.sliderTickColor[Theme.style].getColor() : Theme.sliderTickDisabledColor[Theme.style].getColor());
        graphics.drawLine(0, n, rectangle.width / 2 - 1, n);
    }
    
    protected void paintMajorTickForVertSlider(final Graphics graphics, final Rectangle rectangle, final int n) {
        graphics.setColor(this.slider.isEnabled() ? Theme.sliderTickColor[Theme.style].getColor() : Theme.sliderTickDisabledColor[Theme.style].getColor());
        graphics.drawLine(0, n, rectangle.width - 2, n);
    }
    
    static {
        TinySliderUI.focusStroke = new BasicStroke(1.0f, 0, 2, 1.0f, new float[] { 1.0f, 1.0f }, 0.0f);
    }
    
    class MyTrackListener extends TrackListener
    {
        public void mouseReleased(final MouseEvent mouseEvent) {
            super.mouseReleased(mouseEvent);
            TinySliderUI.this.isDragging = false;
            TinySliderUI.this.slider.repaint();
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            if (TinySliderUI.this.thumbRect.contains(mouseEvent.getX(), mouseEvent.getY())) {
                TinySliderUI.this.isDragging = true;
            }
            TinySliderUI.this.slider.repaint();
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            TinySliderUI.this.isRollover = false;
            TinySliderUI.this.wasRollover = false;
            if (TinySliderUI.this.thumbRect.contains(mouseEvent.getX(), mouseEvent.getY())) {
                TinySliderUI.this.isRollover = true;
            }
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            TinySliderUI.this.isRollover = false;
            if (TinySliderUI.this.isRollover != TinySliderUI.this.wasRollover) {
                TinySliderUI.this.slider.repaint();
                TinySliderUI.this.wasRollover = TinySliderUI.this.isRollover;
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            if (TinySliderUI.this.thumbRect.contains(mouseEvent.getX(), mouseEvent.getY())) {
                TinySliderUI.this.isRollover = true;
            }
            super.mouseDragged(mouseEvent);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (TinySliderUI.this.thumbRect.contains(mouseEvent.getX(), mouseEvent.getY())) {
                TinySliderUI.this.isRollover = true;
                if (TinySliderUI.this.isRollover != TinySliderUI.this.wasRollover) {
                    TinySliderUI.this.slider.repaint();
                    TinySliderUI.this.wasRollover = TinySliderUI.this.isRollover;
                }
            }
            else {
                TinySliderUI.this.isRollover = false;
                if (TinySliderUI.this.isRollover != TinySliderUI.this.wasRollover) {
                    TinySliderUI.this.slider.repaint();
                    TinySliderUI.this.wasRollover = TinySliderUI.this.isRollover;
                }
            }
        }
    }
}
