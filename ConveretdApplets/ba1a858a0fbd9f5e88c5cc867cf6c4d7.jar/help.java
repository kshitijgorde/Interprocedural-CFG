import java.awt.Graphics;
import java.awt.Event;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

public class help extends Frame
{
    private Font fb;
    private Font fp;
    private Font fg;
    private int status;
    private Color bgcolor;
    
    public help(final String s) {
        super(s);
        this.setBackground(this.bgcolor = new Color(0, 115, 63));
        this.setLayout(new GridLayout(1, 1));
        this.fb = new Font("Helvetica", 1, 12);
        this.fp = new Font("Helvetica", 0, 12);
        this.fg = new Font("Helvetica", 1, 18);
        final MenuBar menuBar = new MenuBar();
        final Menu menu = new Menu("Menu");
        menu.add(new MenuItem("Help"));
        menu.add(new MenuItem("About"));
        menu.add(new MenuItem("-"));
        menu.add(new MenuItem("Exit"));
        menuBar.add(menu);
        this.setMenuBar(menuBar);
        this.status = 1;
        this.move(100, 100);
        this.resize(600, 450);
    }
    
    public boolean action(final Event event, final Object o) {
        final String s = (String)o;
        if (event.target instanceof MenuItem) {
            if (s.equals("Help")) {
                this.status = 1;
            }
            else if (s.equals("About")) {
                this.status = 2;
            }
            else if (s.equals("Exit")) {
                this.status = 1;
                this.hide();
            }
            this.repaint();
            return true;
        }
        return false;
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.status = 1;
            this.hide();
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        if (this.status == 1) {
            graphics.setColor(this.bgcolor);
            graphics.fillRect(0, 0, 600, 450);
            graphics.setColor(Color.white);
            graphics.setFont(this.fb);
            graphics.drawString("The plot:", 20, 100);
            graphics.setFont(this.fp);
            graphics.drawString("In this game you have the great opportunity to be a mailman (more correct: Cliff Clavin from Cheers).", 20, 115);
            graphics.drawString("You start the game with four lives, a full beer mug, the score of zero points and you got five minutes", 20, 130);
            graphics.drawString("to complete the round.", 20, 145);
            graphics.drawString("You maneuver Cliff by using the arrow keys. When you stand in front of the correct mail box (marked", 20, 160);
            graphics.drawString("with a red arrow), you press \"enter\" to deliver the letter. You have probably already figured out", 20, 175);
            graphics.drawString("that this is how you collect points in this game. The red arrow will then move to the next mailbox.", 20, 190);
            graphics.drawString("It's not an easy job working as a mailman. You have to look out for cars and dogs. If a car hits you or", 20, 205);
            graphics.drawString("a dog bites you, you will lose a life. It's also important that you make regular visits to the bar", 20, 220);
            graphics.drawString("Cheers to fill up with beer, or else you will lose a life. When you lose a life, Cliff will automatically go", 20, 235);
            graphics.drawString("to his favorite place to have a beer, and lick his wounds.", 20, 250);
            graphics.setFont(this.fb);
            graphics.drawString("Keys:", 20, 300);
            graphics.setFont(this.fp);
            graphics.drawString("Arrow keys - Move Cliff in the direction the arrow points.", 20, 315);
            graphics.drawString("Enter - Deliver the letter or enter Cheers.", 20, 330);
            graphics.drawString("p - Pause the game.", 20, 345);
            graphics.drawString("F1 or h - Help.", 20, 360);
            graphics.drawString("Esc or q - Quit current game.", 20, 375);
            return;
        }
        if (this.status == 2) {
            graphics.setColor(this.bgcolor);
            graphics.fillRect(0, 0, 600, 450);
            graphics.setColor(Color.white);
            this.drawCenterText(this.fg, 100, "Game developers:", graphics);
            this.drawCenterText(this.fg, 150, "Robert Nilsen   (Programming/graphics)", graphics);
            this.drawCenterText(this.fg, 200, "Mikkel Sj\u00f8lie    (Programming/graphics)", graphics);
            this.drawCenterText(this.fg, 300, "Copyright 1998", graphics);
        }
    }
    
    public void drawCenterText(final Font font, final int n, final String s, final Graphics graphics) {
        final int stringWidth = this.getFontMetrics(font).stringWidth(s);
        graphics.setFont(font);
        graphics.drawString(s, (this.size().width - stringWidth) / 2, n);
    }
}
