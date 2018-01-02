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
    public static final int CENTER = 1;
    public static final int LEFT = 0;
    public static final int RIGHT = 2;
    boolean m_bDollar;
    boolean m_bJustNumbers;
    int m_nAlign;
    int m_nDigits;
    int m_nNumber;
    int m_nPadding;
    
    public NumberSprite(final boolean bDollar, final boolean bJustNumbers) {
        this.m_nNumber = 0;
        this.m_nDigits = 0;
        this.m_nPadding = 0;
        this.m_nAlign = 0;
        this.m_bDollar = bDollar;
        this.m_bJustNumbers = bJustNumbers;
    }
    
    public NumberSprite() {
        this.m_nNumber = 0;
        this.m_nDigits = 0;
        this.m_nPadding = 0;
        this.m_nAlign = 0;
        this.m_bDollar = false;
        this.m_bJustNumbers = false;
    }
    
    public Sprite copy() {
        final NumberSprite cpy = new NumberSprite(this.m_bDollar, this.m_bJustNumbers);
        this.copy(cpy);
        return cpy;
    }
    
    public void copy(final NumberSprite cpy) {
        super.copy(cpy);
        cpy.m_nAlign = this.m_nAlign;
        cpy.m_nPadding = this.m_nPadding;
        cpy.m_nDigits = this.m_nDigits;
        cpy.m_nNumber = this.m_nNumber;
    }
    
    private void countDigits() {
        this.m_nDigits = 2;
        int nNumber = this.m_nNumber;
        if (nNumber < 0) {
            nNumber *= -1;
        }
        for (nNumber /= 10; nNumber > 0; nNumber /= 10, this.m_nDigits += 2) {}
        if (this.m_nDigits < this.m_nPadding * 2) {
            this.m_nDigits = this.m_nPadding * 2;
        }
        if (this.m_bDollar) {
            this.m_nDigits += (this.m_nDigits - 6) / 6;
            this.m_nDigits += 2;
            if (this.m_nNumber % 100 != 0) {
                ++this.m_nDigits;
            }
            else if (this.m_nNumber != 0) {
                this.m_nDigits -= 4;
            }
        }
        else if (!this.m_bJustNumbers) {
            this.m_nDigits += (this.m_nDigits - 2) / 6;
        }
        if (this.m_nNumber < 0 && !this.m_bJustNumbers) {
            this.m_nDigits += 2;
        }
    }
    
    public void drawSprite(final Graphics g) {
        if (super.m_image == null || !super.m_bVisible) {
            return;
        }
        final int y = super.m_nY;
        int nNumber = this.m_nNumber;
        if (nNumber < 0) {
            nNumber *= -1;
        }
        final int nWidth = this.getNumberWidth();
        int x;
        if (this.m_nAlign == 1) {
            x = super.m_nX + nWidth / 2;
        }
        else if (this.m_nAlign == 2) {
            x = super.m_nX;
        }
        else {
            if (this.m_nAlign != 0) {
                return;
            }
            x = super.m_nX + nWidth;
        }
        int nCount = 0;
        if (this.m_bDollar && nNumber >= 100 && nNumber % 100 == 0) {
            nNumber /= 100;
            nCount = 3;
        }
        Graphics gTemp;
        do {
            final int nDigit = nNumber % 10;
            nNumber /= 10;
            gTemp = g.create();
            x -= super.m_nWidth;
            gTemp.clipRect(x, super.m_nY, super.m_nWidth, super.m_nHeight);
            if (super.m_rClip != null) {
                gTemp.clipRect(super.m_rClip.x, super.m_rClip.y, super.m_rClip.width, super.m_rClip.height);
            }
            gTemp.drawImage(super.m_image, x - super.m_nWidth * (nDigit + (this.m_bJustNumbers ? 0 : 5)), y, null);
            gTemp.dispose();
            if (++nCount == 2 && this.m_bDollar) {
                gTemp = g.create();
                x -= super.m_nWidth / 2;
                gTemp.clipRect(x, super.m_nY, super.m_nWidth / 2, super.m_nHeight);
                if (super.m_rClip != null) {
                    gTemp.clipRect(super.m_rClip.x, super.m_rClip.y, super.m_rClip.width, super.m_rClip.height);
                }
                gTemp.drawImage(super.m_image, x - super.m_nWidth * 4, y, null);
                gTemp.dispose();
                nCount = 3;
            }
            else {
                if (nCount % 3 != 0 || nNumber <= 0 || this.m_bJustNumbers) {
                    continue;
                }
                gTemp = g.create();
                x -= super.m_nWidth / 2;
                gTemp.clipRect(x, super.m_nY, super.m_nWidth / 2, super.m_nHeight);
                if (super.m_rClip != null) {
                    gTemp.clipRect(super.m_rClip.x, super.m_rClip.y, super.m_rClip.width, super.m_rClip.height);
                }
                gTemp.drawImage(super.m_image, x - super.m_nWidth * 3, y, null);
                gTemp.dispose();
            }
        } while (nNumber > 0 || (this.m_bDollar && nCount < 3 && this.m_nNumber > 0) || nCount < this.m_nPadding);
        if (this.m_bDollar) {
            gTemp = g.create();
            x -= super.m_nWidth;
            gTemp.clipRect(x, super.m_nY, super.m_nWidth, super.m_nHeight);
            if (super.m_rClip != null) {
                gTemp.clipRect(super.m_rClip.x, super.m_rClip.y, super.m_rClip.width, super.m_rClip.height);
            }
            gTemp.drawImage(super.m_image, x - super.m_nWidth * 2, y, null);
            gTemp.dispose();
        }
        if (this.m_nNumber < 0 && !this.m_bJustNumbers) {
            gTemp = g.create();
            x -= super.m_nWidth;
            gTemp.clipRect(x, super.m_nY, super.m_nWidth, super.m_nHeight);
            if (super.m_rClip != null) {
                gTemp.clipRect(super.m_rClip.x, super.m_rClip.y, super.m_rClip.width, super.m_rClip.height);
            }
            gTemp.drawImage(super.m_image, x - super.m_nWidth * 1, y, null);
            gTemp.dispose();
        }
        gTemp.dispose();
    }
    
    public Rectangle getBounding() {
        final int nWidth = this.getNumberWidth();
        if (this.m_nAlign == 1) {
            super.m_rTemp.reshape(super.m_nX + nWidth / 2 - nWidth, super.m_nY, nWidth, super.m_nHeight);
        }
        else if (this.m_nAlign == 2) {
            super.m_rTemp.reshape(super.m_nX - nWidth, super.m_nY, nWidth, super.m_nHeight);
        }
        else if (this.m_nAlign == 0) {
            super.m_rTemp.reshape(super.m_nX, super.m_nY, nWidth, super.m_nHeight);
        }
        return super.m_rTemp;
    }
    
    public int getNumber() {
        return this.m_nNumber;
    }
    
    public int getNumberWidth() {
        return super.m_nWidth * this.m_nDigits / 2;
    }
    
    public int getPadding() {
        return this.m_nPadding;
    }
    
    public boolean init(final Image[] imgs, final int[] nParams) {
        this.setImage(imgs[0]);
        this.setPosition(nParams[0], nParams[1]);
        if (nParams.length >= 3) {
            this.setAlignment(nParams[2]);
        }
        if (nParams.length >= 4) {
            this.setNumber(nParams[3]);
        }
        return true;
    }
    
    public void setAlignment(final int nAlign) {
        this.m_nAlign = nAlign;
    }
    
    public void setImage(final Image img) {
        super.setImage(img);
        if (img != null) {
            super.m_nWidth /= (this.m_bJustNumbers ? 10 : 15);
        }
    }
    
    public void setNumber(final int n) {
        this.addToBounding();
        this.m_nNumber = n;
        this.countDigits();
        this.addToBounding();
    }
    
    public void setPadding(final int n) {
        this.addToBounding();
        this.m_nPadding = n;
        this.countDigits();
        this.addToBounding();
    }
}
