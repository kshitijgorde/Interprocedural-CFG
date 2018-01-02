import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Choice;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Spice2 extends Applet implements Runnable
{
    Choice page;
    String webpage;
    String pageURL;
    String[] holdPage;
    String[] holdURL;
    int NUMBER_OF_URLS;
    int i;
    Thread runner;
    Image back;
    Image fore;
    Image workspace;
    Graphics offscreen;
    String text;
    String fontName;
    int fontSize;
    int x1;
    int x2;
    
    public Spice2() {
        this.page = new Choice();
        this.NUMBER_OF_URLS = 256;
        this.fontSize = 24;
        this.x1 = 0;
    }
    
    public void init() {
        this.page = new Choice();
        this.holdPage = new String[this.NUMBER_OF_URLS];
        this.holdURL = new String[this.NUMBER_OF_URLS];
        this.workspace = this.createImage(this.size().width, this.size().height);
        this.offscreen = this.workspace.getGraphics();
        final String parameter = this.getParameter("backImage");
        if (parameter != null) {
            this.back = this.getImage(this.getDocumentBase(), parameter);
        }
        final String parameter2 = this.getParameter("foreImage");
        if (parameter2 != null) {
            this.fore = this.getImage(this.getDocumentBase(), parameter2);
        }
        this.x2 = this.size().width;
        this.text = this.getParameter("line");
        this.fontName = this.getParameter("fontType");
        if (this.fontName == null) {
            this.fontName = "ArialBlack";
        }
        final String parameter3 = this.getParameter("fontSize");
        if (parameter3 != null) {
            this.fontSize = Integer.parseInt("0" + parameter3);
        }
        this.add(this.page);
        this.i = 0;
        while (this.i < this.NUMBER_OF_URLS) {
            this.webpage = this.getParameter("page" + this.i);
            this.pageURL = this.getParameter("url" + this.i);
            if (this.webpage == null) {
                break;
            }
            if (this.pageURL == null) {
                break;
            }
            this.holdPage[this.i] = this.webpage;
            this.holdURL[this.i] = this.pageURL;
            this.page.addItem(this.holdPage[this.i]);
            ++this.i;
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
            --this.x1;
            --this.x2;
            if (this.x1 <= this.size().width * -1) {
                this.x1 = this.size().width;
            }
            if (this.x2 <= this.size().width * -1) {
                this.x2 = this.size().width;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.offscreen.drawImage(this.back, this.x1, 0, null);
        this.offscreen.drawImage(this.back, this.x2, 0, null);
        if (this.fore != null) {
            this.offscreen.drawImage(this.fore, 0, 0, null);
        }
        if (this.text != null) {
            this.offscreen.setColor(Color.black);
            final Font font = new Font(this.fontName, 1, this.fontSize);
            final FontMetrics fontMetrics = this.getFontMetrics(font);
            this.offscreen.setFont(font);
            final int n = (this.size().width - fontMetrics.stringWidth(this.text)) / 2;
            final int n2 = this.size().height / 2 - fontMetrics.getHeight() / 4;
            this.offscreen.drawString(this.text, n, n2);
            this.offscreen.setColor(Color.white);
            this.offscreen.drawString(this.text, n - 2, n2 - 2);
        }
        graphics.drawImage(this.workspace, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target.equals(this.page)) {
            final int selectedIndex = this.page.getSelectedIndex();
            try {
                this.getAppletContext().showDocument(new URL(this.holdURL[selectedIndex]));
            }
            catch (MalformedURLException ex) {
                System.err.println(ex);
            }
        }
        return true;
    }
    
    public String getAppletInfo() {
        return "Title: Spice2 \nAuthor: Sam A. Napier.";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "area[n]" } };
    }
}
