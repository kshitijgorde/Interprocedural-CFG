// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.applet.AudioClip;
import java.util.Vector;

public class AudioThread implements Runnable
{
    Vector \u00f9;
    Vector \u00fa;
    Vector \u00fb;
    Thread \u00fc;
    boolean \u00fd;
    
    public synchronized void playClip(final AudioClip audioClip) {
        if (!this.\u00fd) {
            this.\u00f9.addElement(audioClip);
            this.notify();
        }
    }
    
    public synchronized void finished() {
        this.\u00fd = true;
        this.notifyAll();
        try {
            if (this.\u00fc != null && this.\u00fc.isAlive()) {
                this.wait(1000L);
                if (this.\u00fc.isAlive()) {
                    this.\u00fc.stop();
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public AudioThread() {
        this.\u00fc = null;
        this.\u00fd = true;
        this.start();
    }
    
    public synchronized void loopClip(final AudioClip audioClip) {
        if (!this.\u00fd) {
            this.\u00fb.addElement(audioClip);
            this.notify();
        }
    }
    
    public void start() {
        if (this.\u00fd) {
            this.\u00fd = false;
            this.\u00f9 = new Vector();
            this.\u00fa = new Vector();
            this.\u00fb = new Vector();
            (this.\u00fc = new Thread(this)).start();
        }
    }
    
    public synchronized void stopClip(final AudioClip audioClip) {
        this.\u00fa.addElement(audioClip);
        this.notify();
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    if (this.\u00f9.size() > 0) {
                        final AudioClip audioClip = this.\u00f9.firstElement();
                        this.\u00f9.removeElementAt(0);
                        if (this.\u00fd) {
                            continue;
                        }
                        audioClip.play();
                    }
                    else if (this.\u00fb.size() > 0) {
                        final AudioClip audioClip2 = this.\u00fb.firstElement();
                        this.\u00fb.removeElementAt(0);
                        if (this.\u00fd) {
                            continue;
                        }
                        audioClip2.loop();
                    }
                    else if (this.\u00fa.size() > 0) {
                        final AudioClip audioClip3 = this.\u00fa.firstElement();
                        this.\u00fa.removeElementAt(0);
                        audioClip3.stop();
                    }
                    else {
                        synchronized (this) {
                            if (this.\u00fd && this.\u00fa.size() == 0) {
                                // monitorexit(this)
                                return;
                            }
                            if (this.\u00fd) {
                                continue;
                            }
                            this.wait();
                            continue;
                        }
                    }
                }
            }
            catch (Exception ex) {
                System.out.println("AudioThread: " + ex.toString());
                continue;
            }
            break;
        }
    }
}
