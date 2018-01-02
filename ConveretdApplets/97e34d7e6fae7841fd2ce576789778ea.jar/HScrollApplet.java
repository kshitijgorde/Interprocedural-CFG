import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class HScrollApplet extends Applet implements Runnable
{
    int delay;
    String fontName;
    int fontSize;
    int frgB;
    int frgR;
    int frgG;
    int bkgB;
    int bkgR;
    int bkgG;
    int yPos;
    int width;
    int height;
    String message;
    Thread t0;
    boolean flag;
    int wt;
    int ht;
    Image img;
    int x;
    int msgWidth;
    int xMove;
    int i;
    
    public HScrollApplet() {
        this.delay = 1000;
        this.fontName = "Arial";
        this.fontSize = 12;
        this.frgB = 0;
        this.frgR = 0;
        this.frgG = 0;
        this.bkgB = 255;
        this.bkgR = 255;
        this.bkgG = 255;
        this.yPos = 10;
        this.width = 500;
        this.height = 15;
        this.message = "";
        this.flag = true;
        this.x = this.getBounds().width;
        this.msgWidth = 0;
        this.xMove = 1;
        this.i = 1;
    }
    
    public void init() {
        this.readParameters();
        this.wt = this.getBounds().width;
        this.ht = this.getBounds().height;
        this.img = this.createImage(this.wt, this.ht);
        this.setBackground(new Color(this.bkgR, this.bkgG, this.bkgB));
    }
    
    public void start() {
        if (this.t0 == null) {
            (this.t0 = new Thread(this)).setPriority(10);
            this.flag = true;
            this.t0.start();
        }
    }
    
    public void stop() {
        this.flag = false;
        this.t0 = null;
    }
    
    public void update(final Graphics graphics) {
        final Graphics graphics2 = this.img.getGraphics();
        graphics2.setColor(new Color(this.bkgR, this.bkgG, this.bkgB));
        graphics2.fillRect(0, 0, this.wt, this.ht);
        graphics2.setColor(new Color(this.frgR, this.frgG, this.frgB));
        graphics2.setFont(new Font(this.fontName, 0, this.fontSize));
        graphics2.drawString(this.message, this.x, this.yPos);
        this.msgWidth = graphics2.getFontMetrics().stringWidth(this.message);
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.img != null) {
            graphics.drawImage(this.img, 0, 0, this);
        }
    }
    
    public void moveX() {
        this.x -= this.xMove;
        if (this.x < -this.msgWidth + 5) {
            this.x = this.getBounds().width;
        }
    }
    
    public void run() {
        while (this.flag) {
            this.moveX();
            this.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {}
        }
    }
    
    public void readParameters() {
        final String parameter = this.getParameter("MESSAGE");
        if (parameter != null && parameter.length() > 0) {
            this.message = parameter;
        }
        final String parameter2 = this.getParameter("FONTNAME");
        if (parameter2 != null) {
            this.fontName = parameter2;
        }
        try {
            this.delay = Integer.parseInt(this.getParameter("DELAY"));
        }
        catch (NumberFormatException ex) {
            System.out.println(ex + " in retrieving del");
        }
        try {
            this.fontSize = Integer.parseInt(this.getParameter("FONTSIZE"));
        }
        catch (NumberFormatException ex2) {
            System.out.println(ex2);
        }
        try {
            this.frgB = Integer.parseInt(this.getParameter("FRGB"));
        }
        catch (NumberFormatException ex3) {
            System.out.println(ex3);
        }
        try {
            this.frgG = Integer.parseInt(this.getParameter("FRGG"));
        }
        catch (NumberFormatException ex4) {
            System.out.println(ex4);
        }
        try {
            this.frgR = Integer.parseInt(this.getParameter("FRGR"));
        }
        catch (NumberFormatException ex5) {
            System.out.println(ex5);
        }
        try {
            this.bkgB = Integer.parseInt(this.getParameter("BKGB"));
        }
        catch (NumberFormatException ex6) {
            System.out.println(ex6);
        }
        try {
            this.bkgG = Integer.parseInt(this.getParameter("BKGG"));
        }
        catch (NumberFormatException ex7) {
            System.out.println(ex7);
        }
        try {
            this.bkgR = Integer.parseInt(this.getParameter("BKGR"));
        }
        catch (NumberFormatException ex8) {
            System.out.println(ex8);
        }
        try {
            this.width = Integer.parseInt(this.getParameter("WIDTH"));
        }
        catch (NumberFormatException ex9) {
            System.out.println(ex9);
        }
        try {
            this.height = Integer.parseInt(this.getParameter("HEIGHT"));
        }
        catch (NumberFormatException ex10) {
            System.out.println(ex10);
        }
        try {
            this.yPos = Integer.parseInt(this.getParameter("YPOS"));
        }
        catch (NumberFormatException ex11) {
            System.out.println(ex11);
        }
        try {
            this.xMove = Integer.parseInt(this.getParameter("XMOVE"));
        }
        catch (NumberFormatException ex12) {
            System.out.println(ex12);
        }
    }
}
