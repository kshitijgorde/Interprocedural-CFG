import java.net.MalformedURLException;
import java.awt.LayoutManager;
import java.awt.Component;
import java.net.URLEncoder;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.applet.AudioClip;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Font;
import java.net.URL;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class StarGateDemo extends Applet implements Runnable
{
    Thread nit;
    Graphics g;
    Graphics b;
    Graphics[] buffer;
    Image[] image;
    MediaTracker tracker;
    Image present;
    Image[] img;
    SG1_Animation animation;
    SG1_Skin skin;
    SG1_Status stat;
    Color fgcolor;
    Color cursorCol;
    Color bgcolor;
    int w;
    int h;
    int absx;
    int absy;
    URL url;
    URL cgiUrl;
    SG1_Button butt1;
    SG1_Button butt2;
    SG1_Button butt3;
    SG1_Button butt4;
    Font font;
    FontMetrics info;
    int step;
    Point labelPosition;
    Point prelabelPosition;
    Dimension screen;
    String recipient;
    String senderAddress;
    String senderName;
    String subject;
    String row1;
    String label;
    String prelabel;
    String row;
    char c;
    int lpoz;
    int vmpoz;
    int vmline;
    int uy;
    int cx;
    int cy;
    int cw;
    int ch;
    SG1_Blink blink;
    Rectangle[] pol;
    Vector vmes;
    Vector response;
    Rectangle Yes;
    Rectangle No;
    SG1_Demo sm;
    boolean error;
    AudioClip typesound;
    AudioClip accsound;
    AudioClip buttonsound;
    
    public boolean keyDown(final Event evt, final int key) {
        if (this.step == 0) {
            this.setStep(1);
            this.setCursor();
        }
        else if (this.step > 0 && this.step < 5) {
            if (key == 8) {
                this.label = this.removeChar(this.label, this.lpoz);
                --this.lpoz;
                this.centerLabel();
            }
            else if (key == 1007) {
                ++this.lpoz;
                if (this.lpoz > this.label.length()) {
                    this.lpoz = this.label.length();
                }
            }
            else if (key != 1004 && key != 1005) {
                if (key == 1006) {
                    if (this.lpoz > 0) {
                        --this.lpoz;
                    }
                }
                else if (key == 10) {
                    this.accept();
                    if (this.step < 5) {
                        this.setStep(this.step + 1);
                    }
                    else {
                        this.setStep(0);
                    }
                }
                else {
                    this.c = (char)key;
                    this.label = this.insertChar(this.label, this.c, this.lpoz);
                    ++this.lpoz;
                    this.centerLabel();
                }
            }
        }
        else if (this.step == 5) {
            this.row = this.vmes.elementAt(this.vmline);
            if (key == 1007) {
                if (this.vmpoz < this.row.length()) {
                    ++this.vmpoz;
                }
                else if (this.vmes.elementAt(this.vmline + 1) != null) {
                    ++this.vmline;
                    this.vmpoz = 0;
                }
            }
            else if (key == 1006) {
                if (this.vmpoz > 0) {
                    --this.vmpoz;
                }
                else if (this.vmline - 1 >= 0) {
                    --this.vmline;
                    final int sl = this.vmes.elementAt(this.vmline).length();
                    this.vmpoz = sl;
                }
            }
            else if (key == 1005) {
                if (this.vmline < this.vmes.size() - 1) {
                    ++this.vmline;
                    final int sl = this.vmes.elementAt(this.vmline).length();
                    if (this.vmpoz > sl) {
                        this.vmpoz = sl;
                    }
                }
            }
            else if (key == 1004) {
                if (this.vmline > 0) {
                    --this.vmline;
                }
                final int sl = this.vmes.elementAt(this.vmline).length();
                if (this.vmpoz > sl) {
                    this.vmpoz = sl;
                }
            }
            else if (key == 8) {
                if (this.vmpoz > 0) {
                    this.row = this.removeChar(this.row, this.vmpoz);
                    this.vmes.setElementAt(this.row, this.vmline);
                    --this.vmpoz;
                }
                else {
                    this.row1 = this.vmes.elementAt(this.vmline - 1);
                    this.vmpoz = this.row1.length();
                    this.row = this.row1 + this.row;
                    this.vmes.removeElementAt(this.vmline);
                    --this.vmline;
                    this.vmes.setElementAt(this.row, this.vmline);
                }
            }
            else if (key == 10) {
                if (this.vmpoz <= this.row.length()) {
                    this.row1 = this.row.substring(0, this.vmpoz);
                    this.row = this.row.substring(this.vmpoz, this.row.length());
                    this.vmes.setElementAt(this.row1, this.vmline);
                    this.vmes.insertElementAt(this.row, this.vmline + 1);
                    ++this.vmline;
                    this.vmpoz = 0;
                }
            }
            else {
                this.c = (char)key;
                this.row = this.insertChar(this.row, this.c, this.vmpoz);
                this.vmes.setElementAt(this.row, this.vmline);
                ++this.vmpoz;
            }
        }
        this.setCursor();
        this.typesound.play();
        return true;
    }
    
    public void stop() {
        this.stat.thisThread.stop();
        this.stat = null;
        this.blink.thread.stop();
        this.blink = null;
        if (this.sm != null) {
            this.sm.thread.stop();
            this.sm = null;
        }
        if (this.nit != null) {
            this.nit.stop();
            this.nit = null;
        }
        this.image = null;
        this.buffer = null;
        this.present = null;
    }
    
    public void centerLabel() {
        this.labelPosition.x = (this.screen.width - this.info.stringWidth(this.label)) / 2;
        this.labelPosition.y = (this.screen.height - this.info.getHeight()) / 2 + 2 * this.info.getHeight();
        this.prelabelPosition.x = (this.screen.width - this.info.stringWidth(this.prelabel)) / 2;
        this.prelabelPosition.y = (this.screen.height - this.info.getHeight()) / 2;
    }
    
    public void paint(final Graphics g) {
        g.setColor(this.bgcolor);
        g.fillRect(0, 0, this.w, this.h);
        if (this.img[0] != null) {
            g.drawImage(this.img[0], 0, 0, this);
        }
    }
    
    public void moveLine(final String where) {
        if (where == "left") {
            this.absx -= 15;
            this.cx -= 15;
        }
        else if (where == "right") {
            this.absx += 60;
            this.cx += 60;
        }
    }
    
    public Color getColor(final String c) {
        final StringTokenizer tok = new StringTokenizer(c, ",");
        final int r = Integer.parseInt(tok.nextToken());
        final int g = Integer.parseInt(tok.nextToken());
        final int b = Integer.parseInt(tok.nextToken());
        final Color bl = new Color(r, g, b);
        return bl;
    }
    
    public void destroy() {
        this.stat.thisThread.stop();
        this.stat = null;
        this.blink.thread.stop();
        this.blink = null;
        if (this.sm != null) {
            this.sm.thread.stop();
            this.sm = null;
        }
        if (this.nit != null) {
            this.nit.stop();
            this.nit = null;
        }
        this.image = null;
        this.buffer = null;
        this.present = null;
    }
    
    public String removeChar(final String st, final int index) {
        if (st == this.label) {
            return new String(st.substring(0, this.lpoz - 1) + st.substring(this.lpoz, st.length()));
        }
        if (st == this.row) {
            return new String(st.substring(0, this.vmpoz - 1) + st.substring(this.vmpoz, st.length()));
        }
        return null;
    }
    
    public StarGateDemo() {
        this.img = new Image[5];
        this.bgcolor = Color.black;
        this.labelPosition = new Point(0, 0);
        this.prelabelPosition = new Point(0, 0);
        this.row = "";
        this.pol = new Rectangle[4];
    }
    
    public void accept() {
        if (this.step == 1) {
            this.recipient = this.label;
        }
        else if (this.step == 2) {
            this.senderAddress = this.label;
        }
        else if (this.step == 3) {
            this.senderName = this.label;
        }
        else if (this.step == 4) {
            this.subject = this.label;
        }
        this.accsound.play();
    }
    
    public void setStep(final int no) {
        this.b.setFont(this.font);
        this.b.setColor(Color.white);
        if (no == 0) {
            this.step = 0;
            this.b.setFont(new Font("Arial", 1, 25));
            this.b.setColor(Color.gray);
            this.stat.controler = 0;
            this.stat.changed = true;
        }
        else if (no == 1) {
            this.step = 1;
            this.prelabel = "Mail to:";
            this.label = this.recipient;
            this.stat.controler = 1;
            this.stat.changed = true;
        }
        else if (no == 2) {
            this.step = 2;
            this.prelabel = "Your address:";
            this.label = this.senderAddress;
            this.stat.controler = 2;
            this.stat.changed = true;
        }
        else if (no == 3) {
            this.step = 3;
            this.label = this.senderName;
            this.prelabel = "Your name";
            this.stat.controler = 3;
            this.stat.changed = true;
        }
        else if (no == 4) {
            this.step = 4;
            this.label = this.subject;
            this.prelabel = "Subject:";
            this.stat.controler = 4;
            this.stat.changed = true;
        }
        else if (no == 5) {
            this.step = 5;
            this.vmpoz = 0;
            this.vmline = 0;
            this.stat.controler = 5;
            this.stat.changed = true;
        }
        else if (no == 6) {
            this.uy = 28 + (this.screen.height - (3 * this.ch + 30)) / 2;
            this.Yes = new Rectangle((this.screen.width - 120) / 2, this.uy + this.ch * 2 + 6, 30, 20);
            this.No = new Rectangle((this.screen.width - 120) / 2 + 90, this.uy + this.ch * 2 + 6, 30, 20);
            this.step = 6;
            this.stat.controler = 6;
            this.stat.changed = true;
        }
        else if (no == 7) {
            this.b.setFont(new Font("Arial", 1, 17));
            this.step = 7;
            this.stat.controler = 7;
            this.stat.changed = true;
        }
        else if (no == 8) {
            this.step = 8;
            if (this.error) {
                this.stat.controler = 9;
            }
            else {
                this.stat.controler = 8;
            }
            this.stat.changed = true;
        }
        this.lpoz = this.label.length();
        this.centerLabel();
        this.setCursor();
    }
    
    public void start() {
        if (this.nit == null) {
            (this.nit = new Thread(this)).start();
        }
    }
    
    public boolean mouseDown(final Event ev, final int x, final int y) {
        if (this.step != 7) {
            if (this.pol[0].inside(x, y)) {
                final String s = "";
                this.subject = s;
                this.senderName = s;
                this.senderAddress = s;
                this.recipient = s;
                this.vmes.removeAllElements();
                this.vmes.addElement("");
                this.setStep(1);
            }
            else if (this.pol[1].inside(x, y)) {
                this.accept();
                if (this.step < 5) {
                    this.accept();
                    this.setStep(this.step + 1);
                }
            }
            else if (this.pol[2].inside(x, y)) {
                this.accept();
                if (this.step > 0 && this.step < 7) {
                    this.accept();
                    this.setStep(this.step - 1);
                }
            }
            else if (this.pol[3].inside(x, y)) {
                this.accept();
                if (this.step < 6) {
                    if (this.dataOk()) {
                        this.error = false;
                        this.setStep(6);
                    }
                    else {
                        this.error = true;
                        this.response.removeAllElements();
                        this.response.addElement("Recipient address format is not valid");
                        this.response.addElement("Click *** here *** to fix it!");
                        this.setStep(8);
                    }
                }
            }
            else if (this.step == 6) {
                if (this.Yes.inside(x - this.skin.area[0].x, y - this.skin.area[0].y)) {
                    String messageString = "&str0" + "=" + URLEncoder.encode(this.vmes.elementAt(0).trim());
                    String toSend = "";
                    for (int p = 1; p < this.vmes.size(); ++p) {
                        messageString = messageString + "&str" + p + "=" + URLEncoder.encode(this.vmes.elementAt(p).trim()) + "&";
                    }
                    toSend = "recipient=" + URLEncoder.encode(this.recipient.trim()) + "&email=" + URLEncoder.encode(this.senderAddress.trim()) + "&realname=" + URLEncoder.encode(this.senderName.trim()) + "&subject=" + URLEncoder.encode(this.subject.trim()) + messageString;
                    this.sm = new SG1_Demo();
                    this.setStep(7);
                }
                else if (this.No.inside(x - this.skin.area[0].x, y - this.skin.area[0].y)) {
                    this.setStep(0);
                }
            }
            else if (this.step == 8 && this.error) {
                this.setStep(1);
            }
        }
        return true;
    }
    
    public String insertChar(final String st, final char ch, final int index) {
        final StringBuffer sb = new StringBuffer(st);
        sb.insert(index, ch);
        return sb.toString();
    }
    
    public boolean dataOk() {
        final StringTokenizer st = new StringTokenizer(this.recipient, "@");
        return st.countTokens() > 1;
    }
    
    public void run() {
        for (int i = 0; i < this.animation.numberOfImages; ++i) {
            this.animation.getImage(i, this.b);
            this.buffer[i].drawImage(this.present, 0, 0, this);
            this.g.setColor(Color.black);
            this.g.fillRect(20, this.h - this.h / 2, this.w - 40, 20);
            this.g.setColor(new Color(255 - this.bgcolor.getRed(), 255 - this.bgcolor.getGreen(), 255 - this.bgcolor.getBlue()));
            this.g.drawString("Preparing background animation, please wait!", 20, this.h / 2 - 5);
            this.g.setColor(Color.white);
            this.g.fillRect(20, this.h / 2, (this.w - 40) / this.animation.numberOfImages + (this.w - 40) / this.animation.numberOfImages * i, 20);
            this.g.draw3DRect(20, this.h - this.h / 2, this.w - 40, 20, true);
        }
        this.g.setColor(this.bgcolor);
        this.g.fillRect(0, 0, this.w, this.h);
        if (this.skin.externalGraphics) {
            this.img[0] = this.getImage(this.url, this.skin.pic0);
            this.img[1] = this.getImage(this.url, this.skin.pic1);
            this.img[2] = this.getImage(this.url, this.skin.pic2);
            this.img[3] = this.getImage(this.url, this.skin.pic3);
            this.img[4] = this.getImage(this.url, this.skin.pic4);
            (this.tracker = new MediaTracker(this)).addImage(this.img[0], 0);
            this.tracker.addImage(this.img[1], 0);
            this.tracker.addImage(this.img[2], 0);
            this.tracker.addImage(this.img[3], 0);
            this.tracker.addImage(this.img[4], 0);
            this.b.setColor(Color.white);
            while (!this.tracker.checkAll(true)) {
                for (int i = 0; i < this.animation.numberOfImages; ++i) {
                    this.b.drawImage(this.image[i], 0, 0, this);
                    this.b.drawString("Loading images", 50, 50);
                    this.b.drawString("Please wait", 50, 70);
                    this.b.drawRect(0, 0, this.w, this.h);
                    this.g.drawImage(this.present, this.skin.area[0].x, this.skin.area[0].y, this);
                    try {
                        Thread.sleep(30L);
                    }
                    catch (InterruptedException ex) {}
                }
            }
        }
        else {
            Image pic = this.createImage(this.w, this.h);
            Graphics buffy = pic.getGraphics();
            int x = 0;
            int t = 0;
            do {
                if (t == 1 || t == 2) {
                    x = 2;
                }
                else if (t == 3 || t == 4) {
                    x = 5;
                }
                if (t == 0) {
                    pic = this.createImage(this.w, this.h);
                }
                else {
                    pic = this.createImage(this.skin.area[x].width, this.skin.area[x].height);
                }
                buffy = pic.getGraphics();
                this.skin.getImage(buffy, t);
                this.img[t] = pic;
            } while (++t < 5);
        }
        final FontMetrics ff = this.getFontMetrics(new Font("Arial", 1, 25));
        this.add(this.butt1 = new SG1_Button(this.img[1], this.img[2], "New", this.skin.pb1, this.skin.buttonFont, this.skin.tfc, this.buttonsound));
        this.butt1.reshape(this.skin.area[2].x, this.skin.area[2].y, this.skin.area[2].width, this.skin.area[2].height);
        this.add(this.butt2 = new SG1_Button(this.img[1], this.img[2], "Forward", this.skin.pb2, this.skin.buttonFont, this.skin.tfc, this.buttonsound));
        this.butt2.reshape(this.skin.area[3].x, this.skin.area[3].y, this.skin.area[3].width, this.skin.area[3].height);
        this.add(this.butt3 = new SG1_Button(this.img[1], this.img[2], "Back", this.skin.pb3, this.skin.buttonFont, this.skin.tfc, this.buttonsound));
        this.butt3.reshape(this.skin.area[4].x, this.skin.area[4].y, this.skin.area[4].width, this.skin.area[4].height);
        this.add(this.butt4 = new SG1_Button(this.img[3], this.img[4], "Send", this.skin.pb4, this.skin.buttonFont, this.skin.tfc, this.buttonsound));
        this.butt4.reshape(this.skin.area[5].x, this.skin.area[5].y, this.skin.area[5].width, this.skin.area[5].height);
        this.repaint();
        this.add(this.stat = new SG1_Status(this.skin.sbc, this.skin.sfc, this.skin.area[1].width, this.skin.area[1].height, this.typesound));
        this.stat.reshape(this.skin.area[1].x, this.skin.area[1].y, this.skin.area[1].width, this.skin.area[1].height);
        this.setStep(0);
        while (true) {
            if (this.step == 7) {
                if (this.sm == null) {
                    continue;
                }
                while (this.sm.running) {
                    int rnx;
                    int rny = rnx = 0;
                    this.b.setColor(new Color(0, 0, 100));
                    this.b.fillRect(0, 0, this.screen.width, this.screen.height);
                    this.b.setColor(new Color(20, 100, 200));
                    int z = 0;
                    do {
                        rnx = (int)(Math.random() * (this.screen.width + 50) - 50.0);
                        rny = (int)(Math.random() * (this.screen.height + 50) - 50.0);
                        this.b.drawRect(rnx, rny, this.screen.width * 2, this.screen.height * 2);
                    } while (++z < 30);
                    this.b.setColor(new Color(255, 255, 255));
                    this.b.drawString("Sending mail...", (this.screen.width - 95) / 2, (this.screen.height - 31) / 2 + 17);
                    this.g.drawImage(this.present, this.skin.area[0].x, this.skin.area[0].y, this);
                    try {
                        Thread.sleep(80L);
                    }
                    catch (InterruptedException ex2) {}
                }
                final StringTokenizer rst = new StringTokenizer(this.sm.resp, "*");
                this.response.removeAllElements();
                this.response.addElement("");
                while (rst.hasMoreTokens()) {
                    this.response.addElement(rst.nextToken());
                }
                this.sm = null;
                this.setStep(8);
            }
            else {
                for (int j = 0; j < this.animation.numberOfImages; ++j) {
                    this.b.drawImage(this.image[j], 0, 0, this);
                    if (this.step == 0) {
                        this.b.drawString("StarGate", (this.screen.width - 119) / 2, (this.screen.height - 31) / 2 + 25);
                    }
                    else if (this.step > 0 && this.step < 5) {
                        if (this.blink.c > 0) {
                            this.b.setColor(this.cursorCol);
                            this.b.fill3DRect(this.cx, this.cy, this.cw, this.ch, true);
                        }
                        this.b.setColor(this.fgcolor);
                        this.b.drawString(this.label, this.labelPosition.x, this.labelPosition.y);
                        this.b.drawString(this.prelabel, this.prelabelPosition.x, this.prelabelPosition.y);
                    }
                    else if (this.step == 5) {
                        if (this.blink.c > 0) {
                            this.b.setColor(this.cursorCol);
                            this.b.fill3DRect(this.cx, this.cy, this.cw, this.ch, true);
                        }
                        this.b.setColor(this.fgcolor);
                        for (int a = 0; a < this.vmes.size(); ++a) {
                            this.b.drawString(this.vmes.elementAt(a), this.absx, this.absy + (a + 1) * this.ch);
                        }
                    }
                    else if (this.step == 6) {
                        this.b.setFont(new Font("Arial", 0, 18));
                        this.b.drawString("Send message ?", (this.screen.width - 125) / 2, 25);
                        this.b.setFont(this.font);
                        this.b.drawString("To: " + this.recipient, 10, this.uy);
                        this.b.drawString("From: " + this.senderName + "   [ " + this.senderAddress + " ]", 10, this.uy + this.ch + 3);
                        this.b.drawRect(this.Yes.x, this.Yes.y, 30, 20);
                        this.b.drawRect(this.No.x, this.No.y, 30, 20);
                        this.b.setFont(new Font("Arial", 0, 16));
                        this.b.drawString("Yes", this.Yes.x + 3, this.Yes.y + 17);
                        this.b.drawString("No", this.No.x + 6, this.No.y + 17);
                    }
                    else if (this.step == 8) {
                        for (int a = 0; a < this.response.size(); ++a) {
                            this.label = this.response.elementAt(a);
                            this.centerLabel();
                            this.b.drawString(this.label, this.labelPosition.x, 20 + a * this.ch);
                        }
                    }
                    this.g.drawImage(this.present, this.skin.area[0].x, this.skin.area[0].y, this);
                    try {
                        Thread.sleep(50L);
                    }
                    catch (InterruptedException ex3) {}
                }
            }
        }
    }
    
    public void init() {
        this.setLayout(null);
        if (this.getParameter("cgiURL") != null) {
            try {
                this.cgiUrl = new URL(this.getParameter("cgiURL"));
            }
            catch (MalformedURLException e) {
                try {
                    this.cgiUrl = new URL("http://bigor@virtualave.net/cgi-bin/formmail.cgi");
                }
                catch (MalformedURLException ex) {}
            }
        }
        else {
            try {
                this.cgiUrl = new URL(this.getDocumentBase(), "sgtmail.cgi");
            }
            catch (MalformedURLException e) {
                this.cgiUrl = this.getDocumentBase();
            }
        }
        this.skin = new SG1_Skin(this.bgcolor, this.url);
        this.w = this.skin.w;
        this.h = this.skin.h;
        final String hoster = this.getDocumentBase().getHost().toString().trim();
        this.bgcolor = this.getColor(this.getParameter("bgcolor"));
        this.typesound = this.getAudioClip(this.getDocumentBase(), this.getParameter("sound1"));
        this.accsound = this.getAudioClip(this.getDocumentBase(), this.getParameter("sound2"));
        this.buttonsound = this.getAudioClip(this.getDocumentBase(), this.getParameter("sound3"));
        final String s = " ";
        this.label = s;
        this.prelabel = s;
        this.response = new Vector();
        (this.vmes = new Vector()).addElement(new String(""));
        final String s2 = "";
        this.subject = s2;
        this.senderName = s2;
        this.senderAddress = s2;
        this.recipient = s2;
        this.screen = new Dimension(this.skin.area[0].width, this.skin.area[0].height);
        this.animation = new SG1_Animation(this.screen.width, this.screen.height);
        this.blink = new SG1_Blink();
        int z = 0;
        do {
            this.pol[z] = this.skin.area[z + 2];
        } while (++z < 4);
        this.url = this.getDocumentBase();
        this.g = this.getGraphics();
        this.setBackground(this.bgcolor);
        this.font = this.skin.textFont;
        this.fgcolor = this.skin.fgcolor;
        this.cursorCol = this.skin.cursorCol;
        this.info = this.getFontMetrics(this.font);
        this.ch = this.info.getHeight();
        this.absy = 0;
        this.absx = 5;
        this.image = new Image[this.animation.numberOfImages];
        this.buffer = new Graphics[this.animation.numberOfImages];
        this.present = this.createImage(this.screen.width, this.screen.height);
        this.b = this.present.getGraphics();
        for (int i = 0; i < this.animation.numberOfImages; ++i) {
            this.image[i] = this.createImage(this.animation.w, this.animation.h);
            this.buffer[i] = this.image[i].getGraphics();
        }
    }
    
    public void setCursor() {
        this.cx = this.absx;
        if (this.step > 0 && this.step < 5) {
            this.cy = this.labelPosition.y - this.info.getHeight() + 3;
            this.cx = this.labelPosition.x + this.info.stringWidth(this.label.substring(0, this.lpoz));
            if (this.lpoz < this.label.length()) {
                this.cw = this.info.stringWidth(this.label.substring(this.lpoz, this.lpoz + 1));
            }
            else {
                this.cw = 5;
            }
        }
        else if (this.step == 5) {
            final String stri = this.vmes.elementAt(this.vmline);
            this.cy = this.absy + this.vmline * this.ch + 3;
            this.cx = this.absx + this.info.stringWidth(stri.substring(0, this.vmpoz));
            this.getAppletContext().showStatus("cx: " + this.cx);
            if (this.vmpoz < stri.length()) {
                this.cw = this.info.stringWidth(stri.substring(this.vmpoz, this.vmpoz + 1));
            }
            else {
                this.cw = 5;
            }
        }
        if (this.cx < 5) {
            while (this.cx < 5) {
                this.moveLine("right");
            }
            if (this.absx > 5) {
                final String stri = this.vmes.elementAt(this.vmline);
                this.absx = 5;
                this.cx = this.absx + this.info.stringWidth(stri.substring(0, this.vmpoz));
            }
        }
        else if (this.cx > this.screen.width - 10) {
            while (this.cx > this.screen.width - 10) {
                this.moveLine("left");
            }
        }
        if (this.cy > this.screen.height - this.ch) {
            this.absy -= this.ch;
            this.cy -= this.ch;
        }
        if (this.cy < 0) {
            this.absy += this.ch;
            this.cy += this.ch;
        }
    }
    
    public boolean mouseMove(final Event ev, final int x, final int y) {
        this.getAppletContext().showStatus("x: " + x + "   y: " + y);
        return true;
    }
}
