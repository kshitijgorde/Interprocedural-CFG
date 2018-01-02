import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Monster
{
    int monsterx;
    int monstery;
    int starty;
    int speed;
    int aliens;
    boolean alive;
    boolean shot;
    int shotx;
    int shoty;
    int time;
    
    public Monster(final int monsterx, final int n) {
        this.speed = 5;
        this.alive = false;
        this.shot = false;
        this.time = 0;
        this.monsterx = monsterx;
        this.monstery = n;
        this.starty = n;
    }
    
    public void move() {
        if (this.monstery < this.starty - 56 && !this.shot) {
            this.speed *= -1;
        }
        if (this.monstery > this.starty + 8) {
            this.alive = false;
            this.monstery = this.starty;
            this.speed = 5;
            ++this.aliens;
        }
        this.monstery -= this.speed;
        if (this.shot) {
            ++this.time;
        }
        if (this.time > 1) {
            this.monstery = this.starty;
            this.alive = false;
            this.time = 0;
            this.shot = false;
            this.speed = 5;
            ++this.aliens;
        }
    }
    
    public boolean hit(final int n, final int n2) {
        if (n < this.monsterx + 46 && n > this.monsterx && n2 > this.monstery && n2 < this.monstery + 56 && !this.shot) {
            this.shot = true;
            this.shotx = this.monsterx;
            this.shoty = this.monstery;
            return true;
        }
        return false;
    }
    
    public void paint(final Graphics graphics) {
    }
}
