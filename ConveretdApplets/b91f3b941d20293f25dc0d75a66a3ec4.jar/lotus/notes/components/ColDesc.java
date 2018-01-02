// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Color;
import java.awt.Font;

public class ColDesc
{
    public int xPos;
    public int yOffset;
    public int hOffset;
    public int hAlignment;
    public int vAlignment;
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_RIGHT = 1;
    public static final int ALIGN_CENTER = 2;
    public static final int ALIGN_TOP = 3;
    public static final int ALIGN_BOTTOM = 4;
    public static final int ALIGNMENT_TOPLEFT = 0;
    public static final int ALIGNMENT_TOPCENTER = 1;
    public static final int ALIGNMENT_TOPRIGHT = 2;
    public static final int ALIGNMENT_MIDDLELEFT = 3;
    public static final int ALIGNMENT_MIDDLECENTER = 4;
    public static final int ALIGNMENT_MIDDLERIGHT = 5;
    public static final int ALIGNMENT_BOTTOMLEFT = 6;
    public static final int ALIGNMENT_BOTTOMCENTER = 7;
    public static final int ALIGNMENT_BOTTOMRIGHT = 8;
    public int Width;
    public Font ColTextFont;
    public Color ColTextColor;
    public boolean bClip;
    public boolean isLastColumn;
    
    public ColDesc() {
        this.hOffset = 0;
        this.hAlignment = 0;
        this.vAlignment = 0;
        this.isLastColumn = false;
        this.bClip = true;
    }
    
    public void SetxPos(final int xPos) {
        this.xPos = xPos;
    }
    
    public int GetxPos() {
        return this.xPos;
    }
    
    public void SetyOffset(final int yOffset) {
        this.yOffset = yOffset;
    }
    
    public int GetyOffset() {
        if (this.vAlignment == 3) {
            return Math.abs(this.yOffset);
        }
        if (this.vAlignment == 4) {
            return -1 * this.yOffset;
        }
        if (this.vAlignment == 2) {
            return 0;
        }
        return this.yOffset;
    }
    
    public void setHalignment(final int hAlignment) {
        this.hAlignment = hAlignment;
    }
    
    public int getHalignment() {
        return this.hAlignment;
    }
    
    public void setValignment(final int vAlignment) {
        this.vAlignment = vAlignment;
    }
    
    public int getValignment() {
        return this.vAlignment;
    }
    
    public void setAlignment(final int n) {
        switch (n) {
            case 0: {
                this.hAlignment = 0;
                this.vAlignment = 3;
                break;
            }
            case 1: {
                this.hAlignment = 2;
                this.vAlignment = 3;
                break;
            }
            case 2: {
                this.hAlignment = 1;
                this.vAlignment = 3;
                break;
            }
            case 3: {
                this.hAlignment = 0;
                this.vAlignment = 2;
                break;
            }
            case 4: {
                this.hAlignment = 2;
                this.vAlignment = 2;
                break;
            }
            case 5: {
                this.hAlignment = 1;
                this.vAlignment = 2;
                break;
            }
            case 6: {
                this.hAlignment = 0;
                this.vAlignment = 4;
                break;
            }
            case 7: {
                this.hAlignment = 2;
                this.vAlignment = 4;
                break;
            }
            case 8: {
                this.hAlignment = 1;
                this.vAlignment = 4;
                break;
            }
            default: {
                this.hAlignment = 0;
                this.vAlignment = 3;
                break;
            }
        }
    }
    
    public void SetWidth(final int width) {
        this.Width = width;
    }
    
    public int GetWidth() {
        return this.Width;
    }
    
    public void SetFont(final Font colTextFont) {
        this.ColTextFont = colTextFont;
    }
    
    public Font GetFont() {
        return this.ColTextFont;
    }
    
    public Color GetColor() {
        return this.ColTextColor;
    }
}
