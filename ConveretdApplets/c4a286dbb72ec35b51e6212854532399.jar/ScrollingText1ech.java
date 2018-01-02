import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.net.URL;
import java.util.Vector;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ScrollingText1ech extends Applet implements Runnable
{
    AppletUtil3 aut;
    boolean inapplet;
    boolean loop;
    Color bgcolor;
    Color fgcolor;
    Font f;
    FontMetrics fm1;
    Graphics bufferG;
    Image bufferI;
    Image background;
    int w;
    int h;
    int wordcount;
    int depth;
    int cycles;
    int step;
    int pause;
    int totalwords;
    int scroll_speed;
    int linecount;
    int scroll_step;
    int left_margin;
    int right_margin;
    int message_index;
    String align;
    String[] words;
    String param;
    String target;
    String linebreak;
    Thread woohoo;
    Vector lines;
    Vector messages;
    Vector urls;
    
    public void init() {
        this.aut = new AppletUtil3(this);
        this.w = this.size().width;
        this.h = this.size().height;
        this.f = this.aut.getFont();
        this.bufferI = this.createImage(this.w, this.h);
        (this.bufferG = this.bufferI.getGraphics()).setFont(this.f);
        this.fm1 = this.bufferG.getFontMetrics();
        this.background = this.aut.getImage(this.getParameter("BACKGROUND"));
        this.bgcolor = this.aut.makeColor(this.getParameter("BGCOLOR"), Color.lightGray);
        this.fgcolor = this.aut.makeColor(this.getParameter("FGCOLOR"), Color.black);
        if (this.getParameter("LINEBREAK") != null) {
            this.linebreak = this.getParameter("LINEBREAK").trim();
        }
        this.param = this.getParameter("CYCLES");
        if (this.param == null || this.param.equalsIgnoreCase("infinite")) {
            this.cycles = 1;
            this.step = 0;
        }
        else {
            this.cycles = this.aut.getRandom(this.param);
            this.step = 1;
        }
        this.param = this.getParameter("PAUSE");
        this.pause = ((this.param != null) ? Integer.parseInt(this.param) : 2000);
        this.param = this.getParameter("SCROLL.SPEED");
        this.scroll_speed = ((this.param != null) ? Math.max(10, Integer.parseInt(this.param)) : 100);
        final String parameter = this.getParameter("SCROLL.STEP");
        this.param = parameter;
        if (parameter != null) {
            this.scroll_step = Integer.parseInt(this.param);
        }
        if ((this.param = this.getParameter("MARGIN.LEFT")) != null) {
            this.left_margin = Integer.parseInt(this.param);
        }
        if ((this.param = this.getParameter("MARGIN.RIGHT")) != null) {
            this.right_margin = Integer.parseInt(this.param);
        }
        else {
            this.right_margin = this.left_margin;
        }
        this.param = this.getParameter("TARGET");
        this.target = ((this.param != null) ? this.param : "_self");
        final String parameter2 = this.getParameter("ALIGN");
        this.param = parameter2;
        if (parameter2 != null) {
            this.align = this.param.toLowerCase();
        }
        for (int n = 0; this.getParameter("TEXT." + n) != null; ++n) {
            this.param = this.getParameter("TEXT." + n);
            final String s = (this.param.lastIndexOf(",") != -1) ? this.param.substring(this.param.lastIndexOf(",") + 1).trim() : null;
            this.messages.addElement((this.param.lastIndexOf(",") != -1) ? this.param.substring(0, this.param.lastIndexOf(",")).trim() : this.param);
            Object o = null;
            try {
                o = ((s != null && !s.equalsIgnoreCase("null")) ? new URL(this.getDocumentBase(), s) : null);
            }
            catch (Exception ex) {}
            this.urls.addElement(o);
        }
        this.param = this.getParameter("TEXTFILE");
        if (this.param != null) {
            try {
                final URLConnection openConnection = new URL(this.getDocumentBase(), this.param).openConnection();
                openConnection.setUseCaches(false);
                final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
                final boolean b = true;
                while (b) {
                    final String line = dataInputStream.readLine();
                    if (line == null || line.trim().equals("")) {
                        break;
                    }
                    final String s2 = (line.lastIndexOf(",") != -1) ? line.substring(line.lastIndexOf(",") + 1).trim() : null;
                    this.messages.addElement((line.lastIndexOf(",") != -1) ? line.substring(0, line.lastIndexOf(",")).trim() : line);
                    Object o2 = null;
                    try {
                        o2 = ((s2 != null && !s2.equalsIgnoreCase("null")) ? new URL(this.getDocumentBase(), s2) : null);
                    }
                    catch (Exception ex2) {}
                    this.urls.addElement(o2);
                }
                dataInputStream.close();
            }
            catch (Exception ex3) {}
        }
    }
    
    public void run() {
        this.buildLines(this.messages.elementAt(this.message_index));
        this.message_index = 0;
        for (int i = 0; i < this.cycles; i += this.step) {
            for (int j = 0; j < this.depth / this.scroll_step + 1; ++j) {
                this.bufferG.setColor(this.bgcolor);
                this.bufferG.fillRect(0, 0, this.w, this.h);
                this.bufferG.setColor(this.fgcolor);
                if (this.background != null) {
                    this.bufferG.drawImage(this.background, 0, 0, this);
                }
                for (int k = 0; k < this.linecount; ++k) {
                    final String s = this.lines.elementAt(k);
                    this.bufferG.drawString(s, this.align.startsWith("l") ? this.left_margin : (this.align.startsWith("r") ? (this.w - this.fm1.stringWidth(s) - this.right_margin) : ((this.w - this.fm1.stringWidth(s)) / 2)), this.h - j * this.scroll_step + (k + 1) * this.fm1.getHeight());
                }
                this.repaint();
                try {
                    Thread.sleep(this.scroll_speed);
                }
                catch (InterruptedException ex) {}
            }
            try {
                Thread.sleep(this.pause);
            }
            catch (InterruptedException ex2) {}
            this.message_index = (this.message_index + 1) % this.messages.size();
            this.buildLines(this.messages.elementAt(this.message_index));
            if (this.inapplet) {
                final URL url = this.urls.elementAt(this.message_index);
                if (url != null) {
                    this.showStatus(url.toString());
                }
                else {
                    this.showStatus("");
                }
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.bufferI, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        if (this.woohoo == null) {
            this.woohoo = new Thread(this);
        }
        if (this.getParameter("AUTHOR").equals("Eric Harshbarger, http://www.ericharshbarger.org") && this.getParameter("COPYRIGHT").equals("ScrollingText applet, Copyright 1999, Eric Harshbarger")) {
            this.woohoo.start();
            return;
        }
        System.out.println("AUTHOR & COPYRIGHT parameters are incorrect.");
    }
    
    public void stop() {
        if (this.woohoo != null) {
            this.woohoo.stop();
            this.woohoo = null;
        }
    }
    
    public void destroy() {
        if (this.bufferG != null) {
            this.bufferG.dispose();
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.inapplet = true;
        final URL url = this.urls.elementAt(this.message_index);
        if (url != null) {
            this.showStatus(url.toString());
        }
        else {
            this.showStatus("");
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.inapplet = false;
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final URL url = this.urls.elementAt(this.message_index);
        try {
            if (url != null) {
                this.getAppletContext().showDocument(url, this.target);
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    public void buildLines(final String s) {
        this.lines = new Vector();
        final Graphics graphics = this.createImage(this.w, this.h).getGraphics();
        graphics.setFont(this.f);
        final StringTokenizer stringTokenizer = new StringTokenizer(s, this.linebreak);
        while (stringTokenizer.hasMoreTokens()) {
            String string = "";
            final StringTokenizer stringTokenizer2 = new StringTokenizer(stringTokenizer.nextToken());
            this.wordcount = stringTokenizer2.countTokens();
            this.totalwords += this.wordcount;
            this.words = new String[this.wordcount];
            for (int i = 0; i < this.wordcount; ++i) {
                this.words[i] = stringTokenizer2.nextToken();
            }
            for (int j = 0; j < this.wordcount; ++j) {
                string = ((string == null) ? this.words[0] : (string + this.words[j] + " "));
                if (this.fm1.stringWidth(string) > this.w - this.left_margin - this.right_margin) {
                    final int lastIndex = string.trim().lastIndexOf(" ");
                    if (lastIndex == -1) {
                        this.lines.addElement(string.trim());
                        string = "";
                    }
                    else {
                        this.lines.addElement(string.substring(0, lastIndex));
                        string = this.words[j] + " ";
                    }
                }
            }
            this.lines.addElement(string);
            this.linecount = this.lines.size();
        }
        this.depth = this.linecount * this.fm1.getHeight() + this.h + this.fm1.getMaxDescent();
        graphics.dispose();
    }
    
    public ScrollingText1ech() {
        this.inapplet = false;
        this.loop = true;
        this.scroll_step = 2;
        this.align = "center";
        this.linebreak = "\\";
        this.lines = new Vector(5);
        this.messages = new Vector(5);
        this.urls = new Vector(5);
    }
}
