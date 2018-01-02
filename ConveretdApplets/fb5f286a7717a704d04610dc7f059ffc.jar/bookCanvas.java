import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class bookCanvas extends Canvas
{
    public void paint(final Graphics graphics) {
        final int h = ColoringBook.h;
        final int w = ColoringBook.w;
        final Graphics graphics2 = ColoringBook.bufImage.getGraphics();
        if (ColoringBook.started) {
            graphics2.setColor(Color.white);
            graphics2.fillRect(0, 0, w, h);
            graphics2.drawImage(ColoringBook.picture, 0, 0, w, h, this);
            ColoringBook.started = false;
        }
        else {
            graphics2.setColor(ColoringBook.col);
            if (ColoringBook.validP & !ColoringBook.tracing) {
                graphics2.fillOval(ColoringBook.mousex - ColoringBook.penSize / 2, ColoringBook.mousey - ColoringBook.penSize / 2, ColoringBook.penSize, ColoringBook.penSize);
            }
            if (ColoringBook.tracing) {
                ColoringBook.fillBack(graphics2);
            }
            graphics2.drawImage(ColoringBook.picture, 0, 0, w, h, this);
            ColoringBook.validP = false;
        }
        graphics2.setColor(Color.black);
        graphics2.drawString("JAKES COLORING BOOK 2.0", 1, h - 15);
        graphics2.drawString("(Copyright 1999 by Jake W. Holmes)", 1, h - 5);
        graphics.drawImage(ColoringBook.bufImage, 0, 0, null);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
