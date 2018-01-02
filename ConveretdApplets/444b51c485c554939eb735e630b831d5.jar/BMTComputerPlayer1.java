import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Color;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class BMTComputerPlayer1 extends BMTPlayer implements Runnable
{
    private Thread thread;
    private boolean firstMove;
    private Random random;
    
    BMTComputerPlayer1(final BMTField bmtField, final String s, final Color color) {
        super(bmtField, s, color);
        this.thread = null;
        this.firstMove = true;
        this.random = new Random();
    }
    
    private int rnd(final int n, final int n2) {
        return (int)((n2 - n + 1) * this.random.nextFloat() + n);
    }
    
    void start() {
        this.firstMove = true;
        super.start();
    }
    
    void stop() {
        this.thread = null;
        super.stop();
    }
    
    void update() {
        (this.thread = new Thread(this)).start();
    }
    
    public void run() {
        if (this.firstMove) {
            this.setDirection(this.rnd(0, 3));
            this.firstMove = false;
            return;
        }
        final Point location = this.getLocation();
        int direction = 0;
        int largestRect = this.getLargestRect(location.x, location.y - 1);
        final int largestRect2 = this.getLargestRect(location.x - 1, location.y);
        if (largestRect2 > largestRect + this.rnd(-2, 1)) {
            direction = 3;
            largestRect = largestRect2;
        }
        final int largestRect3 = this.getLargestRect(location.x, location.y + 1);
        if (largestRect3 > largestRect + this.rnd(-2, 1)) {
            direction = 2;
            largestRect = largestRect3;
        }
        if (this.getLargestRect(location.x + 1, location.y) > largestRect + this.rnd(-2, 1)) {
            direction = 1;
        }
        if (Thread.currentThread() == this.thread) {
            this.setDirection(direction);
        }
    }
    
    private int getLargestRect(final int n, final int n2) {
        if (!super.field.isFree(n, n2)) {
            return 0;
        }
        final Rectangle rectangle = new Rectangle(n, n2, 1, 1);
        boolean b = true;
        while (b) {
            final boolean b2 = false;
            boolean b3 = true;
            for (int i = rectangle.x; i <= rectangle.width + rectangle.x - 1; ++i) {
                b3 &= super.field.isFree(i, rectangle.y - 1);
                if (!b3) {
                    break;
                }
            }
            final boolean b4 = b2 | b3;
            if (b3) {
                final Rectangle rectangle2 = rectangle;
                --rectangle2.y;
                final Rectangle rectangle3 = rectangle;
                ++rectangle3.height;
            }
            boolean b5 = true;
            for (int j = rectangle.x; j <= rectangle.width + rectangle.x - 1; ++j) {
                b5 &= super.field.isFree(j, rectangle.y + rectangle.height);
                if (!b5) {
                    break;
                }
            }
            final boolean b6 = b4 | b5;
            if (b5) {
                final Rectangle rectangle4 = rectangle;
                ++rectangle4.height;
            }
            boolean b7 = true;
            for (int k = rectangle.y; k <= rectangle.height + rectangle.y - 1; ++k) {
                b7 &= super.field.isFree(rectangle.x - 1, k);
                if (!b7) {
                    break;
                }
            }
            final boolean b8 = b6 | b7;
            if (b7) {
                final Rectangle rectangle5 = rectangle;
                --rectangle5.x;
                final Rectangle rectangle6 = rectangle;
                ++rectangle6.width;
            }
            boolean b9 = true;
            for (int l = rectangle.y; l <= rectangle.height + rectangle.y - 1; ++l) {
                b9 &= super.field.isFree(rectangle.x + rectangle.width, l);
                if (!b9) {
                    break;
                }
            }
            b = (b8 | b9);
            if (b9) {
                final Rectangle rectangle7 = rectangle;
                ++rectangle7.width;
            }
        }
        return rectangle.width * rectangle.height;
    }
}
