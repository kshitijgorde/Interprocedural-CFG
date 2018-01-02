import java.util.Random;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.LayoutManager;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Figurines extends Applet implements Runnable
{
    private Thread loader;
    private Thread demo;
    private Image background;
    private int demoDelay;
    
    public Figurines() {
        this.demoDelay = -1;
        System.out.println(this.getAppletInfo());
    }
    
    public String getAppletInfo() {
        return "Figurines, Version 2.0" + System.getProperty("line.separator") + "Copyright (c) 1999 by R\u00fcdiger Appel, All Rights Reserved" + System.getProperty("line.separator") + "See also: http://www.3quarks.com";
    }
    
    public void init() {
        this.setLayout(null);
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("BackgroundColor"), ",");
            if (stringTokenizer.countTokens() == 1) {
                final String trim = stringTokenizer.nextToken().trim();
                this.setBackground(new Color(Integer.parseInt(trim.substring(1, 3), 16), Integer.parseInt(trim.substring(3, 5), 16), Integer.parseInt(trim.substring(5, 7), 16)));
            }
            else {
                this.setBackground(new Color(Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim()), Integer.parseInt(stringTokenizer.nextToken().trim())));
            }
        }
        catch (Exception ex) {}
        try {
            this.demoDelay = Integer.parseInt(this.getParameter("DemoDelay").trim());
        }
        catch (Exception ex2) {}
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.background != null) {
            graphics.drawImage(this.background, 0, 0, this);
        }
    }
    
    public Image getBackgroundImage() {
        return this.background;
    }
    
    public void start() {
        (this.loader = new Thread(this)).start();
    }
    
    public void stop() {
        this.stopDemo();
        if (this.loader != null) {
            this.loader.stop();
            this.loader = null;
        }
        for (int i = 0; i < this.countComponents(); ++i) {
            ((Figurine)this.getComponent(i)).stop();
        }
    }
    
    public void stopDemo() {
        this.demoDelay = -1;
        if (this.demo != null) {
            this.demo.stop();
            this.demo = null;
        }
    }
    
    public void run() {
        if (Thread.currentThread() == this.loader) {
            final MediaTracker mediaTracker = new MediaTracker(this);
            this.createBackground(mediaTracker);
            for (int n = 1; this.createFigurine(mediaTracker, n); ++n) {}
            this.showStatus("done");
            if (this.demoDelay >= 0) {
                (this.demo = new Thread(this)).start();
            }
        }
        if (Thread.currentThread() == this.demo) {
            try {
                Thread.sleep(1000 * this.demoDelay);
                while (true) {
                    final Random random = new Random();
                    ((Figurine)this.getComponent(Math.abs(random.nextInt()) % this.countComponents())).toggle();
                    Thread.sleep(2000 + random.nextInt() % 1000);
                }
            }
            catch (InterruptedException ex) {}
        }
    }
    
    private void createBackground(final MediaTracker mediaTracker) {
        if (this.background != null) {
            return;
        }
        this.showStatus("loading background...");
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("BackgroundImage"), ",");
            final Image image = this.getImage(this.getCodeBase(), stringTokenizer.nextToken().trim());
            mediaTracker.addImage(image, 0);
            mediaTracker.waitForID(0);
            if (mediaTracker.isErrorID(0)) {
                return;
            }
            int int1 = 0;
            int int2 = 0;
            int int3 = 0;
            int int4 = 0;
            if (stringTokenizer.countTokens() >= 4) {
                int1 = Integer.parseInt(stringTokenizer.nextToken().trim());
                int2 = Integer.parseInt(stringTokenizer.nextToken().trim());
                int3 = Integer.parseInt(stringTokenizer.nextToken().trim());
                int4 = Integer.parseInt(stringTokenizer.nextToken().trim());
            }
            final int n = image.getWidth(this) - int1 - int3;
            final int n2 = image.getHeight(this) - int2 - int4;
            if (n < 1 || n2 < 1) {
                return;
            }
            final int width = this.size().width;
            final int height = this.size().height;
            int n3 = (width - int1 - int3) / n;
            int n4 = (height - int2 - int4) / n2;
            if (int1 + n3 * n + int3 < width) {
                ++n3;
            }
            if (int2 + n4 * n2 + int4 < height) {
                ++n4;
            }
            final Image image2 = this.createImage(width, height);
            final Graphics graphics = image2.getGraphics();
            if (int1 > 0 && int2 > 0) {
                final Image image3 = this.createImage(int1, int2);
                image3.getGraphics().drawImage(image, 0, 0, this);
                graphics.drawImage(image3, 0, 0, this);
            }
            if (int1 > 0) {
                final Image image4 = this.createImage(int1, n2);
                image4.getGraphics().drawImage(image, 0, -int2, this);
                for (int i = 0; i < n4; ++i) {
                    graphics.drawImage(image4, 0, int2 + i * n2, this);
                }
            }
            if (int2 > 0) {
                final Image image5 = this.createImage(n, int2);
                image5.getGraphics().drawImage(image, -int1, 0, this);
                for (int j = 0; j < n3; ++j) {
                    graphics.drawImage(image5, int1 + j * n, 0, this);
                }
            }
            final Image image6 = this.createImage(n, n2);
            image6.getGraphics().drawImage(image, -int1, -int2, this);
            for (int k = 0; k < n4; ++k) {
                for (int l = 0; l < n3; ++l) {
                    graphics.drawImage(image6, int1 + l * n, int2 + k * n2, this);
                }
            }
            if (int1 > 0 && int4 > 0) {
                final Image image7 = this.createImage(int1, int4);
                image7.getGraphics().drawImage(image, 0, -(n2 + int2), this);
                graphics.drawImage(image7, 0, height - int4, this);
            }
            if (int3 > 0 && int2 > 0) {
                final Image image8 = this.createImage(int3, int2);
                image8.getGraphics().drawImage(image, -(n + int1), 0, this);
                graphics.drawImage(image8, width - int3, 0, this);
            }
            if (int4 > 0) {
                final Image image9 = this.createImage(n, int4);
                image9.getGraphics().drawImage(image, -int1, -(n2 + int2), this);
                for (int n5 = 0; n5 < n3; ++n5) {
                    graphics.drawImage(image9, int1 + n5 * n, height - int4, this);
                }
            }
            if (int3 > 0) {
                final Image image10 = this.createImage(int3, n2);
                image10.getGraphics().drawImage(image, -(n + int1), -int2, this);
                for (int n6 = 0; n6 < n4; ++n6) {
                    graphics.drawImage(image10, width - int3, int2 + n6 * n2, this);
                }
            }
            if (int3 > 0 && int4 > 0) {
                final Image image11 = this.createImage(int3, int4);
                image11.getGraphics().drawImage(image, -(n + int1), -(n2 + int2), this);
                graphics.drawImage(image11, width - int3, height - int4, this);
            }
            while ((this.checkImage(image2, this) & 0x20) != 0x20) {
                Thread.currentThread();
                Thread.sleep(50L);
            }
            this.background = image2;
        }
        catch (Exception ex) {}
        this.repaint();
    }
    
    public boolean createFigurine(final MediaTracker mediaTracker, final int n) {
        if (n <= this.countComponents()) {
            return true;
        }
        try {
            this.showStatus("Load figurine " + n);
            final StringTokenizer stringTokenizer = new StringTokenizer(this.getParameter("Figurine" + ((n < 10) ? "0" : "") + n), ",");
            final String trim = stringTokenizer.nextToken().trim();
            final int int1 = Integer.parseInt(stringTokenizer.nextToken().trim());
            final int int2 = Integer.parseInt(stringTokenizer.nextToken().trim());
            final int int3 = Integer.parseInt(stringTokenizer.nextToken().trim());
            final int int4 = Integer.parseInt(stringTokenizer.nextToken().trim());
            final Image image = this.getImage(this.getCodeBase(), trim);
            mediaTracker.addImage(image, n);
            mediaTracker.waitForID(n);
            if (mediaTracker.isErrorID(n)) {
                return false;
            }
            final Figurine figurine = new Figurine(image, int3, int4);
            final int n2 = image.getWidth(this) / int3;
            final int height = image.getHeight(this);
            figurine.hide();
            this.add(figurine);
            figurine.reshape(int1, int2, n2, height);
            figurine.show();
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
}
