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
    
    snow(final int xs, final int ys, final Random rd) {
        this.width = xs;
        this.height = ys;
        this.maxsize = this.width * (this.height - 1);
        this.set(rd);
        this.y = Math.abs(rd.nextInt()) % (this.height - 3) * this.width;
        if (Math.abs(rd.nextInt()) % 30 == 0) {
            this.hyper = true;
            this.size = 1 + Math.abs(rd.nextInt() & 0x1);
        }
        else {
            this.hyper = false;
            this.size = Math.abs(rd.nextInt() & 0x1);
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
    
    public void draw(final int[] field) {
        final int i = 0;
        int t = this.y + this.x;
        this.hold[0] = field[t];
        field[t] = this.col;
        if (this.size > 0 && this.x > 0) {
            this.hold[1] = field[t - 1];
            field[t - 1] = this.col;
            if (this.y > this.width) {
                t -= this.width;
                this.hold[2] = field[t];
                this.hold[3] = field[t - 1];
                field[t] = this.col2;
                field[t - 1] = this.col;
                if (this.size > 1 && this.x > 1) {
                    this.hold[4] = field[t - 2];
                    field[t - 2] = this.col;
                    final int top = this.width + this.width;
                    if (this.y > top) {
                        t -= this.width;
                        this.hold[5] = field[t - 1];
                        field[t - 1] = this.col2;
                    }
                }
            }
        }
    }
    
    public void erase(final int[] field, final Random rd) {
        this.erase(field, rd, 0);
    }
    
    public void erase(final int[] field, final Random rd, final int wind) {
        final int i = 0;
        int t = this.y + this.x;
        field[t] = this.hold[0];
        if (this.size > 0 && this.x > 0) {
            field[t - 1] = this.hold[1];
            if (this.y > this.width) {
                t -= this.width;
                field[t] = this.hold[2];
                field[t - 1] = this.hold[3];
                if (this.size > 1 && this.x > 1) {
                    field[t - 2] = this.hold[4];
                    final int top = this.width + this.width;
                    if (this.y > top) {
                        t -= this.width;
                        field[t - 1] = this.hold[5];
                    }
                }
            }
        }
        if ((this.x += wind + rd.nextInt() % 2) < 0) {
            this.x = this.width - 1;
        }
        else if (this.x >= this.width) {
            this.x = 0;
        }
        else if ((this.y += this.speedadd) >= this.maxsize) {
            this.set(rd);
        }
    }
    
    public void set(final Random rd) {
        this.x = 1 + Math.abs(rd.nextInt()) % (this.width - 2);
        this.y = 0;
        if (this.hyper) {
            this.speed = 3 + Math.abs(rd.nextInt()) % 7;
        }
        else {
            this.speed = 1 + Math.abs(rd.nextInt()) % 4;
        }
        this.speedadd = this.speed * this.width;
        int g;
        int b;
        int r = b = (g = 200 + Math.abs(rd.nextInt()) % 55);
        this.col = (0xFF000000 | r << 16 | g << 8 | b);
        r = (b = (g = r - 30));
        this.col2 = (0xFF000000 | r << 16 | g << 8 | b);
    }
}
