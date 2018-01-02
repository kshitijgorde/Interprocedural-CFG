import java.awt.Event;
import java.awt.Container;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Font;
import java.util.StringTokenizer;
import netscape.javascript.JSObject;
import java.awt.Color;
import java.applet.AudioClip;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class apMenu extends Applet implements Runnable
{
    Thread l0;
    int lo;
    int lO;
    Image l1;
    Image l2;
    Graphics ll;
    int l3;
    int l4;
    int l5;
    int l6;
    int l7;
    int l8;
    String[] l9;
    String[] l0l;
    String l01;
    URL[] l00;
    int[] l11;
    int[] l1l;
    int[] lll;
    int[] ll1;
    int[] l10;
    int[] ll0;
    int[] l0O;
    int[] lO0;
    int O0;
    int O1;
    int O2;
    int O3;
    int O4;
    int O5;
    int O6;
    int O7;
    int O8;
    int O9;
    int O10;
    int O11;
    AudioClip Olll;
    AudioClip Oll1;
    int Ol1;
    int O1l;
    boolean O18;
    int O12;
    int Ol2;
    int O13;
    int Ol3;
    int Ol4;
    String O14;
    String Ol5;
    String O15;
    int O16;
    int Ol6;
    String O17;
    String Ol7;
    String ho;
    boolean oo;
    
    public apMenu() {
        this.l8 = -1;
        this.Ol1 = 15;
        this.O1l = 30;
        this.O18 = false;
        this.O12 = 13303482;
        this.Ol2 = 1404929;
        this.O13 = 16384;
        this.Ol3 = 16777215;
        this.Ol4 = 0;
        this.O14 = "";
        this.Ol5 = "left";
        this.O15 = "";
        this.O16 = 14;
        this.Ol6 = 0;
        this.Ol7 = "Gthf\"dqrofvv!cw!yzx0dq{fpo1dqp";
        this.ho = "q{fpo1";
        this.oo = false;
    }
    
    public void OO(final int l8) {
        switch (this.l0O[l8]) {
            case 0: {
                if (this.lO0[l8] != -1) {
                    this.l5 = this.lO0[l8];
                    if (this.Ol4 != 0 && this.l2 == null) {
                        this.ll.setColor(new Color(this.O12));
                        this.ll.fillRect(this.l11[l8], this.l1l[l8], this.lll[l8], this.l4);
                    }
                    this.ll.setColor(new Color(this.l10[this.l5]));
                    if ((this.l2 != null || this.Ol4 != 0) && this.l5 < this.O1l / 2) {
                        switch (this.Ol4) {
                            default: {
                                this.l6 = (int)(this.lll[l8] / 2.0 - this.lll[l8] / 2.0 / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                this.ll.fillRect(this.l11[l8], this.l1l[l8], this.l6, this.l4);
                                this.ll.fillRect(this.l11[l8] + this.lll[l8] - this.l6, this.l1l[l8], this.l6, this.l4);
                                break;
                            }
                            case 2: {
                                this.l6 = (int)(this.l4 / 2.0 - this.l4 / 2.0 / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                this.ll.fillRect(this.l11[l8], this.l1l[l8], this.lll[l8], this.l6);
                                this.ll.fillRect(this.l11[l8], this.l1l[l8] + this.l4 - this.l6, this.lll[l8], this.l6);
                                break;
                            }
                            case 3: {
                                this.l6 = (int)(this.lll[l8] - this.lll[l8] / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                this.ll.fillRect(this.l11[l8], this.l1l[l8], this.l6, this.l4 / 2);
                                this.ll.fillRect(this.l11[l8] + this.lll[l8] - this.l6, this.l1l[l8] + this.l4 / 2, this.l6, this.l4 - this.l4 / 2);
                                break;
                            }
                            case 4: {
                                this.l6 = (int)(this.l4 - this.l4 / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                this.ll.fillRect(this.l11[l8], this.l1l[l8], this.lll[l8] / 2, this.l6);
                                this.ll.fillRect(this.l11[l8] + this.lll[l8] / 2, this.l1l[l8] + this.l4 - this.l6, this.lll[l8] - this.lll[l8] / 2, this.l6);
                                break;
                            }
                            case 5: {
                                this.l6 = (int)(this.lll[l8] / 2.0 - this.lll[l8] / 2.0 / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                final int n = (int)(this.l4 / 2.0 - this.l4 / 2.0 / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                this.ll.fillRect(this.l11[l8] - this.l6 + this.lll[l8] / 2, this.l1l[l8] - n + this.l4 / 2, this.l6 * 2, n * 2);
                                break;
                            }
                            case 6: {
                                this.l6 = (int)(this.lll[l8] / 2.0 - this.lll[l8] / 2.0 / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                final int n2 = (int)(this.l4 / 2.0 - this.l4 / 2.0 / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                this.ll.fillRect(this.l11[l8], this.l1l[l8] - n2 + this.l4 / 2, this.l6 * 2, n2 * 2);
                                break;
                            }
                            case 7: {
                                this.l6 = (int)(this.lll[l8] / 2.0 - this.lll[l8] / 2.0 / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                final int n3 = (int)(this.l4 / 2.0 - this.l4 / 2.0 / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                this.ll.fillRect(this.l11[l8] - this.l6 * 2 + this.lll[l8], this.l1l[l8] - n3 + this.l4 / 2, this.l6 * 2, n3 * 2);
                                break;
                            }
                            case 8: {
                                this.l6 = (int)(this.l4 / 2.0 - this.l4 / 2.0 / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                this.ll.fillRect(this.l11[l8], this.l1l[l8] + this.l4 / 2 - this.l6, this.lll[l8], this.l6 * 2);
                                break;
                            }
                            case 9: {
                                this.l6 = (int)(this.lll[l8] / 2.0 - this.lll[l8] / 2.0 / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                this.ll.fillRect(this.l11[l8] - this.l6 + this.lll[l8] / 2, this.l1l[l8], this.l6 * 2, this.l4);
                                break;
                            }
                            case 10: {
                                this.l6 = (int)(this.l4 / 2.0 - this.l4 / 2.0 / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                if (this.l5 < this.O1l / 4) {
                                    this.ll.fillRect(this.l11[l8], this.l1l[l8] + this.l6 * 2, this.lll[l8], this.l6 * 2);
                                    break;
                                }
                                this.ll.fillRect(this.l11[l8], this.l1l[l8] + this.l4 - this.l6 * 2, this.lll[l8], this.l6 * 2);
                                break;
                            }
                            case 11: {
                                this.l6 = (int)(this.lll[l8] / 2.0 - this.lll[l8] / 2.0 / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                if (this.l5 < this.O1l / 4) {
                                    this.ll.fillRect(this.l11[l8] + this.l6 * 2, this.l1l[l8], this.l6 * 2, this.l4);
                                    break;
                                }
                                this.ll.fillRect(this.l11[l8] - this.l6 * 2 + this.lll[l8], this.l1l[l8], this.l6 * 2, this.l4);
                                break;
                            }
                            case 12: {
                                this.l6 = (int)(this.lll[l8] / 2.0 - this.lll[l8] / 2.0 / (this.O1l / 2.0) * (this.O1l / 2.0 - this.l5));
                                if (this.l5 < this.O1l / 4) {
                                    this.ll.fillRect(this.l11[l8] + this.lll[l8] - this.l6 * 4, this.l1l[l8], this.l6 * 2, this.l4);
                                    break;
                                }
                                this.ll.fillRect(this.l11[l8], this.l1l[l8], this.l6 * 2, this.l4);
                                break;
                            }
                        }
                    }
                    else {
                        this.ll.fillRect(this.l11[l8], this.l1l[l8], this.lll[l8], this.l4);
                    }
                    this.ll.setColor(new Color(this.ll0[this.l5]));
                    this.ll.drawString(this.l9[l8], this.l11[l8] + this.ll1[l8], this.l1l[l8] + this.l7);
                    final int[] lo0 = this.lO0;
                    --lo0[l8];
                    break;
                }
                if (this.l2 != null) {
                    this.ll.setColor(new Color(this.O13));
                    this.ll.drawString(this.l9[l8], this.l11[l8] + this.ll1[l8], this.l1l[l8] + this.l7);
                    break;
                }
                break;
            }
            case 2: {
                this.ll.setColor(new Color(this.l10[this.O1l]));
                this.ll.fillRect(this.l11[l8], this.l1l[l8], this.lll[l8], this.l4);
                this.ll.setColor(new Color(this.ll0[this.O1l]));
                this.ll.drawString(this.l9[l8], this.l11[l8] + this.ll1[l8], this.l1l[l8] + this.l7);
                break;
            }
            case 1: {
                this.ll.setColor(new Color(this.l10[this.O1l]));
                this.ll.fillRect(this.l11[l8], this.l1l[l8], this.lll[l8], this.l4);
                this.ll.setColor(new Color(this.ll0[this.O1l]));
                this.ll.drawString(this.l9[l8], this.l11[l8] + this.ll1[l8], this.l1l[l8] + this.l7);
                if (this.Olll != null) {
                    this.Olll.play();
                }
                this.lOO(l8);
                this.l0O[l8] = 2;
                if (!this.oo) {
                    this.showStatus(this.hO(this.Ol7));
                    break;
                }
                if (this.O15.equalsIgnoreCase("link") && this.l00[l8] != null) {
                    this.showStatus(this.l00[l8].toString());
                    break;
                }
                this.showStatus(this.l9[l8].trim());
                break;
            }
            case 3: {
                this.ll.setColor(new Color(this.l10[this.O1l]));
                this.ll.fillRect(this.l11[l8], this.l1l[l8], this.lll[l8], this.l4);
                this.ll.setColor(new Color(this.O12));
                this.ll.drawString(this.l9[l8], this.l11[l8] + this.ll1[l8] + 1, this.l1l[l8] + this.l7);
                this.ll.drawString(this.l9[l8], this.l11[l8] + this.ll1[l8] - 1, this.l1l[l8] + this.l7);
                this.ll.drawString(this.l9[l8], this.l11[l8] + this.ll1[l8], this.l1l[l8] + this.l7 + 1);
                this.ll.drawString(this.l9[l8], this.l11[l8] + this.ll1[l8], this.l1l[l8] + this.l7 - 1);
                this.ll.setColor(new Color(this.O13));
                this.ll.drawString(this.l9[l8], this.l11[l8] + this.ll1[l8], this.l1l[l8] + this.l7);
                if (this.Oll1 != null) {
                    this.Oll1.play();
                }
                this.lOO(l8);
                this.l0O[l8] = 4;
                this.l8 = l8;
                break;
            }
            case 4: {
                this.ll.setColor(new Color(this.l10[this.O1l]));
                this.ll.fillRect(this.l11[l8], this.l1l[l8], this.lll[l8], this.l4);
                this.ll.setColor(new Color(this.O12));
                this.ll.drawString(this.l9[l8], this.l11[l8] + this.ll1[l8] + 1, this.l1l[l8] + this.l7);
                this.ll.drawString(this.l9[l8], this.l11[l8] + this.ll1[l8] - 1, this.l1l[l8] + this.l7);
                this.ll.drawString(this.l9[l8], this.l11[l8] + this.ll1[l8], this.l1l[l8] + this.l7 + 1);
                this.ll.drawString(this.l9[l8], this.l11[l8] + this.ll1[l8], this.l1l[l8] + this.l7 - 1);
                this.ll.setColor(new Color(this.O13));
                this.ll.drawString(this.l9[l8], this.l11[l8] + this.ll1[l8], this.l1l[l8] + this.l7);
                break;
            }
        }
    }
    
    public void OllI(String s) {
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
    
    public int Oo(final int n, final int n2) {
        if (n2 > this.lO || n > this.lo) {
            return -1;
        }
        for (int i = 0; i < this.l3; ++i) {
            if (n <= this.l11[i] + this.lll[i] && n2 <= this.l1l[i] + this.l4) {
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
        this.lo = this.size().width;
        this.lO = this.size().height;
        this.l01 = this.getParameter("fadeDelay");
        if (this.l01 != null) {
            this.Ol1 = Integer.parseInt(this.l01);
        }
        if (this.Ol1 < 5) {
            this.Ol1 = 5;
        }
        this.l01 = this.getParameter("fadeSteps");
        if (this.l01 != null) {
            this.O1l = Integer.parseInt(this.l01);
        }
        if (this.O1l < 1) {
            this.O1l = 1;
        }
        this.l01 = this.getParameter("fadeType");
        if (this.l01 != null) {
            this.Ol4 = Integer.parseInt(this.l01);
        }
        this.l01 = this.getParameter("backColor");
        if (this.l01 != null) {
            this.O12 = Integer.parseInt(this.l01, 16);
        }
        this.l01 = this.getParameter("backHighColor");
        if (this.l01 != null) {
            this.Ol2 = Integer.parseInt(this.l01, 16);
        }
        this.l01 = this.getParameter("fontColor");
        if (this.l01 != null) {
            this.O13 = Integer.parseInt(this.l01, 16);
        }
        this.l01 = this.getParameter("fontHighColor");
        if (this.l01 != null) {
            this.Ol3 = Integer.parseInt(this.l01, 16);
        }
        this.l01 = this.getParameter("isHorizontal");
        if (this.l01 != null && this.l01.equalsIgnoreCase("true")) {
            this.O18 = true;
        }
        this.l01 = this.getParameter("alignText");
        if (this.l01 != null) {
            this.Ol5 = this.l01;
        }
        this.l01 = this.getParameter("status");
        if (this.l01 != null) {
            this.O15 = this.l01;
        }
        this.l01 = this.getParameter("overSound");
        if (this.l01 != null) {
            this.Olll = this.getAudioClip(this.getCodeBase(), this.l01);
        }
        this.l01 = this.getParameter("clickSound");
        if (this.l01 != null) {
            this.Oll1 = this.getAudioClip(this.getCodeBase(), this.l01);
        }
        this.l01 = this.getParameter("font");
        if (this.l01 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.l01, ",");
            this.O14 = stringTokenizer.nextToken();
            this.O16 = Integer.parseInt(stringTokenizer.nextToken());
            this.Ol6 = Integer.parseInt(stringTokenizer.nextToken());
        }
        this.l01 = this.getParameter("menuItems");
        this.l01 = this.l01.substring(0, this.l01.lastIndexOf("}"));
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.l01, "}");
        this.l3 = stringTokenizer2.countTokens();
        if (!this.O18) {
            this.l4 = this.lO / this.l3;
        }
        else {
            this.l4 = this.lO;
        }
        this.l0O = new int[this.l3];
        this.lO0 = new int[this.l3];
        this.l11 = new int[this.l3];
        this.l1l = new int[this.l3];
        this.lll = new int[this.l3];
        this.ll1 = new int[this.l3];
        this.l9 = new String[this.l3];
        this.l00 = new URL[this.l3];
        this.l0l = new String[this.l3];
        for (int i = 0; i < this.l3; ++i) {
            this.l01 = stringTokenizer2.nextToken();
            this.l01 = this.l01.substring(this.l01.indexOf("{") + 1);
            final StringTokenizer stringTokenizer3 = new StringTokenizer(this.l01, ",");
            this.l9[i] = new String(stringTokenizer3.nextToken());
            this.l01 = stringTokenizer3.nextToken();
            if (!this.l01.startsWith("javascript:")) {
                try {
                    this.l00[i] = new URL(this.getCodeBase(), this.l01);
                }
                catch (Exception ex) {}
                this.l0l[i] = new String(stringTokenizer3.nextToken());
            }
            else {
                this.l0l[i] = new String(this.l01);
            }
        }
        this.O0 = (this.O12 & 0xFF0000) >> 16;
        this.O1 = (this.O12 & 0xFF00) >> 8;
        this.O2 = (this.O12 & 0xFF);
        this.O3 = (((this.Ol2 & 0xFF0000) >> 16) - this.O0) / this.O1l;
        this.O4 = (((this.Ol2 & 0xFF00) >> 8) - this.O1) / this.O1l;
        this.O5 = ((this.Ol2 & 0xFF) - this.O2) / this.O1l;
        this.O6 = (this.O13 & 0xFF0000) >> 16;
        this.O7 = (this.O13 & 0xFF00) >> 8;
        this.O8 = (this.O13 & 0xFF);
        this.O9 = (((this.Ol3 & 0xFF0000) >> 16) - this.O6) / this.O1l;
        this.O10 = (((this.Ol3 & 0xFF00) >> 8) - this.O7) / this.O1l;
        this.O11 = ((this.Ol3 & 0xFF) - this.O8) / this.O1l;
        this.l10 = new int[this.O1l + 1];
        this.ll0 = new int[this.O1l + 1];
        for (int j = 0; j < this.O1l + 1; ++j) {
            this.l10[j] = (this.oO(this.O3 * j + this.O0) << 16) + (this.oO(this.O4 * j + this.O1) << 8) + this.oO(this.O5 * j + this.O2);
            this.ll0[j] = (this.oO(this.O9 * j + this.O6) << 16) + (this.oO(this.O10 * j + this.O7) << 8) + this.oO(this.O11 * j + this.O8);
        }
        this.l1 = this.createImage(this.lo, this.lO);
        (this.ll = this.l1.getGraphics()).setFont(new Font(this.O14, this.Ol6, this.O16));
        this.l7 = this.l4 / 2 + this.O16 / 2 - 2;
        if (this.getParameter("backPic") != null) {
            final Image image = this.getImage(this.getDocumentBase(), this.getParameter("backPic"));
            this.l2 = this.createImage(this.lo, this.lO);
            final Graphics graphics = this.l2.getGraphics();
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(image, 0);
            try {
                mediaTracker.waitForAll();
            }
            catch (Exception ex2) {}
            for (int k = 0; k < this.lo; k += image.getWidth(this)) {
                for (int l = 0; l < this.lO; l += image.getHeight(this)) {
                    graphics.drawImage(image, k, l, this);
                }
            }
            this.ll.drawImage(this.l2, 0, 0, this);
        }
        else {
            this.ll.setColor(new Color(this.O12));
            this.ll.fillRect(0, 0, this.lo, this.lO);
        }
        this.l5 = 0;
        if (this.O18) {
            for (int n = 0; n < this.l3; ++n) {
                this.lll[n] = this.ll.getFontMetrics().stringWidth(this.l9[n]);
                this.l5 += this.lll[n];
            }
            this.l11[0] = 0;
            for (int n2 = 0; n2 < this.l3; ++n2) {
                this.ll1[n2] = (this.lo - this.l5) / this.l3 / 2;
                this.lll[n2] += this.ll1[n2] * 2;
                if (n2 > 0) {
                    this.l11[n2] = this.l11[n2 - 1] + this.lll[n2 - 1];
                }
                this.l1l[n2] = 0;
            }
            this.lll[this.l3 - 1] = this.lo - this.l11[this.l3 - 1];
        }
        else {
            for (int n3 = 0; n3 < this.l3; ++n3) {
                this.l11[n3] = 0;
                this.l1l[n3] = n3 * this.l4;
                this.lll[n3] = this.lo;
                if (this.Ol5.equalsIgnoreCase("center")) {
                    this.ll1[n3] = this.lo / 2 - this.ll.getFontMetrics().stringWidth(this.l9[n3]) / 2;
                }
                if (this.Ol5.equalsIgnoreCase("left")) {
                    this.ll1[n3] = this.O16 / 2;
                }
                if (this.Ol5.equalsIgnoreCase("right")) {
                    this.ll1[n3] = this.lo - this.ll.getFontMetrics().stringWidth(this.l9[n3]) - this.O16 / 2;
                }
            }
        }
        Container parent;
        for (parent = this; parent != null && !(parent instanceof Frame); parent = parent.getParent()) {}
        ((Frame)parent).setCursor(12);
    }
    
    public void lOO(final int n) {
        for (int i = 0; i < this.l3; ++i) {
            if (i != n) {
                this.l0O[i] = 0;
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int oo = this.Oo(n, n2);
        if (oo == -1) {
            return true;
        }
        this.l0O[oo] = 3;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.lOO(-1);
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final int oo = this.Oo(n, n2);
        if (oo == -1) {
            this.lOO(-1);
            return true;
        }
        if (this.l0O[oo] == 0) {
            this.lOO(oo);
            this.l0O[oo] = 1;
            this.lO0[oo] = this.O1l;
        }
        return true;
    }
    
    int oO(final int n) {
        if (n > 255) {
            return 255;
        }
        return n;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void run() {
        while (this.l0 != null) {
            if (this.l2 != null) {
                this.ll.drawImage(this.l2, 0, 0, this);
            }
            for (int i = 0; i < this.l3; ++i) {
                this.OO(i);
            }
            Thread.yield();
            this.getGraphics().drawImage(this.l1, 0, 0, this);
            Thread.yield();
            if (this.l8 != -1) {
                if (!this.l0l[this.l8].startsWith("javascript:")) {
                    if (this.l00[this.l8] != null) {
                        this.getAppletContext().showDocument(this.l00[this.l8], this.l0l[this.l8]);
                    }
                }
                else {
                    this.OllI(this.l0l[this.l8]);
                }
                this.l8 = -1;
            }
            try {
                Thread.sleep(this.Ol1);
            }
            catch (Exception ex) {
                this.l0 = null;
            }
        }
    }
    
    public void start() {
        this.l0 = null;
        (this.l0 = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.l0 != null && this.l0.isAlive()) {
            this.l0.stop();
            this.l0 = null;
        }
    }
    
    public void update(final Graphics graphics) {
        Thread.yield();
        graphics.drawImage(this.l1, 0, 0, this);
        Thread.yield();
    }
}
