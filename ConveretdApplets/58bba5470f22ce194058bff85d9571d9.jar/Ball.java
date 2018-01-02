import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.applet.Applet;
import java.awt.Component;
import java.awt.Image;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Ball
{
    public int pos_x;
    public int pos_y;
    public int vx;
    public int vy;
    public int radius;
    public boolean isStoped;
    private int ball_top;
    private int ball_bottom;
    private int ball_left;
    private int ball_right;
    private int player_top;
    private int player_bottom;
    private int player_right;
    private int comp_top;
    private int comp_bottom;
    private int comp_left;
    private final int max_vx;
    private final int max_vy;
    public Color ballfarbe;
    Image ballImg;
    Component mom;
    
    public Ball(final int radius, final int pos_x, final int pos_y, final int vx, final int vy, final Color ballfarbe, final Component mom) {
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.radius = radius;
        this.vx = vx;
        this.vy = vy;
        this.ballfarbe = ballfarbe;
        this.max_vx = 5;
        this.max_vy = 5;
        this.isStoped = true;
        this.ballImg = ((Applet)mom).getImage(((Applet)mom).getCodeBase(), "Ball.gif");
        this.mom = mom;
    }
    
    public void move() {
        if (this.vx > this.max_vx) {
            this.vx = this.max_vx;
        }
        else if (this.vx < -this.max_vx) {
            this.vx = -this.max_vx;
        }
        else if (this.vy > this.max_vy) {
            this.vy = this.max_vy;
        }
        else if (this.vy < -this.max_vy) {
            this.vy = -this.max_vy;
        }
        else if (this.vx == 0 && !this.isStoped) {
            this.vx = 1;
        }
        this.pos_x += this.vx;
        this.pos_y += this.vy;
    }
    
    public int wheresBall() {
        if (this.vy > 0) {
            if (this.pos_y > 330) {
                this.vy = -this.vy;
                return 0;
            }
        }
        else if (this.vy < 0 && this.pos_y < 20) {
            this.vy = -this.vy;
            return 0;
        }
        if (this.vx < 0) {
            if (this.pos_x < 15 && this.pos_y < 75) {
                this.vx = -this.vx;
                return 0;
            }
            if (this.pos_x < 15 && this.pos_y > 275) {
                this.vx = -this.vx;
                return 0;
            }
            if (this.pos_x < 15) {
                this.vx = -this.vx;
                return 1;
            }
            return 0;
        }
        else {
            if (this.vx <= 0) {
                return 0;
            }
            if (this.pos_x > 485 && this.pos_y < 75) {
                this.vx = -this.vx;
                return 0;
            }
            if (this.pos_x > 485 && this.pos_y > 275) {
                this.vx = -this.vx;
                return 0;
            }
            if (this.pos_x > 485) {
                this.vx = -this.vx;
                return 2;
            }
            return 0;
        }
    }
    
    public void PCollision(final Player player, final Player player2, final AudioClip audioClip) {
        this.ball_top = this.pos_y - this.radius;
        this.ball_bottom = this.pos_y + this.radius;
        this.ball_left = this.pos_x - this.radius;
        this.ball_right = this.pos_x + this.radius;
        this.player_top = Player.pos_y;
        this.player_bottom = Player.pos_y + player.size_y;
        if (this.ball_top >= this.player_top - 10 && this.ball_bottom <= this.player_bottom + 10 && (this.ball_left <= 25 || (this.ball_left <= 380 && this.ball_left >= 370))) {
            audioClip.play();
            this.vx = -this.vx;
            if (Player.vy < 0) {
                --this.vy;
            }
            else if (Player.vy > 0) {
                this.vy += Player.vy;
            }
        }
    }
    
    public void CCollision(final Computer computer, final Computer computer2, final AudioClip audioClip) {
        this.ball_top = this.pos_y - this.radius;
        this.ball_bottom = this.pos_y + this.radius;
        this.ball_left = this.pos_x - this.radius;
        this.ball_right = this.pos_x + this.radius;
        this.comp_top = Computer.pos_y;
        this.comp_bottom = Computer.pos_y + computer.size_y;
        if (this.ball_top >= this.comp_top - 10 && this.ball_bottom <= this.comp_bottom + 10 && (this.ball_right >= 475 || (this.ball_right <= 125 && this.ball_right >= 115))) {
            audioClip.play();
            this.vx = -this.vx;
            if (computer.vy < 0) {
                --this.vy;
            }
            else if (computer.vy > 0) {
                this.vy += computer.vy;
            }
        }
    }
    
    public void DrawBall(final Graphics graphics) {
        graphics.setColor(this.ballfarbe);
        graphics.fillOval(this.pos_x - this.radius, this.pos_y - this.radius, 2 * this.radius, 2 * this.radius);
        graphics.drawImage(this.ballImg, this.pos_x - 10, this.pos_y - 10, this.mom);
    }
    
    public int posX() {
        return this.pos_x;
    }
    
    public int posY() {
        return this.pos_y;
    }
}
