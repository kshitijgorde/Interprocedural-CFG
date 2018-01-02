import java.awt.Color;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class statusBar extends Applet
{
    String message;
    int elapsedTime;
    int time;
    boolean needsdraw;
    
    statusBar() {
        this.message = "";
        this.needsdraw = true;
        this.set("Welcome to pegs, good luck", 300);
    }
    
    public void set(final String message, final int time) {
        this.message = message;
        this.elapsedTime = 0;
        this.time = time;
        this.needsdraw = true;
    }
    
    public void draw(final Graphics graphics) {
        if (this.needsdraw) {
            graphics.setColor(new Color(150, 150, 150));
            graphics.fillRect(0, 320, 480, 30);
            graphics.setColor(new Color(0, 0, 0));
            graphics.fillRect(2, 322, 476, 26);
            graphics.setColor(new Color(255, 255, 255));
            graphics.drawString(this.message, 240 - this.message.length() * 6 / 2, 340);
            this.needsdraw = false;
        }
        ++this.elapsedTime;
        if (this.time == this.elapsedTime) {
            this.message = "";
            this.needsdraw = true;
        }
    }
}
