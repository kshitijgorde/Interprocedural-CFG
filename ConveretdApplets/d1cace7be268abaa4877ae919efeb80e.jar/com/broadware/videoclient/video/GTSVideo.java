// 
// Decompiled by Procyon v0.5.30
// 

package com.broadware.videoclient.video;

import java.awt.Color;
import java.awt.Graphics;
import java.net.URLConnection;
import java.net.Socket;
import java.io.InputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import com.broadware.videoclient.protocol.TCPConnect;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.io.IOException;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Dimension;
import java.io.DataInputStream;
import java.awt.Canvas;

public class GTSVideo extends Canvas implements Runnable
{
    private DataInputStream dis;
    private Dimension res;
    private Image theImage;
    private Image logoImage;
    private Image brandImage;
    private MediaTracker mediaTracker;
    private Thread thread;
    private Toolkit tk;
    private URL url;
    private boolean error;
    private boolean report;
    private boolean httpConnection;
    private boolean videoInterrupted;
    private boolean debug;
    private boolean showBranding;
    private byte[] jpegBuf;
    private static final int jpegBufSize = 65536;
    private static final String USER_AGENT = "GTS VideoClient/2.5";
    
    public GTSVideo(final Dimension res, final boolean httpConnection) {
        this(null, res, httpConnection);
    }
    
    public GTSVideo(final Dimension res) {
        this(null, res, false);
    }
    
    public GTSVideo(final URL url, final Dimension res) {
        this(url, res, false);
    }
    
    public GTSVideo(final URL url, final Dimension res, final boolean httpConnection) {
        this.dis = null;
        this.res = null;
        this.theImage = null;
        this.logoImage = null;
        this.brandImage = null;
        this.mediaTracker = null;
        this.thread = null;
        this.tk = null;
        this.url = null;
        this.error = false;
        this.report = false;
        this.httpConnection = false;
        this.videoInterrupted = false;
        this.debug = false;
        this.showBranding = false;
        this.res = res;
        this.url = url;
        this.httpConnection = httpConnection;
        this.theImage = null;
        this.thread = null;
        this.tk = Toolkit.getDefaultToolkit();
        this.videoInterrupted = true;
    }
    
    public void setSource(final URL url) {
        this.url = url;
        this.Debug("url = ".concat(String.valueOf(String.valueOf(url.toString()))));
    }
    
    public boolean isAlive() {
        return this.thread != null && this.thread.isAlive();
    }
    
    public boolean startVideo(final URL url) {
        this.Debug("url = ".concat(String.valueOf(String.valueOf(url.toString()))));
        if (this.thread != null) {
            return false;
        }
        this.url = url;
        this.error = false;
        this.start();
        return !this.error;
    }
    
    public void start() {
        if (this.thread != null) {
            return;
        }
        this.repaint();
        (this.thread = new Thread(this)).start();
    }
    
