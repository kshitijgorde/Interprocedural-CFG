import java.awt.Image;
import java.awt.image.MemoryImageSource;

// 
// Decompiled by Procyon v0.5.30
// 

class poolball
{
    static final byte[] D18;
    static final byte[] light18;
    static final byte[] shade;
    volatile double x;
    volatile double y;
    volatile double vx;
    volatile double vy;
    volatile byte collided;
    boolean moved;
    byte num;
    byte[] A0data;
    byte[] A45data;
    byte[] A90data;
    byte[] A135data;
    byte[] currentDirection;
    int angle;
    int rotate;
    int color;
    int[] picData;
    MemoryImageSource source;
    Image bd;
    volatile byte state;
    static final byte alive = 1;
    static final byte dying1 = 2;
    static final byte dying2 = 3;
    static final byte rising = 4;
    static final byte heaven = 5;
    
    public poolball() {
        this.picData = new int[324];
        for (int i = 0; i < 324; ++i) {
            if (poolball.D18[i] == 0) {
                this.picData[i] = 0;
            }
        }
        this.moved = true;
        this.rotate = (int)(36.0 * Math.random());
    }
    
    public void allocateMem() {
        final int n = 11664;
        this.A0data = new byte[n];
        this.A45data = new byte[n];
        this.A90data = new byte[n];
        this.A135data = new byte[n];
        this.currentDirection = this.A0data;
    }
    
    static {
        D18 = new byte[] { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 };
        light18 = new byte[] { 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 4, 4, 4, 4, 4, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 4, 8, 8, 8, 8, 8, 8, 8, 8, 4, 1, 0, 0, 0, 0, 0, 1, 4, 8, 13, 13, 13, 13, 13, 13, 13, 13, 8, 4, 1, 0, 0, 0, 0, 1, 4, 8, 13, 19, 19, 19, 19, 19, 19, 13, 8, 4, 1, 0, 0, 0, 1, 4, 8, 13, 19, 26, 26, 26, 26, 26, 26, 19, 13, 8, 4, 1, 0, 0, 1, 4, 8, 13, 19, 26, 34, 34, 34, 34, 26, 19, 13, 8, 4, 1, 0, 1, 4, 8, 13, 19, 26, 34, 44, 44, 44, 44, 34, 26, 19, 13, 8, 4, 1, 1, 4, 8, 13, 19, 26, 34, 44, 46, 46, 44, 34, 26, 19, 13, 8, 4, 1, 1, 4, 8, 13, 19, 26, 34, 44, 46, 46, 44, 34, 26, 19, 13, 8, 4, 1, 1, 4, 8, 13, 19, 26, 34, 44, 44, 44, 44, 34, 26, 19, 13, 8, 4, 1, 1, 4, 8, 13, 19, 26, 26, 34, 34, 34, 34, 34, 26, 19, 13, 8, 4, 1, 0, 1, 4, 8, 13, 19, 19, 26, 26, 26, 26, 26, 19, 13, 8, 4, 1, 0, 0, 1, 4, 8, 13, 13, 13, 19, 19, 19, 19, 19, 13, 13, 8, 4, 1, 0, 0, 0, 1, 4, 8, 8, 13, 13, 13, 13, 13, 13, 8, 8, 4, 1, 0, 0, 0, 0, 1, 4, 4, 4, 8, 8, 8, 8, 8, 8, 4, 4, 4, 1, 0, 0, 0, 0, 0, 0, 1, 1, 4, 4, 4, 4, 4, 4, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0 };
        shade = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0 };
    }
}
