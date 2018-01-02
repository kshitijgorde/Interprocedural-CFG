import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.util.GregorianCalendar;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class moonday extends Applet implements Runnable, MouseListener
{
    Thread runner;
    Image Buffer;
    Graphics gBuffer;
    Image[] luna;
    MediaTracker theTracker;
    Image moon;
    Image play;
    Image anim;
    Image animoon;
    Image stop;
    Image background;
    Image rim;
    Image about;
    Image close;
    String name;
    String phase;
    String phasea;
    int yr;
    int mth;
    int dy;
    int hr;
    int mn;
    int state;
    int x;
    int y;
    int z;
    int p;
    int i;
    int width;
    int height;
    int delay;
    
    public void init() {
        this.theTracker = new MediaTracker(this);
        final GregorianCalendar gregorianCalendar = new GregorianCalendar();
        this.yr = gregorianCalendar.get(1);
        this.dy = gregorianCalendar.get(6);
        this.hr = gregorianCalendar.get(11);
        this.mn = gregorianCalendar.get(12);
        this.mth = gregorianCalendar.get(2);
        this.addMouseListener(this);
        this.width = this.getSize().width;
        this.height = this.getSize().height;
        this.Buffer = this.createImage(this.width, this.height);
        this.gBuffer = this.Buffer.getGraphics();
        this.getImageNumber();
        this.getMoonName();
        this.moon = this.getImage(this.getCodeBase(), "Moon" + this.y + ".gif");
        this.play = this.getImage(this.getCodeBase(), "play.gif");
        this.stop = this.getImage(this.getCodeBase(), "stop.gif");
        this.background = this.getImage(this.getCodeBase(), "background.gif");
        this.rim = this.getImage(this.getCodeBase(), "rim.gif");
        this.about = this.getImage(this.getCodeBase(), "about.gif");
        this.close = this.getImage(this.getCodeBase(), "close.gif");
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {}
            this.gBuffer.drawImage(this.background, 0, 0, this);
            if (this.state == 1) {
                this.gBuffer.drawImage(this.moon, 0, 0, this);
                this.gBuffer.drawImage(this.play, 135, 200, this);
                this.gBuffer.drawImage(this.about, 10, 202, this);
                if (this.p == 1) {
                    this.gBuffer.drawImage(this.rim, 135, 200, this);
                    this.gBuffer.drawImage(this.rim, 5, 200, this);
                }
                this.gBuffer.setFont(new Font("Helvetica", 1, 10));
                this.gBuffer.setColor(Color.white);
                this.gBuffer.drawString("Todays Moon ", 50, 10);
                this.gBuffer.setColor(Color.blue);
                this.gBuffer.drawString(this.name, 10, 190);
            }
            if (this.state >= 2) {
                if (this.state == 2) {
                    this.loadAnim();
                }
                if (this.state == 3) {
                    this.gBuffer.drawImage(this.luna[this.i], 3, 3, this);
                    this.gBuffer.drawImage(this.stop, 135, 200, this);
                    if (this.p == 1) {
                        this.gBuffer.drawImage(this.rim, 135, 200, this);
                    }
                }
                if (this.state >= 4) {
                    this.state = 5;
                    this.gBuffer.drawImage(this.background, 0, 0, this);
                    this.aboutText();
                    if (this.p == 1) {
                        this.gBuffer.drawImage(this.rim, 5, 200, this);
                    }
                }
                ++this.i;
                if (this.i == 25) {
                    this.i = 1;
                }
            }
            this.repaint();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.Buffer, 0, 0, this);
    }
    
    public void loadAnim() {
        this.state = 3;
        this.gBuffer.setFont(new Font("Helvetica", 1, 12));
        this.gBuffer.setColor(Color.red);
        this.repaint();
        this.luna = new Image[25];
        for (int i = 1; i <= 24; ++i) {
            this.luna[i] = this.getImage(this.getCodeBase(), "Moon" + i + ".gif");
            this.theTracker.addImage(this.luna[i], i);
        }
        this.i = 1;
        while (this.i <= 24) {
            this.gBuffer.drawString("Loading Images....24/" + this.i, 10, 110);
            this.repaint();
            try {
                this.theTracker.waitForID(this.i);
            }
            catch (InterruptedException ex) {
                return;
            }
            this.gBuffer.drawImage(this.background, 0, 0, this);
            this.repaint();
            ++this.i;
        }
        this.i = 1;
    }
    
    public void getImageNumber() {
        this.x = (this.dy * 24 + this.hr) * 60 + this.mn;
        if (this.yr == 2003) {
            this.x += 38426;
        }
        if (this.yr == 2004) {
            this.x += 10937;
        }
        if (this.yr == 2005) {
            this.x += 27271;
        }
        if (this.yr == 2006) {
            this.x += 1339;
        }
        if (this.yr == 2007) {
            this.x += 14999;
        }
        if (this.yr == 2008) {
            this.x += 30620;
        }
        if (this.yr == 2009) {
            this.x += 5018;
        }
        if (this.yr == 2010) {
            this.x += 20878;
        }
        if (this.yr == 2011) {
            this.x += 36384;
        }
        this.z = this.x * 100 / 177183;
        this.x = this.z / 24;
        this.y = this.z - this.x * 24;
        ++this.y;
        if (this.yr == 2004 & this.mth == 7 & this.y == 13) {
            this.y = 0;
        }
        if (this.yr == 2007 & this.mth == 7 & this.y == 13) {
            this.y = 0;
        }
        if (this.yr == 2009 & this.mth == 12 & this.y == 13) {
            this.y = 0;
        }
    }
    
    public void aboutText() {
        this.gBuffer.drawImage(this.close, 10, 203, this);
        this.gBuffer.setFont(new Font("Helvetica", 0, 10));
        this.gBuffer.setColor(Color.white);
        this.gBuffer.drawString("                 A B O U T", 1, 10);
        this.gBuffer.drawString("Moonday is a lunar calendar", 1, 30);
        this.gBuffer.drawString("which shows the phase of the", 1, 40);
        this.gBuffer.drawString("moon on this day.  It is calculated", 1, 50);
        this.gBuffer.drawString("from the previous years final New", 1, 60);
        this.gBuffer.drawString("Moon and can look ahead 'till 31st of", 1, 70);
        this.gBuffer.drawString("Dec 2011.    24 images are split ", 1, 80);
        this.gBuffer.drawString("into 29.5 hours, which is the Lunar", 1, 90);
        this.gBuffer.drawString("Cycle of 29.5 days.              ", 1, 100);
        this.gBuffer.drawString("Applet designed and written by", 1, 140);
        this.gBuffer.drawString("Dougie Murray", 1, 150);
        this.gBuffer.drawString("http://www.dougiem.fsnet.co.uk", 1, 160);
        this.gBuffer.setColor(Color.green);
        this.gBuffer.drawString("The Moon now :", 50, 205);
        this.gBuffer.setFont(new Font("Helvetica", 0, 12));
        this.gBuffer.setColor(Color.blue);
        this.gBuffer.drawString(String.valueOf(this.phase) + this.phasea, 50, 220);
    }
    
    public void getMoonName() {
        this.name = "";
        if (this.mth == 1 & this.y == 13) {
            this.name = "Wolf Moon";
        }
        if (this.mth == 2 & this.y == 13) {
            this.name = "Snow Moon";
        }
        if (this.mth == 3 & this.y == 13) {
            this.name = "Worm Moon";
        }
        if (this.mth == 4 & this.y == 13) {
            this.name = "Pink Moon";
        }
        if (this.mth == 5 & this.y == 13) {
            this.name = "Flower Moon";
        }
        if (this.mth == 6 & this.y == 13) {
            this.name = "Strawberry Moon";
        }
        if (this.mth == 7 & this.y == 13) {
            this.name = "Buck Moon";
        }
        if (this.mth == 8 & this.y == 13) {
            this.name = "Sturgeon Moon";
        }
        if (this.mth == 9 & this.y == 13) {
            this.name = "Harvest Moon";
        }
        if (this.mth == 10 & this.y == 13) {
            this.name = "Hunters Moon";
        }
        if (this.mth == 11 & this.y == 13) {
            this.name = "Beaver Moon";
        }
        if (this.mth == 12 & this.y == 13) {
            this.name = "Cold Moon";
        }
        this.phase = "";
        this.phasea = "";
        if (this.y == 1) {
            this.phase = "N e w";
        }
        if (this.y == 7) {
            this.phase = "1st Quarter";
        }
        if (this.y == 13) {
            this.phase = "F u l l";
        }
        if (this.y == 19) {
            this.phase = "Last Quarter";
        }
        if (this.y > 1 & this.y < 7) {
            this.phase = "Waxing";
            this.phasea = " Crescent";
        }
        if (this.y > 7 & this.y < 13) {
            this.phase = "Waxing";
            this.phasea = " Gibbous";
        }
        if (this.y > 13 & this.y < 19) {
            this.phase = "Waning";
            this.phasea = " Gibbous";
        }
        if (this.y > 19 & this.y <= 24) {
            this.phase = "Waning";
            this.phasea = " Crescent";
        }
        if (this.y == 0) {
            this.name = "Blue Moon";
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.p = 1;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.p = 0;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (x >= 135 & x <= 157 & (y >= 200 & y <= 220)) {
            if (this.state == 1) {
                this.state = 2;
            }
            if (this.state == 3) {
                this.state = 1;
            }
        }
        if (x >= 5 & x <= 25 & (y >= 200 & y <= 220)) {
            this.gBuffer.drawImage(this.background, 0, 0, this);
            this.repaint();
            if (this.state == 1) {
                this.state = 4;
            }
            if (this.state == 5) {
                this.state = 1;
            }
        }
    }
    
    public moonday() {
        this.state = 1;
        this.y = 13;
        this.i = 1;
        this.delay = 200;
    }
}
