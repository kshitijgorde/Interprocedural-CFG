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
    private int[] hold;
    private boolean hyper;
    
    snow(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.maxsize = this.width * (this.height - 1);
        this.set();
        this.y = Math.abs(snowdrift.rd.nextInt()) % (this.height - 3) * this.width;
        if (Math.abs(snowdrift.rd.nextInt()) % 30 == 0) {
            this.hyper = true;
            this.size = 1 + Math.abs(snowdrift.rd.nextInt() & 0x1);
        }
        else {
            this.hyper = false;
            this.size = Math.abs(snowdrift.rd.nextInt() & 0x1);
        }
        switch (this.size) {
            case 0: {
                this.hold = new int[1];
                break;
            }
            case 1: {
                this.hold = new int[4];
                break;
            }
            case 2: {
                this.hold = new int[6];
                break;
            }
        }
    }
    
    public void draw(final int[] array) {
        final int n = this.y + this.x;
        this.hold[0] = array[n];
        array[n] = this.col;
        if (this.size > 0 && this.x > 0) {
            this.hold[1] = array[n - 1];
            array[n - 1] = this.col;
            if (this.y > this.width) {
                final int n2 = n - this.width;
                this.hold[2] = array[n2];
                this.hold[3] = array[n2 - 1];
                array[n2] = this.col2;
                array[n2 - 1] = this.col;
                if (this.size > 1 && this.x > 1) {
                    this.hold[4] = array[n2 - 2];
                    array[n2 - 2] = this.col;
                    if (this.y > this.width + this.width) {
                        final int n3 = n2 - this.width;
                        this.hold[5] = array[n3 - 1];
                        array[n3 - 1] = this.col2;
                    }
                }
            }
        }
    }
    
    public void erase(final int[] array) {
        this.erase(array, 0);
    }
    
    public void erase(final int[] array, final int n) {
        final int n2 = this.y + this.x;
        array[n2] = this.hold[0];
        if (this.size > 0 && this.x > 0) {
            array[n2 - 1] = this.hold[1];
            if (this.y > this.width) {
                final int n3 = n2 - this.width;
                array[n3] = this.hold[2];
                array[n3 - 1] = this.hold[3];
                if (this.size > 1 && this.x > 1) {
                    array[n3 - 2] = this.hold[4];
                    if (this.y > this.width + this.width) {
                        array[n3 - this.width - 1] = this.hold[5];
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
        if (this.hyper) {
            this.speed = 3 + Math.abs(rd.nextInt()) % 7;
        }
        else {
            this.speed = 1 + Math.abs(rd.nextInt()) % 4;
        }
        this.speedadd = this.speed * this.width;
        final int n = 200 + Math.abs(rd.nextInt()) % 55;
        this.col = (0xFF000000 | n << 16 | n << 8 | n);
        final int n2 = n - 30;
        this.col2 = (0xFF000000 | n2 << 16 | n2 << 8 | n2);
    }
}
