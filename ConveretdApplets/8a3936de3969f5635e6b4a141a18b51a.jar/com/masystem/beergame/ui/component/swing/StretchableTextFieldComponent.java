// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.component.swing;

import java.awt.Graphics;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;
import com.masystem.beergame.ui.graphics.StretchableImage;

public class StretchableTextFieldComponent extends TextFieldComponent implements HasPaintProperties
{
    private static final long serialVersionUID = 1L;
    private StretchableImage stretchableImage;
    
    public StretchableTextFieldComponent() {
    }
    
    public StretchableTextFieldComponent(final StretchableImage stretchableImage) {
        this.stretchableImage = stretchableImage;
        this.setOpaque(stretchableImage != null && !stretchableImage.getSourceImage().getColorModel().hasAlpha());
    }
    
    public final void setInsets(final int n, final int n2, final int n3, final int n4) {
        this.setBorder(new EmptyBorder(new Insets(n2, n, n4, n3)));
    }
    
    @Override
    protected void paintComponent(final Graphics graphics) {
        if (this.stretchableImage != null) {
            this.stretchableImage.draw$2f958723(graphics, this.getWidth(), this.getHeight());
        }
        super.paintComponent(graphics);
    }
}
