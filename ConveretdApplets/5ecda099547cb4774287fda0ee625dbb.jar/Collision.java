import processing.core.PApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Collision extends PApplet
{
    float ball_x;
    float ball_y;
    float ball_dir;
    float ball_size;
    float dy;
    int paddle_width;
    int paddle_height;
    int dist_wall;
    
    public Collision() {
        this.ball_dir = 1.0f;
        this.ball_size = 15.0f;
        this.dy = 0.0f;
        this.paddle_width = 10;
        this.paddle_height = 60;
        this.dist_wall = 15;
    }
    
    public void setup() {
        this.size(640, 360);
        this.rectMode(2);
        this.ellipseMode(2);
        this.noStroke();
        this.smooth();
        this.ball_y = this.height / 2;
        this.ball_x = 1.0f;
    }
    
    public void draw() {
        this.background(51);
        this.ball_x += this.ball_dir * 1.0f;
        this.ball_y += this.dy;
        if (this.ball_x > this.width + this.ball_size) {
            this.ball_x = -this.width / 2 - this.ball_size;
            this.ball_y = this.random(0.0f, this.height);
            this.dy = 0.0f;
        }
        final float n = PApplet.constrain(this.mouseY, this.paddle_height, this.height - this.paddle_height);
        if (this.ball_x == this.width - this.dist_wall - this.paddle_width - this.ball_size && this.ball_y > n - this.paddle_height - this.ball_size && this.ball_y < n + this.paddle_height + this.ball_size) {
            this.ball_dir *= -1.0f;
            if (this.mouseY != this.pmouseY) {
                this.dy = (this.mouseY - this.pmouseY) / 2.0f;
                if (this.dy > 5.0f) {
                    this.dy = 5.0f;
                }
                if (this.dy < -5.0f) {
                    this.dy = -5.0f;
                }
            }
        }
        if (this.ball_x < this.ball_size && this.ball_dir == -1.0f) {
            this.ball_dir *= -1.0f;
        }
        if (this.ball_y > this.height - this.ball_size) {
            this.dy *= -1.0f;
        }
        if (this.ball_y < this.ball_size) {
            this.dy *= -1.0f;
        }
        this.fill(255);
        this.ellipse(this.ball_x, this.ball_y, this.ball_size, this.ball_size);
        this.fill(153);
        this.rect(this.width - this.dist_wall, n, this.paddle_width, this.paddle_height);
    }
    
    public static void main(final String[] array) {
        PApplet.main(new String[] { "--present", "--bgcolor=#666666", "--stop-color=#cccccc", "Collision" });
    }
}
