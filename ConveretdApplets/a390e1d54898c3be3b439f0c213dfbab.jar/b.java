import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class b
{
    Color a;
    Color do;
    Color if;
    Font new;
    Font int;
    Font for;
    Font try;
    
    public b() {
        this.a = new Color(90, 75, 20);
        this.do = new Color(125, 45, 0);
        this.if = new Color(250, 250, 164);
        this.new = new Font("Helvetica", 0, 15);
        this.int = new Font("Helvetica", 0, 11);
        this.for = new Font("Helvetica", 1, 11);
        this.try = new Font("Helvetica", 0, 17);
    }
    
    public void a(final Graphics graphics) {
        graphics.setColor(this.a);
        graphics.fillRect(75, 65, 350, 260);
        graphics.setColor(this.do);
        graphics.drawRect(75, 65, 349, 259);
        graphics.drawRect(76, 66, 347, 257);
        graphics.drawRect(77, 67, 345, 255);
        graphics.drawLine(75, 260, 423, 260);
        graphics.drawLine(75, 261, 423, 261);
        graphics.fillOval(160, 248, 170, 25);
        graphics.setColor(this.if);
        graphics.setFont(this.new);
        graphics.drawString("Help the worm and the mole to the exit.", 113, 95);
        graphics.setFont(this.try);
        graphics.drawString("www.G5.dk", 205, 265);
        graphics.setFont(this.int);
        graphics.drawString("The worm can move the small rocks and the mole", 113, 125);
        graphics.drawString("can move the big ones. If they work together", 113, 137);
        graphics.drawString("they can both escape the level.....", 113, 149);
        graphics.drawString("To enter the Highscore you have to complete all", 113, 175);
        graphics.drawString("five levels and use less than 1100 moves (Counter). ", 113, 187);
        graphics.drawString("Game programming and idea:", 110, 300);
        graphics.drawString("Most Graphics by:", 150, 315);
        graphics.setFont(this.for);
        graphics.drawString("Pelle Christensen", 242, 315);
        graphics.drawString("Karsten Mandrup Nielsen", 254, 300);
        graphics.drawString("[PRESS SPACE TO PLAY]", 182, 230);
    }
}
