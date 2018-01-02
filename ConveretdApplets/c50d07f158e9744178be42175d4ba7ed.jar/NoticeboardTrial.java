import java.net.URL;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.AppletContext;
import java.awt.Font;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class NoticeboardTrial extends Applet implements Runnable
{
    Thread Noticethread;
    StringTokenizer news;
    String Noticenews;
    String Noticepage;
    String Target;
    String message;
    String paramnews;
    String fonttype;
    int style;
    int displaytype;
    int pointsize;
    int Height;
    int Width;
    int timedelay;
    int speed;
    int x;
    int y;
    FontMetrics fontmet;
    Font font;
    AppletContext ac;
    boolean frame;
    Color text;
    Color bordercolor;
    Color normalborder;
    Color mouseoverborder;
    
    public void init() {
        if (this.getParameter("NoticeClickable").trim().toUpperCase().equals("Y")) {
            this.Noticepage = this.getParameter("NoticePage");
            if ((this.Target = this.getParameter("Target")) == null) {
                this.frame = false;
            }
            this.ac = this.getAppletContext();
        }
        final int[] array = new int[6];
        final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("NormalBorder"), "|");
        array[0] = Integer.parseInt(stringTokenizer.nextToken());
        array[1] = Integer.parseInt(stringTokenizer.nextToken());
        array[2] = Integer.parseInt(stringTokenizer.nextToken());
        this.normalborder = new Color(array[0], array[1], array[2]);
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.getParameter("MouseOverBorder"), "|");
        array[3] = Integer.parseInt(stringTokenizer2.nextToken());
        array[4] = Integer.parseInt(stringTokenizer2.nextToken());
        array[5] = Integer.parseInt(stringTokenizer2.nextToken());
        this.mouseoverborder = new Color(array[3], array[4], array[5]);
        this.bordercolor = this.normalborder;
        this.paramnews = this.getParameter("News");
        this.Height = this.size().height;
        this.Width = this.size().width;
        this.speed = Integer.parseInt(this.getParameter("Speed"));
        this.timedelay = Integer.parseInt(this.getParameter("TimeDelay"));
        this.style = Integer.parseInt(this.getParameter("Style"));
        this.displaytype = Integer.parseInt(this.getParameter("Displaytype"));
        this.pointsize = Integer.parseInt(this.getParameter("Pointsize"));
        this.fonttype = this.getParameter("Font");
        this.font = new Font(this.fonttype, this.style, this.pointsize);
        this.fontmet = this.getFontMetrics(this.font);
        final int[] array2 = new int[6];
        final StringTokenizer stringTokenizer3 = new StringTokenizer(this.getParameter("TextColor"), "|");
        array2[0] = Integer.parseInt(stringTokenizer3.nextToken());
        array2[1] = Integer.parseInt(stringTokenizer3.nextToken());
        array2[2] = Integer.parseInt(stringTokenizer3.nextToken());
        this.setForeground(this.text = new Color(array2[0], array2[1], array2[2]));
        final StringTokenizer stringTokenizer4 = new StringTokenizer(this.getParameter("BackColor"), "|");
        array2[3] = Integer.parseInt(stringTokenizer4.nextToken());
        array2[4] = Integer.parseInt(stringTokenizer4.nextToken());
        array2[5] = Integer.parseInt(stringTokenizer4.nextToken());
        this.setBackground(new Color(array2[3], array2[4], array2[5]));
    }
    
    public void start() {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.paramnews, "|");
        int length = 0;
        String trim = "";
        for (int i = 0; i < stringTokenizer.countTokens(); ++i) {
            if (length < stringTokenizer.nextToken().trim().length()) {
                trim = stringTokenizer.nextToken().trim();
                length = trim.length();
            }
        }
        if (this.Width < this.fontmet.stringWidth(trim) || this.Height < this.fontmet.getHeight()) {
            if (this.Width < this.fontmet.stringWidth(trim)) {
                this.Width = this.fontmet.stringWidth(trim);
            }
            if (this.Height < this.fontmet.getHeight()) {
                this.Height = this.fontmet.getHeight();
            }
            this.resize(this.Width, this.Height);
        }
        super.showStatus("NoticeBoard");
        if (this.Noticethread == null) {
            (this.Noticethread = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    this.news = new StringTokenizer(this.paramnews, "|");
                    while (this.news.hasMoreTokens()) {
                        this.Noticenews = this.news.nextToken().trim();
                        if (this.displaytype >= 2 && this.displaytype <= 5) {
                            if (this.displaytype == 2) {
                                this.x = this.Width;
                                this.y = this.fontmet.getAscent() + (this.Height - (this.fontmet.getAscent() + this.fontmet.getDescent())) / 2;
                            }
                            else if (this.displaytype == 3) {
                                this.x = -this.fontmet.stringWidth(this.Noticenews);
                                this.y = this.fontmet.getAscent() + (this.Height - (this.fontmet.getAscent() + this.fontmet.getDescent())) / 2;
                            }
                            else if (this.displaytype == 4) {
                                this.y = 0;
                                this.x = (this.Width - this.fontmet.stringWidth(this.Noticenews)) / 2;
                            }
                            else if (this.displaytype == 5) {
                                this.y = this.Height;
                                this.x = (this.Width - this.fontmet.stringWidth(this.Noticenews)) / 2;
                            }
                            this.movemessage();
                        }
                        else {
                            this.x = (this.Width - this.fontmet.stringWidth(this.Noticenews)) / 2;
                            this.y = this.fontmet.getAscent() + (this.Height - (this.fontmet.getAscent() + this.fontmet.getDescent())) / 2;
                            this.repaint();
                        }
                        Thread.sleep(this.timedelay);
                    }
                }
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public void movemessage() {
        try {
            if (this.displaytype == 2) {
                while (this.x > -this.fontmet.stringWidth(this.Noticenews)) {
                    this.repaint();
                    this.x -= 2;
                    Thread.sleep(this.speed);
                }
                return;
            }
            if (this.displaytype == 3) {
                while (this.x < this.Width) {
                    this.repaint();
                    this.x += 2;
                    Thread.sleep(this.speed);
                }
                return;
            }
            if (this.displaytype == 4) {
                while (this.y < this.Height + this.fontmet.getHeight()) {
                    this.repaint();
                    this.y += 2;
                    Thread.sleep(this.speed);
                }
                return;
            }
            if (this.displaytype == 5) {
                while (this.y > -this.fontmet.getHeight()) {
                    this.repaint();
                    this.y -= 2;
                    Thread.sleep(this.speed);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(this.bordercolor);
        graphics.drawRect(0, 0, this.Width - 1, this.Height - 1);
        graphics.setFont(this.font);
        graphics.setColor(this.text);
        graphics.drawString(this.Noticenews, this.x, this.y);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                this.mouseclick();
                break;
            }
            case 502:
            case 503: {
                this.showStatus(this.message);
                this.bordercolor = this.mouseoverborder;
                this.repaint();
                break;
            }
            case 504:
            case 505: {
                this.showStatus(" ");
                this.bordercolor = this.normalborder;
                this.repaint();
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    private void mouseclick() {
        if (this.getParameter("NoticeClickable").trim().toUpperCase().equals("Y")) {
            if (this.getParameter("MusicReq").trim().toUpperCase().equals("Y")) {
                this.play(this.getCodeBase(), this.getParameter("MusicFile").trim());
            }
            try {
                if (this.frame) {
                    this.ac.showDocument(new URL(this.Noticepage), this.Target);
                    return;
                }
                this.ac.showDocument(new URL(this.Noticepage));
            }
            catch (Exception ex) {
                System.out.println("Exception  is " + ex.toString());
            }
        }
    }
    
    public NoticeboardTrial() {
        this.Noticenews = "";
        this.Noticepage = "";
        this.Target = "";
        this.message = "free applets at www.consultcom.com";
        this.fonttype = "";
        this.frame = true;
    }
}
