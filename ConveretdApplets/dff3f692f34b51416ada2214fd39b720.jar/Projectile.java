// 
// Decompiled by Procyon v0.5.30
// 

public class Projectile implements Runnable
{
    private final int SIZE = 4;
    private final int DELAY = 100;
    private int endX;
    private int endY;
    private int heading;
    private int curX;
    private int curY;
    private Thread animationThread;
    private Turtle turtle;
    
    public Projectile(final TGCanvas tgCanvas, final int n, final int curY, final int n2, final int n3) {
        this.turtle = new Turtle(tgCanvas);
        this.curX = n;
        this.curY = curY;
        this.heading = 0;
        this.endX = n;
        this.endY = curY + n3;
        this.turtle.hideturtle();
        this.turtle.penup();
        this.turtle.setxy(n, curY);
        this.turtle.pendown();
        this.turtle.setheading(this.heading);
        this.turtle.setpensize(4);
        this.drawProjectile();
        (this.animationThread = new Thread(this)).start();
    }
    
    public void run() {
        while (this.curX <= this.endX && this.curY <= this.endY) {
            if (Thread.currentThread() != this.animationThread) {
                break;
            }
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            this.advanceProjectile(4);
            if (SpaceWar.checkHit(this.curX, this.curY, 4, 4)) {
                break;
            }
        }
    }
    
    private void advanceProjectile(final int n) {
        this.turtle.setpencolor(7);
        this.turtle.back(4);
        this.turtle.forward(4);
        this.drawProjectile();
    }
    
    private void drawProjectile() {
        this.turtle.setpencolor(0);
        this.turtle.forward(4);
        this.curX = this.turtle.xcor() - 2;
        this.curY = this.turtle.ycor() - 4;
    }
    
    public boolean isAlive() {
        return this.animationThread.isAlive();
    }
}
