// 
// Decompiled by Procyon v0.5.30
// 

package tmcm.xSortLab;

import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Canvas;

class MessageCanvas extends Canvas
{
    String message;
    Font font12;
    Font font10;
    Font font9;
    FontMetrics fm12;
    FontMetrics fm10;
    FontMetrics fm9;
    int height12;
    int height10;
    int height9;
    int offset12;
    int offset10;
    int offset9;
    
    MessageCanvas(final String message) {
        this.message = message;
    }
    
    MessageCanvas() {
        this.message = "";
    }
    
    void changeMessage(final String message) {
        this.message = message;
        this.repaint();
    }
    
    void changeMessageNow(final String message) {
        this.message = message;
        final Graphics graphics = this.getGraphics();
        this.update(graphics);
        graphics.dispose();
    }
    
    public void paint(final Graphics graphics) {
        if (this.message == null || this.message.length() == 0) {
            return;
        }
        if (this.font12 == null) {
            final Font font = graphics.getFont();
            final int size = font.getSize();
            if (size > 12) {
                this.font12 = font;
                this.fm12 = graphics.getFontMetrics(font);
                this.font10 = new Font(font.getName(), 0, Math.round(size * 5.0f / 6.0f));
                this.fm10 = graphics.getFontMetrics(this.font10);
                this.font9 = new Font(font.getName(), 0, Math.round(size * 3.0f / 4.0f));
                this.fm9 = graphics.getFontMetrics(this.font9);
            }
            else {
                this.font12 = new Font(font.getName(), 0, 12);
                this.fm12 = graphics.getFontMetrics(this.font12);
                this.font10 = new Font(font.getName(), 0, 10);
                this.fm10 = graphics.getFontMetrics(this.font10);
                this.font9 = new Font(font.getName(), 0, 9);
                this.fm9 = graphics.getFontMetrics(this.font9);
            }
            this.height12 = this.fm12.getAscent() + this.fm12.getDescent();
            this.height10 = this.fm10.getAscent() + this.fm10.getDescent();
            this.height9 = this.fm9.getAscent() + this.fm9.getDescent();
            this.offset12 = this.fm12.getAscent();
            this.offset10 = this.fm10.getAscent();
            this.offset9 = this.fm9.getAscent();
        }
        final int n = this.size().width - 7;
        final int height = this.size().height;
        final int stringWidth = this.fm12.stringWidth(this.message);
        int n2 = this.height12;
        int n3 = this.offset12;
        if (n2 <= height && stringWidth <= n) {
            graphics.setFont(this.font12);
        }
        else {
            final int stringWidth2 = this.fm10.stringWidth(this.message);
            n2 = this.height10;
            n3 = this.offset10;
            if (stringWidth2 <= n && n2 <= height) {
                graphics.setFont(this.font10);
            }
            else {
                n2 = this.height9;
                n3 = this.offset9;
                graphics.setFont(this.font9);
            }
        }
        graphics.drawString(this.message, 7, (height - n2) / 2 + n3);
    }
}
