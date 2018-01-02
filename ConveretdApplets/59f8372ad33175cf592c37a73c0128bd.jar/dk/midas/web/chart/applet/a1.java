// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;

public class a1 extends ac implements Runnable
{
    private String long;
    private int char;
    private String null;
    private Thread goto;
    private int else;
    
    public a1(final String s, final int else1) {
        this.long = s;
        this.null = s;
        this.char = 2;
        this.else = else1;
    }
    
    private void for() {
        if (this.goto != null) {
            try {
                this.goto.stop();
            }
            catch (Throwable t) {}
            this.goto = null;
        }
    }
    
    private void int() {
        if (this.goto == null) {
            (this.goto = new Thread(this)).start();
        }
    }
    
    public void run() {
        try {
            Thread.sleep(this.else);
        }
        catch (InterruptedException ex) {}
        this.for();
        this.long = this.null;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        try {
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            final int width = this.getSize().width;
            final int height = this.getSize().height;
            if (width - 1 == 0 || height - 1 == 0) {
                return;
            }
            final Image image = this.createImage(width, height);
            if (image == null) {
                return;
            }
            final Graphics graphics2 = image.getGraphics();
            graphics2.setColor(this.getForeground());
            final int n = height - this.char * 2;
            final int n2 = (height - fontMetrics.getHeight()) / 2 + fontMetrics.getHeight() - 2;
            graphics2.drawString(this.long, this.char + 2, (n2 >= n) ? n : n2);
            graphics2.setColor(this.getBackground());
            graphics2.draw3DRect(this.char, this.char, width - (this.char * 2 + 1), height - (this.char * 2 + 1), false);
            graphics.drawImage(image, 0, 0, this);
            graphics2.dispose();
        }
        catch (Throwable t) {}
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void if(final String long1) {
        if (this.long.equals(long1)) {
            return;
        }
        this.for();
        this.long = long1;
        this.repaint();
    }
    
    public void new() {
        this.long = this.null;
        this.repaint();
    }
    
    public void a(final String long1) {
        if (this.long.equals(long1)) {
            return;
        }
        this.long = long1;
        this.repaint();
        this.for();
        this.int();
    }
}
