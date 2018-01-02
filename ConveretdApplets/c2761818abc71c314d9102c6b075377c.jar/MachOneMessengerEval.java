import java.net.MalformedURLException;
import java.awt.Event;
import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.image.MemoryImageSource;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class MachOneMessengerEval extends Applet implements Runnable
{
    Dimension bdhBlfz;
    Thread BdhBlfz;
    MediaTracker bDhBlfz;
    Image BDhBlfz;
    Graphics bdHBlfz;
    Image BdHBlfz;
    Graphics bDHBlfz;
    URL BDHBlfz;
    int bdhbLfz;
    int BdhbLfz;
    int bDhbLfz;
    int BDhbLfz;
    int bdHbLfz;
    int BdHbLfz;
    int bDHbLfz;
    int BDHbLfz;
    int bdhBLfz;
    int BdhBLfz;
    int bDhBLfz;
    boolean BDhBLfz;
    boolean bdHBLfz;
    boolean BdHBLfz;
    boolean bDHBLfz;
    boolean BDHBLfz;
    boolean bdhblFz;
    boolean BdhblFz;
    boolean bDhblFz;
    int BDhblFz;
    int bdHblFz;
    String[] BdHblFz;
    Image[] bDHblFz;
    boolean[] BDHblFz;
    int[] bdhBlFz;
    int[] BdhBlFz;
    int[] bDhBlFz;
    int[] BDhBlFz;
    Color bdHBlFz;
    Color BdHBlFz;
    String[][] bDHBlFz;
    Font[] BDHBlFz;
    Font bdhbLFz;
    String[] BdhbLFz;
    String[] bDhbLFz;
    String BDhbLFz;
    Color[] bdHbLFz;
    Color BdHbLFz;
    Color[] bDHbLFz;
    Color BDHbLFz;
    int bdhBLFz;
    int BdhBLFz;
    int[] bDhBLFz;
    int[] BDhBLFz;
    int bdHBLFz;
    int BdHBLFz;
    String bDHBLFz;
    String BDHBLFz;
    String bdhblfZ;
    boolean BdhblfZ;
    Font bDhblfZ;
    Color BDhblfZ;
    int[] bdHblfZ;
    int[] BdHblfZ;
    int[] bDHblfZ;
    int[] BDHblfZ;
    int[] bdhBlfZ;
    Rectangle BdhBlfZ;
    Rectangle bDhBlfZ;
    int BDhBlfZ;
    int bdHBlfZ;
    int BdHBlfZ;
    boolean bDHBlfZ;
    int BDHBlfZ;
    int bdhbLfZ;
    boolean BdhbLfZ;
    int bDhbLfZ;
    int[] BDhbLfZ;
    int[] bdHbLfZ;
    MemoryImageSource BdHbLfZ;
    boolean bDHbLfZ;
    int BDHbLfZ;
    int bdhBLfZ;
    Image BdhBLfZ;
    int bDhBLfZ;
    Image[] BDhBLfZ;
    int bdHBLfZ;
    boolean BdHBLfZ;
    boolean bDHBLfZ;
    static byte[] BDHBLfZ;
    static String bdhblFZ;
    static String BdhblFZ;
    static String bDhblFZ;
    static int BDhblFZ;
    static volatile String bdHblFZ;
    static String BdHblFZ;
    static volatile String bDHblFZ;
    String BDHblFZ;
    static String bdhBlFZ;
    static boolean BdhBlFZ;
    static boolean bDhBlFZ;
    static boolean BDhBlFZ;
    static boolean bdHBlFZ;
    static boolean BdHBlFZ;
    static boolean bDHBlFZ;
    boolean BDHBlFZ;
    static boolean bdhbLFZ;
    String BdhbLFZ;
    Color bDhbLFZ;
    Color BDhbLFZ;
    boolean bdHbLFZ;
    
    public static void zzzf() {
        MachOneMessengerEval.bDhblFZ = MachOneMessengerEval.bdHblFZ;
        System.out.println("(C) " + MachOneMessengerEval.bdhBlFZ);
    }
    
    public synchronized void zzzh(final Graphics graphics) {
        this.bdHBlfz.setColor(this.bdHBlFz);
        this.bdHBlfz.fillRect(0, 0, 180, 180);
    }
    
    public synchronized void zzzm(final int bdHbLfz, final int bdHbLfz2) {
        if (this.bDHbLfZ) {
            return;
        }
        if (bdHbLfz == -1) {
            this.zzzl();
            final int bdHbLfz3 = -1;
            this.BDHbLfz = -1;
            this.bDHbLfz = bdHbLfz3;
            return;
        }
        this.zzzl();
        this.BDHbLfz = bdHbLfz2;
        this.bDHbLfz = bdHbLfz;
        final FontMetrics fontMetrics = this.getFontMetrics(this.BDHBlFz[bdHbLfz]);
        final int maxAscent = fontMetrics.getMaxAscent();
        final int maxDescent = fontMetrics.getMaxDescent();
        int n = this.bDHblfZ[bdHbLfz2];
        this.bdHBlfz.setFont(this.BDHBlFz[bdHbLfz]);
        this.bdHBlfz.setColor(this.bDHbLFz[bdHbLfz]);
        for (int i = 0; i < this.bDHBlFz[bdHbLfz].length; ++i) {
            final int zzzi = this.zzzi(fontMetrics.stringWidth(this.bDHBlFz[bdHbLfz][i]), bdHbLfz);
            final int n2 = n + maxAscent;
            this.bdHBlfz.drawString(this.bDHBlFz[bdHbLfz][i], zzzi, n2);
            n = n2 + maxDescent;
        }
        if (!this.bDhblFz) {
            this.paint(this.getGraphics());
        }
    }
    
    public synchronized void zzzw() {
        this.zzzl();
        this.zzzh(this.getGraphics());
    }
    
    public static void zzzeb() {
        zzzdb();
        zzzbb();
    }
    
    public Image zzzcb(final Image image, String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        if (lowerCase.lastIndexOf("gif") == -1) {
            return image;
        }
        final int width = image.getWidth(this);
        final int height = image.getHeight(this);
        final int[] array = new int[width * height];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, array, 0, width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        final Image image2 = this.createImage(new MemoryImageSource(width, height, array, 0, width));
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image2, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex2) {}
        if (mediaTracker.isErrorID(0)) {
            return null;
        }
        return image2;
    }
    
    public static void zzzdb() {
        final String s = " Hey Idiot. D*CKWAD! Yes, YOU! This is COPYRIGHT. Yeah. CAN YOU SPELL THAT? *c*o*p*y*r*i*g*h*t*";
        final String s2 = " That means. you should NOT be viewing this, or 'decompiling' it you moron. How about getting a life? Or write your own program.";
        System.out.print(s.substring(0, 0));
        System.out.print(s2.substring(0, 0));
    }
    
    static int[] bdhblfz(final String s, final String s2, final int n) {
        int n2 = 0;
        final int[] array = new int[n];
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
            s.trim();
            if (stringTokenizer.countTokens() > n) {
                array[0] = -1;
                return array;
            }
            while (stringTokenizer.hasMoreTokens()) {
                array[n2] = Integer.parseInt(stringTokenizer.nextToken().trim());
                ++n2;
            }
        }
        catch (Exception ex) {
            return null;
        }
        return array;
    }
    
    public static void zzzbb() {
        zzzab();
        zzzz();
    }
    
    public synchronized int zzzhb(final boolean b) {
        this.bdHBlfz.getColor();
        if (this.bDhblFz) {
            return -1;
        }
        if (!this.bdHBLfz) {
            this.bdHBlfz.drawImage(this.bDHblFz[0], this.bdhBlFz[0], this.BdhBlFz[0], this.bdHBlFz, this);
            this.bdHBlfz.drawImage(this.bDHblFz[1], this.bdhBlFz[1], this.BdhBlFz[1], this.bdHBlFz, this);
            if (b) {
                this.paint(this.getGraphics());
            }
            this.BdhblFz = false;
            return -1;
        }
        if (!this.bDHBLfz) {
            if (this.BdhBlfZ.inside(this.bdhBLfz, this.BdhBLfz)) {
                if (this.BDhblFz == 0) {
                    this.bdHBlfz.drawImage(this.bDHblFz[4], this.bdhBlFz[0], this.BdhBlFz[0], this.bdHBlFz, this);
                }
                else if (this.BDhblFz == -1 && !this.BDHBLfz) {
                    this.bdHBlfz.drawImage(this.bDHblFz[2], this.bdhBlFz[0], this.BdhBlFz[0], this.bdHBlFz, this);
                }
                this.bdHBlfz.drawImage(this.bDHblFz[1], this.bdhBlFz[1], this.BdhBlFz[1], this.bdHBlFz, this);
                this.BdhblFz = true;
                if (null != null) {}
            }
            else if (this.bDhBlfZ.inside(this.bdhBLfz, this.BdhBLfz)) {
                this.bdHBlfz.drawImage(this.bDHblFz[0], this.bdhBlFz[0], this.BdhBlFz[0], this.bdHBlFz, this);
                if (this.BDhblFz == 1) {
                    this.bdHBlfz.drawImage(this.bDHblFz[5], this.bdhBlFz[1], this.BdhBlFz[1], this.bdHBlFz, this);
                }
                else if (this.BDhblFz == -1 && !this.BDHBLfz) {
                    this.bdHBlfz.drawImage(this.bDHblFz[3], this.bdhBlFz[1], this.BdhBlFz[1], this.bdHBlFz, this);
                }
                this.BdhblFz = true;
            }
            else {
                this.bdHBlfz.drawImage(this.bDHblFz[0], this.bdhBlFz[0], this.BdhBlFz[0], this.bdHBlFz, this);
                this.bdHBlfz.drawImage(this.bDHblFz[1], this.bdhBlFz[1], this.BdhBlFz[1], this.bdHBlFz, this);
                if (this.BdhblFz) {
                    if (b) {
                        this.paint(this.getGraphics());
                    }
                    this.BdhblFz = false;
                }
            }
        }
        if (this.BDhblFz == -1 && this.bdHBLfz) {
            int i = 0;
            while (i < this.BDHblfZ[this.BdhbLfz]) {
                if (this.BdhBLfz > this.bDHblfZ[this.BdHbLfz + i] + 10 && !this.BdhbLFz[this.BDhbLfz + i].equalsIgnoreCase("-1") && this.BdhBLfz < this.bDHblfZ[this.BdHbLfz + i + 1] + 10 && this.bDHbLfz != this.BDhbLfz + i) {
                    if (this.bdHblFz == -1 && !this.BDHBLfz) {
                        this.zzzm(this.BDhbLfz + i, this.BdHbLfz + i);
                        return this.BDhbLfz + i;
                    }
                    if (this.bdHblFz == this.BDhbLfz + i) {
                        this.zzzm(this.BDhbLfz + i, this.BdHbLfz + i);
                        return this.BDhbLfz + i;
                    }
                    this.zzzm(-1, -1);
                    return this.BDhbLfz + i;
                }
                else {
                    if (this.BdhBLfz > this.bDHblfZ[this.BdHbLfz + i] + 10 && this.BdhBLfz < this.bDHblfZ[this.BdHbLfz + i + 1] + 10 && this.bDHbLfz != this.BDhbLfz + i) {
                        this.zzzm(-1, -1);
                        return this.BDhbLfz + i;
                    }
                    ++i;
                }
            }
            if (this.bDHbLfz != -1 && (this.BdhBLfz < this.bDHblfZ[this.BdHbLfz] + 10 || this.BdhBLfz > this.bDHblfZ[this.BdHbLfz + this.BDHblfZ[this.BdhbLfz]] + 10)) {
                this.zzzm(-1, -1);
            }
        }
        if (b) {
            this.paint(this.getGraphics());
        }
        return -1;
    }
    
    public static void zzzy() {
        MachOneMessengerEval.bDhblFZ = "";
    }
    
    public static void zzzab() {
    }
    
    public static void zzzz() {
        zzzy();
    }
    
    public static void zzzx() {
        zzzn();
    }
    
    public static void zzzv() {
        if (MachOneMessengerEval.BdhBlFZ) {
            MachOneMessengerEval.bDhBlFZ = MachOneMessengerEval.BdhBlFZ;
        }
    }
    
    public void init() {
        final String bdHblFZ = "h";
        this.BdhbLFZ = "j";
        this.BDHblFZ = bdHblFZ;
        final String string = this.BDHblFZ + "t";
        int bDhBLfz = 0;
        this.BDHblFZ = string;
        final String string2 = this.BDHblFZ + "t";
        int n = 0;
        this.BDHblFZ = string2;
        this.BdhbLFZ += "a";
        final String string3 = this.BDHblFZ + "p";
        int n2 = -50;
        int n3 = -50;
        this.BDHblFZ = string3;
        this.BDHblFZ += ":";
        this.bdHBlFz = new Color(143, 143, 255);
        this.BdhbLFZ += "va";
        this.BDHblFZ += "/";
        MachOneMessengerEval.bDHblFZ = this.getDocumentBase().toString().toLowerCase();
        this.BDHblFZ += "/";
        this.BdhbLFZ += " a";
        this.BDHblFZ += "w";
        this.BdhbLFZ += "pple";
        this.BDHblFZ += "y";
        this.BdhbLFZ += "t b";
        this.BDHblFZ += "k";
        this.BdhbLFZ += "y ";
        zzzf();
        this.bdhBlfz = this.size();
        final String string4 = this.BDHblFZ + "a";
        this.BdHBLFz = 10;
        this.BDHblFZ = string4;
        this.getParameter("leftrightmargin");
        final String string5 = this.BDHblFZ + "-";
        this.bdHBlfZ = 180;
        this.BdHBlfZ = 180;
        this.BDHblFZ = string5;
        this.BDHblFZ += "w";
        final String parameter = this.getParameter("autoslide");
        if (parameter != null && parameter.equalsIgnoreCase("yes")) {
            this.BdhbLfZ = true;
        }
        this.BDHblFZ += "a";
        this.BDHbLfZ = this.bdhBlfz.width;
        this.bdhBLfZ = this.bdhBlfz.height;
        final String parameter2 = this.getParameter("copyright");
        this.BDHblFZ += "r";
        if (parameter2 != null && parameter2.equalsIgnoreCase(this.BdhbLFZ + MachOneMessengerEval.bdhBlFZ)) {
            this.BdHBLfz = true;
            while (this.getParameter("page" + (bDhBLfz + 1) + "-item1") != null) {
                ++bDhBLfz;
            }
            this.BDHblFZ += "z";
            if (bDhBLfz > 4) {
                bDhBLfz = 4;
            }
            this.BDHblfZ = new int[bDhBLfz];
            final int[] bdhBlfZ = new int[bDhBLfz];
            this.bDhBLfz = bDhBLfz;
            this.bdhBlfZ = bdhBlfZ;
            final String string6 = this.BDHblFZ + "e";
            int i = 0;
            this.BDHblFZ = string6;
            while (i < bDhBLfz) {
                int n4;
                for (n4 = 0; this.getParameter("page" + (i + 1) + "-item" + (n4 + 1)) != null; ++n4, ++n) {}
                this.BDHblfZ[i] = n4;
                ++i;
            }
            final String string7 = this.BDHblFZ + "c";
            int j = 0;
            this.BDHblFZ = string7;
            while (j < bDhBLfz) {
                if (this.getParameter("page" + (j + 1) + "effect") != null) {
                    this.bdhBlfZ[j] = Integer.parseInt(this.getParameter("page" + (j + 1) + "effect"));
                }
                else {
                    this.bdhBlfZ[j] = 0;
                }
                ++j;
            }
            this.BDHblFZ += "h";
            this.bDHbLFz = new Color[n];
            this.BDHblFZ += "a";
            this.bDHblfZ = new int[n + this.BDHblfZ.length];
            this.BDhBLFz = new int[n];
            this.bdHblfZ = new int[n];
            this.BDHblFZ += ".";
            this.bDHBlFz = new String[n][];
            this.bDhbLFz = new String[n];
            this.BDHblFZ += "c";
            this.bdHbLFz = new Color[n];
            this.BDHBlFz = new Font[n];
            this.BdhbLFz = new String[n];
            this.BdHblfZ = new int[n];
            this.BDHblFZ += "o";
            this.bDhBLFz = new int[this.BDHblfZ.length];
            this.BdHblFz = new String[this.BDhBlfZ];
            this.BDHblFZ += "m";
            final String parameter3 = this.getParameter("image_previous");
            if (parameter3 != null) {
                this.BdHblFz[0] = parameter3;
            }
            final String parameter4 = this.getParameter("image_next");
            if (parameter4 != null) {
                this.BdHblFz[1] = parameter4;
            }
            final String parameter5 = this.getParameter("image_previous_mouseover");
            if (parameter5 != null) {
                this.BdHblFz[2] = parameter5;
            }
            final String parameter6 = this.getParameter("image_next_mouseover");
            if (parameter6 != null) {
                this.BdHblFz[3] = parameter6;
            }
            final String parameter7 = this.getParameter("image_previous_clicking");
            if (parameter7 != null) {
                this.BdHblFz[4] = parameter7;
            }
            final String parameter8 = this.getParameter("image_next_clicking");
            if (parameter8 != null) {
                this.BdHblFz[5] = parameter8;
            }
            final String parameter9 = this.getParameter("backgroundimage");
            if (parameter9 != null) {
                final String[] bdHblFz = this.BdHblFz;
                final int n5 = 6;
                final String s = parameter9;
                this.bDHBlfZ = true;
                this.BDHBlfZ = 10;
                this.bdhbLfZ = 10;
                bdHblFz[n5] = s;
            }
            else {
                this.BdHblFz[6] = "-1";
            }
            final Color white = Color.white;
            this.bDhbLFZ = Color.white;
            this.BDhbLFZ = Color.black;
            this.BdHBlFz = white;
            final String parameter10 = this.getParameter("globalfont");
            if (parameter10 != null) {
                this.bdhbLFz = BDHblfz(parameter10, ",");
            }
            final String parameter11 = this.getParameter("pagenumberingfont");
            if (parameter11 != null) {
                this.bDhblfZ = BDHblfz(parameter11, ",");
            }
            final String parameter12 = this.getParameter("globalTargetFrame");
            if (parameter12 != null) {
                this.BDhbLFz = parameter12;
            }
            final Color bdHbLFz = new Color(0, 0, 255);
            this.BDhblfZ = Color.white;
            this.BdHbLFz = bdHbLFz;
            this.BDHbLFz = new Color(180, 180, 255);
            final String parameter13 = this.getParameter("verticalAlign");
            if (parameter13 != null) {
                this.bdhBLFz = this.zzzr(parameter13, 1);
            }
            final String parameter14 = this.getParameter("horizontalAlign");
            if (parameter14 != null) {
                this.BdhBLFz = this.zzzr(parameter14, 0);
            }
            final String parameter15 = this.getParameter("pageNumberingLocation");
            if (parameter15 != null) {
                final int[] bdhblfz = bdhblfz(parameter15, ",", 2);
                if (bdhblfz != null) {
                    n2 = bdhblfz[0];
                    n3 = bdhblfz[1];
                }
            }
            final String parameter16 = this.getParameter("activatepagenumbering");
            if (parameter16 != null && parameter16.equalsIgnoreCase("yes")) {
                this.BdhblfZ = true;
            }
            final int n6 = 0;
            int k = 0;
            int n7 = n6;
            while (k < bDhBLfz) {
                this.bdHblfZ[k] = n2;
                this.BdHblfZ[k] = n3;
                this.bDhBLFz[k] = this.bdhBLFz;
                final int bdhBLFz = this.BdhBLFz;
                final Color bdHbLFz2 = this.BdHbLFz;
                final Color bdHbLFz3 = this.BDHbLFz;
                final Font bdhbLFz = this.bdhbLFz;
                int l = 0;
                final Font font = bdhbLFz;
                if (null != null) {}
                while (l < this.BDHblfZ[k]) {
                    final String parameter17 = this.getParameter("textfont" + (k + 1) + "-item" + (l + 1));
                    if (parameter17 != null) {
                        this.BDHBlFz[n7] = BDHblfz(parameter17, ",");
                    }
                    else {
                        this.BDHBlFz[n7] = font;
                    }
                    final String parameter18 = this.getParameter("pageurl" + (k + 1) + "-item" + (l + 1));
                    if (parameter18 != null) {
                        this.BdhbLFz[n7] = parameter18;
                    }
                    else {
                        this.BdhbLFz[n7] = "-1";
                    }
                    final String parameter19 = this.getParameter("targetframe" + (k + 1) + "-item" + (l + 1));
                    if (parameter19 != null) {
                        this.bDhbLFz[n7] = parameter19;
                        if (null != null) {}
                    }
                    else {
                        this.bDhbLFz[n7] = this.BDhbLFz;
                    }
                    this.bdHbLFz[n7] = bdHbLFz2;
                    this.bDHbLFz[n7] = bdHbLFz3;
                    final String parameter20 = this.getParameter("horizontalalign" + (k + 1) + "-item" + (l + 1));
                    if (parameter20 != null) {
                        this.BDhBLFz[n7] = this.zzzr(parameter20, 0);
                    }
                    else {
                        this.BDhBLFz[n7] = bdhBLFz;
                    }
                    this.bDHBlFz[n7] = Bdhblfz(this.getParameter("page" + (k + 1) + "-item" + (l + 1)), 160, this.getFontMetrics(this.BDHBlFz[n7]));
                    ++n7;
                    ++l;
                }
                ++k;
            }
            int n8 = 0;
            do {
                String s2;
                if (n8 == 0) {
                    s2 = "previous";
                }
                else {
                    s2 = "next";
                }
                final String parameter21 = this.getParameter(s2 + "buttonlocation");
                if (parameter21 != null) {
                    final int[] bdhblfz2 = bdhblfz(parameter21, ",", 2);
                    if (bdhblfz2 == null) {
                        continue;
                    }
                    this.bdhBlFz[n8] = bdhblfz2[0];
                    this.BdhBlFz[n8] = bdhblfz2[1];
                    if (null != null) {
                        continue;
                    }
                    continue;
                }
                else {
                    this.bdhBlFz[n8] = 0;
                    this.BdhBlFz[n8] = 0;
                }
            } while (++n8 < 2);
            this.setBackground(this.bdHBlFz);
            this.BDhBlfz = this.createImage(180, 180);
            this.BdHBlfz = this.createImage(200, 200);
            this.bDHBlfz = this.BdHBlfz.getGraphics();
            this.bdHBlfz = this.BDhBlfz.getGraphics();
            this.zzzfb();
        }
    }
    
    public MachOneMessengerEval() {
        this.bdhBlFz = new int[] { 50, 50 };
        final int[] bdhBlFz = { 50, 50 };
        this.BDhBlfZ = 7;
        this.bDHBlfZ = false;
        this.BdhbLfZ = false;
        this.bDhbLfZ = 1000;
        this.bDHbLfZ = false;
        this.BdHBLfZ = true;
        this.bDHBLfZ = true;
        this.BDHblFZ = "";
        this.BDHBlFZ = true;
        this.BdhbLFZ = "";
        this.bDhbLFZ = Color.white;
        this.BDhbLFZ = Color.blue;
        this.bdHbLFZ = true;
        this.bDHbLfz = -1;
        this.BDHbLfz = -1;
        this.BDhblFz = -1;
        this.bdHblFz = -1;
        this.BdhBlFz = bdhBlFz;
        this.bDHblFz = new Image[this.BDhBlfZ];
        this.BDHblFz = new boolean[this.BDhBlfZ];
        this.bDhBlFz = new int[2];
        final int[] bDhBlFz = new int[2];
        this.bdHBlFz = Color.white;
        this.BDhBlFz = bDhBlFz;
        this.bdhbLFz = new Font("Arial", 1, 12);
        final Font bDhblfZ = new Font("Arial", 1, 12);
        this.BDhbLFz = "_self";
        this.BdHbLFz = Color.black;
        this.BDHbLFz = Color.yellow;
        this.bdHBLFz = 1000;
        this.BDhblfZ = Color.black;
        this.BDhBLfz = true;
        this.bDhblfZ = bDhblfZ;
    }
    
    static String[] Bdhblfz(final String s, final int n, final FontMetrics fontMetrics) {
        final Vector<String> vector = new Vector<String>();
        String string = "";
        int n2 = 0;
        int n3 = 0;
        int n4 = 1;
        int n5 = 0;
        int n6 = 0;
        final Vector<String> vector2 = vector;
        s.trim();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        int countTokens = stringTokenizer.countTokens();
        String[] array;
        if (countTokens == 0) {
            countTokens = 1;
            array = new String[countTokens];
            array[0] = s;
        }
        else {
            array = new String[countTokens];
            while (stringTokenizer.hasMoreTokens()) {
                array[n3] = stringTokenizer.nextToken();
                ++n3;
            }
        }
        for (int i = 0; i < countTokens; ++i) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(array[i], " ");
            while (stringTokenizer2.hasMoreTokens()) {
                n6 = 1;
                final String nextToken = stringTokenizer2.nextToken();
                n2 += fontMetrics.stringWidth(nextToken + " ");
                if (n2 < n) {
                    if (n4 != 0) {
                        string = nextToken;
                    }
                    else {
                        string = string + " " + nextToken;
                    }
                    n5 = 1;
                }
                else {
                    vector2.addElement(string);
                    if (!stringTokenizer2.hasMoreTokens()) {
                        vector2.addElement(nextToken);
                        if (null != null) {}
                    }
                    else {
                        final int stringWidth = fontMetrics.stringWidth(nextToken + " ");
                        string = nextToken;
                        n2 = stringWidth;
                    }
                    n5 = 0;
                }
                n4 = 0;
            }
            if (n5 != 0 && n6 != 0) {
                n5 = 0;
                vector2.addElement(string);
            }
            if (n6 == 0) {
                vector2.addElement(array[i]);
            }
            if (i < countTokens - 1) {
                vector2.addElement(" ");
            }
            final boolean b = true;
            n6 = 0;
            n2 = 0;
            string = "";
            n4 = (b ? 1 : 0);
        }
        final String[] array2 = new String[vector2.size()];
        vector2.copyInto(array2);
        return array2;
    }
    
    public static void zzzn() {
    }
    
    public void zzzt() {
    }
    
    public void zzze(final int[] array, final int[] array2, final int n, final int n2, final int n3) {
        final int n4 = 200;
        final int n5 = 200;
        final int n6 = n4;
        if (!this.bdHbLFZ) {
            return;
        }
        System.arraycopy(array, 0, array2, 0, 40000);
        for (int i = 0; i < n6 * n5 / (n3 + 1); ++i) {
            final int n7 = (int)(Math.random() * n6 + n6) % n6;
            final int n8 = (int)(Math.random() * n5 + n5) % n5;
            final int n9 = (int)(Math.random() * n3 + 1.0);
            final int n10 = n8 * 200 + n7;
            final int n11 = n8 * 200;
            final int n12 = array[n10] & 0xFF0000;
            final int n13 = array[n10] & 0xFF00;
            final int n14 = array[n10] & 0xFF;
            for (int j = n7 - n9; j < n7 + n9 + 1; ++j) {
                if (j >= 0 && j < n6) {
                    array2[n11 + j] = (0xFF000000 | n12 | n13 | n14);
                }
            }
            for (int k = n8 - n9; k < n8 + n9 + 1; ++k) {
                if (k >= 0 && k < n5) {
                    array2[k * 200 + n7] = (0xFF000000 | n12 | n13 | n14);
                }
            }
        }
    }
    
    void bDhblfz(final int[] array, final int[] array2, final int n, final int n2, int abs, final int n3) {
        final int rgb = this.BdHBlFz.getRGB();
        abs = Math.abs(abs);
        final int[] array3 = new int[abs];
        if (abs > n) {
            abs = n;
        }
        if (!this.bdHbLFZ) {
            return;
        }
        System.arraycopy(array, 0, array2, 0, n * n2);
        for (int i = 0; i < abs; ++i) {
            array3[i] = rgb;
        }
        for (int j = 0; j < n2; ++j) {
            if (j % (n3 * 2) < n3) {
                System.arraycopy(array3, 0, array2, j * n, abs);
                System.arraycopy(array, j * n, array2, j * n + abs, n - abs);
            }
            else {
                System.arraycopy(array3, 0, array2, j * n + n - abs, abs);
                System.arraycopy(array, j * n + abs, array2, j * n, n - abs);
            }
        }
    }
    
    void BDhblfz(final Image image, final int[] array) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, 200, 200, array, 0, 200);
        try {
            if (!pixelGrabber.grabPixels(60000L)) {
                return;
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void paint(final Graphics graphics) {
        if (this.BDHBlFZ) {
            graphics.setColor(this.bDhbLFZ);
            graphics.fillRect(0, 0, 200, 200);
            graphics.setColor(this.BDhbLFZ);
            graphics.setFont(new Font("Arial", 0, 12));
            graphics.drawString("Initializing...", 20, 20);
            return;
        }
        if (!this.bDHbLfZ) {
            this.bDHblfz();
            graphics.drawImage(this.BdHBlfz, 0, 0, this);
            return;
        }
        graphics.drawImage(this.BdhBLfZ = this.createImage(this.BdHbLfZ), 0, 0, null);
        switch (this.bdHBLfZ) {
            case 1: {
                this.l(this.BDhbLfZ, this.bdHbLfZ, 200, 200, this.bDhBLfZ);
            }
            case 2: {
                this.bDhblfz(this.BDhbLfZ, this.bdHbLfZ, 200, 200, this.bDhBLfZ, 1 + this.bDhBLfZ);
            }
            case 3: {
                this.zzze(this.BDhbLfZ, this.bdHbLfZ, 200, 200, this.bDhBLfZ);
            }
            case 4: {
                this.bDhblfz(this.BDhbLfZ, this.bdHbLfZ, 200, 200, this.bDhBLfZ * 5, 1);
            }
            default: {}
        }
    }
    
    void bdHblfz(final boolean b) {
        if (this.bdhBlfZ[this.BdhbLfz] == 0) {
            if (b) {
                this.zzzj();
            }
            else {
                this.zzzd();
            }
            final int bDhblFz = -1;
            this.bdHblFz = -1;
            this.BDhblFz = bDhblFz;
            this.zzzhb(true);
            return;
        }
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.bdHBLfZ = this.bdhBlfZ[this.BdhbLfz];
            if (!b) {
                if (this.BdhbLfz - 1 < 0) {
                    this.bdHBLfZ = this.bdhBlfZ[this.BDHblfZ.length - 1];
                    if (null == null) {}
                }
                else {
                    this.bdHBLfZ = this.bdhBlfZ[this.BdhbLfz - 1];
                }
            }
            this.bDHblfz();
            this.BDhblfz(this.BdHBlfz, this.BDhbLfZ);
            System.arraycopy(this.BDhbLfZ, 0, this.bdHbLfZ, 0, 40000);
            final Image image = this.createImage(this.BdHbLfZ);
            int n = 25;
            this.BdhBLfZ = image;
            if (this.bdHBLfZ == 4) {
                n = 40;
            }
            final boolean bdHbLfZ = true;
            int i = 1;
            this.bDHbLfZ = bdHbLfZ;
            while (i < n) {
                this.bDhBLfZ = i;
                this.paint(graphics);
                this.BdHblfz(1);
                ++i;
            }
            if (b) {
                this.zzzj();
            }
            else {
                this.zzzd();
            }
            final int bDhblFz2 = -1;
            this.bdHblFz = -1;
            this.BDhblFz = bDhblFz2;
            this.zzzhb(false);
            this.bDHblfz();
            this.BDhblfz(this.BdHBlfz, this.BDhbLfZ);
            for (int j = n; j > 0; --j) {
                this.bDhBLfZ = j;
                this.paint(graphics);
                this.BdHblfz(10);
            }
            final boolean bdHbLfZ2 = false;
            this.bdHBLfZ = 0;
            this.bDHbLfZ = bdHbLfZ2;
            this.zzzhb(true);
            this.paint(graphics);
        }
        graphics.dispose();
    }
    
    void BdHblfz(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public void start() {
        if (!this.BdHBLfz) {
            return;
        }
        if (this.BdhBlfz == null) {
            (this.BdhBlfz = new Thread(this)).start();
        }
    }
    
    public void zzzfb() {
        final int n = 0;
        int n2 = 0;
        int n3 = n;
        final int[] array = new int[this.BDHblfZ.length];
        int i;
        int[] array2;
        for (i = 0, array2 = array; i < array2.length; ++i) {
            array2[i] = -1;
        }
        this.zzzh(this.getGraphics());
        for (int j = 0; j < this.BDHblfZ.length; ++j) {
            for (int k = 0; k < this.BDHblfZ[j]; ++k) {
                final FontMetrics fontMetrics = this.getFontMetrics(this.BDHBlFz[n3]);
                final int[] array3 = array2;
                final int n4 = j;
                array3[n4] += this.bDHBlFz[n3].length * (fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent());
                ++n3;
            }
        }
        final int n5 = 0;
        int l = 0;
        int n6 = n5;
        while (l < this.BDHblfZ.length) {
            final FontMetrics fontMetrics2 = this.getFontMetrics(this.BDHBlFz[n6]);
            this.bDHblfZ[n2 + 1] = this.bDHBlFz[n6].length * (fontMetrics2.getMaxAscent() + fontMetrics2.getMaxDescent());
            if (this.bDhBLFz[l] == 0) {
                this.bDHblfZ[n2] = 10;
                final int[] bdHblfZ = this.bDHblfZ;
                final int n7 = n2 + 1;
                bdHblfZ[n7] += 10;
            }
            else if (this.bDhBLFz[l] == 1) {
                this.bDHblfZ[n2] = (180 - array2[l]) / 2;
                final int[] bdHblfZ2 = this.bDHblfZ;
                final int n8 = n2 + 1;
                bdHblfZ2[n8] += this.bDHblfZ[n2];
            }
            else if (this.bDhBLFz[l] == 2) {
                this.bDHblfZ[n2] = 180 - array2[l] - 30;
                final int[] bdHblfZ3 = this.bDHblfZ;
                final int n9 = n2 + 1;
                bdHblfZ3[n9] += this.bDHblfZ[n2] + fontMetrics2.getLeading();
            }
            n6 += this.BDHblfZ[l];
            n2 += this.BDHblfZ[l] + 1;
            ++l;
        }
        final int n10 = 0;
        int n11 = 0;
        int n12 = 0;
        int n13 = n10;
        if (null == null) {}
        while (n12 < this.BDHblfZ.length) {
            if (this.BDHblfZ[n12] > 1) {
                for (int n14 = 0; n14 < this.BDHblfZ[n12]; ++n14) {
                    if (n14 > 1) {
                        final FontMetrics fontMetrics3 = this.getFontMetrics(this.BDHBlFz[n13 - 1]);
                        this.bDHblfZ[n11] = this.bDHblfZ[n11 - 1] + this.bDHBlFz[n13 - 1].length * (fontMetrics3.getMaxAscent() + fontMetrics3.getMaxDescent());
                    }
                    ++n11;
                    ++n13;
                }
                ++n11;
            }
            ++n12;
        }
        final int n15 = 0;
        int n16 = 0;
        int n17 = 0;
        int n18 = n15;
        if (null != null) {}
        while (n17 < this.BDHblfZ.length) {
            n18 += this.BDHblfZ[n17] - 1;
            n16 += this.BDHblfZ[n17];
            final FontMetrics fontMetrics4 = this.getFontMetrics(this.BDHBlFz[n18]);
            this.bDHblfZ[n16] = this.bDHblfZ[n16 - 1] + this.bDHBlFz[n18].length * (fontMetrics4.getMaxAscent() + fontMetrics4.getMaxDescent());
            ++n18;
            ++n16;
            ++n17;
        }
    }
    
    public void stop() {
        if (this.BdhBlfz != null) {
            this.BdhBlfz.stop();
            this.BdhBlfz = null;
        }
    }
    
    public boolean mouseUp(final Event event, final int bdhBLfz, final int bdhBLfz2) {
        if (this.bDHbLfZ) {
            return false;
        }
        this.BdhBLfz = bdhBLfz2;
        this.BDHBLfz = false;
        this.bdhBLfz = bdhBLfz;
        if (this.bdHBLfz && this.bDHbLfz != -1) {
            if (!this.BdhbLFz[this.bDHbLfz].equals("-1")) {
                try {
                    this.BDHBlfz = new URL(this.getDocumentBase(), this.BdhbLFz[this.bDHbLfz]);
                }
                catch (MalformedURLException ex) {}
                this.getAppletContext().showDocument(this.BDHBlfz, this.bDhbLFz[this.bDHbLfz]);
            }
        }
        else {
            final int bdHbLfz = -1;
            this.BDHbLfz = -1;
            this.bDHbLfz = bdHbLfz;
            if (this.bDhBlfZ.inside(bdhBLfz, bdhBLfz2) && this.BDhblFz == 1) {
                this.bdHblfz(true);
            }
            else if (this.BdhBlfZ.inside(bdhBLfz, bdhBLfz2) && this.BDhblFz == 0) {
                this.bdHblfz(false);
            }
        }
        final int bDhblFz = -1;
        this.bdHblFz = -1;
        this.BDhblFz = bDhblFz;
        this.zzzhb(true);
        return true;
    }
    
    private void l(final int[] array, final int[] array2, final int n, final int n2, final int n3) {
        final int n4 = 1;
        final int n5 = n - 1;
        final int n6 = n2 - 1;
        final int n7 = 16711680;
        final int n8 = 65280;
        final int n9 = 255;
        final int n10 = 16;
        final int n11 = 8;
        int i = 0;
        final int n12 = n6;
        while (i < n2) {
            final int n13 = i * n;
            int j = 0;
            final int n14 = n13;
            if (null != null) {}
            while (j < n) {
                if (j <= n5 && i >= n4 - 1 && i <= n12) {
                    int n15 = j - j % n3;
                    int n16 = i - i % n3;
                    if (n15 < 0) {
                        n15 = 0;
                    }
                    if (n16 < 0) {
                        n16 = 0;
                    }
                    final int n17 = n16 * n + n15;
                    array2[n14 + j] = (0xFF000000 | (array[n17] & n7) >> n10 << 16 | (array[n17] & n8) >> n11 << 8 | (array[n17] & n9));
                }
                else {
                    array2[n14 + j] = array[n14 + j];
                }
                ++j;
            }
            ++i;
        }
    }
    
    public boolean mouseExit(final Event event, final int bdhBLfz, final int bdhBLfz2) {
        this.bdHBlfz.setColor(this.bdHBlfz.getColor());
        this.paint(this.getGraphics());
        this.BdhBLfz = bdhBLfz2;
        this.bdHBLfz = false;
        this.bdhBLfz = bdhBLfz;
        if (!this.bDHbLfZ) {
            this.zzzhb(true);
        }
        this.zzzm(-1, -1);
        return true;
    }
    
    public int zzzr(final String s, final int n) {
        if (n == 0) {
            if (s.equalsIgnoreCase("center")) {
                return 1;
            }
            if (!s.equalsIgnoreCase("right")) {
                return 0;
            }
            return 2;
        }
        else {
            if (s.equalsIgnoreCase("center")) {
                return 1;
            }
            if (!s.equalsIgnoreCase("bottom")) {
                return 0;
            }
            return 2;
        }
    }
    
    public boolean mouseEnter(final Event event, final int bdhBLfz, final int bdhBLfz2) {
        final boolean bdHBLfz = true;
        this.bdhBLfz = bdhBLfz;
        this.BdhBLfz = bdhBLfz2;
        this.bdHBLfz = bdHBLfz;
        if (!this.bDHbLfZ) {
            this.zzzhb(true);
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int bdhBLfz, final int bdhBLfz2) {
        if (this.bDHbLfZ) {
            return false;
        }
        this.BDHBLfz = true;
        if (this.bdHBLfz) {
            this.BdhBLfz = bdhBLfz2;
            this.bdhBLfz = bdhBLfz;
            this.zzzhb(true);
        }
        return true;
    }
    
    public int zzzi(final int n, final int n2) {
        if (this.BDhBLFz[n2] == 0) {
            return 0;
        }
        if (this.BDhBLFz[n2] == 1) {
            return (170 - n) / 2 + 5;
        }
        return 170 - n + 10;
    }
    
    public synchronized void zzzl() {
        if (this.bDHbLfZ) {
            return;
        }
        if (this.bDHbLfz == -1 || this.BDHbLfz == -1) {
            return;
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.BDHBlFz[this.bDHbLfz]);
        final int maxAscent = fontMetrics.getMaxAscent();
        final int maxDescent = fontMetrics.getMaxDescent();
        int n = this.bDHblfZ[this.BDHbLfz];
        this.bdHBlfz.setFont(this.BDHBlFz[this.bDHbLfz]);
        this.bdHBlfz.setColor(this.bdHbLFz[this.bDHbLfz]);
        for (int i = 0; i < this.bDHBlFz[this.bDHbLfz].length; ++i) {
            final int zzzi = this.zzzi(fontMetrics.stringWidth(this.bDHBlFz[this.bDHbLfz][i]), this.bDHbLfz);
            final int n2 = n + maxAscent;
            this.bdHBlfz.drawString(this.bDHBlFz[this.bDHbLfz][i], zzzi, n2);
            n = n2 + maxDescent;
        }
        if (!this.bDhblFz) {
            this.paint(this.getGraphics());
        }
    }
    
    public boolean mouseMove(final Event event, final int bdhBLfz, final int bdhBLfz2) {
        if (this.bdHBLfz && !this.BDHBLfz) {
            this.BdhBLfz = bdhBLfz2;
            this.bdhBLfz = bdhBLfz;
            if (!this.bDHbLfZ) {
                this.zzzhb(true);
            }
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int bdhBLfz, final int bdhBLfz2) {
        if (this.bDHbLfZ) {
            return false;
        }
        this.BdhBLfz = bdhBLfz2;
        this.bdhBLfz = bdhBLfz;
        if (!this.bDHBLfz && this.bdHBLfz) {
            if (this.BdhBlfZ.inside(this.bdhBLfz, this.BdhBLfz)) {
                this.BDhblFz = 0;
            }
            else if (this.bDhBlfZ.inside(this.bdhBLfz, this.BdhBLfz)) {
                this.BDhblFz = 1;
                if (null == null) {}
            }
            else {
                this.bdHblFz = this.bDHbLfz;
            }
        }
        else {
            final int bDhblFz = -1;
            this.bdHblFz = -1;
            this.BDhblFz = bDhblFz;
        }
        this.zzzhb(true);
        return true;
    }
    
    void bDHblfz() {
        final String s = "(";
        if (this.bDHblFz[6] != null) {
            this.bDHBlfz.drawImage(this.bDHblFz[6], 0, 0, 200, 200, this);
        }
        this.bDHBlfz.drawImage(this.BDhBlfz, 10, 10, this);
        this.bDHBlfz.setFont(new Font("Arial", 0, 11));
        final String string = s + "C" + ") Wy" + "ka";
        final String s2 = "- Lo";
        final String s3 = string;
        final String string2 = s2 + "ca";
        final String string3 = s3 + "-W";
        final String string4 = string2 + "l ";
        final String string5 = string3 + "ar";
        final String string6 = string4 + "Te";
        final String string7 = string5 + "ze";
        final String string8 = string6 + "st";
        final String string9 = string7 + "ch";
        final String string10 = string8 + "in";
        new StringBuffer().append(string9).append("a ").toString();
        new StringBuffer().append(string10).append("g").toString();
        this.bDHBlfz.setColor(Color.black);
        this.bDHBlfz.fillRect(12, 180, 176, 13);
        this.bDHBlfz.setFont(new Font("Arial", 0, 11));
        this.bDHBlfz.setColor(Color.white);
        this.bDHBlfz.drawString(this.BDHblFZ, 35, 190);
    }
    
    public Image zzzjb(final String s) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        if (s == null || s.equals("") || s.equals("-1")) {
            return null;
        }
        final Image image = this.getImage(this.getDocumentBase(), s);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            return null;
        }
        if (!mediaTracker.isErrorID(0)) {
            return this.zzzcb(image, s);
        }
        return null;
    }
    
    public synchronized void zzzp() {
        this.getGraphics();
        this.bDhbLfz = this.BDhbLfz;
        this.bdHbLfz = this.BdHbLfz;
        this.bdHBlfz.setColor(this.bdHBlFz);
        this.bdHBlfz.fillRect(0, 0, 180, 180);
        for (int i = 0; i < this.BDHblfZ[this.BdhbLfz]; ++i) {
            final FontMetrics fontMetrics = this.getFontMetrics(this.BDHBlFz[this.bDhbLfz]);
            this.bdHBlfz.setFont(this.BDHBlFz[this.bDhbLfz]);
            this.bdHBlfz.setColor(this.bdHbLFz[this.bDhbLfz]);
            int n = this.bDHblfZ[this.bdHbLfz];
            this.zzzhb(true);
            for (int j = 0; j < this.bDHBlFz[this.bDhbLfz].length; ++j) {
                final int zzzi = this.zzzi(fontMetrics.stringWidth(this.bDHBlFz[this.bDhbLfz][j]), this.bDhbLfz);
                final int n2 = n + fontMetrics.getMaxAscent();
                this.bdHBlfz.drawString(this.bDHBlFz[this.bDhbLfz][j], zzzi, n2);
                n = n2 + fontMetrics.getMaxDescent();
            }
            ++this.bDhbLfz;
            ++this.bdHbLfz;
        }
        ++this.bdHbLfz;
        if (this.BDHblFz[0]) {
            this.bdHBlfz.drawImage(this.bDHblFz[0], this.bdhBlFz[0], this.BdhBlFz[0], this);
        }
        if (this.BDHblFz[1]) {
            this.bdHBlfz.drawImage(this.bDHblFz[1], this.bdhBlFz[1], this.BdhBlFz[1], this);
        }
        this.bdHBlfz.setFont(this.bDhblfZ);
        this.bdHBlfz.setColor(this.BDhblfZ);
        if (this.BdhblfZ) {
            this.bdHBlfz.drawString(Integer.toString(this.BdhbLfz + 1) + "/" + Integer.toString(this.bDhBLfz), this.bdHblfZ[this.BdhbLfz], this.BdHblfZ[this.BdhbLfz]);
            this.bdHBlfz.drawString(Integer.toString(this.BdhbLfz + 1), this.bdHblfZ[this.BdhbLfz], this.BdHblfZ[this.BdhbLfz]);
        }
    }
    
    public void zzzj() {
        ++this.BdhbLfz;
        if (this.BdhbLfz >= this.BDHblfZ.length) {
            final boolean bdhbLfz = false;
            this.BDhbLfz = 0;
            this.BdHbLfz = 0;
            this.BdhbLfz = (bdhbLfz ? 1 : 0);
        }
        else {
            this.BDhbLfz += this.BDHblfZ[this.BdhbLfz - 1];
            this.BdHbLfz += this.BDHblfZ[this.BdhbLfz - 1] + 1;
        }
        this.bDhblFz = true;
        this.zzzw();
        this.zzzp();
        this.bDhblFz = false;
    }
    
    public void zzzd() {
        if (this.BdhbLfz == 0) {
            this.BdhbLfz = this.BDHblfZ.length - 1;
            final boolean b = false;
            this.BdHbLfz = (b ? 1 : 0);
            int i = 0;
            this.BDhbLfz = (b ? 1 : 0);
            while (i < this.BDHblfZ.length - 1) {
                this.BDhbLfz += this.BDHblfZ[i];
                this.BdHbLfz += this.BDHblfZ[i] + 1;
                ++i;
            }
            if (null == null) {}
        }
        else if (this.BdhbLfz != 0) {
            --this.BdhbLfz;
            this.BDhbLfz -= this.BDHblfZ[this.BdhbLfz];
            this.BdHbLfz -= this.BDHblfZ[this.BdhbLfz] + 1;
        }
        else {
            final boolean bdhbLfz = false;
            this.BDhbLfz = 0;
            this.BdHbLfz = 0;
            this.BdhbLfz = (bdhbLfz ? 1 : 0);
        }
        this.bDhblFz = true;
        this.zzzw();
        this.zzzp();
        this.bDhblFz = false;
    }
    
    public void run() {
        this.BDhbLfZ = new int[40000];
        this.bdHbLfZ = new int[40000];
        this.BdHbLfZ = new MemoryImageSource(200, 200, this.bdHbLfZ, 0, 200);
        this.bDhbLfz = this.BDhbLfz;
        final int bdHbLfz = this.BdHbLfz;
        this.bDHbLfz = -1;
        this.BDHbLfz = -1;
        this.bdHbLfz = bdHbLfz;
        if (this.BDhBLfz) {
            this.repaint();
            this.bDHBLfz = true;
            for (int i = this.bdhbLfz; i < this.BDhBlfZ; ++i) {
                final Image[] bdHblFz = this.bDHblFz;
                final int n = i;
                final Image zzzjb = this.zzzjb(this.BdHblFz[i]);
                bdHblFz[n] = zzzjb;
                if (zzzjb == null && i > 1 && i != 6) {
                    this.bDHblFz[i] = this.bDHblFz[i - 2];
                }
                this.bdhbLfz = i;
            }
            this.bDhBlFz[0] = this.bDHblFz[0].getWidth(this);
            this.bDhBlFz[1] = this.bDHblFz[1].getWidth(this);
            this.BDhBlFz[0] = this.bDHblFz[0].getHeight(this);
            final int[] bDhBlFz = this.BDhBlFz;
            final int n2 = 1;
            final int height = this.bDHblFz[1].getHeight(this);
            this.bDHBLfz = false;
            this.BDhBLfz = false;
            bDhBlFz[n2] = height;
        }
        if (!this.BDhBLfz) {
            this.BdhBlfZ = new Rectangle(this.bdhBlFz[0] + 10, this.BdhBlFz[0] + 10, this.bDhBlFz[0], this.BDhBlFz[0]);
            this.bDhBlfZ = new Rectangle(this.bdhBlFz[1] + 10, this.BdhBlFz[1] + 10, this.bDhBlFz[1], this.BDhBlFz[1]);
            if (this.BDHblFz[0]) {
                this.bdHBlfz.drawImage(this.bDHblFz[0], this.bdhBlFz[0], this.BdhBlFz[0], this);
            }
            if (this.BDHblFz[1]) {
                this.bdHBlfz.drawImage(this.bDHblFz[1], this.bdhBlFz[1], this.BdhBlFz[1], this);
            }
        }
        this.zzzp();
        if (!this.bDHbLfZ) {
            this.paint(this.getGraphics());
        }
        if (this.bdHBLfz) {
            this.showStatus(this.bDHBLFz);
        }
        else {
            this.showStatus(this.BDHBLFz);
        }
        this.BDHBlFZ = false;
        this.repaint();
        while (true) {
            if (this.BdhbLfZ && !this.bDHbLfZ) {
                try {
                    Thread.sleep(1000L);
                    if (null != null) {}
                }
                catch (InterruptedException ex) {}
                if (this.bdHBLfz) {
                    continue;
                }
                this.bdHblfz(true);
                if (null == null) {
                    continue;
                }
                continue;
            }
            else {
                try {
                    Thread.sleep(250L);
                }
                catch (InterruptedException ex2) {}
            }
        }
    }
    
    public static int zzzb(final String s) {
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
    
    static Font BDHblfz(final String s, final String s2) {
        Font font = new Font("Arial", 0, 12);
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
            font = new Font(array2[0], zzzb(array2[1]), Integer.parseInt(array2[2]));
        }
        catch (Exception ex) {
            return font;
        }
        return font;
    }
    
    static {
        final String bdhblFZ = "";
        MachOneMessengerEval.bDhblFZ = "";
        MachOneMessengerEval.bdHblFZ = "";
        MachOneMessengerEval.BdHblFZ = "(C) Wyka-Warzecha Enterprises";
        MachOneMessengerEval.bDHblFZ = "";
        MachOneMessengerEval.bdhBlFZ = "http://www.wyka-warzecha.com";
        MachOneMessengerEval.bDhBlFZ = true;
        MachOneMessengerEval.BDhBlFZ = true;
        MachOneMessengerEval.bdHBlFZ = true;
        MachOneMessengerEval.BdHBlFZ = true;
        MachOneMessengerEval.bDHBlFZ = true;
        MachOneMessengerEval.BdhblFZ = bdhblFZ;
    }
}
