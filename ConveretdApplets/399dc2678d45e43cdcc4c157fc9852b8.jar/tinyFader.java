import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tinyFader extends Applet implements Runnable
{
    int spacing;
    int delay;
    int step;
    int XPos;
    int YPos;
    int current;
    int direction;
    int color;
    String[] Line;
    Font outFont;
    boolean customFont;
    Graphics offGrfx;
    Image offImage;
    Color background;
    Thread runner;
    
    public void init() {
        final String parameter = this.getParameter("BGRED");
        final String parameter2 = this.getParameter("BGGREEN");
        final String parameter3 = this.getParameter("BGBLUE");
        final String parameter4 = this.getParameter("SPACING");
        final String parameter5 = this.getParameter("DELAY");
        final String parameter6 = this.getParameter("XPOS");
        final String parameter7 = this.getParameter("YPOS");
        final String parameter8 = this.getParameter("FONTNAME");
        final String parameter9 = this.getParameter("FONTSIZE");
        this.Line[0] = this.getParameter("LINE1");
        this.Line[1] = this.getParameter("LINE2");
        this.Line[2] = this.getParameter("LINE3");
        this.Line[3] = this.getParameter("LINE4");
        this.Line[4] = this.getParameter("LINE5");
        this.Line[5] = this.getParameter("LINE6");
        this.Line[6] = this.getParameter("LINE7");
        this.Line[7] = this.getParameter("LINE8");
        this.Line[8] = this.getParameter("LINE9");
        this.Line[9] = this.getParameter("LINE10");
        this.Line[10] = this.getParameter("LINE11");
        this.Line[11] = this.getParameter("LINE12");
        this.Line[12] = this.getParameter("LINE13");
        this.Line[13] = this.getParameter("LINE14");
        this.Line[14] = this.getParameter("LINE15");
        this.Line[15] = this.getParameter("LINE16");
        this.Line[16] = this.getParameter("LINE17");
        this.Line[17] = this.getParameter("LINE18");
        this.Line[18] = this.getParameter("LINE19");
        this.Line[19] = this.getParameter("LINE20");
        this.Line[20] = this.getParameter("LINE21");
        this.Line[21] = this.getParameter("LINE22");
        this.Line[22] = this.getParameter("LINE23");
        this.Line[23] = this.getParameter("LINE24");
        if (parameter9 != null && parameter8 != null) {
            this.outFont = new Font(parameter8, 0, Integer.parseInt(parameter9));
            this.customFont = true;
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
        if (parameter4 != null) {
            this.spacing = Integer.parseInt(parameter4);
        }
        if (parameter5 != null) {
            this.delay = Integer.parseInt(parameter5);
        }
        if (parameter6 != null) {
            this.XPos = Integer.parseInt(parameter6);
        }
        if (parameter7 != null) {
            this.YPos = Integer.parseInt(parameter7);
        }
        this.setBackground(this.background = new Color(int1, int2, int3));
        this.offImage = this.createImage(this.size().width, this.size().height);
        this.offGrfx = this.offImage.getGraphics();
    }
    
    public void paint(final Graphics graphics) {
        final Color color = new Color(this.color, this.color, this.color);
        if (this.current == 1) {
            this.offGrfx.setColor(this.background);
            this.offGrfx.fillRect(0, 0, this.size().width, this.size().height);
            this.offGrfx.setColor(color);
            try {
                this.offGrfx.setFont(this.outFont);
            }
            catch (NullPointerException ex) {}
            for (int i = 0; i < 12; ++i) {
                this.offGrfx.drawString(this.Line[i], this.XPos, this.YPos + i * this.spacing);
            }
            graphics.drawImage(this.offImage, 0, 0, this);
            return;
        }
        this.offGrfx.setColor(this.background);
        this.offGrfx.fillRect(0, 0, this.size().width, this.size().height);
        this.offGrfx.setColor(color);
        try {
            this.offGrfx.setFont(this.outFont);
        }
        catch (NullPointerException ex2) {}
        for (int j = 12; j < 24; ++j) {
            this.offGrfx.drawString(this.Line[j], this.XPos, this.YPos + (j - 12) * this.spacing);
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
            if (this.direction == 1) {
                --this.color;
                if (this.color < 0) {
                    this.color = 0;
                    this.direction = 0;
                }
            }
            else {
                ++this.color;
                if (this.color > 255) {
                    this.color = 255;
                    this.direction = 1;
                    this.current *= -1;
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
    
    public tinyFader() {
        this.spacing = 12;
        this.delay = 50;
        this.step = 4;
        this.XPos = 5;
        this.YPos = 12;
        this.current = 1;
        this.direction = 1;
        this.color = 255;
        this.Line = new String[24];
        this.customFont = false;
    }
}
