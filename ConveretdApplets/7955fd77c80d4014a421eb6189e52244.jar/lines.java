import java.awt.Graphics;
import java.awt.Font;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class lines extends Canvas implements Runnable
{
    Font f;
    String[] mess;
    Thread killme;
    int numMessages;
    int i;
    
    public lines(final String[] mess, final int numMessages) {
        this.f = new Font("Helvetica", 1, 18);
        this.mess = new String[25];
        this.mess = mess;
        this.numMessages = numMessages;
    }
    
    public lines() {
        this.f = new Font("Helvetica", 1, 18);
        (this.mess = new String[25])[0] = "L";
        this.mess[1] = "loading";
        this.mess[2] = "Click a button";
        this.mess[3] = "Choose a language below";
        this.numMessages = 4;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(this.f);
        graphics.drawString(this.mess[this.i], 10, 25);
    }
    
    public void changeMessage(final String[] mess, final int numMessages) {
        this.mess = mess;
        this.numMessages = numMessages;
    }
    
    public void start() {
        if (this.killme == null) {
            (this.killme = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (this.killme != null) {
            this.i = 1;
            while (this.i < this.numMessages) {
                try {
                    Thread.sleep(1200L);
                }
                catch (InterruptedException ex) {}
                this.repaint();
                ++this.i;
            }
        }
        this.killme = null;
    }
    
    public void stop() {
        if (this.killme != null) {
            this.killme.stop();
            this.killme = null;
        }
    }
}
