import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Com2meFWPP
{
    private Image pieceImage;
    private int[][] pieceMap;
    private int xRel;
    private int yRel;
    private int xPos;
    private int yPos;
    private int xSize;
    private int ySize;
    private int number;
    private int grid;
    private int xOffset;
    private int yOffset;
    private Component comp;
    private boolean moveable;
    private boolean visible;
    
    public Com2meFWPP(final Image pieceImage, final int[][] pieceMap, final int xRel, final int yRel, final int grid, final int number, final Component comp) {
        this.pieceImage = pieceImage;
        this.xRel = xRel;
        this.yRel = yRel;
        this.xSize = pieceMap[0].length * grid;
        this.ySize = pieceMap.length * grid;
        this.number = number;
        this.grid = grid;
        this.pieceMap = pieceMap;
        this.xPos = 0;
        this.yPos = 0;
        this.comp = comp;
        this.moveable = true;
        this.visible = false;
        final boolean b = false;
        this.yOffset = (b ? 1 : 0);
        this.xOffset = (b ? 1 : 0);
    }
    
    public void checkPosition() {
        if (Math.abs(this.xPos - this.xOffset - this.xRel) < 8 && Math.abs(this.yPos - this.yOffset - this.yRel) < 8) {
            this.xPos = this.xOffset + this.xRel;
            this.yPos = this.yOffset + this.yRel;
            this.moveable = false;
        }
    }
    
    public void correctPosition(final int n, final int n2) {
        if (!this.moveable) {
            return;
        }
        if (this.xPos + this.xSize > n) {
            this.xPos = n - this.xSize;
        }
        if (this.yPos + this.ySize > n2) {
            this.yPos = n2 - this.ySize;
        }
    }
    
    public Image getImage() {
        return this.pieceImage;
    }
    
    public int getNumber() {
        return this.number;
    }
    
    public int getXPos() {
        return this.xPos;
    }
    
    public int getXSize() {
        return this.xSize;
    }
    
    public int getYPos() {
        return this.yPos;
    }
    
    public int getYSize() {
        return this.ySize;
    }
    
    public boolean isInside(int n, int n2) {
        n -= this.xPos;
        n2 -= this.yPos;
        return n >= 0 && n2 >= 0 && n < this.xSize && n2 < this.ySize && this.pieceMap[n2 / this.grid][n / this.grid] != 0;
    }
    
    public boolean isMoveable() {
        return this.moveable;
    }
    
    public void moveTo(final int xPos, final int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public void moveTo(int n, int n2, final int n3, final int n4) {
        if (n < 0) {
            n = 0;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        if (n + this.xSize > n3) {
            n = n3 - this.xSize;
        }
        if (n2 + this.ySize > n4) {
            n2 = n4 - this.ySize;
        }
        this.moveTo(n, n2);
    }
    
    public void paint(final Graphics graphics) {
        if (this.visible) {
            graphics.drawImage(this.pieceImage, this.xPos, this.yPos, this.comp);
        }
    }
    
    public void setOffset(final int xOffset, final int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        if (!this.moveable) {
            this.xPos = this.xOffset + this.xRel;
            this.yPos = this.yOffset + this.yRel;
        }
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
}
