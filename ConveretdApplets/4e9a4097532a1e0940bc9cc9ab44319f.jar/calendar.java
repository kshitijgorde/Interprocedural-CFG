import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.Dimension;
import java.net.MalformedURLException;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.util.GregorianCalendar;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.Label;
import java.awt.Button;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Frame;
import java.net.URL;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class calendar extends Applet implements ItemListener, MouseListener
{
    URL url;
    URL mail;
    Frame frame;
    TextField year;
    TextField fade;
    TextField title;
    TextField path;
    TextField picsize;
    Choice fntcol;
    Button close;
    Button calen;
    Button home;
    Button email;
    Label msg;
    String message;
    String error;
    String stitle;
    String col;
    String file;
    String dtitle;
    Color fcol;
    Image img;
    Image pic;
    int wid;
    int ht;
    int yr;
    int alpha;
    int wide;
    int high;
    int imagesize;
    int[] pix;
    boolean done;
    boolean dopaint;
    Font font1;
    Font font2;
    FontMetrics fm1;
    FontMetrics fm2;
    GregorianCalendar ucal;
    int[] mm;
    int jan;
    int feb;
    int mar;
    int apr;
    int may;
    int jun;
    int jul;
    int aug;
    int sep;
    int oct;
    int nov;
    int dec;
    char[] day;
    int[] end;
    String[] month;
    String[] fcolor;
    int w;
    int h;
    int w0;
    int h0;
    int wm;
    int hm;
    int dw;
    int wstart;
    
    public calendar() {
        this.done = false;
        this.dopaint = false;
        this.day = new char[] { 'S', 'M', 'T', 'W', 'T', 'F', 'S' };
        this.end = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        this.month = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        this.fcolor = new String[] { "blue", "black", "cyan", "darkGray", "gray", "green", "lightGray", "magenta", "orange", "pink", "red", "white", "yellow" };
        (this.frame = new Frame("Sonali Calendar")).setSize(250, 250);
        this.frame.addMouseListener(this);
        this.frame.setLayout(new GridLayout(8, 2));
        this.frame.setFont(new Font("Arial", 0, 10));
        (this.close = new Button("Close")).addMouseListener(this);
        (this.calen = new Button("Make calendar")).addMouseListener(this);
        (this.home = new Button("Homepage")).addMouseListener(this);
        (this.email = new Button("email")).addMouseListener(this);
        this.fade = new TextField("60");
        this.title = new TextField();
        this.fntcol = new Choice();
        for (int i = 0; i < this.fcolor.length; ++i) {
            this.fntcol.add(this.fcolor[i]);
        }
        this.path = new TextField("pic.jpg");
        this.picsize = new TextField("70");
        this.msg = new Label("Welcome to Sonali Calendar");
        this.ucal = new GregorianCalendar();
        this.year = new TextField(String.valueOf(this.ucal.get(1)));
        this.frame.add(new Label("Input values for your calendar"));
        this.frame.add(new Label("(default values are indicated)"));
        this.frame.add(new Label("Input the year for which you want the calendar"));
        this.frame.add(this.year);
        this.frame.add(new Label("What title do you want on top"));
        this.frame.add(this.title);
        this.frame.add(new Label("Color of calendar font"));
        this.frame.add(this.fntcol);
        this.frame.add(new Label("Fade the picture in background upto (%)"));
        this.frame.add(this.fade);
        this.frame.add(new Label("Name of pciture file (jpg, gif or jpeg)"));
        this.frame.add(this.path);
        this.frame.add(new Label("Size of picture (in % of window size)"));
        this.frame.add(this.picsize);
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(this.calen);
        panel.add(this.close);
        this.frame.add(panel);
        final Panel panel2 = new Panel();
        panel2.setLayout(new GridLayout(1, 2));
        panel2.add(this.home);
        panel2.add(this.email);
        this.frame.add(panel2);
        this.font1 = new Font("Arial", 1, 18);
        this.fm1 = this.getFontMetrics(this.font1);
        this.font2 = new Font("Arial", 1, 14);
        this.fm2 = this.getFontMetrics(this.font2);
        this.mm = new int[12];
    }
    
    public void Update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void getbackimage() {
        this.wide = this.imagesize * this.wid / 100;
        this.high = this.imagesize * this.ht / 100;
        final Image scaledInstance = this.img.getScaledInstance(this.wide, this.high, 2);
        this.pix = new int[this.wide * this.high];
        try {
            new PixelGrabber(scaledInstance, 0, 0, this.wide, this.high, this.pix, 0, this.wide).grabPixels();
        }
        catch (InterruptedException ex) {
            System.out.println("Unable to grab pixels" + ex);
        }
        for (int i = 0; i < this.wide * this.high; ++i) {
            this.pix[i] = ((this.pix[i] & 0xFFFFFF) | this.alpha);
        }
        this.pic = this.createImage(new MemoryImageSource(this.wide, this.high, this.pix, 0, this.wide));
        this.dopaint = true;
    }
    
    public void getorientation(final int n) {
        if (n == 0) {
            this.wm = 0;
            this.hm = 0;
        }
        if (n == 1) {
            this.wm = 220;
            this.hm = 0;
        }
        if (n == 2) {
            this.wm = 440;
            this.hm = 0;
        }
        if (n == 3) {
            this.wm = 0;
            this.hm = 210;
        }
        if (n == 4) {
            this.wm = 220;
            this.hm = 210;
        }
        if (n == 5) {
            this.wm = 440;
            this.hm = 210;
        }
        if (n == 6) {
            this.wm = 0;
            this.hm = 420;
        }
        if (n == 7) {
            this.wm = 220;
            this.hm = 420;
        }
        if (n == 8) {
            this.wm = 440;
            this.hm = 420;
        }
        if (n == 9) {
            this.wm = 0;
            this.hm = 630;
        }
        if (n == 10) {
            this.wm = 220;
            this.hm = 630;
        }
        if (n == 11) {
            this.wm = 440;
            this.hm = 630;
        }
    }
    
    public void getvalues() {
        this.yr = Integer.parseInt(this.year.getText());
        this.stitle = String.valueOf(this.title.getText()) + "Year " + this.yr;
        if (this.fntcol.getSelectedItem() == "blue") {
            this.fcol = Color.blue;
        }
        if (this.fntcol.getSelectedItem() == "black") {
            this.fcol = Color.black;
        }
        if (this.fntcol.getSelectedItem() == "cyan") {
            this.fcol = Color.cyan;
        }
        if (this.fntcol.getSelectedItem() == "darkGray") {
            this.fcol = Color.darkGray;
        }
        if (this.fntcol.getSelectedItem() == "gray") {
            this.fcol = Color.gray;
        }
        if (this.fntcol.getSelectedItem() == "green") {
            this.fcol = Color.green;
        }
        if (this.fntcol.getSelectedItem() == "lightGray") {
            this.fcol = Color.lightGray;
        }
        if (this.fntcol.getSelectedItem() == "magenta") {
            this.fcol = Color.magenta;
        }
        if (this.fntcol.getSelectedItem() == "orange") {
            this.fcol = Color.orange;
        }
        if (this.fntcol.getSelectedItem() == "pink") {
            this.fcol = Color.pink;
        }
        if (this.fntcol.getSelectedItem() == "red") {
            this.fcol = Color.red;
        }
        if (this.fntcol.getSelectedItem() == "white") {
            this.fcol = Color.white;
        }
        if (this.fntcol.getSelectedItem() == "yellow") {
            this.fcol = Color.yellow;
        }
        this.alpha = (int)((100.0 - Integer.parseInt(this.fade.getText())) / 100.0 * 255.0) << 24;
        this.file = "images/" + this.path.getText();
        this.img = this.getImage(this.getCodeBase(), "images/pic.jpg");
        this.imagesize = Integer.parseInt(this.picsize.getText());
        this.getbackimage();
    }
    
    public void init() {
        this.setFont(new Font("Arial", 0, 12));
        this.frame.pack();
        this.frame.show();
        final Dimension size = this.getSize();
        this.wid = size.width;
        this.ht = size.height;
        try {
            this.url = new URL("http://www.pratyush.net");
            this.mail = new URL("mailto:pankaj@pratyush.net");
        }
        catch (MalformedURLException ex) {
            System.out.println("malformed URL");
        }
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final Component component = mouseEvent.getComponent();
        if (component == this.close && this.frame.isShowing()) {
            this.frame.hide();
        }
        if (component == this.calen) {
            this.frame.hide();
            this.startcalendar();
        }
        if (component == this.home) {
            this.getAppletContext().showDocument(this.url);
        }
        if (component == this.email) {
            this.getAppletContext().showDocument(this.mail);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (this.dopaint) {
            final Image image = this.createImage(this.wid, this.ht);
            final Graphics graphics2 = image.getGraphics();
            graphics2.drawImage(this.pic, (this.wid - this.wide) / 2, (this.ht - this.high) / 2, this.wide, this.high, this);
            graphics2.setColor(Color.blue);
            graphics2.setFont(new Font("Arial", 1, 36));
            graphics2.drawString(this.stitle, 20, 40);
            for (int i = 0; i < 12; ++i) {
                final int n = 20;
                final int n2 = 60;
                this.getorientation(i);
                this.w = this.wm + n;
                this.h = this.hm + n2;
                graphics2.setFont(this.font1);
                graphics2.setColor(this.fcol);
                graphics2.drawString("----------------------------------", this.w, this.h);
                this.h += 15;
                graphics2.drawString("           " + this.month[i], this.w, this.h);
                this.h += 15;
                graphics2.drawString("----------------------------------", this.w, this.h);
                this.w += 10;
                this.h += 20;
                this.wstart = this.w;
                for (int j = 0; j < 7; ++j) {
                    if (j == 0) {
                        graphics2.setColor(Color.red);
                    }
                    else {
                        graphics2.setColor(this.fcol);
                    }
                    graphics2.drawString(String.valueOf(this.day[j]), this.w, this.h);
                    this.w += 30;
                }
                graphics2.setFont(this.font2);
                this.w = 0;
                this.h += 25;
                this.dw = this.mm[i];
                for (int k = 1; k < this.end[i] + 1; ++k) {
                    if (this.dw == 1) {
                        this.w = 30 + this.wm;
                    }
                    if (this.dw == 2) {
                        this.w = 62 + this.wm;
                    }
                    if (this.dw == 3) {
                        this.w = 90 + this.wm;
                    }
                    if (this.dw == 4) {
                        this.w = 120 + this.wm;
                    }
                    if (this.dw == 5) {
                        this.w = 150 + this.wm;
                    }
                    if (this.dw == 6) {
                        this.w = 180 + this.wm;
                    }
                    if (this.dw == 7) {
                        this.w = 210 + this.wm;
                    }
                    if (this.dw == 1) {
                        graphics2.setColor(Color.red);
                    }
                    else {
                        graphics2.setColor(this.fcol);
                    }
                    graphics2.drawString(String.valueOf(k), this.w, this.h);
                    ++this.dw;
                    if (this.dw == 8) {
                        this.dw = 1;
                        this.h += 22;
                    }
                }
            }
            this.h += 45;
            graphics2.setFont(new Font("Arial", 0, 10));
            graphics2.setColor(Color.blue);
            graphics2.drawString("Designed by Pankaj Mathur (http://www.pratyush.net)", 30, this.h);
            graphics.drawImage(image, 0, 0, this);
        }
    }
    
    public void start() {
    }
    
    public void startcalendar() {
        this.getvalues();
        this.ucal.set(1, this.yr);
        this.ucal.set(2, 0);
        this.ucal.set(5, 1);
        this.jan = this.ucal.get(7);
        this.mm[0] = this.jan;
        this.ucal.add(2, 1);
        this.feb = this.ucal.get(7);
        this.mm[1] = this.feb;
        if (this.ucal.isLeapYear(this.yr)) {
            this.end[1] = 29;
        }
        this.ucal.add(2, 1);
        this.mar = this.ucal.get(7);
        this.mm[2] = this.mar;
        this.ucal.add(2, 1);
        this.apr = this.ucal.get(7);
        this.mm[3] = this.apr;
        this.ucal.add(2, 1);
        this.may = this.ucal.get(7);
        this.mm[4] = this.may;
        this.ucal.add(2, 1);
        this.jun = this.ucal.get(7);
        this.mm[5] = this.jun;
        this.ucal.add(2, 1);
        this.jul = this.ucal.get(7);
        this.mm[6] = this.jul;
        this.ucal.add(2, 1);
        this.aug = this.ucal.get(7);
        this.mm[7] = this.aug;
        this.ucal.add(2, 1);
        this.sep = this.ucal.get(7);
        this.mm[8] = this.sep;
        this.ucal.add(2, 1);
        this.oct = this.ucal.get(7);
        this.mm[9] = this.oct;
        this.ucal.add(2, 1);
        this.nov = this.ucal.get(7);
        this.mm[10] = this.nov;
        this.ucal.add(2, 1);
        this.dec = this.ucal.get(7);
        this.mm[11] = this.dec;
        this.ucal.add(2, 1);
        this.repaint();
    }
    
    public void stop() {
    }
}
