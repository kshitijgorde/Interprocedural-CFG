// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.component.swing;

import javax.swing.ButtonModel;
import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import java.awt.Color;
import com.masystem.beergame.ui.graphics.StretchableImage;
import com.masystem.beergame.ui.scene.PaintProperties;
import javax.swing.JButton;

public final class StretchableButtonComponent extends JButton implements HasPaintProperties
{
    private static final long serialVersionUID = 1L;
    private PaintProperties paintProperties;
    private StretchableImage releasedImage;
    private StretchableImage pressedImage;
    private StretchableImage rolloverImage;
    private StretchableImage disabledImage;
    
    public StretchableButtonComponent() {
        this.paintProperties = new PaintProperties();
    }
    
    public StretchableButtonComponent(final StretchableImage stretchableImage, final StretchableImage stretchableImage2, final StretchableImage stretchableImage3, final StretchableImage stretchableImage4) {
        this(null, stretchableImage, stretchableImage2, stretchableImage3, stretchableImage4);
    }
    
    public StretchableButtonComponent(final String s, StretchableImage stretchableImage, final StretchableImage stretchableImage2, final StretchableImage stretchableImage3, final StretchableImage stretchableImage4) {
        super(s);
        this.paintProperties = new PaintProperties();
        this.setBackground(null);
        stretchableImage = stretchableImage;
        this.releasedImage = stretchableImage;
        stretchableImage = stretchableImage2;
        this.pressedImage = stretchableImage;
        stretchableImage = stretchableImage3;
        this.rolloverImage = stretchableImage;
        stretchableImage = stretchableImage4;
        this.disabledImage = stretchableImage;
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusable(false);
    }
    
    public final void setInsets(final int n, final int n2, final int n3, final int n4) {
        this.setBorder(new EmptyBorder(new Insets(n2, n, n4, n3)));
    }
    
    public final void setTextColor(final Color foreground) {
        this.setForeground(foreground);
    }
    
    public final StretchableImage getReleasedImage() {
        return this.releasedImage;
    }
    
    @Override
    public final void setPaintProperties(final PaintProperties paintProperties) {
        this.paintProperties = paintProperties;
    }
    
    @Override
    public final void paint(final Graphics graphics) {
        final Graphics2D graphics2 = this.paintProperties.createGraphics(graphics);
        super.paint(graphics2);
        graphics2.dispose();
    }
    
    @Override
    protected final void paintComponent(final Graphics graphics) {
        final ButtonModel model;
        if (!(model = this.getModel()).isEnabled() && this.disabledImage != null) {
            this.disabledImage.draw$2f958723(graphics, this.getWidth(), this.getHeight());
        }
        else if (model.isPressed() && this.pressedImage != null) {
            this.pressedImage.draw$2f958723(graphics, this.getWidth(), this.getHeight());
        }
        else if (model.isRollover() && this.rolloverImage != null) {
            this.rolloverImage.draw$2f958723(graphics, this.getWidth(), this.getHeight());
        }
        else if (this.releasedImage != null) {
            this.releasedImage.draw$2f958723(graphics, this.getWidth(), this.getHeight());
        }
        super.paintComponent(graphics);
    }
}
