import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Racquetball extends Applet implements Runnable, TGMouseHandler
{
    private static final int CANVAS_HEIGHT = 400;
    private static final int CANVAS_WIDTH = 600;
    private static final int COURT_MAX_X = 100;
    private static final int COURT_MIN_X = -100;
    private static final int COURT_MID_X = 0;
    private static final int COURT_MAX_Y = 100;
    private static final int COURT_MIN_Y = -100;
    private static final int COURT_MID_Y = 0;
    private static final int BUTTON_FONT_SIZE = 15;
    private static final int BUTTON_WIDTH = 70;
    private static final int BUTTON_HEIGHT = 25;
    private static final int START_X = -35;
    private static final int START_Y = -125;
    private static Ball ball;
    private static boolean gameOver;
    private static boolean gotOut;
    private static Court court;
    private static Paddle paddle;
    private static TGCanvas canvas;
    private static Thread masterThread;
    private static Turtle turtle;
    
    public void init() {
        this.setLayout(new BorderLayout());
        (Racquetball.canvas = new TGCanvas(600, 400)).setBackground(Color.white);
        this.add("Center", Racquetball.canvas);
        Racquetball.canvas.addMouseHandler(this);
        (Racquetball.turtle = new Turtle(Racquetball.canvas)).hideturtle();
        Racquetball.court = new Court(Racquetball.turtle, -100, 100, -100, 100);
        Racquetball.paddle = new Paddle(Racquetball.canvas, 0, -100);
    }
    
    public void start() {
        this.initialize();
    }
    
    public void stop() {
        Racquetball.ball.interrupt();
        Racquetball.paddle.stop();
        Racquetball.masterThread = null;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawRect(0, 0, 599, 399);
    }
    
    public void run() {
        for (int i = 40; i > 0; --i) {
            if (Thread.currentThread() != Racquetball.masterThread) {
                break;
            }
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {
                return;
            }
            if (Racquetball.gotOut) {
                break;
            }
        }
        Racquetball.gameOver = true;
        if (Racquetball.gotOut) {
            this.lose();
        }
        else {
            this.win();
        }
        try {
            Thread.sleep(1000L);
        }
        catch (InterruptedException ex2) {}
        this.buttonAt(-35, -125, "Restart");
    }
    
    public void mouseClick() {
        if (this.isMouseInRect(-35, -125, 25, 70)) {
            this.doStart();
        }
    }
    
    private void buttonAt(final int n, final int n2, final String s) {
        Racquetball.turtle.penup();
        Racquetball.turtle.setxy(n, n2 + 12);
        Racquetball.turtle.pendown();
        Racquetball.turtle.setpencolor(7);
        Racquetball.turtle.setpensize(25);
        Racquetball.turtle.setheading(90);
        Racquetball.turtle.forward(70);
        Racquetball.turtle.setpencolor(0);
        this.frameAt(n, n2, 25, 70, 2);
        Racquetball.turtle.penup();
        Racquetball.turtle.setxy(n + 4, n2 + 8);
        Racquetball.turtle.pendown();
        Racquetball.turtle.setLabelHeight(15);
        Racquetball.turtle.label(s);
    }
    
    private void doStart() {
        Racquetball.canvas.clean();
        Racquetball.court.paint();
        Racquetball.paddle.start();
        Racquetball.ball = new Ball(Racquetball.canvas, 0, 0, this.randomStartHeading());
        Racquetball.gameOver = false;
        Racquetball.gotOut = false;
        (Racquetball.masterThread = new Thread(this)).start();
    }
    
    private void frameAt(final int n, final int n2, final int n3, final int n4, final int n5) {
        Racquetball.turtle.penup();
        Racquetball.turtle.setxy(n, n2);
        Racquetball.turtle.pendown();
        Racquetball.turtle.setpensize(n5);
        Racquetball.turtle.setpencolor(0);
        Racquetball.turtle.setheading(0);
        for (int i = 2; i > 0; --i) {
            Racquetball.turtle.forward(n3);
            Racquetball.turtle.right(90);
            Racquetball.turtle.forward(n4);
            Racquetball.turtle.right(90);
        }
    }
    
    private void initialize() {
        Racquetball.canvas.clean();
        Racquetball.court.paint();
        this.buttonAt(-35, -125, "Start");
    }
    
    private boolean isMouseInRect(final int n, final int n2, final int n3, final int n4) {
        final int mousex = Racquetball.canvas.mousex();
        final int mousey = Racquetball.canvas.mousey();
        return mousex >= n && mousey >= n2 && mousex < n + n4 && mousey < n2 + n3;
    }
    
    private void lose() {
        Racquetball.paddle.stop();
        Racquetball.turtle.penup();
        Racquetball.turtle.setxy(-89, -20);
        Racquetball.turtle.setLabelHeight(34);
        Racquetball.turtle.setpencolor(1);
        Racquetball.turtle.label("You Lose!");
    }
    
    private int random(final int n) {
        return (int)Math.floor(n * Math.random());
    }
    
    private int randomInRange(final int n, final int n2) {
        return n + this.random(n2 - n);
    }
    
    private int randomStartHeading() {
        return this.randomInRange(30, 60) + 90 * this.random(4);
    }
    
    private void win() {
        Racquetball.ball.interrupt();
        Racquetball.paddle.stop();
        Racquetball.turtle.penup();
        Racquetball.turtle.setxy(-92, -20);
        Racquetball.turtle.setLabelHeight(40);
        Racquetball.turtle.setpencolor(1);
        Racquetball.turtle.label("You Win!");
    }
    
    public static int checkHit(final int n, final int n2, final int n3, final int n4) {
        if (n2 - n3 / 2 < -100) {
            final int checkHit = Racquetball.paddle.checkHit(n, n4);
            if (checkHit == -1) {
                Racquetball.gotOut = true;
            }
            return checkHit;
        }
        return Racquetball.court.checkHitWall(n, n2, n3, n4);
    }
}
