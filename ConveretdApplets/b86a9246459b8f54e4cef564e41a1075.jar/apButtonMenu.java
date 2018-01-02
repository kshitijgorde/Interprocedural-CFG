import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Font;
import java.util.StringTokenizer;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class apButtonMenu extends Applet implements Runnable
{
    Thread theThread;
    int O1;
    int Ol;
    Image offimage;
    Graphics ll0;
    int ol;
    int lll;
    int itmp;
    int itmp2;
    int ll;
    int clck;
    String[] text;
    String[] target;
    String l01;
    URL[] link;
    int[] l11;
    int[] ll1;
    int[] l1l;
    int[] l1;
    int[] l1o;
    int rb;
    int gb;
    int bb;
    int delay;
    boolean bHoriz;
    int lOO;
    int l0O;
    int lO0;
    int l00;
    int lO1;
    int lOl;
    int lol;
    int l0l;
    int l00l;
    String sFontFace;
    String align;
    String stat;
    int fSize;
    int fStyle;
    String sMenuItems;
    String Ol7;
    String ho;
    boolean oo;
    
    public apButtonMenu() {
        this.clck = -1;
        this.delay = 30;
        this.bHoriz = false;
        this.lOO = 13303482;
        this.l0O = 14352074;
        this.lO0 = 16384;
        this.l00 = 47872;
        this.l00l = 1;
        this.sFontFace = "";
        this.align = "left";
        this.stat = "link";
        this.fSize = 12;
        this.fStyle = 0;
        this.Ol7 = "Gthf\"dqrofvv!cw!yzx0dq{fpo1dqp";
        this.ho = "q{fpo1";
        this.oo = false;
    }
    
    public int find(final int n, final int n2) {
        if (n2 > this.Ol || n > this.O1) {
            return -1;
        }
        for (int i = 0; i < this.ol; ++i) {
            if (n <= this.l11[i] + this.l1l[i] && n2 <= this.ll1[i] + this.lll) {
                return i;
            }
        }
        return -1;
    }
    
    protected String hO(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)(charArray[i] - i % 3 - 1);
        }
        return new String(charArray);
    }
    
    public void init() {
        this.l01 = this.getParameter("key");
        if (this.l01 != null && this.l01.length() >= 6) {
            this.ho = this.l01.toLowerCase();
        }
        final String lowerCase = this.getDocumentBase().getHost().toLowerCase();
        if (lowerCase == null || lowerCase.equalsIgnoreCase("localhost") || lowerCase.equalsIgnoreCase("127.0.0.1") || lowerCase.equalsIgnoreCase("") || lowerCase.indexOf(this.hO(this.ho.substring(0, 6))) != -1) {
            this.oo = true;
        }
        this.O1 = this.size().width;
        this.Ol = this.size().height;
        this.l01 = this.getParameter("buttonType");
        if (this.l01 != null) {
            this.l00l = Integer.parseInt(this.l01);
        }
        this.l01 = this.getParameter("backColor");
        if (this.l01 != null) {
            this.lOO = Integer.parseInt(this.l01, 16);
            this.l0O = this.lOO;
        }
        this.l01 = this.getParameter("fontColor");
        if (this.l01 != null) {
            this.lO0 = Integer.parseInt(this.l01, 16);
            this.l00 = this.lO0;
        }
        this.l01 = this.getParameter("backHighColor");
        if (this.l01 != null) {
            this.l0O = Integer.parseInt(this.l01, 16);
        }
        this.l01 = this.getParameter("fontHighColor");
        if (this.l01 != null) {
            this.l00 = Integer.parseInt(this.l01, 16);
        }
        this.l01 = this.getParameter("isHorizontal");
        if (this.l01 != null && this.l01.equalsIgnoreCase("true")) {
            this.bHoriz = true;
        }
        this.l01 = this.getParameter("alignText");
        if (this.l01 != null) {
            this.align = this.l01;
        }
        this.l01 = this.getParameter("status");
        if (this.l01 != null) {
            this.stat = this.l01;
        }
        this.l01 = this.getParameter("font");
        if (this.l01 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.l01, ",");
            this.sFontFace = stringTokenizer.nextToken();
            this.fSize = Integer.parseInt(stringTokenizer.nextToken());
            this.fStyle = Integer.parseInt(stringTokenizer.nextToken());
        }
        this.l01 = this.getParameter("menuItems");
        this.l01 = this.l01.substring(0, this.l01.lastIndexOf("}"));
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.l01, "}");
        this.ol = stringTokenizer2.countTokens();
        if (!this.bHoriz) {
            this.lll = this.Ol / this.ol;
        }
        else {
            this.lll = this.Ol;
        }
        this.l1o = new int[this.ol];
        this.l11 = new int[this.ol];
        this.ll1 = new int[this.ol];
        this.l1l = new int[this.ol];
        this.l1 = new int[this.ol];
        this.text = new String[this.ol];
        this.link = new URL[this.ol];
        this.target = new String[this.ol];
        for (int i = 0; i < this.ol; ++i) {
            this.l01 = stringTokenizer2.nextToken();
            this.l01 = this.l01.substring(this.l01.indexOf("{") + 1);
            final StringTokenizer stringTokenizer3 = new StringTokenizer(this.l01, ",");
            this.text[i] = new String(stringTokenizer3.nextToken());
            try {
                this.link[i] = new URL(this.getCodeBase(), stringTokenizer3.nextToken());
            }
            catch (Exception ex) {}
            this.target[i] = new String(stringTokenizer3.nextToken());
        }
        this.rb = (this.l0O & 0xFF0000) >> 16;
        this.gb = (this.l0O & 0xFF00) >> 8;
        this.bb = (this.l0O & 0xFF);
        this.l0l = (this.llo(this.rb * 2 / 3) << 16) + (this.llo(this.gb * 2 / 3) << 8) + this.llo(this.bb * 2 / 3);
        this.lol = (this.llo((255 - this.rb) / 2 + this.rb) << 16) + (this.llo((255 - this.gb) / 2 + this.gb) << 8) + this.llo((255 - this.bb) / 2 + this.bb);
        this.rb = (this.lOO & 0xFF0000) >> 16;
        this.gb = (this.lOO & 0xFF00) >> 8;
        this.bb = (this.lOO & 0xFF);
        this.lOl = (this.llo(this.rb * 2 / 3) << 16) + (this.llo(this.gb * 2 / 3) << 8) + this.llo(this.bb * 2 / 3);
        this.lO1 = (this.llo((255 - this.rb) / 2 + this.rb) << 16) + (this.llo((255 - this.gb) / 2 + this.gb) << 8) + this.llo((255 - this.bb) / 2 + this.bb);
        this.offimage = this.createImage(this.O1, this.Ol);
        (this.ll0 = this.offimage.getGraphics()).setFont(new Font(this.sFontFace, this.fStyle, this.fSize));
        this.ll = this.lll / 2 + this.fSize / 2 - 2;
        this.ll0.setColor(new Color(this.lOO));
        this.ll0.fillRect(0, 0, this.O1, this.Ol);
        this.itmp = 0;
        if (this.bHoriz) {
            for (int j = 0; j < this.ol; ++j) {
                this.l1l[j] = this.ll0.getFontMetrics().stringWidth(this.text[j]);
                this.itmp += this.l1l[j];
            }
            this.l11[0] = 0;
            for (int k = 0; k < this.ol; ++k) {
                this.l1[k] = (this.O1 - this.itmp) / this.ol / 2;
                this.l1l[k] += this.l1[k] * 2;
                if (k > 0) {
                    this.l11[k] = this.l11[k - 1] + this.l1l[k - 1];
                }
                this.ll1[k] = 0;
            }
            this.l1l[this.ol - 1] = this.O1 - this.l11[this.ol - 1];
        }
        else {
            for (int l = 0; l < this.ol; ++l) {
                this.l11[l] = 0;
                this.ll1[l] = l * this.lll;
                this.l1l[l] = this.O1;
                if (this.align.equalsIgnoreCase("center")) {
                    this.l1[l] = this.O1 / 2 - this.ll0.getFontMetrics().stringWidth(this.text[l]) / 2;
                }
                if (this.align.equalsIgnoreCase("left")) {
                    this.l1[l] = this.fSize / 2;
                }
                if (this.align.equalsIgnoreCase("right")) {
                    this.l1[l] = this.O1 - this.ll0.getFontMetrics().stringWidth(this.text[l]) - this.fSize / 2;
                }
            }
        }
        Container parent;
        for (parent = this; parent != null && !(parent instanceof Frame); parent = parent.getParent()) {}
        ((Frame)parent).setCursor(12);
    }
    
    public void l1O(final int n) {
        for (int i = 0; i < this.ol; ++i) {
            if (i != n) {
                this.l1o[i] = 0;
            }
        }
    }
    
    public void llO(final int clck) {
        Label_0932: {
            switch (this.l1o[clck]) {
                case 0: {
                    this.ll0.setColor(new Color(this.lOO));
                    this.ll0.fillRect(this.l11[clck], this.ll1[clck], this.l1l[clck], this.lll);
                    this.ll0.setColor(new Color(this.lO0));
                    this.ll0.drawString(this.text[clck], this.l11[clck] + this.l1[clck], this.ll1[clck] + this.ll);
                    switch (this.l00l) {
                        case 2:
                        case 5: {
                            this.lo1(clck, 0, 0);
                            break Label_0932;
                        }
                        case 4: {
                            this.lo1(clck, 2, 0);
                            break Label_0932;
                        }
                    }
                    break;
                }
                case 2: {
                    this.ll0.setColor(new Color(this.l0O));
                    this.ll0.fillRect(this.l11[clck], this.ll1[clck], this.l1l[clck], this.lll);
                    this.ll0.setColor(new Color(this.l00));
                    this.ll0.drawString(this.text[clck], this.l11[clck] + this.l1[clck], this.ll1[clck] + this.ll);
                    switch (this.l00l) {
                        case 1:
                        case 2: {
                            this.lo1(clck, 0, 1);
                            break Label_0932;
                        }
                        case 3:
                        case 4:
                        case 5: {
                            this.lo1(clck, 2, 1);
                            break Label_0932;
                        }
                    }
                    break;
                }
                case 1: {
                    this.ll0.setColor(new Color(this.l0O));
                    this.ll0.fillRect(this.l11[clck], this.ll1[clck], this.l1l[clck], this.lll);
                    this.ll0.setColor(new Color(this.l00));
                    this.ll0.drawString(this.text[clck], this.l11[clck] + this.l1[clck], this.ll1[clck] + this.ll);
                    switch (this.l00l) {
                        case 1:
                        case 2: {
                            this.lo1(clck, 0, 1);
                            break;
                        }
                        case 3:
                        case 4:
                        case 5: {
                            this.lo1(clck, 2, 1);
                            break;
                        }
                    }
                    this.l1O(clck);
                    this.l1o[clck] = 2;
                    if (!this.oo) {
                        this.showStatus(this.hO(this.Ol7));
                        break;
                    }
                    if (this.stat.equalsIgnoreCase("link")) {
                        this.showStatus(this.link[clck].toString());
                        break;
                    }
                    this.showStatus(this.text[clck].trim());
                    break;
                }
                case 3: {
                    this.ll0.setColor(new Color(this.l0O));
                    this.ll0.fillRect(this.l11[clck], this.ll1[clck], this.l1l[clck], this.lll);
                    this.ll0.setColor(new Color(this.l00));
                    this.ll0.drawString(this.text[clck], this.l11[clck] + this.l1[clck], this.ll1[clck] + this.ll + 1);
                    switch (this.l00l) {
                        case 1:
                        case 2: {
                            this.lo1(clck, 1, 1);
                            break;
                        }
                        case 3:
                        case 4:
                        case 5: {
                            this.lo1(clck, 3, 1);
                            break;
                        }
                    }
                    this.l1O(clck);
                    this.l1o[clck] = 4;
                    this.clck = clck;
                    break;
                }
                case 4: {
                    this.ll0.setColor(new Color(this.l0O));
                    this.ll0.fillRect(this.l11[clck], this.ll1[clck], this.l1l[clck], this.lll);
                    this.ll0.setColor(new Color(this.l00));
                    this.ll0.drawString(this.text[clck], this.l11[clck] + this.l1[clck], this.ll1[clck] + this.ll + 1);
                    switch (this.l00l) {
                        case 1:
                        case 2: {
                            this.lo1(clck, 1, 1);
                            break Label_0932;
                        }
                        case 3:
                        case 4:
                        case 5: {
                            this.lo1(clck, 3, 1);
                            break Label_0932;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    int llo(final int n) {
        if (n > 255) {
            return 255;
        }
        if (n < 0) {
            return 0;
        }
        return n;
    }
    
    public void lo1(final int n, final int n2, final int n3) {
        int n4 = this.lO1;
        int n5 = this.lOl;
        if (n3 != 0) {
            n4 = this.lol;
            n5 = this.l0l;
        }
        switch (n2) {
            default: {
                this.ll0.setColor(new Color(n4));
                this.ll0.drawLine(this.l11[n], this.ll1[n], this.l11[n] + this.l1l[n], this.ll1[n]);
                this.ll0.drawLine(this.l11[n], this.ll1[n], this.l11[n], this.ll1[n] + this.lll);
                this.ll0.setColor(new Color(n5));
                this.ll0.drawLine(this.l11[n] + this.l1l[n] - 1, this.ll1[n] + this.lll - 1, this.l11[n] + this.l1l[n] - 1, this.ll1[n]);
                this.ll0.drawLine(this.l11[n] + this.l1l[n] - 1, this.ll1[n] + this.lll - 1, this.l11[n] + 1, this.ll1[n] + this.lll - 1);
                break;
            }
            case 1: {
                this.ll0.setColor(new Color(n5));
                this.ll0.drawLine(this.l11[n], this.ll1[n], this.l11[n] + this.l1l[n], this.ll1[n]);
                this.ll0.drawLine(this.l11[n], this.ll1[n], this.l11[n], this.ll1[n] + this.lll);
                this.ll0.setColor(new Color(n4));
                this.ll0.drawLine(this.l11[n] + this.l1l[n] - 1, this.ll1[n] + this.lll - 1, this.l11[n] + this.l1l[n] - 1, this.ll1[n]);
                this.ll0.drawLine(this.l11[n] + this.l1l[n] - 1, this.ll1[n] + this.lll - 1, this.l11[n] + 1, this.ll1[n] + this.lll - 1);
                break;
            }
            case 2: {
                this.ll0.setColor(new Color(n4));
                this.ll0.drawLine(this.l11[n] + 1, this.ll1[n] + 1, this.l11[n] + this.l1l[n] - 2, this.ll1[n] + 1);
                this.ll0.drawLine(this.l11[n] + 1, this.ll1[n] + 1, this.l11[n] + 1, this.ll1[n] + this.lll - 2);
                this.ll0.setColor(new Color(0));
                this.ll0.drawLine(this.l11[n] + this.l1l[n] - 1, this.ll1[n] + this.lll - 1, this.l11[n] + this.l1l[n] - 1, this.ll1[n]);
                this.ll0.drawLine(this.l11[n] + this.l1l[n] - 1, this.ll1[n] + this.lll - 1, this.l11[n] + 1, this.ll1[n] + this.lll - 1);
                this.ll0.setColor(new Color(n5));
                this.ll0.drawLine(this.l11[n] + this.l1l[n] - 2, this.ll1[n] + this.lll - 2, this.l11[n] + this.l1l[n] - 2, this.ll1[n] + 1);
                this.ll0.drawLine(this.l11[n] + this.l1l[n] - 2, this.ll1[n] + this.lll - 2, this.l11[n] + 2, this.ll1[n] + this.lll - 2);
                break;
            }
            case 3: {
                this.ll0.setColor(new Color(n5));
                this.ll0.drawLine(this.l11[n] + 1, this.ll1[n] + 1, this.l11[n] + this.l1l[n] - 2, this.ll1[n] + 1);
                this.ll0.drawLine(this.l11[n] + 1, this.ll1[n] + 1, this.l11[n] + 1, this.ll1[n] + this.lll - 2);
                this.ll0.setColor(new Color(0));
                this.ll0.drawLine(this.l11[n], this.ll1[n], this.l11[n] + this.l1l[n], this.ll1[n]);
                this.ll0.drawLine(this.l11[n], this.ll1[n], this.l11[n], this.ll1[n] + this.lll);
                this.ll0.setColor(new Color(n4));
                this.ll0.drawLine(this.l11[n] + this.l1l[n] - 2, this.ll1[n] + this.lll - 2, this.l11[n] + this.l1l[n] - 2, this.ll1[n] + 1);
                this.ll0.setColor(new Color(n4));
                this.ll0.drawLine(this.l11[n] + this.l1l[n] - 1, this.ll1[n] + this.lll - 1, this.l11[n] + this.l1l[n] - 1, this.ll1[n]);
                this.ll0.drawLine(this.l11[n] + this.l1l[n] - 1, this.ll1[n] + this.lll - 1, this.l11[n] + 1, this.ll1[n] + this.lll - 1);
                break;
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int find = this.find(n, n2);
        if (find == -1) {
            return true;
        }
        this.l1o[find] = 3;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.l1O(-1);
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final int find = this.find(n, n2);
        if (find == -1) {
            this.l1O(-1);
            return true;
        }
        if (this.l1o[find] == 0) {
            this.l1O(find);
            this.l1o[find] = 1;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offimage, 0, 0, this);
    }
    
    public void run() {
        while (this.theThread != null) {
            for (int i = 0; i < this.ol; ++i) {
                this.llO(i);
            }
            this.repaint();
            if (this.clck != -1) {
                this.getAppletContext().showDocument(this.link[this.clck], this.target[this.clck]);
                this.clck = -1;
            }
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {
                this.theThread = null;
            }
        }
    }
    
    public void start() {
        this.theThread = null;
        (this.theThread = new Thread(this)).start();
    }
    
    public void stop() {
        this.theThread = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
