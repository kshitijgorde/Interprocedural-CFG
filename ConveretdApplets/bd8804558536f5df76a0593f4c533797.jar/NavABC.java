import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.util.StringTokenizer;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class NavABC extends Applet implements Runnable
{
    private int lThtlf;
    private int LThtlf;
    private int ltHtlf;
    private int LtHtlf;
    private int lTHtlf;
    private int LTHtlf;
    private int[] lthTlf;
    private String LthTlf;
    private String[] lThTlf;
    private String LThTlf;
    private String ltHTlf;
    private boolean LtHTlf;
    private Image lTHTlf;
    private Image LTHTlf;
    private Image[] lthtLf;
    private Image LthtLf;
    private MediaTracker lThtLf;
    private Thread LThtLf;
    private String[] ltHtLf;
    private Graphics LtHtLf;
    private Color lTHtLf;
    private Color LTHtLf;
    private Color lthTLf;
    private Color LthTLf;
    private Font lThTLf;
    private AudioClip LThTLf;
    private int ltHTLf;
    private int LtHTLf;
    private static boolean lTHTLf;
    private static boolean LTHTLf;
    private static boolean lthtlF;
    private static boolean LthtlF;
    private static boolean lThtlF;
    private static boolean LThtlF;
    private static boolean ltHtlF;
    private static String LtHtlF;
    private static byte[] lTHtlF;
    private static String LTHtlF;
    private static String lthTlF;
    private static String LthTlF;
    private static int lThTlF;
    private static volatile String LThTlF;
    private static String ltHTlF;
    private static String LtHTlF;
    private static String lTHTlF;
    private static boolean LTHTlF;
    private int lthtLF;
    private boolean LthtLF;
    private boolean lThtLF;
    private boolean LThtLF;
    private int ltHtLF;
    private int LtHtLF;
    private boolean lTHtLF;
    private String LTHtLF;
    
    static Font b(final String s, final String s2) {
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
            font = new Font(array2[0], zzze(array2[1]), Integer.parseInt(array2[2]));
        }
        catch (Exception ex) {
            return font;
        }
        return font;
    }
    
    public static int zzze(final String s) {
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
    
    public static Color zzzd(final String s) {
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
    
    public NavABC() {
        final String ltHtLF = "";
        this.LthTlf = "";
        this.LthtLF = false;
        this.lThtLF = true;
        this.LThtLF = false;
        this.lTHtLF = false;
        this.LtHTlf = false;
        this.LTHtlf = -1;
        this.LTHtLF = ltHtLF;
    }
    
    public void zzzc() {
        if (this.getParameter("username") != null) {
            NavABC.LTHtlF = new String("/" + this.getParameter("username").toLowerCase());
        }
        else {
            NavABC.LTHtlF = null;
        }
        NavABC.LtHtlF = this.getParameter("licensekey");
        this.LThtlf = Integer.parseInt(this.getParameter("timingValue"));
        this.ltHtlf = Integer.parseInt(this.getParameter("numItems"));
        this.LthTlf = this.getParameter("aniMenu");
        this.LThTlf = this.getParameter("copyright");
        this.LtHtlf = this.size().width;
        this.lTHtLf = zzzd(this.getParameter("background_color"));
        this.LTHtLf = zzzd(this.getParameter("loading_color"));
        this.lthTLf = zzzd(this.getParameter("hiLiteColor"));
        this.LthTLf = zzzd(this.getParameter("normalColor"));
        this.lThTLf = b(this.getParameter("mainFont"), ",");
        if (this.getParameter("menuItemHeight") != null) {
            this.ltHTLf = Integer.parseInt(this.getParameter("menuItemHeight"));
        }
        else {
            this.ltHTLf = 24;
        }
        if (this.getParameter("centerAdjustment") != null) {
            this.LtHTLf = Integer.parseInt(this.getParameter("centerAdjustment"));
            if (null == null) {}
        }
        else {
            this.LtHTLf = 0;
        }
        if (this.getParameter("numFrames") != null) {
            this.lThtlf = Integer.parseInt(this.getParameter("numFrames"));
        }
        else {
            this.lThtlf = 8;
        }
        this.lTHtlf = this.size().height;
        if (this.getParameter("playsound") != null && !this.getParameter("playsound").equalsIgnoreCase("none")) {
            this.LThTLf = this.getAudioClip(this.getCodeBase(), this.getParameter("playsound"));
            if (this.LThTLf != null) {
                this.LThTLf.play();
                this.LThTLf.stop();
            }
        }
    }
    
    public void start() {
        (this.LThtLf = new Thread(this)).start();
    }
    
    public void stop() {
        this.LThtLf.stop();
        this.LThtLf = null;
    }
    
    public void init() {
        final String ltHtLF = "h";
        NavABC.LTHTlF = true;
        this.LTHtLF = ltHtLF;
        if (this.getParameter("maxMenuItemWidth") != null) {
            this.LtHtLF = Integer.parseInt(this.getParameter("maxMenuItemWidth"));
        }
        else {
            this.LtHtLF = 150;
        }
        this.LTHtLF += "tt";
        this.zzzc();
        this.LTHtLF += "p:";
        this.resize(this.LtHtlf, this.lTHtlf);
        this.LTHtLF += "//";
        this.lThtLf = new MediaTracker(this);
        this.LTHtLF += "ww";
        this.LthtLf = this.createImage(this.LtHtlf, this.lTHtlf);
        this.LTHtLF += "w.";
        this.LtHtLf = this.LthtLf.getGraphics();
        this.LTHtLF += "wy";
        this.lthTlf = new int[this.ltHtlf];
        final String string = this.LTHtLF + "ka";
        this.ltHtLF = 5;
        this.LTHtLF = string;
        this.LTHtLF += "-wa";
        this.lthtLF = this.ltHtlf * this.ltHtLF - 1;
        final String string2 = this.LTHtLF + "rz";
        this.LthtLF = false;
        this.LTHtLF = string2;
        this.LTHtLF += "ec";
        if (this.ltHtLF == 0) {
            this.LthtLF = true;
        }
        this.ltHtLf = new String[this.ltHtlf];
        this.LTHtLF += "ha";
        this.lThTlf = new String[this.ltHtlf];
        final String string3 = this.LTHtLF + ".c";
        int i = 0;
        this.LTHtLF = string3;
        while (i < this.ltHtlf) {
            this.lThTlf[i] = this.getParameter("title" + (i + 1));
            this.ltHtLf[i] = this.getParameter("link" + (i + 1));
            if (this.lThTlf[i].equals("*")) {
                this.lThTlf[i] = "  ";
            }
            ++i;
        }
        this.LTHtLF += "om";
        this.LTHTlf = this.getImage(this.getDocumentBase(), this.LthTlf);
        this.lThtLf.addImage(this.LTHTlf, 0);
        this.repaint();
    }
    
    public void run() {
        final String string = "w" + "y";
        String string2;
        try {
            this.lThtLf.waitForID(0);
            string2 = string + "ka-w";
        }
        catch (Exception ex) {
            return;
        }
        final Image image = this.createImage(this.LtHtLF, this.LTHTlf.getHeight(this));
        image.getGraphics().drawImage(this.LTHTlf, 0, 0, this.LtHtLF, this.LTHTlf.getHeight(this), this);
        this.lTHTlf = image;
        final String string3 = string2 + "arze";
        this.Lthtlf();
        try {
            Thread.sleep(500L);
            new StringBuffer().append(string3).append("cha").toString();
        }
        catch (Exception ex2) {}
        this.LtHTlf = true;
        System.out.println("NumItems: " + this.ltHtlf);
        this.repaint();
        try {
            Thread.sleep(2915L);
            if (null != null) {}
        }
        catch (Exception ex3) {}
        NavABC.LTHTlF = false;
        this.repaint();
        while (true) {
            if (this.LTHtlf == 129384) {
                System.out.println("hey asswipe. What are you doing here?".substring(0, 3));
            }
            if (!this.LthtLF) {
                int ltHtlf = this.lthtLF % this.ltHtlf;
                if (this.lThtLF) {
                    ltHtlf = this.ltHtlf - 1 - ltHtlf;
                }
                if (ltHtlf == 0) {
                    this.lThtLF = true;
                }
                if (ltHtlf == this.ltHtlf - 1) {
                    this.lThtLF = false;
                }
                --this.lthtLF;
                if (this.lthtLF == -1) {
                    this.LthtLF = true;
                }
                if (this.LTHtlf != ltHtlf) {
                    if (this.LTHtlf != -1) {
                        this.lthTlf[this.LTHtlf] = 2;
                    }
                    this.lthTlf[ltHtlf] = 1;
                    if (this.LThTLf != null) {
                        this.LThTLf.play();
                    }
                    this.LTHtlf = ltHtlf;
                    this.repaint();
                }
                if (this.LthtLF) {
                    final int[] lthTlf = this.lthTlf;
                    final int ltHtlf2 = this.LTHtlf;
                    final int n = 2;
                    this.LTHtlf = -1;
                    lthTlf[ltHtlf2] = n;
                    this.repaint();
                }
            }
            int i = 0;
            if (null == null) {}
            while (i < this.ltHtlf) {
                if (this.lthTlf[i] != 0 && this.lthTlf[i] != 1) {
                    final int[] lthTlf2 = this.lthTlf;
                    final int n2 = i;
                    ++lthTlf2[n2];
                    if (this.lthTlf[i] > this.lThtlf + 1) {
                        this.lthTlf[i] = 0;
                    }
                }
                ++i;
            }
            this.repaint();
            try {
                Thread.sleep(this.LThtlf);
            }
            catch (Exception ex4) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        if (!this.LThTlf.equals(this.LTHtLF)) {
            return;
        }
        if (!this.LtHTlf) {
            graphics.setFont(this.lThTLf);
            graphics.setColor(this.lTHtLf);
            graphics.fillRect(0, 0, this.size().width, this.size().height);
            graphics.setColor(this.LTHtLf);
            graphics.drawString("Loading images...", 5, 10);
            return;
        }
        for (int i = 0; i < this.ltHtlf; ++i) {
            int n = 0;
            if (null != null) {}
            while (this.lThTlf[i].charAt(n) == '_' && n < this.lThTlf[i].length()) {
                ++n;
            }
            graphics.drawImage(this.lthtLf[this.lthTlf[i]], i * this.LtHtLF, 0, this);
            if (this.lthTlf[i] != 0) {
                graphics.setColor(this.lthTLf);
                if (this.lThTlf[i] != null) {
                    graphics.drawString(this.lThTlf[i].substring(n, this.lThTlf[i].length()), i * this.LtHtLF + 15 + this.LtHTLf + n * 5, 15);
                }
            }
            else {
                graphics.setColor(this.LthTLf);
                if (this.lThTlf[i] != null) {
                    graphics.drawString(this.lThTlf[i].substring(n, this.lThTlf[i].length()), i * this.LtHtLF + 15 + this.LtHTLf + n * 5, 15);
                }
            }
        }
        final int n2 = 255;
        if (NavABC.LTHTlF) {
            graphics.setFont(new Font("Arial", 0, 10));
            graphics.setColor(new Color(n2, n2, n2));
            graphics.fillRect(0, 0, this.size().width, 30);
            graphics.setColor(new Color(1, 2, 3));
            graphics.drawString(this.LTHtLF + " -- pausing 3 seconds", 10, 10);
            graphics.setFont(this.lThTLf);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.LtHtLf != null) {
            this.paint(this.LtHtLf);
            graphics.drawImage(this.LthtLf, 0, 0, this);
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (!this.LthtLF) {
            this.LthtLF = true;
        }
        final int min = Math.min(n / this.LtHtLF, this.ltHtlf - 1);
        if (this.LTHtlf != min) {
            if (this.LTHtlf != -1) {
                this.lthTlf[this.LTHtlf] = 2;
            }
            this.lthTlf[min] = 1;
            if (this.LThTLf != null) {
                this.LThTLf.play();
            }
            this.LTHtlf = min;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.LthtLF) {
            this.LthtLF = true;
        }
        return this.mouseMove(event, n, n2);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.LthtLF) {
            this.LthtLF = true;
        }
        if (this.LtHTlf) {
            if (!this.lTHtLF) {
                try {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.ltHtLf[Math.min(n / this.LtHtLF, this.ltHtlf - 1)]), "_self");
                }
                catch (Exception ex) {
                    System.out.println("URL error:" + ex);
                }
                return true;
            }
        }
        try {
            this.getAppletContext().showDocument(new URL(this.LTHtLF));
        }
        catch (Exception ex2) {
            if (null == null) {}
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.LthtLF) {
            this.LthtLF = true;
        }
        if (this.LTHtlf != -1) {
            final int[] lthTlf = this.lthTlf;
            final int ltHtlf = this.LTHtlf;
            final int n3 = 2;
            this.LTHtlf = -1;
            lthTlf[ltHtlf] = n3;
            this.repaint();
        }
        return true;
    }
    
    private Image lthtlf(final Image image, final int n, final int n2, final int n3, final int n4) {
        return this.createImage(new FilteredImageSource(image.getSource(), new CropImageFilter(n, n2, n3, n4)));
    }
    
    private void Lthtlf() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image[] lthtLf = new Image[10];
        int i = 0;
        this.lthtLf = lthtLf;
        while (i < 10) {
            mediaTracker.addImage(this.lthtLf[i] = this.lthtlf(this.lTHTlf, 0, this.ltHTLf * i, this.LtHtLF, this.ltHTLf), 0);
            ++i;
        }
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            this.stop();
        }
    }
    
    static {
        final boolean lthtLf = true;
        NavABC.lthtlF = true;
        NavABC.LthtlF = true;
        NavABC.lThtlF = true;
        NavABC.ltHtlF = true;
        NavABC.LtHtlF = "47-652014130-37-3114117325";
        NavABC.LTHtlF = "";
        NavABC.lthTlF = "http://www.wyka-warzecha.com";
        NavABC.LthTlF = "";
        NavABC.LThTlF = "";
        NavABC.ltHTlF = "-141111-1546054-71-143-5442-62";
        NavABC.LtHTlF = "140-131-134-50-154147147-125124-102";
        NavABC.lTHTlF = "147-147152152140-136136-161156-62";
        NavABC.LTHTLf = lthtLf;
    }
}
