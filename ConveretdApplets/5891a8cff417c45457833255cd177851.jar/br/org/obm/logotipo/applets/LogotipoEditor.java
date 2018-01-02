// 
// Decompiled by Procyon v0.5.30
// 

package br.org.obm.logotipo.applets;

import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Color;
import br.org.obm.logotipo.Circle;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

public class LogotipoEditor extends Applet implements Runnable
{
    Thread runner;
    boolean rodando;
    int xpos;
    int xold;
    Image offscreenImg;
    Graphics offscreenG;
    Graphics myG;
    Circle cbase;
    Circle c0;
    Circle c0inv;
    Circle cbaseinv;
    Circle[] chome;
    Circle[] c;
    Circle[] cinv;
    Circle[] chomeinv;
    double a;
    double R;
    double k;
    double teta;
    double dteta;
    double r1;
    double PI;
    Color cfundo;
    Color cpreto;
    Color cazul;
    Color camarelo;
    Color cverde;
    Color cvermelho;
    Color ccinza;
    Color[] colors;
    int n;
    double xorig;
    double yorig;
    double scale;
    double xorig0;
    double yorig0;
    double scale0;
    Button b;
    Button mais;
    Button menos;
    Button zoom_in;
    Button zoom_out;
    Button lzoom_out;
    Button lzoom_in;
    Choice quantos;
    int hlogo;
    int vlogo;
    int hdraw;
    int vdraw;
    int topdraw;
    Rectangle clip;
    volatile boolean changed_geometry;
    Panel topp;
    Panel logop;
    Panel imagep;
    Panel lcontrolp;
    Label numcirc;
    
    public LogotipoEditor() {
        this.cbase = new Circle();
        this.c0 = new Circle();
        this.c0inv = new Circle();
        this.cbaseinv = new Circle();
        this.chome = new Circle[8];
        this.c = new Circle[8];
        this.cinv = new Circle[8];
        this.chomeinv = new Circle[8];
        this.PI = 3.14159;
        this.colors = new Color[] { Color.black, Color.green, Color.red, Color.blue, Color.yellow, Color.cyan, Color.magenta, Color.orange };
        this.n = 5;
        this.quantos = new Choice();
        this.hlogo = 180;
        this.vlogo = 180;
        this.hdraw = 520;
        this.vdraw = 160;
        this.topdraw = 40;
        this.clip = new Rectangle(0, this.topdraw, this.hdraw, this.vdraw);
        this.topp = new Panel();
        this.logop = new Panel();
        this.imagep = new Panel();
        this.lcontrolp = new Panel();
    }
    
