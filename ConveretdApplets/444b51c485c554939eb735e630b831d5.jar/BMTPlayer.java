import java.awt.Point;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class BMTPlayer
{
    protected BMTField field;
    int direction;
    String name;
    boolean stoped;
    int points;
    private int x;
    private int y;
    private Image appear;
    private BMTAnimator blowAnimator;
    private BMTAnimator appearAnimator;
    private Image[] snake;
    
    BMTPlayer(final BMTField field, final String name, final Color color) {
        this.direction = -1;
        this.stoped = true;
        this.points = 0;
        this.x = 0;
        this.y = 0;
        this.blowAnimator = null;
        this.appearAnimator = null;
        this.name = name;
        this.field = field;
        final MediaTracker mediaTracker = new MediaTracker(field.bmt);
        final BMTImageFilter bmtImageFilter = new BMTImageFilter(color);
        this.snake = new Image[field.bmt.skin.snake.length];
        for (int i = 0; i <= this.snake.length - 1; ++i) {
            mediaTracker.addImage(this.snake[i] = field.bmt.createImage(new FilteredImageSource(field.bmt.skin.snake[i].getSource(), bmtImageFilter)), i + 1);
        }
        mediaTracker.addImage(this.appear = field.bmt.createImage(new FilteredImageSource(field.bmt.skin.appear.getSource(), bmtImageFilter)), 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    int getDirection() {
        synchronized (this) {
            return this.direction;
        }
    }
    
    void setDirection(final int direction) {
        synchronized (this) {
            this.direction = direction;
        }
    }
    
    void setLocation(final int x, final int y) {
        synchronized (this) {
            this.x = x;
            this.y = y;
        }
    }
    
    Point getLocation() {
        synchronized (this) {
            return new Point(this.x, this.y);
        }
    }
    
    Image getImage(final int n) {
        return this.snake[n];
    }
    
    BMTAnimator getBlow() {
        if (this.blowAnimator == null) {
            this.blowAnimator = new BMTAnimator(this.field.bmt.skin.blow, 30, 30, 0, 50L);
        }
        return this.blowAnimator;
    }
    
    BMTAnimator getAppear() {
        if (this.appearAnimator == null) {
            this.appearAnimator = new BMTAnimator(this.appear, 50, 50, 0, 100L);
        }
        return this.appearAnimator;
    }
    
    void stop() {
        this.stoped = true;
    }
    
    void start() {
        this.stoped = false;
    }
    
    void reset() {
        if (!this.stoped) {
            this.stop();
        }
        this.blowAnimator = null;
        this.appearAnimator = null;
        this.direction = -1;
    }
    
    void update() {
    }
}
