import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.awt.FontMetrics;
import java.awt.Font;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class fadfree extends Applet implements MouseListener, Runnable
{
    Thread thread1;
    Graphics pad;
    Image buffer;
    boolean fColour;
    boolean increase;
    int cobRed;
    int cobGreen;
    int cobBlue;
    boolean flag;
    boolean goflag;
    int red;
    int green;
    int blue;
    int hold;
    int holdtil;
    int realintens;
    int count2;
    int count;
    String message;
    int msgNum;
    String[] word;
    int intens;
    URL newURL;
    boolean noURL;
    Font font;
    FontMetrics fontmetrics;
    String fontname;
    int realintens2;
    int realintens3;
    int intens1;
    int intens2;
    int intens3;
    int type;
    int speed;
    int size;
    int x;
    int y;
    int x2;
    int y2;
    
    public fadfree() {
        this.fColour = false;
        this.increase = true;
        this.flag = true;
        this.goflag = true;
        this.red = 0;
        this.green = 200;
        this.blue = 0;
        this.hold = 1;
        this.count = 0;
        this.msgNum = 0;
        this.intens = 0;
        this.newURL = null;
        this.noURL = true;
        this.type = 0;
        this.x = 0;
        this.y = 0;
        this.x2 = 0;
        this.y2 = 0;
    }
    
    public void init() {
        this.message = this.parmgetstr("Message", "No Messages Have Been Put in the Parameters");
        final String parmgetstr = this.parmgetstr("ForeColor", "Black");
        final String parmgetstr2 = this.parmgetstr("BgColor", "255|255|255");
        this.fontname = this.parmgetstr("FontName", "TimesRoman");
        this.size = this.parmgetint("FontSize", 22, 8, 200);
        final String s = new String(this.parmgetstr("FontItalic", "No"));
        final String s2 = new String(this.parmgetstr("FontBold", "No"));
        this.speed = this.parmgetint("Speed", 50, 1, 1000);
        this.holdtil = this.parmgetint("Wait", 50, 0, 1000);
        try {
            this.newURL = new URL("http://www.net800.co.uk/netstart/sirius");
            this.noURL = false;
        }
        catch (MalformedURLException ex) {
            this.noURL = true;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(this.message, "|");
        this.word = new String[stringTokenizer.countTokens() + 1];
        while (stringTokenizer.hasMoreTokens()) {
            ++this.msgNum;
            this.word[this.msgNum] = stringTokenizer.nextToken();
        }
        final StringTokenizer stringTokenizer2 = new StringTokenizer(parmgetstr2, "|");
        this.red = Integer.parseInt(stringTokenizer2.nextToken());
        this.green = Integer.parseInt(stringTokenizer2.nextToken());
        this.blue = Integer.parseInt(stringTokenizer2.nextToken());
        this.setBackground(new Color(this.red, this.green, this.blue));
        this.fColour = parmgetstr.equals(new String("White"));
        if (s.equals("Yes")) {
            this.type = 2;
        }
        if (s2.equals("Yes")) {
            this.type = 1;
        }
        if (s2.equals("Yes") && s.equals("Yes")) {
            this.type = 3;
        }
        this.font = new Font(this.fontname, this.type, this.size);
        this.fontmetrics = this.getFontMetrics(this.font);
        this.addMouseListener(this);
        this.intens2 = this.intens;
        this.intens3 = this.intens;
        this.realintens = 255 - this.intens;
        this.realintens2 = this.realintens;
        this.realintens3 = this.realintens;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!this.noURL) {
            this.getAppletContext().showDocument(this.newURL);
        }
    }
    
    public synchronized void mouseEntered(final MouseEvent mouseEvent) {
        System.out.println("Mouse is in the Applet");
        if (!this.noURL) {
            this.setCursor(new Cursor(12));
        }
    }
    
    public synchronized void mouseExited(final MouseEvent mouseEvent) {
        System.out.println("Mouse has left");
        this.setCursor(new Cursor(0));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        this.buffer = this.createImage(this.getSize().width, this.getSize().height);
        (this.pad = this.buffer.getGraphics()).setFont(this.font);
        if (this.fColour) {
            if (this.intens <= 125) {
                this.pad.setColor(new Color(this.intens1, this.intens2, this.intens3));
                if (this.intens3 <= this.blue) {
                    this.pad.setColor(new Color(this.intens1, this.intens2, this.blue));
                }
                this.cobBlue = this.pad.getColor().getBlue();
                if (this.intens1 <= this.red) {
                    this.pad.setColor(new Color(this.red, this.intens2, this.cobBlue));
                }
                this.cobRed = this.pad.getColor().getRed();
                if (this.intens2 <= this.green) {
                    this.pad.setColor(new Color(this.cobRed, this.green, this.cobBlue));
                }
                this.pad.drawString(this.word[this.count], this.x, this.y);
                this.pad.setColor(new Color(this.realintens, this.realintens2, this.realintens3));
                if (this.realintens3 <= this.blue) {
                    this.pad.setColor(new Color(this.realintens, this.realintens2, this.blue));
                }
                this.cobBlue = this.pad.getColor().getBlue();
                if (this.realintens <= this.red) {
                    this.pad.setColor(new Color(this.red, this.realintens2, this.cobBlue));
                }
                this.cobRed = this.pad.getColor().getRed();
                if (this.realintens2 <= this.green) {
                    this.pad.setColor(new Color(this.cobRed, this.green, this.cobBlue));
                }
                this.pad.drawString(this.word[this.count2], this.x2, this.y2);
            }
            else {
                this.pad.setColor(new Color(this.realintens, this.realintens2, this.realintens3));
                if (this.realintens3 <= this.blue) {
                    this.pad.setColor(new Color(this.realintens, this.realintens2, this.blue));
                }
                this.cobBlue = this.pad.getColor().getBlue();
                if (this.realintens <= this.red) {
                    this.pad.setColor(new Color(this.red, this.realintens2, this.cobBlue));
                }
                this.cobRed = this.pad.getColor().getRed();
                if (this.realintens2 <= this.green) {
                    this.pad.setColor(new Color(this.cobRed, this.green, this.cobBlue));
                }
                this.pad.drawString(this.word[this.count2], this.x2, this.y2);
                this.pad.setColor(new Color(this.intens1, this.intens2, this.intens3));
                if (this.intens3 <= this.blue) {
                    this.pad.setColor(new Color(this.intens1, this.intens2, this.blue));
                }
                this.cobBlue = this.pad.getColor().getBlue();
                if (this.intens1 <= this.red) {
                    this.pad.setColor(new Color(this.red, this.intens2, this.cobBlue));
                }
                this.cobRed = this.pad.getColor().getRed();
                if (this.intens2 <= this.green) {
                    this.pad.setColor(new Color(this.cobRed, this.green, this.cobBlue));
                }
                this.pad.drawString(this.word[this.count], this.x, this.y);
            }
        }
        else if (this.intens <= 125) {
            this.pad.setColor(new Color(255 - this.intens1, 255 - this.intens2, 255 - this.intens3));
            if (255 - this.intens2 >= this.green) {
                this.pad.setColor(new Color(255 - this.intens1, this.green, 255 - this.intens3));
            }
            this.cobGreen = this.pad.getColor().getGreen();
            if (255 - this.intens1 >= this.red) {
                this.pad.setColor(new Color(this.red, this.cobGreen, 255 - this.intens3));
            }
            this.cobRed = this.pad.getColor().getRed();
            if (255 - this.intens3 >= this.blue) {
                this.pad.setColor(new Color(this.cobRed, this.cobGreen, this.blue));
            }
            this.pad.drawString(this.word[this.count], this.x, this.y);
            this.pad.setColor(new Color(255 - this.realintens, 255 - this.realintens2, 255 - this.realintens3));
            if (255 - this.realintens2 >= this.green) {
                this.pad.setColor(new Color(255 - this.realintens, this.green, 255 - this.realintens3));
            }
            this.cobGreen = this.pad.getColor().getGreen();
            if (255 - this.realintens >= this.red) {
                this.pad.setColor(new Color(this.red, this.cobGreen, 255 - this.realintens3));
            }
            this.cobRed = this.pad.getColor().getRed();
            if (255 - this.realintens3 >= this.blue) {
                this.pad.setColor(new Color(this.cobRed, this.cobGreen, this.blue));
            }
            this.pad.drawString(this.word[this.count2], this.x2, this.y2);
        }
        else {
            this.pad.setColor(new Color(255 - this.realintens, 255 - this.realintens2, 255 - this.realintens3));
            if (255 - this.realintens2 >= this.green) {
                this.pad.setColor(new Color(255 - this.realintens, this.green, 255 - this.realintens3));
            }
            this.cobGreen = this.pad.getColor().getGreen();
            if (255 - this.realintens >= this.red) {
                this.pad.setColor(new Color(this.red, this.cobGreen, 255 - this.realintens3));
            }
            this.cobRed = this.pad.getColor().getRed();
            if (255 - this.realintens3 >= this.blue) {
                this.pad.setColor(new Color(this.cobRed, this.cobGreen, this.blue));
            }
            this.pad.drawString(this.word[this.count2], this.x2, this.y2);
            this.pad.setColor(new Color(255 - this.intens1, 255 - this.intens2, 255 - this.intens3));
            if (255 - this.intens2 >= this.green) {
                this.pad.setColor(new Color(255 - this.intens1, this.green, 255 - this.intens3));
            }
            this.cobGreen = this.pad.getColor().getGreen();
            if (255 - this.intens1 >= this.red) {
                this.pad.setColor(new Color(this.red, this.cobGreen, 255 - this.intens3));
            }
            this.cobRed = this.pad.getColor().getRed();
            if (255 - this.intens3 >= this.blue) {
                this.pad.setColor(new Color(this.cobRed, this.cobGreen, this.blue));
            }
            this.pad.drawString(this.word[this.count], this.x, this.y);
        }
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public int parmgetint(final String s, final int n, final int n2, final int n3) {
        final String parameter = this.getParameter(s);
        int int1;
        if (parameter == null) {
            int1 = n;
        }
        else {
            int1 = Integer.parseInt(parameter);
        }
        if (int1 > n3) {
            int1 = n3;
        }
        if (int1 < n2) {
            int1 = n2;
        }
        return int1;
    }
    
    public String parmgetstr(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        String s3;
        if (parameter == null) {
            s3 = s2;
        }
        else {
            s3 = parameter;
        }
        return s3;
    }
    
    public void run() {
        while (this.flag) {
            if (this.intens <= 3) {
                this.intens = 255;
                if (this.count >= this.msgNum) {
                    this.count = 0;
                }
                ++this.count;
                this.count2 = this.count + 1;
                if (this.count2 >= this.msgNum + 1) {
                    this.count2 = 1;
                }
                this.x = (this.getSize().width - this.fontmetrics.stringWidth(this.word[this.count])) / 2;
                this.y = (this.getSize().height + this.fontmetrics.getHeight()) / 2;
                this.x2 = (this.getSize().width - this.fontmetrics.stringWidth(this.word[this.count2])) / 2;
                this.y2 = (this.getSize().height + this.fontmetrics.getHeight()) / 2;
                this.hold = 1;
            }
            if (this.hold >= this.holdtil) {
                this.intens -= 5;
                this.intens2 = this.intens;
                this.intens3 = this.intens;
                this.realintens = 255 - this.intens;
                this.realintens2 = this.realintens;
                this.realintens3 = this.realintens;
            }
            else {
                this.intens2 = this.intens;
                this.intens3 = this.intens;
                this.realintens = 255 - this.intens;
                this.realintens2 = this.realintens;
                this.realintens3 = this.realintens;
                ++this.hold;
            }
            this.intens1 = this.intens;
            this.repaint();
            try {
                Thread.sleep(this.speed);
            }
            catch (InterruptedException ex) {
                System.out.println("The thread failed to sleep");
            }
        }
    }
    
    public void start() {
        (this.thread1 = new Thread(this)).setPriority(10);
        this.flag = true;
        this.thread1.start();
    }
    
    public void stop() {
        this.flag = false;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
