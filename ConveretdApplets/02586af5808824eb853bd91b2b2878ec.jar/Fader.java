import java.applet.AppletContext;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Fader extends Applet implements Runnable, MouseListener
{
    String[] text;
    int steps;
    int delay;
    int stop;
    Image im;
    Graphics offscreen;
    Color[] textColor;
    Rectangle first;
    Rectangle second;
    Thread thread;
    int currentText;
    int frame;
    boolean starting;
    Font f;
    URL[] url;
    String[] target;
    boolean Over;
    boolean stopped;
    int Highlight;
    int fontSize;
    
    public void init() {
        final Color colorParameter = this.getColorParameter("bgcolor");
        final Color colorParameter2 = this.getColorParameter("fgcolor");
        this.getStrings();
        this.delay = Integer.parseInt((this.getParameter("delay") != null) ? this.getParameter("delay") : "40");
        this.stop = Integer.parseInt((this.getParameter("stop") != null) ? this.getParameter("stop") : "1000");
        this.fontSize = Integer.parseInt((this.getParameter("fontSize") != null) ? this.getParameter("fontSize") : "24");
        final String s = (this.getParameter("fontName") != null) ? this.getParameter("fontName") : "TimesRoman";
        final int int1 = Integer.parseInt((this.getParameter("fontStyle") != null) ? this.getParameter("fontStyle") : "0");
        this.Highlight = Integer.parseInt((this.getParameter("Highlight") != null) ? this.getParameter("Highlight") : "FF0000", 16);
        this.im = this.createImage(this.getSize().width, this.getSize().height);
        this.offscreen = this.im.getGraphics();
        this.f = new Font(s, int1, this.fontSize);
        this.offscreen.setFont(this.f);
        this.steps = (this.getSize().height - this.fontSize) / 10 + 30;
        this.textColor = new Color[this.steps];
        for (int i = 0; i < this.steps; ++i) {
            this.textColor[i] = new Color(colorParameter.getRed() + i * (colorParameter2.getRed() - colorParameter.getRed()) / this.steps, colorParameter.getGreen() + i * (colorParameter2.getGreen() - colorParameter.getGreen()) / this.steps, colorParameter.getBlue() + i * (colorParameter2.getBlue() - colorParameter.getBlue()) / this.steps);
        }
        this.setBackground(colorParameter);
        this.setForeground(colorParameter2);
        this.addMouseListener(this);
        this.newfirst();
    }
    
    void newfirst() {
        final int n = (this.getSize().width - this.offscreen.getFontMetrics().stringWidth(this.text[this.currentText])) / 2;
        this.first = new Rectangle(n, this.getSize().height, n, this.fontSize);
    }
    
    void getStrings() {
        int n;
        for (n = 0; this.getParameter("text" + n) != null; ++n) {}
        this.text = new String[n];
        this.target = new String[n];
        this.url = new URL[n];
        for (int i = 0; i < n; ++i) {
            this.text[i] = this.getParameter("text" + i);
            this.url[i] = this.getURL(this.getParameter("url" + i));
            this.target[i] = this.getParameter("target" + i);
        }
    }
    
    URL getURL(final String s) {
        URL url = null;
        try {
            url = new URL(null, s);
        }
        catch (MalformedURLException ex) {}
        return url;
    }
    
    Color getColorParameter(final String s) {
        String parameter = this.getParameter(s);
        if (s.equals("bgcolor")) {
            if (parameter == null || parameter.equals("")) {
                parameter = "FFFFFF";
            }
        }
        else if (parameter == null || parameter.equals("")) {
            parameter = "0000CC";
        }
        return new Color(Integer.parseInt(parameter.substring(0, 2), 16), Integer.parseInt(parameter.substring(2, 4), 16), Integer.parseInt(parameter.substring(4, 6), 16));
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public void run() {
        while (this.thread != null) {
            this.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
            if (this.frame == this.steps - 1) {
                this.stopped = true;
                this.repaint();
                try {
                    Thread.sleep(this.stop);
                }
                catch (InterruptedException ex2) {}
                this.stopped = false;
                this.second = this.first;
                ++this.currentText;
                if (this.currentText > this.text.length - 1) {
                    this.currentText %= this.text.length;
                }
                this.newfirst();
                this.starting = false;
                this.frame = 0;
            }
            else {
                ++this.frame;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.offscreen.clearRect(0, 0, this.getSize().width, this.getSize().height);
        if (this.Over && this.stopped && this.url[this.currentText] != null) {
            this.offscreen.setColor(new Color(this.Highlight));
            this.showStatus(String.valueOf(this.url[this.currentText]));
        }
        else if (this.Over) {
            this.offscreen.setColor(this.textColor[this.frame]);
            this.showStatus("");
        }
        else {
            this.offscreen.setColor(this.textColor[this.frame]);
        }
        this.offscreen.drawString(this.text[this.currentText], this.first.x + this.frame * (this.first.width - this.first.x) / (2 * this.steps - 2), this.first.y + this.frame * (this.first.height - this.first.y) / (2 * this.steps - 2));
        if (!this.starting) {
            final int n = this.second.x + (this.frame + this.steps - 1) * (this.second.width - this.second.x) / (2 * this.steps - 2);
            final int n2 = this.second.y + (this.frame + this.steps - 1) * (this.second.height - this.second.y) / (2 * this.steps - 2);
            this.offscreen.setColor(this.textColor[this.steps - this.frame - 1]);
            this.offscreen.drawString(this.text[(this.currentText + this.text.length - 1) % this.text.length], n, n2);
        }
        graphics.drawImage(this.im, 0, 0, this);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.Over = true;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.Over = false;
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.Over && this.url[this.currentText] != null) {
            final AppletContext appletContext = this.getAppletContext();
            if (this.target[this.currentText] != null) {
                appletContext.showDocument(this.url[this.currentText], this.target[this.currentText]);
                return;
            }
            appletContext.showDocument(this.url[this.currentText]);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public Fader() {
        this.starting = true;
        this.Over = false;
        this.stopped = false;
    }
}
