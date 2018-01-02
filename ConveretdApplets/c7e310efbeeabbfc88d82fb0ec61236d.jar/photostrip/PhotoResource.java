// 
// Decompiled by Procyon v0.5.30
// 

package photostrip;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.net.URL;
import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;

public class PhotoResource
{
    private String url;
    private ActionListener listener;
    private BufferedImage img;
    private boolean isLoaded;
    private String pageURL;
    private static GraphicsConfiguration configuration;
    
    public PhotoResource(final String url, final String pageURL) {
        this.isLoaded = false;
        this.url = url;
        this.pageURL = pageURL;
    }
    
    public void setCallback(final ActionListener actionListener) {
        this.listener = actionListener;
    }
    
    public BufferedImage getImage() {
        return this.img;
    }
    
    public String getPageURL() {
        return this.pageURL;
    }
    
    public synchronized boolean isLoaded() {
        return this.isLoaded;
    }
    
    private synchronized void setLoaded(final boolean loaded) {
        this.isLoaded = loaded;
    }
    
    private void alertCallback() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                if (PhotoResource.this.listener != null) {
                    PhotoResource.this.listener.actionPerformed(new ActionEvent(PhotoResource.this, -1, "loaded") {
                        public String paramString() {
                            return PhotoResource.this.url;
                        }
                    });
                }
            }
        });
    }
    
    void startLoading() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    PhotoResource.this.img = ImageIO.read(new URL(PhotoResource.this.url));
                    PhotoResource.this.img = PhotoResource.toCompatibleImage(PhotoResource.this.img);
                    PhotoResource.this.setLoaded(true);
                    PhotoResource.this.alertCallback();
                }
                catch (MalformedURLException ex) {
                    Logger.getLogger(PhotoResource.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (IOException ex2) {
                    Logger.getLogger(PhotoResource.class.getName()).log(Level.SEVERE, null, ex2);
                }
            }
        }).start();
    }
    
    public static BufferedImage toCompatibleImage(final BufferedImage image) {
        final BufferedImage compatibleImage = PhotoResource.configuration.createCompatibleImage(image.getWidth(), image.getHeight(), 3);
        final Graphics g = compatibleImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return compatibleImage;
    }
    
    private static BufferedImage createCompatibleImage(final int width, final int height) {
        final GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice screenDevice = environment.getDefaultScreenDevice();
        final GraphicsConfiguration configuration = screenDevice.getDefaultConfiguration();
        return configuration.createCompatibleImage(width, height);
    }
    
    static {
        PhotoResource.configuration = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }
}
