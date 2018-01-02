// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import javax.swing.LookAndFeel;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Image;
import de.muntjak.tinylookandfeel.controlpanel.ColorRoutines;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Graphics;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class TinyProgressBarUI extends BasicProgressBarUI
{
    static HashMap cache;
    private static final Dimension PREFERRED_YQ_HORIZONTAL;
    private static final Dimension PREFERRED_YQ_VERTICAL;
    private static final Dimension PREFERRED_99_HORIZONTAL;
    private static final Dimension PREFERRED_99_VERTICAL;
    
    protected Dimension getPreferredInnerHorizontal() {
        if (Theme.derivedStyle[Theme.style] == 2) {
            return TinyProgressBarUI.PREFERRED_YQ_HORIZONTAL;
        }
        return TinyProgressBarUI.PREFERRED_99_HORIZONTAL;
    }
    
    protected Dimension getPreferredInnerVertical() {
        if (Theme.derivedStyle[Theme.style] == 2) {
            return TinyProgressBarUI.PREFERRED_YQ_VERTICAL;
        }
        return TinyProgressBarUI.PREFERRED_99_VERTICAL;
    }
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyProgressBarUI();
    }
    
    protected void paintDeterminate(final Graphics graphics, final JComponent component) {
        final Insets insets = this.progressBar.getInsets();
        final int n = this.progressBar.getWidth() - (insets.right + insets.left);
        final int n2 = this.progressBar.getHeight() - (insets.top + insets.bottom);
        if (this.progressBar.getOrientation() == 0) {
            final int amountFull = this.getAmountFull(insets, n, n2);
            switch (Theme.derivedStyle[Theme.style]) {
                case 0: {
                    this.drawTinyHorzProgress(graphics, insets.left, insets.top, n, n2, amountFull);
                    break;
                }
                case 1: {
                    this.drawWinHorzProgress(graphics, insets.left, insets.top, n, n2, amountFull);
                    break;
                }
                case 2: {
                    this.drawXpHorzProgress(graphics, insets.left, insets.top, n, n2, amountFull);
                    break;
                }
            }
            if (this.progressBar.isStringPainted()) {
                graphics.setFont(component.getFont());
                this.paintString(graphics, insets.left, insets.top, n, n2, amountFull, insets);
            }
        }
        else {
            final int amountFull2 = this.getAmountFull(insets, n, n2);
            switch (Theme.derivedStyle[Theme.style]) {
                case 0: {
                    this.drawTinyVertProgress(graphics, insets.left, insets.top, n, n2, amountFull2);
                    break;
                }
                case 1: {
                    this.drawWinVertProgress(graphics, insets.left, insets.top, n, n2, amountFull2);
                    break;
                }
                case 2: {
                    this.drawXpVertProgress(graphics, insets.left, insets.top, n, n2, amountFull2);
                    break;
                }
            }
            if (this.progressBar.isStringPainted()) {
                graphics.setFont(component.getFont());
                this.paintString(graphics, insets.left, insets.top, n, n2, amountFull2, insets);
            }
        }
    }
    
    private void drawTinyHorzProgress(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
    }
    
    private void drawWinHorzProgress(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.translate(n, n2);
        graphics.setColor(Theme.progressTrackColor[Theme.style].getColor());
        graphics.fillRect(1, 1, n3 - 1, n4 - 1);
        graphics.setColor(Theme.progressColor[Theme.style].getColor());
        for (int i = 0; i < n5; i += 8) {
            if (i + 6 > n3) {
                graphics.fillRect(i, 0, n3 - i, n4);
            }
            else {
                graphics.fillRect(i, 0, 6, n4);
            }
        }
        graphics.translate(-n, -n2);
    }
    
    private void drawXpHorzProgress(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.translate(n, n2);
        if (!this.progressBar.isOpaque()) {
            graphics.setColor(this.progressBar.getBackground());
            graphics.fillRect(0, 0, n3, n4);
        }
        final ProgressKey progressKey = new ProgressKey(this.progressBar.getForeground(), true, n4);
        Object value = TinyProgressBarUI.cache.get(progressKey);
        if (value == null) {
            final BufferedImage bufferedImage = new BufferedImage(6, n4, 2);
            final Graphics graphics2 = bufferedImage.getGraphics();
            final Color foreground = this.progressBar.getForeground();
            final Color lighten = ColorRoutines.lighten(foreground, 15);
            final Color lighten2 = ColorRoutines.lighten(foreground, 35);
            graphics2.setColor(ColorRoutines.lighten(foreground, 60));
            graphics2.drawLine(0, 0, 5, 0);
            graphics2.drawLine(0, n4 - 1, 5, n4 - 1);
            graphics2.setColor(lighten2);
            graphics2.drawLine(0, 1, 5, 1);
            graphics2.drawLine(0, n4 - 2, 5, n4 - 2);
            graphics2.setColor(lighten);
            graphics2.drawLine(0, 2, 5, 2);
            graphics2.drawLine(0, n4 - 3, 5, n4 - 3);
            graphics2.setColor(foreground);
            graphics2.fillRect(0, 3, 6, n4 - 6);
            graphics2.dispose();
            TinyProgressBarUI.cache.put(progressKey, bufferedImage);
            value = bufferedImage;
        }
        for (int i = 0; i < n5; i += 8) {
            if (i + 6 > n3) {
                graphics.drawImage((Image)value, i, 0, n3 - i, n4, this.progressBar);
            }
            else {
                graphics.drawImage((Image)value, i, 0, this.progressBar);
            }
        }
        graphics.translate(-n, -n2);
    }
    
    private void drawTinyVertProgress(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
    }
    
    private void drawWinVertProgress(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.translate(n, n2);
        graphics.setColor(Theme.progressTrackColor[Theme.style].getColor());
        graphics.fillRect(1, 1, n3 - 1, n4 - 1);
        graphics.setColor(Theme.progressColor[Theme.style].getColor());
        for (int i = 0; i < n5; i += 8) {
            if (i + 6 > n4) {
                graphics.fillRect(0, 0, n3, n4 - i);
            }
            else {
                graphics.fillRect(0, n4 - i - 6, n3, 6);
            }
        }
        graphics.translate(-n, -n2);
    }
    
    private void drawXpVertProgress(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        graphics.translate(n, n2);
        if (!this.progressBar.isOpaque()) {
            graphics.setColor(this.progressBar.getBackground());
            graphics.fillRect(0, 0, n3, n4);
        }
        final ProgressKey progressKey = new ProgressKey(this.progressBar.getForeground(), false, n3);
        Object value = TinyProgressBarUI.cache.get(progressKey);
        if (value == null) {
            final BufferedImage bufferedImage = new BufferedImage(n3, 6, 2);
            final Graphics graphics2 = bufferedImage.getGraphics();
            final Color foreground = this.progressBar.getForeground();
            final Color lighten = ColorRoutines.lighten(foreground, 15);
            final Color lighten2 = ColorRoutines.lighten(foreground, 35);
            graphics2.setColor(ColorRoutines.lighten(foreground, 60));
            graphics2.drawLine(0, 0, 0, 5);
            graphics2.drawLine(n3 - 1, 0, n3 - 1, 5);
            graphics2.setColor(lighten2);
            graphics2.drawLine(1, 0, 1, 5);
            graphics2.drawLine(n3 - 2, 0, n3 - 2, 5);
            graphics2.setColor(lighten);
            graphics2.drawLine(2, 0, 2, 5);
            graphics2.drawLine(n3 - 3, 0, n3 - 3, 5);
            graphics2.setColor(foreground);
            graphics2.fillRect(3, 0, n3 - 6, 6);
            graphics2.dispose();
            TinyProgressBarUI.cache.put(progressKey, bufferedImage);
            value = bufferedImage;
        }
        for (int i = 0; i < n5; i += 8) {
            if (i + 6 > n4) {
                graphics.drawImage((Image)value, 0, 0, n3, n4 - i, this.progressBar);
            }
            else {
                graphics.drawImage((Image)value, 0, n4 - i - 6, this.progressBar);
            }
        }
        graphics.translate(-n, -n2);
    }
    
    protected void paintIndeterminate(final Graphics graphics, final JComponent component) {
        final Insets insets = this.progressBar.getInsets();
        final int n = this.progressBar.getWidth() - (insets.right + insets.left);
        final int n2 = this.progressBar.getHeight() - (insets.top + insets.bottom);
        Rectangle box = new Rectangle();
        try {
            box = this.getBox(box);
        }
        catch (NullPointerException ex) {}
        if (this.progressBar.getOrientation() == 0) {
            switch (Theme.derivedStyle[Theme.style]) {
                case 0: {
                    this.drawTinyHorzProgress(graphics, insets.left, insets.top, n, n2, box);
                    break;
                }
                case 1: {
                    this.drawWinHorzProgress(graphics, insets.left, insets.top, n, n2, box);
                    break;
                }
                case 2: {
                    this.drawXpHorzProgress(graphics, insets.left, insets.top, n, n2, box);
                    break;
                }
            }
        }
        else {
            switch (Theme.derivedStyle[Theme.style]) {
                case 0: {
                    this.drawTinyVertProgress(graphics, insets.left, insets.top, n, n2, box);
                    break;
                }
                case 1: {
                    this.drawWinVertProgress(graphics, insets.left, insets.top, n, n2, box);
                    break;
                }
                case 2: {
                    this.drawXpVertProgress(graphics, insets.left, insets.top, n, n2, box);
                    break;
                }
            }
        }
        if (this.progressBar.isStringPainted()) {
            if (this.progressBar.getOrientation() == 0) {
                this.paintString(graphics, insets.left, insets.top, n, n2, box.x, box.width, insets);
            }
            else {
                this.paintString(graphics, insets.left, insets.top, n, n2, box.y, box.height, insets);
            }
        }
    }
    
    private void paintString(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final Insets insets) {
        if (!(graphics instanceof Graphics2D)) {
            return;
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final String string = this.progressBar.getString();
        graphics2D.setFont(this.progressBar.getFont());
        final Point stringPlacement = this.getStringPlacement(graphics2D, string, n, n2, n3, n4);
        final Rectangle clipBounds = graphics2D.getClipBounds();
        if (this.progressBar.getOrientation() == 0) {
            graphics2D.setColor(this.getSelectionBackground());
            graphics2D.drawString(string, stringPlacement.x, stringPlacement.y);
            graphics2D.setColor(this.getSelectionForeground());
            graphics2D.clipRect(n5, n2, n6, n4);
            graphics.drawString(string, stringPlacement.x, stringPlacement.y);
        }
        else {
            graphics2D.setColor(this.getSelectionBackground());
            graphics2D.setFont(this.progressBar.getFont().deriveFont(AffineTransform.getRotateInstance(1.5707963267948966)));
            final Point stringPlacement2 = this.getStringPlacement(graphics2D, string, n, n2, n3, n4);
            graphics2D.drawString(string, stringPlacement2.x, stringPlacement2.y);
            graphics2D.setColor(this.getSelectionForeground());
            graphics2D.clipRect(n, n5, n3, n6);
            graphics2D.drawString(string, stringPlacement2.x, stringPlacement2.y);
        }
        graphics2D.setClip(clipBounds);
    }
    
    protected void paintString(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5, final Insets insets) {
        Rectangle box = new Rectangle();
        try {
            box = this.getBox(box);
        }
        catch (NullPointerException ex) {}
        if (this.progressBar.getOrientation() == 0) {
            if (this.progressBar.getComponentOrientation().isLeftToRight()) {
                if (this.progressBar.isIndeterminate()) {
                    this.paintString(graphics, n, n2, n3, n4, box.x, box.width, insets);
                }
                else {
                    this.paintString(graphics, n, n2, n3, n4, n, n5, insets);
                }
            }
            else {
                this.paintString(graphics, n, n2, n3, n4, n + n3 - n5, n5, insets);
            }
        }
        else if (this.progressBar.isIndeterminate()) {
            this.paintString(graphics, n, n2, n3, n4, box.y, box.height, insets);
        }
        else {
            this.paintString(graphics, n, n2, n3, n4, n2 + n4 - n5, n5, insets);
        }
    }
    
    private void drawTinyHorzProgress(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Rectangle rectangle) {
    }
    
    private void drawWinHorzProgress(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Rectangle rectangle) {
        graphics.setColor(Theme.progressTrackColor[Theme.style].getColor());
        graphics.fillRect(n + 1, n2 + 1, n3 - 1, n4 - 1);
        graphics.translate(rectangle.x, rectangle.y);
        graphics.setColor(Theme.progressColor[Theme.style].getColor());
        for (int n5 = 0; n5 + 6 < rectangle.width; n5 += 8) {
            graphics.fillRect(n5, 0, 6, n4);
        }
        graphics.translate(-rectangle.x, -rectangle.y);
    }
    
    private void drawXpHorzProgress(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Rectangle rectangle) {
        if (!this.progressBar.isOpaque()) {
            graphics.setColor(this.progressBar.getBackground());
            graphics.fillRect(n, n2, n3, n4);
        }
        graphics.translate(rectangle.x, rectangle.y);
        final ProgressKey progressKey = new ProgressKey(this.progressBar.getForeground(), true, n4);
        Object value = TinyProgressBarUI.cache.get(progressKey);
        if (value == null) {
            final BufferedImage bufferedImage = new BufferedImage(6, n4, 2);
            final Graphics graphics2 = bufferedImage.getGraphics();
            final Color foreground = this.progressBar.getForeground();
            final Color lighten = ColorRoutines.lighten(foreground, 15);
            final Color lighten2 = ColorRoutines.lighten(foreground, 35);
            graphics2.setColor(ColorRoutines.lighten(foreground, 60));
            graphics2.drawLine(0, 0, 5, 0);
            graphics2.drawLine(0, n4 - 1, 5, n4 - 1);
            graphics2.setColor(lighten2);
            graphics2.drawLine(0, 1, 5, 1);
            graphics2.drawLine(0, n4 - 2, 5, n4 - 2);
            graphics2.setColor(lighten);
            graphics2.drawLine(0, 2, 5, 2);
            graphics2.drawLine(0, n4 - 3, 5, n4 - 3);
            graphics2.setColor(foreground);
            graphics2.fillRect(0, 3, 6, n4 - 6);
            graphics2.dispose();
            TinyProgressBarUI.cache.put(progressKey, bufferedImage);
            value = bufferedImage;
        }
        for (int n5 = 0; n5 + 6 < rectangle.width; n5 += 8) {
            graphics.drawImage((Image)value, n5, 0, this.progressBar);
        }
        graphics.translate(-rectangle.x, -rectangle.y);
    }
    
    private void drawTinyVertProgress(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Rectangle rectangle) {
    }
    
    private void drawWinVertProgress(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Rectangle rectangle) {
        graphics.setColor(Theme.progressTrackColor[Theme.style].getColor());
        graphics.fillRect(n + 1, n2 + 1, n3 - 1, n4 - 1);
        graphics.translate(rectangle.x, rectangle.y);
        graphics.setColor(Theme.progressColor[Theme.style].getColor());
        for (int n5 = 0; n5 + 6 < rectangle.height; n5 += 8) {
            graphics.fillRect(0, n5, n3, 6);
        }
        graphics.translate(-rectangle.x, -rectangle.y);
    }
    
    private void drawXpVertProgress(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Rectangle rectangle) {
        if (!this.progressBar.isOpaque()) {
            graphics.setColor(this.progressBar.getBackground());
            graphics.fillRect(n, n2, n3, n4);
        }
        graphics.translate(rectangle.x, rectangle.y);
        final ProgressKey progressKey = new ProgressKey(this.progressBar.getForeground(), false, n3);
        Object value = TinyProgressBarUI.cache.get(progressKey);
        if (value == null) {
            final BufferedImage bufferedImage = new BufferedImage(n3, 6, 2);
            final Graphics graphics2 = bufferedImage.getGraphics();
            final Color foreground = this.progressBar.getForeground();
            final Color lighten = ColorRoutines.lighten(foreground, 15);
            final Color lighten2 = ColorRoutines.lighten(foreground, 35);
            graphics2.setColor(ColorRoutines.lighten(foreground, 60));
            graphics2.drawLine(0, 0, 0, 5);
            graphics2.drawLine(n3 - 1, 0, n3 - 1, 5);
            graphics2.setColor(lighten2);
            graphics2.drawLine(1, 0, 1, 5);
            graphics2.drawLine(n3 - 2, 0, n3 - 2, 5);
            graphics2.setColor(lighten);
            graphics2.drawLine(2, 0, 2, 5);
            graphics2.drawLine(n3 - 3, 0, n3 - 3, 5);
            graphics2.setColor(foreground);
            graphics2.fillRect(3, 0, n3 - 6, 6);
            graphics2.dispose();
            TinyProgressBarUI.cache.put(progressKey, bufferedImage);
            value = bufferedImage;
        }
        for (int n5 = 0; n5 + 6 < rectangle.height; n5 += 8) {
            graphics.drawImage((Image)value, 0, n5, this.progressBar);
        }
        graphics.translate(-rectangle.x, -rectangle.y);
    }
    
    protected Color getSelectionForeground() {
        return Theme.progressSelectForeColor[Theme.style].getColor();
    }
    
    protected Color getSelectionBackground() {
        return Theme.progressSelectBackColor[Theme.style].getColor();
    }
    
    protected void installDefaults() {
        LookAndFeel.installBorder(this.progressBar, "ProgressBar.border");
        LookAndFeel.installColorsAndFont(this.progressBar, "ProgressBar.background", "ProgressBar.foreground", "ProgressBar.font");
    }
    
    static {
        TinyProgressBarUI.cache = new HashMap();
        PREFERRED_YQ_HORIZONTAL = new Dimension(146, 7);
        PREFERRED_YQ_VERTICAL = new Dimension(7, 146);
        PREFERRED_99_HORIZONTAL = new Dimension(146, 6);
        PREFERRED_99_VERTICAL = new Dimension(6, 146);
    }
    
    static class ProgressKey
    {
        private Color c;
        private boolean horizontal;
        private int size;
        
        ProgressKey(final Color c, final boolean horizontal, final int size) {
            this.c = c;
            this.horizontal = horizontal;
            this.size = size;
        }
        
        public boolean equals(final Object o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof ProgressKey)) {
                return false;
            }
            final ProgressKey progressKey = (ProgressKey)o;
            return this.size == progressKey.size && this.horizontal == progressKey.horizontal && this.c.equals(progressKey.c);
        }
        
        public int hashCode() {
            return this.c.hashCode() * (this.horizontal ? 1 : 2) * this.size;
        }
    }
}
