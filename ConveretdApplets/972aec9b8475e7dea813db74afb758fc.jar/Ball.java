import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Ball
{
    static final int DIAMETER = 21;
    private int m_x;
    private int m_y;
    private int m_velocityX;
    private int m_velocityY;
    private int m_rightBound;
    private int m_bottomBound;
    
    public Ball(final int x, final int y, final int velocityX, final int velocityY) {
        this.m_x = x;
        this.m_y = y;
        this.m_velocityX = velocityX;
        this.m_velocityY = velocityY;
    }
    
    public void setBounds(final int width, final int height) {
        this.m_rightBound = width - 21;
        this.m_bottomBound = height - 21;
    }
    
    public void move() {
        this.m_x += this.m_velocityX;
        this.m_y += this.m_velocityY;
        if (this.m_x < 0) {
            this.m_x = 0;
            this.m_velocityX = -this.m_velocityX;
        }
        else if (this.m_x > this.m_rightBound) {
            this.m_x = this.m_rightBound;
            this.m_velocityX = -this.m_velocityX;
        }
        if (this.m_y < 0) {
            this.m_y = 0;
            this.m_velocityY = -this.m_velocityY;
        }
        else if (this.m_y > this.m_bottomBound) {
            this.m_y = this.m_bottomBound;
            this.m_velocityY = -this.m_velocityY;
        }
    }
    
    public void draw(final Graphics g) {
        g.fillOval(this.m_x, this.m_y, 21, 21);
    }
    
    public int getDiameter() {
        return 21;
    }
    
    public int getX() {
        return this.m_x;
    }
    
    public int getY() {
        return this.m_y;
    }
    
    public void setPosition(final int x, final int y) {
        this.m_x = x;
        this.m_y = y;
    }
}
