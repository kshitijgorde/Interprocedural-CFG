import java.awt.Container;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CerebellumImage extends Applet implements Runnable
{
    String[][] menu;
    String INFO;
    Image[] image;
    MediaTracker a45;
    int[] a112;
    int[] a113;
    int[] a114;
    double[][] a115;
    double[][] a116;
    double a117;
    double a118;
    int a0;
    int[] a1;
    int[] a2;
    int[] a3;
    int a4;
    int width;
    int height;
    int a5;
    int a6;
    int a7;
    int a8;
    int a9;
    int a14;
    int a15;
    int a119;
    int a13;
    int a10;
    int a120;
    long a121;
    boolean a16;
    boolean[] a17;
    Thread a18;
    Image a19;
    Graphics a20;
    Color a21;
    Color[] a22;
    Color[] a23;
    Color[] a24;
    Color[] a25;
    String[] a26;
    String[] a27;
    String[] a28;
    String[] a29;
    Font[] a30;
    Font[] a31;
    boolean a32;
    boolean a33;
    Image a35;
    
    public CerebellumImage() {
        this.INFO = "Cerebellum http://go.to/javabase";
        this.a45 = new MediaTracker(this);
        this.a4 = -1;
        this.a5 = -1;
        this.a6 = -1;
        this.a16 = true;
        this.a32 = true;
        this.a33 = false;
    }
    
    public void init() {
        this.width = this.size().width;
        this.height = this.size().height;
        this.a19 = this.createImage(this.width, this.height);
        this.a20 = this.a19.getGraphics();
        System.out.println(this.INFO);
        final String lowerCase = this.getDocumentBase().getHost().toLowerCase();
        if (lowerCase == null || lowerCase.equals("localhost") || lowerCase.equals("127.0.0.1") || lowerCase.equals("")) {
            this.a32 = false;
        }
        if (this.getParameter("key") != null) {
            final String a98 = this.a98(lowerCase);
            String s;
            for (s = this.getParameter("key"); this.a32 && s.indexOf(32) > 0; s = s.substring(s.indexOf(32) + 1, s.length())) {
                this.a32 = !s.substring(0, s.indexOf(32)).equals(a98);
            }
            if (this.a32) {
                this.a32 = !s.equals(a98);
            }
            if (this.a32 && this.getParameter("host") != null && this.getParameter("host").length() > 4 && lowerCase.indexOf(this.getParameter("host")) > -1 && this.getParameter("key").equals(this.a98(this.getParameter("host")))) {
                this.a32 = false;
            }
        }
        final String parameter = this.getParameter("menu");
        this.a4 = -1;
        for (int i = 0; i >= 0; i = parameter.indexOf(125, i + 1), ++this.a4) {}
        if (this.a32) {
            ++this.a4;
        }
        this.menu = new String[this.a4][3];
        this.image = new Image[this.a4];
        this.a2 = new int[this.a4];
        this.a3 = new int[this.a4];
        this.a1 = new int[this.a4];
        this.a17 = new boolean[this.a4];
        this.a115 = new double[this.a4][2];
        this.a116 = new double[this.a4][2];
        this.a112 = new int[this.a4];
        this.a113 = new int[this.a4];
        this.a114 = new int[this.a4];
        if (this.a32) {
            this.menu[this.a4 - 1][0] = "Java Applets";
            this.menu[this.a4 - 1][1] = "http://go.to/javabase";
            this.menu[this.a4 - 1][2] = "_blank";
        }
        int index = 0;
        int n = 0;
        final int[] array = new int[20];
        for (int j = 0; j < this.a4; ++j) {
            if (j < this.a4 - 1 || !this.a32) {
                final int n2 = parameter.indexOf(123, index) + 1;
                index = parameter.indexOf(125, n2);
                final String substring = parameter.substring(n2, index);
                int index2 = -1;
                for (int k = 0; k < 3; ++k) {
                    final int n3 = index2 + 1;
                    index2 = substring.indexOf(44, n3);
                    this.menu[j][k] = substring.substring(n3, (index2 < 0) ? substring.length() : index2);
                    if (index2 < 0) {
                        ++k;
                        while (k < 3) {
                            this.menu[j][k] = "";
                            ++k;
                        }
                    }
                }
            }
            this.a1[j] = 0;
            while (this.menu[j][0].charAt(this.a1[j]) == ' ') {
                final int[] a99 = this.a1;
                final int n4 = j;
                ++a99[n4];
            }
            this.menu[j][0] = this.menu[j][0].trim();
            if (this.menu[j][0].indexOf("=IMG:") != -1) {
                this.image[j] = this.getImage(this.getDocumentBase(), this.menu[j][0].substring(this.menu[j][0].indexOf("=IMG:") + 5, this.menu[j][0].length()));
                this.a45.addImage(this.image[j], j);
                this.menu[j][0] = this.menu[j][0].substring(0, this.menu[j][0].indexOf("=IMG:"));
            }
            array[this.a1[j]] = j;
            if (this.a1[j] == 0) {
                this.a17[j] = true;
                this.a2[j] = -n * this.a14;
                ++n;
            }
            else {
                this.a17[j] = false;
                this.a2[j] = 0;
            }
            this.a112[j] = ((this.a1[j] == 0) ? -1 : array[this.a1[j] - 1]);
            this.a3[j] = 0;
            this.a115[j][0] = (int)(Math.random() * this.width);
            this.a115[j][1] = (int)(Math.random() * this.height);
            if (this.a1[j] + 1 > this.a0) {
                this.a0 = this.a1[j] + 1;
            }
        }
        if (this.getParameter("AppletHomePage") == null || !this.getParameter("AppletHomePage").equals("http://go.to/javabase")) {
            this.menu[0][0] = "AppletHomePage required";
        }
        this.a33 = (this.getParameter("autoCollapse") != null);
        this.a21 = this.a50(this.getParameter("bgColor"), Color.white);
        this.a22 = this.a55(this.getParameter("textColor"), Color.black);
        this.a23 = this.a55(this.getParameter("mouseTextColor"), Color.blue);
        this.a24 = this.a55(this.getParameter("optionColor"), Color.lightGray);
        this.a25 = this.a55(this.getParameter("mouseOptionColor"), Color.white);
        this.a26 = this.a58(this.getParameter("mouseOverEffect"), "left");
        this.a27 = this.a58(this.getParameter("showOptionEffect"), "left");
        this.a28 = this.a58(this.getParameter("mouseDraw"), "fill");
        this.a29 = this.a58(this.getParameter("optionDraw"), "fill");
        this.a7 = this.a47("delayTime", 20);
        this.a8 = this.a47("visibilityStep", 2);
        this.a9 = this.a47("mouseOverStep", 2);
        this.a14 = this.a47("showDelay", 20);
        this.a15 = this.a47("rounding", 5);
        this.a119 = this.a47("padding", 10);
        this.a13 = this.a47("showDepth", 1);
        this.a10 = this.a47("spacing", 50);
        this.a117 = this.a47("drag", 5) / 10.0;
        this.a120 = this.a47("lineThickness", 1);
        this.a30 = this.a64("textFont", new Font("Times", 0, 12));
        if (this.getParameter("mouseTextFont") != null) {
            this.a31 = this.a64("mouseTextFont", new Font("Times", 2, 12));
        }
        else {
            this.a31 = this.a30;
        }
        if (this.getParameter("background") != null) {
            this.a35 = this.getImage(this.getDocumentBase(), this.getParameter("background"));
            this.a45.addImage(this.a35, this.a4);
            try {
                this.a45.waitForID(this.a4);
            }
            catch (InterruptedException ex) {}
        }
        for (int l = 0; l < this.a4; ++l) {
            if (this.a1[l] < this.a13 && l < this.a4 - 1 && this.a1[l + 1] > this.a1[l]) {
                this.a86(l);
            }
            this.a20.setFont(this.a30[this.a1[l]]);
            this.a113[l] = this.a20.getFontMetrics().stringWidth(this.menu[l][0]) + this.a119 * 2;
            this.a114[l] = this.a20.getFontMetrics().getHeight() + this.a119 * 2;
        }
    }
    
    protected int a47(final String s, final int n) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return Integer.parseInt(this.getParameter(s));
        }
        return n;
    }
    
    protected Color a50(final String s, final Color color) {
        if (s == null || s.equals("") || s.length() != 6) {
            return color;
        }
        return new Color(Integer.parseInt(s.substring(0, 2), 16), Integer.parseInt(s.substring(2, 4), 16), Integer.parseInt(s.substring(4, 6), 16));
    }
    
    protected Color[] a55(String s, Color color) {
        final Color[] array = new Color[this.a0];
        if (s == null) {
            s = "";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        for (int i = 0; i < this.a0; ++i) {
            if (stringTokenizer.hasMoreTokens()) {
                array[i] = this.a50(stringTokenizer.nextToken(), color);
                color = array[i];
            }
            else {
                array[i] = color;
            }
        }
        return array;
    }
    
    protected String[] a58(String s, String s2) {
        final String[] array = new String[this.a0];
        if (s == null) {
            s = "";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        for (int i = 0; i < this.a0; ++i) {
            if (stringTokenizer.hasMoreTokens()) {
                array[i] = stringTokenizer.nextToken();
                s2 = array[i];
            }
            else {
                array[i] = s2;
            }
        }
        return array;
    }
    
    protected Font a59(final String s, final Font font) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        String s2;
        if (stringTokenizer.hasMoreTokens()) {
            s2 = stringTokenizer.nextToken();
        }
        else {
            s2 = font.getName();
        }
        int style;
        if (stringTokenizer.hasMoreTokens()) {
            final String lowerCase = stringTokenizer.nextToken().toLowerCase();
            style = (((lowerCase.indexOf(105) != -1) ? 2 : 0) | ((lowerCase.indexOf(98) != -1) ? 1 : 0));
        }
        else {
            style = font.getStyle();
        }
        int n;
        if (stringTokenizer.hasMoreTokens()) {
            n = Integer.parseInt(stringTokenizer.nextToken());
        }
        else {
            n = font.getSize();
        }
        return new Font(s2, style, n);
    }
    
    protected Font[] a64(final String s, Font font) {
        String parameter = this.getParameter(s);
        final Font[] array = new Font[this.a0];
        if (parameter == null) {
            parameter = "";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
        for (int i = 0; i < this.a0; ++i) {
            if (stringTokenizer.hasMoreTokens()) {
                array[i] = this.a59(stringTokenizer.nextToken(), font);
                font = array[i];
            }
            else {
                array[i] = font;
            }
        }
        return array;
    }
    
    protected String a66(final String s, final String s2) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return this.getParameter(s);
        }
        return s2;
    }
    
    public void start() {
        if (this.a18 == null) {
            (this.a18 = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.a18 != null && this.a18.isAlive()) {
            this.a18.stop();
            this.a18 = null;
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.a16 = true;
    }
    
    protected void a67() {
        Thread.yield();
        this.getGraphics().drawImage(this.a19, 0, 0, this);
        Thread.yield();
    }
    
    Color a70(final int n, final Color color, final Color color2) {
        final int n2 = 100 - n;
        return new Color((n2 * color.getRed() + n * color2.getRed()) / 100, (n2 * color.getGreen() + n * color2.getGreen()) / 100, (n2 * color.getBlue() + n * color2.getBlue()) / 100);
    }
    
    public void run() {
        while (this.a18 != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.a16 || this.a118 > 0.1) {
                this.a16 = false;
                this.a20.setColor(this.a21);
                if (this.a35 == null) {
                    this.a20.fillRect(0, 0, this.width, this.height);
                }
                else {
                    this.a20.drawImage(this.a35, 0, 0, this.width, this.height, this.a21, this);
                }
                for (int i = 0; i < this.a4; ++i) {
                    if (this.a112[i] != -1 && this.a2[i] > 0) {
                        this.a20.setColor(this.a70(this.a80(this.a2[i], 0), this.a21, this.a24[this.a1[this.a112[i]]]));
                        this.a20.drawLine((int)this.a115[i][0], (int)this.a115[i][1], (int)this.a115[this.a112[i]][0], (int)this.a115[this.a112[i]][1]);
                        for (int j = 1; j < this.a120; ++j) {
                            this.a20.drawLine((int)this.a115[i][0] - j + this.a120 / 2, (int)this.a115[i][1], (int)this.a115[this.a112[i]][0] - j + this.a120 / 2, (int)this.a115[this.a112[i]][1]);
                            this.a20.drawLine((int)this.a115[i][0], (int)this.a115[i][1] - j + this.a120 / 2, (int)this.a115[this.a112[i]][0], (int)this.a115[this.a112[i]][1] - j + this.a120 / 2);
                        }
                    }
                }
                for (int k = 0; k < this.a4; ++k) {
                    if (this.a2[k] > 0) {
                        if (this.image[k] != null && (this.a45.statusID(k, true) & 0x8) > 0) {
                            this.a113[k] = this.image[k].getWidth(this);
                            this.a114[k] = this.image[k].getHeight(this);
                        }
                        if (this.a5 != k) {
                            this.a20.setFont(this.a30[this.a1[k]]);
                        }
                        else {
                            this.a20.setFont(this.a31[this.a1[k]]);
                        }
                        this.a20.setColor(this.a24[this.a1[k]]);
                        final Rectangle rectangle = new Rectangle((int)this.a115[k][0] - this.a113[k] / 2, (int)this.a115[k][1] - this.a114[k] / 2, this.a113[k], this.a114[k]);
                        if (this.a2[k] != 100) {
                            this.a93(this.a27[this.a1[k]], rectangle, this.a80(this.a2[k], 0), 101, k);
                        }
                        if (this.image[k] != null && (this.a45.statusID(k, true) & 0x8) > 0) {
                            this.a20.create(rectangle.x, rectangle.y, rectangle.width, rectangle.height).drawImage(this.image[k], (int)this.a115[k][0] - this.a113[k] / 2 - rectangle.x, (int)this.a115[k][1] - this.a114[k] / 2 - rectangle.y, this);
                        }
                        else if (this.a29[this.a1[k]].equals("fill")) {
                            this.a20.fillRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, this.a15, this.a15);
                        }
                        else if (this.a29[this.a1[k]].equals("edge")) {
                            this.a20.drawRoundRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, this.a15, this.a15);
                        }
                        if (this.a3[k] != 0 && this.a2[k] == 100) {
                            final Rectangle rectangle2 = new Rectangle((int)this.a115[k][0] - this.a113[k] / 2, (int)this.a115[k][1] - this.a114[k] / 2, this.a113[k], this.a114[k]);
                            this.a93(this.a26[this.a1[k]], rectangle2, this.a3[k], 101, k);
                            this.a20.setColor(this.a70(this.a3[k], this.a24[this.a1[k]], this.a25[this.a1[k]]));
                            if (this.a28[this.a1[k]].equals("fill")) {
                                this.a20.fillRoundRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height, this.a15, this.a15);
                            }
                            else if (this.a28[this.a1[k]].equals("edge")) {
                                this.a20.drawRoundRect(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height, this.a15, this.a15);
                            }
                        }
                        if (this.a3[k] == 0 || this.a2[k] != 100) {
                            this.a20.setColor(this.a70(this.a80(this.a2[k], 2), this.a24[this.a1[k]], this.a22[this.a1[k]]));
                        }
                        else {
                            this.a20.setColor(this.a70(this.a3[k], this.a22[this.a1[k]], this.a23[this.a1[k]]));
                        }
                        if (this.image[k] == null || (this.a45.statusID(k, true) & 0x8) == 0x0) {
                            final Rectangle rectangle3 = new Rectangle((int)this.a115[k][0] - this.a113[k] / 2, (int)this.a115[k][1] - this.a114[k] / 2, this.a113[k], this.a114[k]);
                            this.a20.drawString(this.menu[k][0], rectangle3.x + (this.a113[k] - this.a20.getFontMetrics().stringWidth(this.menu[k][0])) / 2, rectangle3.y + ((this.a114[k] - this.a20.getFontMetrics().getHeight()) / 2 + this.a20.getFontMetrics().getAscent()));
                        }
                    }
                }
                for (int l = 0; l < this.a4; ++l) {
                    if (this.a17[l]) {
                        if (this.a2[l] < 100) {
                            final int[] a2 = this.a2;
                            final int n = l;
                            a2[n] += (int)(this.a8 * this.a121 / this.a7);
                            if (this.a2[l] > 100) {
                                this.a2[l] = 100;
                            }
                            this.a16 = true;
                            this.a118 = this.a117;
                        }
                    }
                    else if (this.a2[l] > 0) {
                        final int[] a3 = this.a2;
                        final int n2 = l;
                        a3[n2] -= (int)(this.a8 * this.a121 / this.a7);
                        if (this.a2[l] < 0) {
                            this.a2[l] = 0;
                        }
                        this.a16 = true;
                        this.a118 = this.a117;
                    }
                    if (this.a3[l] > 0 && this.a5 != l) {
                        final int[] a4 = this.a3;
                        final int n3 = l;
                        a4[n3] -= (int)(this.a9 * this.a121 / this.a7);
                        if (this.a3[l] < 0) {
                            this.a3[l] = 0;
                        }
                        this.a16 = true;
                    }
                }
            }
            if (this.a118 > 0.1) {
                for (int n4 = 0; n4 < this.a4; ++n4) {
                    if (this.a17[n4]) {
                        final double[] array = this.a116[n4];
                        final int n5 = 0;
                        array[n5] -= 100.0 * (this.a115[n4][0] - this.width / 2) / this.width;
                        final double[] array2 = this.a116[n4];
                        final int n6 = 1;
                        array2[n6] -= 100.0 * (this.a115[n4][1] - this.height / 2) / this.height;
                        for (int n7 = 0; n7 < n4; ++n7) {
                            if (this.a17[n7] && this.a112[n7] != n4 && this.a112[n4] != n7) {
                                final double n8 = (this.a115[n4][0] - this.a115[n7][0]) * (this.a115[n4][0] - this.a115[n7][0]) + (this.a115[n4][1] - this.a115[n7][1]) * (this.a115[n4][1] - this.a115[n7][1]);
                                final double n9 = (n8 == 0.0) ? 0.001 : n8;
                                final double n10 = 1000.0 * (this.a115[n4][0] - this.a115[n7][0]) / n9;
                                final double n11 = 1000.0 * (this.a115[n4][1] - this.a115[n7][1]) / n9;
                                final double[] array3 = this.a116[n4];
                                final int n12 = 0;
                                array3[n12] += n10;
                                final double[] array4 = this.a116[n4];
                                final int n13 = 1;
                                array4[n13] += n11;
                                final double[] array5 = this.a116[n7];
                                final int n14 = 0;
                                array5[n14] -= n10;
                                final double[] array6 = this.a116[n7];
                                final int n15 = 1;
                                array6[n15] -= n11;
                            }
                        }
                        if (this.a112[n4] != -1) {
                            final int n16 = this.a112[n4];
                            final double sqrt = Math.sqrt((this.a115[n4][0] - this.a115[n16][0]) * (this.a115[n4][0] - this.a115[n16][0]) + (this.a115[n4][1] - this.a115[n16][1]) * (this.a115[n4][1] - this.a115[n16][1]));
                            final double n17 = sqrt - this.a10;
                            final double n18 = (sqrt == 0.0) ? 0.001 : sqrt;
                            final double n19 = 0.5 * (this.a115[n4][0] - this.a115[n16][0]) * n17 / n18;
                            final double n20 = 0.5 * (this.a115[n4][1] - this.a115[n16][1]) * n17 / n18;
                            final double[] array7 = this.a116[n4];
                            final int n21 = 0;
                            array7[n21] -= n19;
                            final double[] array8 = this.a116[n4];
                            final int n22 = 1;
                            array8[n22] -= n20;
                            final double[] array9 = this.a116[n16];
                            final int n23 = 0;
                            array9[n23] += n19;
                            final double[] array10 = this.a116[n16];
                            final int n24 = 1;
                            array10[n24] += n20;
                        }
                    }
                }
                for (int n25 = 0; n25 < this.a4; ++n25) {
                    if (this.a17[n25]) {
                        final double[] array11 = this.a116[n25];
                        final int n26 = 0;
                        array11[n26] *= this.a118;
                        final double[] array12 = this.a116[n25];
                        final int n27 = 1;
                        array12[n27] *= this.a118;
                        if (this.a118 > 0.1) {
                            this.a118 -= 5.0E-4;
                        }
                        final double[] array13 = this.a115[n25];
                        final int n28 = 0;
                        array13[n28] += this.a116[n25][0];
                        final double[] array14 = this.a115[n25];
                        final int n29 = 1;
                        array14[n29] += this.a116[n25][1];
                        if (this.a115[n25][0] < this.a113[n25] / 2) {
                            this.a115[n25][0] = this.a113[n25] / 2;
                        }
                        if (this.a115[n25][1] < this.a114[n25] / 2) {
                            this.a115[n25][1] = this.a114[n25] / 2;
                        }
                        if (this.a115[n25][0] > this.width - this.a113[n25] / 2 - 2) {
                            this.a115[n25][0] = this.width - this.a113[n25] / 2 - 2;
                        }
                        if (this.a115[n25][1] > this.height - this.a114[n25] / 2 - 2) {
                            this.a115[n25][1] = this.height - this.a114[n25] / 2 - 2;
                        }
                    }
                }
            }
            this.a121 = System.currentTimeMillis() - currentTimeMillis;
            try {
                Thread.sleep(Math.max(7L, this.a7 - this.a121));
            }
            catch (InterruptedException ex) {}
            this.a121 = System.currentTimeMillis() - currentTimeMillis;
            this.a67();
        }
    }
    
    protected boolean last(final int n) {
        boolean b = true;
        for (int n2 = n + 1; b && n2 < this.a4 && this.a1[n2] >= this.a1[n]; b = (this.a1[n2] != this.a1[n]), ++n2) {}
        return b;
    }
    
    private int a80(final int n, final int n2) {
        if (n < n2 * 25) {
            return 0;
        }
        if (n > n2 * 25 + 50) {
            return 100;
        }
        return (n - n2 * 25) * 2;
    }
    
    private int a82(final int n, final int n2) {
        int n3 = -1;
        long n4 = 40000L;
        for (int i = 0; i < this.a4; ++i) {
            if (this.a17[i]) {
                final long n5 = ((int)this.a115[i][0] - n) * ((int)this.a115[i][0] - n) + ((int)this.a115[i][1] - n2) * ((int)this.a115[i][1] - n2);
                if (n5 < n4) {
                    n3 = i;
                    n4 = n5;
                }
            }
        }
        return n3;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        final int a82 = this.a82(n, n2);
        if (a82 == -1) {
            return true;
        }
        this.a6 = a82;
        return this.a16 = true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        final int a82 = this.a82(n, n2);
        if (a82 == -1) {
            return true;
        }
        if (a82 + 1 < this.a4 && this.a1[a82 + 1] > this.a1[a82]) {
            if (!this.a17[a82 + 1]) {
                this.a86(a82);
            }
            else {
                this.a88(a82);
            }
        }
        if (this.menu[a82][1] != "") {
            URL url = null;
            try {
                url = new URL(this.menu[a82][1]);
            }
            catch (MalformedURLException ex) {
                try {
                    url = new URL(this.getDocumentBase(), this.menu[a82][1]);
                }
                catch (MalformedURLException ex2) {}
            }
            if (url != null) {
                if (this.menu[a82][2] != "") {
                    this.getAppletContext().showDocument(url, this.menu[a82][2]);
                }
                else {
                    this.getAppletContext().showDocument(url);
                }
            }
        }
        return this.a16 = true;
    }
    
    protected void a86(final int n) {
        int n2 = n + 1;
        int n3 = 0;
        int n4 = 0;
        while (n2 < this.a4 && this.a1[n2] > this.a1[n]) {
            if (this.a1[n2] == this.a1[n] + 1) {
                ++n4;
            }
            ++n2;
        }
        int n5;
        for (n5 = n + 1; n5 < this.a4 && this.a1[n5] > this.a1[n]; ++n5) {
            if (this.a1[n5] == this.a1[n] + 1) {
                this.a17[n5] = true;
                this.a2[n5] = -n3 * this.a14;
                ++n3;
                if (this.a112[n] == -1) {
                    final double n6 = 0.785 + 6.283185306 * n3 / n4;
                    this.a115[n5][0] = this.a115[n][0] + 20.0 * Math.cos(n6);
                    this.a115[n5][1] = this.a115[n][1] + 20.0 * Math.sin(n6);
                }
                else {
                    final double n7 = Math.atan2(this.a115[n][0] - this.a115[this.a112[n]][0], this.a115[this.a112[n]][1] - this.a115[n][1]) + 6.283185306 * (n3 + 1) / (n4 + 1);
                    this.a115[n5][0] = this.a115[n][0] + 20.0 * Math.cos(n7);
                    this.a115[n5][1] = this.a115[n][1] + 20.0 * Math.sin(n7);
                }
            }
        }
        if (this.a33) {
            for (int i = n5; i < this.a4; ++i) {
                if (this.a1[i] >= this.a1[n]) {
                    this.a88(i);
                }
            }
            for (int j = 0; j < n; ++j) {
                if (this.a1[j] >= this.a1[n]) {
                    this.a88(j);
                }
            }
        }
    }
    
    protected void a88(final int n) {
        int n2 = 0;
        int n3 = n + 1;
        int n4 = 0;
        while (n3 < this.a4 && this.a1[n3] > this.a1[n]) {
            if (this.a17[n3]) {
                ++n2;
            }
            ++n3;
        }
        for (int n5 = n + 1; n5 < this.a4 && this.a1[n5] > this.a1[n]; ++n5) {
            if (this.a17[n5]) {
                this.a17[n5] = false;
                ++n4;
                final int[] a2 = this.a2;
                final int n6 = n5;
                a2[n6] += (n2 - n4) * this.a14;
            }
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.a5 = this.a82(n, n2);
        if (this.a5 > -1) {
            this.showStatus(this.menu[this.a5][0]);
            this.a3[this.a5] = 100;
            if (this.menu[this.a5][0].length() > 0 && this.menu[this.a5][1].length() > 0) {
                this.setCursor(12);
            }
            else {
                this.setCursor(0);
            }
        }
        else {
            this.showStatus("");
        }
        return this.a16 = true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.a5 = -1;
        return this.a16 = true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.mouseMove(event, n, n2);
    }
    
    protected void a93(final String s, final Rectangle rectangle, final int n, final int n2, final int n3) {
        final double n4 = n / (n2 - 1);
        if (s.equals("flip") || s.equals("flipXY")) {
            final int n5 = (int)(n2 / 1.5);
            int n6 = (n2 - n) % n5;
            if (n6 > n5 / 2 - 1) {
                n6 = n5 - 1 - n6;
            }
            rectangle.grow(0, -(rectangle.height * n6) / (n5 - 2));
        }
        if (s.equals("flipY") || s.equals("flipXY")) {
            final int n7 = (int)(n2 / 1.5);
            int n8 = (n2 - n) % n7;
            if (n8 > n7 / 2 - 1) {
                n8 = n7 - 1 - n8;
            }
            rectangle.grow(-(rectangle.width * n8) / (n7 - 2), 0);
            return;
        }
        if ((s.equals("leftright") && n3 % 2 == 0) || s.equals("left")) {
            rectangle.width *= (int)n4;
            return;
        }
        if ((s.equals("leftright") && n3 % 2 == 1) || s.equals("right")) {
            final int x = rectangle.x;
            rectangle.x = (int)(rectangle.x + rectangle.width + (rectangle.x - rectangle.x - rectangle.width) * n4);
            rectangle.width = rectangle.width + x - rectangle.x;
            return;
        }
        if ((s.equals("updown") && n3 % 2 == 1) || s.equals("down")) {
            rectangle.y = (int)(rectangle.y + rectangle.height + (rectangle.y - rectangle.y - rectangle.height) * n4);
            rectangle.height *= (int)n4;
            return;
        }
        if ((s.equals("updown") && n3 % 2 == 0) || s.equals("up")) {
            rectangle.height *= (int)n4;
            return;
        }
        if (s.equals("shrinkVer")) {
            rectangle.grow(0, -(int)(rectangle.height * (1.0 - n4) / 2.0));
            return;
        }
        if (s.equals("shrinkHor")) {
            rectangle.grow(-(int)(rectangle.width * (1.0 - n4) / 2.0), 0);
            return;
        }
        if (s.equals("shrink2")) {
            rectangle.grow(-(int)(rectangle.height * (1.0 - n4) / 2.0), -(int)(rectangle.height * (1.0 - n4) / 2.0));
            return;
        }
        if (s.equals("shrink")) {
            rectangle.grow(-(int)(rectangle.width * (1.0 - n4) / 2.0), -(int)(rectangle.height * (1.0 - n4) / 2.0));
        }
    }
    
    protected String a98(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)((charArray[i] + i * 7) % 26 + 97);
        }
        return new String(charArray);
    }
    
    protected double a132(final double n) {
        if (n == 0.0) {
            return 0.0;
        }
        return 1.0 / n;
    }
    
    public void setCursor(final int cursor) {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        ((Frame)container).setCursor(cursor);
    }
}
