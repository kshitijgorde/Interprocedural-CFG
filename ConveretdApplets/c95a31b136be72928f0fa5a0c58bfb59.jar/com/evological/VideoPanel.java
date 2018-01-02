// 
// Decompiled by Procyon v0.5.30
// 

package com.evological;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import java.io.InputStream;
import com.sun.image.codec.jpeg.JPEGCodec;
import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.awt.Image;
import java.net.URL;
import javax.swing.JPanel;

class VideoPanel extends JPanel implements Runnable
{
    private URL codebase;
    private String video;
    private Image image;
    private Thread thread;
    private boolean quitNow;
    
    public VideoPanel(final URL codebase, final String video) {
        this.codebase = null;
        this.video = null;
        this.image = null;
        this.thread = null;
        this.quitNow = false;
        this.codebase = codebase;
        this.video = video;
        this.setOpaque(true);
    }
    
    public void startStream() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stopStream() {
        if (this.thread != null) {
            this.quitNow = true;
            this.thread = null;
        }
    }
    
    public void run() {
        while (!this.quitNow) {
            try {
                final URL url = new URL(this.codebase, this.video);
                final HttpURLConnection huc = (HttpURLConnection)url.openConnection();
                if (huc.getResponseCode() != 200) {
                    return;
                }
                final String version = huc.getHeaderField("EvoCam-Version");
                if (version == null || !version.equals("3.5")) {
                    return;
                }
                final InputStream is = huc.getInputStream();
                final BufferedInputStream bis = new BufferedInputStream(is);
                while (!this.quitNow) {
                    this.readLine(bis);
                    this.readLine(bis);
                    this.readLine(bis);
                    final JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder((InputStream)bis);
                    synchronized (this) {
                        this.image = decoder.decodeAsBufferedImage();
                    }
                    this.repaint();
                }
                bis.close();
                huc.disconnect();
            }
            catch (Exception e) {
                try {
                    Thread.currentThread();
                    Thread.sleep(5000L);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public void paintComponent(final Graphics g) {
        synchronized (this) {
            if (this.image != null) {
                g.drawImage(this.image, 0, 0, null);
            }
            else {
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
            }
        }
    }
    
    public void readLine(final BufferedInputStream bis) {
        try {
            int next;
            do {
                next = bis.read();
            } while (next != 10);
        }
        catch (Exception e) {}
    }
}
