import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ShrinkingText1ech extends Applet implements Runnable
{
    AppletUtil3 aut;
    boolean inapplet;
    Color bgcolor;
    Color fgcolor;
    Color[] colors;
    Font[] fonts;
    Graphics bufferG;
    Image bufferI;
    Image background;
    int cycles;
    int index;
    int step;
    int width;
    int height;
    int shrink_speed;
    int pause;
    String delim;
    String linebreak;
    String[] texts;
    String[] targets;
    Thread woohoo;
    URL[] urls;
    
    public void init() {
        this.aut = new AppletUtil3(this);
        this.width = this.size().width;
        this.height = this.size().height;
        if (this.bufferG == null) {
            this.bufferI = this.createImage(this.width, this.height);
            this.bufferG = this.bufferI.getGraphics();
        }
        this.bgcolor = this.aut.makeColor(this.getParameter("BGCOLOR"), Color.white);
        this.fgcolor = this.aut.makeColor(this.getParameter("FGCOLOR"), Color.black);
        final String parameter;
        if ((parameter = this.getParameter("CYCLES")) != null) {
            this.cycles = Integer.parseInt(parameter);
            if (this.cycles > 0) {
                this.step = 1;
            }
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("DELIMITER")) != null) {
            this.delim = parameter2;
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("LINEBREAK")) != null) {
            this.linebreak = parameter3;
        }
        final String parameter4;
        if ((parameter4 = this.getParameter("BACKGROUND")) != null) {
            this.background = this.aut.getImage(parameter4);
        }
        int int1 = 12;
        final String parameter5;
        if ((parameter5 = this.getParameter("FONTSIZE")) != null) {
            int1 = Integer.parseInt(parameter5);
        }
        if (int1 % 2 == 1) {
            ++int1;
        }
        this.fonts = new Font[int1 / 2];
        for (int i = 0; i < int1 / 2; ++i) {
            this.fonts[i] = this.aut.makeFont(this.getParameter("FONTNAME"), this.getParameter("FONTSTYLE"), String.valueOf((i + 1) * 2));
        }
        final String parameter6;
        if ((parameter6 = this.getParameter("PAUSE")) != null) {
            this.pause = Integer.parseInt(parameter6);
        }
        final String parameter7;
        if ((parameter7 = this.getParameter("SMRINK.SPEED")) != null) {
            this.shrink_speed = Integer.parseInt(parameter7);
        }
        int n;
        for (n = 0; this.getParameter("TEXT." + n) != null; ++n) {}
        this.texts = new String[n];
        this.targets = new String[n];
        this.urls = new URL[n];
        this.colors = new Color[n];
        for (int j = 0; j < n; ++j) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("TEXT." + j), this.delim);
            this.texts[j] = stringTokenizer.nextToken().trim();
            this.colors[j] = (stringTokenizer.hasMoreTokens() ? this.aut.makeColor(stringTokenizer.nextToken().trim(), this.fgcolor) : this.fgcolor);
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    this.urls[j] = new URL(this.getDocumentBase(), stringTokenizer.nextToken().trim());
                }
                catch (Exception ex) {}
            }
            else {
                this.urls[j] = null;
            }
            this.targets[j] = (stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken().trim() : "_self");
        }
    }
    
    public void run() {
        for (int n = 0; n < this.cycles || this.step == 0; ++n) {
            for (int i = 0; i < this.texts.length; ++i) {
                this.index = i;
                if (this.inapplet && this.urls[this.index] != null) {
                    this.showStatus(this.urls[this.index].toString());
                }
                else {
                    this.showStatus("");
                }
                for (int j = 0; j < this.fonts.length; ++j) {
                    this.drawText(j);
                }
                try {
                    Thread.sleep(this.pause);
                }
                catch (Exception ex) {}
                for (int k = 0; k < this.fonts.length; ++k) {
                    this.drawText(this.fonts.length - k - 1);
                }
            }
        }
        this.bufferG.setColor(this.bgcolor);
        this.bufferG.fillRect(0, 0, this.width, this.height);
        if (this.background != null) {
            this.bufferG.drawImage(this.background, 0, 0, this);
        }
        this.repaint();
    }
    
    public void drawText(final int n) {
        this.bufferG.setColor(this.bgcolor);
        this.bufferG.fillRect(0, 0, this.width, this.height);
        if (this.background != null) {
            this.bufferG.drawImage(this.background, 0, 0, this);
        }
        this.bufferG.setFont(this.fonts[n]);
        final FontMetrics fontMetrics = this.bufferG.getFontMetrics();
        this.bufferG.setColor(this.colors[this.index]);
        final StringTokenizer stringTokenizer = new StringTokenizer(this.texts[this.index], this.linebreak);
        final String[] array = new String[stringTokenizer.countTokens()];
        final int n2 = (this.height - fontMetrics.getHeight() * array.length) / 2 - fontMetrics.getMaxDescent();
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
            this.bufferG.drawString(array[i], (this.width - fontMetrics.stringWidth(array[i])) / 2, n2 + (i + 1) * fontMetrics.getHeight());
        }
        this.repaint();
        try {
            Thread.sleep(this.shrink_speed);
        }
        catch (Exception ex) {}
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.bufferI, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.bufferI, 0, 0, this);
    }
    
    public void start() {
        if (this.woohoo == null) {
            this.woohoo = new Thread(this);
        }
        if (this.getParameter("AUTHOR").equals("Eric Harshbarger, http://www.ericharshbarger.org") && this.getParameter("COPYRIGHT").equals("ShrinkingText applet, Copyright 1999, Eric Harshbarger")) {
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
        if (this.urls[this.index] != null) {
            this.showStatus(this.urls[this.index].toString());
        }
        else {
            this.showStatus("");
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.inapplet = false;
        this.showStatus("");
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.urls[this.index] != null) {
            try {
                this.getAppletContext().showDocument(this.urls[this.index], this.targets[this.index]);
            }
            catch (Exception ex) {}
        }
        return true;
    }
    
    public ShrinkingText1ech() {
        this.inapplet = false;
        this.shrink_speed = 100;
        this.pause = 2000;
        this.delim = "|";
        this.linebreak = "\\";
    }
}
