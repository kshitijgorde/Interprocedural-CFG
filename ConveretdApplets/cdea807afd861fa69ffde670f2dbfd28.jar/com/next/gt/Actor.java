// 
// Decompiled by Procyon v0.5.30
// 

package com.next.gt;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;

public abstract class Actor
{
    protected Image image;
    protected int numFrames;
    public int width;
    public int height;
    public int currentFrame;
    protected int hFrames;
    public double x;
    public double y;
    public double x_old;
    public double y_old;
    public double velocity_x;
    public double velocity_y;
    public Gamelication owner;
    public boolean wrapAround;
    
    public Actor() {
        this.currentFrame = 0;
        this.wrapAround = true;
    }
    
    public void tick() {
        this.calculateCurrentFrame();
        this.calculateNewPosition();
        this.calculateNewVelocity();
    }
    
    protected void setImage(final Image image, final int width, final int height, final int hFrames, final int numFrames) {
        this.width = width;
        this.height = height;
        this.numFrames = numFrames;
        this.hFrames = hFrames;
        this.image = image;
    }
    
    protected void setImage(final Image image) {
        int i;
        do {
            i = image.getHeight((ImageObserver)this.owner);
        } while (i == -1);
        int j;
        do {
            j = image.getWidth((ImageObserver)this.owner);
        } while (j == -1);
        this.setImage(image, j, i, 1, 1);
    }
    
    protected void setImage(final Image image, final int n, final int n2) {
        int i;
        do {
            i = image.getHeight((ImageObserver)this.owner);
        } while (i == -1);
        int j;
        do {
            j = image.getWidth((ImageObserver)this.owner);
        } while (j == -1);
        this.setImage(image, j / n, i / (int)Math.ceil(n2 / n), n, n2);
    }
    
    protected void calculateNewPosition() {
        final double n = this.owner.deltaTickTimeMillis() / 1000.0;
        this.x_old = this.x;
        this.y_old = this.y;
        this.x += this.velocity_x * n;
        this.y += this.velocity_y * n;
        if (this.wrapAround) {
            this.checkForOutOfBounds();
        }
    }
    
    protected void calculateNewVelocity() {
    }
    
    protected void calculateCurrentFrame() {
        if (++this.currentFrame >= this.numFrames) {
            this.currentFrame = 0;
        }
    }
    
    protected void checkForOutOfBounds() {
        if (this.x > ((Component)this.owner).getSize().width + this.width + this.width) {
            this.x = -this.width;
        }
        else if (this.x < -this.width) {
            this.x = ((Component)this.owner).getSize().width + this.width + this.width;
        }
        if (this.y > ((Component)this.owner).getSize().height + this.height + this.height) {
            this.y = -this.height;
            return;
        }
        if (this.y < -this.height) {
            this.y = ((Component)this.owner).getSize().height + this.height + this.height;
        }
    }
    
    public void draw(final Graphics graphics) {
        final double n = -(this.currentFrame % this.hFrames) * this.width;
        final double n2 = -Math.floor(this.currentFrame / this.hFrames) * this.height;
        final Graphics create = graphics.create((int)this.x, (int)this.y, this.width, this.height);
        create.drawImage(this.image, (int)n, (int)n2, (ImageObserver)this.owner);
        create.dispose();
    }
    
    protected void collideWithActor(final Actor actor) {
    }
    
    public void bounceOff(final Actor actor) {
        final double n = this.width / 2 + this.x_old;
        final double n2 = this.height / 2 + this.y_old;
        final double n3 = (n2 - (actor.height / 2 + actor.y)) / (n - (actor.width / 2 + actor.x));
        final double n4 = n2 - n3 * n;
        if (actor.x >= n) {
            final double n5 = n3 * actor.x + n4;
            if (n5 >= actor.y && n5 <= actor.y + actor.height) {
                this.velocity_x = actor.velocity_x - this.velocity_x;
                this.x = this.x_old;
            }
        }
        else if (actor.x + actor.width <= n) {
            final double n6 = n3 * (actor.x + actor.width) + n4;
            if (n6 >= actor.y && n6 <= actor.y + actor.height) {
                this.velocity_x = actor.velocity_x - this.velocity_x;
                this.x = this.x_old;
            }
        }
        else if (actor.y >= n2) {
            final double n7 = (actor.y - n4) / n3;
            if (n7 >= actor.x && n7 <= actor.x + actor.width) {
                this.velocity_y = actor.velocity_y - this.velocity_y;
                this.y = this.y_old;
            }
        }
        else if (actor.y + actor.height <= n2) {
            final double n8 = (actor.y + actor.height - n4) / n3;
            if (n8 >= actor.x && n8 <= actor.x + actor.width) {
                this.velocity_y = actor.velocity_y - this.velocity_y;
                this.y = this.y_old;
            }
        }
    }
}
