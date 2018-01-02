import java.awt.Container;
import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.Color;
import java.awt.MediaTracker;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Transitions extends Applet implements Runnable
{
    String INFO;
    Image[] image;
    String[][] menu;
    String[] a258;
    int a259;
    int current;
    int last;
    int a4;
    int a91;
    int width;
    int height;
    int a260;
    int a261;
    int a262;
    int a102;
    int a103;
    int a7;
    Thread a18;
    Image a19;
    Graphics a20;
    boolean a32;
    boolean a263;
    boolean a264;
    boolean a265;
    AudioClip a106;
    MediaTracker a45;
    final int a266 = 40;
    
    public Transitions() {
        this.INFO = "Transitions http://go.to/javabase";
        this.a260 = 4;
        this.a261 = 17;
        this.a102 = 150;
        this.a103 = 150;
        this.a7 = 20;
        this.a32 = true;
        this.a263 = false;
        this.a265 = false;
    }
    
    public void init() {
        this.width = this.size().width;
        this.height = this.size().height;
        this.getGraphics().setColor(Color.white);
        this.getGraphics().fillRect(0, 0, this.width, this.height);
        this.getGraphics().drawString("Loading..", 20, 20);
        this.a19 = this.createImage(this.width, this.height * 2);
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
        if (this.getParameter("sound") != null) {
            this.a106 = this.getAudioClip(this.getCodeBase(), this.getParameter("sound"));
        }
        this.a103 = this.a47("ChangeFrames", 50);
        this.a102 = this.a103 + this.a47("WaitFrames", 50);
        this.a7 = this.a47("DelayTime", 20);
        this.a264 = (this.getParameter("random") != null);
        this.a265 = (this.getParameter("printNames") != null);
        final String parameter = this.getParameter("images");
        this.a4 = -1;
        for (int i = 0; i >= 0; i = parameter.indexOf(125, i + 1), ++this.a4) {}
        this.image = new Image[this.a4 + 1];
        this.a258 = new String[this.a4];
        this.menu = new String[this.a4][3];
        this.a45 = new MediaTracker(this);
        int index = -1;
        for (int j = 0; j < this.a4; ++j) {
            final int n = parameter.indexOf(123, index) + 1;
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter.substring(index + 1, n - 1));
            if (stringTokenizer.hasMoreTokens()) {
                this.a258[j] = stringTokenizer.nextToken().toLowerCase();
            }
            else {
                this.a258[j] = "simple";
            }
            index = parameter.indexOf(125, n);
            final String substring = parameter.substring(n, index);
            int index2 = -1;
            for (int k = 0; k < 3; ++k) {
                final int n2 = index2 + 1;
                index2 = substring.indexOf(44, n2);
                this.menu[j][k] = substring.substring(n2, (index2 < 0) ? substring.length() : index2);
                if (index2 < 0) {
                    ++k;
                    while (k < 3) {
                        this.menu[j][k] = "";
                        ++k;
                    }
                }
            }
            if (this.a32 && this.menu[j][1] != null) {
                this.menu[j][1] = "http://go.to/javabase";
            }
            this.image[j] = this.getImage(this.getDocumentBase(), this.menu[j][0]);
            this.a45.addImage(this.image[j], j);
        }
        this.last = 0;
        this.current = this.a4;
        this.a91 = this.a103;
        this.image[this.a4] = this.createImage(this.width, this.height);
        this.image[this.a4].getGraphics().setColor(Color.white);
        this.image[this.a4].getGraphics().fillRect(0, 0, this.width, this.height);
        this.a20.setFont(new Font("Times", 0, 18));
    }
    
    protected int a47(final String s, final int n) {
        if (this.getParameter(s) != null && !this.getParameter(s).equals("")) {
            return Integer.parseInt(this.getParameter(s));
        }
        return n;
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
    }
    
    protected void a67() {
        if (this.a265 && this.current != this.a4) {
            this.a20.setColor(Color.white);
            this.a20.fillRect(0, 0, this.width, 25);
            this.a20.setColor(Color.black);
            this.a20.drawString(this.a258[this.current], 5, 20);
        }
        this.getGraphics().drawImage(this.a19, 0, 0, this);
    }
    
    public void run() {
        while (this.a18 != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            if (this.a32) {
                this.showStatus("http://go.to/javabase");
            }
            if (this.a91 < this.a103) {
                this.a107(this.last, this.current, this.a91 / this.a103);
            }
            else {
                this.a20.drawImage(this.image[this.current], 0, 0, this);
            }
            ++this.a91;
            if (this.a91 >= this.a102) {
                this.a91 = 0;
                this.a262 = (int)(Math.random() * 100.0);
                this.last = this.current;
                ++this.current;
                if (this.current >= this.a4) {
                    this.current = 0;
                }
                if (this.a264) {
                    switch ((int)(Math.random() * this.a261)) {
                        case 0: {
                            this.a258[this.current] = "shift";
                            break;
                        }
                        case 1: {
                            this.a258[this.current] = "blinds";
                            break;
                        }
                        case 2: {
                            this.a258[this.current] = "smudge";
                            break;
                        }
                        case 3: {
                            this.a258[this.current] = "curl";
                            break;
                        }
                        case 4: {
                            this.a258[this.current] = "multicurl";
                            break;
                        }
                        case 5: {
                            this.a258[this.current] = "peel";
                            break;
                        }
                        case 6: {
                            this.a258[this.current] = "multipeel";
                            break;
                        }
                        case 7: {
                            this.a258[this.current] = "squares";
                            break;
                        }
                        case 8: {
                            this.a258[this.current] = "puzzle";
                            break;
                        }
                        case 9: {
                            this.a258[this.current] = "hole";
                            break;
                        }
                        case 10: {
                            this.a258[this.current] = "page";
                            break;
                        }
                        case 11: {
                            this.a258[this.current] = "rect";
                            break;
                        }
                        case 12: {
                            this.a258[this.current] = "rectgrow";
                            break;
                        }
                        case 13: {
                            this.a258[this.current] = "rectgrow2";
                            break;
                        }
                        case 14: {
                            this.a258[this.current] = "rectgrowver";
                            break;
                        }
                        case 15: {
                            this.a258[this.current] = "rectgrowhor";
                            break;
                        }
                        case 16: {
                            this.a258[this.current] = "stretch";
                            break;
                        }
                    }
                    switch ((int)(Math.random() * 4.0)) {
                        case 0: {
                            final String[] a258 = this.a258;
                            final int current = this.current;
                            a258[current] += "left";
                            break;
                        }
                        case 1: {
                            final String[] a259 = this.a258;
                            final int current2 = this.current;
                            a259[current2] += "right";
                            break;
                        }
                        case 2: {
                            final String[] a260 = this.a258;
                            final int current3 = this.current;
                            a260[current3] += "up";
                            break;
                        }
                        case 3: {
                            final String[] a261 = this.a258;
                            final int current4 = this.current;
                            a261[current4] += "down";
                            break;
                        }
                    }
                    switch ((int)(Math.random() * 2.0)) {
                        case 0: {
                            final String[] a262 = this.a258;
                            final int current5 = this.current;
                            a262[current5] += "inv";
                            break;
                        }
                    }
                }
                this.a267();
                if (this.a106 != null) {
                    this.a106.play();
                }
            }
            try {
                Thread.sleep(Math.max(10L, this.a7 - System.currentTimeMillis() + currentTimeMillis));
            }
            catch (InterruptedException ex) {}
            this.a67();
        }
    }
    
    private void a267() {
        if (this.menu[this.current][1] != "") {
            this.setCursor(12);
            this.showStatus(this.menu[this.current][1]);
        }
        else {
            this.setCursor(0);
        }
        try {
            this.a45.waitForID(this.current);
        }
        catch (Exception ex) {}
        if (this.image[this.current].getWidth(this) != this.width || this.image[this.current].getHeight(this) != this.height) {
            final Image image = this.createImage(this.width, this.height);
            image.getGraphics().drawImage(this.image[this.current], 0, 0, this.width, this.height, this);
            this.image[this.current] = image;
        }
    }
    
    private void a107(final int n, final int n2, double n3) {
        this.a263 = this.a258[n2].endsWith("inv");
        if (this.a258[n2].startsWith("shiftleft")) {
            final int n4 = (int)(n3 * this.width);
            this.a20.drawImage(this.image[n], -n4, 0, this);
            this.a20.drawImage(this.image[n2], this.width - n4, 0, this);
        }
        else if (this.a258[n2].startsWith("shiftright")) {
            final int n5 = (int)(n3 * this.width);
            this.a20.drawImage(this.image[n], n5, 0, this);
            this.a20.drawImage(this.image[n2], n5 - this.width, 0, this);
        }
        else if (this.a258[n2].startsWith("shiftup")) {
            final int n6 = (int)(n3 * this.height);
            this.a20.drawImage(this.image[n], 0, -n6, this);
            this.a20.drawImage(this.image[n2], 0, this.height - n6, this);
        }
        else if (this.a258[n2].startsWith("shiftdown")) {
            final int n7 = (int)(n3 * this.height);
            this.a20.drawImage(this.image[n], 0, n7, this);
            this.a20.drawImage(this.image[n2], 0, n7 - this.height, this);
        }
        else if (this.a263) {
            this.a20.drawImage(this.image[n], 0, this.height, this);
            this.a20.drawImage(this.image[n2], 0, 0, this);
            n3 = 1.0 - n3;
        }
        else {
            this.a20.drawImage(this.image[n], 0, 0, this);
            this.a20.drawImage(this.image[n2], 0, this.height, this);
        }
        if (this.a258[n2].indexOf("left") != -1) {
            this.a259 = 0;
        }
        else if (this.a258[n2].indexOf("up") != -1) {
            this.a259 = 1;
        }
        else if (this.a258[n2].indexOf("right") != -1) {
            this.a259 = 2;
        }
        else {
            this.a259 = 3;
        }
        if (this.a258[n2].startsWith("smudge") && this.a259 % 2 == 0) {
            int n8 = 0;
            final int n9 = (int)(n3 * this.width);
            final int n10 = (n3 > 0.25) ? 0 : ((int)((n3 * 4.0 - 1.0) * this.width));
            this.a278(0, this.height, n9, this.height, -n10, -this.height);
            for (int i = n9; i < this.width; ++i) {
                final int n11 = (i - n9) / 5;
                for (int j = 0; j < n11 + 1; ++j) {
                    this.a278(i, this.height, 1, this.height, n8 + j - n10, -this.height);
                }
                if (i > n9) {
                    n8 += n11;
                }
            }
            return;
        }
        if (this.a258[n2].startsWith("smudgedown")) {
            int n12 = 0;
            final int n13 = (int)(n3 * this.height);
            final int n14 = (n3 > 0.25) ? 0 : ((int)((n3 * 4.0 - 1.0) * this.height));
            this.a20.copyArea(0, this.height, this.width, n13, 0, -this.height - n14);
            for (int k = n13; k < this.height; ++k) {
                final int n15 = (k - n13) / 5;
                for (int l = 0; l < n15 + 1; ++l) {
                    this.a20.copyArea(0, this.height + k, this.width, 1, 0, -this.height + n12 + l - n14);
                }
                if (k > n13) {
                    n12 += n15;
                }
            }
            return;
        }
        if (this.a258[n2].startsWith("smudgeup")) {
            int n16 = 0;
            final int n17 = (int)(n3 * this.height);
            final int n18 = (n3 > 0.25) ? 0 : ((int)((n3 * 4.0 - 1.0) * this.height));
            this.a20.copyArea(0, 2 * this.height - n17, this.width, n17, 0, -this.height + n18);
            for (int n19 = this.height - n17; n19 > 0; --n19) {
                final int n20 = (this.height - n17 - n19) / 5;
                for (int n21 = 0; n21 < n20 + 1; ++n21) {
                    this.a20.copyArea(0, n19 + this.height, this.width, 1, 0, n21 + n16 - n20 - this.height + n18);
                }
                if (n19 < this.height - n17) {
                    n16 -= n20;
                }
            }
            return;
        }
        if (this.a258[n2].startsWith("curl")) {
            final int n22 = (int)(n3 * (this.width + 20)) - 20;
            this.a278(this.width - n22, this.height, n22, this.height, 0, -this.height);
            for (int n23 = this.width - n22; n23 > 0; --n23) {
                if (2 * (this.width - n22 - 20 - n23) >= this.width) {
                    return;
                }
                final double n24 = 3.141592654 * (this.width - n22 - n23) / 40.0;
                if (this.width - n22 - n23 < 40) {
                    this.a278(n23, this.height, 1, this.height, (n23 < this.width - n22 - 20) ? (2 * (this.width - n22 - 20 - n23)) : 0, -this.height - (int)(20.0 * (1.0 - Math.cos(n24))));
                }
                else {
                    this.a278(n23, this.height, 1, this.height, 2 * (this.width - n22 - 20 - n23), -this.height - 40);
                }
            }
        }
        else {
            if (this.a258[n2].startsWith("multicurl")) {
                for (int n25 = 0; n25 < this.a260; ++n25) {
                    final int n26 = (int)(n3 * this.width * 2.0 - this.width / 2 + Math.sin(n25 * 5 + this.a262) * this.width / 2.0);
                    this.a278(this.width - n26, this.height + n25 * this.height / this.a260, n26, (n25 + 1) * this.height / this.a260 - n25 * this.height / this.a260, 0, -this.height);
                    for (int n27 = this.width - n26; n27 > 0 && 2 * (this.width - n26 - 20 - n27) < this.width; --n27) {
                        final double n28 = 3.141592654 * (this.width - n26 - n27) / 40.0;
                        if (this.width - n26 - n27 < 40) {
                            this.a278(n27, this.height + n25 * this.height / this.a260, 1, (n25 + 1) * this.height / this.a260 - n25 * this.height / this.a260, (n27 < this.width - n26 - 20) ? (2 * (this.width - n26 - 20 - n27)) : 0, -this.height - (int)(20.0 * (1.0 - Math.cos(n28))));
                        }
                        else {
                            this.a278(n27, this.height + n25 * this.height / this.a260, 1, (n25 + 1) * this.height / this.a260 - n25 * this.height / this.a260, 2 * (this.width - n26 - 20 - n27), -this.height - 40);
                        }
                    }
                }
                return;
            }
            if (this.a258[n2].startsWith("peel")) {
                final Image image = this.createImage(this.width, this.height + this.width);
                final Graphics graphics = image.getGraphics();
                graphics.drawImage(this.image[this.a263 ? n2 : n], 0, 0, this);
                final int n29 = (int)(n3 * (this.width + this.height));
                for (int n30 = 0; n30 < this.width; ++n30) {
                    graphics.copyArea(n30, 0, 1, this.height, 0, n30);
                }
                for (int n31 = 1; n31 <= n29; ++n31) {
                    graphics.copyArea(this.width - n31, this.width + this.height - n31 - 1, n31, 1, -(n29 - n31) * 2 / 3, -2 * (n29 - n31));
                }
                for (int n32 = 0; n32 < this.width; ++n32) {
                    graphics.copyArea(n32, n32, 1, this.height, 0, -n32);
                }
                this.a20.create(0, 0, this.width, this.height).drawImage(image, 0, 0, this);
                for (int n33 = 1; n33 <= n29; ++n33) {
                    this.a20.copyArea(this.width - n29 + n33, 2 * this.height - n33, n29 - n33, 1, 0, -this.height);
                }
                return;
            }
            if (this.a258[n2].startsWith("multipeel")) {
                final Image image2 = this.createImage(this.width, this.height + this.width);
                final Graphics graphics2 = image2.getGraphics();
                graphics2.drawImage(this.image[this.a263 ? n2 : n], 0, 0, this);
                for (int n34 = 0; n34 < this.width; ++n34) {
                    graphics2.copyArea(n34, 0, 1, this.height, 0, n34);
                }
                for (int n35 = 0; n35 < this.a260; ++n35) {
                    final int n36 = (int)(n3 * (this.width + this.height) * 2.0 - (this.width + this.height) + (this.a260 - n35) * (this.width + this.height) / this.a260);
                    if (n36 < this.width + this.height + 1) {
                        for (int n37 = 1; n37 < n36 && n37 < this.height; ++n37) {
                            final int min = this.min(n37, (n35 + 1) * this.width / this.a260 - n35 * this.width / this.a260);
                            graphics2.copyArea(this.width - n35 * this.width / this.a260 - min, this.width + this.height - n35 * this.width / this.a260 - n37 - 1, min, 1, -(n36 - n37) * 2 / 3, -2 * (n36 - n37));
                        }
                    }
                }
                for (int n38 = 0; n38 < this.width; ++n38) {
                    graphics2.copyArea(n38, n38, 1, this.height, 0, -n38);
                }
                this.a20.create(0, 0, this.width, this.height).drawImage(image2, 0, 0, this);
                for (int n39 = 0; n39 < this.a260; ++n39) {
                    for (int n40 = (int)(n3 * (this.width + this.height) * 2.0 - (this.width + this.height) + (this.a260 - n39) * (this.width + this.height) / this.a260), n41 = 1; n41 <= n40 && n41 < this.height + 1; ++n41) {
                        final int min2 = this.min(n40 - n41, (n39 + 1) * this.width / this.a260 - n39 * this.width / this.a260);
                        this.a20.copyArea(this.width - min2 - n39 * this.width / this.a260, 2 * this.height - n41, min2, 1, 0, -this.height);
                    }
                }
                return;
            }
            if (this.a258[n2].startsWith("squares")) {
                final int n42 = (int)(n3 * 40.0);
                for (int n43 = 0; n43 < this.width; n43 += 40) {
                    for (int n44 = 0; n44 < this.height; n44 += 40) {
                        this.a20.copyArea(n43, n44 + this.height, n42, n42, 0, -this.height);
                    }
                }
                return;
            }
            if (this.a258[n2].startsWith("puzzle")) {
                if (n3 > 0.5) {
                    this.a20.copyArea(0, this.height, this.width, this.height, 0, -this.height);
                }
                for (int n45 = 0; n45 < this.width; n45 += 20) {
                    this.a20.copyArea(n45, 0, 20, this.height, 0, (int)(n3 * (1.0 - n3) * 10.0 * 20.0 * Math.sin(n3 * 5.0 + n45 / 4)));
                }
                for (int n46 = 0; n46 < this.height; n46 += 20) {
                    this.a20.copyArea(0, n46, this.width, 20, (int)(n3 * (1.0 - n3) * 20.0 * 10.0 * Math.sin(n3 * 5.0 + n46 / 4)), 0);
                }
                return;
            }
            if (this.a258[n2].startsWith("hole")) {
                for (int n47 = (int)(n3 * Math.sqrt(this.width * this.width + this.height * this.height) / 2.0), max = this.max(-n47, -this.width / 2); max < this.min(n47, this.width / 2); ++max) {
                    final int n48 = (int)Math.sqrt(n47 * n47 - max * max);
                    this.a20.copyArea(max + this.width / 2, this.height * 3 / 2 - n48, 1, n48 * 2, 0, -this.height);
                }
                return;
            }
            if (this.a258[n2].startsWith("pagedown")) {
                n3 *= n3;
                final int n49 = (int)(2 * this.height * (n3 - 0.5));
                if (n49 > 0) {
                    this.a20.copyArea(0, this.height, this.width, n49, 0, -this.height);
                }
                for (int n50 = n49; n50 < this.height; ++n50) {
                    this.a20.copyArea(0, n50 + this.height, this.width, 1, this.width * (n50 - n49) * (n50 - n49) / this.height / this.height, -this.height - 20 * (n50 - n49) * (n50 - n49) / 5000);
                }
                return;
            }
            if (this.a258[n2].startsWith("pageup")) {
                n3 *= n3;
                final int n51 = (int)(2 * this.height * (n3 - 0.5));
                if (n51 > 0) {
                    this.a20.copyArea(0, 2 * this.height - n51, this.width, n51, 0, -this.height);
                }
                for (int n52 = n51; n52 < this.height; ++n52) {
                    this.a20.copyArea(0, this.width - n52 + this.height, this.width, 1, this.width * (n52 - n51) * (n52 - n51) / this.height / this.height, -this.height + 20 * (n52 - n51) * (n52 - n51) / 5000);
                }
                return;
            }
            if (this.a258[n2].startsWith("page") && this.a259 % 2 == 0) {
                n3 *= n3;
                final int n53 = (int)(2 * this.width * (n3 - 0.5));
                if (n53 > 0) {
                    this.a278(0, this.height, n53, this.height, 0, -this.height);
                }
                for (int n54 = n53; n54 < this.width; ++n54) {
                    this.a278(n54, this.height, 1, this.height, -20 * (n54 - n53) * (n54 - n53) / 5000, -this.height + this.height * (n54 - n53) * (n54 - n53) / this.width / this.width);
                }
                return;
            }
            if (this.a258[n2].startsWith("rect")) {
                final Rectangle rectangle = new Rectangle(0, 0, this.width, this.height);
                if (this.a258[n2].endsWith("inv")) {
                    this.a279(this.a258[n2].substring(4, this.a258[n2].length() - 3), rectangle, n3);
                }
                else {
                    this.a279(this.a258[n2].substring(4, this.a258[n2].length()), rectangle, n3);
                }
                this.a20.copyArea(rectangle.x, rectangle.y + this.height, rectangle.width, rectangle.height, 0, -this.height);
                return;
            }
            if (this.a258[n2].startsWith("blinds") && this.a259 % 2 == 0) {
                final int n55 = (int)(40.0 * n3);
                for (int n56 = this.width - 1; n56 >= 0; --n56) {
                    if (n56 % 40 < n55) {
                        final int n57 = n56 % 40 * 40 / n55 + n56 / 40 * 40;
                        this.a278(n57, this.height, 1, this.height, n56 - n57, -this.height + (int)(n3 * (1.0 - n3) * (n56 % 40 * 40 / n55 - 20)));
                    }
                    else {
                        final int n58 = (n56 % 40 - n55) * 40 / (40 - n55) + n56 / 40 * 40;
                        this.a278(n58, 0, 1, this.height, n56 - n58, (int)(n3 * (1.0 - n3) * (20 - (n56 % 40 - n55) * 40 / (40 - n55))));
                    }
                }
                return;
            }
            if (this.a258[n2].startsWith("blinds") && this.a259 % 2 == 1) {
                final int n59 = (int)(40.0 * n3);
                for (int n60 = this.height - 1; n60 >= 0; --n60) {
                    if (n60 % 40 < n59) {
                        final int n61 = n60 % 40 * 40 / n59 + n60 / 40 * 40;
                        this.a278(0, n61 + this.height, this.width, 1, (int)(n3 * (1.0 - n3) * (n60 % 40 * 40 / n59 - 20)), n60 - n61 - this.height);
                    }
                    else {
                        final int n62 = (n60 % 40 - n59) * 40 / (40 - n59) + n60 / 40 * 40;
                        this.a278(0, n62, this.width, 1, (int)(n3 * (1.0 - n3) * (20 - (n60 % 40 - n59) * 40 / (40 - n59))), n60 - n62);
                    }
                }
                return;
            }
            if (this.a258[n2].startsWith("stretch") && this.a259 % 2 == 0) {
                final int n63 = (int)(this.width * n3);
                for (int n64 = this.width - 1; n64 >= 0; --n64) {
                    if (n64 < n63) {
                        final int n65 = n64 * this.width / n63;
                        this.a278(n65, this.height, 1, this.height, n64 - n65, -this.height);
                    }
                    else {
                        final int n66 = (n64 - n63) * this.width / (this.width - n63);
                        this.a278(n66, 0, 1, this.height, n64 - n66, 0);
                    }
                }
                return;
            }
            if (this.a258[n2].startsWith("stretch") && this.a259 % 2 == 1) {
                final int n67 = (int)(this.height * n3);
                for (int n68 = this.height - 1; n68 >= 0; --n68) {
                    if (n68 < n67) {
                        final int n69 = n68 * this.height / n67;
                        this.a278(0, n69 + this.height, this.width, 1, 0, n68 - n69 - this.height);
                    }
                    else {
                        final int n70 = (n68 - n67) * this.width / (this.width - n67);
                        this.a278(0, n70, this.width, 1, 0, n68 - n70);
                    }
                }
            }
        }
    }
    
    protected void a278(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.a20.copyArea((this.a259 < 2) ? n : (this.width - n - n3), n2, n3, n4, (this.a259 < 2) ? n5 : (-n5), n6);
    }
    
    protected void a279(final String s, final Rectangle rectangle, final double n) {
        if (s.startsWith("right")) {
            rectangle.x = rectangle.x - rectangle.width + (int)((rectangle.x + rectangle.width - rectangle.x) * n);
            return;
        }
        if (s.startsWith("left")) {
            rectangle.x = (int)(rectangle.x + rectangle.width + (rectangle.x - rectangle.x - rectangle.width) * n);
            return;
        }
        if (s.startsWith("up")) {
            rectangle.y = (int)(rectangle.y + rectangle.height + (rectangle.y - rectangle.y - rectangle.height) * n);
            return;
        }
        if (s.startsWith("down")) {
            rectangle.y = rectangle.y - rectangle.height + (int)((rectangle.y + rectangle.height - rectangle.y) * n);
            return;
        }
        if (s.startsWith("growver")) {
            rectangle.grow(0, -(int)(rectangle.height * (1.0 - n) / 2.0));
            return;
        }
        if (s.startsWith("growhor")) {
            rectangle.grow(-(int)(rectangle.width * (1.0 - n) / 2.0), 0);
            return;
        }
        if (s.startsWith("grow2")) {
            rectangle.grow(-(int)(rectangle.height * (1.0 - n) / 2.0), -(int)(rectangle.height * (1.0 - n) / 2.0));
            return;
        }
        if (s.startsWith("grow")) {
            rectangle.grow(-(int)(rectangle.width * (1.0 - n) / 2.0), -(int)(rectangle.height * (1.0 - n) / 2.0));
        }
    }
    
    protected int min(final int n, final int n2) {
        if (n < n2) {
            return n;
        }
        return n2;
    }
    
    protected int max(final int n, final int n2) {
        if (n > n2) {
            return n;
        }
        return n2;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.a91 < this.a103) {
            return true;
        }
        if (this.menu[this.current][1] != "") {
            URL url = null;
            try {
                url = new URL(this.menu[this.current][1]);
            }
            catch (MalformedURLException ex) {
                try {
                    url = new URL(this.getDocumentBase(), this.menu[this.current][1]);
                }
                catch (MalformedURLException ex2) {}
            }
            if (url != null) {
                if (this.menu[this.current][2] != "") {
                    this.getAppletContext().showDocument(url, this.menu[this.current][2]);
                }
                else {
                    this.getAppletContext().showDocument(url);
                }
            }
        }
        return true;
    }
    
    protected String a98(final String s) {
        final char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; ++i) {
            charArray[i] = (char)((charArray[i] + i * 7) % 26 + 97);
        }
        return new String(charArray);
    }
    
    public void setCursor(final int cursor) {
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Frame); container = container.getParent()) {}
        ((Frame)container).setCursor(cursor);
    }
}
