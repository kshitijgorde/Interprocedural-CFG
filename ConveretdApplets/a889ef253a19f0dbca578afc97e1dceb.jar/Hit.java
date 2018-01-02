import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

final class Hit
{
    Random r;
    int speed;
    int x;
    int y;
    int size;
    int dx;
    int dy;
    int ds;
    int fieldX;
    int fieldY;
    boolean wall;
    
    public Hit() {
        this.r = new Random();
        this.speed = 0;
        this.x = 390;
        this.y = 390;
        this.size = 0;
        this.dx = 0;
        this.dy = 0;
        this.ds = 0;
        this.fieldX = -820;
        this.fieldY = -1450;
        this.wall = false;
    }
    
    public final int getX() {
        return this.x;
    }
    
    public final int getY() {
        return this.y;
    }
    
    public final int getFieldX() {
        return this.fieldX;
    }
    
    public final int getFieldY() {
        return this.fieldY;
    }
    
    public final int getSpeed() {
        return this.speed;
    }
    
    public final int getSize() {
        return this.size;
    }
    
    public final void increment(final int n) {
        if (!this.wall) {
            if (n == 0) {
                this.size += this.ds;
            }
            else {
                this.size -= this.ds;
            }
        }
        if (this.fieldX - this.dx < 0 && this.fieldY + this.dy < 0 && this.fieldX - this.dx > -600 && this.fieldY + this.dy > -600) {
            this.fieldX -= this.dx;
            this.fieldY += this.dy;
            return;
        }
        this.x += this.dx;
        if (!this.wall) {
            this.y -= this.dy;
        }
        else {
            this.y += this.dy / 2;
        }
        if (this.dy > 0 && this.size <= 4 && this.y < 140) {
            this.wall = true;
        }
    }
    
    public final boolean isHomerun() {
        return this.dy > 35 && this.ds > 0;
    }
    
    public final int getDistance() {
        return 500 - this.y / 2;
    }
    
    public final void setHit(final int n) {
        this.x = 200;
        this.y = 220;
        this.size = 4;
        this.fieldX = -300;
        this.fieldY = -600;
        this.dx = this.r.nextInt() % 10;
        if (this.r.nextInt() % 2 == 1) {
            this.dx = -this.dx;
        }
        this.dy = (Math.abs(this.r.nextInt() % 10) + 1) * n;
        if (this.r.nextInt() % 2 == 1) {
            this.dy = -this.dy;
        }
        this.ds = Math.abs(this.r.nextInt() % 5);
        this.speed = Math.abs(this.r.nextInt() % 3) * 25 + 75;
        this.wall = false;
    }
}
