import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class FadingText1ech extends Applet implements Runnable
{
    AppletUtil3 aut;
    boolean inapplet;
    Color bgcolor;
    Color fgcolor;
    Color[] colors;
    Font f;
    FontMetrics fm;
    Graphics bufferG;
    Image bufferI;
    int index;
    int cycles;
    int step;
    int on_pause;
    int off_pause;
    int fade_steps;
    int fade_speed;
    int width;
    int height;
    int br;
    int bg;
    int bb;
    String[] texts;
    String delim;
    String linebreak;
    String align;
    String valign;
    String[] targets;
    Thread woohoo;
    URL[] urls;
    
    public void init() {
        this.aut = new AppletUtil3(this);
        this.width = this.size().width;
        this.height = this.size().height;
        this.bgcolor = this.aut.makeColor(this.getParameter("BGCOLOR"), Color.white);
        this.br = this.bgcolor.getRed();
        this.bg = this.bgcolor.getGreen();
        this.bb = this.bgcolor.getBlue();
        this.fgcolor = this.aut.makeColor(this.getParameter("FGCOLOR"), Color.black);
        this.f = this.aut.getFont();
        if (this.bufferG == null) {
            this.bufferI = this.createImage(this.width, this.height);
            (this.bufferG = this.bufferI.getGraphics()).setFont(this.f);
            this.fm = this.bufferG.getFontMetrics();
        }
        final String parameter;
        if ((parameter = this.getParameter("cycles")) != null) {
            this.cycles = Integer.parseInt(parameter);
            if (this.cycles > 0) {
                this.step = 1;
            }
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("ON.PAUSE")) != null) {
            this.on_pause = Integer.parseInt(parameter2);
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("OFF.PAUSE")) != null) {
            this.off_pause = Integer.parseInt(parameter3);
        }
        final String parameter4;
        if ((parameter4 = this.getParameter("FADE.STEPS")) != null) {
            this.fade_steps = Integer.parseInt(parameter4);
        }
        final String parameter5;
        if ((parameter5 = this.getParameter("FADE.SPEED")) != null) {
            this.fade_speed = Integer.parseInt(parameter5);
        }
        final String parameter6;
        if ((parameter6 = this.getParameter("DELIMITER")) != null) {
            this.delim = parameter6;
        }
        final String parameter7;
        if ((parameter7 = this.getParameter("LINEBREAK")) != null) {
            this.linebreak = parameter7;
        }
        final String parameter8;
        if ((parameter8 = this.getParameter("ALIGN")) != null) {
            this.align = parameter8.toLowerCase();
        }
        final String parameter9;
        if ((parameter9 = this.getParameter("VALIGN")) != null) {
            this.valign = parameter9.toLowerCase();
        }
        int n;
        for (n = 0; this.getParameter("TEXT." + n) != null; ++n) {}
        this.texts = new String[n];
        this.colors = new Color[n];
        this.urls = new URL[n];
        this.targets = new String[n];
        for (int i = 0; i < n; ++i) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("TEXT." + i), this.delim);
            this.texts[i] = stringTokenizer.nextToken().trim();
            this.colors[i] = (stringTokenizer.hasMoreTokens() ? this.aut.makeColor(stringTokenizer.nextToken().trim(), this.fgcolor) : this.fgcolor);
            if (stringTokenizer.hasMoreTokens()) {
                try {
                    this.urls[i] = new URL(this.getDocumentBase(), stringTokenizer.nextToken().trim());
                }
                catch (Exception ex) {}
            }
            else {
                this.urls[i] = null;
            }
            this.targets[i] = (stringTokenizer.hasMoreTokens() ? stringTokenizer.nextToken().trim() : "_self");
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
                this.fadeText(true);
                try {
                    Thread.sleep(this.on_pause);
                }
                catch (Exception ex) {}
                this.fadeText(false);
                try {
                    Thread.sleep(this.off_pause);
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    public void fadeText(final boolean b) {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.texts[this.index], this.linebreak);
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        final int red = this.colors[this.index].getRed();
        final int green = this.colors[this.index].getGreen();
        final int blue = this.colors[this.index].getBlue();
        for (int j = 0; j < this.fade_steps; ++j) {
            final int n = b ? (j + 1) : (this.fade_steps - j);
            this.bufferG.setColor(this.bgcolor);
            this.bufferG.fillRect(0, 0, this.width, this.height);
            this.bufferG.setColor(new Color(this.br + (red - this.br) * n / this.fade_steps, this.bg + (green - this.bg) * n / this.fade_steps, this.bb + (blue - this.bb) * n / this.fade_steps));
            final int n2 = this.valign.startsWith("t") ? (this.fm.getHeight() - this.fm.getMaxDescent()) : (this.valign.startsWith("b") ? (this.height - (array.length - 1) * this.fm.getHeight() - this.fm.getMaxDescent()) : ((this.height - array.length * this.fm.getHeight() + this.fm.getMaxAscent() + this.fm.getMaxDescent()) / 2));
            for (int k = 0; k < array.length; ++k) {
                this.bufferG.drawString(array[k], this.align.startsWith("l") ? 0 : (this.align.startsWith("r") ? (this.width - this.fm.stringWidth(array[k])) : ((this.width - this.fm.stringWidth(array[k])) / 2)), n2 + k * this.fm.getHeight());
            }
            this.repaint();
            try {
                Thread.sleep(this.fade_speed);
            }
            catch (Exception ex) {}
        }
        if (!b) {
            this.bufferG.setColor(this.bgcolor);
            this.bufferG.fillRect(0, 0, this.width, this.height);
            this.repaint();
        }
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
        if (this.getParameter("AUTHOR").equals("Eric Harshbarger, http://www.ericharshbarger.org") && this.getParameter("COPYRIGHT").equals("FadingText applet, Copyright 1999, Eric Harshbarger")) {
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
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.inapplet = false;
        this.showStatus("");
        return true;
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
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            if (this.urls[this.index] != null) {
                this.getAppletContext().showDocument(this.urls[this.index], this.targets[this.index]);
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    public FadingText1ech() {
        this.inapplet = false;
        this.on_pause = 2000;
        this.off_pause = 2000;
        this.fade_steps = 10;
        this.fade_speed = 100;
        this.delim = "|";
        this.linebreak = "\\";
        this.align = "c";
        this.valign = "c";
    }
}
