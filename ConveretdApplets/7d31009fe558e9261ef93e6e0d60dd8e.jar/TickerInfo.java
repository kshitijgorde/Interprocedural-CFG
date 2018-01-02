import java.awt.image.ImageObserver;
import java.util.Enumeration;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.Vector;
import java.net.URLConnection;
import java.net.URL;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class TickerInfo
{
    protected int width;
    protected int height;
    protected String fontname;
    protected int fontsize;
    protected int smallFontsize;
    protected int tinyFontsize;
    protected Font font;
    protected Color fgColor;
    protected Color color1;
    protected Color bgColor;
    protected Image bgImage;
    protected Applet app;
    protected int appWidth;
    protected int appHeight;
    protected Image appImage;
    protected Graphics appGraphics;
    protected String source;
    protected URL srcURL;
    protected URLConnection conn;
    protected Vector data;
    protected Vector newdata;
    protected Color more;
    protected Color less;
    protected Color same;
    protected int margin;
    protected int space;
    protected boolean horizontal;
    protected int dataHeight;
    
    protected TickerInfo(final Applet app) {
        this.width = 300;
        this.height = 100;
        this.fontname = "Helvetica";
        this.fontsize = 14;
        this.smallFontsize = 12;
        this.tinyFontsize = 10;
        this.font = null;
        this.fgColor = null;
        this.color1 = null;
        this.bgColor = null;
        this.bgImage = null;
        this.app = null;
        this.appWidth = 0;
        this.appHeight = 0;
        this.appImage = null;
        this.appGraphics = null;
        this.source = null;
        this.srcURL = null;
        this.conn = null;
        this.data = null;
        this.newdata = null;
        this.more = null;
        this.less = null;
        this.same = null;
        this.margin = 0;
        this.space = 0;
        this.horizontal = true;
        this.dataHeight = 0;
        this.app = app;
        final Dimension size = app.getSize();
        this.width = size.width;
        this.height = size.height;
        app.resize(this.width, this.height);
        this.appImage = app.createImage(this.width, this.height);
        this.appGraphics = this.appImage.getGraphics();
        this.fontname = app.getParameter("Font");
        try {
            this.fontsize = Integer.parseInt(app.getParameter("Size"));
        }
        catch (NumberFormatException ex4) {}
        try {
            this.smallFontsize = Integer.parseInt(app.getParameter("Smallsize"));
        }
        catch (NumberFormatException ex5) {}
        try {
            this.tinyFontsize = Integer.parseInt(app.getParameter("Tinysize"));
        }
        catch (NumberFormatException ex6) {}
        this.font = new Font(this.fontname, 1, this.fontsize);
        final String parameter = app.getParameter("BGImage");
        if (parameter != null) {
            try {
                this.bgImage = app.getImage(new URL(app.getDocumentBase(), parameter));
                if (this.bgImage != null) {
                    final MediaTracker mediaTracker = new MediaTracker(app);
                    mediaTracker.addImage(this.bgImage, 0);
                    mediaTracker.waitForID(0);
                    if (mediaTracker.isErrorID(0)) {
                        this.log("error loading background image " + parameter);
                    }
                }
                else {
                    this.log("not a valid background image: " + parameter);
                }
            }
            catch (MalformedURLException ex) {
                this.log("MalformedURLException: \"" + parameter + "\"", ex);
            }
            catch (InterruptedException ex2) {
                this.log("interrupted during load of " + parameter, ex2);
            }
        }
        try {
            this.bgColor = new Color(Integer.parseInt(app.getParameter("BGColor"), 16));
        }
        catch (NumberFormatException ex7) {}
        if (this.bgColor != null) {
            app.setBackground(this.bgColor);
        }
        else {
            this.bgColor = app.getBackground();
        }
        try {
            this.fgColor = new Color(Integer.parseInt(app.getParameter("Color"), 16));
        }
        catch (NumberFormatException ex8) {}
        if (this.fgColor != null) {
            app.setForeground(this.fgColor);
        }
        else {
            this.fgColor = app.getForeground();
        }
        try {
            this.color1 = new Color(Integer.parseInt(app.getParameter("Color1"), 16));
        }
        catch (NumberFormatException ex9) {}
        try {
            this.more = new Color(Integer.parseInt(app.getParameter("More"), 16));
        }
        catch (NumberFormatException ex10) {
            this.more = new Color(65280);
        }
        try {
            this.less = new Color(Integer.parseInt(app.getParameter("Less"), 16));
        }
        catch (NumberFormatException ex11) {
            this.less = new Color(16711680);
        }
        try {
            this.same = new Color(Integer.parseInt(app.getParameter("Same"), 16));
        }
        catch (NumberFormatException ex12) {
            this.same = this.fgColor;
        }
        this.source = app.getParameter("Data");
        if (this.source == null) {
            this.source = "Text: Text=\"" + app.getParameter("Text") + "\"";
        }
        try {
            this.srcURL = new URL(app.getDocumentBase(), this.source);
        }
        catch (MalformedURLException ex3) {
            this.log("MalformedURLException: \"" + this.source + "\"", ex3);
        }
        try {
            this.space = Integer.parseInt(app.getParameter("Space"), 10);
        }
        catch (NumberFormatException ex13) {
            this.space = 30;
        }
        try {
            this.margin = Integer.parseInt(app.getParameter("Margin"), 10);
        }
        catch (NumberFormatException ex14) {
            this.margin = 0;
        }
    }
    
    protected void status(final String s) {
        this.app.showStatus(s);
    }
    
    protected void log(final String s) {
        this.status(s);
        System.out.println(s);
    }
    
    protected void log(final String s, final Exception ex) {
        this.log(s);
        System.out.println("Stack trace:");
        ex.printStackTrace(System.out);
    }
    
    protected boolean allege(final boolean b, final String s) {
        if (!b) {
            this.log(s);
        }
        return b;
    }
    
    protected int getWidth() {
        if (this.appWidth == 0) {
            this.resize();
        }
        return this.appWidth;
    }
    
    protected int getHeight() {
        if (this.appHeight == 0) {
            this.resize();
        }
        return this.appHeight;
    }
    
    protected void add(final Vector vector, final String s) {
        TickerData tickerData = null;
        if (s.startsWith("Text:")) {
            tickerData = new TickerText(this, s.substring(5));
        }
        else if (s.startsWith("Price:")) {
            tickerData = new TickerPrice(this, s.substring(6));
        }
        else if (s.startsWith("Image:")) {
            tickerData = new TickerImage(this, s.substring(6));
        }
        if (tickerData != null) {
            vector.addElement(tickerData);
        }
    }
    
    protected synchronized void update() {
        final Vector newdata = new Vector(20);
        if (!this.allege(this.source != null, "No data source specified.")) {
            return;
        }
        try {
            this.srcURL = new URL(this.app.getDocumentBase(), this.source + "?timestamp=" + System.currentTimeMillis());
        }
        catch (MalformedURLException ex) {
            this.log("MalformedURLException: \"" + this.source + "\"", ex);
        }
        if (!this.allege(this.srcURL != null, "Illegal source URL: \"" + this.source + "\"")) {
            return;
        }
        this.status("Reading from \"" + this.source + "\"");
        try {
            this.conn = this.srcURL.openConnection();
            if (!this.allege(this.conn != null, "Failed to open connection")) {
                return;
            }
            final String contentEncoding = this.conn.getContentEncoding();
            final InputStream inputStream = this.conn.getInputStream();
            if (!this.allege(inputStream != null, "Failed to get input stream")) {
                return;
            }
            InputStreamReader inputStreamReader;
            if (contentEncoding != null && contentEncoding.equals("gzip")) {
                inputStreamReader = new InputStreamReader(new GZIPInputStream(inputStream));
            }
            else {
                inputStreamReader = new InputStreamReader(inputStream);
            }
            if (!this.allege(inputStreamReader != null, "Cannot create stream reader")) {
                return;
            }
            final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.add(newdata, line.trim());
            }
            bufferedReader.close();
            this.status("Received " + newdata.size() + " updates.");
        }
        catch (IOException ex3) {}
        catch (Exception ex2) {
            this.log("Unexpected exception", ex2);
        }
        newdata.trimToSize();
        this.newdata = newdata;
    }
    
    protected void resize() {
        this.appWidth = 0;
        this.appHeight = 0;
        this.dataHeight = 0;
        if (this.data != null) {
            final Enumeration<TickerData> elements = this.data.elements();
            while (elements.hasMoreElements()) {
                final TickerData tickerData = elements.nextElement();
                this.appWidth += tickerData.redraw(this.appGraphics, this.appWidth, 0, this.horizontal);
                if (this.dataHeight == 0) {
                    this.dataHeight = tickerData.redraw(this.appGraphics, 0, this.appHeight, this.horizontal);
                }
                this.appHeight += this.dataHeight;
            }
        }
    }
    
    protected void redraw(final boolean b, int margin, final int n, int margin2, final int n2) {
        this.appGraphics.setColor(this.bgColor);
        this.appGraphics.fillRect(0, 0, this.width, this.height);
        if (this.bgImage != null) {
            this.appGraphics.drawImage(this.bgImage, 0, 0, this.width, this.height, this.bgColor, this.app);
        }
        final Enumeration<TickerData> elements = this.data.elements();
        while (elements.hasMoreElements()) {
            final TickerData tickerData = elements.nextElement();
            if (b) {
                margin2 = this.margin;
                tickerData.currentPositionY = this.margin;
                tickerData.currentPositionX = margin;
                if (margin + tickerData.width >= 0 && margin - (tickerData.width - this.space) <= n && tickerData.height > 0) {
                    margin += tickerData.redraw(this.appGraphics, margin, margin2, b);
                }
                else {
                    if (margin > n) {
                        break;
                    }
                    margin += tickerData.width;
                }
            }
            else {
                margin = this.margin;
                tickerData.currentPositionX = this.margin;
                tickerData.currentPositionY = margin2;
                if (margin2 + tickerData.height >= 0 && margin2 - (tickerData.height - this.space) <= n2 && tickerData.height > 0) {
                    margin2 += tickerData.redraw(this.appGraphics, margin, margin2, b);
                }
                else {
                    if (margin2 > n2) {
                        break;
                    }
                    margin2 += tickerData.height;
                }
            }
        }
    }
}
