import java.awt.Point;
import java.awt.Color;
import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class BMTComputerPlayer2 extends BMTPlayer implements Runnable
{
    private Thread thread;
    private int[][] field2;
    private boolean firstMove;
    private Random random;
    private float k1;
    private float k2;
    private Object threadLock;
    
    BMTComputerPlayer2(final BMTField bmtField, final String s, final Color color) {
        super(bmtField, s, color);
        this.thread = null;
        this.firstMove = true;
        this.random = new Random();
        this.k1 = 1.0f;
        this.k2 = 1.0f;
        this.threadLock = new Object();
    }
    
    private int rnd(final int n, final int n2) {
        return (int)((n2 - n + 1) * this.random.nextFloat() + n);
    }
    
    void start() {
        this.firstMove = true;
        super.start();
    }
    
    void stop() {
        synchronized (this.threadLock) {
            this.thread = null;
        }
        super.stop();
    }
    
    void update() {
        synchronized (this.threadLock) {
            if (this.thread != null) {
                return;
            }
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void run() {
        if (this.firstMove) {
            this.setDirection(this.rnd(0, 3));
            this.firstMove = false;
            synchronized (this.threadLock) {
                this.thread = null;
            }
            return;
        }
        this.k1 = 1.0f - this.random.nextFloat() * 0.2f;
        this.k2 = this.random.nextFloat() * 2.0f + 2.0f;
        final Point location = this.getLocation();
        int direction = 0;
        float area = this.getArea(location.x, location.y - 1, location.x, location.y + 1);
        final float area2 = this.getArea(location.x - 1, location.y, location.x + 1, location.y);
        if (area2 > area | (this.rnd(0, 1) == 1 & area2 == area)) {
            direction = 3;
            area = area2;
        }
        final float area3 = this.getArea(location.x, location.y + 1, location.x, location.y - 1);
        if (area3 > area | (this.rnd(0, 1) == 1 & area3 == area)) {
            direction = 2;
            area = area3;
        }
        final float area4 = this.getArea(location.x + 1, location.y, location.x - 1, location.y);
        if (area4 > area | (this.rnd(0, 1) == 1 & area4 == area)) {
            direction = 1;
        }
        synchronized (this.threadLock) {
            if (Thread.currentThread() != this.thread) {
                return;
            }
            this.thread = null;
        }
        this.setDirection(direction);
    }
    
    private void resetField2() {
        this.field2 = new int[50][35];
        for (int i = 0; i <= super.field.players.length - 1; ++i) {
            if (super.field.players[i] != this & !super.field.players[i].stoped) {
                final Point location = super.field.players[i].getLocation();
                int n = location.x - 3;
                if (n < 0) {
                    n = 0;
                }
                int n2 = location.y - 3;
                if (n2 < 0) {
                    n2 = 0;
                }
                int n3 = location.x + 3;
                if (n3 > 49) {
                    n3 = 49;
                }
                int n4 = location.y + 3;
                if (n4 > 34) {
                    n4 = 34;
                }
                for (int j = n; j <= n3; ++j) {
                    for (int k = n2; k <= n4; ++k) {
                        if (Math.abs(j - location.x) <= 1 & Math.abs(k - location.y) <= 1) {
                            this.field2[j][k] = -1;
                        }
                        else {
                            this.field2[j][k] = 2;
                        }
                    }
                }
            }
        }
    }
    
    private float getArea(final int n, final int n2, final int n3, final int n4) {
        if (!super.field.isFree(n, n2)) {
            return Float.NEGATIVE_INFINITY;
        }
        this.resetField2();
        return this.getArea2(n, n2, n3, n4);
    }
    
    private float getArea2(final int n, final int n2, final int n3, final int n4) {
        if (!super.field.isFree(n, n2)) {
            return -1.0f;
        }
        if (Math.abs(n - n3) == Math.abs(n2 - n4) | this.field2[n][n2] == 1) {
            return 0.0f;
        }
        final float n5 = 2.0f;
        final boolean b = this.field2[n][n2] < 0;
        final boolean b2 = this.field2[n][n2] >= 2;
        this.field2[n][n2] = 1;
        final float area2 = this.getArea2(n, n2 - 1, n3, n4);
        final float area3 = this.getArea2(n, n2 + 1, n3, n4);
        if (area2 == -1.0f & area3 == -1.0f & (b | b2)) {
            return -1000.0f;
        }
        final float n6 = n5 + (area2 + area3);
        final float area4 = this.getArea2(n - 1, n2, n3, n4);
        final float area5 = this.getArea2(n + 1, n2, n3, n4);
        if (area4 == -1.0f & area5 == -1.0f & (b | b2)) {
            return -1000.0f;
        }
        float n7 = n6 + (area4 + area5);
        if (b) {
            n7 *= this.k1;
        }
        else if (b2) {
            n7 *= this.k2;
        }
        return n7;
    }
}
