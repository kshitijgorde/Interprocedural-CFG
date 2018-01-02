import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Color;
import java.util.Vector;
import java.awt.Rectangle;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UFO_Attack extends Applet implements Runnable
{
    Image buffer;
    Image backdrop;
    Image bgimg;
    Image ufostrip;
    Image missile;
    Image missile_explosion;
    MediaTracker tracker;
    Graphics buf_g;
    Graphics bkd_g;
    Dimension window_size;
    Font font;
    Font font_s;
    AudioClip explosion;
    AudioClip newufo;
    AudioClip missile_launch;
    Thread game;
    boolean game_over;
    int mouse_x;
    Rectangle paint_area;
    Rectangle new_area;
    Launcher L;
    Missile M;
    Vector UV;
    Vector EV;
    int NU;
    int score;
    Color gunColor;
    Color mColor;
    Color ufoColor;
    Color scoreColor;
    Color bgColor;
    public static final int CENTER = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int FREE = 0;
    public static final int NORMAL = 0;
    public static final int SHADOW = 1;
    private int say_pos_y;
    private int say_pos_x;
    private int say_mode;
    private int say_style;
    private int say_margin;
    private Font say_font;
    
    public void init() {
        this.tracker = new MediaTracker(this);
        this.bgimg = this.getImage(this.getDocumentBase(), "bgimg.gif");
        this.tracker.addImage(this.bgimg, 0);
        this.ufostrip = this.getImage(this.getDocumentBase(), "ufostrip.gif");
        this.tracker.addImage(this.ufostrip, 0);
        this.missile = this.getImage(this.getDocumentBase(), "missile.gif");
        this.tracker.addImage(this.missile, 0);
        this.missile_explosion = this.getImage(this.getDocumentBase(), "explosionstrip.gif");
        this.tracker.addImage(this.missile_explosion, 0);
        this.font = new Font("Helvetica", 1, 24);
        this.font_s = new Font("Helvetica", 1, 14);
        this.bgColor = new Color(0, 0, 0);
        this.gunColor = new Color(0, 88, 0);
        this.mColor = new Color(255, 255, 255);
        this.ufoColor = new Color(255, 0, 0);
        this.scoreColor = new Color(0, 0, 255);
    }
    
    public void start() {
        this.getFrame(this).setCursor(1);
        this.window_size = this.size();
        this.mouse_x = this.window_size.width / 2;
        (this.L = new Launcher(this)).set_color(this.gunColor);
        ((Piece)(this.M = new Missile(this))).set_color(this.mColor);
        this.buffer = null;
        this.buffer = this.createImage(this.window_size.width, this.window_size.height);
        this.backdrop = null;
        this.backdrop = this.createImage(this.window_size.width, this.window_size.height);
        (this.buf_g = this.buffer.getGraphics()).setColor(this.bgColor);
        this.buf_g.fillRect(0, 0, this.window_size.width, this.window_size.height);
        this.buf_g.setColor(Color.red);
        this.buf_g.drawString("Loading...", 20, 20);
        this.repaint();
        long n = 0L;
        final Graphics graphics = this.getGraphics();
        while (!this.tracker.checkAll(true)) {
            if (n++ % 2L == 0L) {
                graphics.setColor(Color.red);
            }
            else {
                graphics.setColor(Color.black);
            }
            graphics.drawString("*", 10, 22);
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException ex) {}
        }
        if (this.tracker.isErrorAny()) {
            this.showStatus("Error getting images");
            return;
        }
        this.showStatus("Loading completed");
        graphics.dispose();
        (this.buf_g = this.buffer.getGraphics()).drawImage(this.bgimg, 0, 0, this);
        this.set_say_font(this.font);
        this.set_say_mode(1);
        this.set_say_style(1);
        this.say("UFO", 10, 80);
        this.say("ATTACK");
        this.set_say_font(this.font_s);
        this.set_say_style(0);
        this.say("");
        this.say("Click to start");
        this.say("a game");
        this.repaint();
        if (this.explosion == null) {
            this.explosion = this.getAudioClip(this.getDocumentBase(), "explosion.au");
        }
        if (this.newufo == null) {
            this.newufo = this.getAudioClip(this.getDocumentBase(), "sonar.au");
        }
        if (this.missile_launch == null) {
            this.missile_launch = this.getAudioClip(this.getDocumentBase(), "rocket.au");
        }
        this.game_over = true;
    }
    
    public void stop() {
        if (this.game != null) {
            this.game.stop();
            this.game = null;
        }
        this.getFrame(this).setCursor(0);
    }
    
    public void run() {
        long n = 0L;
        (this.buf_g = this.backdrop.getGraphics()).drawImage(this.bgimg, 0, 0, this);
        (this.buf_g = this.buffer.getGraphics()).drawImage(this.backdrop, 0, 0, this);
        this.repaint();
        this.display_score();
        this.L.draw();
        this.showStatus("UFO ATTACK");
    Block_16:
        while (true) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.UV.size() < this.NU && Math.random() > ((this.UV.size() == 0) ? 0.9 : 0.98)) {
                this.newufo.play();
                final UFO ufo = new UFO(this);
                ufo.set_color(this.ufoColor);
                if (this.score > 10 && Math.random() > 0.7) {
                    final UFO ufo2 = ufo;
                    --ufo2.vy;
                }
                this.UV.addElement(ufo);
            }
            for (int i = this.EV.size() - 1; i >= 0; --i) {
                final Explosion explosion = this.EV.elementAt(i);
                if (explosion.active) {
                    explosion.draw();
                }
                else {
                    explosion.erase();
                    this.EV.removeElementAt(i);
                }
            }
            this.L.move();
            if (((Piece)this.M).active()) {
                this.M.move();
            }
            for (int j = 0; j < this.UV.size(); ++j) {
                ((UFO)this.UV.elementAt(j)).move();
            }
            for (int k = this.UV.size() - 1; k >= 0; --k) {
                final UFO ufo3 = this.UV.elementAt(k);
                if (ufo3.active() && ((Piece)this.M).active() && ufo3.collision((Piece)this.M)) {
                    ++this.score;
                    this.explosion.stop();
                    this.display_score();
                    this.explosion.play();
                    if (this.NU < 5 && this.score % 10 == 1) {
                        ++this.NU;
                    }
                    ((Piece)this.M).active(false);
                    ((Piece)this.M).erase();
                    ufo3.active(false);
                    ufo3.erase();
                    this.EV.addElement(new Explosion(this, ufo3.px, ufo3.py));
                }
                if (ufo3.active()) {
                    ufo3.draw();
                }
                else {
                    this.UV.removeElementAt(k);
                }
                if (ufo3.py - ufo3.h / 2 <= 0) {
                    break Block_16;
                }
            }
            if (this.L.has_moved() || ((Piece)this.M).py - ((Piece)this.M).h < this.L.py + this.L.h || !((Piece)this.M).active()) {
                this.L.draw();
            }
            if (((Piece)this.M).active()) {
                this.M.draw();
            }
            final long n2 = 20L - (System.currentTimeMillis() - currentTimeMillis);
            final long n3 = (n2 > 0L) ? (10L + n2) : 10L;
            Thread.yield();
            try {
                Thread.sleep(n3);
            }
            catch (InterruptedException ex) {}
            if ((n = (n + 1L) % 100L) == 0L) {
                this.repaint();
            }
        }
        this.game_over = true;
        this.display_game_over();
    }
    
    public void display_score() {
        final Graphics graphics = this.backdrop.getGraphics();
        graphics.clipRect(this.window_size.width / 2, 0, this.window_size.width / 2, 40);
        graphics.drawImage(this.bgimg, 0, 0, this);
        graphics.setColor(Color.red);
        graphics.setFont(this.font);
        graphics.drawString(((this.score > 9) ? "" : "0") + this.score, this.window_size.width - 60, 30);
        graphics.dispose();
        final Graphics graphics2 = this.buffer.getGraphics();
        graphics2.clipRect(0, 0, this.window_size.width, 40);
        graphics2.drawImage(this.backdrop, 0, 0, this);
        graphics2.dispose();
        final Graphics graphics3 = this.getGraphics();
        graphics3.clipRect(0, 0, this.window_size.width, 40);
        graphics3.drawImage(this.buffer, 0, 0, this);
        graphics3.dispose();
    }
    
    public void display_game_over() {
        this.set_say_font(this.font);
        this.set_say_mode(1);
        this.set_say_style(1);
        this.set_say_pos(10, 80);
        this.say("GAME OVER");
        this.set_say_font(this.font_s);
        this.set_say_style(0);
        this.say("(click to start)");
        this.repaint();
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {}
    }
    
    public boolean mouseMove(final Event event, final int mouse_x, final int n) {
        this.mouse_x = mouse_x;
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.game_over) {
            this.game_over = false;
            if (this.game != null) {
                this.game.stop();
                this.game = null;
            }
            this.NU = 1;
            this.score = 0;
            ((Piece)this.M).active(false);
            this.UV.removeAllElements();
            this.EV.removeAllElements();
            (this.game = new Thread(this)).start();
            this.buf_g.dispose();
            return true;
        }
        if (this.M != null && !((Piece)this.M).active()) {
            this.missile_launch.stop();
            this.missile_launch.play();
            ((Piece)this.M).set_pos(this.L.px, this.L.py);
            ((Piece)this.M).active(true);
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.buffer != null) {
            graphics.drawImage(this.buffer, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Frame getFrame(Component parent) {
        while (parent != null && !(parent instanceof Frame)) {
            parent = parent.getParent();
        }
        return (Frame)parent;
    }
    
    public void say(final String s, final int n, final int n2) {
        this.set_say_pos(n, n2);
        this.say(s);
    }
    
    public void say(final String s) {
        final FontMetrics fontMetrics = this.getFontMetrics(this.say_font);
        switch (this.say_mode) {
            case 1: {
                this.say_pos_x = (this.window_size.width - fontMetrics.stringWidth(s)) / 2;
                break;
            }
            case 3: {
                this.say_pos_x = this.window_size.width - fontMetrics.stringWidth(s) - this.say_margin;
                break;
            }
            default: {
                this.say_pos_x = this.say_margin;
                break;
            }
        }
        final Graphics graphics = this.buffer.getGraphics();
        graphics.setFont(this.say_font);
        if (this.say_style == 1) {
            graphics.setColor(new Color(150, 150, 150));
            graphics.drawString(s, this.say_pos_x + 2, this.say_pos_y + 1);
        }
        graphics.setColor(Color.white);
        graphics.drawString(s, this.say_pos_x, this.say_pos_y);
        this.say_pos_y += (int)(1.2 * fontMetrics.getHeight());
        graphics.dispose();
    }
    
    public void set_say_mode(final int say_mode) {
        this.say_mode = say_mode;
    }
    
    public void set_say_style(final int say_style) {
        this.say_style = say_style;
    }
    
    public void set_say_font(final Font say_font) {
        this.say_font = say_font;
    }
    
    public void set_say_margin(final int say_margin) {
        this.say_margin = say_margin;
    }
    
    public void set_say_pos(final int say_pos_x, final int say_pos_y) {
        this.say_pos_x = say_pos_x;
        this.say_pos_y = say_pos_y;
    }
    
    public UFO_Attack() {
        this.game_over = true;
        this.mouse_x = 100;
        this.paint_area = new Rectangle();
        this.new_area = new Rectangle();
        this.UV = new Vector();
        this.EV = new Vector();
        this.NU = 1;
        this.say_mode = -1;
        this.say_style = -1;
        this.say_margin = 10;
    }
}
