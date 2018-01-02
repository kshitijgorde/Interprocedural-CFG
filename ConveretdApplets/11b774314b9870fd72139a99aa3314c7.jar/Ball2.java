import java.awt.Graphics;
import java.util.Enumeration;
import java.applet.AudioClip;
import java.awt.Canvas;
import java.util.Vector;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Ball2
{
    int xpos;
    int ypos;
    int width;
    int height;
    int xvel;
    int yvel;
    Color color;
    Vector v;
    Canvas canvas1;
    Paddle p;
    Paddle p1;
    Paddle p2;
    AudioClip hitEdge;
    int velSave;
    AudioClip hitPaddle;
    
    Ball2(final int xpos, final int ypos, final int xvel, final int yvel, final int width, final int height, final Paddle p, final Canvas canvas1, final Paddle p1, final Paddle p2, final AudioClip hitEdge, final AudioClip hitPaddle) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.xvel = xvel;
        this.yvel = yvel;
        this.width = width;
        this.height = height;
        this.v = this.v;
        this.p = p;
        this.canvas1 = canvas1;
        this.p1 = p1;
        this.p2 = p2;
        this.hitEdge = hitEdge;
        this.velSave = Math.abs(xvel);
        this.hitPaddle = hitPaddle;
    }
    
    boolean testCollision(final Vector v) {
        final Enumeration e = v.elements();
        while (e.hasMoreElements()) {
            final Block block = e.nextElement();
            if (block.collision(this.xpos, this.ypos, this.width, this.height)) {
                this.yvel *= -1;
                v.removeElement(block);
                return true;
            }
        }
        if (this.p1.collision(this.xpos, this.ypos, this.width, this.height)) {
            if (this.xvel < 0) {
                this.xvel = -this.velSave;
            }
            else {
                this.xvel = this.velSave;
            }
            if (this.xvel < 0) {
                this.xvel *= -1;
            }
            this.yvel = this.velSave;
            this.xvel *= -1;
            this.yvel *= -1;
            this.hitPaddle.play();
            return true;
        }
        if (this.p2.collision(this.xpos, this.ypos, this.width, this.height)) {
            if (this.xvel < 0) {
                this.xvel = -this.velSave;
            }
            else {
                this.xvel = this.velSave;
            }
            if (this.xvel < 0) {
                this.xvel *= -1;
            }
            this.yvel = this.velSave;
            this.yvel *= -1;
            this.hitPaddle.play();
            return true;
        }
        if (this.p.collision(this.xpos, this.ypos, this.width, this.height)) {
            if (this.xvel < 0) {
                this.xvel = -1;
            }
            else {
                this.xvel = 1;
            }
            this.yvel = this.velSave;
            this.yvel *= -1;
            this.hitPaddle.play();
            return true;
        }
        return false;
    }
    
    void moveBall() {
        if (this.xpos <= 0) {
            this.xvel *= -1;
            if (this.xvel > 0 && Math.abs(this.xvel) < this.velSave + 6) {
                this.xvel += 2;
            }
            else {
                this.xvel -= 2;
            }
            this.xpos += this.xvel;
            ++this.xpos;
            this.hitEdge.play();
        }
        if (this.xpos + this.width >= this.canvas1.bounds().width) {
            this.xvel *= -1;
            if (this.xvel > 0 && Math.abs(this.xvel) < this.velSave + 6) {
                this.xvel += 2;
            }
            else {
                this.xvel -= 2;
            }
            this.xpos += this.xvel;
            --this.xpos;
            this.hitEdge.play();
        }
        if (this.ypos <= 0) {
            this.yvel *= -1;
            ++this.ypos;
            this.hitEdge.play();
        }
        this.xpos += this.xvel;
        this.ypos += this.yvel;
    }
    
    void setColor() {
        this.color = new Color((int)Math.floor(Math.random() * 250.0), (int)Math.floor(Math.random() * 250.0), (int)Math.floor(Math.random() * 250.0));
    }
    
    void pause(final int time) {
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException ex) {}
    }
    
    void paint(final Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.xpos, this.ypos, this.width, this.height);
    }
    
    void setXvel(final int xvel) {
        this.xvel = xvel;
        this.velSave = Math.abs(xvel);
    }
    
    void setYvel(final int yvel) {
        this.yvel = yvel;
    }
    
    int getXpos() {
        return this.xpos;
    }
    
    void setXpos(final int ypos) {
        this.xpos = this.xpos;
    }
    
    int getYpos() {
        return this.ypos;
    }
    
    void setYpos(final int ypos) {
        this.ypos = ypos;
    }
    
    void setColor(final Color color) {
        this.color = color;
    }
    
    void setSize(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
    
    void setVel(final int xvel, final int yvel) {
        this.xvel = xvel;
        this.yvel = yvel;
        this.velSave = Math.abs(xvel);
    }
    
    int getXvel() {
        return this.xvel;
    }
    
    int getYvel() {
        return this.yvel;
    }
}
