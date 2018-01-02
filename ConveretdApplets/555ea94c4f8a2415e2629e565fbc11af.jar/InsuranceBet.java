import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class InsuranceBet
{
    BaseStack baseStack;
    int bet1;
    int win1;
    int win2;
    int bottom;
    int left;
    int seperation;
    
    public void setBet(final int bet1) {
        if (bet1 > 0) {
            this.bet1 = bet1;
        }
    }
    
    public int getBet() {
        return this.bet1;
    }
    
    public void win() {
        this.win1 = this.bet1;
        this.win2 = this.bet1;
    }
    
    public void lose() {
        this.bet1 = 0;
    }
    
    public void clear() {
        this.baseStack.addHalf(this.bet1);
        this.bet1 = 0;
        this.baseStack.addHalf(this.win1);
        this.win1 = 0;
        this.baseStack.addHalf(this.win2);
        this.win2 = 0;
    }
    
    InsuranceBet(final int left, final int bottom, final int seperation, final BaseStack baseStack) {
        this.baseStack = baseStack;
        this.bottom = bottom;
        this.left = left;
        this.seperation = seperation;
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        this.baseStack.drawHalfAbsolute(this.bet1, this.bottom, this.left, graphics);
        this.baseStack.drawHalfAbsolute(this.win1, this.bottom, this.left - this.seperation, graphics);
        this.baseStack.drawHalfAbsolute(this.win2, this.bottom, this.left + this.seperation, graphics);
    }
}
