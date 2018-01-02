import java.awt.FontMetrics;
import java.util.TimerTask;
import java.awt.geom.Line2D;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Shape;
import java.awt.Paint;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.Timer;
import java.awt.Color;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class LabelPanel extends JPanel
{
    private String caption;
    private String author;
    private Color backColor;
    private Color textColor;
    private boolean useTimer;
    private int seconds;
    private Timer timer;
    
    public LabelPanel(final String caption, final String s, final Color backColor, final Color textColor) {
        this.caption = caption;
        if (s != null) {
            this.author = "Puzzle submitted by " + s;
        }
        this.backColor = backColor;
        this.textColor = textColor;
        this.useTimer = true;
        this.seconds = 0;
        this.timer = null;
    }
    
    public void paint(final Graphics graphics) {
        final Graphics2D graphics2D = (Graphics2D)graphics;
        final Rectangle visibleRect = this.getVisibleRect();
        graphics2D.setPaint(this.backColor);
        graphics2D.fill(visibleRect);
        graphics2D.setPaint(this.textColor);
        int n = 50;
        int n2 = 0;
        if (this.caption != null) {
            graphics2D.setFont(new Font("Arial", 1, 16));
            final FontMetrics fontMetrics = graphics2D.getFontMetrics();
            if (this.author == null) {
                n = (visibleRect.width - fontMetrics.stringWidth(this.caption)) / 2;
            }
            n2 = (visibleRect.height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
            graphics2D.drawString(this.caption, n, n2);
            n += fontMetrics.stringWidth(this.caption) + fontMetrics.stringWidth("  ");
        }
        if (this.author != null) {
            graphics2D.setFont(new Font("Arial", 0, 12));
            if (n2 == 0) {
                final FontMetrics fontMetrics2 = graphics2D.getFontMetrics();
                n2 = (visibleRect.height - fontMetrics2.getHeight()) / 2 + fontMetrics2.getAscent();
            }
            graphics2D.drawString(this.author, n, n2);
        }
        if (this.useTimer) {
            graphics2D.setFont(new Font("Arial", 1, 14));
            final FontMetrics fontMetrics3 = graphics2D.getFontMetrics();
            final int n3 = visibleRect.width - fontMetrics3.stringWidth("00:00:00") - 50;
            final int n4 = (visibleRect.height - fontMetrics3.getHeight()) / 2 + fontMetrics3.getAscent();
            final int n5 = this.seconds / 60;
            final int n6 = this.seconds % 60;
            String s = new Integer(n5).toString() + ":";
            if (n6 < 10) {
                s += "0";
            }
            final String string = s + new Integer(n6).toString();
            graphics2D.setPaint(Color.RED);
            graphics2D.fill(new Rectangle(n3 - 4, n4 - fontMetrics3.getAscent() - 2, fontMetrics3.stringWidth(string) + 8, fontMetrics3.getHeight() + 4));
            graphics2D.setPaint(this.backColor);
            graphics2D.fill(new Rectangle(n3 - 4, n4 - fontMetrics3.getAscent() - 2, 1, 1));
            graphics2D.fill(new Rectangle(n3 - 4, n4 - fontMetrics3.getAscent() + fontMetrics3.getHeight() + 1, 1, 1));
            graphics2D.fill(new Rectangle(n3 + fontMetrics3.stringWidth(string) + 3, n4 - fontMetrics3.getAscent() - 2, 1, 1));
            graphics2D.fill(new Rectangle(n3 + fontMetrics3.stringWidth(string) + 3, n4 - fontMetrics3.getAscent() + fontMetrics3.getHeight() + 1, 1, 1));
            graphics2D.setPaint(Color.WHITE);
            graphics2D.drawString(string, n3, n4);
        }
        final int n7 = visibleRect.x + 10;
        final int n8 = visibleRect.width - 11;
        final int n9 = visibleRect.height - 1;
        final Line2D.Double double1 = new Line2D.Double(n7, n9, n8, n9);
        graphics2D.setPaint(this.textColor);
        graphics2D.draw(double1);
        if (this.useTimer && this.timer == null) {
            (this.timer = new Timer()).schedule(new TimerEvent(), 1000L, 1000L);
        }
    }
    
    public void setTimer(final boolean useTimer) {
        this.useTimer = useTimer;
    }
    
    public void setGameOver(final boolean b) {
        if (b && this.timer != null) {
            this.timer.cancel();
        }
    }
    
    private class TimerEvent extends TimerTask
    {
        public void run() {
            LabelPanel.this.seconds++;
            LabelPanel.this.repaint();
        }
    }
}
