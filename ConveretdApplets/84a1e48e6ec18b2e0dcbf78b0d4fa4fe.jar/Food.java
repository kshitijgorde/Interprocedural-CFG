import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class Food extends Applet
{
    private int x;
    private int y;
    private int bonusX;
    private int bonusY;
    Color foodColor;
    
    public Food() {
    }
    
    public Food(final Graphics graphics, final BoardMap boardMap) {
        for (int i = 0; i < 10; ++i) {
            this.createFood(graphics, boardMap);
        }
    }
    
    public void createFood(final Graphics graphics, final BoardMap boardMap) {
        do {
            this.x = (int)(Math.random() * 76.0) + 2;
            this.y = (int)(Math.random() * 76.0) + 2;
        } while (boardMap.getFood(this.x, this.y) != 99);
        boardMap.putFood(this.x, this.y, (byte)70);
        graphics.setColor(this.foodColor = new Color(this.Randomize(255), this.Randomize(220), this.Randomize(220)));
        graphics.fillRect(this.x * 4 + 40, this.y * 4 + 40, 4, 4);
    }
    
    public void createBonus(final Graphics graphics, final BoardMap boardMap, final Image image) {
        boolean b;
        do {
            this.bonusX = (int)(Math.random() * 70.0) + 2;
            this.bonusY = (int)(Math.random() * 70.0) + 2;
            b = false;
            for (int i = 0; i < 5; ++i) {
                for (int j = 0; j < 6; ++j) {
                    if (boardMap.getFood(this.bonusX + j, this.bonusY + i) != 99) {
                        b = true;
                        break;
                    }
                }
                if (b) {
                    break;
                }
            }
        } while (b);
        for (int k = 0; k < 5; ++k) {
            for (int l = 0; l < 6; ++l) {
                boardMap.putFood(this.bonusX + l, this.bonusY + k, (byte)(k * 6 + l));
            }
        }
        graphics.drawImage(image, this.bonusX * 4 + 40, this.bonusY * 4 + 40, 24, 20, this);
    }
    
    public int getBonusX() {
        return this.bonusX;
    }
    
    public int getBonusY() {
        return this.bonusY;
    }
    
    private int Randomize(final int n) {
        return (int)(Math.random() * n);
    }
}
