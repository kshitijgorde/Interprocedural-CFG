import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public final class Bil
{
    private final boolean ltht;
    private int Ltht;
    private int lTht;
    private Image LTht;
    private int ltHt;
    private int LtHt;
    private int lTHt;
    private int LTHt;
    
    public Bil(final int ltht, final int lTht, final boolean ltht2, final Image lTht2, final int ltHt, final int ltHt2) {
        this.ltht = ltht2;
        this.Ltht = ltht;
        this.lTht = lTht;
        this.LTht = lTht2;
        this.lTHt = ltHt;
        this.LTHt = ltHt2;
        this.ltHt = ltht + ltHt2;
        this.LtHt = lTht + ltHt;
    }
    
    public int getX() {
        return this.Ltht;
    }
    
    public int getY() {
        return this.lTht;
    }
    
    public int getMaxX() {
        return this.ltHt;
    }
    
    public int getMaxY() {
        return this.LtHt;
    }
    
    public Image getImage() {
        return this.LTht;
    }
    
    public boolean NS() {
        return this.ltht;
    }
    
    public int getHeight() {
        return this.lTHt;
    }
    
    public int getWidth() {
        return this.LTHt;
    }
    
    public void setX(final int ltht) {
        this.Ltht = ltht;
        this.ltHt = ltht + this.LTHt;
    }
    
    public void setY(final int lTht) {
        this.lTht = lTht;
        this.LtHt = lTht + this.lTHt;
    }
    
    public void setNum(byte b) {
        if (b < 7) {
            this.lTht = 15;
        }
        else if (b < 13) {
            this.lTht = 55;
        }
        else if (b < 19) {
            this.lTht = 95;
        }
        else if (b < 25) {
            this.lTht = 135;
        }
        else if (b < 31) {
            this.lTht = 174;
        }
        else {
            this.lTht = 215;
        }
        this.LtHt = this.lTht + this.lTHt;
        for (int i = 8; i < 209; i += 40, --b) {
            switch (b) {
                case 1:
                case 7:
                case 13:
                case 19:
                case 25:
                case 31: {
                    this.Ltht = i;
                    this.ltHt = this.Ltht + this.LTHt;
                    i = 210;
                    break;
                }
            }
        }
    }
}
