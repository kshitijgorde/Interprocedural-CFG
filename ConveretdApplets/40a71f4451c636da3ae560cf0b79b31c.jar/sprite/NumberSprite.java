// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;

public class NumberSprite extends AnimateSprite
{
    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    int \u011c;
    int \u011d;
    int \u011e;
    int \u011f;
    boolean m_bDollar;
    boolean \u0120;
    
    public void setNumber(final int \u011d) {
        this.addToBounding();
        this.\u011c = \u011d;
        this.\u011c();
        this.addToBounding();
    }
    
    public int getNumber() {
        return this.\u011c;
    }
    
    private void \u011c() {
        this.\u011d = 2;
        int \u011d = this.\u011c;
        if (\u011d < 0) {
            \u011d *= -1;
        }
        for (int i = \u011d / 10; i > 0; i /= 10, this.\u011d += 2) {}
        if (this.\u011d < this.\u011e * 2) {
            this.\u011d = this.\u011e * 2;
        }
        if (this.m_bDollar) {
            this.\u011d += (this.\u011d - 6) / 6;
            this.\u011d += 2;
            if (this.\u011c % 100 != 0) {
                ++this.\u011d;
            }
            else if (this.\u011c != 0) {
                this.\u011d -= 4;
            }
        }
        else if (!this.\u0120) {
            this.\u011d += (this.\u011d - 2) / 6;
        }
        if (this.\u011c < 0 && !this.\u0120) {
            this.\u011d += 2;
        }
    }
    
    public void drawSprite(final Graphics graphics) {
        if (super.m_image == null || !super.m_bVisible) {
            return;
        }
        final int ny = super.m_nY;
        int \u011d = this.\u011c;
        if (\u011d < 0) {
            \u011d *= -1;
        }
        final int numberWidth = this.getNumberWidth();
        int nx;
        if (this.\u011f == 1) {
            nx = super.m_nX + numberWidth / 2;
        }
        else if (this.\u011f == 2) {
            nx = super.m_nX;
        }
        else {
            if (this.\u011f != 0) {
                return;
            }
            nx = super.m_nX + numberWidth;
        }
        int n = 0;
        if (this.m_bDollar && \u011d >= 100 && \u011d % 100 == 0) {
            \u011d /= 100;
            n = 3;
        }
        Graphics graphics2;
        do {
            final int n2 = \u011d % 10;
            \u011d /= 10;
            graphics2 = graphics.create();
            nx -= super.m_nWidth;
            graphics2.clipRect(nx, super.m_nY, super.m_nWidth, super.m_nHeight);
            graphics2.drawImage(super.m_image, nx - super.m_nWidth * (n2 + (this.\u0120 ? 0 : 5)), ny, null);
            graphics2.dispose();
            if (++n == 2 && this.m_bDollar) {
                graphics2 = graphics.create();
                nx -= super.m_nWidth / 2;
                graphics2.clipRect(nx, super.m_nY, super.m_nWidth / 2, super.m_nHeight);
                graphics2.drawImage(super.m_image, nx - super.m_nWidth * 4, ny, null);
                graphics2.dispose();
                n = 3;
            }
            else {
                if (n % 3 != 0 || \u011d <= 0 || this.\u0120) {
                    continue;
                }
                graphics2 = graphics.create();
                nx -= super.m_nWidth / 2;
                graphics2.clipRect(nx, super.m_nY, super.m_nWidth / 2, super.m_nHeight);
                graphics2.drawImage(super.m_image, nx - super.m_nWidth * 3, ny, null);
                graphics2.dispose();
            }
        } while (\u011d > 0 || (this.m_bDollar && n < 3 && this.\u011c > 0) || n < this.\u011e);
        if (this.m_bDollar) {
            graphics2 = graphics.create();
            nx -= super.m_nWidth;
            graphics2.clipRect(nx, super.m_nY, super.m_nWidth, super.m_nHeight);
            graphics2.drawImage(super.m_image, nx - super.m_nWidth * 2, ny, null);
            graphics2.dispose();
        }
        if (this.\u011c < 0 && !this.\u0120) {
            graphics2 = graphics.create();
            final int n3 = nx - super.m_nWidth;
            graphics2.clipRect(n3, super.m_nY, super.m_nWidth, super.m_nHeight);
            graphics2.drawImage(super.m_image, n3 - super.m_nWidth * 1, ny, null);
            graphics2.dispose();
        }
        graphics2.dispose();
    }
    
    public void setPadding(final int \u011f) {
        this.addToBounding();
        this.\u011e = \u011f;
        this.\u011c();
        this.addToBounding();
    }
    
    public NumberSprite() {
        this.\u011c = 0;
        this.\u011d = 0;
        this.\u011e = 0;
        this.\u011f = 0;
        this.m_bDollar = false;
        this.\u0120 = false;
    }
    
    public NumberSprite(final boolean bDollar, final boolean \u0121) {
        this.\u011c = 0;
        this.\u011d = 0;
        this.\u011e = 0;
        this.\u011f = 0;
        this.m_bDollar = bDollar;
        this.\u0120 = \u0121;
    }
    
    public int getPadding() {
        return this.\u011e;
    }
    
    public Rectangle getBounding() {
        final int numberWidth = this.getNumberWidth();
        if (this.\u011f == 1) {
            super.m_rTemp.reshape(super.m_nX + numberWidth / 2 - numberWidth, super.m_nY, numberWidth, super.m_nHeight);
        }
        else if (this.\u011f == 2) {
            super.m_rTemp.reshape(super.m_nX - numberWidth, super.m_nY, numberWidth, super.m_nHeight);
        }
        else if (this.\u011f == 0) {
            super.m_rTemp.reshape(super.m_nX, super.m_nY, numberWidth, super.m_nHeight);
        }
        return super.m_rTemp;
    }
    
    public void copy(final NumberSprite numberSprite) {
        super.copy(numberSprite);
        numberSprite.\u011f = this.\u011f;
        numberSprite.\u011e = this.\u011e;
        numberSprite.\u011d = this.\u011d;
        numberSprite.\u011c = this.\u011c;
    }
    
    public Sprite copy() {
        final NumberSprite numberSprite = new NumberSprite(this.m_bDollar, this.\u0120);
        this.copy(numberSprite);
        return numberSprite;
    }
    
    public void setImage(final Image image) {
        super.setImage(image);
        if (image != null) {
            super.m_nWidth /= (this.\u0120 ? 10 : 15);
        }
    }
    
    public boolean init(final Image[] array, final int[] array2) {
        this.setImage(array[0]);
        this.setPosition(array2[0], array2[1]);
        if (array2.length >= 3) {
            this.setAlignment(array2[2]);
        }
        if (array2.length >= 4) {
            this.setNumber(array2[3]);
        }
        return true;
    }
    
    public void setAlignment(final int \u011f) {
        this.\u011f = \u011f;
    }
    
    public int getNumberWidth() {
        return super.m_nWidth * this.\u011d / 2;
    }
}
