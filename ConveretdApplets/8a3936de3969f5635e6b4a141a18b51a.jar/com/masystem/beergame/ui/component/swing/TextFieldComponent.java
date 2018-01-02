// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.component.swing;

import java.awt.Graphics2D;
import java.awt.Graphics;
import javax.swing.border.Border;
import java.awt.Color;
import com.masystem.beergame.ui.scene.PaintProperties;
import javax.swing.JTextField;

public class TextFieldComponent extends JTextField implements HasPaintProperties
{
    private static final long serialVersionUID = 1L;
    private PaintProperties paintProperties;
    
    public TextFieldComponent() {
        this.paintProperties = new PaintProperties();
        this.setBackground(null);
        this.setBorder(null);
        this.setOpaque(false);
    }
    
    public final void setTextColor(final Color foreground) {
        this.setForeground(foreground);
    }
    
    @Override
    public final void setPaintProperties(final PaintProperties paintProperties) {
        this.paintProperties = paintProperties;
    }
    
    @Override
    public void paint(final Graphics graphics) {
        final Graphics2D graphics2 = this.paintProperties.createGraphics(graphics);
        super.paint(graphics2);
        graphics2.dispose();
    }
}
