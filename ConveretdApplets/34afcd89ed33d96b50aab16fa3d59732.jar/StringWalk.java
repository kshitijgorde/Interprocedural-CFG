import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Cursor;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class StringWalk extends Applet implements Runnable
{
    static final int MAX_SIZE = 100;
    static final Cursor HAND;
    static final Cursor DEFAULT;
    boolean isMoving;
    int xx;
    int ntext;
    int text_width;
    int width;
    int height;
    int delay;
    int fontsize;
    int mouse_x;
    int mouse_y;
    int selected;
    String[] s;
    String[] url;
    String[] target;
    Font font;
    FontMetrics fm;
    Thread runner;
    Color fgcolor;
    Color bgcolor;
    Color accolor;
    Color bdcolor;
    Image offscrImg;
    Graphics offscr;
    
    public void setFont() {
        String parameter = this.getParameter("fontface");
        if (parameter == null) {
            parameter = "Times Roman";
        }
        try {
            this.fontsize = Integer.parseInt(this.getParameter("fontsize"));
        }
        catch (Exception ex) {
            this.fontsize = 12;
        }
        final String parameter2 = this.getParameter("fontstyle");
        if (parameter2 == null || parameter2.equals("plain")) {
            this.font = new Font(parameter, 0, this.fontsize);
        }
        else if (parameter2.equals("bold")) {
            this.font = new Font(parameter, 1, this.fontsize);
        }
        else {
            this.font = new Font(parameter, 2, this.fontsize);
        }
        this.fm = this.offscr.getFontMetrics(this.font);
    }
    
    public void setColor() {
        String parameter = this.getParameter("bgcolor");
        String parameter2 = this.getParameter("fgcolor");
        String parameter3 = this.getParameter("accolor");
        String parameter4 = this.getParameter("bdcolor");
        if (parameter == null) {
            parameter = "255,255,255";
        }
        if (parameter2 == null) {
            parameter2 = "000,000,000";
        }
        if (parameter3 == null) {
            parameter3 = "255,000,000";
        }
        if (parameter4 == null) {
            parameter4 = "150,150,150";
        }
        this.bgcolor = new Color(Integer.parseInt(parameter.substring(0, 3)), Integer.parseInt(parameter.substring(4, 7)), Integer.parseInt(parameter.substring(8, 11)));
        this.fgcolor = new Color(Integer.parseInt(parameter2.substring(0, 3)), Integer.parseInt(parameter2.substring(4, 7)), Integer.parseInt(parameter2.substring(8, 11)));
        this.accolor = new Color(Integer.parseInt(parameter3.substring(0, 3)), Integer.parseInt(parameter3.substring(4, 7)), Integer.parseInt(parameter3.substring(8, 11)));
        this.bdcolor = new Color(Integer.parseInt(parameter4.substring(0, 3)), Integer.parseInt(parameter4.substring(4, 7)), Integer.parseInt(parameter4.substring(8, 11)));
    }
    
    public void init() {
        this.isMoving = true;
        this.runner = new Thread(this);
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.s = new String[100];
        this.url = new String[100];
        this.target = new String[100];
        this.offscrImg = this.createImage(this.width, this.height);
        this.offscr = this.offscrImg.getGraphics();
        this.runner.start();
        this.setFont();
        this.setColor();
        try {
            this.delay = Integer.parseInt(this.getParameter("delay"));
        }
        catch (Exception ex) {
            this.delay = 10;
        }
        final boolean ntext = false;
        this.xx = (ntext ? 1 : 0);
        this.text_width = (ntext ? 1 : 0);
        this.ntext = (ntext ? 1 : 0);
        String parameter;
        while ((parameter = this.getParameter("caption" + this.ntext)) != null) {
            this.s[this.ntext] = parameter;
            this.url[this.ntext] = this.getParameter("url" + this.ntext);
            this.target[this.ntext] = this.getParameter("target" + this.ntext);
            if (this.target[this.ntext] == null) {
                this.target[this.ntext] = "_blank";
            }
            this.text_width += this.fm.stringWidth(this.s[this.ntext]) + 20;
            ++this.ntext;
        }
        final int n = -this.text_width;
        this.mouse_y = n;
        this.mouse_x = n;
        this.setSize(this.width, this.height);
        this.offscr.setFont(this.font);
    }
    
    public void paint(final Graphics graphics) {
        int selected = 0;
        int xx = this.xx;
        final int n = (this.height + this.fontsize) / 2;
        this.selected = -1;
        boolean b = false;
        while (this.s[selected] != null && xx < this.width) {
            final int stringWidth = this.fm.stringWidth(this.s[selected]);
            this.offscr.setColor(this.fgcolor);
            if (this.mouse_x >= xx && this.mouse_x <= xx + stringWidth && this.mouse_y <= n && this.mouse_y >= n - this.fontsize) {
                this.offscr.setColor(this.accolor);
                this.offscr.drawLine(xx, n + 2, xx + stringWidth, n + 2);
                b = true;
                this.selected = selected;
            }
            this.offscr.drawString(this.s[selected], xx, n);
            xx += stringWidth + 20;
            if (++selected >= this.ntext) {
                selected = 0;
            }
        }
        if (b) {
            this.setCursor(StringWalk.HAND);
        }
        else {
            this.setCursor(StringWalk.DEFAULT);
        }
        graphics.drawImage(this.offscrImg, 0, 0, this);
        this.offscr.clearRect(0, 0, this.getSize().width, this.getSize().height);
        this.offscr.setColor(this.bgcolor);
        this.offscr.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.offscr.setColor(this.bdcolor);
        this.offscr.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
        if (this.isMoving) {
            --this.xx;
        }
        if (xx < -this.text_width) {
            this.xx = 0;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.isMoving = false;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.isMoving = true;
        this.mouse_x = -this.text_width;
        this.mouse_y = -this.text_width;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int mouse_x, final int mouse_y) {
        this.mouse_x = mouse_x;
        this.mouse_y = mouse_y;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.selected != -1 && this.url[this.selected] != null) {
            try {
                this.getAppletContext().showDocument(new URL(this.url[this.selected]), this.target[this.selected]);
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {}
            this.repaint();
        }
    }
    
    static {
        HAND = new Cursor(12);
        DEFAULT = new Cursor(0);
    }
}
