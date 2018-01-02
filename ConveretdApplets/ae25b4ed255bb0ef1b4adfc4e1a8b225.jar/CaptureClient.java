import java.util.Date;
import java.awt.FontMetrics;
import java.net.URLConnection;
import java.io.IOException;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.awt.image.ImageObserver;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CaptureClient extends Applet implements Runnable
{
    URL imageURL;
    URL clickURL;
    String clickURLString;
    String clickURLWindow;
    Thread thread;
    boolean running;
    String status;
    Image image;
    Image imageBuf;
    int viewWidth;
    int viewHeight;
    int delay;
    int statusFlag;
    int statusHeight;
    Font statusFont;
    String statusText;
    Color statusTextColor;
    Color backgroundColor;
    int noSplash;
    boolean splash;
    boolean debugFlag;
    boolean mouseOver;
    
    private void finit$() {
        this.running = false;
        this.splash = false;
        this.debugFlag = false;
        this.mouseOver = false;
    }
    
    @Override
    public String getAppletInfo() {
        return "KidsVision Kiddie Cams";
    }
    
    @Override
    public String[][] getParameterInfo() {
        return new String[][] { { "image", "URL", "Relative URL of image file" }, { "url", "URL", "URL to launch when clicked" }, { "delay", "integer", "Refresh delay, in seconds" }, { "delayMS", "integer", "Refresh delay, in milliseconds" }, { "status", "integer", "Status display flag (0..2)" }, { "statusFont", "string", "Status font (\"name,size\")" }, { "statusText", "string", "Status text message" }, { "statusTextColor", "integer", "Status text color" }, { "backgroundColor", "integer", "Background color" } };
    }
    
    @Override
    public void init() {
        this.debugMessage("init()");
        this.noSplash = this.getParameter("noSplash", 0);
        this.statusFlag = this.getParameter("status", 0);
        this.delay = this.getParameter("delayMS", 0);
        this.delay += 1000 * this.getParameter("delay", 0);
        if (this.delay == 0) {
            this.delay = 1000;
        }
        this.delay = this.delay / 50 * 50;
        this.viewWidth = this.size().width;
        this.viewHeight = this.size().height;
        this.imageBuf = this.createImage(this.viewWidth, this.viewHeight);
        if (this.statusFlag == 2) {
            final Graphics graphics = this.getGraphics();
            final String parameter = this.getParameter("statusFont");
            if (parameter != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
                final String nextToken = stringTokenizer.nextToken();
                final String nextToken2 = stringTokenizer.nextToken();
                this.debugMessage("font=" + nextToken + "size=" + nextToken2);
                this.statusFont = new Font(nextToken, 0, Integer.valueOf(nextToken2));
                if (nextToken != null) {
                    graphics.setFont(this.statusFont);
                }
            }
            this.statusHeight = graphics.getFontMetrics().getHeight();
            this.viewHeight -= this.statusHeight;
            this.debugMessage("statusHeight= " + this.statusHeight);
            graphics.dispose();
        }
        this.statusText = this.getParameter("statusText");
        if (this.statusText == null) {
            this.statusText = "Reload in %s seconds (%H:%M:%S)";
        }
        this.statusTextColor = Color.black;
        final String parameter2 = this.getParameter("statusTextColor");
        try {
            this.statusTextColor = new Color(Integer.decode(parameter2));
        }
        catch (NumberFormatException ex) {}
        catch (NullPointerException ex2) {}
        this.backgroundColor = Color.lightGray;
        final String parameter3 = this.getParameter("backgroundColor");
        try {
            this.backgroundColor = new Color(Integer.decode(parameter3));
        }
        catch (NumberFormatException ex3) {}
        catch (NullPointerException ex4) {}
        final String parameter4 = this.getParameter("image");
        try {
            this.imageURL = new URL(this.getCodeBase(), parameter4);
        }
        catch (MalformedURLException ex5) {
            this.statusMessage("Malformed URL \"" + parameter4 + "\"");
            this.debugMessage("Malformed URL \"" + parameter4 + "\"");
        }
    }
    
    @Override
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.clickURL != null) {
            String clickURLWindow = "_self";
            if (this.clickURLWindow != null) {
                clickURLWindow = this.clickURLWindow;
            }
            this.getAppletContext().showDocument(this.clickURL, clickURLWindow);
        }
        return true;
    }
    
    @Override
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.clickURL != null) {
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            ((Frame)container).setCursor(12);
            this.mouseOver = true;
            this.showStatus(this.clickURLString);
        }
        return true;
    }
    
    @Override
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.clickURL != null) {
            Container container;
            for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
            ((Frame)container).setCursor(0);
            this.mouseOver = false;
            this.showStatus("");
        }
        return true;
    }
    
    @Override
    public void update(final Graphics graphics) {
        this.paint(this.imageBuf.getGraphics());
        graphics.drawImage(this.imageBuf, 0, 0, this);
    }
    
    @Override
    public void paint(final Graphics graphics) {
        if (this.splash) {
            this.displaySplash();
            return;
        }
        if (this.image != null) {
            graphics.drawImage(this.image, 0, 0, this.viewWidth, this.viewHeight, this);
            if (this.statusFlag == 2) {
                graphics.setColor(this.backgroundColor);
                graphics.fillRect(0, this.viewHeight, this.viewWidth, this.viewHeight + this.statusHeight);
                graphics.setColor(this.statusTextColor);
                if (this.status != null) {
                    if (this.statusFont != null) {
                        graphics.setFont(this.statusFont);
                    }
                    graphics.drawString(this.status, 0, this.viewHeight + this.statusHeight - 2);
                }
            }
        }
        else if (this.statusFlag != 1 && this.status != null) {
            graphics.setColor(this.backgroundColor);
            graphics.fillRect(0, 0, this.viewWidth, this.viewHeight + this.statusHeight);
            graphics.setColor(this.statusTextColor);
            if (this.statusFont != null) {
                graphics.setFont(this.statusFont);
            }
            graphics.drawString(this.status, 0, this.viewHeight + this.statusHeight - 2);
        }
    }
    
    @Override
    public void start() {
        this.debugMessage("start()");
        if (this.running) {
            return;
        }
        this.running = true;
        (this.thread = new Thread(this)).start();
        this.debugMessage("thread started");
    }
    
    @Override
    public void stop() {
        this.debugMessage("stop()");
        this.running = false;
        while (this.thread != null) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
        this.thread = null;
        this.debugMessage("thread killed");
    }
    
    @Override
    public void run() {
        int n = 0;
        this.debugMessage("run()");
        this.clickURLString = "http://www.stardot-tech.com";
        try {
            this.clickURL = new URL(this.clickURLString);
        }
        catch (MalformedURLException ex2) {
            this.clickURL = null;
            this.clickURLString = null;
        }
        if (this.noSplash == 0) {
            this.splash = true;
            this.repaint();
            this.delay(2000L);
            this.splash = false;
        }
        this.clickURL = null;
        this.clickURLString = null;
        this.clickURLWindow = null;
        this.mouseOver = false;
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = container.getParent()) {}
        ((Frame)container).setCursor(0);
        this.clickURLWindow = this.getParameter("urlWindow");
        this.clickURLString = this.getParameter("url");
        if (this.clickURLString == null) {
            this.clickURLString = "http://www.stardot-tech.com";
        }
        if (this.clickURLString.length() > 0) {
            try {
                this.clickURL = new URL(this.clickURLString);
            }
            catch (MalformedURLException ex3) {
                try {
                    this.clickURL = new URL(this.getCodeBase(), this.clickURLString);
                }
                catch (MalformedURLException ex4) {
                    this.statusMessage("Malformed URL \"" + this.clickURLString + "\"");
                    this.debugMessage("Malformed URL \"" + this.clickURLString + "\"");
                    this.clickURL = null;
                    this.clickURLString = null;
                }
            }
        }
        while (this.running) {
            final String parameter = this.getParameter("image");
            URL url;
            try {
                url = new URL(this.getCodeBase(), parameter);
            }
            catch (MalformedURLException ex5) {
                this.statusMessage("Malformed URL \"" + parameter + "\"");
                this.debugMessage("Malformed URL \"" + parameter + "\"");
                return;
            }
            int contentLength = 0;
            Label_0661: {
                try {
                    this.debugMessage("opening connection");
                    final URLConnection openConnection = url.openConnection();
                    this.debugMessage("connection opened");
                    openConnection.setUseCaches(false);
                    openConnection.setDoOutput(false);
                    openConnection.setDoInput(true);
                    openConnection.setDefaultUseCaches(false);
                    this.debugMessage("connected; getting content length");
                    contentLength = openConnection.getContentLength();
                    this.debugMessage("got content length");
                    if (contentLength > 0 && contentLength != n) {
                        this.statusMessage("Loading (" + openConnection.getContentType() + "/" + contentLength + " bytes)");
                        this.debugMessage("image size " + contentLength + " bytes");
                        final byte[] array = new byte[contentLength];
                        final BufferedInputStream bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
                        while (true) {
                            int read;
                            for (int i = 0; i < contentLength; i += read) {
                                read = bufferedInputStream.read(array, i, contentLength - i);
                                if (read <= 0) {
                                    this.debugMessage("read failure");
                                    bufferedInputStream.close();
                                    this.debugMessage("done");
                                    this.image = Toolkit.getDefaultToolkit().createImage(array);
                                    if (this.image == null) {
                                        this.debugMessage("null image");
                                    }
                                    this.repaint();
                                    break Label_0661;
                                }
                            }
                            continue;
                        }
                    }
                }
                catch (IOException ex) {
                    this.statusMessage(ex.toString());
                    this.debugMessage(ex.toString());
                    contentLength = 0;
                }
            }
            n = contentLength;
            System.gc();
            if (this.delay > 0) {
                for (int j = this.delay / 50 * 50; j > 0; j -= 50) {
                    if (this.statusFlag > 0 && j % 1000 == 0 && (this.statusFlag != 2 || !this.mouseOver)) {
                        this.showTimer(this.statusText, j);
                    }
                    this.delay(50L);
                }
            }
        }
        this.thread = null;
    }
    
    private void showTimer(final String s, final int n) {
        final int n2 = n / 1000;
        final int n3 = n2 / 3600;
        final int n4 = n2 - n3 * 3600;
        final int n5 = n4 / 60;
        final int n6 = n4 - n5 * 60;
        final StringBuffer sb = new StringBuffer(128);
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '%') {
                switch (s.charAt(++i)) {
                    case 'H': {
                        if (n3 < 10) {
                            sb.append('0');
                        }
                        sb.append(n3);
                        break;
                    }
                    case 'M': {
                        if (n5 < 10) {
                            sb.append('0');
                        }
                        sb.append(n5);
                        break;
                    }
                    case 'S': {
                        if (n6 < 10) {
                            sb.append('0');
                        }
                        sb.append(n6);
                        break;
                    }
                    case 's': {
                        sb.append(n / 1000);
                        break;
                    }
                }
            }
            else {
                sb.append(s.charAt(i));
            }
        }
        this.statusMessage(sb.toString());
    }
    
    @Override
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.image = image;
            this.repaint();
        }
        else if ((n & 0x40) != 0x0) {
            this.statusMessage("Error loading image");
        }
        else if ((n & 0x80) != 0x0) {
            this.statusMessage("Image load aborted");
        }
        return true;
    }
    
    private void displaySplash() {
        final String[] array = { "Kiddie Cams", "http://www.kidsvision.com", "", "Version 2.0.1", "", "©KidsVision" };
        final int[] array2 = { 22, 14, 18, 14, 18, 12 };
        final int length = array2.length;
        final Graphics graphics = this.imageBuf.getGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.size().width, this.size().height);
        graphics.setColor(Color.white);
        while (true) {
            int stringWidth;
            int n = stringWidth = 0;
            for (int i = 0; i < length; ++i) {
                graphics.setFont(new Font("Helvetica", 0, array2[i]));
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                if (fontMetrics.stringWidth(array[i]) > stringWidth) {
                    stringWidth = fontMetrics.stringWidth(array[i]);
                }
                n += fontMetrics.getHeight();
            }
            if (stringWidth <= 85 * this.size().width / 100 && n <= 85 * this.size().height / 100) {
                int n2 = (this.size().height - n) / 2;
                for (int j = 0; j < length; ++j) {
                    graphics.setFont(new Font("Helvetica", 0, array2[j]));
                    final FontMetrics fontMetrics2 = graphics.getFontMetrics();
                    final int stringWidth2 = fontMetrics2.stringWidth(array[j]);
                    n2 += fontMetrics2.getHeight();
                    graphics.drawString(array[j], (this.size().width - stringWidth2) / 2, n2);
                }
                this.getGraphics().drawImage(this.imageBuf, 0, 0, this);
                return;
            }
            for (int k = 0; k < length; ++k) {
                final int[] array3 = array2;
                final int n3 = k;
                --array3[n3];
                if (array2[k] < 4) {
                    return;
                }
            }
        }
    }
    
    private void delay(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    private int getParameter(final String s, final int n) {
        try {
            return Integer.parseInt(this.getParameter(s));
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    private void statusMessage(final String status) {
        this.status = status;
        if (this.statusFlag == 2) {
            this.repaint();
        }
        else if (this.statusFlag == 1 && this.status != null) {
            this.showStatus(status);
        }
    }
    
    private void debugMessage(final String s) {
        if (this.debugFlag) {
            System.out.println(new Date() + ": " + s);
            this.statusMessage(new Date() + ": " + s);
            Thread.yield();
        }
    }
    
    public CaptureClient() {
        this.finit$();
    }
}
