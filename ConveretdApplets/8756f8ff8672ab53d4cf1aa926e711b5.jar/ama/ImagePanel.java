// 
// Decompiled by Procyon v0.5.30
// 

package ama;

import javax.swing.JFrame;
import java.net.URLConnection;
import java.io.EOFException;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;

class ImagePanel extends JPanel implements Runnable
{
    public static final int AUTO = 0;
    private JDesktopPane desktopPane;
    private URL url;
    private ImagePanelObserver observer;
    private Image visibleImage;
    private int imageWidth;
    private int imageHeight;
    private Image drawnImage;
    private boolean stopped;
    private float zoom;
    private boolean resizeToFit;
    private int numDrawn;
    private long time;
    private long oldTime;
    
    public ImagePanel(final JDesktopPane desktopPane, final int n, final int n2, final URL url, final float zoom, final ImagePanelObserver observer) {
        this.drawnImage = null;
        this.stopped = false;
        this.resizeToFit = false;
        this.numDrawn = 0;
        this.time = 0L;
        this.oldTime = 0L;
        this.desktopPane = desktopPane;
        this.url = url;
        this.zoom = zoom;
        this.observer = observer;
        this.resizeToFit = (zoom == 0.0f);
        this.setBackground(Color.BLACK);
        this.setSize(n, n2);
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.visibleImage != null) {
            if (this.resizeToFit) {
                graphics.drawImage(this.visibleImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
            else {
                graphics.drawImage(this.visibleImage, 0, 0, this.imageWidth, this.imageHeight, this);
            }
            if (this.visibleImage != this.drawnImage) {
                this.drawnImage = this.visibleImage;
                ++this.numDrawn;
            }
            this.time = System.currentTimeMillis() / 1000L;
            if (this.time != this.oldTime) {
                final int n = (int)(this.time - this.oldTime);
                if (this.observer != null && !this.isStopped()) {
                    this.observer.updateFPS(this.numDrawn / n);
                }
                this.oldTime = this.time;
                this.numDrawn = 0;
            }
        }
        else {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean imageUpdate(final Image visibleImage, final int n, final int n2, final int n3, int imageWidth, int imageHeight) {
        if ((n & 0x20) != 0x0) {
            synchronized (this) {
                if (((imageWidth == 704 || imageWidth == 768) && imageHeight == 288) || ((imageWidth == 640 || imageWidth == 704 || imageWidth == 720) && imageHeight == 240)) {
                    imageHeight *= 2;
                }
                if (this.zoom > 0.0f) {
                    imageWidth *= (int)this.zoom;
                    imageHeight *= (int)this.zoom;
                }
                if (!this.resizeToFit && (imageWidth != this.getWidth() || imageHeight != this.getHeight())) {
                    this.setSize(imageWidth, imageHeight);
                }
                this.visibleImage = visibleImage;
                this.imageWidth = imageWidth;
                this.imageHeight = imageHeight;
                this.notify();
                this.repaint();
            }
            return false;
        }
        return true;
    }
    
    public synchronized Dimension getImageSize() {
        return new Dimension(this.imageWidth, this.imageHeight);
    }
    
    public void run() {
        byte[] array = new byte[50000];
        byte[] array2 = new byte[50000];
        while (true) {
            if (this.isStopped()) {
                try {
                    Thread.currentThread();
                    Thread.sleep(500L);
                }
                catch (InterruptedException ex3) {
                    System.out.println("Interrupted in sleep");
                }
            }
            else {
                this.repaint();
                try {
                    final URLConnection openConnection = this.url.openConnection();
                    System.out.println("URL: " + this.url);
                    if (openConnection instanceof HttpURLConnection) {
                        final HttpURLConnection httpURLConnection = (HttpURLConnection)openConnection;
                        final int responseCode = httpURLConnection.getResponseCode();
                        if (httpURLConnection.usingProxy()) {
                            System.out.println("Using proxy.");
                        }
                        else {
                            System.out.println("No proxy.");
                        }
                        String s = httpURLConnection.getResponseMessage();
                        System.out.println("Response: " + responseCode + " " + s);
                        if (responseCode != 200) {
                            if (responseCode == 503) {
                                s += ". There might be too many viewers.";
                            }
                            throw new IOException(s);
                        }
                    }
                    final MJPEGHandler mjpegHandler = (MJPEGHandler)new MJPEGHandler().getContent(openConnection);
                    while (!this.isStopped()) {
                        final int nextContentLength = mjpegHandler.getNextContentLength();
                        if (nextContentLength > array.length) {
                            array = new byte[nextContentLength + nextContentLength / 5];
                        }
                        mjpegHandler.read(array, nextContentLength);
                        final Image image = Toolkit.getDefaultToolkit().createImage(array, 0, nextContentLength);
                        synchronized (this) {
                            this.prepareImage(image, -1, -1, this);
                            while (true) {
                                try {
                                    this.wait();
                                }
                                catch (InterruptedException ex4) {
                                    System.out.println("Interrupted in wait");
                                    continue;
                                }
                                break;
                            }
                        }
                        final byte[] array3 = array2;
                        array2 = array;
                        array = array3;
                    }
                    mjpegHandler.getNextContent().close();
                }
                catch (EOFException ex5) {
                    this.observer.stop();
                    if (this.desktopPane != null) {
                        JOptionPane.showMessageDialog(this.desktopPane, "Video stream was closed.", "Video Stream", 2);
                    }
                }
                catch (IOException ex) {
                    this.observer.stop();
                    ex.printStackTrace(System.err);
                    if (this.desktopPane != null) {
                        JOptionPane.showMessageDialog(this.desktopPane, "Error reading video stream.\n" + ex.getMessage(), "Video Error", 0);
                    }
                }
                catch (Exception ex2) {
                    System.err.println("OOPS!");
                    ex2.printStackTrace(System.err);
                    synchronized (this) {
                        this.visibleImage = null;
                    }
                }
                this.numDrawn = 0;
                this.repaint();
                try {
                    Thread.currentThread();
                    Thread.sleep(1000L);
                }
                catch (InterruptedException ex6) {
                    System.out.println("Interrupted in sleep");
                }
            }
        }
    }
    
    private boolean isStopped() {
        return this.stopped;
    }
    
    public synchronized void setStopped(final boolean stopped) {
        this.stopped = stopped;
    }
    
    public synchronized void setURL(final URL url) {
        this.url = url;
    }
    
    public static void main(final String[] array) {
        try {
            final JFrame frame = new JFrame();
            final ImagePanel imagePanel = new ImagePanel(null, 1024, 768, new URL(array[0]), 1.0f, new ImagePanelObserver() {
                public void updateFPS(final int n) {
                    System.out.println("FPS: " + n);
                }
                
                public void stop() {
                }
            });
            new Thread(imagePanel).start();
            frame.getContentPane().add(imagePanel);
            frame.pack();
            frame.setVisible(true);
        }
        catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
