import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Frame;
import java.util.Random;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mondrian extends Applet implements Runnable
{
    Thread fadeno;
    Random hazarda;
    static int maks_x;
    static int maks_y;
    static final int maks_linioj = 7;
    static final int maks_rektanguloj = 7;
    int n_vertikalaj;
    int n_horizontalaj;
    int n_rektanguloj;
    Linio[] v_linioj;
    Linio[] h_linioj;
    Rekt[] rektanguloj;
    static final int l_largheco = 5;
    static final int l_spaco = 20;
    
    public Mondrian() {
        this.hazarda = new Random();
        this.v_linioj = new Linio[14];
        this.h_linioj = new Linio[14];
        this.rektanguloj = new Rekt[7];
    }
    
    public static void main(final String[] array) {
        final Frame frame = new Frame("   Mondrian - Klivo 1999");
        frame.show();
        frame.hide();
        frame.resize(380, 250);
        frame.show();
        final Mondrian mondrian = new Mondrian();
        frame.add("Center", mondrian);
        mondrian.init();
        Mondrian.maks_x = 380;
        Mondrian.maks_y = 250;
        frame.show();
        mondrian.start();
    }
    
    public void init() {
        (this.fadeno = new Thread(this)).start();
        Mondrian.maks_x = this.size().width;
        Mondrian.maks_y = this.size().height;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        this.n_vertikalaj = 0;
        this.n_horizontalaj = 0;
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, Mondrian.maks_x, Mondrian.maks_y);
        graphics.setColor(Color.black);
        this.kreu_v_liniojn((int)(this.hazarda.nextFloat() * 7.0f + 1.0));
        this.kreu_h_liniojn((int)(this.hazarda.nextFloat() * 7.0f + 1.0));
        this.kreu_v_liniojn2((int)(this.hazarda.nextFloat() * 4.0f + 1.0));
        this.kreu_h_liniojn2((int)(this.hazarda.nextFloat() * 4.0f + 1.0));
        this.kreu_rektangulojn();
        this.desegnu_rektangulojn(graphics);
        this.desegnu_v_liniojn(graphics);
        this.desegnu_h_liniojn(graphics);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.repaint();
        return true;
    }
    
    public void start() {
        if (this.fadeno.isAlive()) {
            this.fadeno.resume();
        }
        else {
            this.fadeno.start();
        }
        this.repaint();
    }
    
    public void stop() {
        this.fadeno.suspend();
    }
    
    public void destroy() {
        this.fadeno.stop();
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(60000L);
            }
            catch (InterruptedException ex) {
                this.stop();
            }
        }
    }
    
    public String getAppletInfo() {
        return "Mondrian Versio 1.0\nModerna arto.\nVerkita de Klivo  (klivo@infotrans.or.jp)\nJunio, 1999\n";
    }
    
    void desegnu_kradon(final Graphics graphics) {
        for (int i = 0; i < Mondrian.maks_x; i += 20) {
            graphics.drawLine(i, 0, i, Mondrian.maks_y);
        }
        for (int j = 0; j < Mondrian.maks_y; j += 20) {
            graphics.drawLine(0, j, Mondrian.maks_x, j);
        }
    }
    
    void kreu_v_liniojn(final int n) {
        for (int i = 0; i < n; ++i) {
            final int n2 = (int)(this.hazarda.nextFloat() * Mondrian.maks_x / 20.0f) * 20;
            this.v_linioj[i] = new Linio(n2, 0, n2, Mondrian.maks_y);
            ++this.n_vertikalaj;
        }
    }
    
    void kreu_v_liniojn2(final int n) {
        for (int i = 0; i < n; ++i) {
            final int n2 = (int)(this.hazarda.nextFloat() * Mondrian.maks_x / 20.0f) * 20;
            final int n3 = (int)(this.hazarda.nextFloat() * Mondrian.maks_y / 20.0f) * 20;
            this.v_linioj[this.n_vertikalaj] = new Linio(n2, this.supra_flanko(n2, n3), n2, this.malsupra_flanko(n2, n3));
            ++this.n_vertikalaj;
        }
    }
    
    void kreu_h_liniojn(final int n) {
        for (int i = 0; i < n; ++i) {
            final int n2 = (int)(this.hazarda.nextFloat() * Mondrian.maks_y / 20.0f) * 20;
            this.h_linioj[i] = new Linio(0, n2, Mondrian.maks_x, n2);
            ++this.n_horizontalaj;
        }
    }
    
    void kreu_h_liniojn2(final int n) {
        for (int i = 0; i < n; ++i) {
            final int n2 = (int)(this.hazarda.nextFloat() * Mondrian.maks_x / 20.0f) * 20;
            final int n3 = (int)(this.hazarda.nextFloat() * Mondrian.maks_y / 20.0f) * 20;
            this.h_linioj[this.n_horizontalaj] = new Linio(this.maldekstra_flanko(n2, n3), n3, this.dekstra_flanko(n2, n3), n3);
            ++this.n_horizontalaj;
        }
    }
    
    void desegnu_v_liniojn(final Graphics graphics) {
        for (int i = 0; i < this.n_vertikalaj; ++i) {
            graphics.fillRect(this.v_linioj[i].x1, this.v_linioj[i].y1, 5, this.v_linioj[i].y2 - this.v_linioj[i].y1);
        }
    }
    
    void desegnu_h_liniojn(final Graphics graphics) {
        for (int i = 0; i < this.n_horizontalaj; ++i) {
            graphics.fillRect(this.h_linioj[i].x1, this.h_linioj[i].y1, this.h_linioj[i].x2 - this.h_linioj[i].x1, 5);
        }
    }
    
    void kreu_rektangulojn() {
        this.n_rektanguloj = (int)(this.hazarda.nextFloat() * 7.0f + 1.0);
        if (this.n_rektanguloj >= 7) {
            this.n_rektanguloj = 3;
        }
        for (int i = 0; i < this.n_rektanguloj; ++i) {
            final int n = (int)(this.hazarda.nextFloat() * Mondrian.maks_x);
            final int n2 = (int)(this.hazarda.nextFloat() * Mondrian.maks_y);
            final int maldekstra_flanko = this.maldekstra_flanko(n, n2);
            final int supra_flanko = this.supra_flanko(n, n2);
            this.rektanguloj[i] = new Rekt(maldekstra_flanko, supra_flanko, this.dekstra_flanko(n, n2) - maldekstra_flanko, this.malsupra_flanko(n, n2) - supra_flanko);
        }
    }
    
    int maldekstra_flanko(final int n, final int n2) {
        int n3 = 0;
        for (int i = 0; i < this.n_vertikalaj; ++i) {
            final int x1 = this.v_linioj[i].x1;
            if (n2 > this.v_linioj[i].y1 && n2 < this.v_linioj[i].y2 && x1 > n3 && x1 < n) {
                n3 = x1;
            }
        }
        return n3;
    }
    
    int dekstra_flanko(final int n, final int n2) {
        int maks_x = Mondrian.maks_x;
        for (int i = 0; i < this.n_vertikalaj; ++i) {
            final int x1 = this.v_linioj[i].x1;
            if (n2 > this.v_linioj[i].y1 && n2 < this.v_linioj[i].y2 && x1 < maks_x && x1 > n) {
                maks_x = x1;
            }
        }
        return maks_x;
    }
    
    int supra_flanko(final int n, final int n2) {
        int n3 = 0;
        for (int i = 0; i < this.n_horizontalaj; ++i) {
            final int y1 = this.h_linioj[i].y1;
            if (n > this.h_linioj[i].x1 && n < this.h_linioj[i].x2 && y1 > n3 && y1 < n2) {
                n3 = y1;
            }
        }
        return n3;
    }
    
    int malsupra_flanko(final int n, final int n2) {
        int maks_y = Mondrian.maks_y;
        for (int i = 0; i < this.n_horizontalaj; ++i) {
            final int y1 = this.h_linioj[i].y1;
            if (n > this.h_linioj[i].x1 && n < this.h_linioj[i].x2 && y1 < maks_y && y1 > n2) {
                maks_y = y1;
            }
        }
        return maks_y;
    }
    
    void desegnu_rektangulojn(final Graphics graphics) {
        for (int i = 0; i < this.n_rektanguloj; ++i) {
            graphics.setColor(this.elektu_koloron());
            graphics.fillRect(this.rektanguloj[i].x, this.rektanguloj[i].y, this.rektanguloj[i].l, this.rektanguloj[i].a);
        }
        graphics.setColor(Color.black);
    }
    
    Color elektu_koloron() {
        final int n = (int)(this.hazarda.nextFloat() * 4.0f);
        if (n == 0) {
            return Color.black;
        }
        if (n == 1) {
            return Color.blue;
        }
        if (n == 2) {
            return Color.red;
        }
        if (n == 3) {
            return Color.yellow;
        }
        return Color.black;
    }
}
