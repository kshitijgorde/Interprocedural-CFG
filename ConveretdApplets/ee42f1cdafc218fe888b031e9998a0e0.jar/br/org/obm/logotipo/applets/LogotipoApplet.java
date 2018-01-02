// 
// Decompiled by Procyon v0.5.30
// 

package br.org.obm.logotipo.applets;

import java.awt.image.ImageObserver;
import java.awt.Color;
import br.org.obm.logotipo.Circle;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

public class LogotipoApplet extends Applet implements Runnable
{
    Thread runner;
    int xpos;
    int xold;
    Image offscreenImg;
    Graphics offscreenG;
    Circle cbase;
    Circle c0;
    Circle c0inv;
    Circle[] c;
    Circle[] cinv;
    double a;
    double R;
    double k;
    double teta;
    double dteta;
    double r1;
    double PI;
    Color cfundo;
    Color ccinza;
    Color cazul;
    Color cpreto;
    Color cvermelho;
    Color camarelo;
    Color cverde;
    Color[] colors;
    int n;
    double xorig;
    double yorig;
    double scale;
    
    public LogotipoApplet() {
        this.cbase = new Circle();
        this.c0 = new Circle();
        this.c0inv = new Circle();
        this.c = new Circle[5];
        this.cinv = new Circle[5];
        this.PI = 3.14159;
        this.colors = new Color[] { Color.black, Color.green, Color.red, Color.blue, Color.yellow };
        this.n = 5;
    }
    
    @Override
    public void init() {
        this.offscreenImg = this.createImage(this.size().width, this.size().height);
        this.offscreenG = this.offscreenImg.getGraphics();
        for (int i = 0; i < 5; ++i) {
            this.c[i] = new Circle();
            this.cinv[i] = new Circle();
        }
        this.cfundo = new Color(255, 255, 255);
        this.cpreto = new Color(0, 0, 0);
        this.cazul = new Color(0, 153, 255);
        this.camarelo = new Color(255, 204, 0);
        this.cverde = new Color(0, 153, 51);
        this.cvermelho = new Color(255, 0, 0);
        this.ccinza = new Color(201, 201, 201);
        this.colors[0] = this.cpreto;
        this.colors[1] = this.cazul;
        this.colors[2] = this.camarelo;
        this.colors[3] = this.cverde;
        this.colors[4] = this.cvermelho;
        this.R = 2.0;
        this.a = 8.0;
        this.k = this.a * this.a - this.R * this.R;
        this.teta = 0.0;
        this.dteta = 0.05;
        this.cbase.setX(0.0);
        this.cbase.setY(0.0);
        this.cbase.setR(this.R);
        this.r1 = this.R / (1.0 + 1.0 / Math.sin(this.PI / this.n));
        this.c0.setR(this.R - 2.0 * this.r1);
        this.c0.setX(0.0);
        this.c0.setY(0.0);
        for (int i = 0; i < this.n; ++i) {
            this.c[i].setR(this.r1);
        }
        this.invertcirc(this.c0, this.c0inv);
        this.scale = (this.size().width - 8) / 4.0;
        this.xorig = this.size().width / 2.0;
        this.yorig = this.size().height / 2.0;
    }
    
    @Override
    public void start() {
        (this.runner = new Thread(this)).start();
    }
    
    @Override
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void invert(final double d, final double d_0_, double d_1_, double d_2_) {
        final double d_3_ = this.k / ((d + this.a) * (d + this.a) + d_0_ * d_0_);
        d_1_ = (d + this.a) * d_3_ - this.a;
        d_2_ = d_0_ * d_3_;
    }
    
