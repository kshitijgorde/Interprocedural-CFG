import java.applet.AudioClip;
import java.awt.Image;
import java.awt.MediaTracker;
import java.util.Stack;
import java.util.Hashtable;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class RealMedia extends Component implements Runnable
{
    private Loadable lb;
    private Hashtable htImage;
    private Hashtable htsound;
    private Stack st1;
    private Stack st2;
    private int delay;
    private int size;
    private boolean loadFlag;
    private MediaTracker mt;
    private boolean loading;
    private Thread pauseThread;
    
    public RealMedia(final Loadable lb) {
        this.loadFlag = true;
        this.lb = lb;
        this.htImage = new Hashtable();
        this.htsound = new Hashtable();
        this.st1 = new Stack();
        this.st2 = new Stack();
        this.mt = new MediaTracker(this);
        this.pauseThread = new Thread(this);
        this.setDelay(40);
    }
    
    public void add(final String s, final String s2) {
        this.st1.push(this.getMedia(s));
        this.st2.push(s2);
    }
    
    public void addImage(final String s, final int n) {
        this.st1.push(this.getMedia(s));
        this.st2.push(s);
    }
    
    public Image get(final String s) {
        return this.htImage.get(s);
    }
    
    public int getCurrent() {
        return this.size - this.st1.size() + 1;
    }
    
    private Object getMedia(final String s) {
        if (this.isAudio(s)) {
            return new String(s);
        }
        return this.lb.loadImage(s);
    }
    
    public int getPercent() {
        return (int)(this.getCurrent() / this.size * 100.0);
    }
    
    public int getTotalFiles() {
        return this.size;
    }
    
    private boolean isAudio(final String s) {
        return s.endsWith(".au");
    }
    
    public boolean isLoaded() {
        return this.st1.empty();
    }
    
    public void load() {
        this.size = this.st1.size();
        this.pauseThread.start();
    }
    
    private void loadNext() {
        if (!this.st1.empty() && !this.loading) {
            this.loading = true;
            this.mt = new MediaTracker(this);
            final String pop = this.st1.pop();
            final String s = this.st2.pop();
            if (pop instanceof Image) {
                this.mt.addImage((Image)pop, 0);
                this.htImage.put(s, pop);
            }
            else if (pop instanceof String) {
                this.htsound.put(s, this.lb.loadAudio(pop));
            }
            try {
                this.mt.waitForAll();
            }
            catch (Exception ex) {}
        }
        this.loading = false;
    }
    
    public void play(final String s) {
        this.htsound.get(s).play();
    }
    
    public void run() {
        while (!this.st1.empty()) {
            this.loadNext();
            this.lb.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (Exception ex) {}
        }
        this.lb.startUp();
        this.pauseThread = null;
        System.out.println("Media Fully Loaded");
    }
    
    public void setDelay(final int delay) {
        this.delay = delay;
    }
}
