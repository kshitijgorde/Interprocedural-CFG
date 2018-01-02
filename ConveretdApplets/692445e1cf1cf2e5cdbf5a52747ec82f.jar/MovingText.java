import java.net.MalformedURLException;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Point;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MovingText extends Applet implements Runnable
{
    Thread runner;
    Vector texts;
    Vector fonts;
    Vector colors;
    Vector speeds;
    Vector locations;
    Color bgcolor;
    Image offscreenImage;
    Graphics offscreen;
    int stringx;
    int stringy;
    Color stringcolor;
    
    public void stop() {
        if (this.runner != null) {
            this.runner = null;
        }
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(0));
        this.stringcolor = Color.blue;
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.offscreen.setColor(this.getBackground());
        this.offscreen.fillRect(0, 0, this.size().width, this.size().height);
        for (int i = 0; i < this.texts.size(); ++i) {
            this.offscreen.setFont((Font)this.fonts.elementAt(i));
            this.offscreen.setColor((Color)this.colors.elementAt(i));
            this.offscreen.drawString((String)this.texts.elementAt(i), ((Point)this.locations.elementAt(i)).x, ((Point)this.locations.elementAt(i)).y);
        }
        final Font font = new Font("Helvetica", 0, 10);
        this.offscreen.setFont(font);
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        this.stringx = this.size().width - fontMetrics.stringWidth("www.realapplets.com");
        this.stringy = this.size().height - fontMetrics.getHeight();
        this.offscreen.setColor(Color.white);
        this.offscreen.fillRect(this.stringx, this.stringy, this.size().width, this.size().height);
        this.offscreen.setColor(this.stringcolor);
        this.offscreen.drawString("www.realapplets.com", this.size().width - fontMetrics.stringWidth("www.realapplets.com"), this.size().height - 2);
        this.offscreen.drawLine(this.size().width - fontMetrics.stringWidth("www.realapplets.com"), this.size().height - 1, this.size().width, this.size().height - 1);
        graphics.drawImage(this.offscreenImage, 0, 0, this);
    }
    
    public MovingText() {
        this.texts = new Vector();
        this.fonts = new Vector();
        this.colors = new Vector();
        this.speeds = new Vector();
        this.locations = new Vector();
        this.stringcolor = Color.blue;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n > this.stringx && n2 > this.stringy) {
            try {
                this.getAppletContext().showDocument(new URL("http://www.realapplets.com/"), "_blank");
            }
            catch (MalformedURLException ex) {}
            return true;
        }
        return false;
    }
    
    public void run() {
        while (this.runner == Thread.currentThread()) {
            for (int i = 0; i < this.texts.size(); ++i) {
                final Point point = new Point();
                point.x = this.locations.elementAt(i).x + Integer.parseInt(this.speeds.elementAt(i));
                if (Integer.parseInt((String)this.speeds.elementAt(i)) > 0) {
                    if (point.x > this.size().width) {
                        point.x = 0 - this.getFontMetrics(this.fonts.elementAt(i)).stringWidth(this.texts.elementAt(i));
                    }
                }
                else if (point.x < 0 - this.getFontMetrics(this.fonts.elementAt(i)).stringWidth(this.texts.elementAt(i))) {
                    point.x = this.size().width;
                }
                point.y = this.locations.elementAt(i).y;
                this.locations.setElementAt(point, i);
            }
            this.repaint();
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void init() {
        this.offscreenImage = this.createImage(this.size().width, this.size().height);
        this.offscreen = this.offscreenImage.getGraphics();
        String parameter = this.getParameter("bgcolor");
        if (parameter == null) {
            parameter = "000000";
        }
        this.setBackground(this.stringToColor(parameter));
        int n = 1;
        while (true) {
            final String string = "text" + Integer.toString(n);
            if (this.getParameter(string) == null) {
                break;
            }
            this.texts.addElement(this.getParameter(string));
            String parameter2 = this.getParameter("fontface" + Integer.toString(n));
            if (parameter2 == null) {
                parameter2 = "TimesRoman";
            }
            String parameter3 = this.getParameter("fontstyle" + Integer.toString(n));
            if (parameter3 == null) {
                parameter3 = "0";
            }
            String parameter4 = this.getParameter("fontsize" + Integer.toString(n));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.fonts.addElement(new Font(parameter2, Integer.parseInt(parameter3), Integer.parseInt(parameter4)));
            String parameter5 = this.getParameter("fontcolor" + Integer.toString(n));
            if (parameter5 == null) {
                parameter5 = "FFFFFF";
            }
            this.colors.addElement(this.stringToColor(parameter5));
            String parameter6 = this.getParameter("speed" + Integer.toString(n));
            if (parameter6 == null) {
                parameter6 = "5";
            }
            this.speeds.addElement(parameter6);
            String s = this.getParameter("bottom" + Integer.toString(n));
            if (s == null) {
                s = Integer.toString(this.size().height);
            }
            final FontMetrics fontMetrics = this.getFontMetrics(this.fonts.elementAt(n - 1));
            if (Integer.parseInt((String)this.speeds.elementAt(n - 1)) > 0) {
                this.locations.addElement(new Point(0 - fontMetrics.stringWidth(this.texts.elementAt(n - 1)), Integer.parseInt(s)));
            }
            else {
                this.locations.addElement(new Point(this.size().width, Integer.parseInt(s)));
            }
            ++n;
        }
    }
    
    private Color stringToColor(final String s) {
        return new Color(Integer.decode("0x" + s.substring(0, 2)), Integer.decode("0x" + s.substring(2, 4)), Integer.decode("0x" + s.substring(4, 6)));
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (n > this.stringx && n2 > this.stringy) {
            this.setCursor(new Cursor(12));
            this.stringcolor = Color.red;
        }
        else {
            this.setCursor(new Cursor(0));
            this.stringcolor = Color.blue;
        }
        return true;
    }
}
