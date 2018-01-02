import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Component;
import java.applet.AudioClip;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class tv_banner extends Applet implements Runnable
{
    Thread runner;
    MediaTracker track;
    Graphics offscreenGr;
    Image addTop;
    Image[] addBottom;
    Image offscreenImg;
    int a_w;
    int a_h;
    int addnum;
    int pause;
    int addverts;
    int gadd;
    int loop;
    int sound;
    int random;
    int splash;
    int sleep;
    int ads;
    int nos;
    int noise;
    int preload;
    int compatible;
    String[] urls;
    String[] urls_copy;
    String[] img;
    String[] img_copy;
    int[] rnd;
    String ur;
    String encrypted;
    String splash_loc;
    String splash_url;
    int[] pos;
    URL theurl;
    Image unt1;
    int imagewidth;
    int imageheight;
    int totalpixels;
    int cx;
    int cy;
    int[] pixels1;
    int rnd1;
    int rnd2;
    AudioClip stat;
    String serial_num;
    String owner;
    boolean access;
    String applet_des;
    String applet_des2;
    
    public int[] Unique(final int n) {
        final int[] Cell = new int[n + 1];
        for (int x = 0; x < n; ++x) {
            Cell[x] = (int)Math.ceil(Math.random() * n);
            for (int y = 0; y < x; ++y) {
                if (Cell[x] == Cell[y] && x != y) {
                    --x;
                    break;
                }
            }
        }
        return Cell;
    }
    
    public boolean Registered(String user, final String serial_id) {
        String serial = "";
        int byte_code = 0;
        final int[] magic_number = { 0, 9854, 1987, 7357, 2784, 9354, 2689, 9674, 2513, 5287, 8633, 8324, 5526, 7983, 8547, 6774, 5678, 9675, 3886, 5467, 6737, 4388, 3288, 3276, 8976, 4137, 3778 };
        user = user.toLowerCase();
        for (int x = 0; x < user.length(); ++x) {
            if (user.charAt(x) >= 'a' && user.charAt(x) <= '{') {
                serial += user.charAt(x);
            }
        }
        if (serial.equals("") || user.length() < 10) {
            serial = "mrenfkedsjfkj";
        }
        for (int x2 = 0; x2 < serial.length(); ++x2) {
            byte_code = serial.charAt(x2);
            this.encrypted += magic_number[byte_code - 96] >> 9;
        }
        return serial_id.equals(this.encrypted);
    }
    
    public String space() {
        String dmex = "";
        final int[] num = { 165, 204, 207, 207, 220, 131, 182, 204, 199, 204, 213, 210, 211, 210, 216, 207, 210, 214 };
        for (int x = 0; x <= 17; ++x) {
            dmex += (char)(num[x] - 99);
        }
        return dmex;
    }
    
    public void init() {
        this.serial_num = this.getParameter("serial_num");
        if (this.serial_num == null) {
            this.serial_num = "1234567890";
        }
        this.owner = this.getParameter("owner");
        if (this.owner == null) {
            this.owner = "0";
        }
        this.access = this.Registered(this.owner, this.serial_num);
        this.encrypted = "";
        final String ds = this.getParameter("description");
        if (ds == null) {
            this.applet_des = "TV BANNER APPLET. [ By " + this.space() + "Copyright@1997. Ver. 1.0.0. ]";
        }
        else {
            this.applet_des = ds;
            this.applet_des2 = ds;
        }
        final String dl = this.getParameter("pause");
        if (dl == null) {
            this.pause = 1000;
        }
        else {
            this.pause = Integer.parseInt(dl) * 1000;
        }
        String ss = this.getParameter("speed");
        if (ss == null) {
            this.sleep = 1;
        }
        else {
            this.sleep = Integer.parseInt(ss);
        }
        final String sd = this.getParameter("sound");
        if (sd == null) {
            this.sound = 1;
        }
        else {
            final String ssd = sd.toLowerCase();
            if (ssd.equals("on")) {
                this.sound = 1;
            }
            else {
                this.sound = 0;
            }
        }
        if (this.sound == 1) {
            this.applet_des += "  [ 'Control+Click' to turn sound off. ]";
        }
        final String rd = this.getParameter("random");
        if (rd == null) {
            this.random = 0;
        }
        else {
            final String rrd = rd.toLowerCase();
            if (rrd.equals("on")) {
                this.random = 1;
            }
            else {
                this.random = 0;
            }
        }
        final String lp = this.getParameter("noise");
        if (lp == null) {
            this.noise = 200;
        }
        else {
            this.noise = Integer.parseInt(lp);
        }
        final String lop = this.getParameter("loop");
        if (lop == null) {
            this.loop = 1;
        }
        else {
            final String slop = lop.toLowerCase();
            if (slop.equals("on")) {
                this.loop = 1;
            }
            else {
                this.loop = 0;
            }
        }
        final String cop = this.getParameter("compatibility");
        if (cop == null) {
            this.compatible = 1;
        }
        else {
            final String scop = cop.toLowerCase();
            if (scop.equals("all")) {
                this.compatible = 1;
            }
            else {
                this.compatible = 0;
            }
        }
        final String pre = this.getParameter("preload");
        if (pre == null) {
            this.preload = 0;
        }
        else {
            final String spre = pre.toLowerCase();
            if (spre.equals("on")) {
                this.preload = 1;
            }
            else {
                this.preload = 0;
            }
        }
        ss = this.getParameter("splash");
        if (ss == null) {
            this.splash = 0;
        }
        else {
            this.splash = 1;
            if (ss.indexOf(62) != -1) {
                this.splash_loc = ss.substring(0, ss.indexOf(62));
                this.splash_url = ss.substring(ss.indexOf(62) + 1, ss.length());
            }
            else {
                this.splash_loc = ss;
                this.splash_url = "";
            }
        }
        String p;
        do {
            ++this.ads;
            p = this.getParameter("Advert_" + this.ads);
        } while (p != null);
        --this.ads;
        this.urls = new String[this.ads + 2];
        this.img = new String[this.ads + 2];
        this.urls_copy = new String[this.ads + 2];
        this.img_copy = new String[this.ads + 2];
        this.rnd = new int[this.ads + 1];
        this.addBottom = new Image[this.ads + 2];
        this.addverts = 1;
        while (this.addverts <= this.ads) {
            final String pp = this.getParameter("Advert_" + this.addverts);
            if (pp.indexOf(62) == -1) {
                this.img[this.addverts] = pp;
            }
            else {
                this.img[this.addverts] = pp.substring(0, pp.indexOf(62));
            }
            if (pp.indexOf(62) == -1) {
                this.urls[this.addverts] = "";
            }
            else {
                this.urls[this.addverts] = pp.substring(pp.indexOf(62) + 1, pp.length());
            }
            ++this.addverts;
        }
        --this.addverts;
        if (this.random == 1) {
            this.rnd = this.Unique(this.ads);
            for (int x = 1; x <= this.addverts; ++x) {
                this.img_copy[x] = this.img[this.rnd[x - 1]];
                this.urls_copy[x] = this.urls[this.rnd[x - 1]];
            }
            for (int x2 = 1; x2 <= this.addverts; ++x2) {
                this.img[x2] = this.img_copy[x2];
                this.urls[x2] = this.urls_copy[x2];
            }
        }
        if (this.splash == 1) {
            this.nos = 0;
            this.img[0] = this.splash_loc;
            this.urls[0] = this.splash_url;
            this.addnum = -1;
            this.gadd = -1;
        }
        else {
            this.img[0] = "";
            this.urls[0] = "";
            this.addnum = 0;
            this.gadd = 0;
        }
        if (this.ads <= 0 && this.splash == 0) {
            this.splash = 1;
            this.nos = 1;
            this.pause = 0;
            this.img[0] = "";
            this.urls[0] = "";
            this.noise = 10000000;
            this.gadd = 0;
        }
        if (this.ads <= 0 && this.splash == 1 && this.preload == 1) {
            this.noise = 10000000;
        }
        this.track = new MediaTracker(this);
        this.a_h = this.size().height;
        this.a_w = this.size().width;
        if (this.compatible == 0) {
            this.offscreenImg = this.createImage(this.a_w, this.a_h);
        }
        else {
            this.offscreenImg = this.createImage(this.a_w, this.a_h * 4);
        }
        this.offscreenGr = this.offscreenImg.getGraphics();
    }
    
    public void initialize() {
        if (this.compatible == 1) {
            this.imagewidth = this.size().width;
            this.imageheight = this.size().height * 3;
            this.totalpixels = this.imagewidth * this.imageheight;
            this.pixels1 = new int[this.totalpixels];
            final int tp = this.totalpixels / 100 * 10;
            int c = 0;
            for (int x = 0; x < this.totalpixels; ++x) {
                this.rnd1 = (int)Math.floor(Math.random() * 4.0);
                this.pixels1[x] = ((this.rnd1 >= 2) ? Integer.MIN_VALUE : -1);
                if (x % tp == 0) {
                    c += 10;
                    if (c > 100) {
                        c = 100;
                    }
                    this.showStatus(Integer.toString(c, 10) + "%");
                }
            }
            this.unt1 = this.createImage(new MemoryImageSource(this.imagewidth, this.imageheight, this.pixels1, 0, this.imagewidth));
            this.showStatus("100%");
        }
        if (this.sound > 0) {
            this.showStatus("....");
            this.stat = this.getAudioClip(this.getCodeBase(), "sounds/static.au");
        }
        this.showStatus("Complete.");
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
            if (this.stat != null) {
                this.stat.stop();
            }
        }
    }
    
    public void delay(final int t) {
        long ttime = 0L;
        ttime = System.currentTimeMillis();
        try {
            ttime += t;
            Thread.sleep(Math.max(0L, ttime - System.currentTimeMillis()));
        }
        catch (InterruptedException ex) {}
    }
    
    public void run() {
        this.initialize();
        while (this.access) {
            ++this.addnum;
            if (this.addnum > this.addverts && this.splash == 1) {
                this.addnum = 0;
                if (this.loop == 0) {
                    this.loop = 2;
                    this.pause = 100;
                }
            }
            if (this.addnum > this.addverts && this.splash == 0) {
                this.addnum = 1;
                if (this.loop == 0) {
                    this.loop = 2;
                    this.pause = 100;
                }
            }
            if (this.loop != 2) {
                if (this.preload == 0) {
                    this.setup();
                }
                if (this.preload == 1) {
                    this.setup2();
                }
                this.transition();
            }
            this.delay(this.pause);
        }
    }
    
    public void setup() {
        try {
            this.addBottom[this.addnum] = this.getImage(this.getCodeBase(), this.img[this.addnum]);
            this.track.addImage(this.addBottom[this.addnum], 0);
            this.track.waitForID(0);
        }
        catch (InterruptedException ex) {}
    }
    
    public void setup2() {
        for (int x = this.addnum; x <= this.addverts; ++x) {
            this.addBottom[x] = this.getImage(this.getCodeBase(), this.img[x]);
            this.track.addImage(this.addBottom[x], 0);
        }
        try {
            this.showStatus("Loading...");
            this.track.waitForAll();
        }
        catch (InterruptedException ex) {
            return;
        }
        this.preload = 2;
    }
    
    public void transition() {
        int cont = 0;
        this.cx = (this.a_w - this.addBottom[this.addnum].getWidth(this)) / 2;
        this.cy = (this.a_h - this.addBottom[this.addnum].getHeight(this)) / 2;
        if (this.cx != 0 || this.cy != 0) {
            cont = 1;
        }
        else {
            cont = 0;
        }
        if (this.compatible == 1) {
            this.offscreenGr.drawImage(this.unt1, 0, this.a_h, this);
            this.repaint();
        }
        if (this.stat != null && this.addBottom[this.addnum] != null && this.sound > 0) {
            this.stat.loop();
        }
        long time = System.currentTimeMillis();
        time += this.noise;
        this.showStatus("");
        int cont_pseudo = 0;
        this.rnd2 = 0;
        do {
            if (this.compatible == 1) {
                this.rnd2 = (int)Math.floor(Math.random() * (this.a_h * 3 - this.a_h));
                this.offscreenGr.copyArea(0, this.a_h + this.rnd2, this.a_w, this.a_h, 0, -(this.a_h + this.rnd2));
            }
            if (this.compatible == 0) {
                this.rnd2 += 100;
                if (this.rnd2 > 1000) {
                    this.rnd2 = 0;
                }
                this.offscreenGr.copyArea(0, this.a_h + this.rnd2, this.a_w, this.a_h, 0, -(this.a_h + this.rnd2));
            }
            if (cont == 1 && this.preload == 2) {
                this.offscreenGr.drawImage(this.addBottom[this.addnum], this.cx, this.cy, this);
                if (cont_pseudo == 0) {
                    time += this.pause;
                    cont_pseudo = 1;
                    ++this.gadd;
                    if (this.gadd > this.addverts && this.splash == 1) {
                        this.gadd = 0;
                    }
                    if (this.gadd > this.addverts && this.splash == 0) {
                        this.gadd = 1;
                    }
                    this.showStatus(this.urls[this.gadd]);
                }
            }
            this.repaint();
            this.delay(this.sleep);
        } while (time > System.currentTimeMillis());
        if (cont_pseudo == 1) {
            ++this.addnum;
            if (this.addnum > this.addverts && this.splash == 1) {
                this.addnum = 0;
                if (this.loop == 0) {
                    this.loop = 2;
                    this.pause = 100;
                }
            }
            if (this.addnum > this.addverts && this.splash == 0) {
                this.addnum = 1;
                if (this.loop == 0) {
                    this.loop = 2;
                    this.pause = 100;
                }
            }
            cont = 0;
            this.cx = (this.a_w - this.addBottom[this.addnum].getWidth(this)) / 2;
            this.cy = (this.a_h - this.addBottom[this.addnum].getHeight(this)) / 2;
        }
        if (this.stat != null && this.sound > 0) {
            this.stat.stop();
        }
        if ((cont == 0 && this.ads > 0) || this.nos == 0) {
            this.offscreenGr.setColor(Color.black);
            this.offscreenGr.fillRect(0, 0, this.a_w, this.a_h);
            this.offscreenGr.drawImage(this.addBottom[this.addnum], this.cx, this.cy, this);
            this.repaint();
            this.delay(this.sleep);
            ++this.gadd;
            if (this.gadd > this.addverts && this.splash == 1) {
                this.gadd = 0;
            }
            if (this.gadd > this.addverts && this.splash == 0) {
                this.gadd = 1;
            }
            this.showStatus(this.urls[this.gadd]);
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.offscreenImg, 0, 0, this);
        if (!this.access) {
            g.drawString("Incorrect Serial Number.", 5, this.a_h / 2);
        }
    }
    
    public boolean handleEvent(final Event evt) {
        switch (evt.id) {
            case 504: {
                this.showStatus(this.applet_des);
                return true;
            }
            case 505: {
                this.showStatus("");
                return true;
            }
            case 501: {
                if (evt.shiftDown()) {
                    this.showStatus("Applet by " + this.space() + ". Copyright@1997.");
                    return true;
                }
                if (evt.controlDown()) {
                    if (this.stat != null && this.sound > 0) {
                        this.stat.stop();
                        this.sound = 0;
                        this.showStatus("Sound Off.");
                        this.applet_des = this.applet_des2;
                    }
                    return true;
                }
                if (this.access && !this.urls[this.gadd].equals("")) {
                    try {
                        this.theurl = new URL(this.urls[this.gadd]);
                    }
                    catch (MalformedURLException ex) {
                        System.out.println("Bad URL at:" + this.gadd);
                    }
                    this.getAppletContext().showDocument(this.theurl);
                }
                return true;
            }
            default: {
                return super.handleEvent(evt);
            }
        }
    }
    
    public tv_banner() {
        this.addnum = -1;
        this.encrypted = "";
        this.splash_loc = "";
        this.splash_url = "";
        this.pos = new int[100];
        this.serial_num = "";
        this.owner = "";
        this.access = false;
        this.applet_des = "";
        this.applet_des2 = "";
    }
}
