import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tinyScroller extends Applet implements Runnable
{
    int maxLines;
    int direction;
    int delay;
    int spacing;
    int XPos;
    int maxLine;
    int current;
    int height;
    String[] Line;
    Image offImage;
    Image bg;
    Graphics offGrfx;
    Font outFont;
    boolean customFont;
    Color background;
    Color fontColor;
    Thread runner;
    
    public void init() {
        final String parameter = this.getParameter("BGRED");
        final String parameter2 = this.getParameter("BGGREEN");
        final String parameter3 = this.getParameter("BGBLUE");
        final String parameter4 = this.getParameter("FGRED");
        final String parameter5 = this.getParameter("FGGREEN");
        final String parameter6 = this.getParameter("FGBLUE");
        final String parameter7 = this.getParameter("SPACING");
        final String parameter8 = this.getParameter("DELAY");
        final String parameter9 = this.getParameter("XPOS");
        this.getParameter("MAXLINE");
        final String parameter10 = this.getParameter("BACKGROUND");
        final String parameter11 = this.getParameter("FONTNAME");
        final String parameter12 = this.getParameter("FONTSIZE");
        final String parameter13 = this.getParameter("DIRECTION");
        for (int i = 0; i < this.maxLines; ++i) {
            this.Line[i] = this.getParameter("LINE" + Integer.toString(i + 1));
        }
        if (parameter12 != null && parameter11 != null) {
            this.outFont = new Font(parameter11, 0, Integer.parseInt(parameter12));
            this.customFont = true;
        }
        for (int n = 0; n < this.maxLines && this.Line[n] != null; ++n) {
            ++this.maxLine;
        }
        int int1 = 255;
        if (parameter != null) {
            int1 = Integer.parseInt(parameter);
        }
        int int2 = 255;
        if (parameter2 != null) {
            int2 = Integer.parseInt(parameter2);
        }
        int int3 = 255;
        if (parameter3 != null) {
            int3 = Integer.parseInt(parameter3);
        }
        int int4 = 0;
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
        if (parameter7 != null) {
            this.spacing = Integer.parseInt(parameter7);
        }
        if (parameter8 != null) {
            this.delay = Integer.parseInt(parameter8);
        }
        if (parameter9 != null) {
            this.XPos = Integer.parseInt(parameter9);
        }
        this.height = this.size().height;
        if (parameter13 != null) {
            this.direction = Integer.parseInt(parameter13);
        }
        if (parameter10 != null) {
            this.bg = this.getImage(this.getDocumentBase(), parameter10);
        }
        if (this.direction == 0) {
            this.current = this.height;
        }
        else {
            this.current = -(this.maxLine * this.spacing);
        }
        this.fontColor = new Color(int4, int5, int6);
        this.setBackground(this.background = new Color(int1, int2, int3));
        this.offImage = this.createImage(this.size().width, this.size().height);
        this.offGrfx = this.offImage.getGraphics();
    }
    
    public void paint(final Graphics graphics) {
        this.offGrfx.setColor(this.background);
        this.offGrfx.fillRect(0, 0, this.size().width, this.size().height);
        if (this.bg != null) {
            this.offGrfx.drawImage(this.bg, 0, this.current - this.height, null);
        }
        this.offGrfx.setColor(this.fontColor);
        try {
            this.offGrfx.setFont(this.outFont);
        }
        catch (NullPointerException ex) {}
        if (this.direction == 0) {
            for (int i = 0; i < this.maxLine; ++i) {
                this.offGrfx.drawString(this.Line[i], this.XPos, this.current + i * this.spacing);
            }
        }
        else {
            for (int j = 0; j < this.maxLine; ++j) {
                this.offGrfx.drawString(this.Line[j], this.XPos, this.current + (this.maxLine - j) * this.spacing);
            }
        }
        graphics.drawImage(this.offImage, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            if (this.direction == 0) {
                --this.current;
                if (this.current + this.maxLine * this.spacing < 0) {
                    this.current = this.height + this.spacing;
                }
            }
            else {
                ++this.current;
                if (this.current > this.height) {
                    this.current = -(this.maxLine * this.spacing);
                }
            }
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public tinyScroller() {
        this.maxLines = 100;
        this.delay = 100;
        this.spacing = 12;
        this.XPos = 5;
        this.Line = new String[this.maxLines];
        this.customFont = false;
    }
}
