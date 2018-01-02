import java.awt.Event;
import java.awt.FontMetrics;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class LVInter extends Applet implements Runnable
{
    static final String CLASS_NAME = "LVInter";
    static final String VERSION = "1.2";
    boolean loaded;
    boolean mouseOver;
    int width;
    int height;
    int cps;
    int x;
    int y;
    int strLen;
    int sleepTime;
    long period;
    String fileName;
    String message;
    String defaultMessage;
    String defaultLink;
    String frame;
    Color bg1;
    Color bg2;
    Color fg1;
    Color fg2;
    Font font;
    Image bgImage;
    Image buffer;
    Graphics bgG;
    Graphics bufG;
    Graphics screenG;
    URL link;
    Thread workThread;
    
    public void init() {
        this.setBackground(this.bg1 = this.readColorParameter("BgColor1", new Color(0, 0, 0)));
        this.getParent().setBackground(this.bg1);
        this.getParent().repaint();
        this.bg2 = this.readColorParameter("BgColor2", this.bg1);
        this.fg1 = this.readColorParameter("FgColor1", new Color(255, 255, 255));
        this.fg2 = this.readColorParameter("FgColor2", this.fg1);
        this.defaultMessage = this.readStringParameter("DefaultMessage", "Click here for news");
        this.defaultLink = this.readStringParameter("DefaultLink", " ");
        this.fileName = this.readStringParameter("DataFile", null);
        this.font = this.readFontParameter("Font", new Font("Helvetica", 0, 12));
        this.frame = this.readStringParameter("DestinationFrame", "_self");
        this.cps = this.readIntParameter("CPS", 10);
        this.sleepTime = this.readIntParameter("SleepTime", 100);
        this.width = this.size().width;
        this.height = this.size().height;
        final boolean b = false;
        this.mouseOver = b;
        this.loaded = b;
        this.link = null;
        if (this.defaultLink != " ") {
            try {
                this.link = new URL(this.getDocumentBase(), this.defaultLink);
                this.defaultLink = this.link.toExternalForm();
            }
            catch (MalformedURLException ex) {
                System.err.println("Default link not valid");
            }
        }
    }
    
    public void start() {
        if (this.link != null && this.getParent() instanceof Frame) {
            ((Frame)this.getParent()).setCursor(12);
        }
        if (this.workThread == null) {
            this.workThread = new Thread(this);
        }
        this.workThread.start();
    }
    
    public void stop() {
        if (this.workThread != null) {
            this.workThread.stop();
            this.workThread = null;
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(1);
        while (Thread.currentThread() == this.workThread) {
            if (!this.loaded) {
                this.bgImage = this.createImage(this.width, this.height);
                this.bgG = this.bgImage.getGraphics();
                this.buffer = this.createImage(this.width, this.height);
                this.bufG = this.buffer.getGraphics();
                final int red = this.bg1.getRed();
                final int green = this.bg1.getGreen();
                final int blue = this.bg1.getBlue();
                final int red2 = this.bg2.getRed();
                final int green2 = this.bg2.getGreen();
                final int blue2 = this.bg2.getBlue();
                for (int i = 0; i < this.height / 2; ++i) {
                    final int n = this.height / 2 - 1;
                    this.bgG.setColor(new Color(((n - i) * red + i * red2) / n, ((n - i) * green + i * green2) / n, ((n - i) * blue + i * blue2) / n));
                    this.bgG.fillRect(0, i, this.width, this.height - i * 2);
                }
                this.screenG = this.getGraphics();
                this.bufG.drawImage(this.bgImage, 0, 0, null);
                this.workPaint(this.screenG);
                String s;
                if (this.fileName != null) {
                    try {
                        final URLConnection openConnection = new URL(this.getDocumentBase(), this.fileName).openConnection();
                        int contentLength = openConnection.getContentLength();
                        if (contentLength > 0) {
                            final InputStream inputStream = openConnection.getInputStream();
                            final StringBuffer sb = new StringBuffer(contentLength + 16);
                            int read;
                            while ((read = inputStream.read()) != -1 && --contentLength > 0) {
                                sb.append((char)read);
                            }
                            inputStream.close();
                            s = sb.toString();
                        }
                        else {
                            s = this.defaultLink + "\n" + this.defaultMessage;
                        }
                    }
                    catch (Exception ex) {
                        System.err.println("Cannot read file correctly");
                        s = this.defaultLink + "\n" + this.defaultMessage;
                    }
                }
                else {
                    s = this.defaultLink + "\n" + this.defaultMessage;
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(s, "\t\n\r");
                this.message = stringTokenizer.nextToken();
                try {
                    this.link = new URL(this.message);
                    this.message = "";
                }
                catch (MalformedURLException ex2) {
                    System.err.println("The file don't contain the url: using default");
                }
                while (stringTokenizer.hasMoreTokens()) {
                    this.message = this.message + " " + stringTokenizer.nextToken();
                }
                if (this.link != null && this.getParent() instanceof Frame) {
                    ((Frame)this.getParent()).setCursor(12);
                }
                this.bufG.setFont(this.font);
                final FontMetrics fontMetrics = this.bufG.getFontMetrics();
                for (int n2 = this.width / fontMetrics.charWidth(' ') + 10, j = 0; j < n2; ++j) {
                    this.message += " ";
                }
                this.strLen = fontMetrics.stringWidth(this.message);
                this.period = 1000L * this.message.length() / this.cps;
                this.y = (this.height - fontMetrics.getAscent() - fontMetrics.getDescent()) / 2 + fontMetrics.getAscent();
                this.loaded = true;
            }
            this.x = this.width - (int)(System.currentTimeMillis() % this.period * this.strLen / this.period);
            this.bufG.drawImage(this.bgImage, 0, 0, null);
            this.bufG.setColor(this.mouseOver ? this.fg2 : this.fg1);
            this.bufG.drawString(this.message, this.x, this.y);
            this.workPaint(this.screenG);
            try {
                if (this.sleepTime <= 0) {
                    continue;
                }
                Thread.currentThread();
                Thread.sleep(this.sleepTime);
            }
            catch (InterruptedException ex3) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        this.workPaint(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.workPaint(graphics);
    }
    
    void workPaint(final Graphics graphics) {
        if (this.workThread != null && this.buffer != null) {
            graphics.drawImage(this.buffer, 0, 0, null);
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("LVInter 1.2 by Luciano Vernaschi");
        return this.mouseOver = true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        this.mouseOver = false;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.link != null) {
            this.getAppletContext().showDocument(this.link, this.frame);
        }
        return true;
    }
    
    int readIntParameter(final String s, int int1) {
        try {
            int1 = Integer.parseInt(this.getParameter(s));
        }
        catch (Exception ex) {
            System.out.println("Parameter \"" + s + "\" not found");
        }
        return int1;
    }
    
    String readStringParameter(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            System.out.println("Parameter \"" + s + "\" not found");
            return s2;
        }
        return parameter;
    }
    
    Color readColorParameter(final String s, Color color) {
        try {
            color = new Color(Integer.parseInt(this.getParameter(s), 16));
        }
        catch (Exception ex) {
            System.out.println("Parameter \"" + s + "\" not found");
        }
        return color;
    }
    
    Font readFontParameter(final String s, final Font font) {
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            System.out.println("Parameter \"" + s + "\" not found");
            return font;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(parameter, " ,;+\t\n\r");
        Font font2;
        try {
            font2 = new Font(stringTokenizer.nextToken(), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        catch (Exception ex) {
            System.out.println("Parameter \"" + s + "\" not valid");
            return font;
        }
        return font2;
    }
    
    URL readURLParameter(final String s, final URL url) {
        URL url2 = null;
        final String parameter = this.getParameter(s);
        if (parameter == null) {
            System.out.println("Parameter \"" + s + "\" not found");
            return url;
        }
        try {
            url2 = new URL(parameter);
        }
        catch (MalformedURLException ex) {}
        if (url2 == null) {
            try {
                url2 = new URL(this.getDocumentBase(), parameter);
            }
            catch (MalformedURLException ex2) {
                System.out.println("Parameter \"" + s + "\" not valid");
            }
        }
        return url2;
    }
}
