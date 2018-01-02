import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.util.StringTokenizer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.Image;
import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class LaserBeamer extends Applet implements Runnable
{
    String bg_color;
    String banner;
    int banner_x_pos;
    int banner_y_pos;
    int banner_top_os;
    int banner_bottom_os;
    int banner_left_os;
    int banner_right_os;
    int laser_origin_x_pos;
    int laser_origin_y_pos;
    int laser_origin_region_x;
    int laser_origin_region_y;
    int laser_origin_region_width;
    int laser_origin_region_height;
    int laser_x_step;
    int laser_y_step;
    int link_enable;
    String laser_type;
    String laser_beam_color;
    String laser_spot_color;
    String laser_origin_region;
    String laser_origin_type;
    float laser_beam_spot_aspekt;
    float laser_beam_hue_increment;
    int thread_sleep;
    int sequence_sleep;
    Thread timer;
    boolean laserSpotColorAuto;
    MediaTracker tracker;
    PixelGrabber pg;
    int[] pixels;
    boolean AllImagesLoaded;
    Color bgCol;
    Color laserColSpot;
    Color laserColBeam;
    int AppletWidth;
    int AppletHeight;
    int BannerHeight;
    int BannerWidth;
    int LaserXPos;
    int LaserYPos;
    int LaserXWay;
    int sleep;
    int idle_loops;
    Image Banner;
    TextField field;
    private Image m_image;
    private Graphics g;
    Dimension m_dimImage;
    float[] hsbvals;
    float hueBeam;
    float satBeam;
    float BeamSpotFactor;
    static final int BANNER_ID = 2;
    private String URLName;
    private String frame;
    private String AppletHost;
    String HappyUserHost;
    
    public String getAppletInfo() {
        return "LaserBeamer v1.0 1998-2000, by Peter Hellmann\n\nLaserBeamer.java     February 1998 proramming by Peter Hellmann\noriginal idea: Peter Hellmann <peter@brightman.to>\nenhanced idea: Michael Hoffmann <hoffmann@ad-on.de>\n\nCopyright (c) 1998-2000 Peter Hellmann. All Rights Reserved.\n\n";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "bg-color", "int,int,int", "red,green,blue" }, { "banner", "URL", "image that will be drawn by laser" }, { "banner-x-pos", "int", "x position of banner" }, { "banner-y-pos", "int", "y position of banner" }, { "banner-top-os", "int", "banner frame width top" }, { "banner-bottom-os", "int", "banner frame width bottom" }, { "banner-left-os", "int", "banner frame width left" }, { "banner-right-os", "int", "banner frame width right" }, { "laser-origin-x-pos", "int", "x pos of laser origin" }, { "laser-origin-y-pos", "int", "y pos of laser origin" }, { "laser-origin-region", "int,int,int,int", "x,y,width,height of region (only used if laser-origin-type = RANDOM" }, { "laser-origin-type", "string", "valid types are: FIXED or RANDOM" }, { "laser-x-step", "int", "x increment of laser x-pos" }, { "laser-y-step", "int", "y increment of laser y-pos" }, { "laser-type", "string", "type of laser: NATURAL or COLOR or RAINBOW" }, { "laser-beam-color", "int,int,int", "red,green,blue (only used if laser-type COLOR)" }, { "laser-spot-color", "int,int,int", "red,green,blue (can be -1,-1,-1 to be set automatic)" }, { "laser-beam-spot-aspekt", "double", "any value between 0.01 and 1 (only used if laser-spot-color is set automatic" }, { "laser-beam-hue-increment", "double", "any value between 0.001 and 0.49 (only used if laser-type is RAINBOW" }, { "thread-sleep", "int", "value between 1 and 1000 (milliseconds)" }, { "sequence-sleep", "int", "value between 1 and 20 (seconds)" }, { "link-url", "string", "URL to link to" }, { "link-target-frame", "string", "URL link target frame (default: _top)" }, { "link-enable", "int", "URL link enable/disable (default: 0)" } };
    }
    
    public void init() {
        this.bg_color = this.getParameter("bg-color");
        this.banner = this.getParameter("banner");
        this.banner_x_pos = Integer.parseInt(this.getParameter("banner-x-pos"));
        this.banner_y_pos = Integer.parseInt(this.getParameter("banner-y-pos"));
        this.banner_top_os = Integer.parseInt(this.getParameter("banner-top-os"));
        this.banner_bottom_os = Integer.parseInt(this.getParameter("banner-bottom-os"));
        this.banner_left_os = Integer.parseInt(this.getParameter("banner-left-os"));
        this.banner_right_os = Integer.parseInt(this.getParameter("banner-right-os"));
        this.laser_origin_x_pos = Integer.parseInt(this.getParameter("laser-origin-x-pos"));
        this.laser_origin_y_pos = Integer.parseInt(this.getParameter("laser-origin-y-pos"));
        this.laser_origin_region = this.getParameter("laser-origin-region");
        this.laser_origin_type = this.getParameter("laser-origin-type");
        this.laser_x_step = Integer.parseInt(this.getParameter("laser-x-step"));
        this.laser_y_step = Integer.parseInt(this.getParameter("laser-y-step"));
        this.laser_type = this.getParameter("laser-type");
        this.laser_beam_color = this.getParameter("laser-beam-color");
        this.laser_spot_color = this.getParameter("laser-spot-color");
        this.laser_beam_spot_aspekt = Float.valueOf(this.getParameter("laser-beam-spot-aspekt"));
        this.laser_beam_hue_increment = Float.valueOf(this.getParameter("laser-beam-hue-increment"));
        this.thread_sleep = Integer.parseInt(this.getParameter("thread-sleep"));
        this.sequence_sleep = Integer.parseInt(this.getParameter("sequence-sleep"));
        this.URLName = this.getParameter("link-url");
        this.frame = this.getParameter("link-target-frame");
        if (this.URLName == null) {
            this.URLName = "http://www.w-4.de/~hellmann/";
        }
        if (this.frame == null) {
            this.frame = "_top";
        }
        this.link_enable = Integer.parseInt(this.getParameter("link-enable"));
        final StringTokenizer stringTokenizer = new StringTokenizer(this.laser_origin_region, ",");
        this.laser_origin_region_x = Integer.parseInt(stringTokenizer.nextToken());
        this.laser_origin_region_y = Integer.parseInt(stringTokenizer.nextToken());
        this.laser_origin_region_width = Integer.parseInt(stringTokenizer.nextToken());
        this.laser_origin_region_height = Integer.parseInt(stringTokenizer.nextToken());
        final StringTokenizer stringTokenizer2 = new StringTokenizer(this.bg_color, ",");
        this.bgCol = new Color(Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()));
        final StringTokenizer stringTokenizer3 = new StringTokenizer(this.laser_beam_color, ",");
        final int int1 = Integer.parseInt(stringTokenizer3.nextToken());
        final int int2 = Integer.parseInt(stringTokenizer3.nextToken());
        final int int3 = Integer.parseInt(stringTokenizer3.nextToken());
        this.laserColBeam = new Color(int1, int2, int3);
        this.hsbvals = Color.RGBtoHSB(int1, int2, int3, null);
        this.hueBeam = this.hsbvals[0];
        this.satBeam = this.hsbvals[1];
        final StringTokenizer stringTokenizer4 = new StringTokenizer(this.laser_spot_color, ",");
        final int int4 = Integer.parseInt(stringTokenizer4.nextToken());
        final int int5 = Integer.parseInt(stringTokenizer4.nextToken());
        final int int6 = Integer.parseInt(stringTokenizer4.nextToken());
        if (int4 == -1 && int5 == -1 && int6 == -1) {
            this.laserSpotColorAuto = true;
        }
        else {
            this.laserSpotColorAuto = false;
            this.laserColSpot = new Color(int4, int5, int6);
        }
        final Dimension size = this.size();
        final int width = size.width;
        final int height = size.height;
        this.m_dimImage = new Dimension(width, height);
        this.m_image = this.createImage(width, height);
        this.g = this.m_image.getGraphics();
        this.tracker = new MediaTracker(this);
        this.Banner = this.getImage(this.getDocumentBase(), this.banner);
        this.tracker.addImage(this.Banner, 2);
        this.AppletWidth = Integer.parseInt(this.getParameter("width"));
        this.AppletHeight = Integer.parseInt(this.getParameter("height"));
        this.LaserXPos = this.banner_x_pos + this.banner_left_os;
        this.LaserXWay = 0;
        this.idle_loops = this.sequence_sleep * 100 / this.thread_sleep;
        this.sleep = this.idle_loops;
        this.setBackground(this.bgCol);
    }
    
    public void DrawBeams(final Graphics graphics) {
        this.LaserYPos = this.banner_y_pos + this.banner_top_os;
        this.g.setColor(this.bgCol);
        this.g.fillRect(0, 0, this.AppletWidth, this.AppletHeight);
        this.g.drawImage(this.Banner, this.banner_x_pos, this.banner_y_pos, null);
        if (this.sleep < this.idle_loops) {
            ++this.sleep;
            return;
        }
        this.g.fillRect(this.LaserXPos, this.banner_y_pos + this.banner_top_os, this.BannerWidth - this.banner_left_os - this.banner_right_os - this.LaserXWay, this.BannerHeight - this.banner_top_os - this.banner_bottom_os);
        while (this.LaserYPos < this.banner_y_pos + this.BannerHeight - this.banner_bottom_os) {
            final int n = this.BannerWidth * (this.LaserYPos - this.banner_y_pos) + (this.LaserXPos - this.banner_x_pos);
            if (this.laser_type.equalsIgnoreCase("NATURAL")) {
                final int n2 = this.pixels[n] >> 16 & 0xFF;
                final int n3 = this.pixels[n] >> 8 & 0xFF;
                final int n4 = this.pixels[n] & 0xFF;
                if (n2 != this.bgCol.getRed() || n3 != this.bgCol.getGreen() || n4 != this.bgCol.getBlue()) {
                    final Color color = new Color(n2, n3, n4);
                    graphics.setColor(color);
                    graphics.drawLine(this.laser_origin_x_pos, this.laser_origin_y_pos, this.LaserXPos, this.LaserYPos);
                    if (this.laserSpotColorAuto) {
                        this.hsbvals = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
                        final float[] hsbvals = this.hsbvals;
                        final int n5 = 1;
                        hsbvals[n5] *= this.laser_beam_spot_aspekt;
                        this.laserColSpot = Color.getHSBColor(this.hsbvals[0], this.hsbvals[1], this.hsbvals[2]);
                    }
                    graphics.setColor(this.laserColSpot);
                    graphics.drawLine(this.LaserXPos, this.LaserYPos, this.LaserXPos, this.LaserYPos);
                }
            }
            if (this.laser_type.equalsIgnoreCase("COLOR")) {
                graphics.setColor(this.laserColBeam);
                graphics.drawLine(this.laser_origin_x_pos, this.laser_origin_y_pos, this.LaserXPos, this.LaserYPos);
                if (this.laserSpotColorAuto) {
                    this.hsbvals = Color.RGBtoHSB(this.laserColBeam.getRed(), this.laserColBeam.getGreen(), this.laserColBeam.getBlue(), null);
                    final float[] hsbvals2 = this.hsbvals;
                    final int n6 = 1;
                    hsbvals2[n6] *= this.laser_beam_spot_aspekt;
                    this.laserColSpot = Color.getHSBColor(this.hsbvals[0], this.hsbvals[1], this.hsbvals[2]);
                }
                graphics.setColor(this.laserColSpot);
                graphics.drawLine(this.LaserXPos, this.LaserYPos, this.LaserXPos, this.LaserYPos);
            }
            if (this.laser_type.equalsIgnoreCase("RAINBOW")) {
                graphics.setColor(this.laserColBeam = Color.getHSBColor(this.hueBeam, this.satBeam, this.hsbvals[2]));
                graphics.drawLine(this.laser_origin_x_pos, this.laser_origin_y_pos, this.LaserXPos, this.LaserYPos);
                if (this.laserSpotColorAuto) {
                    this.hsbvals = Color.RGBtoHSB(this.laserColBeam.getRed(), this.laserColBeam.getGreen(), this.laserColBeam.getBlue(), null);
                    final float[] hsbvals3 = this.hsbvals;
                    final int n7 = 1;
                    hsbvals3[n7] *= this.laser_beam_spot_aspekt;
                    this.laserColSpot = Color.getHSBColor(this.hsbvals[0], this.hsbvals[1], this.hsbvals[2]);
                }
                graphics.setColor(this.laserColSpot);
                graphics.drawLine(this.LaserXPos, this.LaserYPos, this.LaserXPos, this.LaserYPos);
            }
            this.LaserYPos += this.laser_y_step;
        }
        this.LaserYPos = this.banner_y_pos + this.banner_top_os;
        this.LaserXPos += this.laser_x_step;
        this.LaserXWay += this.laser_x_step;
        if (this.laser_type.equalsIgnoreCase("RAINBOW")) {
            this.hueBeam += this.laser_beam_hue_increment;
        }
        if (this.laser_type.equalsIgnoreCase("RAINBOW") && this.hueBeam > 1.0f) {
            this.hueBeam = 0.0f;
        }
        if (this.LaserXPos >= this.BannerWidth + this.banner_x_pos - this.banner_right_os) {
            this.LaserXPos = this.banner_x_pos + this.banner_left_os;
            this.LaserXWay = 0;
            this.sleep = 0;
            if (this.laser_origin_type.equalsIgnoreCase("RANDOM")) {
                this.laser_origin_x_pos = this.laser_origin_region_x + (int)(Math.random() * this.laser_origin_region_width);
                this.laser_origin_y_pos = this.laser_origin_region_y + (int)(Math.random() * this.laser_origin_region_height);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.AllImagesLoaded) {
            this.DrawBeams(graphics);
            if (this.m_image != null) {
                graphics.drawImage(this.m_image, 0, 0, null);
            }
        }
        else {
            graphics.setColor(Color.red);
            graphics.drawString("Loading image...", 50, this.AppletHeight / 2);
        }
    }
    
    public void start() {
        if (this.timer == null) {
            (this.timer = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.timer = null;
    }
    
    public void run() {
        try {
            this.tracker.waitForAll();
            this.AllImagesLoaded = !this.tracker.isErrorAny();
        }
        catch (InterruptedException ex) {}
        if (!this.AllImagesLoaded) {
            this.stop();
            (this.g = this.getGraphics()).setColor(Color.red);
            this.g.drawString("Error loading images!", 50, this.AppletHeight / 2);
            return;
        }
        this.BannerWidth = this.Banner.getWidth(this);
        this.BannerHeight = this.Banner.getHeight(this);
        this.pixels = new int[this.BannerWidth * this.BannerHeight];
        this.pg = new PixelGrabber(this.Banner, 0, 0, this.BannerWidth, this.BannerHeight, this.pixels, 0, this.BannerWidth);
        try {
            this.pg.grabPixels();
        }
        catch (Exception ex2) {}
        while (this.timer != null) {
            try {
                Thread.sleep(this.thread_sleep);
            }
            catch (InterruptedException ex3) {}
            this.repaint();
        }
        this.timer = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            this.AppletHost = new URL(this.getCodeBase(), "").getHost().toString();
            if (this.AppletHost.equalsIgnoreCase(this.HappyUserHost)) {
                if (this.link_enable == 1) {
                    this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.URLName), this.frame);
                }
            }
            else {
                this.getAppletContext().showStatus(this.AppletHost);
                try {
                    Thread.sleep(2000L);
                }
                catch (InterruptedException ex) {}
                this.URLName = "http://www.w-4.de/~hellmann/";
                this.frame = "_top";
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.URLName), this.frame);
            }
        }
        catch (MalformedURLException ex2) {
            this.getAppletContext().showStatus("Bad URL: " + this.URLName);
            return false;
        }
        return true;
    }
    
    public LaserBeamer() {
        this.AllImagesLoaded = false;
        this.BeamSpotFactor = 0.6f;
        this.URLName = new String("http://www.w-4.de/~hellmann/");
        this.frame = new String("_top");
        this.AppletHost = new String("");
        this.HappyUserHost = new String("www.brightman.de");
    }
}
