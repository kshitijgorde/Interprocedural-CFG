import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class DiceCanvasGIF extends Canvas
{
    int[] dieValue;
    int ndice;
    Font f;
    int sumX;
    DiceGIF applet;
    Image[] dieGIF;
    int imageWidth;
    int imageHeight;
    Graphics offscreenGraphics;
    Image offscreenImage;
    
    public DiceCanvasGIF(final int[] dieValue, final int ndice, final DiceGIF applet, final Image[] dieGIF) {
        this.f = new Font("TimesRoman", 1, 48);
        this.dieGIF = new Image[6];
        this.applet = applet;
        this.ndice = ndice;
        this.dieValue = dieValue;
        this.dieGIF = dieGIF;
    }
    
    public void clearCanvas(final int ndice) {
        this.ndice = ndice;
        this.dieValue = new int[ndice];
        this.repaint();
    }
    
    @Override
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        for (int n = 0; n < 12 & n < this.ndice; ++n) {
            final int n2 = n / 4 * size.height / 3;
            final int n3 = n % 4 * size.width / 4;
            if (this.dieValue[n] != 0) {
                graphics.drawImage(this.dieGIF[this.dieValue[n] - 1], 7 + n3, 5 + n2, Color.black, this.applet);
            }
        }
        graphics.setFont(this.f);
        graphics.setColor(Color.black);
        graphics.drawString("X = " + this.sumX, size.width / 4, size.height * 4 / 5);
    }
    
    public void update(final int[] dieValue, final int sumX) {
        this.dieValue = dieValue;
        this.sumX = sumX;
        this.repaint();
    }
}
