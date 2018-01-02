// 
// Decompiled by Procyon v0.5.30
// 

public class UFO
{
    int x;
    int y;
    int destX;
    int destY;
    int maxX;
    int maxY;
    int count;
    int shield;
    boolean hit;
    private boolean wantToAttack;
    private boolean attackPos;
    boolean attacking;
    boolean phase;
    boolean sound;
    boolean bomber;
    private static boolean hasBomber;
    
    public UFO(final int maxX, final int maxY) {
        this.hit = false;
        this.wantToAttack = true;
        this.attackPos = false;
        this.attacking = false;
        this.phase = false;
        this.sound = false;
        this.bomber = false;
        this.maxX = maxX;
        this.maxY = maxY;
        this.respawn();
    }
    
    public void respawn() {
        if (this.bomber) {
            UFO.hasBomber = false;
        }
        this.count = 0;
        this.hit = false;
        this.wantToAttack = false;
        this.attacking = false;
        this.attackPos = false;
        this.phase = false;
        this.sound = false;
        if (UFO.hasBomber) {
            this.bomber = false;
        }
        else {
            this.bomber = ((int)(Math.random() * 100) < 25);
        }
        if (this.bomber) {
            UFO.hasBomber = true;
            this.x = -100;
            this.y = 20;
            this.destX = this.maxX + 100;
            this.destY = 20;
            this.shield = 5;
        }
        else {
            this.x = (int)(Math.random() * this.maxX);
            this.y = -40 - (int)(Math.random() * 200);
            this.destX = (int)(Math.random() * this.maxX);
            this.destY = (int)(Math.random() * this.maxY / 2);
            this.shield = 0;
        }
    }
    
    public void step(final int d) {
        if (this.destX > this.x) {
            int x_ = this.x + d;
            if (x_ > this.destX) {
                x_ = this.destX;
            }
            this.x = x_;
        }
        else {
            int x_ = this.x - d;
            if (x_ < this.destX) {
                x_ = this.destX;
            }
            this.x = x_;
        }
        if (this.destY > this.y) {
            int y_ = this.y + d;
            if (y_ > this.destY) {
                y_ = this.destY;
            }
            this.y = y_;
        }
        else {
            int y_ = this.y - d;
            if (y_ < this.destY) {
                y_ = this.destY;
            }
            this.y = y_;
        }
        if (this.x == this.destX && this.y == this.destY) {
            this.changeDestination();
        }
        ++this.count;
        if (this.count > 50) {
            this.wantToAttack = true;
        }
    }
    
    public void changeDestination() {
        if (this.bomber) {
            this.respawn();
        }
        else if (this.wantToAttack) {
            if (this.attackPos) {
                this.attacking = true;
            }
            else {
                this.destX = (int)(Math.random() * this.maxX) - 30;
                this.destY = this.maxY / 2 + (int)(Math.random() * 50) - 25;
                this.attackPos = true;
                this.sound = true;
            }
        }
        else {
            this.destX = (int)(Math.random() * this.maxX);
            this.destY = (int)(Math.random() * this.maxY) / 2;
        }
    }
    
    static {
        UFO.hasBomber = false;
    }
}
