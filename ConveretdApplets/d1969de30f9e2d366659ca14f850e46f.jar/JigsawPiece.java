import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.util.Random;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class JigsawPiece extends Canvas
{
    Image img;
    int xPos;
    int yPos;
    int basePosX;
    int basePosY;
    int jPosX;
    int jPosY;
    int sizeW;
    int sizeH;
    int overlap;
    int orient;
    Random rNum;
    int[] fc;
    
    JigsawPiece(final Image image, final int overlap, final int n, final int n2, final int sizeW, final int sizeH, final int jPosX, final int jPosY, final int xPos, final int yPos, int n3, int n4, int n5, int n6, final int[] array, final int orient) {
        this.rNum = new Random();
        this.jPosX = jPosX;
        this.basePosX = n + jPosX;
        this.jPosY = jPosY;
        this.basePosY = n2 + jPosY;
        this.xPos = xPos;
        this.yPos = yPos;
        (this.fc = new int[4])[0] = n3;
        this.fc[1] = n4;
        this.fc[2] = n5;
        this.fc[3] = n6;
        this.orient = orient;
        if (n3 > 0) {
            if (n4 != 0) {
                if (n4 > 0) {
                    n4 += overlap - 3;
                }
                else {
                    n4 -= overlap - 3;
                }
            }
            if (n6 != 0) {
                if (n6 > 0) {
                    n6 += overlap - 3;
                }
                else {
                    n6 -= overlap - 3;
                }
            }
        }
        if (n6 > 0) {
            if (n3 != 0) {
                if (n3 > 0) {
                    n3 += overlap - 3;
                }
                else {
                    n3 -= overlap - 3;
                }
            }
            if (n5 != 0) {
                if (n5 > 0) {
                    n5 += overlap - 3;
                }
                else {
                    n5 -= overlap - 3;
                }
            }
        }
        this.img = this.createImage(new FilteredImageSource(this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n, n2, sizeW, sizeH))).getSource(), new JigsawCutter(0, false, this.sizeW = sizeW, this.sizeH = sizeH, this.overlap = overlap, n3, n4, n5, n6, array)));
        this.setVisible(false);
    }
    
    public int getBaseX() {
        return this.basePosX;
    }
    
    public int getBaseY() {
        return this.basePosY;
    }
    
    public int getFace(final int n) {
        return this.fc[n];
    }
    
    public int getH() {
        return this.sizeH;
    }
    
    public int getOrientation() {
        return this.orient;
    }
    
    public Image getPiece() {
        return this.img;
    }
    
    public int getW() {
        return this.sizeW;
    }
    
    public int getX() {
        return this.xPos;
    }
    
    public int getY() {
        return this.yPos;
    }
    
    public boolean isOver(final int n, final int n2) {
        return n >= this.xPos && n <= this.xPos + this.sizeW && n2 >= this.yPos && n2 <= this.yPos + this.sizeH;
    }
    
    public void setOrientation(final int n) {
        if (n == 0) {
            this.orient = 0;
        }
        else {
            ++this.orient;
            if (this.orient > 3) {
                this.orient = 0;
            }
        }
    }
    
    public void setPosition(final int xPos, final int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
