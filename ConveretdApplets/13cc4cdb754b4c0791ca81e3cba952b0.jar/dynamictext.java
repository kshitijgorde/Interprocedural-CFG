import java.awt.Graphics;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class dynamictext extends Applet implements Runnable
{
    Thread ticking;
    Color fcolor;
    Color bgcolor;
    Font curFont;
    FontMetrics curMets;
    String curText;
    String lstText;
    String fface;
    int fsize;
    int lst_fsize;
    int fcenter;
    int x_pos;
    int y_pos;
    int lst_x_pos;
    int lst_y_pos;
    
    public void start() {
        if (this.ticking == null) {
            (this.ticking = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.ticking != null) {
            this.ticking.stop();
            this.ticking = null;
        }
    }
    
    public void init() {
        this.x_pos = Integer.parseInt(this.getParameter("x_pos"));
        this.y_pos = Integer.parseInt(this.getParameter("y_pos"));
        this.lst_x_pos = this.x_pos;
        this.lst_y_pos = this.y_pos;
        this.fcenter = Integer.parseInt(this.getParameter("center"));
        this.fcolor = this.convColor(this.getParameter("fontcolor"), this.getForeground());
        this.setBackground(this.bgcolor = this.convColor(this.getParameter("bgcolor"), this.getBackground()));
        this.fface = this.getParameter("fontface");
        this.fsize = Integer.parseInt(this.getParameter("fontsize"));
        this.lst_fsize = this.fsize;
        this.curFont = new Font(this.fface, 0, this.fsize);
    }
    
    public void run() {
        this.getAppletContext().showStatus("dynamicText 1.1, Copyright (c) 1996 Nick Heinle");
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException ex) {}
        this.getAppletContext().showStatus("");
    }
    
    public void setText(final String curText) {
        this.lstText = this.curText;
        this.curText = curText;
        this.repaint();
    }
    
    public void setfColor(final String s) {
        this.fcolor = this.convColor(s, this.getForeground());
        this.repaint();
    }
    
    public void setfSize(final int fsize) {
        this.lst_fsize = this.fsize;
        this.fsize = fsize;
        this.curFont = new Font(this.fface, 0, this.fsize);
        this.repaint();
    }
    
    public void setXY(final int x_pos, final int x_pos2) {
        this.lst_x_pos = this.x_pos;
        this.lst_y_pos = this.y_pos;
        this.x_pos = x_pos;
        this.x_pos = x_pos2;
        this.repaint();
    }
    
    public void setCenter() {
        if (this.fcenter == 1) {
            this.fcenter = 0;
        }
        else {
            this.fcenter = 1;
        }
        this.repaint();
    }
    
    public void textCenter() {
        this.lst_x_pos = this.x_pos;
        this.lst_y_pos = this.y_pos;
        this.curMets = this.getFontMetrics(this.curFont);
        this.x_pos = (this.size().width - this.curMets.stringWidth(this.curText)) / 2;
        this.y_pos = (this.size().height + this.curMets.getHeight()) / 2;
    }
    
    public Color convColor(final String s, final Color color) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        try {
            return new Color(Integer.valueOf(stringTokenizer.nextToken()), Integer.valueOf(stringTokenizer.nextToken()), Integer.valueOf(stringTokenizer.nextToken()));
        }
        catch (Exception ex) {
            return color;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.fcenter == 1) {
            this.textCenter();
        }
        graphics.setFont(this.curFont = new Font(this.fface, 0, this.fsize));
        graphics.setColor(this.fcolor);
        graphics.drawString(this.curText, this.x_pos, this.y_pos);
    }
    
    public dynamictext() {
        this.curText = "";
        this.lstText = "";
    }
}
