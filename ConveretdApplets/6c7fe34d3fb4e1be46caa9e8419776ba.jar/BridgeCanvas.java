import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Event;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class BridgeCanvas extends Canvas
{
    Dimension offDimension;
    Image offImage;
    Graphics offGraphics;
    int rowSize;
    int colSize;
    int topLeftOff;
    int boardSize;
    Point pt;
    Dimension minSize;
    
    public Dimension getSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public synchronized Dimension getMinimumSize() {
        return this.minSize;
    }
    
    public BridgeCanvas() {
        this.offGraphics = null;
        this.rowSize = 19;
        this.colSize = 19;
        this.topLeftOff = 10;
        this.boardSize = this.rowSize * 17;
        this.minSize = new Dimension(340, 340);
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        if (this.pt == null) {
            this.pt = new Point(x, y);
        }
        else {
            this.pt.x = x;
            this.pt.y = y;
        }
        final int n = (this.pt.y - this.topLeftOff) / this.rowSize;
        final int n2 = (this.pt.x - this.topLeftOff) / this.colSize;
        if (n >= 0 && n < 17 && n2 >= 0 && n2 < 17 && !Bridges.blueWins && !Bridges.redWins && Bridges.toggleBox(n, n2)) {
            this.repaint();
            if (!Bridges.redWins) {
                Bridges.findBestMove();
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.offGraphics == null || size.width != this.offDimension.width || size.height != this.offDimension.height) {
            this.offDimension = size;
            this.offImage = this.createImage(size.width, size.height);
            this.offGraphics = this.offImage.getGraphics();
        }
        this.offGraphics.setColor(Color.white);
        this.offGraphics.fillRect(0, 0, size.width - 1, size.height - 1);
        this.offGraphics.setColor(Color.black);
        this.offGraphics.drawRect(this.topLeftOff - 2, this.topLeftOff - 2, this.boardSize + 4, this.boardSize + 4);
        if (Bridges.board != null) {
            for (int i = 0; i < 17; ++i) {
                for (int j = 0; j < 17; ++j) {
                    if (Bridges.board[i][j] == 1) {
                        this.offGraphics.setColor(Color.blue);
                        this.offGraphics.fillRect(this.topLeftOff + j * this.colSize, this.topLeftOff + i * this.rowSize, this.colSize, this.rowSize);
                    }
                    else if (Bridges.board[i][j] == 2) {
                        this.offGraphics.setColor(Color.red);
                        this.offGraphics.fillRect(this.topLeftOff + j * this.colSize, this.topLeftOff + i * this.rowSize, this.colSize, this.rowSize);
                    }
                }
            }
            graphics.drawImage(this.offImage, 0, 0, this);
        }
    }
}
