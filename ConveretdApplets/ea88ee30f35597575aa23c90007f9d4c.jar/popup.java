import java.awt.Component;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class popup implements Runnable
{
    TreeApp m_applet;
    private Thread m_popup;
    private boolean m_displayed;
    private long m_when;
    int m_x;
    int m_y;
    String m_text;
    public Color m_backCol;
    public Color m_textCol;
    
    private final Rectangle getRect(String s, final int n, final FontMetrics fontMetrics) {
        int n2 = 0;
        int n3 = 0;
        String breakString;
        while ((breakString = this.breakString(s, n, fontMetrics)) != null) {
            s = s.substring(breakString.length());
            s = s.trim();
            final int stringWidth = fontMetrics.stringWidth(breakString);
            if (stringWidth > n3) {
                n3 = stringWidth;
            }
            n2 += fontMetrics.getHeight();
        }
        return new Rectangle(0, 0, n3 + 4, n2 + 4);
    }
    
    public void stop() {
        this.m_text = null;
        this.m_displayed = false;
        this.m_popup.stop();
    }
    
    public popup(final TreeApp applet) {
        this.m_backCol = Color.yellow;
        this.m_textCol = Color.black;
        try {
            this.m_applet = applet;
            this.m_popup = new Thread(this);
            this.m_displayed = false;
            this.m_text = null;
        }
        catch (Exception ex) {}
    }
    
    private final String breakString(String substring, final int n, final FontMetrics fontMetrics) {
        if (substring.length() == 0) {
            return null;
        }
        if (fontMetrics.stringWidth(substring) < n) {
            return substring;
        }
        String substring2 = substring;
        int lastIndex;
        while ((lastIndex = substring2.lastIndexOf(32)) != -1) {
            substring2 = substring2.substring(0, lastIndex);
            final int stringWidth = fontMetrics.stringWidth(substring2);
            if (stringWidth < n && stringWidth > n / 2) {
                return substring2;
            }
        }
        for (int length = substring.length(); fontMetrics.stringWidth(substring) > n && --length > 0; substring = substring.substring(0, length)) {}
        return substring;
    }
    
    public void start() {
        if (this.m_popup != null) {
            this.m_popup.start();
        }
    }
    
    public boolean clear(final boolean b) {
        final boolean displayed = this.m_displayed;
        if (b) {
            this.m_text = null;
            this.m_displayed = false;
        }
        return displayed;
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    if (this.m_text != null && !this.m_displayed && System.currentTimeMillis() > this.m_when && this.m_text.length() > 0) {
                        final Graphics graphics = ((Component)this.m_applet).getGraphics();
                        final FontMetrics fontMetrics = graphics.getFontMetrics();
                        final Rectangle rect = this.m_applet.getRect();
                        final Rectangle rect2 = this.getRect(this.m_text, rect.width - 6, fontMetrics);
                        rect2.x = this.m_x - this.m_applet.m_offsetx;
                        rect2.y = this.m_y - this.m_applet.m_offsety;
                        final Rectangle rectangle = rect2;
                        rectangle.y -= rect2.height;
                        if (rect2.y < 1) {
                            rect2.y = 1;
                        }
                        if (rect2.x < 1) {
                            rect2.x = 1;
                        }
                        final int n = rect2.height + rect2.y;
                        if (n > rect.height) {
                            final Rectangle rectangle2 = rect2;
                            rectangle2.y -= n - rect.height;
                        }
                        final int n2 = rect2.width + rect2.x;
                        if (n2 > rect.width) {
                            final Rectangle rectangle3 = rect2;
                            rectangle3.x -= n2 - rect.width + 2;
                        }
                        this.drawRect(graphics, this.m_text, rect2, fontMetrics);
                        this.m_displayed = true;
                        graphics.dispose();
                    }
                    Thread.sleep(1000L);
                }
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public void setText(final String text, final Event event) {
        this.m_text = text;
        this.m_x = event.x;
        this.m_y = event.y;
        this.m_when = event.when + 1000L;
        this.m_displayed = false;
    }
    
    private final void drawRect(final Graphics graphics, String s, final Rectangle rectangle, final FontMetrics fontMetrics) {
        graphics.setColor(this.m_backCol);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.setColor(Color.black);
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.setColor(this.m_textCol);
        int n = rectangle.y + 2;
        rectangle.x += 2;
        String breakString;
        while ((breakString = this.breakString(s, rectangle.width, fontMetrics)) != null) {
            s = s.substring(breakString.length());
            s = s.trim();
            graphics.drawString(breakString, rectangle.x, n + fontMetrics.getAscent());
            n += fontMetrics.getHeight();
        }
    }
}
