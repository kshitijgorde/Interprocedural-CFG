import java.awt.Event;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Stars2000 extends Applet implements Runnable
{
    int mouseX;
    int mouseY;
    int framesPerSecond;
    int refreshRate;
    int stars;
    int messages;
    int sizes;
    int currentMessage;
    int currentSize;
    Dimension screenSize;
    Point screenCenter;
    Image offImage;
    Image background;
    Graphics offGrafix;
    Thread refresh;
    Color border;
    Color paper;
    Color ink;
    Color defaultColor;
    Star[] star;
    Color[] grayScale;
    StarsMenuMessage[] message;
    Font font;
    boolean registered;
    boolean copyrights;
    
    public String getAppletInfo() {
        return "The StarsMenu Java(TM) Applet\r\nWritten by Igal Sapir,  igal@dZiners.com\r\n© 1998 dZiners.com\r\nAll rights reserved\r\n\r\nJava is a TradeMark of Sun Microsystems";
    }
    
    public void init() {
        this.screenSize = this.size();
        this.screenCenter = new Point(this.screenSize.width / 2, this.screenSize.height / 2);
        this.offImage = this.createImage(this.screenSize.width, this.screenSize.height);
        this.offGrafix = this.offImage.getGraphics();
        this.defaultColor = this.ink;
        this.font = new Font("Helvetica", 0, 12);
        this.offGrafix.setFont(this.font);
        final String parameter;
        if ((parameter = this.getParameter("background")) != null) {
            this.background = this.getImage(this.getCodeBase(), parameter);
            this.offGrafix.drawImage(this.background, 0, 0, this);
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("stars")) != null) {
            this.stars = Integer.parseInt(parameter2);
        }
        this.star = new Star[this.stars];
        for (int i = 0; i < this.stars; ++i) {
            this.star[i] = new Star((int)(Math.random() * this.screenSize.width * 4.0 - this.screenSize.width * 2), (int)(Math.random() * this.screenSize.height * 4.0 - this.screenSize.height * 2), (int)(Math.random() * 4095.0));
            if (i % 4 == 0) {
                this.star[i].size = 3;
            }
            if (i % 6 == 0) {
                this.star[i].size = 4;
            }
        }
        this.grayScale = new Color[256];
        for (int j = 0; j < 256; ++j) {
            this.grayScale[255 - j] = new Color(j, j, j);
        }
        this.checkRegistration();
        this.initParameters();
    }
    
    public void initParameters() {
        final String parameter;
        if ((parameter = this.getParameter("font")) != null) {
            int n = 0;
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
            final String nextToken = stringTokenizer.nextToken();
            final String nextToken2 = stringTokenizer.nextToken();
            if (nextToken2.equalsIgnoreCase("plain")) {
                n = 0;
            }
            else if (nextToken2.equalsIgnoreCase("bold")) {
                n = 1;
            }
            else if (nextToken2.equalsIgnoreCase("italic")) {
                n = 2;
            }
            else if (nextToken2.equalsIgnoreCase("bolditalic")) {
                n = 3;
            }
            this.font = new Font(nextToken, n, 10);
            this.offGrafix.setFont(this.font);
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("fps")) != null) {
            this.framesPerSecond = Integer.parseInt(parameter2);
            this.refreshRate = 1000 / this.framesPerSecond;
        }
        while (this.getParameter("message#" + (this.messages + 1)) != null) {
            ++this.messages;
        }
        if (!this.registered) {
            ++this.messages;
        }
        this.message = new StarsMenuMessage[this.messages];
        for (int i = 0; i < this.messages; ++i) {
            Color color = this.defaultColor;
            URL url = null;
            this.showStatus("Initializing Message #" + (i + 1));
            if (!this.registered && i == this.messages - 1) {
                break;
            }
            final StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter("message#" + (i + 1)), ",;");
            final String nextToken3 = stringTokenizer2.nextToken();
            String nextToken4;
            if (stringTokenizer2.hasMoreTokens()) {
                nextToken4 = stringTokenizer2.nextToken();
            }
            else {
                nextToken4 = null;
            }
            if (nextToken4 != null) {
                color = this.parseColor(nextToken4);
            }
            if (stringTokenizer2.hasMoreTokens()) {
                final String nextToken5 = stringTokenizer2.nextToken();
                try {
                    url = new URL(nextToken5);
                }
                catch (Exception ex) {}
            }
            if (url != null) {
                this.message[i] = new StarsMenuMessage(nextToken3, color, url);
            }
            else {
                this.message[i] = new StarsMenuMessage(nextToken3, color);
            }
            final String parameter3;
            if ((parameter3 = this.getParameter("sizes#" + (i + 1))) != null) {
                this.message[i].setSizes(Integer.parseInt(parameter3));
            }
            else {
                this.message[i].setSizes(this.sizes);
            }
            final String parameter4;
            if ((parameter4 = this.getParameter("maxsize#" + (i + 1))) != null) {
                this.message[i].setMaxSize(Integer.parseInt(parameter4));
            }
            final String parameter5;
            if ((parameter5 = this.getParameter("status#" + (i + 1))) != null) {
                this.message[i].status = parameter5;
            }
            if (this.getParameter("setbounds#" + (i + 1)) != null) {
                this.message[i].setBound(this.screenSize, this.offGrafix);
            }
            this.message[i].setFont(this.font);
            this.message[i].init(this.screenCenter, this.offGrafix);
        }
        if (!this.registered) {
            final Color color2 = new Color(230, 230, 107);
            URL url2 = null;
            try {
                url2 = new URL("http://www.dZiners.com/");
            }
            catch (Exception ex2) {}
            if (url2 != null) {
                this.message[this.messages - 1] = new StarsMenuMessage("© 1998 dZiners.com", color2, url2);
            }
            else {
                this.message[this.messages - 1] = new StarsMenuMessage("© 1998 dZiners.com", color2);
            }
            this.message[this.messages - 1].setBound(this.screenSize, this.offGrafix);
            this.message[this.messages - 1].status = "Java applet by dZiners.com © 1998 All rights reserved";
            this.message[this.messages - 1].init(this.screenCenter, this.offGrafix);
        }
    }
    
    public void checkRegistration() {
        if (this.getParameter("copyright").equalsIgnoreCase("© 1998 dZiners.com, http://www.dZiners.com/") && this.getParameter("author").equalsIgnoreCase("Igal Sapir")) {
            this.copyrights = true;
        }
        if (this.getParameter("license").equalsIgnoreCase("freeware")) {
            this.registered = false;
        }
    }
    
    public void start() {
        if (this.refresh == null) {
            (this.refresh = new Thread(this)).start();
        }
    }
    
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        while (this.refresh != null) {
            try {
                currentTimeMillis += this.refreshRate;
                Thread.sleep(Math.max(0L, currentTimeMillis - System.currentTimeMillis()));
            }
            catch (Exception ex) {}
            this.repaint();
        }
    }
    
    public void stop() {
        if (this.refresh != null) {
            this.refresh.suspend();
            this.refresh = null;
        }
    }
    
    public void setMousePosition(final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.setMousePosition(n, n2);
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.setMousePosition(n, n2);
        if (this.message[this.currentMessage].url != null) {
            try {
                this.getAppletContext().showDocument(this.message[this.currentMessage].url);
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.setMousePosition(n, n2);
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.setMousePosition(n, n2);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setMousePosition(-1, -1);
        return true;
    }
    
    public Color parseColor(final String s) {
        Color color = Color.gray;
        if (s.equalsIgnoreCase("black")) {
            color = Color.black;
        }
        if (s.equalsIgnoreCase("white")) {
            color = Color.white;
        }
        if (s.equalsIgnoreCase("red")) {
            color = Color.red;
        }
        if (s.equalsIgnoreCase("green")) {
            color = Color.green;
        }
        if (s.equalsIgnoreCase("blue")) {
            color = Color.blue;
        }
        if (s.equalsIgnoreCase("yellow")) {
            color = Color.yellow;
        }
        if (s.equalsIgnoreCase("magenta")) {
            color = Color.magenta;
        }
        if (s.equalsIgnoreCase("cyan")) {
            color = Color.cyan;
        }
        if (s.equalsIgnoreCase("pink")) {
            color = Color.pink;
        }
        if (s.equalsIgnoreCase("orange")) {
            color = Color.orange;
        }
        if (s.equalsIgnoreCase("gray")) {
            color = Color.gray;
        }
        if (s.length() == 7 && s.charAt(0) == '#') {
            color = new Color(Integer.parseInt(s.substring(1, 3), 16), Integer.parseInt(s.substring(3, 5), 16), Integer.parseInt(s.substring(5, 7), 16));
        }
        return color;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.offGrafix.setColor(this.paper);
        this.offGrafix.fillRect(0, 0, this.screenSize.width, this.screenSize.height);
        if (this.background != null) {
            this.offGrafix.drawImage(this.background, 0, 0, this);
        }
        for (int i = 0; i < this.stars; ++i) {
            final Star star = this.star[i];
            star.z -= 50;
            if (this.star[i].z < 512) {
                this.star[i].z = 4095;
            }
            final int z = this.star[i].z;
            final int n = (this.star[i].x << 9) / this.star[i].z + this.screenCenter.x;
            final int n2 = (this.star[i].y << 9) / this.star[i].z + this.screenCenter.y;
            final int size = this.star[i].size;
            this.offGrafix.setColor(this.grayScale[z >> 4]);
            this.offGrafix.fillRect(n, n2, size, size);
        }
        if (!this.message[this.currentMessage].status.equals("")) {
            this.showStatus(this.message[this.currentMessage].status);
        }
        this.message[this.currentMessage].paint(this.offGrafix, this.currentSize);
        if (++this.currentSize >= this.message[this.currentMessage].sizes) {
            this.currentSize = 0;
            if (++this.currentMessage >= this.messages) {
                this.currentMessage = 0;
            }
        }
        if (this.border != null) {
            this.offGrafix.setColor(this.border);
            this.offGrafix.drawRect(0, 0, this.screenSize.width - 1, this.screenSize.height - 1);
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    public Stars2000() {
        this.mouseX = -1;
        this.mouseY = -1;
        this.framesPerSecond = 20;
        this.refreshRate = 1000 / this.framesPerSecond;
        this.stars = 80;
        this.sizes = 75;
        this.paper = Color.black;
        this.ink = Color.green;
        this.registered = false;
        this.copyrights = false;
    }
}
