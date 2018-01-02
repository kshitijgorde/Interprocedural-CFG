// 
// Decompiled by Procyon v0.5.30
// 

public class Ball implements Runnable
{
    private static final int BALL_SIZE = 12;
    private static final int ADVANCE_AMT = 3;
    private static final int ADVANCE_DELAY = 32;
    private static final int PEN_SIZE = 4;
    private int heading;
    private Thread animationThread;
    private Turtle turtle;
    
    public Ball(final TGCanvas tgCanvas, final int n, final int n2, final int heading) {
        this.turtle = new Turtle(tgCanvas);
        this.heading = heading;
        this.turtle.hideturtle();
        this.turtle.penup();
        this.turtle.setxy(n, n2);
        this.turtle.pendown();
        this.turtle.setheading(this.heading);
        this.turtle.setpensize(4);
        this.drawBall();
        (this.animationThread = new Thread(this)).start();
    }
    
    public void run() {
        try {
            Thread.sleep(1000L);
        }
        catch (InterruptedException ex) {
            return;
        }
        while (Thread.currentThread() == this.animationThread) {
            try {
                Thread.sleep(32L);
            }
            catch (InterruptedException ex2) {
                return;
            }
            this.eraseBall();
            this.turtle.penup();
            this.turtle.forward(3);
            final int checkHit = Racquetball.checkHit(this.turtle.xcor(), this.turtle.ycor(), 12, this.heading);
            if (checkHit == this.heading) {
                this.turtle.pendown();
                this.drawBall();
            }
            else {
                if (checkHit == -1) {
                    this.drawBallExiting();
                    return;
                }
                this.turtle.back(3);
                this.turtle.pendown();
                this.drawBall();
                this.turtle.setheading(checkHit);
                this.heading = checkHit;
            }
        }
    }
    
    private void drawBall() {
        this.turtle.setpensize(4);
        this.turtle.setpencolor(4);
        for (int i = 12; i > 0; --i) {
            this.turtle.forward(6);
            this.turtle.back(6);
            this.turtle.right(30);
        }
    }
    
    private void drawBallExiting() {
        this.turtle.pendown();
        for (int i = 4; i > 0; --i) {
            try {
                Thread.sleep(32L);
            }
            catch (InterruptedException ex) {
                return;
            }
            this.eraseBall();
            this.turtle.forward(3);
        }
        this.drawBall();
    }
    
    private void eraseBall() {
        final int heading = this.turtle.heading();
        this.turtle.setpensize(13);
        this.turtle.setpencolor(7);
        this.turtle.setheading(0);
        this.turtle.pendown();
        this.turtle.forward(7);
        this.turtle.back(14);
        this.turtle.forward(7);
        this.turtle.setheading(heading);
    }
    
    public void interrupt() {
        this.animationThread.interrupt();
    }
    
    public boolean isAlive() {
        return this.animationThread.isAlive();
    }
}
