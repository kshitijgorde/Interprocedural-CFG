import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Container;
import java.awt.Menu;
import java.io.DataInputStream;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import netscape.javascript.JSObject;
import java.awt.Frame;
import java.applet.AudioClip;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class apPopupMenu extends Applet implements Runnable
{
    Thread III;
    int II1;
    int IIl;
    Image I1I;
    Image[] I11;
    Image I1l;
    Graphics IlI;
    int Il1;
    int Ill;
    int lII;
    int lI1;
    int lIl;
    int l1I;
    int I1;
    int l11;
    String[] l1l;
    String[] llI;
    String[] ll1;
    String[] lll;
    String l1II;
    String l1I1;
    URL[] l1Il;
    URL[] l11I;
    int[] l111;
    int[] l11l;
    int[] l1lI;
    int[] l1l1;
    int l1ll;
    int lIII;
    int lII1;
    int lIIl;
    int lI1I;
    PopupMenu[] lI11;
    MenuItem[] lI1l;
    AudioClip lIlI;
    AudioClip lIl1;
    Frame lIll;
    boolean llII;
    boolean llI1;
    boolean llIl;
    boolean ll1I;
    boolean ll11;
    boolean ll1l;
    boolean lllI;
    boolean lll1;
    boolean llll;
    int I1II;
    int I1I1;
    int I1Il;
    int I11I;
    int lllII;
    int lllI1;
    int I111;
    int I11l;
    int I1lI;
    int I1l1;
    int I1ll;
    String IIII;
    String III1;
    String IIIl;
    String II1I;
    int II11;
    int II1l;
    int IIlI;
    
    public apPopupMenu() {
        this.l11 = 0;
        this.l1ll = -1;
        this.llII = false;
        this.llI1 = false;
        this.llIl = false;
        this.ll1I = false;
        this.ll11 = true;
        this.ll1l = true;
        this.lllI = false;
        this.lll1 = true;
        this.llll = false;
        this.I1II = 13303482;
        this.I1I1 = 14352074;
        this.I1Il = 16384;
        this.I11I = 47872;
        this.lllII = -1;
        this.lllI1 = -1;
        this.I1ll = 1;
        this.IIII = "";
        this.III1 = "left";
        this.IIIl = "link";
        this.II1I = ",";
        this.II11 = 12;
        this.II1l = 0;
        this.IIlI = 0;
    }
    
    String I(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)(charArray[i] - i % 3 - 1);
        }
        return new String(charArray);
    }
    
    public void II(String s) {
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
    
    public void IIl1() {
        this.lII = 0;
        this.Ill = 0;
        this.Il1 = 0;
        this.l1II = this.getParameter("Copyright");
        if (this.l1II != null && this.l1II.indexOf("Apycom") != -1) {
            this.llll = true;
        }
        this.l1II = this.getParameter("backColor");
        if (this.l1II != null) {
            this.I1II = Integer.parseInt(this.l1II, 16);
            this.I1I1 = this.I1II;
        }
        this.l1II = this.getParameter("fontColor");
        if (this.l1II != null) {
            this.I1Il = Integer.parseInt(this.l1II, 16);
            this.I11I = this.I1Il;
        }
        this.II1 = this.size().width;
        this.IIl = this.size().height;
        this.I1I = this.createImage(this.II1, this.IIl);
        (this.IlI = this.I1I.getGraphics()).setFont(new Font("Arial", 0, 11));
        this.IlI.setColor(new Color(this.I1II));
        this.IlI.fillRect(0, 0, this.II1, this.IIl);
        this.IlI.setColor(new Color(this.I1Il));
        this.IlI.drawString("Loading...", 10, 15);
        this.IlI1();
        if (this.getParameter("backPic") != null) {
            final Image ilII = this.IlII(this.getParameter("backPic"));
            this.IIlI = 0;
            if (ilII != null) {
                this.I1l = this.createImage(this.II1, this.IIl);
                final Graphics graphics = this.I1l.getGraphics();
                graphics.setColor(new Color(this.I1II));
                graphics.fillRect(0, 0, this.II1, this.IIl);
                for (int i = 0; i < this.II1; i += ilII.getWidth(this)) {
                    for (int j = 0; j < this.IIl; j += ilII.getHeight(this)) {
                        graphics.drawImage(ilII, i, j, this);
                    }
                }
                this.IlI.drawImage(this.I1l, 0, 0, this);
                this.IlI.drawString("Loading...", 10, 15);
                this.IlI1();
            }
        }
        this.l1II = this.getParameter("buttonType");
        if (this.l1II != null) {
            this.I1ll = Integer.parseInt(this.l1II);
        }
        this.l1II = this.getParameter("backHighColor");
        if (this.l1II != null) {
            this.I1I1 = Integer.parseInt(this.l1II, 16);
        }
        this.l1II = this.getParameter("fontHighColor");
        if (this.l1II != null) {
            this.I11I = Integer.parseInt(this.l1II, 16);
        }
        this.l1II = this.getParameter("shadowColor");
        if (this.l1II != null) {
            this.lllII = Integer.parseInt(this.l1II, 16);
        }
        this.l1II = this.getParameter("shadowHighColor");
        if (this.l1II != null) {
            this.lllI1 = Integer.parseInt(this.l1II, 16);
        }
        this.lII1 = (this.I1I1 & 0xFF0000) >> 16;
        this.lIIl = (this.I1I1 & 0xFF00) >> 8;
        this.lI1I = (this.I1I1 & 0xFF);
        this.I1l1 = (this.IIll(this.lII1 * 2 / 3) << 16) + (this.IIll(this.lIIl * 2 / 3) << 8) + this.IIll(this.lI1I * 2 / 3);
        this.I1lI = (this.IIll((255 - this.lII1) / 3 * 2 + this.lII1) << 16) + (this.IIll((255 - this.lIIl) / 3 * 2 + this.lIIl) << 8) + this.IIll((255 - this.lI1I) / 3 * 2 + this.lI1I);
        this.lII1 = (this.I1II & 0xFF0000) >> 16;
        this.lIIl = (this.I1II & 0xFF00) >> 8;
        this.lI1I = (this.I1II & 0xFF);
        this.I11l = (this.IIll(this.lII1 * 2 / 3) << 16) + (this.IIll(this.lIIl * 2 / 3) << 8) + this.IIll(this.lI1I * 2 / 3);
        this.I111 = (this.IIll((255 - this.lII1) / 3 * 2 + this.lII1) << 16) + (this.IIll((255 - this.lIIl) / 3 * 2 + this.lIIl) << 8) + this.IIll((255 - this.lI1I) / 3 * 2 + this.lI1I);
        this.l1II = this.getParameter("isHorizontal");
        if (this.l1II != null && this.l1II.equalsIgnoreCase("true")) {
            this.llII = true;
        }
        this.l1II = this.getParameter("3DBorder");
        if (this.l1II != null && this.l1II.equalsIgnoreCase("true")) {
            this.llI1 = true;
        }
        if (this.I1ll == 0 && this.llI1) {
            this.l11 = 2;
        }
        this.l1II = this.getParameter("solidArrows");
        if (this.l1II != null && this.l1II.equalsIgnoreCase("true")) {
            this.ll1I = true;
        }
        this.l1II = this.getParameter("popupOver");
        if (this.l1II != null && this.l1II.equalsIgnoreCase("true")) {
            this.llIl = true;
        }
        this.l1II = this.getParameter("showArrows");
        if (this.l1II != null && this.l1II.equalsIgnoreCase("false")) {
            this.ll11 = false;
        }
        this.l1II = this.getParameter("systemSubFont");
        if (this.l1II != null && this.l1II.equalsIgnoreCase("false")) {
            this.ll1l = false;
        }
        this.l1II = this.getParameter("alignText");
        if (this.l1II != null) {
            this.III1 = this.l1II;
        }
        this.l1II = this.getParameter("status");
        if (this.l1II != null) {
            this.IIIl = this.l1II;
        }
        this.l1II = this.getParameter("statusString");
        if (this.l1II != null) {
            this.l1I1 = this.l1II;
        }
        this.l1II = this.getDocumentBase().getHost();
        if (this.l1II != null && !this.l1II.equalsIgnoreCase("")) {
            this.showStatus(this.l1I1 = this.I("Gthf\"pfpx!csqnhuu#bv#xyz/cszern0fpo"));
        }
        try {
            this.l1II = this.getParameter("overSound");
            if (this.l1II != null) {
                this.lIlI = this.getAudioClip(this.getCodeBase(), this.l1II);
            }
            this.l1II = this.getParameter("clickSound");
            if (this.l1II != null) {
                this.lIl1 = this.getAudioClip(this.getCodeBase(), this.l1II);
            }
        }
        catch (Exception ex) {}
        this.l1II = this.getParameter("font");
        if (this.l1II != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.l1II, ",");
            this.IIII = stringTokenizer.nextToken();
            this.II11 = Integer.parseInt(stringTokenizer.nextToken());
            this.II1l = Integer.parseInt(stringTokenizer.nextToken());
        }
        this.IlI.setFont(new Font(this.IIII, this.II1l, this.II11));
        this.l1II = this.getParameter("delimiter");
        if (this.l1II != null) {
            this.II1I = this.l1II;
        }
        this.l1II = this.getParameter("menuItemsFile");
        if (this.l1II != null) {
            try {
                final URL url = new URL(this.getCodeBase(), this.l1II);
                this.l1II = "";
                String line;
                while ((line = new DataInputStream(url.openStream()).readLine()) != null) {
                    this.l1II = String.valueOf(this.l1II) + line;
                }
            }
            catch (Exception ex2) {}
        }
        else {
            this.l1II = this.getParameter("menuItems");
        }
        this.l1II = this.l1II.substring(0, this.l1II.lastIndexOf("}"));
        this.lII = new StringTokenizer(this.l1II, "}").countTokens();
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.l1II, "}");
        while (stringTokenizer2.hasMoreTokens()) {
            final String nextToken = stringTokenizer2.nextToken();
            if (nextToken.substring(nextToken.indexOf("{") + 1).trim().startsWith("|")) {
                ++this.Il1;
            }
        }
        final StringTokenizer stringTokenizer3 = new StringTokenizer(this.l1II, "}");
        this.Ill = this.lII;
        this.lII = this.Ill - this.Il1;
        this.l111 = new int[this.lII];
        this.l11l = new int[this.lII];
        this.l1lI = new int[this.lII];
        this.l1l1 = new int[this.lII];
        this.l1l = new String[this.lII];
        this.l1Il = new URL[this.lII];
        this.llI = new String[this.lII];
        this.lI11 = new PopupMenu[this.lII];
        this.lI1l = new MenuItem[this.Il1];
        this.ll1 = new String[this.Il1];
        this.l11I = new URL[this.Il1];
        this.lll = new String[this.Il1];
        this.I11 = new Image[this.lII];
        this.lIl = 0;
        this.l1I = 0;
        int n = 0;
        while (stringTokenizer3.hasMoreTokens()) {
            this.l1II = stringTokenizer3.nextToken();
            this.l1II = this.l1II.substring(this.l1II.indexOf("{") + 1);
            final StringTokenizer stringTokenizer4 = new StringTokenizer(this.l1II, this.II1I);
            if (!this.l1II.trim().startsWith("|")) {
                this.l1l[this.l1I] = new String(stringTokenizer4.nextToken());
                if (this.l1l[this.l1I].equals("_")) {
                    this.l1l[this.l1I] = "";
                }
                if (this.l1l[this.l1I].trim().equals("-")) {
                    ++n;
                    ++this.l1I;
                }
                else if (!stringTokenizer4.hasMoreTokens()) {
                    ++this.l1I;
                }
                else {
                    if (stringTokenizer4.countTokens() == 1) {
                        this.I11[this.l1I] = this.IlII(stringTokenizer4.nextToken());
                    }
                    else {
                        this.l1II = stringTokenizer4.nextToken();
                        if (!this.l1II.startsWith("javascript:")) {
                            try {
                                this.l1Il[this.l1I] = new URL(this.getCodeBase(), this.l1II);
                            }
                            catch (Exception ex3) {}
                            this.llI[this.l1I] = new String(stringTokenizer4.nextToken());
                        }
                        else {
                            this.llI[this.l1I] = new String(this.l1II);
                            this.l1II = stringTokenizer4.nextToken();
                        }
                        if (stringTokenizer4.hasMoreTokens()) {
                            this.I11[this.l1I] = this.IlII(stringTokenizer4.nextToken());
                        }
                    }
                    ++this.l1I;
                }
            }
            else {
                this.llI[this.l1I - 1] = new String("__menu" + (this.l1I - 1) + "_");
                this.ll1[this.lIl] = new String(stringTokenizer4.nextToken());
                if (stringTokenizer4.hasMoreTokens()) {
                    this.l1II = stringTokenizer4.nextToken();
                    if (!this.l1II.startsWith("javascript:")) {
                        try {
                            this.l11I[this.lIl] = new URL(this.getCodeBase(), this.l1II);
                        }
                        catch (Exception ex4) {}
                        this.lll[this.lIl] = new String(stringTokenizer4.nextToken());
                    }
                    else {
                        this.lll[this.lIl] = new String(this.l1II);
                    }
                }
                this.lll[this.lIl] = new String(String.valueOf(this.lll[this.lIl]) + this.llI[this.l1I - 1]);
                ++this.lIl;
            }
        }
        for (int k = 0; k < this.lII; ++k) {
            if (this.llI[k] != null && this.llI[k].startsWith("__menu")) {
                this.Illl(this.lI11[k] = new PopupMenu(), k, 1);
                this.add(this.lI11[k]);
            }
        }
        if (!this.llII) {
            this.lI1 = (this.IIl - n * 8 - this.l11 * 2) / (this.lII - n);
        }
        else {
            this.lI1 = this.IIl - this.l11 * 2;
        }
        this.I1 = this.lI1 / 2 + this.II11 / 2 - 1;
        this.lIl = 0;
        if (this.llII) {
            for (int l = 0; l < this.lII; ++l) {
                if (!this.l1l[l].trim().equals("-")) {
                    this.l1lI[l] = this.IlI.getFontMetrics().stringWidth(this.l1l[l]);
                    if (this.llI[l].startsWith("__menu") && this.ll11) {
                        final int[] l1lI = this.l1lI;
                        final int n2 = l;
                        l1lI[n2] += this.II11;
                    }
                    if (this.I11[l] != null) {
                        final int[] l1lI2 = this.l1lI;
                        final int n3 = l;
                        l1lI2[n3] += this.I11[l].getWidth(this) + 3;
                    }
                    this.lIl += this.l1lI[l];
                }
            }
            this.l111[0] = this.l11;
            for (int n4 = 0; n4 < this.lII; ++n4) {
                this.l1l1[n4] = (this.II1 - 7 * n - this.lIl) / (this.lII - n) / 2;
                if (this.l1l[n4].trim().equals("-")) {
                    this.l1lI[n4] = 7;
                }
                else {
                    this.l1lI[n4] += this.l1l1[n4] * 2;
                }
                if (n4 > 0) {
                    this.l111[n4] = this.l111[n4 - 1] + this.l1lI[n4 - 1];
                }
                this.l11l[n4] = this.l11;
                if (this.I11[n4] != null) {
                    final int[] l1l1 = this.l1l1;
                    final int n5 = n4;
                    l1l1[n5] += this.I11[n4].getWidth(this) + 3;
                }
                this.IllI(n4);
                this.IlI1();
            }
            this.l1lI[this.lII - 1] = this.II1 - this.l111[this.lII - 1] - this.l11;
        }
        else {
            this.lIl = this.l11;
            for (int n6 = 0; n6 < this.lII; ++n6) {
                this.l111[n6] = this.l11;
                this.l1lI[n6] = this.II1 - this.l11 * 2;
                if (this.l1l[n6].trim().equals("-")) {
                    this.l11l[n6] = this.lIl;
                    this.lIl += 8;
                }
                else {
                    this.l11l[n6] = this.lIl;
                    this.lIl += this.lI1;
                    if (this.III1.equalsIgnoreCase("center")) {
                        this.l1l1[n6] = this.II1 / 2 - this.IlI.getFontMetrics().stringWidth(this.l1l[n6]) / 2;
                    }
                    if (this.III1.equalsIgnoreCase("left")) {
                        this.l1l1[n6] = this.II11 / 2 + this.IIlI + 3;
                    }
                    if (this.III1.equalsIgnoreCase("right")) {
                        this.l1l1[n6] = this.II1 - this.IlI.getFontMetrics().stringWidth(this.l1l[n6]) - this.II11 / 2;
                    }
                    this.IllI(n6);
                    this.IlI1();
                }
            }
        }
        Container parent;
        for (parent = this; parent != null && !(parent instanceof Frame); parent = parent.getParent()) {}
        this.lIll = (Frame)parent;
    }
    
    int IIll(final int n) {
        if (n > 255) {
            return 255;
        }
        if (n < 0) {
            return 0;
        }
        return n;
    }
    
    public void Il11(final int n, final int n2, final int n3) {
        int n4 = this.I111;
        int n5 = this.I11l;
        if (n == -1) {
            this.IlI.setColor(new Color(n4));
            this.IlI.drawLine(0, 0, this.II1, 0);
            this.IlI.drawLine(0, 0, 0, this.IIl);
            this.IlI.setColor(new Color(n5));
            this.IlI.drawLine(this.II1 - 1, this.IIl - 1, this.II1 - 1, 0);
            this.IlI.drawLine(this.II1 - 1, this.IIl - 1, 1, this.IIl - 1);
            return;
        }
        if (n3 != 0) {
            n4 = this.I1lI;
            n5 = this.I1l1;
        }
        switch (n2) {
            default: {
                this.IlI.setColor(new Color(n4));
                this.IlI.drawLine(this.l111[n], this.l11l[n], this.l111[n] + this.l1lI[n] - 1, this.l11l[n]);
                this.IlI.drawLine(this.l111[n], this.l11l[n], this.l111[n], this.l11l[n] + this.lI1 - 1);
                this.IlI.setColor(new Color(n5));
                this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 1, this.l11l[n] + this.lI1 - 1, this.l111[n] + this.l1lI[n] - 1, this.l11l[n]);
                this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 1, this.l11l[n] + this.lI1 - 1, this.l111[n] + 1, this.l11l[n] + this.lI1 - 1);
                break;
            }
            case 1: {
                this.IlI.setColor(new Color(n5));
                this.IlI.drawLine(this.l111[n], this.l11l[n], this.l111[n] + this.l1lI[n] - 1, this.l11l[n]);
                this.IlI.drawLine(this.l111[n], this.l11l[n], this.l111[n], this.l11l[n] + this.lI1 - 1);
                this.IlI.setColor(new Color(n4));
                this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 1, this.l11l[n] + this.lI1 - 1, this.l111[n] + this.l1lI[n] - 1, this.l11l[n]);
                this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 1, this.l11l[n] + this.lI1 - 1, this.l111[n] + 1, this.l11l[n] + this.lI1 - 1);
                break;
            }
            case 2: {
                this.IlI.setColor(new Color(n4));
                this.IlI.drawLine(this.l111[n] + 1, this.l11l[n] + 1, this.l111[n] + this.l1lI[n] - 2, this.l11l[n] + 1);
                this.IlI.drawLine(this.l111[n] + 1, this.l11l[n] + 1, this.l111[n] + 1, this.l11l[n] + this.lI1 - 2);
                this.IlI.setColor(new Color(0));
                this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 1, this.l11l[n] + this.lI1 - 1, this.l111[n] + this.l1lI[n] - 1, this.l11l[n]);
                this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 1, this.l11l[n] + this.lI1 - 1, this.l111[n] + 1, this.l11l[n] + this.lI1 - 1);
                this.IlI.setColor(new Color(n5));
                this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 2, this.l11l[n] + this.lI1 - 2, this.l111[n] + this.l1lI[n] - 2, this.l11l[n] + 1);
                this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 2, this.l11l[n] + this.lI1 - 2, this.l111[n] + 2, this.l11l[n] + this.lI1 - 2);
                break;
            }
            case 3: {
                this.IlI.setColor(new Color(n5));
                this.IlI.drawLine(this.l111[n] + 1, this.l11l[n] + 1, this.l111[n] + this.l1lI[n] - 2, this.l11l[n] + 1);
                this.IlI.drawLine(this.l111[n] + 1, this.l11l[n] + 1, this.l111[n] + 1, this.l11l[n] + this.lI1 - 2);
                this.IlI.setColor(new Color(0));
                this.IlI.drawLine(this.l111[n], this.l11l[n], this.l111[n] + this.l1lI[n] - 1, this.l11l[n]);
                this.IlI.drawLine(this.l111[n], this.l11l[n], this.l111[n], this.l11l[n] + this.lI1 - 1);
                this.IlI.setColor(new Color(n4));
                this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 2, this.l11l[n] + this.lI1 - 2, this.l111[n] + this.l1lI[n] - 2, this.l11l[n] + 1);
                this.IlI.setColor(new Color(n4));
                this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 1, this.l11l[n] + this.lI1 - 1, this.l111[n] + this.l1lI[n] - 1, this.l11l[n]);
                this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 1, this.l11l[n] + this.lI1 - 1, this.l111[n] + 1, this.l11l[n] + this.lI1 - 1);
                break;
            }
        }
    }
    
    public void Il1I(final int n, final int n2, final int n3) {
        if (!this.llI[n].startsWith("__menu") || !this.ll11) {
            return;
        }
        int n4 = this.I111;
        int n5 = this.I11l;
        int n6 = this.I1Il;
        if (n3 != 0) {
            n4 = this.I1lI;
            n5 = this.I1l1;
            n6 = this.I11I;
        }
        if (this.llII) {
            final int n7 = this.l11l[n] + this.lI1 / 2 - 3 + n2;
            int n8 = this.l111[n] + this.l1lI[n] - this.l1l1[n] - 6;
            if (this.I11[n] != null) {
                n8 += this.I11[n].getWidth(this);
            }
            if (this.l1l[n] == "") {
                n8 = this.l111[n] + this.l1l1[n];
            }
            if (this.ll1I) {
                this.IlI.setColor(new Color(n6));
                for (int i = 0; i < 4; ++i) {
                    this.IlI.drawLine(n8 + i, n7 + 2 + i, n8 + 6 - i, n7 + 2 + i);
                }
            }
            else {
                this.IlI.setColor(new Color(n5));
                this.IlI.drawLine(n8, n7, n8 + 8, n7);
                this.IlI.drawLine(n8, n7, n8 + 4, n7 + 7);
                this.IlI.setColor(new Color(n4));
                this.IlI.drawLine(n8 + 4, n7 + 7, n8 + 8, n7);
            }
        }
        else {
            final int n9 = this.l11l[n] + this.lI1 / 2 - 5 - 1 + n2;
            if (this.ll1I) {
                this.IlI.setColor(new Color(n6));
                for (int j = 0; j < 4; ++j) {
                    this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 12 + j, n9 + 2 + j, this.l111[n] + this.l1lI[n] - 12 + j, n9 + 2 + 6 - j);
                }
            }
            else {
                this.IlI.setColor(new Color(n5));
                this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 12, n9, this.l111[n] + this.l1lI[n] - 12, n9 + 10);
                this.IlI.setColor(new Color(n4));
                this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 12, n9, this.l111[n] + this.l1lI[n] - 6, n9 + 5);
                this.IlI.drawLine(this.l111[n] + this.l1lI[n] - 12, n9 + 10, this.l111[n] + this.l1lI[n] - 6, n9 + 5);
            }
        }
    }
    
    public void Il1l(final int n, final int n2) {
        if (this.I11[n] == null) {
            return;
        }
        if (this.llII) {
            this.IlI.drawImage(this.I11[n], this.l111[n] + this.l1l1[n] - this.I11[n].getWidth(this) - 3, this.l11l[n] + (this.lI1 - this.I11[n].getHeight(this)) / 2 + n2, this);
        }
        else {
            this.IlI.drawImage(this.I11[n], this.l111[n] + 3, this.l11l[n] + (this.lI1 - this.I11[n].getHeight(this)) / 2 + n2, this);
        }
    }
    
    public synchronized void IlI1() {
        if (this.I1I == null) {
            return;
        }
        if (this.lllI) {
            this.IlI.setColor(new Color(this.I1II));
            this.IlI.fillRect(0, 0, this.II1, this.IIl);
            if (this.llll) {
                if (this.I1l != null) {
                    this.IlI.drawImage(this.I1l, 0, 0, this);
                }
                for (int i = 0; i < this.lII; ++i) {
                    this.IllI(i);
                }
                if (this.llI1) {
                    this.Il11(-1, 0, 0);
                }
            }
            else {
                this.IlI.setColor(new Color(this.I1Il));
                this.IlI.drawString("Incorrect Copyright", 10, 15);
            }
        }
        this.getGraphics().drawImage(this.I1I, 0, 0, this);
    }
    
    public Image IlII(final String s) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        Image image;
        try {
            image = this.getImage(this.getCodeBase(), s);
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
        }
        catch (Exception ex) {
            return null;
        }
        if (mediaTracker.isErrorID(0)) {
            return null;
        }
        this.IIlI = ((image.getWidth(this) > this.IIlI) ? image.getWidth(this) : this.IIlI);
        return image;
    }
    
    public void IlIl(final int n) {
        final int n2 = 3;
        if (this.llII) {
            this.IlI.setColor(new Color(this.I11l));
            this.IlI.drawLine(this.l111[n] + n2, this.l11l[n] + n2, this.l111[n] + n2, this.l11l[n] + this.lI1 - n2 - 1);
            this.IlI.setColor(new Color(this.I111));
            this.IlI.drawLine(this.l111[n] + n2 + 1, this.l11l[n] + n2, this.l111[n] + n2 + 1, this.l11l[n] + this.lI1 - n2 - 1);
            this.IlI.drawLine(this.l111[n] + n2, this.l11l[n] + this.lI1 - n2 - 1, this.l111[n] + n2 + 1, this.l11l[n] + this.lI1 - n2 - 1);
        }
        else {
            this.IlI.setColor(new Color(this.I11l));
            this.IlI.drawLine(this.l111[n] + n2, this.l11l[n] + n2, this.l111[n] + this.l1lI[n] - n2 - 1, this.l11l[n] + n2);
            this.IlI.setColor(new Color(this.I111));
            this.IlI.drawLine(this.l111[n] + n2, this.l11l[n] + n2 + 1, this.l111[n] + this.l1lI[n] - n2 - 1, this.l11l[n] + n2 + 1);
            this.IlI.drawLine(this.l111[n] + this.l1lI[n] - n2 - 1, this.l11l[n] + n2 + 1, this.l111[n] + this.l1lI[n] - n2 - 1, this.l11l[n] + n2);
        }
    }
    
    public int Ill1(final int n, final int n2) {
        for (int i = 0; i < this.lII; ++i) {
            if (!this.l1l[i].trim().equals("-") && new Rectangle(this.l111[i], this.l11l[i], this.l1lI[i], this.lI1).inside(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    public void IllI(final int n) {
        if (this.l1l[n].trim().equals("-")) {
            this.IlIl(n);
            return;
        }
        int n2 = 0;
        int liii = 0;
        int n3 = this.I1II;
        int n4 = this.I1Il;
        int n5 = this.lllII;
        if (this.l1ll == n) {
            n2 = 1;
            liii = this.lIII;
            n3 = this.I1I1;
            n4 = this.I11I;
            n5 = this.lllI1;
        }
        if (this.I1l == null) {
            this.IlI.setColor(new Color(n3));
            this.IlI.fillRect(this.l111[n], this.l11l[n], this.l1lI[n], this.lI1);
        }
        if (n5 != -1) {
            this.IlI.setColor(new Color(n5));
            this.IlI.drawString(this.l1l[n], this.l111[n] + this.l1l1[n] + 1, this.l11l[n] + this.I1 + 1);
        }
        if (this.llI[n].equals("_")) {
            this.IlI.setColor(new Color(this.I111));
            this.IlI.drawString(this.l1l[n], this.l111[n] + this.l1l1[n] + 1, this.l11l[n] + this.I1 + 1);
        }
        this.IlI.setColor(new Color(n4));
        if (this.llI[n].equals("_")) {
            this.IlI.setColor(new Color(this.I11l));
        }
        this.IlI.drawString(this.l1l[n], this.l111[n] + this.l1l1[n], this.l11l[n] + this.I1 + liii);
        if (this.l1ll == n) {
            switch (this.I1ll) {
                case 1:
                case 2: {
                    this.Il11(n, liii, n2);
                    break;
                }
                case 3:
                case 4:
                case 5: {
                    this.Il11(n, 2 + liii, n2);
                    break;
                }
            }
        }
        else {
            switch (this.I1ll) {
                case 2:
                case 5: {
                    this.Il11(n, 0, 0);
                    break;
                }
                case 4: {
                    this.Il11(n, 2, 0);
                    break;
                }
            }
        }
        this.Il1l(n, liii);
        this.Il1I(n, liii, n2);
    }
    
    private void Illl(final Menu menu, final int n, final int n2) {
        final String s = new String("__menu" + n + "_");
        for (int i = 0; i < this.Il1; ++i) {
            if (this.lll[i].indexOf(s) != -1) {
                int n3 = 0;
                int n4 = 0;
                while (new StringTokenizer(this.ll1[i], "|", true).nextToken().equals("|")) {
                    ++n3;
                }
                if (n2 > n3) {
                    return;
                }
                this.lll[i] = this.lll[i].substring(0, this.lll[i].indexOf(s));
                final String substring = this.ll1[i].substring(n3);
                if (this.l11I[i] != null || this.lll[i].startsWith("javascript:")) {
                    this.lI1l[i] = new MenuItem(substring);
                    if (this.lll[i].equals("_")) {
                        this.lI1l[i].enable(false);
                    }
                    menu.add(this.lI1l[i]);
                    if (!this.ll1l) {
                        menu.setFont(new Font(this.IIII, this.II1l, this.II11));
                        this.lI1l[i].setFont(new Font(this.IIII, this.II1l, this.II11));
                    }
                }
                else {
                    if (!substring.startsWith("-")) {
                        final Menu menu2 = new Menu(substring);
                        if (!this.ll1l) {
                            menu2.setFont(new Font(this.IIII, this.II1l, this.II11));
                        }
                        this.Illl(menu2, n, n2 + 1);
                        menu.add(menu2);
                        continue;
                    }
                    menu.addSeparator();
                }
                if (i + 1 < this.Il1 && this.lll[i + 1].indexOf(new String("__menu" + n + "_")) != -1) {
                    while (new StringTokenizer(this.ll1[i + 1], "|", true).nextToken().equals("|")) {
                        ++n4;
                    }
                }
                if (n4 < n3) {
                    return;
                }
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (!this.lllI) {
            return true;
        }
        int ill1 = this.Ill1(event.x, event.y);
        if (ill1 == -1 || this.llI[ill1].equals("_")) {
            ill1 = -1;
        }
        try {
            if (event.id == 1001) {
                try {
                    for (int i = 0; i < this.Il1; ++i) {
                        if (this.lI1l[i] != null && this.lI1l[i].equals(event.target) && !this.lll[i].equals("_")) {
                            if (!this.lll[i].startsWith("javascript:")) {
                                if (this.l11I[i] != null) {
                                    this.getAppletContext().showDocument(this.l11I[i], this.lll[i]);
                                }
                            }
                            else {
                                this.II(this.lll[i]);
                            }
                        }
                    }
                }
                catch (Exception ex) {}
            }
            if (ill1 == -1 || event.id == 505) {
                this.l1ll = -1;
                if (this.lIll != null) {
                    this.lIll.setCursor(0);
                }
                this.IlI1();
                return true;
            }
            if (event.id == 503) {
                if (!this.lll1) {
                    return true;
                }
                this.lll1 = false;
                if (ill1 != this.l1ll) {
                    if (this.lIll != null) {
                        this.lIll.setCursor(12);
                    }
                    this.lIII = 0;
                    this.l1ll = ill1;
                    this.IlI1();
                    if (this.lIlI != null) {
                        this.lIlI.play();
                    }
                    if (this.l1I1 == null) {
                        if (this.IIIl.equalsIgnoreCase("link") && !this.llI[ill1].startsWith("__menu") && this.l1Il[ill1] != null) {
                            this.showStatus(this.l1Il[ill1].toString());
                        }
                        else {
                            this.showStatus(this.l1l[ill1].trim());
                        }
                    }
                    else {
                        this.showStatus(this.l1I1);
                    }
                    if (this.llIl && this.llI[ill1].startsWith("__menu")) {
                        event.id = 501;
                        this.lll1 = true;
                    }
                }
            }
            if (event.id == 501) {
                if (!this.lll1) {
                    return true;
                }
                this.lll1 = false;
                this.l1ll = ill1;
                this.lIII = 1;
                this.IlI1();
                if (this.lIl1 != null) {
                    this.lIl1.play();
                }
                if (this.llI[ill1].startsWith("__menu")) {
                    if (this.l1I1 == null) {
                        this.showStatus(this.l1l[ill1].trim());
                    }
                    if (this.llII) {
                        this.lI11[ill1].show(this, this.l111[ill1], this.l11l[ill1] + this.lI1);
                    }
                    else {
                        this.lI11[ill1].show(this, this.l111[ill1] + this.l1lI[ill1], this.l11l[ill1]);
                    }
                }
                else if (!this.llI[ill1].startsWith("javascript:")) {
                    if (this.l1Il[ill1] != null) {
                        this.getAppletContext().showDocument(this.l1Il[ill1], this.llI[ill1]);
                    }
                }
                else {
                    this.II(this.llI[ill1]);
                }
            }
        }
        catch (Throwable t) {}
        return this.lll1 = true;
    }
    
    public void paint(final Graphics graphics) {
        this.IlI1();
    }
    
    public void run() {
        while (true) {
            if (!this.lllI) {
                this.IIl1();
                this.lllI = true;
                this.IlI1();
            }
            try {
                Thread.sleep(200L);
                this.IlI1();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        if (this.III == null) {
            (this.III = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.III != null) {
            this.III.stop();
            this.III = null;
        }
    }
}