    @Override
    public void init() {
        this.offscreenImg = this.createImage(this.hlogo, this.vlogo);
        this.offscreenG = this.offscreenImg.getGraphics();
        this.myG = this.getGraphics();
        this.setBackground(Color.white);
        for (int i = 0; i < 8; ++i) {
            this.c[i] = new Circle();
            this.cinv[i] = new Circle();
            this.chome[i] = new Circle();
            this.chomeinv[i] = new Circle();
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
        this.setLayout(new BorderLayout());
        this.add("North", this.topp);
        this.b = new Button("parar");
        this.lzoom_in = new Button("maior");
        this.lzoom_out = new Button("menor");
        this.lcontrolp.add(this.b);
        this.lcontrolp.add(this.lzoom_in);
        this.lcontrolp.add(this.lzoom_out);
        this.add("South", this.lcontrolp);
        this.menos = new Button("esquerda");
        this.topp.add("North", this.menos);
        this.mais = new Button("direita");
        this.topp.add("North", this.mais);
        this.zoom_in = new Button("ampliar");
        this.zoom_out = new Button("reduzir");
        this.topp.add("North", this.zoom_in);
        this.topp.add("North", this.zoom_out);
        this.quantos.addItem("3");
        this.quantos.addItem("4");
        this.quantos.addItem("5");
        this.quantos.addItem("6");
        this.quantos.addItem("7");
        this.quantos.addItem("8");
        this.quantos.select(2);
        this.numcirc = new Label("N\u00famero de c\u00edrculos: ");
        this.topp.add(this.numcirc);
        this.topp.add(this.quantos);
        this.R = 2.0;
        this.a = -8.0;
        this.teta = 0.0;
        this.dteta = 0.05;
        this.cbase.setX(0.0);
        this.cbase.setY(0.0);
        this.cbase.setR(this.R);
        this.scale0 = 90.0 / (2.0 * this.R);
        this.xorig0 = 90.0;
        this.yorig0 = 40 + this.vdraw / 2;
        this.scale = 28.0;
        this.xorig = this.hlogo / 2.0;
        this.yorig = this.hlogo / 2.0;
        this.rodando = true;
        this.initgeometry();
    }
    
    public void initgeometry() {
        this.k = this.a * this.a - this.R * this.R;
        this.k = -this.k;
        this.r1 = this.R / (1.0 + 1.0 / Math.sin(this.PI / this.n));
        this.c0.setR(this.R - 2.0 * this.r1);
        for (int i = 0; i < this.n; ++i) {
            this.c[i].setR(this.r1);
            double d = this.teta + 2 * i * this.PI / this.n;
            this.c[i].setX((this.R - this.r1) * Math.cos(d));
            this.c[i].setY((this.R - this.r1) * Math.sin(d));
            d = 2 * i * this.PI / this.n;
            this.chome[i].setR(this.r1);
            this.chome[i].setX((this.R - this.r1) * Math.cos(d));
            this.chome[i].setY((this.R - this.r1) * Math.sin(d));
            this.invertcirc(this.c[i], this.cinv[i]);
            this.invertcirc(this.chome[i], this.chomeinv[i]);
        }
        this.invertcirc(this.c0, this.c0inv);
        this.invertcirc(this.cbase, this.cbaseinv);
        this.changed_geometry = true;
        this.xorig = this.hlogo / 2 - this.cbaseinv.getX() * this.scale;
        this.repaint();
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
    
    @Override
    public boolean action(final Event event, final Object object) {
        if (event.target instanceof Button) {
            if (object == "girar") {
                this.rodando = true;
                this.b.setLabel("parar");
            }
            if (object == "parar") {
                this.rodando = false;
                this.b.setLabel("girar");
            }
            if (object == "direita") {
                this.a -= 0.51;
                this.initgeometry();
            }
            if (object == "esquerda") {
                this.a += 0.51;
                this.initgeometry();
            }
            if (object == "ampliar") {
                this.scale0 *= 1.1;
                this.initgeometry();
            }
            if (object == "reduzir") {
                this.scale0 /= 1.1;
                this.initgeometry();
            }
            if (object == "maior") {
                this.scale *= 1.1;
                this.initgeometry();
            }
            if (object == "menor") {
                this.scale /= 1.1;
                this.initgeometry();
            }
        }
        else if (event.target instanceof Choice) {
            this.n = Integer.parseInt((String)object);
            this.initgeometry();
        }
        this.initgeometry();
        return true;
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
        graphics.drawOval((int)((circle.getX() - circle.getR()) * this.scale0 + this.xorig0), (int)((circle.getY() - circle.getR()) * this.scale0 + this.yorig0), (int)(2.0 * this.scale0 * circle.getR()), (int)(2.0 * this.scale0 * circle.getR()));
    }
    
    public void DrawDisk(final Graphics graphics, final Circle circle) {
        graphics.fillOval((int)((circle.getX() - circle.getR()) * this.scale + this.xorig), (int)((circle.getY() - circle.getR()) * this.scale + this.yorig), (int)(2.0 * this.scale * circle.getR()), (int)(40.0 * circle.getR()));
    }
    
    public void Dot(final Graphics graphics, final double d, final double d_15_) {
        graphics.setColor(this.cpreto);
        graphics.fillOval((int)(d * this.scale0 + this.xorig0 - 1.0), (int)(d_15_ * this.scale0 + this.yorig0 - 1.0), 2, 2);
    }
    
    public void DrawCircleIn(final Graphics graphics, final Circle circle, final int i) {
        final int i_16_ = (int)((circle.getX() - circle.getR()) * this.scale + this.xorig);
        final int i_17_ = (int)((circle.getY() - circle.getR()) * this.scale + this.yorig);
        final int i_18_ = (int)(2.0 * this.scale * circle.getR());
        final int i_19_ = (int)(2.0 * this.scale * circle.getR());
        graphics.fillOval(i_16_, i_17_, i_18_, i_19_);
        graphics.setColor(this.cfundo);
        graphics.fillOval(i_16_ + i, i_17_ + i, i_18_ - 2 * i, i_19_ - 2 * i);
    }
    
    public void DrawCircleOut(final Graphics graphics, final Circle circle, final int i) {
        final int i_20_ = (int)((circle.getX() - circle.getR()) * this.scale + this.xorig);
        final int i_21_ = (int)((circle.getY() - circle.getR()) * this.scale + this.yorig);
        final int i_22_ = (int)(2.0 * this.scale * circle.getR());
        final int i_23_ = (int)(2.0 * this.scale * circle.getR());
        graphics.fillOval(i_20_ - i, i_21_ - i, i_22_ + 2 * i, i_23_ + 2 * i);
        graphics.setColor(this.cfundo);
        graphics.fillOval(i_20_, i_21_, i_22_, i_23_);
    }
    
    @Override
    public void run() {
        while (true) {
            if (this.rodando) {
                this.teta += this.dteta;
            }
            for (int i = 0; i < this.n; ++i) {
                final double d = this.teta + 2 * i * this.PI / this.n;
                this.c[i].setX((this.R - this.r1) * Math.cos(d));
                this.c[i].setY((this.R - this.r1) * Math.sin(d));
                this.invertcirc(this.c[i], this.cinv[i]);
            }
            this.offscreenG.setColor(this.cfundo);
            this.offscreenG.fillRect(0, 0, this.hlogo, this.vlogo);
            for (int i = 0; i < this.n; ++i) {
                if ((this.c[i].getX() + this.a) * (this.c[i].getX() + this.a) + this.c[i].getY() * this.c[i].getY() <= this.c[i].getR() * this.c[i].getR()) {
                    this.offscreenG.setColor(this.colors[i]);
                    this.DrawCircleOut(this.offscreenG, this.cinv[i], 3);
                }
            }
            this.offscreenG.setColor(this.ccinza);
            this.DrawCircleOut(this.offscreenG, this.cbaseinv, 3);
            this.offscreenG.setColor(this.ccinza);
            this.DrawCircleIn(this.offscreenG, this.c0inv, 3);
            for (int i = 0; i < this.n; ++i) {
                if ((this.c[i].getX() + this.a) * (this.c[i].getX() + this.a) + this.c[i].getY() * this.c[i].getY() > this.c[i].getR() * this.c[i].getR()) {
                    this.offscreenG.setColor(this.colors[i]);
                    this.DrawCircleIn(this.offscreenG, this.cinv[i], 3);
                }
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
    
    public void mydraw(final Graphics graphics) {
        if (this.changed_geometry) {
            graphics.clearRect(this.clip.x, this.clip.y, this.clip.width, this.clip.height);
        }
        this.changed_geometry = false;
        graphics.drawRect(this.clip.x + 1, this.clip.y + 1, this.clip.width - 2, this.clip.height - 2);
        graphics.setColor(this.cpreto);
        graphics.clipRect(this.clip.x, this.clip.y, this.clip.width, this.clip.height);
        this.DrawCircle(graphics, this.cbase);
        this.DrawCircle(graphics, this.cbaseinv);
        this.DrawCircle(graphics, this.c0);
        this.DrawCircle(graphics, this.c0inv);
        for (int i = 0; i < this.n; ++i) {
            graphics.setColor(this.colors[i]);
            this.DrawCircle(graphics, this.chomeinv[i]);
            this.DrawCircle(graphics, this.chome[i]);
        }
        this.Dot(graphics, -this.a, 0.0);
        graphics.drawString("O", (int)(-this.a * this.scale0 + this.xorig0), (int)(10.0 + this.yorig0));
    }
    
    @Override
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offscreenImg, 170, this.topdraw + this.vdraw + 10, this);
        this.mydraw(graphics);
    }
}