    public void run() {
        this.videoInterrupted = false;
        if (this.dis != null) {
            try {
                this.dis.close();
            }
            catch (IOException ex) {}
            this.dis = null;
        }
        if (!this.httpConnection) {
            this.openTCPConnection();
            if (this.dis == null) {
                System.err.println("TCP connection failed ... trying HTTP connection");
                this.httpConnection = true;
            }
        }
        if (this.httpConnection) {
            this.openHTTPConnection();
        }
        if (this.dis == null) {
            System.err.println("Unable to open connection ... quitting");
            this.stop();
            this.error = true;
            return;
        }
        final String boundStr = "--RaNd0m";
        final String clStr = "Content-length: ";
        final long startTime = System.currentTimeMillis();
        final long reportInterval = 5000L;
        long nextReport = startTime + reportInterval;
        long lastReport = startTime;
        int samples = 0;
        int bytes = 0;
        System.out.println("hello");
        while (true) {
            int length = 0;
            try {
                String line;
                do {
                    line = this.dis.readLine();
                    if (line == null) {
                        break;
                    }
                } while (!line.startsWith(boundStr));
                do {
                    line = this.dis.readLine();
                    if (line == null) {
                        break;
                    }
                    if (!line.startsWith(clStr)) {
                        continue;
                    }
                    length = new Integer(line.substring(clStr.length()));
                } while (line.length() > 0);
                if (length > 0) {
                    this.jpegBuf = new byte[length];
                    this.dis.readFully(this.jpegBuf, 0, length);
                }
            }
            catch (IOException e) {
                System.out.println("Problems reading video data from + ".concat(String.valueOf(String.valueOf(this.dis))));
                this.repaint();
                return;
            }
            if (length <= 0) {
                break;
            }
            Image newImage = null;
            Image scaledImage = null;
            newImage = this.tk.createImage(this.jpegBuf, 0, length);
            if (newImage == null) {
                System.out.println("null image");
            }
            else {
                this.mediaTracker = new MediaTracker(this);
                if (newImage.getHeight(this) != this.res.height || newImage.getWidth(this) != this.res.width) {
                    scaledImage = newImage.getScaledInstance(this.res.width, this.res.height, 2);
                    this.mediaTracker.addImage(scaledImage, 0);
                }
                else {
                    this.mediaTracker.addImage(newImage, 0);
                }
                try {
                    this.mediaTracker.waitForID(0);
                }
                catch (InterruptedException ex2) {}
            }
            if (newImage == null) {
                continue;
            }
            this.theImage = null;
            if (newImage.getHeight(this) != this.res.height || newImage.getWidth(this) != this.res.width) {
                this.theImage = scaledImage;
            }
            else {
                this.theImage = newImage;
            }
            this.showBranding = false;
            this.repaint();
            if (!this.report) {
                continue;
            }
            ++samples;
            bytes += length;
            final long t = System.currentTimeMillis();
            if (t <= nextReport) {
                continue;
            }
            final long lastTime = t - lastReport;
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("video fps = ").append(samples * 1000 / lastTime).append(", Kbps = ").append(bytes * 8 / lastTime).append(", running ").append((float)((t - startTime) / 1000)).append(" secs."))));
            samples = 0;
            bytes = 0;
            nextReport += reportInterval;
            lastReport = t;
        }
        System.out.println("Network connection closed or timed out");
        this.repaint();
        this.thread = null;
    }
    
    private void openTCPConnection() {
        final String host = this.url.getHost();
        final String uri = this.url.getFile();
        final String requestString = String.valueOf(String.valueOf(new StringBuffer("GET ").append(uri).append(" HTTP/1.0\r\nUser-Agent: ").append("GTS VideoClient/2.5").append("\r\n\r\n")));
        String response = null;
        String responsecode = null;
        boolean dataConnected = false;
        final int port = 80;
        try {
            final TCPConnect connect = new TCPConnect(host, port);
            connect.start();
            try {
                connect.join(5000L);
            }
            catch (InterruptedException ie) {
                connect.interrupt();
                throw new IOException("Connection timed out");
            }
            final Socket dataSocket;
            if ((dataSocket = connect.getSocket()) != null) {
                dataConnected = true;
            }
            else {
                dataConnected = false;
            }
            final BufferedInputStream bis = new BufferedInputStream(dataSocket.getInputStream());
            final DataOutputStream dos = new DataOutputStream(dataSocket.getOutputStream());
            this.dis = new DataInputStream(bis);
            dos.writeBytes(requestString);
            response = this.dis.readLine();
            final int firstSpace;
            if ((firstSpace = response.indexOf(" ")) == -1) {
                this.dis = null;
                return;
            }
            responsecode = response.substring(firstSpace + 1, firstSpace + 4);
            if (responsecode == null) {
                this.dis = null;
                return;
            }
            int rc = 0;
            try {
                rc = Integer.valueOf(responsecode);
            }
            catch (NumberFormatException e) {
                rc = -1;
            }
            if (rc < 200 || rc >= 204) {
                this.dis = null;
            }
        }
        catch (IOException e2) {
            System.err.println("Could not create TCP socket connection");
            this.dis = null;
        }
    }
    
    private void openHTTPConnection() {
        BufferedInputStream bis = null;
        this.dis = null;
        try {
            final URLConnection urlC = this.url.openConnection();
            urlC.setDefaultUseCaches(false);
            urlC.setUseCaches(false);
            urlC.connect();
            try {
                bis = new BufferedInputStream(urlC.getInputStream());
            }
            catch (IOException e) {
                System.err.println("Could not open HTTP connection ");
                return;
            }
            for (int avail = bis.available(); !this.videoInterrupted && avail == 0; avail = bis.available()) {
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex) {}
            }
            this.dis = new DataInputStream(bis);
            if (this.dis == null) {
                return;
            }
        }
        catch (IOException e2) {
            System.out.println("Could not open a network connection to ".concat(String.valueOf(String.valueOf(this.url))));
            this.dis = null;
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        if (this.dis != null) {
            try {
                this.dis.close();
            }
            catch (IOException ex) {}
            this.dis = null;
        }
        this.url = null;
        this.videoInterrupted = true;
        System.gc();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(Graphics g) {
        try {
            if (g == null) {
                g = this.getGraphics();
                g.setColor(Color.black);
                g.fillRect(0, 0, this.res.width, this.res.height);
                return;
            }
            if (this.showBranding && this.brandImage != null) {
                g.drawImage(this.brandImage, 0, 0, null);
                return;
            }
            if (this.mediaTracker.isErrorAny() || this.mediaTracker.statusID(0, false) != 8) {
                return;
            }
            g.drawImage(this.theImage, 0, 0, null);
            if (this.logoImage != null) {
                final int w = this.theImage.getWidth(this) - this.logoImage.getWidth(this);
                final int h = this.theImage.getHeight(this) - this.logoImage.getHeight(this);
                if (w > 0 && h > 0) {
                    g.drawImage(this.logoImage, w, h, null);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void setReport(final boolean report) {
        this.report = report;
    }
    
    public void setLogo(final URL logoURL) {
        try {
            this.logoImage = this.tk.getImage(logoURL);
        }
        catch (Exception e) {
            System.err.println("VideoClient unable to get image ".concat(String.valueOf(String.valueOf(logoURL.toString()))));
            this.logoImage = null;
        }
    }
    
    public void setBrand(final URL brandURL) {
        try {
            final MediaTracker tracker = new MediaTracker(this);
            Image newImage = null;
            Image scaledImage = null;
            boolean useScaledImage = false;
            if (brandURL == null) {
                this.brandImage = null;
                return;
            }
            newImage = this.tk.getImage(brandURL);
            tracker.addImage(newImage, 0);
            tracker.waitForID(0);
            if (tracker.isErrorAny()) {
                return;
            }
            if (newImage.getHeight(this) != this.res.height || newImage.getWidth(this) != this.res.width) {
                scaledImage = newImage.getScaledInstance(this.res.width, this.res.height, 2);
                tracker.addImage(scaledImage, 1);
                tracker.waitForID(1);
                if (tracker.isErrorAny()) {
                    return;
                }
                useScaledImage = true;
            }
            this.showBranding = true;
            this.brandImage = (useScaledImage ? scaledImage : newImage);
        }
        catch (Exception e) {
            System.err.println("VideoClient unable to get image ".concat(String.valueOf(String.valueOf(brandURL.toString()))));
            this.brandImage = null;
        }
    }
    
    public void setDebug(final boolean d) {
        this.debug = d;
    }
    
    public Canvas getCanvas() {
        return this;
    }
    
    public void showBranding(final boolean show) {
        this.showBranding = show;
    }
    
    private void Debug(final String statement) {
        if (this.debug) {
            System.err.println("VideoClient: ".concat(String.valueOf(String.valueOf(statement))));
        }
    }
}
