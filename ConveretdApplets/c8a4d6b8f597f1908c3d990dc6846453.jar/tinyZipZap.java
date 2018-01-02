import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tinyZipZap extends Applet implements Runnable
{
    int delay;
    int pause;
    int ypos_in;
    int length;
    int[] xpos;
    int[] ypos;
    int[] displayX;
    int[] displayY;
    Image offImage;
    Graphics offGrfx;
    Color background;
    Color fontColor;
    Thread runner;
    String Message;
    String[] letter;
    Font outFont;
    URL Link;
    
    public void init() {
        final String parameter = this.getParameter("YPOS");
        final String parameter2 = this.getParameter("BGRED");
        final String parameter3 = this.getParameter("BGGREEN");
        final String parameter4 = this.getParameter("BGBLUE");
        final String parameter5 = this.getParameter("FGRED");
        final String parameter6 = this.getParameter("FGGREEN");
        final String parameter7 = this.getParameter("FGBLUE");
        final String parameter8 = this.getParameter("DELAY");
        final String parameter9 = this.getParameter("PAUSE");
        final String parameter10 = this.getParameter("FONTNAME");
        final String parameter11 = this.getParameter("FONTSIZE");
        final String parameter12 = this.getParameter("FONTITALIC");
        final String parameter13 = this.getParameter("FONTBOLD");
        this.Message = this.getParameter("MESSAGE");
        this.Link = this.getURL(this.getParameter("LINK"));
        if (parameter8 != null) {
            this.delay = Integer.parseInt(parameter8);
        }
        if (parameter9 != null) {
            this.pause = Integer.parseInt(parameter9);
        }
        if (parameter != null) {
            this.ypos_in = Integer.parseInt(parameter);
        }
        this.outFont = new Font(this.getToolkit().getFontList()[0], 0, 16);
        if (parameter11 != null && parameter10 != null) {
            final int int1 = Integer.parseInt(parameter11);
            int n = 0;
            if (parameter12 != null && parameter12 != "0") {
                n = 2;
            }
            if (parameter13 != null && parameter13 != "0") {
                ++n;
            }
            this.outFont = new Font(parameter10, n, int1);
        }
        this.length = this.Message.length();
        int int2 = 255;
        if (parameter2 != null) {
            int2 = Integer.parseInt(parameter2);
        }
        int int3 = 255;
        if (parameter3 != null) {
            int3 = Integer.parseInt(parameter3);
        }
        int int4 = 255;
        if (parameter4 != null) {
            int4 = Integer.parseInt(parameter4);
        }
        int int5 = 0;
        if (parameter5 != null) {
            int5 = Integer.parseInt(parameter5);
        }
        int int6 = 0;
        if (parameter6 != null) {
            int6 = Integer.parseInt(parameter6);
        }
        int int7 = 0;
        if (parameter7 != null) {
            int7 = Integer.parseInt(parameter7);
        }
        this.fontColor = new Color(int5, int6, int7);
        this.setBackground(this.background = new Color(int2, int3, int4));
        this.offImage = this.createImage(this.size().width, this.size().height);
        this.offGrfx = this.offImage.getGraphics();
    }
    
    URL getURL(final String s) {
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        return url;
    }
    
    public void init_string() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.outFont);
        int n = (this.size().width - fontMetrics.stringWidth(this.Message)) / 2;
        final char[] charArray = this.Message.toCharArray();
        for (int i = 0; i < this.length; ++i) {
            this.letter[i] = new String(charArray, i, 1);
            this.xpos[i] = n;
            n += fontMetrics.stringWidth(this.letter[i]);
            if (this.ypos_in == 0) {
                this.ypos[i] = (this.size().height - fontMetrics.getAscent()) / 2;
                this.displayY[i] = this.ypos[i];
            }
            else {
                this.ypos[i] = this.ypos_in;
                this.displayY[i] = this.ypos[i];
            }
        }
        for (int j = 0; j < this.length; ++j) {
            this.displayX[j] = -20;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.offGrfx.setColor(this.background);
        this.offGrfx.fillRect(0, 0, this.size().width, this.size().height);
        this.offGrfx.setColor(this.fontColor);
        try {
            this.offGrfx.setFont(this.outFont);
        }
        catch (NullPointerException ex) {}
        for (int i = 0; i < this.length; ++i) {
            this.offGrfx.drawString(this.letter[i], this.displayX[i], this.displayY[i]);
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        this.init_string();
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (true) {
            for (int i = this.length; i > -1; --i) {
                while (this.displayX[i] != this.xpos[i]) {
                    final int[] displayX = this.displayX;
                    final int n = i;
                    ++displayX[n];
                    this.repaint();
                    try {
                        Thread.sleep(this.delay);
                    }
                    catch (InterruptedException ex) {}
                }
            }
            try {
                Thread.sleep(this.pause);
            }
            catch (InterruptedException ex2) {}
            for (int j = 0; j < this.size().height; ++j) {
                for (int k = 0; k < this.length; k += 2) {
                    final int[] displayY = this.displayY;
                    final int n2 = k;
                    --displayY[n2];
                }
                for (int l = 1; l < this.length; l += 2) {
                    final int[] displayY2 = this.displayY;
                    final int n3 = l;
                    ++displayY2[n3];
                }
                this.repaint();
                try {
                    Thread.sleep(this.delay * 2);
                }
                catch (InterruptedException ex3) {}
            }
            try {
                Thread.sleep(this.pause);
            }
            catch (InterruptedException ex4) {}
            this.init_string();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.Link != null) {
            this.runner.stop();
            this.getAppletContext().showDocument(this.Link);
        }
        return true;
    }
    
    public tinyZipZap() {
        this.delay = 5;
        this.pause = 2000;
        this.xpos = new int[50];
        this.ypos = new int[50];
        this.displayX = new int[50];
        this.displayY = new int[50];
        this.Message = new String("");
        this.letter = new String[50];
    }
}
