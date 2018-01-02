import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

final class Pitch
{
    int speed;
    int type;
    int x;
    int y;
    int dx;
    int dy;
    int size;
    Random r;
    
    public Pitch() {
        this.r = new Random();
        this.reset();
    }
    
    public final int getX() {
        return this.x;
    }
    
    public final int getY() {
        return this.y;
    }
    
    public final int getSize() {
        return this.size;
    }
    
    public final int getSpeed() {
        return this.speed;
    }
    
    public final void increment() {
        switch (this.type) {
            case 1:
            case 2: {
                this.x += this.dx;
                this.y += this.dy;
            }
            case 3: {
                if (this.size < 15) {
                    this.x += this.dx;
                }
                if (this.size > 25) {
                    this.x -= this.dx;
                }
                this.y += this.dy;
            }
            case 4: {
                this.x += this.dx;
                if (this.size < 15) {
                    this.y += this.dy;
                }
                if (this.size > 25) {
                    this.y -= this.dy;
                    return;
                }
                break;
            }
        }
    }
    
    public final void setPitch(final int type, final int n) {
        this.reset();
        this.type = type;
        if (this.type == 0) {
            this.type = Math.abs(this.r.nextInt() % 4) + 1;
        }
        switch (this.type) {
            case 1: {
                this.speed = (3 - n) * 25;
                break;
            }
            case 2: {
                this.speed = (3 - n) * 100;
                break;
            }
            case 3: {
                this.speed = (3 - n) * 50;
                break;
            }
            case 4: {
                this.speed = (3 - n) * 50;
                break;
            }
        }
        this.dx = this.r.nextInt() % 20 + 1;
        if (this.r.nextInt() % 2 == 0) {
            this.dx = -this.dx;
        }
        this.dy = this.r.nextInt() % 10;
        if (this.r.nextInt() % 2 == 0) {
            this.dy = -this.dy;
        }
        if (this.type == 4) {
            ++this.dy;
            this.dy = -Math.abs(this.dy);
        }
    }
    
    public final void reset() {
        this.x = 175;
        this.y = 175;
    }
}
