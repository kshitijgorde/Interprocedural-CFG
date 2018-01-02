import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class snow
{
    private int x;
    private int y;
    private int speed;
    private int speedadd;
    private int size;
    private int col;
    private int col2;
    private int width;
    private int height;
    private int maxsize;
    
    snow(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.maxsize = this.width * (this.height - 1);
        this.set();
        this.y = Math.abs(snowdrift.rd.nextInt()) % (this.height - 3) * this.width;
    }
    
    public void draw(final int[] array) {
        this.draw(array, 0);
    }
    
    public void draw(final int[] array, final int n) {
        final int n2 = this.y + this.x;
        array[n2] = this.col;
        if (this.size > 0 && this.x > 0) {
            array[n2 - 1] = this.col;
            if (this.y > this.width) {
                final int n3 = n2 - this.width;
                array[n3] = this.col2;
                array[n3 - 1] = this.col;
                if (this.size > 1 && this.x > 1) {
                    array[n3 - 2] = this.col;
                    if (this.y > this.width + this.width) {
                        array[n3 - this.width - 1] = this.col2;
                    }
                }
            }
        }
        if ((this.x += n + snowdrift.rd.nextInt() % 2) < 0) {
            this.x = this.width - 1;
        }
        else if (this.x >= this.width) {
            this.x = 0;
        }
        else if ((this.y += this.speedadd) >= this.maxsize) {
            this.set();
        }
    }
    
    public void set() {
        final Random rd = snowdrift.rd;
        this.x = 1 + Math.abs(rd.nextInt()) % (this.width - 2);
        this.y = 0;
        if (Math.abs(rd.nextInt()) % 30 == 0) {
            this.speed = 3 + Math.abs(rd.nextInt()) % 7;
            this.size = 1 + Math.abs(rd.nextInt() & 0x1);
        }
        else {
            this.speed = 1 + Math.abs(rd.nextInt()) % 4;
            this.size = Math.abs(rd.nextInt() & 0x1);
        }
        this.speedadd = this.speed * this.width;
        final int n = 200 + Math.abs(rd.nextInt()) % 55;
        this.col = (0xFF000000 | n << 16 | n << 8 | n);
        final int n2 = n - 30;
        this.col2 = (0xFF000000 | n2 << 16 | n2 << 8 | n2);
    }
}
