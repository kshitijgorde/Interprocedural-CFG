import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Icon
{
    private Image img;
    private int icon;
    private int x;
    private int y;
    
    public Icon(final Image img, final int iconID, final int x, final int y) {
        this.img = img;
        this.icon = iconID;
        this.x = x;
        this.y = y;
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.img, this.x, this.y, this.x + 15, this.y + 15, 15 * this.icon, 0, 15 + 15 * this.icon, 15, null);
    }
    
    public int detectCollision(final int xHead, final int yHead) {
        if (this.icon == 1) {
            if (xHead < this.x + 15 && xHead + 15 > this.x && yHead < this.y + 11 && yHead + 11 > this.y) {
                return this.icon;
            }
            return 0;
        }
        else if (this.icon == 2) {
            if (xHead < this.x + 11 && xHead + 11 > this.x && yHead < this.y + 15 && yHead + 15 > this.y) {
                return this.icon;
            }
            return 0;
        }
        else if (this.icon == 3) {
            if (xHead < this.x + 11 && xHead + 11 > this.x && yHead < this.y + 11 && yHead + 11 > this.y) {
                return this.icon;
            }
            return 0;
        }
        else {
            if (xHead < this.x + 15 && xHead + 15 > this.x && yHead < this.y + 15 && yHead + 15 > this.y) {
                return this.icon;
            }
            return 0;
        }
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getID() {
        return this.icon;
    }
}
