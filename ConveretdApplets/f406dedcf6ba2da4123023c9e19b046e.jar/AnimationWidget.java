import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class AnimationWidget extends Panel implements Runnable
{
    Viewer viewer;
    protected int frameNo;
    protected int lastFrame;
    protected Button start;
    protected Button stop;
    protected Button next;
    protected Button prev;
    protected Button first;
    protected Button last;
    protected TextField text;
    protected Thread engine;
    protected Scrollbar scroll;
    protected int delay;
    static final int BASEDELAY = 500;
    protected boolean auto;
    
    public AnimationWidget(final Viewer viewer) {
        this.frameNo = 0;
        this.lastFrame = 9999;
        this.delay = 500;
        this.auto = true;
        this.viewer = viewer;
    }
    
    void setFrameNo(final int frameNo) {
        this.frameNo = frameNo;
        this.viewer.putFrameNo(frameNo);
    }
    
    public void setLastFrame(final int n) {
        this.lastFrame = n;
        if (this.frameNo > n) {
            this.frameNo = n;
        }
    }
    
    public void start() {
        if (this.engine != null || this.frameNo >= this.lastFrame) {
            this.setFrameNo(0);
        }
        this.stop();
        (this.engine = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.engine != null) {
            this.engine.stop();
            this.engine = null;
        }
    }
    
    public void setAutoPlay(final boolean auto) {
        this.auto = auto;
        if (auto) {
            this.start();
        }
    }
    
    public void run() {
        while (this.auto) {
            try {
                for (int i = 0; i <= this.lastFrame; ++i) {
                    Thread.sleep(this.delay);
                    this.setFrameNo(i);
                }
            }
            catch (InterruptedException ex) {}
        }
        while (this.frameNo < this.lastFrame) {
            this.setFrameNo(this.frameNo + 1);
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void next() {
        this.stop();
        if (this.frameNo < this.lastFrame) {
            this.setFrameNo(this.frameNo + 1);
        }
    }
    
    public void prev() {
        this.stop();
        if (this.frameNo > 0) {
            this.setFrameNo(this.frameNo - 1);
        }
    }
    
    public void first() {
        if (this.auto) {
            this.stop();
            this.start();
        }
        else {
            this.stop();
        }
        this.setFrameNo(0);
    }
}
