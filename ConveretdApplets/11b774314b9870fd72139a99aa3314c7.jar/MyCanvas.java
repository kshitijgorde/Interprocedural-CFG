import java.awt.Event;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class MyCanvas extends Canvas
{
    boolean start;
    Paddle paddle;
    Paddle paddle1;
    Paddle paddle2;
    
    void setUpCanvas(final boolean start, final Paddle paddle, final Paddle paddle1, final Paddle paddle2) {
        this.start = start;
        this.paddle = paddle;
        this.paddle1 = paddle1;
        this.paddle2 = paddle2;
    }
    
    public boolean mouseMove(final Event evt, int x, final int y) {
        if (!this.start || x >= this.bounds().width - this.paddle.width / 2 || x <= this.paddle1.width) {
            return true;
        }
        x -= this.paddle.getWidth() / 2;
        this.paddle.setXpos(x);
        this.paddle1.setXpos(x - this.paddle1.width);
        this.paddle2.setXpos(x + this.paddle.width);
        return true;
    }
}
