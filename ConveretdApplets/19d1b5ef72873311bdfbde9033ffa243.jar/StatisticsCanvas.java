import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class StatisticsCanvas extends Canvas implements ImageObserver
{
    public static final Color textColor;
    public static final int MaxLevel = 9;
    public static final int myFontHeight = 16;
    protected Font BlocksFont;
    protected FontMetrics BlocksFontMetrics;
    protected int Level;
    protected int Lines;
    protected int Score;
    protected Shape NextShape;
    
    public void InitNewGame() {
        final boolean level = false;
        this.Score = (level ? 1 : 0);
        this.Lines = (level ? 1 : 0);
        this.Level = (level ? 1 : 0);
    }
    
    public void GameOver() {
    }
    
    public int GetGameSpeed() {
        switch (this.Level) {
            case 0: {
                return 700;
            }
            case 1: {
                return 600;
            }
            case 2: {
                return 500;
            }
            case 3: {
                return 400;
            }
            case 4: {
                return 350;
            }
            case 5: {
                return 300;
            }
            case 6: {
                return 250;
            }
            case 7: {
                return 200;
            }
            case 8: {
                return 150;
            }
            case 9: {
                return 100;
            }
            default: {
                return 100;
            }
        }
    }
    
    public StatisticsCanvas() {
        this.reshape(0, 0, 100, 100);
        this.setFont(this.BlocksFont = new Font("TimesRoman", 0, 20));
        this.BlocksFontMetrics = this.getFontMetrics(this.BlocksFont);
        this.InitNewGame();
    }
    
    public void AddScore(final int n) {
        this.Score += n;
        this.repaint();
    }
    
    public void AddLines(final int n) {
        switch (n) {
            case 1: {
                this.AddScore(10);
                break;
            }
            case 2: {
                this.AddScore(20);
                break;
            }
            case 3: {
                this.AddScore(30);
                break;
            }
            case 4: {
                this.AddScore(40);
                break;
            }
        }
        this.Lines += n;
        if (this.Lines > 10 * (this.Level + 1)) {
            this.AddLevel();
        }
        this.repaint();
    }
    
    public void AddLevel() {
        Blocks.play(2);
        if (this.Level < 9) {
            ++this.Level;
        }
        this.repaint();
    }
    
    public void DisplayNextShape(final Shape nextShape) {
        this.NextShape = nextShape;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(StatisticsCanvas.textColor);
        graphics.drawString("Level: " + this.Level, 0, 16);
        graphics.drawString("Lines: " + this.Lines, 0, 48);
        graphics.drawString("Score: " + this.Score, 0, 80);
        graphics.drawString("Next:", 0, 112);
        if (this.NextShape != null) {
            this.NextShape.DisplayAbs(graphics, 10, 122);
        }
    }
    
    static {
        textColor = Color.black;
    }
}
