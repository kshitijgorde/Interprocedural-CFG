// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

import java.applet.AudioClip;
import java.util.Vector;

public class AudioThread implements Runnable
{
    boolean m_bStop;
    Vector m_clipsLoop;
    Vector m_clipsStart;
    Vector m_clipsStop;
    Thread m_this;
    
    public AudioThread() {
        this.m_this = null;
        this.m_bStop = true;
        this.start();
    }
    
    public synchronized void finished() {
        this.m_bStop = true;
        this.notifyAll();
        try {
            if (this.m_this != null && this.m_this.isAlive()) {
                this.wait(1000L);
                if (this.m_this.isAlive()) {
                    this.m_this.stop();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void loopClip(final AudioClip clip) {
        if (!this.m_bStop) {
            this.m_clipsLoop.addElement(clip);
            this.notify();
        }
    }
    
    public synchronized void playClip(final AudioClip clip) {
        if (!this.m_bStop) {
            this.m_clipsStart.addElement(clip);
            this.notify();
        }
    }
    
    public void run() {
        while (true) {
            try {
                if (this.m_clipsStart.size() > 0) {
                    final AudioClip clip = this.m_clipsStart.firstElement();
                    this.m_clipsStart.removeElementAt(0);
                    if (this.m_bStop) {
                        continue;
                    }
                    clip.play();
                }
                else if (this.m_clipsLoop.size() > 0) {
                    final AudioClip clip = this.m_clipsLoop.firstElement();
                    this.m_clipsLoop.removeElementAt(0);
                    if (this.m_bStop) {
                        continue;
                    }
                    clip.loop();
                }
                else if (this.m_clipsStop.size() > 0) {
                    final AudioClip clip = this.m_clipsStop.firstElement();
                    this.m_clipsStop.removeElementAt(0);
                    clip.stop();
                }
                else {
                    synchronized (this) {
                        if (this.m_bStop && this.m_clipsStop.size() == 0) {
                            return;
                        }
                        if (this.m_bStop) {
                            continue;
                        }
                        this.wait();
                    }
                }
            }
            catch (Exception e) {
                System.out.println("AudioThread: " + e.toString());
            }
        }
    }
    
    public void start() {
        if (this.m_bStop) {
            this.m_bStop = false;
            this.m_clipsStart = new Vector();
            this.m_clipsStop = new Vector();
            this.m_clipsLoop = new Vector();
            (this.m_this = new Thread(this)).start();
        }
    }
    
    public synchronized void stopClip(final AudioClip clip) {
        this.m_clipsStop.addElement(clip);
        this.notify();
    }
}
