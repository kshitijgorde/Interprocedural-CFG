import java.awt.Event;
import netscape.javascript.JSObject;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.MenuItem;
import java.util.StringTokenizer;
import java.awt.Menu;
import java.applet.AudioClip;
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
    Thread lO11;
    int I1;
    int Il;
    Image offimage;
    Image[] lOl1;
    Graphics lIl;
    int II;
    int ll0;
    int lI;
    int l1I;
    int lI0;
    int I10;
    int lII;
    int II1;
    String[] lI1;
    String[] Il1;
    String[] llI;
    String I11;
    String lIo;
    URL[] I1l;
    int[] l11;
    int[] l1l;
    int[] ll1;
    int[] lll;
    int[] I1I;
    int IO;
    int l00;
    int l0O;
    PopupMenu[] Ill;
    AudioClip lOol;
    AudioClip lO1l;
    int delay;
    boolean lO0;
    boolean lOOl;
    boolean lO01;
    boolean lOo1;
    boolean lO0l;
    boolean llo;
    int Il0;
    int II0;
    int l0;
    int I0;
    int lIO;
    int I1O;
    int IIO;
    int IlO;
    int lO;
    String sFontFace;
    String align;
    String stat;
    int l1O;
    int llO;
    int wIcons;
    String sMenuItems;
    String Ol7;
    String ho;
    boolean oo;
    
    public apPopupMenu() {
        this.II1 = -1;
        this.delay = 30;
        this.lO0 = false;
        this.lOOl = false;
        this.lO01 = false;
        this.lOo1 = false;
        this.lO0l = true;
        this.llo = false;
        this.Il0 = 13303482;
        this.II0 = 14352074;
        this.l0 = 16384;
        this.I0 = 47872;
        this.lO = 1;
        this.sFontFace = "";
        this.align = "left";
        this.stat = "link";
        this.l1O = 12;
        this.llO = 0;
        this.wIcons = 0;
        this.Ol7 = "Gthf\"dqrofvv!cw!yzx0dq{fpo1dqp";
        this.ho = "q{fpo1";
        this.oo = false;
    }
    
    private void III(final Menu menu, final int n, final int n2) {
        final String s = new String("__menu" + n + "_");
        for (int i = 0; i < this.II; ++i) {
            if (this.llI[i].indexOf(s) != -1) {
                int n3 = 0;
                int n4 = 0;
                while (new StringTokenizer(this.llI[i], "|", true).nextToken().equals("|")) {
                    ++n3;
                }
                if (n2 > n3) {
                    return;
                }
                this.llI[i] = this.llI[i].substring(0, this.llI[i].indexOf(s));
                final StringTokenizer stringTokenizer = new StringTokenizer(new StringTokenizer(this.llI[i], "|").nextToken(), ",");
                final String nextToken = stringTokenizer.nextToken();
                if (stringTokenizer.hasMoreTokens()) {
                    final MenuItem menuItem = new MenuItem(nextToken);
                    menuItem.addActionListener(new ActionListener() {
                        private final /* synthetic */ String val$trg = stringTokenizer.nextToken();
                        private final /* synthetic */ String val$slnk = val$slnk;
                        
                        public void actionPerformed(final ActionEvent actionEvent) {
                            if (apPopupMenu.this.lO1l != null) {
                                apPopupMenu.this.lO1l.play();
                            }
                            try {
                                if (!this.val$slnk.startsWith("javascript:")) {
                                    apPopupMenu.this.getAppletContext().showDocument(new URL(apPopupMenu.this.getCodeBase(), this.val$slnk), this.val$trg);
                                }
                                else {
                                    apPopupMenu.this.lOO1(this.val$slnk);
                                }
                            }
                            catch (Exception ex) {}
                        }
                    });
                    menu.add(menuItem);
                    if (!this.llo) {
                        menu.setFont(new Font(this.sFontFace, this.llO, this.l1O));
                        menuItem.setFont(new Font(this.sFontFace, this.llO, this.l1O));
                    }
                }
                else {
                    if (!nextToken.startsWith("-")) {
                        final Menu menu2 = new Menu(nextToken);
                        if (!this.llo) {
                            menu2.setFont(new Font(this.sFontFace, this.llO, this.l1O));
                        }
                        this.III(menu2, n, n2 + 1);
                        menu.add(menu2);
                        continue;
                    }
                    menu.addSeparator();
                }
                if (i + 1 < this.II && this.llI[i + 1].indexOf(new String("__menu" + n + "_")) != -1) {
                    while (new StringTokenizer(this.llI[i + 1], "|", true).nextToken().equals("|")) {
                        ++n4;
                    }
                }
                if (n4 < n3) {
                    return;
                }
            }
        }
    }
    
    public void IIl(final int n, final int n2, final int n3) {
        if (!this.Il1[n].startsWith("__menu") || !this.lO0l) {
            return;
        }
        int n4 = this.lIO;
        int n5 = this.I1O;
        int n6 = this.l0;
        if (n3 != 0) {
            n4 = this.IIO;
            n5 = this.IlO;
            n6 = this.I0;
        }
        if (this.lO0) {
            int n7 = this.l1O - this.l1O / 3;
            final int n8 = this.l1l[n] + this.l1I / 2 - n7 / 2 + n2 + 1;
            final int n9 = this.l1l[n] + this.l1I / 2 + n7 / 2 + n2;
            int n10 = this.l11[n] + this.ll1[n] - this.lll[n] - this.l1O / 3 * 2;
            if (this.lOl1[n] != null) {
                n10 += this.lOl1[n].getWidth(this);
            }
            if (this.lI1[n] == "") {
                n10 = this.l11[n] + this.lll[n];
            }
            if (this.lO01) {
                n7 -= 2;
                final int[] array = new int[3];
                final int[] array2 = new int[3];
                array[0] = n10;
                array2[0] = n8 + 2;
                array[1] = n10 + n7;
                array2[1] = n8 + 2;
                array[2] = n10 + n7 / 2;
                array2[2] = n9 - 2;
                this.lIl.setColor(new Color(n6));
                this.lIl.drawPolygon(array, array2, array.length);
                this.lIl.fillPolygon(array, array2, array.length);
            }
            else {
                this.lIl.setColor(new Color(n5));
                this.lIl.drawLine(n10, n8, n10 + n7, n8);
                this.lIl.drawLine(n10, n8, n10 + n7 / 2, n9);
                this.lIl.setColor(new Color(n4));
                this.lIl.drawLine(n10 + n7 / 2, n9, n10 + n7, n8);
            }
        }
        else {
            final int n11 = this.l1l[n] + this.l1I / 2 - this.l1O / 2 - 1 + n2;
            final int n12 = this.l1l[n] + this.l1I / 2 + this.l1O / 2 - 1 + n2;
            final int n13 = this.l1l[n] + this.l1I / 2 - 1 + n2;
            final int n14 = this.l1O + 1;
            if (this.lO01) {
                final int[] array3 = new int[3];
                final int[] array4 = new int[3];
                array3[0] = this.l11[n] + this.ll1[n] - n14;
                array4[0] = n11 + 2;
                array3[1] = this.l11[n] + this.ll1[n] - n14 + this.l1O / 3;
                array4[1] = n13;
                array3[2] = this.l11[n] + this.ll1[n] - n14;
                array4[2] = n12 - 2;
                this.lIl.setColor(new Color(n6));
                this.lIl.drawPolygon(array3, array4, array3.length);
                this.lIl.fillPolygon(array3, array4, array3.length);
            }
            else {
                this.lIl.setColor(new Color(n5));
                this.lIl.drawLine(this.l11[n] + this.ll1[n] - n14, n11, this.l11[n] + this.ll1[n] - n14, n12);
                this.lIl.setColor(new Color(n4));
                this.lIl.drawLine(this.l11[n] + this.ll1[n] - n14, n11, this.l11[n] + this.ll1[n] - n14 + this.l1O / 3 * 2, n13);
                this.lIl.drawLine(this.l11[n] + this.ll1[n] - n14, n12, this.l11[n] + this.ll1[n] - n14 + this.l1O / 3 * 2, n13);
            }
        }
    }
    
    public void IlI(final int n, final int n2, final int n3) {
        int n4 = this.lIO;
        int n5 = this.I1O;
        if (n3 != 0) {
            n4 = this.IIO;
            n5 = this.IlO;
        }
        switch (n2) {
            default: {
                this.lIl.setColor(new Color(n4));
                this.lIl.drawLine(this.l11[n], this.l1l[n], this.l11[n] + this.ll1[n] - 1, this.l1l[n]);
                this.lIl.drawLine(this.l11[n], this.l1l[n], this.l11[n], this.l1l[n] + this.l1I - 1);
                this.lIl.setColor(new Color(n5));
                this.lIl.drawLine(this.l11[n] + this.ll1[n] - 1, this.l1l[n] + this.l1I - 1, this.l11[n] + this.ll1[n] - 1, this.l1l[n]);
                this.lIl.drawLine(this.l11[n] + this.ll1[n] - 1, this.l1l[n] + this.l1I - 1, this.l11[n] + 1, this.l1l[n] + this.l1I - 1);
                break;
            }
            case 1: {
                this.lIl.setColor(new Color(n5));
                this.lIl.drawLine(this.l11[n], this.l1l[n], this.l11[n] + this.ll1[n] - 1, this.l1l[n]);
                this.lIl.drawLine(this.l11[n], this.l1l[n], this.l11[n], this.l1l[n] + this.l1I - 1);
                this.lIl.setColor(new Color(n4));
                this.lIl.drawLine(this.l11[n] + this.ll1[n] - 1, this.l1l[n] + this.l1I - 1, this.l11[n] + this.ll1[n] - 1, this.l1l[n]);
                this.lIl.drawLine(this.l11[n] + this.ll1[n] - 1, this.l1l[n] + this.l1I - 1, this.l11[n] + 1, this.l1l[n] + this.l1I - 1);
                break;
            }
            case 2: {
                this.lIl.setColor(new Color(n4));
                this.lIl.drawLine(this.l11[n] + 1, this.l1l[n] + 1, this.l11[n] + this.ll1[n] - 2, this.l1l[n] + 1);
                this.lIl.drawLine(this.l11[n] + 1, this.l1l[n] + 1, this.l11[n] + 1, this.l1l[n] + this.l1I - 2);
                this.lIl.setColor(new Color(0));
                this.lIl.drawLine(this.l11[n] + this.ll1[n] - 1, this.l1l[n] + this.l1I - 1, this.l11[n] + this.ll1[n] - 1, this.l1l[n]);
                this.lIl.drawLine(this.l11[n] + this.ll1[n] - 1, this.l1l[n] + this.l1I - 1, this.l11[n] + 1, this.l1l[n] + this.l1I - 1);
                this.lIl.setColor(new Color(n5));
                this.lIl.drawLine(this.l11[n] + this.ll1[n] - 2, this.l1l[n] + this.l1I - 2, this.l11[n] + this.ll1[n] - 2, this.l1l[n] + 1);
                this.lIl.drawLine(this.l11[n] + this.ll1[n] - 2, this.l1l[n] + this.l1I - 2, this.l11[n] + 2, this.l1l[n] + this.l1I - 2);
                break;
            }
            case 3: {
                this.lIl.setColor(new Color(n5));
                this.lIl.drawLine(this.l11[n] + 1, this.l1l[n] + 1, this.l11[n] + this.ll1[n] - 2, this.l1l[n] + 1);
                this.lIl.drawLine(this.l11[n] + 1, this.l1l[n] + 1, this.l11[n] + 1, this.l1l[n] + this.l1I - 2);
                this.lIl.setColor(new Color(0));
                this.lIl.drawLine(this.l11[n], this.l1l[n], this.l11[n] + this.ll1[n] - 1, this.l1l[n]);
                this.lIl.drawLine(this.l11[n], this.l1l[n], this.l11[n], this.l1l[n] + this.l1I - 1);
                this.lIl.setColor(new Color(n4));
                this.lIl.drawLine(this.l11[n] + this.ll1[n] - 2, this.l1l[n] + this.l1I - 2, this.l11[n] + this.ll1[n] - 2, this.l1l[n] + 1);
                this.lIl.setColor(new Color(n4));
                this.lIl.drawLine(this.l11[n] + this.ll1[n] - 1, this.l1l[n] + this.l1I - 1, this.l11[n] + this.ll1[n] - 1, this.l1l[n]);
                this.lIl.drawLine(this.l11[n] + this.ll1[n] - 1, this.l1l[n] + this.l1I - 1, this.l11[n] + 1, this.l1l[n] + this.l1I - 1);
                break;
            }
        }
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
        this.I11 = this.getParameter("buttonType");
        if (this.I11 != null) {
            this.lO = Integer.parseInt(this.I11);
        }
        this.I11 = this.getParameter("backColor");
        if (this.I11 != null) {
            this.Il0 = Integer.parseInt(this.I11, 16);
            this.II0 = this.Il0;
        }
        this.I11 = this.getParameter("fontColor");
        if (this.I11 != null) {
            this.l0 = Integer.parseInt(this.I11, 16);
            this.I0 = this.l0;
        }
        this.I11 = this.getParameter("backHighColor");
        if (this.I11 != null) {
            this.II0 = Integer.parseInt(this.I11, 16);
        }
        this.I11 = this.getParameter("fontHighColor");
        if (this.I11 != null) {
            this.I0 = Integer.parseInt(this.I11, 16);
        }
        this.I11 = this.getParameter("isHorizontal");
        if (this.I11 != null && this.I11.equalsIgnoreCase("true")) {
            this.lO0 = true;
        }
        this.I11 = this.getParameter("3DBorder");
        if (this.I11 != null && this.I11.equalsIgnoreCase("true")) {
            this.lOOl = true;
        }
        this.I11 = this.getParameter("solidArrows");
        if (this.I11 != null && this.I11.equalsIgnoreCase("true")) {
            this.lO01 = true;
        }
        this.I11 = this.getParameter("popupOver");
        if (this.I11 != null && this.I11.equalsIgnoreCase("true")) {
            this.lOo1 = true;
        }
        this.I11 = this.getParameter("showArrows");
        if (this.I11 != null && this.I11.equalsIgnoreCase("false")) {
            this.lO0l = false;
        }
        this.I11 = this.getParameter("systemSubFont");
        if (this.I11 != null && this.I11.equalsIgnoreCase("true")) {
            this.llo = true;
        }
        this.I11 = this.getParameter("alignText");
        if (this.I11 != null) {
            this.align = this.I11;
        }
        this.I11 = this.getParameter("status");
        if (this.I11 != null) {
            this.stat = this.I11;
        }
        this.I11 = this.getParameter("statusString");
        if (this.I11 != null) {
            this.lIo = this.I11;
        }
        this.I11 = this.getParameter("overSound");
        if (this.I11 != null) {
            this.lOol = this.getAudioClip(this.getCodeBase(), this.I11);
        }
        this.I11 = this.getParameter("clickSound");
        if (this.I11 != null) {
            this.lO1l = this.getAudioClip(this.getCodeBase(), this.I11);
        }
        this.I11 = this.getParameter("font");
        if (this.I11 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.I11, ",");
            this.sFontFace = stringTokenizer.nextToken();
            this.l1O = Integer.parseInt(stringTokenizer.nextToken());
            this.llO = Integer.parseInt(stringTokenizer.nextToken());
        }
        this.I11 = this.getParameter("menuItems");
        this.I11 = this.I11.substring(0, this.I11.lastIndexOf("}"));
        this.lI = new StringTokenizer(this.I11, "}").countTokens();
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.I11, "}");
        while (stringTokenizer2.hasMoreTokens()) {
            final String nextToken = stringTokenizer2.nextToken();
            if (nextToken.substring(nextToken.indexOf("{") + 1).trim().startsWith("|")) {
                ++this.II;
            }
        }
        final StringTokenizer stringTokenizer3 = new StringTokenizer(this.I11, "}");
        this.ll0 = this.lI;
        this.lI = this.ll0 - this.II;
        this.I1I = new int[this.lI];
        this.l11 = new int[this.lI];
        this.l1l = new int[this.lI];
        this.ll1 = new int[this.lI];
        this.lll = new int[this.lI];
        this.lI1 = new String[this.lI];
        this.I1l = new URL[this.lI];
        this.Il1 = new String[this.lI];
        this.Ill = new PopupMenu[this.lI];
        this.llI = new String[this.II];
        this.lOl1 = new Image[this.lI];
        this.lI0 = 0;
        this.I10 = 0;
        int n = 0;
        while (stringTokenizer3.hasMoreTokens()) {
            this.I11 = stringTokenizer3.nextToken();
            this.I11 = this.I11.substring(this.I11.indexOf("{") + 1);
            final StringTokenizer stringTokenizer4 = new StringTokenizer(this.I11, ",");
            if (!this.I11.trim().startsWith("|")) {
                this.lI1[this.I10] = new String(stringTokenizer4.nextToken());
                if (this.lI1[this.I10].equals("_")) {
                    this.lI1[this.I10] = "";
                }
                if (this.lI1[this.I10].trim().startsWith("-")) {
                    ++n;
                    ++this.I10;
                }
                else if (!stringTokenizer4.hasMoreTokens()) {
                    ++this.I10;
                }
                else {
                    if (stringTokenizer4.countTokens() == 1) {
                        this.lOl1[this.I10] = this.getImage(this.getDocumentBase(), stringTokenizer4.nextToken());
                        final MediaTracker mediaTracker = new MediaTracker(this);
                        mediaTracker.addImage(this.lOl1[this.I10], 0);
                        try {
                            mediaTracker.waitForAll();
                        }
                        catch (Exception ex) {}
                        this.wIcons = this.lOl1[this.I10].getWidth(this);
                    }
                    else {
                        this.I11 = stringTokenizer4.nextToken();
                        if (!this.I11.startsWith("javascript:")) {
                            try {
                                this.I1l[this.I10] = new URL(this.getCodeBase(), this.I11);
                            }
                            catch (Exception ex2) {}
                            this.Il1[this.I10] = new String(stringTokenizer4.nextToken());
                        }
                        else {
                            this.Il1[this.I10] = new String(this.I11);
                            this.I11 = stringTokenizer4.nextToken();
                        }
                        if (stringTokenizer4.hasMoreTokens()) {
                            this.lOl1[this.I10] = this.getImage(this.getDocumentBase(), stringTokenizer4.nextToken());
                            final MediaTracker mediaTracker2 = new MediaTracker(this);
                            mediaTracker2.addImage(this.lOl1[this.I10], 0);
                            try {
                                mediaTracker2.waitForAll();
                            }
                            catch (Exception ex3) {}
                            this.wIcons = this.lOl1[this.I10].getWidth(this);
                        }
                    }
                    ++this.I10;
                }
            }
            else {
                this.Il1[this.I10 - 1] = new String("__menu" + (this.I10 - 1) + "_");
                this.llI[this.lI0] = new String(String.valueOf(this.I11.trim()) + this.Il1[this.I10 - 1]);
                ++this.lI0;
            }
        }
        for (int i = 0; i < this.lI; ++i) {
            if (this.Il1[i] != null && this.Il1[i].startsWith("__menu")) {
                this.III(this.Ill[i] = new PopupMenu(), i, 1);
                this.add(this.Ill[i]);
            }
        }
        this.IO = (this.II0 & 0xFF0000) >> 16;
        this.l00 = (this.II0 & 0xFF00) >> 8;
        this.l0O = (this.II0 & 0xFF);
        this.IlO = (this.l10(this.IO * 2 / 3) << 16) + (this.l10(this.l00 * 2 / 3) << 8) + this.l10(this.l0O * 2 / 3);
        this.IIO = (this.l10((255 - this.IO) / 2 + this.IO) << 16) + (this.l10((255 - this.l00) / 2 + this.l00) << 8) + this.l10((255 - this.l0O) / 2 + this.l0O);
        this.IO = (this.Il0 & 0xFF0000) >> 16;
        this.l00 = (this.Il0 & 0xFF00) >> 8;
        this.l0O = (this.Il0 & 0xFF);
        this.I1O = (this.l10(this.IO * 2 / 3) << 16) + (this.l10(this.l00 * 2 / 3) << 8) + this.l10(this.l0O * 2 / 3);
        this.lIO = (this.l10((255 - this.IO) / 2 + this.IO) << 16) + (this.l10((255 - this.l00) / 2 + this.l00) << 8) + this.l10((255 - this.l0O) / 2 + this.l0O);
        if (!this.lO0) {
            this.l1I = (this.Il - n * 8) / (this.lI - n);
        }
        else {
            this.l1I = this.Il;
        }
        this.offimage = this.createImage(this.I1, this.Il);
        (this.lIl = this.offimage.getGraphics()).setFont(new Font(this.sFontFace, this.llO, this.l1O));
        this.lII = this.l1I / 2 + this.l1O / 2 - 1;
        this.lIl.setColor(new Color(this.Il0));
        this.lIl.fillRect(0, 0, this.I1, this.Il);
        this.lI0 = 0;
        if (this.lO0) {
            for (int j = 0; j < this.lI; ++j) {
                if (!this.lI1[j].trim().startsWith("-")) {
                    this.ll1[j] = this.lIl.getFontMetrics().stringWidth(this.lI1[j]);
                    if (this.Il1[j].startsWith("__menu") && this.lO0l) {
                        final int[] ll1 = this.ll1;
                        final int n2 = j;
                        ll1[n2] += this.l1O;
                    }
                    if (this.lOl1[j] != null) {
                        final int[] ll2 = this.ll1;
                        final int n3 = j;
                        ll2[n3] += this.lOl1[j].getWidth(this) + 3;
                    }
                    this.lI0 += this.ll1[j];
                }
            }
            this.l11[0] = 0;
            for (int k = 0; k < this.lI; ++k) {
                this.lll[k] = (this.I1 - 7 * n - this.lI0) / (this.lI - n) / 2;
                if (this.lI1[k].trim().startsWith("-")) {
                    this.ll1[k] = 7;
                }
                else {
                    this.ll1[k] += this.lll[k] * 2;
                }
                if (k > 0) {
                    this.l11[k] = this.l11[k - 1] + this.ll1[k - 1];
                }
                this.l1l[k] = 0;
                if (this.lOl1[k] != null) {
                    final int[] lll = this.lll;
                    final int n4 = k;
                    lll[n4] += this.lOl1[k].getWidth(this) + 3;
                }
            }
            this.ll1[this.lI - 1] = this.I1 - this.l11[this.lI - 1];
        }
        else {
            this.lI0 = 0;
            for (int l = 0; l < this.lI; ++l) {
                this.l11[l] = 0;
                this.ll1[l] = this.I1;
                if (this.lI1[l].trim().startsWith("-")) {
                    this.l1l[l] = this.lI0;
                    this.lI0 += 8;
                }
                else {
                    this.l1l[l] = this.lI0;
                    this.lI0 += this.l1I;
                    if (this.align.equalsIgnoreCase("center")) {
                        this.lll[l] = this.I1 / 2 - this.lIl.getFontMetrics().stringWidth(this.lI1[l]) / 2;
                    }
                    if (this.align.equalsIgnoreCase("left")) {
                        this.lll[l] = this.l1O / 2 + this.wIcons + 3;
                    }
                    if (this.align.equalsIgnoreCase("right")) {
                        this.lll[l] = this.I1 - this.lIl.getFontMetrics().stringWidth(this.lI1[l]) - this.l1O / 2;
                    }
                }
            }
        }
        Container parent;
        for (parent = this; parent != null && !(parent instanceof Frame); parent = parent.getParent()) {}
        ((Frame)parent).setCursor(12);
    }
    
    public void l1(final int n) {
        final int n2 = 3;
        if (this.lO0) {
            this.lIl.setColor(new Color(this.I1O));
            this.lIl.drawLine(this.l11[n] + n2, this.l1l[n] + n2, this.l11[n] + n2, this.l1l[n] + this.l1I - n2 - 1);
            this.lIl.setColor(new Color(this.lIO));
            this.lIl.drawLine(this.l11[n] + n2 + 1, this.l1l[n] + n2, this.l11[n] + n2 + 1, this.l1l[n] + this.l1I - n2 - 1);
            this.lIl.drawLine(this.l11[n] + n2, this.l1l[n] + this.l1I - n2 - 1, this.l11[n] + n2 + 1, this.l1l[n] + this.l1I - n2 - 1);
        }
        else {
            this.lIl.setColor(new Color(this.I1O));
            this.lIl.drawLine(this.l11[n] + n2, this.l1l[n] + n2, this.l11[n] + this.ll1[n] - n2 - 1, this.l1l[n] + n2);
            this.lIl.setColor(new Color(this.lIO));
            this.lIl.drawLine(this.l11[n] + n2, this.l1l[n] + n2 + 1, this.l11[n] + this.ll1[n] - n2 - 1, this.l1l[n] + n2 + 1);
            this.lIl.drawLine(this.l11[n] + this.ll1[n] - n2 - 1, this.l1l[n] + n2 + 1, this.l11[n] + this.ll1[n] - n2 - 1, this.l1l[n] + n2);
        }
    }
    
    int l10(final int n) {
        if (n > 255) {
            return 255;
        }
        if (n < 0) {
            return 0;
        }
        return n;
    }
    
    public int lOO(final int n, final int n2) {
        if (n2 > this.Il || n > this.I1) {
            return -1;
        }
        for (int i = 0; i < this.lI; ++i) {
            if (this.lO0) {
                if (n <= this.l11[i] + this.ll1[i]) {
                    return i;
                }
            }
            else {
                if (i + 1 >= this.lI) {
                    return i;
                }
                if (n2 <= this.l1l[i + 1]) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public void lOO1(String s) {
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
    
    public void lOll(final int n, final int n2) {
        if (this.lOl1[n] == null) {
            return;
        }
        if (this.lO0) {
            this.lIl.drawImage(this.lOl1[n], this.l11[n] + this.lll[n] - this.lOl1[n].getWidth(this) - 3, this.l1l[n] + (this.l1I - this.lOl1[n].getHeight(this)) / 2 + n2, this);
        }
        else {
            this.lIl.drawImage(this.lOl1[n], this.l11[n] + 3, this.l1l[n] + (this.l1I - this.lOl1[n].getHeight(this)) / 2 + n2, this);
        }
    }
    
    public void ll(final int n) {
        if (this.lI1[n].trim().startsWith("-")) {
            this.l1(n);
            return;
        }
        switch (this.I1I[n]) {
            case 0: {
                this.lIl.setColor(new Color(this.Il0));
                this.lIl.fillRect(this.l11[n], this.l1l[n], this.ll1[n], this.l1I);
                this.lIl.setColor(new Color(this.l0));
                this.lIl.drawString(this.lI1[n], this.l11[n] + this.lll[n], this.l1l[n] + this.lII);
                switch (this.lO) {
                    case 2:
                    case 5: {
                        this.IlI(n, 0, 0);
                        break;
                    }
                    case 4: {
                        this.IlI(n, 2, 0);
                        break;
                    }
                }
                this.lOll(n, 0);
                this.IIl(n, 0, 0);
                break;
            }
            case 2: {
                this.lIl.setColor(new Color(this.II0));
                this.lIl.fillRect(this.l11[n], this.l1l[n], this.ll1[n], this.l1I);
                this.lIl.setColor(new Color(this.I0));
                this.lIl.drawString(this.lI1[n], this.l11[n] + this.lll[n], this.l1l[n] + this.lII);
                switch (this.lO) {
                    case 1:
                    case 2: {
                        this.IlI(n, 0, 1);
                        break;
                    }
                    case 3:
                    case 4:
                    case 5: {
                        this.IlI(n, 2, 1);
                        break;
                    }
                }
                this.lOll(n, 0);
                this.IIl(n, 0, 1);
                break;
            }
            case 1: {
                this.lIl.setColor(new Color(this.II0));
                this.lIl.fillRect(this.l11[n], this.l1l[n], this.ll1[n], this.l1I);
                this.lIl.setColor(new Color(this.I0));
                this.lIl.drawString(this.lI1[n], this.l11[n] + this.lll[n], this.l1l[n] + this.lII);
                switch (this.lO) {
                    case 1:
                    case 2: {
                        this.IlI(n, 0, 1);
                        break;
                    }
                    case 3:
                    case 4:
                    case 5: {
                        this.IlI(n, 2, 1);
                        break;
                    }
                }
                this.lOll(n, 0);
                if (this.lOol != null) {
                    this.lOol.play();
                }
                this.IIl(n, 0, 1);
                this.reState(n);
                this.I1I[n] = 2;
                if (this.lIo == null) {
                    if (this.stat.equalsIgnoreCase("link") && !this.Il1[n].startsWith("__menu") && this.I1l[n] != null) {
                        this.showStatus(this.I1l[n].toString());
                    }
                    else {
                        this.showStatus(this.lI1[n].trim());
                    }
                }
                else {
                    this.showStatus(this.lIo);
                }
                if (this.lOo1 && this.Il1[n].startsWith("__menu")) {
                    this.reState(n);
                    this.I1I[n] = 4;
                    this.II1 = n;
                }
                if (!this.oo) {
                    this.showStatus(this.hO(this.Ol7));
                    break;
                }
                break;
            }
            case 3: {
                this.lIl.setColor(new Color(this.II0));
                this.lIl.fillRect(this.l11[n], this.l1l[n], this.ll1[n], this.l1I);
                this.lIl.setColor(new Color(this.I0));
                this.lIl.drawString(this.lI1[n], this.l11[n] + this.lll[n], this.l1l[n] + this.lII + 1);
                switch (this.lO) {
                    case 1:
                    case 2: {
                        this.IlI(n, 1, 1);
                        break;
                    }
                    case 3:
                    case 4:
                    case 5: {
                        this.IlI(n, 3, 1);
                        break;
                    }
                }
                this.lOll(n, 1);
                if (this.lO1l != null) {
                    this.lO1l.play();
                }
                this.IIl(n, 1, 1);
                this.reState(n);
                this.I1I[n] = 4;
                this.II1 = n;
                break;
            }
            case 4: {
                this.lIl.setColor(new Color(this.II0));
                this.lIl.fillRect(this.l11[n], this.l1l[n], this.ll1[n], this.l1I);
                this.lIl.setColor(new Color(this.I0));
                this.lIl.drawString(this.lI1[n], this.l11[n] + this.lll[n], this.l1l[n] + this.lII + 1);
                switch (this.lO) {
                    case 1:
                    case 2: {
                        this.IlI(n, 1, 1);
                        break;
                    }
                    case 3:
                    case 4:
                    case 5: {
                        this.IlI(n, 3, 1);
                        break;
                    }
                }
                this.lOll(n, 1);
                this.IIl(n, 1, 1);
                break;
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int loo = this.lOO(n, n2);
        if (loo == -1) {
            return true;
        }
        this.I1I[loo] = 3;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.reState(-1);
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final int loo = this.lOO(n, n2);
        if (loo == -1) {
            this.reState(-1);
            return true;
        }
        if (this.I1I[loo] == 0) {
            this.reState(loo);
            this.I1I[loo] = 1;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void reState(final int n) {
        for (int i = 0; i < this.lI; ++i) {
            if (i != n) {
                this.I1I[i] = 0;
            }
        }
    }
    
    public void run() {
        while (this.lO11 != null) {
            for (int i = 0; i < this.lI; ++i) {
                this.ll(i);
            }
            if (this.lOOl) {
                this.lIl.setColor(new Color(this.lIO));
                this.lIl.drawLine(0, 0, this.I1, 0);
                this.lIl.drawLine(0, 0, 0, this.Il);
                this.lIl.setColor(new Color(this.I1O));
                this.lIl.drawLine(this.I1 - 1, this.Il - 1, this.I1 - 1, 0);
                this.lIl.drawLine(this.I1 - 1, this.Il - 1, 1, this.Il - 1);
            }
            Thread.yield();
            this.getGraphics().drawImage(this.offimage, 0, 0, this);
            Thread.yield();
            if (this.II1 != -1) {
                if (this.Il1[this.II1].startsWith("__menu")) {
                    for (int j = 0; j < this.lI; ++j) {
                        this.ll(j);
                    }
                    if (this.lOOl) {
                        this.lIl.setColor(new Color(this.lIO));
                        this.lIl.drawLine(0, 0, this.I1, 0);
                        this.lIl.drawLine(0, 0, 0, this.Il);
                        this.lIl.setColor(new Color(this.I1O));
                        this.lIl.drawLine(this.I1 - 1, this.Il - 1, this.I1 - 1, 0);
                        this.lIl.drawLine(this.I1 - 1, this.Il - 1, 1, this.Il - 1);
                    }
                    Thread.yield();
                    this.getGraphics().drawImage(this.offimage, 0, 0, this);
                    Thread.yield();
                    if (this.lO0) {
                        this.Ill[this.II1].show(this, this.l11[this.II1], this.l1l[this.II1] + this.l1I);
                    }
                    else {
                        this.Ill[this.II1].show(this, this.l11[this.II1] + this.ll1[this.II1], this.l1l[this.II1]);
                    }
                }
                else if (!this.Il1[this.II1].startsWith("javascript:")) {
                    if (this.I1l[this.II1] != null) {
                        this.getAppletContext().showDocument(this.I1l[this.II1], this.Il1[this.II1]);
                    }
                }
                else {
                    this.lOO1(this.Il1[this.II1]);
                }
                this.II1 = -1;
            }
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {
                this.lO11 = null;
            }
        }
    }
    
    public void start() {
        this.lO11 = null;
        (this.lO11 = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.lO11 != null && this.lO11.isAlive()) {
            this.lO11.stop();
            this.lO11 = null;
        }
    }
    
    public void update(final Graphics graphics) {
        Thread.yield();
        graphics.drawImage(this.offimage, 0, 0, this);
        Thread.yield();
    }
}
