import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class star
{
    private int x;
    private int y;
    private int speed;
    private int trail;
    private int size;
    private int[] c;
    private int[] hold1;
    private int[] hold2;
    private int[] hold3;
    private int[] hold4;
    private int width;
    private int height;
    private boolean hyper;
    
    star(final int width, final int height) {
        this.c = new int[10];
        this.width = width;
        this.height = height;
        this.set();
        this.x = Math.abs(stargaze.rd.nextInt()) % this.width;
        if (Math.abs(stargaze.rd.nextInt()) % 40 == 0) {
            this.hyper = true;
            this.hold1 = new int[7];
            this.hold2 = new int[7];
            this.hold3 = new int[7];
            this.hold4 = new int[7];
        }
        else {
            this.hyper = false;
            this.hold1 = new int[3];
            this.hold2 = new int[3];
            this.hold3 = new int[3];
            this.hold4 = new int[3];
        }
    }
    
    public void drawCopy(final int[] array) {
        int n = 0;
        int x = this.x;
        do {
            final int n2 = this.y + x;
            final int n3 = this.c[n];
            this.hold1[n] = array[n2];
            array[n2] = n3;
            if (this.size > 0 && x > 0) {
                this.hold2[n] = array[n2 - 1];
                array[n2 - 1] = n3;
                final int n4 = n2 + this.width;
                this.hold3[n] = array[n4 - 1];
                array[n4 - 1] = n3;
                this.hold4[n] = array[n4];
                array[n4] = n3;
            }
            x -= this.speed;
        } while (++n < this.trail && x >= 0);
    }
    
    public void eraseCopy(final int[] array) {
        int n = 0;
        int x = this.x;
        do {
            x -= this.speed;
        } while (++n < this.trail && x >= 0);
        --n;
        int n2 = x + this.speed;
        do {
            final int n3 = this.y + n2;
            array[n3] = this.hold1[n];
            if (this.size > 0 && n2 > 0) {
                array[n3 - 1] = this.hold2[n];
                final int n4 = n3 + this.width;
                array[n4 - 1] = this.hold3[n];
                array[n4] = this.hold4[n];
            }
            n2 += this.speed;
        } while (--n >= 0 && n2 <= this.x);
        if ((this.x += this.speed) >= this.width) {
            this.set();
        }
    }
    
    public void set() {
        final Random rd = stargaze.rd;
        this.x = 0;
        this.y = (1 + Math.abs(rd.nextInt()) % (this.height - 2)) * this.width;
        if (this.hyper) {
            this.speed = 7 + Math.abs(rd.nextInt()) % 4;
            this.size = Math.abs(rd.nextInt() & 0x1);
            this.trail = 4 + Math.abs(rd.nextInt() & 0x3);
        }
        else {
            this.speed = 1 + Math.abs(rd.nextInt()) % 4;
            this.size = Math.abs(rd.nextInt() & 0x1);
            this.trail = Math.abs(rd.nextInt() & 0x3);
        }
        int n = 100 + Math.abs(rd.nextInt()) % 75;
        int n2 = 150 + Math.abs(rd.nextInt()) % 55;
        int n3 = 200 + Math.abs(rd.nextInt()) % 55;
        int n4 = 0;
        do {
            this.c[n4] = (0xFF000000 | n << 16 | n2 << 8 | n3);
            if (n != 0) {
                n >>= 1;
            }
            if (n2 != 0) {
                n2 >>= 1;
            }
            if (n3 != 0) {
                n3 >>= 1;
            }
        } while (++n4 < this.trail + 1);
    }
}
