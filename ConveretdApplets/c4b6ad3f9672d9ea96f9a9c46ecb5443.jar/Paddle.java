// 
// Decompiled by Procyon v0.5.30
// 

public class Paddle implements TGKeyHandler
{
    private static final int PADDLE_HEIGHT = 8;
    private static final int PADDLE_WIDTH = 46;
    private static final int PADDLE_HALF_WIDTH = 23;
    private static final int PADDLE_MOVE_STEPS = 5;
    private boolean active;
    private int centerX;
    private int paddleX;
    private int paddleY;
    private Turtle turtle;
    
    public Paddle(final TGCanvas tgCanvas, final int n, final int n2) {
        this.active = false;
        this.centerX = n;
        this.paddleX = n;
        this.paddleY = n2 - 4;
        (this.turtle = new Turtle(tgCanvas)).hideturtle();
        this.turtle.penup();
        this.turtle.setxy(this.paddleX, this.paddleY);
        this.turtle.pendown();
        this.turtle.setheading(90);
        this.turtle.setpensize(8);
        tgCanvas.addKeyHandler(this);
    }
    
    public void keyPressed(final int n) {
        if (!this.active) {
            return;
        }
        if (n == 129) {
            this.movePaddleLeft();
        }
        if (n == 130) {
            this.movePaddleRight();
        }
    }
    
    private void drawPaddle() {
        this.turtle.setpencolor(0);
        this.turtle.forward(23);
        this.turtle.back(46);
        this.turtle.forward(23);
        this.paddleX = this.turtle.xcor();
    }
    
    private void erasePaddle() {
        this.turtle.setpencolor(7);
        this.turtle.forward(24);
        this.turtle.back(48);
        this.turtle.forward(24);
    }
    
    private void movePaddleLeft() {
        this.erasePaddle();
        this.turtle.back(5);
        this.drawPaddle();
    }
    
    private void movePaddleRight() {
        this.erasePaddle();
        this.turtle.forward(5);
        this.drawPaddle();
    }
    
    public int checkHit(final int n, final int n2) {
        if (n < this.paddleX - 23 || n > this.paddleX + 23) {
            return -1;
        }
        if (n2 < 181) {
            return 90 - (n2 - 90);
        }
        return 270 + (90 - (n2 - 180));
    }
    
    public void start() {
        this.paddleX = this.centerX;
        this.turtle.penup();
        this.turtle.setxy(this.paddleX, this.paddleY);
        this.turtle.pendown();
        this.drawPaddle();
        this.active = true;
    }
    
    public void stop() {
        this.erasePaddle();
        this.active = true;
    }
}
