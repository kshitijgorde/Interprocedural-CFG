import java.awt.Event;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class RTLTextField extends Canvas
{
    chat_area c;
    String cur_text;
    int width;
    int height;
    
    String getText() {
        return this.cur_text;
    }
    
    void setText(final String cur_text) {
        this.cur_text = cur_text;
        this.repaint();
    }
    
    RTLTextField(final chat_area c, final int width, final int height) {
        this.cur_text = "";
        this.c = c;
        this.resize(this.width = width, this.height = height);
    }
    
    public void paint(final Graphics graphics) {
        if (null != graphics && null != this.cur_text) {
            final int stringWidth = graphics.getFontMetrics().stringWidth(this.cur_text);
            graphics.drawLine(0, 0, this.width - 1, 0);
            graphics.drawLine(0, this.height - 1, this.width - 1, this.height - 1);
            graphics.drawLine(0, 0, 0, this.height - 1);
            graphics.drawLine(this.width - 1, 0, this.width - 1, this.height - 1);
            graphics.drawString(this.cur_text, this.width - stringWidth - 5, graphics.getFontMetrics().getAscent() + 5);
            final int n = this.width - stringWidth - 10;
            final int n2 = 20;
            graphics.drawLine(n, n2, n - 2, n2);
            graphics.drawLine(n, n2 - 15, n - 2, n2 - 15);
            graphics.drawLine(n - 1, n2, n - 1, n2 - 15);
        }
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (10 == event.key) {
                    this.c.send_chat_string(this.cur_text);
                    this.cur_text = "";
                }
                else if (8 == event.key || 127 == event.key) {
                    if (this.cur_text.length() != 0) {
                        this.cur_text = this.cur_text.substring(0, this.cur_text.length() - 1);
                    }
                }
                else if (this.cur_text.length() < 400) {
                    this.cur_text += (char)event.key;
                }
                this.repaint();
                return true;
            }
            default: {
                return false;
            }
        }
    }
}
