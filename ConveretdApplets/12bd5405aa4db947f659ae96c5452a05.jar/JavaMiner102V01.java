import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaMiner102V01 extends Applet implements Runnable
{
    public static final int ANZAHL_BILDER = 26;
    Thread thread;
    MediaTracker tracker;
    Image[] images;
    Image bslogo;
    boolean threadStarted;
    boolean bilderGeladen;
    
    public void init() {
        this.thread = new Thread(this);
        this.images = new Image[26];
        this.tracker = new MediaTracker(this);
    }
    
    public void start() {
        this.bslogo = this.getImage(this.getDocumentBase(), "images/bslog100.gif");
        this.tracker.addImage(this.bslogo, 0);
        for (int i = 0; i < 26; ++i) {
            String string = "images/JavaMiner";
            if (i < 9) {
                string += "0";
            }
            this.images[i] = this.getImage(this.getDocumentBase(), string + String.valueOf(i + 1) + ".gif");
            this.tracker.addImage(this.images[i], i);
        }
        this.thread.start();
        this.threadStarted = true;
        try {
            this.tracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        if (this.tracker.isErrorAny()) {
            System.out.println("Fehler beim Laden der Bilder!");
        }
        final JavaMiner102V01Spielfenster javaMiner102V01Spielfenster = new JavaMiner102V01Spielfenster(this.images, this.getDocumentBase(), this.bslogo);
        javaMiner102V01Spielfenster.resize(640, 480);
        javaMiner102V01Spielfenster.show();
    }
    
    public void run() {
        int n = 0;
        while (this.tracker.statusAll(true) == 1) {
            if (this.tracker.statusID(n, true) != 1) {
                ++n;
                this.repaint();
            }
        }
        this.bilderGeladen = true;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (!this.threadStarted) {
            graphics.drawString("Please wait...", 50, 50);
            return;
        }
        if (!this.bilderGeladen) {
            graphics.drawString("Loading Images, please wait...", 20, 10);
            for (int i = 0; i < 26; ++i) {
                graphics.setColor(Color.black);
                switch (this.tracker.statusID(i, true)) {
                    case 1: {
                        graphics.setColor(Color.red);
                        break;
                    }
                    case 8: {
                        graphics.setColor(Color.green);
                        break;
                    }
                }
                graphics.fillOval(i * 10 + 5, 50, 7, 7);
            }
            return;
        }
        graphics.drawString("JavaMiner Version 1.00", 50, 50);
        graphics.drawImage(this.images[0], 20, 50, this);
        graphics.drawImage(this.images[1], 220, 50, this);
    }
    
    public String getAppletInfo() {
        return "JavaMiner\nCopyright (c) 1999-2000 by Matthias Burg\neMail: Matthias@burgsoft.de\nInternet: www.burgsoft.de\nAll rights reserved.";
    }
}
