import java.awt.Event;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Color;
import netscape.javascript.JSObject;
import java.applet.AudioClip;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class apImageMenu extends Applet implements Runnable
{
    Thread II0;
    int I1;
    int Il;
    Image offimage;
    Image II;
    Image[][] Ill;
    Graphics lIl;
    int l1I;
    int lI;
    int l10;
    int lII;
    int II1;
    String[] lI1;
    String[] Il1;
    String I11;
    URL[] I1l;
    int[] l11;
    int[] l1l;
    int[] ll1;
    int[] lll;
    int[] llI;
    int[] I1I;
    AudioClip lI0;
    AudioClip I10;
    int delay;
    boolean ll0;
    int cBack;
    int cSel;
    int cFont;
    int cFSel;
    String sFontFace;
    String align;
    String stat;
    int Il0;
    int fStyle;
    String sMenuItems;
    String Ol7;
    String ho;
    boolean oo;
    
    public apImageMenu() {
        this.II1 = -1;
        this.delay = 30;
        this.ll0 = false;
        this.cBack = 13303482;
        this.cSel = 1404929;
        this.cFont = 16384;
        this.cFSel = 16777215;
        this.sFontFace = "";
        this.align = "left";
        this.stat = "text";
        this.Il0 = 14;
        this.fStyle = 0;
        this.Ol7 = "Gthf\"dqrofvv!cw!yzx0dq{fpo1dqp";
        this.ho = "q{fpo1";
        this.oo = false;
    }
    
    public int I0(final int n, final int n2) {
        if (n2 > this.Il || n > this.I1) {
            return -1;
        }
        for (int i = 0; i < this.l1I; ++i) {
            if (n <= this.l11[i] + this.ll1[i] && n2 <= this.l1l[i] + this.lI) {
                return i;
            }
        }
        return -1;
    }
    
    public void III(String s) {
        s = this.getParameter(s);
        if (s != null) {
            if (s.startsWith("javascript:")) {
                s = s.substring(11);
            }
            try {
                JSObject.getWindow((Applet)this).eval(s);
            }
            catch (Exception ex) {}
        }
    }
    
    public void IIl(final int n, final int n2) {
        int n3 = this.cFont;
        int n4 = this.cBack;
        if (n2 > 0) {
            n3 = this.cFSel;
            n4 = this.cSel;
        }
        if (this.II == null) {
            this.lIl.setColor(new Color(n4));
            this.lIl.fillRect(this.l11[n], this.l1l[n], this.ll1[n], this.lI);
        }
        if (this.Ill[n][n2] != null) {
            this.lIl.drawImage(this.Ill[n][n2], this.l11[n] + this.llI[n], this.l1l[n] + (this.lI - this.Ill[n][n2].getHeight(this)) / 2, this);
        }
        this.lIl.setColor(new Color(n3));
        if (n2 != 2) {
            this.lIl.drawString(this.lI1[n], this.l11[n] + this.lll[n], this.l1l[n] + this.lII);
        }
        else {
            this.lIl.drawString(this.lI1[n], this.l11[n] + this.lll[n], this.l1l[n] + this.lII + 1);
        }
    }
    
    public Image IlI(final String s) {
        if (s.equals("_")) {
            return null;
        }
        final Image image = this.getImage(this.getDocumentBase(), s);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            return null;
        }
        return image;
    }
    
    protected String hO(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)(charArray[i] - i % 3 - 1);
        }
        return new String(charArray);
    }
    
    public void init() {
        this.I11 = this.getParameter("key");
        if (this.I11 != null && this.I11.length() >= 6) {
            this.ho = this.I11.toLowerCase();
        }
        final String lowerCase = this.getDocumentBase().getHost().toLowerCase();
        if (lowerCase == null || lowerCase.equalsIgnoreCase("localhost") || lowerCase.equalsIgnoreCase("127.0.0.1") || lowerCase.equalsIgnoreCase("") || lowerCase.indexOf(this.hO(this.ho.substring(0, 6))) != -1) {
            this.oo = true;
        }
        this.I1 = this.size().width;
        this.Il = this.size().height;
        this.I11 = this.getParameter("backColor");
        if (this.I11 != null) {
            this.cBack = Integer.parseInt(this.I11, 16);
        }
        this.I11 = this.getParameter("backHighColor");
        if (this.I11 != null) {
            this.cSel = Integer.parseInt(this.I11, 16);
        }
        this.I11 = this.getParameter("fontColor");
        if (this.I11 != null) {
            this.cFont = Integer.parseInt(this.I11, 16);
        }
        this.I11 = this.getParameter("fontHighColor");
        if (this.I11 != null) {
            this.cFSel = Integer.parseInt(this.I11, 16);
        }
        this.I11 = this.getParameter("isHorizontal");
        if (this.I11 != null && this.I11.equalsIgnoreCase("true")) {
            this.ll0 = true;
        }
        this.I11 = this.getParameter("alignText");
        if (this.I11 != null) {
            this.align = this.I11;
        }
        this.I11 = this.getParameter("status");
        if (this.I11 != null) {
            this.stat = this.I11;
        }
        this.I11 = this.getParameter("overSound");
        if (this.I11 != null) {
            this.lI0 = this.getAudioClip(this.getCodeBase(), this.I11);
        }
        this.I11 = this.getParameter("clickSound");
        if (this.I11 != null) {
            this.I10 = this.getAudioClip(this.getCodeBase(), this.I11);
        }
        this.I11 = this.getParameter("font");
        if (this.I11 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.I11, ",");
            this.sFontFace = stringTokenizer.nextToken();
            this.Il0 = Integer.parseInt(stringTokenizer.nextToken());
            this.fStyle = Integer.parseInt(stringTokenizer.nextToken());
        }
        this.I11 = this.getParameter("menuItems");
        this.I11 = this.I11.substring(0, this.I11.lastIndexOf("}"));
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.I11, "}");
        this.l1I = stringTokenizer2.countTokens();
        if (!this.ll0) {
            this.lI = this.Il / this.l1I;
        }
        else {
            this.lI = this.Il;
        }
        this.I1I = new int[this.l1I];
        this.l11 = new int[this.l1I];
        this.l1l = new int[this.l1I];
        this.ll1 = new int[this.l1I];
        this.lll = new int[this.l1I];
        this.lI1 = new String[this.l1I];
        this.I1l = new URL[this.l1I];
        this.Il1 = new String[this.l1I];
        this.Ill = new Image[this.l1I][3];
        this.llI = new int[this.l1I];
        this.I11 = this.getParameter("defaultImage");
        if (this.I11 != null) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(this.I11, ",");
            this.l10 = 0;
            while (stringTokenizer3.hasMoreTokens()) {
                final Image ilI = this.IlI(stringTokenizer3.nextToken());
                for (int i = 0; i < this.l1I; ++i) {
                    this.Ill[i][this.l10] = ilI;
                }
                ++this.l10;
            }
        }
        for (int j = 0; j < this.l1I; ++j) {
            this.I11 = stringTokenizer2.nextToken();
            this.I11 = this.I11.substring(this.I11.indexOf("{") + 1);
            final StringTokenizer stringTokenizer4 = new StringTokenizer(this.I11, ",");
            this.lI1[j] = new String(stringTokenizer4.nextToken());
            if (this.lI1[j].equals("_")) {
                this.lI1[j] = "";
            }
            this.I11 = stringTokenizer4.nextToken();
            if (!this.I11.startsWith("javascript:")) {
                try {
                    this.I1l[j] = new URL(this.getCodeBase(), this.I11);
                }
                catch (Exception ex) {}
                this.Il1[j] = new String(stringTokenizer4.nextToken());
            }
            else {
                this.Il1[j] = new String(this.I11);
                this.I11 = stringTokenizer4.nextToken();
            }
            this.l10 = 0;
            while (stringTokenizer4.hasMoreTokens()) {
                final Image ilI2 = this.IlI(stringTokenizer4.nextToken());
                if (ilI2 != null) {
                    this.Ill[j][this.l10] = ilI2;
                }
                ++this.l10;
            }
            if (this.Ill[j][2] == null) {
                this.Ill[j][2] = this.Ill[j][1];
            }
        }
        this.offimage = this.createImage(this.I1, this.Il);
        (this.lIl = this.offimage.getGraphics()).setFont(new Font(this.sFontFace, this.fStyle, this.Il0));
        this.lII = this.lI / 2 + this.Il0 / 2 - 2;
        if (this.getParameter("backPic") != null) {
            this.II = this.createImage(this.I1, this.Il);
            final Graphics graphics = this.II.getGraphics();
            final Image ilI3 = this.IlI(this.getParameter("backPic"));
            for (int k = 0; k < this.I1; k += ilI3.getWidth(this)) {
                for (int l = 0; l < this.Il; l += ilI3.getHeight(this)) {
                    graphics.drawImage(ilI3, k, l, this);
                }
            }
            this.lIl.drawImage(this.II, 0, 0, this);
        }
        else {
            this.lIl.setColor(new Color(this.cBack));
            this.lIl.fillRect(0, 0, this.I1, this.Il);
        }
        this.l10 = 0;
        if (this.ll0) {
            for (int n = 0; n < this.l1I; ++n) {
                this.ll1[n] = this.l1(n);
                this.l10 += this.ll1[n];
            }
            this.l11[0] = 0;
            for (int n2 = 0; n2 < this.l1I; ++n2) {
                this.llI[n2] = (this.I1 - this.l10) / this.l1I / 2;
                this.ll1[n2] += this.llI[n2] * 2;
                if (n2 > 0) {
                    this.l11[n2] = this.l11[n2 - 1] + this.ll1[n2 - 1];
                }
                this.l1l[n2] = 0;
                this.lll[n2] = (this.ll1[n2] - this.lIl.getFontMetrics().stringWidth(this.lI1[n2])) / 2;
            }
            this.ll1[this.l1I - 1] = this.I1 - this.l11[this.l1I - 1];
        }
        else {
            for (int n3 = 0; n3 < this.l1I; ++n3) {
                this.l11[n3] = 0;
                this.l1l[n3] = n3 * this.lI;
                this.ll1[n3] = this.I1;
                if (this.align.equalsIgnoreCase("center")) {
                    this.lll[n3] = (this.I1 - this.lIl.getFontMetrics().stringWidth(this.lI1[n3])) / 2;
                    this.llI[n3] = (this.I1 - this.l1(n3)) / 2;
                }
                if (this.align.equalsIgnoreCase("left")) {
                    this.lll[n3] = this.Il0 / 2;
                    this.llI[n3] = 0;
                }
                if (this.align.equalsIgnoreCase("right")) {
                    this.lll[n3] = this.I1 - this.lIl.getFontMetrics().stringWidth(this.lI1[n3]) - this.Il0 / 2;
                    this.llI[n3] = this.I1 - this.l1(n3);
                }
            }
        }
        Container parent;
        for (parent = this; parent != null && !(parent instanceof Frame); parent = parent.getParent()) {}
        ((Frame)parent).setCursor(12);
    }
    
    public void l0(final int n) {
        for (int i = 0; i < this.l1I; ++i) {
            if (i != n) {
                this.I1I[i] = 0;
            }
        }
    }
    
    public int l1(final int n) {
        int width = 0;
        for (int i = 0; i < 3; ++i) {
            if (this.Ill[n][i] != null && width < this.Ill[n][i].getWidth(this)) {
                width = this.Ill[n][i].getWidth(this);
            }
        }
        return width;
    }
    
    public void ll(final int ii1) {
        if (this.I1l[ii1] == null && !this.Il1[ii1].startsWith("javascript:") && this.I1I[ii1] != 0) {
            this.showStatus("");
            this.I1I[ii1] = 0;
        }
        switch (this.I1I[ii1]) {
            case 0: {
                this.IIl(ii1, 0);
                break;
            }
            case 2: {
                this.IIl(ii1, 1);
                break;
            }
            case 1: {
                this.IIl(ii1, 1);
                if (this.lI0 != null) {
                    this.lI0.play();
                }
                this.l0(ii1);
                this.I1I[ii1] = 2;
                if (!this.oo) {
                    this.showStatus(this.hO(this.Ol7));
                    break;
                }
                if (this.stat.equalsIgnoreCase("link") && this.I1l[ii1] != null) {
                    this.showStatus(this.I1l[ii1].toString());
                    break;
                }
                this.showStatus(this.lI1[ii1].trim());
                break;
            }
            case 3: {
                this.IIl(ii1, 2);
                if (this.I10 != null) {
                    this.I10.play();
                }
                this.l0(ii1);
                this.I1I[ii1] = 4;
                this.II1 = ii1;
                break;
            }
            case 4: {
                this.IIl(ii1, 2);
                break;
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int i0 = this.I0(n, n2);
        if (i0 == -1) {
            return true;
        }
        this.I1I[i0] = 3;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.l0(-1);
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final int i0 = this.I0(n, n2);
        if (i0 == -1) {
            this.l0(-1);
            return true;
        }
        if (this.I1I[i0] == 0) {
            this.l0(i0);
            this.I1I[i0] = 1;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void run() {
        while (this.II0 != null) {
            if (this.II != null) {
                this.lIl.drawImage(this.II, 0, 0, this);
            }
            for (int i = 0; i < this.l1I; ++i) {
                this.ll(i);
            }
            Thread.yield();
            this.getGraphics().drawImage(this.offimage, 0, 0, this);
            Thread.yield();
            if (this.II1 != -1) {
                if (this.Il1[this.II1] != null) {
                    if (!this.Il1[this.II1].startsWith("javascript:")) {
                        if (this.I1l[this.II1] != null) {
                            this.getAppletContext().showDocument(this.I1l[this.II1], this.Il1[this.II1]);
                        }
                    }
                    else {
                        this.III(this.Il1[this.II1]);
                    }
                }
                this.II1 = -1;
            }
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {
                this.II0 = null;
            }
        }
    }
    
    public void start() {
        this.II0 = null;
        (this.II0 = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.II0 != null && this.II0.isAlive()) {
            this.II0.stop();
            this.II0 = null;
        }
    }
    
    public void update(final Graphics graphics) {
        Thread.yield();
        graphics.drawImage(this.offimage, 0, 0, this);
        Thread.yield();
    }
}
