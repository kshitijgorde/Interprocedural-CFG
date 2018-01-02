import java.awt.event.KeyEvent;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class under extends Applet implements KeyListener
{
    private int int;
    private b e;
    private int null;
    private boolean if;
    boolean do;
    private boolean case;
    Image[] a;
    Graphics goto;
    Graphics c;
    int else;
    int char;
    Image d;
    Graphics byte;
    int void;
    int new;
    AudioClip b;
    AudioClip for;
    AudioClip long;
    d try;
    
    public under() {
        this.int = 1;
        this.e = new b();
        this.null = 400;
        this.if = true;
        this.do = true;
        this.case = true;
        this.a = new Image[12];
        this.else = 500;
        this.char = 330;
        this.void = 100;
        this.new = 0;
    }
    
    public void init() {
        this.setLayout(null);
        this.d = this.createImage(this.else, this.char);
        this.byte = this.d.getGraphics();
        final String parameter = this.getParameter("highScore");
        try {
            if (Integer.parseInt(parameter) == 0) {
                this.if = false;
            }
        }
        catch (Exception ex) {}
        final String parameter2 = this.getParameter("highScoreID");
        try {
            this.null = Integer.parseInt(parameter2);
        }
        catch (Exception ex2) {
            this.null = 400;
        }
        this.try = new d(this.null, this.if);
        this.b = this.getAudioClip(this.getClass().getResource("click.au"));
        this.long = this.getAudioClip(this.getClass().getResource("loose.au"));
        this.for = this.getAudioClip(this.getClass().getResource("win.au"));
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.a[0] = this.getImage(this.getClass().getResource("lSkib.gif")), 0);
        mediaTracker.addImage(this.a[1] = this.getImage(this.getClass().getResource("sSkib.gif")), 0);
        mediaTracker.addImage(this.a[2] = this.getImage(this.getClass().getResource("lKlods.gif")), 0);
        mediaTracker.addImage(this.a[3] = this.getImage(this.getClass().getResource("sKlods.gif")), 0);
        mediaTracker.addImage(this.a[4] = this.getImage(this.getClass().getResource("lSten.gif")), 0);
        mediaTracker.addImage(this.a[5] = this.getImage(this.getClass().getResource("sSten.gif")), 0);
        mediaTracker.addImage(this.a[6] = this.getImage(this.getClass().getResource("lSkib_h1.gif")), 0);
        mediaTracker.addImage(this.a[7] = this.getImage(this.getClass().getResource("sSkib_h1.gif")), 0);
        mediaTracker.addImage(this.a[8] = this.getImage(this.getClass().getResource("back.gif")), 0);
        mediaTracker.addImage(this.a[9] = this.getImage(this.getClass().getResource("top.gif")), 0);
        mediaTracker.addImage(this.a[10] = this.getImage(this.getClass().getResource("button.gif")), 0);
        mediaTracker.addImage(this.a[11] = this.getImage(this.getClass().getResource("exit.gif")), 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex3) {}
        this.try.a(this.a, this.byte, this.b, this.for, this);
        this.requestFocus();
        this.repaint();
        this.addKeyListener(this);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.d, 0, 35, null);
        if (this.int == 1) {
            this.e.a(graphics);
        }
        graphics.drawImage(this.a[9], 0, 0, null);
        graphics.drawImage(this.a[10], 0, 360, null);
        this.do = true;
    }
    
    public void update(final Graphics graphics) {
        if (!this.case) {
            graphics.setClip(null);
            this.try.a(graphics);
            this.case = false;
            this.paint(graphics);
            graphics.setClip(null);
            graphics.setClip(20, 10, 200, 60);
            this.paint(graphics);
        }
        else {
            this.case = false;
            this.paint(graphics);
        }
    }
    
    public void char() {
        this.case = true;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (this.int == 1 && keyEvent.getKeyCode() == 32) {
            this.int = 0;
        }
        if (this.int == 0 && this.do) {
            this.do = false;
            if (keyEvent.getKeyCode() == 38) {
                this.a();
            }
            else if (keyEvent.getKeyCode() == 40) {
                this.do();
            }
            else if (keyEvent.getKeyCode() == 37) {
                this.new();
            }
            else if (keyEvent.getKeyCode() == 39) {
                this.try();
            }
            if (keyEvent.getKeyCode() == 32) {
                this.case();
            }
            else if (keyEvent.getKeyCode() == 27) {
                this.if();
            }
            else if (keyEvent.getKeyCode() == 85) {
                if (this.try.for()) {
                    this.new += 2;
                    if (this.void > 0) {
                        --this.void;
                    }
                }
                this.byte = this.try.a();
            }
        }
        if (this.try.do() == 1) {
            ++this.new;
            if (this.void > 0) {
                --this.void;
            }
        }
        this.repaint();
    }
    
    private void a() {
        this.try.a(true, null);
        this.byte = this.try.a();
    }
    
    public int a(final int n, final int n2) {
        return (n * 3 + 7) * 2 + (n2 + 5);
    }
    
    private void do() {
        this.try.do(true, null);
        this.byte = this.try.a();
    }
    
    private void new() {
        this.try.if(true, null);
        this.byte = this.try.a();
    }
    
    private void try() {
        this.try.for(true, null);
        this.byte = this.try.a();
    }
    
    private void case() {
        this.try.byte();
        this.b.play();
    }
    
    public int for() {
        return this.new;
    }
    
    private void if() {
        this.long.play();
        this.try.if();
        this.repaint();
        this.char();
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void int() {
        this.int = 2;
    }
    
    public void byte() {
        this.new = 0;
        this.int = 1;
        this.try.int = 0;
        this.try.int();
        this.char();
        this.repaint();
        this.requestFocus();
    }
}
