import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Infocanvas extends Canvas
{
    private Iqapplet iq;
    Screen sc;
    Iqquiz frame;
    int answered;
    
    public Infocanvas(final Screen sc, final Iqapplet iq, final Iqquiz frame) {
        this.answered = 0;
        this.sc = sc;
        this.iq = iq;
        this.frame = frame;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawString("Question " + this.frame.question, 10, 20);
        graphics.drawString("Questions left:", 10, 40);
        graphics.drawString("" + (14 - this.frame.question), 20, 60);
        graphics.drawString("Answered:", 10, 80);
        this.answered = 0;
        for (int i = 1; i < 15; ++i) {
            if (!this.frame.answer[i].equals("") && !this.frame.answer[i].equals("0")) {
                ++this.answered;
            }
        }
        graphics.drawString("" + this.answered, 20, 100);
    }
}
