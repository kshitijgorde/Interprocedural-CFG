import java.awt.Rectangle;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class vrover3 extends Applet implements Runnable
{
    private int butCnt;
    private boolean horiStyle;
    private int activeIndex;
    private Thread m_vrover;
    private Graphics m_Graphics;
    private Image[] m_Images;
    private int m_nImgWidth;
    private int m_nImgHeight;
    private boolean m_fAllLoaded;
    private final int NUM_IMAGES = 4;
    private String[] m_url;
    private String[] m_text;
    private String[] m_frame;
    private Color m_fgColor;
    private Color m_bkColor;
    private int w;
    private int h;
    private final String PARAM_url = "url";
    private final String PARAM_frame = "frame";
    private final String PARAM_text = "text";
    private final String PARAM_bkColor = "bkColor";
    private final String PARAM_fgColor = "fgColor";
    private final String PARAM_w = "w";
    private final String PARAM_h = "h";
    private final String PARAM_style = "style";
    
    public void stop() {
        if (this.m_vrover != null) {
            this.m_vrover.stop();
            this.m_vrover = null;
        }
    }
    
    private void validateAI(final int n, final int n2) {
        if (this.xy2ai(n, n2) != this.activeIndex && this.m_fAllLoaded) {
            if (this.activeIndex != -1) {
                this.displayImage(this.m_Graphics, this.activeIndex, 0);
            }
            this.activeIndex = this.xy2ai(n, n2);
            this.displayImage(this.m_Graphics, this.activeIndex, 1);
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.m_fAllLoaded) {
            this.activeIndex = this.xy2ai(n, n2);
            this.displayImage(this.m_Graphics, this.activeIndex, 1);
        }
        return true;
    }
    
    private int parse(final String[] array, final String s) {
        int n = 0;
        int n2 = 0;
        while (true) {
            final int index = s.indexOf(44, n);
            if (index == -1) {
                break;
            }
            array[n2] = s.substring(n, index);
            array[n2] = array[n2].trim();
            n = index + 1;
            ++n2;
        }
        array[n2] = s.substring(n);
        return n2 + 1;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.m_fAllLoaded) {
            this.displayImage(this.m_Graphics, this.activeIndex, 0);
            this.activeIndex = -1;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        for (int i = 0; i < this.butCnt; ++i) {
            this.draw1(graphics, i);
        }
    }
    
    private int xy2ai(final int n, final int n2) {
        if (this.horiStyle) {
            return n / this.w;
        }
        return n2 / this.h;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.activeIndex == -1) {
            return true;
        }
        this.validateAI(n, n2);
        this.displayImage(this.m_Graphics, this.xy2ai(n, n2), 2);
        try {
            Thread.sleep(60L);
        }
        catch (InterruptedException ex) {}
        this.displayImage(this.m_Graphics, this.xy2ai(n, n2), 1);
        try {
            this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.m_url[this.activeIndex]), (this.m_frame[this.activeIndex] == null) ? "_self" : this.m_frame[this.activeIndex]);
        }
        catch (MalformedURLException ex2) {}
        return true;
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "url", "String", "HREF" }, { "frame", "String", "Target frame" }, { "w", "Integer", "button width" }, { "h", "Integer", "button height" }, { "bkColor", "Integer", "Background Color" }, { "fgColor", "Integer", "Foreground Color" }, { "text", "String", "Text to be displayed" }, { "style", "Integer", "Style" } };
    }
    
    private void displayImage(final Graphics graphics, final int n, final int n2) {
        if (!this.m_fAllLoaded) {
            return;
        }
        if (this.horiStyle) {
            graphics.drawImage(this.m_Images[n2], this.w * n + 5, (this.h - this.m_nImgHeight) / 2, null);
            return;
        }
        graphics.drawImage(this.m_Images[n2], 5, n * this.h + (this.h - this.m_nImgHeight) / 2, null);
    }
    
    public void start() {
        this.setBackground(this.m_bkColor);
        if (this.m_vrover == null) {
            (this.m_vrover = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: vrover\r\n" + "Author: Vitas Ramanchauskas\r\n" + "Created solely";
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.m_fAllLoaded) {
            this.validateAI(n, n2);
            this.displayImage(this.m_Graphics, this.xy2ai(n, n2), 2);
            try {
                Thread.sleep(60L);
            }
            catch (InterruptedException ex) {}
            this.displayImage(this.m_Graphics, this.xy2ai(n, n2), 3);
            try {
                Thread.sleep(60L);
            }
            catch (InterruptedException ex2) {}
        }
        return true;
    }
    
    public void run() {
        if (!this.m_fAllLoaded) {
            this.m_Graphics = this.getGraphics();
            this.repaint();
            this.m_Images = new Image[4];
            final MediaTracker mediaTracker = new MediaTracker(this);
            int n = 1;
            do {
                mediaTracker.addImage(this.m_Images[n - 1] = this.getImage(this.getCodeBase(), "phase" + n + ".gif"), 0);
            } while (++n <= 4);
            try {
                mediaTracker.waitForAll();
                this.m_fAllLoaded = !mediaTracker.isErrorAny();
            }
            catch (InterruptedException ex) {}
            if (!this.m_fAllLoaded) {
                this.stop();
                this.m_Graphics.drawString("Error loading images!", 10, 40);
                return;
            }
            this.m_nImgWidth = this.m_Images[0].getWidth(this);
            this.m_nImgHeight = this.m_Images[0].getHeight(this);
        }
        this.repaint();
        final MediaTracker mediaTracker2 = new MediaTracker(this);
        try {
            mediaTracker2.addImage(this.getImage(new URL("http://webdon.com/vrover3.gif")), 0);
            try {
                mediaTracker2.waitForAll(4444L);
            }
            catch (InterruptedException ex2) {}
        }
        catch (MalformedURLException ex3) {}
    }
    
    public void init() {
        final String parameter = this.getParameter("url");
        if (parameter != null) {
            this.parse(this.m_url, parameter);
        }
        final String parameter2 = this.getParameter("bkColor");
        if (parameter2 != null) {
            this.setBackground(this.m_bkColor = new Color(Integer.parseInt(parameter2)));
        }
        final String parameter3 = this.getParameter("fgColor");
        if (parameter3 != null) {
            this.m_fgColor = new Color(Integer.parseInt(parameter3));
        }
        final String parameter4 = this.getParameter("text");
        if (parameter4 != null) {
            this.butCnt = this.parse(this.m_text, parameter4);
        }
        final String parameter5 = this.getParameter("frame");
        if (parameter5 != null) {
            this.parse(this.m_frame, parameter5);
        }
        final String parameter6 = this.getParameter("w");
        if (parameter6 != null) {
            this.w = Integer.parseInt(parameter6);
        }
        final String parameter7 = this.getParameter("h");
        if (parameter7 != null) {
            this.h = Integer.parseInt(parameter7);
        }
        final String parameter8 = this.getParameter("style");
        if (parameter8 != null) {
            this.horiStyle = (Integer.parseInt(parameter8) == 1);
        }
    }
    
    public vrover3() {
        this.activeIndex = -1;
        this.m_url = new String[50];
        this.m_text = new String[50];
        this.m_frame = new String[50];
        this.m_fgColor = new Color(255, 255, 40);
        this.m_bkColor = new Color(135, 170, 180);
        this.w = 80;
        this.h = 32;
        this.m_url[0] = "index.htm";
        this.m_text[0] = "Pressme";
        this.m_frame[0] = "_self";
    }
    
    private void draw1(final Graphics graphics, final int n) {
        Rectangle rectangle;
        if (this.horiStyle) {
            rectangle = new Rectangle(this.w * n, 0, this.w, this.h);
        }
        else {
            rectangle = new Rectangle(0, n * this.h, this.w, this.h);
        }
        if (this.m_fAllLoaded) {
            graphics.clearRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            graphics.setColor(new Color(192, 192, 192));
            graphics.drawLine(rectangle.x, rectangle.y, rectangle.x + rectangle.width - 2, rectangle.y);
            graphics.drawLine(rectangle.x, rectangle.y, rectangle.x, rectangle.y + rectangle.height - 2);
            graphics.setColor(new Color(120, 120, 120));
            graphics.drawLine(rectangle.x + rectangle.width - 2, rectangle.y + 1, rectangle.x + rectangle.width - 2, rectangle.y + rectangle.height - 2);
            graphics.drawLine(rectangle.x + 1, rectangle.y + rectangle.height - 2, rectangle.x + rectangle.width - 2, rectangle.y + rectangle.height - 2);
            graphics.setColor(new Color(0, 0, 0));
            graphics.drawLine(rectangle.x + rectangle.width - 1, rectangle.y, rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 1);
            graphics.drawLine(rectangle.x, rectangle.y + rectangle.height - 1, rectangle.x + rectangle.width - 1, rectangle.y + rectangle.height - 1);
            this.displayImage(graphics, n, 0);
        }
        graphics.setColor(this.m_fgColor);
        graphics.drawString(this.m_text[n], rectangle.x + 25, rectangle.y + rectangle.height / 2 + graphics.getFontMetrics().getAscent() / 2);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.validateAI(n, n2);
        return true;
    }
}
