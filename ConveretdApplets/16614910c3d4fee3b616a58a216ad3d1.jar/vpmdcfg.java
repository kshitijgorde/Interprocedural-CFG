import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.IOException;
import java.awt.image.ImageProducer;
import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.net.URLConnection;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class vpmdcfg extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    public int box_x;
    public int box_y;
    public int box_w;
    public int box_h;
    public int box_action;
    public int box_en;
    public int box_dbg;
    public String version;
    Image[] m_turbine;
    Image gray;
    int m_index;
    int m_height;
    int m_width;
    int m_interval;
    int m_scale;
    int cbx;
    int cby;
    int cbx2;
    int cby2;
    int cbd;
    int obx;
    int oby;
    int obx2;
    int oby2;
    Color oclr;
    int mx;
    int my;
    int ms;
    int max_img_x;
    int query_string;
    protected Thread m_engine;
    Graphics m_offScreen;
    Image m_offScreenImage;
    String m_errorMsg;
    String m_video_source;
    String image_filename;
    String urlString;
    URLConnection connection;
    String authString;
    
    public vpmdcfg() {
        this.m_turbine = new Image[1];
    }
    
    private void check_cb_range() {
        if (this.cbx2 < 8) {
            this.cbx2 = 8;
        }
        if (this.cby2 < 8) {
            this.cby2 = 8;
        }
        if (this.cbx2 > 319) {
            this.cbx2 = 319;
        }
        if (this.cby2 > 239) {
            this.cby2 = 239;
        }
        if (this.cbx2 - this.cbx + 1 < 8) {
            this.cbx = this.cbx2 - 8 + 1;
        }
        if (this.cby2 - this.cby + 1 < 8) {
            this.cby = this.cby2 - 8 + 1;
        }
        if (this.cbx < 0) {
            this.cbx = 0;
        }
        if (this.cby < 0) {
            this.cby = 0;
        }
        this.box_x = this.cbx * this.m_scale;
        this.box_y = this.cby * this.m_scale;
        this.box_w = (this.cbx2 - this.cbx + 1) * this.m_scale;
        this.box_h = (this.cby2 - this.cby + 1) * this.m_scale;
    }
    
    public void draw_rect(final Graphics graphics) {
        graphics.drawImage(this.m_offScreenImage, 0, 0, this);
        if (this.ms > 0) {
            graphics.setColor(Color.blue);
            this.oclr = Color.blue;
        }
        else {
            graphics.setColor(Color.red);
            this.oclr = Color.red;
        }
        graphics.drawRect(this.cbx, this.cby, this.cbx2 - this.cbx, this.cby2 - this.cby);
        graphics.drawRect(this.cbx - 1, this.cby - 1, this.cbx2 - this.cbx + 2, this.cby2 - this.cby + 2);
        graphics.drawRect(this.cbx + 1, this.cby + 1, this.cbx2 - this.cbx - 2, this.cby2 - this.cby - 2);
        this.obx = this.cbx;
        this.oby = this.cby;
        this.obx2 = this.cbx2;
        this.oby2 = this.cby2;
    }
    
    public void init() {
        this.enableEvents(48L);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.m_index = 0;
        this.m_errorMsg = null;
        this.m_scale = 1;
        this.query_string = 0;
        final int box_x = -1;
        this.box_action = box_x;
        this.box_h = box_x;
        this.box_w = box_x;
        this.box_y = box_x;
        this.box_x = box_x;
        this.box_en = 1;
        this.version = "1.1";
        this.authString = login.codedString;
        this.m_video_source = this.getParameter("VIDEOSOURCE");
        final String parameter = this.getParameter("HEIGHT");
        if (parameter != null) {
            try {
                this.m_height = Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {
                this.m_height = this.getSize().height;
            }
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("WIDTH")) != null) {
            try {
                this.m_width = Integer.parseInt(parameter2);
            }
            catch (NumberFormatException ex2) {
                this.m_width = this.getSize().width;
            }
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("INTERVAL")) != null) {
            try {
                this.m_interval = Integer.parseInt(parameter3);
            }
            catch (NumberFormatException ex3) {
                this.m_interval = 0;
            }
        }
        this.m_offScreenImage = this.createImage(this.m_width - 1, this.m_height - 1);
        this.m_offScreen = this.m_offScreenImage.getGraphics();
        if (this.m_turbine != null) {
            this.m_turbine[this.m_index] = null;
        }
        this.cbx = 0;
        this.cby = 0;
        this.cbx2 = 319;
        this.cby2 = 239;
        this.cbd = 4;
        this.max_img_x = 319;
        final int n = -1;
        this.oby2 = n;
        this.obx2 = n;
        this.oby = n;
        this.obx = n;
        if (this.authString == null) {
            this.authString = login.login();
        }
        this.load_image();
    }
    
    public void load_image() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        try {
            URL url;
            if (this.m_video_source != null) {
                this.urlString = String.valueOf(String.valueOf(this.getCodeBase())) + "snapshot" + this.m_video_source + ".jpg?" + this.query_string;
                url = new URL(this.urlString);
                if (this.query_string++ > 10000) {
                    this.query_string = 0;
                }
            }
            else {
                this.urlString = String.valueOf(String.valueOf(this.getCodeBase())) + "snapshot0.jpg";
                url = new URL(this.urlString);
            }
            if (this.m_turbine != null && this.m_engine != null && this.authString != null) {
                (this.connection = url.openConnection()).setAllowUserInteraction(false);
                this.connection.setDoInput(true);
                this.connection.setDoOutput(false);
                this.connection.setRequestProperty("Authorization", "Basic " + this.authString);
                this.m_turbine[this.m_index] = this.createImage((ImageProducer)this.connection.getContent());
            }
        }
        catch (IOException ex) {
            System.out.println("load_image() IOException");
        }
        try {
            if (this.m_turbine != null && this.m_turbine[this.m_index] != null) {
                mediaTracker.addImage(this.m_turbine[this.m_index], 0);
                mediaTracker.waitForID(0);
                if (mediaTracker.isErrorAny()) {
                    this.m_errorMsg = "No Video";
                }
                else {
                    this.m_errorMsg = null;
                    this.m_scale = this.m_turbine[this.m_index].getWidth(this) / this.m_width;
                    if (this.box_action > 0 && this.box_action == 1) {
                        this.box_action = 0;
                        this.cbx = this.box_x / this.m_scale;
                        this.cby = this.box_y / this.m_scale;
                        this.cbx2 = (this.box_x + this.box_w - 1) / this.m_scale;
                        this.cby2 = (this.box_y + this.box_h - 1) / this.m_scale;
                    }
                    final GrayFilt grayFilt = new GrayFilt();
                    grayFilt.setRegion(this.cbx * this.m_scale, this.cby * this.m_scale, (this.cbx2 - this.cbx) * this.m_scale, (this.cby2 - this.cby) * this.m_scale);
                    this.gray = this.createImage(new FilteredImageSource(this.m_turbine[this.m_index].getSource(), grayFilt));
                    if (this.m_scale < 1) {
                        this.m_scale = 1;
                    }
                    this.box_dbg = this.m_scale;
                }
            }
        }
        catch (InterruptedException ex2) {
            System.out.println("load_image() InterruptedException");
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.box_en == 0) {
            return;
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.getGraphics();
        if (this.box_en == 0) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int ms = this.ms;
        if (this.ms == 0) {
            return;
        }
        if (this.ms == 1) {
            this.cbx += x - this.mx;
            this.cby += y - this.my;
            this.cbx2 += x - this.mx;
            this.cby2 += y - this.my;
            this.check_cb_range();
            this.mx = x;
            this.my = y;
            return;
        }
        final int n = ms >> 1;
        if ((n & 0x1) == 0x1) {
            this.cby = y;
        }
        final int n2 = n >> 1;
        if ((n2 & 0x1) == 0x1) {
            this.cbx2 = x;
        }
        final int n3 = n2 >> 1;
        if ((n3 & 0x1) == 0x1) {
            this.cby2 = y;
        }
        if ((n3 >> 1 & 0x1) == 0x1) {
            this.cbx = x;
        }
        this.check_cb_range();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.box_en == 0) {
            return;
        }
        this.ms = 0;
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.box_en == 0) {
            return;
        }
        this.ms = 0;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.box_en == 0) {
            return;
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final Graphics graphics = this.getGraphics();
        if (this.box_en == 0) {
            return;
        }
        this.mx = mouseEvent.getX();
        this.my = mouseEvent.getY();
        this.ms = 0;
        if (this.mx < this.cbx - this.cbd || this.mx > this.cbx2 + this.cbd || this.my < this.cby - this.cbd || this.my > this.cby2 + this.cbd) {
            return;
        }
        this.ms = 1;
        if (this.mx >= this.cbx - this.cbd && this.mx <= this.cbx + this.cbd) {
            this.ms |= 0x10;
        }
        else if (this.mx >= this.cbx2 - this.cbd && this.mx <= this.cbx2 + this.cbd) {
            this.ms |= 0x4;
        }
        if (this.my >= this.cby - this.cbd && this.my <= this.cby + this.cbd) {
            this.ms |= 0x2;
        }
        else if (this.my >= this.cby2 - this.cbd && this.my <= this.cby2 + this.cbd) {
            this.ms |= 0x8;
        }
        this.draw_rect(graphics);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.box_en == 0) {
            return;
        }
        this.ms = 0;
    }
    
    public void paint(final Graphics graphics) {
        if (this.m_errorMsg != null) {
            graphics.setFont(new Font("bold_roman", 1, 36));
            graphics.setXORMode(graphics.getColor());
            graphics.drawString(this.m_errorMsg, this.getSize().width / 2 - 80, this.getSize().height / 2);
        }
        else {
            try {
                if (this.m_turbine != null && this.m_turbine[this.m_index] != null) {
                    this.m_offScreen.drawImage(this.gray, 0, 0, this.m_width - 2, this.m_height - 2, this);
                    this.draw_rect(graphics);
                }
            }
            catch (Exception ex) {
                System.out.println("drawImage() Exception");
            }
        }
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        int n = 0;
        while (this.m_engine == currentThread) {
            this.repaint();
            try {
                if (this.m_errorMsg != null) {
                    Thread.sleep(this.m_interval * 1000 + 3000);
                }
                else {
                    Thread.sleep(this.m_interval * 1000 + 20);
                }
            }
            catch (InterruptedException ex) {}
            this.load_image();
            if (n++ > 1000) {
                n = 0;
                System.runFinalization();
                System.gc();
            }
        }
    }
    
    public void start() {
        if (this.m_engine == null) {
            (this.m_engine = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.m_engine != null) {
            this.m_engine = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
