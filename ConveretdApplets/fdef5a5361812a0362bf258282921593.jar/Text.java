import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Text implements Runnable
{
    Thread thread;
    Vector text;
    int index;
    int fontsize;
    int speed;
    int width;
    int maxfontsize;
    
    public Text() {
        this.text = new Vector();
        this.index = 0;
        this.fontsize = 1;
        this.maxfontsize = 12;
        this.speed = 0;
        this.width = 0;
    }
    
    public Text(final int width) {
        this.index = 0;
        this.fontsize = 1;
        this.maxfontsize = 12;
        this.speed = 2;
        this.width = width;
        this.text = new Vector();
    }
    
    public final void addLine(final String s) {
        this.text.addElement(s);
    }
    
    public final void setFontSize(final int maxfontsize) {
        this.maxfontsize = maxfontsize;
    }
    
    public final void setSpeed(final int speed) {
        this.speed = speed;
    }
    
    public final int getSize() {
        return this.text.size();
    }
    
    public final String getText() {
        return this.text.elementAt(this.index);
    }
    
    public final int getFontSize() {
        return this.fontsize;
    }
    
    public final void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public final void run() {
        try {
            while (this.thread != null) {
                this.index = 0;
                while (this.index < this.text.size()) {
                    this.fontsize = 1;
                    while (this.fontsize + this.maxfontsize / 10 < this.maxfontsize) {
                        Thread.sleep(this.speed * 50 + 50);
                        this.fontsize += this.maxfontsize / 10;
                    }
                    Thread.sleep(this.speed * 1000 + 1000);
                    this.fontsize = this.fontsize;
                    while (this.fontsize < this.width) {
                        Thread.sleep(this.speed * 50 + 50);
                        this.fontsize *= 2;
                    }
                    ++this.index;
                }
            }
        }
        catch (Exception ex) {
            this.stop();
        }
    }
    
    public final void stop() {
        this.thread = null;
    }
}
