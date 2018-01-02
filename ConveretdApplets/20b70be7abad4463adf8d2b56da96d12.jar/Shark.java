import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class Shark
{
    int lifeCount;
    int x;
    int y;
    int z;
    int type;
    static final int speed = 4;
    static final int popup = 40;
    static final int deadY = 50;
    static final int dieTime = 3;
    int deathCount;
    static final int DEAD = 0;
    static final int ALIVE = 1;
    static final int DYING = 2;
    int mode;
    
    void Shark() {
        this.mode = 0;
    }
    
    boolean moveShark() {
        if (this.mode == 1) {
            if (this.y < 40 && this.lifeCount > 0) {
                this.y += 4;
            }
            if (this.lifeCount > 0 && this.y >= 40) {
                --this.lifeCount;
            }
            if (this.lifeCount == 0) {
                this.y -= 4;
                if (this.y < 0) {
                    this.mode = 0;
                    return true;
                }
            }
        }
        if (this.mode == 2) {
            this.y -= 4;
            if (this.y < 0) {
                this.mode = 0;
            }
        }
        return false;
    }
    
    void createShark(final int l) {
        this.x = (int)Math.round(Math.random() * 300.0);
        this.z = (int)Math.round(Math.random() * 14.0) - 1;
        if (this.z < 1) {
            this.z = 1;
        }
        if (this.z > 11) {
            this.z = 11;
        }
        this.type = (int)Math.round(Math.random() * 7.0) - 1;
        if (this.type < 0) {
            this.type = 0;
        }
        if (this.type > 5) {
            this.type = 5;
        }
        this.y = 0;
        this.mode = 1;
        this.lifeCount = l;
    }
    
    boolean killShark(final int mx, final int my) {
        if (mx >= this.x && mx <= this.x + 100 && my >= 100 + this.z * 10 - this.y && my <= 110 + this.z * 10 && this.mode == 1) {
            this.mode = 2;
            this.deathCount = 3;
            return true;
        }
        return false;
    }
    
    Point getPos() {
        final Point p = new Point();
        p.x = this.x;
        p.y = 100 + this.z * 10 - this.y;
        return p;
    }
    
    void dive() {
        this.y -= 4;
        if (this.y < 0) {
            this.mode = 0;
        }
    }
    
    public Shark() {
        this.mode = 0;
    }
}
