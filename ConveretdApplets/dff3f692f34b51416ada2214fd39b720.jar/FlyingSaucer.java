// 
// Decompiled by Procyon v0.5.30
// 

public class FlyingSaucer implements Runnable
{
    private final int SAUCER_HEIGHT = 14;
    private final int SAUCER_WIDTH = 100;
    private final int DELAY = 110;
    private boolean hit;
    private int endX;
    private int endY;
    private int heading;
    private int curX;
    private int curY;
    private Thread saucerThread;
    private Turtle turtle;
    
    public FlyingSaucer(final TGCanvas tgCanvas, final int curX, final int n, final int n2, final int n3) {
        this.turtle = new Turtle(tgCanvas);
        this.curX = curX;
        this.curY = n;
        this.heading = 90;
        this.endX = curX + n3;
        this.endY = n;
        this.turtle.hideturtle();
        this.turtle.penup();
        this.turtle.setxy(curX, n);
        this.turtle.pendown();
        this.turtle.setheading(this.heading);
        this.drawSaucer();
        this.hit = false;
        (this.saucerThread = new Thread(this)).start();
    }
    
    public void run() {
        while (this.curX <= this.endX && this.curY <= this.endY) {
            if (Thread.currentThread() != this.saucerThread) {
                break;
            }
            try {
                Thread.sleep(110L);
            }
            catch (InterruptedException ex) {}
            if (this.hit) {
                this.splat();
                break;
            }
            this.advanceSaucer(10);
        }
    }
    
    private void advanceSaucer(final int n) {
        this.turtle.setpensize(42);
        this.turtle.setpencolor(7);
        this.turtle.back(2);
        this.turtle.forward(104);
        this.turtle.back(50 - n + 2);
        this.drawSaucer();
    }
    
    private synchronized void drawSaucer() {
        this.turtle.setpensize(10);
        this.turtle.penup();
        this.turtle.setpencolor(0);
        for (int i = 12; i > 0; --i) {
            this.turtle.forward(10);
            this.turtle.pendown();
            this.turtle.forward(10);
            this.turtle.penup();
            this.turtle.back(20);
            this.turtle.right(30);
        }
        this.turtle.setpensize(14);
        this.turtle.pendown();
        this.turtle.forward(50);
        this.turtle.back(100);
        this.curX = this.turtle.xcor();
        this.curY = this.turtle.ycor() - 7;
    }
    
    private boolean isXRangeInSaucer(final int n, final int n2) {
        return n2 >= this.curX && n <= this.curX + 100;
    }
    
    private boolean isYRangeInSaucer(final int n, final int n2) {
        return n2 >= this.curY && n <= this.curY + 14;
    }
    
    private int random(final int n) {
        return (int)Math.floor(n * Math.random());
    }
    
    private void splat() {
        this.turtle.setpensize(8);
        this.turtle.forward(50);
        for (int i = 60; i > 0; --i) {
            this.turtle.setpencolor(this.random(16));
            this.splatLine(this.random(100));
            this.turtle.right(6);
        }
    }
    
    private void splatLine(final int n) {
        this.turtle.forward(n);
        this.turtle.back(n);
    }
    
    public synchronized boolean checkHit(final int n, final int n2, final int n3, final int n4) {
        return this.isYRangeInSaucer(n2 - n3 / 2, n2 + n3 / 2) && this.isXRangeInSaucer(n - n4 / 2, n + n4 / 2) && (this.hit = true);
    }
}