    public void invertcirc(final Circle circle, final Circle circle_4_) {
        double d = 0.0;
        double d_5_ = 0.0;
        double d_6_ = 0.0;
        double d_7_ = 0.0;
        double d_8_ = Math.sqrt((circle.getX() + this.a) * (circle.getX() + this.a) + circle.getY() * circle.getY());
        final double d_9_ = (circle.getX() + this.a) / d_8_;
        final double d_10_ = circle.getY() / d_8_;
        final double d_11_ = circle.getX() + d_9_ * circle.getR();
        final double d_12_ = circle.getX() - d_9_ * circle.getR();
        final double d_13_ = circle.getY() + d_10_ * circle.getR();
        final double d_14_ = circle.getY() - d_10_ * circle.getR();
        d_8_ = this.k / ((d_11_ + this.a) * (d_11_ + this.a) + d_13_ * d_13_);
        d = (d_11_ + this.a) * d_8_ - this.a;
        d_5_ = d_13_ * d_8_;
        d_8_ = this.k / ((d_12_ + this.a) * (d_12_ + this.a) + d_14_ * d_14_);
        d_6_ = (d_12_ + this.a) * d_8_ - this.a;
        d_7_ = d_14_ * d_8_;
        circle_4_.setX((d + d_6_) / 2.0);
        circle_4_.setY((d_5_ + d_7_) / 2.0);
        circle_4_.setR(0.5 * Math.sqrt((d - d_6_) * (d - d_6_) + (d_5_ - d_7_) * (d_5_ - d_7_)));
    }
    
    public void DrawCircle(final Graphics graphics, final Circle circle) {
        graphics.drawOval((int)((circle.getX() - circle.getR()) * this.scale + this.xorig), (int)((circle.getY() - circle.getR()) * this.scale + this.yorig), (int)(2.0 * this.scale * circle.getR()), (int)(2.0 * this.scale * circle.getR()));
    }
    
    public void DrawDisk(final Graphics graphics, final Circle circle) {
        graphics.fillOval((int)((circle.getX() - circle.getR()) * this.scale + this.xorig), (int)((circle.getY() - circle.getR()) * this.scale + this.yorig), (int)(2.0 * this.scale * circle.getR()), (int)(40.0 * circle.getR()));
    }
    
    public void DrawCircleIn(final Graphics graphics, final Circle circle, final int i) {
        final int i_15_ = (int)((circle.getX() - circle.getR()) * this.scale + this.xorig);
        final int i_16_ = (int)((circle.getY() - circle.getR()) * this.scale + this.yorig);
        final int i_17_ = (int)(2.0 * this.scale * circle.getR());
        final int i_18_ = (int)(2.0 * this.scale * circle.getR());
        graphics.fillOval(i_15_, i_16_, i_17_, i_18_);
        graphics.setColor(this.cfundo);
        graphics.fillOval(i_15_ + i, i_16_ + i, i_17_ - 2 * i, i_18_ - 2 * i);
    }
    
    public void DrawCircleOut(final Graphics graphics, final Circle circle, final int i) {
        final int i_19_ = (int)((circle.getX() - circle.getR()) * this.scale + this.xorig);
        final int i_20_ = (int)((circle.getY() - circle.getR()) * this.scale + this.yorig);
        final int i_21_ = (int)(2.0 * this.scale * circle.getR());
        final int i_22_ = (int)(2.0 * this.scale * circle.getR());
        graphics.fillOval(i_19_ - i, i_20_ - i, i_21_ + 2 * i, i_22_ + 2 * i);
        graphics.setColor(this.cfundo);
        graphics.fillOval(i_19_, i_20_, i_21_, i_22_);
    }
    
    @Override
    public void run() {
        this.setBackground(this.cfundo);
        while (true) {
            this.teta += this.dteta;
            for (int i = 0; i < 5; ++i) {
                final double d = this.teta + 2 * i * this.PI / this.n;
                this.c[i].setX((this.R - this.r1) * Math.cos(d));
                this.c[i].setY((this.R - this.r1) * Math.sin(d));
                this.invertcirc(this.c[i], this.cinv[i]);
            }
            this.repaint();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException interruptedexception) {}
        }
    }
    
    @Override
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    @Override
    public void paint(final Graphics graphics) {
        this.offscreenG.setColor(this.cfundo);
        this.offscreenG.fillRect(0, 0, this.size().width, this.size().height);
        this.offscreenG.setColor(this.ccinza);
        this.DrawCircleOut(this.offscreenG, this.cbase, 3);
        this.offscreenG.setColor(this.ccinza);
        this.DrawCircleIn(this.offscreenG, this.c0inv, 3);
        for (int i = 0; i < 5; ++i) {
            this.offscreenG.setColor(this.colors[i]);
            this.DrawCircleIn(this.offscreenG, this.cinv[i], 3);
        }
        graphics.drawImage(this.offscreenImg, 0, 0, this);
    }
}
