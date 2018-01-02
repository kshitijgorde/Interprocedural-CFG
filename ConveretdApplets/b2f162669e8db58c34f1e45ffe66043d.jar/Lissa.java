import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Choice;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Lissa extends Applet implements Runnable
{
    final int Max = 3600;
    final int M1 = 3599;
    final int lbSize = 50;
    int[] arrSin;
    int height;
    int width;
    int iYo;
    int parM;
    int parN;
    int delay;
    Thread ScopeTrace;
    Choice chDelay;
    Choice chM;
    Choice chN;
    Label lbDelay;
    Label lbM;
    Label lbN;
    Image buffImage;
    Graphics buffGraphics;
    
    public Lissa() {
        this.arrSin = new int[3601];
        this.iYo = 0;
        this.parM = 1;
        this.parN = 2;
        this.delay = 10;
    }
    
    public void init() {
        this.height = Integer.parseInt(this.getParameter("height"));
        this.width = Integer.parseInt(this.getParameter("width"));
        final int n = this.width / 2;
        final int n2 = 1800;
        final double sin = Math.sin(3.14159265 / n2);
        final double cos = Math.cos(3.14159265 / n2);
        double n3 = -sin;
        double n4 = cos;
        for (int i = 0; i <= n2; ++i) {
            n3 = n3 * cos + n4 * sin;
            n4 = n4 * cos - n3 * sin;
            final int n5 = (int)(n * n3);
            this.arrSin[i] = n + n5;
            this.arrSin[i + n2] = n - n5;
        }
        this.chM = new Choice();
        this.chN = new Choice();
        for (int j = 1; j < 10; ++j) {
            this.chM.addItem(Integer.toString(j));
            this.chN.addItem(Integer.toString(j));
        }
        this.chN.select(this.parN - 1);
        this.add(this.lbM = new Label("M", 2));
        this.add(this.chM);
        this.add(this.lbN = new Label("N", 2));
        this.add(this.chN);
        this.add(this.lbDelay = new Label("Delay(ms)", 2));
        (this.chDelay = new Choice()).addItem("0");
        this.chDelay.addItem("10");
        this.chDelay.addItem("20");
        this.chDelay.addItem("50");
        this.chDelay.addItem("100");
        this.chDelay.addItem("200");
        this.chDelay.addItem("500");
        this.chDelay.addItem("999");
        this.chDelay.select("10");
        this.add(this.chDelay);
        this.buffImage = this.createImage(this.width, this.width);
        this.buffGraphics = this.buffImage.getGraphics();
    }
    
    public void paint(final Graphics graphics) {
        System.currentTimeMillis();
        int n = 0;
        int n2 = this.arrSin[0];
        int n3 = this.arrSin[this.iYo];
        this.buffGraphics.clearRect(0, 0, this.width, this.width);
        for (int i = 3600; i > 0; --i) {
            final int n4 = (n + this.parM) % 3600;
            final int iYo = (this.iYo + this.parN) % 3599;
            final int n5 = this.arrSin[n4];
            final int n6 = this.arrSin[iYo];
            this.buffGraphics.drawLine(n2, n3, n5, n6);
            n = n4;
            this.iYo = iYo;
            n2 = n5;
            n3 = n6;
        }
        graphics.drawImage(this.buffImage, 0, 50, this);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Choice) {
            if (event.target.equals(this.chDelay)) {
                this.delay = Integer.parseInt(this.chDelay.getSelectedItem());
            }
            else if (event.target.equals(this.chM)) {
                this.parM = this.chM.getSelectedIndex() + 1;
            }
            else {
                if (!event.target.equals(this.chN)) {
                    return false;
                }
                this.parN = this.chN.getSelectedIndex() + 1;
            }
            return true;
        }
        return false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        (this.ScopeTrace = new Thread(this)).start();
    }
    
    public void stop() {
        this.ScopeTrace.stop();
    }
}
