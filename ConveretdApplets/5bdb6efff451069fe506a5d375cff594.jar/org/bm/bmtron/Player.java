// 
// Decompiled by Procyon v0.5.30
// 

package org.bm.bmtron;

import java.awt.Point;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Image;

public abstract class Player
{
    protected Field field;
    private int direction;
    final String name;
    private boolean stoped;
    int points;
    private int x;
    private int y;
    private Image appear;
    private Animator blowAnimator;
    private Animator appearAnimator;
    private Image[] snake;
    
    Player(final Field field, final String name, final Color color) {
        this.direction = -1;
        this.stoped = true;
        this.points = 0;
        this.x = 0;
        this.y = 0;
        this.blowAnimator = null;
        this.appearAnimator = null;
        this.name = name;
        this.field = field;
        final MediaTracker mediaTracker = new MediaTracker(field.game);
        final PlayerColorFilter playerColorFilter = new PlayerColorFilter(color);
        this.snake = new Image[field.game.skin.snake.length];
        for (int i = 0; i <= this.snake.length - 1; ++i) {
            mediaTracker.addImage(this.snake[i] = field.game.createImage(new FilteredImageSource(field.game.skin.snake[i].getSource(), playerColorFilter)), i + 1);
        }
        mediaTracker.addImage(this.appear = field.game.createImage(new FilteredImageSource(field.game.skin.appear.getSource(), playerColorFilter)), 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    synchronized int getDirection() {
        return this.direction;
    }
    
    synchronized void setDirection(final int direction) {
        this.direction = direction;
    }
    
    synchronized void setLocation(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    synchronized Point getLocation() {
        return new Point(this.x, this.y);
    }
    
    Image getImage(final int n) {
        return this.snake[n];
    }
    
    synchronized Animator getBlow() {
        if (this.blowAnimator == null) {
            final Image blow = this.field.game.skin.blow;
            this.field.getClass();
            final int n = 10 * 3;
            this.field.getClass();
            this.blowAnimator = new Animator(blow, n, 10 * 3, 0, 50L);
        }
        return this.blowAnimator;
    }
    
    synchronized Animator getAppear() {
        if (this.appearAnimator == null) {
            final Image appear = this.appear;
            this.field.getClass();
            final int n = 10 * 5;
            this.field.getClass();
            this.appearAnimator = new Animator(appear, n, 10 * 5, 0, 100L);
        }
        return this.appearAnimator;
    }
    
    synchronized void stop() {
        this.stoped = true;
    }
    
    synchronized void start() {
        this.stoped = false;
    }
    
    synchronized boolean isStoped() {
        return this.stoped;
    }
    
    synchronized void reset() {
        if (!this.stoped) {
            this.stop();
        }
        this.blowAnimator = null;
        this.appearAnimator = null;
        this.direction = -1;
    }
    
    synchronized void update() {
    }
}
