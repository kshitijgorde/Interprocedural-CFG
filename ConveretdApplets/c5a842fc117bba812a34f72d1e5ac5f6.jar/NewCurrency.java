import java.awt.Image;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import java.applet.Applet;
import java.awt.Font;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public final class NewCurrency extends Canvas
{
    static Font font2;
    String op;
    Applet applet;
    LogicUnit logicUnit;
    Container owner;
    int x;
    int y;
    
    public NewCurrency(final String op, final Container owner, final Applet applet) {
        this.x = 1;
        this.y = 1;
        this.applet = applet;
        this.logicUnit = ((EuroCalc)applet).getLogicUnit();
        this.op = op;
        this.resize(42, 32);
        (this.owner = owner).validate();
        this.setBackground(Color.blue);
    }
    
    public String getOp() {
        return this.op;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fillOval(this.x + 1, this.y + 1, 38, 28);
        graphics.setColor(Color.black);
        graphics.drawOval(this.x, this.y, 39, 29);
        graphics.setFont(NewCurrency.font2);
        graphics.drawString(this.op, (41 + this.x + 1) / 2 - graphics.getFontMetrics().stringWidth(this.op) / 2, 21 + this.y + 1);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.x = 2;
        this.y = 2;
        this.repaint();
        this.logicUnit.setCurrentCurrency(this.op);
        ((EuroCalc)this.applet).setFlagCurr2(FlagCanvas.HS.get(this.logicUnit.current_currency));
        this.applet.requestFocus();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.x = 1;
        this.y = 1;
        this.repaint();
        return true;
    }
    
    static {
        NewCurrency.font2 = new Font("Arial", 1, 14);
    }
}
