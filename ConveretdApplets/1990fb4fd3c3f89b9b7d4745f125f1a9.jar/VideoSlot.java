import java.awt.Event;
import java.net.URLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Component;
import java.util.Date;
import java.util.Random;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class VideoSlot extends Applet implements Runnable
{
    Thread runner;
    MediaTracker track;
    Graphics offscreenGr;
    Graphics off1;
    Graphics off2;
    Graphics off3;
    Graphics off4;
    Graphics off5;
    Image[] fruits;
    Image offscreenImg;
    Image offImg1;
    Image offImg2;
    Image offImg3;
    Image offImg4;
    Image offImg5;
    Image coin;
    Image backgroundImage;
    Font f;
    FontMetrics fm;
    int rnd;
    int v1;
    int v2;
    int v3;
    int v4;
    int c1;
    int c2;
    int c3;
    int c4;
    int cc1;
    int cc2;
    int cc3;
    int cc4;
    int score;
    int limit;
    int rr;
    int r1;
    int r2;
    int r3;
    int r4;
    static int spins;
    Color backgroundColor;
    AudioClip[] snd;
    boolean access;
    boolean win;
    public boolean sound;
    public boolean loaded;
    public boolean showCoins;
    public String target;
    boolean verify;
    boolean demo;
    boolean runonce;
    boolean autolink;
    boolean D3;
    boolean D3low;
    static int dscore;
    private long seed;
    private Random rand;
    int v;
    int vvv;
    int value;
    int h;
    int m;
    int d;
    public int speed;
    public boolean autospin;
    int start;
    int col;
    int[] offsetArray;
    int[] rnd1;
    int[] rnd2;
    int[] rnd3;
    int[] rnd4;
    int[] pixels;
    int[] pixels1;
    int[] pixels2;
    int[] pixels3;
    int[] pixels4;
    String VicPic;
    String LosePic;
    String serial_num;
    String obj;
    String code;
    String link;
    String ID;
    String backPic;
    String key;
    Date theDate;
    long time;
    long t1;
    long t2;
    long t3;
    long t4;
    long t5;
    long t6;
    long t7;
    long t8;
    Image victory;
    Image defeat;
    
    Color decodeColor(final String s) {
        try {
            int n;
            if (s.startsWith("0x")) {
                n = Integer.parseInt(s.substring(2), 16);
            }
            else if (s.startsWith("#")) {
                n = Integer.parseInt(s.substring(1), 16);
            }
            else if (s.startsWith("0") && s.length() > 1) {
                n = Integer.parseInt(s.substring(1), 8);
            }
            else {
                n = Integer.parseInt(s, 10);
            }
            return new Color(n);
        }
        catch (NumberFormatException ex) {
            return null;
        }
    }
    
    private boolean Registered(String lowerCase, final String s) {
        String string = "";
        String string2 = "";
        final int[] array = { 0, 9854, 1987, 7357, 2784, 9354, 2689, 9674, 2513, 5287, 8633, 8324, 5526, 7983, 8547, 6774, 5678, 9675, 3886, 5467, 6737, 4388, 3288, 3276, 8976, 4137, 3778 };
        lowerCase = lowerCase.toLowerCase();
        for (int i = 0; i < lowerCase.length(); ++i) {
            if (lowerCase.charAt(i) >= 'a' && lowerCase.charAt(i) <= '{') {
                string += lowerCase.charAt(i);
            }
        }
        if (string.equals("") || lowerCase.length() < 10) {
            string = "mrenfkedsjfkj";
        }
        for (int j = 0; j < string.length(); ++j) {
            string2 += array[string.charAt(j) - '`'] >> 9;
        }
        return s.equals(string2);
    }
    
    private String Decipher(final String s, final String s2) {
        String string = "";
        if (s2.equals("")) {
            return s;
        }
        final Random random = new Random(Long.parseLong(s2));
        for (int i = 0; i < s.length(); ++i) {
            try {
                final int n = 1 + (int)Math.floor(random.nextFloat() * 100.0f);
                final int int1 = Integer.parseInt(s.substring(i, i + 1));
                final int n2 = Integer.parseInt(s.substring(i + 1, i + int1 + 1), 10) - n;
                i += int1;
                string += (char)n2;
            }
            catch (Exception ex) {
                return s;
            }
        }
        return string;
    }
    
    String space() {
        String string = "";
        final int[] array = { 165, 204, 207, 207, 220, 131, 182, 204, 199, 204, 213, 210, 211, 210, 216, 207, 210, 214 };
        for (int i = 0; i <= 17; ++i) {
            string += (char)(array[i] - 99);
        }
        return string;
    }
    
    void initialize() {
        this.c1 = 250;
        this.c2 = 300;
        this.c3 = 350;
        this.c4 = 400;
        this.cc1 = 20;
        this.cc2 = 20;
        this.cc3 = 20;
        this.cc4 = 20;
        this.v1 = 0;
        this.v2 = 0;
        this.v3 = 0;
        this.v4 = 0;
        this.code = "";
        this.v = -1;
        this.vvv = -1;
    }
    
    public void init() {
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        this.theDate = new Date();
        this.h = this.theDate.getHours();
        this.m = this.theDate.getMinutes();
        this.d = this.theDate.getDate();
        this.seed = this.theDate.hashCode() + (1 + (int)Math.floor(Math.random() * 10.0));
        this.rand.setSeed(this.seed);
        this.serial_num = this.getParameter("serial_num");
        if (this.serial_num == null) {
            this.serial_num = "5181513618710101316";
        }
        String parameter = this.getParameter("owner");
        if (parameter == null) {
            parameter = "DEMO VERSION";
        }
        this.access = this.Registered(parameter, this.serial_num);
        final String parameter2 = this.getParameter("bkcolor");
        if (parameter2 != null) {
            this.backgroundColor = this.decodeColor(parameter2);
        }
        else {
            this.backgroundColor = new Color(255, 30, 30);
        }
        final String parameter3 = this.getParameter("speed");
        if (parameter3 == null) {
            this.speed = 5;
        }
        else {
            this.speed = Integer.parseInt(parameter3);
        }
        this.backPic = this.getParameter("bkimage");
        final String parameter4 = this.getParameter("limit");
        if (parameter4 == null) {
            this.limit = 10;
        }
        else {
            this.limit = Integer.parseInt(parameter4);
        }
        final String parameter5 = this.getParameter("credits");
        if (parameter5 == null) {
            this.score = 5;
        }
        else {
            this.score = Integer.parseInt(parameter5);
        }
        final String parameter6 = this.getParameter("match4");
        if (parameter6 == null) {
            this.col = 3;
        }
        else {
            final String lowerCase = parameter6.toLowerCase();
            if (lowerCase.equals("on") || lowerCase.equals("true")) {
                this.col = 4;
            }
            else {
                this.col = 3;
            }
        }
        final String parameter7 = this.getParameter("value");
        if (parameter7 == null) {
            this.value = 1;
        }
        else {
            this.value = Integer.parseInt(parameter7);
        }
        final String parameter8 = this.getParameter("sound");
        if (parameter8 == null) {
            this.sound = true;
        }
        else {
            final String lowerCase2 = parameter8.toLowerCase();
            if (lowerCase2.equals("on") || lowerCase2.equals("true")) {
                this.sound = true;
            }
            else {
                this.sound = false;
            }
        }
        final String parameter9 = this.getParameter("3D");
        if (parameter9 == null) {
            this.D3 = false;
            this.D3low = false;
        }
        else {
            final String lowerCase3 = parameter9.toLowerCase();
            if (lowerCase3.equals("high")) {
                this.D3 = true;
                this.D3low = false;
            }
            else if (lowerCase3.equals("low")) {
                this.D3 = true;
                this.D3low = true;
            }
            else {
                this.D3 = false;
                this.D3low = false;
            }
        }
        final String parameter10 = this.getParameter("coins");
        if (parameter10 == null) {
            this.showCoins = true;
        }
        else {
            final String lowerCase4 = parameter10.toLowerCase();
            if (lowerCase4.equals("on") || lowerCase4.equals("true")) {
                this.showCoins = true;
            }
            else {
                this.showCoins = false;
            }
        }
        final String parameter11 = this.getParameter("verify");
        if (parameter11 == null) {
            this.verify = false;
        }
        else if (parameter11.toLowerCase().equals("on")) {
            this.verify = true;
        }
        else {
            this.verify = false;
        }
        final String parameter12 = this.getParameter("autolink");
        if (parameter12 == null) {
            this.autolink = false;
        }
        else {
            parameter12.toLowerCase();
            if (parameter12.equals("on") || parameter12.equals("true")) {
                this.autolink = true;
            }
            else {
                this.autolink = false;
            }
        }
        this.link = this.getParameter("link");
        if (this.link == null) {
            this.link = "";
        }
        this.ID = this.getParameter("id");
        if (this.ID == null) {
            this.ID = "";
        }
        final String parameter13 = this.getParameter("target");
        if (parameter13 != null) {
            this.target = parameter13;
        }
        this.VicPic = this.getParameter("victory");
        if (this.VicPic == null) {
            this.VicPic = "Images/victory.gif";
        }
        this.LosePic = this.getParameter("defeat");
        if (this.LosePic == null) {
            this.LosePic = "Images/defeat.gif";
        }
        this.demo = this.serial_num.equals("5181513618710101316");
        this.track = new MediaTracker(this);
        this.fruits = new Image[8];
        this.offscreenImg = this.createImage(this.size().width, this.size().height * 2);
        (this.offscreenGr = this.offscreenImg.getGraphics()).setFont(this.f);
        this.offImg1 = this.createImage(40, 140);
        this.off1 = this.offImg1.getGraphics();
        this.offImg2 = this.createImage(40, 140);
        this.off2 = this.offImg2.getGraphics();
        this.offImg3 = this.createImage(40, 140);
        this.off3 = this.offImg3.getGraphics();
        if (this.col == 4) {
            this.offImg5 = this.createImage(40, 140);
            this.off5 = this.offImg5.getGraphics();
        }
        if (this.D3) {
            if (!this.D3low) {
                this.pixels = new int[3200];
                this.pixels1 = new int[3200];
                this.pixels2 = new int[3200];
                this.pixels3 = new int[3200];
                if (this.col == 4) {
                    this.pixels4 = new int[3200];
                }
            }
            else {
                final int[] array = new int[3081];
                for (int i = 0; i < 3081; ++i) {
                    array[i] = 0;
                }
                final int n4 = 128;
                for (int j = 0; j < 1560; ++j) {
                    array[j] = (n4 << 24 | n << 16 | n2 << 8 | n3);
                    if (j % 39 == 0) {
                        n += 6;
                        n2 += 6;
                        n3 += 6;
                    }
                }
                for (int k = 1521; k < 3081; ++k) {
                    array[k] = (n4 << 24 | n << 16 | n2 << 8 | n3);
                    if (k % 39 == 0) {
                        n -= 6;
                        n2 -= 6;
                        n3 -= 6;
                    }
                }
                this.offImg4 = this.createImage(new MemoryImageSource(39, 79, array, 0, 39));
            }
        }
        this.fm = this.getFontMetrics(this.f);
        if (this.sound) {
            this.snd[1] = this.getAudioClip(this.getCodeBase(), "Sounds/roll.au");
            this.snd[2] = this.getAudioClip(this.getCodeBase(), "Sounds/coin.au");
            this.snd[3] = this.getAudioClip(this.getCodeBase(), "Sounds/lose.au");
            this.snd[4] = this.getAudioClip(this.getCodeBase(), "Sounds/win.au");
        }
    }
    
    void load() {
        this.showStatus("Loading Images...");
        this.repaint();
        for (int i = 1; i <= 7; ++i) {
            this.fruits[i] = this.getImage(this.getCodeBase(), "Images/" + Integer.toString(i, 10) + ".gif");
            this.track.addImage(this.fruits[i], 0);
        }
        this.coin = this.getImage(this.getCodeBase(), "Images/coin.gif");
        try {
            if (this.VicPic.indexOf("://") == -1) {
                this.VicPic = this.getCodeBase() + this.Decipher(this.VicPic, this.key);
            }
            this.victory = this.getImage(new URL(this.VicPic));
            if (this.LosePic.indexOf("://") == -1) {
                this.LosePic = this.getCodeBase() + this.Decipher(this.LosePic, this.key);
            }
            this.defeat = this.getImage(new URL(this.LosePic));
            if (this.backPic != null) {
                if (this.backPic.indexOf("://") == -1) {
                    this.backPic = this.getCodeBase() + this.Decipher(this.backPic, this.key);
                }
                this.backgroundImage = this.getImage(new URL(this.backPic));
                this.track.addImage(this.backgroundImage, 0);
            }
        }
        catch (MalformedURLException ex) {
            this.showStatus("Bad URL");
            this.access = false;
        }
        this.track.addImage(this.coin, 0);
        try {
            this.track.waitForAll();
        }
        catch (InterruptedException ex2) {}
        this.loaded = true;
        this.track = null;
        this.showStatus("");
    }
    
    int[] make3D(final Image image) {
        final int[] array = new int[3200];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 1, 61, 40, 80, array, 0, 40);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        int n = 0;
        for (int i = 0; i < 1600; ++i) {
            if (i % 40 == 0) {
                n += 3;
            }
            array[i] = (array[i] & 0xFF000000) + (Math.min(255, ((array[i] & 0xFF0000) >> 16) * n / 100) << 16) + (Math.min(255, ((array[i] & 0xFF00) >> 8) * n / 100) << 8) + Math.min(255, (array[i] & 0xFF) * n / 100);
        }
        for (int j = 1600; j < 3200; ++j) {
            if (j % 40 == 0) {
                n -= 3;
            }
            array[j] = (array[j] & 0xFF000000) + (Math.min(255, ((array[j] & 0xFF0000) >> 16) * n / 100) << 16) + (Math.min(255, ((array[j] & 0xFF00) >> 8) * n / 100) << 8) + Math.min(255, (array[j] & 0xFF) * n / 100);
        }
        return array;
    }
    
    void setup(final int n) {
        if (this.backgroundColor != null) {
            this.offscreenGr.setColor(Color.black);
        }
        this.offscreenGr.fillRect(0, 0, this.size().width, 160);
        for (int i = 0; i < n; ++i) {
            this.rnd = 1 + (int)Math.floor(this.rand.nextFloat() * 7.0f);
            this.off1.drawImage(this.fruits[this.rnd], 0, i * 40, this);
            this.rnd = 1 + (int)Math.floor(this.rand.nextFloat() * 7.0f);
            this.off2.drawImage(this.fruits[this.rnd], 0, i * 40, this);
            this.rnd = 1 + (int)Math.floor(this.rand.nextFloat() * 7.0f);
            this.off3.drawImage(this.fruits[this.rnd], 0, i * 40, this);
            if (this.col == 4) {
                this.rnd = 1 + (int)Math.floor(this.rand.nextFloat() * 7.0f);
                this.off5.drawImage(this.fruits[this.rnd], 0, i * 40, this);
            }
        }
        if (this.D3) {
            if (this.D3low) {
                this.offscreenGr.drawImage(this.offImg1, 1, 0, this);
                this.offscreenGr.drawImage(this.offImg2, 42, 0, this);
                this.offscreenGr.drawImage(this.offImg3, 83, 0, this);
                if (this.col == 4) {
                    this.offscreenGr.drawImage(this.offImg5, 124, 0, this);
                }
                this.offscreenGr.drawImage(this.offImg4, 1, 61, this);
                this.offscreenGr.drawImage(this.offImg4, 42, 61, this);
                this.offscreenGr.drawImage(this.offImg4, 83, 61, this);
                if (this.col == 4) {
                    this.offscreenGr.drawImage(this.offImg4, 124, 61, this);
                }
            }
            else {
                this.pixels1 = this.make3D(this.offImg1);
                this.pixels2 = this.make3D(this.offImg2);
                this.pixels3 = this.make3D(this.offImg3);
                if (this.col == 4) {
                    this.pixels4 = this.make3D(this.offImg5);
                }
                this.offscreenGr.drawImage(this.createImage(new MemoryImageSource(40, 80, this.pixels1, 0, 40)), 2, 61, this);
                this.offscreenGr.drawImage(this.createImage(new MemoryImageSource(40, 80, this.pixels2, 0, 40)), 43, 61, this);
                this.offscreenGr.drawImage(this.createImage(new MemoryImageSource(40, 80, this.pixels3, 0, 40)), 84, 61, this);
                if (this.col == 4) {
                    this.offscreenGr.drawImage(this.createImage(new MemoryImageSource(40, 80, this.pixels4, 0, 40)), 125, 61, this);
                }
            }
        }
        else {
            this.offscreenGr.drawImage(this.offImg1, 1, 0, this);
            this.offscreenGr.drawImage(this.offImg2, 42, 0, this);
            this.offscreenGr.drawImage(this.offImg3, 83, 0, this);
            if (this.col == 4) {
                this.offscreenGr.drawImage(this.offImg5, 124, 0, this);
            }
        }
        if (this.backgroundImage != null) {
            this.offscreenGr.drawImage(this.backgroundImage, (this.col == 3) ? 124 : 164, 60, this);
        }
        this.coins(this.score);
        this.frames();
    }
    
    void coins(final int n) {
        if (this.score < 0) {
            this.defeat();
            return;
        }
        if (this.win) {
            if (this.sound) {
                this.snd[2].play();
            }
            this.win = false;
        }
        if (this.backgroundColor != null) {
            this.offscreenGr.setColor(this.backgroundColor);
        }
        this.offscreenGr.fillRect((this.col == 3) ? 124 : 164, 0, this.size().width, 160);
        if (this.backgroundImage != null) {
            this.offscreenGr.drawImage(this.backgroundImage, (this.col == 3) ? 124 : 164, 60, this);
        }
        if (this.showCoins) {
            int n2 = 0;
            int n3;
            if (this.col == 3) {
                if (this.size().width == 160) {
                    n3 = 0;
                }
                else {
                    n3 = -1;
                }
            }
            else if (this.size().width == 200) {
                n3 = 0;
            }
            else {
                n3 = -1;
            }
            for (int i = 0; i < n; ++i) {
                if (this.col == 3) {
                    if (i % 9 == 0 && this.size().width != 160) {
                        n2 = 0;
                        ++n3;
                    }
                }
                else if (i % 9 == 0 && this.size().width != 200) {
                    n2 = 0;
                    ++n3;
                }
                this.offscreenGr.drawImage(this.coin, (this.col == 3) ? (127 + 33 * n3) : (168 + 33 * n3), 122 - n2 * 7, this);
                ++n2;
            }
        }
        this.score();
    }
    
    void score() {
        if (!this.demo) {
            VideoSlot.dscore = this.score;
        }
        this.offscreenGr.setColor(Color.yellow);
        this.offscreenGr.fillRect(0, 140, (this.col == 3) ? 124 : 164, 10);
        this.offscreenGr.setColor(Color.black);
        this.offscreenGr.drawRect(0, 139, (this.col == 3) ? 123 : 164, 10);
        int n;
        if (this.col == 3) {
            n = (124 - this.fm.stringWidth(Integer.toString(this.score * this.value, 10) + "/" + Integer.toString(this.limit * this.value, 10))) / 2;
        }
        else {
            n = (164 - this.fm.stringWidth(Integer.toString(this.score * this.value, 10) + "/" + Integer.toString(this.limit * this.value, 10))) / 2;
        }
        this.offscreenGr.drawString(Integer.toString(this.score * this.value, 10) + "/" + Integer.toString(this.limit * this.value, 10), n, 148);
        this.frames();
    }
    
    void frames() {
        if (this.score < 0) {
            return;
        }
        this.offscreenGr.setColor(Color.black);
        this.offscreenGr.drawRect(0, 60, 41, 79);
        this.offscreenGr.drawRect(41, 60, 41, 79);
        this.offscreenGr.drawRect(82, 60, 41, 79);
        if (this.col == 4) {
            this.offscreenGr.drawRect(123, 60, 41, 79);
        }
        this.repaint();
        this.delay(this.speed);
    }
    
    void rotate(final int n, final int n2, final int n3, final int n4) {
        this.off1.copyArea(0, 0, 40, 160, 0, n);
        this.off2.copyArea(0, 0, 40, 160, 0, n2);
        this.off3.copyArea(0, 0, 40, 160, 0, n3);
        if (this.col == 4) {
            this.off5.copyArea(0, 0, 40, 160, 0, n4);
        }
        this.v1 += n;
        this.v2 += n2;
        this.v3 += n3;
        if (this.col == 4) {
            this.v4 += n4;
        }
        if (this.v1 > 39) {
            ++this.v;
            if (this.v > 1) {
                this.v = 0;
            }
            this.v1 = 0;
            this.rnd1[this.v] = 1 + (int)Math.floor(this.rand.nextFloat() * 7.0f);
            this.off1.drawImage(this.fruits[this.rnd1[this.v]], 0, 0, this);
        }
        if (this.v2 > 39) {
            ++this.v;
            if (this.v > 1) {
                this.v = 0;
            }
            this.v2 = 0;
            this.rnd2[this.v] = 1 + (int)Math.floor(this.rand.nextFloat() * 7.0f);
            this.off2.drawImage(this.fruits[this.rnd2[this.v]], 0, 0, this);
        }
        if (this.v3 > 39) {
            ++this.v;
            if (this.v > 1) {
                this.v = 0;
            }
            this.v3 = 0;
            this.rnd3[this.v] = 1 + (int)Math.floor(this.rand.nextFloat() * 7.0f);
            this.off3.drawImage(this.fruits[this.rnd3[this.v]], 0, 0, this);
        }
        if (this.col == 4 && this.v4 > 39) {
            ++this.vvv;
            if (this.vvv > 1) {
                this.vvv = 0;
            }
            this.v4 = 0;
            this.rnd4[this.vvv] = 1 + (int)Math.floor(this.rand.nextFloat() * 7.0f);
            this.off5.drawImage(this.fruits[this.rnd4[this.vvv]], 0, 0, this);
        }
        if (this.D3) {
            if (this.D3low) {
                this.offscreenGr.drawImage(this.offImg1, 1, 0, this);
                this.offscreenGr.drawImage(this.offImg2, 42, 0, this);
                this.offscreenGr.drawImage(this.offImg3, 83, 0, this);
                if (this.col == 4) {
                    this.offscreenGr.drawImage(this.offImg5, 124, 0, this);
                }
                this.offscreenGr.drawImage(this.offImg4, 1, 61, this);
                this.offscreenGr.drawImage(this.offImg4, 42, 61, this);
                this.offscreenGr.drawImage(this.offImg4, 83, 61, this);
                if (this.col == 4) {
                    this.offscreenGr.drawImage(this.offImg4, 124, 61, this);
                }
            }
            else {
                this.pixels1 = this.make3D(this.offImg1);
                this.pixels2 = this.make3D(this.offImg2);
                this.pixels3 = this.make3D(this.offImg3);
                if (this.col == 4) {
                    this.pixels4 = this.make3D(this.offImg5);
                }
                this.offscreenGr.drawImage(this.createImage(new MemoryImageSource(40, 80, this.pixels1, 0, 40)), 2, 61, this);
                this.offscreenGr.drawImage(this.createImage(new MemoryImageSource(40, 80, this.pixels2, 0, 40)), 43, 61, this);
                this.offscreenGr.drawImage(this.createImage(new MemoryImageSource(40, 80, this.pixels3, 0, 40)), 84, 61, this);
                if (this.col == 4) {
                    this.offscreenGr.drawImage(this.createImage(new MemoryImageSource(40, 80, this.pixels4, 0, 40)), 125, 61, this);
                }
            }
        }
        else {
            this.offscreenGr.drawImage(this.offImg1, 1, 0, this);
            this.offscreenGr.drawImage(this.offImg2, 42, 0, this);
            this.offscreenGr.drawImage(this.offImg3, 83, 0, this);
            if (this.col == 4) {
                this.offscreenGr.drawImage(this.offImg5, 124, 0, this);
            }
        }
        this.frames();
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
        this.runner.resume();
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.suspend();
            if (this.sound) {
                this.snd[1].stop();
                this.snd[2].stop();
                this.snd[3].stop();
                this.snd[4].stop();
            }
        }
    }
    
    public void destroy() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void delay(final int n) {
        final long currentTimeMillis = System.currentTimeMillis();
        try {
            Thread.sleep(Math.max(0L, currentTimeMillis + n - System.currentTimeMillis()));
        }
        catch (InterruptedException ex) {}
    }
    
    void check() {
        int n = 0;
        if (this.col == 3) {
            if (this.rnd1[1] != 6 && this.rnd2[1] == 6 && this.rnd3[1] != 6) {
                ++this.score;
                n = 1;
            }
            if (this.rnd1[1] != 6 && this.rnd2[1] == 6 && this.rnd3[1] == 6) {
                ++this.score;
                n = 1;
            }
            if (this.rnd1[1] == 6 && this.rnd2[1] == 6 && this.rnd3[1] != 6) {
                ++this.score;
                n = 1;
            }
            if (this.rnd1[1] == 1 && this.rnd2[1] == 1 && this.rnd3[1] == 6) {
                this.score += 5;
                n = 2;
            }
            if (this.rnd1[1] == 2 && this.rnd2[1] == 2 && this.rnd3[1] == 6) {
                this.score += 6;
                n = 2;
            }
            if (this.rnd1[1] == 3 && this.rnd2[1] == 3 && this.rnd3[1] == 6) {
                this.score += 7;
                n = 2;
            }
            if (this.rnd1[1] == 4 && this.rnd2[1] == 4 && this.rnd3[1] == 6) {
                this.score += 8;
                n = 2;
            }
            if (this.rnd1[1] == 5 && this.rnd2[1] == 5 && this.rnd3[1] == 6) {
                this.score += 9;
                n = 2;
            }
            if (this.rnd1[1] == 7 && this.rnd2[1] == 7 && this.rnd3[1] == 6) {
                this.score += 50;
                n = 2;
            }
            if (this.rnd1[1] == 6 && this.rnd2[1] == 1 && this.rnd3[1] == 1) {
                this.score += 2;
                n = 2;
            }
            if (this.rnd1[1] == 6 && this.rnd2[1] == 2 && this.rnd3[1] == 2) {
                this.score += 3;
                n = 2;
            }
            if (this.rnd1[1] == 6 && this.rnd2[1] == 3 && this.rnd3[1] == 3) {
                this.score += 4;
                n = 2;
            }
            if (this.rnd1[1] == 6 && this.rnd2[1] == 4 && this.rnd3[1] == 4) {
                this.score += 5;
                n = 2;
            }
            if (this.rnd1[1] == 6 && this.rnd2[1] == 5 && this.rnd3[1] == 5) {
                this.score += 6;
                n = 2;
            }
            if (this.rnd1[1] == 6 && this.rnd2[1] == 7 && this.rnd3[1] == 7) {
                this.score += 10;
                n = 2;
            }
            if (this.rnd1[1] == 1 && this.rnd2[1] == 1 && this.rnd3[1] == 1) {
                this.score += 50;
                n = 3;
            }
            if (this.rnd1[1] == 2 && this.rnd2[1] == 2 && this.rnd3[1] == 2) {
                this.score += 10;
                n = 3;
            }
            if (this.rnd1[1] == 3 && this.rnd2[1] == 3 && this.rnd3[1] == 3) {
                this.score += 20;
                n = 3;
            }
            if (this.rnd1[1] == 4 && this.rnd2[1] == 4 && this.rnd3[1] == 4) {
                this.score += 30;
                n = 3;
            }
            if (this.rnd1[1] == 5 && this.rnd2[1] == 5 && this.rnd3[1] == 5) {
                this.score += 40;
                n = 3;
            }
            if (this.rnd1[1] == 6 && this.rnd2[1] == 6 && this.rnd3[1] == 6) {
                this.score += 50;
                n = 3;
            }
            if (this.rnd1[1] == 7 && this.rnd2[1] == 7 && this.rnd3[1] == 7) {
                this.score += 100;
                n = 3;
            }
            if (this.rnd1[1] != 3 && this.rnd2[1] != 3 && this.rnd3[1] == 3) {
                ++this.score;
                n = 1;
            }
            if (this.rnd1[1] != 3 && this.rnd2[1] == 3 && this.rnd3[1] == 3) {
                this.score += 2;
                n = 2;
            }
        }
        else {
            if (this.rnd1[1] == 1 && this.rnd2[1] == 1 && this.rnd3[1] == 1 && this.rnd4[1] == 1) {
                this.score += 100;
                n = 3;
            }
            if (this.rnd1[1] == 2 && this.rnd2[1] == 2 && this.rnd3[1] == 2 && this.rnd4[1] == 2) {
                this.score += 100;
                n = 3;
            }
            if (this.rnd1[1] == 3 && this.rnd2[1] == 3 && this.rnd3[1] == 3 && this.rnd4[1] == 3) {
                this.score += 100;
                n = 3;
            }
            if (this.rnd1[1] == 4 && this.rnd2[1] == 4 && this.rnd3[1] == 4 && this.rnd4[1] == 4) {
                this.score += 100;
                n = 3;
            }
            if (this.rnd1[1] == 5 && this.rnd2[1] == 5 && this.rnd3[1] == 5 && this.rnd4[1] == 5) {
                this.score += 100;
                n = 3;
            }
            if (this.rnd1[1] == 6 && this.rnd2[1] == 6 && this.rnd3[1] == 6 && this.rnd4[1] == 6) {
                this.score += 100;
                n = 3;
            }
            if (this.rnd1[1] == 7 && this.rnd2[1] == 7 && this.rnd3[1] == 7 && this.rnd4[1] == 7) {
                this.score += 100;
                n = 3;
            }
        }
        for (int i = 0; i < n; ++i) {
            this.win = true;
            if (this.sound) {
                this.snd[4].play();
                this.delay(250);
            }
        }
        if (n == 0 && this.sound) {
            this.snd[3].play();
        }
        if (this.score >= this.limit) {
            this.start = 0;
            this.r1 = this.rnd1[1];
            this.r2 = this.rnd2[1];
            this.r3 = this.rnd3[1];
            this.r4 = this.rnd4[1];
            this.rr = 100 * this.r1 + 10 * this.r2 + this.r3;
            this.rr += 300;
        }
        this.coins(this.score);
        if (this.score == 0) {
            --this.score;
            this.start = 0;
        }
    }
    
    void victory() {
        this.offscreenGr.setColor(Color.black);
        this.offscreenGr.fillRect(0, 60, this.size().width, 90);
        this.code = Integer.toString(this.m + 30, 10) + Integer.toString(this.h + 30, 10) + Integer.toString(this.rr, 16) + Integer.toString(this.d + 99, 16);
        this.code = this.code.toUpperCase();
        if (this.autolink) {
            this.repaint();
            this.delay(this.speed);
            if (!this.demo) {
                VideoSlot.dscore = this.score;
            }
            this.goToLink();
            this.access = false;
            return;
        }
        this.offscreenGr.drawImage(this.victory, (this.size().width - this.victory.getWidth(this)) / 2, (this.size().height - this.victory.getHeight(this)) / 2 + 60, this);
        if (this.verify) {
            this.offscreenGr.setColor(Color.green);
            this.offscreenGr.fillRect(0, 140, this.size().width, 10);
            this.offscreenGr.setColor(Color.black);
            this.offscreenGr.drawString(this.code, (this.size().width - this.fm.stringWidth(this.code)) / 2, 148);
        }
        this.repaint();
        this.delay(this.speed);
        if (!this.demo) {
            VideoSlot.dscore = 0;
        }
    }
    
    void defeat() {
        this.offscreenGr.setColor(Color.black);
        this.offscreenGr.fillRect(0, 60, this.size().width, 90);
        this.offscreenGr.drawImage(this.defeat, (this.size().width - this.defeat.getWidth(this)) / 2, (this.size().height - this.defeat.getHeight(this)) / 2 + 60, this);
        this.repaint();
        this.delay(this.speed);
        if (!this.demo) {
            VideoSlot.dscore = this.score;
        }
    }
    
    void analyze(final String s) {
        final int index = s.indexOf(91);
        final int index2 = s.indexOf(93);
        if (index == -1 && index2 == -1 && s.length() > 0) {
            if (this.obj.equals("[victory]")) {
                this.VicPic = s;
            }
            if (this.obj.equals("[defeat]")) {
                this.LosePic = s;
            }
            if (this.obj.equals("[credits]")) {
                this.score = Integer.parseInt(s);
            }
            if (this.obj.equals("[limit]")) {
                this.limit = Integer.parseInt(s);
            }
            if (this.obj.equals("[runonce]")) {
                this.runonce = s.toLowerCase().equals("true");
            }
            if (this.obj.equals("[autolink]")) {
                this.autolink = s.toLowerCase().equals("true");
            }
            if (this.obj.equals("[link]")) {
                this.link = s;
            }
            if (this.obj.equals("[value]")) {
                this.value = Integer.parseInt(s);
            }
            if (this.obj.equals("[key]")) {
                this.key = s;
            }
            if (this.obj.equals("[magic]")) {
                this.seed = Long.parseLong(s);
                this.rand.setSeed(this.seed);
            }
            this.obj = "";
        }
        if (index > -1 && index2 > -1) {
            if (s.equals("[victory]")) {
                this.obj = "[victory]";
                return;
            }
            if (s.equals("[defeat]")) {
                this.obj = "[defeat]";
                return;
            }
            if (s.equals("[credits]")) {
                this.obj = "[credits]";
                return;
            }
            if (s.equals("[limit]")) {
                this.obj = "[limit]";
                return;
            }
            if (s.equals("[runonce]")) {
                this.obj = "[runonce]";
                return;
            }
            if (s.equals("[link]")) {
                this.obj = "[link]";
                return;
            }
            if (s.equals("[autolink]")) {
                this.obj = "[autolink]";
                return;
            }
            if (s.equals("[value]")) {
                this.obj = "[value]";
                return;
            }
            if (s.equals("[key]")) {
                this.obj = "[key]";
                return;
            }
            if (s.equals("[magic]")) {
                this.obj = "[magic]";
                return;
            }
            this.obj = "";
        }
    }
    
    boolean online() {
        new StringBuffer();
        if (!this.demo) {
            try {
                final URLConnection openConnection = new URL(this.getCodeBase() + this.serial_num.substring(0, 8) + this.ID).openConnection();
                openConnection.connect();
                String line;
                while ((line = new DataInputStream(new BufferedInputStream(openConnection.getInputStream())).readLine()) != null) {
                    this.analyze(line);
                }
                if (this.runonce && VideoSlot.dscore != -99) {
                    this.score = VideoSlot.dscore;
                }
                return true;
            }
            catch (IOException ex) {
                return this.access = false;
            }
        }
        return true;
    }
    
    public void run() {
        if (this.access && this.online()) {
            this.initialize();
            this.load();
            this.setup(4);
        }
        while (this.access) {
            if (this.autospin) {
                this.roll();
            }
            switch (this.start) {
                case 0: {
                    if (this.score >= this.limit) {
                        this.delay(1000);
                        this.victory();
                    }
                    if (this.score < 0) {
                        this.delay(1000);
                        this.defeat();
                        break;
                    }
                    break;
                }
                case 1: {
                    if (this.c1 > 0) {
                        --this.c1;
                    }
                    if (this.c2 > 0) {
                        --this.c2;
                    }
                    if (this.c3 > 0) {
                        --this.c3;
                    }
                    if (this.col == 4) {
                        if (this.c4 > 0) {
                            --this.c4;
                        }
                        if (this.cc1 == 0 && this.cc2 == 0 && this.cc3 == 0 && this.cc4 == 0) {
                            this.start = 0;
                            this.check();
                            this.v1 = 40;
                            this.v2 = 40;
                            this.v3 = 40;
                            this.v4 = 40;
                        }
                    }
                    else if (this.cc1 == 0 && this.cc2 == 0 && this.cc3 == 0) {
                        this.start = 0;
                        this.check();
                        this.v1 = 40;
                        this.v2 = 40;
                        this.v3 = 40;
                    }
                    switch (this.c1) {
                        case 239: {
                            this.cc1 = 20;
                            break;
                        }
                        case 199: {
                            this.cc1 = 10;
                            break;
                        }
                        case 159: {
                            this.cc1 = 8;
                            break;
                        }
                        case 119: {
                            this.cc1 = 5;
                            break;
                        }
                        case 79: {
                            this.cc1 = 2;
                            break;
                        }
                        case 39: {
                            this.cc1 = 1;
                            break;
                        }
                        case 0: {
                            this.cc1 = 0;
                            break;
                        }
                    }
                    switch (this.c2) {
                        case 239: {
                            this.cc2 = 20;
                            break;
                        }
                        case 199: {
                            this.cc2 = 10;
                            break;
                        }
                        case 159: {
                            this.cc2 = 8;
                            break;
                        }
                        case 119: {
                            this.cc2 = 5;
                            break;
                        }
                        case 79: {
                            this.cc2 = 2;
                            break;
                        }
                        case 39: {
                            this.cc2 = 1;
                            break;
                        }
                        case 0: {
                            this.cc2 = 0;
                            break;
                        }
                    }
                    switch (this.c3) {
                        case 239: {
                            this.cc3 = 20;
                            break;
                        }
                        case 199: {
                            this.cc3 = 10;
                            break;
                        }
                        case 159: {
                            this.cc3 = 8;
                            break;
                        }
                        case 119: {
                            this.cc3 = 5;
                            break;
                        }
                        case 79: {
                            this.cc3 = 2;
                            break;
                        }
                        case 39: {
                            this.cc3 = 1;
                            break;
                        }
                        case 0: {
                            this.cc3 = 0;
                            break;
                        }
                    }
                    switch (this.c4) {
                        case 239: {
                            this.cc4 = 20;
                            break;
                        }
                        case 199: {
                            this.cc4 = 10;
                            break;
                        }
                        case 159: {
                            this.cc4 = 8;
                            break;
                        }
                        case 119: {
                            this.cc4 = 5;
                            break;
                        }
                        case 79: {
                            this.cc4 = 2;
                            break;
                        }
                        case 39: {
                            this.cc4 = 1;
                            break;
                        }
                        case 0: {
                            this.cc4 = 0;
                            break;
                        }
                    }
                    this.rotate(this.cc1, this.cc2, this.cc3, this.cc4);
                    break;
                }
            }
            this.delay(this.speed);
        }
    }
    
    public void roll() {
        if (this.start == 0 && this.score > 0 && this.score < this.limit && this.access) {
            this.showStatus("");
            this.initialize();
            this.coins(--this.score);
            if (this.sound) {
                this.snd[1].play();
            }
            this.start = 1;
            ++VideoSlot.spins;
        }
    }
    
    public int getScore() {
        return this.score;
    }
    
    public int getHash() {
        return this.theDate.hashCode();
    }
    
    public int getLimit() {
        return this.limit;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public int getSpinCount() {
        return VideoSlot.spins;
    }
    
    public String getLink() {
        return this.link;
    }
    
    public void setLink(final String link) {
        this.link = link;
    }
    
    public String getExtendedCode() {
        return this.getCode() + "-" + this.getScore() + "-" + this.getLimit() + "-" + this.getSpinCount();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.loaded) {
            final int n = (this.size().width - this.fm.stringWidth("VIDEO SLOT MACHINE v1.2")) / 2;
            final int n2 = (this.size().height - this.fm.getAscent()) / 2;
            this.offscreenGr.setColor(Color.black);
            this.offscreenGr.drawString("VIDEO SLOT MACHINE v1.2", n, 60 + n2 + this.fm.getAscent() / 2);
        }
        graphics.drawImage(this.offscreenImg, 0, -60, this);
    }
    
    public void goToLink() {
        URL url = null;
        final String decipher = this.Decipher(this.link, this.key);
        final int index = decipher.indexOf("{");
        final int index2 = decipher.indexOf("}");
        String string;
        if (index > -1 && index2 > -1) {
            string = decipher.substring(0, index) + this.getExtendedCode() + decipher.substring(index2 + 1, decipher.length());
        }
        else {
            string = decipher;
        }
        try {
            url = new URL(string);
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad URL");
        }
        this.getAppletContext().showDocument(url, this.target);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                if (event.shiftDown()) {
                    this.showStatus("Applet by " + this.space() + ". Copyright@1999. #" + Long.toString(this.seed));
                    return true;
                }
                if (event.controlDown()) {
                    if (!this.autospin) {
                        this.autospin = true;
                    }
                    else {
                        this.autospin = false;
                    }
                    return true;
                }
                if (this.score >= this.limit && this.link.length() != 0 && this.access) {
                    this.goToLink();
                    return true;
                }
                this.roll();
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public VideoSlot() {
        this.f = new Font("Courier", 1, 9);
        this.snd = new AudioClip[5];
        this.access = false;
        this.win = false;
        this.sound = true;
        this.loaded = false;
        this.showCoins = true;
        this.target = "_top";
        this.verify = false;
        this.demo = true;
        this.runonce = false;
        this.autolink = false;
        this.D3 = false;
        this.D3low = false;
        this.rand = new Random();
        this.v = -1;
        this.vvv = -1;
        this.speed = 1;
        this.autospin = false;
        this.col = 3;
        this.rnd1 = new int[2];
        this.rnd2 = new int[2];
        this.rnd3 = new int[2];
        this.rnd4 = new int[2];
        this.key = "";
    }
    
    static {
        VideoSlot.dscore = -99;
    }
}
