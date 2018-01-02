// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.beergamecomponents;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Dimension;
import com.masystem.beergame.ui.component.swing.ImageComponent;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;
import com.masystem.beergame.ui.scene.Node;

public class BitmapTextNode extends Node
{
    private boolean userSizeSet;
    
    public BitmapTextNode() {
        this((String)null);
    }
    
    private BitmapTextNode(final String s) {
        super(new BitmapTextComponent());
        this.setText(null);
    }
    
    @Override
    public final void setPreferredSize() {
        super.setPreferredSize();
        this.userSizeSet = false;
    }
    
    @Override
    public final void setSizeRelativeTo(final Node node, final float n, final float n2) {
        super.setSizeRelativeTo(node, n, n2);
        this.userSizeSet = isUserSizeSet(n, n2);
    }
    
    @Override
    public final void setSizeRelativeToParent(final float n, final float n2) {
        super.setSizeRelativeToParent(n, n2);
        this.userSizeSet = isUserSizeSet(n, n2);
    }
    
    private static boolean isUserSizeSet(final float n, final float n2) {
        return n != -2.0f || n2 != -2.0f;
    }
    
    public final void setCharsImage(final BufferedImage bufferedImage, final String s, final int n) {
        ((BitmapTextComponent)super.getComponent()).setCharsImage(bufferedImage, s, 32);
        this.setPreferredSize();
    }
    
    public final void setText(final String text) {
        ((BitmapTextComponent)super.getComponent()).setText(text);
        if (!this.userSizeSet) {
            super.setPreferredSize();
        }
    }
    
    @Override
    public final void setCacheEnabled(final boolean cacheEnabled) {
        if (!cacheEnabled || this.getNbrChildren() != 0) {
            super.setCacheEnabled(cacheEnabled);
        }
    }
    
    public static final class BitmapTextComponent extends ImageComponent
    {
        private static final long serialVersionUID = 1L;
        private BufferedImage charsImage;
        private String chars;
        private int charWidth;
        private String text;
        
        public final void setCharsImage(final BufferedImage charsImage, final String chars, final int charWidth) {
            this.charsImage = charsImage;
            this.chars = chars;
            this.charWidth = charWidth;
            this.setText(this.text);
        }
        
        public final void setText(String text) {
            if (text == null) {
                text = "";
            }
            this.text = text;
            if (this.charsImage != null) {
                this.setPreferredSize(new Dimension(this.charWidth * text.length(), this.charsImage.getHeight()));
            }
        }
        
        @Override
        public final void paint(final Graphics graphics) {
            final Graphics2D graphics2 = this.paintProperties.createGraphics(graphics);
            super.paint(graphics2);
            graphics2.dispose();
        }
        
        @Override
        protected final void paintComponent(final Graphics graphics) {
            final int charWidth = this.charWidth;
            final int height = this.charsImage.getHeight();
            for (int i = 0; i < this.text.length(); ++i) {
                final int index;
                if ((index = this.chars.indexOf(this.text.charAt(i))) != -1) {
                    final int n = i * this.charWidth;
                    final int n2 = index * this.charWidth;
                    graphics.drawImage(this.charsImage, n, 0, n + charWidth, height, n2, 0, n2 + charWidth, height, null);
                }
            }
        }
    }
}
