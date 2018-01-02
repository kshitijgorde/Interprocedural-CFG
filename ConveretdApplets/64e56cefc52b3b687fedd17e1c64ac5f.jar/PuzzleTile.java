import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class PuzzleTile extends Canvas
{
    int tileID;
    int row;
    int col;
    int xCnt;
    int yCnt;
    Image i;
    Color bc;
    Object owner;
    
    public PuzzleTile(final Object owner, final int tileID, final int row, final int col, final Image i, final int xCnt, final int yCnt, final Color bc) {
        this.tileID = tileID;
        this.row = row;
        this.col = col;
        this.i = i;
        this.repaint();
        this.xCnt = xCnt;
        this.yCnt = yCnt;
        this.bc = bc;
        this.owner = owner;
    }
    
    public void destroy() {
    }
    
    public int getTileID() {
        return this.tileID;
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getBounds().width;
        final int height = this.getBounds().height;
        if (this.i != null) {
            final int n = this.i.getWidth(this) / this.xCnt;
            final int n2 = this.i.getHeight(this) / this.yCnt;
            final int n3 = this.col * n;
            final int n4 = this.row * n2;
            final int n5 = n3 + n;
            final int n6 = n4 + n2;
            graphics.setColor(this.bc);
            graphics.fillRoundRect(0, 0, width, height, 10, 10);
            graphics.setColor(Color.black);
            graphics.drawRoundRect(0, 0, width, height, 10, 10);
            graphics.setClip(3, 3, width - 6, height - 6);
            graphics.drawImage(this.i, 0, 0, width, height, n3, n4, n5, n6, this);
        }
        else {
            graphics.drawString("NO IMG", 2, 2);
        }
    }
    
    public void setTileBG(final Color bc) {
        if (bc != this.bc) {
            this.bc = bc;
            this.repaint();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
