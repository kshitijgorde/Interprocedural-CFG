import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import gamelib.ActionBuffer;
import gamelib.OffComponent;

// 
// Decompiled by Procyon v0.5.30
// 

class PlayerControls extends OffComponent
{
    private String str1;
    private String str2;
    private Player oplayer;
    private int w1;
    private int w2;
    
    PlayerControls(final ActionBuffer actionBuffer) {
        super(actionBuffer, new Rectangle(0, 0, 800, 600));
        this.setVisible(true);
        this.setActive(true);
    }
    
    protected void go() {
        final Tank actt = ((Field)super.buffer).actt;
        if (actt == null) {
            return;
        }
        if (actt.owner != this.oplayer) {
            this.oplayer = actt.owner;
            this.str1 = "Controls:  " + actt.owner.controls;
            this.str2 = "Player:  " + actt.owner.name;
            final FontMetrics fontMetrics = super.buffer.getFontMetrics(super.buffer.getFont());
            this.w1 = fontMetrics.stringWidth(this.str1) + 10;
            this.w2 = fontMetrics.stringWidth(this.str2) + 10;
            this.setSize(Math.max(this.w1, this.w2), super.bounds.height);
            this.setPosition(super.buffer.getBounds().width - super.bounds.width, super.bounds.y);
            this.repaint();
        }
    }
    
    protected void paint(final Graphics graphics) {
        if (this.str1 == null) {
            return;
        }
        final Tank actt = ((Field)super.buffer).actt;
        graphics.setColor(Color.white);
        graphics.drawString(this.str1, super.bounds.x + super.bounds.width - this.w1, 16);
        graphics.drawString(this.str2, super.bounds.x + super.bounds.width - this.w2, 30);
    }
}
