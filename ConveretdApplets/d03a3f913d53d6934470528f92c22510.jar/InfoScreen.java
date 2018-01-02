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
    protected Color inactiveColor;
    protected Color activeColor;
    protected Color bgcolor;
    protected Data data;
    private Digit scoreLed1;
    protected int bullets;
    protected int ships;
    protected int score;
    protected int level;
    private Graphics bufG;
    private Image buf;
    
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
        this.bufG.drawString("Score", 15, 150);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.score % 10000 / 1000), 0, 160, this);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.score % 1000 / 100), 20, 160, this);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.score % 100 / 10), 40, 160, this);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.score % 10), 60, 160, this);
        this.bufG.drawString("Level", 20, 90);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.level % 100 / 10), 20, 100, this);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.level % 10), 40, 100, this);
        this.bufG.drawString("Ships", 20, 20);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.ships % 100 / 10), 20, 30, this);
        this.bufG.drawImage(this.scoreLed1.getDigit(this.ships % 10), 40, 30, this);
        for (int i = 0; i < this.data.getBonus(); ++i) {
            this.bufG.drawImage(this.data.getGraphic("bonus1G"), 10 + 20 * i, 200, this);
        }
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
