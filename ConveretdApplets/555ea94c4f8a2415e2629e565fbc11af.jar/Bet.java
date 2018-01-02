import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class Bet extends Panel
{
    static final int HALF = 1;
    static final int FULL = 2;
    static final int betWidth = 32;
    static final int minHeight = 15;
    private int amount;
    private String betCaption;
    private int coinType;
    private BaseStack baseStack;
    
    public void setAmount(final int amount) {
        this.amount = amount;
        this.repaint();
    }
    
    public int getAmount() {
        return this.amount;
    }
    
    public void setBetCaption(final String betCaption) {
        this.betCaption = betCaption;
        this.repaint();
    }
    
    Bet(final int n, final int n2, final int n3, final BaseStack baseStack) {
        this.setVisible(true);
        this.baseStack = baseStack;
        this.setLocation(n, n2);
        this.coinType = 2;
        this.betCaption = "";
        this.setLayout(new FlowLayout(1, 5, 5));
        this.setSize(0, 0);
        this.setSize(32, n3);
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        super.paint(graphics);
        if (this.coinType == 2) {
            this.baseStack.draw(this.amount, this.getHeight(), graphics);
        }
        if (this.coinType == 1) {
            this.baseStack.drawHalf(this.amount, this.getHeight(), graphics);
        }
        if (this.amount == 0) {
            graphics.setColor(Color.black);
        }
        else {
            graphics.setColor(Color.white);
        }
        graphics.setFont(new Font(this.getFont().getName(), 0, 10));
        graphics.drawString(this.betCaption, 1, this.getHeight());
    }
    
    public void clear() {
        if (this.coinType == 2) {
            this.baseStack.addChips(this.amount);
        }
        if (this.coinType == 1) {
            this.baseStack.addHalf(this.amount);
        }
        this.setBetCaption("");
        this.setAmount(0);
    }
    
    public void setHalf() {
        this.coinType = 1;
    }
    
    public void setFull() {
        this.coinType = 2;
    }
    
    public void setX(final int n) {
        this.setLocation(n, this.getY());
    }
    
    public void setY(final int n) {
        this.setLocation(this.getX(), n);
    }
    
    public int getHeight() {
        return this.getSize().height;
    }
    
    public int getX() {
        return this.getLocation().x;
    }
    
    public int getY() {
        return this.getLocation().y;
    }
}
