import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.FontMetrics;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class popupvideo extends Applet implements Runnable
{
    AudioClip Bdhblf;
    boolean bDhblf;
    double[] BDhblf;
    FontMetrics bdHblf;
    int BdHblf;
    int bDHblf;
    int BDHblf;
    Point[] bdhBlf;
    Rectangle[] BdhBlf;
    int bDhBlf;
    int BDhBlf;
    int bdHBlf;
    String[] BdHBlf;
    Point bDHBlf;
    private Thread Ltht;
    AudioClip BDHBlf;
    Font bdhbLf;
    Graphics BdhbLf;
    Image bDhbLf;
    Image BDhbLf;
    int bdHbLf;
    int BdHbLf;
    int bDHbLf;
    int BDHbLf;
    String[] bdhBLf;
    int BdhBLf;
    int bDhBLf;
    boolean BDhBLf;
    boolean bdHBLf;
    int BdHBLf;
    int bDHBLf;
    int BDHBLf;
    int bdhblF;
    boolean BdhblF;
    boolean bDhblF;
    boolean BDhblF;
    int bdHblF;
    boolean BdHblF;
    boolean bDHblF;
    int BDHblF;
    String bdhBlF;
    String BdhBlF;
    String bDhBlF;
    String BDhBlF;
    String bdHBlF;
    String BdHBlF;
    String bDHBlF;
    String BDHBlF;
    String bdhbLF;
    String BdhbLF;
    String bDhbLF;
    String BDhbLF;
    boolean bdHbLF;
    private boolean lTht;
    private boolean LTht;
    private Font ltHt;
    private Color LtHt;
    private Color lTHt;
    private String LTHt;
    private String lthT;
    private String LthT;
    
    public popupvideo() {
        this.bDhblf = false;
        final double[] bDhblf = { 0.2, 0.4, 0.7, 1.0, 1.3, 1.0 };
        this.BDhBLf = false;
        this.bdHBLf = false;
        this.BdhblF = false;
        this.bDhblF = false;
        this.BDhblF = false;
        this.bdHblF = -1;
        this.BdHblF = false;
        this.bDHblF = false;
        this.BDHblF = 2999;
        this.bdhBlF = "";
        this.BdhBlF = "";
        this.bDhBlF = "";
        this.BDhBlF = "";
        this.bdHBlF = "";
        this.BdHBlF = "-=";
        this.bDHBlF = "       ";
        this.BDHBlF = "(";
        this.bdhbLF = "copy";
        this.BdhbLF = "";
        this.bDhbLF = "";
        this.BDhbLF = "Invalid Copyright Param";
        this.bdHbLF = false;
        this.lTht = false;
        this.LTht = false;
        this.lthT = "";
        this.LthT = "";
        this.lTht = false;
        this.LTht = false;
        this.BDhblf = bDhblf;
    }
    
    static Font bdhblf(final String s, final String s2) {
        Font font = new Font("Arial", 0, 11);
        final String[] array = new String[3];
        int n = 0;
        final String[] array2 = array;
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
            s.trim();
            if (stringTokenizer.countTokens() != 3) {
                return font;
            }
            while (stringTokenizer.hasMoreTokens()) {
                array2[n] = stringTokenizer.nextToken().trim();
                ++n;
            }
            font = new Font(array2[0], zzzc(array2[1]), Integer.parseInt(array2[2]));
        }
        catch (Exception ex) {
            return font;
        }
        return font;
    }
    
    public static int zzzc(final String s) {
        int n = 0;
        if (s.equalsIgnoreCase("bold")) {
            ++n;
        }
        if (s.equalsIgnoreCase("bolditalic")) {
            n += 3;
        }
        if (s.equalsIgnoreCase("italic")) {
            n += 2;
        }
        return n;
    }
    
    public static Color zzzb(final String s) {
        final int n = 0;
        int int1 = 0;
        int int2 = 0;
        int int3 = n;
        s.trim();
        final int index = s.indexOf("#");
        if (index != -1 && index + 1 != s.length()) {
            return new Color(Integer.parseInt(s.substring(index + 1), 16));
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        if (stringTokenizer.countTokens() > 3) {
            return Color.black;
        }
        while (stringTokenizer.hasMoreTokens()) {
            int3 = Integer.parseInt(stringTokenizer.nextToken().trim());
            int1 = Integer.parseInt(stringTokenizer.nextToken().trim());
            int2 = Integer.parseInt(stringTokenizer.nextToken().trim());
        }
        if (int3 > 255) {
            int3 = 255;
        }
        if (int1 > 255) {
            int1 = 255;
        }
        if (int2 > 255) {
            int2 = 255;
        }
        return new Color(int3, int1, int2);
    }
    
    public void start() {
        (this.Ltht = new Thread(this)).start();
    }
    
    public void stop() {
        this.Ltht.stop();
        this.Ltht = null;
    }
    
    public void init() {
        if (this.getParameter("loadingfont") != null) {
            this.ltHt = bdhblf(this.getParameter("loadingfont"), ",");
        }
        else {
            this.ltHt = new Font("Arial", 0, 12);
        }
        if (this.getParameter("bgloadingcolor") != null) {
            this.LtHt = zzzb(this.getParameter("bgloadingcolor"));
        }
        else {
            this.LtHt = Color.white;
        }
        if (this.getParameter("fgloadingcolor") != null) {
            this.lTHt = zzzb(this.getParameter("fgloadingcolor"));
        }
        else {
            this.lTHt = Color.black;
        }
        if (this.getParameter("loadingstring") != null) {
            this.LTHt = this.getParameter("loadingstring");
        }
        else {
            this.LTHt = "Loading Popup Image...";
        }
        final String string = this.BdHBlF + this.bDHBlF + this.BDHBlF + "C) ";
        this.BdhBlF = "";
        this.BDHBlF = string;
        final String string2 = this.BDHBlF + "W";
        this.bDHblF = false;
        this.bDhBlF = "-";
        this.BDHBlF = string2;
        final String string3 = this.BDHBlF + "y";
        this.BdHblF = false;
        this.BDHBlF = string3;
        this.bDhBlF += "w";
        this.bdhbLF += "r";
        final String string4 = this.BDHBlF + "k";
        this.bdHblF = -1;
        this.bDhblF = false;
        this.BDHBlF = string4;
        this.bDhBlF += "a";
        final String string5 = this.BDHBlF + "a";
        this.BdhblF = false;
        this.BDHBlF = string5;
        this.BdhBlF += "h";
        this.bdhbLF += "ig";
        this.BDHBlF += "-W";
        this.bDhBlF += "r";
        final String string6 = this.BDHBlF + "ar";
        this.BDhblF = false;
        this.BDHBlF = string6;
        this.BDHBlF += "z";
        this.BdhBlF += "t";
        this.bDhBlF += "z";
        this.bdhbLF += "ht";
        this.bDHbLf = this.size().width;
        this.BDHBlF += "e";
        this.BdhBlF += "t";
        this.bDhBlF += "ec";
        this.BDHbLf = this.size().height;
        this.BDHBlF += "c";
        this.BdhBlF += "p";
        this.BDHBlF = this.BDHBlF + "ha" + this.bDHBlF + "=-;";
        this.bdhbLf = this.zzzf(this.getParameter("fonttype"), this.getParameter("fontstyle"), this.getParameter("fontsize"));
        this.BdhBlF += ":";
        this.bDhBlF += "h";
        this.lthT = this.getParameter("popupmenusound");
        this.LthT = this.getParameter("backgroundimage");
        this.BdhBlF += "/";
        this.bDhBlF += "a.";
        this.BdhBlF += "/";
        if (this.getParameter("allowedit") != null && this.getParameter("allowedit").equalsIgnoreCase("true")) {
            this.BdHblF = true;
        }
        this.BdhBlF += "w";
        final String string7 = this.bDhBlF + "co";
        int n = 0;
        this.bDhBlF = string7;
        while (this.getParameter("popupmessage" + (n + 1)) != null) {
            ++n;
        }
        this.bDhBlF += "m";
        this.bdhBlf = new Point[n];
        this.BdhBlF += "ww";
        this.BdhBlf = new Rectangle[n];
        this.BdhBlF += ".w";
        this.bdhBLf = new String[n];
        final String string8 = this.BdhBlF + "yk";
        int i = 0;
        this.BdhBlF = string8;
        while (i < n) {
            this.bdhBlf[i] = this.ltht(this.getParameter("popuppoint" + (i + 1)));
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("hotspotarea" + (i + 1)), ",");
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final int int2 = Integer.parseInt(stringTokenizer.nextToken());
            this.BdhBlf[i] = new Rectangle(int1, int2, Integer.parseInt(stringTokenizer.nextToken()) - int1, Integer.parseInt(stringTokenizer.nextToken()) - int2);
            this.bdhBLf[i] = this.getParameter("popupmessage" + (i + 1));
            ++i;
        }
        this.BdhBlF += "a";
        this.BdhBlF += this.bDhBlF;
        this.BDHBlF += this.BdhBlF;
        this.bDhbLF = this.BdhBlF;
        this.bdhBlF = this.BDHBlF;
        if (!this.getParameter(this.bdhbLF).equalsIgnoreCase(this.bDhbLF)) {
            for (int j = 0; j < n; ++j) {
                this.bdhBLf[j] = this.BDhbLF;
            }
        }
        System.out.println("(C) " + this.bDhbLF);
    }
    
    private Point ltht(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ,");
        return new Point(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
    }
    
    public void paint(final Graphics graphics) {
        if (!this.lTht) {
            graphics.setFont(this.ltHt);
            graphics.setColor(this.LtHt);
            graphics.fillRect(0, 0, this.size().width, this.size().height);
            graphics.setColor(this.lTHt);
            graphics.drawString(this.LTHt, 5, 25);
            return;
        }
        if (this.BdhbLf == null) {
            this.bDhbLf = this.createImage(this.bDHbLf, this.BDHbLf);
            (this.BdhbLf = this.bDhbLf.getGraphics()).setFont(this.bdhbLf);
            this.BdhbLf.drawImage(this.BDhbLf, 0, 0, this);
            final boolean bDhblf = false;
            this.BdHblf = 45;
            this.BDhBlf = 5;
            this.bdHBlf = 5;
            this.bDhblf = bDhblf;
            this.Bdhblf = this.BDHBlf;
            this.bdHblf = this.BdhbLf.getFontMetrics();
            this.start();
            final int n = 0;
            final int n2 = 2;
            int i = 0;
            int n3 = n;
            while (i < this.BDHBlF.length() - 1) {
                n3 += this.BDHBlF.charAt(i) * (i % 5 * n2);
                ++i;
            }
            if (n3 / n2 != 9734) {
                this.Ltht.stop();
                final int bdHblf = 4988;
                this.BDhBlf = 30;
                this.BdhbLf = null;
                this.BdHblf = bdHblf;
                return;
            }
            this.zzzh(this.bdhBlF, new Point(this.bDHbLf / 2, this.BDHbLf / 2));
        }
        if (this.BDhblF && this.BdhbLf != null) {
            if (!this.bdHbLF) {
                this.BdhbLf.setColor(new Color(180, 180, 255));
                if (null != null) {}
            }
            else {
                this.BdhbLf.setColor(new Color(0, 0, 0));
            }
            for (int j = 0; j < this.BdhBlf.length; ++j) {
                this.BdhbLf.drawRect(this.BdhBlf[j].x, this.BdhBlf[j].y, this.BdhBlf[j].width, this.BdhBlf[j].height);
            }
            this.BdhbLf.drawRect(0, 0, this.bDHbLf, 19);
        }
        graphics.drawImage(this.bDhbLf, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (!this.LTht) {
            return true;
        }
        if (this.BdHblF) {
            boolean b = false;
            if (n == 114 || n == 82) {
                b = true;
                this.BDhblF = !this.BDhblF;
            }
            if (n == 98 || n == 66) {
                b = true;
                boolean bdHbLF;
                if (this.bdHbLF) {
                    bdHbLF = false;
                    while (null != null) {}
                }
                else {
                    bdHbLF = true;
                }
                this.bdHbLF = bdHbLF;
            }
            if (n == 104 || n == 72) {
                b = true;
                this.BDhBLf = !this.BDhBLf;
            }
            if (n == 112 || n == 80) {
                b = true;
                boolean bdHblF;
                if (this.bDHblF) {
                    bdHblF = false;
                    while (null != null) {}
                }
                else {
                    bdHblF = true;
                }
                this.bDHblF = bdHblF;
            }
            if (b) {
                this.BdhbLf.drawImage(this.BDhbLf, 0, 0, null);
                this.repaint();
                return true;
            }
        }
        return false;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.LTht) {
            return true;
        }
        this.BdHBLf = n;
        this.BDHBLf = n;
        if (this.BdHBLf > 10102) {}
        this.bdhblF = n2;
        this.bDHBLf = n2;
        if (event.clickCount == 2 && !this.BDhBLf && !this.BDhblF) {
            this.zzzh(this.bdhBlF, new Point(this.bDHbLf / 2, this.BDHbLf / 2));
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int bdhbLf, final int bdhblF) {
        if (!this.LTht) {
            return true;
        }
        this.bdhblF = bdhblF;
        this.BDHBLf = bdhbLf;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int bdhBLf, final int bDhBLf) {
        if (!this.LTht) {
            return true;
        }
        if (Math.abs(this.BdhBLf - bdhBLf) > 2 || Math.abs(this.bDhBLf - bDhBLf) > 2) {
            this.bdHBLf = true;
        }
        this.bDhBLf = bDhBLf;
        this.BdhBLf = bdhBLf;
        if (this.bDHblF) {
            return false;
        }
        int n = 2 * 2 * 4;
        n += 4;
        if (bDhBLf < n) {
            this.zzzh(this.bdhBlF, new Point(this.bDHbLf / 2, this.BDHbLf / 2));
            return false;
        }
        try {
            for (int i = 0; i < this.BdhBlf.length; ++i) {
                if (this.BdhBlf[i].inside(bdhBLf, bDhBLf) && this.bdHblF != i) {
                    if (this.zzzh(this.bdhBLf[i], this.bdhBlf[i]) != -1) {
                        this.bdHblF = i;
                    }
                    i = this.BdhBlf.length;
                }
            }
            if (this.bdHblF != -1 && !this.BdhBlf[this.bdHblF].inside(bdhBLf, bDhBLf)) {
                this.bdHblF = -1;
            }
        }
        catch (Exception ex) {}
        return true;
    }
    
    public Image zzzg(final String s) {
        if (s != null) {
            try {
                final Image image = this.getImage(new URL(this.getDocumentBase(), s));
                if (image != null) {
                    final MediaTracker mediaTracker = new MediaTracker(this);
                    mediaTracker.addImage(image, 0);
                    mediaTracker.waitForID(0);
                    return image;
                }
                return null;
            }
            catch (Exception ex) {
                return null;
            }
            return null;
        }
        return null;
    }
    
    public Font zzzf(final String s, final String s2, final String s3) {
        String s4;
        if (s != null) {
            s4 = s;
            while (null != null) {}
        }
        else {
            s4 = "Arial";
        }
        final String s5 = s4;
        int n = 0;
        if (s2 != null) {
            if (s2.toLowerCase().indexOf("bold") != -1) {
                ++n;
            }
            if (s2.toLowerCase().indexOf("italic") != -1) {
                n += 2;
            }
        }
        int int1;
        if (s3 != null) {
            int1 = Integer.parseInt(s3);
        }
        else {
            int1 = 12;
        }
        return new Font(s5, n, int1);
    }
    
    public int zzzh(final String s, final Point bdhBlf) {
        if (this.bDhblf) {
            return -1;
        }
        final boolean bDhblf = true;
        this.BdHBLf = 0;
        this.BDHBLf = 0;
        this.bDHBLf = 0;
        this.bdhblF = 0;
        this.bDHBlf = bdhBlf;
        this.bDhblf = bDhblf;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        final String[] bdHBlf = new String[stringTokenizer.countTokens()];
        this.bDHblf = 0;
        int i = 0;
        this.BdHBlf = bdHBlf;
        while (i < this.BdHBlf.length) {
            this.BdHBlf[i] = stringTokenizer.nextToken();
            this.bDHblf = Math.max(this.bDHblf, this.bdHblf.stringWidth(this.BdHBlf[i]));
            ++i;
        }
        this.bdHbLf = this.bDHblf + this.bdHBlf * 4;
        this.BdHbLf = this.BdHBlf.length * this.bdHblf.getHeight() + this.BDhBlf * 4;
        this.BDHblf = this.bDHblf + this.bdHBlf * 2;
        this.bDhBlf = this.BdHBlf.length * this.bdHblf.getHeight() + this.BDhBlf * 2;
        final StringTokenizer stringTokenizer2 = new StringTokenizer(s);
        return 0;
    }
    
    public void run() {
        try {
            this.BDHBlf = this.getAudioClip(new URL(this.getDocumentBase(), this.lthT));
            if (this.BDHBlf != null) {
                this.BDHBlf.play();
                this.BDHBlf.stop();
            }
            this.BDhbLf = this.zzzg(this.LthT);
        }
        catch (Exception ex) {}
        this.lTht = true;
        this.repaint();
        this.LTht = true;
        this.repaint();
        while (true) {
            if (this.bDhblf && this.BdhbLf != null && !this.bDhblF) {
                this.bDhblF = true;
                if (this.Bdhblf != null) {
                    this.Bdhblf.play();
                }
                for (int i = 0; i < this.BDhblf.length; ++i) {
                    this.BdhbLf.drawImage(this.BDhbLf, 0, 0, null);
                    final int n = (int)(this.BDhblf[i] * this.bdHbLf);
                    final int n2 = (int)(this.BDhblf[i] * this.BdHbLf);
                    this.BdhbLf.setColor(Color.black);
                    this.BdhbLf.fillRoundRect(this.bDHBlf.x - n / 2, this.bDHBlf.y - n2 / 2, n, n2, this.bdHBlf * 2, this.BDhBlf * 2);
                    final int n3 = (int)(this.BDhblf[i] * this.BDHblf);
                    final int n4 = (int)(this.BDhblf[i] * this.bDhBlf);
                    this.BdhbLf.setColor(Color.white);
                    this.BdhbLf.fillRoundRect(this.bDHBlf.x - n3 / 2, this.bDHBlf.y - n4 / 2, n3, n4, this.bdHBlf * 2, this.BDhBlf * 2);
                    if (i == this.BDhblf.length - 1) {
                        this.BdhbLf.setColor(Color.black);
                        int j = 0;
                        if (null != null) {}
                        while (j < this.BdHBlf.length) {
                            this.BdhbLf.drawString(this.BdHBlf[j], this.bDHBlf.x - this.bDHblf / 2, this.bDHBlf.y - n4 / 2 + this.bdHblf.getHeight() * (j + 1));
                            ++j;
                        }
                    }
                    this.repaint();
                    try {
                        Thread.sleep(this.BdHblf);
                        if (null == null) {}
                    }
                    catch (Exception ex2) {}
                }
                try {
                    this.bdHBLf = false;
                    if (null != null) {}
                    while (!this.bdHBLf) {
                        Thread.sleep(1L);
                    }
                }
                catch (Exception ex3) {}
                this.BdhbLf.drawImage(this.BDhbLf, 0, 0, null);
                this.repaint();
                final boolean bdHBLf = false;
                this.bDhblf = false;
                this.bDhblF = false;
                this.bdHBLf = bdHBLf;
            }
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex4) {}
            if (!this.bDhblf) {
                String string = "HotSpot String: " + this.BdHBLf + "," + this.bDHBLf + "," + this.BDHBLf + "," + this.bdhblF;
                int bdhbLf = this.BDHBLf;
                if ((this.BdHBLf == bdhbLf && this.bDHBLf == bdhbLf && this.BDHBLf == bdhbLf && this.bdhblF == bdhbLf) || this.BDHBLf - this.BdHBLf < 0 || this.bdhblF - this.bDHBLf < 0) {
                    final String s = "HotSpot String: Currently none selected";
                    bdhbLf = -1;
                    string = s;
                }
                try {
                    if (this.BdhbLf == null) {
                        continue;
                    }
                    this.BdhbLf.drawImage(this.BDhbLf, 0, 0, this);
                    if (!this.bdHbLF) {
                        this.BdhbLf.setColor(Color.white);
                    }
                    else {
                        this.BdhbLf.setColor(Color.black);
                    }
                    if (bdhbLf > 0) {
                        this.BdhbLf.drawRect(this.BdHBLf, this.bDHBLf, this.BDHBLf - this.BdHBLf, this.bdhblF - this.bDHBLf);
                    }
                    if (this.BDhBLf) {
                        this.BdhbLf.setColor(Color.white);
                        this.BdhbLf.fillRect(0, 0, this.bDHbLf, this.bdHblf.getHeight() * 2 + 10);
                        this.BdhbLf.setColor(Color.black);
                        this.BdhbLf.drawString(string, 10, this.bdHblf.getHeight());
                        this.BdhbLf.drawString("To turn off/on: | Popups - Press P | Rectangles - R | Hotspot Info - H |", 10, this.bdHblf.getHeight() * 2);
                    }
                    if (!this.BDhBLf && !this.BDhblF) {
                        continue;
                    }
                    this.repaint();
                }
                catch (Exception ex5) {}
            }
        }
    }
}
