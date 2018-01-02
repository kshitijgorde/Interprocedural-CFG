import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ball
{
    public boolean alive;
    public Color color;
    private int ballwidth;
    private int ballheight;
    private int x_pos;
    private int y_pos;
    private int x_speed;
    private int y_speed;
    private int aim_adjust;
    private int appletsize_x;
    private int appletsize_y;
    private int maxspeed;
    private int first_x;
    private int first_y;
    private Random rnd;
    
    public Ball(final int n, final int n2, final int ballwidth, final int ballheight, final Color color, final int x_speed, final int y_speed, final int maxspeed, final boolean alive, final int appletsize_x, final int appletsize_y) {
        this.alive = true;
        this.rnd = new Random();
        this.x_pos = n;
        this.y_pos = n2;
        this.first_x = n;
        this.first_y = n2;
        this.ballwidth = ballwidth;
        this.ballheight = ballheight;
        this.color = color;
        this.x_speed = x_speed;
        this.y_speed = y_speed;
        this.maxspeed = maxspeed;
        this.alive = alive;
        this.appletsize_x = appletsize_x;
        this.appletsize_y = appletsize_y;
    }
    
    public void move() {
        this.y_pos += this.y_speed;
        this.x_pos += this.x_speed;
        this.isOut();
    }
    
    public boolean userHit(final double n, final double n2) {
        if (this.alive) {
            this.aim_adjust = this.ballwidth / 2;
            final double n3 = n - this.x_pos - this.aim_adjust;
            final double n4 = n2 - this.y_pos - this.aim_adjust;
            return Math.sqrt(n3 * n3 + n4 * n4) < this.aim_adjust;
        }
        return false;
    }
    
    private boolean isOut() {
        if (this.x_pos < 5) {
            if (this.x_speed < 1) {
                this.x_speed = (int)(Math.random() * this.maxspeed);
            }
            return true;
        }
        if (this.y_pos < 5) {
            if (this.y_speed < 1) {
                this.y_speed = (int)(Math.random() * this.maxspeed);
            }
            return true;
        }
        if (this.y_pos > this.appletsize_y) {
            if (this.y_speed > -1) {
                this.y_speed = (int)(Math.random() * this.maxspeed);
                this.y_speed -= this.y_speed * 2;
            }
            return true;
        }
        if (this.x_pos > this.appletsize_x) {
            if (this.x_speed > -1) {
                this.x_speed = (int)(Math.random() * this.maxspeed);
                this.x_speed -= this.x_speed * 2;
            }
            return true;
        }
        return true;
    }
    
    public void killBall() {
        this.alive = false;
    }
    
    public void drawBall(final Graphics graphics) {
        graphics.setColor(this.color);
        graphics.fillOval(this.x_pos, this.y_pos, this.ballwidth, this.ballheight);
    }
}
