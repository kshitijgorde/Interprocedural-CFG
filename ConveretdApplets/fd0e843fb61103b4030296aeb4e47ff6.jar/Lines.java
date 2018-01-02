import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Lines
{
    int mathq;
    int checkmove;
    int movex;
    int movey;
    int movex1;
    int movey1;
    int movex2;
    int movey2;
    int movex21;
    int movey21;
    int width;
    int height;
    int x1;
    int y1;
    int speed;
    int changemovex;
    int changemovey;
    Graphics g;
    
    public Lines(final int width, final int height) {
        this.movex = 1;
        this.movey = 1;
        this.movex1 = 1;
        this.movey1 = 1;
        this.movex2 = 1;
        this.movey2 = 1;
        this.movex21 = 2;
        this.movey21 = 2;
        this.x1 = 1;
        this.y1 = 1;
        this.speed = 1;
        this.width = width;
        this.height = height;
        this.x1 = (int)(Math.random() * this.width);
        this.y1 = (int)(Math.random() * this.height);
        this.changemovex = (int)(Math.random() * 1.2 + 1.4);
        this.changemovey = (int)(Math.random() * 1.2 + 1.4);
    }
    
    public void move1() {
        this.movex += this.movex1;
        this.movey += this.movey1;
        if (this.movex <= 0 || this.movex >= this.width) {
            this.movex1 = -this.movex1;
            this.movex += this.movex1;
        }
        if (this.movey <= 0 || this.movey >= this.height) {
            this.movey1 = -this.movey1;
            this.movey += this.movey1;
        }
        this.movex2 += this.movex21;
        this.movey2 += this.movey21;
        if (this.movex2 <= 0 || this.movex2 >= this.width) {
            this.movex21 = -this.movex21;
            this.movex2 += this.movex21;
        }
        if (this.movey2 <= 0 || this.movey2 >= this.height) {
            this.movey21 = -this.movey21;
            this.movey2 += this.movey21;
        }
    }
    
    public void move2() {
        this.movex += this.movex1;
        this.movey += this.movey1;
        if (this.movex <= 0 || this.movex >= this.width) {
            this.movex1 = -this.movex1;
            this.movex += this.movex1;
        }
        if (this.movey <= 0 || this.movey >= this.height) {
            this.movey1 = -this.movey1;
            this.movey += this.movey1;
        }
        this.movex2 += this.movex21;
        this.movey2 += this.movey21;
        if (this.movex2 <= 0 || this.movex2 >= this.width) {
            this.movex21 = -this.movex21;
            this.movex2 += this.movex21;
        }
        if (this.movey2 <= 0 || this.movey2 >= this.height) {
            this.movey21 = -this.movey21;
            this.movey2 += this.movey21;
        }
        if (this.y1 == this.height) {
            this.y1 = 0;
        }
        else {
            this.y1 += this.speed;
        }
        if (this.x1 == this.width) {
            this.x1 = 0;
        }
        else {
            this.x1 += this.speed;
        }
    }
    
    public void move3() {
        this.movex2 += this.changemovex;
        this.movey2 += this.changemovey;
        if (this.movex2 <= 0 || this.movex2 >= this.width) {
            this.changemovex = -this.changemovex;
            this.movex2 += this.changemovex;
        }
        if (this.movey2 <= 0 || this.movey2 >= this.height) {
            this.changemovey = -this.changemovey;
            this.movey2 += this.changemovey;
        }
    }
    
    public void move4() {
        this.mathq = (int)(Math.random() * 15.0);
    }
    
    public void paint(final Graphics g) {
        this.g = g;
        ++this.checkmove;
        if (this.checkmove == 1000) {
            this.checkmove = 0;
        }
        if (this.checkmove > 400 && this.checkmove < 800) {
            this.movex2 = this.movex;
            this.movey2 = this.movey;
        }
        this.g.drawLine(this.movex, this.movey, this.x1 + this.mathq, this.y1);
        this.g.drawLine(this.movex2, this.movey2, this.x1 + this.mathq, this.y1);
    }
}
