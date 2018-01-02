import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class PlayFieldCanvas extends Canvas implements ImageObserver
{
    public static final int fh1 = 17;
    public static final int fh2 = 17;
    public static final int BorderWidth = 5;
    public static final Color BorderColor;
    protected Shape FallingShape;
    boolean FallingShapeNeedRepaint;
    boolean PlayFieldNeedRepaint;
    boolean DiscardGame;
    boolean ShowAboutBox;
    protected Font BlocksFont1;
    protected Font BlocksFont2;
    Color[][] PlayField;
    
    public void About() {
        this.ShowAboutBox = true;
        this.repaint();
    }
    
    public void GameOver() {
        this.DiscardGame = true;
        this.repaint();
    }
    
    public void RepaintPlayField() {
        this.PlayFieldNeedRepaint = true;
        this.repaint();
    }
    
    public void SetPlayField(final Color[][] playField) {
        this.PlayField = playField;
    }
    
    public PlayFieldCanvas() {
        this.FallingShapeNeedRepaint = false;
        this.PlayFieldNeedRepaint = false;
        this.DiscardGame = false;
        this.ShowAboutBox = false;
        this.getFontMetrics(this.BlocksFont1 = new Font("TimesRoman", 1, 20));
        this.getFontMetrics(this.BlocksFont2 = new Font("TimesRoman", 0, 14));
        this.reshape(0, 0, 160, 275);
    }
    
    public void repaint(final Shape fallingShape) {
        this.FallingShape = fallingShape;
        this.FallingShapeNeedRepaint = true;
        this.repaint();
    }
    
    public void DrawLines(final Graphics graphics, final int n, final int n2) {
        for (int i = n * 15; i < n2 * 15; ++i) {
            graphics.drawLine(5, i, 155, i);
        }
    }
    
    public void GraphicsEffect(final Graphics graphics, final int n, final int n2) {
        for (int i = 0; i < 10; ++i) {
            graphics.setColor(Color.red);
            this.DrawLines(graphics, n, n2);
            graphics.setColor(Color.green);
            this.DrawLines(graphics, n, n2);
            graphics.setColor(Color.blue);
            this.DrawLines(graphics, n, n2);
            graphics.setColor(Color.black);
            this.DrawLines(graphics, n, n2);
        }
    }
    
    public void DiscardIt(final Graphics graphics) {
        this.DiscardGame = false;
        this.GraphicsEffect(graphics, 0, 18);
    }
    
    public void DisplayAboutBox(final Graphics graphics) {
        int n = 1;
        graphics.setColor(Color.black);
        graphics.fillRect(5, 0, 150, 270);
        graphics.setFont(this.BlocksFont1);
        graphics.setColor(Color.red);
        graphics.drawString("BLOCKS", 20, 17);
        graphics.setFont(this.BlocksFont2);
        graphics.setColor(Color.cyan);
        graphics.drawString("v 1.01", 115, 17);
        graphics.drawString("Copyright (c) 1995, 1996", 7, 17 + 17 * n++);
        graphics.drawString("Iwan van Rienen", 7, 17 + 17 * n++);
        graphics.drawString("This program is postcard", 7, 17 + 17 * n++);
        graphics.drawString("ware. If you like this", 7, 17 + 17 * n++);
        graphics.drawString("program buy a stamp and", 7, 17 + 17 * n++);
        graphics.drawString("a postcard and send it to: ", 7, 17 + 17 * n++);
        graphics.setColor(Color.green);
        graphics.setColor(Color.yellow);
        graphics.drawString("Iwan van Rienen", 7, 17 + 17 * n++);
        graphics.drawString("J. Maetsuyckerstr. 145", 7, 17 + 17 * n++);
        graphics.drawString("2593 ZG  The Hague", 7, 17 + 17 * n++);
        graphics.drawString("The Netherlands", 7, 17 + 17 * n++);
        graphics.setColor(Color.cyan);
        graphics.drawString("The JAVA source is free", 7, 17 + 17 * n++);
        graphics.drawString("Visit my homepage at", 7, 17 + 17 * n++);
        graphics.setColor(Color.green);
        graphics.drawString("http://www.bart.nl/~ivr", 7, 17 + 17 * n++);
        graphics.drawString("E-mail to ivr@bart.nl", 7, 17 + 17 * n++);
    }
    
    public void update(final Graphics graphics) {
        if (this.DiscardGame) {
            this.DiscardIt(graphics);
        }
        if (this.PlayFieldNeedRepaint) {
            this.DrawPlayField(graphics);
        }
        this.DrawFallingShape(graphics);
        if (this.ShowAboutBox) {
            this.DisplayAboutBox(graphics);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.ShowAboutBox) {
            this.DisplayAboutBox(graphics);
            return;
        }
        this.DrawPlayField(graphics);
        this.FallingShapeNeedRepaint = true;
        this.DrawFallingShape(graphics);
    }
    
    public void DrawFallingShape(final Graphics graphics) {
        if (this.FallingShapeNeedRepaint && this.FallingShape != null) {
            this.FallingShape.hide(graphics, 5);
            while (!this.FallingShape.IsReady()) {}
            this.FallingShape.Display(graphics, 5);
            this.FallingShapeNeedRepaint = false;
        }
    }
    
    public void DrawPlayField(final Graphics graphics) {
        graphics.setColor(PlayFieldCanvas.BorderColor);
        graphics.fillRect(0, 0, 5, 270);
        graphics.fillRect(155, 0, 5, 270);
        graphics.fillRect(0, 270, 160, 5);
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 18; ++j) {
                if (this.PlayField[i][j] != Color.black) {
                    graphics.setColor(this.PlayField[i][j]);
                    graphics.fillRect(5 + i * 15 + 1, j * 15 + 1, 13, 13);
                    graphics.setColor(Color.white);
                    graphics.drawRect(5 + i * 15, j * 15, 14, 14);
                }
                else {
                    graphics.setColor(Color.black);
                    graphics.fillRect(5 + i * 15, j * 15, 15, 15);
                }
            }
        }
        this.PlayFieldNeedRepaint = false;
    }
    
    static {
        BorderColor = Color.blue;
    }
}
