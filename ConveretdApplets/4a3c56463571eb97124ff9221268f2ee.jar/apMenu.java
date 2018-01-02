import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Container;
import java.io.DataInputStream;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.StringTokenizer;
import netscape.javascript.JSObject;
import java.awt.Frame;
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
    Thread III;
    int II1;
    int IIl;
    Image I1I;
    Image I1l;
    Graphics IlI;
    int lII;
    int lI1;
    int lIl;
    int l1I;
    int Il;
    String[][] l1l;
    String[] llI;
    String l1II;
    String l1I1;
    String llllI;
    URL[] l1Il;
    int[] l111;
    int[] l11l;
    int[] l1lI;
    int[] lll11;
    int[][] l1l1;
    int[] lll1I;
    int[] llll1;
    int[] lI;
    int[] l1;
    int[] ll;
    int[] ll1II;
    int lII1;
    int lIIl;
    int lI1I;
    int ll1I1;
    int ll1Il;
    int ll11I;
    int ll111;
    int ll11l;
    int ll1lI;
    int ll1l1;
    int ll1ll;
    int llIII;
    AudioClip lIlI;
    AudioClip lIl1;
    Frame lIll;
    int llII1;
    int llIIl;
    boolean llII;
    boolean lllI;
    boolean lll1;
    boolean llll;
    boolean llI1I;
    int I1II;
    int I1I1;
    int I1Il;
    int I11I;
    int llI11;
    String IIII;
    String III1;
    String IIIl;
    String II1I;
    int II11;
    int II1l;
    
    public apMenu() {
        this.Il = -1;
        this.llllI = "Loading...";
        this.llII1 = 20;
        this.llIIl = 30;
        this.llII = false;
        this.lllI = false;
        this.lll1 = true;
        this.llll = false;
        this.llI1I = true;
        this.I1II = 13303482;
        this.I1I1 = 1404929;
        this.I1Il = 16384;
        this.I11I = 16777215;
        this.llI11 = 0;
        this.IIII = "";
        this.III1 = "";
        this.IIIl = "link";
        this.II1I = ",";
        this.II11 = 14;
        this.II1l = 0;
    }
    
    String I(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)(charArray[i] - i % 3 - 1);
        }
        return new String(charArray);
    }
    
    public void II(String s) {
        if ("0123456789".indexOf(s.substring(11, 12)) != -1) {
            s = this.getParameter(s);
        }
        if (s != null) {
            if (s.startsWith("javascript:")) {
                s = s.substring(11);
            }
            try {
                JSObject.getWindow((Applet)this).eval(s);
            }
            catch (Throwable t) {
                try {
                    JSObject.getWindow((Applet)this);
                    String nextToken = "";
                    final StringTokenizer stringTokenizer = new StringTokenizer(s, "()");
                    s = stringTokenizer.nextToken().trim();
                    if (stringTokenizer.hasMoreTokens()) {
                        nextToken = stringTokenizer.nextToken();
                    }
                    final StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken.trim(), "'");
                    final String[] array = new String[16];
                    int n = 0;
                    array[0] = "";
                    while (stringTokenizer2.hasMoreTokens()) {
                        array[n] = stringTokenizer2.nextToken().trim();
                        if (array[n].equals(",")) {
                            array[n] = null;
                        }
                        else {
                            ++n;
                        }
                    }
                    JSObject.getWindow((Applet)this).call(s, (Object[])array);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void IIl1() {
        this.lII = 0;
        this.l1II = this.getParameter("Copyright");
        if (this.l1II != null && this.l1II.indexOf("Apycom") != -1) {
            this.llll = true;
        }
        this.l1II = this.getParameter("backColor");
        if (this.l1II != null) {
            if (this.l1II.equalsIgnoreCase("system")) {
                this.I1II = SystemColor.control.getRGB();
            }
            else {
                this.I1II = Integer.parseInt(this.l1II, 16);
            }
            this.I1I1 = this.I1II;
        }
        this.l1II = this.getParameter("fontColor");
        if (this.l1II != null) {
            if (this.l1II.equalsIgnoreCase("system")) {
                this.I1Il = SystemColor.controlText.getRGB();
            }
            else {
                this.I1Il = Integer.parseInt(this.l1II, 16);
            }
            this.I11I = this.I1Il;
        }
        this.l1II = this.getParameter("loadingString");
        if (this.l1II != null) {
            this.llllI = this.l1II;
        }
        this.II1 = this.size().width;
        this.IIl = this.size().height;
        this.I1I = this.createImage(this.II1, this.IIl);
        (this.IlI = this.I1I.getGraphics()).setFont(new Font("Arial", 0, 11));
        this.IlI.setColor(new Color(this.I1II));
        this.IlI.fillRect(0, 0, this.II1, this.IIl);
        this.IlI.setColor(new Color(this.I1Il));
        this.IlI.drawString(this.llllI, 10, 15);
        this.IlI1();
        if (this.getParameter("backPic") != null) {
            final Image ilII = this.IlII(this.getParameter("backPic"));
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
                this.IlI.drawString(this.llllI, 10, 15);
                this.IlI1();
            }
        }
        this.l1II = this.getParameter("fadeDelay");
        if (this.l1II != null) {
            this.llII1 = Integer.parseInt(this.l1II);
        }
        if (this.llII1 < 5) {
            this.llII1 = 5;
        }
        this.l1II = this.getParameter("fadeSteps");
        if (this.l1II != null) {
            this.llIIl = Integer.parseInt(this.l1II);
        }
        if (this.llIIl < 1) {
            this.llIIl = 1;
        }
        this.l1II = this.getParameter("fadeType");
        if (this.l1II != null) {
            this.llI11 = Integer.parseInt(this.l1II);
        }
        this.l1II = this.getParameter("backHighColor");
        if (this.l1II != null) {
            if (this.l1II.equalsIgnoreCase("system")) {
                this.I1I1 = SystemColor.textHighlight.getRGB();
            }
            else {
                this.I1I1 = Integer.parseInt(this.l1II, 16);
            }
        }
        this.l1II = this.getParameter("fontHighColor");
        if (this.l1II != null) {
            if (this.l1II.equalsIgnoreCase("system")) {
                this.I11I = SystemColor.textHighlightText.getRGB();
            }
            else {
                this.I11I = Integer.parseInt(this.l1II, 16);
            }
        }
        this.l1II = this.getParameter("isHorizontal");
        if (this.l1II != null && this.l1II.equalsIgnoreCase("true")) {
            this.llII = true;
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
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.l1II, "}");
        this.lII = stringTokenizer2.countTokens();
        this.ll = new int[this.lII];
        this.ll1II = new int[this.lII];
        this.l111 = new int[this.lII];
        this.l11l = new int[this.lII];
        this.l1lI = new int[this.lII];
        this.lll11 = new int[this.lII];
        this.l1l1 = new int[this.lII][10];
        this.lll1I = new int[this.lII];
        this.llll1 = new int[this.lII];
        this.l1l = new String[this.lII][10];
        this.l1Il = new URL[this.lII];
        this.llI = new String[this.lII];
        int n = 0;
        for (int k = 0; k < this.lII; ++k) {
            this.l1II = stringTokenizer2.nextToken();
            this.l1II = this.l1II.substring(this.l1II.indexOf("{") + 1);
            final StringTokenizer stringTokenizer3 = new StringTokenizer(this.l1II, this.II1I);
            String substring = "";
            final int index = this.l1II.indexOf("javascript:");
            final int n2 = this.l1II.lastIndexOf(")") + 1;
            if (index != -1 && n2 > index) {
                substring = this.l1II.substring(index, n2);
            }
            this.l1II = stringTokenizer3.nextToken();
            this.l1l[k][0] = this.l1II;
            final StringTokenizer stringTokenizer4 = new StringTokenizer(this.l1II, "\\");
            int n3 = 0;
            while (stringTokenizer4.hasMoreTokens()) {
                this.l1l[k][n3] = stringTokenizer4.nextToken();
                ++n3;
            }
            if ((this.llll1[k] = n3) > 1) {
                n += n3 - 1;
            }
            if (this.l1l[k][0].equals("_")) {
                this.l1l[k][0] = "";
            }
            this.l1II = stringTokenizer3.nextToken().trim();
            if (!this.l1II.startsWith("javascript:")) {
                try {
                    this.l1Il[k] = new URL(this.getCodeBase(), this.l1II);
                }
                catch (Exception ex3) {}
                this.llI[k] = stringTokenizer3.nextToken();
            }
            else if (substring == "") {
                this.llI[k] = this.l1II;
            }
            else {
                this.llI[k] = substring;
            }
        }
        this.lII1 = (this.I1II & 0xFF0000) >> 16;
        this.lIIl = (this.I1II & 0xFF00) >> 8;
        this.lI1I = (this.I1II & 0xFF);
        this.ll1I1 = (((this.I1I1 & 0xFF0000) >> 16) - this.lII1) / this.llIIl;
        this.ll1Il = (((this.I1I1 & 0xFF00) >> 8) - this.lIIl) / this.llIIl;
        this.ll11I = ((this.I1I1 & 0xFF) - this.lI1I) / this.llIIl;
        this.ll111 = (this.I1Il & 0xFF0000) >> 16;
        this.ll11l = (this.I1Il & 0xFF00) >> 8;
        this.ll1lI = (this.I1Il & 0xFF);
        this.ll1l1 = (((this.I11I & 0xFF0000) >> 16) - this.ll111) / this.llIIl;
        this.ll1ll = (((this.I11I & 0xFF00) >> 8) - this.ll11l) / this.llIIl;
        this.llIII = ((this.I11I & 0xFF) - this.ll1lI) / this.llIIl;
        this.lI = new int[this.llIIl + 1];
        this.l1 = new int[this.llIIl + 1];
        for (int l = 0; l < this.llIIl + 1; ++l) {
            this.lI[l] = (this.IIll(this.ll1I1 * l + this.lII1) << 16) + (this.IIll(this.ll1Il * l + this.lIIl) << 8) + this.IIll(this.ll11I * l + this.lI1I);
            this.l1[l] = (this.IIll(this.ll1l1 * l + this.ll111) << 16) + (this.IIll(this.ll1ll * l + this.ll11l) << 8) + this.IIll(this.llIII * l + this.ll1lI);
        }
        if (!this.llII) {
            this.lI1 = (this.IIl - (this.II11 + 1) * n) / this.lII;
        }
        else {
            this.lI1 = this.IIl;
        }
        this.lIl = 0;
        if (this.llII) {
            for (int n4 = 0; n4 < this.lII; ++n4) {
                this.lll1I[n4] = this.lI1 / 2 - (this.II11 + 1) * (this.llll1[n4] - 1) / 2 + this.II11 / 2 - 1;
                this.l1lI[n4] = 0;
                for (int n5 = 0; n5 < this.llll1[n4]; ++n5) {
                    if (this.l1lI[n4] < this.IlI.getFontMetrics().stringWidth(this.l1l[n4][n5])) {
                        this.l1lI[n4] = this.IlI.getFontMetrics().stringWidth(this.l1l[n4][n5]);
                    }
                }
                this.lIl += this.l1lI[n4];
            }
            this.l111[0] = 0;
            for (int n6 = 0; n6 < this.lII; ++n6) {
                this.lll11[n6] = this.lI1;
                final int n7 = (this.II1 - this.lIl) / this.lII / 2;
                this.l1lI[n6] += n7 * 2;
                if (n6 > 0) {
                    this.l111[n6] = this.l111[n6 - 1] + this.l1lI[n6 - 1];
                }
                this.l11l[n6] = 0;
                for (int n8 = 0; n8 < this.llll1[n6]; ++n8) {
                    this.l1l1[n6][n8] = (this.l1lI[n6] - this.IlI.getFontMetrics().stringWidth(this.l1l[n6][n8])) / 2;
                    if (this.III1.equalsIgnoreCase("right")) {
                        this.l1l1[n6][n8] = this.l1l1[n6][n8] * 2 - n7;
                    }
                    if (this.III1.equalsIgnoreCase("left")) {
                        this.l1l1[n6][n8] = n7;
                    }
                }
            }
            this.l1lI[this.lII - 1] = this.II1 - this.l111[this.lII - 1];
        }
        else {
            this.lIl = 0;
            for (int n9 = 0; n9 < this.lII; ++n9) {
                this.l111[n9] = 0;
                this.l1lI[n9] = this.II1;
                this.lll1I[n9] = this.lI1 / 2 + this.II11 / 2 - 1;
                this.l11l[n9] = this.lIl;
                this.lll11[n9] = this.lI1 + (this.llll1[n9] - 1) * (this.II11 + 1);
                this.lIl += this.lll11[n9];
                for (int n10 = 0; n10 < this.llll1[n9]; ++n10) {
                    this.l1l1[n9][n10] = this.II11 / 2;
                    if (this.III1.equalsIgnoreCase("right")) {
                        this.l1l1[n9][n10] = this.II1 - this.IlI.getFontMetrics().stringWidth(this.l1l[n9][n10]) - this.II11 / 2;
                    }
                    if (this.III1.equalsIgnoreCase("center")) {
                        this.l1l1[n9][n10] = (this.II1 - this.IlI.getFontMetrics().stringWidth(this.l1l[n9][n10])) / 2;
                    }
                }
            }
        }
        Container parent;
        for (parent = this; parent != null && !(parent instanceof Frame); parent = parent.getParent()) {}
        this.lIll = (Frame)parent;
        System.gc();
    }
    
    int IIll(final int n) {
        if (n > 255) {
            return 255;
        }
        return n;
    }
    
    public void IlI1() {
        if (this.I1I == null) {
            return;
        }
        if (this.lllI) {
            if (this.llll) {
                if (this.I1l != null) {
                    this.IlI.drawImage(this.I1l, 0, 0, this);
                }
                this.llI1I = true;
                for (int i = 0; i < this.lII; ++i) {
                    this.IllI(i);
                    if (this.ll1II[i] >= 0) {
                        this.llI1I = false;
                    }
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
        return image;
    }
    
    public int Ill1(final int n, final int n2) {
        for (int i = 0; i < this.lII; ++i) {
            if (new Rectangle(this.l111[i], this.l11l[i], this.l1lI[i], this.lll11[i]).inside(n, n2)) {
                return i;
            }
        }
        return -1;
    }
    
    public synchronized void IllI(final int il) {
        try {
            switch (this.ll[il]) {
                case 0: {
                    if (this.ll1II[il] != -1) {
                        this.lIl = this.ll1II[il];
                        if (this.llI11 != 0 && this.I1l == null) {
                            this.IlI.setColor(new Color(this.I1II));
                            this.IlI.fillRect(this.l111[il], this.l11l[il], this.l1lI[il], this.lll11[il]);
                        }
                        this.IlI.setColor(new Color(this.lI[this.lIl]));
                        if ((this.I1l != null || this.llI11 != 0) && this.lIl < this.llIIl / 2) {
                            switch (this.llI11) {
                                default: {
                                    this.l1I = (int)(this.l1lI[il] / 2.0 - this.l1lI[il] / 2.0 / (this.llIIl / 2.0) * (this.llIIl / 2.0 - this.lIl));
                                    this.IlI.fillRect(this.l111[il], this.l11l[il], this.l1I, this.lll11[il]);
                                    this.IlI.fillRect(this.l111[il] + this.l1lI[il] - this.l1I, this.l11l[il], this.l1I, this.lll11[il]);
                                    break;
                                }
                                case 2: {
                                    this.l1I = (int)(this.lll11[il] / 2.0 - this.lll11[il] / 2.0 / (this.llIIl / 2.0) * (this.llIIl / 2.0 - this.lIl));
                                    this.IlI.fillRect(this.l111[il], this.l11l[il], this.l1lI[il], this.l1I);
                                    this.IlI.fillRect(this.l111[il], this.l11l[il] + this.lll11[il] - this.l1I, this.l1lI[il], this.l1I);
                                    break;
                                }
                                case 3: {
                                    this.l1I = (int)(this.l1lI[il] - this.l1lI[il] / (this.llIIl / 2.0) * (this.llIIl / 2.0 - this.lIl));
                                    this.IlI.fillRect(this.l111[il], this.l11l[il], this.l1I, this.lll11[il] / 2);
                                    this.IlI.fillRect(this.l111[il] + this.l1lI[il] - this.l1I, this.l11l[il] + this.lll11[il] / 2, this.l1I, this.lll11[il] - this.lll11[il] / 2);
                                    break;
                                }
                                case 4: {
                                    this.l1I = (int)(this.lll11[il] - this.lll11[il] / (this.llIIl / 2.0) * (this.llIIl / 2.0 - this.lIl));
                                    this.IlI.fillRect(this.l111[il], this.l11l[il], this.l1lI[il] / 2, this.l1I);
                                    this.IlI.fillRect(this.l111[il] + this.l1lI[il] / 2, this.l11l[il] + this.lll11[il] - this.l1I, this.l1lI[il] - this.l1lI[il] / 2, this.l1I);
                                    break;
                                }
                                case 5: {
                                    this.l1I = (int)(this.l1lI[il] / 2.0 - this.l1lI[il] / 2.0 / (this.llIIl / 2.0) * (this.llIIl / 2.0 - this.lIl));
                                    final int n = (int)(this.lll11[il] / 2.0 - this.lll11[il] / 2.0 / (this.llIIl / 2.0) * (this.llIIl / 2.0 - this.lIl));
                                    this.IlI.fillRect(this.l111[il] - this.l1I + this.l1lI[il] / 2, this.l11l[il] - n + this.lll11[il] / 2, this.l1I * 2, n * 2);
                                    break;
                                }
                                case 6: {
                                    this.l1I = (int)(this.l1lI[il] / 2.0 - this.l1lI[il] / 2.0 / (this.llIIl / 2.0) * (this.llIIl / 2.0 - this.lIl));
                                    this.IlI.fillRect(this.l111[il], this.l11l[il], this.l1I * 2, this.lll11[il]);
                                    break;
                                }
                                case 7: {
                                    this.l1I = (int)(this.l1lI[il] / 2.0 - this.l1lI[il] / 2.0 / (this.llIIl / 2.0) * (this.llIIl / 2.0 - this.lIl));
                                    this.IlI.fillRect(this.l111[il] - this.l1I * 2 + this.l1lI[il], this.l11l[il], this.l1I * 2, this.lll11[il]);
                                    break;
                                }
                                case 8: {
                                    this.l1I = (int)(this.lll11[il] / 2.0 - this.lll11[il] / 2.0 / (this.llIIl / 2.0) * (this.llIIl / 2.0 - this.lIl));
                                    this.IlI.fillRect(this.l111[il], this.l11l[il] + this.lll11[il] / 2 - this.l1I, this.l1lI[il], this.l1I * 2);
                                    break;
                                }
                                case 9: {
                                    this.l1I = (int)(this.l1lI[il] / 2.0 - this.l1lI[il] / 2.0 / (this.llIIl / 2.0) * (this.llIIl / 2.0 - this.lIl));
                                    this.IlI.fillRect(this.l111[il] - this.l1I + this.l1lI[il] / 2, this.l11l[il], this.l1I * 2, this.lll11[il]);
                                    break;
                                }
                                case 10: {
                                    this.l1I = (int)(this.lll11[il] / 2.0 - this.lll11[il] / 2.0 / (this.llIIl / 2.0) * (this.llIIl / 2.0 - this.lIl));
                                    if (this.lIl < this.llIIl / 4) {
                                        this.IlI.fillRect(this.l111[il], this.l11l[il] + this.l1I * 2, this.l1lI[il], this.l1I * 2);
                                        break;
                                    }
                                    this.IlI.fillRect(this.l111[il], this.l11l[il] + this.lll11[il] - this.l1I * 2, this.l1lI[il], this.l1I * 2);
                                    break;
                                }
                                case 11: {
                                    this.l1I = (int)(this.l1lI[il] / 2.0 - this.l1lI[il] / 2.0 / (this.llIIl / 2.0) * (this.llIIl / 2.0 - this.lIl));
                                    if (this.lIl < this.llIIl / 4) {
                                        this.IlI.fillRect(this.l111[il] + this.l1I * 2, this.l11l[il], this.l1I * 2, this.lll11[il]);
                                        break;
                                    }
                                    this.IlI.fillRect(this.l111[il] - this.l1I * 2 + this.l1lI[il], this.l11l[il], this.l1I * 2, this.lll11[il]);
                                    break;
                                }
                                case 12: {
                                    this.l1I = (int)(this.l1lI[il] / 2.0 - this.l1lI[il] / 2.0 / (this.llIIl / 2.0) * (this.llIIl / 2.0 - this.lIl));
                                    if (this.lIl < this.llIIl / 4) {
                                        this.IlI.fillRect(this.l111[il] + this.l1lI[il] - this.l1I * 4, this.l11l[il], this.l1I * 2, this.lll11[il]);
                                        break;
                                    }
                                    this.IlI.fillRect(this.l111[il], this.l11l[il], this.l1I * 2, this.lll11[il]);
                                    break;
                                }
                            }
                        }
                        else {
                            this.IlI.fillRect(this.l111[il], this.l11l[il], this.l1lI[il], this.lll11[il]);
                        }
                        this.IlI.setColor(new Color(this.l1[this.lIl]));
                        this.lllll(il, 0);
                        final int[] ll1II = this.ll1II;
                        --ll1II[il];
                        break;
                    }
                    if (this.I1l != null) {
                        this.IlI.setColor(new Color(this.I1Il));
                        this.lllll(il, 0);
                        break;
                    }
                    break;
                }
                case 1: {
                    this.IlI.setColor(new Color(this.lI[this.llIIl]));
                    this.IlI.fillRect(this.l111[il], this.l11l[il], this.l1lI[il], this.lll11[il]);
                    this.IlI.setColor(new Color(this.l1[this.llIIl]));
                    this.lllll(il, 0);
                    if (this.lIlI != null) {
                        this.lIlI.play();
                    }
                    this.llI1l(il);
                    this.ll[il] = 2;
                    if (this.l1I1 != null) {
                        this.showStatus(this.l1I1);
                        break;
                    }
                    if (this.IIIl.equalsIgnoreCase("link") && this.l1Il[il] != null) {
                        this.showStatus(this.l1Il[il].toString());
                        break;
                    }
                    this.showStatus(this.l1l[il][0].trim());
                    break;
                }
                case 2: {
                    this.IlI.setColor(new Color(this.lI[this.llIIl]));
                    this.IlI.fillRect(this.l111[il], this.l11l[il], this.l1lI[il], this.lll11[il]);
                    this.IlI.setColor(new Color(this.l1[this.llIIl]));
                    this.lllll(il, 0);
                    break;
                }
                case 3: {
                    this.IlI.setColor(new Color(this.lI[this.llIIl]));
                    this.IlI.fillRect(this.l111[il], this.l11l[il], this.l1lI[il], this.lll11[il]);
                    this.lllll(il, 1);
                    this.IlI.setColor(new Color(this.I1Il));
                    this.lllll(il, 0);
                    if (this.lIl1 != null) {
                        this.lIl1.play();
                    }
                    this.llI1l(il);
                    this.ll[il] = 4;
                    this.Il = il;
                    break;
                }
                case 4: {
                    this.IlI.setColor(new Color(this.lI[this.llIIl]));
                    this.IlI.fillRect(this.l111[il], this.l11l[il], this.l1lI[il], this.lll11[il]);
                    this.lllll(il, 1);
                    this.IlI.setColor(new Color(this.I1Il));
                    this.lllll(il, 0);
                    break;
                }
            }
        }
        catch (Exception ex) {}
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
            if (ill1 == -1 || event.id == 505) {
                this.llI1l(-1);
                if (this.lIll != null) {
                    this.lIll.setCursor(0);
                }
                return true;
            }
            if (event.id == 503) {
                if (!this.lll1) {
                    return true;
                }
                this.lll1 = false;
                if (this.ll[ill1] == 0) {
                    if (this.lIll != null) {
                        this.lIll.setCursor(12);
                    }
                    this.llI1l(ill1);
                    this.ll[ill1] = 1;
                    this.ll1II[ill1] = this.llIIl;
                }
                if (this.llI1I) {
                    this.IlI1();
                }
            }
            if (event.id == 501) {
                if (!this.lll1) {
                    return true;
                }
                this.lll1 = false;
                this.ll[ill1] = 3;
            }
        }
        catch (Throwable t) {}
        return this.lll1 = true;
    }
    
    public void llI1l(final int n) {
        for (int i = 0; i < this.lII; ++i) {
            if (i != n) {
                this.ll[i] = 0;
            }
        }
    }
    
    public void lllll(final int n, final int n2) {
        int n3 = 0;
        for (int i = 0; i < this.llll1[n]; ++i) {
            this.IlI.drawString(this.l1l[n][i], this.l111[n] + this.l1l1[n][i], this.l11l[n] + this.lll1I[n] + n3);
            if (n2 == 1) {
                this.IlI.setColor(new Color(this.I1II));
                this.IlI.drawString(this.l1l[n][i], this.l111[n] + this.l1l1[n][i] + 1, this.l11l[n] + this.lll1I[n] + n3);
                this.IlI.drawString(this.l1l[n][i], this.l111[n] + this.l1l1[n][i] - 1, this.l11l[n] + this.lll1I[n] + n3);
                this.IlI.drawString(this.l1l[n][i], this.l111[n] + this.l1l1[n][i], this.l11l[n] + this.lll1I[n] + n3 + 1);
                this.IlI.drawString(this.l1l[n][i], this.l111[n] + this.l1l1[n][i], this.l11l[n] + this.lll1I[n] + n3 - 1);
            }
            n3 += this.II11 + 1;
        }
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
            this.IlI1();
            if (this.Il != -1) {
                if (!this.llI[this.Il].startsWith("javascript:")) {
                    if (this.l1Il[this.Il] != null) {
                        this.getAppletContext().showDocument(this.l1Il[this.Il], this.llI[this.Il]);
                    }
                }
                else {
                    this.II(this.llI[this.Il]);
                }
                this.Il = -1;
            }
            try {
                if (this.llI1I) {
                    Thread.sleep(100L);
                }
                else {
                    Thread.sleep(this.llII1);
                }
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
