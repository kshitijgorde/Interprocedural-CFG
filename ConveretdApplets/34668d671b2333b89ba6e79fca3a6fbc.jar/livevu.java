import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.URL;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URLConnection;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class livevu extends Applet implements Runnable
{
    Image[] m_turbine;
    int m_index;
    int m_height;
    int m_width;
    int m_interval;
    int query_string;
    private volatile Thread m_engine;
    Graphics m_offScreen;
    Image m_offScreenImage;
    boolean m_isRunning;
    String m_errorMsg;
    String m_video_source;
    String image_filename;
    String urlString;
    URLConnection connection;
    
    public livevu() {
        this.m_turbine = new Image[1];
    }
    
    public void init() {
        this.m_isRunning = true;
        this.m_index = 0;
        this.m_errorMsg = null;
        this.query_string = 0;
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
        this.m_offScreenImage = this.createImage(this.m_width, this.m_height);
        this.m_offScreen = this.m_offScreenImage.getGraphics();
        if (this.m_turbine != null) {
            this.m_turbine[this.m_index] = null;
        }
        this.load_image();
    }
    
    public void load_image() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        URL url = null;
        try {
            if (this.m_turbine != null && this.m_turbine[this.m_index] != null) {
                this.m_turbine[this.m_index].flush();
                this.m_turbine[this.m_index] = null;
            }
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
        }
        catch (IOException ex) {
            System.out.println("load_image() MalformedURLException");
        }
        try {
            if (this.m_turbine != null && this.m_engine != null && login.codedString != null) {
                (this.connection = url.openConnection()).setAllowUserInteraction(false);
                this.connection.setDoInput(true);
                this.connection.setDoOutput(false);
                this.connection.setRequestProperty("Authorization", "Basic " + login.codedString);
                this.m_turbine[this.m_index] = this.createImage((ImageProducer)this.connection.getContent());
            }
        }
        catch (IOException ex2) {
            this.m_errorMsg = "No Video";
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
                }
            }
        }
        catch (InterruptedException ex3) {
            System.out.println("load_image() InterruptedException");
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.m_errorMsg != null) {
            graphics.setFont(new Font("bold_roman", 1, 36));
            graphics.drawString(this.m_errorMsg, this.getSize().width / 2 - 80, this.getSize().height / 2);
        }
        else {
            try {
                if (this.m_turbine != null && this.m_turbine[this.m_index] != null) {
                    this.m_offScreen.drawImage(this.m_turbine[this.m_index], 0, 0, this.m_width, this.m_height, this);
                    graphics.drawImage(this.m_offScreenImage, 0, 0, this);
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
            if (n++ > 100) {
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
