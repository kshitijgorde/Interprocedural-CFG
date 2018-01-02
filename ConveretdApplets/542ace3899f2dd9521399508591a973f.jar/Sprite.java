import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Sprite implements Cloneable
{
    public static final int COIN = 0;
    public static final int DEADLY = 1;
    public static final int ENEMY = 2;
    public static final int DECO = 4;
    public static final int TRAP = 5;
    public static final int LOCK = 6;
    public static final int KEY = 7;
    public static final int GEM = 8;
    public static final int HEART = 9;
    public static final int EXIT = 10;
    public static final int EXIT_ = 11;
    Image img;
    int frameCount;
    protected int curFrame;
    int delayTime;
    int id;
    private boolean alive;
    protected Rectangle bounds;
    protected Rectangle bounds_;
    boolean moving;
    private int collisionGap;
    long lastTime;
    
    public Sprite(final Image img) {
        this.alive = true;
        this.moving = true;
        this.collisionGap = 0;
        this.lastTime = 0L;
        this.img = img;
        this.frameCount = img.getWidth(null) / 32;
        this.bounds = new Rectangle(0, 0, 32, 32);
        this.bounds_ = new Rectangle(0, 0, 32, 32);
        this.id = 0;
    }
    
    public void setCollisionGap(final int collisionGap) {
        this.collisionGap = collisionGap;
        this.bounds_.x = this.bounds.x + collisionGap;
        this.bounds_.y = this.bounds.y + collisionGap;
        final int c = collisionGap << 1;
        this.bounds_.width = this.bounds.width - c;
        this.bounds_.height = this.bounds.height - c;
    }
    
    public int getCollisionGap() {
        return this.collisionGap;
    }
    
    public int getFrameCount() {
        return this.frameCount;
    }
    
    public void setMoving(final boolean moving) {
        this.moving = moving;
    }
    
    public boolean isMoving() {
        return this.moving;
    }
    
    public void setId(final int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    protected void ping() {
        if (!this.alive || !this.moving) {
            return;
        }
        ++this.curFrame;
        if (this.curFrame >= this.frameCount) {
            this.curFrame = 0;
        }
    }
    
    public void incFrame() {
        final long cur = System.currentTimeMillis();
        if (this.lastTime + this.delayTime <= cur) {
            this.lastTime = cur;
            this.ping();
        }
    }
    
    public void setAlive(final boolean alive) {
        this.alive = alive;
    }
    
    public boolean isAlive() {
        return this.alive;
    }
    
    public void setPos(final int x, final int y) {
        this.bounds.x = x;
        this.bounds.y = y;
        this.bounds_.x = this.bounds.x + this.collisionGap;
        this.bounds_.y = this.bounds.y + this.collisionGap;
        final int c = this.collisionGap << 1;
        this.bounds_.width = this.bounds.width - c;
        this.bounds_.height = this.bounds.height - c;
    }
    
    public Rectangle getBounds() {
        return this.bounds;
    }
    
    public Rectangle getBoundingRect() {
        return this.bounds_;
    }
    
    public int getY() {
        return this.bounds.y;
    }
    
    public int getX() {
        return this.bounds.x;
    }
    
    public void setCurFrame(final int curFrame) {
        this.curFrame = curFrame;
    }
    
    public int getCurFrame() {
        return this.curFrame;
    }
    
    public void setDelayTime(final int delayTime) {
        this.delayTime = delayTime;
    }
    
    public int getDelayTime() {
        return this.delayTime;
    }
    
    public void paintAt(final Graphics g, final int x, final int y) {
        final int x_ = this.curFrame << 5;
        g.drawImage(this.img, x, y, x + 32, y + 32, x_, 0, x_ + 32, 32, null);
    }
    
    public void paint(final Graphics g) {
        final int x_ = this.curFrame << 5;
        final int x = this.bounds.x;
        final int y = this.bounds.y;
        g.drawImage(this.img, x, y, x + 32, y + 32, x_, 0, x_ + 32, 32, null);
    }
    
    public void paint(final Graphics g, final int dx) {
        final int x_ = this.curFrame << 5;
        final int x = this.bounds.x - dx;
        final int y = this.bounds.y;
        g.drawImage(this.img, x, y, x + 32, y + 32, x_, 0, x_ + 32, 32, null);
    }
    
    public Object clone() {
        final Sprite s = new Sprite(this.img);
        s.setPos(this.bounds.x, this.bounds.y);
        s.setDelayTime(this.delayTime);
        s.setId(this.id);
        return s;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf("[Sprite at: ").concat(String.valueOf(this.bounds.x))).concat(String.valueOf(", "))).concat(String.valueOf(this.bounds.y))).concat(String.valueOf("]"));
    }
}
