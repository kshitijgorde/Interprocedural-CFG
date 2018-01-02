// 
// Decompiled by Procyon v0.5.30
// 

package com.grooveshark.ui.component;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.border.Border;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JTextField;

public class IconTextField extends JTextField
{
    private static final long serialVersionUID = -3506600425158379308L;
    private static final int SPACING = 2;
    private int imageWidth;
    private int imageHeight;
    private final Image image;
    private final int positionX;
    
    public IconTextField(final ImageIcon icon) throws IOException {
        this.image = icon.getImage();
        this.imageHeight = icon.getIconHeight();
        this.imageWidth = icon.getIconWidth();
        final Border border = UIManager.getBorder("TextField.border");
        this.positionX = border.getBorderInsets(this).left;
        this.setMargin(new Insets(0, this.positionX + this.imageWidth + 2, 0, 0));
    }
    
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final int positionY = (this.getHeight() - this.imageHeight) / 2;
        g.drawImage(this.image, this.positionX + 2, positionY, this);
    }
}
