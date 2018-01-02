// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JScrollBar;
import javax.swing.plaf.ColorUIResource;
import de.muntjak.tinylookandfeel.controlpanel.SBChooser;
import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import java.awt.Dimension;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class TinyScrollBarUI extends BasicScrollBarUI
{
    static final int alpha = 92;
    protected boolean isRollover;
    protected boolean wasRollover;
    private boolean freeStanding;
    private int scrollBarWidth;
    
    public TinyScrollBarUI() {
        this.isRollover = false;
        this.wasRollover = false;
        this.freeStanding = false;
    }
    
    protected void installDefaults() {
        this.scrollBarWidth = TinyScrollButton.size[Theme.derivedStyle[Theme.style]].width;
        super.installDefaults();
        this.scrollbar.setBorder(null);
        this.minimumThumbSize = new Dimension(17, 17);
    }
    
    protected Dimension getMaximumThumbSize() {
        return this.maximumThumbSize;
    }
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyScrollBarUI();
    }
    
    protected JButton createDecreaseButton(final int n) {
        return new TinyScrollButton(n, this);
    }
    
    protected JButton createIncreaseButton(final int n) {
        return new TinyScrollButton(n, this);
    }
    
    public Dimension getPreferredSize(final JComponent component) {
        if (this.scrollbar.getOrientation() == 1) {
            return new Dimension(this.scrollBarWidth, this.scrollBarWidth * 3 + 10);
        }
        return new Dimension(this.scrollBarWidth * 3 + 10, this.scrollBarWidth);
    }
    
    public void paintTrack(final Graphics graphics, final JComponent component, final Rectangle rectangle) {
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyTrack(graphics, rectangle);
                break;
            }
            case 1: {
                this.drawWinTrack(graphics, rectangle);
                break;
            }
            case 2: {
                this.drawXpTrack(graphics, rectangle);
                break;
            }
        }
    }
    
    private void drawTinyTrack(final Graphics graphics, final Rectangle rectangle) {
        if (this.isThumbVisible()) {
            graphics.setColor(Theme.scrollTrackColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.scrollTrackDisabledColor[Theme.style].getColor());
        }
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.setColor(Color.BLACK);
        if (this.scrollbar.getOrientation() == 1) {
            graphics.drawLine(rectangle.x, rectangle.y, rectangle.x, rectangle.y + rectangle.height - 1);
        }
        else {
            graphics.drawLine(rectangle.x, rectangle.y, rectangle.x + rectangle.width - 1, rectangle.y);
        }
        if (!this.isThumbVisible()) {
            return;
        }
        if (this.scrollbar.getOrientation() == 1) {
            graphics.setColor(ColorRoutines.darken(Theme.scrollTrackColor[Theme.style].getColor(), 30));
            graphics.drawLine(rectangle.x + 1, rectangle.y + 1, rectangle.x + rectangle.width - 3, rectangle.y + 1);
            graphics.drawLine(rectangle.x + 1, rectangle.y + 1, rectangle.x + 1, rectangle.y + rectangle.height - 1);
            graphics.setColor(ColorRoutines.darken(Theme.scrollTrackColor[Theme.style].getColor(), 20));
            graphics.drawLine(rectangle.x + 2, rectangle.y + 2, rectangle.x + rectangle.width - 4, rectangle.y + 2);
            graphics.drawLine(rectangle.x + 2, rectangle.y + 2, rectangle.x + 2, rectangle.y + rectangle.height - 1);
            graphics.setColor(ColorRoutines.lighten(Theme.scrollTrackColor[Theme.style].getColor(), 40));
            graphics.drawLine(rectangle.x + rectangle.width - 2, rectangle.y + 1, rectangle.x + rectangle.width - 2, rectangle.y + rectangle.height - 1);
            graphics.setColor(ColorRoutines.lighten(Theme.scrollTrackColor[Theme.style].getColor(), 20));
            graphics.drawLine(rectangle.x + rectangle.width - 3, rectangle.y + 2, rectangle.x + rectangle.width - 3, rectangle.y + rectangle.height - 1);
        }
        else {
            graphics.setColor(ColorRoutines.darken(Theme.scrollTrackColor[Theme.style].getColor(), 30));
            graphics.drawLine(rectangle.x + 1, rectangle.y + 1, rectangle.x + rectangle.width - 1, rectangle.y + 1);
            graphics.drawLine(rectangle.x + 1, rectangle.y + 1, rectangle.x + 1, rectangle.y + rectangle.height - 3);
            graphics.setColor(ColorRoutines.darken(Theme.scrollTrackColor[Theme.style].getColor(), 20));
            graphics.drawLine(rectangle.x + 2, rectangle.y + 2, rectangle.x + rectangle.width - 1, rectangle.y + 2);
            graphics.drawLine(rectangle.x + 2, rectangle.y + 2, rectangle.x + 2, rectangle.y + rectangle.height - 4);
            graphics.setColor(ColorRoutines.lighten(Theme.scrollTrackColor[Theme.style].getColor(), 40));
            graphics.drawLine(rectangle.x + 1, rectangle.y + rectangle.height - 2, rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 2);
            graphics.setColor(ColorRoutines.lighten(Theme.scrollTrackColor[Theme.style].getColor(), 20));
            graphics.drawLine(rectangle.x + 2, rectangle.y + rectangle.height - 3, rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 3);
        }
    }
    
    private void drawWinTrack(final Graphics graphics, final Rectangle rectangle) {
        if (this.isThumbVisible()) {
            graphics.setColor(Theme.scrollTrackColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.scrollTrackDisabledColor[Theme.style].getColor());
        }
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    private void drawXpTrack(final Graphics graphics, final Rectangle rectangle) {
        if (this.isThumbVisible()) {
            graphics.setColor(Theme.scrollTrackColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.scrollTrackDisabledColor[Theme.style].getColor());
        }
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        if (this.isThumbVisible()) {
            graphics.setColor(Theme.scrollTrackBorderColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.scrollTrackBorderDisabledColor[Theme.style].getColor());
        }
        if (this.scrollbar.getOrientation() == 1) {
            graphics.drawLine(rectangle.x, rectangle.y, rectangle.x, rectangle.y + rectangle.height - 1);
            graphics.drawLine(rectangle.x + rectangle.width - 1, rectangle.y, rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 1);
        }
        else {
            graphics.drawLine(rectangle.x, rectangle.y, rectangle.x + rectangle.width - 1, rectangle.y);
            graphics.drawLine(rectangle.x, rectangle.y + rectangle.height - 1, rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 1);
        }
    }
    
    public void paintThumb(final Graphics graphics, final JComponent component, final Rectangle rectangle) {
        switch (Theme.derivedStyle[Theme.style]) {
            case 0: {
                this.drawTinyThumb(graphics, rectangle);
                break;
            }
            case 1: {
                this.drawWinThumb(graphics, rectangle);
                break;
            }
            case 2: {
                this.drawXpThumb(graphics, rectangle);
                break;
            }
        }
    }
    
    private void drawTinyThumb(final Graphics graphics, final Rectangle rectangle) {
    }
    
    private void drawWinThumb(final Graphics graphics, final Rectangle rectangle) {
        if (this.isDragging) {
            graphics.setColor(Theme.scrollThumbPressedColor[Theme.style].getColor());
        }
        else if (this.isRollover && Theme.scrollRollover[Theme.style]) {
            graphics.setColor(Theme.scrollThumbRolloverColor[Theme.style].getColor());
        }
        else {
            graphics.setColor(Theme.scrollThumbColor[Theme.style].getColor());
        }
        final int n = rectangle.x + rectangle.width;
        final int n2 = rectangle.y + rectangle.height;
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height - 1);
        graphics.setColor(Theme.scrollLightColor[Theme.style].getColor());
        graphics.drawLine(rectangle.x + 1, rectangle.y + 1, n - 3, rectangle.y + 1);
        graphics.drawLine(rectangle.x + 1, rectangle.y + 1, rectangle.x + 1, n2 - 3);
        graphics.setColor(Theme.scrollDarkColor[Theme.style].getColor());
        graphics.drawLine(n - 2, rectangle.y + 1, n - 2, n2 - 3);
        graphics.drawLine(rectangle.x + 1, n2 - 2, n - 2, n2 - 2);
        graphics.setColor(Theme.scrollBorderColor[Theme.style].getColor());
        graphics.drawLine(n - 1, rectangle.y, n - 1, n2 - 2);
        graphics.drawLine(rectangle.x, n2 - 1, n - 1, n2 - 1);
    }
    
    private void drawXpThumb(final Graphics graphics, final Rectangle rectangle) {
        ColorUIResource color;
        if (this.isDragging && this.isRollover) {
            color = Theme.scrollThumbPressedColor[Theme.style].getColor();
        }
        else if (this.isRollover && Theme.scrollRollover[Theme.style]) {
            color = Theme.scrollThumbRolloverColor[Theme.style].getColor();
        }
        else {
            color = Theme.scrollThumbColor[Theme.style].getColor();
        }
        graphics.setColor(color);
        final int n = rectangle.x + rectangle.width - 1;
        final int n2 = rectangle.y + rectangle.height - 1;
        final int n3 = Theme.scrollSpreadLight[Theme.style];
        final int n4 = Theme.scrollSpreadDark[Theme.style];
        final int n5 = 15;
        final float n6 = 10.0f * n3 / 10.0f;
        final float n7 = 10.0f * n4 / 10.0f;
        final int n8 = n5 / 2;
        switch (this.scrollbar.getOrientation()) {
            case 1: {
                for (int i = 1; i < n5; ++i) {
                    if (i < n8) {
                        graphics.setColor(ColorRoutines.lighten(color, (int)((n8 - i) * n6)));
                    }
                    else if (i == n8) {
                        graphics.setColor(color);
                    }
                    else {
                        graphics.setColor(ColorRoutines.darken(color, (int)((i - n8) * n7)));
                    }
                    graphics.drawLine(rectangle.x + i, rectangle.y + 2, rectangle.x + i, n2 - 1);
                }
                graphics.setColor(Theme.scrollLightColor[Theme.style].getColor());
                graphics.drawLine(rectangle.x + 3, rectangle.y + 1, rectangle.x + 14, rectangle.y + 1);
                graphics.drawLine(rectangle.x + 15, rectangle.y + 2, rectangle.x + 15, n2 - 2);
                graphics.setColor(Theme.scrollBorderColor[Theme.style].getColor());
                graphics.drawRect(rectangle.x + 1, rectangle.y, 15, n2 - rectangle.y);
                final ColorUIResource color2 = Theme.scrollBorderColor[Theme.style].getColor();
                graphics.setColor(new Color(color2.getRed(), color2.getGreen(), color2.getBlue(), 92));
                graphics.drawLine(rectangle.x + 2, rectangle.y + 1, rectangle.x + 2, rectangle.y + 1);
                graphics.drawLine(rectangle.x + 15, rectangle.y + 1, rectangle.x + 15, rectangle.y + 1);
                graphics.drawLine(rectangle.x + 2, n2 - 1, rectangle.x + 2, n2 - 1);
                graphics.drawLine(rectangle.x + 15, n2 - 1, rectangle.x + 15, n2 - 1);
                final ColorUIResource color3 = Theme.scrollLightColor[Theme.style].getColor();
                graphics.setColor(new Color(color3.getRed(), color3.getGreen(), color3.getBlue(), 92));
                graphics.drawLine(rectangle.x + 1, rectangle.y, rectangle.x + 1, rectangle.y);
                graphics.drawLine(rectangle.x + 16, rectangle.y, rectangle.x + 16, rectangle.y);
                graphics.drawLine(rectangle.x + 1, n2, rectangle.x + 1, n2);
                graphics.drawLine(rectangle.x + 16, n2, rectangle.x + 16, n2);
                break;
            }
            case 0: {
                for (int j = 1; j < n5; ++j) {
                    if (j < n8) {
                        graphics.setColor(ColorRoutines.lighten(color, (int)((n8 - j) * n6)));
                    }
                    else if (j == n8) {
                        graphics.setColor(color);
                    }
                    else {
                        graphics.setColor(ColorRoutines.darken(color, (int)((j - n8) * n7)));
                    }
                    graphics.drawLine(rectangle.x + 1, rectangle.y + j, n - 2, rectangle.y + j);
                }
                graphics.setColor(Theme.scrollLightColor[Theme.style].getColor());
                graphics.drawLine(rectangle.x + 2, rectangle.y + 15, n - 2, rectangle.y + 15);
                graphics.drawLine(n - 1, rectangle.y + 3, n - 1, rectangle.y + 14);
                graphics.setColor(Theme.scrollBorderColor[Theme.style].getColor());
                graphics.drawRect(rectangle.x, rectangle.y + 1, n - rectangle.x, 15);
                graphics.setColor(Theme.scrollTrackBorderColor[Theme.style].getColor());
                graphics.drawLine(rectangle.x, rectangle.y, n, rectangle.y);
                final ColorUIResource color4 = Theme.scrollBorderColor[Theme.style].getColor();
                graphics.setColor(new Color(color4.getRed(), color4.getGreen(), color4.getBlue(), 92));
                graphics.drawLine(rectangle.x + 1, rectangle.y + 2, rectangle.x + 1, rectangle.y + 2);
                graphics.drawLine(rectangle.x + 1, rectangle.y + 15, rectangle.x + 1, rectangle.y + 15);
                graphics.drawLine(n - 1, rectangle.y + 2, n - 1, rectangle.y + 2);
                graphics.drawLine(n - 1, rectangle.y + 15, n - 1, rectangle.y + 15);
                final ColorUIResource color5 = Theme.scrollLightColor[Theme.style].getColor();
                graphics.setColor(new Color(color5.getRed(), color5.getGreen(), color5.getBlue(), 92));
                graphics.drawLine(rectangle.x, rectangle.y + 1, rectangle.x, rectangle.y + 1);
                graphics.drawLine(rectangle.x, rectangle.y + 16, rectangle.x, rectangle.y + 16);
                graphics.drawLine(n, rectangle.y + 1, n, rectangle.y + 1);
                graphics.drawLine(n, rectangle.y + 16, n, rectangle.y + 16);
                break;
            }
        }
        if (rectangle.height < 11) {
            return;
        }
        if (this.scrollbar.getOrientation() == 1) {
            final int n9 = rectangle.y + rectangle.height / 2 - 4;
            final int min = Math.min(n9 + 8, rectangle.y + rectangle.height - 5);
            int k = n9;
            graphics.setColor(SBChooser.getAdjustedColor(color, Theme.scrollGripLightColor[Theme.style].getSaturation(), Theme.scrollGripLightColor[Theme.style].getBrightness()));
            while (k < min) {
                graphics.drawLine(5, k, 11, k);
                k += 2;
            }
            int l = n9 + 1;
            graphics.setColor(SBChooser.getAdjustedColor(color, Theme.scrollGripDarkColor[Theme.style].getSaturation(), Theme.scrollGripDarkColor[Theme.style].getBrightness()));
            while (l < min) {
                graphics.drawLine(6, l, 12, l);
                l += 2;
            }
        }
        else {
            final int n10 = rectangle.x + rectangle.width / 2 - 4;
            final int min2 = Math.min(n10 + 8, rectangle.x + rectangle.width - 5);
            int n11 = n10 + 1;
            graphics.setColor(SBChooser.getAdjustedColor(color, Theme.scrollGripLightColor[Theme.style].getSaturation(), Theme.scrollGripLightColor[Theme.style].getBrightness()));
            while (n11 < min2) {
                graphics.drawLine(n11, 5, n11, 11);
                n11 += 2;
            }
            int n12 = n10;
            graphics.setColor(SBChooser.getAdjustedColor(color, Theme.scrollGripDarkColor[Theme.style].getSaturation(), Theme.scrollGripDarkColor[Theme.style].getBrightness()));
            while (n12 < min2) {
                graphics.drawLine(n12, 6, n12, 12);
                n12 += 2;
            }
        }
    }
    
    public boolean isThumbVisible() {
        if (this.scrollbar.getOrientation() == 1) {
            return this.getThumbBounds().height > 0;
        }
        return this.getThumbBounds().width > 0;
    }
    
    protected TrackListener createTrackListener() {
        return new MyTrackListener();
    }
    
    protected class OrientationChangeListener implements PropertyChangeListener
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            final Integer n = (Integer)propertyChangeEvent.getNewValue();
            if (TinyScrollBarUI.this.scrollbar.getComponentOrientation().isLeftToRight()) {
                if (TinyScrollBarUI.this.incrButton instanceof TinyScrollButton) {
                    ((TinyScrollButton)TinyScrollBarUI.this.incrButton).setDirection((n == 0) ? 3 : 5);
                }
                if (TinyScrollBarUI.this.decrButton instanceof TinyScrollButton) {
                    ((TinyScrollButton)TinyScrollBarUI.this.decrButton).setDirection((n == 0) ? 7 : 1);
                }
            }
            else {
                if (TinyScrollBarUI.this.incrButton instanceof TinyScrollButton) {
                    ((TinyScrollButton)TinyScrollBarUI.this.incrButton).setDirection((n == 0) ? 7 : 5);
                }
                if (TinyScrollBarUI.this.decrButton instanceof TinyScrollButton) {
                    ((TinyScrollButton)TinyScrollBarUI.this.decrButton).setDirection((n == 0) ? 3 : 1);
                }
            }
        }
    }
    
    protected class MyTrackListener extends TrackListener
    {
        public void mouseReleased(final MouseEvent mouseEvent) {
            super.mouseReleased(mouseEvent);
            TinyScrollBarUI.this.scrollbar.repaint();
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            super.mousePressed(mouseEvent);
            TinyScrollBarUI.this.scrollbar.repaint();
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            TinyScrollBarUI.this.isRollover = false;
            TinyScrollBarUI.this.wasRollover = false;
            if (TinyScrollBarUI.this.getThumbBounds().contains(mouseEvent.getPoint())) {
                TinyScrollBarUI.this.isRollover = true;
                TinyScrollBarUI.this.wasRollover = TinyScrollBarUI.this.isRollover;
                TinyScrollBarUI.this.scrollbar.repaint();
            }
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            TinyScrollBarUI.this.isRollover = false;
            if (TinyScrollBarUI.this.isRollover != TinyScrollBarUI.this.wasRollover) {
                TinyScrollBarUI.this.wasRollover = TinyScrollBarUI.this.isRollover;
                TinyScrollBarUI.this.scrollbar.repaint();
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            if (TinyScrollBarUI.this.getThumbBounds().contains(mouseEvent.getPoint())) {
                TinyScrollBarUI.this.isDragging = true;
            }
            super.mouseDragged(mouseEvent);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
            if (TinyScrollBarUI.this.getThumbBounds().contains(mouseEvent.getPoint())) {
                TinyScrollBarUI.this.isRollover = true;
                if (TinyScrollBarUI.this.isRollover != TinyScrollBarUI.this.wasRollover) {
                    TinyScrollBarUI.this.scrollbar.repaint();
                    TinyScrollBarUI.this.wasRollover = TinyScrollBarUI.this.isRollover;
                }
            }
            else {
                TinyScrollBarUI.this.isRollover = false;
                if (TinyScrollBarUI.this.isRollover != TinyScrollBarUI.this.wasRollover) {
                    TinyScrollBarUI.this.scrollbar.repaint();
                    TinyScrollBarUI.this.wasRollover = TinyScrollBarUI.this.isRollover;
                }
            }
        }
    }
}
