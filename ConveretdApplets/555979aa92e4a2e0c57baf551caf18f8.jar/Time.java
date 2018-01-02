import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
import java.awt.FontMetrics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Time extends Applet implements Runnable
{
    private Thread clock;
    private String font_name;
    private String title;
    private String datestring;
    private int height;
    private int width;
    private int font_size;
    private Font wordFont;
    private FontMetrics wordMetrics;
    private int textcolor;
    private int backcolor;
    private int bordercolor;
    private int stringx;
    private int stringy;
    private int delay;
    private int s_width;
    TimeAndDateFormatter formattedTimeAndDate;
    
    public void init() {
        this.height = this.size().height;
        this.width = this.size().width;
        final String parameter = this.getParameter("delay");
        this.delay = ((parameter == null) ? 5000 : Integer.parseInt(parameter));
        final String parameter2 = this.getParameter("title");
        this.title = ((parameter2 == null) ? " " : parameter2);
        final String parameter3 = this.getParameter("font");
        this.font_name = ((parameter3 == null) ? "TimesRoman" : parameter3);
        final String parameter4 = this.getParameter("fontsize");
        this.font_size = ((parameter4 == null) ? 12 : Integer.parseInt(parameter4));
        this.wordFont = new Font(this.font_name, 1, this.font_size);
        if (this.wordFont == null) {
            this.wordFont = this.getFont();
        }
        this.wordMetrics = this.getFontMetrics(this.wordFont);
        final String parameter5 = this.getParameter("textcolor");
        this.textcolor = ((parameter5 == null) ? 0 : Integer.parseInt(parameter5, 16));
        final String parameter6 = this.getParameter("backcolor");
        this.backcolor = ((parameter6 == null) ? 16777215 : Integer.parseInt(parameter6, 16));
        final String parameter7 = this.getParameter("bordercolor");
        this.bordercolor = ((parameter7 == null) ? this.backcolor : Integer.parseInt(parameter7, 16));
    }
    
    public void start() {
        (this.clock = new Thread(this)).start();
    }
    
    public void stop() {
        this.clock.stop();
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        while (true) {
            this.formattedTimeAndDate = new TimeAndDateFormatter(new Date(), this.title);
            this.datestring = this.formattedTimeAndDate.toString();
            this.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        graphics.setFont(this.wordFont);
        graphics.setColor(new Color(this.backcolor));
        graphics.fillRect(0, 0, this.width, this.height);
        graphics.setColor(new Color(this.textcolor));
        this.s_width = this.wordMetrics.stringWidth(this.datestring);
        this.stringy = (this.height - (this.wordMetrics.getHeight() - this.wordMetrics.getLeading())) / 2 + (this.wordMetrics.getHeight() - this.wordMetrics.getLeading() - this.wordMetrics.getDescent());
        this.stringx = (this.width - this.s_width) / 2;
        graphics.drawString(this.datestring, this.stringx, this.stringy);
        graphics.setColor(new Color(this.bordercolor));
        graphics.drawRect(0, 0, this.width - 1, this.height - 1);
    }
    
    public void paintApplet(final Graphics graphics) {
    }
}
