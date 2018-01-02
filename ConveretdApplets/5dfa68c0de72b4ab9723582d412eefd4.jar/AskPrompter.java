import java.awt.Shape;
import java.awt.image.BufferedImageOp;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class AskPrompter extends StretchyBox
{
    int fontSize;
    Font font;
    Font answerFont;
    FontRenderContext renderContext;
    String promptString;
    String answerString;
    int lineX;
    int lineY;
    
    AskPrompter(final String prompt) {
        this.fontSize = 11;
        this.font = new Font("Arial Unicode MS", 1, this.fontSize);
        this.answerFont = new Font("Arial Unicode MS", 0, 13);
        this.promptString = "";
        this.answerString = "";
        this.renderContext = Skin.askBubbleFrame.createGraphics().getFontRenderContext();
        this.setFrameImage(Skin.askBubbleFrame);
        this.setPrompt(prompt);
    }
    
    void setPrompt(final String promptString) {
        final int n = (promptString.length() == 0) ? 1 : 2;
        this.promptString = promptString;
        this.x = 15;
        this.w = 452;
        this.h = n * (this.fontSize + 4) + 22;
        this.y = 378 - this.h;
    }
    
    public Rectangle rect() {
        return new Rectangle(this.x, this.y, this.w, this.h);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        final Graphics2D graphics2D = (Graphics2D)graphics;
        this.lineX = this.x + 12;
        this.lineY = this.y + this.fontSize + 14;
        graphics2D.setColor(new Color(0, 0, 0));
        graphics2D.setFont(this.font);
        if (this.promptString.length() > 0) {
            graphics2D.drawString(this.promptString, this.lineX - 2, this.lineY - 8);
            this.lineY += this.fontSize + 4;
        }
        graphics2D.setStroke(new BasicStroke(2.0f));
        graphics2D.setColor(new Color(213, 213, 213));
        graphics2D.drawLine(this.lineX - 4, this.lineY - this.fontSize - 5, this.lineX - 4 + this.w - 38, this.lineY - this.fontSize - 5);
        graphics2D.drawLine(this.lineX - 4, this.lineY - this.fontSize - 5, this.lineX - 4, this.lineY - this.fontSize - 5 + this.fontSize + 9);
        graphics2D.setColor(new Color(242, 242, 242));
        graphics2D.fillRect(this.lineX - 3, this.lineY - this.fontSize - 4, this.w - 39, this.fontSize + 8);
        final Shape clip = graphics.getClip();
        graphics.setClip(this.lineX - 3, this.lineY - this.fontSize - 4, this.w - 39, this.fontSize + 8);
        graphics2D.setColor(new Color(0, 0, 0));
        graphics2D.setFont(this.answerFont);
        graphics2D.drawString(this.answerString, this.lineX, this.lineY - 1);
        graphics.setClip(clip);
        graphics2D.drawImage(Skin.promptCheckButton, null, this.lineX + this.w - 38, this.lineY - this.fontSize - 6);
    }
    
    public boolean mouseDown(final int n, final int n2, final PlayerCanvas playerCanvas) {
        if (n > this.lineX + this.w - 38 && n < this.lineX + this.w - 38 + 20 && n2 > this.lineY - this.fontSize - 6 && n2 < this.lineY - this.fontSize - 6 + 20) {
            playerCanvas.hideAskPrompt();
            return true;
        }
        return false;
    }
    
    public void handleKeystroke(final int n, final PlayerCanvas playerCanvas) {
        playerCanvas.inval(this.rect());
        if (n == 3 || n == 10) {
            playerCanvas.hideAskPrompt();
            return;
        }
        if (n == 8 || n == 127) {
            if (this.answerString.length() > 0) {
                this.answerString = this.answerString.substring(0, this.answerString.length() - 1);
            }
            return;
        }
        final char[] array = { (char)n };
        if (n >= 32) {
            this.answerString += new String(array);
        }
    }
}
