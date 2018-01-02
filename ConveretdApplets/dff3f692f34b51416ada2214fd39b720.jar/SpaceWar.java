import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpaceWar extends Applet implements TGKeyHandler, TGMouseHandler
{
    private static final int HEIGHT = 400;
    private static final int WIDTH = 600;
    private static final int MOVE_STEPS = 5;
    private static final int NUM_PROJECTILES = 5;
    private static final int BUTTON_FONT_SIZE = 15;
    private static final int BUTTON_WIDTH = 125;
    private static final int BUTTON_HEIGHT = 25;
    private static final int START_X = -280;
    private static final int START_Y = -125;
    private static FlyingSaucer fs;
    private static Projectile[] projectiles;
    private static TGCanvas canvas;
    private static Turtle turtle;
    
    public void init() {
        this.setLayout(new BorderLayout());
        (SpaceWar.canvas = new TGCanvas(600, 400)).setBackground(Color.white);
        this.add("Center", SpaceWar.canvas);
        SpaceWar.canvas.addKeyHandler(this);
        SpaceWar.canvas.addMouseHandler(this);
        SpaceWar.turtle = new Turtle(SpaceWar.canvas);
        this.initialize();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawRect(0, 0, 599, 399);
    }
    
    public void keyPressed(final int n) {
        if (n == 129) {
            this.moveCannon(-5);
        }
        if (n == 130) {
            this.moveCannon(5);
        }
        if (n == 131) {
            this.fireCannon();
        }
    }
    
    public void mouseClick() {
        if (this.isMouseInRect(-280, -125, 25, 125)) {
            this.initialize();
            SpaceWar.fs = new FlyingSaucer(SpaceWar.canvas, -380, 100, 90, 760);
        }
    }
    
    private void buttonAt(final int n, final int n2, final String s) {
        SpaceWar.turtle.setpencolor(0);
        this.frameAt(n, n2, 25, 125, 2);
        SpaceWar.turtle.penup();
        SpaceWar.turtle.setxy(n + 4, n2 + 8);
        SpaceWar.turtle.pendown();
        SpaceWar.turtle.setLabelHeight(15);
        SpaceWar.turtle.label(s);
    }
    
    private void drawCannon() {
        SpaceWar.turtle.setpensize(1);
        SpaceWar.turtle.setpencolor(0);
        SpaceWar.turtle.setheading(270);
        SpaceWar.turtle.forward(10);
        SpaceWar.turtle.right(120);
        SpaceWar.turtle.forward(20);
        SpaceWar.turtle.right(120);
        SpaceWar.turtle.forward(20);
        SpaceWar.turtle.right(120);
        SpaceWar.turtle.forward(10);
    }
    
    private void eraseCannon() {
        SpaceWar.turtle.setpensize(22);
        SpaceWar.turtle.setpencolor(7);
        SpaceWar.turtle.setheading(0);
        SpaceWar.turtle.back(1);
        SpaceWar.turtle.forward(22);
        SpaceWar.turtle.back(21);
    }
    
    private void fireCannon() {
        int i;
        for (i = 0; i < SpaceWar.projectiles.length; ++i) {
            if (SpaceWar.projectiles[i] == null) {
                break;
            }
            if (!SpaceWar.projectiles[i].isAlive()) {
                break;
            }
        }
        if (i < SpaceWar.projectiles.length) {
            SpaceWar.projectiles[i] = new Projectile(SpaceWar.canvas, SpaceWar.turtle.xcor(), SpaceWar.turtle.ycor() + 20, 0, 260);
        }
    }
    
    private void frameAt(final int n, final int n2, final int n3, final int n4, final int n5) {
        SpaceWar.turtle.penup();
        SpaceWar.turtle.setxy(n, n2);
        SpaceWar.turtle.pendown();
        SpaceWar.turtle.setpensize(n5);
        SpaceWar.turtle.setpencolor(0);
        SpaceWar.turtle.setheading(0);
        for (int i = 2; i > 0; --i) {
            SpaceWar.turtle.forward(n3);
            SpaceWar.turtle.right(90);
            SpaceWar.turtle.forward(n4);
            SpaceWar.turtle.right(90);
        }
    }
    
    private void initialize() {
        SpaceWar.canvas.clean();
        SpaceWar.turtle.hideturtle();
        SpaceWar.turtle.setpensize(2);
        this.buttonAt(-280, -125, "Start/Restart");
        SpaceWar.turtle.penup();
        SpaceWar.turtle.setxy(0, -80);
        SpaceWar.turtle.pendown();
        this.drawCannon();
    }
    
    private boolean isMouseInRect(final int n, final int n2, final int n3, final int n4) {
        final int mousex = SpaceWar.canvas.mousex();
        final int mousey = SpaceWar.canvas.mousey();
        return mousex >= n && mousey >= n2 && mousex < n + n4 && mousey < n2 + n3;
    }
    
    private void moveCannon(final int n) {
        this.eraseCannon();
        SpaceWar.turtle.penup();
        SpaceWar.turtle.setx(SpaceWar.turtle.xcor() + n);
        SpaceWar.turtle.pendown();
        this.drawCannon();
    }
    
    public static boolean checkHit(final int n, final int n2, final int n3, final int n4) {
        return SpaceWar.fs.checkHit(n, n2, n3, n4);
    }
    
    static {
        SpaceWar.projectiles = new Projectile[5];
    }
}
