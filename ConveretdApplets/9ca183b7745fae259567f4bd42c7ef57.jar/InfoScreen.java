import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class InfoScreen extends Canvas
{
    Color inactiveColor;
    Color activeColor;
    Color bgcolor;
    Data data;
    Digit scoreLed1;
    int bullets;
    int ships;
    int score;
    int level;
    Graphics bufG;
    Image buf;
    
    public InfoScreen(final Data data) {
        this.data = data;
        this.buf = data.inv.getEmptyImage(100, 300);
        this.bufG = this.buf.getGraphics();
        this.bgcolor = data.bgColor;
        this.inactiveColor = data.fgColor;
        this.activeColor = new Color(0, 0, 200);
        this.setBackground(this.bgcolor);
        this.scoreLed1 = new Digit(this);
    }
    
    public void paint(final Graphics graphics) {
        this.inactiveColor = this.data.fgColor;
        this.scoreLed1.setColor(this.inactiveColor);
        this.bufG.setFont(new Font("Arial", 1, 16));
        this.bufG.setColor(this.bgcolor);
        this.buf.flush();
        this.bufG.clearRect(0, 0, 100, 300);
        this.bufG.fillRect(0, 0, 100, 300);
        this.bufG.setColor(this.inactiveColor);
        this.bufG.drawString("Score", 15, 160);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.score % 10000 / 1000), 0, 180, this);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.score % 1000 / 100), 20, 180, this);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.score % 100 / 10), 40, 180, this);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.score % 10), 60, 180, this);
        this.bufG.drawString("Level", 20, 90);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.level % 100 / 10), 20, 110, this);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.level % 10), 40, 110, this);
        this.bufG.drawString("Ships", 20, 20);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.ships % 100 / 10), 20, 40, this);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.ships % 10), 40, 40, this);
        graphics.drawImage(this.buf, 0, 0, this);
    }
    
    public void refresh() {
        this.bullets = this.data.getBulletsLeft();
        this.ships = this.data.getShipsLeft();
        this.level = this.data.getLevel();
        this.score = this.data.getScore();
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
