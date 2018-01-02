import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Button;
import java.applet.AudioClip;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BoomButton extends Applet implements Runnable
{
    private Image[] img;
    public int state;
    private AudioClip clip1;
    Thread anim;
    Button goButton;
    String button_text;
    
    public void init() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        this.button_text = this.getParameter("button_text");
        this.add(this.goButton = new Button(this.button_text));
        this.clip1 = this.getAudioClip(this.getCodeBase(), "sounds/Explosion-3.au");
        mediaTracker.addImage(this.img[0] = this.getImage(this.getDocumentBase(), "images/home2.gif"), 0);
        mediaTracker.addImage(this.img[1] = this.getImage(this.getDocumentBase(), "images/bang1.gif"), 0);
        mediaTracker.addImage(this.img[2] = this.getImage(this.getDocumentBase(), "images/puff1.gif"), 0);
        mediaTracker.addImage(this.img[3] = this.getImage(this.getDocumentBase(), "images/puff2.gif"), 0);
        mediaTracker.addImage(this.img[4] = this.getImage(this.getDocumentBase(), "images/puff3.gif"), 0);
        mediaTracker.addImage(this.img[5] = this.getImage(this.getDocumentBase(), "images/puff4.gif"), 0);
        Thread.currentThread().setPriority(1);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {
            System.out.println("Interrupted while loading images.");
            System.exit(0);
        }
        Thread.currentThread().setPriority(5);
    }
    
    public void start() {
        this.anim = null;
        this.repaint();
    }
    
    public void stop() {
        if (this.anim != null) {
            this.anim.stop();
            this.anim = null;
        }
    }
    
    public void destroy() {
    }
    
    public boolean action(final Event event, final Object o) {
        if (o.equals(this.button_text)) {
            (this.anim = new Thread(this)).start();
            this.removeAll();
            return true;
        }
        return false;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.add(this.goButton);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        return true;
    }
    
    public void paint(final Graphics graphics) {
        switch (this.state) {
            case 1: {
                graphics.drawImage(this.img[1], 0, 0, this);
            }
            case 2: {
                graphics.drawImage(this.img[2], 0, 0, this);
            }
            case 3: {
                graphics.drawImage(this.img[3], 0, 0, this);
            }
            case 4: {
                graphics.drawImage(this.img[4], 0, 0, this);
            }
            case 5: {
                graphics.drawImage(this.img[5], 0, 0, this);
            }
            default: {}
        }
    }
    
    public void run() {
        if (this.clip1 != null) {
            this.clip1.play();
        }
        this.state = 1;
        while (this.state <= 5) {
            this.repaint();
            try {
                Thread.sleep(75L);
            }
            catch (InterruptedException ex) {}
            ++this.state;
        }
        this.state = 0;
        this.repaint();
    }
    
    public BoomButton() {
        this.img = new Image[6];
    }
}
