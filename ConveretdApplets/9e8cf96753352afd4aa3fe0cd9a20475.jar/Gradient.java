import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Gradient extends Applet
{
    String text;
    int a;
    int pixely;
    int fontsize;
    Color kleur;
    Color bgkleur;
    boolean upsidedown;
    int blue;
    int green;
    int red;
    int bgblue;
    String cyclecolor;
    int bggreen;
    int bgred;
    int cyclespeed;
    FontMetrics fontmetrics;
    int fontwidth;
    
    public Gradient() {
        this.a = 0;
        this.pixely = 10;
        this.upsidedown = false;
        this.fontwidth = 0;
    }
    
    public void init() {
        this.cyclecolor = this.getParameter("cyclecolor");
        this.text = this.getParameter("Text");
        this.cyclespeed = Integer.parseInt(this.getParameter("cyclespeed"));
        this.fontsize = Integer.parseInt(this.getParameter("Fontsize"));
        this.red = Integer.parseInt(this.getParameter("fontred"));
        this.green = Integer.parseInt(this.getParameter("fontgreen"));
        this.blue = Integer.parseInt(this.getParameter("fontblue"));
        this.kleur = new Color(this.red, this.green, this.blue);
        this.bgred = Integer.parseInt(this.getParameter("bgred"));
        this.bggreen = Integer.parseInt(this.getParameter("bggreen"));
        this.bgblue = Integer.parseInt(this.getParameter("bgblue"));
        this.bgkleur = new Color(this.bgred, this.bggreen, this.bgblue);
        if (this.getParameter("upsidedown").equals("true")) {
            this.upsidedown = true;
        }
    }
    
    public void paint(final Graphics graphics) {
        final Image[] array = new Image[this.fontsize + this.fontsize / 2];
        final Graphics[] array2 = new Graphics[this.fontsize + this.fontsize / 2];
        final Font font = new Font("Impact", 0, this.fontsize);
        this.fontwidth = this.getFontMetrics(font).stringWidth(this.text);
        for (int i = 0; i < this.fontsize + this.fontsize / 2; ++i) {
            array[i] = this.createImage(this.fontwidth + 30, 1);
            array2[i] = array[i].getGraphics();
            if (this.cyclecolor.equals("red")) {
                this.red += this.cyclespeed;
                if (this.red > 255) {
                    this.red = 0;
                }
            }
            else if (this.cyclecolor.equals("green")) {
                this.green += this.cyclespeed;
                if (this.green > 255) {
                    this.green = 0;
                }
            }
            else if (this.cyclecolor.equals("blue")) {
                this.blue += this.cyclespeed;
                if (this.blue > 255) {
                    this.blue = 0;
                }
            }
            else if (this.cyclecolor.equals("all")) {
                this.blue += this.cyclespeed;
                this.red += this.cyclespeed;
                this.green += this.cyclespeed;
                if (this.blue > 255) {
                    this.blue = 0;
                }
                if (this.red > 255) {
                    this.red = 0;
                }
                if (this.green > 255) {
                    this.green = 0;
                }
            }
            this.kleur = new Color(this.red, this.green, this.blue);
            array2[i].setColor(this.kleur);
            this.setBackground(this.bgkleur);
            array2[i].setFont(font);
        }
        if (this.upsidedown) {
            for (int j = 0; j < this.fontsize + this.fontsize / 2 - 1; ++j) {
                array2[j].drawString(this.text, 20, this.a);
                graphics.drawImage(array[j], 20, this.pixely + 20, this);
                ++this.a;
                ++this.pixely;
            }
        }
        else {
            for (int k = 0; k < this.fontsize + this.fontsize / 2 - 1; ++k) {
                array2[k].drawString(this.text, 20, this.a);
                graphics.drawImage(array[this.fontsize + this.fontsize / 2 - 1 - k], 20, this.pixely - this.fontsize + 20, this);
                ++this.a;
                ++this.pixely;
            }
        }
        this.a = 0;
        this.pixely = 10;
    }
}
