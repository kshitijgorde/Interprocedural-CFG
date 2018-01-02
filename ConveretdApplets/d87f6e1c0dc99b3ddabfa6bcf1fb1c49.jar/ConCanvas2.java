import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class ConCanvas2 extends Canvas
{
    int width;
    int height;
    Concentration2ech c;
    Graphics bufferG;
    Image bufferI;
    Image background;
    Image facedown;
    Image mask;
    
    ConCanvas2(final Concentration2ech c, final int width, final int height, final Image background, final Image facedown, final Image mask) {
        this.c = c;
        this.width = width;
        this.height = height;
        this.background = background;
        this.facedown = facedown;
        this.mask = mask;
    }
    
    public Dimension mininmumSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.bufferI == null) {
            this.bufferI = this.createImage(this.width, this.height);
            this.bufferG = this.bufferI.getGraphics();
        }
        this.bufferG.fillRect(0, 0, this.width, this.height);
        if (this.background != null) {
            this.bufferG.drawImage(this.background, 0, 0, this.c);
        }
        final int rows = this.c.rows;
        final int columns = this.c.columns;
        final int[] card_order = this.c.card_order;
        final boolean[] card_removed = this.c.card_removed;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                if (!card_removed[i * columns + j]) {
                    if (this.c.first_picked != i * columns + j && this.c.second_picked != i * columns + j) {
                        if (this.facedown != null) {
                            this.bufferG.drawImage(this.facedown, j * this.facedown.getWidth(null), i * this.facedown.getHeight(null), null);
                        }
                    }
                    else {
                        final Image image = this.c.cards[card_order[i * columns + j] % (card_order.length / 2)];
                        if (image != null) {
                            this.bufferG.drawImage(image, j * image.getWidth(null), i * image.getHeight(null), null);
                        }
                    }
                    if (this.mask != null) {
                        this.bufferG.drawImage(this.mask, j * this.mask.getWidth(null), i * this.mask.getHeight(null), null);
                    }
                }
            }
        }
        graphics.drawImage(this.bufferI, 0, 0, null);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.c.cardPicked(n, n2);
        return true;
    }
}
