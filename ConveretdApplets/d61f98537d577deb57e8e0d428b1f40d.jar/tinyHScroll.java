import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tinyHScroll extends Applet implements Runnable
{
    int delay;
    int xpos;
    int ypos;
    int length;
    int direction;
    Image offImage;
    Image bg;
    Graphics offGrfx;
    Color background;
    Color fontColor;
    boolean customFont;
    Thread runner;
    String outString;
    String Message;
    Font outFont;
    
    public void init() {
        final String parameter = this.getParameter("BGRED");
        final String parameter2 = this.getParameter("BGGREEN");
        final String parameter3 = this.getParameter("BGBLUE");
        final String parameter4 = this.getParameter("FGRED");
        final String parameter5 = this.getParameter("FGGREEN");
        final String parameter6 = this.getParameter("FGBLUE");
        final String parameter7 = this.getParameter("BACKGROUND");
        final String parameter8 = this.getParameter("DELAY");
        final String parameter9 = this.getParameter("FONTNAME");
        final String parameter10 = this.getParameter("FONTSIZE");
        final String parameter11 = this.getParameter("YPOS");
        final String parameter12 = this.getParameter("DIRECTION");
        this.Message = this.getParameter("MESSAGE");
        if (parameter11 != null) {
            this.ypos = Integer.parseInt(parameter11);
        }
        if (parameter12 != null) {
            this.direction = Integer.parseInt(parameter12);
        }
        if (parameter8 != null) {
            this.delay = Integer.parseInt(parameter8);
        }
        if (parameter10 != null && parameter9 != null) {
            this.outFont = new Font(parameter9, 0, Integer.parseInt(parameter10));
            this.customFont = true;
        }
        this.length = this.Message.length();
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
            this.bg = this.getImage(this.getDocumentBase(), parameter7);
        }
        this.fontColor = new Color(int4, int5, int6);
        this.setBackground(this.background = new Color(int1, int2, int3));
        this.offImage = this.createImage(this.size().width, this.size().height);
        this.offGrfx = this.offImage.getGraphics();
        this.xpos = this.size().width + 10;
    }
    
    public void paint(final Graphics graphics) {
        this.offGrfx.setColor(this.background);
        this.offGrfx.fillRect(0, 0, this.size().width, this.size().height);
        if (this.bg != null) {
            this.offGrfx.drawImage(this.bg, 0, 0, null);
        }
        this.offGrfx.setColor(this.fontColor);
        try {
            this.offGrfx.setFont(this.outFont);
        }
        catch (NullPointerException ex) {}
        this.offGrfx.drawString(this.Message, this.xpos, this.ypos);
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
        if (this.customFont) {
            this.length = this.getFontMetrics(this.outFont).stringWidth(this.Message);
        }
        else {
            this.length *= 7;
        }
        if (this.ypos == 0) {
            this.ypos = (int)(0.5 * this.size().height);
        }
        while (true) {
            this.repaint();
            if (this.direction == 0) {
                --this.xpos;
                if (this.xpos <= -this.length) {
                    this.xpos = this.size().width + 10;
                }
            }
            else {
                ++this.xpos;
                if (this.xpos >= this.size().width) {
                    this.xpos = -this.length;
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
    
    public tinyHScroll() {
        this.delay = 50;
        this.customFont = false;
        this.outString = new String("");
        this.Message = new String("");
    }
}
