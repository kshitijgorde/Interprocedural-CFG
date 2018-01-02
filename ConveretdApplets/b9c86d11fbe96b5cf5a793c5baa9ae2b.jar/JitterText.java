import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JitterText extends Applet implements Runnable
{
    static final int MAXCLORS = 10;
    String s;
    Thread jitterThread;
    boolean threadSuspended;
    int fontHeight;
    Color textColor;
    Color bgColor;
    int speed;
    FontMetrics fm;
    int baseline;
    Image offScrImage;
    Graphics offScrGC;
    boolean normal;
    Font font;
    Color[] randomColors;
    boolean randomColor;
    
    public void init() {
        final Graphics graphics = this.getGraphics();
        this.fontHeight = this.size().height - 10;
        this.offScrImage = this.createImage(this.size().width, this.size().height);
        this.offScrGC = this.offScrImage.getGraphics();
        this.s = this.getParameter("text");
        if (this.s == null) {
            this.s = "Java Power!";
        }
        final int n = this.size().width - (this.s.length() + 1) * 5 - 10;
        do {
            graphics.setFont(new Font("TimesRoman", 1, this.fontHeight));
            this.fm = graphics.getFontMetrics();
            if (this.fm.stringWidth(this.s) > n) {
                --this.fontHeight;
            }
        } while (this.fm.stringWidth(this.s) > n);
        this.baseline = this.size().height - this.fm.getMaxDescent();
        this.font = new Font("TimesRoman", 1, this.fontHeight);
        final String parameter;
        if ((parameter = this.getParameter("TEXTCOLOR")) == null) {
            this.textColor = Color.black;
        }
        else {
            this.textColor = this.parseColorString(parameter);
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("BGCOLOR")) == null) {
            this.bgColor = Color.lightGray;
        }
        else {
            this.bgColor = this.parseColorString(parameter2);
        }
        this.setBackground(this.bgColor);
        final String parameter3;
        if ((parameter3 = this.getParameter("SPEED")) != null) {
            this.speed = Integer.valueOf(parameter3);
        }
        if (this.speed == 0) {
            this.speed = 200;
        }
        final String parameter4;
        if ((parameter4 = this.getParameter("RANDOMCOLOR")) != null) {
            this.randomColor = (Integer.valueOf(parameter4) != 0);
        }
        this.randomColors[0] = Color.magenta;
        this.randomColors[1] = Color.orange;
        this.randomColors[2] = Color.red;
        this.randomColors[3] = Color.white;
        this.randomColors[4] = Color.yellow;
        this.randomColors[5] = Color.blue;
        this.randomColors[6] = Color.cyan;
        this.randomColors[7] = Color.green;
        this.randomColors[8] = Color.pink;
        this.randomColors[9] = Color.gray;
        this.jitterThread = new Thread(this);
    }
    
    public void start() {
        if (this.jitterThread != null) {
            this.jitterThread.start();
        }
    }
    
    public void stop() {
        if (this.jitterThread != null) {
            this.jitterThread.stop();
        }
        this.jitterThread = null;
    }
    
    public void run() {
        while (this.jitterThread != null) {
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
        System.exit(0);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.threadSuspended) {
            this.jitterThread.resume();
            this.normal = false;
        }
        else {
            this.normal = true;
            this.repaint();
            this.jitterThread.suspend();
        }
        this.threadSuspended = !this.threadSuspended;
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.normal) {
            graphics.setColor(this.bgColor);
            graphics.fillRect(0, 0, this.size().width, this.size().height);
            graphics.setColor(this.textColor);
            graphics.setFont(this.font);
            graphics.drawString(this.s, (this.size().width - this.fm.stringWidth(this.s)) / 2, this.baseline);
        }
    }
    
    public void update(final Graphics graphics) {
        this.offScrGC.setColor(this.bgColor);
        this.offScrGC.fillRect(0, 0, this.size().width, this.size().height);
        this.offScrGC.setColor(this.textColor);
        this.offScrGC.setFont(this.font);
        if (!this.normal) {
            int n = 0;
            for (int i = 0; i < this.s.length(); ++i) {
                if (this.randomColor) {
                    Color color;
                    while (this.bgColor == (color = this.randomColors[Math.min(9, (int)(Math.random() * 10.0))])) {}
                    this.offScrGC.setColor(color);
                }
                final int n2 = n + (int)(Math.random() * 10.0);
                final int n3 = this.baseline - (int)(Math.random() * 10.0);
                final String substring = this.s.substring(i, i + 1);
                this.offScrGC.drawString(substring, n2, n3);
                n = n2 + this.fm.stringWidth(substring);
            }
        }
        else {
            this.offScrGC.drawString(this.s, (this.size().width - this.fm.stringWidth(this.s)) / 2, this.baseline);
        }
        graphics.drawImage(this.offScrImage, 0, 0, this);
    }
    
    private Color parseColorString(final String s) {
        if (s.length() == 6) {
            return new Color(Integer.valueOf(s.substring(0, 2), 16), Integer.valueOf(s.substring(2, 4), 16), Integer.valueOf(s.substring(4, 6), 16));
        }
        return Color.lightGray;
    }
    
    public JitterText() {
        this.threadSuspended = false;
        this.speed = 200;
        this.normal = false;
        this.randomColors = new Color[10];
        this.randomColor = false;
    }
}
